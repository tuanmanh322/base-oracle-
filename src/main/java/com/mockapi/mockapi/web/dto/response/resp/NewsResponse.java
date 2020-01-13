package com.mockapi.mockapi.web.dto.response.resp;

import com.mockapi.mockapi.web.dto.BaseDTO;

import java.util.Date;

public class NewsResponse extends BaseDTO {
    private long id;

    private String thumbnail;

    private String title;

    private String posted;

    private Date time_post;

    private String summary;

    private String content;
}
