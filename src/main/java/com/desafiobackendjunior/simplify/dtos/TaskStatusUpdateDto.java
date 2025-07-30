package com.desafiobackendjunior.simplify.dtos;

import com.desafiobackendjunior.simplify.enuns.Status;
import jakarta.validation.constraints.NotNull;

public record TaskStatusUpdateDto(@NotNull Status status) {
}
