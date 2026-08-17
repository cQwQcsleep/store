package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014J\f\u0010\u0015\u001a\u00020\u0016*\u00020\u0013H\u0002J!\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyAccessorsTypesChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkGetter", "property", "checkSetter", "checkAccessorForDelegatedProperty", "accessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)V", "isImplicitDelegateAccessor", Argument.Delimiters.none, "isLegallyAbstract", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyAccessorsTypesChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirPropertyAccessorsTypesChecker INSTANCE = new FirPropertyAccessorsTypesChecker();

    private FirPropertyAccessorsTypesChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkAccessorForDelegatedProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty, FirPropertyAccessor firPropertyAccessor) {
        if (firProperty.getDelegateFieldSymbol() == null || firPropertyAccessor.getBody() == null || FirDeclarationUtilKt.hasGeneratedDelegateBody(firPropertyAccessor)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessor.getSource(), FirErrors.INSTANCE.getACCESSOR_FOR_DELEGATED_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkGetter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirPropertyAccessor getter = firProperty.getGetter();
        if (getter == null) {
            return;
        }
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef());
        checkAccessorForDelegatedProperty(checkerContext, diagnosticReporter, firProperty, getter);
        if (isImplicitDelegateAccessor(getter)) {
            return;
        }
        if (!Intrinsics.areEqual(getter.getStatus().getVisibility(), firProperty.getStatus().getVisibility())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) getter.getSource(), FirErrors.INSTANCE.getGETTER_VISIBILITY_DIFFERS_FROM_PROPERTY_VISIBILITY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        CallableId callableId = firProperty.getSymbol().getCallableId();
        if ((callableId != null ? callableId.getClassId() : null) != null && getter.getBody() != null && firProperty.getDelegate() == null && isLegallyAbstract(checkerContext, firProperty)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) getter.getSource(), FirErrors.INSTANCE.getABSTRACT_PROPERTY_WITH_GETTER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirTypeRef returnTypeRef = getter.getReturnTypeRef();
        KtSourceElement source = returnTypeRef.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
            return;
        }
        ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(returnTypeRef);
        if ((coneType instanceof ConeErrorType) || (coneType2 instanceof ConeErrorType) || Intrinsics.areEqual(coneType2, FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()))) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) returnTypeRef.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_GETTER_RETURN_TYPE(), (Object) coneType, (Object) coneType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void checkSetter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        FirPropertyAccessor setter = firProperty.getSetter();
        if (setter == null) {
            return;
        }
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef());
        if (firProperty.getIsVal()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getSource(), FirErrors.INSTANCE.getVAL_WITH_SETTER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        checkAccessorForDelegatedProperty(checkerContext, diagnosticReporter, firProperty, setter);
        if (isImplicitDelegateAccessor(setter)) {
            return;
        }
        Integer numCompareTo = setter.getStatus().getVisibility().compareTo(firProperty.getStatus().getVisibility());
        if (numCompareTo == null || numCompareTo.intValue() > 0) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getSource(), FirErrors.INSTANCE.getSETTER_VISIBILITY_INCONSISTENT_WITH_PROPERTY_VISIBILITY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        CallableId callableId = firProperty.getSymbol().getCallableId();
        if ((callableId != null ? callableId.getClassId() : null) != null) {
            boolean zIsLegallyAbstract = isLegallyAbstract(checkerContext, firProperty);
            Visibility visibility = setter.getStatus().getVisibility();
            Visibilities.Private r2 = Visibilities.Private.INSTANCE;
            if (Intrinsics.areEqual(visibility, r2) && !Intrinsics.areEqual(firProperty.getStatus().getVisibility(), r2)) {
                if (zIsLegallyAbstract) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getSource(), FirErrors.INSTANCE.getPRIVATE_SETTER_FOR_ABSTRACT_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (!DeclarationUtilsKt.isEffectivelyFinal(firProperty)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getSource(), FirErrors.INSTANCE.getPRIVATE_SETTER_FOR_OPEN_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            if (zIsLegallyAbstract && setter.getBody() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getSource(), FirErrors.INSTANCE.getABSTRACT_PROPERTY_WITH_SETTER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.first(setter.getValueParameters());
        if (firValueParameter.getIsVararg()) {
            return;
        }
        ConeKotlinType coneType2 = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        KtSourceElement source = firValueParameter.getReturnTypeRef().getSource();
        if ((coneType instanceof ConeErrorType) || (coneType2 instanceof ConeErrorType)) {
            return;
        }
        ConeAttributes.Companion companion = ConeAttributes.INSTANCE;
        if (!Intrinsics.areEqual(TypeUtilsKt.withAttributes(coneType2, companion.getEmpty()), TypeUtilsKt.withAttributes(coneType, companion.getEmpty())) && !ConeTypeUtilsKt.hasError(coneType2)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_SETTER_PARAMETER_TYPE(), (Object) coneType, (Object) coneType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        if (ConeBuiltinTypeUtilsKt.isUnit(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(setter.getReturnTypeRef())))) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) setter.getReturnTypeRef().getSource(), FirErrors.INSTANCE.getWRONG_SETTER_RETURN_TYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final boolean isImplicitDelegateAccessor(FirPropertyAccessor firPropertyAccessor) {
        KtSourceElement source = firPropertyAccessor.getSource();
        return Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isLegallyAbstract(CheckerContext checkerContext, FirProperty firProperty) {
        Modality modality = firProperty.getStatus().getModality();
        Modality modality2 = Modality.ABSTRACT;
        if (modality != modality2) {
            return false;
        }
        FirClassSymbol<?> firClassSymbolFindClosestClassOrObject = FirHelpersKt.findClosestClassOrObject(checkerContext);
        if (!(firClassSymbolFindClosestClassOrObject instanceof FirRegularClassSymbol)) {
            return false;
        }
        FirRegularClass firRegularClass = (FirRegularClass) ((FirRegularClassSymbol) firClassSymbolFindClosestClassOrObject).getFir();
        return firRegularClass.getClassKind() == ClassKind.INTERFACE || firRegularClass.getStatus().getModality() == modality2 || firRegularClass.getStatus().getModality() == Modality.SEALED || firRegularClass.getClassKind() == ClassKind.ENUM_CLASS;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        checkGetter(checkerContext, diagnosticReporter, firProperty);
        checkSetter(checkerContext, diagnosticReporter, firProperty);
    }
}
