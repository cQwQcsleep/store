package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeObjCOverrideInitChecker;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.name.NativeStandardInteropNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCOverrideInitChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCOverrideInitChecker extends FirDeclarationChecker<FirClass> {
    public static final FirNativeObjCOverrideInitChecker INSTANCE = new FirNativeObjCOverrideInitChecker();

    private FirNativeObjCOverrideInitChecker() {
        super(MppCheckerKind.Platform);
    }

    public static Unit b(List list, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        list.add(firConstructorSymbol);
        return Unit.INSTANCE;
    }

    public static Unit c(CheckerContext checkerContext, FirFunctionSymbol firFunctionSymbol, DiagnosticReporter diagnosticReporter, FirConstructorSymbol firConstructorSymbol, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if ((firCallableSymbol instanceof FirNamedFunctionSymbol) && CollectionsKt.contains(FirHelpersKt.directOverriddenFunctionsSafe(checkerContext, (FirNamedFunctionSymbol) firCallableSymbol), firFunctionSymbol) && !ClassMembersKt.isSubstitutionOrIntersectionOverride((FirCallableSymbol<?>) firCallableSymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getCONSTRUCTOR_OVERRIDES_ALREADY_OVERRIDDEN_OBJC_INITIALIZER(), (Object) NativeStandardInteropNames.INSTANCE.getObjCOverrideInitClassId().asSingleFqName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        return Unit.INSTANCE;
    }

    private static final void check$checkCanGenerateOverrideInit(FirSession firSession, final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, FirClass firClass, final FirConstructorSymbol firConstructorSymbol) {
        firClass.getClass();
        FirRegularClass firRegularClass = (FirRegularClass) firClass;
        FirRegularClassSymbol superClassSymbolOrAny = SupertypeUtilsKt.getSuperClassSymbolOrAny(firRegularClass.getSymbol(), firSession);
        if (superClassSymbolOrAny == null) {
            return;
        }
        List<FirConstructorSymbol> listCheck$constructors = check$constructors(superClassSymbolOrAny, firSession);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listCheck$constructors) {
            if (check$overridesConstructor(firConstructorSymbol, (FirConstructorSymbol) obj)) {
                arrayList.add(obj);
            }
        }
        List list = CollectionsKt.toList(arrayList);
        FirConstructorSymbol firConstructorSymbol2 = (FirConstructorSymbol) CollectionsKt.singleOrNull(list);
        if (firConstructorSymbol2 != null) {
            final FirFunctionSymbol<?> objCInitMethod = FirObjCInteropKt.getObjCInitMethod(firConstructorSymbol2, firSession);
            objCInitMethod.getClass();
            DeclarationUtilsKt.processAllDeclaredCallables$default(firRegularClass.getSymbol(), checkerContext.getSession(), null, new Function1() { // from class: bb5
                public final Object invoke(Object obj2) {
                    return FirNativeObjCOverrideInitChecker.c(checkerContext, objCInitMethod, diagnosticReporter, firConstructorSymbol, (FirCallableSymbol) obj2);
                }
            }, 2, null);
        } else if (list.isEmpty()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getCONSTRUCTOR_DOES_NOT_OVERRIDE_ANY_SUPER_CONSTRUCTOR(), (Object) NativeStandardInteropNames.INSTANCE.getObjCOverrideInitClassId().asSingleFqName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firConstructorSymbol.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getCONSTRUCTOR_MATCHES_SEVERAL_SUPER_CONSTRUCTORS(), (Object) NativeStandardInteropNames.INSTANCE.getObjCOverrideInitClassId().asSingleFqName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private static final void check$checkKotlinObjCClass(CheckerContext checkerContext, FirSession firSession, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        for (FirConstructorSymbol firConstructorSymbol : DeclarationUtilsKt.constructors(firClass, checkerContext.getSession())) {
            if (FirAnnotationUtilsKt.hasAnnotation(firConstructorSymbol, NativeStandardInteropNames.INSTANCE.getObjCOverrideInitClassId(), firSession)) {
                check$checkCanGenerateOverrideInit(firSession, checkerContext, diagnosticReporter, firClass, firConstructorSymbol);
            }
        }
    }

    private static final List<FirConstructorSymbol> check$constructors(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        final ArrayList arrayList = new ArrayList();
        FirDeclaredMemberScopeProviderKt.declaredMemberScope(firSession, firClassSymbol, (FirResolvePhase) null).processDeclaredConstructors(new Function1() { // from class: cb5
            public final Object invoke(Object obj) {
                return FirNativeObjCOverrideInitChecker.b(arrayList, (FirConstructorSymbol) obj);
            }
        });
        return arrayList;
    }

    private static final boolean check$overridesConstructor(FirConstructorSymbol firConstructorSymbol, FirConstructorSymbol firConstructorSymbol2) {
        List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbol.getValueParameterSymbols();
        List<FirValueParameterSymbol> valueParameterSymbols2 = firConstructorSymbol2.getValueParameterSymbols();
        if (valueParameterSymbols.size() != valueParameterSymbols2.size()) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(valueParameterSymbols, valueParameterSymbols2);
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return true;
        }
        for (Pair pair : listZip) {
            FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) pair.component1();
            FirValueParameterSymbol firValueParameterSymbol2 = (FirValueParameterSymbol) pair.component2();
            if (!Intrinsics.areEqual(firValueParameterSymbol.getName(), firValueParameterSymbol2.getName()) || !Intrinsics.areEqual(firValueParameterSymbol.getResolvedReturnType(), firValueParameterSymbol2.getResolvedReturnType())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirSession session = checkerContext.getSession();
        if (firClass.getStatus().isExpect() || !FirObjCInteropKt.isKotlinObjCClass(firClass.getSymbol(), checkerContext.getSession())) {
            return;
        }
        check$checkKotlinObjCClass(checkerContext, session, diagnosticReporter, firClass);
    }
}
