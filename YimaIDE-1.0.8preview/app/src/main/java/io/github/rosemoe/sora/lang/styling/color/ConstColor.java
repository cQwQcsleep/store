package io.github.rosemoe.sora.lang.styling.color;

import android.graphics.Color;
import io.github.rosemoe.sora.lang.styling.inlayHint.ColorInlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/color/ConstColor;", "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", ColorInlayHint.TYPE_NAME, "", "<init>", "(I)V", "", "(Ljava/lang/String;)V", "resolve", "colorScheme", "Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ConstColor implements ResolvableColor {
    private final int color;

    public ConstColor(String str) {
        str.getClass();
        this.color = Color.parseColor(str);
    }

    @Override // io.github.rosemoe.sora.lang.styling.color.ResolvableColor
    public int resolve(EditorColorScheme colorScheme) {
        colorScheme.getClass();
        return this.color;
    }

    public ConstColor(int i) {
        this.color = i;
    }
}
