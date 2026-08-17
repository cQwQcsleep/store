package com.reandroid.arsc.list;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.header.TypeHeader;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.OffsetItem;
import com.reandroid.arsc.list.EntryItemList;
import com.reandroid.arsc.pool.SpecStringPool;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.CompareUtil;
import defpackage.ub4;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class EntryItemList extends OffsetBlockList<Entry> implements JSONConvert<JSONArray> {
    private static final Predicate<Entry> NON_NULL_PREDICATE = new Predicate() { // from class: vb4
        @Override // java.util.function.Predicate
        public final boolean test(Object obj) {
            return EntryItemList.o((Entry) obj);
        }
    };
    private final TypeHeader header;

    public EntryItemList(TypeHeader typeHeader, EntryItemOffsetList entryItemOffsetList) {
        super(typeHeader.getEntriesStart(), entryItemOffsetList, Entry.CREATOR);
        this.header = typeHeader;
        typeHeader.setOffsetTypeChangedListener(new TypeHeader.OffsetTypeChangedListener() { // from class: tb4
            @Override // com.reandroid.arsc.header.TypeHeader.OffsetTypeChangedListener
            public final void onOffsetTypeChanged(int i) {
                this.a.onOffsetTypeChanged(i);
            }
        });
    }

    private Entry createSparse(int i) {
        EntryItemOffsetList offsetReferenceList = getOffsetReferenceList();
        int iFindSortPoint = offsetReferenceList.findSortPoint(i);
        ((OffsetItem) offsetReferenceList.createAt(iFindSortPoint)).setIdx(i);
        return createAt(iFindSortPoint);
    }

    public static /* synthetic */ boolean o(Entry entry) {
        return !entry.isNull();
    }

    public int countNonNull() {
        return countIf(NON_NULL_PREDICATE);
    }

    public void fromJson(JSONArray jSONArray) {
        clear();
        if (jSONArray != null) {
            int length = jSONArray.length();
            if (!isSparse()) {
                setSize(length);
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                getOrCreate(jSONObject.getInt(TypeBlock.NAME_id)).fromJson(jSONObject);
            }
        }
        buildOffsetList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Entry getEntry(String str) {
        TypeBlock typeBlock;
        PackageBlock packageBlock;
        if (str == null || (typeBlock = (TypeBlock) getParentInstance(TypeBlock.class)) == null || (packageBlock = typeBlock.getPackageBlock()) == null) {
            return null;
        }
        Iterator<Entry> entries = packageBlock.getEntries(typeBlock.getTypeName(), str);
        while (entries.hasNext()) {
            Entry next = entries.next();
            if (next.getParentInstance(EntryItemList.class) == this) {
                return next;
            }
        }
        return null;
    }

    public int getEntryId(int i) {
        return isSparse() ? ((OffsetItem) getOffsetReferenceList().get(i)).getIdx() : i;
    }

    public int getEntryIndex(int i) {
        return isSparse() ? getOffsetReferenceList().indexOfIdx(i) : i;
    }

    public int getHighestEntryId() {
        return isSparse() ? getOffsetReferenceList().getHighestIdx() : size() - 1;
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public EntryItemOffsetList getOffsetReferenceList() {
        return (EntryItemOffsetList) super.getOffsetReferenceList();
    }

    public int getOffsetType() {
        return getOffsetReferenceList().getOffsetType();
    }

    public Entry getOrCreate(int i) {
        Entry entry = getEntry(i);
        if (entry == null) {
            return isSparse() ? createSparse(i) : createAt(i);
        }
        return entry;
    }

    public Boolean hasComplexEntry() {
        Iterator<Entry> it = iterator(true);
        while (it.hasNext()) {
            Entry next = it.next();
            if (next.isComplex()) {
                return Boolean.TRUE;
            }
            ValueType valueType = next.getResValue().getValueType();
            if (valueType != null && valueType != ValueType.REFERENCE && valueType != ValueType.NULL) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public boolean isEmptyEntries() {
        return !iterator(true).hasNext();
    }

    public boolean isSparse() {
        return getOffsetReferenceList().isSparse();
    }

    public Iterator<Entry> iterator(boolean z) {
        return !z ? iterator() : iterator(NON_NULL_PREDICATE);
    }

    public void linkSpecStringsInternal(SpecStringPool specStringPool) {
        Iterator<Entry> it = iterator(true);
        while (it.hasNext()) {
            it.next().linkSpecStringsInternal(specStringPool);
        }
    }

    public void linkTableStringsInternal(TableStringPool tableStringPool) {
        Iterator<Entry> it = iterator(true);
        while (it.hasNext()) {
            it.next().linkTableStringsInternal(tableStringPool);
        }
    }

    public void merge(EntryItemList entryItemList) {
        if (entryItemList == null || entryItemList == this) {
            return;
        }
        Iterator<Entry> it = entryItemList.iterator(true);
        while (it.hasNext()) {
            Entry next = it.next();
            getOrCreate(next.getId()).merge(next);
        }
        buildOffsetList();
    }

    public void onOffsetTypeChanged(int i) {
        if (getOffsetReferenceList().setOffsetType(i, this)) {
            buildOffsetList();
        }
    }

    public void onPreRemove(Entry entry) {
        entry.setNull(true);
        super.onPreRemove(entry);
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public void onRefreshed() {
        super.onRefreshed();
        ByteItem flags = this.header.getFlags();
        flags.set(getOffsetType() | (flags.get() & (-4)));
    }

    public void removeAllNull(int i) {
        trimLastIf(i, new ub4());
    }

    public void setOffsetType(int i) {
        this.header.setOffsetType(i);
    }

    public void sort() {
        if (isSparse()) {
            getOffsetReferenceList().sort(CompareUtil.getComparableComparator(), this);
        }
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m72toJson() {
        JSONArray jSONArray = new JSONArray(size());
        Iterator<Entry> it = iterator(true);
        while (it.hasNext()) {
            JSONObject json = it.next().toJson();
            if (json != null) {
                jSONArray.put(json);
            }
        }
        return jSONArray;
    }

    public Entry getEntry(int i) {
        if (isSparse()) {
            i = getEntryIndex(i);
        }
        return get(i);
    }
}
