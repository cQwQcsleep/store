package com.sun.org.apache.xalan.internal.lib;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.objects.XBoolean;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExsltDatetime {
    static final String EMPTY_STR = "";
    static final String d = "yyyy-MM-dd";
    static final String dt = "yyyy-MM-dd'T'HH:mm:ss";
    static final String gd = "---dd";
    static final String gm = "--MM--";
    static final String gmd = "--MM-dd";
    static final String gy = "yyyy";
    static final String gym = "yyyy-MM";
    static final String t = "HH:mm:ss";

    public static String date(String str) throws ParseException {
        Date dateTestFormats;
        String[] eraDatetimeZone = getEraDatetimeZone(str);
        String str2 = eraDatetimeZone[0];
        String str3 = eraDatetimeZone[1];
        String str4 = eraDatetimeZone[2];
        if (str3 == null || str4 == null || (dateTestFormats = testFormats(str3, new String[]{dt, d})) == null) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(d);
        simpleDateFormat.setLenient(false);
        String str5 = simpleDateFormat.format(dateTestFormats);
        if (str5.length() == 0) {
            return "";
        }
        return str2 + str5 + str4;
    }

    public static String dateTime() {
        Calendar calendar = Calendar.getInstance();
        StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat(dt).format(calendar.getTime()));
        int i = calendar.get(15) + calendar.get(16);
        if (i == 0) {
            stringBuffer.append(Constants.HASIDCALL_INDEX_SIG);
        } else {
            int i2 = i / 3600000;
            int i3 = i % 3600000;
            stringBuffer.append(i2 < 0 ? LocaleUtility.IETF_SEPARATOR : '+');
            stringBuffer.append(formatDigits(i2));
            stringBuffer.append(':');
            stringBuffer.append(formatDigits(i3));
        }
        return stringBuffer.toString();
    }

    public static String dayAbbreviation(String str) throws ParseException {
        return getEraDatetimeZone(str)[1] == null ? "" : getNameOrAbbrev(str, new String[]{dt, d}, "EEE");
    }

    public static double dayInMonth(String str) throws ParseException {
        return getNumber(getEraDatetimeZone(str)[1], new String[]{dt, d, gmd, gd}, 5);
    }

    public static double dayInWeek(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, d}, 7);
    }

    public static double dayInYear(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, d}, 6);
    }

    public static String dayName(String str) throws ParseException {
        return getEraDatetimeZone(str)[1] == null ? "" : getNameOrAbbrev(str, new String[]{dt, d}, "EEEE");
    }

    public static double dayOfWeekInMonth(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, d}, 8);
    }

    public static String formatDate(String str, String str2) {
        TimeZone timeZone;
        String str3 = "z";
        if (str.endsWith(Constants.HASIDCALL_INDEX_SIG) || str.endsWith("z")) {
            timeZone = TimeZone.getTimeZone("GMT");
            str = str.substring(0, str.length() - 1).concat("GMT");
        } else if (str.length() >= 6 && str.charAt(str.length() - 3) == ':' && (str.charAt(str.length() - 6) == '+' || str.charAt(str.length() - 6) == '-')) {
            String strSubstring = str.substring(str.length() - 6);
            timeZone = ("+00:00".equals(strSubstring) || "-00:00".equals(strSubstring)) ? TimeZone.getTimeZone("GMT") : TimeZone.getTimeZone("GMT".concat(strSubstring));
            str = str.substring(0, str.length() - 6) + "GMT" + strSubstring;
        } else {
            timeZone = TimeZone.getDefault();
            str3 = "";
        }
        String[] strArr = {dt.concat(str3), d, gym, gy};
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(t.concat(str3));
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(str);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(strip("GyMdDEFwW", str2));
            simpleDateFormat2.setTimeZone(timeZone);
            return simpleDateFormat2.format(date);
        } catch (ParseException unused) {
            for (int i = 0; i < 4; i++) {
                try {
                    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(strArr[i]);
                    simpleDateFormat3.setLenient(false);
                    Date date2 = simpleDateFormat3.parse(str);
                    SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(str2);
                    simpleDateFormat4.setTimeZone(timeZone);
                    return simpleDateFormat4.format(date2);
                } catch (ParseException unused2) {
                }
            }
            try {
                try {
                    try {
                        SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat(gmd);
                        simpleDateFormat5.setLenient(false);
                        Date date3 = simpleDateFormat5.parse(str);
                        SimpleDateFormat simpleDateFormat6 = new SimpleDateFormat(strip("Gy", str2));
                        simpleDateFormat6.setTimeZone(timeZone);
                        return simpleDateFormat6.format(date3);
                    } catch (ParseException unused3) {
                        return "";
                    }
                } catch (ParseException unused4) {
                    SimpleDateFormat simpleDateFormat7 = new SimpleDateFormat(gm);
                    simpleDateFormat7.setLenient(false);
                    Date date4 = simpleDateFormat7.parse(str);
                    SimpleDateFormat simpleDateFormat8 = new SimpleDateFormat(strip("Gy", str2));
                    simpleDateFormat8.setTimeZone(timeZone);
                    return simpleDateFormat8.format(date4);
                }
            } catch (ParseException unused5) {
                SimpleDateFormat simpleDateFormat9 = new SimpleDateFormat(gd);
                simpleDateFormat9.setLenient(false);
                Date date5 = simpleDateFormat9.parse(str);
                SimpleDateFormat simpleDateFormat10 = new SimpleDateFormat(strip("GyM", str2));
                simpleDateFormat10.setTimeZone(timeZone);
                return simpleDateFormat10.format(date5);
            }
        }
    }

    private static String formatDigits(int i) {
        String strValueOf = String.valueOf(Math.abs(i));
        return strValueOf.length() == 1 ? "0".concat(strValueOf) : strValueOf;
    }

    private static String[] getEraDatetimeZone(String str) {
        String str2;
        String strSubstring = "";
        if (str.charAt(0) != '-' || str.startsWith("--")) {
            str2 = "";
        } else {
            str = str.substring(1);
            str2 = "-";
        }
        int zoneStart = getZoneStart(str);
        if (zoneStart > 0) {
            strSubstring = str.substring(zoneStart);
            str = str.substring(0, zoneStart);
        } else if (zoneStart == -2) {
            strSubstring = null;
        }
        return new String[]{str2, str, strSubstring};
    }

    private static String getNameOrAbbrev(String str, String[] strArr, String str2) throws ParseException {
        for (String str3 : strArr) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3, Locale.ENGLISH);
                simpleDateFormat.setLenient(false);
                Date date = simpleDateFormat.parse(str);
                simpleDateFormat.applyPattern(str2);
                return simpleDateFormat.format(date);
            } catch (ParseException unused) {
            }
        }
        return "";
    }

    private static double getNumber(String str, String[] strArr, int i) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        Date dateTestFormats = testFormats(str, strArr);
        if (dateTestFormats == null) {
            return Double.NaN;
        }
        calendar.setTime(dateTestFormats);
        return calendar.get(i);
    }

    private static int getZoneStart(String str) {
        if (str.indexOf(Constants.HASIDCALL_INDEX_SIG) == str.length() - 1) {
            return str.length() - 1;
        }
        if (str.length() < 6 || str.charAt(str.length() - 3) != ':') {
            return -1;
        }
        if (str.charAt(str.length() - 6) != '+' && str.charAt(str.length() - 6) != '-') {
            return -1;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(str.substring(str.length() - 5));
            return str.length() - 6;
        } catch (ParseException e) {
            System.out.println("ParseException " + e.getErrorOffset());
            return -2;
        }
    }

    public static double hourInDay(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, t}, 11);
    }

    public static XObject leapYear(String str) throws ParseException {
        boolean z = true;
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return new XNumber(Double.NaN);
        }
        double number = getNumber(str2, new String[]{dt, d, gym, gy}, 1);
        if (number == Double.NaN) {
            return new XNumber(Double.NaN);
        }
        int i = (int) number;
        if (i % 400 != 0 && (i % 100 == 0 || i % 4 != 0)) {
            z = false;
        }
        return new XBoolean(z);
    }

    public static double minuteInHour(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, t}, 12);
    }

    public static String monthAbbreviation(String str) throws ParseException {
        return getEraDatetimeZone(str)[1] == null ? "" : getNameOrAbbrev(str, new String[]{dt, d, gym, gm}, "MMM");
    }

    public static double monthInYear(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, d, gym, gm, gmd}, 2) + 1.0d;
    }

    public static String monthName(String str) throws ParseException {
        return getEraDatetimeZone(str)[1] == null ? "" : getNameOrAbbrev(str, new String[]{dt, d, gym, gm}, "MMMM");
    }

    public static double secondInMinute(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, t}, 13);
    }

    private static String strip(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(str2.length());
        int i = 0;
        while (i < str2.length()) {
            char cCharAt = str2.charAt(i);
            if (cCharAt == '\'') {
                int iIndexOf = str2.indexOf(39, i + 1);
                if (iIndexOf == -1) {
                    iIndexOf = str2.length();
                }
                stringBuffer.append(str2.substring(i, iIndexOf));
                i = iIndexOf;
            } else {
                if (str.indexOf(cCharAt) <= -1) {
                    stringBuffer.append(cCharAt);
                }
                i++;
            }
        }
        return stringBuffer.toString();
    }

    private static Date testFormats(String str, String[] strArr) throws ParseException {
        for (String str2 : strArr) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
                simpleDateFormat.setLenient(false);
                return simpleDateFormat.parse(str);
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public static String time(String str) throws ParseException {
        Date dateTestFormats;
        String[] eraDatetimeZone = getEraDatetimeZone(str);
        String str2 = eraDatetimeZone[1];
        String str3 = eraDatetimeZone[2];
        if (str2 == null || str3 == null || (dateTestFormats = testFormats(str2, new String[]{dt, d, t})) == null) {
            return "";
        }
        return new SimpleDateFormat(t).format(dateTestFormats) + str3;
    }

    public static double weekInYear(String str) throws ParseException {
        String str2 = getEraDatetimeZone(str)[1];
        if (str2 == null) {
            return Double.NaN;
        }
        return getNumber(str2, new String[]{dt, d}, 3);
    }

    public static double year(String str) throws ParseException {
        String[] eraDatetimeZone = getEraDatetimeZone(str);
        boolean z = eraDatetimeZone[0].length() == 0;
        String str2 = eraDatetimeZone[1];
        if (str2 == null) {
            return Double.NaN;
        }
        double number = getNumber(str2, new String[]{dt, d, gym, gy}, 1);
        return (z || number == Double.NaN) ? number : -number;
    }

    public static double dayInMonth() {
        return Calendar.getInstance().get(5);
    }

    public static double dayInWeek() {
        return Calendar.getInstance().get(7);
    }

    public static double dayInYear() {
        return Calendar.getInstance().get(6);
    }

    public static double hourInDay() {
        return Calendar.getInstance().get(11);
    }

    public static double minuteInHour() {
        return Calendar.getInstance().get(12);
    }

    public static double secondInMinute() {
        return Calendar.getInstance().get(13);
    }

    public static double weekInYear() {
        return Calendar.getInstance().get(3);
    }

    public static String dayAbbreviation() {
        return getNameOrAbbrev("EEE");
    }

    public static String dayName() {
        return getNameOrAbbrev("EEEE");
    }

    public static double dayOfWeekInMonth() {
        return Calendar.getInstance().get(8);
    }

    public static String monthAbbreviation() {
        return getNameOrAbbrev("MMM");
    }

    public static String monthName() {
        Calendar.getInstance();
        return getNameOrAbbrev("MMMM");
    }

    private static String getNameOrAbbrev(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(Calendar.getInstance().getTime());
    }

    public static double monthInYear() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static double year() {
        return Calendar.getInstance().get(1);
    }

    public static String time() {
        String string = dateTime().toString();
        return string.substring(string.indexOf("T") + 1);
    }

    public static boolean leapYear() {
        int i = Calendar.getInstance().get(1);
        return i % 400 == 0 || (i % 100 != 0 && i % 4 == 0);
    }

    public static String date() {
        String string = dateTime().toString();
        return string.substring(0, string.indexOf("T")).concat(string.substring(getZoneStart(string)));
    }
}
