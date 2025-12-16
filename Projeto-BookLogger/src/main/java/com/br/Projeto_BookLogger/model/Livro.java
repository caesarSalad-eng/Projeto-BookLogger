package com.br.Projeto_BookLogger.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String editora;
    private int dataPublicacao;

    @Enumerated(EnumType.STRING)
    private StatusLeitura statusLeitura;

    private int notaLivro;

    private String caminhoCapa;

    private LocalDate dataAdicao = LocalDate.now();

     @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.LAZY)
     private List<Ensinamentos> ensinamentos;


}
