package com.shadow.kotlin.coroutines.jvm.internal;

import com.shadow.kotlin.Result;
import com.shadow.kotlin.coroutines.CoroutineContext;
import com.shadow.kotlin.coroutines.intrinsics.CoroutineSingletons;
import com.shadow.kotlin.io.CloseableKt;
import java.io.Serializable;
import java.lang.reflect.Field;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* loaded from: /workspace/unpacked/classes2.dex */
public abstract class BaseContinuationImpl implements Continuation<Object>, kotlin.coroutines.jvm.internal.CoroutineStackFrame, Serializable {
    private final Continuation<Object> completion;

    public BaseContinuationImpl(Continuation<Object> continuation) {
        this.completion = continuation;
    }

    public Continuation<Unit> create(Continuation<?> continuation) {
        CloseableKt.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    public CoroutineStackFrame getCallerFrame() {
        CoroutineStackFrame coroutineStackFrame = this.completion;
        if (coroutineStackFrame instanceof CoroutineStackFrame) {
            return coroutineStackFrame;
        }
        return null;
    }

    public final Continuation<Object> getCompletion() {
        return this.completion;
    }

    public abstract /* synthetic */ CoroutineContext getContext();

    public StackTraceElement getStackTraceElement() throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        int iIntValue;
        String strC;
        DebugMetadata debugMetadata = (DebugMetadata) getClass().getAnnotation(DebugMetadata.class);
        if (debugMetadata == null) {
            return null;
        }
        int iV = debugMetadata.v();
        if (iV > 1) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: 1, got " + iV + ". Please update the Kotlin standard library.").toString());
        }
        try {
            Field declaredField = getClass().getDeclaredField("label");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this);
            Integer num = obj instanceof Integer ? (Integer) obj : null;
            iIntValue = (num != null ? num.intValue() : 0) - 1;
        } catch (Exception unused) {
            iIntValue = -1;
        }
        int i = iIntValue >= 0 ? debugMetadata.l()[iIntValue] : -1;
        String moduleName = ModuleNameRetriever.getModuleName(this);
        if (moduleName == null) {
            strC = debugMetadata.c();
        } else {
            strC = moduleName + '/' + debugMetadata.c();
        }
        return new StackTraceElement(strC, debugMetadata.m(), debugMetadata.f(), i);
    }

    public abstract Object invokeSuspend(Object obj);

    public void releaseIntercepted() {
    }

    public final void resumeWith(Object obj) {
        Object objInvokeSuspend;
        BaseContinuationImpl baseContinuationImpl = this;
        while (true) {
            BaseContinuationImpl baseContinuationImpl2 = baseContinuationImpl;
            BaseContinuationImpl baseContinuationImpl3 = baseContinuationImpl2.completion;
            CloseableKt.checkNotNull(baseContinuationImpl3);
            try {
                objInvokeSuspend = baseContinuationImpl2.invokeSuspend(obj);
            } catch (Throwable th) {
                obj = Result.m1constructorimpl(new Result.Failure(th));
            }
            if (objInvokeSuspend == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return;
            }
            obj = Result.m1constructorimpl(objInvokeSuspend);
            baseContinuationImpl2.releaseIntercepted();
            if (!(baseContinuationImpl3 instanceof BaseContinuationImpl)) {
                baseContinuationImpl3.resumeWith(obj);
                return;
            }
            baseContinuationImpl = baseContinuationImpl3;
        }
    }

    public String toString() throws IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException {
        StringBuilder sb = new StringBuilder("Continuation at ");
        Object stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    public Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CloseableKt.checkNotNullParameter(continuation, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }
}
