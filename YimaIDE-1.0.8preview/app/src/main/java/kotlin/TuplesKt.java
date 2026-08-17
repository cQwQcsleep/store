package kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a3\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u0002H\u0003H\u0086\u0084\u0004¢\u0006\u0002\u0010\u0005\u001a&\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\u0001H\u0086\u0080\u0004\u001a,\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\b0\tH\u0086\u0080\u0004¨\u0006\n"}, d2 = {"to", "Lkotlin/Pair;", "A", "B", "that", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "toList", Argument.Delimiters.none, "T", "Lkotlin/Triple;", "kotlin-stdlib"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TuplesKt {
    public static final <A, B> Pair<A, B> to(A a, B b) {
        return new Pair<>(a, b);
    }

    public static final <T> List<T> toList(Triple<? extends T, ? extends T, ? extends T> triple) {
        triple.getClass();
        return CollectionsKt.listOf(new Object[]{triple.getFirst(), triple.getSecond(), triple.getThird()});
    }

    public static final <T> List<T> toList(Pair<? extends T, ? extends T> pair) {
        pair.getClass();
        return CollectionsKt.listOf(new Object[]{pair.getFirst(), pair.getSecond()});
    }
}
