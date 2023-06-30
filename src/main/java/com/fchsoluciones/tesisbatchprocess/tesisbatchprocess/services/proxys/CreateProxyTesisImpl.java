package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.proxys;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor.ProcessorTesisData;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader.ReaderTesisData;

@Service("proxyTesis")
public class CreateProxyTesisImpl implements CreateProxyTesis {

    private final ReaderTesisData tesisReader;
    private final ProcessorTesisData processorTesisData;
    private final JobLauncher jobLauncher;
    private final Job tesisJob;

    public CreateProxyTesisImpl(ReaderTesisData tesisReader, JobLauncher jobLauncher, Job  tesisJob,
    ProcessorTesisData processorTesisData){
        this.jobLauncher = jobLauncher;
        this.tesisReader = tesisReader;
        this.tesisJob = tesisJob;
        this.processorTesisData = processorTesisData;
    }
    
    @Async
    public void proxyToStartAsyncJob(List<TesisDTO> items){
        tesisReader.addElements(items);
        processorTesisData.addItems(items);
        try {
            jobLauncher.run(tesisJob, new JobParameters(Collections.singletonMap("uuid", new JobParameter(UUID.randomUUID().toString()))));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
