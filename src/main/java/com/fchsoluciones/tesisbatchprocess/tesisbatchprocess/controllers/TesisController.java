package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.mappers.CreateProxyTesis;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.RelacionesTesisDTO;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tesiscontrollers")
public class TesisController {

    private final CreateProxyTesis createProxyTesis;

    public TesisController(@Qualifier("proxyTesis") CreateProxyTesis proxyTesis) {
       this.createProxyTesis = proxyTesis;
    }
    
    @PostMapping(value="/tesis")
    public ResponseEntity<Map<String,Object>> postMethodName(@RequestBody List<TesisDTO> tesisDto) {
       createProxyTesis.proxyToStartAsyncJob(tesisDto);
        return ResponseEntity.ok(Collections.singletonMap("created",Boolean.TRUE));
    }

    @PostMapping("/relaciones")
    public ResponseEntity<Map<String,Object>> saveRelations(@RequestBody List<RelacionesTesisDTO> relacionesTesis){
        createProxyTesis.proxyToStartAsyncSaveRelations(relacionesTesis);
        return ResponseEntity.ok(Collections.singletonMap("created",Boolean.TRUE));
    }
    
}
