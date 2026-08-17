package defpackage;

import it.unimi.dsi.fastutil.booleans.BooleanComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ly0 implements BooleanComparator, Serializable {
    public final /* synthetic */ BooleanComparator b;
    public final /* synthetic */ BooleanComparator c;

    public /* synthetic */ ly0(BooleanComparator booleanComparator, BooleanComparator booleanComparator2) {
        this.b = booleanComparator;
        this.c = booleanComparator2;
    }

    @Override // it.unimi.dsi.fastutil.booleans.BooleanComparator
    public final int compare(boolean z, boolean z2) {
        return BooleanComparator.e0(this.b, this.c, z, z2);
    }
}
