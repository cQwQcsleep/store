package org.jetbrains.kotlin.cfg.pseudocode.instructions.eval;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0013\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cfg/pseudocode/instructions/eval/MagicKind;", "", "sideEffectFree", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;IZ)V", "getSideEffectFree", "()Z", "STRING_TEMPLATE", "AND", "OR", "NOT_NULL_ASSERTION", "EQUALS_IN_WHEN_CONDITION", "IS", "CAST", "UNBOUND_CALLABLE_REFERENCE", "BOUND_CALLABLE_REFERENCE", "LOOP_RANGE_ITERATION", "IMPLICIT_RECEIVER", "VALUE_CONSUMER", "UNRESOLVED_CALL", "UNSUPPORTED_ELEMENT", "UNRECOGNIZED_WRITE_RHS", "FAKE_INITIALIZER", "EXHAUSTIVE_WHEN_ELSE", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum MagicKind {
    STRING_TEMPLATE(true),
    AND(true),
    OR(true),
    NOT_NULL_ASSERTION(false, 1, null),
    EQUALS_IN_WHEN_CONDITION(false, 1, null),
    IS(false, 1, null),
    CAST(false, 1, null),
    UNBOUND_CALLABLE_REFERENCE(true),
    BOUND_CALLABLE_REFERENCE(true),
    LOOP_RANGE_ITERATION(false, 1, null),
    IMPLICIT_RECEIVER(false, 1, null),
    VALUE_CONSUMER(false, 1, null),
    UNRESOLVED_CALL(false, 1, null),
    UNSUPPORTED_ELEMENT(false, 1, null),
    UNRECOGNIZED_WRITE_RHS(false, 1, null),
    FAKE_INITIALIZER(false, 1, null),
    EXHAUSTIVE_WHEN_ELSE(false, 1, null);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean sideEffectFree;

    /* synthetic */ MagicKind(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public static EnumEntries<MagicKind> getEntries() {
        return $ENTRIES;
    }

    public final boolean getSideEffectFree() {
        return this.sideEffectFree;
    }

    MagicKind(boolean z) {
        this.sideEffectFree = z;
    }
}
