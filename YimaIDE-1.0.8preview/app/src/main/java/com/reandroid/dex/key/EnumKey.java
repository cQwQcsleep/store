package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumKey extends FieldKey {
    private EnumKey(FieldKey fieldKey) {
        super(fieldKey.getDeclaring(), fieldKey.getNameKey(), fieldKey.getType());
    }

    public static EnumKey create(FieldKey fieldKey) {
        if (fieldKey == null) {
            return null;
        }
        return fieldKey instanceof EnumKey ? (EnumKey) fieldKey : new EnumKey(fieldKey);
    }

    public static EnumKey parse(String str) {
        if (str != null && str.startsWith(".enum ")) {
            return create(FieldKey.parse(str.substring(6).trim()));
        }
        return null;
    }

    public static EnumKey read(SmaliReader smaliReader) throws IOException {
        SmaliParseException.expect(smaliReader, SmaliDirective.ENUM);
        smaliReader.skipWhitespacesOrComment();
        return create(FieldKey.read(smaliReader));
    }

    @Override // com.reandroid.dex.key.FieldKey, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        SmaliDirective.ENUM.append(smaliWriter);
        super.append(smaliWriter);
    }

    @Override // com.reandroid.dex.key.FieldKey
    public EnumKey changeDeclaring(TypeKey typeKey) {
        return create(super.changeDeclaring(typeKey));
    }

    @Override // com.reandroid.dex.key.FieldKey
    public EnumKey changeName(String str) {
        return create(super.changeName(str));
    }

    @Override // com.reandroid.dex.key.FieldKey
    public EnumKey changeType(TypeKey typeKey) {
        return create(super.changeType(typeKey));
    }

    @Override // com.reandroid.dex.key.FieldKey, com.reandroid.dex.key.Key
    public EnumKey replaceKey(Key key, Key key2) {
        return create(super.replaceKey(key, key2));
    }

    @Override // com.reandroid.dex.key.FieldKey
    public String toString() {
        return SmaliDirective.ENUM + " " + super.toString();
    }

    @Override // com.reandroid.dex.key.FieldKey
    public EnumKey changeName(StringKey stringKey) {
        return create(super.changeName(stringKey));
    }
}
