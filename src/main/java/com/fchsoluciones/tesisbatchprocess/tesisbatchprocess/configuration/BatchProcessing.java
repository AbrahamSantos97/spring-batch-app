package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.Tesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.SeccionesTesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor.ProcessorTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisSql;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterSeccionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterTesisData;

@Configuration
public class BatchProcessing {
    
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final ReaderTesisData readerTesis;
    private final WriterTesisData writerTesis;
    
    private final ReaderTesisSql readerTesisSql;
    private final ProcessorTesisData processorTesisData;
    private final WriterSeccionesTesis writerSeccionesTesis;

    public BatchProcessing(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,
    ReaderTesisData readerTesisData, WriterTesisData writerTesisData, ReaderTesisSql readerTesisSql, 
    ProcessorTesisData processorTesisData,WriterSeccionesTesis writerSeccionesTesis) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.readerTesis = readerTesisData;
        this.writerTesis = writerTesisData;
        this.readerTesisSql = readerTesisSql;
        this.processorTesisData = processorTesisData;
        this.writerSeccionesTesis = writerSeccionesTesis;
    }

    @Bean
    public Job jobForTesis(){
        return jobBuilderFactory.get("Almacenado masivo de Tesis")
        .incrementer(new RunIdIncrementer())
        .start(stepForTesis())
        .next(stepForContent())
        .build();
    } 

    public Step stepForTesis(){
        return stepBuilderFactory.get("Crear simple Tesis")
        .<TesisDTO,TesisDTO>chunk(100)
        .reader(readerTesis)
        .writer(writerTesis)
        .build();
    }

    public Step stepForContent(){
        return stepBuilderFactory.get("Crear el contenido de las tesis recien guardadas")
        .<Tesis,SeccionesTesisDTO>chunk(100)
        .reader(readerTesisSql)
        .processor(processorTesisData)
        .writer(writerSeccionesTesis)
        .build();
    }
    
}
