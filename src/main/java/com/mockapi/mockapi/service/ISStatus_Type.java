package com.mockapi.mockapi.service;

import com.mockapi.mockapi.web.dto.Status_TypeDTO;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;

import java.util.List;

public interface ISStatus_Type {
    GetListDataResponseDTO<Status_TypeDTO> getAll();
}
