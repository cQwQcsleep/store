package org.jetbrains.kotlin.backend.wasm.dwarf.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010(\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0096\u0082\u0004R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/dwarf/utils/IndexedSet;", "T", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "pool", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "size", "getSize", "()I", "add", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "(Ljava/lang/Object;)I", "iterator", "", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IndexedSet<T> implements Iterable<T>, KMappedMarker {
    private final LinkedHashMap<T, Integer> pool = new LinkedHashMap<>();

    public final int add(T element) {
        LinkedHashMap<T, Integer> linkedHashMap = this.pool;
        Integer numValueOf = linkedHashMap.get(element);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(this.pool.size());
            linkedHashMap.put(element, numValueOf);
        }
        return numValueOf.intValue();
    }

    public final int getSize() {
        return this.pool.size();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.pool.keySet().iterator();
    }
}
