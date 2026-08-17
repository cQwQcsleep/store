package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryN;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B)\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011\u0082\u0001\u0005\u0014\u0015\u0016\u0017\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation;", "F", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "deprecatingFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningFactory", "errorFactory", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;)V", "getName", "()Ljava/lang/String;", "getDeprecatingFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "getWarningFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", "getErrorFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation3;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation4;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticFactoryForDeprecation<F extends KtDiagnosticFactoryN> {
    private final LanguageFeature deprecatingFeature;
    private final F errorFactory;
    private final String name;
    private final F warningFactory;

    private KtDiagnosticFactoryForDeprecation(String str, LanguageFeature languageFeature, F f, F f2) {
        this.name = str;
        this.deprecatingFeature = languageFeature;
        this.warningFactory = f;
        this.errorFactory = f2;
    }

    public final LanguageFeature getDeprecatingFeature() {
        return this.deprecatingFeature;
    }

    public final F getErrorFactory() {
        return this.errorFactory;
    }

    public final String getName() {
        return this.name;
    }

    public final F getWarningFactory() {
        return this.warningFactory;
    }

    public /* synthetic */ KtDiagnosticFactoryForDeprecation(String str, LanguageFeature languageFeature, KtDiagnosticFactoryN ktDiagnosticFactoryN, KtDiagnosticFactoryN ktDiagnosticFactoryN2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, languageFeature, ktDiagnosticFactoryN, ktDiagnosticFactoryN2);
    }
}
