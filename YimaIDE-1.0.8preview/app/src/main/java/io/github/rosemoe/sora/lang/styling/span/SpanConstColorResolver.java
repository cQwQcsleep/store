package io.github.rosemoe.sora.lang.styling.span;

import io.github.rosemoe.sora.lang.styling.Span;
import io.github.rosemoe.sora.lang.styling.color.ConstColor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/span/SpanConstColorResolver;", "Lio/github/rosemoe/sora/lang/styling/span/SpanColorResolver;", "foreground", "", "background", "<init>", "(II)V", "foregroundColor", "Lio/github/rosemoe/sora/lang/styling/color/ConstColor;", "backgroundColor", "getForegroundColor", "span", "Lio/github/rosemoe/sora/lang/styling/Span;", "getBackgroundColor", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SpanConstColorResolver implements SpanColorResolver {
    private final ConstColor backgroundColor;
    private final ConstColor foregroundColor;

    public SpanConstColorResolver(int i, int i2) {
        this.foregroundColor = i == 0 ? null : new ConstColor(i);
        this.backgroundColor = i2 != 0 ? new ConstColor(i2) : null;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanColorResolver
    public ConstColor getBackgroundColor(Span span) {
        span.getClass();
        return this.backgroundColor;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanColorResolver
    public ConstColor getForegroundColor(Span span) {
        span.getClass();
        return this.foregroundColor;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SpanConstColorResolver() {
        int i = 0;
        this(i, i, 3, null);
    }

    public /* synthetic */ SpanConstColorResolver(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }
}
