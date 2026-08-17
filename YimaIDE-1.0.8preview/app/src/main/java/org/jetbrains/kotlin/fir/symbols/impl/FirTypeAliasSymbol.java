package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.mpp.TypeAliasSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/mpp/TypeAliasSymbolMarker;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "resolvedExpandedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getResolvedExpandedTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeAliasSymbol extends FirClassLikeSymbol<FirTypeAlias> implements TypeAliasSymbolMarker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirTypeAliasSymbol(ClassId classId) {
        super(classId, null);
        classId.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirResolvedTypeRef getResolvedExpandedTypeRef() {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.SUPER_TYPES);
        FirResolvedTypeRef expandedTypeRef = ((FirTypeAlias) getFir()).getExpandedTypeRef();
        expandedTypeRef.getClass();
        return expandedTypeRef;
    }
}
