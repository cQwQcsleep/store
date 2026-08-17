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
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001b*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0004*\b\b\u0002\u0010\u0005*\u00020\u0004*\b\b\u0003\u0010\u0006*\u00020\u0004*\b\b\u0004\u0010\u0007*\u00020\u00042P\u0012\u0004\u0012\u0002H\u0001\u0012\"\u0012 \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\t\u0012\"\u0012 \u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\n0\b:\u0001\u001bB[\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012$\u0010\r\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\n\u0012$\u0010\u000e\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\n¢\u0006\u0004\b\u000f\u0010\u0010JA\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u00022\u0006\u0010\u0018\u001a\u00028\u00032\u0006\u0010\u0019\u001a\u00028\u0004¢\u0006\u0002\u0010\u001a¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation4;", "E", "Lcom/intellij/psi/PsiElement;", "A", Argument.Delimiters.none, "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory4;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "warningFactory", "errorFactory", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory4;Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory4;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/ParametrizedDiagnostic;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "element", "a", "b", "c", "d", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lcom/intellij/psi/PsiElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/diagnostics/ParametrizedDiagnostic;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DiagnosticFactoryForDeprecation4<E extends PsiElement, A, B, C, D> extends DiagnosticFactoryForDeprecation<E, DiagnosticWithParameters4<E, A, B, C, D>, DiagnosticFactory4<E, A, B, C, D>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticFactoryForDeprecation4(LanguageFeature languageFeature, DiagnosticFactory4<E, A, B, C, D> diagnosticFactory4, DiagnosticFactory4<E, A, B, C, D> diagnosticFactory5) {
        super(languageFeature, diagnosticFactory4, diagnosticFactory5, null);
        languageFeature.getClass();
        diagnosticFactory4.getClass();
        diagnosticFactory5.getClass();
    }

    @JvmStatic
    public static final <E extends PsiElement, A, B, C, D> DiagnosticFactoryForDeprecation4<E, A, B, C, D> create(LanguageFeature languageFeature) {
        return INSTANCE.create(languageFeature);
    }

    public final ParametrizedDiagnostic<E> on(LanguageVersionSettings languageVersionSettings, E element, A a, B b, C c, D d) {
        languageVersionSettings.getClass();
        element.getClass();
        a.getClass();
        b.getClass();
        c.getClass();
        d.getClass();
        ParametrizedDiagnostic<E> parametrizedDiagnosticOn = chooseFactory(languageVersionSettings).on(element, a, b, c, d);
        parametrizedDiagnosticOn.getClass();
        return parametrizedDiagnosticOn;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jx\u0010\u0004\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\u0005\"\b\b\u0005\u0010\u0006*\u00020\u000b\"\b\b\u0006\u0010\u0007*\u00020\u0001\"\b\b\u0007\u0010\b*\u00020\u0001\"\b\b\b\u0010\t*\u00020\u0001\"\b\b\t\u0010\n*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000fH\u0007b\u0002\b\u0010b\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation4$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactoryForDeprecation4;", "E", "A", "B", "C", "D", "Lcom/intellij/psi/PsiElement;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/PositioningStrategy;", "Lkotlin/jvm/JvmStatic;", "Lkotlin/jvm/JvmOverloads;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DiagnosticFactoryForDeprecation4 create$default(Companion companion, LanguageFeature languageFeature, PositioningStrategy positioningStrategy, int i, Object obj) {
            if ((i & 2) != 0) {
                positioningStrategy = PositioningStrategies.DEFAULT;
            }
            return companion.create(languageFeature, positioningStrategy);
        }

        @JvmStatic
        public final <E extends PsiElement, A, B, C, D> DiagnosticFactoryForDeprecation4<E, A, B, C, D> create(LanguageFeature featureForError, PositioningStrategy<? super E> positioningStrategy) {
            featureForError.getClass();
            positioningStrategy.getClass();
            DiagnosticFactory4 diagnosticFactory4Create = DiagnosticFactory4.create(Severity.WARNING, positioningStrategy);
            diagnosticFactory4Create.getClass();
            DiagnosticFactory4 diagnosticFactory4Create2 = DiagnosticFactory4.create(Severity.ERROR, positioningStrategy);
            diagnosticFactory4Create2.getClass();
            return new DiagnosticFactoryForDeprecation4<>(featureForError, diagnosticFactory4Create, diagnosticFactory4Create2);
        }

        private Companion() {
        }

        @JvmStatic
        public final <E extends PsiElement, A, B, C, D> DiagnosticFactoryForDeprecation4<E, A, B, C, D> create(LanguageFeature languageFeature) {
            languageFeature.getClass();
            return create$default(this, languageFeature, null, 2, null);
        }
    }

    @JvmStatic
    public static final <E extends PsiElement, A, B, C, D> DiagnosticFactoryForDeprecation4<E, A, B, C, D> create(LanguageFeature languageFeature, PositioningStrategy<? super E> positioningStrategy) {
        return INSTANCE.create(languageFeature, positioningStrategy);
    }
}
