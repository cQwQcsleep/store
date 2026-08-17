package com.reandroid.dex.dexopt;

import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.dexopt.HotMethodRegion;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HotMethodRegionList extends SizedBlockList<HotMethodRegion> implements LinkableProfileItem, JSONConvert<JSONArray> {
    public HotMethodRegionList(IntegerReference integerReference) {
        super(integerReference, HotMethodRegion.CREATOR);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void initIdx() {
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            HotMethodRegion hotMethodRegion = (HotMethodRegion) get(i2);
            i += hotMethodRegion.diffWithLastMethodDexIndex().get();
            hotMethodRegion.setIdx(i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateIdx() {
        int size = size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            HotMethodRegion hotMethodRegion = (HotMethodRegion) get(i);
            int idx = hotMethodRegion.getIdx();
            hotMethodRegion.diffWithLastMethodDexIndex().set(idx - i2);
            i++;
            i2 = idx;
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONArray jSONArray) {
        BlockList.fromJsonArray(this, jSONArray);
        sort();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        LinkableProfileItem.linkAll(dexFile, iterator());
    }

    @Override // com.reandroid.dex.dexopt.SizedBlockList
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        initIdx();
    }

    @Override // com.reandroid.dex.dexopt.SizedBlockList, com.reandroid.arsc.container.BlockList
    public void onRefreshed() {
        super.onRefreshed();
        sort();
    }

    public void removeInvalids() {
        removeIf(new Predicate() { // from class: wb6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HotMethodRegion) obj).isInvalid();
            }
        });
    }

    public boolean sort() {
        boolean zSort = sort(CompareUtil.getComparableComparator());
        updateIdx();
        return zSort;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        LinkableProfileItem.updateAll(dexFile, iterator());
        removeInvalids();
        sort();
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONArray toJson() {
        return BlockList.toJsonArray(this);
    }
}
