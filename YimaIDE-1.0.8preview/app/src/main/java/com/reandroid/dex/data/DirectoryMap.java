package com.reandroid.dex.data;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.data.DataItem;
import com.reandroid.dex.data.DefIndex;
import com.reandroid.dex.data.DirectoryEntry;
import com.reandroid.dex.data.DirectoryMap;
import com.reandroid.dex.key.Key;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.FilterIterator;
import defpackage.yt3;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DirectoryMap<DEFINITION extends DefIndex, VALUE extends DataItem> extends CountedBlockList<DirectoryEntry<DEFINITION, VALUE>> implements Iterable<DirectoryEntry<DEFINITION, VALUE>> {
    public DirectoryMap(IntegerReference integerReference, Creator<DirectoryEntry<DEFINITION, VALUE>> creator) {
        super(creator, integerReference);
    }

    public static /* synthetic */ boolean s(DefIndex defIndex, Predicate predicate, DirectoryEntry directoryEntry) {
        return directoryEntry.equalsDefIndex(defIndex) && predicate.test(directoryEntry.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void add(DEFINITION definition, VALUE value) {
        if (contains(definition, value)) {
            return;
        }
        ((DirectoryEntry) createNext()).set(definition, value);
    }

    public boolean contains(DEFINITION definition, VALUE value) {
        Iterator<DirectoryEntry<DEFINITION, VALUE>> entries = getEntries(definition);
        while (entries.hasNext()) {
            if (entries.next().equalsValue(value)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.reandroid.arsc.container.BlockList
    public int countBytes() {
        return getCount() * 8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void editInternal() {
        int size = size();
        for (int i = 0; i < size; i++) {
            ((DirectoryEntry) get(i)).editInternal();
        }
    }

    public Iterator<DirectoryEntry<DEFINITION, VALUE>> getEntries(final int i) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: du3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DirectoryEntry) obj).equalsDefIndex(i);
            }
        });
    }

    public Iterator<VALUE> getValues(int i) {
        return ComputeIterator.of(getEntries(i), new yt3());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void link(DEFINITION definition) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            ((DirectoryEntry) it.next()).link(definition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void merge(DirectoryMap<DEFINITION, VALUE> directoryMap) {
        if (directoryMap == this) {
            return;
        }
        int size = directoryMap.size();
        ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            ((DirectoryEntry) createNext()).merge((DirectoryEntry) directoryMap.get(i));
        }
    }

    public void remove(final DEFINITION definition) {
        super.removeIf(new Predicate() { // from class: zt3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DirectoryEntry) obj).equalsDefIndex(definition);
            }
        });
    }

    public boolean sort() {
        return super.sort(CompareUtil.getComparableComparator());
    }

    public void remove(final DEFINITION definition, final Predicate<VALUE> predicate) {
        super.removeIf(new Predicate() { // from class: au3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DirectoryMap.s(definition, predicate, (DirectoryEntry) obj);
            }
        });
    }

    public Iterator<DirectoryEntry<DEFINITION, VALUE>> getEntries(final DEFINITION definition) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: bu3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DirectoryEntry) obj).equalsDefIndex(definition);
            }
        });
    }

    public Iterator<VALUE> getValues(DEFINITION definition) {
        return ComputeIterator.of(getEntries(definition), new yt3());
    }

    public Iterator<DirectoryEntry<DEFINITION, VALUE>> getEntries(final Key key) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: cu3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DirectoryEntry) obj).matchesDefinition(key);
            }
        });
    }

    public Iterator<VALUE> getValues() {
        return ComputeIterator.of(iterator(), new yt3());
    }

    public boolean contains(DEFINITION definition) {
        return getEntries(definition).hasNext();
    }

    public boolean contains(DEFINITION definition, Key key) {
        Iterator<DirectoryEntry<DEFINITION, VALUE>> entries = getEntries(definition);
        while (entries.hasNext()) {
            if (entries.next().matchesValue(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(Key key, Key key2) {
        Iterator<DirectoryEntry<DEFINITION, VALUE>> entries = getEntries(key);
        while (entries.hasNext()) {
            if (entries.next().matchesValue(key2)) {
                return true;
            }
        }
        return false;
    }
}
