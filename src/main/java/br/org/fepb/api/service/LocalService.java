package br.org.fepb.api.service;

import br.org.fepb.api.domain.Local;
import br.org.fepb.api.enumeration.TipoLocalEnum;
import br.org.fepb.api.repository.LocalRepository;
import br.org.fepb.api.service.dto.LocalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public List<Local> listarLocais() {
        return this.localRepository.findAll();
    }

    public Local salvarLocal(LocalDTO l) {
        Local newLocal = new Local();
        newLocal.setNome(l.getNome());

        if (l.getTipoLocal().equals(TipoLocalEnum.INTERNO.toString())) {
            newLocal.setTipoLocal(TipoLocalEnum.INTERNO);
        } else if (l.getTipoLocal().equals(TipoLocalEnum.EXTERNO.toString())) {
            newLocal.setTipoLocal(TipoLocalEnum.EXTERNO);
        }

        return this.localRepository.save(newLocal);
    }

}
