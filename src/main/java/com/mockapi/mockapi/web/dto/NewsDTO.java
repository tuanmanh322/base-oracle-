package com.mockapi.mockapi.web.dto;

import com.mockapi.mockapi.model.Employee;
import com.mockapi.mockapi.model.NewsCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class NewsDTO extends BaseDTO{
    private long id;

    @NotNull
    private String thumbnail;

    @NotNull
    private String title;

    @NotNull
    private String posted;

    @NotNull
    private Date time_post;

    @NotNull
    private String summary;

    @NotNull
    private String content;

    private Employee employee;


    private NewsCategory newsCategory;

}
