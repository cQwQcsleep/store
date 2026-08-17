package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class PrecisionDecimalDV extends TypeValidator {

    public static final class XPrecisionDecimal {
        private String canonical;
        String ivalue;
        int sign;
        int totalDigits = 0;
        int intDigits = 0;
        int fracDigits = 0;
        String fvalue = "";
        int pvalue = 0;

        public XPrecisionDecimal(String str) throws NumberFormatException {
            this.sign = 1;
            this.ivalue = "";
            if (str.equals("NaN")) {
                this.ivalue = str;
                this.sign = 0;
            }
            if (str.equals("+INF") || str.equals("INF") || str.equals("-INF")) {
                this.ivalue = str.charAt(0) == '+' ? str.substring(1) : str;
            } else {
                initD(str);
            }
        }

        private static String canonicalToStringForHashCode(String str, String str2, int i, int i2) {
            if ("NaN".equals(str)) {
                return "NaN";
            }
            if ("INF".equals(str)) {
                return i < 0 ? "-INF" : "INF";
            }
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            int length2 = str2.length();
            while (length2 > 0 && str2.charAt(length2 - 1) == '0') {
                length2--;
            }
            int i3 = 0;
            int i4 = 0;
            while (i4 < length && str.charAt(i4) == '0') {
                i4++;
            }
            if (i4 >= str.length()) {
                if (length2 > 0) {
                    while (i3 < length2 && str2.charAt(i3) == '0') {
                        i3++;
                    }
                    if (i3 < length2) {
                        sb.append(i == -1 ? "-" : "");
                        sb.append(str2.charAt(i3));
                        i3++;
                        i2 -= i3;
                    }
                }
                return "0";
            }
            sb.append(i == -1 ? "-" : "");
            sb.append(str.charAt(i4));
            i4++;
            if (i4 < length || i3 < length2) {
                sb.append('.');
            }
            while (i4 < length) {
                sb.append(str.charAt(i4));
                i2++;
                i4++;
            }
            while (i3 < length2) {
                sb.append(str2.charAt(i3));
                i3++;
            }
            if (i2 != 0) {
                sb.append("E");
                sb.append(i2);
            }
            return sb.toString();
        }

        private int compare(XPrecisionDecimal xPrecisionDecimal) {
            int i;
            int i2 = this.pvalue;
            if ((i2 != 0 || xPrecisionDecimal.pvalue != 0) && i2 != (i = xPrecisionDecimal.pvalue)) {
                int i3 = this.intDigits;
                int i4 = i3 + i2;
                int i5 = xPrecisionDecimal.intDigits;
                if (i4 != i5 + i) {
                    return i3 + i2 > i5 + i ? 1 : -1;
                }
                int i6 = 0;
                if (i2 > i) {
                    int i7 = i2 - i;
                    StringBuffer stringBuffer = new StringBuffer(this.ivalue);
                    StringBuffer stringBuffer2 = new StringBuffer(this.fvalue);
                    while (i6 < i7) {
                        if (i6 < this.fracDigits) {
                            stringBuffer.append(this.fvalue.charAt(i6));
                            stringBuffer2.deleteCharAt(i6);
                        } else {
                            stringBuffer.append('0');
                        }
                        i6++;
                    }
                    return compareDecimal(stringBuffer.toString(), xPrecisionDecimal.ivalue, stringBuffer2.toString(), xPrecisionDecimal.fvalue);
                }
                int i8 = i - i2;
                StringBuffer stringBuffer3 = new StringBuffer(xPrecisionDecimal.ivalue);
                StringBuffer stringBuffer4 = new StringBuffer(xPrecisionDecimal.fvalue);
                while (i6 < i8) {
                    if (i6 < xPrecisionDecimal.fracDigits) {
                        stringBuffer3.append(xPrecisionDecimal.fvalue.charAt(i6));
                        stringBuffer4.deleteCharAt(i6);
                    } else {
                        stringBuffer3.append('0');
                    }
                    i6++;
                }
                return compareDecimal(this.ivalue, stringBuffer3.toString(), this.fvalue, stringBuffer4.toString());
            }
            return intComp(xPrecisionDecimal);
        }

        private int compareDecimal(String str, String str2, String str3, String str4) {
            int iCompareTo = str.compareTo(str3);
            if (iCompareTo != 0) {
                return iCompareTo > 0 ? 1 : -1;
            }
            if (str2.equals(str4)) {
                return 0;
            }
            StringBuffer stringBuffer = new StringBuffer(str2);
            StringBuffer stringBuffer2 = new StringBuffer(str4);
            truncateTrailingZeros(stringBuffer, stringBuffer2);
            int iCompareTo2 = stringBuffer.toString().compareTo(stringBuffer2.toString());
            if (iCompareTo2 == 0) {
                return 0;
            }
            return iCompareTo2 > 0 ? 1 : -1;
        }

        private int compareFractionalPart(XPrecisionDecimal xPrecisionDecimal) {
            if (this.fvalue.equals(xPrecisionDecimal.fvalue)) {
                return 0;
            }
            StringBuffer stringBuffer = new StringBuffer(this.fvalue);
            StringBuffer stringBuffer2 = new StringBuffer(xPrecisionDecimal.fvalue);
            truncateTrailingZeros(stringBuffer, stringBuffer2);
            return stringBuffer.toString().compareTo(stringBuffer2.toString());
        }

        private int intComp(XPrecisionDecimal xPrecisionDecimal) {
            int i = this.intDigits;
            int i2 = xPrecisionDecimal.intDigits;
            if (i != i2) {
                return i > i2 ? 1 : -1;
            }
            return compareDecimal(this.ivalue, xPrecisionDecimal.ivalue, this.fvalue, xPrecisionDecimal.fvalue);
        }

        private void makeCanonical() {
            this.canonical = "TBD by Working Group";
        }

        private void truncateTrailingZeros(StringBuffer stringBuffer, StringBuffer stringBuffer2) {
            int length = stringBuffer.length();
            while (true) {
                length--;
                if (length < 0 || stringBuffer.charAt(length) != '0') {
                    break;
                } else {
                    stringBuffer.deleteCharAt(length);
                }
            }
            for (int length2 = stringBuffer2.length() - 1; length2 >= 0 && stringBuffer2.charAt(length2) == '0'; length2--) {
                stringBuffer2.deleteCharAt(length2);
            }
        }

        public int compareTo(XPrecisionDecimal xPrecisionDecimal) {
            if (this.sign == 0) {
                return 2;
            }
            if (this.ivalue.equals("INF") || xPrecisionDecimal.ivalue.equals("INF")) {
                if (this.ivalue.equals(xPrecisionDecimal.ivalue)) {
                    return 0;
                }
                return this.ivalue.equals("INF") ? 1 : -1;
            }
            if (this.ivalue.equals("-INF") || xPrecisionDecimal.ivalue.equals("-INF")) {
                if (this.ivalue.equals(xPrecisionDecimal.ivalue)) {
                    return 0;
                }
                return this.ivalue.equals("-INF") ? -1 : 1;
            }
            int i = this.sign;
            int i2 = xPrecisionDecimal.sign;
            if (i != i2) {
                return i > i2 ? 1 : -1;
            }
            return i * compare(xPrecisionDecimal);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof XPrecisionDecimal) && compareTo((XPrecisionDecimal) obj) == 0;
        }

        public int hashCode() {
            return canonicalToStringForHashCode(this.ivalue, this.fvalue, this.sign, this.pvalue).hashCode();
        }

        public void initD(String str) throws NumberFormatException {
            int i;
            int i2;
            int length = str.length();
            if (length == 0) {
                throw new NumberFormatException();
            }
            int i3 = 0;
            if (str.charAt(0) == '+') {
                i = 1;
            } else if (str.charAt(0) == '-') {
                this.sign = -1;
                i = 1;
            } else {
                i = 0;
            }
            int i4 = i;
            while (i4 < length && str.charAt(i4) == '0') {
                i4++;
            }
            int i5 = i4;
            while (i5 < length && TypeValidator.isDigit(str.charAt(i5))) {
                i5++;
            }
            if (i5 >= length) {
                i2 = 0;
            } else {
                if (str.charAt(i5) != '.' && str.charAt(i5) != 'E' && str.charAt(i5) != 'e') {
                    throw new NumberFormatException();
                }
                if (str.charAt(i5) == '.') {
                    i3 = i5 + 1;
                    i2 = i3;
                    while (i2 < length && TypeValidator.isDigit(str.charAt(i2))) {
                        i2++;
                    }
                } else {
                    this.pvalue = Integer.parseInt(str.substring(i5 + 1, length));
                    i2 = 0;
                }
            }
            if (i == i5 && i3 == i2) {
                throw new NumberFormatException();
            }
            for (int i6 = i3; i6 < i2; i6++) {
                if (!TypeValidator.isDigit(str.charAt(i6))) {
                    throw new NumberFormatException();
                }
            }
            int i7 = i5 - i4;
            this.intDigits = i7;
            this.fracDigits = i2 - i3;
            if (i7 > 0) {
                this.ivalue = str.substring(i4, i5);
            }
            if (this.fracDigits > 0) {
                this.fvalue = str.substring(i3, i2);
                if (i2 < length) {
                    this.pvalue = Integer.parseInt(str.substring(i2 + 1, length));
                }
            }
            this.totalDigits = this.intDigits + this.fracDigits;
        }

        public boolean isIdentical(XPrecisionDecimal xPrecisionDecimal) {
            if (this.ivalue.equals(xPrecisionDecimal.ivalue) && (this.ivalue.equals("INF") || this.ivalue.equals("-INF") || this.ivalue.equals("NaN"))) {
                return true;
            }
            return this.sign == xPrecisionDecimal.sign && this.intDigits == xPrecisionDecimal.intDigits && this.fracDigits == xPrecisionDecimal.fracDigits && this.pvalue == xPrecisionDecimal.pvalue && this.ivalue.equals(xPrecisionDecimal.ivalue) && this.fvalue.equals(xPrecisionDecimal.fvalue);
        }

        public synchronized String toString() {
            try {
                if (this.canonical == null) {
                    makeCanonical();
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.canonical;
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int compare(Object obj, Object obj2) {
        return ((XPrecisionDecimal) obj).compareTo((XPrecisionDecimal) obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return new XPrecisionDecimal(str);
        } catch (NumberFormatException unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "precisionDecimal"});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 4088;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getFractionDigits(Object obj) {
        return ((XPrecisionDecimal) obj).fracDigits;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int getTotalDigits(Object obj) {
        return ((XPrecisionDecimal) obj).totalDigits;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public boolean isIdentical(Object obj, Object obj2) {
        if ((obj2 instanceof XPrecisionDecimal) && (obj instanceof XPrecisionDecimal)) {
            return ((XPrecisionDecimal) obj).isIdentical((XPrecisionDecimal) obj2);
        }
        return false;
    }
}
