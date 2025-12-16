package com.br.Projeto_BookLogger.repository;

import com.br.Projeto_BookLogger.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
