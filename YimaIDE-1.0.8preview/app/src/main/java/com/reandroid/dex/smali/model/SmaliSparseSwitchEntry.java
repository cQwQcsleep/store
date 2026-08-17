package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliSparseSwitchEntry extends SmaliSwitchEntry {
    private final SmaliValueInteger smaliValue;

    public SmaliSparseSwitchEntry() {
        SmaliValueInteger smaliValueInteger = new SmaliValueInteger();
        this.smaliValue = smaliValueInteger;
        smaliValueInteger.setParent(this);
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendOptional(getSmaliValue());
        smaliWriter.append(" -> ");
        getLabel().append(smaliWriter);
    }

    public SmaliValueInteger getSmaliValue() {
        return this.smaliValue;
    }

    public int getValue() {
        return this.smaliValue.getValue();
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        getSmaliValue().parse(smaliReader);
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, LocaleUtility.IETF_SEPARATOR);
        SmaliParseException.expect(smaliReader, '>');
        smaliReader.skipSpaces();
        getLabel().parse(smaliReader);
    }

    public void setValue(int i) {
        this.smaliValue.setValue(i);
    }
}
