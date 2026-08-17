package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a&\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0001\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\f"}, d2 = {"isEffectivelyFinal", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "tryAccessExplicitFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirBackingFieldSymbol;", "closestPublicApiInlineFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "hasVisibleBackingField", "Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", "closestInlineFunction", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExplicitFieldsUtilsKt {
    private static final boolean isEffectivelyFinal(FirPropertySymbol firPropertySymbol, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol;
        Modality modality = firPropertySymbol.getResolvedStatus().getModality();
        Modality modality2 = Modality.FINAL;
        if (modality == modality2) {
            return true;
        }
        ConeSimpleKotlinType dispatchReceiverType = firPropertySymbol.getDispatchReceiverType();
        return (dispatchReceiverType == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(dispatchReceiverType, firSession)) == null || regularClassSymbol.getResolvedStatus().getModality() != modality2 || regularClassSymbol.getClassKind() == ClassKind.ENUM_CLASS) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirBackingFieldSymbol tryAccessExplicitFieldSymbol(FirPropertySymbol firPropertySymbol, FirFunction firFunction, FirSession firSession, boolean z) {
        FirBackingFieldSymbol symbol;
        firPropertySymbol.getClass();
        firSession.getClass();
        FirBackingField backingField = ((FirProperty) firPropertySymbol.getFir()).getBackingField();
        if (backingField != null && (symbol = backingField.getSymbol()) != null && firFunction == null && z && isEffectivelyFinal(firPropertySymbol, firSession)) {
            return symbol;
        }
        return null;
    }

    public static final FirBackingFieldSymbol tryAccessExplicitFieldSymbol(FirPropertyWithExplicitBackingFieldResolvedNamedReference firPropertyWithExplicitBackingFieldResolvedNamedReference, FirFunction firFunction, FirSession firSession) {
        firPropertyWithExplicitBackingFieldResolvedNamedReference.getClass();
        firSession.getClass();
        FirBasedSymbol<?> resolvedSymbol = firPropertyWithExplicitBackingFieldResolvedNamedReference.getResolvedSymbol();
        FirPropertySymbol firPropertySymbol = resolvedSymbol instanceof FirPropertySymbol ? (FirPropertySymbol) resolvedSymbol : null;
        if (firPropertySymbol != null) {
            return tryAccessExplicitFieldSymbol(firPropertySymbol, firFunction, firSession, firPropertyWithExplicitBackingFieldResolvedNamedReference.getHasVisibleBackingField());
        }
        return null;
    }
}
