package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reporte")
    public String reporte() throws JRException, FileNotFoundException {
        return reportService.exportReport("pdf");
    }
}
