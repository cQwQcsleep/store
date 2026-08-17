package io.github.rosemoe.sora.lang.styling.line;

import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.lang.styling.inlayHint.ColorInlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/line/LineBackground;", "Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "line", "", ColorInlayHint.TYPE_NAME, "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "<init>", "(ILio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "getLine", "()I", "setLine", "(I)V", "getColor", "()Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "setColor", "(Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class LineBackground extends LineAnchorStyle {
    private ResolvableColor color;
    private int line;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineBackground(int i, ResolvableColor resolvableColor) {
        super(i);
        resolvableColor.getClass();
        this.line = i;
        this.color = resolvableColor;
    }

    public static /* synthetic */ LineBackground copy$default(LineBackground lineBackground, int i, ResolvableColor resolvableColor, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = lineBackground.line;
        }
        if ((i2 & 2) != 0) {
            resolvableColor = lineBackground.color;
        }
        return lineBackground.copy(i, resolvableColor);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ResolvableColor getColor() {
        return this.color;
    }

    public final LineBackground copy(int line, ResolvableColor color) {
        color.getClass();
        return new LineBackground(line, color);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineBackground)) {
            return false;
        }
        LineBackground lineBackground = (LineBackground) other;
        return this.line == lineBackground.line && Intrinsics.areEqual(this.color, lineBackground.color);
    }

    public final ResolvableColor getColor() {
        return this.color;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public int getLine() {
        return this.line;
    }

    public int hashCode() {
        return (Integer.hashCode(this.line) * 31) + this.color.hashCode();
    }

    public final void setColor(ResolvableColor resolvableColor) {
        resolvableColor.getClass();
        this.color = resolvableColor;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public void setLine(int i) {
        this.line = i;
    }

    public String toString() {
        return "LineBackground(line=" + this.line + ", color=" + this.color + ")";
    }
}
