package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0497Fs {
    public final int a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;

    public C0497Fs(int i, String str, String str2, String str3, boolean z) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = z;
    }

    public final String a() {
        return this.c;
    }

    public final String b() {
        return this.b;
    }

    public final int c() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0497Fs)) {
            return false;
        }
        C0497Fs c0497Fs = (C0497Fs) obj;
        return this.a == c0497Fs.a && this.e == c0497Fs.e && this.b.equals(c0497Fs.b) && this.c.equals(c0497Fs.c) && this.d.equals(c0497Fs.d);
    }

    public final int hashCode() {
        return (this.d.hashCode() * this.c.hashCode() * this.b.hashCode()) + this.a + (this.e ? 64 : 0);
    }

    public final String toString() {
        return this.b + "." + this.c + this.d + " (" + this.a + (this.e ? " itf" : XmlPullParser.NO_NAMESPACE) + ")";
    }
}
