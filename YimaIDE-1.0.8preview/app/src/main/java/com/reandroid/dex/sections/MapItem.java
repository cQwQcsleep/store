package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IndirectInteger;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexBlockItem;
import com.reandroid.dex.base.IndirectIntegerPair;
import com.reandroid.dex.base.ParallelIntegerPair;
import com.reandroid.dex.base.ParallelReference;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.header.CountAndOffset;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MapItem extends DexBlockItem implements Comparable<MapItem> {
    public static final int SIZE = 12;
    private final ParallelIntegerPair countAndOffset;
    private final IndirectInteger type;

    public MapItem() {
        super(12);
        this.type = new IndirectInteger(this, 0);
        this.countAndOffset = new ParallelIntegerPair(new IndirectIntegerPair(this, 4));
    }

    @Override // java.lang.Comparable
    public int compareTo(MapItem mapItem) {
        if (mapItem == this) {
            return 0;
        }
        return CompareUtil.compare(getOffsetValue(), mapItem.getOffsetValue());
    }

    public <T1 extends SectionItem> Section<T1> createNewSection() {
        SectionType<T1> sectionType = getSectionType();
        if (sectionType == null) {
            System.err.println("Unknown section: " + toString());
            return null;
        }
        Block parent = (Block) getParent(SectionList.class);
        if (parent != null) {
            parent = parent.getParent();
        }
        if (parent == null) {
            parent = (Block) getParent(MapList.class);
        }
        if (parent == null) {
            parent = (Block) getParent(DexLayoutBlock.class);
        }
        if (parent == null) {
            parent = getParent();
        }
        Block block = (Section<T1>) sectionType.createSection(getCountAndOffset());
        block.setParent(parent);
        return block;
    }

    public ParallelReference getCount() {
        return this.countAndOffset.getFirst();
    }

    public ParallelIntegerPair getCountAndOffset() {
        return this.countAndOffset;
    }

    public int getCountValue() {
        return getCount().get();
    }

    public ParallelReference getOffset() {
        return this.countAndOffset.getSecond();
    }

    public int getOffsetValue() {
        return getOffset().get();
    }

    public <T1 extends SectionItem> SectionType<T1> getSectionType() {
        return SectionType.get(getType().get());
    }

    public IntegerReference getType() {
        return this.type;
    }

    public boolean hasNoSection() {
        return getSection(getSectionType()) == null;
    }

    public boolean isNormalItem() {
        SectionType sectionType = getSectionType();
        return (sectionType == null || sectionType.isSpecialSection()) ? false : true;
    }

    public void link(DexHeader dexHeader) {
        CountAndOffset countAndOffset = dexHeader.get(getSectionType());
        if (countAndOffset != null) {
            getCountAndOffset().setReference2(countAndOffset);
        }
    }

    public void setCount(int i) {
        getCount().set(i);
    }

    public void setOffset(int i) {
        getOffset().set(i);
    }

    public void setType(SectionType<?> sectionType) {
        getType().set(sectionType.getType());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        SectionType sectionType = getSectionType();
        String hex = sectionType == null ? HexUtil.toHex("UNKNOWN_", getType().get(), 1) : sectionType.getName();
        sb.append(hex);
        sb.append(' ');
        int length = 24 - hex.length();
        for (int i = 0; i < length; i++) {
            sb.append(LocaleUtility.IETF_SEPARATOR);
        }
        ParallelIntegerPair countAndOffset = getCountAndOffset();
        sb.append("[");
        String string = Integer.toString(countAndOffset.getFirst().get());
        sb.append(string);
        int length2 = 6 - string.length();
        for (int i2 = 0; i2 < length2; i2++) {
            sb.append(' ');
        }
        sb.append(',');
        String string2 = Integer.toString(countAndOffset.getSecond().get());
        int length3 = 8 - string2.length();
        for (int i3 = 0; i3 < length3; i3++) {
            sb.append(' ');
        }
        sb.append(string2);
        sb.append(']');
        return sb.toString();
    }
}
