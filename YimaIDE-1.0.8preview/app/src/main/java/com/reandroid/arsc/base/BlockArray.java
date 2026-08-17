package com.reandroid.arsc.base;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockArray;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.common.ArraySupplier;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.collection.FilterIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BlockArray<T extends Block> extends BlockList<T> implements Creator<T>, ArraySupplier<T> {
    public BlockArray() {
        setCreator(this);
    }

    private Predicate<? super T> nonNullPredicate() {
        return new Predicate() { // from class: fx0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return BlockArray.o((Block) obj);
            }
        };
    }

    private Predicate<? super T> nullPredicate() {
        return new Predicate() { // from class: gx0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Block) obj).isNull();
            }
        };
    }

    public static /* synthetic */ boolean o(Block block) {
        return !block.isNull();
    }

    public void clear() {
        super.clearChildes();
    }

    public final int countNonNull() {
        return countIf(nonNullPredicate());
    }

    public /* bridge */ /* synthetic */ Object get(int i) {
        return super.get(i);
    }

    public int indexOf(Object obj) {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (obj == ((Block) it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public Iterator<T> iterator(boolean z) {
        return z ? iterator(nonNullPredicate()) : super.iterator();
    }

    public int lastIndexOf(Object obj) {
        Iterator it = iterator();
        int i = -1;
        int i2 = 0;
        while (it.hasNext()) {
            if (obj == ((Block) it.next())) {
                i = i2;
            }
            i2++;
        }
        return i;
    }

    public Iterable<T> listItems() {
        return listItems(false);
    }

    public void onPreRemove(T t) {
        t.setParent(null);
        t.setIndex(-1);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.readChildes(blockReader);
    }

    public void removeAllNull(int i) {
        int size = size() - i;
        if (size <= 0) {
            return;
        }
        setSize(size() - NumbersUtil.min(size, countFromLast(nullPredicate())));
    }

    public void setSize(int i) {
        int size = size();
        for (int i2 = i; i2 < size; i2++) {
            onPreRemove(get(i2));
        }
        super.setSize(i);
    }

    public Iterable<T> listItems(final boolean z) {
        return new Iterable() { // from class: ex0
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.b.iterator(z);
            }
        };
    }

    public BlockArray(Creator<? extends T> creator) {
        super(creator);
    }

    public Iterator<T> iterator(boolean z, int i, int i2) {
        Iterator<T> it = super.iterator(i, i2);
        return z ? FilterIterator.of(it, nonNullPredicate()) : it;
    }
}
