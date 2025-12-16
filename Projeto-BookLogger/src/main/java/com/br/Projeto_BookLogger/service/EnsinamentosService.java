package com.br.Projeto_BookLogger.service;

import com.br.Projeto_BookLogger.model.Ensinamentos;
import com.br.Projeto_BookLogger.repository.EnsinamentosRepository;
import org.springframework.stereotype.Service;
import com.br.Projeto_BookLogger.model.Livro;

import java.util.List;

@Service
public class EnsinamentosService {

    private final EnsinamentosRepository ensinamentosRepository;

    private final LivroService livroService;


    public EnsinamentosService(EnsinamentosRepository ensinamentosRepository, LivroService livroService){

        this.ensinamentosRepository = ensinamentosRepository;

        this.livroService = livroService;

    }

    public Ensinamentos save(Ensinamentos ensinamentos){

        return ensinamentosRepository.save(ensinamentos);

    }

    public void deletar(Long id){

        ensinamentosRepository.deleteById(id);

    }

    public List<Ensinamentos> findByLivro(Long livroId){

        return ensinamentosRepository.findByLivroId(livroId);


    }

    public Ensinamentos salvarEnsinamento(Ensinamentos ensinamentos, Long livroId){

        Livro livro = livroService.findById(livroId);

        ensinamentos.setLivro(livro);

        return ensinamentosRepository.save(ensinamentos);

    }


}
