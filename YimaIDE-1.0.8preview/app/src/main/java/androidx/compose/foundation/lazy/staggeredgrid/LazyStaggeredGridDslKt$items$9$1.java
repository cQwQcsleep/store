package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
public final class LazyStaggeredGridDslKt$items$9$1 implements Function1<Integer, StaggeredGridItemSpan> {
    final /* synthetic */ T[] $items;
    final /* synthetic */ Function1<T, StaggeredGridItemSpan> $span;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridDslKt$items$9$1(Function1<? super T, StaggeredGridItemSpan> function1, T[] tArr) {
        this.$span = function1;
        this.$items = tArr;
    }

    public final StaggeredGridItemSpan invoke(int i) {
        return (StaggeredGridItemSpan) this.$span.invoke(this.$items[i]);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
