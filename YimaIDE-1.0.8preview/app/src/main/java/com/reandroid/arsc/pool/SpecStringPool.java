package com.reandroid.arsc.pool;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.item.SpecString;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.value.Entry;
import com.reandroid.utils.collection.IterableIterator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SpecStringPool extends StringPool<SpecString> {
    public SpecStringPool(boolean z) {
        super(z, new StringCreator() { // from class: bkd
            public final StringItem newInstance(boolean z2) {
                return new SpecString(z2);
            }
        });
    }

    public Iterator<Entry> getEntries(final int i, String str) {
        return new IterableIterator<SpecString, Entry>(getAll(str)) { // from class: com.reandroid.arsc.pool.SpecStringPool.1
            public Iterator<Entry> iterator(SpecString specString) {
                return specString.getEntries(i);
            }
        };
    }

    public PackageBlock getPackageBlock() {
        return (PackageBlock) getParent(PackageBlock.class);
    }

    public void linkStrings() {
        super.linkStrings();
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock != null) {
            packageBlock.linkSpecStringsInternal(this);
        }
    }

    public int resolveResourceId(int i, String str) {
        Iterator<Entry> entries = getEntries(i, str);
        if (entries.hasNext()) {
            return entries.next().getResourceId();
        }
        return 0;
    }

    public Iterator<Entry> getEntries(final String str, String str2) {
        return new IterableIterator<SpecString, Entry>(getAll(str2)) { // from class: com.reandroid.arsc.pool.SpecStringPool.2
            public Iterator<Entry> iterator(SpecString specString) {
                return specString.getEntries(str);
            }
        };
    }

    public Iterator<Entry> getEntries(final Block block, String str) {
        return new IterableIterator<SpecString, Entry>(getAll(str)) { // from class: com.reandroid.arsc.pool.SpecStringPool.3
            public Iterator<Entry> iterator(SpecString specString) {
                return specString.getEntries(block);
            }
        };
    }

    public int resolveResourceId(String str, String str2) {
        Iterator<Entry> entries = getEntries(str, str2);
        if (entries.hasNext()) {
            return entries.next().getResourceId();
        }
        return 0;
    }

    public int resolveResourceId(Block block, String str) {
        Iterator<Entry> entries = getEntries(block, str);
        if (entries.hasNext()) {
            return entries.next().getResourceId();
        }
        return 0;
    }
}
