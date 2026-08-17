package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.extensions.FirFunctionCallRefinementExtensionKt;
import org.jetbrains.kotlin.fir.extensions.OriginalCallData;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b*\f\b\u0002\u0010\t\"\u00020\n2\u00020\n¨\u0006\u000b"}, d2 = {"computeCompletionMode", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "components", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "currentReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "CsCompleterContext", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CompletionModeCalculatorKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConstraintSystemCompletionMode computeCompletionMode(Candidate candidate, InferenceComponents inferenceComponents, ResolutionMode resolutionMode, ConeKotlinType coneKotlinType) {
        FirRegularClass firRegularClass;
        candidate.getClass();
        inferenceComponents.getClass();
        resolutionMode.getClass();
        if (resolutionMode.getForceFullCompletion()) {
            return ConstraintSystemCompletionMode.FULL;
        }
        OriginalCallData originalCallDataForPluginRefinedCall = null;
        if (candidate.getCallInfo().isCollectionLiteralCall()) {
            k2d.a("Should not run completion for collection literal");
            return null;
        }
        if (coneKotlinType == null) {
            return ConstraintSystemCompletionMode.PARTIAL;
        }
        if (ConstraintSystemCompleterKt.getCsBuilder(candidate).isProperType(coneKotlinType)) {
            return ConstraintSystemCompletionMode.FULL;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, inferenceComponents.getSession());
        if (regularClassSymbol != null && (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) != null) {
            originalCallDataForPluginRefinedCall = FirFunctionCallRefinementExtensionKt.getOriginalCallDataForPluginRefinedCall(firRegularClass);
        }
        return originalCallDataForPluginRefinedCall != null ? ConstraintSystemCompletionMode.FULL : new CalculatorForNestedCall(candidate, coneKotlinType, ConstraintSystemCompleterKt.getCsBuilder(candidate), inferenceComponents.getTrivialConstraintTypeInferenceOracle()).computeCompletionMode();
    }
}
