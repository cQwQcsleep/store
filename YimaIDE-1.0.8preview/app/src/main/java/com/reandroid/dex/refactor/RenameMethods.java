package com.reandroid.dex.refactor;

import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RenameMethods extends Rename<MethodKey, MethodKey> {
    private int applyToAnnotations(DexClassRepository dexClassRepository, List<KeyPair<MethodKey, MethodKey>> list) {
        Map<TypeKey, Set<KeyPair<MethodKey, MethodKey>>> mapMapDeclaring = mapDeclaring(list);
        Iterator items = dexClassRepository.getItems(SectionType.ANNOTATION_ITEM);
        int iRenameAnnotation = 0;
        while (items.hasNext()) {
            iRenameAnnotation += renameAnnotation(mapMapDeclaring, (AnnotationItem) items.next());
        }
        return iRenameAnnotation;
    }

    private int applyToMethodIds(DexClassRepository dexClassRepository, List<KeyPair<MethodKey, MethodKey>> list) {
        int i = 0;
        for (KeyPair<MethodKey, MethodKey> keyPair : list) {
            MethodKey methodKey = (MethodKey) keyPair.getFirst();
            MethodKey methodKey2 = (MethodKey) keyPair.getSecond();
            Iterator items = dexClassRepository.getItems(SectionType.METHOD_ID, methodKey);
            while (items.hasNext()) {
                ((MethodId) items.next()).setKey(methodKey2);
                i++;
            }
        }
        return i;
    }

    private Map<TypeKey, Set<KeyPair<MethodKey, MethodKey>>> mapDeclaring(List<KeyPair<MethodKey, MethodKey>> list) {
        HashMap map = new HashMap();
        for (KeyPair<MethodKey, MethodKey> keyPair : list) {
            TypeKey declaring = ((MethodKey) keyPair.getFirst()).getDeclaring();
            Set hashSet = (Set) map.get(declaring);
            if (hashSet == null) {
                hashSet = new HashSet();
                hashSet.add(keyPair);
                map.put(declaring, hashSet);
            } else {
                hashSet.add(keyPair);
            }
            hashSet.add(keyPair);
        }
        return map;
    }

    private int renameAnnotation(Map<TypeKey, Set<KeyPair<MethodKey, MethodKey>>> map, AnnotationItem annotationItem) {
        Set<KeyPair<MethodKey, MethodKey>> set = map.get(annotationItem.getType());
        int i = 0;
        if (set == null) {
            return 0;
        }
        for (KeyPair<MethodKey, MethodKey> keyPair : set) {
            MethodKey methodKey = (MethodKey) keyPair.getFirst();
            MethodKey methodKey2 = (MethodKey) keyPair.getSecond();
            AnnotationItemKey key = annotationItem.getKey();
            AnnotationItemKey annotationItemKeyReplaceKey = key.replaceKey((Key) methodKey, (Key) methodKey2);
            if (key != annotationItemKeyReplaceKey) {
                annotationItem.setKey(annotationItemKeyReplaceKey);
                i++;
            }
        }
        return i;
    }

    public void add(DexClassRepository dexClassRepository, MethodKey methodKey, MethodKey methodKey2) {
        KeyPair keyPair = new KeyPair(methodKey, methodKey2);
        if (!keyPair.isValid() || isLocked(keyPair)) {
            return;
        }
        if (containsDeclaration(dexClassRepository, methodKey2)) {
            lock(keyPair);
            return;
        }
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.add(keyPair);
        Iterator<MethodKey> itFindEquivalentMethods = dexClassRepository.findEquivalentMethods(methodKey);
        while (itFindEquivalentMethods.hasNext()) {
            MethodKey next = itFindEquivalentMethods.next();
            KeyPair keyPair2 = new KeyPair(next, next.changeName(methodKey2.getName()));
            if (isLocked(keyPair2)) {
                lockAll(arrayCollection);
                arrayCollection.clear();
                break;
            }
            arrayCollection.add(keyPair2);
        }
        addAll(arrayCollection);
    }

    @Override // com.reandroid.dex.refactor.Rename
    public int apply(DexClassRepository dexClassRepository) {
        List<KeyPair<MethodKey, MethodKey>> list = toList();
        return applyToMethodIds(dexClassRepository, list) + applyToAnnotations(dexClassRepository, list);
    }

    @Override // com.reandroid.dex.refactor.Rename
    public boolean containsDeclaration(DexClassRepository dexClassRepository, MethodKey methodKey) {
        return dexClassRepository.getDeclaredMethod(methodKey, false) != null;
    }

    public void add(DexClassRepository dexClassRepository, MethodKey methodKey, String str) {
        add(dexClassRepository, methodKey, methodKey.changeName(str));
    }
}
