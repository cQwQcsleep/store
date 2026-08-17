package io.github.rosemoe.sora.graphics.inlayHint;

import android.graphics.Canvas;
import io.github.rosemoe.sora.graphics.InlayHintRenderParams;
import io.github.rosemoe.sora.graphics.Paint;
import io.github.rosemoe.sora.lang.styling.inlayHint.InlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ6\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tJ \u0010\u0017\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J8\u0010\u0018\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\tH&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0019"}, d2 = {"Lio/github/rosemoe/sora/graphics/inlayHint/InlayHintRenderer;", "", "<init>", "()V", "typeName", "", "getTypeName", "()Ljava/lang/String;", "measure", "", "inlayHint", "Lio/github/rosemoe/sora/lang/styling/inlayHint/InlayHint;", "paint", "Lio/github/rosemoe/sora/graphics/Paint;", "params", "Lio/github/rosemoe/sora/graphics/InlayHintRenderParams;", "render", "", "canvas", "Landroid/graphics/Canvas;", "colorScheme", "Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "measuredWidth", "onMeasure", "onRender", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public abstract class InlayHintRenderer {
    public abstract String getTypeName();

    public final float measure(InlayHint inlayHint, Paint paint, InlayHintRenderParams params) {
        inlayHint.getClass();
        paint.getClass();
        params.getClass();
        return onMeasure(inlayHint, paint, params);
    }

    public abstract float onMeasure(InlayHint inlayHint, Paint paint, InlayHintRenderParams params);

    public abstract void onRender(InlayHint inlayHint, Canvas canvas, Paint paint, InlayHintRenderParams params, EditorColorScheme colorScheme, float measuredWidth);

    public final void render(InlayHint inlayHint, Canvas canvas, Paint paint, InlayHintRenderParams params, EditorColorScheme colorScheme, float measuredWidth) {
        inlayHint.getClass();
        canvas.getClass();
        paint.getClass();
        params.getClass();
        colorScheme.getClass();
        onRender(inlayHint, canvas, paint, params, colorScheme, measuredWidth);
    }
}
