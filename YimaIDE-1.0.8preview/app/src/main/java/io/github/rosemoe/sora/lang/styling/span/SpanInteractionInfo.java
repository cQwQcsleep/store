package io.github.rosemoe.sora.lang.styling.span;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/span/SpanInteractionInfo;", "Lio/github/rosemoe/sora/lang/styling/span/SpanExt;", "isClickable", "", "isLongClickable", "isDoubleClickable", "getData", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface SpanInteractionInfo extends SpanExt {
    Object getData();

    boolean isClickable();

    boolean isDoubleClickable();

    boolean isLongClickable();
}
