package com.darkmalygos.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopicos(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Long cursoId
) {
}
