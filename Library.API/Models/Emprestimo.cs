using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Library.API.Models
{
    public class Emprestimo
    {
        public Emprestimo(string nome, string telefone, Livro livro)
        {
            this.Nome = nome;
            this.Telefone = telefone;
            this.Livro = livro;

        }

        public string Nome { get; set; }
        public string Telefone { get; set; }
        public Livro Livro { get; set; }
    }
}
