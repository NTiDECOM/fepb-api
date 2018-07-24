package br.org.fepb.api.reports;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import javax.servlet.ServletContext;
import java.net.URL;
import java.util.HashMap;

public class GenericReport {

    private String caminhoRelatorioCompilado;
    private String nomeDoRelatorio;
    private String reportLogo;
    private HashMap<String, Object> parametros;
    private JasperReport relatorio;
    private JasperPrint impressao;

    public GenericReport(String nomeDoRelatorio, ServletContext servletContext) {
        URL url = this.getClass().getResource(this.getClass().getName().replace(this.getClass().getPackage().getName() + ".", "") + ".class");
        String caminhoReal = servletContext.getRealPath("/");

        this.reportLogo = caminhoReal + "/images/aje-logo.png";
        this.nomeDoRelatorio = nomeDoRelatorio;
        this.caminhoRelatorioCompilado = caminhoReal + "/reports/jasper/" + nomeDoRelatorio + ".jasper";
        this.parametros = new HashMap<String, Object>();
        montarParametrosGenericos();
    }

    public final void montarParametrosGenericos() {
        parametros.put("REPORT_LOGO", this.reportLogo);
    }

    public String getCaminhoRelatorioCompilado() {
        return caminhoRelatorioCompilado;
    }

    public void setCaminhoRelatorioCompilado(String caminhoRelatorioCompilado) {
        this.caminhoRelatorioCompilado = caminhoRelatorioCompilado;
    }

    public String getNomeDoRelatorio() {
        return nomeDoRelatorio;
    }

    public void setNomeDoRelatorio(String nomeDoRelatorio) {
        this.nomeDoRelatorio = nomeDoRelatorio;
    }

    public HashMap<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(HashMap<String, Object> parametros) {
        this.parametros = parametros;
    }

    public JasperReport getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(JasperReport relatorio) {
        this.relatorio = relatorio;
    }

    public JasperPrint getImpressao() {
        return impressao;
    }

    public void setImpressao(JasperPrint impressao) {
        this.impressao = impressao;
    }

    public String getReportLogo() {
        return reportLogo;
    }

    public void setReportLogo(String reportLogo) {
        this.reportLogo = reportLogo;
    }
}
