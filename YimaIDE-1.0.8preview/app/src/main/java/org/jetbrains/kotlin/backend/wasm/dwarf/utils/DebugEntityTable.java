package org.jetbrains.kotlin.backend.wasm.dwarf.utils;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0096\u0082\u0004J\u0015\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00020\u0007H$¢\u0006\u0002\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/utils/DebugEntityTable;", "E", "I", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "size", "", "getSize", "()I", "set", "Lorg/jetbrains/kotlin/backend/wasm/dwarf/utils/IndexedSet;", "add", "entity", "(Ljava/lang/Object;)Ljava/lang/Object;", "iterator", "", "computeId", "index", "(I)Ljava/lang/Object;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class DebugEntityTable<E, I> implements Iterable<E>, KMappedMarker {
    private final IndexedSet<E> set = new IndexedSet<>();

    public final I add(E entity) {
        return computeId(this.set.add(entity));
    }

    public abstract I computeId(int index);

    public final int getSize() {
        return this.set.getSize();
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return this.set.iterator();
    }
}
