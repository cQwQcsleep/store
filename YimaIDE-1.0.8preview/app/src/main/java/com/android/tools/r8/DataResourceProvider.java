package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DataResourceProvider {

    public interface Visitor {
        void visit(DataDirectoryResource dataDirectoryResource);

        void visit(DataEntryResource dataEntryResource);
    }

    void accept(Visitor visitor) throws ResourceException;
}
