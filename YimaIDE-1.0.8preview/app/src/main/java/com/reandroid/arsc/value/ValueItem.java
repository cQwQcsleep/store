package com.reandroid.arsc.value;

import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.MainChunk;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.ParentChunk;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.coder.CoderUnknownStringRef;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.ReferenceItem;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.StyleDocument;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ValueItem extends BlockItem implements Value, JSONConvert<JSONObject> {
    public static final String NAME_data = "data";
    public static final String NAME_value_type = "value_type";
    private static final int OFFSET_DATA = 4;
    private static final int OFFSET_RES0 = 2;
    private static final int OFFSET_SIZE = 0;
    private static final int OFFSET_TYPE = 3;
    private ReferenceItem mStringReference;
    private final int sizeOffset;

    public static class ValueStringReference implements ReferenceItem {
        private final ValueItem valueItem;

        public ValueStringReference(ValueItem valueItem) {
            this.valueItem = valueItem;
        }

        @Override // com.reandroid.arsc.item.IntegerReference
        public int get() {
            return this.valueItem.getData();
        }

        @Override // com.reandroid.arsc.item.ReferenceItem
        public <T1 extends Block> T1 getReferredParent(Class<T1> cls) {
            ValueItem valueItem = this.valueItem;
            return cls.isInstance(valueItem) ? valueItem : (T1) valueItem.getParentInstance(cls);
        }

        @Override // com.reandroid.arsc.item.IntegerReference
        public void set(int i) {
            this.valueItem.writeData(i);
        }
    }

    public ValueItem(int i, int i2) {
        super(i);
        this.sizeOffset = i2;
        writeSize();
    }

    private String decodeAsReferenceString(ValueType valueType, boolean z) {
        int data = getData();
        if (data == 0) {
            return ValueCoder.decodeReference(null, valueType, data);
        }
        ResourceEntry valueAsReference = getValueAsReference();
        if (z && valueAsReference == null && getPackageBlock() == null) {
            x0e.a("Parent package block is null");
            return null;
        }
        if (valueAsReference == null || !valueAsReference.isDeclared()) {
            return ValueCoder.decodeUnknownResourceId(valueType == ValueType.REFERENCE, data);
        }
        return valueAsReference.buildReference(getPackageBlock(), valueType);
    }

    private int initializeBytes(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        int i = this.sizeOffset;
        blockReader.offset(i);
        int unsignedShort = blockReader.readUnsignedShort();
        int i2 = (unsignedShort >= 8 || blockReader.available() < 8) ? unsignedShort : 8;
        blockReader.seek(position);
        setBytesLength(i + i2, false);
        return unsignedShort;
    }

    private void linkStringReference(StringPool<?> stringPool) {
        StringItem stringItem = stringPool.get(getData());
        if (stringItem == null) {
            unLinkStringReference();
            return;
        }
        if (this.mStringReference != null) {
            unLinkStringReference();
        }
        ValueStringReference valueStringReference = new ValueStringReference(this);
        this.mStringReference = valueStringReference;
        stringItem.addReference(valueStringReference);
    }

    private void onTypeChanged(byte b, byte b2) {
        byte b3 = ValueType.STRING.getByte();
        if (b == b3) {
            unLinkStringReference();
        } else if (b2 == b3) {
            linkStringReference();
        }
    }

    private void unLinkStringReference() {
        ReferenceItem referenceItem = this.mStringReference;
        if (referenceItem == null) {
            return;
        }
        this.mStringReference = null;
        onUnlinkDataString(referenceItem);
    }

    private void writeSize() {
        int i = this.sizeOffset;
        Block.putShort(getBytesInternal(), i, (short) (countBytes() - i));
    }

    public String decodeValue(boolean z) {
        ValueType valueType = getValueType();
        if (valueType == null) {
            return null;
        }
        if (valueType.isReference()) {
            return decodeAsReferenceString(valueType, z);
        }
        return valueType == ValueType.STRING ? getValueAsString() : ValueCoder.decode(valueType, getData());
    }

    @Override // 
    public void fromJson(JSONObject jSONObject) {
        if (jSONObject != null) {
            ValueType valueTypeFromName = ValueType.fromName(jSONObject.getString(NAME_value_type));
            if (valueTypeFromName == ValueType.STRING) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(NAME_data);
                StringPool<?> stringPool = getStringPool();
                setTypeAndData(valueTypeFromName, (jSONObjectOptJSONObject != null ? stringPool.getOrCreate(jSONObjectOptJSONObject) : stringPool.getOrCreate(jSONObject.getString(NAME_data))).getIndex());
            } else if (valueTypeFromName == ValueType.BOOLEAN) {
                setValueAsBoolean(jSONObject.getBoolean(NAME_data));
            } else {
                setValueType(valueTypeFromName);
                setData(jSONObject.getInt(NAME_data));
            }
        }
    }

    public int getData() {
        return Block.getInteger(getBytesInternal(), this.sizeOffset + 4);
    }

    public StringItem getDataAsPoolString() {
        StringPool<?> stringPool;
        if (getValueType() == ValueType.STRING && (stringPool = getStringPool()) != null) {
            return stringPool.get(getData());
        }
        return null;
    }

    public PackageBlock getPackageBlock() {
        ParentChunk parentChunk = getParentChunk();
        if (parentChunk != null) {
            return parentChunk.getPackageBlock();
        }
        return null;
    }

    public byte getRes0() {
        return getBytesInternal()[this.sizeOffset + 2];
    }

    public int getSize() {
        return Block.getShort(getBytesInternal(), this.sizeOffset) & 65535;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StringPool<?> getStringPool() {
        for (Block parent = getParent(); parent != 0; parent = parent.getParent()) {
            if (parent instanceof MainChunk) {
                return ((MainChunk) parent).getStringPool();
            }
        }
        return null;
    }

    public byte getType() {
        return getBytesInternal()[this.sizeOffset + 3];
    }

    public boolean getValueAsBoolean() {
        return getData() != 0;
    }

    public String getValueAsString() {
        StringItem dataAsPoolString = getDataAsPoolString();
        if (dataAsPoolString == null) {
            return null;
        }
        String xml = dataAsPoolString.getXml();
        return xml == null ? XmlPullParser.NO_NAMESPACE : xml;
    }

    public StyleDocument getValueAsStyleDocument() {
        StringItem dataAsPoolString = getDataAsPoolString();
        if (dataAsPoolString != null) {
            return dataAsPoolString.getStyleDocument();
        }
        return null;
    }

    public ValueType getValueType() {
        return ValueType.valueOf(getType());
    }

    public boolean isUndefined() {
        return getValueType() == ValueType.NULL && getData() == 0;
    }

    public void linkTableStrings(TableStringPool tableStringPool) {
        if (getValueType() == ValueType.STRING) {
            linkStringReference(tableStringPool);
        }
    }

    public void merge(ValueItem valueItem) {
        if (valueItem == null || valueItem == this) {
            return;
        }
        if (valueItem.getSize() != 0) {
            setSize(valueItem.getSize());
        }
        ValueType valueType = valueItem.getValueType();
        if (valueType != ValueType.STRING) {
            setTypeAndData(valueType, valueItem.getData());
            return;
        }
        StringItem dataAsPoolString = valueItem.getDataAsPoolString();
        if (dataAsPoolString != null) {
            StyleDocument styleDocument = dataAsPoolString.getStyleDocument();
            if (styleDocument != null) {
                setValueAsString(styleDocument);
            } else {
                setValueAsString(dataAsPoolString.get());
            }
        }
    }

    public void mergeWithName(ResourceMergeOption resourceMergeOption, ValueItem valueItem) {
        int data;
        if (valueItem == null || valueItem == this) {
            return;
        }
        if (valueItem.getSize() != 0) {
            setSize(valueItem.getSize());
        }
        ValueType valueType = valueItem.getValueType();
        if (valueType != ValueType.STRING) {
            if (!valueType.isReference()) {
                setTypeAndData(valueType, valueItem.getData());
                return;
            }
            ResourceEntry valueAsReference = valueItem.getValueAsReference();
            if (valueAsReference != null && valueAsReference.isContext(valueItem.getPackageBlock())) {
                ResourceEntry resourceEntryMergeWithName = valueAsReference.isDeclared() ? getPackageBlock().mergeWithName(resourceMergeOption, valueAsReference) : resourceMergeOption.resolveUndeclared(getPackageBlock(), valueAsReference);
                data = resourceEntryMergeWithName != null ? resourceEntryMergeWithName.getResourceId() : 0;
            } else {
                data = valueItem.getData();
            }
            setTypeAndData(valueType, data);
            return;
        }
        StyleDocument valueAsStyleDocument = valueItem.getValueAsStyleDocument();
        if (valueAsStyleDocument != null) {
            setValueAsString(valueAsStyleDocument);
            return;
        }
        ApkFile apkFile = getPackageBlock().getTableBlock().getApkFile();
        ApkFile apkFile2 = valueItem.getPackageBlock().getTableBlock().getApkFile();
        String valueAsString = valueItem.getValueAsString();
        setValueAsString(valueAsString);
        if (apkFile == null || apkFile2 == null) {
            return;
        }
        apkFile.mergeWithName(resourceMergeOption, apkFile2, valueAsString);
    }

    public void onDataChanged() {
    }

    public void onDataLoaded() {
        if (getValueType() == ValueType.STRING) {
            linkStringReference();
        } else {
            unLinkStringReference();
        }
    }

    @Override // com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int iInitializeBytes = initializeBytes(blockReader);
        super.onReadBytes(blockReader);
        if (iInitializeBytes < 8) {
            setBytesLength(this.sizeOffset + 8, false);
            writeSize();
        }
    }

    public void onRemoved() {
        unLinkStringReference();
    }

    public void onUnlinkDataString(ReferenceItem referenceItem) {
        StringPool<?> stringPool = getStringPool();
        if (stringPool == null) {
            return;
        }
        stringPool.removeReference(referenceItem);
    }

    public void refresh() {
        updateSize();
    }

    public ResourceEntry resolve(int i) {
        TableBlock tableBlock;
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null || (tableBlock = packageBlock.getTableBlock()) == null) {
            return null;
        }
        return tableBlock.getResource(packageBlock, i);
    }

    public void serializeAttribute(XmlSerializer xmlSerializer, String str, String str2, boolean z) throws IOException {
        if (getValueType() == ValueType.STRING) {
            StringItem dataAsPoolString = getDataAsPoolString();
            if (dataAsPoolString != null) {
                dataAsPoolString.serializeAttribute(xmlSerializer, str, str2);
                return;
            } else {
                xmlSerializer.attribute(str, str2, CoderUnknownStringRef.INS.decode(getData()));
                return;
            }
        }
        String strDecodeValue = decodeValue();
        if (z && StringsUtil.isEmpty(strDecodeValue)) {
            return;
        }
        if (strDecodeValue == null) {
            strDecodeValue = XmlPullParser.NO_NAMESPACE;
        }
        xmlSerializer.attribute(str, str2, strDecodeValue);
    }

    public void serializeText(XmlSerializer xmlSerializer, boolean z) throws IOException {
        if (getValueType() != ValueType.STRING) {
            String strDecodeValue = decodeValue();
            if (strDecodeValue == null) {
                strDecodeValue = XmlPullParser.NO_NAMESPACE;
            }
            xmlSerializer.text(strDecodeValue);
            return;
        }
        StringItem dataAsPoolString = getDataAsPoolString();
        if (dataAsPoolString != null) {
            dataAsPoolString.serializeText(xmlSerializer, z);
        } else {
            xmlSerializer.text(CoderUnknownStringRef.INS.decode(getData()));
        }
    }

    public void setData(int i) {
        if (getData() == i) {
            return;
        }
        unLinkStringReference();
        writeData(i);
        if (ValueType.STRING == getValueType()) {
            linkStringReference();
        }
        onDataChanged();
    }

    public void setRes0(byte b) {
        getBytesInternal()[this.sizeOffset + 2] = b;
    }

    public void setSize(int i) {
        setBytesLength(this.sizeOffset + i, false);
        writeSize();
    }

    public void setType(byte b) {
        if (b == getType()) {
            return;
        }
        byte[] bytesInternal = getBytesInternal();
        int i = this.sizeOffset + 3;
        byte b2 = bytesInternal[i];
        bytesInternal[i] = b;
        onTypeChanged(b2, b);
        onDataChanged();
    }

    public void setValue(EncodeResult encodeResult) {
        encodeResult.getClass();
        if (encodeResult.isError()) {
            z01.a("Can not set error value: ", encodeResult.getError());
        } else {
            setTypeAndData(encodeResult.valueType, encodeResult.value);
        }
    }

    public void setValueAsBoolean(boolean z) {
        setValueType(ValueType.BOOLEAN);
        setData(z ? -1 : 0);
    }

    public void setValueAsString(StyleDocument styleDocument) {
        if (styleDocument == null) {
            setValueAsString(XmlPullParser.NO_NAMESPACE);
            return;
        }
        StringPool<?> stringPool = getStringPool();
        if (!styleDocument.hasElements()) {
            setValueAsString(XmlSanitizer.unEscapeUnQuote(styleDocument.getXml(false)));
        } else {
            setData(stringPool.getOrCreate(styleDocument).getIndex());
            setValueType(ValueType.STRING);
        }
    }

    public void setValueType(ValueType valueType) {
        setType(valueType != null ? valueType.getByte() : (byte) 0);
    }

    @Override // 
    public JSONObject toJson() {
        if (isNull()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        ValueType valueType = getValueType();
        jSONObject.put(NAME_value_type, valueType.name());
        if (valueType != ValueType.STRING) {
            if (valueType == ValueType.BOOLEAN) {
                jSONObject.put(NAME_data, getValueAsBoolean());
                return jSONObject;
            }
            jSONObject.put(NAME_data, getData());
            return jSONObject;
        }
        StringItem dataAsPoolString = getDataAsPoolString();
        if (dataAsPoolString.hasStyle()) {
            jSONObject.put(NAME_data, getDataAsPoolString().toJson());
            return jSONObject;
        }
        jSONObject.put(NAME_data, dataAsPoolString.get());
        return jSONObject;
    }

    public String toString() {
        StringItem dataAsPoolString;
        if (getPackageBlock() != null) {
            return getValueType() + ":" + HexUtil.toHex8(getData()) + " " + decodeValue();
        }
        StringBuilder sb = new StringBuilder();
        if (getSize() != 8) {
            sb.append("size=");
            sb.append(getSize());
            sb.append(", ");
        }
        sb.append("type=");
        ValueType valueType = getValueType();
        if (valueType != null) {
            sb.append(valueType);
        } else {
            sb.append(HexUtil.toHex2(getType()));
        }
        sb.append(", data=");
        int data = getData();
        if (valueType != ValueType.STRING || (dataAsPoolString = getDataAsPoolString()) == null) {
            sb.append(HexUtil.toHex8(data));
        } else {
            sb.append(dataAsPoolString.getHtml());
        }
        return sb.toString();
    }

    public void updateSize() {
        writeSize();
    }

    public void writeData(int i) {
        Block.putInteger(getBytesInternal(), this.sizeOffset + 4, i);
    }

    private void linkStringReference() {
        StringPool<?> stringPool = getStringPool();
        if (stringPool == null || stringPool.isStringLinkLocked()) {
            return;
        }
        linkStringReference(stringPool);
    }

    public String decodeValue() {
        return decodeValue(true);
    }

    public void serializeText(XmlSerializer xmlSerializer) throws IOException {
        serializeText(xmlSerializer, false);
    }

    public void setValueAsString(String str) {
        ValueType valueType = getValueType();
        ValueType valueType2 = ValueType.STRING;
        if (valueType == valueType2 && Objects.equals(str, getValueAsString())) {
            return;
        }
        if (str == null) {
            str = XmlPullParser.NO_NAMESPACE;
        }
        setData(getStringPool().getOrCreate(str).getIndex());
        setValueType(valueType2);
    }

    public void serializeAttribute(XmlSerializer xmlSerializer, String str, boolean z) throws IOException {
        serializeAttribute(xmlSerializer, null, str, z);
    }
}
