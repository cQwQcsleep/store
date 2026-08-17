package com.reandroid.dex.dexopt;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.dexopt.InlineCache;
import com.reandroid.dex.model.DexFile;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InlineCache extends FixedBlockContainer implements LinkableProfileItem, JSONConvert<JSONObject> {
    public static final Creator<InlineCache> CREATOR = new Creator() { // from class: np6
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new InlineCache();
        }
    };
    private final IntegerReference dexPc;
    private final IntegerReference dexPcMapSize;
    private final BlockList<InlineCachePcMap> pcMapList;

    public InlineCache() {
        super(3);
        Block shortItem = new ShortItem();
        this.dexPc = shortItem;
        final ByteItem byteItem = new ByteItem();
        this.dexPcMapSize = byteItem;
        SizedBlockList sizedBlockList = new SizedBlockList(new IntegerReference() { // from class: com.reandroid.dex.dexopt.InlineCache.1
            public int get() {
                int i = byteItem.get();
                if (MethodEncodingType.isPcMapSize(i)) {
                    return i;
                }
                return 0;
            }

            public void set(int i) {
                byteItem.set(i);
            }

            public String toString() {
                return Integer.toString(get());
            }
        }, InlineCachePcMap.CREATOR);
        this.pcMapList = sizedBlockList;
        addChild(0, shortItem);
        addChild(1, byteItem);
        addChild(2, sizedBlockList);
    }

    public IntegerReference dexPc() {
        return this.dexPc;
    }

    @Override // com.reandroid.json.JSONConvert
    public void fromJson(JSONObject jSONObject) {
        dexPc().set(jSONObject.getInt("pc"));
        setEncodingType(MethodEncodingType.valueOf(jSONObject.optString("type")));
        BlockList.fromJsonArray(pcMapList(), jSONObject.optJSONArray("pc_maps"));
    }

    public MethodEncodingType getEncodingType() {
        return MethodEncodingType.valueOf(this.dexPcMapSize.get());
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void link(DexFile dexFile) {
        LinkableProfileItem.linkAll(dexFile, this.pcMapList.iterator());
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
    }

    public BlockList<InlineCachePcMap> pcMapList() {
        return this.pcMapList;
    }

    public void setEncodingType(MethodEncodingType methodEncodingType) {
        if (methodEncodingType != null) {
            this.dexPcMapSize.set(methodEncodingType.flag());
        }
    }

    @Override // com.reandroid.json.JSONConvert
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pc", dexPc().get());
        MethodEncodingType encodingType = getEncodingType();
        if (encodingType != null) {
            jSONObject.put("type", encodingType.name());
        }
        jSONObject.put("pc_maps", BlockList.toJsonArray(pcMapList()));
        return jSONObject;
    }

    public String toString() {
        return "InlineCache{dexPc=" + this.dexPc + "type=" + getEncodingType() + ", pcMapList=" + this.pcMapList + '}';
    }

    @Override // com.reandroid.dex.dexopt.LinkableProfileItem
    public void update(DexFile dexFile) {
        LinkableProfileItem.updateAll(dexFile, this.pcMapList.iterator());
    }
}
