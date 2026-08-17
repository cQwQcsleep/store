package com.reandroid.graph.cleaners;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.model.DexInstruction;
import com.reandroid.graph.ApkBuildOption;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnusedFieldsCleaner extends UnusedClassComponentCleaner<DexField> {
    public UnusedFieldsCleaner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkBuildOption, apkModule, dexClassRepository);
    }

    private boolean isUnusedField(DexField dexField) {
        return isUnusedPrivateField(dexField) || isUnusedInstanceField(dexField);
    }

    private boolean isUnusedInstanceField(DexField dexField) {
        return false;
    }

    private boolean isUnusedPrivateField(DexField dexField) {
        if (!dexField.isPrivate()) {
            return false;
        }
        FieldKey key = dexField.getKey();
        String name = key.getName();
        Iterator<DexInstruction> dexInstructions = dexField.getDexClass().getDexInstructions();
        while (dexInstructions.hasNext()) {
            DexInstruction next = dexInstructions.next();
            if (key.equals(next.getKeyAsField()) || name.equals(next.getString())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.reandroid.graph.cleaners.UnusedCleaner
    public boolean isEnabled() {
        return getBuildOption().isMinifyFields();
    }

    @Override // com.reandroid.graph.cleaners.UnusedClassComponentCleaner
    public List<DexField> listUnusedInClass(DexClass dexClass) {
        Iterator<DexField> declaredFields = dexClass.getDeclaredFields();
        ArrayCollection arrayCollection = null;
        while (declaredFields.hasNext()) {
            DexField next = declaredFields.next();
            if (isUnusedField(next)) {
                if (arrayCollection == null) {
                    arrayCollection = new ArrayCollection();
                }
                arrayCollection.add(next);
            }
        }
        return arrayCollection;
    }
}
