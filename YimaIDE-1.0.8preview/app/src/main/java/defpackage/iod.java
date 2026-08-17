package defpackage;

import io.vavr.collection.Stream;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class iod implements Supplier {
    public final /* synthetic */ Stream b;

    @Override // java.util.function.Supplier
    public final Object get() {
        return this.b.tail();
    }
}
