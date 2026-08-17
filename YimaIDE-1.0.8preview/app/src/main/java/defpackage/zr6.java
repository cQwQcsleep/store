package defpackage;

import it.unimi.dsi.fastutil.ints.IntComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class zr6 implements IntComparator, Serializable {
    public final /* synthetic */ IntComparator b;
    public final /* synthetic */ IntComparator c;

    public /* synthetic */ zr6(IntComparator intComparator, IntComparator intComparator2) {
        this.b = intComparator;
        this.c = intComparator2;
    }

    @Override // it.unimi.dsi.fastutil.ints.IntComparator
    public final int compare(int i, int i2) {
        return IntComparator.j0(this.b, this.c, i, i2);
    }
}
