package io.github.rosemoe.sora.lang.styling.color;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/color/EditorColor;", "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "colorId", "", "<init>", "(I)V", "resolve", "colorScheme", "Lio/github/rosemoe/sora/widget/schemes/EditorColorScheme;", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class EditorColor implements ResolvableColor {
    private final int colorId;

    public EditorColor(int i) {
        this.colorId = i;
    }

    @Override // io.github.rosemoe.sora.lang.styling.color.ResolvableColor
    public int resolve(EditorColorScheme colorScheme) {
        colorScheme.getClass();
        return colorScheme.getColor(this.colorId);
    }
}
