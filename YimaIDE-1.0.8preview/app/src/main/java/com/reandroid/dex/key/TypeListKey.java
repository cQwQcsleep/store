package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeListKey extends KeyList<TypeKey> {
    private static final TypeListKey EMPTY = new TypeListKey(KeyList.EMPTY_ARRAY);

    private TypeListKey(Key[] keyArr) {
        super(keyArr);
    }

    public static TypeListKey create(Key... keyArr) {
        Key[] keyArrRemoveNulls = KeyList.removeNulls(keyArr);
        return keyArrRemoveNulls == KeyList.EMPTY_ARRAY ? empty() : new TypeListKey(keyArrRemoveNulls);
    }

    public static TypeListKey empty() {
        return EMPTY;
    }

    public static TypeListKey parseParameters(String str, int i, int i2) {
        if (i2 == i) {
            return empty();
        }
        if (i > i2) {
            return null;
        }
        ArrayCollection arrayCollection = new ArrayCollection();
        while (i < i2) {
            TypeKey binaryType = TypeKey.parseBinaryType(str, i, i2);
            if (binaryType == null) {
                return null;
            }
            arrayCollection.add(binaryType);
            i += binaryType.getTypeName().length();
        }
        return create((Key[]) arrayCollection.toArrayFill(new TypeKey[arrayCollection.size()]));
    }

    public static TypeListKey readParameters(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, '(');
        smaliReader.skipWhitespacesOrComment();
        ArrayCollection arrayCollection = null;
        while (!smaliReader.finished() && smaliReader.get() != 41) {
            if (arrayCollection == null) {
                arrayCollection = new ArrayCollection();
            }
            arrayCollection.add(TypeKey.read(smaliReader));
            smaliReader.skipWhitespacesOrComment();
        }
        SmaliParseException.expect(smaliReader, ')');
        return arrayCollection == null ? empty() : create((Key[]) arrayCollection.toArrayFill(new TypeKey[arrayCollection.size()]));
    }

    public void appendInterfaces(SmaliWriter smaliWriter) throws IOException {
        int size = size();
        if (size == 0) {
            return;
        }
        smaliWriter.newLine();
        smaliWriter.appendCommentNewLine("interfaces");
        SmaliDirective smaliDirective = SmaliDirective.IMPLEMENTS;
        for (int i = 0; i < size; i++) {
            smaliWriter.newLine();
            smaliDirective.append(smaliWriter);
            get(i).append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        return !(obj instanceof TypeListKey) ? StringsUtil.compareToString(this, obj) : compareElements((TypeListKey) obj);
    }

    public boolean contains(TypeKey typeKey) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (ObjectsUtil.equals(typeKey, get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TypeListKey) {
            return equalsElements((TypeListKey) obj);
        }
        return false;
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public TypeListKey replaceKey(Key key, Key key2) {
        return (TypeListKey) super.replaceKey(key, key2);
    }

    @Override // com.reandroid.dex.key.KeyList
    public String toString() {
        return "(" + StringsUtil.join(iterator(), (Object) null) + ')';
    }

    @Override // com.reandroid.dex.key.KeyList
    public TypeListKey newInstance(Key[] keyArr) {
        return create(keyArr);
    }

    @Override // com.reandroid.dex.key.KeyList
    public TypeListKey add(TypeKey typeKey) {
        return (TypeListKey) super.add(typeKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public TypeListKey set(int i, TypeKey typeKey) {
        return (TypeListKey) super.set(i, typeKey);
    }

    @Override // com.reandroid.dex.key.KeyList
    public TypeListKey remove(int i) {
        return (TypeListKey) super.remove(i);
    }

    @Override // com.reandroid.dex.key.KeyList
    public TypeListKey remove(TypeKey typeKey) {
        return (TypeListKey) super.remove(typeKey);
    }

    public static TypeListKey parseParameters(String str) {
        return parseParameters(str, 0, str.length());
    }
}
