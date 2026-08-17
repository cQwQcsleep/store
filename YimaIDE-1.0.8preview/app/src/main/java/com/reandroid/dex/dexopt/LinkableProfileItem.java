package com.reandroid.dex.dexopt;

import com.reandroid.dex.model.DexFile;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LinkableProfileItem {
    static void linkAll(DexFile dexFile, Iterator<? extends LinkableProfileItem> it) {
        while (it.hasNext()) {
            it.next().link(dexFile);
        }
    }

    static void updateAll(DexFile dexFile, Iterator<? extends LinkableProfileItem> it) {
        while (it.hasNext()) {
            it.next().update(dexFile);
        }
    }

    void link(DexFile dexFile);

    void update(DexFile dexFile);
}
