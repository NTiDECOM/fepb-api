package br.org.fepb.api.reports;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportGenerator {

    public static void print(GenericReport relatorio, JRBeanCollectionDataSource dataSource, HttpServletResponse response)
        throws Exception {

        JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorio.getCaminhoRelatorioCompilado(),
            relatorio.getParametros(), dataSource);

        JasperExportManager
            .exportReportToPdfStream(impressoraJasper,getOut(relatorio, response));
    }

    private static OutputStream getOut(GenericReport relatorio, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control",
            "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader(
            "Content-Disposition",
            "attachment;filename=\""
                + relatorio
                .getNomeDoRelatorio()
                .toUpperCase()
                .concat("_")
                .concat(new SimpleDateFormat(
                    "dd_MM_yyyy_hh_mm_ss")
                    .format(new Date())) + ".pdf");
        OutputStream out = response.getOutputStream();
        return out;
    }

}
