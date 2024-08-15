package br.com.cadubcastro.netfilmes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadubcastro.netfilmes.dto.EpsodioDTO;
import br.com.cadubcastro.netfilmes.dto.SerieDTO;
import br.com.cadubcastro.netfilmes.service.SerieService;

@RequestMapping("/series")
@RestController
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return serieService.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return  serieService.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return  serieService.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return  serieService.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpsodioDTO> obterTodasTemporadas(@PathVariable Long id) {
        return  serieService.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpsodioDTO> obterTodasTemporadas(@PathVariable Long id, @PathVariable Long numero) {
        return  serieService.obterTodasTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero) {
        return  serieService.obterSeriesPorCategoria(nomeGenero);
    }
}