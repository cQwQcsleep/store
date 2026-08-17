package com.reandroid.arsc.value;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.ParentChunk;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.graphics.AndroidColor;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Value {
    int getData();

    PackageBlock getPackageBlock();

    ParentChunk getParentChunk();

    default AndroidColor getValueAsColor() {
        ValueType valueType = getValueType();
        if (valueType == null || !valueType.isColor()) {
            return null;
        }
        return AndroidColor.decode(ValueCoder.decode(valueType, getData()));
    }

    default Float getValueAsFloat() {
        if (getValueType() != ValueType.FLOAT) {
            return null;
        }
        return Float.valueOf(Float.intBitsToFloat(getData()));
    }

    default Integer getValueAsInteger() {
        ValueType valueType = getValueType();
        if (valueType == ValueType.DEC || valueType == ValueType.HEX) {
            return Integer.valueOf(getData());
        }
        return null;
    }

    default ResourceEntry getValueAsReference() {
        TableBlock tableBlock;
        ValueType valueType = getValueType();
        ResourceEntry resource = null;
        if (valueType != null && valueType.isReference()) {
            PackageBlock packageBlock = getPackageBlock();
            if (packageBlock == null) {
                return null;
            }
            int data = getData();
            resource = packageBlock.getResource(data);
            if (resource == null && (tableBlock = packageBlock.getTableBlock()) != null) {
                return tableBlock.getResource(packageBlock, data);
            }
        }
        return resource;
    }

    default int getValueAsResourceId() {
        ValueType valueType = getValueType();
        if (valueType == null || !valueType.isReference()) {
            return 0;
        }
        return getData();
    }

    String getValueAsString();

    ValueType getValueType();

    void setData(int i);

    default void setTypeAndData(ValueType valueType, int i) {
        setData(i);
        setValueType(valueType);
    }

    void setValue(EncodeResult encodeResult);

    default void setValue(AndroidColor androidColor) {
        setValue(ValueCoder.encode(androidColor.toHexString()));
    }

    default void setValueAsDecimal(int i) {
        setTypeAndData(ValueType.DEC, i);
    }

    default void setValueAsFloat(float f) {
        setTypeAndData(ValueType.FLOAT, Float.floatToIntBits(f));
    }

    default void setValueAsHex(int i) {
        setTypeAndData(ValueType.HEX, i);
    }

    default void setValueAsResourceAttributeId(int i) {
        setTypeAndData(ValueType.ATTRIBUTE, i);
    }

    default void setValueAsResourceId(int i) {
        setTypeAndData(ValueType.REFERENCE, i);
    }

    void setValueAsString(String str);

    void setValueType(ValueType valueType);
}
