package org.codehaus.stax2.ri.typed;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import org.codehaus.stax2.typed.TypedArrayDecoder;
import org.codehaus.stax2.typed.TypedValueDecoder;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ValueDecoderFactory {
    protected BooleanDecoder mBooleanDecoder = null;
    protected IntDecoder mIntDecoder = null;
    protected LongDecoder mLongDecoder = null;
    protected FloatDecoder mFloatDecoder = null;
    protected DoubleDecoder mDoubleDecoder = null;

    public static abstract class BaseArrayDecoder extends TypedArrayDecoder {
        protected static final int INITIAL_RESULT_BUFFER_SIZE = 40;
        protected static final int SMALL_RESULT_BUFFER_SIZE = 4000;
        protected int mCount = 0;
        protected int mEnd;
        protected int mStart;

        public BaseArrayDecoder(int i, int i2) {
            this.mStart = i;
            if (i2 >= 1) {
                this.mEnd = i2;
            } else {
                w01.a("Number of elements to read can not be less than 1");
                throw null;
            }
        }

        public int calcNewSize(int i) {
            return i < SMALL_RESULT_BUFFER_SIZE ? i << 2 : i + i;
        }

        public abstract void expand();

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public final int getCount() {
            return this.mCount;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public final boolean hasRoom() {
            return this.mCount < this.mEnd;
        }
    }

    public BooleanDecoder getBooleanDecoder() {
        if (this.mBooleanDecoder == null) {
            this.mBooleanDecoder = new BooleanDecoder();
        }
        return this.mBooleanDecoder;
    }

    public DecimalDecoder getDecimalDecoder() {
        return new DecimalDecoder();
    }

    public DoubleArrayDecoder getDoubleArrayDecoder(double[] dArr, int i, int i2) {
        return new DoubleArrayDecoder(dArr, i, i2, getDoubleDecoder());
    }

    public DoubleDecoder getDoubleDecoder() {
        if (this.mDoubleDecoder == null) {
            this.mDoubleDecoder = new DoubleDecoder();
        }
        return this.mDoubleDecoder;
    }

    public FloatArrayDecoder getFloatArrayDecoder(float[] fArr, int i, int i2) {
        return new FloatArrayDecoder(fArr, i, i2, getFloatDecoder());
    }

    public FloatDecoder getFloatDecoder() {
        if (this.mFloatDecoder == null) {
            this.mFloatDecoder = new FloatDecoder();
        }
        return this.mFloatDecoder;
    }

    public IntArrayDecoder getIntArrayDecoder(int[] iArr, int i, int i2) {
        return new IntArrayDecoder(iArr, i, i2, getIntDecoder());
    }

    public IntDecoder getIntDecoder() {
        if (this.mIntDecoder == null) {
            this.mIntDecoder = new IntDecoder();
        }
        return this.mIntDecoder;
    }

    public IntegerDecoder getIntegerDecoder() {
        return new IntegerDecoder();
    }

    public LongArrayDecoder getLongArrayDecoder(long[] jArr, int i, int i2) {
        return new LongArrayDecoder(jArr, i, i2, getLongDecoder());
    }

    public LongDecoder getLongDecoder() {
        if (this.mLongDecoder == null) {
            this.mLongDecoder = new LongDecoder();
        }
        return this.mLongDecoder;
    }

    public QNameDecoder getQNameDecoder(NamespaceContext namespaceContext) {
        return new QNameDecoder(namespaceContext);
    }

    public static abstract class DecoderBase extends TypedValueDecoder {
        static final long L_BILLION = 1000000000;
        static final long L_MAX_INT = 2147483647L;
        static final long L_MIN_INT = -2147483648L;
        protected int mNextPtr;
        static final BigInteger BD_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
        static final BigInteger BD_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

        public static final int parseInt(String str, int i, int i2) {
            int iCharAt = str.charAt(i) - '0';
            int i3 = i + 1;
            if (i3 >= i2) {
                return iCharAt;
            }
            int iCharAt2 = (iCharAt * 10) + (str.charAt(i3) - '0');
            int i4 = i + 2;
            if (i4 >= i2) {
                return iCharAt2;
            }
            int iCharAt3 = (iCharAt2 * 10) + (str.charAt(i4) - '0');
            int i5 = i + 3;
            if (i5 >= i2) {
                return iCharAt3;
            }
            int iCharAt4 = (iCharAt3 * 10) + (str.charAt(i5) - '0');
            int i6 = i + 4;
            if (i6 >= i2) {
                return iCharAt4;
            }
            int iCharAt5 = (iCharAt4 * 10) + (str.charAt(i6) - '0');
            int i7 = i + 5;
            if (i7 >= i2) {
                return iCharAt5;
            }
            int iCharAt6 = (iCharAt5 * 10) + (str.charAt(i7) - '0');
            int i8 = i + 6;
            if (i8 >= i2) {
                return iCharAt6;
            }
            int iCharAt7 = (iCharAt6 * 10) + (str.charAt(i8) - '0');
            int i9 = i + 7;
            if (i9 >= i2) {
                return iCharAt7;
            }
            int iCharAt8 = (iCharAt7 * 10) + (str.charAt(i9) - '0');
            int i10 = i + 8;
            return i10 < i2 ? (iCharAt8 * 10) + (str.charAt(i10) - '0') : iCharAt8;
        }

        public static final long parseLong(char[] cArr, int i, int i2) {
            int i3 = i2 - 9;
            return (((long) parseInt(cArr, i, i3)) * L_BILLION) + ((long) parseInt(cArr, i3, i2));
        }

        public String _clean(String str) {
            return str.trim();
        }

        public IllegalArgumentException constructInvalidValue(char[] cArr, int i, int i2) {
            return new IllegalArgumentException("Value \"" + lexicalDesc(cArr, i, i2) + "\" not a valid lexical representation of " + getType());
        }

        public abstract String getType();

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void handleEmptyValue() {
            throw new IllegalArgumentException("Empty value (all white space) not a valid lexical representation of " + getType());
        }

        public String lexicalDesc(char[] cArr, int i, int i2) {
            return _clean(new String(cArr, i, i2 - i));
        }

        public int skipSignAndZeroes(String str, char c, boolean z, int i) {
            int i2 = 1;
            if (z) {
                if (1 >= i) {
                    throw constructInvalidValue(str);
                }
                c = str.charAt(1);
                i2 = 2;
            }
            int i3 = c - '0';
            if (i3 < 0 || i3 > 9) {
                throw constructInvalidValue(str);
            }
            while (i3 == 0 && i2 < i) {
                int iCharAt = str.charAt(i2) - '0';
                if (iCharAt < 0 || iCharAt > 9) {
                    break;
                }
                i2++;
                i3 = iCharAt;
            }
            this.mNextPtr = i2;
            return i3;
        }

        public void verifyDigits(String str, int i, int i2) {
            while (i < i2) {
                char cCharAt = str.charAt(i);
                if (cCharAt > '9' || cCharAt < '0') {
                    throw constructInvalidValue(str);
                }
                i++;
            }
        }

        public String lexicalDesc(String str) {
            return _clean(str);
        }

        public static final long parseLong(String str, int i, int i2) {
            int i3 = i2 - 9;
            return (((long) parseInt(str, i, i3)) * L_BILLION) + ((long) parseInt(str, i3, i2));
        }

        public void verifyDigits(char[] cArr, int i, int i2, int i3) {
            while (i3 < i2) {
                char c = cArr[i3];
                if (c > '9' || c < '0') {
                    throw constructInvalidValue(cArr, i, i2);
                }
                i3++;
            }
        }

        public IllegalArgumentException constructInvalidValue(String str) {
            return new IllegalArgumentException("Value \"" + str + "\" not a valid lexical representation of " + getType());
        }

        public int skipSignAndZeroes(char[] cArr, char c, boolean z, int i, int i2) {
            int i3 = i + 1;
            if (z) {
                if (i3 < i2) {
                    char c2 = cArr[i3];
                    i3 = i + 2;
                    c = c2;
                } else {
                    throw constructInvalidValue(cArr, i, i2);
                }
            }
            int i4 = c - '0';
            if (i4 >= 0 && i4 <= 9) {
                while (i4 == 0 && i3 < i2) {
                    int i5 = cArr[i3] - '0';
                    if (i5 < 0 || i5 > 9) {
                        break;
                    }
                    i3++;
                    i4 = i5;
                }
                this.mNextPtr = i3;
                return i4;
            }
            throw constructInvalidValue(cArr, i, i2);
        }

        public static final int parseInt(int i, char[] cArr, int i2, int i3) {
            int i4 = (i * 10) + (cArr[i2] - '0');
            int i5 = i2 + 1;
            if (i5 >= i3) {
                return i4;
            }
            int i6 = (i4 * 10) + (cArr[i5] - '0');
            int i7 = i2 + 2;
            if (i7 >= i3) {
                return i6;
            }
            int i8 = (i6 * 10) + (cArr[i7] - '0');
            int i9 = i2 + 3;
            if (i9 >= i3) {
                return i8;
            }
            int i10 = (i8 * 10) + (cArr[i9] - '0');
            int i11 = i2 + 4;
            if (i11 >= i3) {
                return i10;
            }
            int i12 = (i10 * 10) + (cArr[i11] - '0');
            int i13 = i2 + 5;
            if (i13 >= i3) {
                return i12;
            }
            int i14 = (i12 * 10) + (cArr[i13] - '0');
            int i15 = i2 + 6;
            if (i15 >= i3) {
                return i14;
            }
            int i16 = (i14 * 10) + (cArr[i15] - '0');
            int i17 = i2 + 7;
            return i17 < i3 ? (i16 * 10) + (cArr[i17] - '0') : i16;
        }

        public static final int parseInt(char[] cArr, int i, int i2) {
            int i3 = cArr[i] - '0';
            int i4 = i + 1;
            if (i4 >= i2) {
                return i3;
            }
            int i5 = (i3 * 10) + (cArr[i4] - '0');
            int i6 = i + 2;
            if (i6 >= i2) {
                return i5;
            }
            int i7 = (i5 * 10) + (cArr[i6] - '0');
            int i8 = i + 3;
            if (i8 >= i2) {
                return i7;
            }
            int i9 = (i7 * 10) + (cArr[i8] - '0');
            int i10 = i + 4;
            if (i10 >= i2) {
                return i9;
            }
            int i11 = (i9 * 10) + (cArr[i10] - '0');
            int i12 = i + 5;
            if (i12 >= i2) {
                return i11;
            }
            int i13 = (i11 * 10) + (cArr[i12] - '0');
            int i14 = i + 6;
            if (i14 >= i2) {
                return i13;
            }
            int i15 = (i13 * 10) + (cArr[i14] - '0');
            int i16 = i + 7;
            if (i16 >= i2) {
                return i15;
            }
            int i17 = (i15 * 10) + (cArr[i16] - '0');
            int i18 = i + 8;
            return i18 < i2 ? (i17 * 10) + (cArr[i18] - '0') : i17;
        }

        public static final int parseInt(int i, String str, int i2, int i3) {
            int iCharAt = (i * 10) + (str.charAt(i2) - '0');
            int i4 = i2 + 1;
            if (i4 >= i3) {
                return iCharAt;
            }
            int iCharAt2 = (iCharAt * 10) + (str.charAt(i4) - '0');
            int i5 = i2 + 2;
            if (i5 >= i3) {
                return iCharAt2;
            }
            int iCharAt3 = (iCharAt2 * 10) + (str.charAt(i5) - '0');
            int i6 = i2 + 3;
            if (i6 >= i3) {
                return iCharAt3;
            }
            int iCharAt4 = (iCharAt3 * 10) + (str.charAt(i6) - '0');
            int i7 = i2 + 4;
            if (i7 >= i3) {
                return iCharAt4;
            }
            int iCharAt5 = (iCharAt4 * 10) + (str.charAt(i7) - '0');
            int i8 = i2 + 5;
            if (i8 >= i3) {
                return iCharAt5;
            }
            int iCharAt6 = (iCharAt5 * 10) + (str.charAt(i8) - '0');
            int i9 = i2 + 6;
            if (i9 >= i3) {
                return iCharAt6;
            }
            int iCharAt7 = (iCharAt6 * 10) + (str.charAt(i9) - '0');
            int i10 = i2 + 7;
            return i10 < i3 ? (iCharAt7 * 10) + (str.charAt(i10) - '0') : iCharAt7;
        }
    }

    public DoubleArrayDecoder getDoubleArrayDecoder() {
        return new DoubleArrayDecoder(getDoubleDecoder());
    }

    public FloatArrayDecoder getFloatArrayDecoder() {
        return new FloatArrayDecoder(getFloatDecoder());
    }

    public IntArrayDecoder getIntArrayDecoder() {
        return new IntArrayDecoder(getIntDecoder());
    }

    public LongArrayDecoder getLongArrayDecoder() {
        return new LongArrayDecoder(getLongDecoder());
    }

    public static final class DoubleArrayDecoder extends BaseArrayDecoder {
        final DoubleDecoder mDecoder;
        double[] mResult;

        public DoubleArrayDecoder(DoubleDecoder doubleDecoder) {
            super(0, 40);
            this.mResult = new double[40];
            this.mDecoder = doubleDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(String str) throws IllegalArgumentException {
            this.mDecoder.decode(str);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i = this.mCount + 1;
            this.mCount = i;
            return i >= this.mEnd;
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.BaseArrayDecoder
        public void expand() {
            double[] dArr = this.mResult;
            int iCalcNewSize = calcNewSize(dArr.length);
            double[] dArr2 = new double[iCalcNewSize];
            this.mResult = dArr2;
            System.arraycopy(dArr, this.mStart, dArr2, 0, this.mCount);
            this.mStart = 0;
            this.mEnd = iCalcNewSize;
        }

        public double[] getValues() {
            int i = this.mCount;
            double[] dArr = new double[i];
            System.arraycopy(this.mResult, this.mStart, dArr, 0, i);
            return dArr;
        }

        public DoubleArrayDecoder(double[] dArr, int i, int i2, DoubleDecoder doubleDecoder) {
            super(i, i2);
            this.mResult = dArr;
            this.mDecoder = doubleDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(char[] cArr, int i, int i2) throws IllegalArgumentException {
            this.mDecoder.decode(cArr, i, i2);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i3 = this.mCount + 1;
            this.mCount = i3;
            return i3 >= this.mEnd;
        }
    }

    public static final class FloatArrayDecoder extends BaseArrayDecoder {
        final FloatDecoder mDecoder;
        float[] mResult;

        public FloatArrayDecoder(FloatDecoder floatDecoder) {
            super(0, 40);
            this.mResult = new float[40];
            this.mDecoder = floatDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(String str) throws IllegalArgumentException {
            this.mDecoder.decode(str);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i = this.mCount + 1;
            this.mCount = i;
            return i >= this.mEnd;
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.BaseArrayDecoder
        public void expand() {
            float[] fArr = this.mResult;
            int iCalcNewSize = calcNewSize(fArr.length);
            float[] fArr2 = new float[iCalcNewSize];
            this.mResult = fArr2;
            System.arraycopy(fArr, this.mStart, fArr2, 0, this.mCount);
            this.mStart = 0;
            this.mEnd = iCalcNewSize;
        }

        public float[] getValues() {
            int i = this.mCount;
            float[] fArr = new float[i];
            System.arraycopy(this.mResult, this.mStart, fArr, 0, i);
            return fArr;
        }

        public FloatArrayDecoder(float[] fArr, int i, int i2, FloatDecoder floatDecoder) {
            super(i, i2);
            this.mResult = fArr;
            this.mDecoder = floatDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(char[] cArr, int i, int i2) throws IllegalArgumentException {
            this.mDecoder.decode(cArr, i, i2);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i3 = this.mCount + 1;
            this.mCount = i3;
            return i3 >= this.mEnd;
        }
    }

    public static final class IntArrayDecoder extends BaseArrayDecoder {
        final IntDecoder mDecoder;
        int[] mResult;

        public IntArrayDecoder(IntDecoder intDecoder) {
            super(0, 40);
            this.mResult = new int[40];
            this.mDecoder = intDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(String str) throws IllegalArgumentException {
            this.mDecoder.decode(str);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i = this.mCount + 1;
            this.mCount = i;
            return i >= this.mEnd;
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.BaseArrayDecoder
        public void expand() {
            int[] iArr = this.mResult;
            int iCalcNewSize = calcNewSize(iArr.length);
            int[] iArr2 = new int[iCalcNewSize];
            this.mResult = iArr2;
            System.arraycopy(iArr, this.mStart, iArr2, 0, this.mCount);
            this.mStart = 0;
            this.mEnd = iCalcNewSize;
        }

        public int[] getValues() {
            int i = this.mCount;
            int[] iArr = new int[i];
            System.arraycopy(this.mResult, this.mStart, iArr, 0, i);
            return iArr;
        }

        public IntArrayDecoder(int[] iArr, int i, int i2, IntDecoder intDecoder) {
            super(i, i2);
            this.mResult = iArr;
            this.mDecoder = intDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(char[] cArr, int i, int i2) throws IllegalArgumentException {
            this.mDecoder.decode(cArr, i, i2);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i3 = this.mCount + 1;
            this.mCount = i3;
            return i3 >= this.mEnd;
        }
    }

    public static final class LongArrayDecoder extends BaseArrayDecoder {
        final LongDecoder mDecoder;
        long[] mResult;

        public LongArrayDecoder(LongDecoder longDecoder) {
            super(0, 40);
            this.mResult = new long[40];
            this.mDecoder = longDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(String str) throws IllegalArgumentException {
            this.mDecoder.decode(str);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i = this.mCount + 1;
            this.mCount = i;
            return i >= this.mEnd;
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.BaseArrayDecoder
        public void expand() {
            long[] jArr = this.mResult;
            int iCalcNewSize = calcNewSize(jArr.length);
            long[] jArr2 = new long[iCalcNewSize];
            this.mResult = jArr2;
            System.arraycopy(jArr, this.mStart, jArr2, 0, this.mCount);
            this.mStart = 0;
            this.mEnd = iCalcNewSize;
        }

        public long[] getValues() {
            int i = this.mCount;
            long[] jArr = new long[i];
            System.arraycopy(this.mResult, this.mStart, jArr, 0, i);
            return jArr;
        }

        public LongArrayDecoder(long[] jArr, int i, int i2, LongDecoder longDecoder) {
            super(i, i2);
            this.mResult = jArr;
            this.mDecoder = longDecoder;
        }

        @Override // org.codehaus.stax2.typed.TypedArrayDecoder
        public boolean decodeValue(char[] cArr, int i, int i2) throws IllegalArgumentException {
            this.mDecoder.decode(cArr, i, i2);
            this.mResult[this.mStart + this.mCount] = this.mDecoder.getValue();
            int i3 = this.mCount + 1;
            this.mCount = i3;
            return i3 >= this.mEnd;
        }
    }

    public static final class DecimalDecoder extends DecoderBase {
        protected BigDecimal mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            int i3 = i2 - i;
            try {
                this.mValue = new BigDecimal(cArr, i, i3);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(new String(cArr, i, i3));
            }
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "decimal";
        }

        public BigDecimal getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            try {
                this.mValue = new BigDecimal(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }

    public static final class IntegerDecoder extends DecoderBase {
        protected BigInteger mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            String str = new String(cArr, i, i2 - i);
            try {
                this.mValue = new BigInteger(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "integer";
        }

        public BigInteger getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            try {
                this.mValue = new BigInteger(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }

    public static final class QNameDecoder extends DecoderBase {
        final NamespaceContext mNsCtxt;
        protected QName mValue;

        public QNameDecoder(NamespaceContext namespaceContext) {
            this.mNsCtxt = namespaceContext;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            for (int i3 = i; i3 < i2; i3++) {
                if (cArr[i3] == ':') {
                    this.mValue = resolveQName(new String(cArr, i, i3 - i), new String(cArr, i3 + 1, (i2 - i3) - 1));
                    return;
                }
            }
            this.mValue = resolveQName(new String(cArr, i, i2 - i));
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "QName";
        }

        public QName getValue() {
            return this.mValue;
        }

        public QName resolveQName(String str, String str2) throws IllegalArgumentException {
            if (str.isEmpty() || str2.isEmpty()) {
                throw constructInvalidValue(str + ":" + str2);
            }
            String namespaceURI = this.mNsCtxt.getNamespaceURI(str);
            if (namespaceURI != null && !namespaceURI.isEmpty()) {
                return new QName(namespaceURI, str2, str);
            }
            krd.a("Value \"", lexicalDesc(str + ":" + str2), "\" not a valid QName: prefix '", str, "' not bound to a namespace");
            return null;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int iIndexOf = str.indexOf(58);
            if (iIndexOf >= 0) {
                this.mValue = resolveQName(str.substring(0, iIndexOf), str.substring(iIndexOf + 1));
            } else {
                this.mValue = resolveQName(str);
            }
        }

        public QName resolveQName(String str) throws IllegalArgumentException {
            String namespaceURI = this.mNsCtxt.getNamespaceURI("");
            return new QName(namespaceURI != null ? namespaceURI : "", str);
        }
    }

    public static final class LongDecoder extends DecoderBase {
        protected long mValue;

        private long parseUsingBD(String str, boolean z) {
            BigInteger bigInteger = new BigInteger(str);
            if (z) {
                BigInteger bigIntegerNegate = bigInteger.negate();
                if (bigIntegerNegate.compareTo(DecoderBase.BD_MIN_LONG) >= 0) {
                    return bigIntegerNegate.longValue();
                }
            } else if (bigInteger.compareTo(DecoderBase.BD_MAX_LONG) <= 0) {
                return bigInteger.longValue();
            }
            yba.a("value \"", lexicalDesc(str), "\" not a valid long: overflow.");
            return 0L;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            LongDecoder longDecoder;
            char[] cArr2;
            int i3;
            int i4;
            int iSkipSignAndZeroes;
            char c = cArr[i];
            boolean z = c == '-';
            if (z || c == '+') {
                longDecoder = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
                iSkipSignAndZeroes = longDecoder.skipSignAndZeroes(cArr2, c, true, i3, i4);
            } else {
                longDecoder = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
                iSkipSignAndZeroes = longDecoder.skipSignAndZeroes(cArr2, c, false, i3, i4);
            }
            int i5 = longDecoder.mNextPtr;
            int i6 = i4 - i5;
            if (i6 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                longDecoder.mValue = iSkipSignAndZeroes;
                return;
            }
            longDecoder.verifyDigits(cArr2, i3, i4, i5);
            if (i6 <= 8) {
                int i7 = DecoderBase.parseInt(iSkipSignAndZeroes, cArr2, i5, i6 + i5);
                if (z) {
                    i7 = -i7;
                }
                longDecoder.mValue = i7;
                return;
            }
            int i8 = i5 - 1;
            int i9 = i6 + 1;
            if (i9 > 18) {
                longDecoder.mValue = longDecoder.parseUsingBD(new String(cArr2, i8, i9), z);
                return;
            }
            long j = DecoderBase.parseLong(cArr2, i8, i9 + i8);
            if (z) {
                j = -j;
            }
            longDecoder.mValue = j;
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "long";
        }

        public long getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int iSkipSignAndZeroes;
            int length = str.length();
            char cCharAt = str.charAt(0);
            boolean z = cCharAt == '-';
            if (!z && cCharAt != '+') {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, false, length);
            } else {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, true, length);
            }
            int i = this.mNextPtr;
            int i2 = length - i;
            if (i2 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                this.mValue = iSkipSignAndZeroes;
                return;
            }
            verifyDigits(str, i, length);
            if (i2 <= 8) {
                int i3 = DecoderBase.parseInt(iSkipSignAndZeroes, str, i, i2 + i);
                if (z) {
                    i3 = -i3;
                }
                this.mValue = i3;
                return;
            }
            int i4 = i - 1;
            int i5 = i2 + 1;
            if (i5 <= 18) {
                long j = DecoderBase.parseLong(str, i4, i5 + i4);
                if (z) {
                    j = -j;
                }
                this.mValue = j;
                return;
            }
            this.mValue = parseUsingBD(str.substring(i4, i5 + i4), z);
        }
    }

    public static final class DoubleDecoder extends DecoderBase {
        protected double mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            if (length == 3) {
                char cCharAt = str.charAt(0);
                if (cCharAt == 'I') {
                    if (str.charAt(1) == 'N' && str.charAt(2) == 'F') {
                        this.mValue = Double.POSITIVE_INFINITY;
                        return;
                    }
                } else if (cCharAt == 'N' && str.charAt(1) == 'a' && str.charAt(2) == 'N') {
                    this.mValue = Double.NaN;
                    return;
                }
            } else if (length == 4 && str.charAt(0) == '-' && str.charAt(1) == 'I' && str.charAt(2) == 'N' && str.charAt(3) == 'F') {
                this.mValue = Double.NEGATIVE_INFINITY;
                return;
            }
            try {
                this.mValue = Double.parseDouble(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "double";
        }

        public double getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            int i3 = i2 - i;
            if (i3 == 3) {
                char c = cArr[i];
                if (c == 'I') {
                    if (cArr[i + 1] == 'N' && cArr[i + 2] == 'F') {
                        this.mValue = Double.POSITIVE_INFINITY;
                        return;
                    }
                } else if (c == 'N' && cArr[i + 1] == 'a' && cArr[i + 2] == 'N') {
                    this.mValue = Double.NaN;
                    return;
                }
            } else if (i3 == 4 && cArr[i] == '-' && cArr[i + 1] == 'I' && cArr[i + 2] == 'N' && cArr[i + 3] == 'F') {
                this.mValue = Double.NEGATIVE_INFINITY;
                return;
            }
            String str = new String(cArr, i, i3);
            try {
                this.mValue = Double.parseDouble(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }

    public static final class FloatDecoder extends DecoderBase {
        protected float mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            if (length == 3) {
                char cCharAt = str.charAt(0);
                if (cCharAt == 'I') {
                    if (str.charAt(1) == 'N' && str.charAt(2) == 'F') {
                        this.mValue = Float.POSITIVE_INFINITY;
                        return;
                    }
                } else if (cCharAt == 'N' && str.charAt(1) == 'a' && str.charAt(2) == 'N') {
                    this.mValue = Float.NaN;
                    return;
                }
            } else if (length == 4 && str.charAt(0) == '-' && str.charAt(1) == 'I' && str.charAt(2) == 'N' && str.charAt(3) == 'F') {
                this.mValue = Float.NEGATIVE_INFINITY;
                return;
            }
            try {
                this.mValue = Float.parseFloat(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "float";
        }

        public float getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            int i3 = i2 - i;
            if (i3 == 3) {
                char c = cArr[i];
                if (c == 'I') {
                    if (cArr[i + 1] == 'N' && cArr[i + 2] == 'F') {
                        this.mValue = Float.POSITIVE_INFINITY;
                        return;
                    }
                } else if (c == 'N' && cArr[i + 1] == 'a' && cArr[i + 2] == 'N') {
                    this.mValue = Float.NaN;
                    return;
                }
            } else if (i3 == 4 && cArr[i] == '-' && cArr[i + 1] == 'I' && cArr[i + 2] == 'N' && cArr[i + 3] == 'F') {
                this.mValue = Float.NEGATIVE_INFINITY;
                return;
            }
            String str = new String(cArr, i, i3);
            try {
                this.mValue = Float.parseFloat(str);
            } catch (NumberFormatException unused) {
                throw constructInvalidValue(str);
            }
        }
    }

    public static final class BooleanDecoder extends DecoderBase {
        protected boolean mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int length = str.length();
            char cCharAt = str.charAt(0);
            if (cCharAt == 't') {
                if (length == 4 && str.charAt(1) == 'r' && str.charAt(2) == 'u' && str.charAt(3) == 'e') {
                    this.mValue = true;
                    return;
                }
            } else if (cCharAt == 'f') {
                if (length == 5 && str.charAt(1) == 'a' && str.charAt(2) == 'l' && str.charAt(3) == 's' && str.charAt(4) == 'e') {
                    this.mValue = false;
                    return;
                }
            } else if (cCharAt == '0') {
                if (length == 1) {
                    this.mValue = false;
                    return;
                }
            } else if (cCharAt == '1' && length == 1) {
                this.mValue = true;
                return;
            }
            throw constructInvalidValue(str);
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "boolean";
        }

        public boolean getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            int i3 = i2 - i;
            char c = cArr[i];
            if (c == 't') {
                if (i3 == 4 && cArr[i + 1] == 'r' && cArr[i + 2] == 'u' && cArr[i + 3] == 'e') {
                    this.mValue = true;
                    return;
                }
            } else if (c == 'f') {
                if (i3 == 5 && cArr[i + 1] == 'a' && cArr[i + 2] == 'l' && cArr[i + 3] == 's' && cArr[i + 4] == 'e') {
                    this.mValue = false;
                    return;
                }
            } else if (c == '0') {
                if (i3 == 1) {
                    this.mValue = false;
                    return;
                }
            } else if (c == '1' && i3 == 1) {
                this.mValue = true;
                return;
            }
            throw constructInvalidValue(cArr, i, i2);
        }
    }

    public static final class IntDecoder extends DecoderBase {
        protected int mValue;

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(char[] cArr, int i, int i2) throws IllegalArgumentException {
            IntDecoder intDecoder;
            char[] cArr2;
            int i3;
            int i4;
            int iSkipSignAndZeroes;
            char c = cArr[i];
            boolean z = c == '-';
            if (z || c == '+') {
                intDecoder = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
                iSkipSignAndZeroes = intDecoder.skipSignAndZeroes(cArr2, c, true, i3, i4);
            } else {
                intDecoder = this;
                cArr2 = cArr;
                i3 = i;
                i4 = i2;
                iSkipSignAndZeroes = intDecoder.skipSignAndZeroes(cArr2, c, false, i3, i4);
            }
            int i5 = intDecoder.mNextPtr;
            int i6 = i4 - i5;
            if (i6 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                intDecoder.mValue = iSkipSignAndZeroes;
                return;
            }
            intDecoder.verifyDigits(cArr2, i3, i4, i5);
            if (i6 <= 8) {
                int i7 = DecoderBase.parseInt(iSkipSignAndZeroes, cArr2, i5, i6 + i5);
                if (z) {
                    i7 = -i7;
                }
                intDecoder.mValue = i7;
                return;
            }
            if (i6 == 9 && iSkipSignAndZeroes < 3) {
                long j = (iSkipSignAndZeroes == 2 ? 2000000000L : 1000000000L) + ((long) DecoderBase.parseInt(cArr2, i5, i6 + i5));
                if (z) {
                    long j2 = -j;
                    if (j2 >= -2147483648L) {
                        intDecoder.mValue = (int) j2;
                        return;
                    }
                } else if (j <= 2147483647L) {
                    intDecoder.mValue = (int) j;
                    return;
                }
            }
            yba.a("value \"", intDecoder.lexicalDesc(cArr2, i3, i4), "\" not a valid 32-bit integer: overflow.");
        }

        @Override // org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecoderBase
        public String getType() {
            return "int";
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // org.codehaus.stax2.typed.TypedValueDecoder
        public void decode(String str) throws IllegalArgumentException {
            int iSkipSignAndZeroes;
            int length = str.length();
            char cCharAt = str.charAt(0);
            boolean z = cCharAt == '-';
            if (!z && cCharAt != '+') {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, false, length);
            } else {
                iSkipSignAndZeroes = skipSignAndZeroes(str, cCharAt, true, length);
            }
            int i = this.mNextPtr;
            int i2 = length - i;
            if (i2 == 0) {
                if (z) {
                    iSkipSignAndZeroes = -iSkipSignAndZeroes;
                }
                this.mValue = iSkipSignAndZeroes;
                return;
            }
            verifyDigits(str, i, length);
            if (i2 <= 8) {
                int i3 = DecoderBase.parseInt(iSkipSignAndZeroes, str, i, i2 + i);
                if (z) {
                    i3 = -i3;
                }
                this.mValue = i3;
                return;
            }
            if (i2 == 9 && iSkipSignAndZeroes < 3) {
                long j = (iSkipSignAndZeroes == 2 ? 2000000000L : 1000000000L) + ((long) DecoderBase.parseInt(str, i, i2 + i));
                if (z) {
                    long j2 = -j;
                    if (j2 >= -2147483648L) {
                        this.mValue = (int) j2;
                        return;
                    }
                } else if (j <= 2147483647L) {
                    this.mValue = (int) j;
                    return;
                }
            }
            yba.a("value \"", lexicalDesc(str), "\" not a valid 32-bit integer: overflow.");
        }
    }
}
