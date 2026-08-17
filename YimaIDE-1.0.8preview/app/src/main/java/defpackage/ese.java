package defpackage;

import io.vavr.Tuple5;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ese implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;
    public final /* synthetic */ Comparator c;
    public final /* synthetic */ Comparator d;
    public final /* synthetic */ Comparator e;
    public final /* synthetic */ Comparator f;

    public /* synthetic */ ese(Comparator comparator, Comparator comparator2, Comparator comparator3, Comparator comparator4, Comparator comparator5) {
        this.b = comparator;
        this.c = comparator2;
        this.d = comparator3;
        this.e = comparator4;
        this.f = comparator5;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple5.b(this.b, this.c, this.d, this.e, this.f, (Tuple5) obj, (Tuple5) obj2);
    }
}
