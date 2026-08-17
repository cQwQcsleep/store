package com.reandroid.dex.dexopt;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.StringReference;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONObject;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ProfileMetadata extends FixedBlockContainer implements ProfileData {
    private boolean initialized;

    public ProfileMetadata(int i) {
        super(i);
    }

    public abstract ProfileClassList classList();

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        name().set(jSONObject.getString("name"));
        classList().fromJson(jSONObject.getJSONArray("classes"));
        setInitialized(true);
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public String getName() {
        return name().get();
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public boolean isInitialized() {
        return this.initialized;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        classList().link(dexFile);
    }

    public abstract StringReference name();

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
        setInitialized(true);
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public void setInitialized(boolean z) {
        this.initialized = z;
    }

    @Override // com.reandroid.dex.dexopt.ProfileData
    public void setName(String str) {
        name().set(str);
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", getName());
        jSONObject.put("classes", classList().toJson());
        return jSONObject;
    }

    public String toString() {
        return "name=" + getName() + ", classes=" + classList();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        classList().update(dexFile);
        setInitialized(true);
    }
}
