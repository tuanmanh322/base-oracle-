package com.mockapi.mockapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity(name = "NEWS_CATEGORY")
//@Table
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "NEWS_CATEGORY_ID_SEQ")
    @SequenceGenerator(name = "NEWS_CATEGORY_ID_SEQ",sequenceName = "AUTO_INCRE_SEQ_CATEGORY",initialValue = 1,allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "newsCategory",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference(value = "new-cate")
    private List<News> news;
}
