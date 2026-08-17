package com.reandroid.dex.tools;

import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.SectionType;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexValidator {
    private final DexClassRepository classRepository;

    public DexValidator(DexClassRepository dexClassRepository) {
        this.classRepository = dexClassRepository;
    }

    private DexClassRepository getClassRepository() {
        return this.classRepository;
    }

    public Set<FieldKey> findMissingOrInaccessibleFields() {
        DexClass dexClass;
        HashSet hashSet = new HashSet();
        DexClassRepository classRepository = getClassRepository();
        Iterator items = classRepository.getItems(SectionType.FIELD_ID);
        while (items.hasNext()) {
            FieldKey key = ((FieldId) items.next()).getKey();
            if (!hashSet.contains(key) && (dexClass = classRepository.getDexClass(key.getDeclaring())) != null && dexClass.getField(key) == null) {
                hashSet.add(key);
            }
        }
        return hashSet;
    }

    public Set<MethodKey> findMissingOrInaccessibleMethods() {
        DexClass dexClass;
        HashSet hashSet = new HashSet();
        DexClassRepository classRepository = getClassRepository();
        Iterator items = classRepository.getItems(SectionType.METHOD_ID);
        while (items.hasNext()) {
            MethodKey key = ((MethodId) items.next()).getKey();
            if (!hashSet.contains(key) && (dexClass = classRepository.getDexClass(key.getDeclaring())) != null && dexClass.getMethod(key) == null) {
                hashSet.add(key);
            }
        }
        return hashSet;
    }
}
