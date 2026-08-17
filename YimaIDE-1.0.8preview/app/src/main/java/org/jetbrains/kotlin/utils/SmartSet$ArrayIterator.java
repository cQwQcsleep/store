package org.jetbrains.kotlin.utils;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.markers.KMutableIterator;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\t\u001a\u00020\nH\u0096\u0082\u0004J\u000f\u0010\u000b\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/utils/SmartSet$ArrayIterator;", "T", HttpUrl.FRAGMENT_ENCODE_SET, "array", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "([Ljava/lang/Object;)V", "arrayIterator", HttpUrl.FRAGMENT_ENCODE_SET, "hasNext", HttpUrl.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "remove", HttpUrl.FRAGMENT_ENCODE_SET, "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SmartSet$ArrayIterator<T> implements Iterator<T>, KMutableIterator {
    private final Iterator<T> arrayIterator;

    public SmartSet$ArrayIterator(T[] tArr) {
        tArr.getClass();
        this.arrayIterator = ArrayIteratorKt.iterator(tArr);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.arrayIterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        return this.arrayIterator.next();
    }

    @Override // java.util.Iterator
    public Void remove() {
        throw new UnsupportedOperationException();
    }
}
