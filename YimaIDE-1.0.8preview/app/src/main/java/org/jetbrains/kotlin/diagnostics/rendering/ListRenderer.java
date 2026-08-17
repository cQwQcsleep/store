package org.jetbrains.kotlin.diagnostics.rendering;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.rendering.ListRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/ListRenderer;", "T", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", Argument.Delimiters.none, "elementRenderer", "elemProcessor", "Lkotlin/Function1;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;Lkotlin/jvm/functions/Function1;)V", "render", "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ListRenderer<T> implements DiagnosticParameterRenderer<List<? extends T>> {
    private final Function1<String, String> elemProcessor;
    private final DiagnosticParameterRenderer<T> elementRenderer;

    /* JADX WARN: Multi-variable type inference failed */
    public ListRenderer(DiagnosticParameterRenderer<? super T> diagnosticParameterRenderer, Function1<? super String, String> function1) {
        diagnosticParameterRenderer.getClass();
        function1.getClass();
        this.elementRenderer = diagnosticParameterRenderer;
        this.elemProcessor = function1;
    }

    public static String a(String str) {
        str.getClass();
        return str;
    }

    public static CharSequence b(ListRenderer listRenderer, RenderingContext renderingContext, Object obj) {
        return (CharSequence) listRenderer.elemProcessor.invoke(listRenderer.elementRenderer.render(obj, renderingContext));
    }

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
    public String render(List<? extends T> obj, final RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        return CollectionsKt.joinToString$default(obj, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: oc9
            public final Object invoke(Object obj2) {
                return ListRenderer.b(this.b, renderingContext, obj2);
            }
        }, 31, (Object) null);
    }

    public /* synthetic */ ListRenderer(DiagnosticParameterRenderer diagnosticParameterRenderer, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(diagnosticParameterRenderer, (i & 2) != 0 ? new Function1() { // from class: nc9
            public final Object invoke(Object obj) {
                return ListRenderer.a((String) obj);
            }
        } : function1);
    }
}
