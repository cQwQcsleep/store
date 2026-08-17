package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IdSection<T extends IdItem> extends Section<T> {
    public IdSection(IntegerPair integerPair, SectionType<T> sectionType) {
        super(sectionType, new IdSectionArray(integerPair, sectionType.getCreator()));
    }

    public boolean canAddAll(Collection<IdItem> collection, int i) {
        if (i < 0) {
            i = 200;
        }
        int freeSpace = getFreeSpace();
        if (freeSpace <= i) {
            return false;
        }
        if (collection.size() < freeSpace) {
            return true;
        }
        HashSet hashSet = new HashSet();
        SectionType<T> sectionType = getSectionType();
        for (IdItem idItem : collection) {
            if (idItem.getSectionType() == sectionType) {
                Key key = idItem.getKey();
                if (hashSet.contains(key)) {
                    continue;
                } else {
                    hashSet.add(key);
                    if (!contains(key) && (freeSpace = freeSpace - 1) <= i) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override // com.reandroid.dex.sections.Section
    public T createItem() {
        return getItemArray().createNext();
    }

    public int getFreeSpace() {
        return 65535 - getCount();
    }

    @Override // com.reandroid.dex.sections.Section
    public T getSectionItem(int i) {
        T t = getItemArray().get(i);
        if (i < 0 || t != null) {
            return t;
        }
        throw new NullPointerException("Null id: " + i);
    }

    @Override // com.reandroid.dex.sections.Section
    public void onPreRefresh() {
        super.onPreRefresh();
        sort();
    }

    @Override // com.reandroid.dex.sections.Section
    public void onRefreshed(int i) {
        updateNextSection(i + getItemArray().countBytes());
    }

    @Override // com.reandroid.dex.sections.Section
    public boolean remove(Key key) {
        T sectionItem = getSectionItem(key);
        if (sectionItem == null || sectionItem.getParent() == null) {
            return false;
        }
        sectionItem.removeSelf();
        return true;
    }

    @Override // com.reandroid.dex.sections.Section
    public boolean removeWithKeys(final Predicate<? super Key> predicate) {
        return getItemArray().removeIf(new Predicate() { // from class: xi6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((IdItem) obj).getKey());
            }
        });
    }

    public IdSection(SectionType<T> sectionType, IdSectionArray<T> idSectionArray) {
        super(sectionType, idSectionArray);
    }
}
