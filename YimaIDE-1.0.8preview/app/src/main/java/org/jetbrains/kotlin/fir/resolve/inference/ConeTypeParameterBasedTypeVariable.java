package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeParameterBasedTypeVariable;", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "typeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;)V", "getTypeParameterSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeParameterBasedTypeVariable extends ConeTypeVariable {
    private final FirTypeParameterSymbol typeParameterSymbol;

    /* JADX WARN: Illegal instructions before constructor call */
    public ConeTypeParameterBasedTypeVariable(FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        String identifier = SpecialNames.safeIdentifier(firTypeParameterSymbol.getName()).getIdentifier();
        identifier.getClass();
        super(identifier, firTypeParameterSymbol.getLookupTag());
        this.typeParameterSymbol = firTypeParameterSymbol;
    }

    public final FirTypeParameterSymbol getTypeParameterSymbol() {
        return this.typeParameterSymbol;
    }
}
