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
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0017*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u000428\u0012\u0004\u0012\u0002H\u0001\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00050\b0\u0006:\u0001\u0017BC\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b\u0012\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ1\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0015\u001a\u00028\u0002¢\u0006\u0002\u0010\u0016¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation2;", "E", "Lcom/intellij/psi/PsiElement;", "A", Argument.Delimiters.none, "B", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory2;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningFactory", "errorFactory", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory2;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory2;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/ParametrizedDiagnostic;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "element", "a", "b", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lcom/intellij/psi/PsiElement;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/diagnostics/ParametrizedDiagnostic;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticFactoryForDeprecation2<E extends PsiElement, A, B> extends DiagnosticFactoryForDeprecation<E, DiagnosticWithParameters2<E, A, B>, DiagnosticFactory2<E, A, B>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticFactoryForDeprecation2(LanguageFeature languageFeature, DiagnosticFactory2<E, A, B> diagnosticFactory2, DiagnosticFactory2<E, A, B> diagnosticFactory3) {
        super(languageFeature, diagnosticFactory2, diagnosticFactory3, null);
        languageFeature.getClass();
        diagnosticFactory2.getClass();
        diagnosticFactory3.getClass();
    }

    @JvmStatic
    public static final <E extends PsiElement, A, B> DiagnosticFactoryForDeprecation2<E, A, B> create(LanguageFeature languageFeature) {
        return INSTANCE.create(languageFeature);
    }

    public final ParametrizedDiagnostic<E> on(LanguageVersionSettings languageVersionSettings, E element, A a, B b) {
        languageVersionSettings.getClass();
        element.getClass();
        a.getClass();
        b.getClass();
        ParametrizedDiagnostic<E> parametrizedDiagnosticOn = chooseFactory(languageVersionSettings).on(element, a, b);
        parametrizedDiagnosticOn.getClass();
        return parametrizedDiagnosticOn;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\u0005\"\b\b\u0003\u0010\u0006*\u00020\t\"\b\b\u0004\u0010\u0007*\u00020\u0001\"\b\b\u0005\u0010\b*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00060\rH\u0007b\u0002\b\u000eb\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation2$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation2;", "E", "A", "B", "Lcom/intellij/psi/PsiElement;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lkotlin/jvm/JvmStatic;", "Lkotlin/jvm/JvmOverloads;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DiagnosticFactoryForDeprecation2 create$default(Companion companion, LanguageFeature languageFeature, PositioningStrategy positioningStrategy, int i, Object obj) {
            if ((i & 2) != 0) {
                positioningStrategy = PositioningStrategies.DEFAULT;
            }
            return companion.create(languageFeature, positioningStrategy);
        }

        @JvmStatic
        public final <E extends PsiElement, A, B> DiagnosticFactoryForDeprecation2<E, A, B> create(LanguageFeature featureForError, PositioningStrategy<? super E> positioningStrategy) {
            featureForError.getClass();
            positioningStrategy.getClass();
            DiagnosticFactory2 diagnosticFactory2Create = DiagnosticFactory2.create(Severity.WARNING, positioningStrategy);
            diagnosticFactory2Create.getClass();
            DiagnosticFactory2 diagnosticFactory2Create2 = DiagnosticFactory2.create(Severity.ERROR, positioningStrategy);
            diagnosticFactory2Create2.getClass();
            return new DiagnosticFactoryForDeprecation2<>(featureForError, diagnosticFactory2Create, diagnosticFactory2Create2);
        }

        private Companion() {
        }

        @JvmStatic
        public final <E extends PsiElement, A, B> DiagnosticFactoryForDeprecation2<E, A, B> create(LanguageFeature languageFeature) {
            languageFeature.getClass();
            return create$default(this, languageFeature, null, 2, null);
        }
    }

    @JvmStatic
    public static final <E extends PsiElement, A, B> DiagnosticFactoryForDeprecation2<E, A, B> create(LanguageFeature languageFeature, PositioningStrategy<? super E> positioningStrategy) {
        return INSTANCE.create(languageFeature, positioningStrategy);
    }
}
