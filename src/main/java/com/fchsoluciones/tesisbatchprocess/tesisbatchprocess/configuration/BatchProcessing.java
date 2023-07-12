package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.Tesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.SeccionesTesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor.ProcessorCreateRelation;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor.ProcessorTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderCreateRelations;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderRelacionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisSql;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterRelacionesBotTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterRelacionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterSeccionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer.WriterTesisData;

@Configuration
public class BatchProcessing {
    /*Factorys para crear los jobs y steps */
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /*Reader y writer para guardar las tesis, solo datos de tesis no contenido */
    private final ReaderTesisData readerTesis;
    private final WriterTesisData writerTesis;
    
    /*Reader, Processor y Writer para crear las secciones de las tesis. revisar los triggers de la bd de legal para complementar */
    private final ReaderTesisSql readerTesisSql;
    private final ProcessorTesisData processorTesisData;
    private final WriterSeccionesTesis writerSeccionesTesis;

    /*Reader y Writer en crudo de las relaciones obtenidas del bot */
    private final ReaderRelacionesTesis readerRelacionesTesis;
    private final WriterRelacionesBotTesis writerRelacionesBotTesis;

    /*Reader, Porcessor y Writer para crear las relaciones ya con los id de su respectivo documento, tesis y articulo, checar triggers de la bd de legal para complementar y entender el logger */
    private final ReaderCreateRelations readerCreateRelations;
    private final WriterRelacionesTesis writerRelacionesTesis;
    private final ProcessorCreateRelation processorCreateRelation;



    public BatchProcessing(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,
    ReaderTesisData readerTesisData, WriterTesisData writerTesisData, ReaderTesisSql readerTesisSql, 
    ProcessorTesisData processorTesisData,WriterSeccionesTesis writerSeccionesTesis,
    ReaderRelacionesTesis readerRelacionesTesis, WriterRelacionesBotTesis writerRelacionesTesis,
    ReaderCreateRelations readerCreateRelations, WriterRelacionesTesis writerRelacionesTesis2,
    ProcessorCreateRelation processorCreateRelation) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.readerTesis = readerTesisData;
        this.writerTesis = writerTesisData;
        this.readerTesisSql = readerTesisSql;
        this.processorTesisData = processorTesisData;
        this.writerSeccionesTesis = writerSeccionesTesis;
        this.readerRelacionesTesis = readerRelacionesTesis;
        this.writerRelacionesBotTesis = writerRelacionesTesis;
        this.writerRelacionesTesis = writerRelacionesTesis2;
        this.readerCreateRelations = readerCreateRelations;
        this.processorCreateRelation = processorCreateRelation;
    }

    @Bean
    public Job jobForTesis(){
        return jobBuilderFactory.get("Almacenado masivo de Tesis")
        .incrementer(new RunIdIncrementer())
        .start(stepForTesis())
        .next(stepForContent())
        .build();
    }
    
    @Bean
    public Job jobForRelations(){
        return jobBuilderFactory.get("Almacenamiento de relaciones")
        .incrementer(new RunIdIncrementer())
        .start(stepForRelations())
        .next(secondStepForRelations())
        .build();
    }

    public Step stepForRelations(){
        return stepBuilderFactory.get("Crear relaciones")
        .<RelacionesBot,RelacionesBot>chunk(1000)
        .reader(readerRelacionesTesis)
        .writer(writerRelacionesBotTesis)
        .build();
    }

    public Step secondStepForRelations(){
        return stepBuilderFactory.get("relaciones oficiales")
        .<RelacionesBot,RelacionesTesis>chunk(1000)
        .reader(readerCreateRelations)
        .processor(processorCreateRelation)
        .writer(writerRelacionesTesis)
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
