package defpackage;

import it.unimi.dsi.fastutil.shorts.ShortComparator;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class x9d implements ShortComparator, Serializable {
    public final /* synthetic */ ShortComparator b;
    public final /* synthetic */ ShortComparator c;

    public /* synthetic */ x9d(ShortComparator shortComparator, ShortComparator shortComparator2) {
        this.b = shortComparator;
        this.c = shortComparator2;
    }

    @Override // it.unimi.dsi.fastutil.shorts.ShortComparator
    public final int compare(short s, short s2) {
        return ShortComparator.z(this.b, this.c, s, s2);
    }
}
