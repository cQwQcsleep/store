package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.JvmFieldApplicabilityProblem;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmFieldApplicabilityChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u0007*\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\f\u0010\u0015\u001a\u00020\u0007*\u00020\u0002H\u0002J\u0014\u0010\u0016\u001a\u00020\u0007*\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0014\u0010\u001a\u001a\u00020\u0007*\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0019\u0010\u001c\u001a\u00020\u0007H\u0002R\u00020\fj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmFieldApplicabilityChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "isOverridable", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "hasCustomAccessor", "isInsideCompanionObjectOfInterface", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isInterfaceCompanionWithPublicJvmFieldProperties", "hasJvmFieldAnnotation", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "isInsideJvmMultifileClassFile", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmFieldApplicabilityChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirJvmFieldApplicabilityChecker INSTANCE = new FirJvmFieldApplicabilityChecker();

    private FirJvmFieldApplicabilityChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0039  */
    public static Unit b(Ref.BooleanRef booleanRef, FirSession firSession, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (!booleanRef.element || !(firCallableSymbol instanceof FirPropertySymbol)) {
            return Unit.INSTANCE;
        }
        if (Intrinsics.areEqual(firCallableSymbol.getResolvedStatus().getVisibility(), Visibilities.Public.INSTANCE)) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
            if (firPropertySymbol.isVar() || firCallableSymbol.getResolvedStatus().getModality() != Modality.FINAL || !INSTANCE.hasJvmFieldAnnotation(firPropertySymbol, firSession)) {
                booleanRef.element = false;
            }
        } else {
            booleanRef.element = false;
        }
        return Unit.INSTANCE;
    }

    private final boolean hasCustomAccessor(FirProperty firProperty) {
        KtSourceElement source;
        KtSourceElement source2;
        KtSourceElementKind kind = null;
        if (firProperty.getGetter() != null) {
            FirPropertyAccessor getter = firProperty.getGetter();
            if (!(((getter == null || (source2 = getter.getSource()) == null) ? null : source2.getKind()) instanceof KtFakeSourceElementKind)) {
                return true;
            }
        }
        if (firProperty.getSetter() == null) {
            return false;
        }
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter != null && (source = setter.getSource()) != null) {
            kind = source.getKind();
        }
        return !(kind instanceof KtFakeSourceElementKind);
    }

    private final boolean hasJvmFieldAnnotation(FirPropertySymbol firPropertySymbol, FirSession firSession) {
        FirBackingFieldSymbol backingFieldSymbol = firPropertySymbol.getBackingFieldSymbol();
        return backingFieldSymbol != null && FirAnnotationUtilsKt.hasAnnotationWithClassId(backingFieldSymbol, JvmAbi.JVM_FIELD_ANNOTATION_CLASS_ID, firSession);
    }

    private final boolean isInsideCompanionObjectOfInterface(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        if (!firRegularClassSymbol.getRawStatus().isCompanion()) {
            return false;
        }
        FirClassLikeSymbol<FirClassLikeDeclaration> containingDeclaration = DeclarationUtilsKt.getContainingDeclaration(firRegularClassSymbol, firSession);
        ClassKind classKind = containingDeclaration != null ? FirHelpersKt.getClassKind(containingDeclaration) : null;
        return classKind == ClassKind.INTERFACE || classKind == ClassKind.ANNOTATION_CLASS;
    }

    private final boolean isInsideJvmMultifileClassFile(CheckerContext checkerContext) {
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        return containingFileSymbol != null && FirAnnotationUtilsKt.hasAnnotation(containingFileSymbol, JvmStandardClassIds.INSTANCE.getJVM_MULTIFILE_CLASS_ID(), checkerContext.getSession());
    }

    private final boolean isInterfaceCompanionWithPublicJvmFieldProperties(FirRegularClassSymbol containingClass, final FirSession session) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.processAllDeclaredCallables$default(containingClass, session, null, new Function1() { // from class: w95
            public final Object invoke(Object obj) {
                return FirJvmFieldApplicabilityChecker.b(booleanRef, session, (FirCallableSymbol) obj);
            }
        }, 2, null);
        return booleanRef.element;
    }

    private final boolean isOverridable(FirProperty firProperty, FirRegularClassSymbol firRegularClassSymbol) {
        if (Intrinsics.areEqual(firProperty.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return false;
        }
        Modality modality = firProperty.getStatus().getModality();
        Modality modality2 = Modality.FINAL;
        if (modality != modality2) {
            return firRegularClassSymbol == null || firRegularClassSymbol.getResolvedStatus().getModality() != modality2;
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirAnnotation annotationByClassId;
        JvmFieldApplicabilityProblem jvmFieldApplicabilityProblem;
        KtDiagnosticFactory1<String> inapplicable_jvm_field;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        FirSession session = checkerContext.getSession();
        FirBackingField backingField = firProperty.getBackingField();
        if (backingField == null || (annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(backingField, JvmAbi.JVM_FIELD_ANNOTATION_CLASS_ID, session)) == null) {
            return;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firProperty);
        FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
        if (firProperty.getDelegate() != null) {
            jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.DELEGATE;
        } else {
            if (!DeclarationAttributesKt.getHasBackingField(firProperty)) {
                return;
            }
            if (isOverridable(firProperty, regularClassSymbol)) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.NOT_FINAL;
            } else if (Visibilities.INSTANCE.isPrivate(firProperty.getStatus().getVisibility())) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.PRIVATE;
            } else if (hasCustomAccessor(firProperty)) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.CUSTOM_ACCESSOR;
            } else if (firProperty.getStatus().isOverride()) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.OVERRIDES;
            } else if (firProperty.getStatus().isLateInit()) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.LATEINIT;
            } else if (firProperty.getStatus().isConst()) {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.CONST;
            } else if (regularClassSymbol == null || !isInsideCompanionObjectOfInterface(regularClassSymbol, session)) {
                if (regularClassSymbol == null && isInsideJvmMultifileClassFile(checkerContext)) {
                    jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.TOP_LEVEL_PROPERTY_OF_MULTIFILE_FACADE;
                } else if (FirJvmFieldApplicabilityCheckerKt.isInlineClassThatRequiresMangling(firProperty.getReturnTypeRef(), session) || org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt.needsMultiFieldValueClassFlattening(firProperty.getReturnTypeRef(), session)) {
                    jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.RETURN_TYPE_IS_VALUE_CLASS;
                } else {
                    if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.ANNOTATION_CLASS) {
                        jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.ANNOTATION;
                    } else if (!DeclarationAttributesKt.getHasExplicitBackingField(firProperty)) {
                        return;
                    } else {
                        jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.PROPERTY_WITH_EXPLICIT_FIELD;
                    }
                }
            } else if (isInterfaceCompanionWithPublicJvmFieldProperties(regularClassSymbol, session)) {
                return;
            } else {
                jvmFieldApplicabilityProblem = JvmFieldApplicabilityProblem.NOT_PUBLIC_VAL_WITH_JVMFIELD;
            }
        }
        if (jvmFieldApplicabilityProblem != JvmFieldApplicabilityProblem.ANNOTATION) {
            inapplicable_jvm_field = FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_FIELD();
        } else if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ForbidJvmAnnotationsOnAnnotationParameters)) {
            inapplicable_jvm_field = FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_FIELD_WARNING();
        } else if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidFieldAnnotationsOnAnnotationParameters)) {
            return;
        } else {
            inapplicable_jvm_field = FirJvmErrors.INSTANCE.getINAPPLICABLE_JVM_FIELD();
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), (KtDiagnosticFactory1) inapplicable_jvm_field, (Object) jvmFieldApplicabilityProblem.getErrorMessage(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
