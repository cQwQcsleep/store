package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.ArrayValueKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.NullValueKey;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import defpackage.aca;
import defpackage.i0f;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliValueFactory {
    public static SmaliValue create(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        int iPosition = smaliReader.position();
        byte b = smaliReader.get(iPosition);
        if (b == 46) {
            SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
            if (smaliDirective == SmaliDirective.ENUM) {
                return new SmaliValueEnum();
            }
            if (smaliDirective == SmaliDirective.SUB_ANNOTATION) {
                return new SmaliValueAnnotation();
            }
            l78.a("Unrecognized value", smaliReader);
            return null;
        }
        if (b == 123) {
            return new SmaliValueArray();
        }
        if (b == 39) {
            return new SmaliValueChar();
        }
        if (b == 110) {
            return new SmaliValueNull();
        }
        if (b == 116 || b == 102) {
            return new SmaliValueBoolean();
        }
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
        if (b > 57 || b < 48) {
            return new SmaliValueSectionData();
        }
        return smaliReader.get(smaliReader.indexOfWhiteSpaceOrComment() + (-1)) == 102 ? new SmaliValueFloat() : new SmaliValueDouble();
    }

    public static SmaliValue createForField(TypeKey typeKey) {
        if (typeKey.isTypeArray() || !typeKey.isPrimitive()) {
            return new SmaliValueNull();
        }
        if (TypeKey.TYPE_I.equals(typeKey)) {
            return new SmaliValueInteger();
        }
        if (TypeKey.TYPE_J.equals(typeKey)) {
            return new SmaliValueLong();
        }
        if (TypeKey.TYPE_D.equals(typeKey)) {
            return new SmaliValueDouble();
        }
        if (TypeKey.TYPE_F.equals(typeKey)) {
            return new SmaliValueFloat();
        }
        if (TypeKey.TYPE_S.equals(typeKey)) {
            return new SmaliValueShort();
        }
        if (TypeKey.TYPE_B.equals(typeKey)) {
            return new SmaliValueByte();
        }
        if (TypeKey.TYPE_C.equals(typeKey)) {
            return new SmaliValueChar();
        }
        if (TypeKey.TYPE_Z.equals(typeKey)) {
            return new SmaliValueBoolean();
        }
        aca.a("Undefined: ", typeKey);
        return null;
    }

    public static SmaliValue createForValue(Key key) {
        SmaliValue smaliValueInitializeForValue = initializeForValue(key);
        if (smaliValueInitializeForValue != null) {
            smaliValueInitializeForValue.setKey(key);
        }
        return smaliValueInitializeForValue;
    }

    public static SmaliValue initializeForValue(Key key) {
        if (key == null) {
            return null;
        }
        if (!(key instanceof PrimitiveKey)) {
            if ((key instanceof StringKey) || (key instanceof TypeKey) || (key instanceof MethodKey) || (key instanceof MethodHandleKey)) {
                return new SmaliValueSectionData();
            }
            if (key instanceof FieldKey) {
                return new SmaliValueEnum();
            }
            if (key instanceof ArrayValueKey) {
                return new SmaliValueArray();
            }
            if (key instanceof AnnotationItemKey) {
                return new SmaliValueAnnotation();
            }
            if (key instanceof NullValueKey) {
                return new SmaliValueNull();
            }
            i0f.a("Undefined key: ", key.getClass(), ", ", key);
            return null;
        }
        PrimitiveKey primitiveKey = (PrimitiveKey) key;
        if (primitiveKey.isX()) {
            return new SmaliValueX();
        }
        if (primitiveKey.isBoolean()) {
            return new SmaliValueBoolean();
        }
        if (primitiveKey.isByte()) {
            return new SmaliValueByte();
        }
        if (primitiveKey.isChar()) {
            return new SmaliValueChar();
        }
        if (primitiveKey.isDouble()) {
            return new SmaliValueDouble();
        }
        if (primitiveKey.isFloat()) {
            return new SmaliValueFloat();
        }
        if (primitiveKey.isInteger()) {
            return new SmaliValueInteger();
        }
        if (primitiveKey.isLong()) {
            return new SmaliValueLong();
        }
        if (primitiveKey.isShort()) {
            return new SmaliValueShort();
        }
        i0f.a("Unknown primitive key: ", key.getClass(), ", ", key);
        return null;
    }

    public static SmaliValueNumber<?> valueNumberFor(PrimitiveKey primitiveKey) {
        if (primitiveKey.isByte()) {
            return new SmaliValueByte();
        }
        if (primitiveKey.isShort()) {
            return new SmaliValueShort();
        }
        if (primitiveKey.isInteger()) {
            return new SmaliValueInteger();
        }
        if (!primitiveKey.isLong() && !primitiveKey.isX()) {
            i0f.a("Unknown primitive key type: ", primitiveKey.getClass(), ", ", primitiveKey);
            return null;
        }
        return new SmaliValueLong();
    }

    public static SmaliValueNumber<?> valueNumberForWidth(int i) {
        if (i == 1) {
            return new SmaliValueByte();
        }
        if (i == 2) {
            return new SmaliValueShort();
        }
        if (i == 4) {
            return new SmaliValueInteger();
        }
        if (i == 8) {
            return new SmaliValueLong();
        }
        if (i >= 0 && i < 8) {
            return new SmaliValueX();
        }
        qf1.a("Unknown width: ", i);
        return null;
    }
}
