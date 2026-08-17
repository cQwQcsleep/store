package com.reandroid.arsc.value.plurals;

import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.value.AttributeType;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.bag.BagItem;
import com.reandroid.utils.HexUtil;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PluralsBagItem extends BagItem {

    /* JADX INFO: renamed from: com.reandroid.arsc.value.plurals.PluralsBagItem$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$reandroid$arsc$value$ValueType;

        static {
            int[] iArr = new int[ValueType.values().length];
            $SwitchMap$com$reandroid$arsc$value$ValueType = iArr;
            try {
                iArr[ValueType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$reandroid$arsc$value$ValueType[ValueType.REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private PluralsBagItem(ResValueMap resValueMap) {
        super(resValueMap);
    }

    public static PluralsBagItem copyOf(ResValueMap resValueMap) {
        ValueType valueType = resValueMap.getValueType();
        return valueType == ValueType.STRING ? new PluralsBagItem(resValueMap.getDataAsPoolString()) : new PluralsBagItem(valueType, resValueMap.getData());
    }

    public static PluralsBagItem create(ResValueMap resValueMap) {
        if (resValueMap == null) {
            return null;
        }
        return new PluralsBagItem(resValueMap);
    }

    private String formattedRefValue() {
        return HexUtil.toHex8("@0x", getValue());
    }

    public static PluralsBagItem reference(int i) {
        return new PluralsBagItem(ValueType.REFERENCE, i);
    }

    public static PluralsBagItem string(TableString tableString) {
        if (tableString == null) {
            return null;
        }
        return new PluralsBagItem((StringItem) tableString);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0046  */
    public String getQualityString(ResConfig resConfig) {
        Entry entry;
        int i = AnonymousClass1.$SwitchMap$com$reandroid$arsc$value$ValueType[getValueType().ordinal()];
        if (i == 1) {
            return getStringValue();
        }
        if (i != 2) {
            z01.a("Not STR/REFERENCE ValueType=", getValueType());
            return null;
        }
        ResValueMap resValueMap = this.mBagItem;
        Entry entry2 = resValueMap != null ? resValueMap.getEntry() : null;
        if (entry2 == null) {
            return null;
        }
        if (resConfig == null) {
            resConfig = entry2.getResConfig();
        }
        if (resConfig != null) {
            List listResolveReferenceWithConfig = entry2.getPackageBlock().getTableBlock().resolveReferenceWithConfig(getValue(), resConfig);
            if (listResolveReferenceWithConfig.size() > 0) {
                entry = (Entry) listResolveReferenceWithConfig.get(0);
            } else {
                entry = null;
            }
        } else {
            entry = null;
        }
        if (entry == null) {
            return null;
        }
        ResValue resValue = entry.getResValue();
        if (resValue != null && resValue.getValueType() == ValueType.STRING) {
            return resValue.getValueAsString();
        }
        z01.a("Not a STR reference: ", formattedRefValue());
        return null;
    }

    public AttributeType getQuantity() {
        AttributeType attributeType;
        ResValueMap resValueMap = this.mBagItem;
        if (resValueMap == null || (attributeType = resValueMap.getAttributeType()) == null || !attributeType.isPlural()) {
            return null;
        }
        return attributeType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<item quantity=\"");
        sb.append(getQuantity());
        sb.append("\">");
        if (hasStringValue()) {
            sb.append(getStringValue());
        } else {
            sb.append(formattedRefValue());
        }
        sb.append("</item>");
        return sb.toString();
    }

    private PluralsBagItem(StringItem stringItem) {
        super(stringItem);
    }

    private PluralsBagItem(ValueType valueType, int i) {
        super(valueType, i);
    }
}
