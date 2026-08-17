package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001122\u0012.\u0012,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0004\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u00050\u00030\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJB\u0010\f\u001a\u00020\r20\u0010\u000e\u001a,\u0012(\u0012&\u0012\u0004\u0012\u00020\u0004\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u00050\u00030\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/IncompatibleExpectedActualClassScopesRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualCompatibility$Incompatible;", Argument.Delimiters.none, "mode", "Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;)V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncompatibleExpectedActualClassScopesRenderer implements DiagnosticParameterRenderer<List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>>> {
    public static final IncompatibleExpectedActualClassScopesRenderer TEXT = new IncompatibleExpectedActualClassScopesRenderer(new MultiplatformDiagnosticRenderingMode());
    private final MultiplatformDiagnosticRenderingMode mode;

    public IncompatibleExpectedActualClassScopesRenderer(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        multiplatformDiagnosticRenderingMode.getClass();
        this.mode = multiplatformDiagnosticRenderingMode;
    }

    /* JADX INFO: renamed from: render, reason: avoid collision after fix types in other method */
    public String render2(List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>> obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        if (obj.isEmpty()) {
            return Argument.Delimiters.none;
        }
        StringBuilder sb = new StringBuilder();
        this.mode.newLine(sb);
        PlatformIncompatibilityDiagnosticRendererKt.renderIncompatibleClassScopes(sb, obj, Argument.Delimiters.none, renderingContext, this.mode);
        return sb.toString();
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public /* bridge */ /* synthetic */ String render(List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<? extends MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>> list, RenderingContext renderingContext) {
        return render2((List<? extends Pair<? extends MemberDescriptor, ? extends Map<K1ExpectActualCompatibility.Incompatible<MemberDescriptor>, ? extends Collection<? extends MemberDescriptor>>>>) list, renderingContext);
    }
}
