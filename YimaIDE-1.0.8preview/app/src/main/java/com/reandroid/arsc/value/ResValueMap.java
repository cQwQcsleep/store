package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.reandroid.xml.XMLAttribute;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResValueMap extends AttributeValue implements Comparable<ResValueMap> {
    public static final String NAME_name = "name";
    private static final int OFFSET_NAME = 0;
    private static final int OFFSET_SIZE = 4;

    public ResValueMap() {
        super(12, 4);
    }

    public void addAttributeTypeFormat(AttributeDataFormat attributeDataFormat) {
        if (attributeDataFormat == null) {
            return;
        }
        setData(attributeDataFormat.getMask() | getData());
    }

    public void addAttributeTypeFormats(AttributeDataFormat... attributeDataFormatArr) {
        if (attributeDataFormatArr == null) {
            return;
        }
        setData(AttributeDataFormat.sum(attributeDataFormatArr) | getData());
        if (getValueType() == ValueType.NULL) {
            setValueType(ValueType.DEC);
        }
    }

    public boolean allowNullPrefixEncode() {
        return true;
    }

    @Override // java.lang.Comparable
    public int compareTo(ResValueMap resValueMap) {
        int nameId;
        int nameId2;
        if (resValueMap == null) {
            return -1;
        }
        if (resValueMap == this || (nameId = getNameId()) == (nameId2 = resValueMap.getNameId())) {
            return 0;
        }
        if (nameId == 0) {
            return 1;
        }
        if (nameId2 == 0) {
            return -1;
        }
        return Integer.compare(nameId, nameId2);
    }

    public String decodeDataAsAttrFormats() {
        if (getAttributeType() != AttributeType.FORMATS) {
            return null;
        }
        int data = getData() & 255;
        return data == 0 ? "" : AttributeDataFormat.toString(AttributeDataFormat.decodeValueTypes(data));
    }

    public String decodeName(boolean z) {
        String packageName;
        int nameId = getNameId();
        if (!PackageBlock.isResourceId(nameId)) {
            if (nameId == 0 || getAttributeType() != null) {
                return null;
            }
            return ValueCoder.decodeUnknownNameId(nameId);
        }
        ResourceEntry resourceEntryResolve = resolve(nameId);
        if (resourceEntryResolve == null || !resourceEntryResolve.isDeclared()) {
            return ValueCoder.decodeUnknownNameId(nameId);
        }
        String name = resourceEntryResolve.getName();
        if (!z || resourceEntryResolve.getPackageBlock() == getPackageBlock() || (packageName = resourceEntryResolve.getPackageName()) == null) {
            return name;
        }
        return packageName + ":" + name;
    }

    public String decodePrefix() {
        ResourceEntry resourceEntryResolveName = resolveName();
        if (resourceEntryResolveName == null || getPackageBlock() == resourceEntryResolveName.getPackageBlock()) {
            return null;
        }
        return resourceEntryResolveName.getPackageName();
    }

    public EncodeResult encodeStyle(boolean z, XMLElement xMLElement) {
        XMLAttribute attribute = xMLElement.getAttribute("name");
        return attribute == null ? new EncodeResult("Missing attribute name") : encodeStyle(z, attribute.getPrefix(), attribute.getValueAsString(), xMLElement.getTextContent());
    }

    public void fromJson(JSONObject jSONObject) {
        super/*com.reandroid.arsc.value.ValueItem*/.fromJson(jSONObject);
        setNameId(jSONObject.getInt("name"));
    }

    public int getArrayIndex() {
        int nameId = getNameId();
        int i = (-65536) & nameId;
        if (i == 16777216 || i == 33554432) {
            return nameId & 65535;
        }
        return -1;
    }

    public AttributeType getAttributeType() {
        return AttributeType.valueOf(getNameId());
    }

    public AttributeDataFormat[] getAttributeTypeFormats() {
        if (getAttributeType() != AttributeType.FORMATS) {
            return null;
        }
        return AttributeDataFormat.decodeValueTypes(getData());
    }

    public Entry getEntry() {
        return (Entry) getParent(Entry.class);
    }

    public int getNameId() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    /* JADX INFO: renamed from: getParentChunk, reason: merged with bridge method [inline-methods] */
    public PackageBlock m4getParentChunk() {
        Entry entry = getEntry();
        if (entry != null) {
            return entry.getPackageBlock();
        }
        return null;
    }

    public Entry getParentEntry() {
        return (Entry) getParentInstance(Entry.class);
    }

    public ResTableMapEntry getParentMapEntry() {
        return (ResTableMapEntry) getParentInstance(ResTableMapEntry.class);
    }

    public void merge(ValueItem valueItem) {
        if (valueItem == this || !(valueItem instanceof ResValueMap)) {
            return;
        }
        ResValueMap resValueMap = (ResValueMap) valueItem;
        super/*com.reandroid.arsc.value.ValueItem*/.merge(resValueMap);
        setNameId(resValueMap.getNameId());
    }

    public void setArrayIndex() {
        setArrayIndex(getIndex() + 1);
    }

    public void setAttributeType(AttributeType attributeType) {
        setNameId(attributeType.getId());
        if (attributeType == AttributeType.FORMATS && getValueType() == ValueType.NULL) {
            setValueType(ValueType.DEC);
        }
    }

    public void setDataHigh(short s) {
        setData(((s & 65535) << 16) | (getData() & 65535));
    }

    public void setDataLow(short s) {
        setData((s & 65535) | (getData() & (-65536)));
    }

    public void setNameHigh(short s) {
        setNameId(((s & 65535) << 16) | (getNameId() & 65535));
    }

    public void setNameId(int i) {
        Block.putInteger(getBytesInternal(), 0, i);
    }

    public void setNameLow(short s) {
        setNameId((s & 65535) | (getNameId() & (-65536)));
    }

    public JSONObject toJson() {
        JSONObject json = super/*com.reandroid.arsc.value.ValueItem*/.toJson();
        if (json == null) {
            return null;
        }
        json.put("name", getNameId());
        return json;
    }

    public String toString() {
        String strDecodeName = decodeName();
        String strDecodeValue = decodeValue();
        if (strDecodeName == null || strDecodeValue == null) {
            return "name=" + HexUtil.toHex8(getNameId()) + ", " + super/*com.reandroid.arsc.value.ValueItem*/.toString();
        }
        return strDecodeName + "=\"" + strDecodeValue + "\"";
    }

    public void setArrayIndex(int i) {
        setNameId(i | 16777216);
    }

    public EncodeResult encodeStyle(XMLElement xMLElement) {
        return encodeStyle(false, xMLElement);
    }

    public EncodeResult encodeStyle(boolean z, String str, String str2) {
        return encodeStyle(z, XMLUtil.splitPrefix(str), XMLUtil.splitName(str), str2);
    }

    public EncodeResult encodeStyle(String str, String str2) {
        return encodeStyle(false, XMLUtil.splitPrefix(str), XMLUtil.splitName(str), str2);
    }

    public EncodeResult encodeStyle(String str, String str2, String str3) {
        return encodeStyle(false, str, str2, str3);
    }

    public EncodeResult encodeStyle(boolean z, String str, String str2, String str3) {
        ResourceEntry resourceEntryEncodeAttrName = super.encodeAttrName(str, str2);
        if (resourceEntryEncodeAttrName == null) {
            return new EncodeResult("Unknown attribute name");
        }
        return super.encodeStyleValue(z, resourceEntryEncodeAttrName, str3);
    }
}
