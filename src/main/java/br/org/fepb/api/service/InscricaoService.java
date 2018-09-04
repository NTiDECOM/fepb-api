package br.org.fepb.api.service;

import br.org.fepb.api.domain.Inscricao;
import br.org.fepb.api.domain.Oficina;
import br.org.fepb.api.domain.Pessoa;
import br.org.fepb.api.enumeration.RestricaoAlimentarEnum;
import br.org.fepb.api.enumeration.SexoEnum;
import br.org.fepb.api.enumeration.TipoSanguineoEnum;
import br.org.fepb.api.repository.InscricaoRepository;
import br.org.fepb.api.repository.OficinaRepository;
import br.org.fepb.api.service.dto.InscricaoDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InscricaoService {

    private final Logger log = LoggerFactory.getLogger(InscricaoService.class);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatterUpdate = new SimpleDateFormat("yyyy-MM-dd");

    private InscricaoRepository inscricaoRepository;

    private OficinaRepository oficinaRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository,
                            OficinaRepository oficinaRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.oficinaRepository = oficinaRepository;
    }

    public boolean gerarExcel(ServletContext context) {

        final String FILE_NAME = context.getRealPath("") + "/INSCRICOES_AJE.xlsx";

        List<Inscricao> inscricoes = inscricaoRepository.findAll();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Inscritos");
        Object[][] datatypes = new Object[inscricoes.size() + 1][16];

        Object[] header = {
            "nome", "como_chamar", "tipo_pessoa", "sexo", "tipo_sanguineo", "email", "data_nascimento", "restricao_saude",
            "restricao_alimentar", "trabalhador", "telefone", "instituicao", "nome_coordenador", "email_coordenador",
            "oficina", "pago"
        };

        datatypes[0] = header;

        int idx = 0;
        for (Inscricao i : inscricoes) {
            idx++;
            Object[] obj = {
                i.getPessoa().getNome(),
                i.getPessoa().getComoChamar(),
                i.getPessoa().getTipoPessoa().toString(),
                i.getPessoa().getSexo().toString(),
                i.getPessoa().getTipoSanguineo().toString(),
                i.getPessoa().getEmail(),
                i.getPessoa().getDataNascimento().toString(),
                i.getPessoa().getRestricaoSaude(),
                i.getPessoa().getRestricaoAlimentar(),
                i.getTrabalhador().toString(),
                i.getTelefone(),
                i.getInstituicao(),
                i.getNomeCoordenador(),
                i.getEmailCoordenador(),
                (i.getOficina() != null) ? i.getOficina().getNome() : "",
                i.getPago().toString()
            };
            datatypes[idx] = obj;
        }

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            if (Files.exists(Paths.get(FILE_NAME))) {
                Files.delete(Paths.get(FILE_NAME));
            }
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

        return true;
    }

    public InscricaoDTO getInscricao(Long id) {
        Optional<Inscricao> i = this.inscricaoRepository.findById(id);
        InscricaoDTO dto = new InscricaoDTO(i.get());
        return dto;
    }

    public List<Inscricao> listarInscricoes() {
        return inscricaoRepository.findAll();
    }

    public Inscricao salvarInscricao(InscricaoDTO i) throws ParseException {

        Pessoa newPessoa = new Pessoa();
        newPessoa.setNome(i.getPessoa().getNome());
        newPessoa.setComoChamar(i.getPessoa().getComoChamar());
        newPessoa.setSexo((SexoEnum.MASCULINO.toString().equals(i.getPessoa().getSexo())) ? SexoEnum.MASCULINO : SexoEnum.FEMININO);

        if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.A_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_POSITIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.A_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.A_NEGATIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.B_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_POSITIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.B_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.B_NEGATIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.AB_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_POSITIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.AB_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.AB_NEGATIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.O_POSITIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.O_POSITIVO);
        } else if (i.getPessoa().getTipoSanguineo().toString()
            .equals(TipoSanguineoEnum.O_NEGATIVO.toString())) {
            newPessoa.setTipoSanguineo(TipoSanguineoEnum.O_NEGATIVO);
        }

        newPessoa.setEmail(i.getPessoa().getEmail());
        newPessoa.setDataNascimento(formatter.parse(i.getPessoa().getDataNascimento()));

        if (RestricaoAlimentarEnum.COME_CARNE.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
            newPessoa.setRestricaoAlimentar(RestricaoAlimentarEnum.COME_CARNE);
        } else if (RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
            newPessoa.setRestricaoAlimentar(RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA);
        } else if (RestricaoAlimentarEnum.VEGETARIANO.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
            newPessoa.setRestricaoAlimentar(RestricaoAlimentarEnum.VEGETARIANO);
        } else if (RestricaoAlimentarEnum.VEGANO.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
            newPessoa.setRestricaoAlimentar(RestricaoAlimentarEnum.VEGANO);
        }

        newPessoa.setRestricaoSaude(i.getPessoa().getRestricaoSaude());

        Inscricao newInscricao = new Inscricao();
        newInscricao.setInstituicao(i.getInstituicao());
        newInscricao.setEmailCoordenador(i.getEmailCoordenador());
        newInscricao.setNomeCoordenador(i.getNomeCoordenador());
        newInscricao.setTelefone(i.getTelefone());
        newInscricao.setTrabalhador(new Boolean(i.isTrabalhador()));
        newInscricao.setPago(new Boolean(false));

        newInscricao.setPessoa(newPessoa);

        if (i.getOficina() != null && i.getOficina().getId() != null) {
            Oficina o = oficinaRepository.getOne(i.getOficina().getId());
            newInscricao.setOficina(o);
        }
        return inscricaoRepository.save(newInscricao);

    }

    public Inscricao atualizarInscricao(InscricaoDTO i, Long id) throws ParseException {
        Inscricao iUpdate = inscricaoRepository.getOne(id);

        if (iUpdate != null) {
            iUpdate.getPessoa().setNome(i.getPessoa().getNome());
            iUpdate.getPessoa().setComoChamar(i.getPessoa().getComoChamar());
            iUpdate.getPessoa().setSexo((SexoEnum.MASCULINO.toString().equals(i.getPessoa().getSexo())) ? SexoEnum.MASCULINO : SexoEnum.FEMININO);

            if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.A_POSITIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.A_POSITIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.A_NEGATIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.A_NEGATIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.B_POSITIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.B_POSITIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.B_NEGATIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.B_NEGATIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.AB_POSITIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.AB_POSITIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.AB_NEGATIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.AB_NEGATIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.O_POSITIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.O_POSITIVO);
            } else if (i.getPessoa().getTipoSanguineo().toString()
                .equals(TipoSanguineoEnum.O_NEGATIVO.toString())) {
                iUpdate.getPessoa().setTipoSanguineo(TipoSanguineoEnum.O_NEGATIVO);
            }

            iUpdate.getPessoa().setEmail(i.getPessoa().getEmail());
            iUpdate.getPessoa().setDataNascimento(formatterUpdate.parse(i.getPessoa().getDataNascimento()));

            if (RestricaoAlimentarEnum.COME_CARNE.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
                iUpdate.getPessoa().setRestricaoAlimentar(RestricaoAlimentarEnum.COME_CARNE);
            } else if (RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
                iUpdate.getPessoa().setRestricaoAlimentar(RestricaoAlimentarEnum.NAO_COME_CARNE_VERMELHA);
            } else if (RestricaoAlimentarEnum.VEGETARIANO.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
                iUpdate.getPessoa().setRestricaoAlimentar(RestricaoAlimentarEnum.VEGETARIANO);
            } else if (RestricaoAlimentarEnum.VEGANO.toString().equals(i.getPessoa().getRestricaoAlimentar())) {
                iUpdate.getPessoa().setRestricaoAlimentar(RestricaoAlimentarEnum.VEGANO);
            }

            iUpdate.getPessoa().setRestricaoSaude(i.getPessoa().getRestricaoSaude());

            iUpdate.setInstituicao(i.getInstituicao());
            iUpdate.setEmailCoordenador(i.getEmailCoordenador());
            iUpdate.setNomeCoordenador(i.getNomeCoordenador());
            iUpdate.setTelefone(i.getTelefone());
            iUpdate.setTrabalhador(new Boolean(i.isTrabalhador()));
            iUpdate.setPago(new Boolean(i.isPago()));

            if (i.getOficina() != null && i.getOficina().getId() != null) {
                Oficina o = oficinaRepository.getOne(i.getOficina().getId());
                iUpdate.setOficina(o);
            }

            return inscricaoRepository.save(iUpdate);

        } else {
            return null;
        }

    }

}
