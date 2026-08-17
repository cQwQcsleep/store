package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ProtoKey implements Key {
    private final TypeListKey parameters;
    private final TypeKey returnType;

    private ProtoKey(TypeListKey typeListKey, TypeKey typeKey) {
        this.parameters = typeListKey;
        this.returnType = typeKey;
    }

    public static ProtoKey create(TypeListKey typeListKey, TypeKey typeKey) {
        if (typeKey == null) {
            return null;
        }
        if (typeListKey == null) {
            typeListKey = TypeListKey.empty();
        }
        return new ProtoKey(typeListKey, typeKey);
    }

    public static ProtoKey emptyParameters(TypeKey typeKey) {
        return create(TypeListKey.empty(), typeKey);
    }

    public static ProtoKey parse(String str, int i) {
        int iIndexOf;
        TypeListKey parameters;
        TypeKey binaryType;
        if (str.length() - i < 3 || str.charAt(i) != '(' || (iIndexOf = str.indexOf(41, i)) < 0 || (parameters = TypeListKey.parseParameters(str, i + 1, iIndexOf)) == null || (binaryType = TypeKey.parseBinaryType(str, iIndexOf + 1, str.length())) == null) {
            return null;
        }
        return create(parameters, binaryType);
    }

    public static ProtoKey read(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        TypeListKey parameters = TypeListKey.readParameters(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        return new ProtoKey(parameters, TypeKey.read(smaliReader));
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('(');
        smaliWriter.appendAll(iterator(), false);
        smaliWriter.append(')');
        getReturnType().append(smaliWriter);
    }

    public ProtoKey changeParameter(int i, TypeKey typeKey) {
        return changeParameters(getParameters().set(i, typeKey));
    }

    public ProtoKey changeParameters(TypeListKey typeListKey) {
        return KeyList.equalsIgnoreEmpty(getParameters(), typeListKey) ? this : create(typeListKey, getReturnType());
    }

    public ProtoKey changeReturnType(TypeKey typeKey) {
        return getReturnType().equals(typeKey) ? this : create(getParameters(), typeKey);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (!(obj instanceof ProtoKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        ProtoKey protoKey = (ProtoKey) obj;
        int iCompare = CompareUtil.compare(getReturnType(), protoKey.getReturnType());
        return iCompare != 0 ? iCompare : CompareUtil.compare(getParameters(), protoKey.getParameters());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProtoKey)) {
            return false;
        }
        ProtoKey protoKey = (ProtoKey) obj;
        return ObjectsUtil.equals(getReturnType(), protoKey.getReturnType()) && ObjectsUtil.equals(getParameters(), protoKey.getParameters());
    }

    public boolean equalsParameters(ProtoKey protoKey) {
        if (protoKey == null) {
            return false;
        }
        if (protoKey == this) {
            return true;
        }
        return KeyList.equalsIgnoreEmpty(getParameters(), protoKey.getParameters());
    }

    public boolean equalsReturnType(ProtoKey protoKey) {
        if (protoKey == null) {
            return false;
        }
        if (protoKey == this) {
            return true;
        }
        return getReturnType().equals(protoKey.getReturnType());
    }

    public TypeKey getParameter(int i) {
        return getParameters().get(i);
    }

    public int getParameterIndex(int i) {
        int parametersCount = getParametersCount();
        int i2 = 0;
        for (int i3 = 0; i3 < parametersCount; i3++) {
            if (i2 == i) {
                return i3;
            }
            i2 = getParameter(i3).isWide() ? i2 + 2 : i2 + 1;
        }
        return -1;
    }

    public int getParameterRegistersCount() {
        Iterator<TypeKey> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i = it.next().isWide() ? i + 2 : i + 1;
        }
        return i;
    }

    public TypeListKey getParameters() {
        return this.parameters;
    }

    public int getParametersCount() {
        return getParameters().size();
    }

    public int getRegister(int i) {
        int parametersCount = getParametersCount();
        if (i >= parametersCount) {
            i = parametersCount;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 = getParameter(i3).isWide() ? i2 + 2 : i2 + 1;
        }
        return i2;
    }

    public TypeKey getReturnType() {
        return this.returnType;
    }

    public String getShorty() {
        TypeListKey parameters = getParameters();
        int size = parameters.size() + 1;
        char[] cArr = new char[size];
        cArr[0] = getReturnType().shorty();
        for (int i = 1; i < size; i++) {
            cArr[i] = parameters.get(i - 1).shorty();
        }
        return new String(cArr);
    }

    public int hashCode() {
        return ObjectsUtil.hash(getParameters(), getReturnType());
    }

    public Iterator<TypeKey> iterator() {
        return getParameters().iterator();
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<Key> mentionedKeys() {
        return CombiningIterator.singleThree(this, SingleIterator.of(StringKey.create(getShorty())), iterator(), SingleIterator.of(getReturnType()));
    }

    public ProtoKey removeParameter(int i) {
        return changeParameters(getParameters().remove(i));
    }

    @Override // com.reandroid.dex.key.Key
    public ProtoKey replaceKey(Key key, Key key2) {
        if (key.equals(this)) {
            return (ProtoKey) key2;
        }
        return (key.equals(getReturnType()) ? changeReturnType((TypeKey) key2) : this).changeParameters(getParameters().replaceKey(key, key2));
    }

    public String toString() {
        return getParameters().toString() + getReturnType();
    }

    public static ProtoKey create(TypeKey typeKey, TypeKey... typeKeyArr) {
        return create(TypeListKey.create(typeKeyArr), typeKey);
    }

    public boolean equalsParameters(TypeListKey typeListKey) {
        return KeyList.equalsIgnoreEmpty(getParameters(), typeListKey);
    }

    public boolean equalsReturnType(TypeKey typeKey) {
        return getReturnType().equals(typeKey);
    }

    public static ProtoKey parse(String str) {
        if (str == null) {
            return null;
        }
        return parse(str, 0);
    }
}
