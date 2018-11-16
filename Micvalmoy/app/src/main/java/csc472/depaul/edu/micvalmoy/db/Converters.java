package csc472.depaul.edu.micvalmoy.db;

import android.arch.persistence.room.TypeConverter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//https://mobikul.com/add-typeconverters-room-database-android/
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * 
     * https://www.tutorialspoint.com/javatime/javatime_offsetdatetime_parse1.htm
     * https://medium.com/androiddevelopers/room-time-2b4cf9672b98
     * 
     * String dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
     */
	@TypeConverter
	public static OffsetDateTime toOffsetDateTime(String strDate){
		return OffsetDateTime.parse(strDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	};  

	@TypeConverter
	public static String fromOffsetDateTime( OffsetDateTime offsetDateTime){
		return offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	};
}