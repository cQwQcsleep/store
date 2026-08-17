package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.BuiltinTypes;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonAbstractNativeAnnotationChecker;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ-\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u00020\u0010R\u00020\u0012j\u0006\u0010\u0011\u001a\u00020\u0010j\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsAbstractNativeIndexerChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonAbstractNativeAnnotationChecker;", "requiredAnnotation", "Lorg/jetbrains/kotlin/name/ClassId;", "indexerKind", Argument.Delimiters.none, "requiredParametersCount", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Ljava/lang/String;I)V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirJsAbstractNativeIndexerChecker extends FirWebCommonAbstractNativeAnnotationChecker {
    private final String indexerKind;
    private final int requiredParametersCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJsAbstractNativeIndexerChecker(ClassId classId, String str, int i) {
        super(classId, FirJsErrors.INSTANCE.getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN());
        classId.getClass();
        str.getClass();
        this.indexerKind = str;
        this.requiredParametersCount = i;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonAbstractNativeAnnotationChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        super.check(checkerContext, diagnosticReporter, firNamedFunction);
        List<FirValueParameter> valueParameters = firNamedFunction.getValueParameters();
        BuiltinTypes builtinTypes = checkerContext.getSession().getBuiltinTypes();
        if (!valueParameters.isEmpty()) {
            FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.first(valueParameters);
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
            if (!(coneType instanceof ConeErrorType) && !ConeBuiltinTypeUtilsKt.isString(coneType) && !TypeUtilsKt.isSubtypeOf$default(coneType, builtinTypes.getNumberType().getConeType(), checkerContext.getSession(), false, 4, null)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getNATIVE_INDEXER_KEY_SHOULD_BE_STRING_OR_NUMBER(), (Object) this.indexerKind, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
        if (valueParameters.size() != this.requiredParametersCount) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), (KtDiagnosticFactory2) FirJsErrors.INSTANCE.getNATIVE_INDEXER_WRONG_PARAMETER_COUNT(), (Object) Integer.valueOf(this.requiredParametersCount), (Object) this.indexerKind, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        for (FirValueParameter firValueParameter2 : valueParameters) {
            if (firValueParameter2.getDefaultValue() != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameter2.getSource(), (KtDiagnosticFactory1) FirJsErrors.INSTANCE.getNATIVE_INDEXER_CAN_NOT_HAVE_DEFAULT_ARGUMENTS(), (Object) this.indexerKind, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
