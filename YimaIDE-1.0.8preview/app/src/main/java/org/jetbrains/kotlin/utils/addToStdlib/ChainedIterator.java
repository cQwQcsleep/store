package org.jetbrains.kotlin.utils.addToStdlib;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0002J\n\u0010\u000b\u001a\u00020\fH\u0096\u0082\u0004J\u000f\u0010\r\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000eR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/utils/addToStdlib/ChainedIterator;", "T", HttpUrl.FRAGMENT_ENCODE_SET, "delegates", HttpUrl.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/Collection;)V", "metaIterator", "currentIterator", "promote", HttpUrl.FRAGMENT_ENCODE_SET, "hasNext", HttpUrl.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChainedIterator<T> implements Iterator<T>, KMappedMarker {
    private Iterator<? extends T> currentIterator;
    private Iterator<? extends Iterator<? extends T>> metaIterator;

    public ChainedIterator(Collection<? extends Iterator<? extends T>> collection) {
        collection.getClass();
        this.metaIterator = collection.iterator();
    }

    private final void promote() {
        Iterator<? extends T> it = this.currentIterator;
        if (it == null || !it.hasNext()) {
            while (this.metaIterator.hasNext()) {
                Iterator<? extends T> next = this.metaIterator.next();
                this.currentIterator = next;
                next.getClass();
                if (next.hasNext()) {
                    return;
                }
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        promote();
        Iterator<? extends T> it = this.currentIterator;
        return it != null && it.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T next;
        promote();
        Iterator<? extends T> it = this.currentIterator;
        if (it != null && (next = it.next()) != null) {
            return next;
        }
        z0e.a();
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
