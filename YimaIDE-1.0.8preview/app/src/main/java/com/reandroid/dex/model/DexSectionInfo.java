package com.reandroid.dex.model;

import com.reandroid.dex.sections.MapItem;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexSectionInfo {
    private final DexLayout dexLayout;
    private final MapItem mapItem;

    public DexSectionInfo(DexLayout dexLayout, MapItem mapItem) {
        this.dexLayout = dexLayout;
        this.mapItem = mapItem;
    }

    private MapItem getMapItem() {
        return this.mapItem;
    }

    public int getCount() {
        return getMapItem().getCountValue();
    }

    public DexLayout getDexLayout() {
        return this.dexLayout;
    }

    public int getIndex() {
        return getMapItem().getIndex();
    }

    public String getName() {
        SectionType<?> sectionType = getSectionType();
        return sectionType != null ? sectionType.getName() : HexUtil.toHex("UNKNOWN_", getType(), 1);
    }

    public int getOffset() {
        return getMapItem().getOffsetValue();
    }

    public SectionType<?> getSectionType() {
        return getMapItem().getSectionType();
    }

    public int getType() {
        return getMapItem().getType().get();
    }

    public String print(boolean z) {
        String string;
        int i;
        String string2;
        StringBuilder sb = new StringBuilder();
        String name = getName();
        sb.append(name);
        sb.append(' ');
        int length = 24 - name.length();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append(LocaleUtility.IETF_SEPARATOR);
        }
        sb.append("[");
        int i3 = 8;
        if (z) {
            string = HexUtil.toHex(getCount(), 1);
            i = 8;
        } else {
            string = Integer.toString(getCount());
            i = 6;
        }
        sb.append(string);
        int length2 = i - string.length();
        for (int i4 = 0; i4 < length2; i4++) {
            sb.append(' ');
        }
        sb.append(", ");
        if (z) {
            string2 = HexUtil.toHex(getOffset(), 8);
            i3 = 10;
        } else {
            string2 = Integer.toString(getOffset());
        }
        int length3 = i3 - string2.length();
        for (int i5 = 0; i5 < length3; i5++) {
            sb.append(' ');
        }
        sb.append(string2);
        sb.append(']');
        return sb.toString();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("index", getIndex());
        jSONObject.put("name", getName());
        jSONObject.put("count", getCount());
        jSONObject.put("offset", getOffset());
        return jSONObject;
    }

    public String toString() {
        return print(false);
    }
}
