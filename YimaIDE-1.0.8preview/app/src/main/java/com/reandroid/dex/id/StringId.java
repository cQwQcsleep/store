package com.reandroid.dex.id;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.data.StringData;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringId extends IdItem implements IntegerReference, Comparable<StringId> {
    private StringData stringData;

    public StringId() {
        super(4);
    }

    public static boolean equals(StringId stringId, StringId stringId2) {
        if (stringId == stringId2) {
            return true;
        }
        if (stringId == null) {
            return false;
        }
        return ObjectsUtil.equals(stringId.getStringData(), stringId2.getStringData());
    }

    private void requireStringData() {
        if (getStringData() == null) {
            if (isRemoved()) {
                eq7.a("Removed string id, index = ", getIndex(), ", offset = ", get());
            } else {
                eq7.a("Unlinked string id, index = ", getIndex(), ", offset = ", get());
            }
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        StringData stringData = getStringData();
        if (stringData != null) {
            stringData.append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.id.IdItem
    public void cacheItems() {
    }

    @Override // java.lang.Comparable
    public int compareTo(StringId stringId) {
        if (stringId == null) {
            return -1;
        }
        if (stringId == this) {
            return 0;
        }
        return SectionTool.compareIndex(getStringData(), stringId.getStringData());
    }

    public int get() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public StringKey getKey() {
        StringData stringData = getStringData();
        if (stringData != null) {
            return stringData.getKey();
        }
        return null;
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.SectionItem
    public SectionType<StringId> getSectionType() {
        return SectionType.STRING_ID;
    }

    public String getString() {
        StringData stringData = getStringData();
        if (stringData != null) {
            return stringData.getString();
        }
        return null;
    }

    public StringData getStringData() {
        return this.stringData;
    }

    public void linkStringData(StringData stringData) {
        StringData stringData2 = this.stringData;
        if (stringData2 == stringData) {
            return;
        }
        if (stringData2 != null) {
            w01.a("String data already linked");
        } else {
            this.stringData = stringData;
            stringData.setOffsetReference(this);
        }
    }

    @Override // com.reandroid.dex.id.IdItem
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        getStringData().onReadBytes(blockReader);
    }

    @Override // com.reandroid.dex.common.SectionItem
    public void onRemovedInternal() {
        StringData stringData = this.stringData;
        if (stringData != null) {
            stringData.removeSelf(this);
            this.stringData = null;
        }
    }

    public void refresh() {
        set(this.stringData.getOffset());
    }

    public void set(int i) {
        Block.putInteger(getBytesInternal(), 0, i);
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        requireStringData();
        StringKey key2 = getKey();
        if (key2 != getStringData().updateString((StringKey) key)) {
            keyChanged(key2);
        }
    }

    public void setString(String str) {
        setKey(StringKey.create(str));
    }

    public String toString() {
        StringData stringData = getStringData();
        if (stringData != null) {
            return stringData.toString();
        }
        if (isRemoved()) {
            return "REMOVED";
        }
        return "Unlinked index = " + getIndex() + ", offset = " + get();
    }

    @Override // com.reandroid.dex.id.IdItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return EmptyIterator.of();
    }
}
