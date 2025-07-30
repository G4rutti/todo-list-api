package com.desafiobackendjunior.simplify.dtos;

import com.desafiobackendjunior.simplify.enuns.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRecordDto(@NotBlank String name, @NotBlank String description, @NotNull Priority priority) {
}
