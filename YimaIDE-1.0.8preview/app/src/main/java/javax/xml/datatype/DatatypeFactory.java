package javax.xml.datatype;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DatatypeFactory {
    public static final String DATATYPEFACTORY_PROPERTY = "javax.xml.datatype.DatatypeFactory";
    public static final String DATATYPEFACTORY_IMPLEMENTATION_CLASS = new String("com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl");
    private static final Pattern XDTSCHEMA_YMD = Pattern.compile("[^DT]*");
    private static final Pattern XDTSCHEMA_DTD = Pattern.compile("[^YM]*[DT].*");

    public static DatatypeFactory newDefaultInstance() {
        return new DatatypeFactoryImpl();
    }

    public static DatatypeFactory newInstance() throws DatatypeConfigurationException {
        return (DatatypeFactory) FactoryFinder.find(DatatypeFactory.class, DATATYPEFACTORY_IMPLEMENTATION_CLASS);
    }

    public abstract Duration newDuration(long j);

    public abstract Duration newDuration(String str);

    public Duration newDuration(boolean z, int i, int i2, int i3, int i4, int i5, int i6) {
        return newDuration(z, i != Integer.MIN_VALUE ? BigInteger.valueOf(i) : null, i2 != Integer.MIN_VALUE ? BigInteger.valueOf(i2) : null, i3 != Integer.MIN_VALUE ? BigInteger.valueOf(i3) : null, i4 != Integer.MIN_VALUE ? BigInteger.valueOf(i4) : null, i5 != Integer.MIN_VALUE ? BigInteger.valueOf(i5) : null, i6 != Integer.MIN_VALUE ? BigDecimal.valueOf(i6) : null);
    }

    public abstract Duration newDuration(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigDecimal bigDecimal);

    public Duration newDurationDayTime(String str) {
        if (str == null) {
            x0e.a("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"null\"");
            return null;
        }
        if (XDTSCHEMA_DTD.matcher(str).matches()) {
            return newDuration(str);
        }
        kg9.a("Trying to create an xdt:dayTimeDuration with an invalid lexical representation of \"", str, "\", data model requires years and months only.");
        return null;
    }

    public Duration newDurationYearMonth(long j) {
        Duration durationNewDuration = newDuration(j);
        boolean z = durationNewDuration.getSign() != -1;
        BigInteger bigInteger = (BigInteger) durationNewDuration.getField(DatatypeConstants.YEARS);
        if (bigInteger == null) {
            bigInteger = BigInteger.ZERO;
        }
        BigInteger bigInteger2 = (BigInteger) durationNewDuration.getField(DatatypeConstants.MONTHS);
        if (bigInteger2 == null) {
            bigInteger2 = BigInteger.ZERO;
        }
        return newDurationYearMonth(z, bigInteger, bigInteger2);
    }

    public abstract XMLGregorianCalendar newXMLGregorianCalendar();

    public XMLGregorianCalendar newXMLGregorianCalendar(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        BigDecimal bigDecimalMovePointLeft = null;
        BigInteger bigIntegerValueOf = i != Integer.MIN_VALUE ? BigInteger.valueOf(i) : null;
        if (i7 != Integer.MIN_VALUE) {
            if (i7 < 0 || i7 > 1000) {
                qf1.a("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond, int timezone)with invalid millisecond: ", i7);
                return null;
            }
            bigDecimalMovePointLeft = BigDecimal.valueOf(i7).movePointLeft(3);
        }
        return newXMLGregorianCalendar(bigIntegerValueOf, i2, i3, i4, i5, i6, bigDecimalMovePointLeft, i8);
    }

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(String str);

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(BigInteger bigInteger, int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, int i6);

    public abstract XMLGregorianCalendar newXMLGregorianCalendar(GregorianCalendar gregorianCalendar);

    public XMLGregorianCalendar newXMLGregorianCalendarDate(int i, int i2, int i3, int i4) {
        return newXMLGregorianCalendar(i, i2, i3, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i4);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int i, int i2, int i3, int i4, int i5) {
        BigDecimal bigDecimalMovePointLeft;
        if (i4 == Integer.MIN_VALUE) {
            bigDecimalMovePointLeft = null;
        } else {
            if (i4 < 0 || i4 > 1000) {
                qf1.a("javax.xml.datatype.DatatypeFactory#newXMLGregorianCalendarTime(int hours, int minutes, int seconds, int milliseconds, int timezone)with invalid milliseconds: ", i4);
                return null;
            }
            bigDecimalMovePointLeft = BigDecimal.valueOf(i4).movePointLeft(3);
        }
        return newXMLGregorianCalendarTime(i, i2, i3, bigDecimalMovePointLeft, i5);
    }

    public static DatatypeFactory newInstance(String str, ClassLoader classLoader) throws DatatypeConfigurationException {
        return (DatatypeFactory) FactoryFinder.newInstance(DatatypeFactory.class, str, classLoader, false);
    }

    public Duration newDurationDayTime(long j) {
        return newDuration(j);
    }

    public Duration newDurationDayTime(boolean z, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        return newDuration(z, (BigInteger) null, (BigInteger) null, bigInteger, bigInteger2, bigInteger3, bigInteger4 != null ? new BigDecimal(bigInteger4) : null);
    }

    public Duration newDurationDayTime(boolean z, int i, int i2, int i3, int i4) {
        return newDurationDayTime(z, BigInteger.valueOf(i), BigInteger.valueOf(i2), BigInteger.valueOf(i3), BigInteger.valueOf(i4));
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int i, int i2, int i3, BigDecimal bigDecimal, int i4) {
        return newXMLGregorianCalendar((BigInteger) null, Integer.MIN_VALUE, Integer.MIN_VALUE, i, i2, i3, bigDecimal, i4);
    }

    public XMLGregorianCalendar newXMLGregorianCalendarTime(int i, int i2, int i3, int i4) {
        return newXMLGregorianCalendar(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i, i2, i3, Integer.MIN_VALUE, i4);
    }

    public Duration newDurationYearMonth(String str) {
        if (str != null) {
            if (XDTSCHEMA_YMD.matcher(str).matches()) {
                return newDuration(str);
            }
            kg9.a("Trying to create an xdt:yearMonthDuration with an invalid lexical representation of \"", str, "\", data model requires days and times only.");
            return null;
        }
        x0e.a("Trying to create an xdt:yearMonthDuration with an invalid lexical representation of \"null\"");
        return null;
    }

    public Duration newDurationYearMonth(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        return newDuration(z, bigInteger, bigInteger2, (BigInteger) null, (BigInteger) null, (BigInteger) null, (BigDecimal) null);
    }

    public Duration newDurationYearMonth(boolean z, int i, int i2) {
        return newDurationYearMonth(z, BigInteger.valueOf(i), BigInteger.valueOf(i2));
    }
}
