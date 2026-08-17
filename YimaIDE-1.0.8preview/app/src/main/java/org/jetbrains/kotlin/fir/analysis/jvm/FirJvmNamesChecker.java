package org.jetbrains.kotlin.fir.analysis.jvm;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineScopeUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/FirJvmNamesChecker;", Argument.Delimiters.none, "<init>", "()V", "INVALID_CHARS", Argument.Delimiters.none, Argument.Delimiters.none, "DANGEROUS_CHARS", "checkNameAndReport", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "declarationSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmNamesChecker {
    public static final FirJvmNamesChecker INSTANCE = new FirJvmNamesChecker();
    private static final Set<Character> INVALID_CHARS = SetsKt.setOf(new Character[]{'.', ';', '[', ']', '/', '<', '>', ':', Character.valueOf(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR)});
    private static final Set<Character> DANGEROUS_CHARS = SetsKt.setOf(new Character[]{'?', '*', '\"', '|', '%'});

    private FirJvmNamesChecker() {
    }

    public final void checkNameAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Name name, KtSourceElement ktSourceElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        name.getClass();
        if (ktSourceElement == null || (ktSourceElement.getKind() instanceof KtFakeSourceElementKind) || name.isSpecial()) {
            return;
        }
        String strAsString = name.asString();
        strAsString.getClass();
        for (int i = 0; i < strAsString.length(); i++) {
            char cCharAt = strAsString.charAt(i);
            Set<Character> set = INVALID_CHARS;
            if (set.contains(Character.valueOf(cCharAt))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getINVALID_CHARACTERS(), (Object) ("contains illegal characters: " + CollectionsKt.joinToString$default(CollectionsKt.intersect(set, StringsKt.toSet(strAsString)), Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
        for (int i2 = 0; i2 < strAsString.length(); i2++) {
            char cCharAt2 = strAsString.charAt(i2);
            Set<Character> set2 = DANGEROUS_CHARS;
            if (set2.contains(Character.valueOf(cCharAt2))) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirJvmErrors.INSTANCE.getDANGEROUS_CHARACTERS(), (Object) CollectionsKt.joinToString$default(CollectionsKt.intersect(set2, StringsKt.toSet(strAsString)), Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
    }
}
