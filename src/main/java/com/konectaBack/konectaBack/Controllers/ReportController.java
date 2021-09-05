package com.konectaBack.konectaBack.Controllers;

import com.konectaBack.konectaBack.Services.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reportelocal")
    public String reporteLocal() throws JRException, FileNotFoundException {
        return reportService.exportReport("pdf");
    }

    @GetMapping("/reportereturn")
    public ResponseEntity<byte[]> reportePdf() throws JRException, FileNotFoundException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + "CitaReporte.pdf" + "\"")
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(reportService.exportReportReturn());
    }
}
