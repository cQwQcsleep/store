package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import defpackage.l78;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParameterisedProtoKey extends ArrayKey<ParameterisedTypeKey> {
    public static final ParameterisedProtoKey EMPTY = new ParameterisedProtoKey(false, null, KeyList.EMPTY_ARRAY);
    private final boolean method;
    private final ParameterisedTypeKey returnType;

    private ParameterisedProtoKey(boolean z, ParameterisedTypeKey parameterisedTypeKey, Key[] keyArr) {
        super(keyArr);
        this.method = z;
        this.returnType = parameterisedTypeKey;
    }

    public static ParameterisedProtoKey create(boolean z, ParameterisedTypeKey parameterisedTypeKey, Key[] keyArr) {
        if (keyArr == null || keyArr.length == 0) {
            if (parameterisedTypeKey == null) {
                return EMPTY;
            }
            keyArr = KeyList.EMPTY_ARRAY;
        }
        return new ParameterisedProtoKey(z, parameterisedTypeKey, keyArr);
    }

    public static ParameterisedProtoKey read(SmaliReader smaliReader) throws IOException {
        char ascii = smaliReader.readASCII();
        if (ascii != '<' && ascii != '(') {
            smaliReader.position(smaliReader.position() - 1);
            l78.a("Expecting '<' or '(", smaliReader);
            return null;
        }
        char c = ascii == '<' ? '>' : ')';
        ArrayCollection arrayCollection = null;
        while (!smaliReader.finished() && smaliReader.get() != c) {
            if (arrayCollection == null) {
                arrayCollection = new ArrayCollection();
            }
            arrayCollection.add(ParameterisedTypeKey.read(smaliReader));
        }
        SmaliParseException.expect(smaliReader, c);
        return create(c == ')', (c == ')' || (!smaliReader.finished() && smaliReader.get() == 46)) ? ParameterisedTypeKey.read(smaliReader) : null, arrayCollection != null ? (Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]) : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void appendString(StringBuilder sb, boolean z) {
        ParameterisedTypeKey returnType = getReturnType();
        if (isBlank()) {
            return;
        }
        boolean zIsMethod = isMethod();
        sb.append(zIsMethod ? '(' : '<');
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ParameterisedTypeKey) get(i)).appendString(sb, z);
        }
        sb.append(zIsMethod ? ')' : '>');
        if (returnType != null) {
            returnType.appendString(sb, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void buildSignature(DalvikSignatureBuilder dalvikSignatureBuilder) {
        ParameterisedTypeKey returnType = getReturnType();
        if (isBlank()) {
            return;
        }
        boolean zIsMethod = isMethod();
        dalvikSignatureBuilder.append(zIsMethod ? '(' : '<');
        dalvikSignatureBuilder.flushPending();
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ParameterisedTypeKey) get(i)).buildSignature(dalvikSignatureBuilder);
        }
        dalvikSignatureBuilder.append(zIsMethod ? ')' : '>');
        if (returnType != null) {
            returnType.buildSignature(dalvikSignatureBuilder);
        }
    }

    public ParameterisedProtoKey changeReturnType(ParameterisedTypeKey parameterisedTypeKey) {
        return ObjectsUtil.equals(getReturnType(), parameterisedTypeKey) ? this : create(isMethod(), parameterisedTypeKey, getElements());
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (!(obj instanceof ParameterisedProtoKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        ParameterisedProtoKey parameterisedProtoKey = (ParameterisedProtoKey) obj;
        int iCompare = CompareUtil.compare(getReturnType(), parameterisedProtoKey.getReturnType());
        return iCompare == 0 ? super.compareElements(parameterisedProtoKey) : iCompare;
    }

    @Override // com.reandroid.dex.key.ArrayKey
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParameterisedProtoKey)) {
            return false;
        }
        ParameterisedProtoKey parameterisedProtoKey = (ParameterisedProtoKey) obj;
        return ObjectsUtil.equals(getReturnType(), parameterisedProtoKey.getReturnType()) && equalsElements(parameterisedProtoKey);
    }

    public ParameterisedTypeKey getReturnType() {
        return this.returnType;
    }

    public Iterator<TypeKey> getTypes() {
        ParameterisedTypeKey returnType = getReturnType();
        return CombiningIterator.two(new IterableIterator<ParameterisedTypeKey, TypeKey>(iterator()) { // from class: com.reandroid.dex.key.ParameterisedProtoKey.1
            public Iterator<TypeKey> iterator(ParameterisedTypeKey parameterisedTypeKey) {
                return parameterisedTypeKey.getTypes();
            }
        }, returnType != null ? returnType.getTypes() : EmptyIterator.of());
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public int hashCode() {
        return (ObjectsUtil.hash(getReturnType()) * 31) + getHashCode();
    }

    public boolean isBlank() {
        return isEmpty() && getReturnType() == null;
    }

    public boolean isMethod() {
        return this.method;
    }

    @Override // com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        return super.mentionedKeys();
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public ParameterisedProtoKey newInstance(Key[] keyArr) {
        boolean zIsMethod = isMethod();
        ParameterisedTypeKey returnType = getReturnType();
        if (keyArr.length == 0) {
            if (!zIsMethod && returnType == null) {
                return EMPTY;
            }
            keyArr = KeyList.EMPTY_ARRAY;
        }
        return new ParameterisedProtoKey(zIsMethod, returnType, keyArr);
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public ParameterisedProtoKey replaceKey(Key key, Key key2) {
        ParameterisedProtoKey parameterisedProtoKey = (ParameterisedProtoKey) super.replaceKey(key, key2);
        ParameterisedTypeKey returnType = parameterisedProtoKey.getReturnType();
        return returnType != null ? parameterisedProtoKey.changeReturnType(returnType.replaceKey(key, key2)) : parameterisedProtoKey;
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendString(sb, false);
        return sb.toString();
    }
}
