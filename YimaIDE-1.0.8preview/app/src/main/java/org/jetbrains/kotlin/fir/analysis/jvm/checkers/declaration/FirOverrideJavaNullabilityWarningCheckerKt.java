package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedForWarningConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a'\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006\u001a'\u0010\u0000\u001a\u0004\u0018\u00010\u0007*\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\b\u001a'\u0010\u0000\u001a\u0004\u0018\u00010\t*\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\n\u001a'\u0010\u0000\u001a\u0004\u0018\u00010\u000b*\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"substituteOrNull", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "substitutor", "Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;)Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;)Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverrideJavaNullabilityWarningCheckerKt {
    private static final FirNamedFunction substituteOrNull(CheckerContext checkerContext, FirNamedFunction firNamedFunction, EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor) {
        boolean z;
        ConeKotlinType coneKotlinType;
        ConeKotlinType coneKotlinType2;
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firNamedFunction.getSymbol(), FirResolvePhase.TYPES);
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        boolean z2 = false;
        while (true) {
            z = true;
            ConeKotlinType coneKotlinType3 = null;
            if (!it.hasNext()) {
                break;
            }
            ConeKotlinType coneKotlinTypeSubstituteOrNull2 = enhancedForWarningConeSubstitutor.substituteOrNull(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
            if (coneKotlinTypeSubstituteOrNull2 != null) {
                coneKotlinType3 = coneKotlinTypeSubstituteOrNull2;
                z2 = true;
            }
            arrayList.add(coneKotlinType3);
        }
        List<FirValueParameter> contextParameters = firNamedFunction.getContextParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it2 = contextParameters.iterator();
        while (it2.hasNext()) {
            ConeKotlinType coneKotlinTypeSubstituteOrNull3 = enhancedForWarningConeSubstitutor.substituteOrNull(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef()));
            if (coneKotlinTypeSubstituteOrNull3 != null) {
                z2 = true;
            } else {
                coneKotlinTypeSubstituteOrNull3 = null;
            }
            arrayList2.add(coneKotlinTypeSubstituteOrNull3);
        }
        ConeKotlinType coneKotlinTypeSubstituteOrNull4 = enhancedForWarningConeSubstitutor.substituteOrNull(checkerContext.getReturnTypeCalculator().tryCalculateReturnType(firNamedFunction).getConeType());
        if (coneKotlinTypeSubstituteOrNull4 != null) {
            coneKotlinType = coneKotlinTypeSubstituteOrNull4;
            z2 = true;
        } else {
            coneKotlinType = null;
        }
        FirReceiverParameter receiverParameter = firNamedFunction.getReceiverParameter();
        if (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null || (coneKotlinTypeSubstituteOrNull = enhancedForWarningConeSubstitutor.substituteOrNull(coneType)) == null) {
            z = z2;
            coneKotlinType2 = null;
        } else {
            coneKotlinType2 = coneKotlinTypeSubstituteOrNull;
        }
        if (!z) {
            return null;
        }
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        return FirFakeOverrideGenerator.createCopyForFirFunction$default(firFakeOverrideGenerator, FirFakeOverrideGenerator.createSymbolForSubstitutionOverride$default(firFakeOverrideGenerator, firNamedFunction.getSymbol(), (ClassId) null, 2, (Object) null), firNamedFunction, null, checkerContext.getSession(), FirDeclarationOrigin.Enhancement.INSTANCE, false, null, arrayList, null, coneKotlinType2, arrayList2, coneKotlinType, null, null, null, null, true, 61728, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirNamedFunctionSymbol substituteOrNull(CheckerContext checkerContext, FirNamedFunctionSymbol firNamedFunctionSymbol, EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor) {
        FirNamedFunction firNamedFunctionSubstituteOrNull = substituteOrNull(checkerContext, (FirNamedFunction) firNamedFunctionSymbol.getFir(), enhancedForWarningConeSubstitutor);
        if (firNamedFunctionSubstituteOrNull != null) {
            return firNamedFunctionSubstituteOrNull.getSymbol();
        }
        return null;
    }

    private static final FirProperty substituteOrNull(CheckerContext checkerContext, FirProperty firProperty, EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor) {
        boolean z;
        ConeKotlinType coneKotlinType;
        ConeKotlinType coneKotlinType2;
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        if (!DeclarationUtilsKt.isJavaOrEnhancement(firProperty)) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firProperty.getSymbol(), FirResolvePhase.TYPES);
        List<FirValueParameter> contextParameters = firProperty.getContextParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
        Iterator<T> it = contextParameters.iterator();
        boolean z2 = false;
        while (true) {
            z = true;
            if (!it.hasNext()) {
                break;
            }
            ConeKotlinType coneKotlinTypeSubstituteOrNull2 = enhancedForWarningConeSubstitutor.substituteOrNull(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
            if (coneKotlinTypeSubstituteOrNull2 != null) {
                z2 = true;
            } else {
                coneKotlinTypeSubstituteOrNull2 = null;
            }
            arrayList.add(coneKotlinTypeSubstituteOrNull2);
        }
        ConeKotlinType coneKotlinTypeSubstituteOrNull3 = enhancedForWarningConeSubstitutor.substituteOrNull(checkerContext.getReturnTypeCalculator().tryCalculateReturnType(firProperty).getConeType());
        if (coneKotlinTypeSubstituteOrNull3 != null) {
            coneKotlinType = coneKotlinTypeSubstituteOrNull3;
            z2 = true;
        } else {
            coneKotlinType = null;
        }
        FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
        if (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null || (coneKotlinTypeSubstituteOrNull = enhancedForWarningConeSubstitutor.substituteOrNull(coneType)) == null) {
            coneKotlinType2 = null;
            z = z2;
        } else {
            coneKotlinType2 = coneKotlinTypeSubstituteOrNull;
        }
        if (!z) {
            return null;
        }
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        return FirFakeOverrideGenerator.createCopyForFirProperty$default(firFakeOverrideGenerator, FirFakeOverrideGenerator.createSymbolForSubstitutionOverride$default(firFakeOverrideGenerator, firProperty.getSymbol(), (ClassId) null, 2, (Object) null), firProperty, null, checkerContext.getSession(), FirDeclarationOrigin.Enhancement.INSTANCE, false, null, null, coneKotlinType2, arrayList, coneKotlinType, null, null, null, null, null, null, null, null, 522400, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final FirPropertySymbol substituteOrNull(CheckerContext checkerContext, FirPropertySymbol firPropertySymbol, EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor) {
        FirProperty firPropertySubstituteOrNull = substituteOrNull(checkerContext, (FirProperty) firPropertySymbol.getFir(), enhancedForWarningConeSubstitutor);
        if (firPropertySubstituteOrNull != null) {
            return firPropertySubstituteOrNull.getSymbol();
        }
        return null;
    }
}
