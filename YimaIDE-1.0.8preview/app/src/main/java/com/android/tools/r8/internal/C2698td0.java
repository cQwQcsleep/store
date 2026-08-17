package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceStackTraceElementProxy;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import com.android.tools.r8.retrace.RetracedTypeReference;
import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.td0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2698td0 implements RetraceStackTraceElementProxy {
    public static final /* synthetic */ boolean m = true;
    public final StackTraceElementProxy b;
    public final RetracedClassReference c;
    public final RetracedMethodReference d;
    public final RetracedFieldReference e;
    public final RetracedTypeReference f;
    public final List g;
    public final RetracedSourceFile h;
    public final int i;
    public final boolean j;
    public final boolean k;
    public final RetraceStackTraceContext l;

    public C2698td0(StackTraceElementProxy stackTraceElementProxy, RetracedClassReference retracedClassReference, RetracedMethodReference retracedMethodReference, RetracedFieldReference retracedFieldReference, RetracedTypeReference retracedTypeReference, List list, RetracedSourceFile retracedSourceFile, int i, boolean z, boolean z2, RetraceStackTraceContext retraceStackTraceContext) {
        if (!m && stackTraceElementProxy == null) {
            x1f.a();
            throw null;
        }
        this.b = stackTraceElementProxy;
        this.c = retracedClassReference;
        this.d = retracedMethodReference;
        this.e = retracedFieldReference;
        this.f = retracedTypeReference;
        this.g = list;
        this.h = retracedSourceFile;
        this.i = i;
        this.j = z;
        this.k = z2;
        this.l = retraceStackTraceContext;
    }

    public final C2612sd0 a() {
        C2612sd0 c2612sd0 = new C2612sd0(this.b);
        c2612sd0.b = this.c;
        c2612sd0.c = this.d;
        c2612sd0.d = this.e;
        c2612sd0.e = this.f;
        c2612sd0.f = this.g;
        c2612sd0.g = this.h;
        c2612sd0.h = this.i;
        c2612sd0.i = this.j;
        c2612sd0.j = this.k;
        c2612sd0.k = this.l;
        return c2612sd0;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        int iCompareTo;
        int iCompareTo2;
        int iCompareTo3;
        RetraceStackTraceElementProxy retraceStackTraceElementProxy = (RetraceStackTraceElementProxy) obj;
        if (this == retraceStackTraceElementProxy) {
            return 0;
        }
        int iCompare = Boolean.compare(hasRetracedClass(), retraceStackTraceElementProxy.hasRetracedClass());
        if (iCompare != 0) {
            return iCompare;
        }
        if (hasRetracedClass() && (iCompareTo3 = this.c.getTypeName().compareTo(retraceStackTraceElementProxy.getRetracedClass().getTypeName())) != 0) {
            return iCompareTo3;
        }
        int iCompare2 = Boolean.compare(hasRetracedMethod(), retraceStackTraceElementProxy.hasRetracedMethod());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        if (hasRetracedMethod() && (iCompareTo2 = this.d.compareTo(retraceStackTraceElementProxy.getRetracedMethod())) != 0) {
            return iCompareTo2;
        }
        int iCompare3 = Boolean.compare(hasSourceFile(), retraceStackTraceElementProxy.hasSourceFile());
        if (iCompare3 != 0) {
            return iCompare3;
        }
        if (hasSourceFile() && (iCompareTo = getSourceFile().compareTo(retraceStackTraceElementProxy.getSourceFile())) != 0) {
            return iCompareTo;
        }
        int iCompare4 = Boolean.compare(hasLineNumber(), retraceStackTraceElementProxy.hasLineNumber());
        if (iCompare4 != 0) {
            return iCompare4;
        }
        if (hasLineNumber()) {
            return Integer.compare(this.i, retraceStackTraceElementProxy.getLineNumber());
        }
        return 0;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetraceStackTraceContext getContext() {
        return this.l;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final int getLineNumber() {
        return this.i;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final StackTraceElementProxy getOriginalItem() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetracedClassReference getRetracedClass() {
        return this.c;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetracedFieldReference getRetracedField() {
        return this.e;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetracedTypeReference getRetracedFieldOrReturnType() {
        return this.f;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetracedMethodReference getRetracedMethod() {
        return this.d;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final List getRetracedMethodArguments() {
        return this.g;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final RetracedSourceFile getRetracedSourceFile() {
        return this.h;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final String getSourceFile() {
        RetracedSourceFile retracedSourceFile = this.h;
        if (retracedSourceFile != null) {
            return retracedSourceFile.getOrInferSourceFile(this.b.getSourceFile() == null ? XmlPullParser.NO_NAMESPACE : this.b.getSourceFile());
        }
        if (!m && this.b.getSourceFile() != null) {
            x1f.a();
        }
        return null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasLineNumber() {
        return this.i != -1;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasRetracedClass() {
        return this.c != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasRetracedField() {
        return this.e != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasRetracedFieldOrReturnType() {
        return this.f != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasRetracedMethod() {
        return this.d != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasRetracedMethodArguments() {
        return this.g != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean hasSourceFile() {
        return this.h != null;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean isAmbiguous() {
        return this.j;
    }

    @Override // com.android.tools.r8.retrace.RetraceStackTraceElementProxy
    public final boolean isTopFrame() {
        return this.k;
    }
}
