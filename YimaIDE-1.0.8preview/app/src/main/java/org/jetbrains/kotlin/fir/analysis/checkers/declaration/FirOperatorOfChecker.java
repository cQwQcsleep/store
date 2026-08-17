package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirStandardOverrideChecker;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u000eB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "CheckerImpl", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOperatorOfChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirOperatorOfChecker INSTANCE = new FirOperatorOfChecker();

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001:\u0002%&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\b\u001a\u00020\t*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J=\u0010\u0012\u001a\u00020\u0013*\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\nR\u00020\u0014R\u00020\u0016j\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\u0015\u001a\u00020\u0014j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u001aJ5\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\u0014R\u00020\u0016j\u0006\u0010\u0015\u001a\u00020\u0014j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u001dJ3\u0010\u001e\u001a\u00020\t2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002R\u00020\u0014R\u00020\u0016j\u0006\u0010\u0015\u001a\u00020\u0014j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\"J#\u0010#\u001a\u00020\u0013R\u00020\u0014R\u00020\u0016j\u0006\u0010\u0015\u001a\u00020\u0014j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl;", Argument.Delimiters.none, "companion", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getCompanion", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "isMatchingParameter", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$MainOfOverload;", "valueParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$MainOfOverload;Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)Z", "checkOverload", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "overload", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirStandardOverrideChecker;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$MainOfOverload;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "checkStatus", "main", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "checkNumberOfMainOverloads", "overloads", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$OfOverload;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;)Z", "check", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "OfOverload", "MainOfOverload", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CheckerImpl {
        private final FirRegularClassSymbol companion;

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$MainOfOverload;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$OfOverload;", "function", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "mainParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;)V", "getMainParameter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "mainParameterElementType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getMainParameterElementType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class MainOfOverload extends OfOverload {
            private final FirValueParameterSymbol mainParameter;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public MainOfOverload(FirNamedFunctionSymbol firNamedFunctionSymbol, FirValueParameterSymbol firValueParameterSymbol) {
                super(firNamedFunctionSymbol);
                firNamedFunctionSymbol.getClass();
                firValueParameterSymbol.getClass();
                this.mainParameter = firValueParameterSymbol;
            }

            public final FirValueParameterSymbol getMainParameter() {
                return this.mainParameter;
            }

            public final ConeKotlinType getMainParameterElementType() {
                return FirTypeUtilsKt.arrayElementType$default(this.mainParameter.getResolvedReturnType(), false, 1, null);
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOperatorOfChecker$CheckerImpl$OfOverload;", Argument.Delimiters.none, "function", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "getFunction", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static class OfOverload {
            private final FirNamedFunctionSymbol function;

            public OfOverload(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                firNamedFunctionSymbol.getClass();
                this.function = firNamedFunctionSymbol;
            }

            public final FirNamedFunctionSymbol getFunction() {
                return this.function;
            }
        }

        public CheckerImpl(FirRegularClassSymbol firRegularClassSymbol) {
            firRegularClassSymbol.getClass();
            this.companion = firRegularClassSymbol;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit check$lambda$0$0(CheckerContext checkerContext, List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
            Object next;
            firNamedFunctionSymbol.getClass();
            if (firNamedFunctionSymbol.getResolvedStatus().isOperator() && !DeprecationUtilsKt.isDeprecationLevelHidden(firNamedFunctionSymbol, checkerContext.getSession())) {
                Iterator<T> it = firNamedFunctionSymbol.getValueParameterSymbols().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!((FirValueParameterSymbol) next).isVararg());
                FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) next;
                if (firValueParameterSymbol != null) {
                    list.add(new MainOfOverload(firNamedFunctionSymbol, firValueParameterSymbol));
                } else {
                    list.add(new OfOverload(firNamedFunctionSymbol));
                }
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }

        private final boolean checkNumberOfMainOverloads(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<? extends OfOverload> list) {
            int i;
            List<? extends OfOverload> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                i = 0;
            } else {
                Iterator<T> it = list2.iterator();
                i = 0;
                while (it.hasNext()) {
                    if ((((OfOverload) it.next()) instanceof MainOfOverload) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i == 0) {
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((OfOverload) it2.next()).getFunction().getSource(), FirErrors.INSTANCE.getNO_VARARG_OVERLOAD_OF_OPERATOR_OF(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                return false;
            }
            if (i == 1) {
                return true;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                if (obj instanceof MainOfOverload) {
                    arrayList.add(obj);
                }
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((MainOfOverload) it3.next()).getFunction().getSource(), FirErrors.INSTANCE.getMULTIPLE_VARARG_OVERLOADS_OF_OPERATOR_OF(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final void checkOverload(FirStandardOverrideChecker firStandardOverrideChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, MainOfOverload mainOfOverload, FirNamedFunctionSymbol firNamedFunctionSymbol) {
            ConeSubstitutor coneSubstitutorBuildTypeParametersSubstitutorIfCompatible = firStandardOverrideChecker.buildTypeParametersSubstitutorIfCompatible((FirCallableDeclaration) mainOfOverload.getFunction().getFir(), (FirCallableDeclaration) firNamedFunctionSymbol.getFir(), true);
            if (coneSubstitutorBuildTypeParametersSubstitutorIfCompatible == null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINCONSISTENT_TYPE_PARAMETERS_IN_OF_OVERLOADS(), (Object) mainOfOverload.getFunction(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
            for (FirValueParameterSymbol firValueParameterSymbol : firNamedFunctionSymbol.getValueParameterSymbols()) {
                if (!isMatchingParameter(firStandardOverrideChecker, mainOfOverload, firValueParameterSymbol, coneSubstitutorBuildTypeParametersSubstitutorIfCompatible)) {
                    KtSourceElement source = firValueParameterSymbol.getSource();
                    KtDiagnosticFactory1<ConeKotlinType> inconsistent_parameter_types_in_of_overloads = FirErrors.INSTANCE.getINCONSISTENT_PARAMETER_TYPES_IN_OF_OVERLOADS();
                    ConeKotlinType mainParameterElementType = mainOfOverload.getMainParameterElementType();
                    mainParameterElementType.getClass();
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) inconsistent_parameter_types_in_of_overloads, (Object) coneSubstitutorBuildTypeParametersSubstitutorIfCompatible.substituteOrSelf(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, mainParameterElementType)), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
            if (mainOfOverload.getFunction() == firNamedFunctionSymbol) {
                return;
            }
            if (!firStandardOverrideChecker.isEqualTypes((FirTypeRef) mainOfOverload.getFunction().getResolvedReturnTypeRef(), (FirTypeRef) firNamedFunctionSymbol.getResolvedReturnTypeRef(), coneSubstitutorBuildTypeParametersSubstitutorIfCompatible)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINCONSISTENT_RETURN_TYPES_IN_OF_OVERLOADS(), (Object) coneSubstitutorBuildTypeParametersSubstitutorIfCompatible.substituteOrSelf(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, mainOfOverload.getFunction().getResolvedReturnType())), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            checkStatus(checkerContext, diagnosticReporter, mainOfOverload.getFunction(), firNamedFunctionSymbol);
        }

        private final void checkStatus(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
            if (!Intrinsics.areEqual(firNamedFunctionSymbol.getResolvedStatus().getVisibility(), firNamedFunctionSymbol2.getResolvedStatus().getVisibility())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol2.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINCONSISTENT_VISIBILITY_IN_OF_OVERLOADS(), (Object) firNamedFunctionSymbol.getResolvedStatus().getVisibility(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (firNamedFunctionSymbol.getRawStatus().isSuspend() != firNamedFunctionSymbol2.getRawStatus().isSuspend()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunctionSymbol2.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getINCONSISTENT_SUSPEND_IN_OF_OVERLOADS(), (Object) checkStatus$suspendString(firNamedFunctionSymbol2), (Object) checkStatus$suspendString(firNamedFunctionSymbol), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }

        private static final String checkStatus$suspendString(FirNamedFunctionSymbol firNamedFunctionSymbol) {
            return firNamedFunctionSymbol.getRawStatus().isSuspend() ? "suspend " : "not suspend";
        }

        private final boolean isMatchingParameter(FirStandardOverrideChecker firStandardOverrideChecker, MainOfOverload mainOfOverload, FirValueParameterSymbol firValueParameterSymbol, ConeSubstitutor coneSubstitutor) {
            ConeKotlinType mainParameterElementType;
            FirResolvedTypeRef firResolvedTypeRef$default;
            if (mainOfOverload.getMainParameter() == firValueParameterSymbol || (mainParameterElementType = mainOfOverload.getMainParameterElementType()) == null || (firResolvedTypeRef$default = UtilsKt.toFirResolvedTypeRef$default(mainParameterElementType, null, null, 3, null)) == null) {
                return true;
            }
            return firStandardOverrideChecker.isEqualTypes((FirTypeRef) firResolvedTypeRef$default, (FirTypeRef) firValueParameterSymbol.getResolvedReturnTypeRef(), coneSubstitutor);
        }

        public final void check(final CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            final List listCreateListBuilder = CollectionsKt.createListBuilder();
            FirHelpersKt.declaredMemberScope(checkerContext, this.companion).processFunctionsByName(OperatorNameConventions.OF, new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.b
                public final Object invoke(Object obj) {
                    return FirOperatorOfChecker.CheckerImpl.check$lambda$0$0(checkerContext, listCreateListBuilder, (FirNamedFunctionSymbol) obj);
                }
            });
            List<? extends OfOverload> listBuild = CollectionsKt.build(listCreateListBuilder);
            if (!listBuild.isEmpty() && checkNumberOfMainOverloads(checkerContext, diagnosticReporter, listBuild)) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : listBuild) {
                    if (obj instanceof MainOfOverload) {
                        arrayList.add(obj);
                    }
                }
                MainOfOverload mainOfOverload = (MainOfOverload) CollectionsKt.single(arrayList);
                FirStandardOverrideChecker firStandardOverrideChecker = new FirStandardOverrideChecker(checkerContext.getSession());
                Iterator<? extends OfOverload> it = listBuild.iterator();
                while (it.hasNext()) {
                    checkOverload(firStandardOverrideChecker, checkerContext, diagnosticReporter, mainOfOverload, it.next().getFunction());
                }
            }
        }

        public final FirRegularClassSymbol getCompanion() {
            return this.companion;
        }
    }

    private FirOperatorOfChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        FirRegularClassSymbol companionObjectSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.CollectionLiterals) || (companionObjectSymbol = firRegularClass.getCompanionObjectSymbol()) == null) {
            return;
        }
        new CheckerImpl(companionObjectSymbol).check(checkerContext, diagnosticReporter);
    }
}
