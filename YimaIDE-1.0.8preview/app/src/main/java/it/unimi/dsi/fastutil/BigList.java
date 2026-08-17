package it.unimi.dsi.fastutil;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface BigList extends Size64, Collection {
    @Override // it.unimi.dsi.fastutil.Size64, java.util.Collection
    default int size() {
        return super.size();
    }
}
