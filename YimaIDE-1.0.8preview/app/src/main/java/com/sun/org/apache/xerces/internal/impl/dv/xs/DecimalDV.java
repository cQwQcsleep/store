package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DecimalDV extends TypeValidator {
    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public final int compare(Object obj, Object obj2) {
        return ((XDecimal) obj).compareTo((XDecimal) obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return new XDecimal(str);
        } catch (NumberFormatException unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, SchemaSymbols.ATTVAL_DECIMAL});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public final short getAllowedFacets() {
        return (short) 4088;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public final int getFractionDigits(Object obj) {
        return ((XDecimal) obj).fracDigits;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public final int getTotalDigits(Object obj) {
        return ((XDecimal) obj).totalDigits;
    }

    public static final class XDecimal implements XSDecimal {
        private String canonical;
        int sign = 1;
        int totalDigits = 0;
        int intDigits = 0;
        int fracDigits = 0;
        String ivalue = "";
        String fvalue = "";
        boolean integer = false;

        public XDecimal(String str, boolean z) throws NumberFormatException {
            if (z) {
                initI(str);
            } else {
                initD(str);
            }
        }

        private int intComp(XDecimal xDecimal) {
            int i = this.intDigits;
            int i2 = xDecimal.intDigits;
            if (i != i2) {
                return i > i2 ? 1 : -1;
            }
            int iCompareTo = this.ivalue.compareTo(xDecimal.ivalue);
            if (iCompareTo != 0) {
                return iCompareTo > 0 ? 1 : -1;
            }
            int iCompareTo2 = this.fvalue.compareTo(xDecimal.fvalue);
            if (iCompareTo2 == 0) {
                return 0;
            }
            return iCompareTo2 > 0 ? 1 : -1;
        }

        private void makeCanonical() {
            int i = this.sign;
            boolean z = this.integer;
            if (i == 0) {
                if (z) {
                    this.canonical = "0";
                    return;
                } else {
                    this.canonical = "0.0";
                    return;
                }
            }
            if (z && i > 0) {
                this.canonical = this.ivalue;
                return;
            }
            StringBuilder sb = new StringBuilder(this.totalDigits + 3);
            if (this.sign == -1) {
                sb.append(LocaleUtility.IETF_SEPARATOR);
            }
            if (this.intDigits != 0) {
                sb.append(this.ivalue);
            } else {
                sb.append('0');
            }
            if (!this.integer) {
                sb.append('.');
                if (this.fracDigits != 0) {
                    sb.append(this.fvalue);
                } else {
                    sb.append('0');
                }
            }
            this.canonical = sb.toString();
        }

        public int compareTo(XDecimal xDecimal) {
            int i = this.sign;
            int i2 = xDecimal.sign;
            if (i != i2) {
                return i > i2 ? 1 : -1;
            }
            if (i == 0) {
                return 0;
            }
            return i * intComp(xDecimal);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof XDecimal)) {
                return false;
            }
            XDecimal xDecimal = (XDecimal) obj;
            int i = this.sign;
            if (i != xDecimal.sign) {
                return false;
            }
            if (i == 0) {
                return true;
            }
            return this.intDigits == xDecimal.intDigits && this.fracDigits == xDecimal.fracDigits && this.ivalue.equals(xDecimal.ivalue) && this.fvalue.equals(xDecimal.fvalue);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public BigDecimal getBigDecimal() {
            return this.sign == 0 ? new BigDecimal(BigInteger.ZERO) : new BigDecimal(toString());
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public BigInteger getBigInteger() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            }
            int i = this.sign;
            if (i == 0) {
                return BigInteger.ZERO;
            }
            if (i == 1) {
                return new BigInteger(this.ivalue);
            }
            return new BigInteger("-" + this.ivalue);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public byte getByte() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            }
            int i = this.sign;
            if (i == 0) {
                return (byte) 0;
            }
            String str = this.ivalue;
            if (i == 1) {
                return Byte.parseByte(str);
            }
            return Byte.parseByte("-" + str);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public int getInt() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            }
            int i = this.sign;
            if (i == 0) {
                return 0;
            }
            String str = this.ivalue;
            if (i == 1) {
                return Integer.parseInt(str);
            }
            return Integer.parseInt("-" + str);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public long getLong() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            }
            int i = this.sign;
            if (i == 0) {
                return 0L;
            }
            String str = this.ivalue;
            if (i == 1) {
                return Long.parseLong(str);
            }
            return Long.parseLong("-" + str);
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDecimal
        public short getShort() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            }
            int i = this.sign;
            if (i == 0) {
                return (short) 0;
            }
            String str = this.ivalue;
            if (i == 1) {
                return Short.parseShort(str);
            }
            return Short.parseShort("-" + str);
        }

        public int hashCode() {
            int i = this.sign;
            int i2 = 119 + i;
            return i == 0 ? i2 : (((((((i2 * 17) + this.intDigits) * 17) + this.fracDigits) * 17) + Objects.hashCode(this.ivalue)) * 17) + Objects.hashCode(this.fvalue);
        }

        public void initD(String str) throws NumberFormatException {
            int i;
            int length = str.length();
            if (length == 0) {
                throw new NumberFormatException();
            }
            int i2 = 1;
            if (str.charAt(0) != '+') {
                if (str.charAt(0) == '-') {
                    this.sign = -1;
                } else {
                    i2 = 0;
                }
            }
            int i3 = i2;
            while (i3 < length && str.charAt(i3) == '0') {
                i3++;
            }
            int i4 = i3;
            while (i4 < length && TypeValidator.isDigit(str.charAt(i4))) {
                i4++;
            }
            if (i4 >= length) {
                length = 0;
                i = 0;
            } else {
                if (str.charAt(i4) != '.') {
                    throw new NumberFormatException();
                }
                i = i4 + 1;
            }
            if (i2 == i4 && i == length) {
                throw new NumberFormatException();
            }
            while (length > i && str.charAt(length - 1) == '0') {
                length--;
            }
            for (int i5 = i; i5 < length; i5++) {
                if (!TypeValidator.isDigit(str.charAt(i5))) {
                    throw new NumberFormatException();
                }
            }
            int i6 = i4 - i3;
            this.intDigits = i6;
            int i7 = length - i;
            this.fracDigits = i7;
            this.totalDigits = i6 + i7;
            if (i6 > 0) {
                this.ivalue = str.substring(i3, i4);
                if (this.fracDigits > 0) {
                    this.fvalue = str.substring(i, length);
                    return;
                }
                return;
            }
            if (i7 > 0) {
                this.fvalue = str.substring(i, length);
            } else {
                this.sign = 0;
            }
        }

        public void initI(String str) throws NumberFormatException {
            int i;
            int length = str.length();
            if (length == 0) {
                throw new NumberFormatException();
            }
            if (str.charAt(0) == '+') {
                i = 1;
            } else if (str.charAt(0) == '-') {
                this.sign = -1;
                i = 1;
            } else {
                i = 0;
            }
            int i2 = i;
            while (i2 < length && str.charAt(i2) == '0') {
                i2++;
            }
            int i3 = i2;
            while (i3 < length && TypeValidator.isDigit(str.charAt(i3))) {
                i3++;
            }
            if (i3 < length) {
                throw new NumberFormatException();
            }
            if (i == i3) {
                throw new NumberFormatException();
            }
            int i4 = i3 - i2;
            this.intDigits = i4;
            this.fracDigits = 0;
            this.totalDigits = i4;
            if (i4 > 0) {
                this.ivalue = str.substring(i2, i3);
            } else {
                this.sign = 0;
            }
            this.integer = true;
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

        public XDecimal(String str) throws NumberFormatException {
            initD(str);
        }
    }
}
