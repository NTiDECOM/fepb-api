package br.org.fepb.api.service;

import br.org.fepb.api.domain.ConfiguracaoEvento;
import br.org.fepb.api.repository.ConfiguracaoEventoRepository;
import br.org.fepb.api.service.dto.ConfiguracaoEventoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConfiguracaoEventoService {

    private final Logger log = LoggerFactory.getLogger(ConfiguracaoEventoService.class);


    private ConfiguracaoEventoRepository configuracaoEventoRepository;

    public ConfiguracaoEventoService(ConfiguracaoEventoRepository configuracaoEventoRepository) {
        this.configuracaoEventoRepository = configuracaoEventoRepository;
    }

    public ConfiguracaoEventoDTO buscarPorCodigo(String codigo) {
        ConfiguracaoEvento c = this.configuracaoEventoRepository.findByCodigo(codigo);
        return new ConfiguracaoEventoDTO(c);
    }

}
