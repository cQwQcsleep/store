package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/SyntheticVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "fir", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "originalType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getOriginalType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SyntheticVariable extends DataFlowVariable {
    private final FirExpression fir;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyntheticVariable(FirExpression firExpression) {
        super(null);
        firExpression.getClass();
        this.fir = firExpression;
    }

    public static /* synthetic */ SyntheticVariable copy$default(SyntheticVariable syntheticVariable, FirExpression firExpression, int i, Object obj) {
        if ((i & 1) != 0) {
            firExpression = syntheticVariable.fir;
        }
        return syntheticVariable.copy(firExpression);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirExpression getFir() {
        return this.fir;
    }

    public final SyntheticVariable copy(FirExpression fir) {
        fir.getClass();
        return new SyntheticVariable(fir);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SyntheticVariable) && Intrinsics.areEqual(this.fir, ((SyntheticVariable) other).fir);
    }

    public final FirExpression getFir() {
        return this.fir;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.DataFlowVariable
    public ConeKotlinType getOriginalType() {
        return FirTypeUtilsKt.getResolvedType(this.fir);
    }

    public int hashCode() {
        return this.fir.hashCode();
    }

    public String toString() {
        return "SyntheticVariable(fir=" + this.fir + ')';
    }
}
