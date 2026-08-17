package com.reandroid.arsc.item;

import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.pool.TypeStringPool;
import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TypeString extends StringItem {
    public TypeString(boolean z) {
        super(z);
    }

    public static boolean isTypeArray(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("array");
    }

    public static boolean isTypeAttr(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("attr");
    }

    public static boolean isTypePlurals(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("plurals");
    }

    public static boolean isTypeString(String str) {
        if (str == null) {
            return false;
        }
        return str.equals("string");
    }

    public static boolean isTypeStyle(String str) {
        if (str == null) {
            return false;
        }
        return str.contains("style");
    }

    public static String toXmlTagName(String str) {
        return (str == null || str.length() <= 0 || str.charAt(0) != '^') ? str : str.substring(1);
    }

    @Override // com.reandroid.arsc.item.StringItem, java.lang.Comparable
    public int compareTo(StringItem stringItem) {
        if (stringItem == null) {
            return -1;
        }
        return Integer.compare(getIndex(), stringItem.getIndex());
    }

    @Override // com.reandroid.arsc.item.StringItem
    public void ensureStringLinkUnlocked() {
    }

    public int getId() {
        TypeStringPool typeStringPool = (TypeStringPool) getParent(TypeStringPool.class);
        return typeStringPool != null ? typeStringPool.idOf(this) : getIndex() + 1;
    }

    @Override // com.reandroid.arsc.item.StringItem
    public StyleItem getOrCreateStyle() {
        return null;
    }

    public boolean isTypeId() {
        return isTypeId(get());
    }

    @Override // com.reandroid.arsc.item.StringItem, com.reandroid.arsc.item.StringBlock
    public String toString() {
        return HexUtil.toHex2((byte) getId()) + ':' + get();
    }

    public static boolean isTypeId(String str) {
        return TypeBlock.NAME_id.equals(str);
    }

    public boolean isTypeAttr() {
        return isTypeAttr(get());
    }
}
