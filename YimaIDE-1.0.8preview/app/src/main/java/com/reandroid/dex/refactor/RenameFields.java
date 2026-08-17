package com.reandroid.dex.refactor;

import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RenameFields extends Rename<FieldKey, FieldKey> {
    private int applyToFieldIds(DexClassRepository dexClassRepository, List<KeyPair<FieldKey, FieldKey>> list) {
        int i = 0;
        for (KeyPair<FieldKey, FieldKey> keyPair : list) {
            FieldKey fieldKey = (FieldKey) keyPair.getFirst();
            FieldKey fieldKey2 = (FieldKey) keyPair.getSecond();
            Iterator items = dexClassRepository.getItems(SectionType.FIELD_ID, fieldKey);
            while (items.hasNext()) {
                ((FieldId) items.next()).setKey(fieldKey2);
                i++;
            }
        }
        return i;
    }

    public void add(DexClassRepository dexClassRepository, FieldKey fieldKey, FieldKey fieldKey2) {
        KeyPair keyPair = new KeyPair(fieldKey, fieldKey2);
        if (keyPair.isValid() && !isLocked(keyPair) && fieldKey.getType().equals(fieldKey2.getType())) {
            if (containsDeclaration(dexClassRepository, fieldKey2)) {
                lock(keyPair);
                return;
            }
            ArrayCollection arrayCollection = new ArrayCollection();
            arrayCollection.add(keyPair);
            Iterator<FieldKey> itFindEquivalentFields = dexClassRepository.findEquivalentFields(fieldKey);
            while (itFindEquivalentFields.hasNext()) {
                FieldKey next = itFindEquivalentFields.next();
                KeyPair keyPair2 = new KeyPair(next, next.changeName(fieldKey2.getName()));
                if (isLocked(keyPair2)) {
                    lockAll(arrayCollection);
                    arrayCollection.clear();
                    break;
                }
                arrayCollection.add(keyPair2);
            }
            addAll(arrayCollection);
        }
    }

    @Override // com.reandroid.dex.refactor.Rename
    public int apply(DexClassRepository dexClassRepository) {
        return applyToFieldIds(dexClassRepository, toList());
    }

    @Override // com.reandroid.dex.refactor.Rename
    public boolean containsDeclaration(DexClassRepository dexClassRepository, FieldKey fieldKey) {
        return dexClassRepository.getDeclaredField(fieldKey) != null;
    }

    public void add(DexClassRepository dexClassRepository, FieldKey fieldKey, String str) {
        add(dexClassRepository, fieldKey, fieldKey.changeName(str));
    }
}
