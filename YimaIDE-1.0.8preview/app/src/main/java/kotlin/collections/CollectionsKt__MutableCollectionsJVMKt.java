package kotlin.collections;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001ar\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0005j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0006H\u0087\u0088\u0004b6\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u001c\b\n\u0012\u0018\b\u000bB\u0014\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0006\b\u000e\u0012\u0002\b\f\u0012\n\b\u000f\u0012\u0006\b\n0\u00108\u0011b\u0002\b\u0012\u001as\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0014H\u0087\u0088\u0004b6\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0016\u0012\u001c\b\n\u0012\u0018\b\u000bB\u0014\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0017\u0012\u0006\b\u000e\u0012\u0002\b\f\u0012\n\b\u000f\u0012\u0006\b\n0\u00108\u0011b\u0002\b\u0012ø\u0001\u0000\u001a$\u0010\u0000\u001a\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0018*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0080\u0004\u001a6\u0010\u0019\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0005j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0006H\u0086\u0080\u0004\u001a9\u0010\u001a\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001b\u001a\u0002H\u0002H\u0087\u0088\u0004b\u0002\b\u0012b\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f¢\u0006\u0002\u0010\u001c\u001a,\u0010 \u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\u0088\u0004b\u0002\b\u0012b\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u001a4\u0010 \u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010!\u001a\u00020\"H\u0087\u0088\u0004b\u0002\b\u0012b\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"sort", "", "T", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "Lkotlin/Deprecated;", "message", "Use sortWith(comparator) instead.", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "this.sortWith(comparator)", "imports", "level", "Lkotlin/DeprecationLevel;", "ERROR", "Lkotlin/internal/InlineOnly;", "comparison", "Lkotlin/Function2;", "", "Use sortWith(Comparator(comparison)) instead.", "this.sortWith(Comparator(comparison))", "", "sortWith", "fill", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "Lkotlin/SinceKotlin;", "version", "1.2", "shuffle", "random", "Ljava/util/Random;", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/CollectionsKt")
public class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    private static final <T> void fill(List<T> list, T t) {
        list.getClass();
        Collections.fill(list, t);
    }

    private static final <T> void shuffle(List<T> list, Random random) {
        list.getClass();
        random.getClass();
        Collections.shuffle(list, random);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    private static final <T> void sort(List<T> list, Comparator<? super T> comparator) {
        list.getClass();
        comparator.getClass();
        throw new NotImplementedError(null, 1, null);
    }

    public static <T> void sortWith(List<T> list, Comparator<? super T> comparator) {
        list.getClass();
        comparator.getClass();
        if (list.size() > 1) {
            Collections.sort(list, comparator);
        }
    }

    private static final <T> void shuffle(List<T> list) {
        list.getClass();
        Collections.shuffle(list);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    private static final <T> void sort(List<T> list, Function2<? super T, ? super T, Integer> function2) {
        list.getClass();
        function2.getClass();
        throw new NotImplementedError(null, 1, null);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.getClass();
        if (list.size() > 1) {
            Collections.sort(list);
        }
    }
}
