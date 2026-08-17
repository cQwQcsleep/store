package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u0004B3\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010JW\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u00022\b\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007b\u0002\b\u001c¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "A", "B", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "defaultPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "a", "b", "c", "positioningStrategy", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/InternalDiagnosticFactoryMethod;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactory3<A, B, C> extends KtDiagnosticFactoryN {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticFactory3(String str, Severity severity, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, severity, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory, null);
        str.getClass();
        severity.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        baseDiagnosticRendererFactory.getClass();
    }

    @InternalDiagnosticFactoryMethod
    public final KtDiagnosticWithParameters3<A, B, C> on(AbstractKtSourceElement element, A a, B b, C c, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        context.getClass();
        Severity effectiveSeverity = getEffectiveSeverity(context.getLanguageVersionSettings());
        if (effectiveSeverity == null) {
            return null;
        }
        if (element instanceof KtPsiSourceElement) {
            KtPsiSourceElement ktPsiSourceElement = (KtPsiSourceElement) element;
            if (positioningStrategy == null) {
                positioningStrategy = getDefaultPositioningStrategy();
            }
            return new KtPsiDiagnosticWithParameters3(ktPsiSourceElement, a, b, c, effectiveSeverity, this, positioningStrategy, context);
        }
        if (!(element instanceof KtLightSourceElement)) {
            if (positioningStrategy == null) {
                positioningStrategy = getDefaultPositioningStrategy();
            }
            return new KtOffsetsOnlyDiagnosticWithParameters3(element, a, b, c, effectiveSeverity, this, positioningStrategy, context);
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) element;
        if (positioningStrategy == null) {
            positioningStrategy = getDefaultPositioningStrategy();
        }
        return new KtLightDiagnosticWithParameters3(ktLightSourceElement, a, b, c, effectiveSeverity, this, positioningStrategy, context);
    }
}
