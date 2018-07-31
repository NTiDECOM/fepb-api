package br.org.fepb.api.service;

import br.org.fepb.api.domain.Departamento;
import br.org.fepb.api.repository.DepartamentoRepository;
import br.org.fepb.api.service.dto.DepartamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> listarDepartamentos() {
        return this.departamentoRepository.findAll();
    }

    public Departamento salvarDepartamento(DepartamentoDTO d) {
        Departamento newDepartamento = new Departamento();
        newDepartamento.setNome(d.getNome());
        newDepartamento.setEmail(d.getEmail());
        newDepartamento.setResponsavel(d.getResponsavel());

        return this.departamentoRepository.save(newDepartamento);
    }

}
