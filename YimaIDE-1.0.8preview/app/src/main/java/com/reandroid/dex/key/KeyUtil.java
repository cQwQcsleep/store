package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.ObjectsUtil;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class KeyUtil {
    public static final String ANY_NAME = ObjectsUtil.of("*");

    private static TypeKey getReturnTypeForValue(Key key, int i) {
        if (key instanceof StringKey) {
            return TypeKey.STRING.setArrayDimension(i);
        }
        if (key instanceof TypeKey) {
            return TypeKey.CLASS.setArrayDimension(i);
        }
        if (key instanceof PrimitiveKey) {
            return ((PrimitiveKey) key).valueType().setArrayDimension(i);
        }
        if (key instanceof EnumKey) {
            return ((EnumKey) key).getType().setArrayDimension(i);
        }
        if (key instanceof AnnotationItemKey) {
            return ((AnnotationItemKey) key).getType().setArrayDimension(i);
        }
        if (key instanceof KeyList) {
            return getReturnTypeForValue(((KeyList) key).get(0), i + 1);
        }
        return null;
    }

    public static boolean matches(String str, String str2) {
        if (str == null) {
            if (str2 == null) {
                return true;
            }
            return str2.equals(ANY_NAME);
        }
        if (str2 == null) {
            return false;
        }
        if (!str.equals(str2)) {
            String str3 = ANY_NAME;
            if (!str.equals(str3) && !str2.equals(str3)) {
                return false;
            }
        }
        return true;
    }

    public static Key readKey(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
        if (smaliDirective != null) {
            if (smaliDirective == SmaliDirective.SUB_ANNOTATION) {
                return AnnotationItemKey.read(smaliReader);
            }
            if (smaliDirective == SmaliDirective.ENUM) {
                return EnumKey.read(smaliReader);
            }
            l78.a("Unexpected directive", smaliReader);
            return null;
        }
        char ascii = smaliReader.getASCII(smaliReader.position());
        if (ascii == '{') {
            return ArrayValueKey.read(smaliReader);
        }
        if (ascii == '\"') {
            return StringKey.read(smaliReader);
        }
        if (ascii == '(') {
            return ProtoKey.read(smaliReader);
        }
        if (ascii == 'n') {
            return NullValueKey.read(smaliReader);
        }
        PrimitiveKey safe = PrimitiveKey.readSafe(smaliReader);
        if (safe != null) {
            return safe;
        }
        MethodHandleKey methodHandleKey = MethodHandleKey.read(smaliReader);
        if (methodHandleKey != null) {
            return methodHandleKey;
        }
        TypeKey typeKeyPrimitiveType = TypeKey.primitiveType(ascii);
        if (typeKeyPrimitiveType != null) {
            smaliReader.readASCII();
            return typeKeyPrimitiveType;
        }
        if (ascii != 'L' && ascii != '[') {
            l78.a("Unrecognized value", smaliReader);
            return null;
        }
        TypeKey typeKey = TypeKey.read(smaliReader);
        if (smaliReader.finished() || smaliReader.get() != 45) {
            return typeKey;
        }
        smaliReader.readASCII();
        SmaliParseException.expect(smaliReader, '>');
        smaliReader.skipWhitespacesOrComment();
        StringKey simpleName = StringKey.readSimpleName(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        if (SmaliParseException.expect(smaliReader, '(', ':') != '(') {
            return FieldKey.create(typeKey, simpleName, TypeKey.read(smaliReader));
        }
        smaliReader.skip(-1);
        return MethodKey.create(typeKey, simpleName, ProtoKey.read(smaliReader));
    }

    public static TypeKey getReturnTypeForValue(Key key) {
        return getReturnTypeForValue(key, 0);
    }
}
