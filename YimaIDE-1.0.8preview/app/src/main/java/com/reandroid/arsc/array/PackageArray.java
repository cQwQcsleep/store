package com.reandroid.arsc.array;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PackageArray extends BlockArray<PackageBlock> implements BlockLoad, JSONConvert<JSONArray>, Comparator<PackageBlock> {
    private final IntegerItem mPackageCount;

    public PackageArray(IntegerItem integerItem) {
        this.mPackageCount = integerItem;
        integerItem.setBlockLoad(this);
    }

    private PackageBlock pickOne(List<PackageBlock> list, int i) {
        PackageBlock packageBlock = null;
        if (list != null && list.size() != 0) {
            if (list.size() == 1 && i == 0) {
                return list.get(0);
            }
            for (PackageBlock packageBlock2 : list) {
                if (packageBlock2 != null && (i == 0 || i == packageBlock2.getId())) {
                    if (packageBlock == null || packageBlock2.getHeaderBlock().getChunkSize() > packageBlock.getHeaderBlock().getChunkSize()) {
                        packageBlock = packageBlock2;
                    }
                }
            }
        }
        return packageBlock;
    }

    private void refreshPackageCount() {
        this.mPackageCount.set(size());
    }

    public void destroy() {
        Iterator<PackageBlock> it = iterator(true);
        while (it.hasNext()) {
            it.next().destroy();
        }
        clear();
    }

    public void fromJson(JSONArray jSONArray) {
        int length = jSONArray.length();
        clear();
        ensureSize(length);
        for (int i = 0; i < length; i++) {
            ((PackageBlock) get(i)).fromJson(jSONArray.getJSONObject(i));
        }
    }

    public PackageBlock getOrCreate(int i) {
        PackageBlock packageBlockById = getPackageBlockById(i);
        if (packageBlockById != null) {
            return packageBlockById;
        }
        PackageBlock packageBlock = (PackageBlock) createNext();
        packageBlock.setId(i);
        packageBlock.setName("PACKAGE NAME");
        return packageBlock;
    }

    public PackageBlock getPackageBlockById(int i) {
        Iterator<PackageBlock> it = iterator(true);
        while (it.hasNext()) {
            PackageBlock next = it.next();
            if (next.getId() == i) {
                return next;
            }
        }
        return null;
    }

    public PackageBlock getPackageBlockByName(String str) {
        Iterator<PackageBlock> it = iterator(true);
        while (it.hasNext()) {
            PackageBlock next = it.next();
            if (Objects.equals(str, next.getName())) {
                return next;
            }
        }
        return null;
    }

    public void merge(PackageArray packageArray) {
        if (packageArray == null || packageArray == this) {
            return;
        }
        for (PackageBlock packageBlock : packageArray.listItems()) {
            getOrCreate(packageBlock.getId()).merge(packageBlock);
        }
    }

    public PackageBlock newInstance() {
        return new PackageBlock();
    }

    @Override // com.reandroid.arsc.io.BlockLoad
    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        IntegerItem integerItem = this.mPackageCount;
        if (block != integerItem) {
            return;
        }
        setSize(integerItem.get());
    }

    public void onRefreshed() {
        refreshPackageCount();
    }

    public void sort() {
        Iterator<PackageBlock> it = listItems().iterator();
        while (it.hasNext()) {
            it.next().sortTypes();
        }
        sort(this);
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m25toJson() {
        JSONArray jSONArray = new JSONArray();
        Iterator<PackageBlock> it = listItems().iterator();
        int i = 0;
        while (it.hasNext()) {
            JSONObject json = it.next().toJson();
            if (json != null) {
                jSONArray.put(i, json);
                i++;
            }
        }
        return jSONArray;
    }

    @Override // java.util.Comparator
    public int compare(PackageBlock packageBlock, PackageBlock packageBlock2) {
        return packageBlock.compareTo(packageBlock2);
    }

    public PackageBlock getOrCreate(byte b) {
        return getOrCreate(b & 255);
    }

    public PackageBlock pickOne(int i) {
        return pickOne(getChildes(), i);
    }

    public PackageBlock pickOne() {
        return pickOne(getChildes(), 0);
    }
}
