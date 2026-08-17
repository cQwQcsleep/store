package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationElementKey implements Key {
    private final String name;
    private final Key value;

    private AnnotationElementKey(String str, Key key) {
        this.name = str;
        this.value = key;
    }

    public static AnnotationElementKey create(String str, Key key) {
        if (str == null) {
            return null;
        }
        if (key == null) {
            key = NullValueKey.INSTANCE;
        }
        return new AnnotationElementKey(str, key);
    }

    public static AnnotationElementKey read(SmaliReader smaliReader) throws IOException {
        String simpleNameIgnoreWhitespaces = smaliReader.readSimpleNameIgnoreWhitespaces();
        SmaliParseException.expect(smaliReader, '=');
        smaliReader.skipWhitespacesOrComment();
        return create(simpleNameIgnoreWhitespaces, KeyUtil.readKey(smaliReader));
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(" = ");
        getValue().append(smaliWriter);
    }

    public AnnotationElementKey changeName(String str) {
        return ObjectsUtil.equals(getName(), str) ? this : create(str, getValue());
    }

    public AnnotationElementKey changeValue(Key key) {
        return ObjectsUtil.equals(getValue(), key) ? this : create(getName(), key);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (!(obj instanceof AnnotationElementKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        AnnotationElementKey annotationElementKey = (AnnotationElementKey) obj;
        int iCompare = CompareUtil.compare(getName(), annotationElementKey.getName());
        return iCompare == 0 ? CompareUtil.compare(getValue(), annotationElementKey.getValue()) : iCompare;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotationElementKey)) {
            return false;
        }
        AnnotationElementKey annotationElementKey = (AnnotationElementKey) obj;
        return ObjectsUtil.equals(getName(), annotationElementKey.getName()) && ObjectsUtil.equals(getValue(), annotationElementKey.getValue());
    }

    public String getName() {
        return this.name;
    }

    public StringKey getNameKey() {
        return StringKey.create(getName());
    }

    public Key getValue() {
        return this.value;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getName(), getValue());
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        Key value = getValue();
        return CombiningIterator.singleThree(this, SingleIterator.of(getNameKey()), SingleIterator.of(value), value == null ? EmptyIterator.of() : value.mentionedKeys());
    }

    @Override // com.reandroid.dex.key.Key
    public AnnotationElementKey replaceKey(Key key, Key key2) {
        Key keyReplaceKey;
        if (equals(key)) {
            return (AnnotationElementKey) key2;
        }
        AnnotationElementKey annotationElementKeyChangeName = ObjectsUtil.equals(getNameKey(), key) ? changeName((StringKey) key2) : this;
        Key value = getValue();
        return (value == null || (keyReplaceKey = value.replaceKey(key, key2)) == getValue()) ? annotationElementKeyChangeName : annotationElementKeyChangeName.changeValue(keyReplaceKey);
    }

    public MethodKey toMethod(TypeKey typeKey) {
        return MethodKey.create(typeKey, getNameKey(), ProtoKey.create(KeyUtil.getReturnTypeForValue(getValue()), new TypeKey[0]));
    }

    public String toString() {
        return getName() + " = " + getValue();
    }

    public AnnotationElementKey changeName(StringKey stringKey) {
        return changeName(stringKey == null ? null : stringKey.getString());
    }
}
