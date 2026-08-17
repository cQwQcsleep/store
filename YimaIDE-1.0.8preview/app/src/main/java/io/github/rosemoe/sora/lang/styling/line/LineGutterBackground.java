package io.github.rosemoe.sora.lang.styling.line;

import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.lang.styling.inlayHint.ColorInlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/line/LineGutterBackground;", "Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "line", "", ColorInlayHint.TYPE_NAME, "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "<init>", "(ILio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "getLine", "()I", "setLine", "(I)V", "getColor", "()Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "setColor", "(Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class LineGutterBackground extends LineAnchorStyle {
    private ResolvableColor color;
    private int line;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineGutterBackground(int i, ResolvableColor resolvableColor) {
        super(i);
        resolvableColor.getClass();
        this.line = i;
        this.color = resolvableColor;
    }

    public final ResolvableColor getColor() {
        return this.color;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public int getLine() {
        return this.line;
    }

    public final void setColor(ResolvableColor resolvableColor) {
        resolvableColor.getClass();
        this.color = resolvableColor;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public void setLine(int i) {
        this.line = i;
    }
}
