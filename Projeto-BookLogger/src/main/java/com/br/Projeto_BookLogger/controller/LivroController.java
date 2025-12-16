package com.br.Projeto_BookLogger.controller;

import com.br.Projeto_BookLogger.model.Livro;
import com.br.Projeto_BookLogger.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService  livroService){

        this.livroService = livroService;

    }

    @GetMapping
    public String listarLivros(Model model){

        model.addAttribute("listaLivros", livroService.findAll());

        return "livros/lista";

    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model){

        model.addAttribute("livro", new Livro());

        return "livros/formulario";

    }

    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro, @RequestParam("capaUpload") MultipartFile capaFile, RedirectAttributes ra){

        try{

            livroService.save(livro, capaFile);

            ra.addFlashAttribute("mensagemSucesso", "Livro e capa salvos com sucesso");

            return "redirect:/livros";

        }catch (RuntimeException e){

            ra.addFlashAttribute("mensagemErro", "Erro ao salvar livro: " + e.getMessage());

            return "redirect/livros/novo";

    }



    }

}
