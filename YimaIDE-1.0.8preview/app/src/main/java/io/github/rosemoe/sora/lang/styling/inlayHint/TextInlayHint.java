package io.github.rosemoe.sora.lang.styling.inlayHint;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/inlayHint/TextInlayHint;", "Lio/github/rosemoe/sora/lang/styling/inlayHint/InlayHint;", "line", "", "column", TextInlayHint.TYPE_NAME, "", "<init>", "(IILjava/lang/String;)V", "getText", "()Ljava/lang/String;", "Companion", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class TextInlayHint extends InlayHint {
    public static final String TYPE_NAME = "text";
    private final String text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextInlayHint(int i, int i2, String str) {
        super(i, i2, TYPE_NAME, null, 8, null);
        str.getClass();
        this.text = str;
    }

    public final String getText() {
        return this.text;
    }
}
