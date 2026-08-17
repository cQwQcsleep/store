package com.reandroid.dex.resource;

import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class RStyleableItem {
    private final DexField dexField;

    public RStyleableItem(DexField dexField) {
        this.dexField = dexField;
    }

    public void appendJava(SmaliWriter smaliWriter) throws IOException {
        DexField dexField = getDexField();
        smaliWriter.appendModifiers(dexField.getAccessFlags());
        smaliWriter.append((CharSequence) TypeKey.create(dexField.getKey().getType().getTypeName()).getSourceName());
        smaliWriter.append(' ');
        smaliWriter.append((CharSequence) dexField.getName());
        smaliWriter.append(" = ");
        appendJavaValue(smaliWriter);
        smaliWriter.append(';');
    }

    public abstract void appendJavaValue(SmaliWriter smaliWriter) throws IOException;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ObjectsUtil.equals(getName(), ((RStyleableItem) obj).getName());
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
        return ObjectsUtil.hash(getName());
    }

    public boolean isValid() {
        DexField dexField = getDexField();
        return dexField.isPublic() && dexField.isStatic();
    }

    public void serialize(TableBlock tableBlock, XmlSerializer xmlSerializer) throws IOException {
    }

    public String toString() {
        return getName();
    }
}
