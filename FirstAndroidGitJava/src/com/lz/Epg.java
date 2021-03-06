package com.lz;


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

    public long id;
    public int indexes;
    public int date;
    public String name;
    public long start;
    public long end;
    public long videoPlayItem;

    public String channelId;

    /*public static Epg fromJSON(JSONObject object) {
        Epg epg = new Epg();
        epg.id = object.getString(ID);
        epg.indexes = object.getIntValue(INDEXES);
        epg.date = object.getIntValue(DATE);
        epg.name = object.getString(NAME);
        epg.start = object.getLongValue(START);
        epg.end = object.getLongValue(END);
        return epg;
    }*/


    public static Epg fromJSON(org.json.JSONObject object) {
        Epg epg = new Epg();
        epg.id = object.optLong(ID);
        epg.indexes = object.optInt(INDEXES);
        epg.date = object.optInt(DATE);
        epg.name = object.optString(NAME);
        epg.start = object.optLong(START);
        epg.end = object.optLong(END);
        return epg;
    }

}
