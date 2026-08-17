package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.StackTraceElementProxy;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RetraceStackTraceElementProxy<T, ST extends StackTraceElementProxy<T, ST>> extends Comparable<RetraceStackTraceElementProxy<T, ST>> {
    RetraceStackTraceContext getContext();

    int getLineNumber();

    ST getOriginalItem();

    RetracedClassReference getRetracedClass();

    RetracedFieldReference getRetracedField();

    RetracedTypeReference getRetracedFieldOrReturnType();

    RetracedMethodReference getRetracedMethod();

    List<RetracedTypeReference> getRetracedMethodArguments();

    RetracedSourceFile getRetracedSourceFile();

    String getSourceFile();

    boolean hasLineNumber();

    boolean hasRetracedClass();

    boolean hasRetracedField();

    boolean hasRetracedFieldOrReturnType();

    boolean hasRetracedMethod();

    boolean hasRetracedMethodArguments();

    boolean hasSourceFile();

    boolean isAmbiguous();

    boolean isTopFrame();
}
