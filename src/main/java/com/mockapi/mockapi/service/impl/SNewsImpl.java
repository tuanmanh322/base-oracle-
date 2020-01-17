package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.model.News;
import com.mockapi.mockapi.repository.EmployeeRepo;
import com.mockapi.mockapi.repository.NewsCategoryRepo;
import com.mockapi.mockapi.repository.NewsRepo;
import com.mockapi.mockapi.service.ISNewsService;
import com.mockapi.mockapi.web.dto.NewsDTO;
import com.mockapi.mockapi.web.dto.request.NewsRequest;
import com.mockapi.mockapi.web.dto.response.GetListDataResponseDTO;
import com.mockapi.mockapi.web.dto.response.GetSingleDataResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class SNewsImpl implements ISNewsService {
    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsCategoryRepo newsCategoryRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public GetListDataResponseDTO<NewsDTO> getAllNews() {
        GetListDataResponseDTO<NewsDTO> result = new GetListDataResponseDTO<>();
        List<News> data = newsRepo.findAll();
        List<NewsDTO> dtos = new ArrayList<>();
        try{
            data.forEach(ne ->{
                NewsDTO newsDTO = modelMapper.map(ne,NewsDTO.class);
                dtos.add(newsDTO);

            });
            result.setValue(dtos);
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
        return result;
    }

    @Override
    public GetSingleDataResponseDTO<NewsDTO> delete(Long id) {
        GetSingleDataResponseDTO<NewsDTO> result = new GetSingleDataResponseDTO<>();
        News news = newsRepo.findById(id).get();
        if(news != null){
            newsRepo.deleteById(id);
        }
        result.setResult(modelMapper.map(news,NewsDTO.class));
        return result;
    }

    @Override
    public GetSingleDataResponseDTO<NewsDTO> update(NewsDTO newsDTO) {
        GetSingleDataResponseDTO<NewsDTO> result = new GetSingleDataResponseDTO<>();
        try {
            News news = newsRepo.findById(newsDTO.getId()).get();
            if(news !=null){
                news.setContent(newsDTO.getContent());
                news.setPosted(newsDTO.getContent());
                news.setSummary(newsDTO.getSummary());
                news.setThumbnail(newsDTO.getThumbnail());
                news.setTime_post(newsDTO.getTime_post());
                news.setTitle(newsDTO.getTitle());
                newsRepo.save(news);
                result.setResult(modelMapper.map(news,NewsDTO.class));
                return result;
            }
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            result.setResult(null);
        }

        return result;
    }

    @Override
    public GetSingleDataResponseDTO<NewsDTO> add(NewsRequest newsRequest) {
        GetSingleDataResponseDTO<NewsDTO> result = new GetSingleDataResponseDTO<>();
        try {
            News news = modelMapper.map(newsRequest,News.class);
            news = newsRepo.save(news);
            result.setResult(modelMapper.map(news,NewsDTO.class));
            log.info("response ----" +result.getMessage());
            return  result;
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
            result.setResult(null);
        }
        return result;
    }
}
