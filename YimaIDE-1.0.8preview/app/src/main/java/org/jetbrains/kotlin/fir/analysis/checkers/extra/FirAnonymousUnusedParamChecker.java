package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u0013B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\u00020\u0002H\u0002R \u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "getReportableParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "unusedParamsVisitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "LambdaBodyContext", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousUnusedParamChecker extends FirDeclarationChecker<FirAnonymousFunction> {
    public static final FirAnonymousUnusedParamChecker INSTANCE = new FirAnonymousUnusedParamChecker();
    private static final FirVisitor<Unit, Set<FirValueParameterSymbol>> unusedParamsVisitor = new FirVisitor<Unit, Set<FirValueParameterSymbol>>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.extra.FirAnonymousUnusedParamChecker$unusedParamsVisitor$1
        /* JADX INFO: renamed from: visitAnonymousFunction, reason: avoid collision after fix types in other method */
        public void visitAnonymousFunction2(FirAnonymousFunction anonymousFunction, Set<FirValueParameterSymbol> data) {
            anonymousFunction.getClass();
            data.getClass();
            if (!anonymousFunction.isLambda()) {
                visitElement2((FirElement) anonymousFunction, data);
            } else {
                data.addAll(FirAnonymousUnusedParamChecker.INSTANCE.getReportableParameters(anonymousFunction));
                anonymousFunction.acceptChildren(this, data);
            }
        }

        /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
        public void visitElement2(FirElement element, Set<FirValueParameterSymbol> data) {
            element.getClass();
            data.getClass();
            if (data.isEmpty()) {
                return;
            }
            element.acceptChildren(this, data);
        }

        /* JADX INFO: renamed from: visitResolvedNamedReference, reason: avoid collision after fix types in other method */
        public void visitResolvedNamedReference2(FirResolvedNamedReference resolvedNamedReference, Set<FirValueParameterSymbol> data) {
            resolvedNamedReference.getClass();
            data.getClass();
            TypeIntrinsics.asMutableCollection(data).remove(resolvedNamedReference.getResolvedSymbol());
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, Set<FirValueParameterSymbol> set) {
            visitElement2(firElement, set);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitResolvedNamedReference(FirResolvedNamedReference firResolvedNamedReference, Set<FirValueParameterSymbol> set) {
            visitResolvedNamedReference2(firResolvedNamedReference, set);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitAnonymousFunction(FirAnonymousFunction firAnonymousFunction, Set<FirValueParameterSymbol> set) {
            visitAnonymousFunction2(firAnonymousFunction, set);
            return Unit.INSTANCE;
        }
    };

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J/\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0003H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", Argument.Delimiters.none, "outermostLambda", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "checkUnusedParams", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "checkUnusedParams$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class LambdaBodyContext {
        private final FirAnonymousFunction outermostLambda;

        public LambdaBodyContext(FirAnonymousFunction firAnonymousFunction) {
            firAnonymousFunction.getClass();
            this.outermostLambda = firAnonymousFunction;
        }

        public final void checkUnusedParams$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnonymousFunction firAnonymousFunction) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firAnonymousFunction.getClass();
            if (Intrinsics.areEqual(firAnonymousFunction, this.outermostLambda)) {
                Set<FirValueParameterSymbol> reportableParameters = FirAnonymousUnusedParamChecker.INSTANCE.getReportableParameters(firAnonymousFunction);
                FirBlock body = firAnonymousFunction.getBody();
                if (body != null) {
                    body.accept(FirAnonymousUnusedParamChecker.unusedParamsVisitor, reportableParameters);
                }
                for (FirValueParameterSymbol firValueParameterSymbol : reportableParameters) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNUSED_ANONYMOUS_PARAMETER(), (Object) firValueParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    private FirAnonymousUnusedParamChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<FirValueParameterSymbol> getReportableParameters(FirAnonymousFunction firAnonymousFunction) {
        List<FirValueParameter> valueParameters = firAnonymousFunction.getValueParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : valueParameters) {
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            KtSourceElement source = firValueParameter.getSource();
            if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) && !Intrinsics.areEqual(firValueParameter.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
                arrayList.add(obj);
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((FirValueParameter) it.next()).getSymbol());
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnonymousFunction firAnonymousFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnonymousFunction.getClass();
        LambdaBodyContext lambdaBodyContext = checkerContext.getLambdaBodyContext();
        if (lambdaBodyContext != null) {
            lambdaBodyContext.checkUnusedParams$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, firAnonymousFunction);
        }
    }
}
