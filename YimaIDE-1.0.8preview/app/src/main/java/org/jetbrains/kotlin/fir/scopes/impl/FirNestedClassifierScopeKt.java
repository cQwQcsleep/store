package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"toConeType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeParameterType;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "isNullable", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedClassifierScopeKt {
    public static final ConeTypeParameterType toConeType(FirTypeParameterSymbol firTypeParameterSymbol, boolean z) {
        firTypeParameterSymbol.getClass();
        return new ConeTypeParameterTypeImpl(new ConeTypeParameterLookupTag(firTypeParameterSymbol), z, null, 4, null);
    }

    public static final ConeTypeParameterType toConeType(FirTypeParameterSymbol firTypeParameterSymbol) {
        firTypeParameterSymbol.getClass();
        return toConeType(firTypeParameterSymbol, false);
    }

    public static final ConeTypeParameterType toConeType(FirTypeParameterRef firTypeParameterRef) {
        firTypeParameterRef.getClass();
        return toConeType(firTypeParameterRef.getSymbol());
    }
}
