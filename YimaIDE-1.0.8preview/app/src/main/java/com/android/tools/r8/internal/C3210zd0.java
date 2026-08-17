package com.android.tools.r8.internal;

import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxy;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3210zd0 extends StackTraceElementProxy<String, C3210zd0> {
    public static final /* synthetic */ boolean j = true;
    public final String a;
    public final List b;
    public final C2955wd0 c;
    public final C3124yd0 d;
    public final C3124yd0 e;
    public final C3124yd0 f;
    public final C3124yd0 g;
    public final C3124yd0 h;
    public final C3124yd0 i;

    public C3210zd0(String str, ArrayList arrayList, C2955wd0 c2955wd0, C3124yd0 c3124yd0, C3124yd0 c3124yd1, C3124yd0 c3124yd2, C3124yd0 c3124yd3, C3124yd0 c3124yd4, C3124yd0 c3124yd5) {
        this.a = str;
        this.b = arrayList;
        this.c = c2955wd0;
        this.d = c3124yd0;
        this.e = c3124yd1;
        this.f = c3124yd2;
        this.g = c3124yd3;
        this.h = c3124yd4;
        this.i = c3124yd5;
    }

    public final String a(C3124yd0 c3124yd0) {
        if (j || c3124yd0 != C2955wd0.e) {
            return this.a.substring(c3124yd0.a, c3124yd0.b);
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final ClassReference getClassReference() {
        if (!this.c.a()) {
            return null;
        }
        C2955wd0 c2955wd0 = this.c;
        String strSubstring = this.a.substring(c2955wd0.a, c2955wd0.b);
        return c2955wd0.d == EnumC2869vd0.b ? Reference.classFromBinaryName(strSubstring) : Reference.classFromTypeName(strSubstring);
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String getFieldName() {
        if (this.g.a()) {
            return a(this.g);
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String getFieldOrReturnType() {
        if (this.h.a()) {
            return a(this.h);
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final int getLineNumber() {
        if (!this.f.a()) {
            return -1;
        }
        try {
            String strA = a(this.f);
            if (strA.startsWith(":")) {
                strA = strA.substring(1);
            }
            if (strA.isEmpty()) {
                return -1;
            }
            return Integer.parseInt(strA);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String getMethodArguments() {
        if (this.i.a()) {
            return a(this.i);
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String getMethodName() {
        if (this.d.a()) {
            return a(this.d);
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String getSourceFile() {
        if (this.e.a()) {
            return a(this.e);
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasClassName() {
        return this.c.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasFieldName() {
        return this.g.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasFieldOrReturnType() {
        return this.h.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasLineNumber() {
        return this.f.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasMethodArguments() {
        return this.i.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasMethodName() {
        return this.d.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final boolean hasSourceFile() {
        return this.e.a();
    }

    @Override // com.android.tools.r8.retrace.StackTraceElementProxy
    public final String toRetracedItem(RetraceStackTraceElementProxy retraceStackTraceElementProxy, boolean z) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (C3124yd0 c3124yd0 : this.b) {
            sb.append((CharSequence) this.a, i, c3124yd0.a);
            sb.append((String) c3124yd0.c.a(retraceStackTraceElementProxy, this, Boolean.valueOf(z)));
            i = c3124yd0.b;
        }
        String str = this.a;
        sb.append((CharSequence) str, i, str.length());
        return sb.toString();
    }

    public final String a() {
        return a(this.f);
    }
}
