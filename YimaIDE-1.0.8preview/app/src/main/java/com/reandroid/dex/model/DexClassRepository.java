package com.reandroid.dex.model;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.data.AnnotationItem;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.id.SourceFile;
import com.reandroid.dex.key.CallSiteKey;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodHandleKey;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeKeyReference;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.sections.Marker;
import com.reandroid.dex.sections.Section;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.utils.collection.UniqueIterator;
import defpackage.krd;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DexClassRepository extends FullRefresh, BlockRefresh {
    static /* synthetic */ FieldKey f(FieldKey fieldKey, DexClass dexClass) {
        FieldKey fieldKeyChangeDeclaring = fieldKey.changeDeclaring(dexClass.getKey());
        if (fieldKey.equals(dexClass.getField(fieldKeyChangeDeclaring).getKey())) {
            return fieldKeyChangeDeclaring;
        }
        return null;
    }

    default void clearDebug() {
        Iterator sections = getSections(SectionType.DEBUG_INFO);
        while (sections.hasNext()) {
            ((Section) sections.next()).removeSelf();
        }
    }

    default void clearMarkers() {
        Iterator<Marker> markers = getMarkers();
        while (markers.hasNext()) {
            markers.next().removeSelf();
        }
    }

    default void clearPoolMap() {
        Iterator<DexClassModule> itModules = modules();
        while (itModules.hasNext()) {
            itModules.next().clearPoolMap();
        }
    }

    default boolean contains(Key key) {
        if (key == null) {
            return false;
        }
        if (key instanceof StringKey) {
            return contains(SectionType.STRING_ID, key);
        }
        if (key instanceof TypeKey) {
            return contains(SectionType.TYPE_ID, key);
        }
        if (key instanceof FieldKey) {
            return contains(SectionType.FIELD_ID, key);
        }
        if (key instanceof ProtoKey) {
            return contains(SectionType.PROTO_ID, key);
        }
        if (key instanceof MethodKey) {
            return contains(SectionType.METHOD_ID, key);
        }
        if (key instanceof TypeListKey) {
            return contains(SectionType.TYPE_LIST, key);
        }
        if (key instanceof MethodHandleKey) {
            return contains(SectionType.METHOD_HANDLE, key);
        }
        if (key instanceof CallSiteKey) {
            return contains(SectionType.CALL_SITE_ID, key);
        }
        krd.a("Unknown key type: ", key.getClass(), ", '", key, "'");
        return false;
    }

    default boolean containsClass(TypeKey typeKey) {
        return contains(SectionType.CLASS_ID, typeKey);
    }

    default void edit() {
        CollectionUtil.walk(FilterIterator.of(getItems(SectionType.CODE), new Predicate() { // from class: qp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CodeItem) obj).flattenTryItems();
            }
        }));
        Iterator<DexClass> dexClasses = getDexClasses();
        while (dexClasses.hasNext()) {
            dexClasses.next().edit();
        }
    }

    default Iterator<FieldKey> findEquivalentFields(FieldKey fieldKey) {
        DexField field;
        DexClass dexClass = getDexClass(fieldKey.getDeclaring());
        if (dexClass != null && (field = dexClass.getField(fieldKey)) != null) {
            DexClass dexClass2 = field.getDexClass();
            final FieldKey key = field.getKey();
            return CombiningIterator.two(SingleIterator.of(key), ComputeIterator.of(getSuccessorClasses(dexClass2.getKey()), new Function() { // from class: wp3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return DexClassRepository.f(key, (DexClass) obj);
                }
            }));
        }
        return EmptyIterator.of();
    }

    default Iterator<MethodKey> findEquivalentMethods(MethodKey methodKey) {
        DexClass dexClass = getDexClass(methodKey.getDeclaring());
        return dexClass == null ? EmptyIterator.of() : new IterableIterator<DexMethod, MethodKey>(dexClass.getMethods(methodKey)) { // from class: com.reandroid.dex.model.DexClassRepository.13
            public Iterator<MethodKey> iterator(DexMethod dexMethod) {
                DexMethod declared = dexMethod.getDeclared();
                return CombiningIterator.two(SingleIterator.of(declared.getKey()), declared.getOverridingKeys());
            }
        };
    }

    default Iterator<DexClass> findUserClasses(final Key key) {
        return new UniqueIterator(getDexClasses(), new Predicate() { // from class: sp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DexClass) obj).uses(key);
            }
        });
    }

    default <T extends SectionItem> Iterator<T> getClonedItems(SectionType<T> sectionType) {
        return new IterableIterator<Section<T>, T>(getSections(sectionType)) { // from class: com.reandroid.dex.model.DexClassRepository.7
            public Iterator<T> iterator(Section<T> section) {
                return section.clonedIterator();
            }
        };
    }

    default <T extends SectionItem> Iterator<T> getClonedItemsIf(SectionType<T> sectionType, Predicate<? super T> predicate) {
        return FilterIterator.of(getClonedItems(sectionType), predicate);
    }

    default <T extends SectionItem> Iterator<T> getClonedItemsIfKey(SectionType<T> sectionType, final Predicate<? super Key> predicate) {
        return predicate == null ? getClonedItems(sectionType) : getClonedItemsIf(sectionType, new Predicate() { // from class: tp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((SectionItem) obj).getKey());
            }
        });
    }

    default int getCount(SectionType<?> sectionType) {
        Iterator<DexClassModule> itModules = modules();
        int count = 0;
        while (itModules.hasNext()) {
            count += itModules.next().getCount(sectionType);
        }
        return count;
    }

    default DexField getDeclaredField(FieldKey fieldKey) {
        DexClass dexClass = getDexClass(fieldKey.getDeclaring());
        if (dexClass != null) {
            return dexClass.getDeclaredField(fieldKey);
        }
        return null;
    }

    default Iterator<DexField> getDeclaredFields() {
        return new IterableIterator<DexClass, DexField>(getDexClasses()) { // from class: com.reandroid.dex.model.DexClassRepository.11
            public Iterator<DexField> iterator(DexClass dexClass) {
                return dexClass.getDeclaredFields();
            }
        };
    }

    default DexMethod getDeclaredMethod(MethodKey methodKey) {
        DexClass dexClass = getDexClass(methodKey.getDeclaring());
        if (dexClass == null) {
            return null;
        }
        DexMethod declaredMethod = dexClass.getDeclaredMethod(methodKey, false);
        return declaredMethod == null ? dexClass.getDeclaredMethod(methodKey, true) : declaredMethod;
    }

    default Iterator<DexMethod> getDeclaredMethods() {
        return new IterableIterator<DexClass, DexMethod>(getDexClasses()) { // from class: com.reandroid.dex.model.DexClassRepository.10
            public Iterator<DexMethod> iterator(DexClass dexClass) {
                return dexClass.getDeclaredMethods();
            }
        };
    }

    default DexClass getDexClass(TypeKey typeKey) {
        return searchClass(modules(), typeKey);
    }

    default Iterator<DexClass> getDexClasses(final Predicate<? super TypeKey> predicate) {
        return new IterableIterator<DexClassModule, DexClass>(modules()) { // from class: com.reandroid.dex.model.DexClassRepository.1
            public Iterator<DexClass> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getDexClasses(predicate);
            }
        };
    }

    default Iterator<DexClass> getDexClassesCloned(final Predicate<? super TypeKey> predicate) {
        return new IterableIterator<DexClassModule, DexClass>(modules()) { // from class: com.reandroid.dex.model.DexClassRepository.2
            public Iterator<DexClass> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getDexClassesCloned(predicate);
            }
        };
    }

    default int getDexClassesCount() {
        return getCount(SectionType.CLASS_ID);
    }

    default DexDeclaration getDexDeclaration(Key key) {
        if (key instanceof TypeKey) {
            return getDexClass((TypeKey) key);
        }
        if (key instanceof MethodKey) {
            return getDeclaredMethod((MethodKey) key);
        }
        if (key instanceof FieldKey) {
            return getDeclaredField((FieldKey) key);
        }
        return null;
    }

    default List<TypeKeyReference> getExternalTypeKeyReferenceList() {
        return ArrayCollection.empty();
    }

    default <T extends SectionItem> T getItem(SectionType<T> sectionType, int i) {
        Iterator<DexClassModule> itModules = modules();
        while (itModules.hasNext()) {
            T t = (T) itModules.next().getItem(sectionType, i);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    default <T extends SectionItem> Iterator<T> getItems(SectionType<T> sectionType) {
        return new IterableIterator<Section<T>, T>(getSections(sectionType)) { // from class: com.reandroid.dex.model.DexClassRepository.6
            public Iterator<T> iterator(Section<T> section) {
                return section.iterator();
            }
        };
    }

    default <T extends SectionItem> Iterator<T> getItemsIf(SectionType<T> sectionType, final Predicate<? super T> predicate) {
        return new IterableIterator<Section<T>, T>(getSections(sectionType)) { // from class: com.reandroid.dex.model.DexClassRepository.9
            public Iterator<T> iterator(Section<T> section) {
                return section.iterator(predicate);
            }
        };
    }

    default <T extends SectionItem> Iterator<T> getItemsIfKey(SectionType<T> sectionType, final Predicate<? super Key> predicate) {
        return predicate == null ? getItems(sectionType) : getItemsIf(sectionType, new Predicate() { // from class: up3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return predicate.test(((SectionItem) obj).getKey());
            }
        });
    }

    default Iterator<Marker> getMarkers() {
        return new IterableIterator<DexClassModule, Marker>(modules()) { // from class: com.reandroid.dex.model.DexClassRepository.15
            public Iterator<Marker> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getMarkers();
            }
        };
    }

    default Iterator<MethodId> getMethodIds(MethodKey methodKey) {
        return new IterableIterator<MethodKey, MethodId>(findEquivalentMethods(methodKey)) { // from class: com.reandroid.dex.model.DexClassRepository.14
            public Iterator<MethodId> iterator(MethodKey methodKey2) {
                return DexClassRepository.this.getItems(SectionType.METHOD_ID, methodKey2);
            }
        };
    }

    default Iterator<DexMethod> getMethods(MethodKey methodKey) {
        return ComputeIterator.of(findEquivalentMethods(methodKey), new Function() { // from class: rp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.getDeclaredMethod((MethodKey) obj);
            }
        });
    }

    default Iterator<DexClass> getPackageClasses(final String str, final boolean z) {
        return getDexClasses(new Predicate() { // from class: vp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TypeKey) obj).isPackage(str, z);
            }
        });
    }

    DexClassRepository getRootRepository();

    default <T extends SectionItem> Iterator<Section<T>> getSections(final SectionType<T> sectionType) {
        return new IterableIterator<DexClassModule, Section<T>>(modules()) { // from class: com.reandroid.dex.model.DexClassRepository.5
            public Iterator<Section<T>> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getSections(sectionType);
            }
        };
    }

    default Iterator<DexClass> getSuccessorClasses(final TypeKey typeKey) {
        return new IterableIterator<DexClassModule, DexClass>(modules()) { // from class: com.reandroid.dex.model.DexClassRepository.12
            public Iterator<DexClass> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getSuccessorClasses(typeKey);
            }
        };
    }

    default int getVersion() {
        Iterator<DexClassModule> itModules = modules();
        int i = 0;
        while (itModules.hasNext()) {
            int version = itModules.next().getVersion();
            if (version > i) {
                i = version;
            }
        }
        return i;
    }

    Iterator<DexClassModule> modules();

    default boolean removeAnnotations(final TypeKey typeKey) {
        return removeEntries(SectionType.ANNOTATION_ITEM, new Predicate() { // from class: xp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return typeKey.equals(((AnnotationItem) obj).getType());
            }
        });
    }

    default boolean removeClass(TypeKey typeKey) {
        return removeEntry(SectionType.CLASS_ID, typeKey);
    }

    default boolean removeClasses(Predicate<? super DexClass> predicate) {
        Iterator<DexClassModule> itModules = modules();
        boolean z = false;
        while (itModules.hasNext()) {
            if (itModules.next().removeClasses(predicate)) {
                z = true;
            }
        }
        return z;
    }

    default boolean removeClassesWithKeys(Predicate<? super TypeKey> predicate) {
        return removeEntriesWithKey(SectionType.CLASS_ID, (Predicate) ObjectsUtil.cast(predicate));
    }

    default <T1 extends SectionItem> boolean removeEntries(SectionType<T1> sectionType, Predicate<T1> predicate) {
        Iterator<DexClassModule> itModules = modules();
        boolean z = false;
        while (itModules.hasNext()) {
            if (itModules.next().removeEntries(sectionType, predicate)) {
                z = true;
            }
        }
        return z;
    }

    default <T1 extends SectionItem> boolean removeEntriesWithKey(SectionType<T1> sectionType, Predicate<? super Key> predicate) {
        Iterator<DexClassModule> itModules = modules();
        boolean z = false;
        while (itModules.hasNext()) {
            if (itModules.next().removeEntriesWithKey(sectionType, predicate)) {
                z = true;
            }
        }
        return z;
    }

    default <T1 extends SectionItem> boolean removeEntry(SectionType<T1> sectionType, Key key) {
        Iterator<DexClassModule> itModules = modules();
        boolean z = false;
        while (itModules.hasNext()) {
            if (itModules.next().removeEntry(sectionType, key)) {
                z = true;
            }
        }
        return z;
    }

    default DexClass searchClass(Iterator<DexClassModule> it, TypeKey typeKey) {
        while (it.hasNext()) {
            DexClass dexClass = it.next().getDexClass(typeKey);
            if (dexClass != null) {
                return dexClass;
            }
        }
        return null;
    }

    default Iterator<DexClass> searchExtending(final TypeKey typeKey) {
        UniqueIterator uniqueIterator = new UniqueIterator(new IterableIterator<DexClassModule, DexClass>(getRootRepository().modules()) { // from class: com.reandroid.dex.model.DexClassRepository.3
            public Iterator<DexClass> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getExtendingClasses(typeKey);
            }
        });
        uniqueIterator.exclude(getDexClass(typeKey));
        return uniqueIterator;
    }

    default Iterator<DexClass> searchImplementations(final TypeKey typeKey) {
        UniqueIterator uniqueIterator = new UniqueIterator(new IterableIterator<DexClassModule, DexClass>(getRootRepository().modules()) { // from class: com.reandroid.dex.model.DexClassRepository.4
            public Iterator<DexClass> iterator(DexClassModule dexClassModule) {
                return dexClassModule.getImplementClasses(typeKey);
            }
        });
        uniqueIterator.exclude(getDexClass(typeKey));
        return uniqueIterator;
    }

    default void setClassSourceFileAll(String str) {
        Iterator items = getItems(SectionType.CLASS_ID);
        while (items.hasNext()) {
            ((ClassId) items.next()).setSourceFile(str);
        }
    }

    default void setVersion(int i) {
        Iterator<DexClassModule> itModules = modules();
        while (itModules.hasNext()) {
            itModules.next().setVersion(i);
        }
    }

    default int shrink() {
        Iterator<DexClassModule> itModules = modules();
        int iShrink = 0;
        while (itModules.hasNext()) {
            iShrink += itModules.next().shrink();
        }
        return iShrink;
    }

    default boolean sort() {
        Iterator<DexClassModule> itModules = modules();
        boolean z = false;
        while (itModules.hasNext()) {
            if (itModules.next().sort()) {
                z = true;
            }
        }
        return z;
    }

    default Iterator<IntegerReference> visitIntegers() {
        return new DexIntegerVisitor(this);
    }

    default Iterator<DexClass> getDexClasses() {
        return getDexClasses(null);
    }

    default Iterator<DexClass> getDexClassesCloned() {
        return getDexClassesCloned(null);
    }

    default <T extends SectionItem> Iterator<T> getItems(SectionType<T> sectionType, final Key key) {
        return new IterableIterator<Section<T>, T>(getSections(sectionType)) { // from class: com.reandroid.dex.model.DexClassRepository.8
            public Iterator<T> iterator(Section<T> section) {
                return section.getAll(key);
            }
        };
    }

    default Iterator<DexClass> getPackageClasses(String str) {
        return getPackageClasses(str, true);
    }

    default DexClass searchClass(TypeKey typeKey) {
        return searchClass(getRootRepository().modules(), typeKey);
    }

    default void setClassSourceFileAll() {
        setClassSourceFileAll(SourceFile.SourceFile);
    }

    default <T extends SectionItem> T getItem(SectionType<T> sectionType, Key key) {
        Iterator<DexClassModule> itModules = modules();
        while (itModules.hasNext()) {
            T t = (T) itModules.next().getItem(sectionType, key);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    default DexMethod getDeclaredMethod(MethodKey methodKey, boolean z) {
        DexClass dexClass = getDexClass(methodKey.getDeclaring());
        if (dexClass != null) {
            return dexClass.getDeclaredMethod(methodKey, z);
        }
        return null;
    }

    default boolean contains(SectionType<?> sectionType, Key key) {
        return getItems(sectionType, key).hasNext();
    }
}
