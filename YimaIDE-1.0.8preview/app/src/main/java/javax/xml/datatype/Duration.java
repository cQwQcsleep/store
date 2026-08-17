package javax.xml.datatype;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Duration {
    private static final boolean DEBUG = true;

    private static long getCalendarTimeInMillis(Calendar calendar) {
        return calendar.getTime().getTime();
    }

    public abstract Duration add(Duration duration);

    public abstract void addTo(Calendar calendar);

    public void addTo(Date date) {
        if (date != null) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            addTo(gregorianCalendar);
            date.setTime(getCalendarTimeInMillis(gregorianCalendar));
            return;
        }
        throw new NullPointerException("Cannot call " + getClass().getName() + "#addTo(Date date) with date == null.");
    }

    public abstract int compare(Duration duration);

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof Duration) && compare((Duration) obj) == 0;
    }

    public int getDays() {
        return getField(DatatypeConstants.DAYS).intValue();
    }

    public abstract Number getField(DatatypeConstants.Field field);

    public int getHours() {
        return getField(DatatypeConstants.HOURS).intValue();
    }

    public int getMinutes() {
        return getField(DatatypeConstants.MINUTES).intValue();
    }

    public int getMonths() {
        return getField(DatatypeConstants.MONTHS).intValue();
    }

    public int getSeconds() {
        return getField(DatatypeConstants.SECONDS).intValue();
    }

    public abstract int getSign();

    public long getTimeInMillis(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        addTo(gregorianCalendar);
        return getCalendarTimeInMillis(gregorianCalendar) - date.getTime();
    }

    public QName getXMLSchemaType() {
        boolean zIsSet = isSet(DatatypeConstants.YEARS);
        boolean zIsSet2 = isSet(DatatypeConstants.MONTHS);
        boolean zIsSet3 = isSet(DatatypeConstants.DAYS);
        boolean zIsSet4 = isSet(DatatypeConstants.HOURS);
        boolean zIsSet5 = isSet(DatatypeConstants.MINUTES);
        boolean zIsSet6 = isSet(DatatypeConstants.SECONDS);
        if (zIsSet && zIsSet2 && zIsSet3 && zIsSet4 && zIsSet5 && zIsSet6) {
            return DatatypeConstants.DURATION;
        }
        if (!zIsSet && !zIsSet2 && zIsSet3 && zIsSet4 && zIsSet5 && zIsSet6) {
            return DatatypeConstants.DURATION_DAYTIME;
        }
        if (zIsSet && zIsSet2 && !zIsSet3 && !zIsSet4 && !zIsSet5 && !zIsSet6) {
            return DatatypeConstants.DURATION_YEARMONTH;
        }
        throw new IllegalStateException("javax.xml.datatype.Duration#getXMLSchemaType(): this Duration does not match one of the XML Schema date/time datatypes: year set = " + zIsSet + " month set = " + zIsSet2 + " day set = " + zIsSet3 + " hour set = " + zIsSet4 + " minute set = " + zIsSet5 + " second set = " + zIsSet6);
    }

    public int getYears() {
        return getField(DatatypeConstants.YEARS).intValue();
    }

    public abstract int hashCode();

    public boolean isLongerThan(Duration duration) {
        return compare(duration) == 1;
    }

    public abstract boolean isSet(DatatypeConstants.Field field);

    public boolean isShorterThan(Duration duration) {
        return compare(duration) == -1;
    }

    public Duration multiply(int i) {
        return multiply(new BigDecimal(String.valueOf(i)));
    }

    public abstract Duration multiply(BigDecimal bigDecimal);

    public abstract Duration negate();

    public abstract Duration normalizeWith(Calendar calendar);

    public Duration subtract(Duration duration) {
        return add(duration.negate());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getSign() < 0) {
            stringBuffer.append(LocaleUtility.IETF_SEPARATOR);
        }
        stringBuffer.append('P');
        BigInteger bigInteger = (BigInteger) getField(DatatypeConstants.YEARS);
        if (bigInteger != null) {
            stringBuffer.append(bigInteger + "Y");
        }
        BigInteger bigInteger2 = (BigInteger) getField(DatatypeConstants.MONTHS);
        if (bigInteger2 != null) {
            stringBuffer.append(bigInteger2 + "M");
        }
        BigInteger bigInteger3 = (BigInteger) getField(DatatypeConstants.DAYS);
        if (bigInteger3 != null) {
            stringBuffer.append(bigInteger3 + "D");
        }
        BigInteger bigInteger4 = (BigInteger) getField(DatatypeConstants.HOURS);
        BigInteger bigInteger5 = (BigInteger) getField(DatatypeConstants.MINUTES);
        BigDecimal bigDecimal = (BigDecimal) getField(DatatypeConstants.SECONDS);
        if (bigInteger4 != null || bigInteger5 != null || bigDecimal != null) {
            stringBuffer.append('T');
            if (bigInteger4 != null) {
                stringBuffer.append(bigInteger4 + "H");
            }
            if (bigInteger5 != null) {
                stringBuffer.append(bigInteger5 + "M");
            }
            if (bigDecimal != null) {
                stringBuffer.append(toString(bigDecimal) + "S");
            }
        }
        return stringBuffer.toString();
    }

    public long getTimeInMillis(Calendar calendar) {
        Calendar calendar2 = (Calendar) calendar.clone();
        addTo(calendar2);
        return getCalendarTimeInMillis(calendar2) - getCalendarTimeInMillis(calendar);
    }

    private String toString(BigDecimal bigDecimal) {
        StringBuffer stringBuffer;
        String string = bigDecimal.unscaledValue().toString();
        int iScale = bigDecimal.scale();
        if (iScale == 0) {
            return string;
        }
        int length = string.length() - iScale;
        if (length == 0) {
            return "0.".concat(string);
        }
        if (length > 0) {
            stringBuffer = new StringBuffer(string);
            stringBuffer.insert(length, '.');
        } else {
            StringBuffer stringBuffer2 = new StringBuffer((3 - length) + string.length());
            stringBuffer2.append("0.");
            for (int i = 0; i < (-length); i++) {
                stringBuffer2.append('0');
            }
            stringBuffer2.append(string);
            stringBuffer = stringBuffer2;
        }
        return stringBuffer.toString();
    }
}
