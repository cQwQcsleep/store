package androidx.compose.foundation.lazy.staggeredgrid;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
public final class LazyStaggeredGridDslKt$itemsIndexed$4$1 implements Function1<Integer, StaggeredGridItemSpan> {
    final /* synthetic */ List<T> $items;
    final /* synthetic */ Function2<Integer, T, StaggeredGridItemSpan> $span;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridDslKt$itemsIndexed$4$1(Function2<? super Integer, ? super T, StaggeredGridItemSpan> function2, List<? extends T> list) {
        this.$span = function2;
        this.$items = list;
    }

    public final StaggeredGridItemSpan invoke(int i) {
        return (StaggeredGridItemSpan) this.$span.invoke(Integer.valueOf(i), this.$items.get(i));
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
