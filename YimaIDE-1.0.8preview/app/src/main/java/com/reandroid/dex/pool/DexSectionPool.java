package com.reandroid.dex.pool;

import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyItem;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.pool.DexSectionPool;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.MultiMap;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexSectionPool<T extends SectionItem> extends MultiMap<Key, T> {
    private boolean keyItems;
    private boolean keyItemsChecked;
    private boolean keyItemsCreate;
    private final Section<T> section;

    public DexSectionPool(Section<T> section) {
        this.section = section;
    }

    public static /* synthetic */ int a(SectionItem sectionItem, SectionItem sectionItem2) {
        int iCompare = CompareUtil.compare(sectionItem.isRemoved(), sectionItem2.isRemoved());
        return (iCompare != 0 || sectionItem.isRemoved()) ? iCompare : CompareUtil.compare(sectionItem.getKey(), sectionItem2.getKey());
    }

    public static /* synthetic */ void b(ArrayCollection arrayCollection, List list) {
        SectionItem sectionItem = (SectionItem) list.get(0);
        for (int i = 1; i < list.size(); i++) {
            SectionItem sectionItem2 = (SectionItem) list.get(i);
            sectionItem2.setReplace(sectionItem);
            arrayCollection.add(sectionItem2);
        }
    }

    private boolean isKeyItems() {
        if (this.keyItemsChecked) {
            return this.keyItems;
        }
        if (getSectionType().isIdSection()) {
            this.keyItemsChecked = true;
            this.keyItems = true;
            this.keyItemsCreate = true;
            return true;
        }
        SectionItem sectionItemNewInstance = getSectionType().getCreator().newInstance();
        this.keyItemsChecked = true;
        boolean z = sectionItemNewInstance instanceof KeyItem;
        this.keyItems = z;
        this.keyItemsCreate = sectionItemNewInstance instanceof KeyReference;
        return z;
    }

    public int clearDuplicates() {
        if (size() == 0 || size() == getSection().getCount()) {
            return 0;
        }
        final ArrayCollection arrayCollection = new ArrayCollection();
        findDuplicates(new Comparator() { // from class: jr3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DexSectionPool.a((SectionItem) obj, (SectionItem) obj2);
            }
        }, new Consumer() { // from class: kr3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DexSectionPool.b(arrayCollection, (List) obj);
            }
        });
        getSection().getItemArray().removeAll(arrayCollection);
        return arrayCollection.size();
    }

    public boolean contains(Key key) {
        return super.containsKey(key);
    }

    public T createNext(Key key) {
        T t = (T) getSection().createItem();
        ((KeyReference) t).setKey(key);
        return t;
    }

    public T getOrCreate(Key key) {
        if (key == null || !isKeyItemsCreate()) {
            return null;
        }
        T t = get(key);
        if (t != null) {
            return t;
        }
        T t2 = (T) createNext(key);
        put(t2.getKey(), t2);
        return t2;
    }

    public Section<T> getSection() {
        return this.section;
    }

    public SectionType<T> getSectionType() {
        return getSection().getSectionType();
    }

    public boolean isKeyItemsCreate() {
        isKeyItems();
        return this.keyItemsCreate;
    }

    public void load() {
        if (isKeyItems()) {
            putAll(new Function() { // from class: lr3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((SectionItem) obj).getKey();
                }
            }, getSection().iterator());
        }
    }

    public void remove(T t) {
        if (t != null) {
            super.remove(t.getKey(), t);
        }
    }

    @Override // com.reandroid.utils.collection.MultiMap
    public String toString() {
        return getSectionType().getName() + "-Pool = " + size();
    }
}
