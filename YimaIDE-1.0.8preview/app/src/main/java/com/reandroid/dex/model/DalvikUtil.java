package com.reandroid.dex.model;

import com.reandroid.dex.dalvik.DalvikMemberClass;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DalvikUtil;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikUtil {
    public static /* synthetic */ boolean a(DexClassRepository dexClassRepository, TypeKey typeKey) {
        return !dexClassRepository.containsClass(typeKey);
    }

    public static int cleanMissingMembers(DexClass dexClass) {
        DalvikMemberClass dalvikMemberClassOf = DalvikMemberClass.of(dexClass);
        if (dalvikMemberClassOf != null) {
            final DexClassRepository classRepository = dexClass.getClassRepository();
            dalvikMemberClassOf.removeIf(new Predicate() { // from class: e83
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DalvikUtil.a(classRepository, (TypeKey) obj);
                }
            });
            if (dalvikMemberClassOf.isEmpty()) {
                dexClass.removeAnnotation(dalvikMemberClassOf.getAnnotationType());
            }
        }
        return 0;
    }

    public static int cleanMissingMembers(DexClassRepository dexClassRepository) {
        Iterator<DexClass> dexClasses = dexClassRepository.getDexClasses();
        int iCleanMissingMembers = 0;
        while (dexClasses.hasNext()) {
            iCleanMissingMembers += cleanMissingMembers(dexClasses.next());
        }
        return iCleanMissingMembers;
    }
}
