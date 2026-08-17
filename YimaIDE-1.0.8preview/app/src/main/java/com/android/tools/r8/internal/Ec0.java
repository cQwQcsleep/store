package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Ec0 extends Rc0 {
    public Ec0(int i) {
        super(i);
    }

    @Override // com.android.tools.r8.internal.Rc0
    public final void k() {
        if (!this.e) {
            for (int i = 0; i < this.c.size(); i++) {
                Map.Entry entry = (Map.Entry) this.c.get(i);
                if (((C1856jk) ((InterfaceC0468Ep) entry.getKey())).m()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            for (Map.Entry entry2 : i()) {
                if (((C1856jk) ((InterfaceC0468Ep) entry2.getKey())).m()) {
                    entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                }
            }
        }
        if (this.e) {
            return;
        }
        this.d = this.d.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.d);
        this.g = this.g.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(this.g);
        this.e = true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return a((InterfaceC0468Ep) obj, obj2);
    }
}
