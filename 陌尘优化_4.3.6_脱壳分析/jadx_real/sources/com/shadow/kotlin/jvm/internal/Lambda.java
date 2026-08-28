package com.shadow.kotlin.jvm.internal;

import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class Lambda<R> implements kotlin.jvm.internal.FunctionBase<R>, Serializable {
    private final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    public int getArity() {
        return this.arity;
    }

    public String toString() {
        String strRenderLambdaToString = Reflection.renderLambdaToString(this);
        CloseableKt.checkNotNullExpressionValue(strRenderLambdaToString, "renderLambdaToString(...)");
        return strRenderLambdaToString;
    }
}
