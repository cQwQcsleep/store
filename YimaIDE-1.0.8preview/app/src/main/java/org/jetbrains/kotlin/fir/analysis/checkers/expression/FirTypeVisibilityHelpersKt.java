package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"isTypeVisibilityBroken", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "checkTypeArguments", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)Z", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeVisibilityHelpersKt {
    public static final boolean isTypeVisibilityBroken(CheckerContext checkerContext, ConeKotlinType coneKotlinType, boolean z) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, coneKotlinType);
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (classSymbol != null && containingFileSymbol != null) {
            if (!FirVisibilityCheckerKt.isClassLikeVisible(visibilityChecker, classSymbol, checkerContext.getSession(), containingFileSymbol, checkerContext.getContainingDeclarations())) {
                return true;
            }
            if (z) {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                    if ((coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) && isTypeVisibilityBroken(checkerContext, coneKotlinTypeProjection.getType(), true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
