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
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ(\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0007b\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryN;", ModuleXmlParser.NAME, Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "defaultPositioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "psiType", "Lkotlin/reflect/KClass;", "rendererFactory", "Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/diagnostics/Severity;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;Lkotlin/reflect/KClass;Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;)V", "on", "Lorg/jetbrains/kotlin/diagnostics/KtSimpleDiagnostic;", "element", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "positioningStrategy", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "Lorg/jetbrains/kotlin/diagnostics/InternalDiagnosticFactoryMethod;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactory0 extends KtDiagnosticFactoryN {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KtDiagnosticFactory0(String str, Severity severity, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, KClass<?> kClass, BaseDiagnosticRendererFactory baseDiagnosticRendererFactory) {
        super(str, severity, abstractSourceElementPositioningStrategy, kClass, baseDiagnosticRendererFactory, null);
        str.getClass();
        severity.getClass();
        abstractSourceElementPositioningStrategy.getClass();
        kClass.getClass();
        baseDiagnosticRendererFactory.getClass();
    }

    @InternalDiagnosticFactoryMethod
    public final KtSimpleDiagnostic on(AbstractKtSourceElement element, AbstractSourceElementPositioningStrategy positioningStrategy, DiagnosticBaseContext context) {
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
            return new KtPsiSimpleDiagnostic(ktPsiSourceElement, effectiveSeverity, this, positioningStrategy, context);
        }
        if (!(element instanceof KtLightSourceElement)) {
            if (positioningStrategy == null) {
                positioningStrategy = getDefaultPositioningStrategy();
            }
            return new KtOffsetsOnlySimpleDiagnostic(element, effectiveSeverity, this, positioningStrategy, context);
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) element;
        if (positioningStrategy == null) {
            positioningStrategy = getDefaultPositioningStrategy();
        }
        return new KtLightSimpleDiagnostic(ktLightSourceElement, effectiveSeverity, this, positioningStrategy, context);
    }
}
