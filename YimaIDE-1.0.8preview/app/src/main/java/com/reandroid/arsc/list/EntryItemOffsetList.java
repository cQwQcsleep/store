package com.reandroid.arsc.list;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.header.TypeHeader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.OffsetItem;
import com.reandroid.arsc.value.Entry;
import com.reandroid.utils.collection.ArrayCollection;
import defpackage.ub4;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EntryItemOffsetList extends OffsetReferenceList<OffsetItem> {
    public EntryItemOffsetList(IntegerReference integerReference) {
        super(integerReference, OffsetItem.CREATOR_OFFSET32);
    }

    private void changeFromSparse(EntryItemList entryItemList, Creator<? extends OffsetItem> creator) {
        if (entryItemList.isEmpty()) {
            setCreator(creator);
            return;
        }
        entryItemList.sort();
        List<Integer> listListIds = listIds(entryItemList);
        int size = listListIds.size();
        int iIntValue = listListIds.get(size - 1).intValue();
        clear();
        setCreator(creator);
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int iIntValue2 = listListIds.get(i).intValue();
            while (true) {
                i2++;
                if (i2 < iIntValue2) {
                    entryItemList.createAt(i2);
                }
            }
            i++;
            i2 = iIntValue2;
        }
        entryItemList.setSize(iIntValue + 1);
        setSize(entryItemList.size());
    }

    private void changeToSparse(EntryItemList entryItemList) {
        if (entryItemList.isEmpty()) {
            setCreator(OffsetItem.CREATOR_SPARSE);
            return;
        }
        List<Integer> listListIds = listIds(entryItemList);
        int size = listListIds.size();
        clear();
        setCreator(OffsetItem.CREATOR_SPARSE);
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((OffsetItem) get(i)).setIdx(listListIds.get(i).intValue());
        }
        entryItemList.removeIf(new ub4());
    }

    private List<Integer> listIds(EntryItemList entryItemList) {
        int size = entryItemList.size();
        ArrayCollection arrayCollection = new ArrayCollection(size);
        for (int i = 0; i < size; i++) {
            Entry entry = entryItemList.get(i);
            if (!entry.isNull()) {
                arrayCollection.add(Integer.valueOf(entry.getId()));
            }
        }
        return arrayCollection;
    }

    public int findSortPoint(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i > ((OffsetItem) get(i2)).getIdx()) {
                return i2;
            }
        }
        return size;
    }

    public int getHighestIdx() {
        int size = size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            int idx = ((OffsetItem) get(i2)).getIdx();
            if (idx > i) {
                i = idx;
            }
        }
        return i;
    }

    public int getOffsetType() {
        Creator<OffsetItem> creator = getCreator();
        if (creator == OffsetItem.CREATOR_OFFSET16) {
            return TypeHeader.OFFSET_16;
        }
        return creator == OffsetItem.CREATOR_SPARSE ? TypeHeader.OFFSET_SPARSE : TypeHeader.OFFSET_32;
    }

    public int indexOfIdx(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i == ((OffsetItem) get(i2)).getIdx()) {
                return i2;
            }
        }
        return -1;
    }

    public boolean isSparse() {
        return getCreator() == OffsetItem.CREATOR_SPARSE;
    }

    public boolean setOffsetType(int i, EntryItemList entryItemList) {
        if (i == getOffsetType()) {
            return false;
        }
        if (i == TypeHeader.OFFSET_SPARSE) {
            changeToSparse(entryItemList);
        } else if (!isSparse()) {
            if (i == TypeHeader.OFFSET_16) {
                setCreator(OffsetItem.CREATOR_OFFSET16);
            } else {
                setCreator(OffsetItem.CREATOR_OFFSET32);
            }
            if (!entryItemList.isEmpty()) {
                clear();
            }
        } else if (i == TypeHeader.OFFSET_16) {
            changeFromSparse(entryItemList, OffsetItem.CREATOR_OFFSET16);
        } else {
            changeFromSparse(entryItemList, OffsetItem.CREATOR_OFFSET32);
        }
        return !entryItemList.isEmpty();
    }
}
