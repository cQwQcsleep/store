package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.SmartTypeRenderer;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.renderer.DescriptorRendererOptions;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/SmartTypeRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lorg/jetbrains/kotlin/types/KotlinType;", "baseRenderer", "Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;", "<init>", "(Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;)V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SmartTypeRenderer implements DiagnosticParameterRenderer<KotlinType> {
    private final DescriptorRenderer baseRenderer;

    public SmartTypeRenderer(DescriptorRenderer descriptorRenderer) {
        descriptorRenderer.getClass();
        this.baseRenderer = descriptorRenderer;
    }

    public static Unit a(RenderingContext renderingContext, DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setClassifierNamePolicy(AdaptiveClassifierNamePolicyKt.getAdaptiveClassifierPolicy(renderingContext));
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(KotlinType obj, final RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        return this.baseRenderer.withOptions(new Function1() { // from class: nhd
            public final Object invoke(Object obj2) {
                return SmartTypeRenderer.a(renderingContext, (DescriptorRendererOptions) obj2);
            }
        }).renderType(obj);
    }
}
