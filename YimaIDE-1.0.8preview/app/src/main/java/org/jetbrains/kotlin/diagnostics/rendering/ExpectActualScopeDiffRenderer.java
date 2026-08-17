package org.jetbrains.kotlin.diagnostics.rendering;

import java.text.MessageFormat;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.Named;
import org.jetbrains.kotlin.resolve.multiplatform.K1ExpectActualMemberDiff;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/ExpectActualScopeDiffRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff;", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "<init>", "()V", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpectActualScopeDiffRenderer implements DiagnosticParameterRenderer<K1ExpectActualMemberDiff<? extends CallableMemberDescriptor, ? extends ClassDescriptor>> {
    public static final ExpectActualScopeDiffRenderer INSTANCE = new ExpectActualScopeDiffRenderer();

    private ExpectActualScopeDiffRenderer() {
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(K1ExpectActualMemberDiff<? extends CallableMemberDescriptor, ? extends ClassDescriptor> obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        String str = MessageFormat.format(obj.getKind().getRawMessage(), Renderers.DECLARATION_NAME_WITH_KIND.render((DeclarationDescriptor) obj.getActualMember(), renderingContext), Renderers.NAME.render((Named) obj.getExpectClass()));
        str.getClass();
        return str;
    }
}
