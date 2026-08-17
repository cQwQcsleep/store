package defpackage;

import it.unimi.dsi.fastutil.chars.CharComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class sf1 implements CharComparator, Serializable {
    public final /* synthetic */ CharComparator b;
    public final /* synthetic */ CharComparator c;

    public /* synthetic */ sf1(CharComparator charComparator, CharComparator charComparator2) {
        this.b = charComparator;
        this.c = charComparator2;
    }

    @Override // it.unimi.dsi.fastutil.chars.CharComparator
    public final int compare(char c, char c2) {
        return CharComparator.n(this.b, this.c, c, c2);
    }
}
