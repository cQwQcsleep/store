package com.reandroid.dex.smali.model;

import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.debug.DebugLineNumber;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliLineNumber extends SmaliDebugElement implements SmaliRegion {
    private int number;

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        super.append(smaliWriter);
        smaliWriter.appendInteger(getNumber());
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebugElement
    public DebugElementType<DebugLineNumber> getDebugElementType() {
        return DebugElementType.LINE_NUMBER;
    }

    public int getNumber() {
        return this.number;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        SmaliParseException.expect(smaliReader, getSmaliDirective());
        smaliReader.skipWhitespaces();
        setNumber(smaliReader.readInteger());
    }

    public void setNumber(int i) {
        this.number = i;
    }
}
