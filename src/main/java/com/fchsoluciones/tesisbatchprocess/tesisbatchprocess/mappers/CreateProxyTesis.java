package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.mappers;

import java.util.List;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;

public interface CreateProxyTesis {
    
    public void proxyToStartAsyncJob(List<TesisDTO> items);
}
