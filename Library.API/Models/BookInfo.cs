using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Library.API.Models
{
    public class BookInfo
    {
        public BookInfo(string json)
        {
            JObject jObject = JObject.Parse(json);
            JToken jInfo = jObject["informacoesPublicacao"];
            Descricao = (string)jObject["descricao"];
            Ano = (int)jInfo["ano"];
            Isbn = (string)jInfo["isbn"];
            Edicao = (int)jInfo["edicao"];
            Paginas = (int)jInfo["paginas"];
        }


        public string Descricao { get; set; }
        public int Ano { get; set; }
        public string Isbn { get; set; }
        public int Edicao { get; set; }
        public int Paginas { get; set; }

    }
}
