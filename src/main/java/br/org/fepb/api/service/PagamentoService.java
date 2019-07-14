package br.org.fepb.api.service;

import br.org.fepb.api.domain.Pagamento;
import br.org.fepb.api.repository.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PagamentoService {
    private final Logger log = LoggerFactory.getLogger(PagamentoService.class);

    private PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public Pagamento salvar(Pagamento p) {
        return this.pagamentoRepository.save(p);
    }

    public Pagamento buscarPorId(Long id) {
        Optional<Pagamento> op = this.pagamentoRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }

}
