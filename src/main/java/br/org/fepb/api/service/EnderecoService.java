package br.org.fepb.api.service;


import br.org.fepb.api.domain.Cidade;
import br.org.fepb.api.domain.Estado;
import br.org.fepb.api.repository.CidadeRepository;
import br.org.fepb.api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnderecoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado buscarEstadoPorId(Long id) {
        Optional<Estado> op = this.estadoRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }

    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    public List<Cidade> listarCidadesPorEstado(Estado e) {
        return cidadeRepository.findAllByEstado(e);
    }

}
