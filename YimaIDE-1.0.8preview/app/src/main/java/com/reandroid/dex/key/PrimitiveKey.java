package com.reandroid.dex.key;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.NumberX;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PrimitiveKey implements Key {
    private static PrimitiveKey FALSE_KEY;
    private static PrimitiveKey TRUE_KEY;

    public static class BooleanKey extends PrimitiveKey {
        private final boolean value;

        public BooleanKey(boolean z) {
            this.value = z;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append((CharSequence) (value() ? "true" : "false"));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((BooleanKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public Boolean getValue() {
            return Boolean.valueOf(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value() ? 1L : 0L;
        }

        public int hashCode() {
            return value() ? 1 : 0;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isBoolean() {
            return true;
        }

        public String toString() {
            return value() ? "true" : "false";
        }

        public boolean value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_Z;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 0;
        }
    }

    public static class ByteKey extends NumberKey {
        private final byte value;

        public ByteKey(byte b) {
            this.value = b;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendHex(value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((ByteKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Byte getValue() {
            return Byte.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isByte() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return HexUtil.toSignedHex((int) value()) + "t";
        }

        public byte value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_B;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 1;
        }
    }

    public static class CharKey extends PrimitiveKey {
        private final char value;

        public CharKey(char c) {
            this.value = c;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append((CharSequence) DexUtils.quoteChar(value()));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((CharKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public Character getValue() {
            return Character.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isChar() {
            return true;
        }

        public String toString() {
            return DexUtils.quoteChar(value());
        }

        public char value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_C;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 0;
        }
    }

    public static class DoubleKey extends NumberKey {
        private final double value;

        public DoubleKey(double d) {
            this.value = d;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, java.lang.Comparable
        public int compareTo(Object obj) {
            if (obj == this) {
                return 0;
            }
            return !(obj instanceof DoubleKey) ? StringsUtil.compareToString(this, obj) : Double.compare(value(), ((DoubleKey) obj).value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && Double.doubleToLongBits(value()) == Double.doubleToLongBits(((DoubleKey) obj).value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Double getValue() {
            return Double.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return Double.doubleToLongBits(value());
        }

        public int hashCode() {
            return Double.hashCode(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isDouble() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return Double.toString(value());
        }

        public double value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_D;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 8;
        }
    }

    public static class FloatKey extends NumberKey {
        private final float value;

        public FloatKey(float f) {
            this.value = f;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, java.lang.Comparable
        public int compareTo(Object obj) {
            if (obj == this) {
                return 0;
            }
            return !(obj instanceof FloatKey) ? StringsUtil.compareToString(this, obj) : Float.compare(value(), ((FloatKey) obj).value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && Float.floatToIntBits(value()) == Float.floatToIntBits(((FloatKey) obj).value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Float getValue() {
            return Float.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return Float.floatToIntBits(value());
        }

        public int hashCode() {
            return Float.hashCode(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isFloat() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return value() + "f";
        }

        public float value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_F;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 4;
        }
    }

    public static class IntegerKey extends NumberKey {
        private final int value;

        public IntegerKey(int i) {
            this.value = i;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendHex(value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((IntegerKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Integer getValue() {
            return Integer.valueOf(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isInteger() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return HexUtil.toSignedHex(getValue().intValue());
        }

        public int value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_I;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 4;
        }
    }

    public static class LongKey extends NumberKey {
        private final long value;

        public LongKey(long j) {
            this.value = j;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendHex(value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((LongKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Long getValue() {
            return Long.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return Long.hashCode(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isLong() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return HexUtil.toSignedHex(getValue().longValue());
        }

        public long value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_J;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 8;
        }
    }

    public static abstract class NumberKey extends PrimitiveKey {
        @Override // com.reandroid.dex.key.PrimitiveKey
        public abstract Number getValue();

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isNumber() {
            return true;
        }

        public String toString() {
            return getValue().toString();
        }
    }

    public static class NumberXKey extends NumberKey {
        private final long value;
        private final int width;

        public NumberXKey(int i, long j) {
            this.width = i;
            this.value = j;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendHex(value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((NumberXKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Number getValue() {
            int iWidth = width();
            long jValue = value();
            if (iWidth == 1) {
                return Byte.valueOf((byte) jValue);
            }
            if (iWidth == 2) {
                return Short.valueOf((short) jValue);
            }
            if (iWidth == 4) {
                return Integer.valueOf((int) jValue);
            }
            return iWidth == 8 ? Long.valueOf(jValue) : NumberX.valueOf(width(), value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return Long.hashCode(value());
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isX() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return NumberX.toHexString(width(), value());
        }

        public long value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return null;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return this.width;
        }
    }

    public static class ShortKey extends NumberKey {
        private final short value;

        public ShortKey(short s) {
            this.value = s;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.appendHex(value());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && obj.getClass() == getClass() && value() == ((ShortKey) obj).value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey, com.reandroid.dex.key.PrimitiveKey
        public Short getValue() {
            return Short.valueOf(this.value);
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public long getValueAsLong() {
            return value();
        }

        public int hashCode() {
            return value();
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public boolean isShort() {
            return true;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey.NumberKey
        public String toString() {
            return HexUtil.toSignedHex((int) value()) + "S";
        }

        public short value() {
            return this.value;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public TypeKey valueType() {
            return TypeKey.TYPE_S;
        }

        @Override // com.reandroid.dex.key.PrimitiveKey
        public int width() {
            return 2;
        }
    }

    public static PrimitiveKey of(boolean z) {
        if (z) {
            PrimitiveKey primitiveKey = TRUE_KEY;
            if (primitiveKey != null) {
                return primitiveKey;
            }
            BooleanKey booleanKey = new BooleanKey(true);
            TRUE_KEY = booleanKey;
            return booleanKey;
        }
        PrimitiveKey primitiveKey2 = FALSE_KEY;
        if (primitiveKey2 != null) {
            return primitiveKey2;
        }
        BooleanKey booleanKey2 = new BooleanKey(false);
        FALSE_KEY = booleanKey2;
        return booleanKey2;
    }

    public static PrimitiveKey parse(String str) {
        return PrimitiveKeyHelper.parse(str);
    }

    public static PrimitiveKey readSafe(SmaliReader smaliReader) {
        return PrimitiveKeyHelper.readSafe(smaliReader);
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public abstract void append(SmaliWriter smaliWriter) throws IOException;

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        return !(obj instanceof PrimitiveKey) ? StringsUtil.compareToString(this, obj) : Long.compare(getValueAsLong(), ((PrimitiveKey) obj).getValueAsLong());
    }

    public abstract Object getValue();

    public abstract long getValueAsLong();

    public boolean isBoolean() {
        return false;
    }

    public boolean isByte() {
        return false;
    }

    public boolean isChar() {
        return false;
    }

    public boolean isDouble() {
        return false;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isLong() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isShort() {
        return false;
    }

    public boolean isX() {
        return false;
    }

    public abstract TypeKey valueType();

    public abstract int width();

    public static PrimitiveKey of(byte b) {
        return new ByteKey(b);
    }

    public static PrimitiveKey of(char c) {
        return new CharKey(c);
    }

    public static PrimitiveKey of(double d) {
        return new DoubleKey(d);
    }

    public static PrimitiveKey of(float f) {
        return new FloatKey(f);
    }

    public static PrimitiveKey of(int i) {
        return new IntegerKey(i);
    }

    public static PrimitiveKey of(long j) {
        return new LongKey(j);
    }

    public static PrimitiveKey of(short s) {
        return new ShortKey(s);
    }

    public static PrimitiveKey of(int i, long j) {
        return new NumberXKey(i, j);
    }
}
