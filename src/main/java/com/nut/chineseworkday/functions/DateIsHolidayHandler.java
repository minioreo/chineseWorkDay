package com.nut.chineseworkday.functions;

import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.PojoRequestHandler;
import com.nut.chineseworkday.pojo.DateIsHolidayRequest;
import com.nut.chineseworkday.pojo.Response;
import com.nut.chineseworkday.pojo.DateIsHolidayResponseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.SUNDAY;

public class DateIsHolidayHandler implements PojoRequestHandler<DateIsHolidayRequest, Response<DateIsHolidayResponseData>> {
    public Response<DateIsHolidayResponseData> handleRequest(DateIsHolidayRequest request, Context context) {
        String dateString = request.getDateString();
        if(dateString == null || dateString.trim().isEmpty()){
            return Response.createFailResponse("param invalid","param dateString required.");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            context.getLogger().error("fail to parse date:" + dateString);
            return Response.createFailResponse("DateFormat Error","correct dateFormat is 'yyyy/MM/dd'.");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return weekDayHandler(calendar);
    }

    private Response<DateIsHolidayResponseData> weekDayHandler(Calendar calendar){
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == SUNDAY){
            return Response.createSuccessResponse(DateIsHolidayResponseData.createHolidayResponse("周末"));
        }
        else{
            return Response.createSuccessResponse(DateIsHolidayResponseData.createWorkdayResponse());
        }
    }
}
