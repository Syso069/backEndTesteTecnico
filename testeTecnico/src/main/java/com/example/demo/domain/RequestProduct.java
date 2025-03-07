package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProduct(
        Long id,

        @NotBlank
        String nome,

        String descricao,

        @NotNull
        float preco,

        @NotNull
        boolean disponivel) {
}