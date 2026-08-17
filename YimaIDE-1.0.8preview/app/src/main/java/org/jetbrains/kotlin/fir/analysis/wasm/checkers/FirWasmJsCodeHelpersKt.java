package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0005\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0007H\u0002\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\tH\u0002¨\u0006\n"}, d2 = {"hasValidJsCodeBody", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isValidJsCodeBody", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "isJsCodeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:checkers.wasm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsCodeHelpersKt {
    public static final boolean hasValidJsCodeBody(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        FirBlock body = firNamedFunction.getBody();
        return body != null && isValidJsCodeBody(body);
    }

    private static final boolean isJsCodeCall(FirExpression firExpression) {
        FirCallableSymbol resolvedCallableSymbol$default;
        if ((firExpression instanceof FirFunctionCall) && (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(((FirFunctionCall) firExpression).getCalleeReference(), false, 1, null)) != null) {
            return Intrinsics.areEqual(resolvedCallableSymbol$default.getCallableId(), WebCommonStandardClassIds.Callables.Js);
        }
        return false;
    }

    private static final boolean isValidJsCodeBody(FirBlock firBlock) {
        FirStatement firStatement = (FirStatement) CollectionsKt.singleOrNull(firBlock.getStatements());
        if (firStatement == null) {
            return false;
        }
        if (firStatement instanceof FirFunctionCall) {
            return isJsCodeCall((FirExpression) firStatement);
        }
        if ((firStatement instanceof FirReturnExpression) && (firBlock instanceof FirSingleExpressionBlock)) {
            return isJsCodeCall(((FirReturnExpression) firStatement).getResult());
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean hasValidJsCodeBody(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return hasValidJsCodeBody((FirNamedFunction) firNamedFunctionSymbol.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean hasValidJsCodeBody(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        return hasValidJsCodeBody((FirProperty) firPropertySymbol.getFir());
    }

    public static final boolean hasValidJsCodeBody(FirProperty firProperty) {
        firProperty.getClass();
        FirExpression initializer = firProperty.getInitializer();
        return initializer != null && isJsCodeCall(initializer);
    }
}
