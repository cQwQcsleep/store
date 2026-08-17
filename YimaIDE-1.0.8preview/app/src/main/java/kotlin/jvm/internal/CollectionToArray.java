package kotlin.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aY\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0087\u0080\u0004b\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\rb\u0016\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012b\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t¢\u0006\u0004\b\t\u0010\n\u001ak\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0087\u0080\u0004b\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\rb\u0016\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012b\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\t¢\u0006\u0004\b\t\u0010\u0016\u001a\u007f\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u0018\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00192\u001a\u0010\u001a\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u001b2(\u0010\u001c\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u001dH\u0082\u0088\u0004¢\u0006\u0002\u0010\u001e\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0003\"\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "Lkotlin/Deprecated;", "message", "This function will be made internal in a future release", "Lkotlin/DeprecatedSinceKotlin;", "warningSince", "1.9", "errorSince", "2.1", "Lkotlin/jvm/JvmName;", "name", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class CollectionToArray {
    private static final Object[] EMPTY = new Object[0];
    private static final int MAX_SIZE = 2147483645;

    @Deprecated(message = "This function will be made internal in a future release")
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.9")
    public static final Object[] toArray(Collection<?> collection, Object[] objArr) {
        Object[] objArrCopyOf;
        collection.getClass();
        objArr.getClass();
        int size = collection.size();
        int i = 0;
        if (size != 0) {
            Iterator<?> it2 = collection.iterator();
            if (it2.hasNext()) {
                if (size <= objArr.length) {
                    objArrCopyOf = objArr;
                } else {
                    Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
                    objNewInstance.getClass();
                    objArrCopyOf = (Object[]) objNewInstance;
                }
                while (true) {
                    int i2 = i + 1;
                    objArrCopyOf[i] = it2.next();
                    if (i2 >= objArrCopyOf.length) {
                        if (!it2.hasNext()) {
                            return objArrCopyOf;
                        }
                        int i3 = ((i2 * 3) + 1) >>> 1;
                        if (i3 <= i2) {
                            i3 = MAX_SIZE;
                            if (i2 >= MAX_SIZE) {
                                throw new OutOfMemoryError();
                            }
                        }
                        objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                    } else if (!it2.hasNext()) {
                        if (objArrCopyOf != objArr) {
                            return Arrays.copyOf(objArrCopyOf, i2);
                        }
                        objArr[i2] = null;
                        return objArr;
                    }
                    i = i2;
                }
            } else if (objArr.length > 0) {
                objArr[0] = null;
            }
        } else if (objArr.length > 0) {
            objArr[0] = null;
            return objArr;
        }
        return objArr;
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    private static final Object[] toArrayImpl(Collection<?> collection, Function0<Object[]> function0, Function1<? super Integer, Object[]> function1, Function2<? super Object[], ? super Integer, Object[]> function2) {
        int size = collection.size();
        if (size == 0) {
            return function0.invoke();
        }
        Iterator<?> it2 = collection.iterator();
        if (!it2.hasNext()) {
            return function0.invoke();
        }
        Object[] objArrInvoke = function1.invoke(Integer.valueOf(size));
        int i = 0;
        ?? CopyOf = objArrInvoke;
        while (true) {
            int i2 = i + 1;
            CopyOf[i] = it2.next();
            if (i2 >= CopyOf.length) {
                if (!it2.hasNext()) {
                    return CopyOf;
                }
                int i3 = ((i2 * 3) + 1) >>> 1;
                if (i3 <= i2) {
                    i3 = MAX_SIZE;
                    if (i2 >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                }
                CopyOf = Arrays.copyOf((Object[]) CopyOf, i3);
            } else if (!it2.hasNext()) {
                return function2.invoke(CopyOf, Integer.valueOf(i2));
            }
            i = i2;
            CopyOf = CopyOf;
        }
    }

    @Deprecated(message = "This function will be made internal in a future release")
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.9")
    public static final Object[] toArray(Collection<?> collection) {
        collection.getClass();
        int size = collection.size();
        if (size == 0) {
            return EMPTY;
        }
        Iterator<?> it2 = collection.iterator();
        if (!it2.hasNext()) {
            return EMPTY;
        }
        Object[] objArrCopyOf = new Object[size];
        int i = 0;
        while (true) {
            int i2 = i + 1;
            objArrCopyOf[i] = it2.next();
            if (i2 >= objArrCopyOf.length) {
                if (!it2.hasNext()) {
                    return objArrCopyOf;
                }
                int i3 = ((i2 * 3) + 1) >>> 1;
                if (i3 <= i2) {
                    i3 = MAX_SIZE;
                    if (i2 >= MAX_SIZE) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
            } else if (!it2.hasNext()) {
                return Arrays.copyOf(objArrCopyOf, i2);
            }
            i = i2;
        }
    }
}
