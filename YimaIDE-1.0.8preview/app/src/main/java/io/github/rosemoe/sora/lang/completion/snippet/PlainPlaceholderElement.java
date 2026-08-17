package io.github.rosemoe.sora.lang.completion.snippet;

import io.github.rosemoe.sora.lang.styling.inlayHint.TextInlayHint;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/snippet/PlainPlaceholderElement;", "Lio/github/rosemoe/sora/lang/completion/snippet/PlaceHolderElement;", TextInlayHint.TYPE_NAME, "", "<init>", "(Ljava/lang/String;)V", "getText", "()Ljava/lang/String;", "setText", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final /* data */ class PlainPlaceholderElement implements PlaceHolderElement {
    private String text;

    public PlainPlaceholderElement(String str) {
        str.getClass();
        this.text = str;
    }

    public static /* synthetic */ PlainPlaceholderElement copy$default(PlainPlaceholderElement plainPlaceholderElement, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = plainPlaceholderElement.text;
        }
        return plainPlaceholderElement.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    public final PlainPlaceholderElement copy(String text) {
        text.getClass();
        return new PlainPlaceholderElement(text);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlainPlaceholderElement) && Intrinsics.areEqual(this.text, ((PlainPlaceholderElement) other).text);
    }

    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        return this.text.hashCode();
    }

    public final void setText(String str) {
        str.getClass();
        this.text = str;
    }

    public String toString() {
        return "PlainPlaceholderElement(text=" + this.text + ")";
    }
}
