package br.com.cadubcastro.netfilmes.dto;

import br.com.cadubcastro.netfilmes.model.Categoria;

public record SerieDTO(Long id,
        String titulo,
        Integer totalTemporadas,
        Double avaliacao,
        Categoria genero,
        String atores,
        String poster,
        String sinopse) {

}
