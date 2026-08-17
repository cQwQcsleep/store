package com.sun.org.apache.xerces.internal.jaxp.datatype;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.util.DatatypeMessageFormatter;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import defpackage.uj0;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLGregorianCalendarImpl extends XMLGregorianCalendar implements Serializable, Cloneable {
    private static final int BILLION_I = 1000000000;
    private static final int DAY = 2;
    private static final int HOUR = 3;
    private static final int MILLISECOND = 6;
    private static final int MINUTE = 4;
    private static final int MONTH = 1;
    private static final int SECOND = 5;
    private static final int TIMEZONE = 7;
    private static final int YEAR = 0;
    private transient BigInteger orig_eon;
    private transient BigDecimal orig_fracSeconds;
    private static final BigInteger BILLION_B = new BigInteger("1000000000");
    private static final Date PURE_GREGORIAN_CHANGE = new Date(Long.MIN_VALUE);
    private static final String[] FIELD_NAME = {"Year", "Month", "Day", "Hour", "Minute", "Second", "Millisecond", "Timezone"};
    public static final XMLGregorianCalendar LEAP_YEAR_DEFAULT = createDateTime(400, 1, 1, 0, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger HUNDRED = BigInteger.valueOf(100);
    private static final BigInteger FOUR_HUNDRED = BigInteger.valueOf(400);
    private static final BigInteger SIXTY = BigInteger.valueOf(60);
    private static final BigInteger TWENTY_FOUR = BigInteger.valueOf(24);
    private static final BigInteger TWELVE = BigInteger.valueOf(12);
    private static final BigDecimal DECIMAL_ZERO = BigDecimal.valueOf(0L);
    private static final long serialVersionUID = 1;
    private static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(serialVersionUID);
    private static final BigDecimal DECIMAL_TWELVE = BigDecimal.valueOf(12L);
    private static final BigDecimal DECIMAL_TWENTY_FOUR = BigDecimal.valueOf(24L);
    private static final BigDecimal DECIMAL_SIXTY = BigDecimal.valueOf(60L);
    private transient int orig_year = Integer.MIN_VALUE;
    private transient int orig_month = Integer.MIN_VALUE;
    private transient int orig_day = Integer.MIN_VALUE;
    private transient int orig_hour = Integer.MIN_VALUE;
    private transient int orig_minute = Integer.MIN_VALUE;
    private transient int orig_second = Integer.MIN_VALUE;
    private transient int orig_timezone = Integer.MIN_VALUE;
    private BigInteger eon = null;
    private int year = Integer.MIN_VALUE;
    private int month = Integer.MIN_VALUE;
    private int day = Integer.MIN_VALUE;
    private int timezone = Integer.MIN_VALUE;
    private int hour = Integer.MIN_VALUE;
    private int minute = Integer.MIN_VALUE;
    private int second = Integer.MIN_VALUE;
    private BigDecimal fractionalSecond = null;

    public static class DaysInMonth {
        private static final int[] table = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        private DaysInMonth() {
        }
    }

    public final class Parser {
        private int fidx;
        private final int flen;
        private final String format;
        private final String value;
        private int vidx;
        private final int vlen;

        private Parser(String str, String str2) {
            this.format = str;
            this.value = str2;
            this.flen = str.length();
            this.vlen = str2.length();
        }

        private BigDecimal parseBigDecimal() throws IllegalArgumentException {
            int i = this.vidx;
            if (peek() != '.') {
                w01.a(this.value);
                return null;
            }
            this.vidx++;
            while (XMLGregorianCalendarImpl.isDigit(peek())) {
                this.vidx++;
            }
            return new BigDecimal(this.value.substring(i, this.vidx));
        }

        private int parseInt(int i, int i2) throws IllegalArgumentException {
            int i3 = this.vidx;
            int i4 = 0;
            while (true) {
                char cPeek = peek();
                if (!XMLGregorianCalendarImpl.isDigit(cPeek)) {
                    break;
                }
                int i5 = this.vidx;
                if (i5 - i3 >= i2) {
                    break;
                }
                this.vidx = i5 + 1;
                i4 = ((i4 * 10) + cPeek) - 48;
            }
            if (this.vidx - i3 >= i) {
                return i4;
            }
            w01.a(this.value);
            return 0;
        }

        private void parseYear() throws IllegalArgumentException {
            int i;
            int i2;
            int i3 = this.vidx;
            if (peek() == '-') {
                i = 1;
                this.vidx++;
            } else {
                i = 0;
            }
            while (true) {
                boolean zIsDigit = XMLGregorianCalendarImpl.isDigit(peek());
                i2 = this.vidx;
                if (!zIsDigit) {
                    break;
                } else {
                    this.vidx = i2 + 1;
                }
            }
            int i4 = (i2 - i3) - i;
            String str = this.value;
            if (i4 < 4) {
                w01.a(str);
                return;
            }
            String strSubstring = str.substring(i3, i2);
            XMLGregorianCalendarImpl xMLGregorianCalendarImpl = XMLGregorianCalendarImpl.this;
            if (i4 < 10) {
                xMLGregorianCalendarImpl.setYear(Integer.parseInt(strSubstring));
            } else {
                xMLGregorianCalendarImpl.setYear(new BigInteger(strSubstring));
            }
        }

        private char peek() throws IllegalArgumentException {
            int i = this.vidx;
            if (i == this.vlen) {
                return (char) 65535;
            }
            return this.value.charAt(i);
        }

        private char read() throws IllegalArgumentException {
            int i = this.vidx;
            int i2 = this.vlen;
            String str = this.value;
            if (i != i2) {
                this.vidx = i + 1;
                return str.charAt(i);
            }
            w01.a(str);
            return (char) 0;
        }

        private void skip(char c) throws IllegalArgumentException {
            if (read() == c) {
                return;
            }
            w01.a(this.value);
        }

        public void parse() throws IllegalArgumentException {
            while (true) {
                int i = this.fidx;
                if (i >= this.flen) {
                    if (this.vidx == this.vlen) {
                        XMLGregorianCalendarImpl.this.testHour();
                        return;
                    } else {
                        w01.a(this.value);
                        return;
                    }
                }
                String str = this.format;
                this.fidx = i + 1;
                char cCharAt = str.charAt(i);
                if (cCharAt != '%') {
                    skip(cCharAt);
                } else {
                    String str2 = this.format;
                    int i2 = this.fidx;
                    this.fidx = i2 + 1;
                    char cCharAt2 = str2.charAt(i2);
                    if (cCharAt2 == 'D') {
                        XMLGregorianCalendarImpl.this.setDay(parseInt(2, 2));
                    } else if (cCharAt2 == 'M') {
                        XMLGregorianCalendarImpl.this.setMonth(parseInt(2, 2));
                    } else if (cCharAt2 == 'Y') {
                        parseYear();
                    } else if (cCharAt2 == 'h') {
                        XMLGregorianCalendarImpl.this.setHour(parseInt(2, 2), false);
                    } else if (cCharAt2 == 'm') {
                        XMLGregorianCalendarImpl.this.setMinute(parseInt(2, 2));
                    } else if (cCharAt2 == 's') {
                        XMLGregorianCalendarImpl.this.setSecond(parseInt(2, 2));
                        if (peek() == '.') {
                            XMLGregorianCalendarImpl.this.setFractionalSecond(parseBigDecimal());
                        }
                    } else {
                        if (cCharAt2 != 'z') {
                            uj0.a();
                            return;
                        }
                        char cPeek = peek();
                        if (cPeek == 'Z') {
                            this.vidx++;
                            XMLGregorianCalendarImpl.this.setTimezone(0);
                        } else if (cPeek == '+' || cPeek == '-') {
                            this.vidx++;
                            int i3 = parseInt(2, 2);
                            skip(':');
                            XMLGregorianCalendarImpl.this.setTimezone(((i3 * 60) + parseInt(2, 2)) * (cPeek != '+' ? -1 : 1));
                        }
                    }
                }
            }
        }
    }

    public XMLGregorianCalendarImpl(String str) throws IllegalArgumentException {
        String str2;
        int length = str.length();
        if (str.indexOf(84) != -1) {
            str2 = "%Y-%M-%DT%h:%m:%s%z";
        } else if (length >= 3 && str.charAt(2) == ':') {
            str2 = "%h:%m:%s%z";
        } else if (str.startsWith("--")) {
            str2 = (length < 3 || str.charAt(2) != '-') ? (length == 4 || length == 5 || length == 10) ? "--%M%z" : "--%M-%D%z" : "---%D%z";
        } else {
            length = str.indexOf(58) != -1 ? length - 6 : length;
            int i = 0;
            for (int i2 = 1; i2 < length; i2++) {
                if (str.charAt(i2) == '-') {
                    i++;
                }
            }
            str2 = i == 0 ? "%Y%z" : i == 1 ? "%Y-%M%z" : "%Y-%M-%D%z";
        }
        new Parser(str2, str).parse();
        if (!isValid()) {
            w01.a(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCRepresentation", new Object[]{str}));
            throw null;
        }
        save();
    }

    private static int compareField(int i, int i2) {
        if (i == i2) {
            return 0;
        }
        if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
            return 2;
        }
        return i < i2 ? -1 : 1;
    }

    public static XMLGregorianCalendar createDate(int i, int i2, int i3, int i4) {
        return new XMLGregorianCalendarImpl(i, i2, i3, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i4);
    }

    public static XMLGregorianCalendar createDateTime(int i, int i2, int i3, int i4, int i5, int i6) {
        return new XMLGregorianCalendarImpl(i, i2, i3, i4, i5, i6, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static XMLGregorianCalendar createTime(int i, int i2, int i3, int i4) {
        return new XMLGregorianCalendarImpl(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i, i2, i3, Integer.MIN_VALUE, i4);
    }

    private String format(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt != '%') {
                sb.append(cCharAt);
                i = i2;
            } else {
                i += 2;
                char cCharAt2 = str.charAt(i2);
                if (cCharAt2 == 'D') {
                    printNumber(sb, getDay(), 2);
                } else if (cCharAt2 == 'M') {
                    printNumber(sb, getMonth(), 2);
                } else if (cCharAt2 != 'Y') {
                    if (cCharAt2 == 'h') {
                        printNumber(sb, getHour(), 2);
                    } else if (cCharAt2 == 'm') {
                        printNumber(sb, getMinute(), 2);
                    } else if (cCharAt2 == 's') {
                        printNumber(sb, getSecond(), 2);
                        if (getFractionalSecond() != null) {
                            String plainString = getFractionalSecond().toPlainString();
                            sb.append(plainString.substring(1, plainString.length()));
                        }
                    } else {
                        if (cCharAt2 != 'z') {
                            uj0.a();
                            return null;
                        }
                        int timezone = getTimezone();
                        if (timezone == 0) {
                            sb.append('Z');
                        } else if (timezone != Integer.MIN_VALUE) {
                            if (timezone < 0) {
                                sb.append(LocaleUtility.IETF_SEPARATOR);
                                timezone *= -1;
                            } else {
                                sb.append('+');
                            }
                            printNumber(sb, timezone / 60, 2);
                            sb.append(':');
                            printNumber(sb, timezone % 60, 2);
                        }
                    }
                } else if (this.eon == null) {
                    int i3 = this.year;
                    if (i3 < 0) {
                        sb.append(LocaleUtility.IETF_SEPARATOR);
                        i3 = -this.year;
                    }
                    printNumber(sb, i3, 4);
                } else {
                    printNumber(sb, getEonAndYear(), 4);
                }
            }
        }
        return sb.toString();
    }

    private Locale getDefaultLocale() {
        Locale locale;
        String systemProperty = SecuritySupport.getSystemProperty("user.language.format");
        String systemProperty2 = SecuritySupport.getSystemProperty("user.country.format");
        String systemProperty3 = SecuritySupport.getSystemProperty("user.variant.format");
        if (systemProperty == null) {
            locale = null;
        } else if (systemProperty2 != null) {
            locale = systemProperty3 != null ? new Locale(systemProperty, systemProperty2, systemProperty3) : new Locale(systemProperty, systemProperty2);
        } else {
            locale = new Locale(systemProperty);
        }
        return locale == null ? Locale.getDefault() : locale;
    }

    private BigDecimal getSeconds() {
        int i = this.second;
        if (i == Integer.MIN_VALUE) {
            return DECIMAL_ZERO;
        }
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(i);
        BigDecimal bigDecimal = this.fractionalSecond;
        return bigDecimal != null ? bigDecimalValueOf.add(bigDecimal) : bigDecimalValueOf;
    }

    private static int internalCompare(XMLGregorianCalendar xMLGregorianCalendar, XMLGregorianCalendar xMLGregorianCalendar2) {
        if (xMLGregorianCalendar.getEon() == xMLGregorianCalendar2.getEon()) {
            int iCompareField = compareField(xMLGregorianCalendar.getYear(), xMLGregorianCalendar2.getYear());
            if (iCompareField != 0) {
                return iCompareField;
            }
        } else {
            int iCompareField2 = compareField(xMLGregorianCalendar.getEonAndYear(), xMLGregorianCalendar2.getEonAndYear());
            if (iCompareField2 != 0) {
                return iCompareField2;
            }
        }
        int iCompareField3 = compareField(xMLGregorianCalendar.getMonth(), xMLGregorianCalendar2.getMonth());
        if (iCompareField3 != 0) {
            return iCompareField3;
        }
        int iCompareField4 = compareField(xMLGregorianCalendar.getDay(), xMLGregorianCalendar2.getDay());
        if (iCompareField4 != 0) {
            return iCompareField4;
        }
        int iCompareField5 = compareField(xMLGregorianCalendar.getHour(), xMLGregorianCalendar2.getHour());
        if (iCompareField5 != 0) {
            return iCompareField5;
        }
        int iCompareField6 = compareField(xMLGregorianCalendar.getMinute(), xMLGregorianCalendar2.getMinute());
        if (iCompareField6 != 0) {
            return iCompareField6;
        }
        int iCompareField7 = compareField(xMLGregorianCalendar.getSecond(), xMLGregorianCalendar2.getSecond());
        return iCompareField7 != 0 ? iCompareField7 : compareField(xMLGregorianCalendar.getFractionalSecond(), xMLGregorianCalendar2.getFractionalSecond());
    }

    private void invalidFieldValue(int i, int i2) {
        throw new IllegalArgumentException(DatatypeMessageFormatter.formatMessage(null, "InvalidFieldValue", new Object[]{Integer.valueOf(i2), FIELD_NAME[i]}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    private static int maximumDayInMonthFor(BigInteger bigInteger, int i) {
        if (i != 2) {
            return DaysInMonth.table[i];
        }
        BigInteger bigIntegerMod = bigInteger.mod(FOUR_HUNDRED);
        BigInteger bigInteger2 = BigInteger.ZERO;
        if (bigIntegerMod.equals(bigInteger2)) {
            return 29;
        }
        if (bigInteger.mod(HUNDRED).equals(bigInteger2) || !bigInteger.mod(FOUR).equals(bigInteger2)) {
            return DaysInMonth.table[i];
        }
        return 29;
    }

    private XMLGregorianCalendar normalizeToTimezone(int i) {
        XMLGregorianCalendar xMLGregorianCalendar = (XMLGregorianCalendar) clone();
        int i2 = -i;
        boolean z = i2 >= 0;
        if (i2 < 0) {
            i2 = -i2;
        }
        xMLGregorianCalendar.add(new DurationImpl(z, 0, 0, 0, 0, i2, 0));
        xMLGregorianCalendar.setTimezone(0);
        return xMLGregorianCalendar;
    }

    public static XMLGregorianCalendar parse(String str) {
        return new XMLGregorianCalendarImpl(str);
    }

    private void printNumber(StringBuilder sb, int i, int i2) {
        String strValueOf = String.valueOf(i);
        for (int length = strValueOf.length(); length < i2; length++) {
            sb.append('0');
        }
        sb.append(strValueOf);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        save();
    }

    public static BigInteger sanitize(Number number, int i) {
        if (i == 0 || number == null) {
            return BigInteger.ZERO;
        }
        BigInteger bigInteger = (BigInteger) number;
        return i < 0 ? bigInteger.negate() : bigInteger;
    }

    private void save() {
        this.orig_eon = this.eon;
        this.orig_year = this.year;
        this.orig_month = this.month;
        this.orig_day = this.day;
        this.orig_hour = this.hour;
        this.orig_minute = this.minute;
        this.orig_second = this.second;
        this.orig_fracSeconds = this.fractionalSecond;
        this.orig_timezone = this.timezone;
    }

    private void setEon(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.compareTo(BigInteger.ZERO) != 0) {
            this.eon = bigInteger;
        } else {
            this.eon = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHour(int i, boolean z) {
        if ((i < 0 || i > 24) && i != Integer.MIN_VALUE) {
            invalidFieldValue(3, i);
        }
        this.hour = i;
        if (z) {
            testHour();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void testHour() {
        if (getHour() == 24) {
            if (getMinute() != 0 || getSecond() != 0) {
                invalidFieldValue(3, getHour());
            }
            setHour(0, false);
            add(new DurationImpl(true, 0, 0, 1, 0, 0, 0));
        }
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void add(Duration duration) {
        BigDecimal seconds;
        int i;
        int i2;
        int i3;
        int iIntValue;
        int i4;
        boolean[] zArr = {false, false, false, false, false, false};
        int sign = duration.getSign();
        int month = getMonth();
        if (month == Integer.MIN_VALUE) {
            zArr[1] = true;
            month = 1;
        }
        BigInteger bigIntegerAdd = BigInteger.valueOf(month).add(sanitize(duration.getField(DatatypeConstants.MONTHS), sign));
        BigInteger bigIntegerValueOf = BigInteger.ONE;
        setMonth(bigIntegerAdd.subtract(bigIntegerValueOf).mod(TWELVE).intValue() + 1);
        BigDecimal bigDecimal = new BigDecimal(bigIntegerAdd.subtract(bigIntegerValueOf));
        BigDecimal bigDecimal2 = DECIMAL_TWELVE;
        RoundingMode roundingMode = RoundingMode.FLOOR;
        BigInteger bigInteger = bigDecimal.divide(bigDecimal2, roundingMode).toBigInteger();
        BigInteger eonAndYear = getEonAndYear();
        if (eonAndYear == null) {
            zArr[0] = true;
            eonAndYear = BigInteger.ZERO;
        }
        setYear(eonAndYear.add(sanitize(duration.getField(DatatypeConstants.YEARS), sign)).add(bigInteger));
        if (getSecond() == Integer.MIN_VALUE) {
            zArr[5] = true;
            seconds = DECIMAL_ZERO;
        } else {
            seconds = getSeconds();
        }
        BigDecimal bigDecimalAdd = seconds.add(DurationImpl.sanitize((BigDecimal) duration.getField(DatatypeConstants.SECONDS), sign));
        int i5 = 4;
        BigDecimal bigDecimal3 = new BigDecimal(bigDecimalAdd.toBigInteger());
        BigDecimal bigDecimal4 = DECIMAL_SIXTY;
        BigDecimal bigDecimal5 = new BigDecimal(bigDecimal3.divide(bigDecimal4, roundingMode).toBigInteger());
        BigDecimal bigDecimalSubtract = bigDecimalAdd.subtract(bigDecimal5.multiply(bigDecimal4));
        BigInteger bigInteger2 = bigDecimal5.toBigInteger();
        setSecond(bigDecimalSubtract.intValue());
        int i6 = 3;
        int i7 = 2;
        BigDecimal bigDecimalSubtract2 = bigDecimalSubtract.subtract(new BigDecimal(BigInteger.valueOf(getSecond())));
        if (bigDecimalSubtract2.compareTo(DECIMAL_ZERO) < 0) {
            setFractionalSecond(DECIMAL_ONE.add(bigDecimalSubtract2));
            if (getSecond() == 0) {
                setSecond(59);
                bigInteger2 = bigInteger2.subtract(bigIntegerValueOf);
            } else {
                setSecond(getSecond() - 1);
            }
        } else {
            setFractionalSecond(bigDecimalSubtract2);
        }
        int minute = getMinute();
        if (minute == Integer.MIN_VALUE) {
            zArr[4] = true;
            minute = 0;
        }
        BigInteger bigIntegerAdd2 = BigInteger.valueOf(minute).add(sanitize(duration.getField(DatatypeConstants.MINUTES), sign)).add(bigInteger2);
        setMinute(bigIntegerAdd2.mod(SIXTY).intValue());
        BigInteger bigInteger3 = new BigDecimal(bigIntegerAdd2).divide(bigDecimal4, roundingMode).toBigInteger();
        int hour = getHour();
        if (hour == Integer.MIN_VALUE) {
            zArr[3] = true;
            hour = 0;
        }
        BigInteger bigIntegerAdd3 = BigInteger.valueOf(hour).add(sanitize(duration.getField(DatatypeConstants.HOURS), sign)).add(bigInteger3);
        setHour(bigIntegerAdd3.mod(TWENTY_FOUR).intValue(), false);
        BigInteger bigInteger4 = new BigDecimal(bigIntegerAdd3).divide(DECIMAL_TWENTY_FOUR, roundingMode).toBigInteger();
        int day = getDay();
        if (day == Integer.MIN_VALUE) {
            zArr[2] = true;
            day = 1;
        }
        BigInteger bigIntegerSanitize = sanitize(duration.getField(DatatypeConstants.DAYS), sign);
        int iMaximumDayInMonthFor = maximumDayInMonthFor(getEonAndYear(), getMonth());
        if (day > iMaximumDayInMonthFor) {
            bigIntegerValueOf = BigInteger.valueOf(iMaximumDayInMonthFor);
        } else if (day >= 1) {
            bigIntegerValueOf = BigInteger.valueOf(day);
        }
        BigInteger bigIntegerAdd4 = bigIntegerValueOf.add(bigIntegerSanitize).add(bigInteger4);
        while (true) {
            BigInteger bigInteger5 = BigInteger.ONE;
            if (bigIntegerAdd4.compareTo(bigInteger5) >= 0) {
                if (bigIntegerAdd4.compareTo(BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear(), getMonth()))) <= 0) {
                    break;
                }
                bigIntegerAdd4 = bigIntegerAdd4.add(BigInteger.valueOf(-maximumDayInMonthFor(getEonAndYear(), getMonth())));
                i3 = 1;
            } else {
                bigIntegerAdd4 = bigIntegerAdd4.add(this.month >= i7 ? BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear(), getMonth() - 1)) : BigInteger.valueOf(maximumDayInMonthFor(getEonAndYear().subtract(bigInteger5), 12)));
                i3 = -1;
            }
            int month2 = (getMonth() + i3) - 1;
            int i8 = month2 % 12;
            if (i8 < 0) {
                i4 = i8 + 13;
                iIntValue = BigDecimal.valueOf(month2).divide(DECIMAL_TWELVE, RoundingMode.UP).intValue();
            } else {
                iIntValue = month2 / 12;
                i4 = i8 + 1;
            }
            setMonth(i4);
            if (iIntValue != 0) {
                setYear(getEonAndYear().add(BigInteger.valueOf(iIntValue)));
            }
            i7 = 2;
        }
        setDay(bigIntegerAdd4.intValue());
        int i9 = 0;
        while (i9 <= 5) {
            if (!zArr[i9]) {
                i = i5;
                i2 = i6;
            } else if (i9 == 0) {
                i = i5;
                i2 = i6;
                setYear(Integer.MIN_VALUE);
            } else if (i9 == 1) {
                i = i5;
                i2 = i6;
                setMonth(Integer.MIN_VALUE);
            } else if (i9 != 2) {
                i2 = i6;
                if (i9 != i2) {
                    i = i5;
                    if (i9 == i) {
                        setMinute(Integer.MIN_VALUE);
                    } else if (i9 == 5) {
                        setSecond(Integer.MIN_VALUE);
                        setFractionalSecond(null);
                    }
                } else {
                    i = i5;
                    setHour(Integer.MIN_VALUE, false);
                }
            } else {
                i = i5;
                i2 = i6;
                setDay(Integer.MIN_VALUE);
            }
            i9++;
            i6 = i2;
            i5 = i;
        }
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void clear() {
        this.eon = null;
        this.year = Integer.MIN_VALUE;
        this.month = Integer.MIN_VALUE;
        this.day = Integer.MIN_VALUE;
        this.timezone = Integer.MIN_VALUE;
        this.hour = Integer.MIN_VALUE;
        this.minute = Integer.MIN_VALUE;
        this.second = Integer.MIN_VALUE;
        this.fractionalSecond = null;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public Object clone() {
        return new XMLGregorianCalendarImpl(getEonAndYear(), this.month, this.day, this.hour, this.minute, this.second, this.fractionalSecond, this.timezone);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int compare(XMLGregorianCalendar xMLGregorianCalendar) {
        XMLGregorianCalendarImpl xMLGregorianCalendarImpl = (XMLGregorianCalendarImpl) xMLGregorianCalendar;
        if (getTimezone() == xMLGregorianCalendarImpl.getTimezone()) {
            return internalCompare(this, xMLGregorianCalendarImpl);
        }
        if (getTimezone() != Integer.MIN_VALUE && xMLGregorianCalendarImpl.getTimezone() != Integer.MIN_VALUE) {
            return internalCompare((XMLGregorianCalendarImpl) normalize(), (XMLGregorianCalendarImpl) xMLGregorianCalendarImpl.normalize());
        }
        if (getTimezone() != Integer.MIN_VALUE) {
            if (getTimezone() != 0) {
                this = (XMLGregorianCalendarImpl) normalize();
            }
            int iInternalCompare = internalCompare(this, xMLGregorianCalendarImpl.normalizeToTimezone(DatatypeConstants.MIN_TIMEZONE_OFFSET));
            if (iInternalCompare == -1) {
                return iInternalCompare;
            }
            int iInternalCompare2 = internalCompare(this, xMLGregorianCalendarImpl.normalizeToTimezone(DatatypeConstants.MAX_TIMEZONE_OFFSET));
            if (iInternalCompare2 == 1) {
                return iInternalCompare2;
            }
            return 2;
        }
        if (xMLGregorianCalendarImpl.getTimezone() != 0) {
            xMLGregorianCalendarImpl = (XMLGregorianCalendarImpl) xMLGregorianCalendarImpl.normalizeToTimezone(xMLGregorianCalendarImpl.getTimezone());
        }
        int iInternalCompare3 = internalCompare(normalizeToTimezone(DatatypeConstants.MAX_TIMEZONE_OFFSET), xMLGregorianCalendarImpl);
        if (iInternalCompare3 == -1) {
            return iInternalCompare3;
        }
        int iInternalCompare4 = internalCompare(normalizeToTimezone(DatatypeConstants.MIN_TIMEZONE_OFFSET), xMLGregorianCalendarImpl);
        if (iInternalCompare4 == 1) {
            return iInternalCompare4;
        }
        return 2;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getDay() {
        return this.day;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public BigInteger getEon() {
        return this.eon;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public BigInteger getEonAndYear() {
        BigInteger bigInteger;
        int i = this.year;
        if (i != Integer.MIN_VALUE && (bigInteger = this.eon) != null) {
            return bigInteger.add(BigInteger.valueOf(i));
        }
        if (i == Integer.MIN_VALUE || this.eon != null) {
            return null;
        }
        return BigInteger.valueOf(i);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public BigDecimal getFractionalSecond() {
        return this.fractionalSecond;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getHour() {
        return this.hour;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getMillisecond() {
        BigDecimal bigDecimal = this.fractionalSecond;
        if (bigDecimal == null) {
            return Integer.MIN_VALUE;
        }
        return bigDecimal.movePointRight(3).intValue();
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getMinute() {
        return this.minute;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getMonth() {
        return this.month;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getSecond() {
        return this.second;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public TimeZone getTimeZone(int i) {
        int timezone = getTimezone();
        if (timezone != Integer.MIN_VALUE) {
            i = timezone;
        }
        if (i == Integer.MIN_VALUE) {
            return TimeZone.getDefault();
        }
        char c = i < 0 ? '-' : '+';
        if (c == '-') {
            i = -i;
        }
        int i2 = i / 60;
        int i3 = i - (i2 * 60);
        StringBuffer stringBuffer = new StringBuffer(8);
        stringBuffer.append("GMT");
        stringBuffer.append(c);
        stringBuffer.append(i2);
        if (i3 != 0) {
            if (i3 < 10) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i3);
        }
        return TimeZone.getTimeZone(stringBuffer.toString());
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getTimezone() {
        return this.timezone;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public QName getXMLSchemaType() {
        int i = (this.year != Integer.MIN_VALUE ? ' ' : (char) 0) | (this.month != Integer.MIN_VALUE ? (char) 16 : (char) 0) | (this.day != Integer.MIN_VALUE ? 8 : 0) | (this.hour != Integer.MIN_VALUE ? 4 : 0) | (this.minute != Integer.MIN_VALUE ? 2 : 0) | (this.second != Integer.MIN_VALUE ? (char) 1 : (char) 0);
        if (i == 7) {
            return DatatypeConstants.TIME;
        }
        if (i == 8) {
            return DatatypeConstants.GDAY;
        }
        if (i == 16) {
            return DatatypeConstants.GMONTH;
        }
        if (i == 24) {
            return DatatypeConstants.GMONTHDAY;
        }
        if (i == 32) {
            return DatatypeConstants.GYEAR;
        }
        if (i == 48) {
            return DatatypeConstants.GYEARMONTH;
        }
        if (i == 56) {
            return DatatypeConstants.DATE;
        }
        if (i == 63) {
            return DatatypeConstants.DATETIME;
        }
        throw new IllegalStateException(getClass().getName() + "#getXMLSchemaType() :" + DatatypeMessageFormatter.formatMessage(null, "InvalidXGCFields", null));
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public int getYear() {
        return this.year;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final boolean isValid() {
        BigDecimal bigDecimal;
        int i;
        int i2 = this.month;
        if (i2 != Integer.MIN_VALUE && (i = this.day) != Integer.MIN_VALUE) {
            int i3 = this.year;
            if (i3 != Integer.MIN_VALUE) {
                if (this.eon == null) {
                    if (i > maximumDayInMonthFor(i3, i2)) {
                        return false;
                    }
                } else if (i > maximumDayInMonthFor(getEonAndYear(), this.month)) {
                    return false;
                }
            } else if (i > maximumDayInMonthFor(WinError.ERROR_INVALID_PIXEL_FORMAT, i2)) {
                return false;
            }
        }
        if (this.hour != 24 || (this.minute == 0 && this.second == 0 && ((bigDecimal = this.fractionalSecond) == null || bigDecimal.compareTo(DECIMAL_ZERO) == 0))) {
            return (this.eon == null && this.year == 0) ? false : true;
        }
        return false;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public XMLGregorianCalendar normalize() {
        XMLGregorianCalendar xMLGregorianCalendarNormalizeToTimezone = normalizeToTimezone(this.timezone);
        if (getTimezone() == Integer.MIN_VALUE) {
            xMLGregorianCalendarNormalizeToTimezone.setTimezone(Integer.MIN_VALUE);
        }
        if (getMillisecond() == Integer.MIN_VALUE) {
            xMLGregorianCalendarNormalizeToTimezone.setMillisecond(Integer.MIN_VALUE);
        }
        return xMLGregorianCalendarNormalizeToTimezone;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void reset() {
        this.eon = this.orig_eon;
        this.year = this.orig_year;
        this.month = this.orig_month;
        this.day = this.orig_day;
        this.hour = this.orig_hour;
        this.minute = this.orig_minute;
        this.second = this.orig_second;
        this.fractionalSecond = this.orig_fracSeconds;
        this.timezone = this.orig_timezone;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setDay(int i) {
        if ((i < 1 || 31 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(2, i);
        }
        this.day = i;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setFractionalSecond(BigDecimal bigDecimal) {
        if (bigDecimal == null || (bigDecimal.compareTo(DECIMAL_ZERO) >= 0 && bigDecimal.compareTo(DECIMAL_ONE) <= 0)) {
            this.fractionalSecond = bigDecimal;
        } else {
            w01.a(DatatypeMessageFormatter.formatMessage(null, "InvalidFractional", new Object[]{bigDecimal.toString()}));
        }
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void setMillisecond(int i) {
        if (i == Integer.MIN_VALUE) {
            this.fractionalSecond = null;
            return;
        }
        if ((i < 0 || 999 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(6, i);
        }
        this.fractionalSecond = BigDecimal.valueOf(i, 3);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void setMinute(int i) {
        if ((i < 0 || 59 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(4, i);
        }
        this.minute = i;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setMonth(int i) {
        if ((i < 1 || 12 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(1, i);
        }
        this.month = i;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void setSecond(int i) {
        if ((i < 0 || 60 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(5, i);
        }
        this.second = i;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setTime(int i, int i2, int i3, BigDecimal bigDecimal) {
        setHour(i, false);
        setMinute(i2);
        if (i3 != 60) {
            setSecond(i3);
        } else if ((i == 23 && i2 == 59) || (i == 0 && i2 == 0)) {
            setSecond(i3);
        } else {
            invalidFieldValue(5, i3);
        }
        setFractionalSecond(bigDecimal);
        testHour();
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setTimezone(int i) {
        if ((i < -840 || 840 < i) && i != Integer.MIN_VALUE) {
            invalidFieldValue(7, i);
        }
        this.timezone = i;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setYear(int i) {
        if (i == Integer.MIN_VALUE) {
            this.year = Integer.MIN_VALUE;
            this.eon = null;
        } else if (Math.abs(i) < BILLION_I) {
            this.year = i;
            this.eon = null;
        } else {
            BigInteger bigIntegerValueOf = BigInteger.valueOf(i);
            BigInteger bigIntegerRemainder = bigIntegerValueOf.remainder(BILLION_B);
            this.year = bigIntegerRemainder.intValue();
            setEon(bigIntegerValueOf.subtract(bigIntegerRemainder));
        }
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public GregorianCalendar toGregorianCalendar(TimeZone timeZone, Locale locale, XMLGregorianCalendar xMLGregorianCalendar) {
        int year;
        if (timeZone == null) {
            timeZone = getTimeZone(xMLGregorianCalendar != null ? xMLGregorianCalendar.getTimezone() : Integer.MIN_VALUE);
        }
        if (locale == null) {
            locale = Locale.getDefault();
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, locale);
        gregorianCalendar.clear();
        gregorianCalendar.setGregorianChange(PURE_GREGORIAN_CHANGE);
        int i = this.year;
        if (i != Integer.MIN_VALUE) {
            if (this.eon == null) {
                gregorianCalendar.set(0, i < 0 ? 0 : 1);
                gregorianCalendar.set(1, Math.abs(this.year));
            } else {
                BigInteger eonAndYear = getEonAndYear();
                gregorianCalendar.set(0, eonAndYear.signum() == -1 ? 0 : 1);
                gregorianCalendar.set(1, eonAndYear.abs().intValue());
            }
        } else if (xMLGregorianCalendar != null && (year = xMLGregorianCalendar.getYear()) != Integer.MIN_VALUE) {
            if (xMLGregorianCalendar.getEon() == null) {
                gregorianCalendar.set(0, year < 0 ? 0 : 1);
                gregorianCalendar.set(1, Math.abs(year));
            } else {
                BigInteger eonAndYear2 = xMLGregorianCalendar.getEonAndYear();
                gregorianCalendar.set(0, eonAndYear2.signum() == -1 ? 0 : 1);
                gregorianCalendar.set(1, eonAndYear2.abs().intValue());
            }
        }
        int i2 = this.month;
        if (i2 != Integer.MIN_VALUE) {
            gregorianCalendar.set(2, i2 - 1);
        } else {
            int month = xMLGregorianCalendar != null ? xMLGregorianCalendar.getMonth() : Integer.MIN_VALUE;
            if (month != Integer.MIN_VALUE) {
                gregorianCalendar.set(2, month - 1);
            }
        }
        int i3 = this.day;
        if (i3 != Integer.MIN_VALUE) {
            gregorianCalendar.set(5, i3);
        } else {
            int day = xMLGregorianCalendar != null ? xMLGregorianCalendar.getDay() : Integer.MIN_VALUE;
            if (day != Integer.MIN_VALUE) {
                gregorianCalendar.set(5, day);
            }
        }
        int i4 = this.hour;
        if (i4 != Integer.MIN_VALUE) {
            gregorianCalendar.set(11, i4);
        } else {
            int hour = xMLGregorianCalendar != null ? xMLGregorianCalendar.getHour() : Integer.MIN_VALUE;
            if (hour != Integer.MIN_VALUE) {
                gregorianCalendar.set(11, hour);
            }
        }
        int i5 = this.minute;
        if (i5 != Integer.MIN_VALUE) {
            gregorianCalendar.set(12, i5);
        } else {
            int minute = xMLGregorianCalendar != null ? xMLGregorianCalendar.getMinute() : Integer.MIN_VALUE;
            if (minute != Integer.MIN_VALUE) {
                gregorianCalendar.set(12, minute);
            }
        }
        int i6 = this.second;
        if (i6 != Integer.MIN_VALUE) {
            gregorianCalendar.set(13, i6);
        } else {
            int second = xMLGregorianCalendar != null ? xMLGregorianCalendar.getSecond() : Integer.MIN_VALUE;
            if (second != Integer.MIN_VALUE) {
                gregorianCalendar.set(13, second);
            }
        }
        if (this.fractionalSecond != null) {
            gregorianCalendar.set(14, getMillisecond());
            return gregorianCalendar;
        }
        if ((xMLGregorianCalendar != null ? xMLGregorianCalendar.getFractionalSecond() : null) != null) {
            gregorianCalendar.set(14, xMLGregorianCalendar.getMillisecond());
        }
        return gregorianCalendar;
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public String toXMLFormat() {
        String str;
        QName xMLSchemaType = getXMLSchemaType();
        if (xMLSchemaType == DatatypeConstants.DATETIME) {
            str = "%Y-%M-%DT%h:%m:%s%z";
        } else if (xMLSchemaType == DatatypeConstants.DATE) {
            str = "%Y-%M-%D%z";
        } else if (xMLSchemaType == DatatypeConstants.TIME) {
            str = "%h:%m:%s%z";
        } else if (xMLSchemaType == DatatypeConstants.GMONTH) {
            str = "--%M%z";
        } else if (xMLSchemaType == DatatypeConstants.GDAY) {
            str = "---%D%z";
        } else if (xMLSchemaType == DatatypeConstants.GYEAR) {
            str = "%Y%z";
        } else if (xMLSchemaType == DatatypeConstants.GYEARMONTH) {
            str = "%Y-%M%z";
        } else {
            str = xMLSchemaType == DatatypeConstants.GMONTHDAY ? "--%M-%D%z" : null;
        }
        return format(str);
    }

    public static XMLGregorianCalendar createDateTime(BigInteger bigInteger, int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, int i6) {
        return new XMLGregorianCalendarImpl(bigInteger, i, i2, i3, i4, i5, bigDecimal, i6);
    }

    public static XMLGregorianCalendar createDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new XMLGregorianCalendarImpl(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static XMLGregorianCalendar createTime(int i, int i2, int i3, BigDecimal bigDecimal, int i4) {
        return new XMLGregorianCalendarImpl((BigInteger) null, Integer.MIN_VALUE, Integer.MIN_VALUE, i, i2, i3, bigDecimal, i4);
    }

    private static int compareField(BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger == null) {
            return bigInteger2 == null ? 0 : 2;
        }
        if (bigInteger2 == null) {
            return 2;
        }
        return bigInteger.compareTo(bigInteger2);
    }

    public static XMLGregorianCalendar createTime(int i, int i2, int i3, int i4, int i5) {
        return new XMLGregorianCalendarImpl(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, i, i2, i3, i4, i5);
    }

    private static int compareField(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        if (bigDecimal == bigDecimal2) {
            return 0;
        }
        if (bigDecimal == null) {
            bigDecimal = DECIMAL_ZERO;
        }
        if (bigDecimal2 == null) {
            bigDecimal2 = DECIMAL_ZERO;
        }
        return bigDecimal.compareTo(bigDecimal2);
    }

    private void printNumber(StringBuilder sb, BigInteger bigInteger, int i) {
        String string = bigInteger.toString();
        for (int length = string.length(); length < i; length++) {
            sb.append('0');
        }
        sb.append(string);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public void setHour(int i) {
        setHour(i, true);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setTime(int i, int i2, int i3) {
        setTime(i, i2, i3, (BigDecimal) null);
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setTime(int i, int i2, int i3, int i4) {
        setHour(i, false);
        setMinute(i2);
        if (i3 != 60) {
            setSecond(i3);
        } else if ((i == 23 && i2 == 59) || (i == 0 && i2 == 0)) {
            setSecond(i3);
        } else {
            invalidFieldValue(5, i3);
        }
        setMillisecond(i4);
        testHour();
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public final void setYear(BigInteger bigInteger) {
        if (bigInteger == null) {
            this.eon = null;
            this.year = Integer.MIN_VALUE;
        } else {
            BigInteger bigIntegerRemainder = bigInteger.remainder(BILLION_B);
            this.year = bigIntegerRemainder.intValue();
            setEon(bigInteger.subtract(bigIntegerRemainder));
        }
    }

    private static int maximumDayInMonthFor(int i, int i2) {
        if (i2 != 2) {
            return DaysInMonth.table[i2];
        }
        if (i % 400 == 0) {
            return 29;
        }
        if (i % 100 == 0 || i % 4 != 0) {
            return DaysInMonth.table[2];
        }
        return 29;
    }

    public XMLGregorianCalendarImpl() {
    }

    public XMLGregorianCalendarImpl(BigInteger bigInteger, int i, int i2, int i3, int i4, int i5, BigDecimal bigDecimal, int i6) {
        setYear(bigInteger);
        setMonth(i);
        setDay(i2);
        setTime(i3, i4, i5, bigDecimal);
        setTimezone(i6);
        if (isValid()) {
            save();
        } else {
            w01.a(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-fractional", new Object[]{bigInteger, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), bigDecimal, Integer.valueOf(i6)}));
            throw null;
        }
    }

    private XMLGregorianCalendarImpl(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setYear(i);
        setMonth(i2);
        setDay(i3);
        setTime(i4, i5, i6);
        setTimezone(i8);
        setFractionalSecond(i7 != Integer.MIN_VALUE ? BigDecimal.valueOf(i7, 3) : null);
        if (isValid()) {
            save();
        } else {
            w01.a(DatatypeMessageFormatter.formatMessage(null, "InvalidXGCValue-milli", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i8)}));
            throw null;
        }
    }

    public XMLGregorianCalendarImpl(GregorianCalendar gregorianCalendar) {
        int i = gregorianCalendar.get(1);
        setYear(gregorianCalendar.get(0) == 0 ? -i : i);
        setMonth(gregorianCalendar.get(2) + 1);
        setDay(gregorianCalendar.get(5));
        setTime(gregorianCalendar.get(11), gregorianCalendar.get(12), gregorianCalendar.get(13), gregorianCalendar.get(14));
        setTimezone((gregorianCalendar.get(15) + gregorianCalendar.get(16)) / 60000);
        save();
    }

    @Override // javax.xml.datatype.XMLGregorianCalendar
    public GregorianCalendar toGregorianCalendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(getTimeZone(Integer.MIN_VALUE), getDefaultLocale());
        gregorianCalendar.clear();
        gregorianCalendar.setGregorianChange(PURE_GREGORIAN_CHANGE);
        int i = this.year;
        if (i != Integer.MIN_VALUE) {
            if (this.eon == null) {
                gregorianCalendar.set(0, i < 0 ? 0 : 1);
                gregorianCalendar.set(1, Math.abs(this.year));
            } else {
                BigInteger eonAndYear = getEonAndYear();
                gregorianCalendar.set(0, eonAndYear.signum() == -1 ? 0 : 1);
                gregorianCalendar.set(1, eonAndYear.abs().intValue());
            }
        }
        int i2 = this.month;
        if (i2 != Integer.MIN_VALUE) {
            gregorianCalendar.set(2, i2 - 1);
        }
        int i3 = this.day;
        if (i3 != Integer.MIN_VALUE) {
            gregorianCalendar.set(5, i3);
        }
        int i4 = this.hour;
        if (i4 != Integer.MIN_VALUE) {
            gregorianCalendar.set(11, i4);
        }
        int i5 = this.minute;
        if (i5 != Integer.MIN_VALUE) {
            gregorianCalendar.set(12, i5);
        }
        int i6 = this.second;
        if (i6 != Integer.MIN_VALUE) {
            gregorianCalendar.set(13, i6);
        }
        if (this.fractionalSecond != null) {
            gregorianCalendar.set(14, getMillisecond());
        }
        return gregorianCalendar;
    }
}
