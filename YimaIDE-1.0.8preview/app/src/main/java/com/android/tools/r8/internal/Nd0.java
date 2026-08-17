package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.StackTraceLineParser;
import defpackage.oof;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Nd0 implements StackTraceLineParser {
    public static final /* synthetic */ boolean l = true;
    public final Pattern a;
    public final ArrayList c;
    public final Ld0 b = new Ld0();
    public final Md0 d = new Md0();
    public final Ad0 e = new Ad0();
    public final Kd0 f = new Kd0();
    public final Fd0 g = new Fd0();
    public final Ed0 h = new Ed0();
    public final Gd0 i = new Gd0();
    public final Hd0 j = new Hd0();
    public final Dd0 k = new Dd0();

    public Nd0(String str) {
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        StringBuilder sb = new StringBuilder();
        a(str, sb, arrayList);
        this.a = Pattern.compile(sb.toString());
    }

    public final void a(String str, StringBuilder sb, ArrayList arrayList) {
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (!z) {
                boolean z3 = !z2 && str.charAt(i3) == '%';
                z2 = !z2 && str.charAt(i3) == '\\';
                z = z3;
            } else {
                if (!l && z2) {
                    x1f.a();
                    return;
                }
                Id0 id0A = a(str.charAt(i3));
                sb.append((CharSequence) str, i, i3 - 1);
                id0A.getClass();
                int i4 = i2 + 1;
                String str2 = "captureGroup" + i2;
                sb.append("(?<");
                sb.append(str2);
                sb.append(">");
                sb.append(id0A.a());
                sb.append(")");
                arrayList.add(id0A.a(str2));
                i2 = i4;
                i = i3 + 1;
                z = false;
            }
        }
        sb.append((CharSequence) str, i, str.length());
    }

    @Override // com.android.tools.r8.retrace.StackTraceLineParser
    public final C3210zd0 parse(String str) {
        boolean z = C3210zd0.j;
        C3041xd0 c3041xd0 = new C3041xd0(str);
        Matcher matcher = this.a.matcher(str);
        if (matcher.matches()) {
            boolean zA = false;
            for (Jd0 jd0 : this.c) {
                if (!zA || !jd0.a()) {
                    if (jd0.a(c3041xd0, matcher)) {
                        zA |= jd0.a();
                    }
                }
            }
        }
        return new C3210zd0(c3041xd0.a, c3041xd0.b, c3041xd0.c, c3041xd0.d, c3041xd0.e, c3041xd0.f, c3041xd0.g, c3041xd0.h, c3041xd0.i);
    }

    public final Id0 a(char c) {
        if (c == 'C') {
            return this.e;
        }
        if (c == 'S') {
            return this.b;
        }
        if (c == 'a') {
            return this.i;
        }
        if (c == 'c') {
            return this.d;
        }
        if (c == 'f') {
            return this.k;
        }
        if (c == 'l') {
            return this.g;
        }
        if (c == 'm') {
            return this.j;
        }
        if (c == 's') {
            return this.f;
        }
        if (c == 't') {
            return this.h;
        }
        oof.a("Unexpected variable: ", c);
        return null;
    }
}
