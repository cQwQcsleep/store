package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.Collection;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2 \u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ0\u0010\n\u001a\u00020\u000b2\u001e\u0010\f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/PlatformIncompatibilityDiagnosticRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", Argument.Delimiters.none, "mode", "Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;)V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PlatformIncompatibilityDiagnosticRenderer implements DiagnosticParameterRenderer<Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>> {
    public static final PlatformIncompatibilityDiagnosticRenderer TEXT = new PlatformIncompatibilityDiagnosticRenderer(new MultiplatformDiagnosticRenderingMode());
    private final MultiplatformDiagnosticRenderingMode mode;

    public PlatformIncompatibilityDiagnosticRenderer(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        multiplatformDiagnosticRenderingMode.getClass();
        this.mode = multiplatformDiagnosticRenderingMode;
    }

    /* JADX INFO: renamed from: render, reason: avoid collision after fix types in other method */
    public String render2(Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>> obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        if (obj.isEmpty()) {
            return Argument.Delimiters.none;
        }
        StringBuilder sb = new StringBuilder();
        this.mode.newLine(sb);
        PlatformIncompatibilityDiagnosticRendererKt.renderIncompatibilityInformation(sb, obj, Argument.Delimiters.none, renderingContext, this.mode);
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public /* bridge */ /* synthetic */ String render(Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>> map, RenderingContext renderingContext) {
        return render2((Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>) map, renderingContext);
    }
}
