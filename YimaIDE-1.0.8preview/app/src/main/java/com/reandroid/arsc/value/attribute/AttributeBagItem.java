package com.reandroid.arsc.value.attribute;

import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.AttributeDataFormat;
import com.reandroid.arsc.value.AttributeType;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.utils.HexUtil;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AttributeBagItem {
    private final ResValueMap mBagItem;

    public AttributeBagItem(ResValueMap resValueMap) {
        this.mBagItem = resValueMap;
    }

    public static AttributeBagItem[] create(List<ResValueMap> list) {
        if (list == null) {
            return null;
        }
        int size = list.size();
        AttributeBagItem[] attributeBagItemArr = new AttributeBagItem[size];
        AttributeBagItem attributeBagItem = null;
        for (int i = 0; i < size; i++) {
            AttributeBagItem attributeBagItem2 = new AttributeBagItem(list.get(i));
            attributeBagItemArr[i] = attributeBagItem2;
            if (attributeBagItem == null && AttributeType.FORMATS == attributeBagItem2.getType()) {
                attributeBagItem = attributeBagItem2;
            }
        }
        if (attributeBagItem != null) {
            return attributeBagItemArr;
        }
        return null;
    }

    public static String toString(AttributeBagItem[] attributeBagItemArr, boolean z) {
        if (attributeBagItemArr == null || (attributeBagItemArr.length) == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = false;
        for (AttributeBagItem attributeBagItem : attributeBagItemArr) {
            if (attributeBagItem != null) {
                if (z2) {
                    sb.append("|");
                }
                String nameOrHex = z ? attributeBagItem.getNameOrHex() : attributeBagItem.getName();
                if (nameOrHex == null) {
                    return null;
                }
                sb.append(nameOrHex);
                z2 = true;
            }
        }
        if (z2) {
            return sb.toString();
        }
        return null;
    }

    public boolean contains(AttributeDataFormat attributeDataFormat) {
        if (attributeDataFormat == null || !isFormats()) {
            return false;
        }
        return attributeDataFormat.matches(getBagItem().getData());
    }

    public ResValueMap getBagItem() {
        return this.mBagItem;
    }

    public int getData() {
        return getBagItem().getData();
    }

    public AttributeDataFormat[] getDataFormats() {
        if (isFormats()) {
            return AttributeDataFormat.decodeValueTypes(getBagItem().getData());
        }
        return null;
    }

    public String getName() {
        ResourceEntry resourceEntryResolveName;
        if (isType() || (resourceEntryResolveName = getBagItem().resolveName()) == null) {
            return null;
        }
        return resourceEntryResolveName.getName();
    }

    public String getNameOrHex() {
        String name = getName();
        return name == null ? ValueCoder.decodeUnknownNameId(getBagItem().getNameId()) : name;
    }

    public AttributeType getType() {
        return getBagItem().getAttributeType();
    }

    public boolean isEnum() {
        if (isFormats()) {
            return AttributeDataFormat.ENUM.matches(getBagItem().getData());
        }
        return false;
    }

    public boolean isEqualType(AttributeDataFormat attributeDataFormat) {
        return attributeDataFormat != null && isFormats() && attributeDataFormat.getMask() == getBagItem().getData();
    }

    public boolean isFlag() {
        if (isFormats()) {
            return AttributeDataFormat.FLAG.matches(getBagItem().getData());
        }
        return false;
    }

    public boolean isFormats() {
        return getType() == AttributeType.FORMATS;
    }

    public boolean isType() {
        return getType() != null;
    }

    public static String toString(AttributeBagItem[] attributeBagItemArr) {
        return toString(attributeBagItemArr, false);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ResValueMap bagItem = getBagItem();
        sb.append(getNameOrHex());
        sb.append("=");
        sb.append(HexUtil.toHex8(bagItem.getData()));
        return sb.toString();
    }
}
