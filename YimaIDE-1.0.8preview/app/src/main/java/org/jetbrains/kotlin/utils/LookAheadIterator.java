package org.jetbrains.kotlin.utils;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0082\u0004J\u000f\u0010\n\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000bJ\u000b\u0010\f\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/utils/LookAheadIterator;", "T", "", "iterator", "<init>", "(Ljava/util/Iterator;)V", "currentLookAhead", "Ljava/lang/Object;", "hasNext", "", "next", "()Ljava/lang/Object;", "nextWithoutConsuming", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
final class LookAheadIterator<T> implements Iterator<T>, KMappedMarker {
    private T currentLookAhead;
    private final Iterator<T> iterator;

    /* JADX WARN: Multi-variable type inference failed */
    public LookAheadIterator(Iterator<? extends T> it) {
        it.getClass();
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.currentLookAhead != null || this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T t = this.currentLookAhead;
        if (t == null) {
            return this.iterator.next();
        }
        this.currentLookAhead = null;
        return t;
    }

    public final T nextWithoutConsuming() {
        T t = this.currentLookAhead;
        if (t != null) {
            return t;
        }
        T next = this.iterator.next();
        this.currentLookAhead = next;
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
