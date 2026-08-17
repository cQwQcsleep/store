package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsInheritanceClassChecker;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirWebCommonHelpersKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\u0019\u001aB\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u0015\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001f\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u0002H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0018\u0082\u0001\u0002\u001b\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "isBuiltinFunctionalTypeOrSubtype", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isSuspendFunctionTypeOrSubtype", "findFakeMethodOverridingExternalWithOptionalParams", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker$Regular;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirJsInheritanceClassChecker extends FirDeclarationChecker<FirClass> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirJsInheritanceClassChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsInheritanceClassChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsInheritanceClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirJsInheritanceClassChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsInheritanceClassChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
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

    public /* synthetic */ FirJsInheritanceClassChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    private final FirNamedFunctionSymbol findFakeMethodOverridingExternalWithOptionalParams(CheckerContext checkerContext, FirClass firClass) {
        Object next;
        Collection<FirNamedFunctionSymbol> collectionCollectAllFunctions = FirContainingNamesAwareScopeKt.collectAllFunctions(FirHelpersKt.unsubstitutedScope(checkerContext, firClass.getSymbol()));
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionCollectAllFunctions) {
            if (obj instanceof FirIntersectionOverrideFunctionSymbol) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            FirIntersectionOverrideFunctionSymbol firIntersectionOverrideFunctionSymbol = (FirIntersectionOverrideFunctionSymbol) obj2;
            if (Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(firIntersectionOverrideFunctionSymbol), firClass.getSymbol()) && !firIntersectionOverrideFunctionSymbol.getIntersections().isEmpty()) {
                arrayList2.add(obj2);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (FirJsHelpersKt.isOverridingExternalWithOptionalParams(checkerContext, (FirIntersectionOverrideFunctionSymbol) next)) {
                return (FirNamedFunctionSymbol) next;
            }
        }
        next = null;
        return (FirNamedFunctionSymbol) next;
    }

    private final boolean isBuiltinFunctionalTypeOrSubtype(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        return TypeComponentsKt.getTypeContext(firSession).isBuiltinFunctionTypeOrSubtype(coneClassLikeType);
    }

    private final boolean isSuspendFunctionTypeOrSubtype(ConeClassLikeType coneClassLikeType, final FirSession firSession) {
        return TypeComponentsKt.getTypeContext(firSession).isTypeOrSubtypeOf(coneClassLikeType, new Function1() { // from class: r95
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirJsInheritanceClassChecker.isSuspendFunctionTypeOrSubtype$lambda$0$0(firSession, (ConeKotlinType) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSuspendFunctionTypeOrSubtype$lambda$0$0(FirSession firSession, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(coneKotlinType, firSession);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FirNamedFunctionSymbol firNamedFunctionSymbolFindFakeMethodOverridingExternalWithOptionalParams;
        FirClassLikeSymbol<?> symbol;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        FirSession session = checkerContext.getSession();
        boolean zIsEffectivelyExternal = FirWebCommonHelpersKt.isEffectivelyExternal(firClass.getSymbol(), session);
        if (zIsEffectivelyExternal && firClass.getClassKind() != ClassKind.ANNOTATION_CLASS) {
            for (ConeClassLikeType coneClassLikeType : FirDeclarationUtilKt.getSuperConeTypes(firClass)) {
                if (!ConeBuiltinTypeUtilsKt.isAnyOrNullableAny(coneClassLikeType) && !ConeBuiltinTypeUtilsKt.isThrowableOrNullableThrowable(coneClassLikeType) && !ConeBuiltinTypeUtilsKt.isEnum(coneClassLikeType) && (symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType)) != null && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol)) != null && !FirWebCommonHelpersKt.isEffectivelyExternal(firRegularClassSymbolFullyExpandedClass, session) && !firRegularClassSymbolFullyExpandedClass.getRawStatus().isExpect()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getEXTERNAL_TYPE_EXTENDS_NON_EXTERNAL_TYPE(), (Object) coneClassLikeType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
        if (!zIsEffectivelyExternal && (firNamedFunctionSymbolFindFakeMethodOverridingExternalWithOptionalParams = findFakeMethodOverridingExternalWithOptionalParams(checkerContext, firClass)) != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getOVERRIDING_EXTERNAL_FUN_WITH_OPTIONAL_PARAMS_WITH_FAKE(), (Object) firNamedFunctionSymbolFindFakeMethodOverridingExternalWithOptionalParams, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.JsAllowImplementingFunctionInterface)) {
            List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firClass);
            if ((superConeTypes instanceof Collection) && superConeTypes.isEmpty()) {
                return;
            }
            for (ConeClassLikeType coneClassLikeType2 : superConeTypes) {
                if (isBuiltinFunctionalTypeOrSubtype(coneClassLikeType2, session) && !isSuspendFunctionTypeOrSubtype(coneClassLikeType2, session)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), FirJsErrors.INSTANCE.getIMPLEMENTING_FUNCTION_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
        }
    }

    private FirJsInheritanceClassChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
