package com.sun.tools.javac.jvm;

import com.sun.tools.javac.util.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassFile {
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Dynamic = 17;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_InvokeDynamic = 18;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_Module = 19;
    public static final int CONSTANT_NameandType = 12;
    public static final int CONSTANT_Package = 20;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Unicode = 2;
    public static final int CONSTANT_Utf8 = 1;
    public static final int JAVA_MAGIC = -889275714;
    public static final int MAX_ANNOTATIONS = 65535;
    public static final int MAX_CODE = 65535;
    public static final int MAX_DIMENSIONS = 255;
    public static final int MAX_LOCALS = 65535;
    public static final int MAX_PARAMETERS = 255;
    public static final int MAX_STACK = 65535;
    public static final int PREVIEW_MINOR_VERSION = 65535;
    public static final int REF_getField = 1;
    public static final int REF_getStatic = 2;
    public static final int REF_invokeInterface = 9;
    public static final int REF_invokeSpecial = 7;
    public static final int REF_invokeStatic = 6;
    public static final int REF_invokeVirtual = 5;
    public static final int REF_newInvokeSpecial = 8;
    public static final int REF_putField = 3;
    public static final int REF_putStatic = 4;

    public enum Version {
        V45_3(45, 3),
        V48(48, 0),
        V49(49, 0),
        V50(50, 0),
        V51(51, 0),
        V52(52, 0),
        V53(53, 0),
        V54(54, 0),
        V55(55, 0),
        V56(56, 0),
        V57(57, 0),
        V58(58, 0),
        V59(59, 0),
        V60(60, 0),
        V61(61, 0),
        V62(62, 0),
        V63(63, 0),
        V64(64, 0),
        V65(65, 0),
        V66(66, 0),
        V67(67, 0),
        V68(68, 0),
        V69(69, 0),
        V70(70, 0);

        public final int major;
        public final int minor;
        private static final Version MIN = values()[0];
        private static final Version MAX = values()[values().length - 1];

        Version(int i, int i2) {
            this.major = i;
            this.minor = i2;
        }

        public static Version MAX() {
            return MAX;
        }

        public static Version MIN() {
            return MIN;
        }
    }

    public static Name externalize(Name name) {
        return name.table.names.fromString(externalize(name.toString()));
    }

    public static byte[] internalize(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            if (b == 47) {
                bArr2[i3] = 46;
            } else {
                bArr2[i3] = b;
            }
        }
        return bArr2;
    }

    public static String externalize(String str) {
        return str.replace('.', '/');
    }

    public static Name internalize(Name name) {
        return name.table.names.fromString(name.toString().replace('/', '.'));
    }
}
