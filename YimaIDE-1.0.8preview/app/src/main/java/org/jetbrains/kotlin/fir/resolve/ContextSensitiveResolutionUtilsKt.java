package org.jetbrains.kotlin.fir.resolve;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeHiddenCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ContextSensitiveResolutionMightBeUsed;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformerKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\b*\u00020\u0004\u001a\f\u0010\t\u001a\u00020\b*\u00020\nH\u0002\u001a\u0014\u0010\u000b\u001a\u00020\b*\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001\u001a\u0012\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f*\u00020\u0001H\u0002¨\u0006\u0010"}, d2 = {"runContextSensitiveResolutionForPropertyAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "originalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "shouldBeResolvedInContextSensitiveMode", Argument.Delimiters.none, "meansNoAvailableCandidate", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "appendCSRAlternativeDiagnosticIfNeeded", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "resolvedSimpleNameVersion", "obtainSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContextSensitiveResolutionUtilsKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean appendCSRAlternativeDiagnosticIfNeeded(FirQualifierWithContextSensitiveAlternative firQualifierWithContextSensitiveAlternative, FirExpression firExpression) {
        firQualifierWithContextSensitiveAlternative.getClass();
        if (!(firQualifierWithContextSensitiveAlternative instanceof FirExpression)) {
            xz8.a("All inheritors of sealed FirQualifierWithContextSensitiveAlternative should be expressions, but ", Reflection.getOrCreateKotlinClass(firQualifierWithContextSensitiveAlternative.getClass()).getSimpleName(), " found");
            return false;
        }
        if (!Intrinsics.areEqual(obtainSymbol((FirExpression) firQualifierWithContextSensitiveAlternative), firExpression != null ? obtainSymbol(firExpression) : null)) {
            return false;
        }
        if (firQualifierWithContextSensitiveAlternative instanceof FirPropertyAccessExpression) {
            FirCallCompletionResultsWriterTransformerKt.appendNonFatalDiagnostics((FirQualifiedAccessExpression) firQualifierWithContextSensitiveAlternative, ContextSensitiveResolutionMightBeUsed.INSTANCE);
        } else {
            if (!(firQualifierWithContextSensitiveAlternative instanceof FirResolvedQualifier)) {
                bu8.a();
                return false;
            }
            FirCallCompletionResultsWriterTransformerKt.appendNonFatalDiagnostics((FirResolvedQualifier) firQualifierWithContextSensitiveAlternative, ContextSensitiveResolutionMightBeUsed.INSTANCE);
        }
        return true;
    }

    private static final boolean meansNoAvailableCandidate(ConeDiagnostic coneDiagnostic) {
        if ((coneDiagnostic instanceof ConeUnresolvedError) || (coneDiagnostic instanceof ConeVisibilityError) || (coneDiagnostic instanceof ConeHiddenCandidateError)) {
            return true;
        }
        if (!(coneDiagnostic instanceof ConeAmbiguityError)) {
            return false;
        }
        Collection<AbstractCandidate> candidates = ((ConeAmbiguityError) coneDiagnostic).getCandidates();
        if ((candidates instanceof Collection) && candidates.isEmpty()) {
            return true;
        }
        for (AbstractCandidate abstractCandidate : candidates) {
            if (abstractCandidate.getApplicability() != CandidateApplicability.HIDDEN && abstractCandidate.getApplicability() != CandidateApplicability.K2_VISIBILITY_ERROR) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirBasedSymbol<?> obtainSymbol(FirExpression firExpression) {
        if (firExpression instanceof FirPropertyAccessExpression) {
            return ReferenceUtilsKt.toResolvedCallableSymbol((FirResolvable) firExpression);
        }
        if (firExpression instanceof FirResolvedQualifier) {
            return ((FirResolvedQualifier) firExpression).getSymbol();
        }
        return null;
    }

    public static final FirExpression runContextSensitiveResolutionForPropertyAccess(BodyResolveComponents bodyResolveComponents, FirPropertyAccessExpression firPropertyAccessExpression, ConeKotlinType coneKotlinType) {
        FirExpression firExpressionResolveVariableAccessAndSelectCandidate;
        boolean z;
        bodyResolveComponents.getClass();
        firPropertyAccessExpression.getClass();
        coneKotlinType.getClass();
        Iterator it = ContextSensitiveResolutionKt.getParentChainForContextSensitiveResolutionOfExpressions(coneKotlinType, bodyResolveComponents.getSession()).iterator();
        do {
            if (!it.hasNext()) {
                return null;
            }
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) it.next();
            KtSourceElement source = firPropertyAccessExpression.getSource();
            FirResolvedQualifier implicitResolvedQualifierReceiver = ResolveUtilsKt.toImplicitResolvedQualifierReceiver(firRegularClassSymbol, bodyResolveComponents, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.QualifierForContextSensitiveResolution.INSTANCE, null, 2, null) : null);
            FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
            firPropertyAccessExpressionBuilder.getAnnotations().addAll(firPropertyAccessExpression.getAnnotations());
            firPropertyAccessExpressionBuilder.setExplicitReceiver(implicitResolvedQualifierReceiver);
            firPropertyAccessExpressionBuilder.setSource(firPropertyAccessExpression.getSource());
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(firPropertyAccessExpression.getCalleeReference().getSource());
            firSimpleNamedReferenceBuilder.setName(firPropertyAccessExpression.getCalleeReference().getName());
            firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            FirPropertyAccessExpression firPropertyAccessExpressionBuild = firPropertyAccessExpressionBuilder.mo288build();
            firExpressionResolveVariableAccessAndSelectCandidate = bodyResolveComponents.getCallResolver().resolveVariableAccessAndSelectCandidate(firPropertyAccessExpressionBuild, false, false, firPropertyAccessExpressionBuild, ResolutionMode.ContextIndependent.INSTANCE);
            z = false;
            if (firExpressionResolveVariableAccessAndSelectCandidate instanceof FirPropertyAccessExpression) {
                FirNamedReference calleeReference = ((FirPropertyAccessExpression) firExpressionResolveVariableAccessAndSelectCandidate).getCalleeReference();
                if ((calleeReference instanceof FirResolvedNamedReference) && !(calleeReference instanceof FirResolvedErrorReference)) {
                    z = true;
                }
                if (z) {
                    ((FirResolvedNamedReference) calleeReference).replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin.ContextSensitive);
                }
            } else if (firExpressionResolveVariableAccessAndSelectCandidate instanceof FirResolvedQualifier) {
                ((FirResolvedQualifier) firExpressionResolveVariableAccessAndSelectCandidate).replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin.ContextSensitive);
                z = true;
            }
        } while (!z);
        return firExpressionResolveVariableAccessAndSelectCandidate;
    }

    public static final boolean shouldBeResolvedInContextSensitiveMode(FirPropertyAccessExpression firPropertyAccessExpression) {
        ConeDiagnostic diagnostic;
        firPropertyAccessExpression.getClass();
        FirNamedReference calleeReference = firPropertyAccessExpression.getCalleeReference();
        if (calleeReference instanceof FirErrorNamedReference) {
            diagnostic = ((FirErrorNamedReference) calleeReference).getDiagnostic();
        } else if (calleeReference instanceof FirErrorReferenceWithCandidate) {
            diagnostic = ((FirErrorReferenceWithCandidate) calleeReference).getDiagnostic();
        } else {
            if (!(calleeReference instanceof FirResolvedErrorReference)) {
                return false;
            }
            diagnostic = ((FirResolvedErrorReference) calleeReference).getDiagnostic();
        }
        if (firPropertyAccessExpression.getExplicitReceiver() != null) {
            return false;
        }
        return meansNoAvailableCandidate(diagnostic);
    }
}
