package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliCodeExceptionHandler extends SmaliCode implements SmaliRegion {
    private final SmaliLabel catchLabel;
    private final SmaliLabel end;
    private final SmaliLabel start;

    public SmaliCodeExceptionHandler() {
        SmaliLabel smaliLabel = new SmaliLabel();
        this.start = smaliLabel;
        SmaliLabel smaliLabel2 = new SmaliLabel();
        this.end = smaliLabel2;
        SmaliLabel smaliLabel3 = new SmaliLabel();
        this.catchLabel = smaliLabel3;
        smaliLabel.setParent(this);
        smaliLabel2.setParent(this);
        smaliLabel3.setParent(this);
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        appendType(smaliWriter);
        smaliWriter.append('{');
        getStart().append(smaliWriter);
        smaliWriter.append(" .. ");
        getEnd().append(smaliWriter);
        smaliWriter.append('}');
        smaliWriter.append(' ');
        getCatchLabel().append(smaliWriter);
    }

    public void appendType(SmaliWriter smaliWriter) throws IOException {
    }

    public int getAddress() {
        SmaliCodeTryItem tryItem = getTryItem();
        if (tryItem == null) {
            return -1;
        }
        return tryItem.getAddress();
    }

    public SmaliLabel getCatchLabel() {
        return this.catchLabel;
    }

    public SmaliLabel getEnd() {
        return this.end;
    }

    public SmaliLabel getStart() {
        return this.start;
    }

    public SmaliCodeTryItem getTryItem() {
        return (SmaliCodeTryItem) getParentInstance(SmaliCodeTryItem.class);
    }

    @Override // com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, getSmaliDirective());
        parseType(smaliReader);
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, '{');
        getStart().parse(smaliReader);
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, '.');
        SmaliParseException.expect(smaliReader, '.');
        getEnd().parse(smaliReader);
        smaliReader.skipSpaces();
        SmaliParseException.expect(smaliReader, '}');
        getCatchLabel().parse(smaliReader);
    }

    public void parseType(SmaliReader smaliReader) throws IOException {
    }
}
