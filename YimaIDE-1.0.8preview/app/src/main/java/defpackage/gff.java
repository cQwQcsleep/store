package defpackage;

import com.android.tools.r8.graph.C0245l1;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class gff implements Consumer {
    public final /* synthetic */ Set b;

    public /* synthetic */ gff(Set set) {
        this.b = set;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((C0245l1) obj);
    }
}
