package com.reandroid.dex.debug;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliLineNumber;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugLineNumber extends DebugElement {
    public DebugLineNumber() {
        super(DebugElementType.LINE_NUMBER);
    }

    private DebugAdvanceLine getAdvanceLine() {
        DebugSequence debugSequence = getDebugSequence();
        if (debugSequence == null) {
            return null;
        }
        DebugElement debugElement = debugSequence.get(getIndex() - 1);
        if (debugElement instanceof DebugAdvancePc) {
            debugElement = debugSequence.get(debugElement.getIndex() - 1);
        }
        if (debugElement instanceof DebugAdvanceLine) {
            return (DebugAdvanceLine) debugElement;
        }
        return null;
    }

    private int getMaxLineDiff() {
        return getAddressDiff() == 16 ? 1 : 10;
    }

    private DebugAdvanceLine getOrCreateAdvanceLine() {
        DebugSequence debugSequence;
        DebugAdvanceLine advanceLine = getAdvanceLine();
        return (advanceLine == null && (debugSequence = getDebugSequence()) != null) ? (DebugAdvanceLine) debugSequence.createAtPosition(DebugElementType.ADVANCE_LINE, getIndex()) : advanceLine;
    }

    private int getPreviousLineNumber() {
        DebugSequence debugSequence = getDebugSequence();
        int index = getIndex();
        if (debugSequence != null && index != 0) {
            for (int i = index - 1; i >= 0; i--) {
                DebugElement debugElement = debugSequence.get(i);
                if (debugElement instanceof DebugLineNumber) {
                    return ((DebugLineNumber) debugElement).getLineNumber();
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    private void setUpLineNumber(int i) throws DexException {
        int previousLineNumber = i - getPreviousLineNumber();
        int maxLineDiff = getMaxLineDiff();
        if (previousLineNumber >= -4 && previousLineNumber <= maxLineDiff) {
            setLineDiff(previousLineNumber);
            return;
        }
        DebugSequence debugSequence = getDebugSequence();
        if (debugSequence == null) {
            return;
        }
        if (getIndex() == 0) {
            debugSequence.setLineStartInternal(i);
            setLineDiff(0);
            return;
        }
        DebugAdvanceLine orCreateAdvanceLine = getOrCreateAdvanceLine();
        if (orCreateAdvanceLine == null) {
            return;
        }
        setLineDiff(0);
        orCreateAdvanceLine.setLineDiff(previousLineNumber);
    }

    @Override // com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public void appendExtra(SmaliWriter smaliWriter) throws IOException {
        int lineNumber;
        if (!isValid() || (lineNumber = getLineNumber()) == -1) {
            return;
        }
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendInteger(lineNumber);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.debug.DebugElement
    public void fromSmali(Smali smali) throws DexException {
        super.fromSmali(smali);
        setLineNumber(((SmaliLineNumber) smali).getNumber());
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public int getAddressDiff() {
        return getFlagOffset() / 15;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugLineNumber> getElementType() {
        return DebugElementType.LINE_NUMBER;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public int getLineDiff() {
        return (getFlagOffset() % 15) - 4;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public int getLineNumber() {
        return super.getLineNumber();
    }

    @Override // com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public int getSortOrder() {
        return 0;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getSortOrderFine() {
        return getLineNumber();
    }

    @Override // com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public boolean isEqualExtraLine(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DebugLineNumber) && getTargetAddress() == ((DebugLineNumber) obj).getTargetAddress();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.debug.DebugElement
    public void setAddressDiff(int i) throws DexException {
        int lineDiff = getLineDiff();
        int i2 = (i * 15) + lineDiff + 4;
        if (i2 > 245) {
            super.setAddressDiff(i);
            i2 = lineDiff + 4;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        setFlagOffset(i2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.debug.DebugElement
    public void setLineDiff(int i) throws DexException {
        int addressDiff = getAddressDiff() * 15;
        int i2 = addressDiff + i + 4;
        if (i2 > 245) {
            getOrCreateAdvanceLine().setLineDiff(i - 4);
        } else {
            addressDiff = i2;
        }
        setFlagOffset(addressDiff);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.debug.DebugElement
    public void setLineNumber(int i) throws DexException {
        setUpLineNumber(i);
        super.setLineNumber(i);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public String toString() {
        return ".line " + getLineNumber();
    }
}
