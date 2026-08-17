package org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.web.common.FirWebCommonErrors;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0007\u001a\u00020\u0003H\u0014R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ)\u0010\u000f\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J/\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u00020\bR\u00020\u0015j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirWebReflectionAPICallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/AbstractFirReflectionApiCallChecker;", "isWasm", Argument.Delimiters.none, "<init>", "(Z)V", "()Z", "isWholeReflectionApiAvailable", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "isAllowedKClassMember", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/Name;)Z", "isAllowedReflectionApi", "containingClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/name/ClassId;)Z", "report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers.web.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWebReflectionAPICallChecker extends AbstractFirReflectionApiCallChecker {
    private final boolean isWasm;

    public FirWebReflectionAPICallChecker(boolean z) {
        this.isWasm = z;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public boolean isAllowedKClassMember(CheckerContext checkerContext, Name name) {
        checkerContext.getClass();
        name.getClass();
        return super.isAllowedKClassMember(checkerContext, name) || Intrinsics.areEqual(name, AbstractFirReflectionApiCallChecker.getK_CLASS_IS_INTERFACE_NAME());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public boolean isAllowedReflectionApi(CheckerContext checkerContext, Name name, ClassId classId) {
        checkerContext.getClass();
        name.getClass();
        classId.getClass();
        return super.isAllowedReflectionApi(checkerContext, name, classId) || StandardClassIds$Annotations.INSTANCE.getAssociatedObjectAnnotations().contains(classId) || Intrinsics.areEqual(name, StandardNames.FqNames.findAssociatedObject.shortName());
    }

    /* JADX INFO: renamed from: isWasm, reason: from getter */
    public final boolean getIsWasm() {
        return this.isWasm;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public boolean isWholeReflectionApiAvailable(CheckerContext checkerContext) {
        checkerContext.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.AbstractFirReflectionApiCallChecker
    public void report(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        String str = this.isWasm ? "Wasm" : "JS";
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirWebCommonErrors.INSTANCE.getUNSUPPORTED_REFLECTION_API(), (Object) ("This reflection API is not supported in Kotlin/" + str + '.'), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
