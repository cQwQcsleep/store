package com.reandroid.arsc.value;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.xml.XMLUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AttributeValue extends ValueItem {
    public AttributeValue(int i, int i2) {
        super(i, i2);
    }

    private String decodeDataAsAttr() {
        ResourceEntry resourceEntryResolveName = resolveName();
        if (resourceEntryResolveName != null) {
            return resourceEntryResolveName.decodeAttributeData(getData());
        }
        return null;
    }

    public boolean allowNullPrefixEncode() {
        return false;
    }

    public String decodeDataAsAttrFormats() {
        return null;
    }

    public String decodeName() {
        return decodeName(true);
    }

    public abstract String decodeName(boolean z);

    public abstract String decodePrefix();

    @Override // com.reandroid.arsc.value.ValueItem
    public String decodeValue(boolean z) {
        if (AttributeDataFormat.INTEGER.contains(getValueType())) {
            String strDecodeDataAsAttrFormats = decodeDataAsAttrFormats();
            if (strDecodeDataAsAttrFormats == null) {
                strDecodeDataAsAttrFormats = decodeDataAsAttr();
            }
            if (strDecodeDataAsAttrFormats != null) {
                return strDecodeDataAsAttrFormats;
            }
        }
        return super.decodeValue(z);
    }

    public ResourceEntry encodeAttrName(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        if (str == null) {
            str = XMLUtil.splitPrefix(str2);
        }
        String strSplitName = XMLUtil.splitName(str2);
        EncodeResult encodeResultEncodeUnknownNameId = ValueCoder.encodeUnknownNameId(strSplitName);
        if (encodeResultEncodeUnknownNameId != null) {
            setName(strSplitName, encodeResultEncodeUnknownNameId.value);
            return new ResourceEntry(getPackageBlock(), encodeResultEncodeUnknownNameId.value);
        }
        if (str == null && !allowNullPrefixEncode()) {
            return null;
        }
        PackageBlock packageBlock = getPackageBlock();
        ResourceEntry attrResource = packageBlock.getTableBlock().getAttrResource(packageBlock, str, strSplitName);
        if (attrResource != null) {
            setName(strSplitName, attrResource.getResourceId());
        }
        return attrResource;
    }

    public ResourceEntry encodeIdName(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        if (str == null) {
            str = XMLUtil.splitPrefix(str2);
        }
        String strSplitName = XMLUtil.splitName(str2);
        EncodeResult encodeResultEncodeUnknownNameId = ValueCoder.encodeUnknownNameId(strSplitName);
        if (encodeResultEncodeUnknownNameId != null) {
            setName(strSplitName, encodeResultEncodeUnknownNameId.value);
            return new ResourceEntry(getPackageBlock(), encodeResultEncodeUnknownNameId.value);
        }
        if (str == null && !allowNullPrefixEncode()) {
            return null;
        }
        PackageBlock packageBlock = getPackageBlock();
        ResourceEntry idResource = packageBlock.getTableBlock().getIdResource(packageBlock, str, strSplitName);
        if (idResource != null) {
            setName(strSplitName, idResource.getResourceId());
        }
        return idResource;
    }

    public EncodeResult encodeStyleValue(ResourceEntry resourceEntry, String str) {
        return encodeStyleValue(false, resourceEntry, str);
    }

    public abstract int getNameId();

    @Override // com.reandroid.arsc.value.ValueItem
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ValueItem valueItem) {
        if (valueItem == null || valueItem == this || getClass() != valueItem.getClass()) {
            return;
        }
        AttributeValue attributeValue = (AttributeValue) valueItem;
        super.mergeWithName(resourceMergeOption, attributeValue);
        String strDecodeName = attributeValue.decodeName(false);
        ResourceEntry resourceEntryResolveName = attributeValue.resolveName();
        if (resourceEntryResolveName == null) {
            setName(strDecodeName, attributeValue.getNameId());
            return;
        }
        int nameId = attributeValue.getNameId();
        if (resourceEntryResolveName.isContext(attributeValue.getPackageBlock())) {
            PackageBlock packageBlock = getPackageBlock();
            ResourceEntry resourceEntryMergeWithName = resourceEntryResolveName.isDeclared() ? packageBlock.mergeWithName(resourceMergeOption, resourceEntryResolveName) : resourceMergeOption.resolveUndeclared(packageBlock, resourceEntryResolveName);
            if (resourceEntryMergeWithName != null) {
                nameId = resourceEntryMergeWithName.getResourceId();
                strDecodeName = resourceEntryMergeWithName.getName();
            }
        } else {
            nameId = resourceEntryResolveName.getResourceId();
        }
        setName(strDecodeName, nameId);
    }

    public ResourceEntry resolveName() {
        return resolve(getNameId());
    }

    public void setName(String str, int i) {
        setNameId(i);
    }

    public abstract void setNameId(int i);

    public EncodeResult encodeStyleValue(boolean z, ResourceEntry resourceEntry, String str) {
        return ValueCoder.encodeAttributeValue(z, this, resourceEntry, str);
    }

    public ResourceEntry encodeAttrName(String str) {
        return encodeAttrName(XMLUtil.splitPrefix(str), XMLUtil.splitName(str));
    }

    public ResourceEntry encodeIdName(String str) {
        return encodeIdName(XMLUtil.splitPrefix(str), XMLUtil.splitName(str));
    }
}
