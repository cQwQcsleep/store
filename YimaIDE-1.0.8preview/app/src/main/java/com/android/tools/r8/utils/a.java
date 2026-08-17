package com.android.tools.r8.utils;

import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.internal.K7;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a implements DataResourceProvider.Visitor {
    public final /* synthetic */ Set a;
    public final /* synthetic */ Set b;

    public a(TreeSet treeSet, TreeSet treeSet2) {
        this.a = treeSet;
        this.b = treeSet2;
    }

    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataEntryResource dataEntryResource) {
        try {
            this.b.add(DataEntryResource.fromBytes(K7.a(dataEntryResource.getByteStream()), dataEntryResource.getName(), dataEntryResource.getOrigin()));
        } catch (ResourceException | IOException e) {
            rc6.a(e);
        }
    }

    @Override // com.android.tools.r8.DataResourceProvider.Visitor
    public final void visit(DataDirectoryResource dataDirectoryResource) {
        this.a.add(DataDirectoryResource.fromName(dataDirectoryResource.getName(), dataDirectoryResource.getOrigin()));
    }
}
