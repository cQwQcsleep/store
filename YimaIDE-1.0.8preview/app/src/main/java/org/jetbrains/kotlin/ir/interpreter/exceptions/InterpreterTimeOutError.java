package org.jetbrains.kotlin.ir.interpreter.exceptions;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/exceptions/InterpreterTimeOutError;", "Lorg/jetbrains/kotlin/ir/interpreter/exceptions/InterpreterError;", "<init>", "()V", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InterpreterTimeOutError extends InterpreterError {
    public InterpreterTimeOutError() {
        super("Exceeded execution limit of constexpr expression");
    }
}
