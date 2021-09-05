package com.konectaBack.konectaBack.Services;

import com.konectaBack.konectaBack.Models.Cita;
import com.konectaBack.konectaBack.Repositories.CitaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private CitaRepository citaRepository;

    public ReportService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "/home/samuelg/Desktop";
        List<Cita> citas = citaRepository.findAll();
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(citas);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Samuel");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "/employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/employees.pdf");
        }

        return "report generated in path : " + path;
    }
}
