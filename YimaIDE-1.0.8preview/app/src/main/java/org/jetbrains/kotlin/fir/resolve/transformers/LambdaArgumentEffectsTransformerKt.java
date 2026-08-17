package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.KtCallsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.contracts.ContractUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"replaceLambdaArgumentEffects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LambdaArgumentEffectsTransformerKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final void replaceLambdaArgumentEffects(FirFunctionCall firFunctionCall, FirSession firSession) {
        FirValueParameter firValueParameter;
        FirContractDescription contractDescription;
        firFunctionCall.getClass();
        firSession.getClass();
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference : null;
        if (firNamedReferenceWithCandidate == null) {
            return;
        }
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping = firNamedReferenceWithCandidate.getCandidate().getArgumentMapping();
        FirBasedSymbol<?> symbol = firNamedReferenceWithCandidate.getCandidate().getSymbol();
        FirDeclaration fir = symbol.getFir();
        FirFunction firFunction = fir instanceof FirNamedFunction ? (FirNamedFunction) fir : null;
        if (firFunction == null) {
            FirDeclaration fir2 = symbol.getFir();
            firFunction = fir2 instanceof FirConstructor ? (FirConstructor) fir2 : null;
            if (firFunction == null) {
                return;
            }
        }
        boolean z = firFunction.getStatus().isInline() || TransformUtilsKt.isArrayConstructorWithLambda(symbol);
        FirCallableDeclaration firCallableDeclaration = firFunction;
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            } else {
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
        }
        FirContractDescriptionOwner firContractDescriptionOwner = firCallableDeclaration instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) firCallableDeclaration : null;
        List<FirEffectDeclaration> effects = (firContractDescriptionOwner == null || (contractDescription = firContractDescriptionOwner.getContractDescription()) == null) ? null : ContractUtilsKt.getEffects(contractDescription);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (effects != null) {
            Iterator<T> it = effects.iterator();
            while (it.hasNext()) {
                KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = ((FirEffectDeclaration) it.next()).getEffect();
                if (effect instanceof KtCallsEffectDeclaration) {
                    KtCallsEffectDeclaration ktCallsEffectDeclaration = (KtCallsEffectDeclaration) effect;
                    FirValueParameter firValueParameter2 = (FirValueParameter) CollectionsKt.getOrNull(firFunction.getValueParameters(), ktCallsEffectDeclaration.getValueParameterReference().getParameterIndex());
                    if (firValueParameter2 != null) {
                        linkedHashMap.put(firValueParameter2, ktCallsEffectDeclaration.getKind());
                    }
                } else if ((effect instanceof KtHoldsInEffectDeclaration) && (firValueParameter = (FirValueParameter) CollectionsKt.getOrNull(firFunction.getValueParameters(), ((KtHoldsInEffectDeclaration) effect).getValueParameterReference().getParameterIndex())) != null) {
                    linkedHashSet.add(firValueParameter);
                }
            }
        }
        if (!linkedHashMap.isEmpty() || z) {
            for (Map.Entry<ConeResolutionAtom, FirValueParameter> entry : argumentMapping.entrySet()) {
                ConeResolutionAtom key = entry.getKey();
                FirValueParameter value = entry.getValue();
                FirAnonymousFunction firAnonymousFunctionUnwrapAnonymousFunctionExpression = FirExpressionUtilKt.unwrapAnonymousFunctionExpression(key.getExpression());
                if (firAnonymousFunctionUnwrapAnonymousFunctionExpression != null) {
                    TransformUtilsKt.transformInlineStatus(firAnonymousFunctionUnwrapAnonymousFunctionExpression, value, z, firSession);
                    EventOccurrencesRange eventOccurrencesRange = (EventOccurrencesRange) linkedHashMap.get(value);
                    if (eventOccurrencesRange == null) {
                        eventOccurrencesRange = EventOccurrencesRange.UNKNOWN;
                        if (!z || value.getIsNoinline() || value.getIsCrossinline() || !FunctionalTypeUtilsKt.isNonReflectFunctionType(FirTypeUtilsKt.getConeType(value.getReturnTypeRef()), firSession)) {
                            eventOccurrencesRange = null;
                        }
                    }
                    if (eventOccurrencesRange != null) {
                        firAnonymousFunctionUnwrapAnonymousFunctionExpression.replaceInvocationKind(eventOccurrencesRange);
                    }
                    if (linkedHashSet.contains(value)) {
                        DeclarationAttributesKt.setLambdaArgumentParent(firAnonymousFunctionUnwrapAnonymousFunctionExpression, firFunctionCall);
                    }
                }
            }
        }
    }
}
