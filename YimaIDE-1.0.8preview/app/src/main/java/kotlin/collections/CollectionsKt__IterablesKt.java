package kotlin.collections;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\u0088\u0004b\u0002\b\u0006ø\u0001\u0000\u001a%\u0010\u0007\u001a\u0004\u0018\u00010\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0081\u0080\u0004b\u0002\b\n¢\u0006\u0002\u0010\t\u001a&\u0010\u000b\u001a\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001a\u00020\bH\u0081\u0080\u0004b\u0002\b\n\u001a&\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u000e\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001H\u0086\u0080\u0004\u001aD\u0010\u000f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u000e0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00110\u00100\u0001H\u0086\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "Lkotlin/internal/InlineOnly;", "collectionSizeOrNull", "", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "Lkotlin/PublishedApi;", "collectionSizeOrDefault", "default", "flatten", "", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/CollectionsKt")
public class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlin.collections.CollectionsKt__IterablesKt$Iterable$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0010\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0082\u0004¨\u0006\u0004"}, d2 = {"kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", "", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = 176)
    public static final class AnonymousClass1<T> implements Iterable<T>, KMappedMarker {
        final /* synthetic */ Function0<Iterator<T>> $iterator;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function0<? extends Iterator<? extends T>> function0) {
            this.$iterator = function0;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.$iterator.invoke();
        }
    }

    private static final <T> Iterable<T> Iterable(Function0<? extends Iterator<? extends T>> function0) {
        function0.getClass();
        return new AnonymousClass1(function0);
    }

    public static <T> int collectionSizeOrDefault(Iterable<? extends T> iterable, int i) {
        iterable.getClass();
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    public static final <T> Integer collectionSizeOrNull(Iterable<? extends T> iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return Integer.valueOf(((Collection) iterable).size());
        }
        return null;
    }

    public static <T> List<T> flatten(Iterable<? extends Iterable<? extends T>> iterable) {
        iterable.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Iterable<? extends T>> it2 = iterable.iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, it2.next());
        }
        return arrayList;
    }

    public static <T, R> Pair<List<T>, List<R>> unzip(Iterable<? extends Pair<? extends T, ? extends R>> iterable) {
        iterable.getClass();
        int iCollectionSizeOrDefault = collectionSizeOrDefault(iterable, 10);
        ArrayList arrayList = new ArrayList(iCollectionSizeOrDefault);
        ArrayList arrayList2 = new ArrayList(iCollectionSizeOrDefault);
        for (Pair<? extends T, ? extends R> pair : iterable) {
            arrayList.add(pair.getFirst());
            arrayList2.add(pair.getSecond());
        }
        return TuplesKt.to(arrayList, arrayList2);
    }
}
