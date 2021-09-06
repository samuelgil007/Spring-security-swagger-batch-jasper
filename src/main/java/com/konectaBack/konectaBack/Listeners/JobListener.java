package com.konectaBack.konectaBack.Listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class JobListener extends JobExecutionListenerSupport {
    private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //muestro un mensaje luego de haber realizado el job o batch
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("FINALIZÓ EL JOB!! Verifica los resultados:");
            Long diff = jobExecution.getStartTime().getTime() - jobExecution.getEndTime().getTime();
            long total = jdbcTemplate.queryForObject("SELECT count(nombre) FROM Medico", Long.class);
            LOG.info("Tiempo de ejecucion: {} ", TimeUnit.SECONDS.convert(diff,TimeUnit.MICROSECONDS));
            LOG.info("Total: ", total);
        }

    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //muestro un mensaje empezando el job
        if (jobExecution.getStatus() == BatchStatus.STARTING) {
            LOG.info("El job esta iniciando!!!!");
        }
    }
}

