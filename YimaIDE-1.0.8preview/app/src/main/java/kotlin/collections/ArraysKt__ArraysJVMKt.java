package kotlin.collections;

import defpackage.qnd;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0086\u0088\u0004¢\u0006\u0002\u0010\u0003\u001a\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0087\u0088\u0004b\u0002\b\t\u001a'\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u000bH\u0086\u0088\u0004¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0081\u0080\u0004b\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u001aE\u0010\u0018\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0001H\u0081\u0080\u0004b\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017b\u0002\b\u001bb\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0019¢\u0006\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"orEmpty", "", "T", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/internal/InlineOnly;", "toTypedArray", "", "(Ljava/util/Collection;)[Ljava/lang/Object;", "arrayOfNulls", "reference", "size", "", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRangeToIndexCheck", "", "toIndex", "Lkotlin/SinceKotlin;", "version", "1.3", "contentDeepHashCodeImpl", "contentDeepHashCode", "([Ljava/lang/Object;)I", "Lkotlin/PublishedApi;", "Lkotlin/jvm/JvmName;", "name", "kotlin-stdlib"}, k = 5, mv = {2, 4, 0}, xi = EditorColorScheme.TEXT_INLAY_HINT_BACKGROUND, xs = "kotlin/collections/ArraysKt")
public class ArraysKt__ArraysJVMKt {
    public static final <T> T[] arrayOfNulls(T[] tArr, int i) {
        tArr.getClass();
        Object objNewInstance = Array.newInstance(tArr.getClass().getComponentType(), i);
        objNewInstance.getClass();
        return (T[]) ((Object[]) objNewInstance);
    }

    public static <T> int contentDeepHashCode(T[] tArr) {
        return Arrays.deepHashCode(tArr);
    }

    public static final void copyOfRangeToIndexCheck(int i, int i2) {
        if (i <= i2) {
            return;
        }
        qnd.a("toIndex (", i, ") is greater than size (", i2, ").");
    }

    public static final /* synthetic */ <T> T[] orEmpty(T[] tArr) {
        if (tArr != null) {
            return tArr;
        }
        Intrinsics.reifiedOperationMarker(0, "T");
        return (T[]) new Object[0];
    }

    private static final String toString(byte[] bArr, Charset charset) {
        bArr.getClass();
        charset.getClass();
        return new String(bArr, charset);
    }

    public static final /* synthetic */ <T> T[] toTypedArray(Collection<? extends T> collection) {
        collection.getClass();
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) collection.toArray(new Object[0]);
    }
}
