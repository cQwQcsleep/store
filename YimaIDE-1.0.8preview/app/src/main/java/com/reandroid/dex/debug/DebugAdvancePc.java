package com.reandroid.dex.debug;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.Ule128Item;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugAdvancePc extends DebugAdvance {
    public DebugAdvancePc() {
        super(DebugElementType.ADVANCE_PC, new Ule128Item());
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public int getAddressDiff() {
        return getAdvance();
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugAdvancePc> getElementType() {
        return DebugElementType.ADVANCE_PC;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void setAddressDiff(int i) {
        setAdvance(i);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.debug.DebugAdvance
    public void setAdvance(int i) throws DexException {
        if (i >= 0) {
            super.setAdvance(i);
        } else {
            throw new DexException("Can not set negative advance: " + i);
        }
    }
}
