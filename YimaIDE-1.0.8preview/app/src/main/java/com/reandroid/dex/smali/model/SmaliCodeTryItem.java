package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.InstanceIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliCodeTryItem extends SmaliCode {
    private SmaliCodeCatchAll catchAll;
    private final SmaliSet<SmaliCodeCatch> catchSet;

    public SmaliCodeTryItem() {
        SmaliSet<SmaliCodeCatch> smaliSet = new SmaliSet<>();
        this.catchSet = smaliSet;
        smaliSet.setParent(this);
    }

    private boolean isDifferentGroup(SmaliCodeExceptionHandler smaliCodeExceptionHandler) {
        SmaliLabel smaliLabelPickStartLabel = pickStartLabel();
        return (smaliLabelPickStartLabel == null || smaliLabelPickStartLabel.equals(smaliCodeExceptionHandler.getStart())) ? false : true;
    }

    private void parseCatchAll(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        if (SmaliDirective.parse(smaliReader, false) == SmaliDirective.CATCH_ALL) {
            SmaliCodeCatchAll smaliCodeCatchAll = new SmaliCodeCatchAll();
            int iPosition = smaliReader.position();
            smaliCodeCatchAll.parse(smaliReader);
            if (isDifferentGroup(smaliCodeCatchAll)) {
                smaliReader.position(iPosition);
            } else {
                setCatchAll(smaliCodeCatchAll);
            }
        }
    }

    private void parseCatches(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        SmaliSet<SmaliCodeCatch> catchSet = getCatchSet();
        SmaliDirective smaliDirective = SmaliDirective.parse(smaliReader, false);
        while (smaliDirective == SmaliDirective.CATCH) {
            int iPosition = smaliReader.position();
            SmaliCodeCatch smaliCodeCatch = new SmaliCodeCatch();
            smaliCodeCatch.parse(smaliReader);
            if (isDifferentGroup(smaliCodeCatch)) {
                smaliReader.position(iPosition);
                return;
            } else {
                catchSet.add(smaliCodeCatch);
                smaliReader.skipWhitespacesOrComment();
                smaliDirective = SmaliDirective.parse(smaliReader, false);
            }
        }
    }

    private SmaliLabel pickStartLabel() {
        Iterator<T> it = getCatchSet().iterator();
        if (it.hasNext()) {
            return ((SmaliCodeCatch) it.next()).getStart();
        }
        SmaliCodeCatchAll catchAll = getCatchAll();
        if (catchAll != null) {
            return catchAll.getStart();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendAll(getCatchSet().iterator());
        SmaliCodeCatchAll catchAll = getCatchAll();
        if (catchAll != null) {
            smaliWriter.newLine();
            catchAll.append(smaliWriter);
        }
    }

    public int getAddress() {
        SmaliInstruction smaliInstruction;
        SmaliCodeSet codeSet = getCodeSet();
        if (codeSet == null || (smaliInstruction = (SmaliInstruction) CollectionUtil.getFirst(InstanceIterator.of(codeSet.iterator(codeSet.indexOf(this) + 1), SmaliInstruction.class))) == null) {
            return -1;
        }
        return smaliInstruction.getAddress();
    }

    public SmaliCodeCatchAll getCatchAll() {
        return this.catchAll;
    }

    public SmaliSet<SmaliCodeCatch> getCatchSet() {
        return this.catchSet;
    }

    public int getStartAddress() {
        SmaliLabel smaliLabelPickStartLabel = pickStartLabel();
        if (smaliLabelPickStartLabel != null) {
            return smaliLabelPickStartLabel.getAddress();
        }
        return -1;
    }

    @Override // com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        parseCatches(smaliReader);
        parseCatchAll(smaliReader);
    }

    public void setCatchAll(SmaliCodeCatchAll smaliCodeCatchAll) {
        this.catchAll = smaliCodeCatchAll;
        if (smaliCodeCatchAll != null) {
            smaliCodeCatchAll.setParent(this);
        }
    }
}
