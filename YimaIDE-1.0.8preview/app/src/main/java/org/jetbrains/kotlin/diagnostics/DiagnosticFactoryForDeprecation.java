package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactoryWithPsiElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\u0014\b\u0002\u0010\u0005*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u00020\u0007B!\b\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00028\u0002\u0012\u0006\u0010\u000b\u001a\u00028\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u0014\u001a\u00028\u0002*\u00020\u0015¢\u0006\u0002\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\n\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000b\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011\u0082\u0001\u0005\u0017\u0018\u0019\u001a\u001b¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation;", "E", "Lcom/intellij/psi/PsiElement;", "D", "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "F", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;", Argument.Delimiters.none, "deprecatingFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningFactory", "errorFactory", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;)V", "getDeprecatingFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "getWarningFactory", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;", "getErrorFactory", "chooseFactory", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryWithPsiElement;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation0;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation1;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation2;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation3;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation4;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DiagnosticFactoryForDeprecation<E extends PsiElement, D extends Diagnostic, F extends DiagnosticFactoryWithPsiElement<E, D>> {
    private final LanguageFeature deprecatingFeature;
    private final F errorFactory;
    private final F warningFactory;

    private DiagnosticFactoryForDeprecation(LanguageFeature languageFeature, F f, F f2) {
        this.deprecatingFeature = languageFeature;
        this.warningFactory = f;
        this.errorFactory = f2;
    }

    public final F chooseFactory(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        return languageVersionSettings.supportsFeature(this.deprecatingFeature) ? this.errorFactory : this.warningFactory;
    }

    public final LanguageFeature getDeprecatingFeature() {
        return this.deprecatingFeature;
    }

    public final F getErrorFactory() {
        return this.errorFactory;
    }

    public final F getWarningFactory() {
        return this.warningFactory;
    }

    public /* synthetic */ DiagnosticFactoryForDeprecation(LanguageFeature languageFeature, DiagnosticFactoryWithPsiElement diagnosticFactoryWithPsiElement, DiagnosticFactoryWithPsiElement diagnosticFactoryWithPsiElement2, DefaultConstructorMarker defaultConstructorMarker) {
        this(languageFeature, diagnosticFactoryWithPsiElement, diagnosticFactoryWithPsiElement2);
    }
}
