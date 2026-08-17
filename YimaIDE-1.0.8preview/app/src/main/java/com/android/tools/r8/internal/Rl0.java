package com.android.tools.r8.internal;

import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Rl0 {
    public final Ql0 a;
    public final N00 b;
    public final EnumC2023li c;
    public final Integer d;
    public final String e;

    public Rl0(Ql0 ql0, N00 n00, EnumC2023li enumC2023li, Integer num, String str) {
        KB.c(n00, "kind");
        this.a = ql0;
        this.b = n00;
        this.c = enumC2023li;
        this.d = num;
        this.e = str;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("since ");
        sb.append(this.a);
        sb.append(' ');
        sb.append(this.c);
        Integer num = this.d;
        String str2 = XmlPullParser.NO_NAMESPACE;
        if (num != null) {
            str = " error " + this.d;
        } else {
            str = XmlPullParser.NO_NAMESPACE;
        }
        sb.append(str);
        if (this.e != null) {
            str2 = ": " + this.e;
        }
        sb.append(str2);
        return sb.toString();
    }
}
