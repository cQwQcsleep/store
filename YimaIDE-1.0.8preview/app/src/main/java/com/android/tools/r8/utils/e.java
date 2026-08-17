package com.android.tools.r8.utils;

import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResourceProvider;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e implements DataResourceProvider {
    public final /* synthetic */ f a;

    public e(f fVar) {
        this.a = fVar;
    }

    @Override // com.android.tools.r8.DataResourceProvider
    public final void accept(DataResourceProvider.Visitor visitor) {
        Iterator it = this.a.b.iterator();
        while (it.hasNext()) {
            visitor.visit((DataEntryResource) it.next());
        }
    }
}
