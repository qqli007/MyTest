package com.lz;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: LiZ
 * Date: 13-6-25
 * Time: 下午2:48
 * DOWhat:
 */
public class TestFastJson {

    private static final String CHANNEL = "channel";
    private static final String EPG_LIST = "epgs";

    public static void time(InputStream inputStream ) throws FileNotFoundException, JSONException {

        long startTime = System.nanoTime();

        Map<String, List<Epg>> epgCacheMap = new HashMap<String, List<Epg>>();
        String json_str = ConvertStream2Json(inputStream);

        long middleT1 = System.nanoTime();

        JSONArray jsonArray = new JSONArray(json_str);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String channelId = jsonObject.getString(CHANNEL);
            List<Epg> epgList = new ArrayList<Epg>();
            JSONArray jsonArrayEpgList = jsonObject.getJSONArray(EPG_LIST);
            for (int j = 0; j < jsonArrayEpgList.length(); j++) {
                JSONObject jsonObjectEPG = (JSONObject) jsonArrayEpgList.get(j);
                Epg epg = Epg.fromJSON(jsonObjectEPG);
                epg.channelId = channelId;
                epgList.add(epg);
            }
            epgCacheMap.put(channelId, epgList);
        }

        long endTime = System.nanoTime();
        Log.d("0.0", "解析所有频道epg epgCacheMap 耗时 = " + (endTime - startTime));
        Log.d("0.0", "1 nanoTime String jsonString = IOUtils.toString(inputStream) 前 = " + (middleT1 - startTime));
        Log.d("0.0", "2 nanoTime String jsonString = IOUtils.toString(inputStream) 后 = " + (endTime - middleT1));


    }

    private static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }


}
