package com.sun.tools.classfile;

import com.intellij.psi.PsiKeyword;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AccessFlags {
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_BRIDGE = 64;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_FINAL = 16;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_MANDATED = 32768;
    public static final int ACC_MODULE = 32768;
    public static final int ACC_NATIVE = 256;
    public static final int ACC_PRIVATE = 2;
    public static final int ACC_PROTECTED = 4;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_STATIC = 8;
    public static final int ACC_STRICT = 2048;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNCHRONIZED = 32;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int ACC_TRANSIENT = 128;
    public static final int ACC_VARARGS = 128;
    public static final int ACC_VOLATILE = 64;
    public final int flags;
    private static final int[] classModifiers = {1, 16, 1024};
    private static final int[] classFlags = {1, 16, 32, 512, 1024, 4096, 8192, 16384, 32768};
    private static final int[] innerClassModifiers = {1, 2, 4, 8, 16, 1024};
    private static final int[] innerClassFlags = {1, 2, 4, 8, 16, 32, 512, 1024, 4096, 8192, 16384};
    private static final int[] fieldModifiers = {1, 2, 4, 8, 16, 64, 128};
    private static final int[] fieldFlags = {1, 2, 4, 8, 16, 64, 128, 4096, 16384};
    private static final int[] methodModifiers = {1, 2, 4, 8, 16, 32, 256, 1024, 2048};
    private static final int[] methodFlags = {1, 2, 4, 8, 16, 32, 64, 128, 256, 1024, 2048, 4096};

    public enum Kind {
        Class,
        InnerClass,
        Field,
        Method
    }

    public AccessFlags(ClassReader classReader) throws IOException {
        this(classReader.readUnsignedShort());
    }

    private static String flagToModifier(int i, Kind kind) {
        if (i == 1) {
            return PsiKeyword.PUBLIC;
        }
        if (i == 2) {
            return PsiKeyword.PRIVATE;
        }
        if (i == 4) {
            return PsiKeyword.PROTECTED;
        }
        if (i == 8) {
            return PsiKeyword.STATIC;
        }
        if (i == 16) {
            return PsiKeyword.FINAL;
        }
        if (i == 32) {
            return PsiKeyword.SYNCHRONIZED;
        }
        if (i == 64) {
            return PsiKeyword.VOLATILE;
        }
        if (i == 128) {
            if (kind == Kind.Field) {
                return PsiKeyword.TRANSIENT;
            }
            return null;
        }
        if (i == 256) {
            return PsiKeyword.NATIVE;
        }
        if (i == 1024) {
            return PsiKeyword.ABSTRACT;
        }
        if (i == 2048) {
            return PsiKeyword.STRICTFP;
        }
        if (i != 32768) {
            return null;
        }
        return "mandated";
    }

    private static String flagToName(int i, Kind kind) {
        if (i == 1) {
            return "ACC_PUBLIC";
        }
        if (i == 2) {
            return "ACC_PRIVATE";
        }
        switch (i) {
            case 4:
                return "ACC_PROTECTED";
            case 8:
                return "ACC_STATIC";
            case 16:
                return "ACC_FINAL";
            case 32:
                return kind == Kind.Class ? "ACC_SUPER" : "ACC_SYNCHRONIZED";
            case 64:
                return kind == Kind.Field ? "ACC_VOLATILE" : "ACC_BRIDGE";
            case 128:
                return kind == Kind.Field ? "ACC_TRANSIENT" : "ACC_VARARGS";
            case 256:
                return "ACC_NATIVE";
            case 512:
                return "ACC_INTERFACE";
            case 1024:
                return "ACC_ABSTRACT";
            case 2048:
                return "ACC_STRICT";
            case 4096:
                return "ACC_SYNTHETIC";
            case 8192:
                return "ACC_ANNOTATION";
            case 16384:
                return "ACC_ENUM";
            case 32768:
                return kind == Kind.Class ? "ACC_MODULE" : "ACC_MANDATED";
            default:
                return null;
        }
    }

    private Set<String> getFlags(int[] iArr, Kind kind) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = this.flags;
        for (int i2 : iArr) {
            if ((i & i2) != 0) {
                linkedHashSet.add(flagToName(i2, kind));
                i &= ~i2;
            }
        }
        while (i != 0) {
            int iHighestOneBit = Integer.highestOneBit(i);
            linkedHashSet.add("0x" + Integer.toHexString(iHighestOneBit));
            i &= ~iHighestOneBit;
        }
        return linkedHashSet;
    }

    private static Set<String> getModifiers(int i, int[] iArr, Kind kind) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i2 : iArr) {
            if ((i & i2) != 0) {
                linkedHashSet.add(flagToModifier(i2, kind));
            }
        }
        return linkedHashSet;
    }

    public int byteLength() {
        return 2;
    }

    public Set<String> getClassFlags() {
        return getFlags(classFlags, Kind.Class);
    }

    public Set<String> getClassModifiers() {
        int i = this.flags;
        if ((i & 512) != 0) {
            i &= -1025;
        }
        return getModifiers(i, classModifiers, Kind.Class);
    }

    public Set<String> getFieldFlags() {
        return getFlags(fieldFlags, Kind.Field);
    }

    public Set<String> getFieldModifiers() {
        return getModifiers(fieldModifiers, Kind.Field);
    }

    public Set<String> getInnerClassFlags() {
        return getFlags(innerClassFlags, Kind.InnerClass);
    }

    public Set<String> getInnerClassModifiers() {
        int i = this.flags;
        if ((i & 512) != 0) {
            i &= -1025;
        }
        return getModifiers(i, innerClassModifiers, Kind.InnerClass);
    }

    public Set<String> getMethodFlags() {
        return getFlags(methodFlags, Kind.Method);
    }

    public Set<String> getMethodModifiers() {
        return getModifiers(methodModifiers, Kind.Method);
    }

    public AccessFlags ignore(int i) {
        return new AccessFlags(this.flags & (~i));
    }

    public boolean is(int i) {
        return (this.flags & i) != 0;
    }

    public AccessFlags(int i) {
        this.flags = i;
    }

    private Set<String> getModifiers(int[] iArr, Kind kind) {
        return getModifiers(this.flags, iArr, kind);
    }
}
