package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0010R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaAnnotationsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "<init>", "()V", "javaToKotlinNameMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaAnnotationsChecker extends FirExpressionChecker<FirAnnotation> {
    public static final FirJavaAnnotationsChecker INSTANCE = new FirJavaAnnotationsChecker();
    private static final Map<ClassId, ClassId> javaToKotlinNameMap;

    static {
        JvmStandardClassIds.Annotations.Java java = JvmStandardClassIds.Annotations.Java.INSTANCE;
        ClassId target = java.getTarget();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        javaToKotlinNameMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(target, standardClassIds$Annotations.getTarget()), TuplesKt.to(java.getRetention(), standardClassIds$Annotations.getRetention()), TuplesKt.to(java.getDeprecated(), standardClassIds$Annotations.getDeprecated()), TuplesKt.to(java.getDocumented(), standardClassIds$Annotations.getMustBeDocumented())});
    }

    private FirJavaAnnotationsChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation) {
        ConeClassLikeLookupTag classLikeLookupTagIfAny;
        FirClassSymbol<?> classSymbol;
        FirResolvedArgumentList firResolvedArgumentList;
        FirArgumentList originalArgumentList;
        List<FirExpression> arguments;
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firAnnotation.getClass();
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        if (Intrinsics.areEqual((firBasedSymbol == null || (source = firBasedSymbol.getSource()) == null) ? null : source.getKind(), KtRealSourceElementKind.INSTANCE) && (classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef())))) != null && (classSymbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, classLikeLookupTagIfAny)) != null && (classSymbol.getOrigin() instanceof FirDeclarationOrigin.Java)) {
            ClassId classId = javaToKotlinNameMap.get(classSymbol.getLookupTag().getClassId());
            if (classId != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getDEPRECATED_JAVA_ANNOTATION(), (Object) classId.asSingleFqName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if (firAnnotation instanceof FirAnnotationCall) {
                FirArgumentList argumentList = ((FirAnnotationCall) firAnnotation).getArgumentList();
                if (!(argumentList instanceof FirResolvedArgumentList) || (originalArgumentList = (firResolvedArgumentList = (FirResolvedArgumentList) argumentList).getOriginalArgumentList()) == null || (arguments = originalArgumentList.getArguments()) == null) {
                    return;
                }
                for (FirExpression firExpression : arguments) {
                    if (!(firExpression instanceof FirWrappedArgumentExpression) && !(firExpression instanceof FirErrorExpression)) {
                        FirValueParameter firValueParameter = firResolvedArgumentList.getMapping().get(firExpression);
                        Name name = firValueParameter != null ? firValueParameter.getName() : null;
                        if (name != null && !Intrinsics.areEqual(name, StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue())) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), FirJvmErrors.INSTANCE.getPOSITIONED_VALUE_ARGUMENT_FOR_JAVA_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        }
                    }
                }
            }
        }
    }
}
