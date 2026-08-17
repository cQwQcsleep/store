package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011JC\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0017J7\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0019J\f\u0010\u001a\u001a\u00020\u0007*\u00020\u0016H\u0002J\f\u0010\u001b\u001a\u00020\u001c*\u00020\u0016H\u0002J\u0018\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001cH\u0002JC\u0010 \u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020!2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\"J7\u0010#\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0019J\u0014\u0010$\u001a\u00020\u0007*\u00020\f2\u0006\u0010%\u001a\u00020&H\u0002J\u0014\u0010'\u001a\u00020\u0007*\u00020\f2\u0006\u0010%\u001a\u00020&H\u0002J\u001a\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010)*\u00020\f2\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010*\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030+H\u0002J\u001c\u0010,\u001a\u00020\u0007*\u00020\u00022\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J\u001e\u00101\u001a\u0004\u0018\u000102*\u00020\u00022\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmStaticChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkAnnotated", "targetSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "outerProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkForInterface", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;)V", "hasExternalParts", "getMinimumVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "chooseMostSpecific", "a", "b", "checkOverrideCannotBeStatic", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkStaticOnConstOrJvmField", "containerIsInterface", "outerLevel", Argument.Delimiters.none, "containerIsNonCompanionObject", "getContainerAt", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isCompanion", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "hasAnnotationNamedAs", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "findAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmStaticChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmStaticChecker INSTANCE = new FirJvmStaticChecker();

    private FirJvmStaticChecker() {
        super(MppCheckerKind.Common);
    }

    private static final void check$checkIfAnnotated(CheckerContext checkerContext, FirDeclaration firDeclaration, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration2) {
        FirJvmStaticChecker firJvmStaticChecker = INSTANCE;
        FirAnnotation firAnnotationFindAnnotation = firJvmStaticChecker.findAnnotation(firDeclaration2, JvmStandardClassIds.Annotations.INSTANCE.getJvmStatic(), checkerContext.getSession());
        if (firAnnotationFindAnnotation == null) {
            return;
        }
        KtSourceElement source = firAnnotationFindAnnotation.getSource();
        if (source == null && (source = firDeclaration2.getSource()) == null) {
            source = firDeclaration.getSource();
        }
        firJvmStaticChecker.checkAnnotated(checkerContext, diagnosticReporter, firDeclaration2, source, firDeclaration instanceof FirProperty ? (FirProperty) firDeclaration : null);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x004e  */
    private final void checkAnnotated(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement, FirProperty firProperty) {
        if (firDeclaration instanceof FirMemberDeclaration) {
            boolean z = false;
            FirBasedSymbol<?> containerAt = getContainerAt(checkerContext, 0);
            if (containerAt == null) {
                return;
            }
            boolean z2 = containerAt instanceof FirClassSymbol;
            if (z2 && Intrinsics.areEqual(((FirClassSymbol) containerAt).getClassId().getShortClassName(), SpecialNames.ANONYMOUS)) {
                z = true;
            }
            if (z2 && ((FirClassSymbol) containerAt).getClassKind() == ClassKind.OBJECT) {
                FirClassLikeSymbol<?> firClassLikeSymbol = (FirClassLikeSymbol) containerAt;
                if (!isCompanion(firClassLikeSymbol) && z) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJVM_STATIC_NOT_IN_OBJECT_OR_COMPANION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (isCompanion(firClassLikeSymbol) && containerIsInterface(checkerContext, 1)) {
                    checkForInterface(checkerContext, diagnosticReporter, firDeclaration, ktSourceElement);
                }
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJVM_STATIC_NOT_IN_OBJECT_OR_COMPANION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            checkOverrideCannotBeStatic(checkerContext, diagnosticReporter, (FirMemberDeclaration) firDeclaration, ktSourceElement, firProperty);
            checkStaticOnConstOrJvmField(checkerContext, diagnosticReporter, firDeclaration, ktSourceElement);
        }
    }

    private final void checkForInterface(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        if (firDeclaration instanceof FirCallableDeclaration) {
            boolean z = firDeclaration instanceof FirProperty;
            Visibility minimumVisibility = z ? getMinimumVisibility((FirProperty) firDeclaration) : ((FirMemberDeclaration) firDeclaration).getStatus().getVisibility();
            boolean zHasExternalParts = z ? hasExternalParts((FirProperty) firDeclaration) : ((FirMemberDeclaration) firDeclaration).getStatus().isExternal();
            if (!Intrinsics.areEqual(minimumVisibility, Visibilities.Public.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJVM_STATIC_ON_NON_PUBLIC_MEMBER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (zHasExternalParts) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJVM_STATIC_ON_EXTERNAL_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkOverrideCannotBeStatic(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirMemberDeclaration firMemberDeclaration, KtSourceElement ktSourceElement, FirProperty firProperty) {
        if ((firProperty != null ? firProperty.getStatus() : firMemberDeclaration.getStatus()).isOverride() && containerIsNonCompanionObject(checkerContext, 0)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getOVERRIDE_CANNOT_BE_STATIC(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkStaticOnConstOrJvmField(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, KtSourceElement ktSourceElement) {
        FirBackingField backingField;
        if (firDeclaration instanceof FirProperty) {
            if (((FirMemberDeclaration) firDeclaration).getStatus().isConst() || ((backingField = ((FirProperty) firDeclaration).getBackingField()) != null && hasAnnotationNamedAs(backingField, JvmStandardClassIds.Annotations.INSTANCE.getJvmField(), checkerContext.getSession()))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirJvmErrors.INSTANCE.getJVM_STATIC_ON_CONST_OR_JVM_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final Visibility chooseMostSpecific(Visibility a, Visibility b) {
        Integer numCompareTo = a.compareTo(b);
        return (numCompareTo == null || numCompareTo.intValue() <= 0) ? a : b;
    }

    private final boolean containerIsInterface(CheckerContext checkerContext, int i) {
        FirBasedSymbol<?> containerAt = getContainerAt(checkerContext, i);
        return (containerAt instanceof FirClassSymbol) && ((FirClassSymbol) containerAt).getClassKind() == ClassKind.INTERFACE;
    }

    private final boolean containerIsNonCompanionObject(CheckerContext checkerContext, int i) {
        FirBasedSymbol<?> containerAt = getContainerAt(checkerContext, i);
        if (containerAt == null) {
            return false;
        }
        FirRegularClassSymbol firRegularClassSymbol = containerAt instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containerAt : null;
        return (firRegularClassSymbol == null || firRegularClassSymbol.getClassKind() != ClassKind.OBJECT || firRegularClassSymbol.getRawStatus().isCompanion()) ? false : true;
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

    private final FirBasedSymbol<?> getContainerAt(CheckerContext checkerContext, int i) {
        return (FirBasedSymbol) CollectionsKt.getOrNull(CollectionsKt.asReversed(checkerContext.getContainingDeclarations()), i + (CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations()) instanceof FirPropertySymbol ? 1 : 0));
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

    private final boolean hasAnnotationNamedAs(FirDeclaration firDeclaration, ClassId classId, FirSession firSession) {
        return findAnnotation(firDeclaration, classId, firSession) != null;
    }

    private final boolean hasExternalParts(FirProperty firProperty) {
        boolean zIsExternal = firProperty.getStatus().isExternal();
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter != null) {
            zIsExternal = zIsExternal || getter.getStatus().isExternal();
        }
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter != null) {
            return zIsExternal || setter.getStatus().isExternal();
        }
        return zIsExternal;
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
        check$checkIfAnnotated(checkerContext, firDeclaration, diagnosticReporter, firDeclaration);
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
