package csc472.depaul.edu.micvalmoy.tools;


import org.parceler.Parcel;
import org.parceler.ParcelClass;
import org.parceler.ParcelConverter;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * https://github.com/johncarl81/parceler#parcel-attribute-types
 *
 * Create a OffsetDateTime converter to use with parcelable from android.os.Parcel
 *
 * https://github.com/johncarl81/parceler/blob/master/examples/test/src/main/java/org/parceler/test/DateConverter.java
 *
 * @ParcelClass(value = OffsetDateTime.class, annotation = @Parcel(converter = OffsetDateTimeParcelConverter.class))
 * 
 */


public class OffsetDateTimeParcelConverter implements ParcelConverter<OffsetDateTime> {


	@Override
    public void toParcel(OffsetDateTime offsetDateTime, android.os.Parcel parcel) {
        if (offsetDateTime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            String strOffsetDate = fromOffsetDateTime(offsetDateTime);
            parcel.writeString(strOffsetDate);
        }
    }
    @Override
    public OffsetDateTime fromParcel(android.os.Parcel parcel) {
        OffsetDateTime offsetDateTime = null;
        if (parcel.readByte() == 0) {
            offsetDateTime = null;
        } else {
            String strOffsetDate = parcel.readString();
            offsetDateTime = toOffsetDateTime(strOffsetDate);
        }
   
        return offsetDateTime;
    }


	private  OffsetDateTime toOffsetDateTime(String strOffsetDate){
		return OffsetDateTime.parse(strOffsetDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	};  


	private  String fromOffsetDateTime( OffsetDateTime offsetDateTimeTime){
		return offsetDateTimeTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}