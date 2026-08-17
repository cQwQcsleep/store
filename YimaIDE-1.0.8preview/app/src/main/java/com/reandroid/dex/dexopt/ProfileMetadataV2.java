package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.arsc.item.StringReference;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileMetadataV2 extends ProfileMetadata {
    private final ProfileClassList classList;
    private final StringReference name;
    private final IntegerReference profileIndex;
    private final IntegerReference typeIdCount;

    public ProfileMetadataV2() {
        super(6);
        Block shortItem = new ShortItem();
        this.profileIndex = shortItem;
        ShortItem shortItem2 = new ShortItem();
        Block profString = new ProfString(shortItem2);
        this.name = profString;
        Block integerItem = new IntegerItem();
        this.typeIdCount = integerItem;
        ShortItem shortItem3 = new ShortItem();
        ProfileClassList profileClassList = new ProfileClassList(shortItem3);
        this.classList = profileClassList;
        addChild(0, shortItem);
        addChild(1, shortItem2);
        addChild(2, profString);
        addChild(3, integerItem);
        addChild(4, shortItem3);
        addChild(5, profileClassList);
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata
    public ProfileClassList classList() {
        return this.classList;
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata, com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        super.fromJson(jSONObject);
        profileIndex().set(jSONObject.getInt("profile_index"));
        typeIdCount().set(jSONObject.getInt("types"));
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata
    public StringReference name() {
        return this.name;
    }

    public IntegerReference profileIndex() {
        return this.profileIndex;
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata, com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject json = super.toJson();
        json.put("profile_index", profileIndex().get());
        json.put("types", typeIdCount().get());
        return json;
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata
    public String toString() {
        return super.toString() + ", profileIndex=" + profileIndex() + ", types=" + typeIdCount();
    }

    public IntegerReference typeIdCount() {
        return this.typeIdCount;
    }

    @Override // com.reandroid.dex.dexopt.ProfileMetadata, com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        if (!isInitialized()) {
            profileIndex().set(getIndex());
        }
        typeIdCount().set(dexFile.getCount(SectionType.TYPE_ID));
        super.update(dexFile);
    }
}
