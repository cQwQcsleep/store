package com.reandroid.dex.refactor;

import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.dalvik.DalvikSignature;
import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.DalvikSignatureKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeKeyReference;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import defpackage.aca;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RenameTypes extends Rename<TypeKey, TypeKey> {
    public static final int DEFAULT_ARRAY_DEPTH = ObjectsUtil.of(3);
    private final Set<String> renamedStrings = new HashSet();
    private final Map<String, String> stringMap = new HashMap();
    private int arrayDepth = DEFAULT_ARRAY_DEPTH;
    private boolean renameSourceClassName = true;
    private boolean skipSourceRenameRootPackageClass = true;
    private boolean fixAccessibility = true;
    private boolean fixInnerSimpleName = true;
    private boolean fixSourceFileName = false;
    private boolean renameInnerClasses = false;
    private boolean mChanged = true;

    private void applyFix(DexClassRepository dexClassRepository) {
        final Set<String> set = this.renamedStrings;
        if (set.isEmpty()) {
            return;
        }
        boolean z = this.fixAccessibility;
        boolean z2 = this.fixInnerSimpleName;
        boolean z3 = this.fixSourceFileName;
        if (z || z2 || z3) {
            Iterator<DexClass> dexClasses = dexClassRepository.getDexClasses(new Predicate() { // from class: zcc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return set.contains(((TypeKey) obj).getTypeName());
                }
            });
            while (dexClasses.hasNext()) {
                DexClass next = dexClasses.next();
                if (z) {
                    next.fixAccessibility();
                }
                if (z2) {
                    next.fixDalvikInnerClassName();
                }
                if (z3) {
                    TypeKey key = next.getKey();
                    if (isSimpleNameChanged(key)) {
                        next.setSourceFile(DexUtils.toSourceFileName(key.getTypeName()));
                    }
                }
            }
        }
    }

    private void buildRenameMap() {
        if (this.mChanged) {
            this.mChanged = false;
            List<KeyPair<TypeKey, TypeKey>> list = toList();
            boolean z = this.renameSourceClassName;
            boolean z2 = this.skipSourceRenameRootPackageClass;
            int size = list.size();
            Map<String, String> map = this.stringMap;
            int i = this.arrayDepth + 1;
            for (int i2 = 0; i2 < size; i2++) {
                KeyPair<TypeKey, TypeKey> keyPair = list.get(i2);
                TypeKey typeKey = (TypeKey) keyPair.getFirst();
                TypeKey typeKey2 = (TypeKey) keyPair.getSecond();
                map.put(typeKey.getTypeName(), typeKey2.getTypeName());
                for (int i3 = 1; i3 < i; i3++) {
                    map.put(typeKey.getArrayType(i3), typeKey2.getArrayType(i3));
                }
                if (z) {
                    String typeName = typeKey.getTypeName();
                    if (!z2 || typeName.indexOf(47) > 0) {
                        map.put(typeKey.getSourceName(), typeKey2.getSourceName());
                    }
                }
            }
        }
    }

    private boolean isSimpleNameChanged(TypeKey typeKey) {
        KeyPair<TypeKey, TypeKey> flipped = getFlipped(typeKey);
        if (flipped == null) {
            return false;
        }
        return !((TypeKey) flipped.getFirst()).getSimpleName().equals(((TypeKey) flipped.getSecond()).getSimpleName());
    }

    private void renameAnnotationSignatures(DexClassRepository dexClassRepository) {
        DalvikSignatureKey dalvikSignatureKey;
        Iterator itOf = ComputeIterator.of(dexClassRepository.getItems(SectionType.ANNOTATION_ITEM), new Function() { // from class: xcc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DalvikSignature.of(((AnnotationItem) obj).asAnnotated());
            }
        });
        while (itOf.hasNext()) {
            DalvikSignature dalvikSignature = (DalvikSignature) itOf.next();
            DalvikSignatureKey signature = dalvikSignature.getSignature();
            if (signature != null && signature != (dalvikSignatureKey = (DalvikSignatureKey) replaceInKey(signature))) {
                dalvikSignature.setSignature(dalvikSignatureKey);
            }
        }
    }

    private void renameExternalTypeKeyReference(TypeKeyReference typeKeyReference) {
        TypeKey typeKey = typeKeyReference.getTypeKey();
        if (typeKey == null) {
            return;
        }
        Map<String, String> map = this.stringMap;
        String str = map.get(typeKey.getTypeName());
        if (str == null) {
            str = map.get(typeKey.getSourceName());
        }
        TypeKey typeKey2 = TypeKey.parse(str);
        if (typeKey2 != null) {
            typeKeyReference.setTypeKey(typeKey2);
            this.renamedStrings.add(str);
        }
    }

    private void renameExternalTypeKeyReferences(DexClassRepository dexClassRepository) {
        Iterator<TypeKeyReference> it = dexClassRepository.getExternalTypeKeyReferenceList().iterator();
        while (it.hasNext()) {
            renameExternalTypeKeyReference(it.next());
        }
    }

    private void renameStringIds(DexClassRepository dexClassRepository) {
        Map<String, String> map = this.stringMap;
        if (map.isEmpty()) {
            return;
        }
        Iterator clonedItems = dexClassRepository.getClonedItems(SectionType.STRING_ID);
        while (clonedItems.hasNext()) {
            StringId stringId = (StringId) clonedItems.next();
            String str = map.get(stringId.getString());
            if (str != null) {
                setString(stringId, str);
            }
        }
    }

    private void setString(StringId stringId, String str) {
        stringId.setString(str);
        this.renamedStrings.add(str);
    }

    private boolean validateAndAdd(DexClassRepository dexClassRepository, KeyPair<TypeKey, TypeKey> keyPair) {
        if (keyPair == null || !keyPair.isValid()) {
            return false;
        }
        KeyPair<TypeKey, TypeKey> keyPair2 = get(keyPair.getFirst());
        if (keyPair2 != null && keyPair2.equalsBoth(keyPair)) {
            return true;
        }
        if (dexClassRepository.containsClass((TypeKey) keyPair.getSecond())) {
            lock(keyPair);
            return false;
        }
        add(keyPair);
        return true;
    }

    private void validatePackageName(String str) {
        if (StringsUtil.isEmpty(str)) {
            aca.a("Empty package name: ", str);
            return;
        }
        if (str.charAt(0) != 'L') {
            w01.a("Package name should start with 'L': ".concat(str));
            return;
        }
        int length = str.length() - 1;
        if (length == 0 || str.charAt(length) == '/') {
            return;
        }
        w01.a("Non root package name should end with '/': ".concat(str));
    }

    public void add(DexClassRepository dexClassRepository, KeyPair<TypeKey, TypeKey> keyPair) {
        if (validateAndAdd(dexClassRepository, keyPair) && this.renameInnerClasses) {
            final TypeKey typeKey = (TypeKey) keyPair.getFirst();
            TypeKey typeKey2 = (TypeKey) keyPair.getSecond();
            Iterator itemsIfKey = dexClassRepository.getItemsIfKey(SectionType.TYPE_ID, new Predicate() { // from class: adc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return typeKey.isOuterOf(((Key) obj).getDeclaring());
                }
            });
            String strReplace = typeKey2.getTypeName().replace(';', '$');
            int length = typeKey.getTypeName().length();
            while (itemsIfKey.hasNext()) {
                TypeKey declaring = ((TypeId) itemsIfKey.next()).getKey().getDeclaring();
                validateAndAdd(dexClassRepository, new KeyPair<>(declaring, TypeKey.create(strReplace + declaring.getTypeName().substring(length))));
            }
        }
    }

    public void addPackage(DexClassRepository dexClassRepository, final String str, String str2, final boolean z) {
        if (ObjectsUtil.equals(str, str2)) {
            return;
        }
        validatePackageName(str);
        validatePackageName(str2);
        Iterator itemsIfKey = dexClassRepository.getItemsIfKey(SectionType.TYPE_ID, new Predicate() { // from class: ycc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TypeKey) ((Key) obj)).isPackage(str, z);
            }
        });
        ArrayCollection arrayCollection = new ArrayCollection();
        while (itemsIfKey.hasNext()) {
            TypeKey key = ((TypeId) itemsIfKey.next()).getKey();
            TypeKey typeKeyRenamePackage = key.renamePackage(str, str2);
            arrayCollection.add(new KeyPair(key, typeKeyRenamePackage));
            if (dexClassRepository.containsClass(typeKeyRenamePackage)) {
                lockAll(arrayCollection);
                arrayCollection.clear();
                break;
            }
        }
        addAll(arrayCollection);
    }

    @Override // com.reandroid.dex.refactor.Rename
    public int apply(DexClassRepository dexClassRepository) {
        this.renamedStrings.clear();
        buildRenameMap();
        if (this.stringMap.isEmpty()) {
            return 0;
        }
        renameStringIds(dexClassRepository);
        renameAnnotationSignatures(dexClassRepository);
        renameExternalTypeKeyReferences(dexClassRepository);
        int size = this.renamedStrings.size();
        if (size != 0) {
            dexClassRepository.clearPoolMap();
        }
        applyFix(dexClassRepository);
        return size;
    }

    @Override // com.reandroid.dex.refactor.Rename
    public void close() {
        super.close();
        this.stringMap.clear();
        this.renamedStrings.clear();
        this.mChanged = true;
    }

    @Override // com.reandroid.dex.refactor.Rename
    public TypeKey getReplace(Key key) {
        String str;
        if (!(key instanceof TypeKey)) {
            return null;
        }
        TypeKey typeKey = (TypeKey) super.getReplace(key);
        return (typeKey != null || (str = this.stringMap.get(key.toString())) == null) ? typeKey : TypeKey.create(str);
    }

    @Override // com.reandroid.dex.refactor.Rename
    public void onChanged() {
        super.onChanged();
        this.mChanged = true;
    }

    public void setArrayDepth(int i) {
        if (i < 0) {
            i = DEFAULT_ARRAY_DEPTH;
        }
        this.arrayDepth = i;
    }

    public void setFixAccessibility(boolean z) {
        this.fixAccessibility = z;
    }

    public void setFixInnerSimpleName(boolean z) {
        this.fixInnerSimpleName = z;
    }

    public void setFixSourceFileName(boolean z) {
        this.fixSourceFileName = z;
    }

    public void setRenameInnerClasses(boolean z) {
        this.renameInnerClasses = z;
    }

    public void setRenameSourceClassName(boolean z) {
        this.renameSourceClassName = z;
    }

    public void setSkipSourceRenameRootPackageClass(boolean z) {
        this.skipSourceRenameRootPackageClass = z;
    }

    @Override // com.reandroid.dex.refactor.Rename
    public List<KeyPair<TypeKey, TypeKey>> toList() {
        return super.toList(CompareUtil.getInverseComparator());
    }

    public void add(DexClassRepository dexClassRepository, TypeKey typeKey, TypeKey typeKey2) {
        add(dexClassRepository, new KeyPair<>(typeKey, typeKey2));
    }
}
