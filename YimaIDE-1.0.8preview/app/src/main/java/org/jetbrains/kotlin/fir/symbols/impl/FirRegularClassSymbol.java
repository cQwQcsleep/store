package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.mpp.RegularClassSymbolMarker;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0004\u0018\u00010\u00008FX\u0087\u0004r\u0002\b\r¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00008F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/mpp/RegularClassSymbolMarker;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "companionObjectSymbol", "getCompanionObjectSymbol$annotations", "()V", "getCompanionObjectSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "resolvedCompanionObjectSymbol", "getResolvedCompanionObjectSymbol", "resolvedContextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getResolvedContextParameters", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRegularClassSymbol extends FirClassSymbol<FirRegularClass> implements RegularClassSymbolMarker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirRegularClassSymbol(ClassId classId) {
        super(classId, null);
        classId.getClass();
    }

    @SymbolInternals
    public static /* synthetic */ void getCompanionObjectSymbol$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirRegularClassSymbol getCompanionObjectSymbol() {
        return ((FirRegularClass) getFir()).getCompanionObjectSymbol();
    }

    public final FirRegularClassSymbol getResolvedCompanionObjectSymbol() {
        FirRegularClassSymbol companionObjectSymbol = getCompanionObjectSymbol();
        if (companionObjectSymbol != null) {
            return companionObjectSymbol;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.COMPANION_GENERATION);
        return getCompanionObjectSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirValueParameter> getResolvedContextParameters() {
        if (((FirRegularClass) getFir()).getContextParameters().isEmpty()) {
            return CollectionsKt.emptyList();
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.TYPES);
        return ((FirRegularClass) getFir()).getContextParameters();
    }
}
