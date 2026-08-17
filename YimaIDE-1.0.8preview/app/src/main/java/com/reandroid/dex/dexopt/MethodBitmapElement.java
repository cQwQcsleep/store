package com.reandroid.dex.dexopt;

import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodBitmapElement implements LinkableProfileItem, KeyReference, JSONConvert<JSONObject> {
    private final BooleanBit postStartup;
    private final BooleanBit startup;

    public MethodBitmapElement(BooleanBit booleanBit, BooleanBit booleanBit2) {
        this.startup = booleanBit;
        this.postStartup = booleanBit2;
    }

    private MethodBitmap getParentBitmap() {
        return (MethodBitmap) this.startup.getParentInstance(MethodBitmap.class);
    }

    private void setInvalid(boolean z) {
        this.postStartup.setTag(z ? Boolean.TRUE : null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.startup == ((MethodBitmapElement) obj).startup;
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        setKey(MethodKey.parse(jSONObject.optString("key")));
        setStartup(jSONObject.optBoolean("startup", false));
        setPostStartup(jSONObject.optBoolean("post_startup", false));
        setInvalid(jSONObject.optBoolean("invalid", false));
    }

    public int getFlags() {
        int iFlag = isStartup() ? MethodEncodingType.STARTUP.flag() : 0;
        return isPostStartup() ? MethodEncodingType.POST_STARTUP.flag() | iFlag : iFlag;
    }

    public int getIdx() {
        return this.startup.getIndex();
    }

    @Override // com.reandroid.dex.key.KeyItem
    public MethodKey getKey() {
        return (MethodKey) this.startup.getTag();
    }

    public int hashCode() {
        return getIdx();
    }

    public boolean isInvalid() {
        Boolean bool = (Boolean) this.postStartup.getTag();
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public boolean isPostStartup() {
        return this.postStartup.get();
    }

    public boolean isStartup() {
        return this.startup.get();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        MethodKey key;
        boolean z;
        MethodId methodId = (MethodId) dexFile.getItem(SectionType.METHOD_ID, getIdx());
        if (methodId != null) {
            key = methodId.getKey();
            z = false;
        } else {
            key = null;
            z = true;
        }
        setKey(key);
        setInvalid(z);
    }

    public BooleanBit postStartup() {
        return this.postStartup;
    }

    public void removeSelf() {
        MethodBitmap parentBitmap = getParentBitmap();
        if (parentBitmap != null) {
            parentBitmap.remove(this);
        }
    }

    public void setIdx(int i) {
        MethodBitmap parentBitmap;
        if (i == getIdx() || (parentBitmap = getParentBitmap()) == null) {
            return;
        }
        parentBitmap.moveTo(this, i);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        this.startup.setTag(key);
    }

    public void setPostStartup(boolean z) {
        this.postStartup.set(z);
    }

    public void setStartup(boolean z) {
        this.startup.set(z);
    }

    public BooleanBit startup() {
        return this.startup;
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", getIdx());
        if (isStartup()) {
            jSONObject.put("startup", true);
        }
        if (isPostStartup()) {
            jSONObject.put("post_startup", true);
        }
        MethodKey key = getKey();
        if (key != null) {
            jSONObject.put("key", key.toString());
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
        sb.append(" (");
        sb.append(isStartup());
        sb.append(", ");
        sb.append(isPostStartup());
        sb.append(")");
        MethodKey key = getKey();
        if (key != null) {
            sb.append(", ");
            sb.append(key);
        }
        return sb.toString();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        boolean z;
        MethodId methodId = (MethodId) dexFile.getItem(SectionType.METHOD_ID, getKey());
        if (methodId != null) {
            setIdx(methodId.getIdx());
            z = false;
        } else {
            z = true;
        }
        setInvalid(z);
    }
}
