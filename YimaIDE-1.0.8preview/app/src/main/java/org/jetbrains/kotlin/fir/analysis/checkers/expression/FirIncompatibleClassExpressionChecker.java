package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerAbiStability;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0013\u0010\u0014J7\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirIncompatibleClassExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "checkType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "checkType$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/FirElement;)V", "checkSourceElement", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/descriptors/SourceElement;Lorg/jetbrains/kotlin/fir/FirElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIncompatibleClassExpressionChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirIncompatibleClassExpressionChecker INSTANCE = new FirIncompatibleClassExpressionChecker();

    private FirIncompatibleClassExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkSourceElement(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, SourceElement sourceElement, FirElement firElement) {
        if (sourceElement instanceof DeserializedContainerSource) {
            DeserializedContainerSource deserializedContainerSource = (DeserializedContainerSource) sourceElement;
            IncompatibleVersionErrorData<?> incompatibleVersionErrorDataMo247getIncompatibility = deserializedContainerSource.mo247getIncompatibility();
            if (incompatibleVersionErrorDataMo247getIncompatibility != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firElement.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getINCOMPATIBLE_CLASS(), (Object) deserializedContainerSource.getPresentableString(), (Object) incompatibleVersionErrorDataMo247getIncompatibility, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            if (deserializedContainerSource.getPreReleaseInfo().isInvisible()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firElement.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getPRE_RELEASE_CLASS(), (Object) deserializedContainerSource.getPresentableString(), (Object) deserializedContainerSource.getPreReleaseInfo().getPoisoningFeatures(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            if (deserializedContainerSource.getAbiStability() == DeserializedContainerAbiStability.UNSTABLE) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firElement.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getIR_WITH_UNSTABLE_ABI_COMPILED_CLASS(), (Object) deserializedContainerSource.getPresentableString(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null) {
            return;
        }
        checkType$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, resolvedCallableSymbol$default.getResolvedReturnType(), firQualifiedAccessExpression);
        Iterator<FirValueParameterSymbol> it = resolvedCallableSymbol$default.getContextParameterSymbols().iterator();
        while (it.hasNext()) {
            checkType$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, it.next().getResolvedReturnType(), firQualifiedAccessExpression);
        }
        checkType$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, resolvedCallableSymbol$default.getResolvedReceiverType(), firQualifiedAccessExpression);
        if (resolvedCallableSymbol$default instanceof FirFunctionSymbol) {
            Iterator<FirValueParameterSymbol> it2 = ((FirFunctionSymbol) resolvedCallableSymbol$default).getValueParameterSymbols().iterator();
            while (it2.hasNext()) {
                checkType$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, it2.next().getResolvedReturnTypeRef().getConeType(), firQualifiedAccessExpression);
            }
        }
        checkSourceElement(checkerContext, diagnosticReporter, resolvedCallableSymbol$default.getContainerSource(), firQualifiedAccessExpression);
    }

    public final void checkType$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, FirElement firElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firElement.getClass();
        FirRegularClassSymbol regularClassSymbol = coneKotlinType != null ? ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinType) : null;
        checkSourceElement(checkerContext, diagnosticReporter, regularClassSymbol != null ? DeclarationAttributesKt.getSourceElement(regularClassSymbol) : null, firElement);
    }
}
