package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.lang.styling.span.internal.NoExtSpanImpl;
import io.github.rosemoe.sora.lang.styling.span.internal.SpanImpl;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SpanFactory {
    private SpanFactory() {
    }

    public static Span obtain(int i, long j) {
        return SpanImpl.obtain(i, j);
    }

    public static Span obtainNoExt(int i, long j) {
        return NoExtSpanImpl.obtain(i, j);
    }

    public static void recycleAll(Collection<Span> collection) {
        Iterator<Span> it2 = collection.iterator();
        while (it2.hasNext() && it2.next().recycle()) {
        }
    }
}
