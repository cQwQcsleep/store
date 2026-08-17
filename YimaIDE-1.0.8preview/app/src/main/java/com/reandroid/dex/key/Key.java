package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Key extends Comparable<Object>, SmaliFormat {
    public static final String DALVIK_accessFlags = ObjectsUtil.of("accessFlags");
    public static final String DALVIK_name = ObjectsUtil.of("name");
    public static final String DALVIK_value = ObjectsUtil.of("value");

    static Key parseBasic(String str) {
        if (StringsUtil.isEmpty(str)) {
            return null;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt == '\"') {
            return StringKey.parseQuotedString(str);
        }
        if (cCharAt == 'L' || cCharAt == '[') {
            if (cCharAt != 'L' || str.indexOf(58) <= 0) {
                return str.indexOf(40) > 0 ? MethodKey.parse(str) : TypeKey.parse(str);
            }
            return FieldKey.parse(str);
        }
        if (cCharAt == '(') {
            return str.indexOf(44) > 0 ? CallSiteKey.parse(str) : ProtoKey.parse(str);
        }
        if (cCharAt == '{') {
            return ArrayValueKey.parse(str);
        }
        if (str.startsWith(".annotation") || str.startsWith(".subannotation")) {
            return AnnotationItemKey.parse(str);
        }
        if (str.startsWith(".enum")) {
            return EnumKey.parse(str);
        }
        if (str.indexOf(40) > 0) {
            return MethodKey.parse(str);
        }
        return str.indexOf(58) > 0 ? FieldKey.parse(str) : PrimitiveKey.parse(str);
    }

    default void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) toString());
    }

    @Override // java.lang.Comparable
    int compareTo(Object obj);

    default TypeKey getDeclaring() {
        return null;
    }

    default boolean isPrimitiveKey() {
        return false;
    }

    default Iterator<? extends Key> mentionedKeys() {
        throw new RuntimeException("Method 'mentionedKeys()' Not implemented for: " + getClass());
    }

    default Key replaceKey(Key key, Key key2) {
        return this;
    }

    default boolean uses(Key key) {
        return equals(key) || CollectionUtil.contains(mentionedKeys(), key);
    }
}
