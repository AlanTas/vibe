package com.vibe.bibliotecagothan.apis;
import com.vibe.bibliotecagothan.modelos.*;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;

public class LibraryServiceMock  {
    public Pagina obterAcervo(int pag) {
        List<LivroLista> livros = new ArrayList<>();
        livros.add(new LivroLista(10,
                "Homero",
                "Odisseia",
                true));
        livros.add(new LivroLista( 11,
                 "Fiódor Dostoiévski",
                "Os irmãos Karamázov",
                 true));
        livros.add(new LivroLista( 3,
                "F. Scott Fitzgerald",
                 "O Grande Gatsby",
                true));
        return new Pagina(livros,new Paginador(pag,5));

    }

    public Livro obterInfoLivro(int id) {
        return new Livro (
                 "Um homem sai de casa pela manhã, cumpre com as tarefas do dia e, pela noite, retorna ao lar. Foi em torno desse esqueleto enganosamente simples, quase banal, que James Joyce elaborou o que veio a ser o grande romance do século XX. Inspirado na Odisseia de Homero, Ulysses é ambientado em Dublin, e narra as aventuras de Leopold Bloom e seu amigo Stephen Dedalus ao longo do dia 16 de junho de 1904. Tal como o Ulisses homérico, Bloom precisa superar numerosos obstáculos e tentações até retornar ao apartamento na rua Eccles, onde sua mulher, Molly, o espera. Para criar esse personagem rico e vibrante, Joyce misturou numerosos estilos e referências culturais, num caleidoscópio de vozes que tem desafiado gerações de leitores e estudiosos ao redor do mundo. O culto em torno de Ulysses teve início antes mesmo de sua publicação em livro, quando trechos do romance começaram a aparecer num jornal literário dos EUA. Por conta dessas passagens, Ulysses foi banido nos Estados Unidos, numa acusação de obscenidade, dando início a uma longa pendenga legal, que seria resolvida apenas onze anos depois, com a liberação do romance em solo americano. Mas, para além das disputas e polêmicas, Ulysses segue como um divisor de águas por conta do êxito do autor no principal ponto do romance: esticar e moldar a língua inglesa ao limite, a fim de retirar disso um retrato fiel, divertido e comovente do que se convencionou chamar de o “homem moderno”. Em seu clássico estudo sobre a obra de James Joyce, Homem comum enfim, o crítico e escritor britânico Anthony Burgess afirma que, “se alguma vez houve um grande escritor popular, Joyce foi este escritor”. Guiado por esse espírito eminentemente democrático da escrita joyceana, Caetano Galindo realizou esta nova tradução de Ulysses, a fim de captar “a imensa gama de cores, registros, estilos, recursos e efeitos” de sua prosa revolucionária.",
                 1922,
                 "246-69-779-8474-8",
                 5,
                 1112
        );
    }

    public String emprestarLivro(int id,String nome, String telefone) {
        return "null";
    }
}
