package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirInaccessibleReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSmartCastExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001¨\u0006\u0002"}, d2 = {"copyImplicitValueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImplicitValueKt {
    public static final FirExpression copyImplicitValueExpression(FirExpression firExpression) {
        firExpression.getClass();
        if (firExpression instanceof FirPropertyAccessExpression) {
            FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
            FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
            firPropertyAccessExpressionBuilder.setConeTypeOrNull(firPropertyAccessExpression.getConeTypeOrNull());
            firPropertyAccessExpressionBuilder.getAnnotations().addAll(firPropertyAccessExpression.getAnnotations());
            firPropertyAccessExpressionBuilder.getContextArguments().addAll(firPropertyAccessExpression.getContextArguments());
            firPropertyAccessExpressionBuilder.getTypeArguments().addAll(firPropertyAccessExpression.getTypeArguments());
            firPropertyAccessExpressionBuilder.setExplicitReceiver(firPropertyAccessExpression.getExplicitReceiver());
            firPropertyAccessExpressionBuilder.setDispatchReceiver(firPropertyAccessExpression.getDispatchReceiver());
            firPropertyAccessExpressionBuilder.setExtensionReceiver(firPropertyAccessExpression.getExtensionReceiver());
            firPropertyAccessExpressionBuilder.setSource(firPropertyAccessExpression.getSource());
            firPropertyAccessExpressionBuilder.getNonFatalDiagnostics().addAll(firPropertyAccessExpression.getNonFatalDiagnostics());
            firPropertyAccessExpressionBuilder.setContextSensitiveAlternative(firPropertyAccessExpression.getContextSensitiveAlternative());
            firPropertyAccessExpressionBuilder.setCalleeReference(firPropertyAccessExpression.getCalleeReference());
            return firPropertyAccessExpressionBuilder.mo289build();
        }
        if (firExpression instanceof FirThisReceiverExpression) {
            FirThisReceiverExpression firThisReceiverExpression = (FirThisReceiverExpression) firExpression;
            FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
            firThisReceiverExpressionBuilder.setConeTypeOrNull(firThisReceiverExpression.getConeTypeOrNull());
            firThisReceiverExpressionBuilder.getAnnotations().addAll(firThisReceiverExpression.getAnnotations());
            firThisReceiverExpressionBuilder.getTypeArguments().addAll(firThisReceiverExpression.getTypeArguments());
            firThisReceiverExpressionBuilder.setSource(firThisReceiverExpression.getSource());
            firThisReceiverExpressionBuilder.getNonFatalDiagnostics().addAll(firThisReceiverExpression.getNonFatalDiagnostics());
            firThisReceiverExpressionBuilder.setCalleeReference(firThisReceiverExpression.getCalleeReference());
            firThisReceiverExpressionBuilder.setImplicit(firThisReceiverExpression.getIsImplicit());
            return firThisReceiverExpressionBuilder.mo289build();
        }
        if (firExpression instanceof FirInaccessibleReceiverExpression) {
            FirInaccessibleReceiverExpression firInaccessibleReceiverExpression = (FirInaccessibleReceiverExpression) firExpression;
            FirInaccessibleReceiverExpressionBuilder firInaccessibleReceiverExpressionBuilder = new FirInaccessibleReceiverExpressionBuilder();
            firInaccessibleReceiverExpressionBuilder.setSource(firInaccessibleReceiverExpression.getSource());
            firInaccessibleReceiverExpressionBuilder.setConeTypeOrNull(firInaccessibleReceiverExpression.getConeTypeOrNull());
            firInaccessibleReceiverExpressionBuilder.getAnnotations().addAll(firInaccessibleReceiverExpression.getAnnotations());
            firInaccessibleReceiverExpressionBuilder.setCalleeReference(firInaccessibleReceiverExpression.getCalleeReference());
            firInaccessibleReceiverExpressionBuilder.setKind(firInaccessibleReceiverExpression.getKind());
            return firInaccessibleReceiverExpressionBuilder.mo289build();
        }
        if (!(firExpression instanceof FirSmartCastExpression)) {
            c2f.a("Unexpected expression type '", firExpression.getClass().getSimpleName(), 39);
            return null;
        }
        FirSmartCastExpression firSmartCastExpression = (FirSmartCastExpression) firExpression;
        FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
        firSmartCastExpressionBuilder.setConeTypeOrNull(firSmartCastExpression.getConeTypeOrNull());
        firSmartCastExpressionBuilder.getAnnotations().addAll(firSmartCastExpression.getAnnotations());
        firSmartCastExpressionBuilder.setOriginalExpression(firSmartCastExpression.getOriginalExpression());
        firSmartCastExpressionBuilder.setUpperTypesFromSmartCast(firSmartCastExpression.getUpperTypesFromSmartCast());
        firSmartCastExpressionBuilder.setLowerTypesFromSmartCast(firSmartCastExpression.getLowerTypesFromSmartCast());
        firSmartCastExpressionBuilder.setSmartcastType(firSmartCastExpression.getSmartcastType());
        firSmartCastExpressionBuilder.setSmartcastTypeWithoutNullableNothing(firSmartCastExpression.getSmartcastTypeWithoutNullableNothing());
        firSmartCastExpressionBuilder.setSmartcastStability(firSmartCastExpression.getSmartcastStability());
        return firSmartCastExpressionBuilder.mo289build();
    }
}
