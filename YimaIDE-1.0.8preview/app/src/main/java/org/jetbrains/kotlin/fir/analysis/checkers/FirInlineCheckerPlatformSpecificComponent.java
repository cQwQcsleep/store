package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002 !B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011JA\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016H\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019J%\u0010\u001a\u001a\u00020\u0005H\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001bJ\u001a\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00000\u0016H\u0017b\u0002\b\u001f¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "isGenerallyOk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "checkSuspendFunctionalParameterWithDefaultValue", Argument.Delimiters.none, "param", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)V", "checkParametersWithInheritedDefaultValues", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "overriddenSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Ljava/util/List;)V", "shouldReportRegularOverridesWithDefaultParameters", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)Z", "createComposed", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent$Composed;", "components", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "NonJvmDefault", "Composed", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirInlineCheckerPlatformSpecificComponent implements FirComposableSessionComponent<FirInlineCheckerPlatformSpecificComponent> {

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0017JA\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u0004H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u001eR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent$Composed;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "nonDuplicatingComponents", "isGenerallyOk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Z", "checkSuspendFunctionalParameterWithDefaultValue", Argument.Delimiters.none, "param", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)V", "checkParametersWithInheritedDefaultValues", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "overriddenSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Ljava/util/List;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirInlineCheckerPlatformSpecificComponent implements FirComposableSessionComponent.Composed<FirInlineCheckerPlatformSpecificComponent> {
        private final List<FirInlineCheckerPlatformSpecificComponent> components;
        private final List<FirInlineCheckerPlatformSpecificComponent> nonDuplicatingComponents;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirInlineCheckerPlatformSpecificComponent> list) {
            List<FirInlineCheckerPlatformSpecificComponent> components;
            list.getClass();
            this.components = list;
            if (getComponents().size() == 1) {
                components = getComponents();
            } else {
                List<FirInlineCheckerPlatformSpecificComponent> components2 = getComponents();
                ArrayList arrayList = new ArrayList();
                for (Object obj : components2) {
                    if (((FirInlineCheckerPlatformSpecificComponent) obj) != NonJvmDefault.INSTANCE) {
                        arrayList.add(obj);
                    }
                }
                components = arrayList;
            }
            this.nonDuplicatingComponents = components;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponent
        public void checkParametersWithInheritedDefaultValues(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction, List<? extends FirCallableSymbol<? extends FirCallableDeclaration>> list) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firNamedFunction.getClass();
            list.getClass();
            Iterator<T> it = this.nonDuplicatingComponents.iterator();
            while (it.hasNext()) {
                ((FirInlineCheckerPlatformSpecificComponent) it.next()).checkParametersWithInheritedDefaultValues(checkerContext, diagnosticReporter, firNamedFunction, list);
            }
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponent
        public void checkSuspendFunctionalParameterWithDefaultValue(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firValueParameter.getClass();
            Iterator<T> it = this.nonDuplicatingComponents.iterator();
            while (it.hasNext()) {
                ((FirInlineCheckerPlatformSpecificComponent) it.next()).checkSuspendFunctionalParameterWithDefaultValue(checkerContext, diagnosticReporter, firValueParameter);
            }
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirInlineCheckerPlatformSpecificComponent> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponent
        public boolean isGenerallyOk(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firFunction.getClass();
            List<FirInlineCheckerPlatformSpecificComponent> list = this.nonDuplicatingComponents;
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((FirInlineCheckerPlatformSpecificComponent) it.next()).isGenerallyOk(checkerContext, diagnosticReporter, firFunction)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent$NonJvmDefault;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirInlineCheckerPlatformSpecificComponent;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NonJvmDefault extends FirInlineCheckerPlatformSpecificComponent {
        public static final NonJvmDefault INSTANCE = new NonJvmDefault();

        private NonJvmDefault() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkParametersWithInheritedDefaultValues$checkDefaultParamsRecursive(final SmartSet<FirCallableSymbol<?>> smartSet, final CheckerContext checkerContext, final boolean[] zArr, FirCallableSymbol<?> firCallableSymbol) {
        if (smartSet.add(firCallableSymbol) && (firCallableSymbol instanceof FirFunctionSymbol)) {
            int i = 0;
            for (Object obj : ((FirFunctionSymbol) firCallableSymbol).getValueParameterSymbols()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (((FirValueParameterSymbol) obj).getHasDefaultValue() && i < zArr.length) {
                    zArr[i] = true;
                }
                i = i2;
            }
            FirNamedFunctionSymbol firNamedFunctionSymbol = firCallableSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firCallableSymbol : null;
            if (firNamedFunctionSymbol != null) {
                FirHelpersKt.processOverriddenFunctionsWithActionSafe(checkerContext, firNamedFunctionSymbol, new Function1<FirNamedFunctionSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.FirInlineCheckerPlatformSpecificComponent$checkParametersWithInheritedDefaultValues$checkDefaultParamsRecursive$$inlined$processOverriddenFunctionsSafe$1
                    public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                        firNamedFunctionSymbol2.getClass();
                        FirInlineCheckerPlatformSpecificComponent.checkParametersWithInheritedDefaultValues$checkDefaultParamsRecursive(smartSet, checkerContext, zArr, firNamedFunctionSymbol2);
                        return ProcessorAction.NEXT;
                    }
                });
            }
        }
    }

    public void checkParametersWithInheritedDefaultValues(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction, List<? extends FirCallableSymbol<? extends FirCallableDeclaration>> list) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        list.getClass();
        boolean[] zArr = new boolean[firNamedFunction.getValueParameters().size()];
        SmartSet smartSetCreate = SmartSet.Companion.create();
        Iterator<? extends FirCallableSymbol<? extends FirCallableDeclaration>> it = list.iterator();
        while (it.hasNext()) {
            checkParametersWithInheritedDefaultValues$checkDefaultParamsRecursive(smartSetCreate, checkerContext, zArr, it.next());
        }
        boolean zShouldReportRegularOverridesWithDefaultParameters = shouldReportRegularOverridesWithDefaultParameters(checkerContext, diagnosticReporter);
        int i = 0;
        for (Object obj : firNamedFunction.getValueParameters()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirValueParameter firValueParameter = (FirValueParameter) obj;
            if (firValueParameter.getDefaultValue() == null && zArr[i]) {
                if (zShouldReportRegularOverridesWithDefaultParameters) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNOT_YET_SUPPORTED_IN_INLINE(), (Object) "Parameters with inherited default values", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), FirErrors.INSTANCE.getNOT_YET_SUPPORTED_IN_INLINE_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            i = i2;
        }
    }

    public void checkSuspendFunctionalParameterWithDefaultValue(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firValueParameter.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirInlineCheckerPlatformSpecificComponent> components) {
        components.getClass();
        return new Composed(components);
    }

    public boolean isGenerallyOk(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        return true;
    }

    public boolean shouldReportRegularOverridesWithDefaultParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        return LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidOverriddenDefaultParametersInInline);
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirInlineCheckerPlatformSpecificComponent>) list);
    }
}
