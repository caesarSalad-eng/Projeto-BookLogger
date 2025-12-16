package com.br.Projeto_BookLogger.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Ensinamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private int pagina;

    private TipoConteudo tipo;

    private LocalDate dataAdicao = LocalDate.now();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id",  nullable = false)
    private Livro livro;


}
