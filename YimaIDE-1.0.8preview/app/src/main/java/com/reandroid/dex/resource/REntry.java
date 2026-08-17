package com.reandroid.dex.resource;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class REntry implements IntegerReference {
    private final DexField dexField;

    public REntry(DexField dexField) {
        this.dexField = dexField;
    }

    public void appendJava(SmaliWriter smaliWriter) throws IOException {
        DexField dexField = getDexField();
        smaliWriter.appendModifiers(dexField.getAccessFlags());
        smaliWriter.append((CharSequence) TypeKey.create(dexField.getKey().getType().getTypeName()).getSourceName());
        smaliWriter.append(' ');
        smaliWriter.append((CharSequence) dexField.getName());
        smaliWriter.append(" = ");
        smaliWriter.appendHex(get());
        smaliWriter.append(';');
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof REntry) {
            return getKey().equals(((REntry) obj).getKey());
        }
        return false;
    }

    public int get() {
        IntegerReference staticIntegerValue = getDexField().getStaticIntegerValue();
        if (staticIntegerValue != null) {
            return staticIntegerValue.get();
        }
        return 0;
    }

    public DexField getDexField() {
        return this.dexField;
    }

    public FieldKey getKey() {
        return getDexField().getKey();
    }

    public String getName() {
        return getKey().getName();
    }

    public int hashCode() {
        return getKey().hashCode();
    }

    public boolean isValid() {
        DexField dexField = getDexField();
        return dexField.isPublic() && dexField.isStatic() && PackageBlock.isResourceId(get());
    }

    public void set(int i) {
        IntegerReference staticIntegerValue = getDexField().getStaticIntegerValue();
        if (staticIntegerValue != null) {
            staticIntegerValue.set(i);
        }
    }

    public String toString() {
        return getName() + " = " + HexUtil.toHex8(get());
    }
}
