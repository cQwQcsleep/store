package com.reandroid.arsc.value.array;

import com.reandroid.arsc.array.ResValueMapArray;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.bag.Bag;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayBag extends AbstractList<ArrayBagItem> implements Bag, RandomAccess {
    private final Entry entry;

    private ArrayBag(Entry entry) {
        this.entry = entry;
    }

    public static ArrayBag create(Entry entry) {
        if (entry == null || !entry.isComplex()) {
            return null;
        }
        return new ArrayBag(entry);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ResValueMapArray getMapArray() {
        return (ResValueMapArray) getTableEntry().getValue();
    }

    private ResTableMapEntry getTableEntry() {
        return (ResTableMapEntry) this.entry.getTableEntry();
    }

    public static boolean isArray(Entry entry) {
        ArrayBag arrayBagCreate = create(entry);
        if (arrayBagCreate == null) {
            return false;
        }
        ResTableMapEntry tableEntry = arrayBagCreate.getTableEntry();
        if (tableEntry.getParentId() != 0) {
            return false;
        }
        Iterator<ResValueMap> it = tableEntry.iterator();
        if (!it.hasNext()) {
            return false;
        }
        int i = 0;
        while (it.hasNext()) {
            int nameId = it.next().getNameId();
            if (((nameId >> 16) & 65535) != 256 || (nameId & 65535) != (i = i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void setIndex(ResValueMap resValueMap, int i) {
        resValueMap.setNameId(i + 16777217);
    }

    private void updateStructure(int i) {
        getTableEntry().setValuesCount(size());
        ((AbstractList) this).modCount++;
        if (i < 1) {
            return;
        }
        BlockArray mapArray = getMapArray();
        while (i < mapArray.size()) {
            setIndex((ResValueMap) mapArray.get(i), i);
            i++;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, ArrayBagItem arrayBagItem) {
        if (i < 0 || i > size()) {
            qc6.a();
            return;
        }
        if (arrayBagItem == null) {
            x0e.a("value is null");
            return;
        }
        ResValueMap resValueMap = new ResValueMap();
        setIndex(resValueMap, i);
        getMapArray().add(i, resValueMap);
        arrayBagItem.copyTo(resValueMap);
        updateStructure(i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        getMapArray().clear();
        updateStructure(-1);
    }

    @Override // java.util.AbstractList, java.util.List
    public ArrayBagItem get(int i) {
        return ArrayBagItem.create(getMapArray().get(i));
    }

    public ArrayBagItem[] getBagItems() {
        return (ArrayBagItem[]) toArray(new ArrayBagItem[0]);
    }

    @Override // com.reandroid.arsc.value.bag.Bag
    public Entry getEntry() {
        return this.entry;
    }

    @Override // java.util.AbstractList, java.util.List
    public ArrayBagItem remove(int i) {
        BlockArray mapArray = getMapArray();
        ResValueMap resValueMap = mapArray.get(i);
        mapArray.remove(resValueMap);
        updateStructure(i);
        return ArrayBagItem.copyOf(resValueMap);
    }

    @Override // java.util.AbstractList, java.util.List
    public ArrayBagItem set(int i, ArrayBagItem arrayBagItem) {
        ArrayBagItem arrayBagItem2 = get(i);
        arrayBagItem.copyTo(arrayBagItem2.getBagItem());
        return arrayBagItem2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getMapArray().size();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        String typeName = getTypeName();
        sb.append(typeName);
        sb.append(" name=\"");
        sb.append(getName());
        sb.append("\">");
        for (ArrayBagItem arrayBagItem : getBagItems()) {
            sb.append("\n    ");
            sb.append(arrayBagItem.toString());
        }
        sb.append("\n</");
        sb.append(typeName);
        sb.append(">");
        return sb.toString();
    }
}
