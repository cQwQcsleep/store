package org.jetbrains.kotlin.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004\u001a\u0017\u0010\u0005\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004\u001a0\u0010\u0006\u001a\u00020\u0007\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a<\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0010\u001aH\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00130\u0012\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u00132\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014\u001aT\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00170\u0016\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u00172\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0018\u001a`\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u001b0\u001a\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u0017\"\u0004\b\u0004\u0010\u001b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a\u0017\u0010\u001d\u001a\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004\u001a0\u0010\u001e\u001a\u00020\u0007\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a<\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u000e\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0010\u001aH\u0010 \u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00130\u0012\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u00132\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014\u001aT\u0010!\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00170\u0016\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u00172\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0018\u001a`\u0010\"\u001a\u001a\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u001b0\u001a\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u0017\"\u0004\b\u0004\u0010\u001b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001c\u001a8\u0010#\u001a\u00020$\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010'\u001aD\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u000f0)\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010*\u001aP\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u00130,\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u00132\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010-\u001a\\\u0010.\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u00170/\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u00172\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u00100\u001ah\u00101\u001a\u001a\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0013\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u001b02\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t\"\u0004\b\u0001\u0010\u000f\"\u0004\b\u0002\u0010\u0013\"\u0004\b\u0003\u0010\u0017\"\u0004\b\u0004\u0010\u001b2\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0086\bR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u00103¨\u00064"}, d2 = {"strongWarningWithoutSource", "Lorg/jetbrains/kotlin/diagnostics/SourcelessDiagnosticFactoryDelegateProvider;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;", "container", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;)Lorg/jetbrains/kotlin/diagnostics/SourcelessDiagnosticFactoryDelegateProvider;", "warningWithoutSource", "warning0", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0DelegateProvider;", "P", "Lcom/intellij/psi/PsiElement;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory0DelegateProvider;", "warning1", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory1DelegateProvider;", "A", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory1DelegateProvider;", "warning2", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory2DelegateProvider;", "B", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory2DelegateProvider;", "warning3", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory3DelegateProvider;", "C", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory3DelegateProvider;", "warning4", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory4DelegateProvider;", "D", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DiagnosticFactory4DelegateProvider;", "errorWithoutSource", "error0", "error1", "error2", "error3", "error4", "deprecationError0", "Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory0DelegateProvider;", "featureForError", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory0DelegateProvider;", "deprecationError1", "Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory1DelegateProvider;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory1DelegateProvider;", "deprecationError2", "Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory2DelegateProvider;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory2DelegateProvider;", "deprecationError3", "Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory3DelegateProvider;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory3DelegateProvider;", "deprecationError4", "Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory4DelegateProvider;", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticsContainer;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/DeprecationDiagnosticFactory4DelegateProvider;", "org.jetbrains.kotlin:frontend.common-psi"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactoryDslKt {
    public static final /* synthetic */ <P extends PsiElement> DeprecationDiagnosticFactory0DelegateProvider deprecationError0(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory0DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DeprecationDiagnosticFactory0DelegateProvider deprecationError0$default(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory0DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A> DeprecationDiagnosticFactory1DelegateProvider<A> deprecationError1(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory1DelegateProvider<>(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DeprecationDiagnosticFactory1DelegateProvider deprecationError1$default(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory1DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B> DeprecationDiagnosticFactory2DelegateProvider<A, B> deprecationError2(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory2DelegateProvider<>(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DeprecationDiagnosticFactory2DelegateProvider deprecationError2$default(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory2DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C> DeprecationDiagnosticFactory3DelegateProvider<A, B, C> deprecationError3(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory3DelegateProvider<>(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DeprecationDiagnosticFactory3DelegateProvider deprecationError3$default(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory3DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C, D> DeprecationDiagnosticFactory4DelegateProvider<A, B, C, D> deprecationError4(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory4DelegateProvider<>(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DeprecationDiagnosticFactory4DelegateProvider deprecationError4$default(KtDiagnosticsContainer ktDiagnosticsContainer, LanguageFeature languageFeature, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        languageFeature.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DeprecationDiagnosticFactory4DelegateProvider(languageFeature, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement> DiagnosticFactory0DelegateProvider error0(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory0DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory0DelegateProvider error0$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory0DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A> DiagnosticFactory1DelegateProvider<A> error1(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory1DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory1DelegateProvider error1$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory1DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B> DiagnosticFactory2DelegateProvider<A, B> error2(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory2DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory2DelegateProvider error2$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory2DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C> DiagnosticFactory3DelegateProvider<A, B, C> error3(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory3DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory3DelegateProvider error3$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory3DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C, D> DiagnosticFactory4DelegateProvider<A, B, C, D> error4(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory4DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory4DelegateProvider error4$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.ERROR;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory4DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final SourcelessDiagnosticFactoryDelegateProvider errorWithoutSource(KtDiagnosticsContainer ktDiagnosticsContainer) {
        ktDiagnosticsContainer.getClass();
        return new SourcelessDiagnosticFactoryDelegateProvider(Severity.ERROR, ktDiagnosticsContainer);
    }

    public static final SourcelessDiagnosticFactoryDelegateProvider strongWarningWithoutSource(KtDiagnosticsContainer ktDiagnosticsContainer) {
        ktDiagnosticsContainer.getClass();
        return new SourcelessDiagnosticFactoryDelegateProvider(Severity.STRONG_WARNING, ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement> DiagnosticFactory0DelegateProvider warning0(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory0DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory0DelegateProvider warning0$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory0DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A> DiagnosticFactory1DelegateProvider<A> warning1(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory1DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory1DelegateProvider warning1$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory1DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B> DiagnosticFactory2DelegateProvider<A, B> warning2(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory2DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory2DelegateProvider warning2$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory2DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C> DiagnosticFactory3DelegateProvider<A, B, C> warning3(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory3DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory3DelegateProvider warning3$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory3DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final /* synthetic */ <P extends PsiElement, A, B, C, D> DiagnosticFactory4DelegateProvider<A, B, C, D> warning4(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory4DelegateProvider<>(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static /* synthetic */ DiagnosticFactory4DelegateProvider warning4$default(KtDiagnosticsContainer ktDiagnosticsContainer, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            abstractSourceElementPositioningStrategy = SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
        }
        ktDiagnosticsContainer.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        Severity severity = Severity.WARNING;
        Intrinsics.reifiedOperationMarker(4, "P");
        return new DiagnosticFactory4DelegateProvider(severity, abstractSourceElementPositioningStrategy, Reflection.getOrCreateKotlinClass(PsiElement.class), ktDiagnosticsContainer);
    }

    public static final SourcelessDiagnosticFactoryDelegateProvider warningWithoutSource(KtDiagnosticsContainer ktDiagnosticsContainer) {
        ktDiagnosticsContainer.getClass();
        return new SourcelessDiagnosticFactoryDelegateProvider(Severity.WARNING, ktDiagnosticsContainer);
    }
}
