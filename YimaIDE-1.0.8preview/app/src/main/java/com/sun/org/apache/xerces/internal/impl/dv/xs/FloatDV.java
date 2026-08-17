package com.sun.org.apache.xerces.internal.impl.dv.xs;

import com.sun.org.apache.xerces.internal.impl.dv.InvalidDatatypeValueException;
import com.sun.org.apache.xerces.internal.impl.dv.ValidationContext;
import com.sun.org.apache.xerces.internal.xs.datatypes.XSFloat;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FloatDV extends TypeValidator {

    public static final class XFloat implements XSFloat {
        private String canonical;
        private final float value;

        public XFloat(String str) throws NumberFormatException {
            if (DoubleDV.isPossibleFP(str)) {
                this.value = Float.parseFloat(str);
                return;
            }
            if (str.equals("INF")) {
                this.value = Float.POSITIVE_INFINITY;
            } else if (str.equals("-INF")) {
                this.value = Float.NEGATIVE_INFINITY;
            } else {
                if (!str.equals("NaN")) {
                    throw new NumberFormatException(str);
                }
                this.value = Float.NaN;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int compareTo(XFloat xFloat) {
            float f = xFloat.value;
            float f2 = this.value;
            if (f2 < f) {
                return -1;
            }
            if (f2 > f) {
                return 1;
            }
            if (f2 == f) {
                return 0;
            }
            return (f2 == f2 || f == f) ? 2 : 0;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof XFloat)) {
                return false;
            }
            float f = this.value;
            float f2 = ((XFloat) obj).value;
            if (f == f2) {
                return true;
            }
            return (f == f || f2 == f2) ? false : true;
        }

        @Override // com.sun.org.apache.xerces.internal.xs.datatypes.XSFloat
        public float getValue() {
            return this.value;
        }

        public int hashCode() {
            float f = this.value;
            if (f == 0.0f) {
                return 0;
            }
            return Float.floatToIntBits(f);
        }

        public boolean isIdentical(XFloat xFloat) {
            if (xFloat == this) {
                return true;
            }
            float f = this.value;
            float f2 = xFloat.value;
            if (f == f2) {
                return f != 0.0f || Float.floatToIntBits(f) == Float.floatToIntBits(xFloat.value);
            }
            return (f == f || f2 == f2) ? false : true;
        }

        public synchronized String toString() {
            char c;
            int i;
            char c2;
            try {
                if (this.canonical == null) {
                    float f = this.value;
                    if (f == Float.POSITIVE_INFINITY) {
                        this.canonical = "INF";
                    } else if (f == Float.NEGATIVE_INFINITY) {
                        this.canonical = "-INF";
                    } else if (f != f) {
                        this.canonical = "NaN";
                    } else if (f == 0.0f) {
                        this.canonical = "0.0E1";
                    } else {
                        String string = Float.toString(f);
                        this.canonical = string;
                        if (string.indexOf(69) == -1) {
                            int length = this.canonical.length();
                            char[] cArr = new char[length + 3];
                            this.canonical.getChars(0, length, cArr, 0);
                            int i2 = cArr[0] == '-' ? 2 : 1;
                            float f2 = this.value;
                            if (f2 >= 1.0f || f2 <= -1.0f) {
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

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public int compare(Object obj, Object obj2) {
        return ((XFloat) obj).compareTo((XFloat) obj2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public Object getActualValue(String str, ValidationContext validationContext) throws InvalidDatatypeValueException {
        try {
            return new XFloat(str);
        } catch (NumberFormatException unused) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{str, "float"});
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public short getAllowedFacets() {
        return (short) 2552;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator
    public boolean isIdentical(Object obj, Object obj2) {
        if (obj2 instanceof XFloat) {
            return ((XFloat) obj).isIdentical((XFloat) obj2);
        }
        return false;
    }
}
