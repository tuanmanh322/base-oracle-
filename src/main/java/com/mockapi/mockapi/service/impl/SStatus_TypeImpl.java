package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.model.Status_Type;
import com.mockapi.mockapi.repository.StatusTypeRepo;
import com.mockapi.mockapi.service.ISStatus_Type;
import com.mockapi.mockapi.web.dto.Status_TypeDTO;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SStatus_TypeImpl implements ISStatus_Type {
    private final Logger log = LoggerFactory.getLogger(SStatus_TypeImpl.class);


    private ModelMapper modelMapper;

    @Autowired
    private StatusTypeRepo statusTypeRepo;

    public SStatus_TypeImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GetListDataResponseDTO<Status_TypeDTO> getAll() {
        log.info("Status_TypeDTO service");
        List<Status_Type> data = statusTypeRepo.findAll();
        List<Status_TypeDTO> dtos = new ArrayList<>();
        GetListDataResponseDTO<Status_TypeDTO> result = new GetListDataResponseDTO<>();
        data.forEach(datas -> {
            Status_TypeDTO typeDTO = modelMapper.map(datas, Status_TypeDTO.class);
            dtos.add(typeDTO);
        });
        log.info("Response to get all SS 1111:"+result.getMessage());
        result.setValue(dtos);
        log.info("Response to get all SS :"+result.getMessage());
        return result;

    }
}
