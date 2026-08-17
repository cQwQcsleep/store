package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.diagnostics.rendering.ExpectActualAnnotationIncompatibilityDiagnosticRenderers;
import org.jetbrains.kotlin.renderer.AnnotationArgumentsRenderingPolicy;
import org.jetbrains.kotlin.renderer.ClassifierNamePolicy;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.renderer.DescriptorRendererOptions;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualAnnotationsIncompatibilityType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004\u0092\u0002\u0002\b\b¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n8\u0006X\u0087\u0004\u0092\u0002\u0002\b\b¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/ExpectActualAnnotationIncompatibilityDiagnosticRenderers;", Argument.Delimiters.none, "<init>", "()V", "descriptorRender", "Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;", "DESCRIPTOR_RENDERER", "Lorg/jetbrains/kotlin/diagnostics/rendering/SmartDescriptorRenderer;", "Lkotlin/jvm/JvmField;", "INCOMPATIBILITY", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualAnnotationsIncompatibilityType;", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationDescriptor;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpectActualAnnotationIncompatibilityDiagnosticRenderers {
    public static final SmartDescriptorRenderer DESCRIPTOR_RENDERER;
    public static final ContextIndependentParameterRenderer<ExpectActualAnnotationsIncompatibilityType<? extends AnnotationDescriptor>> INCOMPATIBILITY;
    public static final ExpectActualAnnotationIncompatibilityDiagnosticRenderers INSTANCE = new ExpectActualAnnotationIncompatibilityDiagnosticRenderers();
    private static final DescriptorRenderer descriptorRender;

    static {
        DescriptorRenderer descriptorRendererWithOptions = DescriptorRenderer.Companion.withOptions(new Function1() { // from class: ke4
            public final Object invoke(Object obj) {
                return ExpectActualAnnotationIncompatibilityDiagnosticRenderers.a((DescriptorRendererOptions) obj);
            }
        });
        descriptorRender = descriptorRendererWithOptions;
        DESCRIPTOR_RENDERER = RenderersKt.asRenderer(descriptorRendererWithOptions);
        INCOMPATIBILITY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: le4
            public final Object invoke(Object obj) {
                return ExpectActualAnnotationIncompatibilityDiagnosticRenderers.b((ExpectActualAnnotationsIncompatibilityType) obj);
            }
        });
    }

    private ExpectActualAnnotationIncompatibilityDiagnosticRenderers() {
    }

    public static Unit a(DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy.UNLESS_EMPTY);
        descriptorRendererOptions.setModifiers(SetsKt.emptySet());
        descriptorRendererOptions.setWithDefinedIn(true);
        descriptorRendererOptions.setClassifierNamePolicy(ClassifierNamePolicy.SHORT.INSTANCE);
        return Unit.INSTANCE;
    }

    public static String b(ExpectActualAnnotationsIncompatibilityType expectActualAnnotationsIncompatibilityType) {
        expectActualAnnotationsIncompatibilityType.getClass();
        StringBuilder sb = new StringBuilder("Annotation `");
        DescriptorRenderer descriptorRenderer = descriptorRender;
        sb.append(DescriptorRenderer.renderAnnotation$default(descriptorRenderer, (AnnotationDescriptor) expectActualAnnotationsIncompatibilityType.getExpectAnnotation(), (AnnotationUseSiteTarget) null, 2, (Object) null));
        sb.append("` ");
        if (expectActualAnnotationsIncompatibilityType instanceof ExpectActualAnnotationsIncompatibilityType.MissingOnActual) {
            sb.append("is missing on actual declaration");
        } else {
            if (!(expectActualAnnotationsIncompatibilityType instanceof ExpectActualAnnotationsIncompatibilityType.DifferentOnActual)) {
                bu8.a();
                return null;
            }
            sb.append("has different arguments on actual declaration: `");
            sb.append(DescriptorRenderer.renderAnnotation$default(descriptorRenderer, (AnnotationDescriptor) ((ExpectActualAnnotationsIncompatibilityType.DifferentOnActual) expectActualAnnotationsIncompatibilityType).getActualAnnotation(), (AnnotationUseSiteTarget) null, 2, (Object) null));
            sb.append("`");
        }
        return sb.toString();
    }
}
