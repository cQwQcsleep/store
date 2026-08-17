package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class J2 {
    public final J2 a;

    public J2(J2 j2) {
        this.a = j2;
    }

    public J2 a(String str, String str2) {
        J2 j2 = this.a;
        if (j2 != null) {
            return j2.a(str, str2);
        }
        return null;
    }

    public void a(String str, String str2, String str3) {
        J2 j2 = this.a;
        if (j2 != null) {
            j2.a(str, str2, str3);
        }
    }

    public void a(Object obj, String str) {
        J2 j2 = this.a;
        if (j2 != null) {
            j2.a(obj, str);
        }
    }

    public J2 a(String str) {
        J2 j2 = this.a;
        if (j2 != null) {
            return j2.a(str);
        }
        return null;
    }

    public void a() {
        J2 j2 = this.a;
        if (j2 != null) {
            j2.a();
        }
    }
}
