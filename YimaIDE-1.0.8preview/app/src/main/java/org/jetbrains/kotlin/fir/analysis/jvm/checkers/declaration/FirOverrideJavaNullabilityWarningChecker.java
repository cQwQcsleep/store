package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAbstractOverrideChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirOverrideJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedForWarningConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000f\u0010B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000e\u0082\u0001\u0002\u0011\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAbstractOverrideChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker$Regular;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirOverrideJavaNullabilityWarningChecker extends FirAbstractOverrideChecker {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirOverrideJavaNullabilityWarningChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirOverrideJavaNullabilityWarningChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirOverrideJavaNullabilityWarningChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirOverrideJavaNullabilityWarningChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirOverrideJavaNullabilityWarningChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firClass);
        }
    }

    public /* synthetic */ FirOverrideJavaNullabilityWarningChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(FirTypeScope firTypeScope, FirOverrideJavaNullabilityWarningChecker firOverrideJavaNullabilityWarningChecker, CheckerContext checkerContext, TypeCheckerState typeCheckerState, EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor, DiagnosticReporter diagnosticReporter, FirCallableSymbol firCallableSymbol) {
        FirCallableSymbol<?> firCallableSymbolCheckReturnType;
        FirCallableSymbol<?> firCallableSymbolCheckReturnType2;
        FirCallableSymbol firCallableSymbol2 = firCallableSymbol;
        firCallableSymbol2.getClass();
        boolean z = false;
        if (firCallableSymbol2 instanceof FirNamedFunctionSymbol) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firCallableSymbol2;
            List<FirNamedFunctionSymbol> directOverriddenFunctions$default = FirTypeScopeKt.getDirectOverriddenFunctions$default(firTypeScope, firNamedFunctionSymbol, false, 2, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenFunctions$default, 10));
            boolean z2 = false;
            for (FirNamedFunctionSymbol firNamedFunctionSymbol2 : directOverriddenFunctions$default) {
                FirNamedFunctionSymbol firNamedFunctionSymbolSubstituteOrNull = FirOverrideJavaNullabilityWarningCheckerKt.substituteOrNull(checkerContext, firNamedFunctionSymbol2, enhancedForWarningConeSubstitutor);
                if (firNamedFunctionSymbolSubstituteOrNull != null) {
                    if (!z2 && !FirOverrideCheckerKt.isOverriddenFunction(FirOverrideCheckerKt.getFirOverrideChecker(checkerContext.getSession()), firNamedFunctionSymbol, firNamedFunctionSymbolSubstituteOrNull)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getWRONG_TYPE_FOR_JAVA_OVERRIDE(), (Object) firCallableSymbol, (Object) firNamedFunctionSymbolSubstituteOrNull, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        firCallableSymbol2 = firCallableSymbol;
                        z2 = true;
                    }
                    firNamedFunctionSymbol2 = firNamedFunctionSymbolSubstituteOrNull;
                    z = true;
                }
                arrayList.add(firNamedFunctionSymbol2);
            }
            if (z && !z2 && (firCallableSymbolCheckReturnType2 = firOverrideJavaNullabilityWarningChecker.checkReturnType(checkerContext, firCallableSymbol2, arrayList, typeCheckerState)) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getWRONG_TYPE_FOR_JAVA_OVERRIDE(), (Object) firCallableSymbol2, (Object) firCallableSymbolCheckReturnType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        } else if (firCallableSymbol2 instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol2;
            List<FirPropertySymbol> directOverriddenProperties$default = FirTypeScopeKt.getDirectOverriddenProperties$default(firTypeScope, firPropertySymbol, false, 2, null);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenProperties$default, 10));
            boolean z3 = false;
            for (FirPropertySymbol firPropertySymbol2 : directOverriddenProperties$default) {
                FirPropertySymbol firPropertySymbolSubstituteOrNull = FirOverrideJavaNullabilityWarningCheckerKt.substituteOrNull(checkerContext, firPropertySymbol2, enhancedForWarningConeSubstitutor);
                if (firPropertySymbolSubstituteOrNull != null) {
                    if (!z3 && !FirOverrideCheckerKt.isOverriddenProperty(FirOverrideCheckerKt.getFirOverrideChecker(checkerContext.getSession()), firCallableSymbol2, firPropertySymbolSubstituteOrNull)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertySymbol.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getWRONG_TYPE_FOR_JAVA_OVERRIDE(), (Object) firCallableSymbol, (Object) firPropertySymbolSubstituteOrNull, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                        firCallableSymbol2 = firCallableSymbol;
                        z3 = true;
                    }
                    firPropertySymbol2 = firPropertySymbolSubstituteOrNull;
                    z = true;
                }
                arrayList2.add(firPropertySymbol2);
            }
            if (z && !z3 && (firCallableSymbolCheckReturnType = firOverrideJavaNullabilityWarningChecker.checkReturnType(checkerContext, firCallableSymbol2, arrayList2, typeCheckerState)) != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertySymbol.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getWRONG_TYPE_FOR_JAVA_OVERRIDE(), (Object) firCallableSymbol2, (Object) firCallableSymbolCheckReturnType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        final EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutor = new EnhancedForWarningConeSubstitutor(TypeComponentsKt.getTypeContext(checkerContext.getSession()), null, 2, null);
        final FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
        final TypeCheckerState typeCheckerStateNewTypeCheckerState$default = TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(checkerContext.getSession()), false, false, false, 4, (Object) null);
        DeclarationUtilsKt.processAllDeclaredCallables$default(firClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: ob5
            public final Object invoke(Object obj) {
                return FirOverrideJavaNullabilityWarningChecker.b(firTypeScopeUnsubstitutedScope, this, checkerContext, typeCheckerStateNewTypeCheckerState$default, enhancedForWarningConeSubstitutor, diagnosticReporter, (FirCallableSymbol) obj);
            }
        }, 2, null);
    }

    private FirOverrideJavaNullabilityWarningChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
