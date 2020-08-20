using Library.API.Models;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Net.Http;
using Newtonsoft.Json;
using System.Text;
using Newtonsoft.Json.Linq;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Caching.Memory;
using System;
using System.Threading.Tasks;

namespace Library.API.Controllers
{
    [ApiController]
    [Route("api")]
    public class LivroController : ControllerBase
    {
        private readonly string BaseURL = "https://bibliotecagotham.azurewebsites.net";

        private static readonly HttpClient client = new HttpClient();


        [HttpGet("[controller]/{id}")]
        public async Task<Livro> GetAsyncLivro(string id,
            [FromServices] IConfiguration config,
            [FromServices] IMemoryCache cache)

        {
            string responseString = await cache.GetOrCreate<Task<string>>(
                id, async context =>
                {
                    context.SetAbsoluteExpiration(TimeSpan.FromSeconds(120));
                    context.SetPriority(CacheItemPriority.High);
                    try
                    {
                        string temp = await client.GetStringAsync(BaseURL + "/Acervo/DetalharLivro/" + id);
                        System.Diagnostics.Debug.WriteLine("Não pegou do cache, pegou da request");
                        return temp;
                    }
                    catch (HttpRequestException e)
                    {
                        System.Diagnostics.Debug.WriteLine("\nException Caught!");
                        System.Diagnostics.Debug.WriteLine("Message :{0} ", e.Message);
                        return null;
                    }

                });

            return new Livro(responseString);
        }

        [HttpGet("[controller]/{id}/info")]
        public async Task<BookInfo> GetAsyncBookInfo(string id,
            [FromServices] IConfiguration config,
            [FromServices] IMemoryCache cache)
        {
            string responseString = await cache.GetOrCreate<Task<string>>(
                id, async context =>
                {
                    context.SetAbsoluteExpiration(TimeSpan.FromSeconds(120));
                    context.SetPriority(CacheItemPriority.High);
                    try
                    {
                        string temp = await client.GetStringAsync(BaseURL + "/Acervo/DetalharLivro/" + id);
                        System.Diagnostics.Debug.WriteLine("Não pegou do cache, pegou da request");
                        return temp;
                    }
                    catch (HttpRequestException e)
                    {
                        System.Diagnostics.Debug.WriteLine("\nException Caught!");
                        System.Diagnostics.Debug.WriteLine("Message :{0} ", e.Message);
                        return null;
                    }
                });

            return new BookInfo(responseString);
        }

        [HttpGet("acervo/{pagina}")]
        public async Task<Pagina> GetAsyncAcervo(string pagina)
        {

            var values = new Dictionary<string, int>
            {
                { "pagina", int.Parse(pagina)},
                { "totalPaginas", 0},
                { "registrosPorPagina", 0},
                { "totalRegistros", 0}
            };

            string body = JsonConvert.SerializeObject(values);


            var content = new StringContent(body, Encoding.UTF8, "application/json");
            try
            {
                var response = await client.PostAsync(BaseURL + "/Acervo/obterAcervo", content);
                var responseString = await response.Content.ReadAsStringAsync();
                //System.Diagnostics.Debug.WriteLine(responseString);
                return new Pagina(responseString);
            }
            catch (HttpRequestException e)
            {
                System.Diagnostics.Debug.WriteLine("\nException Caught!");
                System.Diagnostics.Debug.WriteLine("Message :{0} ", e.Message);
                return null;
            }


        }

        [HttpPost("[controller]/emprestar")]
        public async Task<string> PostAsyncEmprestar(JObject jsonResult,
            [FromServices] IConfiguration config,
            [FromServices] IMemoryCache cache)
        {
            int id = (int)jsonResult["id"];
            string nome = (string)jsonResult["nome"];
            string telefone = (string)jsonResult["telefone"];

            string responseString = await cache.GetOrCreate<Task<string>>(
                id.ToString(), async context =>
                {
                    context.SetAbsoluteExpiration(TimeSpan.FromSeconds(120));
                    context.SetPriority(CacheItemPriority.High);
                    try
                    {
                        string temp = await client.GetStringAsync(BaseURL + "/Acervo/DetalharLivro/" + id);
                        System.Diagnostics.Debug.WriteLine("Não pegou do cache, pegou da request");
                        return temp;
                    }
                    catch (HttpRequestException e)
                    {
                        System.Diagnostics.Debug.WriteLine("\nException Caught!");
                        System.Diagnostics.Debug.WriteLine("Message :{0} ", e.Message);
                        return null;
                    }
                });

            Livro livro =  new Livro(responseString);

            if (!livro.Disponivel)
            {
                return "Livro não encontra-se disponível";
            }

            Emprestimo emprestimo = new Emprestimo(nome, telefone, livro);

            string body = JsonConvert.SerializeObject(emprestimo);
            var content = new StringContent(body, Encoding.UTF8, "application/json");
            try { 
                var response = await client.PostAsync(BaseURL + "/Acervo/EmprestarLivro", content);
                var resString = await response.Content.ReadAsStringAsync();
                return resString;
            }
            catch (HttpRequestException e)
            {
                System.Diagnostics.Debug.WriteLine("\nException Caught!");
                System.Diagnostics.Debug.WriteLine("Message :{0} ", e.Message);
                return null;
            }

            //System.Diagnostics.Debug.WriteLine(json);

        }

    }


}
