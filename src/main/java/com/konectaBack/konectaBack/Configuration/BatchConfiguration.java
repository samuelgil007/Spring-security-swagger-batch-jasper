package com.konectaBack.konectaBack.Configuration;

import javax.sql.DataSource;
import com.konectaBack.konectaBack.Listeners.JobListener;
import com.konectaBack.konectaBack.Models.Medico;
import com.konectaBack.konectaBack.Processors.MedicoItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Medico> reader(){
        return new FlatFileItemReaderBuilder<Medico>()
                .name("personaItemReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .delimited()
                .names(new String[] {"nombre", "apellido", "id"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Medico>() {{
                    setTargetType(Medico.class);
                }})
                .build();
    }

    @Bean
    public MedicoItemProcessor processor() {
        return new MedicoItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Medico> writer(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Medico>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO persona (nombre, apellido, id) VALUES (:nombre, :apellido, :id)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importMedicoJob(JobListener listener, Step step1) {
        return jobBuilderFactory.get("importMedicoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Medico> writer) {
        return stepBuilderFactory.get("step1")
                .<Medico, Medico> chunk(10)
                .reader(reader())
                .writer(writer)
                .build();
    }

}