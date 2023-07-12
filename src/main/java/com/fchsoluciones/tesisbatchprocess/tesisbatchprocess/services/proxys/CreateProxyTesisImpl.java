package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.proxys;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.mappers.CreateProxyTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.RelacionDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.RelacionesTesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor.ProcessorTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderRelacionesTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisData;

@Service("proxyTesis")
public class CreateProxyTesisImpl implements CreateProxyTesis {

    private final ReaderTesisData tesisReader;
    private final ProcessorTesisData processorTesisData;
    private final JobLauncher jobLauncher;
    private final Job jobForTesis;
    private final ReaderRelacionesTesis readerRelacionesTesis;
    private final Job jobForRelations;

    public CreateProxyTesisImpl(ReaderTesisData tesisReader, JobLauncher jobLauncher, Job  jobForTesis,
    ProcessorTesisData processorTesisData, ReaderRelacionesTesis readerRelacionesTesis, Job jobForRelations){
        this.jobLauncher = jobLauncher;
        this.tesisReader = tesisReader;
        this.jobForTesis = jobForTesis;
        this.processorTesisData = processorTesisData;
        this.readerRelacionesTesis = readerRelacionesTesis;
        this.jobForRelations = jobForRelations;
    }

    @Async
    public void proxyToStartAsyncSaveRelations(List<RelacionesTesisDTO> items) {
        List<RelacionesBot> relaciones = items.stream().map(this::mapToValidRelation)
                                        .flatMap(List::stream).collect(Collectors.toList());
        relaciones.stream().filter(e-> Strings.isNotBlank(e.getNombreDocumento()))
        .forEach(e-> e.setNombreDocumento(e.getNombreDocumento().replaceAll("\\n", "")));
        readerRelacionesTesis.setRelaciones(relaciones);
        launchJob(jobForRelations);
    }

    @Async
    public void proxyToStartAsyncJob(List<TesisDTO> items){
        items.forEach(e-> e.setRubro(e.getRubro().replaceAll("\n", "")));
        tesisReader.addElements(items);
        processorTesisData.addItems(items);
        launchJob(jobForTesis);
    }

    private List<RelacionesBot> mapToValidRelation(RelacionesTesisDTO current){
        List<RelacionesBot> relaciones = current.getRelaciones()
        .stream()
        .map(this::factoryMap)
        .collect(Collectors.toList());
        if(relaciones.isEmpty())return Arrays.asList(new RelacionesBot(current.getRegistroDigital()));
        relaciones.forEach(e-> e.setRegistroDigital(current.getRegistroDigital()));
        return relaciones;
    }

    private RelacionesBot factoryMap(RelacionDTO relacionDTO){
        return new RelacionesBot(relacionDTO.getDocumento(), relacionDTO.getRelaciones());
    }

    private void launchJob(Job job){
        try {
            jobLauncher.run(job, new JobParameters(Collections.singletonMap("uuid", new JobParameter(UUID.randomUUID().toString()))));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
