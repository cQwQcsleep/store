package com.reandroid.arsc.value.array;

import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.bag.BagItem;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayBagItem extends BagItem {
    private ArrayBagItem(ResValueMap resValueMap) {
        super(resValueMap);
    }

    public static ArrayBagItem copyOf(ResValueMap resValueMap) {
        ValueType valueType = resValueMap.getValueType();
        return valueType == ValueType.STRING ? new ArrayBagItem(resValueMap.getDataAsPoolString()) : new ArrayBagItem(valueType, resValueMap.getData());
    }

    public static ArrayBagItem create(ValueType valueType, int i) {
        if (valueType == null || valueType == ValueType.STRING) {
            return null;
        }
        return new ArrayBagItem(valueType, i);
    }

    public static ArrayBagItem encoded(EncodeResult encodeResult) {
        if (encodeResult == null) {
            return null;
        }
        return create(encodeResult.valueType, encodeResult.value);
    }

    public static ArrayBagItem integer(int i) {
        return create(ValueType.DEC, i);
    }

    public static ArrayBagItem reference(int i) {
        return create(ValueType.REFERENCE, i);
    }

    public static ArrayBagItem string(TableString tableString) {
        if (tableString == null) {
            return null;
        }
        return new ArrayBagItem((StringItem) tableString);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<item>");
        if (hasStringValue()) {
            sb.append(getStringValue());
        } else {
            sb.append(HexUtil.toHex8(getValue()));
        }
        sb.append("</item>");
        return sb.toString();
    }

    private ArrayBagItem(StringItem stringItem) {
        super(stringItem);
    }

    private ArrayBagItem(ValueType valueType, int i) {
        super(valueType, i);
    }

    public static ArrayBagItem create(ResValueMap resValueMap) {
        if (resValueMap == null) {
            return null;
        }
        return new ArrayBagItem(resValueMap);
    }
}
