package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0006"}, d2 = {"isUnitBlock", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)Z", "hasSideEffect", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnusedCheckerBaseKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean hasSideEffect(FirExpression firExpression) {
        FirExpression extensionReceiver;
        FirExpression explicitReceiver;
        firExpression.getClass();
        if ((firExpression instanceof FirLiteralExpression) || (firExpression instanceof FirClassReferenceExpression) || (firExpression instanceof FirResolvedQualifier) || (firExpression instanceof FirThisReceiverExpression) || (firExpression instanceof FirAnonymousFunctionExpression)) {
            return false;
        }
        if (firExpression instanceof FirSmartCastExpression) {
            return hasSideEffect(((FirSmartCastExpression) firExpression).getOriginalExpression());
        }
        if (firExpression instanceof FirCallableReferenceAccess) {
            FirCallableReferenceAccess firCallableReferenceAccess = (FirCallableReferenceAccess) firExpression;
            FirExpression dispatchReceiver = firCallableReferenceAccess.getDispatchReceiver();
            return (dispatchReceiver != null && hasSideEffect(dispatchReceiver)) || ((extensionReceiver = firCallableReferenceAccess.getExtensionReceiver()) != null && hasSideEffect(extensionReceiver)) || ((explicitReceiver = firCallableReferenceAccess.getExplicitReceiver()) != null && hasSideEffect(explicitReceiver));
        }
        if ((firExpression instanceof FirStringConcatenationCall) || (firExpression instanceof FirGetClassCall)) {
            List<FirExpression> arguments = ((FirCall) firExpression).getArgumentList().getArguments();
            if ((arguments instanceof Collection) && arguments.isEmpty()) {
                return false;
            }
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                if (hasSideEffect((FirExpression) it.next())) {
                    return true;
                }
            }
            return false;
        }
        if (!(firExpression instanceof FirPropertyAccessExpression)) {
            return true;
        }
        FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
        KtSourceElement source = firPropertyAccessExpression.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE)) {
            return true;
        }
        FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(firPropertyAccessExpression.getCalleeReference());
        if ((symbol instanceof FirValueParameterSymbol) || (symbol instanceof FirReceiverParameterSymbol)) {
            return false;
        }
        if (symbol instanceof FirLocalPropertySymbol) {
            return ((FirLocalPropertySymbol) symbol).getHasDelegate();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isUnitBlock(FirStatement firStatement) {
        FirSingleExpressionBlock firSingleExpressionBlock = firStatement instanceof FirSingleExpressionBlock ? (FirSingleExpressionBlock) firStatement : null;
        if (firSingleExpressionBlock == null) {
            return false;
        }
        FirStatement statement = firSingleExpressionBlock.getStatement();
        FirResolvedQualifier firResolvedQualifier = statement instanceof FirResolvedQualifier ? (FirResolvedQualifier) statement : null;
        if (firResolvedQualifier == null) {
            return false;
        }
        return Intrinsics.areEqual(firResolvedQualifier.getClassId(), StandardClassIds.INSTANCE.getUnit());
    }
}
