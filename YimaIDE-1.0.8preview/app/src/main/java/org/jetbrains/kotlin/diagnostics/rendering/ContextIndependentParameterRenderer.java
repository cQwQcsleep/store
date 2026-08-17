package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\bJ\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "O", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "render", Argument.Delimiters.none, "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;)Ljava/lang/String;", "(Ljava/lang/Object;)Ljava/lang/String;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ContextIndependentParameterRenderer<O> extends DiagnosticParameterRenderer<O> {
    String render(O obj);

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    default String render(O obj, RenderingContext renderingContext) {
        renderingContext.getClass();
        return render(obj);
    }
}
