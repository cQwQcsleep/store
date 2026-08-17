package io.github.rosemoe.sora.lang.analysis;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lio/github/rosemoe/sora/lang/analysis/StyleUpdateRange;", "", "isInRange", "", "line", "", "lineIndexIterator", "Lkotlin/collections/IntIterator;", "maxLineIndex", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public interface StyleUpdateRange {
    boolean isInRange(int line);

    IntIterator lineIndexIterator(int maxLineIndex);
}
