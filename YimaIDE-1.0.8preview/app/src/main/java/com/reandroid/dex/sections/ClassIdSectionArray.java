package com.reandroid.dex.sections;

import com.reandroid.dex.base.IntegerPair;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.pool.DexSectionPool;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassIdSectionArray extends IdSectionArray<ClassId> {
    private boolean mDefinitionSortDisabled;

    public ClassIdSectionArray(IntegerPair integerPair) {
        super(integerPair, SectionType.CLASS_ID.getCreator());
    }

    private void onCyclicInheritance(ClassId classId) {
        this.mDefinitionSortDisabled = true;
        System.err.println("WARN: Cyclic inheritance involving '" + classId.getKey() + "'");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean sortDefinition(Set<TypeKey> set, DexSectionPool<ClassId> dexSectionPool, ClassId classId) {
        int index;
        int index2;
        TypeKey key = classId.getKey();
        Iterator<TypeKey> instanceKeys = classId.getInstanceKeys();
        boolean z = false;
        while (instanceKeys.hasNext()) {
            TypeKey next = instanceKeys.next();
            ClassId classId2 = dexSectionPool.get(next);
            if (classId2 != null && (index = classId.getIndex()) <= (index2 = classId2.getIndex())) {
                if (index == index2) {
                    onCyclicInheritance(classId);
                    return false;
                }
                moveTo(classId2, index);
                if (set == null) {
                    set = new HashSet<>();
                    set.add(key);
                } else if (set.contains(next)) {
                    onCyclicInheritance(classId);
                    return false;
                }
                set.add(next);
                sortDefinition(set, dexSectionPool, classId2);
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean comparatorSort(Comparator<? super ClassId> comparator) {
        return super/*com.reandroid.arsc.container.BlockList*/.sort(comparator);
    }

    public boolean sort(Comparator<? super ClassId> comparator) {
        SectionList sectionList;
        Section<T> parentSection = getParentSection();
        if (parentSection == 0 || (sectionList = parentSection.getSectionList()) == null) {
            return false;
        }
        return sectionList.getHeader().isClassDefinitionOrderEnforced() ? sortDefinition() : comparatorSort(comparator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean sortDefinition() {
        if (this.mDefinitionSortDisabled) {
            return false;
        }
        DexSectionPool pool = getParentSection().getPool();
        Iterator it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (sortDefinition(null, pool, (ClassId) it.next())) {
                z = true;
            }
        }
        return z && !this.mDefinitionSortDisabled;
    }
}
