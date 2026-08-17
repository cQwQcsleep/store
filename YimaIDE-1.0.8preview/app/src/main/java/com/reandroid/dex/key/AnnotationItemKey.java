package com.reandroid.dex.key;

import com.reandroid.dex.common.AnnotationVisibility;
import com.reandroid.dex.key.AnnotationElementKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsStore;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import defpackage.l78;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationItemKey extends KeyList<AnnotationElementKey> implements Key, Iterable<AnnotationElementKey> {
    private final TypeKey type;
    private final AnnotationVisibility visibility;

    private AnnotationItemKey(AnnotationVisibility annotationVisibility, TypeKey typeKey, Key[] keyArr) {
        super(keyArr, true);
        this.visibility = annotationVisibility;
        this.type = typeKey;
    }

    private AnnotationItemKey addUnchecked(AnnotationElementKey annotationElementKey) {
        return (AnnotationItemKey) super.add(annotationElementKey);
    }

    private static boolean containsElementName(Object obj, String str) {
        int size = ObjectsStore.size(obj);
        for (int i = 0; i < size; i++) {
            if (str.equals(((AnnotationElementKey) ObjectsStore.get(obj, i)).getName())) {
                return true;
            }
        }
        return false;
    }

    public static AnnotationItemKey create(AnnotationVisibility annotationVisibility, TypeKey typeKey, AnnotationElementKey... annotationElementKeyArr) {
        return createKey(annotationVisibility, typeKey, annotationElementKeyArr);
    }

    private static AnnotationItemKey createKey(AnnotationVisibility annotationVisibility, TypeKey typeKey, Key[] keyArr) {
        if (typeKey == null) {
            return null;
        }
        return new AnnotationItemKey(annotationVisibility, typeKey, KeyList.removeNulls(keyArr));
    }

    public static AnnotationItemKey parse(String str) {
        throw new RuntimeException("AnnotationItemKey.parse not implemented");
    }

    public static AnnotationItemKey read(SmaliReader smaliReader) throws IOException {
        AnnotationVisibility annotationVisibility;
        smaliReader.skipWhitespacesOrComment();
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader);
        SmaliDirective smaliDirective2 = SmaliDirective.ANNOTATION;
        Key[] keyArr = null;
        if (smaliDirective != smaliDirective2 && smaliDirective != SmaliDirective.SUB_ANNOTATION) {
            l78.a("Expecting annotation directive", smaliReader);
            return null;
        }
        if (smaliDirective == smaliDirective2) {
            annotationVisibility = AnnotationVisibility.parse(smaliReader);
            if (annotationVisibility == null) {
                l78.a("Unrecognized annotation visibility", smaliReader);
                return null;
            }
        } else {
            annotationVisibility = null;
        }
        smaliReader.skipWhitespacesOrComment();
        TypeKey typeKey = TypeKey.read(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        Object objAdd = null;
        while (!smaliDirective.isEnd(smaliReader)) {
            int iPosition = smaliReader.position();
            AnnotationElementKey annotationElementKey = AnnotationElementKey.read(smaliReader);
            if (containsElementName(objAdd, annotationElementKey.getName())) {
                smaliReader.position(iPosition);
                throw new SmaliParseException("Duplicate element name: " + annotationElementKey.getName(), smaliReader);
            }
            objAdd = ObjectsStore.add(objAdd, annotationElementKey);
            smaliReader.skipWhitespacesOrComment();
        }
        SmaliParseException.expect(smaliReader, smaliDirective, true);
        if (objAdd != null) {
            keyArr = new Key[ObjectsStore.size(objAdd)];
            ObjectsStore.collect(objAdd, keyArr);
        }
        return createKey(annotationVisibility, typeKey, keyArr);
    }

    private AnnotationItemKey replaceMethodKeys(MethodKey methodKey, MethodKey methodKey2) {
        AnnotationElementKey annotationElementKey;
        TypeKey type = getType();
        if (!type.equals(methodKey.getDeclaring())) {
            return this;
        }
        TypeKey declaring = methodKey2.getDeclaring();
        AnnotationItemKey annotationItemKeyChangeType = !type.equals(declaring) ? changeType(declaring) : this;
        String name = methodKey.getName();
        String name2 = methodKey2.getName();
        return (name.equals(name2) || containsElement(name2) || (annotationElementKey = annotationItemKeyChangeType.get(name)) == null) ? annotationItemKeyChangeType : annotationItemKeyChangeType.set(indexOf(annotationElementKey), annotationElementKey.changeName(name2));
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey add(AnnotationElementKey annotationElementKey) {
        return annotationElementKey == null ? this : remove(annotationElementKey.getName()).addUnchecked(annotationElementKey).sorted();
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendOptional(getVisibility());
        getType().append(smaliWriter);
        smaliWriter.indentPlus();
        smaliWriter.appendAllWithDoubleNewLine(iterator());
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public AnnotationItemKey changeType(TypeKey typeKey) {
        return typeKey.equals(getType()) ? this : createKey(getVisibility(), typeKey, getElements());
    }

    public AnnotationItemKey changeVisibility(AnnotationVisibility annotationVisibility) {
        return ObjectsUtil.equals(getVisibility(), annotationVisibility) ? this : createKey(annotationVisibility, getType(), getElements());
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey clearDuplicates() {
        return (AnnotationItemKey) super.clearDuplicates();
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj instanceof AnnotationItemKey) {
            return CompareUtil.compare(getType(), ((AnnotationItemKey) obj).getType());
        }
        return -1;
    }

    @Override // com.reandroid.dex.key.KeyList
    public int computeHash() {
        return (ObjectsUtil.hash(getVisibility(), getType()) * 31) + super.computeHash();
    }

    public boolean containsElement(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            AnnotationElementKey annotationElementKey = get(i);
            if (annotationElementKey != null && ObjectsUtil.equals(str, annotationElementKey.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotationItemKey)) {
            return false;
        }
        AnnotationItemKey annotationItemKey = (AnnotationItemKey) obj;
        return hashCode() == annotationItemKey.hashCode() && ObjectsUtil.equals(getVisibility(), annotationItemKey.getVisibility()) && ObjectsUtil.equals(getType(), annotationItemKey.getType()) && equalsElements(annotationItemKey);
    }

    public boolean equalsMethod(MethodKey methodKey) {
        return methodKey != null && ObjectsUtil.equals(getType(), methodKey.getDeclaring()) && containsElement(methodKey.getName());
    }

    public boolean equalsType(TypeKey typeKey) {
        return ObjectsUtil.equals(getType(), typeKey);
    }

    public AnnotationElementKey get(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            AnnotationElementKey annotationElementKey = get(i);
            if (annotationElementKey != null && ObjectsUtil.equals(str, annotationElementKey.getName())) {
                return annotationElementKey;
            }
        }
        return null;
    }

    public Iterator<MethodKey> getMethods() {
        final TypeKey type = getType();
        return ComputeIterator.of(iterator(), new Function() { // from class: x80
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((AnnotationElementKey) obj).toMethod(type);
            }
        });
    }

    public AnnotationItemKey getOrCreate(String str) {
        return get(str) != null ? this : add(AnnotationElementKey.create(str, null)).sorted();
    }

    public SmaliDirective getSmaliDirective() {
        return hasVisibility() ? SmaliDirective.ANNOTATION : SmaliDirective.SUB_ANNOTATION;
    }

    public TypeKey getType() {
        return this.type;
    }

    public Key getValue(String str) {
        AnnotationElementKey annotationElementKey = get(str);
        if (annotationElementKey != null) {
            return annotationElementKey.getValue();
        }
        return null;
    }

    public AnnotationVisibility getVisibility() {
        return this.visibility;
    }

    public boolean hasVisibility() {
        return getVisibility() != null;
    }

    @Override // com.reandroid.dex.key.KeyList
    public int hashCode() {
        return getHashCode();
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        return CombiningIterator.singleTwo(getType(), super.mentionedKeys(), getMethods());
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey newInstance(Key[] keyArr) {
        return createKey(getVisibility(), getType(), keyArr);
    }

    public AnnotationItemKey remove(final String str) {
        return removeIf(new Predicate() { // from class: w80
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ObjectsUtil.equals(((AnnotationElementKey) obj).getName(), str);
            }
        });
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey removeIf(Predicate<? super AnnotationElementKey> predicate) {
        return (AnnotationItemKey) super.removeIf((Predicate) predicate);
    }

    public AnnotationItemKey rename(String str, String str2) {
        AnnotationElementKey annotationElementKey = get(str);
        return annotationElementKey == null ? this : set(indexOf(annotationElementKey), annotationElementKey.changeName(str2)).sorted();
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public AnnotationItemKey replaceKey(Key key, Key key2) {
        if (equals(key)) {
            return (AnnotationItemKey) key2;
        }
        if (key instanceof MethodKey) {
            this = replaceMethodKeys((MethodKey) key, (MethodKey) key2);
        } else if (key.equals(getType())) {
            this = changeType((TypeKey) key2);
        }
        return (AnnotationItemKey) this.replaceElements(key, key2);
    }

    public AnnotationItemKey setValue(String str, Key key) {
        AnnotationItemKey orCreate = getOrCreate(str);
        AnnotationElementKey annotationElementKey = orCreate.get(str);
        return orCreate.set(orCreate.indexOf(annotationElementKey), annotationElementKey.changeValue(key));
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey sorted() {
        return (AnnotationItemKey) super.sorted();
    }

    @Override // com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList clearDuplicates(Comparator comparator) {
        return clearDuplicates((Comparator<? super AnnotationElementKey>) comparator);
    }

    @Override // com.reandroid.dex.key.KeyList
    public /* bridge */ /* synthetic */ KeyList removeIf(Predicate predicate) {
        return removeIf((Predicate<? super AnnotationElementKey>) predicate);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey set(int i, AnnotationElementKey annotationElementKey) {
        return (AnnotationItemKey) super.set(i, annotationElementKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey clearDuplicates(Comparator<? super AnnotationElementKey> comparator) {
        return (AnnotationItemKey) super.clearDuplicates((Comparator) comparator);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey remove(AnnotationElementKey annotationElementKey) {
        return (AnnotationItemKey) super.remove(annotationElementKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public AnnotationItemKey remove(int i) {
        return (AnnotationItemKey) super.remove(i);
    }

    public AnnotationItemKey add(String str, Key key) {
        return add(AnnotationElementKey.create(str, key));
    }
}
