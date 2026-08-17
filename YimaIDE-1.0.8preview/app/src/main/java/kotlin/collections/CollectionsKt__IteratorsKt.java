package kotlin.collections;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0087\u008a\u0004b\u0002\b\u0003\u001a&\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0086\u0080\u0004\u001a1\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00070\tH\u0086\u0088\u0004ø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"iterator", "", "T", "Lkotlin/internal/InlineOnly;", "withIndex", "Lkotlin/collections/IndexedValue;", "forEach", "", "operation", "Lkotlin/Function1;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__IteratorsKt extends CollectionsKt__IteratorsJVMKt {
    public static final <T> void forEach(Iterator<? extends T> it2, Function1<? super T, Unit> function1) {
        it2.getClass();
        function1.getClass();
        while (it2.hasNext()) {
            function1.invoke(it2.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Iterator<T> iterator(Iterator<? extends T> it2) {
        it2.getClass();
        return it2;
    }

    public static final <T> Iterator<IndexedValue<T>> withIndex(Iterator<? extends T> it2) {
        it2.getClass();
        return new IndexingIterator(it2);
    }
}
