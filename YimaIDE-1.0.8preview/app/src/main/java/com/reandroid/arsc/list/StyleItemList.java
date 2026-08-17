package com.reandroid.arsc.list;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.StyleItem;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.utils.CompareUtil;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StyleItemList extends OffsetBlockList<StyleItem> {
    private boolean stringsLinked;

    public StyleItemList(IntegerReference integerReference, OffsetReferenceList<?> offsetReferenceList) {
        super(integerReference, offsetReferenceList, StyleItem.CREATOR);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean adjustIndexes() {
        int index;
        Iterator itClonedIterator = clonedIterator();
        boolean z = false;
        while (itClonedIterator.hasNext()) {
            StyleItem styleItem = (StyleItem) itClonedIterator.next();
            StringItem stringItemInternal = styleItem.getStringItemInternal();
            if (stringItemInternal != null && (index = stringItemInternal.getIndex()) != styleItem.getIndex()) {
                moveTo(styleItem, index);
                z = true;
            }
        }
        if (z) {
            ((StringPool) getParentInstance(StringPool.class)).linkStylesInternal();
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isSortingAllowed() {
        StringPool stringPool = (StringPool) getParentInstance(StringPool.class);
        if (stringPool != null) {
            return !stringPool.getStringsArray().isSortRequired();
        }
        return false;
    }

    public void linkStyleStringsInternal() {
        if (this.stringsLinked) {
            return;
        }
        this.stringsLinked = true;
        int size = size();
        for (int i = 0; i < size; i++) {
            ((StyleItem) get(i)).linkStringsInternal();
        }
    }

    public void onPreRemove(StyleItem styleItem) {
        styleItem.onRemoved();
        super.onPreRemove(styleItem);
    }

    @Override // com.reandroid.arsc.list.OffsetBlockList
    public void onRefreshed() {
        super.onRefreshed();
        sort();
    }

    public boolean sort(Comparator<? super StyleItem> comparator) {
        if (!isSortingAllowed()) {
            return false;
        }
        boolean zSort = super.sort(comparator);
        if (adjustIndexes() && super.sort(comparator)) {
            zSort = true;
        }
        trimLastIf(new Predicate() { // from class: jtd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((StyleItem) obj).isEmpty();
            }
        });
        return zSort;
    }

    public boolean sort() {
        return sort(CompareUtil.getComparableComparator());
    }
}
