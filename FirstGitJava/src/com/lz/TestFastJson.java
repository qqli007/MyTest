package com.lz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

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

    public static void main(String[] args) throws FileNotFoundException {

        long startTime = System.nanoTime();

        Map<String, List<Epg>> epgCacheMap = new HashMap<String, List<Epg>>();
        File file = new File("F:\\AndroidWorkspace\\LivePlayer_Local3\\LivePlayerUC\\test\\other\\c.json");
        InputStream inputStream = new FileInputStream(file);

        String json_str = ConvertStream2Json(inputStream);

        long middleT1 = System.nanoTime();

        JSONArray jsonArray = JSON.parseArray(json_str);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String channelId = jsonObject.getString(CHANNEL);
            List<Epg> epgList = new ArrayList<Epg>();
            JSONArray jsonArrayEpgList = jsonObject.getJSONArray(EPG_LIST);
            for (int j = 0; j < jsonArrayEpgList.size(); j++) {
                JSONObject jsonObjectEPG = (JSONObject) jsonArrayEpgList.get(j);
                Epg epg = Epg.fromJSON(jsonObjectEPG);
                epg.channelId = channelId;
                epgList.add(epg);
            }
            epgCacheMap.put(channelId, epgList);
        }



        long endTime = System.nanoTime();
        System.out.println("解析所有频道epg epgCacheMap 耗时 = " + (endTime - startTime));
        System.out.println("1 nanoTime String jsonString = IOUtils.toString(inputStream) 前 = " + (middleT1 - startTime));
        System.out.println("2 nanoTime String jsonString = IOUtils.toString(inputStream) 后 = " + (endTime - middleT1));


    }

    private static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
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
