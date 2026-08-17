package com.reandroid.dex.ins;

import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CatchAllHandler extends ExceptionHandler {

    public static class Compact extends CatchAllHandler {
        private final CatchAllHandler catchAllHandler;

        public Compact(CatchAllHandler catchAllHandler) {
            super(true);
            this.catchAllHandler = catchAllHandler;
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler
        public Ule128Item getCatchAddressUle128() {
            return this.catchAllHandler.getCatchAddressUle128();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler
        public boolean isRemoved() {
            return super.isRemoved() || this.catchAllHandler.isRemoved();
        }
    }

    public CatchAllHandler() {
        super(0);
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.CATCH_ALL;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public TypeId getTypeId() {
        return null;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public boolean isCatchAll() {
        return true;
    }

    public CatchAllHandler newCompact(TryItem tryItem) {
        Compact compact = new Compact(this);
        compact.setIndex(getIndex());
        compact.setParent(tryItem);
        return compact;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public boolean traps(TypeKey typeKey) {
        return true;
    }

    public CatchAllHandler(boolean z) {
    }
}
