package org.jetbrains.kotlin.js.resolve.diagnostics;

import kotlin.Metadata;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH$J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallDataRenderer;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallData;", "<init>", "()V", "format", "", "data", "Lorg/jetbrains/kotlin/js/resolve/diagnostics/JsCallDataWithCode;", "render", "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class JsCallDataRenderer implements DiagnosticParameterRenderer<JsCallData> {
    public abstract String format(JsCallDataWithCode data);

    public String render(JsCallData obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        return obj instanceof JsCallDataWithCode ? format((JsCallDataWithCode) obj) : obj.getMessage();
    }
}
