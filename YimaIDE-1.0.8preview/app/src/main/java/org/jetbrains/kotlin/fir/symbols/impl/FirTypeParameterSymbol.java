package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.mpp.TypeParameterSymbolMarker;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\u000bH\u0016J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001aR\u0015\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/mpp/TypeParameterSymbolMarker;", "<init>", "()V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "lookupTag", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "toLookupTag", "toString", Argument.Delimiters.none, "resolvedBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getResolvedBounds", "()Ljava/util/List;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "isReified", Argument.Delimiters.none, "()Z", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeParameterSymbol extends FirClassifierSymbol<FirTypeParameter> implements TypeParameterSymbolMarker {
    private final ConeTypeParameterLookupTag lookupTag;

    public FirTypeParameterSymbol() {
        super(null);
        this.lookupTag = new ConeTypeParameterLookupTag(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return ((FirTypeParameter) getFir()).getContainingDeclarationSymbol();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Name getName() {
        return ((FirTypeParameter) getFir()).getName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FirResolvedTypeRef> getResolvedBounds() {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(this, FirResolvePhase.TYPES);
        List<FirTypeRef> bounds = ((FirTypeParameter) getFir()).getBounds();
        bounds.getClass();
        return bounds;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Variance getVariance() {
        return ((FirTypeParameter) getFir()).getVariance();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isReified() {
        return ((FirTypeParameter) getFir()).isReified();
    }

    public String toString() {
        if (!isBound()) {
            return Reflection.getOrCreateKotlinClass(FirTypeParameterSymbol.class).getSimpleName() + " <unbound>";
        }
        return Reflection.getOrCreateKotlinClass(FirTypeParameterSymbol.class).getSimpleName() + ' ' + getName().asString();
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol
    /* JADX INFO: renamed from: toLookupTag, reason: from getter */
    public ConeTypeParameterLookupTag getLookupTag() {
        return this.lookupTag;
    }
}
