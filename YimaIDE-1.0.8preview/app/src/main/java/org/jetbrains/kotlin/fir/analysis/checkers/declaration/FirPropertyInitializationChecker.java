package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyInitializationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyInitializationChecker extends FirDeclarationChecker<FirClass> {
    public static final FirPropertyInitializationChecker INSTANCE = new FirPropertyInitializationChecker();

    private FirPropertyInitializationChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        FirVisitorVoid firVisitorVoid = new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyInitializationChecker$check$visitor$1
            public void visitAnonymousFunction(FirAnonymousFunction anonymousFunction) {
                anonymousFunction.getClass();
            }

            public void visitConstructor(FirConstructor constructor) {
                constructor.getClass();
            }

            public void visitElement(FirElement element) {
                element.getClass();
                element.acceptChildren(this);
            }

            public void visitEnumEntry(FirEnumEntry enumEntry) {
                enumEntry.getClass();
            }

            public void visitNamedFunction(FirNamedFunction namedFunction) {
                namedFunction.getClass();
            }

            public void visitPropertyAccessor(FirPropertyAccessor propertyAccessor) {
                propertyAccessor.getClass();
            }

            public void visitRegularClass(FirRegularClass regularClass) {
                regularClass.getClass();
            }

            public void visitVariableAssignment(FirVariableAssignment variableAssignment) {
                variableAssignment.getClass();
                variableAssignment.acceptChildren(this);
                FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(variableAssignment);
                FirCallableSymbol resolvedCallableSymbol$default = calleeReference != null ? FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null) : null;
                FirPropertySymbol firPropertySymbol = resolvedCallableSymbol$default instanceof FirPropertySymbol ? (FirPropertySymbol) resolvedCallableSymbol$default : null;
                if (firPropertySymbol != null && linkedHashSet.contains(firPropertySymbol)) {
                    if ((firClass instanceof FirAnonymousObject) && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ForbidInitializationBeforeDeclarationInAnonymous)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) variableAssignment.getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINITIALIZATION_BEFORE_DECLARATION_WARNING(), (Object) firPropertySymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) variableAssignment.getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINITIALIZATION_BEFORE_DECLARATION(), (Object) firPropertySymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
            }
        };
        for (FirDeclaration firDeclaration : CollectionsKt.asReversed(firClass.getDeclarations())) {
            if (!linkedHashSet.isEmpty()) {
                firDeclaration.accept(firVisitorVoid);
            }
            if (firDeclaration instanceof FirProperty) {
                linkedHashSet.add(((FirProperty) firDeclaration).getSymbol());
            }
        }
    }
}
