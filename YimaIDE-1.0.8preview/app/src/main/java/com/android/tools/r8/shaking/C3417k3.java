package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.Bc0;
import com.sun.jna.platform.linux.Fcntl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.k3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3417k3 {
    public static final Bc0 u;
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;
    public boolean e = false;
    public boolean f = false;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q = false;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;

    static {
        int i = AbstractC0551Hu.c;
        u = new Bc0("*");
    }

    public static C3417k3 a(List<String> list) {
        C3417k3 c3417k3 = new C3417k3();
        c3417k3.a = a(c3417k3.a, "SourceFile", list);
        c3417k3.b = a(c3417k3.b, "SourceDir", list);
        c3417k3.c = a(c3417k3.c, "InnerClasses", list);
        c3417k3.d = a(c3417k3.d, "EnclosingMethod", list);
        c3417k3.g = a(c3417k3.g, "LineNumberTable", list);
        c3417k3.h = a(c3417k3.h, "LocalVariableTable", list);
        c3417k3.i = a(c3417k3.i, "LocalVariableTypeTable", list);
        c3417k3.f = a(c3417k3.f, "Exceptions", list);
        c3417k3.j = a(c3417k3.j, "MethodParameters", list);
        c3417k3.e = a(c3417k3.e, "Signature", list);
        c3417k3.k = a(c3417k3.k, "SourceDebugExtension", list);
        c3417k3.l = a(c3417k3.l, "RuntimeVisibleAnnotations", list);
        c3417k3.m = a(c3417k3.m, "RuntimeInvisibleAnnotations", list);
        c3417k3.n = a(c3417k3.n, "RuntimeVisibleParameterAnnotations", list);
        c3417k3.o = a(c3417k3.o, "RuntimeInvisibleParameterAnnotations", list);
        c3417k3.p = a(c3417k3.p, "RuntimeVisibleTypeAnnotations", list);
        c3417k3.q = a(c3417k3.q, "RuntimeInvisibleTypeAnnotations", list);
        c3417k3.r = a(c3417k3.r, "AnnotationDefault", list);
        c3417k3.s = a(c3417k3.s, "StackMapTable", list);
        c3417k3.t = a(c3417k3.t, "PermittedSubclasses", list);
        return c3417k3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3417k3)) {
            return false;
        }
        C3417k3 c3417k3 = (C3417k3) obj;
        return this.a == c3417k3.a && this.b == c3417k3.b && this.c == c3417k3.c && this.d == c3417k3.d && this.e == c3417k3.e && this.f == c3417k3.f && this.j == c3417k3.j && this.k == c3417k3.k && this.l == c3417k3.l && this.m == c3417k3.m && this.n == c3417k3.n && this.o == c3417k3.o && this.p == c3417k3.p && this.q == c3417k3.q && this.r == c3417k3.r && this.s == c3417k3.s && this.t == c3417k3.t;
    }

    public final int hashCode() {
        return (this.a ? 1 : 0) + (this.b ? 2 : 0) + (this.c ? 4 : 0) + (this.d ? 8 : 0) + (this.e ? 16 : 0) + (this.f ? 32 : 0) + (this.k ? 64 : 0) + (this.l ? 128 : 0) + (this.m ? Fcntl.S_IRUSR : 0) + (this.n ? 512 : 0) + (this.o ? Fcntl.S_ISGID : 0) + (this.p ? Fcntl.S_ISUID : 0) + (this.q ? 4096 : 0) + (this.r ? 8192 : 0) + (this.s ? 16384 : 0) + (this.j ? 32768 : 0) + (this.t ? 65536 : 0);
    }

    public String toString() {
        return a(new StringBuilder()).toString();
    }

    public static boolean a(int i, int i2, String str, String str2) {
        while (i < str.length()) {
            int i3 = i + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '*') {
                while (i2 < str2.length()) {
                    int i4 = i2 + 1;
                    if (a(i3, i2, str, str2)) {
                        return true;
                    }
                    i2 = i4;
                }
                return i3 >= str.length();
            }
            if (i2 >= str2.length() || str2.charAt(i2) != cCharAt) {
                return false;
            }
            i2++;
            i = i3;
        }
        return i2 == str2.length();
    }

    public static boolean a(boolean z, String str, List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (z) {
                return true;
            }
            if (str2.length() > 0 && str2.charAt(0) == '!') {
                if (a(1, 0, str2, str)) {
                    break;
                }
            } else {
                z = a(0, 0, str2, str);
            }
        }
        return z;
    }

    public final StringBuilder a(StringBuilder sb) {
        ArrayList arrayList = new ArrayList();
        if (this.a) {
            arrayList.add("SourceFile");
        }
        if (this.b) {
            arrayList.add("SourceDir");
        }
        if (this.c) {
            arrayList.add("InnerClasses");
        }
        if (this.d) {
            arrayList.add("EnclosingMethod");
        }
        if (this.e) {
            arrayList.add("Signature");
        }
        if (this.f) {
            arrayList.add("Exceptions");
        }
        if (this.j) {
            arrayList.add("MethodParameters");
        }
        if (this.k) {
            arrayList.add("SourceDebugExtension");
        }
        if (this.l) {
            arrayList.add("RuntimeVisibleAnnotations");
        }
        if (this.m) {
            arrayList.add("RuntimeInvisibleAnnotations");
        }
        if (this.n) {
            arrayList.add("RuntimeVisibleParameterAnnotations");
        }
        if (this.o) {
            arrayList.add("RuntimeInvisibleParameterAnnotations");
        }
        if (this.p) {
            arrayList.add("RuntimeVisibleTypeAnnotations");
        }
        if (this.q) {
            arrayList.add("RuntimeInvisibleTypeAnnotations");
        }
        if (this.r) {
            arrayList.add("AnnotationDefault");
        }
        if (this.s) {
            arrayList.add("StackMapTable");
        }
        if (this.t) {
            arrayList.add("PermittedSubclasses");
        }
        if (arrayList.size() > 0) {
            sb.append("-keepattributes ");
            sb.append(String.join(",", arrayList));
        }
        return sb;
    }
}
