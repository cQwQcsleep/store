package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FieldKey implements ProgramKey {
    private final TypeKey declaring;
    private final StringKey name;
    private final TypeKey type;

    public FieldKey(TypeKey typeKey, StringKey stringKey, TypeKey typeKey2) {
        this.declaring = typeKey;
        this.name = stringKey;
        this.type = typeKey2;
    }

    public static FieldKey convert(Field field) {
        return create(TypeKey.convert(field.getDeclaringClass()), field.getName(), TypeKey.convert(field.getType()));
    }

    public static FieldKey create(TypeKey typeKey, StringKey stringKey, TypeKey typeKey2) {
        if (typeKey == null || stringKey == null || typeKey2 == null) {
            return null;
        }
        return new FieldKey(typeKey, stringKey, typeKey2);
    }

    public static FieldKey parse(String str, int i) {
        int iIndexOf;
        TypeKey binaryType;
        int length;
        int iIndexOf2;
        if (i + 6 >= str.length() || str.charAt(i) != 'L' || (iIndexOf = str.indexOf("->", i)) < 0 || (binaryType = TypeKey.parseBinaryType(str, i, iIndexOf)) == null || (iIndexOf2 = str.indexOf(58, (length = i + binaryType.getTypeName().length() + 2))) < 0 || iIndexOf2 == length) {
            return null;
        }
        String strSubstring = str.substring(length, iIndexOf2);
        return create(binaryType, strSubstring, TypeKey.parseBinaryType(str, length + strSubstring.length() + 1, str.length()));
    }

    public static FieldKey read(SmaliReader smaliReader) throws IOException {
        TypeKey typeKey = TypeKey.read(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, LocaleUtility.IETF_SEPARATOR);
        SmaliParseException.expect(smaliReader, '>');
        StringKey simpleName = StringKey.readSimpleName(smaliReader, ':');
        smaliReader.skip(1);
        return create(typeKey, simpleName, TypeKey.read(smaliReader));
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getDeclaring().append(smaliWriter);
        smaliWriter.append(LocaleUtility.IETF_SEPARATOR);
        smaliWriter.append('>');
        appendDefinition(smaliWriter);
    }

    public void appendDefinition(SmaliWriter smaliWriter) throws IOException {
        getNameKey().appendSimpleName(smaliWriter);
        smaliWriter.append(':');
        getType().append(smaliWriter);
    }

    public FieldKey changeDeclaring(TypeKey typeKey) {
        return typeKey.equals(getDeclaring()) ? this : create(typeKey, getNameKey(), getType());
    }

    public FieldKey changeName(String str) {
        return str.equals(getName()) ? this : create(getDeclaring(), str, getType());
    }

    public FieldKey changeType(TypeKey typeKey) {
        return typeKey.equals(getType()) ? this : create(getDeclaring(), getNameKey(), typeKey);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        FieldKey fieldKey = (FieldKey) obj;
        int iCompare = CompareUtil.compare(getDeclaring(), fieldKey.getDeclaring());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getNameKey(), fieldKey.getNameKey());
        return iCompare2 != 0 ? iCompare2 : CompareUtil.compare(getType(), fieldKey.getType());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldKey)) {
            return false;
        }
        FieldKey fieldKey = (FieldKey) obj;
        return getDeclaring().equals(fieldKey.getDeclaring()) && getNameKey().equals(fieldKey.getNameKey()) && getType().equals(fieldKey.getType());
    }

    public boolean equalsDeclaring(FieldKey fieldKey) {
        if (fieldKey == null) {
            return false;
        }
        if (fieldKey == this) {
            return true;
        }
        return getDeclaring().equals(fieldKey.getDeclaring());
    }

    public boolean equalsIgnoreDeclaring(FieldKey fieldKey) {
        if (fieldKey == null) {
            return false;
        }
        if (fieldKey == this) {
            return true;
        }
        return getName().equals(fieldKey.getName()) && getType().equals(fieldKey.getType());
    }

    public boolean equalsIgnoreName(FieldKey fieldKey) {
        if (fieldKey == null) {
            return false;
        }
        if (fieldKey == this) {
            return true;
        }
        return getDeclaring().equals(fieldKey.getDeclaring()) && getType().equals(fieldKey.getType());
    }

    public boolean equalsName(FieldKey fieldKey) {
        if (fieldKey == null) {
            return false;
        }
        if (fieldKey == this) {
            return true;
        }
        return getNameKey().equals(fieldKey.getNameKey());
    }

    public boolean equalsType(FieldKey fieldKey) {
        if (fieldKey == null) {
            return false;
        }
        if (fieldKey == this) {
            return true;
        }
        return getType().equals(fieldKey.getType());
    }

    @Override // com.reandroid.dex.key.ProgramKey, com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        return this.declaring;
    }

    @Override // com.reandroid.dex.key.ProgramKey
    public ElementType getElementType() {
        return ElementType.FIELD;
    }

    public String getName() {
        return getNameKey().getString();
    }

    public StringKey getNameKey() {
        return this.name;
    }

    public TypeKey getType() {
        return this.type;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getDeclaring(), getNameKey(), getType());
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<Key> mentionedKeys() {
        return CombiningIterator.singleThree(this, SingleIterator.of(getDeclaring()), SingleIterator.of(getNameKey()), SingleIterator.of(getType()));
    }

    @Override // com.reandroid.dex.key.Key
    public FieldKey replaceKey(Key key, Key key2) {
        if (key.equals(this)) {
            return (FieldKey) key2;
        }
        if (key.equals(getDeclaring())) {
            this = changeDeclaring((TypeKey) key2);
        }
        if ((key2 instanceof StringKey) && key.equals(this.getNameKey())) {
            this = this.changeName((StringKey) key2);
        }
        return ((key2 instanceof TypeKey) && key.equals(this.getType())) ? this.changeType((TypeKey) key2) : this;
    }

    public String toString() {
        return getDeclaring() + "->" + getNameKey().getAsSimpleName() + ':' + getType();
    }

    public static FieldKey create(TypeKey typeKey, String str, TypeKey typeKey2) {
        return create(typeKey, StringKey.create(str), typeKey2);
    }

    public boolean equalsDeclaring(TypeKey typeKey) {
        if (typeKey == null) {
            return false;
        }
        return getDeclaring().equals(typeKey);
    }

    public boolean equalsName(String str) {
        return ObjectsUtil.equals(getName(), str);
    }

    public boolean equalsType(TypeKey typeKey) {
        if (typeKey == null) {
            return false;
        }
        return getType().equals(typeKey);
    }

    public FieldKey changeName(StringKey stringKey) {
        return stringKey.equals(getNameKey()) ? this : create(getDeclaring(), stringKey, getType());
    }

    public static FieldKey parse(String str) {
        return parse(str, 0);
    }
}
