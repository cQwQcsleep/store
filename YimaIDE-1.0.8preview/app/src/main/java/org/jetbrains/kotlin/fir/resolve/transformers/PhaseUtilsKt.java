package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"ensureResolvedTypeDeclaration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "requiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PhaseUtilsKt {
    public static final void ensureResolvedTypeDeclaration(ConeKotlinType coneKotlinType, FirSession firSession, FirResolvePhase firResolvePhase) {
        firSession.getClass();
        firResolvePhase.getClass();
        if (coneKotlinType instanceof ConeClassLikeType) {
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), firSession);
            if (symbol != null) {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, firResolvePhase);
            }
            FirClassLikeSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null).getLookupTag(), firSession);
            if (symbol2 != null) {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol2, firResolvePhase);
            }
        }
    }

    public static /* synthetic */ void ensureResolvedTypeDeclaration$default(ConeKotlinType coneKotlinType, FirSession firSession, FirResolvePhase firResolvePhase, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.INSTANCE.getDECLARATIONS();
        }
        ensureResolvedTypeDeclaration(coneKotlinType, firSession, firResolvePhase);
    }

    public static /* synthetic */ void ensureResolvedTypeDeclaration$default(FirTypeRef firTypeRef, FirSession firSession, FirResolvePhase firResolvePhase, int i, Object obj) {
        if ((i & 2) != 0) {
            firResolvePhase = FirResolvePhase.INSTANCE.getDECLARATIONS();
        }
        ensureResolvedTypeDeclaration(firTypeRef, firSession, firResolvePhase);
    }

    public static final void ensureResolvedTypeDeclaration(FirTypeRef firTypeRef, FirSession firSession, FirResolvePhase firResolvePhase) {
        firTypeRef.getClass();
        firSession.getClass();
        firResolvePhase.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ensureResolvedTypeDeclaration(coneType != null ? coneType : null, firSession, firResolvePhase);
    }
}
