package com.reandroid.dex.smali.model;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliLabel extends SmaliCode {
    private String labelName;

    private int searchAddress() {
        SmaliCodeSet codeSet = getCodeSet();
        if (codeSet == null) {
            return -1;
        }
        if (codeSet != getParent()) {
            int iIndexOf = codeSet.indexOf(this);
            if (iIndexOf < 0) {
                return -1;
            }
            return ((SmaliLabel) codeSet.get(iIndexOf)).getAddress();
        }
        Iterator<T2> it = codeSet.iterator(codeSet.indexOf(this) + 1, SmaliInstruction.class);
        if (it.hasNext()) {
            return ((SmaliInstruction) it.next()).getAddress();
        }
        SmaliInstruction nullInstruction = codeSet.getNullInstruction();
        if (nullInstruction != null) {
            return nullInstruction.getAddress();
        }
        return -1;
    }

    private void setLabelNameInternal(String str) {
        this.labelName = str;
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append(':');
        smaliWriter.append((CharSequence) getLabelName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SmaliLabel) {
            return ObjectsUtil.equals(getLabelName(), ((SmaliLabel) obj).getLabelName());
        }
        return false;
    }

    public int getAddress() {
        return searchAddress();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public int getIntegerData() throws DexException {
        int address = getAddress();
        if (address != -1) {
            return address;
        }
        throw new DexException("Missing target label '" + getLabelName() + "'" + buildOrigin());
    }

    public String getLabelName() {
        return this.labelName;
    }

    public SmaliInstruction getTargetInstruction() {
        int iIndexOf;
        SmaliCodeSet codeSet = getCodeSet();
        if (codeSet == null || (iIndexOf = codeSet.indexOf(this)) < 0) {
            return null;
        }
        SmaliInstruction smaliInstruction = (SmaliInstruction) CollectionUtil.getFirst(codeSet.iterator(iIndexOf + 1, SmaliInstruction.class));
        return smaliInstruction == null ? codeSet.getNullInstruction() : smaliInstruction;
    }

    public int hashCode() {
        return Objects.hash(getLabelName());
    }

    @Override // com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        setOrigin(smaliReader.getCurrentOrigin());
        SmaliParseException.expect(smaliReader, ':');
        int iIndexOfWhiteSpaceOrComment = smaliReader.indexOfWhiteSpaceOrComment();
        int iIndexOfBeforeLineEnd = smaliReader.indexOfBeforeLineEnd('}');
        if (iIndexOfBeforeLineEnd >= 0 && iIndexOfBeforeLineEnd < iIndexOfWhiteSpaceOrComment) {
            iIndexOfWhiteSpaceOrComment = iIndexOfBeforeLineEnd;
        }
        setLabelNameInternal(smaliReader.readString(iIndexOfWhiteSpaceOrComment - smaliReader.position()));
    }

    public void setLabelName(String str) {
        if (str.charAt(0) == ':') {
            str = str.substring(1);
        }
        setLabelNameInternal(str);
    }
}
