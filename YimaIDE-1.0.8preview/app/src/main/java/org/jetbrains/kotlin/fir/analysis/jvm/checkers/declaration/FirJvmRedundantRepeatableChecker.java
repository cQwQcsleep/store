package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmRedundantRepeatableChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmRedundantRepeatableChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirJvmRedundantRepeatableChecker INSTANCE = new FirJvmRedundantRepeatableChecker();

    private FirJvmRedundantRepeatableChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FqName fqNameAsSingleFqName;
        FqName fqNameAsSingleFqName2;
        ConeClassLikeLookupTag lookupTag;
        ConeClassLikeLookupTag lookupTag2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, StandardClassIds$Annotations.INSTANCE.getRepeatable(), checkerContext.getSession());
        FirAnnotation annotationByClassId2 = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, JvmStandardClassIds.Annotations.INSTANCE.getJvmRepeatable(), checkerContext.getSession());
        if (annotationByClassId2 == null) {
            annotationByClassId2 = FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, JvmStandardClassIds.Annotations.Java.INSTANCE.getRepeatable(), checkerContext.getSession());
        }
        if (annotationByClassId == null || annotationByClassId2 == null) {
            return;
        }
        KtSourceElement source = annotationByClassId.getSource();
        KtDiagnosticFactory2<FqName, FqName> redundant_repeatable_annotation = FirJvmErrors.INSTANCE.getREDUNDANT_REPEATABLE_ANNOTATION();
        FirResolvedTypeRef annotationTypeRef = annotationByClassId.getAnnotationTypeRef();
        ClassId classId = null;
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        ClassId classId2 = (coneClassLikeType == null || (lookupTag2 = coneClassLikeType.getLookupTag()) == null) ? null : lookupTag2.getClassId();
        if (classId2 == null || (fqNameAsSingleFqName = classId2.asSingleFqName()) == null) {
            fqNameAsSingleFqName = FqName.ROOT;
        }
        FqName fqName = fqNameAsSingleFqName;
        FirResolvedTypeRef annotationTypeRef2 = annotationByClassId2.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef2 = annotationTypeRef2 instanceof FirResolvedTypeRef ? annotationTypeRef2 : null;
        ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
        ConeClassLikeType coneClassLikeType2 = coneType2 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType2 : null;
        if (coneClassLikeType2 != null && (lookupTag = coneClassLikeType2.getLookupTag()) != null) {
            classId = lookupTag.getClassId();
        }
        if (classId == null || (fqNameAsSingleFqName2 = classId.asSingleFqName()) == null) {
            fqNameAsSingleFqName2 = FqName.ROOT;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory2) redundant_repeatable_annotation, (Object) fqName, (Object) fqNameAsSingleFqName2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }
}
