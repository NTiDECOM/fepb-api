package br.org.fepb.api.service;

import br.org.fepb.api.domain.Departamento;
import br.org.fepb.api.domain.Evento;
import br.org.fepb.api.domain.Local;
import br.org.fepb.api.enumeration.TipoEventoEnum;
import br.org.fepb.api.repository.DepartamentoRepository;
import br.org.fepb.api.repository.EventoRepository;
import br.org.fepb.api.repository.LocalRepository;
import br.org.fepb.api.service.dto.DepartamentoDTO;
import br.org.fepb.api.service.dto.EventoDTO;
import org.apache.poi.ss.formula.functions.Even;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class EventoService {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatterUpdate = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public EventoDTO buscarPorId(Long id) {
        Optional<Evento> op = this.eventoRepository.findById(id);
        if (op.isPresent()) {
            Evento e = op.get();
            return new EventoDTO(e);
        }
        return null;
    }

    public List<Evento> listarEventos() {
        return this.eventoRepository.findAll();
    }

    public Evento salvarEvento(EventoDTO e) throws ParseException {

        Local l = null;

        if (e.getLocal() != null && e.getLocal().getId() != null) {
            Optional<Local> op = this.localRepository.findById(e.getLocal().getId());
            if (op.isPresent()) {
                l = op.get();
            }
        }

        Evento newEvento = new Evento();
        newEvento.setNome(e.getNome());
        newEvento.setTematica(e.getTematica());
        newEvento.setDataInicio(formatter.parse(e.getDataInicio()));
        newEvento.setDataFim(formatter.parse(e.getDataFim()));
        newEvento.setHoraInicio(e.getHoraInicio());
        newEvento.setHoraFim(e.getHoraFim());

        if (e.getTipo().equals(TipoEventoEnum.SEMINARIO.toString())) {
            newEvento.setTipo(TipoEventoEnum.SEMINARIO);
        } else if (e.getTipo().equals(TipoEventoEnum.PALESTRA.toString())) {
            newEvento.setTipo(TipoEventoEnum.PALESTRA);
        } else if (e.getTipo().equals(TipoEventoEnum.CONGRESSO.toString())) {
            newEvento.setTipo(TipoEventoEnum.CONGRESSO);
        } else if (e.getTipo().equals(TipoEventoEnum.SIMPOSIO.toString())) {
            newEvento.setTipo(TipoEventoEnum.SIMPOSIO);
        } else if (e.getTipo().equals(TipoEventoEnum.ENCONTRO.toString())) {
            newEvento.setTipo(TipoEventoEnum.ENCONTRO);
        } else if (e.getTipo().equals(TipoEventoEnum.ARTE.toString())) {
            newEvento.setTipo(TipoEventoEnum.ARTE);
        }

        if (l != null) {
            newEvento.setLocal(l);
        }

        if (e.getDepartamentos() != null) {
            for (DepartamentoDTO dto : e.getDepartamentos()) {
                if (dto.getId() != null) {
                    Optional<Departamento> op = this.departamentoRepository.findById(dto.getId());
                    if (op.isPresent()) {
                        Departamento d = op.get();
                        newEvento.getDepartamentos().add(d);
                    }
                }
            }
        }

        newEvento.setEnderecoInscricao(e.getEnderecoInscricao());
        newEvento.setContato(e.getContato());
        newEvento.setEstimativa(e.getEstimativa());
        newEvento.setObjetivo(e.getObjetivo());
        newEvento.setAlvo(e.getAlvo());

        return this.eventoRepository.save(newEvento);

    }

}
