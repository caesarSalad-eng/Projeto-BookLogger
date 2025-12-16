package com.br.Projeto_BookLogger.repository;

import com.br.Projeto_BookLogger.model.Ensinamentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnsinamentosRepository extends JpaRepository<Ensinamentos,Long> {

    List<Ensinamentos> findByLivroId(Long livroId);

}
