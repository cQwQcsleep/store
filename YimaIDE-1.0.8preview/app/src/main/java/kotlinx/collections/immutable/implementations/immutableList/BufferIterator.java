package kotlinx.collections.immutable.implementations.immutableList;

import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000b\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\fJ\r\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\fR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000e"}, d2 = {"Lkotlinx/collections/immutable/implementations/immutableList/BufferIterator;", "T", "Lkotlinx/collections/immutable/implementations/immutableList/AbstractListIterator;", "buffer", HttpUrl.FRAGMENT_ENCODE_SET, "index", HttpUrl.FRAGMENT_ENCODE_SET, "size", "<init>", "([Ljava/lang/Object;II)V", "[Ljava/lang/Object;", "next", "()Ljava/lang/Object;", "previous", "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class BufferIterator<T> extends AbstractListIterator<T> {
    private final T[] buffer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferIterator(T[] tArr, int i, int i2) {
        super(i, i2);
        tArr.getClass();
        this.buffer = tArr;
    }

    @Override // kotlinx.collections.immutable.implementations.immutableList.AbstractListIterator, java.util.ListIterator, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        T[] tArr = this.buffer;
        int index = getIndex();
        setIndex(index + 1);
        return tArr[index];
    }

    @Override // java.util.ListIterator
    public T previous() {
        if (!hasPrevious()) {
            z0e.a();
            return null;
        }
        T[] tArr = this.buffer;
        setIndex(getIndex() - 1);
        return tArr[getIndex()];
    }
}
