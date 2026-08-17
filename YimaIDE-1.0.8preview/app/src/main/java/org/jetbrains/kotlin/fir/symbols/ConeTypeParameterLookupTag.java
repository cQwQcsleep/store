package org.jetbrains.kotlin.fir.symbols;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "Lorg/jetbrains/kotlin/fir/symbols/ConeClassifierLookupTagWithFixedSymbol;", "Lorg/jetbrains/kotlin/types/model/TypeParameterMarker;", "typeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "getTypeParameterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "symbol", "getSymbol", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ConeTypeParameterLookupTag extends ConeClassifierLookupTagWithFixedSymbol implements TypeParameterMarker {
    private final FirTypeParameterSymbol typeParameterSymbol;

    public ConeTypeParameterLookupTag(FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        this.typeParameterSymbol = firTypeParameterSymbol;
    }

    public static /* synthetic */ ConeTypeParameterLookupTag copy$default(ConeTypeParameterLookupTag coneTypeParameterLookupTag, FirTypeParameterSymbol firTypeParameterSymbol, int i, Object obj) {
        if ((i & 1) != 0) {
            firTypeParameterSymbol = coneTypeParameterLookupTag.typeParameterSymbol;
        }
        return coneTypeParameterLookupTag.copy(firTypeParameterSymbol);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirTypeParameterSymbol getTypeParameterSymbol() {
        return this.typeParameterSymbol;
    }

    public final ConeTypeParameterLookupTag copy(FirTypeParameterSymbol typeParameterSymbol) {
        typeParameterSymbol.getClass();
        return new ConeTypeParameterLookupTag(typeParameterSymbol);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConeTypeParameterLookupTag) && Intrinsics.areEqual(this.typeParameterSymbol, ((ConeTypeParameterLookupTag) other).typeParameterSymbol);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag
    public Name getName() {
        return this.typeParameterSymbol.getName();
    }

    public final FirTypeParameterSymbol getTypeParameterSymbol() {
        return this.typeParameterSymbol;
    }

    public int hashCode() {
        return this.typeParameterSymbol.hashCode();
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag
    public String toString() {
        return "ConeTypeParameterLookupTag(typeParameterSymbol=" + this.typeParameterSymbol + ')';
    }

    @Override // org.jetbrains.kotlin.fir.symbols.ConeClassifierLookupTagWithFixedSymbol
    public FirTypeParameterSymbol getSymbol() {
        return this.typeParameterSymbol;
    }
}
