package org.jetbrains.kotlin.ir.interpreter.stack;

import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import org.jetbrains.kotlin.ir.interpreter.state.State;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\u0006J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/stack/DataStack;", "", "<init>", "()V", "stack", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/ir/interpreter/state/State;", "push", "", "state", "pop", "peek", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class DataStack {
    private final ArrayDeque<State> stack = new ArrayDeque<>();

    public final State peek() {
        return (State) this.stack.lastOrNull();
    }

    public final State pop() {
        return (State) this.stack.removeLast();
    }

    public final void push(State state) {
        state.getClass();
        this.stack.add(state);
    }
}
