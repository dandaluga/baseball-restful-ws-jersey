package com.daluga.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Date unmarshal(String date) throws Exception {
        return formatter.parse(date);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return formatter.format(date);
    }
}
