package com.reandroid.arsc.list;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockLocator;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.header.StringPoolHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Comparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StringItemList<T extends StringItem> extends OffsetBlockList<T> implements JSONConvert<JSONArray> {
    private final AlignItem alignment;
    private final StringPoolHeader header;
    private int mBytesCount;
    private boolean mSortRequired;

    public StringItemList(AlignItem alignItem, final StringPoolHeader stringPoolHeader, OffsetReferenceList<?> offsetReferenceList, final StringCreator<? extends T> stringCreator) {
        super(stringPoolHeader.getStartStrings(), offsetReferenceList);
        this.alignment = alignItem;
        this.header = stringPoolHeader;
        stringPoolHeader.setEncodingChangedListener(new StringPoolHeader.EncodingChangedListener() { // from class: mqd
            public final void onEncodingChanged(boolean z) {
                this.a.onStringEncodingChanged(z);
            }
        });
        setCreator(new Creator() { // from class: nqd
            public final Block newInstance() {
                return stringCreator.newInstance(stringPoolHeader.isUtf8());
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private StringPool<T> getStringPool() {
        return (StringPool) ObjectsUtil.cast(getParentInstance(StringPool.class));
    }

    private void resetCountBytes() {
        this.mBytesCount = 0;
    }

    public void add(int i, T t) {
        getStringPool().onPreAddInternal(i, t);
        super.add(i, t);
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public int buildOffsetList() {
        int iBuildOffsetList = super.buildOffsetList();
        this.mBytesCount = iBuildOffsetList - getAlignment().size();
        return iBuildOffsetList;
    }

    public int countBytes() {
        int i = this.mBytesCount;
        if (i != 0 || size() == 0) {
            return i;
        }
        int iCountBytes = super.countBytes();
        this.mBytesCount = iCountBytes;
        return iCountBytes;
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray != null) {
            int size = size();
            int length = jSONArray.length();
            setSize(size + length);
            for (int i = 0; i < length; i++) {
                ((StringItem) get(size + i)).fromJson(jSONArray.getJSONObject(i));
            }
        }
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public AlignItem getAlignment() {
        return this.alignment;
    }

    public boolean isSortRequired() {
        return this.mSortRequired;
    }

    public boolean isUtf8() {
        return this.header.isUtf8();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        Block block = blockCounter.END;
        if (block == this) {
            blockCounter.FOUND = true;
        } else if ((block instanceof StringItem) || (blockCounter instanceof BlockLocator)) {
            super.onCountUpTo(blockCounter);
        } else {
            blockCounter.addCount(countBytes());
        }
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        if (this.mSortRequired) {
            sort();
        }
    }

    public void onPreRemove(T t) {
        getStringPool().onStringRemoved(t);
        t.onRemoved();
        super.onPreRemove(t);
        resetCountBytes();
        this.mSortRequired = true;
    }

    public void onStringChanged(String str, T t) {
        StringPool<T> stringPool = getStringPool();
        if (stringPool != null) {
            resetCountBytes();
            stringPool.onStringChanged(str, t);
        }
        this.mSortRequired = true;
    }

    public void onStringEncodingChanged(boolean z) {
        int size = size();
        for (int i = 0; i < size; i++) {
            ((StringItem) get(i)).setUtf8(z);
        }
        resetCountBytes();
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public void readChildes(BlockReader blockReader) throws IOException {
        super.readChildes(blockReader);
        resetCountBytes();
        this.mSortRequired = false;
    }

    public void setUtf8(boolean z) {
        this.header.setUtf8(z);
    }

    public boolean sort(Comparator<? super T> comparator) {
        this.mSortRequired = false;
        boolean zSort = super.sort(comparator);
        StringPool<T> stringPool = getStringPool();
        if (stringPool != null) {
            stringPool.onSortedInternal();
        }
        this.mSortRequired = false;
        return zSort;
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m75toJson() {
        return BlockList.toJsonArray(this);
    }

    public void sort() {
        sort(CompareUtil.getComparableComparator());
    }
}
