package com.reandroid.arsc.array;

import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.value.LibraryInfo;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class LibraryInfoArray extends BlockArray<LibraryInfo> implements JSONConvert<JSONArray> {
    private final IntegerItem mInfoCount;

    public LibraryInfoArray(IntegerItem integerItem) {
        this.mInfoCount = integerItem;
    }

    public boolean containsLibraryInfo(String str) {
        Iterator it = getChildes().iterator();
        while (it.hasNext()) {
            if (((LibraryInfo) it.next()).packageNameMatches(str)) {
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
        ensureSize(length);
        for (int i = 0; i < length; i++) {
            ((LibraryInfo) get(i)).fromJson(jSONArray.getJSONObject(i));
        }
    }

    public LibraryInfo getById(int i) {
        for (LibraryInfo libraryInfo : listItems()) {
            if (libraryInfo != null && i == libraryInfo.getId()) {
                return libraryInfo;
            }
        }
        return null;
    }

    public LibraryInfo getOrCreate(int i) {
        LibraryInfo byId = getById(i);
        if (byId != null) {
            return byId;
        }
        int size = size();
        ensureSize(size + 1);
        LibraryInfo libraryInfo = (LibraryInfo) get(size);
        libraryInfo.setId(i);
        return libraryInfo;
    }

    public void merge(LibraryInfoArray libraryInfoArray) {
        if (libraryInfoArray == null || libraryInfoArray == this || libraryInfoArray.size() == 0) {
            return;
        }
        for (LibraryInfo libraryInfo : libraryInfoArray.listItems()) {
            getOrCreate(libraryInfo.getId()).merge(libraryInfo);
        }
    }

    public LibraryInfo newInstance() {
        return new LibraryInfo();
    }

    @Override // com.reandroid.arsc.base.BlockArray
    public void onReadBytes(BlockReader blockReader) throws IOException {
        setSize(this.mInfoCount.get());
        super.onReadBytes(blockReader);
    }

    public void onRefreshed() {
        this.mInfoCount.set(size());
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m23toJson() {
        JSONArray jSONArray = new JSONArray();
        Iterator<LibraryInfo> it = listItems().iterator();
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
}
