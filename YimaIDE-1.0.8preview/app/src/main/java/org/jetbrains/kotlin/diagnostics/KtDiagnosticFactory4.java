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
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005B3\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011Je\u0010\u0012\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u00012\u0006\u0010\u0018\u001a\u00028\u00022\u0006\u0010\u0019\u001a\u00028\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007b\u0002\b\u001e¢\u0006\u0002\u0010\u001d¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "A", "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "defaultPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "a", "b", "c", "d", "positioningStrategy", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/InternalDiagnosticFactoryMethod;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactory4<A, B, C, D> extends KtDiagnosticFactoryN {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticFactory4(String str, Severity severity, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, severity, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory, null);
        str.getClass();
        severity.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        baseDiagnosticRendererFactory.getClass();
    }

    @InternalDiagnosticFactoryMethod
    public final KtDiagnosticWithParameters4<A, B, C, D> on(AbstractKtSourceElement element, A a, B b, C c, D d, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
        element.getClass();
        context.getClass();
        Severity effectiveSeverity = getEffectiveSeverity(context.getLanguageVersionSettings());
        if (effectiveSeverity == null) {
            return null;
        }
        if (element instanceof KtPsiSourceElement) {
            return new KtPsiDiagnosticWithParameters4((KtPsiSourceElement) element, a, b, c, d, effectiveSeverity, this, positioningStrategy == null ? getDefaultPositioningStrategy() : positioningStrategy, context);
        }
        if (element instanceof KtLightSourceElement) {
            return new KtLightDiagnosticWithParameters4((KtLightSourceElement) element, a, b, c, d, effectiveSeverity, this, positioningStrategy == null ? getDefaultPositioningStrategy() : positioningStrategy, context);
        }
        return new KtOffsetsOnlyDiagnosticWithParameters4(element, a, b, c, d, effectiveSeverity, this, positioningStrategy == null ? getDefaultPositioningStrategy() : positioningStrategy, context);
    }
}
