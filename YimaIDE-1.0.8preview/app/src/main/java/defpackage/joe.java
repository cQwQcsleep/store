package defpackage;

import io.vavr.collection.TreeMap;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class joe implements Supplier {
    public final /* synthetic */ TreeMap b;

    public /* synthetic */ joe(TreeMap treeMap) {
        this.b = treeMap;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.b.emptyInstance();
    }
}
