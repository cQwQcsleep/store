package com.reandroid.arsc.chunk;

import com.reandroid.arsc.array.TypeBlockArray;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.header.SpecHeader;
import com.reandroid.arsc.item.SpecFlag;
import com.reandroid.arsc.item.SpecFlagsArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecBlock extends Chunk<SpecHeader> implements JSONConvert<JSONObject> {
    public static final String NAME_flag = "flag";
    public static final String NAME_spec = "spec";
    public static final String NAME_spec_flags = "spec_flags";
    private final SpecFlagsArray specFlagsArray;

    public enum Flag {
        SPEC_PUBLIC((byte) 64),
        SPEC_STAGED_API((byte) 32);

        private final byte flag;

        Flag(byte b) {
            this.flag = b;
        }

        public static boolean isPublic(byte b) {
            byte b2 = SPEC_PUBLIC.flag;
            return (b & b2) == b2;
        }

        public static boolean isStagedApi(byte b) {
            byte b2 = SPEC_STAGED_API.flag;
            return (b & b2) == b2;
        }

        public static String toString(byte b) {
            StringBuilder sb = new StringBuilder();
            int i = b & 255;
            int i2 = 0;
            boolean z = false;
            for (Flag flag : values()) {
                int i3 = flag.flag & 255;
                if ((i3 & i) == i3) {
                    if (z) {
                        sb.append('|');
                    }
                    sb.append(flag);
                    i2 |= i3;
                    z = true;
                }
            }
            if (i2 != i) {
                if (z) {
                    sb.append('|');
                }
                sb.append(HexUtil.toHex2((byte) i));
            }
            return sb.toString();
        }

        public byte getFlag() {
            return this.flag;
        }
    }

    public SpecBlock() {
        super(new SpecHeader(), 1);
        SpecFlagsArray specFlagsArray = new SpecFlagsArray(((SpecHeader) getHeaderBlock()).getEntryCount());
        this.specFlagsArray = specFlagsArray;
        addChild(specFlagsArray);
    }

    public void destroy() {
        setParent(null);
        getSpecFlagsArray().clear();
    }

    public void fromJson(JSONObject jSONObject) {
        setId(jSONObject.getInt(TypeBlock.NAME_id));
        getSpecFlagsArray().fromJson(jSONObject.optJSONArray(NAME_spec_flags));
    }

    public int getEntryCount() {
        return this.specFlagsArray.size();
    }

    public int getId() {
        return getHeaderBlock().getId().get();
    }

    public SpecFlag getSpecFlag(int i) {
        return getSpecFlagsArray().getFlag(i);
    }

    public SpecFlagsArray getSpecFlagsArray() {
        return this.specFlagsArray;
    }

    public SpecTypePair getSpecTypePair() {
        return (SpecTypePair) getParent(SpecTypePair.class);
    }

    public TypeBlockArray getTypeBlockArray() {
        SpecTypePair specTypePair = getSpecTypePair();
        if (specTypePair != null) {
            return specTypePair.getTypeBlockArray();
        }
        return null;
    }

    public byte getTypeId() {
        return getHeaderBlock().getId().getByte();
    }

    public List<Integer> listSpecFlags() {
        return this.specFlagsArray.toList();
    }

    public void merge(SpecBlock specBlock) {
        if (specBlock == null || specBlock == this) {
            return;
        }
        getSpecFlagsArray().merge(specBlock.getSpecFlagsArray());
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        this.specFlagsArray.refresh();
    }

    public void setEntryCount(int i) {
        this.specFlagsArray.setSize(i);
        this.specFlagsArray.refresh();
    }

    public void setId(int i) {
        setTypeId((byte) (i & 255));
    }

    public void setTypeId(byte b) {
        getHeaderBlock().getId().set(b);
        getTypeBlockArray().setTypeId(b);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TypeBlock.NAME_id, getId());
        jSONObject.put(NAME_spec_flags, getSpecFlagsArray().m68toJson());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        TypeBlockArray typeBlockArray = getTypeBlockArray();
        if (typeBlockArray != null) {
            sb.append(", typesCount=");
            sb.append(typeBlockArray.size());
        }
        return sb.toString();
    }
}
