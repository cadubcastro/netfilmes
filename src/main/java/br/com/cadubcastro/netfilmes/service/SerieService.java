package br.com.cadubcastro.netfilmes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadubcastro.netfilmes.dto.EpsodioDTO;
import br.com.cadubcastro.netfilmes.dto.SerieDTO;
import br.com.cadubcastro.netfilmes.model.Categoria;
import br.com.cadubcastro.netfilmes.model.Serie;
import br.com.cadubcastro.netfilmes.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO>  obterTodasAsSeries() {
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository.encontrarEpisodiosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()) {

            Serie s = serie.get();

            return new SerieDTO(s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtores(),
                    s.getPoster(),
                    s.getSinopse());
        }

        return null;
    }


    public List<EpsodioDTO> obterTodasTemporadas(Long id) {

        Optional<Serie> serie = repository.findById(id);

        if(serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpsodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<EpsodioDTO> obterTodasTemporadasPorNumero(Long id, Long numero) {
        return  repository.obterEpisodioPorTemporada(id, numero).stream()
                .map(e -> new EpsodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo())).
                collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String nomeGenero) {

        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repository.findByGenero(categoria));
    }

    private List<SerieDTO> converteDados(List<Serie> series) {

        return series.stream()
                .map(s ->
                        new SerieDTO(s.getId(),
                                s.getTitulo(),
                                s.getTotalTemporadas(),
                                s.getAvaliacao(),
                                s.getGenero(),
                                s.getAtores(),
                                s.getPoster(),
                                s.getSinopse()
                        )
                ).collect(Collectors.toList());
    }
}