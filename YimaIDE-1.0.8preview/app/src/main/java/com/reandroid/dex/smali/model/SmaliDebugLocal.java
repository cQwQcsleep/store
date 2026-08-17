package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugStartLocal;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliDebugLocal extends SmaliDebugRegister {
    private StringKey name;
    private StringKey signature;
    private TypeKey type;

    @Override // com.reandroid.dex.smali.model.SmaliDebugRegister, com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        super.append(smaliWriter);
        smaliWriter.append(", ");
        smaliWriter.appendOptional(getName());
        smaliWriter.append(':');
        smaliWriter.appendOptional(getType());
        StringKey signature = getSignature();
        if (signature != null) {
            smaliWriter.append(", ");
            signature.append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebugRegister, com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<? extends DebugStartLocal> getDebugElementType() {
        return isExtended() ? DebugElementType.START_LOCAL_EXTENDED : DebugElementType.START_LOCAL;
    }

    public StringKey getName() {
        return this.name;
    }

    public StringKey getSignature() {
        return this.signature;
    }

    public TypeKey getType() {
        return this.type;
    }

    public boolean isExtended() {
        return this.signature != null;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebugRegister, com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        super.parse(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, ',');
        smaliReader.skipWhitespacesOrComment();
        setName(StringKey.read(smaliReader));
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, ':');
        smaliReader.skipSpaces();
        setType(TypeKey.read(smaliReader));
        smaliReader.skipSpaces();
        if (smaliReader.get() == 44) {
            smaliReader.skip(1);
            smaliReader.skipWhitespacesOrComment();
            setSignature(StringKey.read(smaliReader));
        }
    }

    public void setName(StringKey stringKey) {
        this.name = stringKey;
    }

    public void setSignature(StringKey stringKey) {
        this.signature = stringKey;
    }

    public void setType(TypeKey typeKey) {
        this.type = typeKey;
    }
}
