package defpackage;

import io.vavr.Tuple4;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class dse implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;
    public final /* synthetic */ Comparator c;
    public final /* synthetic */ Comparator d;
    public final /* synthetic */ Comparator e;

    public /* synthetic */ dse(Comparator comparator, Comparator comparator2, Comparator comparator3, Comparator comparator4) {
        this.b = comparator;
        this.c = comparator2;
        this.d = comparator3;
        this.e = comparator4;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple4.b(this.b, this.c, this.d, this.e, (Tuple4) obj, (Tuple4) obj2);
    }
}
