package com.mockapi.mockapi.service;

import com.mockapi.mockapi.web.dto.NewsDTO;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;

public interface ISNewsService {
    GetListDataResponseDTO<NewsDTO> getAllNews();

    GetSingleDataResponseDTO<NewsDTO> delete(Long id);

    GetSingleDataResponseDTO<NewsDTO> update(NewsDTO newsDTO);

    GetSingleDataResponseDTO<NewsDTO> add(NewsDTO newsDTO);
}
