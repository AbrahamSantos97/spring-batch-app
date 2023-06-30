package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;

@Component
public class ReaderTesisData implements ItemReader<TesisDTO> {

    private List<TesisDTO> items = new ArrayList<>();
    private Integer index = 0;

    @Override
    public TesisDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        TesisDTO item = null;
        if(index < items.size()){
            item = items.get(index++);
        }
        return item;
    }

    public void addElements(List<TesisDTO> items){
        this.items.clear();
        this.items.addAll(items);
        this.index = 0;
    }
    
}
