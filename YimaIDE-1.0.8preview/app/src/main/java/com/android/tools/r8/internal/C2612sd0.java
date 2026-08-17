package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import com.android.tools.r8.retrace.RetracedTypeReference;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sd0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2612sd0 {
    public static final /* synthetic */ boolean l = true;
    public final StackTraceElementProxy a;
    public RetracedClassReference b;
    public RetracedMethodReference c;
    public RetracedFieldReference d;
    public RetracedTypeReference e;
    public List f;
    public RetracedSourceFile g;
    public int h = -1;
    public boolean i;
    public boolean j;
    public RetraceStackTraceContext k;

    public C2612sd0(StackTraceElementProxy stackTraceElementProxy) {
        this.a = stackTraceElementProxy;
    }

    public final C2698td0 a() {
        RetracedClassReference holderClass = this.b;
        RetracedMethodReference retracedMethodReference = this.c;
        if (retracedMethodReference != null) {
            holderClass = retracedMethodReference.getHolderClass();
        }
        return new C2698td0(this.a, holderClass, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
    }

    public final C2612sd0 a(Consumer consumer) {
        consumer.accept(this);
        return this;
    }

    public final C2612sd0 a(boolean z, Consumer consumer) {
        if (z) {
            consumer.accept(this);
        }
        return this;
    }

    public final C2612sd0 a(boolean z) {
        this.i = z || this.i;
        return this;
    }
}
