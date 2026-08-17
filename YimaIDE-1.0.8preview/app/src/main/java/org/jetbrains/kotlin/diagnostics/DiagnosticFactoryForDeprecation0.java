package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0013*\b\b\u0000\u0010\u0001*\u00020\u00022 \u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u0003:\u0001\u0013B+\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u000f\u001a\u00028\u0000¢\u0006\u0002\u0010\u0012¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation0;", "E", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation;", "Lorg/jetbrains/kotlin/diagnostics/SimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningFactory", "errorFactory", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0;)V", "on", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "element", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lcom/intellij/psi/PsiElement;)Lorg/jetbrains/kotlin/diagnostics/SimpleDiagnostic;", "onError", "(Lcom/intellij/psi/PsiElement;)Lorg/jetbrains/kotlin/diagnostics/SimpleDiagnostic;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticFactoryForDeprecation0<E extends PsiElement> extends DiagnosticFactoryForDeprecation<E, SimpleDiagnostic<E>, DiagnosticFactory0<E>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticFactoryForDeprecation0(LanguageFeature languageFeature, DiagnosticFactory0<E> diagnosticFactory0, DiagnosticFactory0<E> diagnosticFactory1) {
        super(languageFeature, diagnosticFactory0, diagnosticFactory1, null);
        languageFeature.getClass();
        diagnosticFactory0.getClass();
        diagnosticFactory1.getClass();
    }

    @JvmStatic
    public static final <E extends PsiElement> DiagnosticFactoryForDeprecation0<E> create(LanguageFeature languageFeature) {
        return INSTANCE.create(languageFeature);
    }

    public final SimpleDiagnostic<E> on(LanguageVersionSettings languageVersionSettings, E element) {
        languageVersionSettings.getClass();
        element.getClass();
        SimpleDiagnostic<E> simpleDiagnosticOn = chooseFactory(languageVersionSettings).on(element);
        simpleDiagnosticOn.getClass();
        return simpleDiagnosticOn;
    }

    public final SimpleDiagnostic<E> onError(E element) {
        element.getClass();
        SimpleDiagnostic<E> simpleDiagnosticOn = getErrorFactory().on(element);
        simpleDiagnosticOn.getClass();
        return simpleDiagnosticOn;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\b\b\u0001\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000bH\u0007b\u0002\b\fb\u0002\b\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation0$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation0;", "E", "Lcom/intellij/psi/PsiElement;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lkotlin/jvm/JvmStatic;", "Lkotlin/jvm/JvmOverloads;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DiagnosticFactoryForDeprecation0 create$default(Companion companion, LanguageFeature languageFeature, PositioningStrategy positioningStrategy, int i, Object obj) {
            if ((i & 2) != 0) {
                positioningStrategy = PositioningStrategies.DEFAULT;
            }
            return companion.create(languageFeature, positioningStrategy);
        }

        @JvmStatic
        public final <E extends PsiElement> DiagnosticFactoryForDeprecation0<E> create(LanguageFeature featureForError, PositioningStrategy<? super E> positioningStrategy) {
            featureForError.getClass();
            positioningStrategy.getClass();
            DiagnosticFactory0 diagnosticFactory0Create = DiagnosticFactory0.create(Severity.WARNING, positioningStrategy);
            diagnosticFactory0Create.getClass();
            DiagnosticFactory0 diagnosticFactory0Create2 = DiagnosticFactory0.create(Severity.ERROR, positioningStrategy);
            diagnosticFactory0Create2.getClass();
            return new DiagnosticFactoryForDeprecation0<>(featureForError, diagnosticFactory0Create, diagnosticFactory0Create2);
        }

        private Companion() {
        }

        @JvmStatic
        public final <E extends PsiElement> DiagnosticFactoryForDeprecation0<E> create(LanguageFeature languageFeature) {
            languageFeature.getClass();
            return create$default(this, languageFeature, null, 2, null);
        }
    }

    @JvmStatic
    public static final <E extends PsiElement> DiagnosticFactoryForDeprecation0<E> create(LanguageFeature languageFeature, PositioningStrategy<? super E> positioningStrategy) {
        return INSTANCE.create(languageFeature, positioningStrategy);
    }
}
