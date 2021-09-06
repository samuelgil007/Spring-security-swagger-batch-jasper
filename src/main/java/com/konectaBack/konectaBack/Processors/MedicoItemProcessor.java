package com.konectaBack.konectaBack.Processors;

import com.konectaBack.konectaBack.Models.Medico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MedicoItemProcessor implements ItemProcessor<Medico,Medico > {
    private static final Logger LOG = LoggerFactory.getLogger(MedicoItemProcessor.class);

    @Override
    public Medico process(Medico item) throws Exception {
        item.setNombre(item.getNombre().toUpperCase());
        return item;
    }
}