package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
public final class LazyStaggeredGridDslKt$items$7$1 implements Function1<Integer, Object> {
    final /* synthetic */ T[] $items;
    final /* synthetic */ Function1<T, Object> $key;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridDslKt$items$7$1(Function1<? super T, ? extends Object> function1, T[] tArr) {
        this.$key = function1;
        this.$items = tArr;
    }

    public final Object invoke(int i) {
        return this.$key.invoke(this.$items[i]);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
