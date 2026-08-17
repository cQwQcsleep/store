package com.reandroid.arsc.array;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.value.StagedAliasEntry;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StagedAliasEntryArray extends BlockArray<StagedAliasEntry> implements BlockLoad, JSONConvert<JSONArray> {
    private final IntegerItem count;

    public StagedAliasEntryArray(IntegerItem integerItem) {
        this.count = integerItem;
        integerItem.setBlockLoad(this);
    }

    private void updateCount() {
        this.count.set(size());
    }

    public boolean contains(StagedAliasEntry stagedAliasEntry) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((StagedAliasEntry) it.next()).isEqual(stagedAliasEntry)) {
                return true;
            }
        }
        return false;
    }

    public void fromJson(JSONArray jSONArray) {
        clear();
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        setSize(length);
        for (int i = 0; i < length; i++) {
            ((StagedAliasEntry) get(i)).fromJson(jSONArray.getJSONObject(i));
        }
    }

    public StagedAliasEntry newInstance() {
        return new StagedAliasEntry();
    }

    @Override // com.reandroid.arsc.io.BlockLoad
    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        IntegerItem integerItem = this.count;
        if (block == integerItem) {
            setSize(integerItem.get());
        }
    }

    public void onRefreshed() {
        updateCount();
    }

    public StagedAliasEntry searchByStagedResId(int i) {
        Iterator it = iterator();
        while (it.hasNext()) {
            StagedAliasEntry stagedAliasEntry = (StagedAliasEntry) it.next();
            if (i == stagedAliasEntry.getStagedResId()) {
                return stagedAliasEntry;
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m30toJson() {
        if (size() == 0) {
            return null;
        }
        Iterator it = iterator();
        JSONArray jSONArray = new JSONArray(size());
        while (it.hasNext()) {
            jSONArray.put(0, ((StagedAliasEntry) it.next()).toJson());
        }
        return jSONArray;
    }
}
