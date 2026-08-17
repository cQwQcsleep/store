package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.AnnotationsWhitelistDescriptorRenderer;
import org.jetbrains.kotlin.platform.PlatformSpecificDiagnosticComponents;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.renderer.DescriptorRendererOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B.\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u001d\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00010\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u0005\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00010\u0006¢\u0006\u0002\b\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/AnnotationsWhitelistDescriptorRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DeclarationWithDiagnosticComponents;", "baseRenderer", "Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;", "toParameterRenderer", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lorg/jetbrains/kotlin/renderer/DescriptorRenderer;Lkotlin/jvm/functions/Function1;)V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationsWhitelistDescriptorRenderer implements DiagnosticParameterRenderer<DeclarationWithDiagnosticComponents> {
    private final DescriptorRenderer baseRenderer;
    private final Function1<DescriptorRenderer, DiagnosticParameterRenderer<DeclarationDescriptor>> toParameterRenderer;

    public AnnotationsWhitelistDescriptorRenderer(DescriptorRenderer descriptorRenderer, Function1<? super DescriptorRenderer, ? extends DiagnosticParameterRenderer<? super DeclarationDescriptor>> function1) {
        descriptorRenderer.getClass();
        function1.getClass();
        this.baseRenderer = descriptorRenderer;
        this.toParameterRenderer = function1;
    }

    public static Unit b(final PlatformSpecificDiagnosticComponents platformSpecificDiagnosticComponents, final DeclarationDescriptor declarationDescriptor, DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setAnnotationFilter(new Function1() { // from class: ea0
            public final Object invoke(Object obj) {
                return Boolean.valueOf(AnnotationsWhitelistDescriptorRenderer.render$lambda$0$0(platformSpecificDiagnosticComponents, declarationDescriptor, (AnnotationDescriptor) obj));
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean render$lambda$0$0(PlatformSpecificDiagnosticComponents platformSpecificDiagnosticComponents, DeclarationDescriptor declarationDescriptor, AnnotationDescriptor annotationDescriptor) {
        annotationDescriptor.getClass();
        return platformSpecificDiagnosticComponents.isNullabilityAnnotation(annotationDescriptor, declarationDescriptor);
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(DeclarationWithDiagnosticComponents obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        final DeclarationDescriptor declarationDescriptorComponent1 = obj.component1();
        final PlatformSpecificDiagnosticComponents platformSpecificDiagnosticComponentsComponent2 = obj.component2();
        return ((DiagnosticParameterRenderer) this.toParameterRenderer.invoke(this.baseRenderer.withOptions(new Function1() { // from class: da0
            public final Object invoke(Object obj2) {
                return AnnotationsWhitelistDescriptorRenderer.b(platformSpecificDiagnosticComponentsComponent2, declarationDescriptorComponent1, (DescriptorRendererOptions) obj2);
            }
        }))).render(declarationDescriptorComponent1, renderingContext);
    }
}
