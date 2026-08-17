package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

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
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0014J9\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0000R\u00020\u000fR\u00020\u0011j\u0006\u0010\u0010\u001a\u00020\u000fj\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeIdentifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "invalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkNameAndReport", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "checkNameAndReport$org_jetbrains_kotlin_checkers_native", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeIdentifierChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirNativeIdentifierChecker INSTANCE = new FirNativeIdentifierChecker();
    private static final Set<Character> invalidChars = SetsKt.setOf(new Character[]{'.', ';', ',', '(', ')', '[', ']', '{', '}', '/', '<', '>', ':', Character.valueOf(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR), '$', '&', '~', '*', '?', '#', '|', 167, '%', '@'});

    private FirNativeIdentifierChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtSourceElement source = firDeclaration.getSource();
        if (firDeclaration instanceof FirRegularClass) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirRegularClass) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirNamedFunction) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirNamedFunction) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirTypeParameter) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirTypeParameter) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirProperty) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirProperty) firDeclaration).getName(), source);
            return;
        }
        if (firDeclaration instanceof FirTypeAlias) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirTypeAlias) firDeclaration).getName(), source);
        } else if (firDeclaration instanceof FirValueParameter) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirValueParameter) firDeclaration).getName(), source);
        } else if (firDeclaration instanceof FirEnumEntry) {
            checkNameAndReport$org_jetbrains_kotlin_checkers_native(checkerContext, diagnosticReporter, ((FirEnumEntry) firDeclaration).getName(), source);
        }
    }

    public final void checkNameAndReport$org_jetbrains_kotlin_checkers_native(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Name name, KtSourceElement ktSourceElement) {
        String str;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        name.getClass();
        if (ktSourceElement == null || (ktSourceElement.getKind() instanceof KtFakeSourceElementKind) || name.isSpecial()) {
            return;
        }
        String strAsString = name.asString();
        strAsString.getClass();
        if (strAsString.length() != 0) {
            int i = 0;
            while (true) {
                if (i >= strAsString.length()) {
                    str = null;
                    break;
                }
                char cCharAt = strAsString.charAt(i);
                Set<Character> set = invalidChars;
                if (set.contains(Character.valueOf(cCharAt))) {
                    str = "contains illegal characters: " + CollectionsKt.joinToString$default(CollectionsKt.intersect(set, StringsKt.toSet(strAsString)), Argument.Delimiters.none, "\"", "\"", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
                    break;
                }
                i++;
            }
        } else {
            str = "should not be empty";
        }
        String str2 = str;
        if (str2 != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getINVALID_CHARACTERS_NATIVE_ERROR(), (Object) str2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
