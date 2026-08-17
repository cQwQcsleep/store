package defpackage;

import io.vavr.Tuple1;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ase implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;

    public /* synthetic */ ase(Comparator comparator) {
        this.b = comparator;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple1.b(this.b, (Tuple1) obj, (Tuple1) obj2);
    }
}
