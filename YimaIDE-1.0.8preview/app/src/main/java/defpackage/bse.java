package defpackage;

import io.vavr.Tuple2;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bse implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;
    public final /* synthetic */ Comparator c;

    public /* synthetic */ bse(Comparator comparator, Comparator comparator2) {
        this.b = comparator;
        this.c = comparator2;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple2.b(this.b, this.c, (Tuple2) obj, (Tuple2) obj2);
    }
}
