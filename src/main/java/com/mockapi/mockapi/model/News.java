package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "NEWS")
@Table
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "NEWS_ID_SEQ")
    @SequenceGenerator(name = "NEWS_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_NEWS",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "THUMBNAIL")
    private String thumbnail;

    @Column(name = "TITLE")
    private String title;

    // người đăng bài
    @Column(name = "POSTED")
    private String posted;

    @Column(name = "TIME_POST")
    private Date time_post;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "CONTENT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    @JsonBackReference
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "NEWSCATEGORY_ID")
    @JsonBackReference
    private NewsCategory newsCategory;
}
