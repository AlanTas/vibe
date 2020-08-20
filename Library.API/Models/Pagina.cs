using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Library.API.Models
{
    public class Pagina
    {
        public Pagina(string json)
        {
            JObject jObject = JObject.Parse(json);
            JArray jArray = (JArray)jObject["lista"];
            foreach (var livro in jArray)
            {
                Livros.Add(new Livro(livro.ToString()));
            }

            Livros = Livros.Where(q => q.Disponivel == true).ToList();

            JToken jInfo = jObject["paginador"];

            Paginador = JsonConvert.DeserializeObject<Paginador>(jInfo.ToString());
        }

        public List<Livro> Livros { get; set; } = new List<Livro>();
        public Paginador Paginador { get; set; }
    }
}
