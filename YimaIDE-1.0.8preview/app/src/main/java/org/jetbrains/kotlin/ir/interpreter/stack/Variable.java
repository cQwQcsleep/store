package org.jetbrains.kotlin.ir.interpreter.stack;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.interpreter.state.State;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\n\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/stack/Variable;", "", "state", "Lorg/jetbrains/kotlin/ir/interpreter/state/State;", "<init>", "(Lorg/jetbrains/kotlin/ir/interpreter/state/State;)V", "getState", "()Lorg/jetbrains/kotlin/ir/interpreter/state/State;", "setState", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class Variable {
    private State state;

    public Variable(State state) {
        this.state = state;
    }

    public static /* synthetic */ Variable copy$default(Variable variable, State state, int i, Object obj) {
        if ((i & 1) != 0) {
            state = variable.state;
        }
        return variable.copy(state);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final State getState() {
        return this.state;
    }

    public final Variable copy(State state) {
        return new Variable(state);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof Variable) && Intrinsics.areEqual(this.state, ((Variable) other).state);
    }

    public final State getState() {
        return this.state;
    }

    public int hashCode() {
        State state = this.state;
        if (state == null) {
            return 0;
        }
        return state.hashCode();
    }

    public final void setState(State state) {
        this.state = state;
    }

    public String toString() {
        return "Variable(state=" + this.state + ')';
    }
}
