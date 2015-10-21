package com.daluga.repository;

import com.daluga.model.Boxscore;
import com.daluga.model.BoxscoreSearch;

import java.util.Date;
import java.util.List;

public interface BoxscoreRepository {

    List<Boxscore> findAllBoxscores();

    Boxscore findBoxscore(String boxscoreId);

    Boxscore create(Boxscore boxscore);

    Boxscore update(Boxscore boxscore);

    void delete(String boxscoreId);

    List<Boxscore> findByDescription(List<String> names, Date fromDate, Date toDate);

    List<Boxscore> findByDescription(BoxscoreSearch search);
}
