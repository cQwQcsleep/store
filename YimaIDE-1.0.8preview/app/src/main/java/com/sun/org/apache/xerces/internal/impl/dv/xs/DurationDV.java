package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.XPath;
import defpackage.sxf;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DurationDV extends AbstractDateTimeDV {
    private static final AbstractDateTimeDV.DateTimeData[] DATETIMES = {new AbstractDateTimeDV.DateTimeData(1696, 9, 1, 0, 0, XPath.MATCH_SCORE_QNAME, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(1697, 2, 1, 0, 0, XPath.MATCH_SCORE_QNAME, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(WinError.ERROR_INVALID_FORM_SIZE, 3, 1, 0, 0, XPath.MATCH_SCORE_QNAME, 90, null, true, null), new AbstractDateTimeDV.DateTimeData(WinError.ERROR_INVALID_FORM_SIZE, 7, 1, 0, 0, XPath.MATCH_SCORE_QNAME, 90, null, true, null)};
    public static final int DAYTIMEDURATION_TYPE = 2;
    public static final int DURATION_TYPE = 0;
    public static final int YEARMONTHDURATION_TYPE = 1;

    private AbstractDateTimeDV.DateTimeData addDuration(AbstractDateTimeDV.DateTimeData dateTimeData, AbstractDateTimeDV.DateTimeData dateTimeData2, AbstractDateTimeDV.DateTimeData dateTimeData3) {
        int i;
        resetDateObj(dateTimeData3);
        int i2 = dateTimeData2.month + dateTimeData.month;
        dateTimeData3.month = modulo(i2, 1, 13);
        dateTimeData3.year = dateTimeData2.year + dateTimeData.year + fQuotient(i2, 1, 13);
        double d = dateTimeData2.second + dateTimeData.second;
        int iFloor = (int) Math.floor(d / 60.0d);
        dateTimeData3.second = d - ((double) (iFloor * 60));
        int i3 = dateTimeData2.minute + dateTimeData.minute + iFloor;
        int iFQuotient = fQuotient(i3, 60);
        dateTimeData3.minute = mod(i3, 60, iFQuotient);
        int i4 = dateTimeData2.hour + dateTimeData.hour + iFQuotient;
        int iFQuotient2 = fQuotient(i4, 24);
        dateTimeData3.hour = mod(i4, 24, iFQuotient2);
        dateTimeData3.day = dateTimeData2.day + dateTimeData.day + iFQuotient2;
        while (true) {
            int iMaxDayInMonthFor = maxDayInMonthFor(dateTimeData3.year, dateTimeData3.month);
            int i5 = dateTimeData3.day;
            if (i5 < 1) {
                dateTimeData3.day = i5 + maxDayInMonthFor(dateTimeData3.year, dateTimeData3.month - 1);
                i = -1;
            } else {
                if (i5 <= iMaxDayInMonthFor) {
                    dateTimeData3.utc = 90;
                    return dateTimeData3;
                }
                dateTimeData3.day = i5 - iMaxDayInMonthFor;
                i = 1;
            }
            int i6 = dateTimeData3.month + i;
            dateTimeData3.month = modulo(i6, 1, 13);
            dateTimeData3.year += fQuotient(i6, 1, 13);
        }
    }

    private short compareResults(short s, short s2, boolean z) {
        if (s2 == 2) {
            return (short) 2;
        }
        if (s != s2 && z) {
            return (short) 2;
        }
        if (s != s2 && !z) {
            if (s != 0 && s2 != 0) {
                return (short) 2;
            }
            if (s == 0) {
                return s2;
            }
        }
        return s;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public short compareDates(AbstractDateTimeDV.DateTimeData dateTimeData, AbstractDateTimeDV.DateTimeData dateTimeData2, boolean z) {
        short sCompareResults;
        short sCompareResults2;
        if (compareOrder(dateTimeData, dateTimeData2) == 0) {
            return (short) 0;
        }
        AbstractDateTimeDV.DateTimeData[] dateTimeDataArr = {new AbstractDateTimeDV.DateTimeData(null, this), new AbstractDateTimeDV.DateTimeData(null, this)};
        AbstractDateTimeDV.DateTimeData[] dateTimeDataArr2 = DATETIMES;
        short sCompareOrder = compareOrder(addDuration(dateTimeData, dateTimeDataArr2[0], dateTimeDataArr[0]), addDuration(dateTimeData2, dateTimeDataArr2[0], dateTimeDataArr[1]));
        if (sCompareOrder == 2 || (sCompareResults = compareResults(sCompareOrder, compareOrder(addDuration(dateTimeData, dateTimeDataArr2[1], dateTimeDataArr[0]), addDuration(dateTimeData2, dateTimeDataArr2[1], dateTimeDataArr[1])), z)) == 2 || (sCompareResults2 = compareResults(sCompareResults, compareOrder(addDuration(dateTimeData, dateTimeDataArr2[2], dateTimeDataArr[0]), addDuration(dateTimeData2, dateTimeDataArr2[2], dateTimeDataArr[1])), z)) == 2) {
            return (short) 2;
        }
        return compareResults(sCompareResults2, compareOrder(addDuration(dateTimeData, dateTimeDataArr2[3], dateTimeDataArr[0]), addDuration(dateTimeData2, dateTimeDataArr2[3], dateTimeDataArr[1])), z);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public String dateToString(AbstractDateTimeDV.DateTimeData dateTimeData) {
        StringBuffer stringBuffer = new StringBuffer(30);
        if (dateTimeData.year < 0 || dateTimeData.month < 0 || dateTimeData.day < 0 || dateTimeData.hour < 0 || dateTimeData.minute < 0 || dateTimeData.second < XPath.MATCH_SCORE_QNAME) {
            stringBuffer.append(LocaleUtility.IETF_SEPARATOR);
        }
        stringBuffer.append('P');
        int i = dateTimeData.year;
        stringBuffer.append((i < 0 ? -1 : 1) * i);
        stringBuffer.append('Y');
        int i2 = dateTimeData.month;
        stringBuffer.append((i2 < 0 ? -1 : 1) * i2);
        stringBuffer.append('M');
        int i3 = dateTimeData.day;
        stringBuffer.append((i3 < 0 ? -1 : 1) * i3);
        stringBuffer.append("DT");
        int i4 = dateTimeData.hour;
        stringBuffer.append((i4 < 0 ? -1 : 1) * i4);
        stringBuffer.append('H');
        int i5 = dateTimeData.minute;
        stringBuffer.append((i5 < 0 ? -1 : 1) * i5);
        stringBuffer.append('M');
        double d = dateTimeData.second;
        append2(stringBuffer, ((double) (d < XPath.MATCH_SCORE_QNAME ? -1 : 1)) * d);
        stringBuffer.append('S');
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return parse(str, 0);
        } catch (Exception unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_DURATION});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public Duration getDuration(AbstractDateTimeDV.DateTimeData dateTimeData) {
        int i = dateTimeData.year;
        int i2 = (i < 0 || dateTimeData.month < 0 || dateTimeData.day < 0 || dateTimeData.hour < 0 || dateTimeData.minute < 0 || dateTimeData.second < XPath.MATCH_SCORE_QNAME) ? -1 : 1;
        DatatypeFactory datatypeFactory = AbstractDateTimeDV.datatypeFactory;
        boolean z = i2 == 1;
        BigInteger bigIntegerValueOf = i != Integer.MIN_VALUE ? BigInteger.valueOf(i * i2) : null;
        int i3 = dateTimeData.month;
        BigInteger bigIntegerValueOf2 = i3 != Integer.MIN_VALUE ? BigInteger.valueOf(i3 * i2) : null;
        int i4 = dateTimeData.day;
        BigInteger bigIntegerValueOf3 = i4 != Integer.MIN_VALUE ? BigInteger.valueOf(i4 * i2) : null;
        int i5 = dateTimeData.hour;
        BigInteger bigIntegerValueOf4 = i5 != Integer.MIN_VALUE ? BigInteger.valueOf(i5 * i2) : null;
        int i6 = dateTimeData.minute;
        return datatypeFactory.newDuration(z, bigIntegerValueOf, bigIntegerValueOf2, bigIntegerValueOf3, bigIntegerValueOf4, i6 != Integer.MIN_VALUE ? BigInteger.valueOf(i6 * i2) : null, dateTimeData.second != -2.147483648E9d ? new BigDecimal(String.valueOf(((double) i2) * dateTimeData.second)) : null);
    }

    public AbstractDateTimeDV.DateTimeData parse(String str, int i) throws SchemaDateTimeException {
        int i2;
        int length = str.length();
        AbstractDateTimeDV.DateTimeData dateTimeData = new AbstractDateTimeDV.DateTimeData(str, this);
        boolean z = false;
        char cCharAt = str.charAt(0);
        if (cCharAt != 'P' && cCharAt != '-') {
            throw new SchemaDateTimeException();
        }
        dateTimeData.utc = cCharAt == '-' ? 45 : 0;
        boolean z2 = true;
        if (cCharAt != '-') {
            i2 = 1;
        } else {
            if (str.charAt(1) != 'P') {
                throw new SchemaDateTimeException();
            }
            i2 = 2;
        }
        int i3 = dateTimeData.utc == 45 ? -1 : 1;
        int iIndexOf = indexOf(str, i2, length, 'T');
        if (iIndexOf == -1) {
            iIndexOf = length;
        } else if (i == 1) {
            throw new SchemaDateTimeException();
        }
        int iIndexOf2 = indexOf(str, i2, iIndexOf, 'Y');
        if (iIndexOf2 != -1) {
            if (i == 2) {
                throw new SchemaDateTimeException();
            }
            dateTimeData.year = parseInt(str, i2, iIndexOf2) * i3;
            i2 = iIndexOf2 + 1;
            z = true;
        }
        int iIndexOf3 = indexOf(str, i2, iIndexOf, 'M');
        if (iIndexOf3 != -1) {
            if (i == 2) {
                throw new SchemaDateTimeException();
            }
            dateTimeData.month = parseInt(str, i2, iIndexOf3) * i3;
            i2 = iIndexOf3 + 1;
            z = true;
        }
        int iIndexOf4 = indexOf(str, i2, iIndexOf, 'D');
        if (iIndexOf4 != -1) {
            if (i == 1) {
                throw new SchemaDateTimeException();
            }
            dateTimeData.day = parseInt(str, i2, iIndexOf4) * i3;
            i2 = iIndexOf4 + 1;
            z = true;
        }
        if (length == iIndexOf && i2 != length) {
            throw new SchemaDateTimeException();
        }
        if (length != iIndexOf) {
            int i4 = i2 + 1;
            int iIndexOf5 = indexOf(str, i4, length, 'H');
            if (iIndexOf5 != -1) {
                dateTimeData.hour = parseInt(str, i4, iIndexOf5) * i3;
                i4 = iIndexOf5 + 1;
                z = true;
            }
            int iIndexOf6 = indexOf(str, i4, length, 'M');
            if (iIndexOf6 != -1) {
                dateTimeData.minute = parseInt(str, i4, iIndexOf6) * i3;
                i4 = iIndexOf6 + 1;
                z = true;
            }
            int iIndexOf7 = indexOf(str, i4, length, 'S');
            if (iIndexOf7 != -1) {
                dateTimeData.second = ((double) i3) * parseSecond(str, i4, iIndexOf7);
                i4 = iIndexOf7 + 1;
            } else {
                z2 = z;
            }
            if (i4 != length || str.charAt(i4 - 1) == 'T') {
                throw new SchemaDateTimeException();
            }
            z = z2;
        }
        if (z) {
            return dateTimeData;
        }
        throw new SchemaDateTimeException();
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV
    public double parseSecond(String str, int i, int i2) throws NumberFormatException {
        int i3 = -1;
        for (int i4 = i; i4 < i2; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '.') {
                i3 = i4;
            } else if (cCharAt > '9' || cCharAt < '0') {
                sxf.a("'", str, "' has wrong format");
                return XPath.MATCH_SCORE_QNAME;
            }
        }
        if (i3 + 1 == i2) {
            sxf.a("'", str, "' has wrong format");
            return XPath.MATCH_SCORE_QNAME;
        }
        double d = Double.parseDouble(str.substring(i, i2));
        if (d != Double.POSITIVE_INFINITY) {
            return d;
        }
        sxf.a("'", str, "' has wrong format");
        return XPath.MATCH_SCORE_QNAME;
    }
}
