package com.reandroid.arsc.chunk;

import com.reandroid.arsc.array.StagedAliasEntryArray;
import com.reandroid.arsc.header.StagedAliasHeader;
import com.reandroid.arsc.value.StagedAliasEntry;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StagedAlias extends Chunk<StagedAliasHeader> {
    private final StagedAliasEntryArray stagedAliasEntryArray;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.reandroid.arsc.array.StagedAliasEntryArray, com.reandroid.arsc.base.Block] */
    public StagedAlias() {
        super(new StagedAliasHeader(), 1);
        ?? stagedAliasEntryArray = new StagedAliasEntryArray(((StagedAliasHeader) getHeaderBlock()).getCountItem());
        this.stagedAliasEntryArray = stagedAliasEntryArray;
        addChild(stagedAliasEntryArray);
    }

    public static StagedAlias mergeAll(Collection<StagedAlias> collection) {
        if (collection.size() == 0) {
            return null;
        }
        StagedAlias stagedAlias = new StagedAlias();
        for (StagedAlias stagedAlias2 : collection) {
            if (!stagedAlias2.isNull()) {
                stagedAlias.merge(stagedAlias2);
            }
        }
        if (stagedAlias.isNull()) {
            return null;
        }
        stagedAlias.refresh();
        return stagedAlias;
    }

    public StagedAliasEntryArray getStagedAliasEntryArray() {
        return this.stagedAliasEntryArray;
    }

    public int getStagedAliasEntryCount() {
        return getStagedAliasEntryArray().size();
    }

    @Override // com.reandroid.arsc.base.Block
    public boolean isNull() {
        return getStagedAliasEntryCount() == 0;
    }

    public Iterable<StagedAliasEntry> listStagedAliasEntry() {
        return getStagedAliasEntryArray().listItems();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.value.StagedAliasEntry] */
    public void merge(StagedAlias stagedAlias) {
        if (stagedAlias == null || stagedAlias == this) {
            return;
        }
        StagedAliasEntryArray stagedAliasEntryArray = getStagedAliasEntryArray();
        for (StagedAliasEntry stagedAliasEntry : stagedAlias.listStagedAliasEntry()) {
            if (!stagedAliasEntryArray.contains(stagedAliasEntry)) {
                stagedAliasEntryArray.add(stagedAliasEntry);
            }
        }
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        getHeaderBlock().getCountItem().set(getStagedAliasEntryCount());
    }

    public StagedAliasEntry searchByStagedResId(int i) {
        return getStagedAliasEntryArray().searchByStagedResId(i);
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return getClass().getSimpleName() + ": count=" + getStagedAliasEntryCount();
    }
}
