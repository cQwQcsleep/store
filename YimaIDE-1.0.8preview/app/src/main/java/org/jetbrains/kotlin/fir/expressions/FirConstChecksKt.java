package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a(\u0010\u0003\u001a\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001\u001a \u0010\n\u001a\u00020\u000b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0001¨\u0006\f"}, d2 = {"canBeUsedForConstVal", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "canBeEvaluatedAtCompileTime", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "allowErrors", "calledOnCheckerStage", "computeConstantExpressionKind", "Lorg/jetbrains/kotlin/fir/expressions/ConstantArgumentKind;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstChecksKt {
    public static final boolean canBeEvaluatedAtCompileTime(FirExpression firExpression, FirSession firSession, boolean z, boolean z2) {
        firSession.getClass();
        ConstantArgumentKind constantArgumentKindComputeConstantExpressionKind = computeConstantExpressionKind(firExpression, firSession, z2);
        if (constantArgumentKindComputeConstantExpressionKind != ConstantArgumentKind.VALID_CONST) {
            return z && constantArgumentKindComputeConstantExpressionKind == ConstantArgumentKind.RESOLUTION_ERROR;
        }
        return true;
    }

    public static final boolean canBeUsedForConstVal(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        return ConeBuiltinTypeUtilsKt.isPrimitive(coneRigidTypeLowerBoundIfFlexible) || ConeBuiltinTypeUtilsKt.isString(coneRigidTypeLowerBoundIfFlexible) || ConeBuiltinTypeUtilsKt.isUnsignedType(coneRigidTypeLowerBoundIfFlexible);
    }

    public static final ConstantArgumentKind computeConstantExpressionKind(FirExpression firExpression, FirSession firSession, boolean z) {
        firSession.getClass();
        return firExpression == null ? ConstantArgumentKind.RESOLUTION_ERROR : (ConstantArgumentKind) firExpression.accept(new FirConstCheckVisitor(firSession, z), null);
    }
}
