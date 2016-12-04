package com.daluga.client;

import com.daluga.model.Boxscore;

import com.daluga.model.BoxscoreSearch;
import com.daluga.model.BoxscoreSearchType;
import com.daluga.util.DateUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;

public class BoxscoreClientTest {

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreClientTest.class);

    @Test
    public void testGet()  {

        BoxscoreClient client = new BoxscoreClient();

        Boxscore boxscore = client.get("1");

        logger.debug("Boxscore Id: " + boxscore.getId());

        assertNotNull(boxscore);
    }

    @Test
    public void testGetList() {

        BoxscoreClient client = new BoxscoreClient();

        List<Boxscore> boxscores = client.get();

        logger.debug(boxscores.toString());

        assertNotNull(boxscores);
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithBadRequest() {

        BoxscoreClient client = new BoxscoreClient();

        client.get("");
    }

    @Test(expected = RuntimeException.class)
    public void testGetWithNotFound() {

        BoxscoreClient client = new BoxscoreClient();

        client.get("123");
    }

    @Test
    public void testCreate() {

        BoxscoreClient client = new BoxscoreClient();

        Boxscore boxscore = new Boxscore();
        boxscore.setName("Testing a create");
        boxscore.setId(456);

        boxscore = client.create(boxscore);

        assertNotNull(boxscore);
    }

    @Test
    public void testPut() {

        Boxscore boxscore = new Boxscore();

        boxscore.setId(1234);
        boxscore.setName("Testing a Put (Update) of a record");

        BoxscoreClient client = new BoxscoreClient();

        boxscore = client.update(boxscore);

        assertNotNull(boxscore);
    }

    @Test
    public void testDelete() {

        BoxscoreClient client = new BoxscoreClient();

        client.delete("1234");
    }

    @Test
    public void testSearch() {
        BoxscoreSearchClient client = new BoxscoreSearchClient();

        String nameParam = "name";

        List<String> names = new ArrayList<>();

        names.add("name1");
        names.add("name2");

        String fromDateParam = "fromDate";
        String fromDate = "09/01/2015";

        String toDateParam = "toDate";
        String toDate = "09/15/2015";

        List<Boxscore> boxscores = client.search(nameParam, names, fromDateParam, fromDate, toDateParam, toDate);

        logger.debug(boxscores.toString());

        assertNotNull(boxscores);
    }

    @Test
    public void testSearchObject() {

        BoxscoreSearchClient client = new BoxscoreSearchClient();

        List<String> names = new ArrayList<>();

        names.add("name1");
        names.add("name2");

        BoxscoreSearch search = new BoxscoreSearch();
        search.setName(names);
        search.setFromDate(DateUtil.formatDate("09/01/2015"));
        search.setToDate(DateUtil.formatDate("09/15/2015"));
        search.setSearchType(BoxscoreSearchType.SEARCH_BY_NAME);

        List<Boxscore> boxscores = client.search(search);

        logger.debug(boxscores.toString());

        assertNotNull(boxscores);
    }
}