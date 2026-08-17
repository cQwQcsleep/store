package com.reandroid.dex.sections;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.ParallelIntegerPair;
import com.reandroid.dex.base.ParallelReference;
import com.reandroid.dex.base.PositionAlignedItem;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.dex.sections.MapItem;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.CollectionUtil;
import defpackage.bu9;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MapList extends SpecialItem implements Iterable<MapItem>, PositionAlignedItem {
    private static final Creator<MapItem> CREATOR = new Creator() { // from class: cu9
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new MapItem();
        }
    };
    private final ParallelReference dataSize;
    private final ParallelReference dataStart;
    private final ParallelReference fileSize;
    private final CountedBlockList<MapItem> itemArray;
    private final DexPositionAlign positionAlign;

    public static class DataSizeReference implements IntegerReference {
        private final MapList mapList;

        private DataSizeReference(MapList mapList) {
            this.mapList = mapList;
        }

        public int get() {
            MapItem dataStartItem = this.mapList.getDataStartItem();
            if (dataStartItem != null) {
                return this.mapList.getFileSize().get() - dataStartItem.getOffset().get();
            }
            return 0;
        }

        public void set(int i) {
        }
    }

    public static class DataStartReference implements IntegerReference {
        private final MapList mapList;

        private DataStartReference(MapList mapList) {
            this.mapList = mapList;
        }

        public int get() {
            MapItem dataStartItem = this.mapList.getDataStartItem();
            if (dataStartItem != null) {
                return dataStartItem.getOffset().get();
            }
            return 0;
        }

        public void set(int i) {
        }
    }

    public static class FileSizeReference implements IntegerReference {
        private final MapList mapList;

        private FileSizeReference(MapList mapList) {
            this.mapList = mapList;
        }

        private int getHeaderBaseOffset() {
            SectionList sectionList = this.mapList.getSectionList();
            if (sectionList != null) {
                return sectionList.getHeader().getOffsetReference().get();
            }
            return 0;
        }

        public int get() {
            return (this.mapList.getOffset() + this.mapList.countBytes()) - getHeaderBaseOffset();
        }

        public void set(int i) {
        }
    }

    public MapList(IntegerReference integerReference) {
        super(3);
        Block integerItem = new IntegerItem();
        CountedBlockList<MapItem> countedBlockList = new CountedBlockList<>(CREATOR, integerItem);
        this.itemArray = countedBlockList;
        Block dexPositionAlign = new DexPositionAlign();
        this.positionAlign = dexPositionAlign;
        addChildBlock(0, dexPositionAlign);
        addChildBlock(1, integerItem);
        addChildBlock(2, countedBlockList);
        setOffsetReference(integerReference);
        this.fileSize = new ParallelReference(new FileSizeReference());
        this.dataStart = new ParallelReference(new DataStartReference());
        this.dataSize = new ParallelReference(new DataSizeReference());
    }

    private void linkIdTypesHeader(DexHeader dexHeader) {
        Iterator<SectionType<?>> idSectionTypes = SectionType.getIdSectionTypes();
        while (idSectionTypes.hasNext()) {
            MapItem mapItem = get(idSectionTypes.next());
            if (mapItem != null) {
                mapItem.link(dexHeader);
            }
        }
    }

    private void linkSpecialReference(SectionType<?> sectionType) {
        MapItem mapItem = get(sectionType);
        ParallelIntegerPair parallelIntegerPair = (ParallelIntegerPair) getSection(sectionType).getItemArray().getCountAndOffset();
        parallelIntegerPair.setReference2(mapItem.getCountAndOffset());
        parallelIntegerPair.refresh();
    }

    public MapItem get(SectionType<?> sectionType) {
        for (MapItem mapItem : this) {
            if (sectionType == mapItem.getSectionType()) {
                return mapItem;
            }
        }
        return null;
    }

    public MapItem[] getBodyReaderSorted() {
        List list = CollectionUtil.toList(this.itemArray.iterator(new Predicate() { // from class: au9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MapItem) obj).isNormalItem();
            }
        }));
        list.sort(SectionType.getReadComparator(new bu9()));
        return (MapItem[]) list.toArray(new MapItem[list.size()]);
    }

    public ParallelReference getDataSize() {
        return this.dataSize;
    }

    public ParallelReference getDataStart() {
        return this.dataStart;
    }

    public MapItem getDataStartItem() {
        while (true) {
            boolean z = false;
            for (MapItem mapItem : this) {
                SectionType<DexHeader> sectionType = mapItem.getSectionType();
                if (z) {
                    if (sectionType.isDataSection()) {
                        return mapItem;
                    }
                } else if (sectionType == SectionType.HEADER) {
                    z = true;
                }
            }
            return null;
        }
    }

    public ParallelReference getFileSize() {
        return this.fileSize;
    }

    public MapItem getOrCreate(SectionType<?> sectionType) {
        MapItem mapItem = get(sectionType);
        if (mapItem != null) {
            return mapItem;
        }
        MapItem mapItemCreateNext = this.itemArray.createNext();
        mapItemCreateNext.setType(sectionType);
        return mapItemCreateNext;
    }

    @Override // com.reandroid.dex.base.PositionAlignedItem
    public DexPositionAlign getPositionAlign() {
        return this.positionAlign;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<MapList> getSectionType() {
        return SectionType.MAP_LIST;
    }

    @Override // java.lang.Iterable
    public Iterator<MapItem> iterator() {
        return this.itemArray.iterator();
    }

    public void linkHeader(DexHeader dexHeader) {
        linkSpecialReference(SectionType.HEADER);
        linkSpecialReference(SectionType.MAP_LIST);
        linkIdTypesHeader(dexHeader);
        getFileSize().setReference2(dexHeader.fileSize);
        getDataSize().setReference2(dexHeader.data.getFirst());
        getDataStart().setReference2(dexHeader.data.getSecond());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        this.itemArray.removeIf(new Predicate() { // from class: du9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MapItem) obj).hasNoSection();
            }
        });
        this.itemArray.sort(CompareUtil.getComparableComparator());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        getFileSize().refresh();
        getDataStart().refresh();
        getDataSize().refresh();
    }

    public void remove(MapItem mapItem) {
        if (mapItem == null) {
            return;
        }
        ParallelIntegerPair countAndOffset = mapItem.getCountAndOffset();
        countAndOffset.getFirst().set(0);
        countAndOffset.getSecond().set(0);
        countAndOffset.setReference2(null);
        this.itemArray.remove(mapItem);
        mapItem.setParent((Block) null);
        mapItem.setIndex(-1);
    }

    public void sortMapItems(SectionType<?>[] sectionTypeArr) {
        this.itemArray.sort(SectionType.comparator(sectionTypeArr, new bu9()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (MapItem mapItem : this.itemArray) {
            if (i != 0) {
                sb.append('\n');
            }
            if (i < 9) {
                sb.append(' ');
            }
            i++;
            sb.append(i);
            sb.append(") ");
            sb.append(mapItem);
        }
        return sb.toString();
    }

    public void remove(SectionType<?> sectionType) {
        remove(get(sectionType));
    }
}
