package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J7\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0015J7\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0015J\f\u0010\u0017\u001a\u00020\u0018*\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J7\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0015J\u0010\u0010\u001e\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u001fH\u0002J\u0010\u0010 \u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u001fH\u0002J\u001e\u0010!\u001a\u0004\u0018\u00010\"*\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsStaticChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkAnnotated", "targetSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkVisibility", "getMinimumVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "chooseMostSpecific", "a", "b", "checkStaticOnConst", "containerIsInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "isCompanion", "findAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsStaticChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJsStaticChecker INSTANCE = new FirJsStaticChecker();

    private FirJsStaticChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$checkIfAnnotated(CheckerContext checkerContext, FirDeclaration firDeclaration, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration2) {
        if (FirAnnotationUtilsKt.hasAnnotation(firDeclaration2, JsStandardClassIds.Annotations.JsStatic, checkerContext.getSession())) {
            KtSourceElement source = firDeclaration2.getSource();
            if (source == null) {
                source = firDeclaration.getSource();
            }
            INSTANCE.checkAnnotated(checkerContext, diagnosticReporter, firDeclaration2, source);
        }
    }

    private final void checkAnnotated(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        KtSourceElement ktSourceElement2;
        if (firDeclaration instanceof FirMemberDeclaration) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firDeclaration);
            if (containingClassSymbol == null || !isCompanion(containingClassSymbol) || (containerIsInterface(containingClassSymbol) && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.JsStaticInInterface))) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, FirJsErrors.INSTANCE.getJS_STATIC_NOT_IN_CLASS_COMPANION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
            }
            checkStaticOnConst(checkerContext2, diagnosticReporter2, firDeclaration, ktSourceElement2);
            checkVisibility(checkerContext2, diagnosticReporter2, firDeclaration, ktSourceElement2);
        }
    }

    private final void checkStaticOnConst(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        if ((firDeclaration instanceof FirProperty) && ((FirMemberDeclaration) firDeclaration).getStatus().isConst()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_STATIC_ON_CONST(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkVisibility(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        if (firDeclaration instanceof FirCallableDeclaration) {
            if (Intrinsics.areEqual(firDeclaration instanceof FirProperty ? getMinimumVisibility((FirProperty) firDeclaration) : ((FirMemberDeclaration) firDeclaration).getStatus().getVisibility(), Visibilities.Public.INSTANCE)) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJsErrors.INSTANCE.getJS_STATIC_ON_NON_PUBLIC_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final Visibility chooseMostSpecific(Visibility a, Visibility b) {
        Integer numCompareTo = a.compareTo(b);
        return (numCompareTo == null || numCompareTo.intValue() <= 0) ? a : b;
    }

    private final boolean containerIsInterface(FirClassLikeSymbol<?> firClassLikeSymbol) {
        ClassKind classKind;
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firClassLikeSymbol);
        return (containingClassSymbol == null || (classKind = FirHelpersKt.getClassKind(containingClassSymbol)) == null || classKind != ClassKind.INTERFACE) ? false : true;
    }

    private final FirAnnotation findAnnotation(FirDeclaration firDeclaration, ClassId classId, FirSession firSession) {
        Object next;
        Iterator<T> it = firDeclaration.getAnnotations().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getConeType(((FirAnnotation) next).getAnnotationTypeRef()), firSession), classId)) {
                return (FirAnnotation) next;
            }
        }
        next = null;
        return (FirAnnotation) next;
    }

    private final Visibility getMinimumVisibility(FirProperty firProperty) {
        Visibility visibility = firProperty.getStatus().getVisibility();
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter != null) {
            visibility = INSTANCE.chooseMostSpecific(visibility, getter.getStatus().getVisibility());
        }
        FirPropertyAccessor setter = firProperty.getSetter();
        return setter != null ? INSTANCE.chooseMostSpecific(visibility, setter.getStatus().getVisibility()) : visibility;
    }

    private final boolean isCompanion(FirClassLikeSymbol<?> firClassLikeSymbol) {
        FirRegularClassSymbol firRegularClassSymbol = firClassLikeSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) firClassLikeSymbol : null;
        return firRegularClassSymbol != null && firRegularClassSymbol.getRawStatus().isCompanion();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if ((firDeclaration instanceof FirConstructor) || (firDeclaration instanceof FirPropertyAccessor)) {
            return;
        }
        if (findAnnotation(firDeclaration, JsStandardClassIds.Annotations.JsStatic, checkerContext.getSession()) != null) {
            checkAnnotated(checkerContext, diagnosticReporter, firDeclaration, firDeclaration.getSource());
        }
        if (firDeclaration instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firDeclaration;
            FirPropertyAccessor getter = firProperty.getGetter();
            if (getter != null) {
                check$checkIfAnnotated(checkerContext, firDeclaration, diagnosticReporter, getter);
            }
            FirPropertyAccessor setter = firProperty.getSetter();
            if (setter != null) {
                check$checkIfAnnotated(checkerContext, firDeclaration, diagnosticReporter, setter);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
