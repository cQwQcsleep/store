package com.reandroid.dex.common;

import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodHandleType implements Comparable<MethodHandleType>, SmaliFormat {
    public static final MethodHandleType INSTANCE_GET;
    public static final MethodHandleType INSTANCE_PUT;
    public static final MethodHandleType INVOKE_CONSTRUCTOR;
    public static final MethodHandleType INVOKE_DIRECT;
    public static final MethodHandleType INVOKE_INSTANCE;
    public static final MethodHandleType INVOKE_INTERFACE;
    public static final MethodHandleType INVOKE_STATIC;
    public static final MethodHandleType STATIC_GET;
    public static final MethodHandleType STATIC_PUT;
    private static final MethodHandleType[] VALUES;
    private static final Map<String, MethodHandleType> nameMap;
    private final boolean field;
    private final String name;
    private final int type;

    static {
        MethodHandleType methodHandleType = new MethodHandleType(0, "static-put", true);
        STATIC_PUT = methodHandleType;
        MethodHandleType methodHandleType2 = new MethodHandleType(1, "static-get", true);
        STATIC_GET = methodHandleType2;
        MethodHandleType methodHandleType3 = new MethodHandleType(2, "instance-put", true);
        INSTANCE_PUT = methodHandleType3;
        MethodHandleType methodHandleType4 = new MethodHandleType(3, "instance-get", true);
        INSTANCE_GET = methodHandleType4;
        MethodHandleType methodHandleType5 = new MethodHandleType(4, "invoke-static", false);
        INVOKE_STATIC = methodHandleType5;
        MethodHandleType methodHandleType6 = new MethodHandleType(5, "invoke-instance", false);
        INVOKE_INSTANCE = methodHandleType6;
        MethodHandleType methodHandleType7 = new MethodHandleType(6, "invoke-constructor", false);
        INVOKE_CONSTRUCTOR = methodHandleType7;
        MethodHandleType methodHandleType8 = new MethodHandleType(7, "invoke-direct", false);
        INVOKE_DIRECT = methodHandleType8;
        MethodHandleType methodHandleType9 = new MethodHandleType(8, "invoke-interface", false);
        INVOKE_INTERFACE = methodHandleType9;
        MethodHandleType[] methodHandleTypeArr = {methodHandleType, methodHandleType2, methodHandleType3, methodHandleType4, methodHandleType5, methodHandleType6, methodHandleType7, methodHandleType8, methodHandleType9};
        VALUES = methodHandleTypeArr;
        HashMap map = new HashMap(9);
        nameMap = map;
        for (int i = 0; i < 9; i++) {
            MethodHandleType methodHandleType10 = methodHandleTypeArr[i];
            map.put(methodHandleType10.name, methodHandleType10);
        }
    }

    private MethodHandleType(int i, String str, boolean z) {
        this.type = i;
        this.name = str;
        this.field = z;
    }

    public static MethodHandleType get(SmaliReader smaliReader) {
        if (smaliReader.available() < 10) {
            return null;
        }
        int iPosition = smaliReader.position();
        MethodHandleType methodHandleType = read(smaliReader);
        smaliReader.position(iPosition);
        return methodHandleType;
    }

    public static MethodHandleType read(SmaliReader smaliReader) {
        int iIndexOfBeforeLineEnd;
        smaliReader.skipWhitespaces();
        int iPosition = smaliReader.position();
        char ascii = smaliReader.getASCII(iPosition);
        if ((ascii != 's' && ascii != 'i') || (iIndexOfBeforeLineEnd = smaliReader.indexOfBeforeLineEnd('@')) < 0) {
            return null;
        }
        MethodHandleType methodHandleTypeValueOf = valueOf(smaliReader.readString(iIndexOfBeforeLineEnd - iPosition).trim());
        if (methodHandleTypeValueOf == null) {
            smaliReader.position(iPosition);
        }
        return methodHandleTypeValueOf;
    }

    public static boolean startsWithHandleType(SmaliReader smaliReader) {
        return get(smaliReader) != null;
    }

    public static MethodHandleType valueOf(int i) {
        if (i < 0 || i >= 9) {
            return null;
        }
        return VALUES[i];
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) name());
    }

    @Override // java.lang.Comparable
    public int compareTo(MethodHandleType methodHandleType) {
        if (methodHandleType == this) {
            return 0;
        }
        if (methodHandleType == null) {
            return -1;
        }
        return CompareUtil.compare(type(), methodHandleType.type());
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int hashCode() {
        return this.type;
    }

    public boolean isField() {
        return this.field;
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public int type() {
        return this.type;
    }

    public static MethodHandleType valueOf(String str) {
        return nameMap.get(str);
    }
}
