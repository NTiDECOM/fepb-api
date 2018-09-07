package br.org.fepb.api.service;

import br.org.fepb.api.domain.Oficina;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.repository.OficinaRepository;
import br.org.fepb.api.service.dto.OficinaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OficinaService {

    private final Logger log = LoggerFactory.getLogger(InscricaoService.class);

    private OficinaRepository oficinaRepository;

    private InscricaoRepository inscricaoRepository;

    public OficinaService(OficinaRepository oficinaRepository,
                          InscricaoRepository inscricaoRepository) {
        this.oficinaRepository = oficinaRepository;
        this.inscricaoRepository = inscricaoRepository;
    }

    public Oficina buscarOficina(Long id) {
        return this.oficinaRepository.getOne(id);
    }

    public Oficina salvarOficina(OficinaDTO o) {
        Oficina newOficina = new Oficina();
        newOficina.setNome(o.getNome());
        newOficina.setVagas(o.getVagas());

        return oficinaRepository.save(newOficina);
    }

    public List<OficinaDTO> listarOficinas() {
        List<OficinaDTO> oficinaDTOList = new ArrayList<OficinaDTO>();
        List<Oficina> oficinas = oficinaRepository.findAll();
        for(Oficina o : oficinas) {
            OficinaDTO dto = new OficinaDTO(o);
            Integer numInscricoes = inscricaoRepository.countAllByOficinaAndTrabalhadorFalse(o);
            dto.setNumInscricoes(numInscricoes);

            oficinaDTOList.add(dto);
        }

        return oficinaDTOList;

    }

}
