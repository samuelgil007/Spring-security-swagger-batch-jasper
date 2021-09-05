package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Repositories.CitaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    protected EntityManager entityManager;
    private CitaRepository citaRepository;

    public ReportService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "/home/samuelg/Desktop";
        List<Cita> citas = citaRepository.findAll();
        File file = ResourceUtils.getFile("classpath:Reports/cita.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(citas);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Samuel");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/citas.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/citas.pdf");
            JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return "report generated in path : " + path;
    }

    public byte[] exportReportReturn() throws FileNotFoundException, JRException {
        List<Cita> citas = citaRepository.findAll();
        File file = ResourceUtils.getFile("classpath:Reports/cita.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(citas);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Samuel");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return  JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] exportReportReturnJoin() throws FileNotFoundException, JRException {
        TypedQuery<Cita> query = entityManager.createQuery(
                "SELECT d" +
                        " FROM Cita c JOIN c.idMedico d", Cita.class);
        List<Cita> resultList = query.getResultList();

        File file = ResourceUtils.getFile("classpath:Reports/citaJoinMedico.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(resultList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Samuel");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return  JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
