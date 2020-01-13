package com.mockapi.mockapi.repository;

import com.fasterxml.jackson.databind.node.LongNode;
import com.mockapi.mockapi.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepo extends JpaRepository<NewsCategory, LongNode> {
}
