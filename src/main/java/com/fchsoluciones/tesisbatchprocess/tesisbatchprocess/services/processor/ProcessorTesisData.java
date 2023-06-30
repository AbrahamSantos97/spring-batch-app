package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.Tesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.SeccionesTesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;

@Component
public class ProcessorTesisData implements ItemProcessor<Tesis,SeccionesTesisDTO> {

    private List<TesisDTO> items = new ArrayList<>();

    @Override
    public SeccionesTesisDTO process(Tesis item) throws Exception {
        Optional<String> resultOptional = items.stream()
        .filter(e-> e.getRegistroDigital().equals(item.getRegistroDigital()))
        .findFirst().map(TesisDTO::getAllContent);
        if(resultOptional.isPresent())return new SeccionesTesisDTO(item.getIdTesis(), resultOptional.get(), item.getRubro());
        return null;
    }

    public void addItems(List<TesisDTO> items){
        this.items.clear();
        this.items.addAll(items
        .stream()
        .filter(e-> Strings.isNotBlank(e.getRegistroDigital()))
        .collect(Collectors.toList())
        );
    }
    
    
}
