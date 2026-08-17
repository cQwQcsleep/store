package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.resolve.InlineClassesUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u000ej\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/declaration/FirJvmInlineApplicabilityChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmInlineApplicabilityChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirJvmInlineApplicabilityChecker INSTANCE = new FirJvmInlineApplicabilityChecker();

    private FirJvmInlineApplicabilityChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firRegularClass, InlineClassesUtilsKt.getJVM_INLINE_ANNOTATION_CLASS_ID(), checkerContext.getSession());
        if (annotationByClassId != null && !firRegularClass.getStatus().isValue()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) annotationByClassId.getSource(), FirJvmErrors.INSTANCE.getJVM_INLINE_WITHOUT_VALUE_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (annotationByClassId == null && firRegularClass.getStatus().isValue() && !firRegularClass.getStatus().isExpect()) {
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.VALUE_KEYWORD;
            ktModifierKeywordToken.getClass();
            FirModifier<?> modifier = FirKeywordUtilsKt.getModifier(firRegularClass, ktModifierKeywordToken);
            modifier.getClass();
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) modifier.getSource(), FirJvmErrors.INSTANCE.getVALUE_CLASS_WITHOUT_JVM_INLINE_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
