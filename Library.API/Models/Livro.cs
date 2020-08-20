using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Library.API.Models
{
    public class Livro
    {

        public Livro(string json)
        {
            JObject jObject = JObject.Parse(json);
            Id = (int)jObject["id"];
            Autor = (string)jObject["autor"];
            Titulo = (string)jObject["titulo"];
            Disponivel = (bool)jObject["disponivel"];
        }

        public int Id { get; set; }
        public string Autor { get; set; }
        public string Titulo { get; set; }
        public bool Disponivel { get; set; }
    }
}
