package com.reandroid.dex.smali.model;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliInterfaceSet extends SmaliSet<SmaliInterface> implements SmaliRegion, Iterable<SmaliInterface> {
    public static SmaliInterfaceSet read(SmaliReader smaliReader) throws IOException {
        SmaliInterfaceSet smaliInterfaceSet = new SmaliInterfaceSet();
        smaliInterfaceSet.parse(smaliReader);
        if (smaliInterfaceSet.isEmpty()) {
            return null;
        }
        return smaliInterfaceSet;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        if (isEmpty()) {
            return;
        }
        smaliWriter.newLine();
        smaliWriter.newLine();
        smaliWriter.newLine();
        smaliWriter.appendComment("interfaces");
        smaliWriter.newLine();
        smaliWriter.appendAll(iterator());
    }

    public SmaliInterface createNew() {
        SmaliInterface smaliInterface = new SmaliInterface();
        add(smaliInterface);
        return smaliInterface;
    }

    @Override // com.reandroid.dex.smali.model.SmaliSet
    public SmaliInterface createNext(SmaliReader smaliReader) {
        smaliReader.skipWhitespacesOrComment();
        if (SmaliDirective.parse(smaliReader, false) != getSmaliDirective()) {
            return null;
        }
        return new SmaliInterface();
    }

    public TypeListKey getKey() {
        int size = size();
        if (size == 0) {
            return TypeListKey.empty();
        }
        TypeKey[] typeKeyArr = new TypeKey[size];
        for (int i = 0; i < size; i++) {
            typeKeyArr[i] = get(i).getKey();
        }
        return TypeListKey.create(typeKeyArr);
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.IMPLEMENTS;
    }

    public void setKey(Key key) {
        clear();
        if (key == null) {
            return;
        }
        TypeListKey typeListKey = (TypeListKey) key;
        int size = typeListKey.size();
        for (int i = 0; i < size; i++) {
            createNew().setKey(typeListKey.get(i));
        }
    }
}
