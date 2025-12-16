package com.br.Projeto_BookLogger.main;

import com.br.Projeto_BookLogger.model.Ensinamentos;
import com.br.Projeto_BookLogger.model.TipoConteudo;
import com.br.Projeto_BookLogger.service.EnsinamentosService;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import com.br.Projeto_BookLogger.model.Livro;
import com.br.Projeto_BookLogger.model.StatusLeitura;
import com.br.Projeto_BookLogger.service.LivroService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.List;



@Component
public class main implements CommandLineRunner {

    private final Scanner scanner;

    private final LivroService livroService;

    private final EnsinamentosService ensinamentosService;

    public main(LivroService livroService, EnsinamentosService ensinamentosService){

        this.scanner = new Scanner(System.in);

        this.livroService = livroService;

        this.ensinamentosService = ensinamentosService;

    }

    public void run(String... args) throws Exception{

        mostrarMenu();

    }

    private void mostrarMenu(){

        int opcao = -1;

        while (opcao != 0){

            System.out.println("\n==Menu Principal==");
            System.out.println("1. Cadastrar Novo Livro");
            System.out.println("2. Listar Todos os Livros");
            System.out.println("3. Colocar Nota Pessoal em Livro");
            System.out.println("4. Adicionar Ensinamento a um Livro");
            System.out.println("5. Sair");
            System.out.println("Escolha: ");

            try{

                opcao = Integer.parseInt(scanner.nextLine());
                System.out.println("\n");

                switch (opcao){

                    case 1:

                        cadastrarNovoLivro();

                        break;

                    case 2:

                        listarLivros();

                        break;

                    case 3:

                        colocarNotaLivro();

                        break;

                    case 4:

                        adicionarEnsinamentoLivro();

                        break;

                    case 5:

                        System.out.println("\nSaindo...");

                        System.exit(0);

                        break;

                    default:

                        System.out.println("\nOpção Inválida. Tente Novamente");

                        break;


                }

            }catch (NumberFormatException e){

                System.err.println("\nErro: " + e.getMessage());

            }


        }

        scanner.close();

    }

    private void adicionarEnsinamentoLivro(){

        System.out.println("== Adicionar Ensinamento == ");

        listarLivros();

        System.out.print("\nDigite o ID do Livro: ");
        Long idLivro = Long.parseLong(scanner.nextLine());

        System.out.println("== Tipo de Conteúdos disponíveis ==");

        for (TipoConteudo tc : TipoConteudo.values()){

            System.out.println("- " + tc.name());

        }

        System.out.print("\nTipo (ex: CITACAO_DIRETA): ");
        String tipoInput = scanner.nextLine().toUpperCase();

        TipoConteudo tipo;

        try{

            tipo = TipoConteudo.valueOf(tipoInput);

        }catch (IllegalArgumentException e){

            System.err.println("\nTipo de Conteúdo Inválido. Operação cancelada");

            return;

        }

        System.out.print("\nConteúdo do Ensinamento (texto longo): ");
        String conteudo = scanner.nextLine();

        System.out.print("\nPágina (opcional, digite 0 se não souber): ");
        Integer pagina = Integer.parseInt(scanner.nextLine());

        Ensinamentos novoEnsinamento = new Ensinamentos();
        novoEnsinamento.setConteudo(conteudo);
        novoEnsinamento.setTipo(tipo);
        novoEnsinamento.setPagina(pagina);

        try{

            Ensinamentos salvo = ensinamentosService.salvarEnsinamento(novoEnsinamento, idLivro);
            System.out.printf("\nEnsinamento (%s) adicionado com sucesso ao Livro %d.%n", salvo.getTipo().name(), idLivro);

        }catch (RuntimeException e){

            System.err.println("\nErro ao salvar o ensinamento " + e.getMessage());

        }

    }

    private void colocarNotaLivro(){

        System.out.println("\nColocar Nota Pessoal");

        listarLivros();

        System.out.println("\nDigite o ID do Livro: ");
        Long livroId = Long.parseLong(scanner.nextLine());

        System.out.println("\nDigite a Nota (1 a 5): ");
        int nota = Integer.parseInt(scanner.nextLine());

        try{

            Livro atualizado = livroService.upadateNota(livroId, nota);

            System.out.printf("\nNota %d adicionada ao Livro '%s'.%n", atualizado.getNotaLivro(), atualizado.getTitulo());

        }catch (RuntimeException e){

            System.out.println("\nErro ao atualizar a nota: " + e.getMessage());

        }

    }


    private void listarLivros(){

        List<Livro> livros = livroService.findAll();

        if (livros.isEmpty()){

            System.out.println("\nNenhum Livro Cadastrado");

            return;

        }

        System.out.println("==Lista de Livros Cadastrados==");

        for (Livro livro : livros){

            System.out.printf("\nID: %d | Título: %s | Autor: %s | Status: %s%n", livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getStatusLeitura());

        }


    }

    private void cadastrarNovoLivro(){

        System.out.println("== Cadastro de Novo Livro ==");

        Livro novolivro = new Livro();

        System.out.println("\nTítulo: ");
        novolivro.setTitulo(scanner.nextLine());

        System.out.println("\nAutor: ");
        novolivro.setAutor(scanner.nextLine());

        System.out.println("\nEditoras: ");
        novolivro.setEditora(scanner.nextLine());

        System.out.println("\nAno de publicação: ");

        try {

            String anoInput = scanner.nextLine();

            novolivro.setDataPublicacao(Integer.parseInt(anoInput));
        }catch (NumberFormatException e){

            System.out.println("\nAtenção. Ano Inválido. Usando número padrão: 0.");

            novolivro.setDataPublicacao(0);

        }

        try{

            Livro salvo = livroService.save(novolivro, null);

            System.out.printf("\nLivro '%s' cadastrado com sucesso! (ID: %d)", salvo.getTitulo(), salvo.getId());

        }catch (RuntimeException e){

            System.err.println("\nErro ao salvar o livro: " + e.getMessage());

        }



    }



}
