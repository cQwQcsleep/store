package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.ExpectActualScopeDiffsRenderer;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualMemberDiff;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ*\u0010\n\u001a\u00020\u000b2\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/ExpectActualScopeDiffsRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff;", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "mode", "Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;", "<init>", "(Lorg/jetbrains/kotlin/diagnostics/rendering/MultiplatformDiagnosticRenderingMode;)V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpectActualScopeDiffsRenderer implements DiagnosticParameterRenderer<Set<? extends K1ExpectActualMemberDiff<? extends CallableMemberDescriptor, ? extends ClassDescriptor>>> {
    public static final ExpectActualScopeDiffsRenderer TEXT = new ExpectActualScopeDiffsRenderer(new MultiplatformDiagnosticRenderingMode());
    private final MultiplatformDiagnosticRenderingMode mode;

    public ExpectActualScopeDiffsRenderer(MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode) {
        multiplatformDiagnosticRenderingMode.getClass();
        this.mode = multiplatformDiagnosticRenderingMode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit render$lambda$0$0$0(StringBuilder sb, K1ExpectActualMemberDiff k1ExpectActualMemberDiff, RenderingContext renderingContext) {
        sb.append('\n');
        sb.append(ExpectActualScopeDiffRenderer.INSTANCE.render((K1ExpectActualMemberDiff<? extends CallableMemberDescriptor, ? extends ClassDescriptor>) k1ExpectActualMemberDiff, renderingContext));
        sb.append('\n');
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(Set<? extends K1ExpectActualMemberDiff<? extends CallableMemberDescriptor, ? extends ClassDescriptor>> obj, final RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        if (obj.isEmpty()) {
            k2d.a("Check failed.");
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        MultiplatformDiagnosticRenderingMode multiplatformDiagnosticRenderingMode = this.mode;
        List<K1ExpectActualMemberDiff> list = CollectionsKt.toList(obj);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (final K1ExpectActualMemberDiff k1ExpectActualMemberDiff : list) {
            arrayList.add(new Function0() { // from class: ne4
                public final Object invoke() {
                    return ExpectActualScopeDiffsRenderer.render$lambda$0$0$0(sb, k1ExpectActualMemberDiff, renderingContext);
                }
            });
        }
        multiplatformDiagnosticRenderingMode.renderList(sb, arrayList);
        return sb.toString();
    }
}
