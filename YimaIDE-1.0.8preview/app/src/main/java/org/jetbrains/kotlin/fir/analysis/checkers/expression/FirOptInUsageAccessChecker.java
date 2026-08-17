package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ExplicitFieldsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "loadExperimentalitiesFromExplicitField", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)Ljava/util/Set;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInUsageAccessChecker extends FirExpressionChecker<FirStatement> {
    public static final FirOptInUsageAccessChecker INSTANCE = new FirOptInUsageAccessChecker();

    private FirOptInUsageAccessChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x005d  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirBasedSymbol<?> resolvedBaseSymbol$default;
        ConeKotlinType resolvedType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        KtSourceElement source = firStatement.getSource();
        KtSourceElementKind kind = source != null ? source.getKind() : null;
        if ((kind instanceof KtFakeSourceElementKind.DataClassGeneratedMembers) || (kind instanceof KtFakeSourceElementKind.PropertyFromParameter) || FirHelpersKt.isLhsOfAssignment(checkerContext, firStatement)) {
            return;
        }
        if (firStatement instanceof FirThisReceiverExpression) {
            FirBasedSymbol<?> symbol = FirReferenceUtilsKt.getSymbol(((FirThisReceiverExpression) firStatement).getCalleeReference());
            FirReceiverParameterSymbol firReceiverParameterSymbol = symbol instanceof FirReceiverParameterSymbol ? (FirReceiverParameterSymbol) symbol : null;
            if (firReceiverParameterSymbol == null || (resolvedType = firReceiverParameterSymbol.getResolvedType()) == null) {
                resolvedBaseSymbol$default = null;
            } else {
                resolvedBaseSymbol$default = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, resolvedType);
            }
        } else {
            FirReference reference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toReference(firStatement, checkerContext.getSession());
            if (reference != null) {
                resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(reference, false, 1, null);
            } else {
                resolvedBaseSymbol$default = null;
            }
        }
        if (resolvedBaseSymbol$default == null) {
            return;
        }
        FirOptInUsageBaseChecker firOptInUsageBaseChecker = FirOptInUsageBaseChecker.INSTANCE;
        if (firStatement instanceof FirVariableAssignment) {
            FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, SetsKt.plus(firOptInUsageBaseChecker.loadExperimentalities(checkerContext, resolvedBaseSymbol$default, true), firOptInUsageBaseChecker.loadExperimentalitiesFromTypeArguments(checkerContext, CollectionsKt.emptyList())), ((FirVariableAssignment) firStatement).getLValue(), null, false, 48, null);
            return;
        }
        if (!(firStatement instanceof FirQualifiedAccessExpression)) {
            if ((firStatement instanceof FirDelegatedConstructorCall) && (resolvedBaseSymbol$default instanceof FirConstructorSymbol)) {
                FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, ((FirCallableSymbol) resolvedBaseSymbol$default).getRawStatus().isFromEnumClass() ? firOptInUsageBaseChecker.loadExperimentalities(checkerContext, resolvedBaseSymbol$default, false) : firOptInUsageBaseChecker.loadExperimentalitiesFromConstructor(checkerContext, (FirConstructorSymbol) resolvedBaseSymbol$default), ((FirDelegatedConstructorCall) firStatement).getCalleeReference(), null, false, 48, null);
                return;
            }
            return;
        }
        Set<FirOptInUsageBaseChecker.Experimentality> setLoadExperimentalities = firOptInUsageBaseChecker.loadExperimentalities(checkerContext, resolvedBaseSymbol$default, false);
        FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firStatement;
        Set setPlus = SetsKt.plus(SetsKt.plus(setLoadExperimentalities, firOptInUsageBaseChecker.loadExperimentalitiesFromTypeArguments(checkerContext, firQualifiedAccessExpression.getTypeArguments())), INSTANCE.loadExperimentalitiesFromExplicitField(checkerContext, firStatement));
        KtSourceElement source2 = firQualifiedAccessExpression.getSource();
        FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, setPlus, firStatement, source2 != null ? SourceHelpersKt.delegatedPropertySourceOrThis(checkerContext, source2) : null, false, 32, null);
    }

    public final Set<FirOptInUsageBaseChecker.Experimentality> loadExperimentalitiesFromExplicitField(CheckerContext checkerContext, FirStatement firStatement) {
        checkerContext.getClass();
        firStatement.getClass();
        if (!(firStatement instanceof FirPropertyAccessExpression)) {
            return SetsKt.emptySet();
        }
        FirNamedReference calleeReference = ((FirPropertyAccessExpression) firStatement).getCalleeReference();
        FirPropertyWithExplicitBackingFieldResolvedNamedReference firPropertyWithExplicitBackingFieldResolvedNamedReference = calleeReference instanceof FirPropertyWithExplicitBackingFieldResolvedNamedReference ? (FirPropertyWithExplicitBackingFieldResolvedNamedReference) calleeReference : null;
        if (firPropertyWithExplicitBackingFieldResolvedNamedReference == null) {
            return SetsKt.emptySet();
        }
        FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(firPropertyWithExplicitBackingFieldResolvedNamedReference, false, 1, null);
        if (resolvedPropertySymbol$default != null) {
            if (!DeclarationAttributesKt.getHasExplicitBackingField(resolvedPropertySymbol$default)) {
                resolvedPropertySymbol$default = null;
            }
            if (resolvedPropertySymbol$default != null) {
                FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
                FirBackingFieldSymbol firBackingFieldSymbolTryAccessExplicitFieldSymbol = ExplicitFieldsUtilsKt.tryAccessExplicitFieldSymbol(firPropertyWithExplicitBackingFieldResolvedNamedReference, inlineFunctionBodyContext != null ? inlineFunctionBodyContext.getInlineFunction() : null, checkerContext.getSession());
                if (firBackingFieldSymbolTryAccessExplicitFieldSymbol != null && Intrinsics.areEqual(resolvedPropertySymbol$default.getBackingFieldSymbol(), firBackingFieldSymbolTryAccessExplicitFieldSymbol)) {
                    return FirOptInUsageBaseChecker.INSTANCE.loadExperimentalities(checkerContext, firBackingFieldSymbolTryAccessExplicitFieldSymbol, false);
                }
                return SetsKt.emptySet();
            }
        }
        return SetsKt.emptySet();
    }
}
