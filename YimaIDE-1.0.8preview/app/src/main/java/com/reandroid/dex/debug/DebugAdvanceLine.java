package com.reandroid.dex.debug;

import com.reandroid.dex.base.Sle128Item;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugAdvanceLine extends DebugAdvance {
    public DebugAdvanceLine() {
        super(DebugElementType.ADVANCE_LINE, new Sle128Item());
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugAdvanceLine> getElementType() {
        return DebugElementType.ADVANCE_LINE;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public int getLineDiff() {
        return getAdvance();
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void setLineDiff(int i) {
        setAdvance(i);
    }
}
