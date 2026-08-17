package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.dexopt.HotMethodRegion;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.model.DexFile;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HotMethodRegion extends FixedBlockContainer implements LinkableProfileItem, Comparable<HotMethodRegion>, JSONConvert<JSONObject> {
    public static final Creator<HotMethodRegion> CREATOR = new Creator() { // from class: vb6
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new HotMethodRegion();
        }
    };
    private final IntegerReference diffWithLastMethodDexIndex;
    private int idx;
    private final BlockList<InlineCache> inlineCacheList;
    private boolean invalid;
    private MethodKey methodKey;

    public HotMethodRegion() {
        super(3);
        Block shortItem = new ShortItem();
        this.diffWithLastMethodDexIndex = shortItem;
        ShortItem shortItem2 = new ShortItem();
        CountedBlockList countedBlockList = new CountedBlockList(InlineCache.CREATOR, shortItem2);
        this.inlineCacheList = countedBlockList;
        this.idx = -1;
        addChild(0, shortItem);
        addChild(1, shortItem2);
        addChild(2, countedBlockList);
    }

    @Override // java.lang.Comparable
    public int compareTo(HotMethodRegion hotMethodRegion) {
        if (hotMethodRegion == this) {
            return 0;
        }
        return CompareUtil.compare(getIdx(), hotMethodRegion.getIdx());
    }

    public IntegerReference diffWithLastMethodDexIndex() {
        return this.diffWithLastMethodDexIndex;
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        this.invalid = jSONObject.optBoolean("invalid", false);
        setIdx(jSONObject.getInt("id"));
        setKey(MethodKey.parse(jSONObject.optString("key")));
        BlockList.fromJsonArray(this.inlineCacheList, jSONObject.optJSONArray("inline_caches"));
    }

    public InlineCache get(int i) {
        return this.inlineCacheList.get(i);
    }

    public int getIdx() {
        return this.idx;
    }

    public MethodKey getKey() {
        return this.methodKey;
    }

    public boolean isInvalid() {
        return this.invalid;
    }

    public Iterator<InlineCache> iterator() {
        return this.inlineCacheList.iterator();
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        MethodId methodId = (MethodId) dexFile.getItem(SectionType.METHOD_ID, getIdx());
        if (methodId != null) {
            setKey(methodId.getKey());
            this.invalid = false;
        } else {
            this.invalid = true;
        }
        LinkableProfileItem.linkAll(dexFile, iterator());
    }

    public void setIdx(int i) {
        this.idx = i;
    }

    public void setKey(Key key) {
        this.methodKey = (MethodKey) key;
    }

    public int size() {
        return this.inlineCacheList.size();
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        if (isInvalid()) {
            jSONObject.put("invalid", true);
        }
        jSONObject.put("id", getIdx());
        MethodKey key = getKey();
        if (key != null) {
            jSONObject.put("key", key.toString());
        }
        jSONObject.put("inline_caches", BlockList.toJsonArray(this.inlineCacheList));
        return jSONObject;
    }

    public String toString() {
        MethodKey key = getKey();
        if (key == null) {
            return "HotMethodRegion{diffWithLastMethodDexIndex=" + this.diffWithLastMethodDexIndex + ", inlineCacheList=" + this.inlineCacheList + '}';
        }
        return getIdx() + " [" + key + "], inlineCacheList=" + this.inlineCacheList;
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        MethodId methodId = (MethodId) dexFile.getItem(SectionType.METHOD_ID, getKey());
        if (methodId != null) {
            setIdx(methodId.getIdx());
            this.invalid = false;
        } else {
            this.invalid = true;
        }
        LinkableProfileItem.updateAll(dexFile, iterator());
    }
}
