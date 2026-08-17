package defpackage;

import it.unimi.dsi.fastutil.doubles.DoubleComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class bw3 implements DoubleComparator, Serializable {
    public final /* synthetic */ DoubleComparator b;
    public final /* synthetic */ DoubleComparator c;

    public /* synthetic */ bw3(DoubleComparator doubleComparator, DoubleComparator doubleComparator2) {
        this.b = doubleComparator;
        this.c = doubleComparator2;
    }

    @Override // it.unimi.dsi.fastutil.doubles.DoubleComparator
    public final int compare(double d, double d2) {
        return DoubleComparator.d(this.b, this.c, d, d2);
    }
}
