package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeObjCRefinementOverridesChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00122\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0003\u0010\u0011\u0012B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000f\u0082\u0001\u0002\u0013\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "Regular", "ForExpectClass", "Companion", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker$Regular;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNativeObjCRefinementOverridesChecker extends FirDeclarationChecker<FirClass> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirNativeObjCRefinementOverridesChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeObjCRefinementOverridesChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirNativeObjCRefinementOverridesChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.p001native.checkers.FirNativeObjCRefinementOverridesChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
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

    public /* synthetic */ FirNativeObjCRefinementOverridesChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    public static Unit b(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeScope firTypeScope, FirClass firClass, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!ClassMembersKt.isIntersectionOverride(firNamedFunctionSymbol)) {
            return Unit.INSTANCE;
        }
        INSTANCE.check(checkerContext, diagnosticReporter, firTypeScope, firNamedFunctionSymbol, firClass, CollectionsKt.emptyList(), CollectionsKt.emptyList());
        return Unit.INSTANCE;
    }

    public static Unit c(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeScope firTypeScope, FirClass firClass, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (!ClassMembersKt.isIntersectionOverride(firVariableSymbol)) {
            return Unit.INSTANCE;
        }
        INSTANCE.check(checkerContext, diagnosticReporter, firTypeScope, firVariableSymbol, firClass, CollectionsKt.emptyList(), CollectionsKt.emptyList());
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        final FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
        FirContainingNamesAwareScopeKt.processAllFunctions(firTypeScopeUnsubstitutedScope, new Function1() { // from class: db5
            public final Object invoke(Object obj) {
                return FirNativeObjCRefinementOverridesChecker.b(checkerContext, diagnosticReporter, firTypeScopeUnsubstitutedScope, firClass, (FirNamedFunctionSymbol) obj);
            }
        });
        FirContainingNamesAwareScopeKt.processAllProperties(firTypeScopeUnsubstitutedScope, new Function1() { // from class: eb5
            public final Object invoke(Object obj) {
                return FirNativeObjCRefinementOverridesChecker.c(checkerContext, diagnosticReporter, firTypeScopeUnsubstitutedScope, firClass, (FirVariableSymbol) obj);
            }
        });
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J[\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014J,\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bH\u0002J$\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002JE\u0010\u001b\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001c\u001a\u00020\u000f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u0011H\u0002R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u001f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCRefinementOverridesChecker$Companion;", Argument.Delimiters.none, "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "baseScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "memberSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "declarationToReport", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "objCAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "swiftAnnotations", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;Ljava/util/List;)V", "inheritsRefinedAnnotations", "Lkotlin/Pair;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "hasRefinedAnnotations", "reportIncompatibleOverride", "declaration", "annotations", "notRefinedSupers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/List;Ljava/util/List;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Pair<Boolean, Boolean> hasRefinedAnnotations(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
            Iterator<FirAnnotation> it = firCallableSymbol.getResolvedAnnotationsWithClassIds().iterator();
            boolean z = false;
            boolean z2 = false;
            while (it.hasNext()) {
                FirClassLikeSymbol<?> annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(it.next(), firSession);
                List<FirAnnotation> resolvedAnnotationsWithClassIds = annotationClassLikeSymbol != null ? annotationClassLikeSymbol.getResolvedAnnotationsWithClassIds() : null;
                if (resolvedAnnotationsWithClassIds == null) {
                    resolvedAnnotationsWithClassIds = CollectionsKt.emptyList();
                }
                Iterator<FirAnnotation> it2 = resolvedAnnotationsWithClassIds.iterator();
                while (it2.hasNext()) {
                    ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId(it2.next(), firSession);
                    FirNativeObjCRefinementChecker firNativeObjCRefinementChecker = FirNativeObjCRefinementChecker.INSTANCE;
                    if (Intrinsics.areEqual(annotationClassId, firNativeObjCRefinementChecker.getHidesFromObjCClassId())) {
                        z = true;
                        break;
                    }
                    if (Intrinsics.areEqual(annotationClassId, firNativeObjCRefinementChecker.getRefinesInSwiftClassId())) {
                        z2 = true;
                        break;
                    }
                }
                if (z && z2) {
                    Boolean bool = Boolean.TRUE;
                    return TuplesKt.to(bool, bool);
                }
            }
            return TuplesKt.to(Boolean.valueOf(z), Boolean.valueOf(z2));
        }

        private final Pair<Boolean, Boolean> inheritsRefinedAnnotations(FirCallableSymbol<?> firCallableSymbol, FirSession firSession, FirTypeScope firTypeScope) {
            Pair<Boolean, Boolean> pairHasRefinedAnnotations = hasRefinedAnnotations(firCallableSymbol, firSession);
            Boolean bool = (Boolean) pairHasRefinedAnnotations.component1();
            boolean zBooleanValue = bool.booleanValue();
            Boolean bool2 = (Boolean) pairHasRefinedAnnotations.component2();
            boolean zBooleanValue2 = bool2.booleanValue();
            if (zBooleanValue && zBooleanValue2) {
                Boolean bool3 = Boolean.TRUE;
                return TuplesKt.to(bool3, bool3);
            }
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) CollectionsKt.firstOrNull(FirTypeScopeKt.getDirectOverriddenMembersWithBaseScopeSafe(firTypeScope, firCallableSymbol));
            if (memberWithBaseScope == null) {
                return TuplesKt.to(bool, bool2);
            }
            Pair<Boolean, Boolean> pairInheritsRefinedAnnotations = inheritsRefinedAnnotations(memberWithBaseScope.component1(), firSession, memberWithBaseScope.getBaseScope());
            boolean zBooleanValue3 = ((Boolean) pairInheritsRefinedAnnotations.component1()).booleanValue();
            boolean zBooleanValue4 = ((Boolean) pairInheritsRefinedAnnotations.component2()).booleanValue();
            boolean z = true;
            Boolean boolValueOf = Boolean.valueOf(zBooleanValue || zBooleanValue3);
            if (!zBooleanValue2 && !zBooleanValue4) {
                z = false;
            }
            return TuplesKt.to(boolValueOf, Boolean.valueOf(z));
        }

        private final void reportIncompatibleOverride(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, List<? extends FirAnnotation> list, List<? extends FirCallableSymbol<?>> list2) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) it.next());
                FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
                if (regularClassSymbol != null) {
                    arrayList.add(regularClassSymbol);
                }
            }
            if (list.isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firDeclaration.getSource(), (KtDiagnosticFactory2) FirNativeErrors.INSTANCE.getINCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE(), (Object) firDeclaration.getSymbol(), (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                return;
            }
            Iterator<? extends FirAnnotation> it2 = list.iterator();
            while (it2.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it2.next().getSource(), (KtDiagnosticFactory2) FirNativeErrors.INSTANCE.getINCOMPATIBLE_OBJC_REFINEMENT_OVERRIDE(), (Object) firDeclaration.getSymbol(), (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }

        public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol, FirDeclaration firDeclaration, List<? extends FirAnnotation> list, List<? extends FirAnnotation> list2) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firTypeScope.getClass();
            firCallableSymbol.getClass();
            firDeclaration.getClass();
            list.getClass();
            list2.getClass();
            List<MemberWithBaseScope<FirCallableSymbol<?>>> directOverriddenMembersWithBaseScopeSafe = FirTypeScopeKt.getDirectOverriddenMembersWithBaseScopeSafe(firTypeScope, firCallableSymbol);
            if (directOverriddenMembersWithBaseScopeSafe.isEmpty()) {
                return;
            }
            boolean z = !list.isEmpty();
            boolean z2 = !list2.isEmpty();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (MemberWithBaseScope<FirCallableSymbol<?>> memberWithBaseScope : directOverriddenMembersWithBaseScopeSafe) {
                FirCallableSymbol<?> firCallableSymbolComponent1 = memberWithBaseScope.component1();
                Pair<Boolean, Boolean> pairInheritsRefinedAnnotations = inheritsRefinedAnnotations(firCallableSymbolComponent1, checkerContext.getSession(), memberWithBaseScope.getBaseScope());
                boolean zBooleanValue = ((Boolean) pairInheritsRefinedAnnotations.component1()).booleanValue();
                boolean zBooleanValue2 = ((Boolean) pairInheritsRefinedAnnotations.component2()).booleanValue();
                if (zBooleanValue) {
                    z = true;
                } else {
                    arrayList.add(firCallableSymbolComponent1);
                }
                if (zBooleanValue2) {
                    z2 = true;
                } else {
                    arrayList2.add(firCallableSymbolComponent1);
                }
            }
            if (z && !arrayList.isEmpty()) {
                reportIncompatibleOverride(checkerContext, diagnosticReporter, firDeclaration, list, arrayList);
            }
            if (!z2 || arrayList2.isEmpty()) {
                return;
            }
            reportIncompatibleOverride(checkerContext, diagnosticReporter, firDeclaration, list2, arrayList2);
        }

        private Companion() {
        }
    }

    private FirNativeObjCRefinementOverridesChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}
