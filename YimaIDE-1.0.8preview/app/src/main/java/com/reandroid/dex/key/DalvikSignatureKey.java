package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.IterableIterator;
import defpackage.l78;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DalvikSignatureKey extends ArrayKey<ParameterisedTypeKey> {
    private DalvikSignatureKey(Key[] keyArr) {
        super(keyArr);
    }

    private static DalvikSignatureKey createKey(Key[] keyArr) {
        return new DalvikSignatureKey(keyArr);
    }

    private static SmaliReader ensurePlainString(SmaliReader smaliReader) throws IOException {
        if (smaliReader.finished()) {
            return smaliReader;
        }
        int iPosition = smaliReader.position();
        smaliReader.skipWhitespacesOrComment();
        if (!smaliReader.finished() && smaliReader.get() == 123) {
            return SmaliReader.of(StringsUtil.join(ArrayValueKey.read(smaliReader).stringValuesIterator(), ""));
        }
        smaliReader.position(iPosition);
        return smaliReader;
    }

    public static DalvikSignatureKey of(ParameterisedTypeKey... parameterisedTypeKeyArr) {
        if (parameterisedTypeKeyArr == null || parameterisedTypeKeyArr.length == 0) {
            return null;
        }
        return createKey(parameterisedTypeKeyArr);
    }

    public static DalvikSignatureKey parse(String str) {
        try {
            return read(SmaliReader.of(str));
        } catch (IOException unused) {
            return null;
        }
    }

    public static DalvikSignatureKey parseAnnotationValue(ArrayValueKey arrayValueKey) {
        if (arrayValueKey != null) {
            return parse(StringsUtil.join(arrayValueKey.stringValuesIterator(), ""));
        }
        return null;
    }

    public static DalvikSignatureKey read(SmaliReader smaliReader) throws IOException {
        SmaliReader smaliReaderEnsurePlainString = ensurePlainString(smaliReader);
        ArrayCollection arrayCollection = null;
        while (!smaliReaderEnsurePlainString.finished() && !smaliReaderEnsurePlainString.skipWhitespaces()) {
            ParameterisedTypeKey parameterisedTypeKey = ParameterisedTypeKey.read(smaliReaderEnsurePlainString);
            if (arrayCollection == null) {
                arrayCollection = new ArrayCollection();
            }
            arrayCollection.add(parameterisedTypeKey);
        }
        if (arrayCollection != null) {
            return createKey((Key[]) arrayCollection.toArrayFill(new Key[arrayCollection.size()]));
        }
        if (smaliReaderEnsurePlainString.finished()) {
            a16.a("EOF: Invalid signature");
            return null;
        }
        smaliReaderEnsurePlainString.position(smaliReaderEnsurePlainString.position() - 1);
        l78.a("Whitespace detected", smaliReaderEnsurePlainString);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ParameterisedProtoKey getMethodProto() {
        int size = size();
        for (int i = 0; i < size; i++) {
            ParameterisedProtoKey protoKey = ((ParameterisedTypeKey) get(i)).getProtoKey();
            if (protoKey.isMethod()) {
                return protoKey;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ParameterisedTypeKey getProtoParameter(int i) {
        ParameterisedProtoKey methodProto = getMethodProto();
        if (methodProto != null) {
            return (ParameterisedTypeKey) methodProto.get(i);
        }
        return null;
    }

    public Iterator<TypeKey> getTypes() {
        return new IterableIterator<ParameterisedTypeKey, TypeKey>(iterator()) { // from class: com.reandroid.dex.key.DalvikSignatureKey.1
            public Iterator<TypeKey> iterator(ParameterisedTypeKey parameterisedTypeKey) {
                return parameterisedTypeKey.getTypes();
            }
        };
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList, com.reandroid.dex.key.Key
    public DalvikSignatureKey replaceKey(Key key, Key key2) {
        return (DalvikSignatureKey) super.replaceKey(key, key2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ParameterisedTypeKey) get(i)).appendString(sb, false);
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArrayValueKey toStringValues() {
        DalvikSignatureBuilder dalvikSignatureBuilder = new DalvikSignatureBuilder();
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ParameterisedTypeKey) get(i)).buildSignature(dalvikSignatureBuilder);
        }
        dalvikSignatureBuilder.flush();
        return dalvikSignatureBuilder.build();
    }

    @Override // com.reandroid.dex.key.ArrayKey, com.reandroid.dex.key.KeyList
    public DalvikSignatureKey newInstance(Key[] keyArr) {
        return createKey(keyArr);
    }
}
