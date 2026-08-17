package org.jetbrains.kotlin.cfg.variable;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.backend.jvm.JvmSyntheticAccessorGenerator;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cfg/variable/InitState;", "", JvmSyntheticAccessorGenerator.SUPER_QUALIFIER_SUFFIX_MARKER, "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;ILjava/lang/String;)V", "INITIALIZED", "INITIALIZED_EXHAUSTIVELY", "UNKNOWN", "NOT_INITIALIZED", "merge", "other", "toString", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum InitState {
    INITIALIZED("I"),
    INITIALIZED_EXHAUSTIVELY("IE"),
    UNKNOWN("I?"),
    NOT_INITIALIZED("");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String s;

    InitState(String str) {
        this.s = str;
    }

    public static EnumEntries<InitState> getEntries() {
        return $ENTRIES;
    }

    public final InitState merge(InitState other) {
        InitState initState;
        other.getClass();
        if (this == other || other == (initState = INITIALIZED_EXHAUSTIVELY)) {
            return this;
        }
        return this == initState ? other : UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.s;
    }
}
