package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.NumberX;
import com.reandroid.utils.ObjectsUtil;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import defpackage.l78;
import java.io.IOException;
import java.lang.Number;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliValueNumber<T extends Number> extends SmaliValue {
    public static <T1 extends Number> SmaliValueNumber<T1> createFor(T1 t1) {
        return (SmaliValueNumber<T1>) createUnchecked(t1);
    }

    public static SmaliValueNumber<?> createNumber(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        byte b = smaliReader.get(iPosition);
        if (b == 45 || b == 43) {
            iPosition++;
            b = smaliReader.get(iPosition);
        }
        if (b == 48 && smaliReader.get(iPosition + 1) == 120) {
            byte b2 = smaliReader.get(smaliReader.indexOfWhiteSpaceOrComment() - 1);
            if (b2 == 116) {
                return new SmaliValueByte();
            }
            if (b2 == 83 || b2 == 115) {
                return new SmaliValueShort();
            }
            return b2 == 76 ? new SmaliValueLong() : new SmaliValueInteger();
        }
        if (smaliReader.startsWith(new byte[]{ElementValue.PRIMITIVE_INT, 110, 102, 105, 110, 105, 116, 121}, iPosition)) {
            return smaliReader.get(8 + iPosition) == 102 ? new SmaliValueFloat() : new SmaliValueDouble();
        }
        if (smaliReader.startsWith(new byte[]{78, 97, 78}, iPosition)) {
            return smaliReader.get(3 + iPosition) == 102 ? new SmaliValueFloat() : new SmaliValueDouble();
        }
        if (b <= 57 && b >= 48) {
            return smaliReader.get(smaliReader.indexOfWhiteSpaceOrComment() + (-1)) == 102 ? new SmaliValueFloat() : new SmaliValueDouble();
        }
        l78.a("Unrecognized number format", smaliReader);
        return null;
    }

    private static SmaliValueNumber<?> createUnchecked(Number number) {
        number.getClass();
        if (number instanceof Byte) {
            return new SmaliValueByte(((Byte) number).byteValue());
        }
        if (number instanceof Short) {
            return new SmaliValueShort(((Short) number).shortValue());
        }
        if (number instanceof Integer) {
            return new SmaliValueInteger(((Integer) number).intValue());
        }
        if (number instanceof Long) {
            return new SmaliValueLong(((Long) number).longValue());
        }
        if (number instanceof Float) {
            return new SmaliValueFloat(((Float) number).floatValue());
        }
        if (number instanceof Double) {
            return new SmaliValueDouble(((Double) number).doubleValue());
        }
        if (number instanceof NumberX) {
            return new SmaliValueX((NumberX) number);
        }
        ib0.a("Unrecognized number class: ", number.getClass());
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsUtil.equals(getKey(), ((SmaliValueNumber) obj).getKey());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyItem
    public abstract PrimitiveKey getKey();

    public abstract T getNumber();

    public abstract long getValueAsLong();

    public abstract int getWidth();

    public int hashCode() {
        return ObjectsUtil.hash(getKey());
    }

    @Override // com.reandroid.dex.smali.model.SmaliValue, com.reandroid.dex.key.KeyReference
    public abstract void setKey(Key key);

    public abstract void setNumber(T t);
}
