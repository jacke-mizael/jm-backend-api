package mizael.jackeline.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "INSCRICAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Column(name = "status_pagamento")
    private String statusPagamento;

    @Column(name = "comprovante_url")
    private String comprovanteUrl;

    @Column(name = "data_inscricao")
    private LocalDateTime dataInscricao;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;
}

