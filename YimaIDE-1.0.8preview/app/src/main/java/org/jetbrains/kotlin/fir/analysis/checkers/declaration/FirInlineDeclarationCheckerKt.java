package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.PrivateToThisUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001\u001a\u001c\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\n"}, d2 = {"createInlineFunctionBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "parentInlineContext", "unwrapDataClassCopyWithPrimaryConstructorOrNull", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineDeclarationCheckerKt {
    public static final FirInlineDeclarationChecker.InlineFunctionBodyContext createInlineFunctionBodyContext(FirFunction firFunction, FirSession firSession, FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext) {
        firFunction.getClass();
        firSession.getClass();
        EffectiveVisibility publishedApiEffectiveVisibility = PublishedApiEffectiveVisibilityKt.getPublishedApiEffectiveVisibility(firFunction);
        if (publishedApiEffectiveVisibility == null) {
            FirDeclarationStatus status = firFunction.getStatus();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = status instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status : null;
            if (firResolvedDeclarationStatus == null || (publishedApiEffectiveVisibility = firResolvedDeclarationStatus.getEffectiveVisibility()) == null) {
                publishedApiEffectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
        }
        return new FirInlineDeclarationChecker.InlineFunctionBodyContext(firFunction, publishedApiEffectiveVisibility, firSession, inlineFunctionBodyContext);
    }

    public static final FirCallableSymbol<?> unwrapDataClassCopyWithPrimaryConstructorOrNull(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirClassSymbol<?> classSymbol;
        firBasedSymbol.getClass();
        firSession.getClass();
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        if (firCallableSymbol != null && (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firCallableSymbol)) != null && (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession)) != null) {
            if (!PrivateToThisUtilsKt.isDataClassCopy((FirCallableSymbol) firBasedSymbol, classSymbol, firSession)) {
                classSymbol = null;
            }
            if (classSymbol != null) {
                return org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(classSymbol, firSession);
            }
        }
        return null;
    }
}
