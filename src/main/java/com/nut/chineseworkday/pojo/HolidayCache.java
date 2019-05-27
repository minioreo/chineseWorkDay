package com.nut.chineseworkday.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HolidayCache {
    private final static String holidayListKey = "holidays";
    private final static String workdayListKey = "workdays";

    private Map<String,String> workdays = new ConcurrentHashMap<>();
    private Map<String,String> holidays = new ConcurrentHashMap<>();

    public static class DayInfo{
        private boolean isHoliday;
        private String relatedHolidayName;

        public boolean isHoliday() {
            return isHoliday;
        }

        public String getRelatedHolidayName() {
            return relatedHolidayName;
        }

        DayInfo(boolean isHoliday, String relatedHolidayName) {
            this.isHoliday = isHoliday;
            this.relatedHolidayName = relatedHolidayName;
        }
    }

    public static HolidayCache load(String json){
        HolidayCache holidayData = new HolidayCache();
        JSONObject rootObject = JSON.parseObject(json);
        for (Object group : rootObject.values()) {
            JSONObject holidayGroup = (JSONObject) group;
            for ( Map.Entry<String, Object> entry : holidayGroup.entrySet()) {
                String holidayName = entry.getKey();
                JSONObject holidayObject = (JSONObject) entry.getValue();
                JSONArray holidayArray = holidayObject.getJSONArray(holidayListKey);

                for (Object date : holidayArray) {
                    String dateString = (String) date;
                    holidayData.holidays.put(dateString,holidayName);
                }

                JSONArray workdayArray = holidayObject.getJSONArray(workdayListKey);
                for (Object date : workdayArray) {
                    String dateString = (String) date;
                    holidayData.workdays.put(dateString,holidayName);
                }
            }
        }
        return holidayData;
    }

    public DayInfo judgeDay(Calendar c){
        String key = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        if(holidays.containsKey(key)){
            System.out.println("find date " + key + "in holiday " + holidays.get(key));
            return new DayInfo(true,holidays.get(key));
        }
        if(workdays.containsKey(key)){
            System.out.println("find date " + key + "in workday " + workdays.get(key));
            return new DayInfo(false,workdays.get(key));
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = HolidayCache.class.getClassLoader().getResourceAsStream("HolidayData.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        StringBuilder sb = new StringBuilder();
        String tempString;
        while ((tempString = reader.readLine()) != null) {
            sb.append(tempString);
        }
        String json = sb.toString();
        HolidayCache holidayCache = HolidayCache.load(json);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2019);
        calendar.set(Calendar.MONTH,9);
        calendar.set(Calendar.DAY_OF_MONTH,13);
        DayInfo dayInfo = holidayCache.judgeDay(calendar);
        System.out.println(JSON.toJSONString(dayInfo));

    }
}
