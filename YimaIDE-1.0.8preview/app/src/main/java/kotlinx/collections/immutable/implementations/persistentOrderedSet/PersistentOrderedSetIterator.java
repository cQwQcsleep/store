package kotlinx.collections.immutable.implementations.persistentOrderedSet;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0013H\u0096\u0002J\u000e\u0010\u0014\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lkotlinx/collections/immutable/implementations/persistentOrderedSet/PersistentOrderedSetIterator;", "E", HttpUrl.FRAGMENT_ENCODE_SET, "nextElement", HttpUrl.FRAGMENT_ENCODE_SET, "map", HttpUrl.FRAGMENT_ENCODE_SET, "Lkotlinx/collections/immutable/implementations/persistentOrderedSet/Links;", "<init>", "(Ljava/lang/Object;Ljava/util/Map;)V", "getMap$kotlinx_collections_immutable", "()Ljava/util/Map;", "index", HttpUrl.FRAGMENT_ENCODE_SET, "getIndex$kotlinx_collections_immutable", "()I", "setIndex$kotlinx_collections_immutable", "(I)V", "hasNext", HttpUrl.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "checkHasNext", HttpUrl.FRAGMENT_ENCODE_SET, "kotlinx-collections-immutable"}, k = 1, mv = {2, 1, 0}, xi = 48)
public class PersistentOrderedSetIterator<E> implements Iterator<E>, KMappedMarker {
    private int index;
    private final Map<E, Links> map;
    private Object nextElement;

    public PersistentOrderedSetIterator(Object obj, Map<E, Links> map) {
        map.getClass();
        this.nextElement = obj;
        this.map = map;
    }

    private final void checkHasNext() {
        if (hasNext()) {
            return;
        }
        z0e.a();
    }

    /* JADX INFO: renamed from: getIndex$kotlinx_collections_immutable, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    public final Map<E, Links> getMap$kotlinx_collections_immutable() {
        return this.map;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.map.size();
    }

    @Override // java.util.Iterator
    public E next() {
        checkHasNext();
        E e = (E) this.nextElement;
        this.index++;
        Links links = this.map.get(e);
        if (links != null) {
            this.nextElement = links.getNext();
            return e;
        }
        throw new ConcurrentModificationException("Hash code of an element (" + e + ") has changed after it was added to the persistent set.");
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setIndex$kotlinx_collections_immutable(int i) {
        this.index = i;
    }
}
