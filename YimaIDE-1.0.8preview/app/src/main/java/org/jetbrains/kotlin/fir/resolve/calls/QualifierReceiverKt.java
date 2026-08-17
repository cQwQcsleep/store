package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a \u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"fullyExpandedClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "createQualifierReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class QualifierReceiverKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final QualifierReceiver createQualifierReceiver(FirResolvedQualifier firResolvedQualifier, FirSession firSession, ScopeSession scopeSession) {
        FirRegularClassSymbol symbol;
        firResolvedQualifier.getClass();
        firSession.getClass();
        scopeSession.getClass();
        FirClassLikeSymbol<?> symbol2 = firResolvedQualifier.getSymbol();
        if (symbol2 == null) {
            return new PackageQualifierReceiver(firResolvedQualifier, firSession);
        }
        FirRegularClass firRegularClassFullyExpandedClass = fullyExpandedClass((FirClassLikeDeclaration) symbol2.getFir(), firSession);
        if (firRegularClassFullyExpandedClass == null || (symbol = firRegularClassFullyExpandedClass.getSymbol()) == null) {
            return null;
        }
        return new ClassQualifierReceiver(firResolvedQualifier, symbol, symbol2, firSession, scopeSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClass fullyExpandedClass(FirClassLikeDeclaration firClassLikeDeclaration, FirSession firSession) {
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration2;
        firClassLikeDeclaration.getClass();
        firSession.getClass();
        if (!(firClassLikeDeclaration instanceof FirTypeAlias)) {
            if (firClassLikeDeclaration instanceof FirRegularClass) {
                return (FirRegularClass) firClassLikeDeclaration;
            }
            w04.a("Not supported: ", firClassLikeDeclaration);
            return null;
        }
        ConeClassLikeType expandedConeType = FirDeclarationUtilKt.getExpandedConeType((FirTypeAlias) firClassLikeDeclaration);
        if (expandedConeType == null || (lookupTag = expandedConeType.getLookupTag()) == null || (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, firSession)) == null || (firClassLikeDeclaration2 = (FirClassLikeDeclaration) symbol.getFir()) == null) {
            return null;
        }
        return fullyExpandedClass(firClassLikeDeclaration2, firSession);
    }
}
