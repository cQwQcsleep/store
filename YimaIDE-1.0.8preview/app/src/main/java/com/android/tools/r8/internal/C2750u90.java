package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.RetraceStackTraceElementProxy;
import com.android.tools.r8.retrace.RetracedFieldReference;
import com.android.tools.r8.retrace.RetracedMethodReference;
import com.android.tools.r8.retrace.RetracedTypeReference;
import defpackage.xei;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2750u90 extends AbstractC2205no {
    public static final /* synthetic */ boolean b = true;
    public final boolean a;

    public C2750u90(boolean z) {
        this.a = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(RetraceStackTraceElementProxy retraceStackTraceElementProxy, RetraceStackTraceElementProxy retraceStackTraceElementProxy2) {
        if (retraceStackTraceElementProxy == retraceStackTraceElementProxy2) {
            return true;
        }
        if (a(retraceStackTraceElementProxy, retraceStackTraceElementProxy2, new Function() { // from class: yei
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((RetraceStackTraceElementProxy) obj).hasRetracedClass());
            }
        }, new Function() { // from class: zei
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetraceStackTraceElementProxy) obj).getRetracedClass().getTypeName();
            }
        }) || a(retraceStackTraceElementProxy, retraceStackTraceElementProxy2, new Function() { // from class: afi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((RetraceStackTraceElementProxy) obj).hasSourceFile());
            }
        }, new Function() { // from class: bfi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RetraceStackTraceElementProxy) obj).getSourceFile();
            }
        })) {
            return false;
        }
        if (!b && retraceStackTraceElementProxy.getOriginalItem() != retraceStackTraceElementProxy2.getOriginalItem()) {
            x1f.a();
            return false;
        }
        if (((this.a || (retraceStackTraceElementProxy.getOriginalItem().hasLineNumber() && retraceStackTraceElementProxy.getOriginalItem().getLineNumber() > 0)) && a(retraceStackTraceElementProxy, retraceStackTraceElementProxy2, new Function() { // from class: cfi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((RetraceStackTraceElementProxy) obj).hasLineNumber());
            }
        }, new Function() { // from class: dfi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((RetraceStackTraceElementProxy) obj).getLineNumber());
            }
        })) || retraceStackTraceElementProxy.hasRetracedMethod() != retraceStackTraceElementProxy2.hasRetracedMethod()) {
            return false;
        }
        if (retraceStackTraceElementProxy.hasRetracedMethod()) {
            RetracedMethodReference retracedMethod = retraceStackTraceElementProxy.getRetracedMethod();
            RetracedMethodReference retracedMethod2 = retraceStackTraceElementProxy2.getRetracedMethod();
            if (retracedMethod.isKnown() != retracedMethod2.isKnown() || !retracedMethod.getMethodName().equals(retracedMethod2.getMethodName())) {
                return false;
            }
            if (this.a && ((retracedMethod.isKnown() && !retracedMethod.asKnown().getMethodReference().toString().equals(retracedMethod2.asKnown().getMethodReference().toString())) || (!retracedMethod.isKnown() && !retracedMethod.getMethodName().equals(retracedMethod2.getMethodName())))) {
                return false;
            }
        }
        if (retraceStackTraceElementProxy.hasRetracedField() != retraceStackTraceElementProxy2.hasRetracedField()) {
            return false;
        }
        if (retraceStackTraceElementProxy.hasRetracedField()) {
            RetracedFieldReference retracedField = retraceStackTraceElementProxy.getRetracedField();
            RetracedFieldReference retracedField2 = retraceStackTraceElementProxy2.getRetracedField();
            if (retracedField.isKnown() != retracedField2.isKnown() || !retracedField.getFieldName().equals(retracedField2.getFieldName())) {
                return false;
            }
            if (this.a && ((retracedField.isKnown() && !retracedField.asKnown().getFieldReference().toString().equals(retracedField2.asKnown().getFieldReference().toString())) || (retracedField.isUnknown() && !retracedField.getFieldName().equals(retracedField2.getFieldName())))) {
                return false;
            }
        }
        if (retraceStackTraceElementProxy.hasRetracedFieldOrReturnType() != retraceStackTraceElementProxy2.hasRetracedFieldOrReturnType()) {
            return false;
        }
        if (retraceStackTraceElementProxy.hasRetracedFieldOrReturnType()) {
            RetracedTypeReference retracedFieldOrReturnType = retraceStackTraceElementProxy.getRetracedFieldOrReturnType();
            RetracedTypeReference retracedFieldOrReturnType2 = retraceStackTraceElementProxy2.getRetracedFieldOrReturnType();
            if (retracedFieldOrReturnType.isVoid() != retracedFieldOrReturnType2.isVoid() || (!retracedFieldOrReturnType.isVoid() && !retracedFieldOrReturnType.getTypeName().equals(retracedFieldOrReturnType2.getTypeName()))) {
                return false;
            }
        }
        if (retraceStackTraceElementProxy.hasRetracedMethodArguments() != retraceStackTraceElementProxy2.hasRetracedMethodArguments()) {
            return false;
        }
        if (retraceStackTraceElementProxy.hasRetracedMethodArguments()) {
            List<RetracedTypeReference> retracedMethodArguments = retraceStackTraceElementProxy.getRetracedMethodArguments();
            List<RetracedTypeReference> retracedMethodArguments2 = retraceStackTraceElementProxy2.getRetracedMethodArguments();
            if (retracedMethodArguments.size() != retracedMethodArguments2.size()) {
                return false;
            }
            for (int i = 0; i < retracedMethodArguments.size(); i++) {
                RetracedTypeReference retracedTypeReference = retracedMethodArguments.get(i);
                RetracedTypeReference retracedTypeReference2 = retracedMethodArguments2.get(i);
                if (retracedTypeReference.isVoid() == retracedTypeReference2.isVoid() && (retracedTypeReference.isVoid() || retracedTypeReference.getTypeName().equals(retracedTypeReference2.getTypeName()))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final /* bridge */ /* synthetic */ int a(Object obj) {
        return 0;
    }

    public static boolean a(RetraceStackTraceElementProxy retraceStackTraceElementProxy, RetraceStackTraceElementProxy retraceStackTraceElementProxy2, Function function, Function function2) {
        return Comparator.comparing(function).thenComparing(function2, Comparator.nullsFirst(new xei())).compare(retraceStackTraceElementProxy, retraceStackTraceElementProxy2) != 0;
    }
}
