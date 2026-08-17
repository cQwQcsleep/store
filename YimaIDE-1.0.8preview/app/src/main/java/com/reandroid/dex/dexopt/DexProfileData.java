package com.reandroid.dex.dexopt;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DexProfileData extends FixedBlockContainer implements ProfileData {
    private boolean initialized;

    public DexProfileData(int i) {
        super(i);
    }

    public abstract ProfileClassList classList();

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        setName(jSONObject.getString("name"));
        setChecksum(jSONObject.getLong("checksum"));
        hotMethodList().fromJson(jSONObject.optJSONArray("hot_methods"));
        classList().fromJson(jSONObject.optJSONArray("classes"));
        methodBitmap().setSize(jSONObject.getInt("method_ids"));
        methodBitmap().fromJson(jSONObject.optJSONArray("method_bitmap"));
        setInitialized(true);
    }

    public abstract long getChecksum();

    public abstract HotMethodRegionList hotMethodList();

    @Override // com.reandroid.dex.dexopt.ProfileData
    public boolean isInitialized() {
        return this.initialized;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        hotMethodList().link(dexFile);
        classList().link(dexFile);
        methodBitmap().link(dexFile);
    }

    public abstract MethodBitmap methodBitmap();

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
        setInitialized(true);
    }

    public abstract void setChecksum(long j);

    @Override // com.reandroid.dex.dexopt.ProfileData
    public void setInitialized(boolean z) {
        this.initialized = z;
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", getName());
        jSONObject.put("checksum", getChecksum());
        jSONObject.put("hot_methods", hotMethodList().toJson());
        jSONObject.put("classes", classList().toJson());
        jSONObject.put("method_ids", methodBitmap().size());
        jSONObject.put("method_bitmap", methodBitmap().toJson());
        return jSONObject;
    }

    public String toString() {
        return "name=" + getName() + ", checksum=" + HexUtil.toHex(getChecksum(), 8) + ", methodList=" + hotMethodList() + ", classList=" + classList() + ", bitmap=" + methodBitmap();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        hotMethodList().update(dexFile);
        classList().update(dexFile);
        methodBitmap().update(dexFile, isInitialized());
        setInitialized(true);
    }
}
