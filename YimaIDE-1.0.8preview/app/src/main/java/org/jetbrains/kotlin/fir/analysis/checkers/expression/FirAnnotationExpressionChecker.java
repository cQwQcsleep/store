package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirNonSuppressibleErrorNamesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.ConstantArgumentKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirConstChecksKt;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.RequireKotlinConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J7\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\u0011R\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001aJ/\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0013\u001a\u00020\u0017H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u001dJ7\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010\t2\u0006\u0010 \u001a\u00020!H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\"JM\u0010#\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001f\u001a\u0004\u0018\u00010\t2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170'H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010(J-\u0010)\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020*H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010+J-\u0010,\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H\u0002R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014JC\u0010-\u001a\u00020\u000e2\b\u0010.\u001a\u0004\u0018\u00010/2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00170'H\u0002R\u00020\u0011R\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u00100J9\u00101\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010.\u001a\u0004\u0018\u00010/H\u0002R\u00020\u0011R\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u00102R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationExpressionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "<init>", "()V", "versionArgumentName", "Lorg/jetbrains/kotlin/name/Name;", "deprecatedSinceKotlinFqName", "Lorg/jetbrains/kotlin/name/FqName;", "sinceKotlinFqName", "annotationFqNamesWithVersion", Argument.Delimiters.none, "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;)V", "checkAnnotationArgumentWithSubElements", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "parseVersionExpressionOrReport", "Lorg/jetbrains/kotlin/config/ApiVersion;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/config/ApiVersion;", "checkAnnotationsWithVersion", "fqName", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "checkDeprecatedSinceKotlin", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "argumentMapping", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/FqName;Ljava/util/Map;)V", "checkAnnotationsInsideAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCall;)V", "checkNotAClass", "checkErrorSuppression", "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/Map;)V", "checkContextFunctionTypeParams", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/ClassId;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationExpressionChecker extends FirExpressionChecker<FirAnnotationCall> {
    public static final FirAnnotationExpressionChecker INSTANCE = new FirAnnotationExpressionChecker();
    private static final Set<FqName> annotationFqNamesWithVersion;
    private static final FqName deprecatedSinceKotlinFqName;
    private static final FqName sinceKotlinFqName;
    private static final Name versionArgumentName;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConstantArgumentKind.values().length];
            try {
                iArr[ConstantArgumentKind.NOT_CONST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConstantArgumentKind.ENUM_NOT_CONST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConstantArgumentKind.NOT_KCLASS_LITERAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ConstantArgumentKind.KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ConstantArgumentKind.NOT_CONST_VAL_IN_CONST_EXPRESSION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ConstantArgumentKind.VALID_CONST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ConstantArgumentKind.RESOLUTION_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Name nameIdentifier = Name.identifier("version");
        nameIdentifier.getClass();
        versionArgumentName = nameIdentifier;
        deprecatedSinceKotlinFqName = new FqName("kotlin.DeprecatedSinceKotlin");
        FqName fqName = new FqName("kotlin.SinceKotlin");
        sinceKotlinFqName = fqName;
        annotationFqNamesWithVersion = SetsKt.setOf(new FqName[]{RequireKotlinConstants.INSTANCE.getFQ_NAME(), fqName});
    }

    private FirAnnotationExpressionChecker() {
        super(MppCheckerKind.Common);
    }

    private final KtDiagnosticFactory0 checkAnnotationArgumentWithSubElements(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirExpression firExpression, FirSession firSession) {
        if (firExpression instanceof FirCollectionLiteral) {
            return checkAnnotationArgumentWithSubElements$checkArgumentList(diagnosticReporter, checkerContext, firSession, ((FirCollectionLiteral) firExpression).getArgumentList());
        }
        if (firExpression instanceof FirVarargArgumentsExpression) {
            Iterator<FirExpression> it = ((FirVarargArgumentsExpression) firExpression).getArguments().iterator();
            while (it.hasNext()) {
                FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(it.next());
                KtDiagnosticFactory0 ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements = checkAnnotationArgumentWithSubElements(diagnosticReporter, checkerContext, firExpressionUnwrapArgument, firSession);
                if (ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpressionUnwrapArgument.getSource(), ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            return null;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[FirConstChecksKt.computeConstantExpressionKind(firExpression, firSession, true).ordinal()]) {
            case 1:
                return FirErrors.INSTANCE.getANNOTATION_ARGUMENT_MUST_BE_CONST();
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return FirErrors.INSTANCE.getANNOTATION_ARGUMENT_MUST_BE_ENUM_CONST();
            case 3:
                return FirErrors.INSTANCE.getANNOTATION_ARGUMENT_MUST_BE_KCLASS_LITERAL();
            case 4:
                return FirErrors.INSTANCE.getANNOTATION_ARGUMENT_KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR();
            case 5:
                return FirErrors.INSTANCE.getNON_CONST_VAL_USED_IN_CONSTANT_EXPRESSION();
            case 6:
            case 7:
                if (firExpression instanceof FirFunctionCall) {
                    return checkAnnotationArgumentWithSubElements$checkArgumentList(diagnosticReporter, checkerContext, firSession, ((FirFunctionCall) firExpression).getArgumentList());
                }
                return null;
            default:
                bu8.a();
                return null;
        }
    }

    private static final KtDiagnosticFactory0 checkAnnotationArgumentWithSubElements$checkArgumentList(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirSession firSession, FirArgumentList firArgumentList) {
        boolean z = false;
        for (FirExpression firExpression : firArgumentList.getArguments()) {
            KtSourceElement source = firExpression.getSource();
            KtDiagnosticFactory0 ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements = INSTANCE.checkAnnotationArgumentWithSubElements(diagnosticReporter, checkerContext, firExpression, firSession);
            if (ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements != null) {
                if (!Intrinsics.areEqual(ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements, FirErrors.INSTANCE.getANNOTATION_ARGUMENT_MUST_BE_KCLASS_LITERAL())) {
                    z = true;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        if (z) {
            return FirErrors.INSTANCE.getNON_CONST_VAL_USED_IN_CONSTANT_EXPRESSION();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkAnnotationsInsideAnnotationCall(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCall firCall) {
        Iterator<FirExpression> it = firCall.getArgumentList().getArguments().iterator();
        while (it.hasNext()) {
            FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(it.next());
            FirExpression firExpressionUnwrapErrorExpression = FirExpressionUtilKt.unwrapErrorExpression(firExpressionUnwrapArgument);
            KtDiagnosticFactory0 annotation_used_as_annotation_argument = ((firExpressionUnwrapArgument instanceof FirErrorExpression) && ((FirErrorExpression) firExpressionUnwrapArgument).getExpression() == null) ? FirErrors.INSTANCE.getANNOTATION_USED_AS_ANNOTATION_ARGUMENT() : FirErrors.INSTANCE.getANNOTATION_ON_ANNOTATION_ARGUMENT();
            Iterator<FirAnnotation> it2 = firExpressionUnwrapErrorExpression.getAnnotations().iterator();
            while (it2.hasNext()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it2.next().getSource(), annotation_used_as_annotation_argument, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            CheckerContext checkerContext2 = checkerContext;
            DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
            if (firExpressionUnwrapErrorExpression instanceof FirCollectionLiteral) {
                checkAnnotationsInsideAnnotationCall(checkerContext2, diagnosticReporter2, (FirCall) firExpressionUnwrapErrorExpression);
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    private final void checkAnnotationsWithVersion(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FqName fqName, FirAnnotation firAnnotation) {
        FirExpression firExpressionFindArgumentByName$default;
        ApiVersion versionExpressionOrReport;
        if (CollectionsKt.contains(annotationFqNamesWithVersion, fqName) && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, versionArgumentName, false, 2, null)) != null && (versionExpressionOrReport = parseVersionExpressionOrReport(checkerContext, diagnosticReporter, firExpressionFindArgumentByName$default)) != null && Intrinsics.areEqual(fqName, sinceKotlinFqName)) {
            ApiVersion apiVersion = FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()).getApiVersion();
            if (versionExpressionOrReport.compareTo(apiVersion) > 0) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpressionFindArgumentByName$default.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNEWER_VERSION_IN_SINCE_KOTLIN(), (Object) apiVersion.getVersionString(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final void checkContextFunctionTypeParams(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement, ClassId classId) {
        if (Intrinsics.areEqual(classId, StandardClassIds$Annotations.INSTANCE.getContextFunctionTypeParams())) {
            FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, ktSourceElement, LanguageFeature.ContextReceivers, (SourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:56:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:76:0x0090 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x009b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x00ab A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0096 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x0030 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x0030 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x0030 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0030 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    private final void checkDeprecatedSinceKotlin(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FqName fqName, Map<Name, ? extends FirExpression> map) {
        boolean z;
        ApiVersion versionExpressionOrReport;
        int iHashCode;
        if (Intrinsics.areEqual(fqName, deprecatedSinceKotlinFqName)) {
            if (map.size() == 0) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getDEPRECATED_SINCE_KOTLIN_WITHOUT_ARGUMENTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            ApiVersion apiVersion = null;
            ApiVersion apiVersion2 = null;
            ApiVersion apiVersion3 = null;
            for (Map.Entry<Name, ? extends FirExpression> entry : map.entrySet()) {
                Name key = entry.getKey();
                FirExpression value = entry.getValue();
                String identifier = key.getIdentifier();
                identifier.getClass();
                int iHashCode2 = identifier.hashCode();
                if (iHashCode2 != -2027506434) {
                    if (iHashCode2 != -855220304) {
                        if (iHashCode2 == 1624782354 && identifier.equals("errorSince")) {
                            versionExpressionOrReport = parseVersionExpressionOrReport(checkerContext, diagnosticReporter, value);
                            if (versionExpressionOrReport != null) {
                                iHashCode = identifier.hashCode();
                                if (iHashCode != -2027506434) {
                                    if (iHashCode != -855220304) {
                                        if (iHashCode != 1624782354 && identifier.equals("errorSince")) {
                                            apiVersion2 = versionExpressionOrReport;
                                        }
                                    } else if (identifier.equals("hiddenSince")) {
                                        apiVersion3 = versionExpressionOrReport;
                                    }
                                } else if (identifier.equals("warningSince")) {
                                    apiVersion = versionExpressionOrReport;
                                }
                            }
                        }
                    } else if (identifier.equals("hiddenSince")) {
                        versionExpressionOrReport = parseVersionExpressionOrReport(checkerContext, diagnosticReporter, value);
                        if (versionExpressionOrReport != null) {
                            iHashCode = identifier.hashCode();
                            if (iHashCode != -2027506434) {
                                if (iHashCode != -855220304) {
                                    if (iHashCode != 1624782354) {
                                        apiVersion2 = versionExpressionOrReport;
                                    }
                                } else if (identifier.equals("hiddenSince")) {
                                    apiVersion3 = versionExpressionOrReport;
                                }
                            } else if (identifier.equals("warningSince")) {
                                apiVersion = versionExpressionOrReport;
                            }
                        }
                    }
                } else if (identifier.equals("warningSince")) {
                    versionExpressionOrReport = parseVersionExpressionOrReport(checkerContext, diagnosticReporter, value);
                    if (versionExpressionOrReport != null) {
                        iHashCode = identifier.hashCode();
                        if (iHashCode != -2027506434) {
                            if (iHashCode != -855220304) {
                                if (iHashCode != 1624782354) {
                                    apiVersion2 = versionExpressionOrReport;
                                }
                            } else if (identifier.equals("hiddenSince")) {
                                apiVersion3 = versionExpressionOrReport;
                            }
                        } else if (identifier.equals("warningSince")) {
                            apiVersion = versionExpressionOrReport;
                        }
                    }
                }
            }
            if (apiVersion == null) {
                z = false;
            } else {
                z = apiVersion2 != null && apiVersion.compareTo(apiVersion2) > 0;
                if (apiVersion3 != null && !z) {
                    if (apiVersion.compareTo(apiVersion3) > 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            if (apiVersion2 != null && apiVersion3 != null && !z) {
                z = apiVersion2.compareTo(apiVersion3) > 0;
            }
            if (z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getDEPRECATED_SINCE_KOTLIN_WITH_UNORDERED_VERSIONS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkErrorSuppression(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ClassId classId, Map<Name, ? extends FirExpression> map) {
        FirExpression firExpression;
        List<FirExpression> listUnwrapVarargValue;
        if (((Boolean) checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getDontWarnOnErrorSuppression())).booleanValue() || !Intrinsics.areEqual(classId, StandardClassIds$Annotations.INSTANCE.getSuppress()) || (firExpression = map.get(StandardClassIds$Annotations.ParameterNames.INSTANCE.getSuppressNames())) == null || (listUnwrapVarargValue = FirAnnotationUtilsKt.unwrapVarargValue(firExpression)) == null) {
            return;
        }
        for (FirExpression firExpression2 : listUnwrapVarargValue) {
            FirLiteralExpression firLiteralExpression = firExpression2 instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression2 : null;
            Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
            String str = value instanceof String ? (String) value : null;
            if (str != null) {
                if (!FirNonSuppressibleErrorNamesKt.getFIR_NON_SUPPRESSIBLE_ERROR_NAMES().contains(str)) {
                    if (Intrinsics.areEqual(str, AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS)) {
                        str = "all errors";
                    }
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpression2).getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getERROR_SUPPRESSION(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final void checkNotAClass(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationCall firAnnotationCall) {
        FirTypeRef annotationTypeRef = firAnnotationCall.getAnnotationTypeRef();
        if (!(firAnnotationCall.getCalleeReference() instanceof FirErrorNamedReference) || (annotationTypeRef instanceof FirErrorTypeRef) || (FirTypeUtilsKt.getConeType(annotationTypeRef) instanceof ConeClassLikeType)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationTypeRef.getSource(), FirErrors.INSTANCE.getNOT_A_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final ApiVersion parseVersionExpressionOrReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirExpression firExpression) {
        FirLiteralExpression firLiteralExpression = firExpression instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression : null;
        if (firLiteralExpression == null) {
            return null;
        }
        Object value = firLiteralExpression.getValue();
        String str = value instanceof String ? (String) value : null;
        if (str == null) {
            return null;
        }
        if (!RequireKotlinConstants.INSTANCE.getVERSION_REGEX().matches(str)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpression).getSource(), FirErrors.INSTANCE.getILLEGAL_KOTLIN_VERSION_STRING_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return null;
        }
        ApiVersion apiVersion = ApiVersion.INSTANCE.parse(str);
        if (apiVersion == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirLiteralExpression) firExpression).getSource(), FirErrors.INSTANCE.getILLEGAL_KOTLIN_VERSION_STRING_VALUE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        return apiVersion;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationCall firAnnotationCall) {
        FirExpression expression;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnnotationCall.getClass();
        ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId(firAnnotationCall, checkerContext.getSession());
        FqName fqNameAsSingleFqName = annotationClassId != null ? annotationClassId.asSingleFqName() : null;
        for (FirExpression firExpression : firAnnotationCall.getArgumentList().getArguments()) {
            FirErrorExpression firErrorExpression = firExpression instanceof FirErrorExpression ? (FirErrorExpression) firExpression : null;
            if (firErrorExpression != null && (expression = firErrorExpression.getExpression()) != null) {
                firExpression = expression;
            }
            KtDiagnosticFactory0 ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements = checkAnnotationArgumentWithSubElements(diagnosticReporter, checkerContext, firExpression, checkerContext.getSession());
            if (ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), ktDiagnosticFactory0CheckAnnotationArgumentWithSubElements, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        checkAnnotationsWithVersion(checkerContext, diagnosticReporter, fqNameAsSingleFqName, firAnnotationCall);
        checkDeprecatedSinceKotlin(checkerContext, diagnosticReporter, firAnnotationCall.getSource(), fqNameAsSingleFqName, firAnnotationCall.getArgumentMapping().getMapping());
        checkAnnotationsInsideAnnotationCall(checkerContext, diagnosticReporter, firAnnotationCall);
        checkNotAClass(checkerContext, diagnosticReporter, firAnnotationCall);
        checkErrorSuppression(diagnosticReporter, checkerContext, annotationClassId, firAnnotationCall.getArgumentMapping().getMapping());
        checkContextFunctionTypeParams(diagnosticReporter, checkerContext, firAnnotationCall.getSource(), annotationClassId);
    }
}
