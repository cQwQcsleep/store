package org.jetbrains.kotlin.config.phaser;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0019\u0010\u0016\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005j\u0002`\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005j\u0002`\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\bHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR!\u0010\u0004\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/config/phaser/ActionState;", "", "config", "Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "phase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/config/phaser/AnyNamedPhase;", "phaseCount", "", "beforeOrAfter", "Lorg/jetbrains/kotlin/config/phaser/BeforeOrAfter;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;ILorg/jetbrains/kotlin/config/phaser/BeforeOrAfter;)V", "getConfig", "()Lorg/jetbrains/kotlin/config/phaser/PhaseConfig;", "getPhase", "()Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "getPhaseCount", "()I", "getBeforeOrAfter", "()Lorg/jetbrains/kotlin/config/phaser/BeforeOrAfter;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionState {
    private final BeforeOrAfter beforeOrAfter;
    private final PhaseConfig config;
    private final NamedCompilerPhase<?, ?, ?> phase;
    private final int phaseCount;

    public ActionState(PhaseConfig phaseConfig, NamedCompilerPhase<?, ?, ?> namedCompilerPhase, int i, BeforeOrAfter beforeOrAfter) {
        phaseConfig.getClass();
        namedCompilerPhase.getClass();
        beforeOrAfter.getClass();
        this.config = phaseConfig;
        this.phase = namedCompilerPhase;
        this.phaseCount = i;
        this.beforeOrAfter = beforeOrAfter;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionState copy$default(ActionState actionState, PhaseConfig phaseConfig, NamedCompilerPhase namedCompilerPhase, int i, BeforeOrAfter beforeOrAfter, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            phaseConfig = actionState.config;
        }
        if ((i2 & 2) != 0) {
            namedCompilerPhase = actionState.phase;
        }
        if ((i2 & 4) != 0) {
            i = actionState.phaseCount;
        }
        if ((i2 & 8) != 0) {
            beforeOrAfter = actionState.beforeOrAfter;
        }
        return actionState.copy(phaseConfig, namedCompilerPhase, i, beforeOrAfter);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PhaseConfig getConfig() {
        return this.config;
    }

    public final NamedCompilerPhase<?, ?, ?> component2() {
        return this.phase;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getPhaseCount() {
        return this.phaseCount;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final BeforeOrAfter getBeforeOrAfter() {
        return this.beforeOrAfter;
    }

    public final ActionState copy(PhaseConfig config, NamedCompilerPhase<?, ?, ?> phase, int phaseCount, BeforeOrAfter beforeOrAfter) {
        config.getClass();
        phase.getClass();
        beforeOrAfter.getClass();
        return new ActionState(config, phase, phaseCount, beforeOrAfter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionState)) {
            return false;
        }
        ActionState actionState = (ActionState) other;
        return Intrinsics.areEqual(this.config, actionState.config) && Intrinsics.areEqual(this.phase, actionState.phase) && this.phaseCount == actionState.phaseCount && this.beforeOrAfter == actionState.beforeOrAfter;
    }

    public final BeforeOrAfter getBeforeOrAfter() {
        return this.beforeOrAfter;
    }

    public final PhaseConfig getConfig() {
        return this.config;
    }

    public final NamedCompilerPhase<?, ?, ?> getPhase() {
        return this.phase;
    }

    public final int getPhaseCount() {
        return this.phaseCount;
    }

    public int hashCode() {
        return (((((this.config.hashCode() * 31) + this.phase.hashCode()) * 31) + Integer.hashCode(this.phaseCount)) * 31) + this.beforeOrAfter.hashCode();
    }

    public String toString() {
        return "ActionState(config=" + this.config + ", phase=" + this.phase + ", phaseCount=" + this.phaseCount + ", beforeOrAfter=" + this.beforeOrAfter + Util.C_PARAM_END;
    }
}
