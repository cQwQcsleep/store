package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.dexopt.ProfileClass;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProfileClass extends ShortItem implements LinkableProfileItem, KeyReference, Comparable<ProfileClass>, JSONConvert<JSONObject> {
    public static final Creator<ProfileClass> CREATOR = new Creator() { // from class: p9b
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new ProfileClass();
        }
    };
    private int idx = -1;
    private boolean invalid;
    private TypeKey typeKey;

    @Override // java.lang.Comparable
    public int compareTo(ProfileClass profileClass) {
        if (profileClass == this) {
            return 0;
        }
        return CompareUtil.compare(getIdx(), profileClass.getIdx());
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        setIdx(jSONObject.optInt("id"));
        setKey(TypeKey.create(jSONObject.optString("key")));
        this.invalid = jSONObject.optBoolean("invalid", false);
    }

    public int getIdx() {
        return this.idx;
    }

    public boolean isInvalid() {
        return this.invalid;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        TypeId typeId = (TypeId) dexFile.getItem(SectionType.TYPE_ID, getIdx());
        if (typeId == null) {
            this.invalid = true;
        } else {
            setKey(typeId.getKey());
            this.invalid = false;
        }
    }

    public void setIdx(int i) {
        this.idx = i;
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        this.typeKey = (TypeKey) key;
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", getIdx());
        TypeKey key = getKey();
        if (key != null) {
            jSONObject.put("key", key.getTypeName());
        }
        if (isInvalid()) {
            jSONObject.put("invalid", true);
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isInvalid()) {
            sb.append("INVALID ");
        }
        sb.append(getIdx());
        TypeKey key = getKey();
        if (key != null) {
            sb.append(" [");
            sb.append(key);
            sb.append(']');
        }
        return sb.toString();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        TypeId typeId = (TypeId) dexFile.getItem(SectionType.TYPE_ID, getKey());
        if (typeId == null) {
            this.invalid = true;
        } else {
            setIdx(typeId.getIdx());
            this.invalid = false;
        }
    }

    @Override // com.reandroid.dex.key.KeyItem
    public TypeKey getKey() {
        return this.typeKey;
    }
}
