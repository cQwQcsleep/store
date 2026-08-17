package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmAnalysisFlags;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.java.FirJvmTargetProvider;
import org.jetbrains.kotlin.fir.java.FirJvmTargetProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000fH\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmRecordChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "areRecordsAllowed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/JvmTarget;", "enableJvmPreview", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmRecordChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirJvmRecordChecker INSTANCE = new FirJvmRecordChecker();

    private FirJvmRecordChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean areRecordsAllowed(JvmTarget jvmTarget, boolean z) {
        int majorVersion = jvmTarget.getMajorVersion();
        JvmTarget jvmTarget2 = JvmTarget.JVM_15;
        if (majorVersion < jvmTarget2.getMajorVersion()) {
            return false;
        }
        return z || jvmTarget.getMajorVersion() > jvmTarget2.getMajorVersion();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        KtSourceElement source;
        List<FirValueParameterSymbol> valueParameterSymbols;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        FirTypeRef firTypeRef = (FirTypeRef) CollectionsKt.firstOrNull(firRegularClass.getSuperTypeRefs());
        if (firTypeRef != null) {
            KtSourceElement source2 = firTypeRef.getSource();
            if (!((source2 != null ? source2.getKind() : null) instanceof KtFakeSourceElementKind.RecordSuperTypeRef) && Intrinsics.areEqual(FirHelpersKt.fullyExpandedClassId(FirTypeUtilsKt.getConeType(firTypeRef), checkerContext.getSession()), JvmStandardClassIds.Java.INSTANCE.getRecord())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirJvmErrors.INSTANCE.getILLEGAL_JAVA_LANG_RECORD_SUPERTYPE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firRegularClass, JvmStandardClassIds.JVM_RECORD_ANNOTATION_CLASS_ID, checkerContext.getSession());
        if (annotationByClassId == null || (source = annotationByClassId.getSource()) == null) {
            return;
        }
        FirJvmTargetProvider jvmTargetProvider = FirJvmTargetProviderKt.getJvmTargetProvider(checkerContext.getSession());
        JvmTarget jvmTarget = jvmTargetProvider != null ? jvmTargetProvider.getJvmTarget() : null;
        if (jvmTarget != null && !areRecordsAllowed(jvmTarget, ((Boolean) checkerContext.get$languageVersionSettings().getFlag(JvmAnalysisFlags.getEnableJvmPreview())).booleanValue())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirJvmErrors.INSTANCE.getJVM_RECORDS_ILLEGAL_BYTECODE_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (firRegularClass.getIsLocal()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirJvmErrors.INSTANCE.getLOCAL_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (firRegularClass.getStatus().isInner()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirJvmErrors.INSTANCE.getINNER_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        Modality modality = firRegularClass.getStatus().getModality();
        if (modality != null && modality != Modality.FINAL) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirJvmErrors.INSTANCE.getNON_FINAL_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (firRegularClass.getClassKind() == ClassKind.ENUM_CLASS) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), FirJvmErrors.INSTANCE.getENUM_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (!firRegularClass.getStatus().isData()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirJvmErrors.INSTANCE.getNON_DATA_CLASS_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firRegularClass, checkerContext.getSession());
        if (firConstructorSymbolPrimaryConstructorIfAny != null && (valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols()) != null) {
            if (valueParameterSymbols.isEmpty()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirJvmErrors.INSTANCE.getJVM_RECORD_WITHOUT_PRIMARY_CONSTRUCTOR_PARAMETERS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
            for (FirValueParameterSymbol firValueParameterSymbol : CollectionsKt.dropLast(valueParameterSymbols, 1)) {
                if (firValueParameterSymbol.isVararg()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getSource(), FirJvmErrors.INSTANCE.getJVM_RECORD_NOT_LAST_VARARG_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
        for (FirDeclaration firDeclaration : firRegularClass.getDeclarations()) {
            if (firDeclaration instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) firDeclaration;
                KtSourceElement source3 = firProperty.getSource();
                boolean zAreEqual = Intrinsics.areEqual(source3 != null ? source3.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
                if (firProperty.getIsVar() && zAreEqual) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirJvmErrors.INSTANCE.getJVM_RECORD_NOT_VAL_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (!zAreEqual && (DeclarationAttributesKt.getHasBackingField(firProperty) || firProperty.getDelegateFieldSymbol() != null)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firProperty.getSource(), FirJvmErrors.INSTANCE.getFIELD_IN_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            } else if ((firDeclaration instanceof FirField) && (firDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirField) firDeclaration).getSource(), FirJvmErrors.INSTANCE.getDELEGATION_BY_IN_JVM_RECORD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        FirTypeRef firTypeRef2 = (FirTypeRef) CollectionsKt.firstOrNull(firRegularClass.getSuperTypeRefs());
        if (firTypeRef2 != null) {
            KtSourceElement source4 = firTypeRef2.getSource();
            if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(firTypeRef2, checkerContext.getSession());
                if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.CLASS) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firRegularClass.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getJVM_RECORD_EXTENDS_CLASS(), (Object) FirTypeUtilsKt.getConeType(firTypeRef2), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }
}
