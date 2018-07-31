package br.org.fepb.api.web.rest;

import br.org.fepb.api.domain.Departamento;
import br.org.fepb.api.domain.Evento;
import br.org.fepb.api.domain.Local;
import br.org.fepb.api.service.DepartamentoService;
import br.org.fepb.api.service.EventoService;
import br.org.fepb.api.service.LocalService;
import br.org.fepb.api.service.dto.EventoDTO;
import br.org.fepb.api.service.dto.LocalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaResource {

    @Autowired
    private LocalService localService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventos")
    public List<Evento> listarEventos() {
        return this.eventoService.listarEventos();
    }

    @PostMapping("/eventos")
    public Evento salvarEvento(@RequestBody EventoDTO e) throws ParseException {
        return this.eventoService.salvarEvento(e);
    }

    @GetMapping("/locais")
    public List<Local> listarLocais() {
        return this.localService.listarLocais();
    }

    @PostMapping("/locais")
    public Local salvarLocal(@RequestBody LocalDTO l) {
        return this.localService.salvarLocal(l);
    }

    @GetMapping("/departamentos")
    public List<Departamento> listarDepartamentos() {
        return this.departamentoService.listarDepartamentos();
    }

}
