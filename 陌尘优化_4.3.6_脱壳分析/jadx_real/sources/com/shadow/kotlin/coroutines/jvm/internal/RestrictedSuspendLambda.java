package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Reflection;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class RestrictedSuspendLambda extends kotlin.coroutines.jvm.internal.RestrictedContinuationImpl implements FunctionBase<Object> {
    private final int arity;

    public RestrictedSuspendLambda(int i, Continuation<Object> continuation) {
        super(continuation);
        this.arity = i;
    }

    public int getArity() {
        return this.arity;
    }

    public String toString() {
        if (getCompletion() != null) {
            return super.toString();
        }
        String strRenderLambdaToString = Reflection.renderLambdaToString(this);
        CloseableKt.checkNotNullExpressionValue(strRenderLambdaToString, "renderLambdaToString(...)");
        return strRenderLambdaToString;
    }

    public RestrictedSuspendLambda(int i) {
        this(i, null);
    }
}
