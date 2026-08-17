package com.android.tools.r8.internal;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.references.TypeReference;
import com.android.tools.r8.retrace.RetraceFieldResult;
import com.android.tools.r8.retrace.RetraceFrameResult;
import com.android.tools.r8.retrace.RetraceMethodResult;
import com.android.tools.r8.retrace.RetraceStackTraceContext;
import com.android.tools.r8.retrace.RetraceThrownExceptionResult;
import com.android.tools.r8.retrace.RetraceTypeResult;
import com.android.tools.r8.retrace.Retracer;
import java.util.OptionalInt;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ha0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1667ha0 implements Retracer {
    public static final /* synthetic */ boolean c = true;
    public final SM a;
    public final DiagnosticsHandler b;

    public C1667ha0(SM sm, DiagnosticsHandler diagnosticsHandler) {
        this.a = sm;
        this.b = diagnosticsHandler;
        if (c || sm != null) {
            return;
        }
        x1f.a();
        throw null;
    }

    public static C1667ha0 a(SM sm, DiagnosticsHandler diagnosticsHandler) {
        return new C1667ha0(sm, diagnosticsHandler);
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final C3091y90 retraceClass(ClassReference classReference) {
        return new C3091y90(classReference, this.a.a(classReference.getTypeName()), this);
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceFieldResult retraceField(FieldReference fieldReference) {
        return retraceClass(fieldReference.getHolderClass()).a(new C1437ep(fieldReference));
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceFrameResult retraceFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, MethodReference methodReference) {
        C3091y90 c3091y90RetraceClass = retraceClass(methodReference.getHolderClass());
        return c3091y90RetraceClass.a(new C2423qO(Reference.method(c3091y90RetraceClass.a, methodReference.getMethodName(), methodReference.getFormalTypes(), methodReference.getReturnType()))).narrowByPosition(retraceStackTraceContext, optionalInt);
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceMethodResult retraceMethod(MethodReference methodReference) {
        return retraceClass(methodReference.getHolderClass()).a(new C2423qO(methodReference));
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceThrownExceptionResult retraceThrownException(ClassReference classReference) {
        C3091y90 c3091y90RetraceClass = retraceClass(classReference);
        return new Q90(c3091y90RetraceClass.a, c3091y90RetraceClass.b);
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceTypeResult retraceType(TypeReference typeReference) {
        return new S90(typeReference, S90.a(typeReference, this), this);
    }

    @Override // com.android.tools.r8.retrace.Retracer
    public final RetraceFrameResult retraceFrame(RetraceStackTraceContext retraceStackTraceContext, OptionalInt optionalInt, ClassReference classReference, String str) {
        C3091y90 c3091y90RetraceClass = retraceClass(classReference);
        return c3091y90RetraceClass.a(new C2338pO(c3091y90RetraceClass.a, str)).narrowByPosition(retraceStackTraceContext, optionalInt);
    }
}
