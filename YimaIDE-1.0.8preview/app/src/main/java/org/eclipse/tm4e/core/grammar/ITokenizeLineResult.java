package org.eclipse.tm4e.core.grammar;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ITokenizeLineResult<T> {
    IStateStack getRuleStack();

    T getTokens();

    boolean isStoppedEarly();
}
