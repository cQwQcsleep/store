package com.reandroid.arsc.value.style;

import com.reandroid.arsc.coder.CommonType;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.attribute.AttributeBag;
import com.reandroid.arsc.value.attribute.AttributeBagItem;
import com.reandroid.arsc.value.bag.BagItem;
import com.reandroid.utils.HexUtil;
import defpackage.aca;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleBagItem extends BagItem {
    private StyleBagItem(ResValueMap resValueMap) {
        super(resValueMap);
    }

    public static StyleBagItem attribute(int i) {
        return new StyleBagItem(ValueType.ATTRIBUTE, i);
    }

    public static StyleBagItem color(String str) {
        return encoded(ValueCoder.encode(str, CommonType.COLOR.valueTypes()));
    }

    public static StyleBagItem copyOf(ResValueMap resValueMap) {
        ValueType valueType = resValueMap.getValueType();
        return valueType == ValueType.STRING ? new StyleBagItem(resValueMap.getDataAsPoolString()) : new StyleBagItem(valueType, resValueMap.getData());
    }

    public static StyleBagItem create(ValueType valueType, int i) {
        if (valueType == null || valueType == ValueType.STRING) {
            return null;
        }
        return new StyleBagItem(valueType, i);
    }

    public static StyleBagItem createFloat(float f) {
        return new StyleBagItem(ValueType.FLOAT, Float.floatToIntBits(f));
    }

    public static StyleBagItem dimensionOrFraction(String str) {
        EncodeResult encodeResultEncode = ValueCoder.encode(str, new ValueType[]{ValueType.DIMENSION});
        if (encodeResultEncode == null) {
            encodeResultEncode = ValueCoder.encode(str, new ValueType[]{ValueType.FRACTION});
        }
        return encoded(encodeResultEncode);
    }

    public static StyleBagItem encoded(EncodeResult encodeResult) {
        if (encodeResult == null) {
            return null;
        }
        return create(encodeResult.valueType, encodeResult.value);
    }

    public static StyleBagItem enumOrFlag(AttributeBag attributeBag, String str) {
        return encoded(attributeBag.encodeEnumOrFlagValue(str));
    }

    public static StyleBagItem integer(int i) {
        return new StyleBagItem(ValueType.DEC, i);
    }

    public static StyleBagItem reference(int i) {
        return new StyleBagItem(ValueType.REFERENCE, i);
    }

    public static StyleBagItem string(TableString tableString) {
        if (tableString == null) {
            return null;
        }
        return new StyleBagItem((StringItem) tableString);
    }

    public String decodeAttributeValue(AttributeBag attributeBag) {
        if (hasIntValue()) {
            return attributeBag.decodeAttributeValue(getValue());
        }
        return null;
    }

    public Entry getAttributeEntry() {
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap == null) {
            return null;
        }
        return resValueMap.resolveName().get();
    }

    public AttributeBagItem[] getFlagsOrEnum(AttributeBag attributeBag) {
        if (hasIntValue()) {
            return attributeBag.searchValue(getValue());
        }
        return null;
    }

    public String getName() {
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap == null) {
            return null;
        }
        return resValueMap.decodeName(true);
    }

    public int getNameId() {
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap == null) {
            return 0;
        }
        return resValueMap.getNameId();
    }

    public String getValueAsReference() {
        ValueType valueType = getValueType();
        if (valueType == ValueType.REFERENCE || valueType == ValueType.ATTRIBUTE) {
            return getBagItem().decodeValue();
        }
        aca.a("Not REF ValueType=", valueType);
        return null;
    }

    public boolean hasAttributeValue() {
        return getValueType() == ValueType.ATTRIBUTE;
    }

    public boolean hasIntValue() {
        ValueType valueType = getValueType();
        return valueType == ValueType.DEC || valueType == ValueType.HEX;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<item name=\"");
        String name = getName();
        if (name == null) {
            name = HexUtil.toHex8("@0x", getNameId());
        }
        sb.append(name);
        sb.append("\">");
        if (hasStringValue()) {
            sb.append(getStringValue());
        }
        String valueAsReference = (hasReferenceValue() || hasAttributeValue()) ? getValueAsReference() : null;
        if (valueAsReference == null) {
            valueAsReference = HexUtil.toHex8(getValue());
        }
        sb.append(valueAsReference);
        sb.append("</item>");
        return sb.toString();
    }

    private StyleBagItem(ValueType valueType, int i) {
        super(valueType, i);
    }

    private StyleBagItem(StringItem stringItem) {
        super(stringItem);
    }

    public static StyleBagItem create(ResValueMap resValueMap) {
        if (resValueMap == null) {
            return null;
        }
        return new StyleBagItem(resValueMap);
    }
}
