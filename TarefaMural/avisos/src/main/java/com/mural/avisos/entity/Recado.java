package com.mural.avisos.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data

public class Recado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long idRecado;
    private String titulo;
    private String descricao;
    private String autor;
    private LocalDate dataPublicacao;

    @PrePersist
    private void prePersist() {
        this.dataPublicacao = LocalDate.now();
    }

}
