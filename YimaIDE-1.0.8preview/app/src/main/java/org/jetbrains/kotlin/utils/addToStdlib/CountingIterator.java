package org.jetbrains.kotlin.utils.addToStdlib;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\r\u001a\u00020\u000eH\u0096\u0082\u0004J\u000f\u0010\u000f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/utils/addToStdlib/CountingIterator;", "T", "", "delegate", "<init>", "(Ljava/util/Iterator;)V", "getDelegate", "()Ljava/util/Iterator;", "value", "", "numberOfElementsSeen", "getNumberOfElementsSeen", "()I", "hasNext", "", "next", "()Ljava/lang/Object;", "org.jetbrains.kotlin:util.runtime"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class CountingIterator<T> implements Iterator<T>, KMappedMarker {
    private final Iterator<T> delegate;
    private int numberOfElementsSeen;

    /* JADX WARN: Multi-variable type inference failed */
    public CountingIterator(Iterator<? extends T> it) {
        it.getClass();
        this.delegate = it;
    }

    public final Iterator<T> getDelegate() {
        return this.delegate;
    }

    public final int getNumberOfElementsSeen() {
        return this.numberOfElementsSeen;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.delegate.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        T next = this.delegate.next();
        this.numberOfElementsSeen++;
        return next;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
