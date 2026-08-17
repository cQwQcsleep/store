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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ;\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007b\u0002\b\u0018¢\u0006\u0002\u0010\u0017¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "defaultPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "a", "positioningStrategy", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/InternalDiagnosticFactoryMethod;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactory1<A> extends KtDiagnosticFactoryN {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticFactory1(String str, Severity severity, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, severity, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory, null);
        str.getClass();
        severity.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        baseDiagnosticRendererFactory.getClass();
    }

    @InternalDiagnosticFactoryMethod
    public final KtDiagnosticWithParameters1<A> on(AbstractKtSourceElement element, A a, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
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
            return new KtPsiDiagnosticWithParameters1(ktPsiSourceElement, a, effectiveSeverity, this, positioningStrategy, context);
        }
        if (!(element instanceof KtLightSourceElement)) {
            if (positioningStrategy == null) {
                positioningStrategy = getDefaultPositioningStrategy();
            }
            return new KtOffsetsOnlyDiagnosticWithParameters1(element, a, effectiveSeverity, this, positioningStrategy, context);
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) element;
        if (positioningStrategy == null) {
            positioningStrategy = getDefaultPositioningStrategy();
        }
        return new KtLightDiagnosticWithParameters1(ktLightSourceElement, a, effectiveSeverity, this, positioningStrategy, context);
    }
}
