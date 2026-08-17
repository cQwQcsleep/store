package io.github.rosemoe.sora.widget.rendering;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000f\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/rosemoe/sora/widget/rendering/MeasureCacheItem;", "", "line", "", "widths", "Lio/github/rosemoe/sora/widget/rendering/TextAdvancesCache;", "updateTimestamp", "", "<init>", "(ILio/github/rosemoe/sora/widget/rendering/TextAdvancesCache;J)V", "getLine", "()I", "setLine", "(I)V", "getWidths", "()Lio/github/rosemoe/sora/widget/rendering/TextAdvancesCache;", "setWidths", "(Lio/github/rosemoe/sora/widget/rendering/TextAdvancesCache;)V", "getUpdateTimestamp", "()J", "setUpdateTimestamp", "(J)V", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class MeasureCacheItem {
    private int line;
    private long updateTimestamp;
    private TextAdvancesCache widths;

    public MeasureCacheItem(int i, TextAdvancesCache textAdvancesCache, long j) {
        this.line = i;
        this.widths = textAdvancesCache;
        this.updateTimestamp = j;
    }

    public final int getLine() {
        return this.line;
    }

    public final long getUpdateTimestamp() {
        return this.updateTimestamp;
    }

    public final TextAdvancesCache getWidths() {
        return this.widths;
    }

    public final void setLine(int i) {
        this.line = i;
    }

    public final void setUpdateTimestamp(long j) {
        this.updateTimestamp = j;
    }

    public final void setWidths(TextAdvancesCache textAdvancesCache) {
        this.widths = textAdvancesCache;
    }
}
