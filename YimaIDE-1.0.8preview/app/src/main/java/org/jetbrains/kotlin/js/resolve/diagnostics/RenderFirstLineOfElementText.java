package org.jetbrains.kotlin.js.resolve.diagnostics;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/js/resolve/diagnostics/RenderFirstLineOfElementText;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "Lcom/intellij/psi/PsiElement;", "<init>", "()V", "render", "", "obj", "renderingContext", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "org.jetbrains.kotlin:js.frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class RenderFirstLineOfElementText implements DiagnosticParameterRenderer<PsiElement> {
    public static final RenderFirstLineOfElementText INSTANCE = new RenderFirstLineOfElementText();

    private RenderFirstLineOfElementText() {
    }

    public String render(PsiElement obj, RenderingContext renderingContext) {
        obj.getClass();
        renderingContext.getClass();
        String text = obj.getText();
        text.getClass();
        int iIndexOf$default = StringsKt.indexOf$default(text, '\n', 0, false, 6, (Object) null);
        return iIndexOf$default == -1 ? text : text.substring(0, iIndexOf$default).concat("...");
    }
}
