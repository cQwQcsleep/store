package io.github.rosemoe.sora.lang.styling.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J.\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0003H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/span/SpanExternalRenderer;", "Lio/github/rosemoe/sora/lang/styling/span/SpanExt;", "requirePreDraw", "", "requirePostDraw", "draw", "", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "colorScheme", "Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "preOrPost", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface SpanExternalRenderer extends SpanExt {
    void draw(Canvas canvas, Paint paint, EditorColorScheme colorScheme, boolean preOrPost);

    boolean requirePostDraw();

    boolean requirePreDraw();
}
