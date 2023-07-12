package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;

@Component
public class ReaderRelacionesTesis implements ItemReader<RelacionesBot>{

    private List<RelacionesBot> relaciones = new ArrayList<>();
    private Integer index = 0;

    @Override
    public RelacionesBot read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
       RelacionesBot item = null;
        if(index < relaciones.size()){
            item = relaciones.get(index++);
        }
        return item;
    }

    public List<RelacionesBot> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<RelacionesBot> relaciones) {
        this.relaciones.clear();
        this.relaciones.addAll(relaciones);
        this.index = 0;
    }
    
}
