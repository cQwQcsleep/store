package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.dexopt.InlineCachePcMap;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InlineCachePcMap extends FixedBlockContainer implements LinkableProfileItem, JSONConvert<JSONObject> {
    public static final Creator<InlineCachePcMap> CREATOR = new Creator() { // from class: op6
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new InlineCachePcMap();
        }
    };
    private final ProfileClassList classList;
    private final IntegerReference profileIndex;

    public InlineCachePcMap() {
        super(3);
        Block byteItem = new ByteItem();
        this.profileIndex = byteItem;
        ByteItem byteItem2 = new ByteItem();
        ProfileClassList profileClassList = new ProfileClassList(byteItem2);
        this.classList = profileClassList;
        addChild(0, byteItem);
        addChild(1, byteItem2);
        addChild(2, profileClassList);
    }

    public ProfileClassList classList() {
        return this.classList;
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        profileIndex().set(jSONObject.getInt("profile_index"));
        classList().fromJson(jSONObject.optJSONArray("classes"));
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        classList().link(dexFile);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
    }

    public IntegerReference profileIndex() {
        return this.profileIndex;
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("profile_index", profileIndex().get());
        jSONObject.put("classes", classList().toJson());
        return jSONObject;
    }

    public String toString() {
        return "InlineCachePcMap{profileIndex=" + this.profileIndex + ", classList=" + this.classList + '}';
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        classList().update(dexFile);
    }
}
