package org.jetbrains.kotlin.ir.validation;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.validation.checkers.IrChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000f\u001a\u00020\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0010\"\u00020\u0007¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u00002\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0010\"\u00020\u0007¢\u0006\u0002\u0010\u0011J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/ir/validation/IrValidatorConfig;", "", "checkTreeConsistency", "", "checkUnboundSymbols", "checkers", "", "Lorg/jetbrains/kotlin/ir/validation/checkers/IrChecker;", "<init>", "(ZZLjava/util/Set;)V", "getCheckTreeConsistency", "()Z", "getCheckUnboundSymbols", "getCheckers", "()Ljava/util/Set;", "withCheckers", "", "([Lorg/jetbrains/kotlin/ir/validation/checkers/IrChecker;)Lorg/jetbrains/kotlin/ir/validation/IrValidatorConfig;", "withoutCheckers", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:ir.validation"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class IrValidatorConfig {
    private final boolean checkTreeConsistency;
    private final boolean checkUnboundSymbols;
    private final Set<IrChecker> checkers;

    public /* synthetic */ IrValidatorConfig(boolean z, boolean z2, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? SetsKt.emptySet() : set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IrValidatorConfig copy$default(IrValidatorConfig irValidatorConfig, boolean z, boolean z2, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            z = irValidatorConfig.checkTreeConsistency;
        }
        if ((i & 2) != 0) {
            z2 = irValidatorConfig.checkUnboundSymbols;
        }
        if ((i & 4) != 0) {
            set = irValidatorConfig.checkers;
        }
        return irValidatorConfig.copy(z, z2, set);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getCheckTreeConsistency() {
        return this.checkTreeConsistency;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getCheckUnboundSymbols() {
        return this.checkUnboundSymbols;
    }

    public final Set<IrChecker> component3() {
        return this.checkers;
    }

    public final IrValidatorConfig copy(boolean checkTreeConsistency, boolean checkUnboundSymbols, Set<? extends IrChecker> checkers) {
        checkers.getClass();
        return new IrValidatorConfig(checkTreeConsistency, checkUnboundSymbols, checkers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrValidatorConfig)) {
            return false;
        }
        IrValidatorConfig irValidatorConfig = (IrValidatorConfig) other;
        return this.checkTreeConsistency == irValidatorConfig.checkTreeConsistency && this.checkUnboundSymbols == irValidatorConfig.checkUnboundSymbols && Intrinsics.areEqual(this.checkers, irValidatorConfig.checkers);
    }

    public final boolean getCheckTreeConsistency() {
        return this.checkTreeConsistency;
    }

    public final boolean getCheckUnboundSymbols() {
        return this.checkUnboundSymbols;
    }

    public final Set<IrChecker> getCheckers() {
        return this.checkers;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.checkTreeConsistency) * 31) + Boolean.hashCode(this.checkUnboundSymbols)) * 31) + this.checkers.hashCode();
    }

    public String toString() {
        return "IrValidatorConfig(checkTreeConsistency=" + this.checkTreeConsistency + ", checkUnboundSymbols=" + this.checkUnboundSymbols + ", checkers=" + this.checkers + ')';
    }

    public final IrValidatorConfig withCheckers(IrChecker... checkers) {
        checkers.getClass();
        return copy$default(this, false, false, SetsKt.plus(this.checkers, checkers), 3, null);
    }

    public final IrValidatorConfig withoutCheckers(IrChecker... checkers) {
        checkers.getClass();
        return copy$default(this, false, false, SetsKt.minus(this.checkers, ArraysKt.toSet(checkers)), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public IrValidatorConfig(boolean z, boolean z2, Set<? extends IrChecker> set) {
        set.getClass();
        this.checkTreeConsistency = z;
        this.checkUnboundSymbols = z2;
        this.checkers = set;
    }

    public IrValidatorConfig() {
        this(false, false, null, 7, null);
    }
}
