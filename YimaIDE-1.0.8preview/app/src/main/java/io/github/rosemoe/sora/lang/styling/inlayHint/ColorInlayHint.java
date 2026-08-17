package io.github.rosemoe.sora.lang.styling.inlayHint;

import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/inlayHint/ColorInlayHint;", "Lio/github/rosemoe/sora/lang/styling/inlayHint/InlayHint;", "line", "", "column", ColorInlayHint.TYPE_NAME, "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "<init>", "(IILio/github/rosemoe/sora/lang/styling/color/ResolvableColor;)V", "getColor", "()Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "Companion", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ColorInlayHint extends InlayHint {
    public static final String TYPE_NAME = "color";
    private final ResolvableColor color;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColorInlayHint(int i, int i2, ResolvableColor resolvableColor) {
        super(i, i2, TYPE_NAME, null, 8, null);
        resolvableColor.getClass();
        this.color = resolvableColor;
    }

    public final ResolvableColor getColor() {
        return this.color;
    }
}
