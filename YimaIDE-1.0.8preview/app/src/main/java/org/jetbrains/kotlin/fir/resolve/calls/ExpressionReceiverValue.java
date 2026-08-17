package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u00020\u000ej\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "receiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getReceiverExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "c", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpressionReceiverValue implements ReceiverValue {
    private final FirExpression receiverExpression;

    public ExpressionReceiverValue(FirExpression firExpression) {
        firExpression.getClass();
        this.receiverExpression = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue
    public FirExpression getReceiverExpression() {
        return this.receiverExpression;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue
    public ConeKotlinType getType() {
        return FirTypeUtilsKt.getResolvedType(getReceiverExpression());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue
    public FirTypeScope scope(SessionAndScopeSessionHolder sessionAndScopeSessionHolder) {
        sessionAndScopeSessionHolder.getClass();
        FirAnnotationContainer receiverExpression = getReceiverExpression();
        if (receiverExpression instanceof FirCheckNotNullCall) {
            receiverExpression = (FirExpression) CollectionsKt.firstOrNull(((FirCall) receiverExpression).getArgumentList().getArguments());
        }
        return receiverExpression instanceof FirSmartCastExpression ? ScopeUtilsKt.smartcastScope(sessionAndScopeSessionHolder, (FirSmartCastExpression) receiverExpression, FirResolvePhase.STATUS) : ScopeUtilsKt.scope(sessionAndScopeSessionHolder, getType(), CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
    }
}
