package com.lz;



import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SlothMonkey
 * Date: 12-7-23
 * Time: 下午2:00
 */
public class Epg {

    public static final String ID = "id";
    public static final String INDEXES = "indexes";
    public static final String DATE = "date";
    public static final String NAME = "name";
    public static final String START = "start";
    public static final String END = "end";

    public String toString(){
        return id+ "  " +name+ "  " + date+ "  " +start+ "  " +end;
    }

    public String id;
    public int indexes;
    public int date;
    public java.lang.String name;
    public long start;
    public long end;
    public long videoPlayItem;

    public String channelId;

    public static Epg fromJSON(JSONObject object) {
        Epg epg = new Epg();
        epg.id = object.getString(ID);
        epg.indexes = object.getIntValue(INDEXES);
        epg.date = object.getIntValue(DATE);
        epg.name = object.getString(NAME);
        epg.start = object.getLongValue(START);
        epg.end = object.getLongValue(END);
        return epg;
    }




}
