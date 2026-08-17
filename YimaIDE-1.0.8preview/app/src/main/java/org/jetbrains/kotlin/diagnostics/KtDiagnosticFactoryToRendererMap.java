package org.jetbrains.kotlin.diagnostics;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\nH\u0086\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\nJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0003J2\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u00192\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00190\u001a2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001cJN\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d0\u001e2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001cJj\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d\"\u0004\b\u0002\u0010 2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H 0!2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001c2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u0002H \u0018\u00010\u001cJ\u0086\u0001\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010#2\u001e\u0010\r\u001a\u001a\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H#0$2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001c2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u0002H \u0018\u00010\u001c2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u0002H#\u0018\u00010\u001cJ\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020&2\u0006\u0010\u0017\u001a\u00020\u0003J2\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u00192\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00190'2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001cJN\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d0(2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001cJj\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d\"\u0004\b\u0002\u0010 2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H 0)2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001c2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u0002H \u0018\u00010\u001cJ\u0086\u0001\u0010\u0014\u001a\u00020\u0015\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010#2\u001e\u0010\r\u001a\u001a\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H#0*2\u0006\u0010\u0017\u001a\u00020\u00032\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u001d\u0018\u00010\u001c2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u0002H \u0018\u00010\u001c2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u0002H#\u0018\u00010\u001cJ\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\r\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u000bH\u0002J\u0018\u0010,\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030-2\u0006\u0010.\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "renderersMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/AbstractKtDiagnosticFactory;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "get", "factory", "factories", Argument.Delimiters.none, "getFactories", "()Ljava/util/Collection;", "containsKey", Argument.Delimiters.none, "put", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/KtSourcelessDiagnosticFactory;", "message", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "rendererA", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "B", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "rendererB", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "rendererC", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "rendererD", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation0;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation1;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation2;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation3;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation4;", "renderer", "warningMessage", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryForDeprecation;", "errorMessage", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtDiagnosticFactoryToRendererMap {
    private final String name;
    private final Map<AbstractKtDiagnosticFactory, KtDiagnosticRenderer> renderersMap;

    public KtDiagnosticFactoryToRendererMap(String str) {
        str.getClass();
        this.name = str;
        this.renderersMap = new LinkedHashMap();
    }

    private final String warningMessage(KtDiagnosticFactoryForDeprecation<?> ktDiagnosticFactoryForDeprecation, String str) {
        return RenderingUtilsKt.toDeprecationWarningMessage(str, ktDiagnosticFactoryForDeprecation.getDeprecatingFeature());
    }

    public final boolean containsKey(AbstractKtDiagnosticFactory factory) {
        factory.getClass();
        return this.renderersMap.containsKey(factory);
    }

    public final KtDiagnosticRenderer get(AbstractKtDiagnosticFactory factory) {
        factory.getClass();
        return this.renderersMap.get(factory);
    }

    public final Collection<AbstractKtDiagnosticFactory> getFactories() {
        return this.renderersMap.keySet();
    }

    public final String getName() {
        return this.name;
    }

    public final <A, B, C, D> void put(KtDiagnosticFactoryForDeprecation4<A, B, C, D> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB, DiagnosticParameterRenderer<? super C> rendererC, DiagnosticParameterRenderer<? super D> rendererD) {
        factory.getClass();
        message.getClass();
        put(factory.getErrorFactory(), new KtDiagnosticWithParameters4Renderer(message, rendererA, rendererB, rendererC, rendererD));
        put(factory.getWarningFactory(), new KtDiagnosticWithParameters4Renderer(warningMessage(factory, message), rendererA, rendererB, rendererC, rendererD));
    }

    public final void put(KtDiagnosticFactory0 factory, String message) {
        factory.getClass();
        message.getClass();
        put(factory, new SimpleKtDiagnosticRenderer(message));
    }

    public final <A> void put(KtDiagnosticFactory1<A> factory, String message, DiagnosticParameterRenderer<? super A> rendererA) {
        factory.getClass();
        message.getClass();
        put(factory, new KtDiagnosticWithParameters1Renderer(message, rendererA));
    }

    public final <A, B> void put(KtDiagnosticFactory2<A, B> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB) {
        factory.getClass();
        message.getClass();
        put(factory, new KtDiagnosticWithParameters2Renderer(message, rendererA, rendererB));
    }

    public final <A, B, C> void put(KtDiagnosticFactory3<A, B, C> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB, DiagnosticParameterRenderer<? super C> rendererC) {
        factory.getClass();
        message.getClass();
        put(factory, new KtDiagnosticWithParameters3Renderer(message, rendererA, rendererB, rendererC));
    }

    public final <A, B, C, D> void put(KtDiagnosticFactory4<A, B, C, D> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB, DiagnosticParameterRenderer<? super C> rendererC, DiagnosticParameterRenderer<? super D> rendererD) {
        factory.getClass();
        message.getClass();
        put(factory, new KtDiagnosticWithParameters4Renderer(message, rendererA, rendererB, rendererC, rendererD));
    }

    public final void put(KtDiagnosticFactoryForDeprecation0 factory, String message) {
        factory.getClass();
        message.getClass();
        put(factory.getErrorFactory(), new SimpleKtDiagnosticRenderer(message));
        put(factory.getWarningFactory(), new SimpleKtDiagnosticRenderer(warningMessage(factory, message)));
    }

    public final <A> void put(KtDiagnosticFactoryForDeprecation1<A> factory, String message, DiagnosticParameterRenderer<? super A> rendererA) {
        factory.getClass();
        message.getClass();
        put(factory.getErrorFactory(), new KtDiagnosticWithParameters1Renderer(message, rendererA));
        put(factory.getWarningFactory(), new KtDiagnosticWithParameters1Renderer(warningMessage(factory, message), rendererA));
    }

    public final <A, B> void put(KtDiagnosticFactoryForDeprecation2<A, B> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB) {
        factory.getClass();
        message.getClass();
        put(factory.getErrorFactory(), new KtDiagnosticWithParameters2Renderer(message, rendererA, rendererB));
        put(factory.getWarningFactory(), new KtDiagnosticWithParameters2Renderer(warningMessage(factory, message), rendererA, rendererB));
    }

    public final <A, B, C> void put(KtDiagnosticFactoryForDeprecation3<A, B, C> factory, String message, DiagnosticParameterRenderer<? super A> rendererA, DiagnosticParameterRenderer<? super B> rendererB, DiagnosticParameterRenderer<? super C> rendererC) {
        factory.getClass();
        message.getClass();
        put(factory.getErrorFactory(), new KtDiagnosticWithParameters3Renderer(message, rendererA, rendererB, rendererC));
        put(factory.getWarningFactory(), new KtDiagnosticWithParameters3Renderer(warningMessage(factory, message), rendererA, rendererB, rendererC));
    }

    public final void put(KtSourcelessDiagnosticFactory factory, String message) {
        factory.getClass();
        message.getClass();
        put(factory, new KtSourcelessDiagnosticRenderer(message));
    }

    private final void put(AbstractKtDiagnosticFactory factory, KtDiagnosticRenderer renderer) {
        if (!this.renderersMap.containsKey(factory)) {
            this.renderersMap.put(factory, renderer);
        } else {
            qu7.a("Diagnostic renderer is already initialized for ", factory);
        }
    }
}
