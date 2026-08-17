package com.reandroid.dex.key;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationItemKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationSetKey extends KeyList<AnnotationItemKey> {
    private static final AnnotationSetKey EMPTY = new AnnotationSetKey(KeyList.EMPTY_ARRAY);

    private AnnotationSetKey(Key[] keyArr) {
        super(keyArr, true);
    }

    private AnnotationSetKey addUnchecked(AnnotationItemKey annotationItemKey) {
        return (AnnotationSetKey) super.add(annotationItemKey);
    }

    public static AnnotationSetKey combined(Iterator<AnnotationSetKey> it) {
        ArrayCollection arrayCollection = null;
        while (it.hasNext()) {
            AnnotationSetKey next = it.next();
            if (!next.isEmpty()) {
                if (arrayCollection == null) {
                    if (!it.hasNext()) {
                        return next;
                    }
                    arrayCollection = new ArrayCollection();
                }
                arrayCollection.addAll(next.iterator());
            }
        }
        return arrayCollection == null ? empty() : createKey((Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]));
    }

    public static AnnotationSetKey create(Iterator<AnnotationItemKey> it) {
        ArrayCollection arrayCollection = null;
        while (it.hasNext()) {
            AnnotationItemKey next = it.next();
            if (arrayCollection == null) {
                arrayCollection = new ArrayCollection();
            }
            arrayCollection.add(next);
        }
        return arrayCollection == null ? empty() : createKey((Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]));
    }

    private static AnnotationSetKey createKey(Key[] keyArr) {
        if (keyArr == null || keyArr.length == 0) {
            return empty();
        }
        Key[] keyArrRemoveNulls = KeyList.removeNulls(keyArr);
        return keyArrRemoveNulls.length == 0 ? empty() : new AnnotationSetKey(keyArrRemoveNulls);
    }

    public static AnnotationSetKey empty() {
        return EMPTY;
    }

    public static AnnotationSetKey of(AnnotationItemKey... annotationItemKeyArr) {
        return createKey(annotationItemKeyArr);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey add(AnnotationItemKey annotationItemKey) {
        return annotationItemKey == null ? this : remove(annotationItemKey.getType()).addUnchecked(annotationItemKey).sorted();
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        for (int i = 0; i < size; i++) {
            smaliWriter.newLine();
            get(i).append(smaliWriter);
        }
    }

    public void appendClass(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        if (size == 0) {
            return;
        }
        smaliWriter.newLine();
        smaliWriter.newLine();
        smaliWriter.appendCommentNewLine("annotations");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                smaliWriter.newLine();
            }
            smaliWriter.newLine();
            get(i).append(smaliWriter);
        }
    }

    public AnnotationSetKey changeType(TypeKey typeKey, TypeKey typeKey2) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        return annotationItemKey == null ? this : set(indexOf(annotationItemKey), annotationItemKey.changeType(typeKey2)).sorted();
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey clearDuplicates() {
        return (AnnotationSetKey) super.clearDuplicates();
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj != this && (obj instanceof AnnotationSetKey)) {
            return compareElements((AnnotationSetKey) obj);
        }
        return 0;
    }

    public boolean contains(TypeKey typeKey) {
        int size = size();
        for (int i = 0; i < size; i++) {
            AnnotationItemKey annotationItemKey = get(i);
            if (annotationItemKey != null && ObjectsUtil.equals(typeKey, annotationItemKey.getType())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsElement(TypeKey typeKey, String str) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        if (annotationItemKey != null) {
            return annotationItemKey.containsElement(str);
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AnnotationSetKey) {
            return equalsElements((AnnotationSetKey) obj);
        }
        return false;
    }

    public AnnotationItemKey get(TypeKey typeKey) {
        int size = size();
        for (int i = 0; i < size; i++) {
            AnnotationItemKey annotationItemKey = get(i);
            if (annotationItemKey != null && ObjectsUtil.equals(typeKey, annotationItemKey.getType())) {
                return annotationItemKey;
            }
        }
        return null;
    }

    public Key getAnnotationValue(TypeKey typeKey, String str) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        if (annotationItemKey != null) {
            return annotationItemKey.get(str);
        }
        return null;
    }

    public AnnotationSetKey getOrCreate(TypeKey typeKey) {
        return get(typeKey) != null ? this : add(AnnotationItemKey.create(AnnotationVisibility.BUILD, typeKey, new AnnotationElementKey[0])).sorted();
    }

    public Iterator<TypeKey> getTypes() {
        return ComputeIterator.of(iterator(), new Function() { // from class: e90
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AnnotationItemKey) obj).getType();
            }
        });
    }

    @Override // com.reandroid.dex.key.KeyList
    public int hashCode() {
        return getHashCode();
    }

    public AnnotationSetKey remove(final TypeKey typeKey) {
        return removeIf(new Predicate() { // from class: f90
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AnnotationItemKey) obj).equalsType(typeKey);
            }
        });
    }

    public AnnotationSetKey removeElement(TypeKey typeKey, String str) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        return annotationItemKey == null ? this : set(indexOf(annotationItemKey), annotationItemKey.remove(str));
    }

    public AnnotationSetKey removeElementIf(TypeKey typeKey, Predicate<? super AnnotationElementKey> predicate) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        return annotationItemKey == null ? this : set(indexOf(annotationItemKey), annotationItemKey.removeIf(predicate));
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey removeIf(Predicate<? super AnnotationItemKey> predicate) {
        return (AnnotationSetKey) super.removeIf((Predicate) predicate);
    }

    public AnnotationSetKey renameElement(TypeKey typeKey, String str, String str2) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        return annotationItemKey == null ? this : set(indexOf(annotationItemKey), annotationItemKey.rename(str, str2));
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public AnnotationSetKey replaceKey(Key key, Key key2) {
        return (AnnotationSetKey) super.replaceKey(key, key2);
    }

    public AnnotationSetKey setAnnotation(TypeKey typeKey, String str, Key key) {
        AnnotationSetKey orCreate = getOrCreate(typeKey);
        AnnotationItemKey annotationItemKey = orCreate.get(typeKey);
        return orCreate.set(orCreate.indexOf(annotationItemKey), annotationItemKey.setValue(str, key));
    }

    public AnnotationSetKey setVisibility(TypeKey typeKey, AnnotationVisibility annotationVisibility) {
        AnnotationItemKey annotationItemKey = get(typeKey);
        return annotationItemKey == null ? this : set(indexOf(annotationItemKey), annotationItemKey.changeVisibility(annotationVisibility));
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey sorted() {
        return (AnnotationSetKey) super.sorted();
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey newInstance(Key[] keyArr) {
        return createKey(keyArr);
    }

    @Override // com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList clearDuplicates(Comparator comparator) {
        return clearDuplicates((Comparator<? super AnnotationItemKey>) comparator);
    }

    @Override // com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList removeIf(Predicate predicate) {
        return removeIf((Predicate<? super AnnotationItemKey>) predicate);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey set(int i, AnnotationItemKey annotationItemKey) {
        return (AnnotationSetKey) super.set(i, annotationItemKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey clearDuplicates(Comparator<? super AnnotationItemKey> comparator) {
        return (AnnotationSetKey) super.clearDuplicates((Comparator) comparator);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey remove(AnnotationItemKey annotationItemKey) {
        return (AnnotationSetKey) super.remove(annotationItemKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationSetKey remove(int i) {
        return (AnnotationSetKey) super.remove(i);
    }

    public AnnotationSetKey getOrCreate(TypeKey typeKey, String str) {
        AnnotationSetKey orCreate = getOrCreate(typeKey);
        AnnotationItemKey annotationItemKey = orCreate.get(typeKey);
        return orCreate.set(orCreate.indexOf(annotationItemKey), annotationItemKey.getOrCreate(str));
    }
}
