package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.value.Entry;
import com.reandroid.utils.CompareUtil;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class SpecString extends StringItem {
    public SpecString(boolean z) {
        super(z);
    }

    @Override // com.reandroid.arsc.item.StringItem
    public int compareStringValue(StringItem stringItem) {
        if (stringItem == null) {
            return -1;
        }
        if (stringItem == this) {
            return 0;
        }
        return CompareUtil.compare(get(), stringItem.get());
    }

    public Iterator<Entry> getEntries(final int i) {
        return getUsers(Entry.class, new Predicate<Entry>() { // from class: com.reandroid.arsc.item.SpecString.1
            @Override // java.util.function.Predicate
            public boolean test(Entry entry) {
                return i == entry.getTypeId();
            }
        });
    }

    @Override // com.reandroid.arsc.item.StringItem
    public StyleItem getOrCreateStyle() {
        return null;
    }

    public int resolveResourceId(String str) {
        Iterator<Entry> entries = getEntries(str);
        if (entries.hasNext()) {
            return entries.next().getResourceId();
        }
        return 0;
    }

    public Iterator<Entry> getEntries(Predicate<Entry> predicate) {
        return getUsers(Entry.class, predicate);
    }

    public Iterator<Entry> getEntries(final String str) {
        return getUsers(Entry.class, new Predicate<Entry>() { // from class: com.reandroid.arsc.item.SpecString.2
            @Override // java.util.function.Predicate
            public boolean test(Entry entry) {
                String str2 = str;
                return str2 == null || str2.equals(entry.getTypeName());
            }
        });
    }

    public Iterator<Entry> getEntries(final Block block) {
        return getUsers(Entry.class, new Predicate<Entry>() { // from class: com.reandroid.arsc.item.SpecString.3
            @Override // java.util.function.Predicate
            public boolean test(Entry entry) {
                return entry.getParentInstance(block.getClass()) == block;
            }
        });
    }
}
