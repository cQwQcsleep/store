package defpackage;

import io.vavr.Tuple7;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class gse implements Comparator, Serializable {
    public final /* synthetic */ Comparator b;
    public final /* synthetic */ Comparator c;
    public final /* synthetic */ Comparator d;
    public final /* synthetic */ Comparator e;
    public final /* synthetic */ Comparator f;
    public final /* synthetic */ Comparator g;
    public final /* synthetic */ Comparator h;

    public /* synthetic */ gse(Comparator comparator, Comparator comparator2, Comparator comparator3, Comparator comparator4, Comparator comparator5, Comparator comparator6, Comparator comparator7) {
        this.b = comparator;
        this.c = comparator2;
        this.d = comparator3;
        this.e = comparator4;
        this.f = comparator5;
        this.g = comparator6;
        this.h = comparator7;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return Tuple7.b(this.b, this.c, this.d, this.e, this.f, this.g, this.h, (Tuple7) obj, (Tuple7) obj2);
    }
}
