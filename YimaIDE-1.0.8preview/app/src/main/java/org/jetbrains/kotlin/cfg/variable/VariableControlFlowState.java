package org.jetbrains.kotlin.cfg.variable;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000b\u001a\u00020\u0005J\u0006\u0010\f\u001a\u00020\u0005J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/cfg/variable/VariableControlFlowState;", "", "initState", "Lorg/jetbrains/kotlin/cfg/variable/InitState;", "isDeclared", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/cfg/variable/InitState;Z)V", "getInitState", "()Lorg/jetbrains/kotlin/cfg/variable/InitState;", "()Z", "definitelyInitialized", "mayBeInitialized", "toString", "", "Companion", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class VariableControlFlowState {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final VariableControlFlowState VS_EF;
    private static final VariableControlFlowState VS_ET;
    private static final VariableControlFlowState VS_IF;
    private static final VariableControlFlowState VS_IT;
    private static final VariableControlFlowState VS_NF;
    private static final VariableControlFlowState VS_NT;
    private static final VariableControlFlowState VS_UF;
    private static final VariableControlFlowState VS_UT;
    private final InitState initState;
    private final boolean isDeclared;

    static {
        InitState initState = InitState.INITIALIZED;
        VS_IT = new VariableControlFlowState(initState, true);
        VS_IF = new VariableControlFlowState(initState, false);
        InitState initState2 = InitState.INITIALIZED_EXHAUSTIVELY;
        VS_ET = new VariableControlFlowState(initState2, true);
        VS_EF = new VariableControlFlowState(initState2, false);
        InitState initState3 = InitState.UNKNOWN;
        VS_UT = new VariableControlFlowState(initState3, true);
        VS_UF = new VariableControlFlowState(initState3, false);
        InitState initState4 = InitState.NOT_INITIALIZED;
        VS_NT = new VariableControlFlowState(initState4, true);
        VS_NF = new VariableControlFlowState(initState4, false);
    }

    private VariableControlFlowState(InitState initState, boolean z) {
        this.initState = initState;
        this.isDeclared = z;
    }

    public final boolean definitelyInitialized() {
        return this.initState == InitState.INITIALIZED;
    }

    public final InitState getInitState() {
        return this.initState;
    }

    /* JADX INFO: renamed from: isDeclared, reason: from getter */
    public final boolean getIsDeclared() {
        return this.isDeclared;
    }

    public final boolean mayBeInitialized() {
        return this.initState != InitState.NOT_INITIALIZED;
    }

    public String toString() {
        if (this.initState == InitState.NOT_INITIALIZED && !this.isDeclared) {
            return "-";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.initState);
        sb.append(this.isDeclared ? "D" : "");
        return sb.toString();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cfg/variable/VariableControlFlowState$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "VS_IT", "Lorg/jetbrains/kotlin/cfg/variable/VariableControlFlowState;", "VS_IF", "VS_ET", "VS_EF", "VS_UT", "VS_UF", "VS_NT", "VS_NF", "create", "initState", "Lorg/jetbrains/kotlin/cfg/variable/InitState;", "isDeclared", "", "createInitializedExhaustively", "isInitialized", "isDeclaredHere", "mergedEdgesData", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[InitState.values().length];
                try {
                    iArr[InitState.INITIALIZED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[InitState.INITIALIZED_EXHAUSTIVELY.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[InitState.UNKNOWN.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[InitState.NOT_INITIALIZED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ VariableControlFlowState create$default(Companion companion, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z2 = false;
            }
            return companion.create(z, z2);
        }

        public final VariableControlFlowState create(InitState initState, boolean isDeclared) {
            initState.getClass();
            int i = WhenMappings.$EnumSwitchMapping$0[initState.ordinal()];
            if (i == 1) {
                return isDeclared ? VariableControlFlowState.VS_IT : VariableControlFlowState.VS_IF;
            }
            if (i == 2) {
                return isDeclared ? VariableControlFlowState.VS_ET : VariableControlFlowState.VS_EF;
            }
            if (i == 3) {
                return isDeclared ? VariableControlFlowState.VS_UT : VariableControlFlowState.VS_UF;
            }
            if (i == 4) {
                return isDeclared ? VariableControlFlowState.VS_NT : VariableControlFlowState.VS_NF;
            }
            bu8.a();
            return null;
        }

        public final VariableControlFlowState createInitializedExhaustively(boolean isDeclared) {
            return create(InitState.INITIALIZED_EXHAUSTIVELY, isDeclared);
        }

        private Companion() {
        }

        public final VariableControlFlowState create(boolean isInitialized, boolean isDeclared) {
            return create(isInitialized ? InitState.INITIALIZED : InitState.NOT_INITIALIZED, isDeclared);
        }

        public final VariableControlFlowState create(boolean isDeclaredHere, VariableControlFlowState mergedEdgesData) {
            return create(true, isDeclaredHere || (mergedEdgesData != null && mergedEdgesData.getIsDeclared()));
        }
    }
}
