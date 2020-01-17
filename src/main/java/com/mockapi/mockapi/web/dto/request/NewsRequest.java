package com.mockapi.mockapi.web.dto.request;

import com.mockapi.mockapi.model.NewsCategory;
import com.mockapi.mockapi.web.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class NewsRequest extends BaseDTO {
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

    private NewsCategory newsCategory;
}
