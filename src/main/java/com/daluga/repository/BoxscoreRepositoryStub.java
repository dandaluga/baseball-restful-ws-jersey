package com.daluga.repository;

import com.daluga.model.Boxscore;
import com.daluga.model.BoxscoreSearch;
import com.daluga.model.Venue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoxscoreRepositoryStub implements BoxscoreRepository {

    @Override
    public List<Boxscore> findAllBoxscores() {
        List<Boxscore> boxscores = new ArrayList<Boxscore>();

        Boxscore boxscore1 = new Boxscore();
        boxscore1.setId(1);
        boxscore1.setName("The first one");
        boxscores.add(boxscore1);

        Boxscore boxscore2 = new Boxscore();
        boxscore2.setId(2);
        boxscore2.setName("The second one");
        boxscores.add(boxscore2);

        return boxscores;

    }

    @Override
    public Boxscore findBoxscore(String boxscoreId) {

        if (boxscoreId.equals("123")) {
            return null;
        }

        Boxscore boxscore = new Boxscore();

        boxscore.setId(1);
        boxscore.setName("The first one");

        Venue venue = new Venue();
        venue.setVenueId(1234);
        venue.setPark("Wrigley Field");

        boxscore.setVenue(venue);

        return boxscore;
    }

    @Override
    public Boxscore create(Boxscore boxscore) {

        // Insert into db.
        return boxscore;
    }

    @Override
    public Boxscore update(Boxscore boxscore) {

        // Update the db.
        return boxscore;
    }

    @Override
    public void delete(String boxscoreId) {

    }

    @Override
    public List<Boxscore> findByDescription(List<String> names, Date fromDate, Date toDate) {

        List<Boxscore> boxscores = new ArrayList<Boxscore>();

        Boxscore boxscore1 = new Boxscore();
        boxscore1.setId(789);
        boxscore1.setName("name1");

        boxscores.add(boxscore1);

        return boxscores;
    }

    @Override
    public List<Boxscore> findByDescription(BoxscoreSearch search) {

        List<Boxscore> boxscores = new ArrayList<Boxscore>();

        Boxscore boxscore1 = new Boxscore();
        boxscore1.setId(789);
        boxscore1.setName("name1");

        boxscores.add(boxscore1);

        return boxscores;
    }
}
