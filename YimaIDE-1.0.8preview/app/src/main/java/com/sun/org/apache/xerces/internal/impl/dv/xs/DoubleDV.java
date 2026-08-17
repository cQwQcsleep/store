package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSDouble;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xpath.internal.XPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DoubleDV extends TypeValidator {

    public static final class XDouble implements XSDouble {
        private String canonical;
        private final double value;

        public XDouble(String str) throws NumberFormatException {
            if (DoubleDV.isPossibleFP(str)) {
                this.value = Double.parseDouble(str);
                return;
            }
            if (str.equals("INF")) {
                this.value = Double.POSITIVE_INFINITY;
            } else if (str.equals("-INF")) {
                this.value = Double.NEGATIVE_INFINITY;
            } else {
                if (!str.equals("NaN")) {
                    throw new NumberFormatException(str);
                }
                this.value = Double.NaN;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int compareTo(XDouble xDouble) {
            double d = xDouble.value;
            double d2 = this.value;
            if (d2 < d) {
                return -1;
            }
            if (d2 > d) {
                return 1;
            }
            if (d2 == d) {
                return 0;
            }
            return (d2 == d2 || d == d) ? 2 : 0;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof XDouble)) {
                return false;
            }
            double d = this.value;
            double d2 = ((XDouble) obj).value;
            if (d == d2) {
                return true;
            }
            return (d == d || d2 == d2) ? false : true;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSDouble
        public double getValue() {
            return this.value;
        }

        public int hashCode() {
            double d = this.value;
            if (d == XPath.MATCH_SCORE_QNAME) {
                return 0;
            }
            long jDoubleToLongBits = Double.doubleToLongBits(d);
            return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }

        public boolean isIdentical(XDouble xDouble) {
            if (xDouble == this) {
                return true;
            }
            double d = this.value;
            double d2 = xDouble.value;
            if (d == d2) {
                return d != XPath.MATCH_SCORE_QNAME || Double.doubleToLongBits(d) == Double.doubleToLongBits(xDouble.value);
            }
            return (d == d || d2 == d2) ? false : true;
        }

        public synchronized String toString() {
            char c;
            int i;
            char c2;
            try {
                if (this.canonical == null) {
                    double d = this.value;
                    if (d == Double.POSITIVE_INFINITY) {
                        this.canonical = "INF";
                    } else if (d == Double.NEGATIVE_INFINITY) {
                        this.canonical = "-INF";
                    } else if (d != d) {
                        this.canonical = "NaN";
                    } else if (d == XPath.MATCH_SCORE_QNAME) {
                        this.canonical = "0.0E1";
                    } else {
                        String string = Double.toString(d);
                        this.canonical = string;
                        if (string.indexOf(69) == -1) {
                            int length = this.canonical.length();
                            char[] cArr = new char[length + 3];
                            this.canonical.getChars(0, length, cArr, 0);
                            int i2 = cArr[0] == '-' ? 2 : 1;
                            double d2 = this.value;
                            if (d2 >= 1.0d || d2 <= -1.0d) {
                                int iIndexOf = this.canonical.indexOf(46);
                                for (int i3 = iIndexOf; i3 > i2; i3--) {
                                    cArr[i3] = cArr[i3 - 1];
                                }
                                cArr[i2] = '.';
                                while (true) {
                                    c = cArr[length - 1];
                                    if (c != '0') {
                                        break;
                                    }
                                    length--;
                                }
                                if (c == '.') {
                                    length++;
                                }
                                int i4 = length + 1;
                                cArr[length] = 'E';
                                i = length + 2;
                                cArr[i4] = (char) ((iIndexOf - i2) + 48);
                            } else {
                                int i5 = i2 + 1;
                                int i6 = i5;
                                while (true) {
                                    c2 = cArr[i6];
                                    if (c2 != '0') {
                                        break;
                                    }
                                    i6++;
                                }
                                cArr[i2 - 1] = c2;
                                cArr[i2] = '.';
                                int i7 = i6 + 1;
                                int i8 = i5;
                                while (i7 < length) {
                                    cArr[i8] = cArr[i7];
                                    i7++;
                                    i8++;
                                }
                                int i9 = i6 - i2;
                                int i10 = length - i9;
                                if (i10 == i5) {
                                    cArr[i10] = '0';
                                    i10++;
                                }
                                cArr[i10] = 'E';
                                int i11 = i10 + 2;
                                cArr[i10 + 1] = LocaleUtility.IETF_SEPARATOR;
                                i = i10 + 3;
                                cArr[i11] = (char) (i9 + 48);
                            }
                            this.canonical = new String(cArr, 0, i);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.canonical;
        }
    }

    public static boolean isPossibleFP(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '.' && cCharAt != '-' && cCharAt != '+' && cCharAt != 'E' && cCharAt != 'e') {
                return false;
            }
        }
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int compare(Object obj, Object obj2) {
        return ((XDouble) obj).compareTo((XDouble) obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return new XDouble(str);
        } catch (NumberFormatException unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "double"});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2552;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public boolean isIdentical(Object obj, Object obj2) {
        if (obj2 instanceof XDouble) {
            return ((XDouble) obj).isIdentical((XDouble) obj2);
        }
        return false;
    }
}
