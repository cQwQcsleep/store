package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1523fp;
import com.android.tools.r8.internal.AbstractC2508rO;
import com.android.tools.r8.internal.C3091y90;
import com.android.tools.r8.naming.C3331k;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetraceClassElement;
import com.android.tools.r8.retrace.RetraceFieldResult;
import com.android.tools.r8.retrace.RetraceFrameResult;
import com.android.tools.r8.retrace.RetraceMethodResult;
import com.android.tools.r8.retrace.RetraceResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceUnknownJsonMappingInformationResult;
import com.android.tools.r8.retrace.RetracedClassReference;
import com.android.tools.r8.retrace.RetracedSourceFile;
import defpackage.sqi;
import defpackage.uqi;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3007x90 implements RetraceClassElement {
    public final C3091y90 a;
    public final W90 b;
    public final C3331k c;

    public C3007x90(C3091y90 c3091y90, W90 w90, C3331k c3331k) {
        this.a = c3091y90;
        this.b = w90;
        this.c = c3331k;
    }

    public final A90 a(C1351dp c1351dp) {
        BiFunction biFunction = new BiFunction() { // from class: rqi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C3091y90.a((C3331k) obj, (AbstractC1523fp) obj2);
            }
        };
        sqi sqiVar = new sqi();
        ArrayList arrayList = new ArrayList();
        C3091y90.b(c1351dp, arrayList, biFunction, this);
        C3091y90 c3091y90 = this.a;
        return (A90) sqiVar.a(c3091y90, arrayList, c1351dp, c3091y90.c);
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement, com.android.tools.r8.retrace.RetraceElement
    public final RetraceResult getParentResult() {
        return this.a;
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetracedClassReference getRetracedClass() {
        return this.b;
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetracedSourceFile getSourceFile() {
        W90 w90 = this.b;
        C1667ha0 c1667ha0 = this.a.c;
        HashSet hashSet = V90.a;
        return new C1410ea0(w90, c1667ha0.a.b(w90.getClassReference().getTypeName()));
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceUnknownJsonMappingInformationResult getUnknownJsonMappingInformation() {
        return T90.a(this.c.c());
    }

    @Override // com.android.tools.r8.retrace.RetraceElement
    public final boolean isCompilerSynthesized() {
        C3331k c3331k = this.a.b;
        if (c3331k == null) {
            return false;
        }
        Iterator<com.android.tools.r8.naming.mappinginformation.e> it = c3331k.c().iterator();
        while (it.hasNext()) {
            if (it.next().l()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceFieldResult lookupField(String str) {
        return a(new C1351dp(this.b.a, str));
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceFrameResult lookupFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, String str, List list, TypeReference typeReference) {
        return this.a.a(new C2423qO(Reference.method(this.b.a, str, list, typeReference))).narrowByPosition(retraceStackTraceContext, optionalInt);
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceMethodResult lookupMethod(String str) {
        return a(new C2338pO(this.b.a, str));
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceFrameResult lookupFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, String str) {
        return this.a.a(new C2338pO(this.b.a, str)).narrowByPosition(retraceStackTraceContext, optionalInt);
    }

    public final G90 a(C2338pO c2338pO) {
        BiFunction biFunction = new BiFunction() { // from class: tqi
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return C3091y90.a((C3331k) obj, (AbstractC2508rO) obj2);
            }
        };
        uqi uqiVar = new uqi();
        ArrayList arrayList = new ArrayList();
        C3091y90.b(c2338pO, arrayList, biFunction, this);
        C3091y90 c3091y90 = this.a;
        return (G90) uqiVar.a(c3091y90, arrayList, c2338pO, c3091y90.c);
    }

    @Override // com.android.tools.r8.retrace.RetraceClassElement
    public final RetraceFrameResult lookupFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, MethodReference methodReference) {
        return this.a.a(new C2423qO(methodReference)).narrowByPosition(retraceStackTraceContext, optionalInt);
    }
}
