package io.github.rosemoe.sora.lang.styling.span;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/span/SpanClickableUrl;", "Lio/github/rosemoe/sora/lang/styling/span/SpanInteractionInfo;", "link", "", "<init>", "(Ljava/lang/String;)V", "getLink", "()Ljava/lang/String;", "isClickable", "", "isLongClickable", "isDoubleClickable", "getData", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class SpanClickableUrl implements SpanInteractionInfo {
    private final String link;

    public SpanClickableUrl(String str) {
        str.getClass();
        this.link = str;
    }

    public final String getLink() {
        return this.link;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanInteractionInfo
    public boolean isClickable() {
        return false;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanInteractionInfo
    public boolean isDoubleClickable() {
        return true;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanInteractionInfo
    public boolean isLongClickable() {
        return false;
    }

    @Override // io.github.rosemoe.sora.lang.styling.span.SpanInteractionInfo
    /* JADX INFO: renamed from: getData, reason: from getter */
    public String getLink() {
        return this.link;
    }
}
