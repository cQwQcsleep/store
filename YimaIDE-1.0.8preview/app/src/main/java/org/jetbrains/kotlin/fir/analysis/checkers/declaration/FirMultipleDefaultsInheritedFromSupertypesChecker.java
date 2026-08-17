package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMultipleDefaultsInheritedFromSupertypesChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\"#B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ5\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0013JU\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u001eH\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001fJK\u0010 \u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u001eH\u0002R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010!\u0082\u0001\u0002$%¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkFunction", "function", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "reportDiagnosticForImplicitOverride", "k1WouldMiss", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "parameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "basesWithDefaultValues", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;ZLorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Ljava/util/List;)V", "reportDiagnosticForExplicitOverride", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;ZLorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;Ljava/util/List;)V", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirMultipleDefaultsInheritedFromSupertypesChecker extends FirDeclarationChecker<FirClass> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirMultipleDefaultsInheritedFromSupertypesChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMultipleDefaultsInheritedFromSupertypesChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMultipleDefaultsInheritedFromSupertypesChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirMultipleDefaultsInheritedFromSupertypesChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMultipleDefaultsInheritedFromSupertypesChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
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

    public /* synthetic */ FirMultipleDefaultsInheritedFromSupertypesChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(FirClass firClass, FirMultipleDefaultsInheritedFromSupertypesChecker firMultipleDefaultsInheritedFromSupertypesChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firNamedFunctionSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol");
            return null;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
        if (!Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol2), firClass.getSymbol().getLookupTag())) {
            return Unit.INSTANCE;
        }
        firMultipleDefaultsInheritedFromSupertypesChecker.checkFunction(checkerContext, diagnosticReporter, firClass, firNamedFunctionSymbol2);
        return Unit.INSTANCE;
    }

    private final void checkFunction(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        int i;
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        CheckerContext checkerContext2 = checkerContext;
        FirHelpersKt.processOverriddenFunctionsWithActionSafe(checkerContext2, firNamedFunctionSymbol, new Function1<FirNamedFunctionSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMultipleDefaultsInheritedFromSupertypesChecker$checkFunction$$inlined$processOverriddenFunctionsSafe$1
            public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                firNamedFunctionSymbol2.getClass();
                FirFunctionSymbol<?> singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((FirFunctionSymbol<?>) firNamedFunctionSymbol2);
                FirNamedFunctionSymbol firNamedFunctionSymbol3 = singleMatchedExpectForActualOrNull instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) singleMatchedExpectForActualOrNull : null;
                if (firNamedFunctionSymbol3 != null) {
                    firNamedFunctionSymbol2 = firNamedFunctionSymbol3;
                }
                List<FirValueParameterSymbol> valueParameterSymbols = firNamedFunctionSymbol2.getValueParameterSymbols();
                if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                    Iterator<T> it = valueParameterSymbols.iterator();
                    while (it.hasNext()) {
                        if (((FirValueParameterSymbol) it.next()).getHasDefaultValue()) {
                            linkedHashSet.add(firNamedFunctionSymbol2);
                            break;
                        }
                    }
                }
                return ProcessorAction.NEXT;
            }
        });
        boolean zAreEqual = Intrinsics.areEqual(firNamedFunctionSymbol.getOrigin(), FirDeclarationOrigin.Source.INSTANCE);
        List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firClass);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Iterator<T> it = superConeTypes.iterator();
        while (it.hasNext()) {
            linkedHashSet2.add(((ConeClassLikeType) it.next()).getLookupTag());
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : linkedHashSet) {
            if (CollectionsKt.contains(linkedHashSet2, ClassMembersKt.containingClassLookupTag((FirNamedFunctionSymbol) obj))) {
                arrayList.add(obj);
            }
        }
        int i2 = 0;
        for (FirValueParameterSymbol firValueParameterSymbol : firNamedFunctionSymbol.getValueParameterSymbols()) {
            int i3 = i2 + 1;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : linkedHashSet) {
                if (((FirNamedFunctionSymbol) obj2).getValueParameterSymbols().get(i2).getHasDefaultValue()) {
                    arrayList2.add(obj2);
                }
            }
            if (arrayList2.size() > 1) {
                if (arrayList.isEmpty()) {
                    i = 0;
                } else {
                    Iterator it2 = arrayList.iterator();
                    i = 0;
                    while (it2.hasNext()) {
                        if (((FirNamedFunctionSymbol) it2.next()).getValueParameterSymbols().get(i2).getHasDefaultValue() && (i = i + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                boolean z = i <= 1;
                if (!zAreEqual) {
                    reportDiagnosticForImplicitOverride(checkerContext2, diagnosticReporter, z, firClass.getSource(), firNamedFunctionSymbol.getName(), firValueParameterSymbol, arrayList2);
                    return;
                }
                reportDiagnosticForExplicitOverride(checkerContext, diagnosticReporter, z, firNamedFunctionSymbol.getName(), firValueParameterSymbol, arrayList2);
            }
            checkerContext2 = checkerContext;
            i2 = i3;
        }
    }

    private final void reportDiagnosticForExplicitOverride(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, boolean z, Name name, FirValueParameterSymbol firValueParameterSymbol, List<? extends FirNamedFunctionSymbol> list) {
        if (z) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), (KtDiagnosticFactoryForDeprecation3<Name, FirValueParameterSymbol, List<? extends FirNamedFunctionSymbol>>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getMULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES_DEPRECATION()), name, firValueParameterSymbol, list, (64 & 64) != 0 ? null : null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), (KtDiagnosticFactory3<Name, FirValueParameterSymbol, List<? extends FirNamedFunctionSymbol>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getMULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES()), name, firValueParameterSymbol, list, (64 & 64) != 0 ? null : null);
        }
    }

    private final void reportDiagnosticForImplicitOverride(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, boolean z, KtSourceElement ktSourceElement, Name name, FirValueParameterSymbol firValueParameterSymbol, List<? extends FirNamedFunctionSymbol> list) {
        if (z) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation3<Name, FirValueParameterSymbol, List<? extends FirNamedFunctionSymbol>>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getMULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES_WHEN_NO_EXPLICIT_OVERRIDE_DEPRECATION()), name, firValueParameterSymbol, list, (64 & 64) != 0 ? null : null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<Name, FirValueParameterSymbol, List<? extends FirNamedFunctionSymbol>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getMULTIPLE_DEFAULTS_INHERITED_FROM_SUPERTYPES_WHEN_NO_EXPLICIT_OVERRIDE()), name, firValueParameterSymbol, list, (64 & 64) != 0 ? null : null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (firClass.getStatus().isExternal()) {
            return;
        }
        FirContainingNamesAwareScopeKt.processAllFunctions(FirHelpersKt.unsubstitutedScope(checkerContext, firClass), new Function1() { // from class: ta5
            public final Object invoke(Object obj) {
                return FirMultipleDefaultsInheritedFromSupertypesChecker.b(firClass, this, checkerContext, diagnosticReporter, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    private FirMultipleDefaultsInheritedFromSupertypesChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
