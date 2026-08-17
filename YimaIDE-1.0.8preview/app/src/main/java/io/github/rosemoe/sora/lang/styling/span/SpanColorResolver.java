package io.github.rosemoe.sora.lang.styling.span;

import io.github.rosemoe.sora.lang.styling.Span;
import io.github.rosemoe.sora.lang.styling.color.ResolvableColor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/span/SpanColorResolver;", "Lio/github/rosemoe/sora/lang/styling/span/SpanExt;", "getForegroundColor", "Lio/github/rosemoe/sora/lang/styling/color/ResolvableColor;", "span", "Lio/github/rosemoe/sora/lang/styling/Span;", "getBackgroundColor", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface SpanColorResolver extends SpanExt {
    ResolvableColor getBackgroundColor(Span span);

    ResolvableColor getForegroundColor(Span span);
}
