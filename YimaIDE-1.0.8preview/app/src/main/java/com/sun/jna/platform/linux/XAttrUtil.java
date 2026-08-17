package com.sun.jna.platform.linux;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class XAttrUtil {
    private XAttrUtil() {
    }

    public static String fGetXAttr(int i, String str, String str2) throws IOException {
        return new String(fGetXAttrBytes(i, str), Charset.forName(str2));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static Memory fGetXAttrAsMemory(int i, String str) throws IOException {
        Memory memory;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarFgetxattr = xAttr.fgetxattr(i, str, (Pointer) null, XAttr.size_t.ZERO);
            if (ssize_tVarFgetxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            if (ssize_tVarFgetxattr.longValue() == 0) {
                return null;
            }
            memory = new Memory(ssize_tVarFgetxattr.longValue());
            XAttr.ssize_t ssize_tVarFgetxattr2 = xAttr.fgetxattr(i, str, memory, new XAttr.size_t(memory.size()));
            if (ssize_tVarFgetxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarFgetxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return memory;
    }

    public static byte[] fGetXAttrBytes(int i, String str) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarFgetxattr = xAttr.fgetxattr(i, str, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVarFgetxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVarFgetxattr.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVarFgetxattr2 = xAttr.fgetxattr(i, str, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVarFgetxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarFgetxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return bArr;
    }

    public static Collection<String> fListXAttr(int i, String str) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarFlistxattr = xAttr.flistxattr(i, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVarFlistxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVarFlistxattr.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVarFlistxattr2 = xAttr.flistxattr(i, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVarFlistxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarFlistxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return splitBufferToStrings(bArr, str);
    }

    public static void fRemoveXAttr(int i, String str) throws IOException {
        if (XAttr.INSTANCE.fremovexattr(i, str) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    public static void fSetXAttr(int i, String str, byte[] bArr) throws IOException {
        if (XAttr.INSTANCE.fsetxattr(i, str, bArr, new XAttr.size_t(bArr.length), 0) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    public static String getXAttr(String str, String str2, String str3) throws IOException {
        return new String(getXAttrBytes(str, str2), Charset.forName(str3));
    }

    public static Memory getXAttrAsMemory(String str, String str2) throws IOException {
        Memory memory;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVar = xAttr.getxattr(str, str2, (Pointer) null, XAttr.size_t.ZERO);
            if (ssize_tVar.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            if (ssize_tVar.longValue() == 0) {
                return null;
            }
            memory = new Memory(ssize_tVar.longValue());
            XAttr.ssize_t ssize_tVar2 = xAttr.getxattr(str, str2, memory, new XAttr.size_t(memory.size()));
            if (ssize_tVar2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVar2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return memory;
    }

    public static byte[] getXAttrBytes(String str, String str2) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVar = xAttr.getxattr(str, str2, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVar.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVar.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVar2 = xAttr.getxattr(str, str2, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVar2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVar2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return bArr;
    }

    public static String lGetXAttr(String str, String str2, String str3) throws IOException {
        return new String(lGetXAttrBytes(str, str2), Charset.forName(str3));
    }

    public static Memory lGetXAttrAsMemory(String str, String str2) throws IOException {
        Memory memory;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarLgetxattr = xAttr.lgetxattr(str, str2, (Pointer) null, XAttr.size_t.ZERO);
            if (ssize_tVarLgetxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            if (ssize_tVarLgetxattr.longValue() == 0) {
                return null;
            }
            memory = new Memory(ssize_tVarLgetxattr.longValue());
            XAttr.ssize_t ssize_tVarLgetxattr2 = xAttr.lgetxattr(str, str2, memory, new XAttr.size_t(memory.size()));
            if (ssize_tVarLgetxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarLgetxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return memory;
    }

    public static byte[] lGetXAttrBytes(String str, String str2) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarLgetxattr = xAttr.lgetxattr(str, str2, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVarLgetxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVarLgetxattr.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVarLgetxattr2 = xAttr.lgetxattr(str, str2, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVarLgetxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarLgetxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return bArr;
    }

    public static Collection<String> lListXAttr(String str, String str2) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarLlistxattr = xAttr.llistxattr(str, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVarLlistxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVarLlistxattr.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVarLlistxattr2 = xAttr.llistxattr(str, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVarLlistxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarLlistxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return splitBufferToStrings(bArr, str2);
    }

    public static void lRemoveXAttr(String str, String str2) throws IOException {
        if (XAttr.INSTANCE.lremovexattr(str, str2) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    public static void lSetXAttr(String str, String str2, byte[] bArr) throws IOException {
        if (XAttr.INSTANCE.lsetxattr(str, str2, bArr, new XAttr.size_t(bArr.length), 0) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    public static Collection<String> listXAttr(String str, String str2) throws IOException {
        byte[] bArr;
        int lastError = 0;
        do {
            XAttr xAttr = XAttr.INSTANCE;
            XAttr.ssize_t ssize_tVarListxattr = xAttr.listxattr(str, (byte[]) null, XAttr.size_t.ZERO);
            if (ssize_tVarListxattr.longValue() < 0) {
                t8g.a("errno: ", Native.getLastError());
                return null;
            }
            int iIntValue = ssize_tVarListxattr.intValue();
            bArr = new byte[iIntValue];
            XAttr.ssize_t ssize_tVarListxattr2 = xAttr.listxattr(str, bArr, new XAttr.size_t(iIntValue));
            if (ssize_tVarListxattr2.longValue() < 0 && (lastError = Native.getLastError()) != 34) {
                t8g.a("errno: ", lastError);
                return null;
            }
            if (ssize_tVarListxattr2.longValue() >= 0) {
                break;
            }
        } while (lastError == 34);
        return splitBufferToStrings(bArr, str2);
    }

    public static void removeXAttr(String str, String str2) throws IOException {
        if (XAttr.INSTANCE.removexattr(str, str2) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    public static void setXAttr(String str, String str2, byte[] bArr) throws IOException {
        if (XAttr.INSTANCE.setxattr(str, str2, bArr, new XAttr.size_t(bArr.length), 0) == 0) {
            return;
        }
        t8g.a("errno: ", Native.getLastError());
    }

    private static Collection<String> splitBufferToStrings(byte[] bArr, String str) throws IOException {
        Charset charsetForName = Charset.forName(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet(1);
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (bArr[i2] == 0) {
                linkedHashSet.add(new String(bArr, i, i2 - i, charsetForName));
                i = i2 + 1;
            }
        }
        return linkedHashSet;
    }

    public static String fGetXAttr(int i, String str) throws IOException {
        return fGetXAttr(i, str, Native.getDefaultStringEncoding());
    }

    public static String getXAttr(String str, String str2) throws IOException {
        return getXAttr(str, str2, Native.getDefaultStringEncoding());
    }

    public static String lGetXAttr(String str, String str2) throws IOException {
        return lGetXAttr(str, str2, Native.getDefaultStringEncoding());
    }

    public static void fSetXAttr(int i, String str, String str2, String str3) throws IOException {
        fSetXAttr(i, str, str2.getBytes(str3));
    }

    public static void lSetXAttr(String str, String str2, String str3, String str4) throws IOException {
        lSetXAttr(str, str2, str3.getBytes(str4));
    }

    public static void setXAttr(String str, String str2, String str3, String str4) throws IOException {
        setXAttr(str, str2, str3.getBytes(str4));
    }

    public static void fSetXAttr(int i, String str, String str2) throws IOException {
        fSetXAttr(i, str, str2, Native.getDefaultStringEncoding());
    }

    public static void lSetXAttr(String str, String str2, String str3) throws IOException {
        lSetXAttr(str, str2, str3, Native.getDefaultStringEncoding());
    }

    public static void setXAttr(String str, String str2, String str3) throws IOException {
        setXAttr(str, str2, str3, Native.getDefaultStringEncoding());
    }

    public static Collection<String> fListXAttr(int i) throws IOException {
        return fListXAttr(i, Native.getDefaultStringEncoding());
    }

    public static Collection<String> lListXAttr(String str) throws IOException {
        return lListXAttr(str, Native.getDefaultStringEncoding());
    }

    public static Collection<String> listXAttr(String str) throws IOException {
        return listXAttr(str, Native.getDefaultStringEncoding());
    }
}
