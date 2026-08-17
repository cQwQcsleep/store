package com.reandroid.dex.resource;

import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RTypeItem {
    private final DexClass dexClass;

    public RTypeItem(DexClass dexClass) {
        this.dexClass = dexClass;
    }

    public void appendJava(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendModifiers(getDexClass().getAccessFlags());
        smaliWriter.append("class ");
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(" {");
        smaliWriter.indentPlus();
        appendJavaEntries(smaliWriter);
        smaliWriter.indentMinus();
        smaliWriter.newLine();
        smaliWriter.append('}');
    }

    public abstract void appendJavaEntries(SmaliWriter smaliWriter) throws IOException;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return getKey().equals(((RTypeItem) obj).getKey());
    }

    public DexClass getDexClass() {
        return this.dexClass;
    }

    public TypeKey getKey() {
        return getDexClass().getKey();
    }

    public String getName() {
        return getKey().getSimpleInnerName();
    }

    public int hashCode() {
        return getKey().hashCode();
    }

    public abstract boolean isValid();

    public String toString() {
        return getKey().toString();
    }
}
