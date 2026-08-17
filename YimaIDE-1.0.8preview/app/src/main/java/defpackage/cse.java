package defpackage;

import io.vavr.Tuple3;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class cse implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;
    public final /* synthetic */ Comparator c;
    public final /* synthetic */ Comparator d;

    public /* synthetic */ cse(Comparator comparator, Comparator comparator2, Comparator comparator3) {
        this.b = comparator;
        this.c = comparator2;
        this.d = comparator3;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple3.b(this.b, this.c, this.d, (Tuple3) obj, (Tuple3) obj2);
    }
}
