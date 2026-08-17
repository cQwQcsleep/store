package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\u0010\u0011B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000f\u0082\u0001\u0002\u0012\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirFunInterfaceDeclarationChecker extends FirDeclarationChecker<FirRegularClass> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirFunInterfaceDeclarationChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunInterfaceDeclarationChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firRegularClass.getClass();
            if (firRegularClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firRegularClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunInterfaceDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirFunInterfaceDeclarationChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunInterfaceDeclarationChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firRegularClass.getClass();
            if (firRegularClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firRegularClass);
        }
    }

    public /* synthetic */ FirFunInterfaceDeclarationChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() == ClassKind.INTERFACE && firRegularClass.getStatus().isFun()) {
            FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClass);
            FirRegularClassSymbol symbol = firRegularClass.getSymbol();
            FirNamedFunctionSymbol firNamedFunctionSymbol = null;
            for (Name name : firTypeScopeUnsubstitutedScope.getCallableNames()) {
                List<FirNamedFunctionSymbol> functions = FirScopeKt.getFunctions(firTypeScopeUnsubstitutedScope, name);
                List<FirVariableSymbol<?>> properties = FirScopeKt.getProperties(firTypeScopeUnsubstitutedScope, name);
                for (FirNamedFunctionSymbol firNamedFunctionSymbol2 : functions) {
                    if (firNamedFunctionSymbol2.getResolvedStatus().getModality() == Modality.ABSTRACT) {
                        if (firNamedFunctionSymbol == null) {
                            firNamedFunctionSymbol = firNamedFunctionSymbol2;
                        } else {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getFUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                }
                for (FirVariableSymbol<?> firVariableSymbol : properties) {
                    FirPropertySymbol firPropertySymbol = firVariableSymbol instanceof FirPropertySymbol ? (FirPropertySymbol) firVariableSymbol : null;
                    if (firPropertySymbol != null && firPropertySymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (!Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(firPropertySymbol), symbol) ? firRegularClass.getSource() : firPropertySymbol.getSource()), FirErrors.INSTANCE.getFUN_INTERFACE_CANNOT_HAVE_ABSTRACT_PROPERTIES(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
            }
            if (firNamedFunctionSymbol == null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirErrors.INSTANCE.getFUN_INTERFACE_WRONG_COUNT_OF_ABSTRACT_MEMBERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            boolean z = ContainingClassUtilsKt.getContainingClassSymbol(firNamedFunctionSymbol) == symbol;
            if (!firNamedFunctionSymbol.getTypeParameterSymbols().isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (z ? firNamedFunctionSymbol.getSource() : firRegularClass.getSource()), FirErrors.INSTANCE.getFUN_INTERFACE_ABSTRACT_METHOD_WITH_TYPE_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            for (FirValueParameterSymbol firValueParameterSymbol : firNamedFunctionSymbol.getValueParameterSymbols()) {
                if (firValueParameterSymbol.getHasDefaultValue()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (z ? firValueParameterSymbol.getSource() : firRegularClass.getSource()), FirErrors.INSTANCE.getFUN_INTERFACE_ABSTRACT_METHOD_WITH_DEFAULT_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    private FirFunInterfaceDeclarationChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
