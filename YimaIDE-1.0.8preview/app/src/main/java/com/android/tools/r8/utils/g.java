package com.android.tools.r8.utils;

import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.DataResourceProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class g implements DataResourceProvider {
    public static final /* synthetic */ boolean b = true;
    public final /* synthetic */ h a;

    public g(h hVar) {
        this.a = hVar;
    }

    @Override // com.android.tools.r8.DataResourceProvider
    public final void accept(DataResourceProvider.Visitor visitor) {
        for (DataResource dataResource : this.a.b) {
            if (dataResource instanceof DataEntryResource) {
                visitor.visit((DataEntryResource) dataResource);
            } else {
                if (!b && !(dataResource instanceof DataDirectoryResource)) {
                    x1f.a();
                    return;
                }
                visitor.visit((DataDirectoryResource) dataResource);
            }
        }
    }
}
