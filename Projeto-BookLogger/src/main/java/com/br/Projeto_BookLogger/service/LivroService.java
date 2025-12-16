package com.br.Projeto_BookLogger.service;

import com.br.Projeto_BookLogger.model.Livro;
import com.br.Projeto_BookLogger.repository.LivroRepository;
import org.springframework.beans.factory.   annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){

        this.livroRepository = livroRepository;

    }

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<Livro> findAll(){

        return livroRepository.findAll();

    }

    public Livro findById(Long id){

        return livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

    }

    public Livro save(Livro livro, MultipartFile capaFile){

        if (capaFile != null && !capaFile.isEmpty()){

            try{

                String fileName= UUID.randomUUID().toString() + "_" + capaFile.getOriginalFilename();

                File destinoFile = new File(uploadDir + fileName);

                capaFile.transferTo(destinoFile);

                livro.setCaminhoCapa(fileName);

            }catch (IOException e){

                throw new RuntimeException("Falha ao salvar a capa do livro");

            }

        }

        return livroRepository.save(livro);

    }

    public Livro upadateNota(Long id, int novaNova){

        Livro livro = findById(id);

        if (novaNova < 1 || novaNova > 5){

            throw new IllegalArgumentException("A nota deve estar entre 1 e 5");

        }

        livro.setNotaLivro(novaNova);

        return livroRepository.save(livro);

    }


}
