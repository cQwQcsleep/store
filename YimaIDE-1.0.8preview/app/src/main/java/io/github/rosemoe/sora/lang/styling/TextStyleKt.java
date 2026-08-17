package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006¨\u0006\n"}, d2 = {"textStyle", "", "foreground", "", "background", "bold", "", "italic", "strikethrough", "noCompletion", "editor_release"}, k = 2, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class TextStyleKt {
    public static final long textStyle(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4) {
        return TextStyle.makeStyle(i, i2, z, z2, z3, z4);
    }

    public static /* synthetic */ long textStyle$default(int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            z2 = false;
        }
        if ((i3 & 16) != 0) {
            z3 = false;
        }
        if ((i3 & 32) != 0) {
            z4 = false;
        }
        return textStyle(i, i2, z, z2, z3, z4);
    }
}
