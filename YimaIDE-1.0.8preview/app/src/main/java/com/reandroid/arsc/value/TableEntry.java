package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.arsc.value.ValueHeader;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class TableEntry<HEADER extends ValueHeader, VALUE extends Block> extends Block implements JSONConvert<JSONObject> {
    private final HEADER header;
    private final VALUE resValue;

    public TableEntry(HEADER header, VALUE value) {
        this.header = header;
        this.resValue = value;
        header.setParent(this);
        header.setIndex(0);
        value.setParent(this);
        value.setIndex(1);
    }

    public abstract Iterator<ValueItem> allValues();

    public abstract boolean canMerge(TableEntry<?, ?> tableEntry);

    public int countBytes() {
        return getHeader().countBytes() + getValue().countBytes();
    }

    @Override // com.reandroid.json.JSONConvert
    public abstract void fromJson(JSONObject jSONObject);

    public byte[] getBytes() {
        return Block.addBytes(getHeader().getBytes(), getValue().getBytes());
    }

    public final HEADER getHeader() {
        return this.header;
    }

    public Entry getParentEntry() {
        return (Entry) getParent(Entry.class);
    }

    public VALUE getValue() {
        return this.resValue;
    }

    public abstract void linkTableStringsInternal(TableStringPool tableStringPool);

    public abstract void merge(TableEntry<?, ?> tableEntry);

    public abstract void mergeWithName(ResourceMergeOption resourceMergeOption, TableEntry<?, ?> tableEntry);

    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
        } else {
            getHeader().onCountUpTo(blockCounter);
            getValue().onCountUpTo(blockCounter);
        }
    }

    public void onHeaderLoaded(VALUE value, HEADER header) {
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        ValueHeader header = getHeader();
        header.readBytes(blockReader);
        Block value = getValue();
        onHeaderLoaded(value, header);
        value.readBytes(blockReader);
    }

    public abstract void onRemoved();

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        return getHeader().writeBytes(outputStream) + getValue().writeBytes(outputStream);
    }

    public void refresh() {
    }

    @Override // com.reandroid.json.JSONConvert
    public abstract JSONObject toJson();

    public String toString() {
        return getHeader() + ", value={" + getValue() + "}";
    }
}
