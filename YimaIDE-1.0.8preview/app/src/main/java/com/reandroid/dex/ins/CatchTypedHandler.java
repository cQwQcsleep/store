package com.reandroid.dex.ins;

import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.reference.Ule128IdItemReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.model.SmaliCodeCatch;
import com.reandroid.dex.smali.model.SmaliCodeExceptionHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CatchTypedHandler extends ExceptionHandler {
    private final Ule128IdItemReference<TypeId> typeId;

    public static class Compact extends CatchTypedHandler {
        private final CatchTypedHandler catchTypedHandler;

        public Compact(CatchTypedHandler catchTypedHandler) {
            super(null);
            this.catchTypedHandler = catchTypedHandler;
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler
        public Ule128Item getCatchAddressUle128() {
            return this.catchTypedHandler.getCatchAddressUle128();
        }

        @Override // com.reandroid.dex.ins.CatchTypedHandler
        public Ule128IdItemReference<TypeId> getTypeUle128() {
            return this.catchTypedHandler.getTypeUle128();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler
        public boolean isRemoved() {
            return super.isRemoved() || this.catchTypedHandler.isRemoved();
        }

        @Override // com.reandroid.dex.ins.CatchTypedHandler, com.reandroid.dex.ins.ExceptionHandler
        public void merge(ExceptionHandler exceptionHandler) {
        }
    }

    public CatchTypedHandler() {
        super(1);
        Ule128IdItemReference<TypeId> ule128IdItemReference = new Ule128IdItemReference<>(SectionType.TYPE_ID, UsageMarker.USAGE_INSTRUCTION);
        this.typeId = ule128IdItemReference;
        addChild(0, ule128IdItemReference);
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public void fromSmali(SmaliCodeExceptionHandler smaliCodeExceptionHandler) {
        this.typeId.setKey(((SmaliCodeCatch) smaliCodeExceptionHandler).getType());
        super.fromSmali(smaliCodeExceptionHandler);
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public TypeKey getKey() {
        return (TypeKey) getTypeUle128().getKey();
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler, com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.CATCH;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public TypeId getTypeId() {
        return (TypeId) getTypeUle128().getItem();
    }

    public Ule128IdItemReference<TypeId> getTypeUle128() {
        return this.typeId;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public boolean isCatchAll() {
        return false;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public void merge(ExceptionHandler exceptionHandler) {
        super.merge(exceptionHandler);
        this.typeId.setKey(((CatchTypedHandler) exceptionHandler).typeId.getKey());
    }

    public CatchTypedHandler newCompact(TryItem tryItem) {
        Compact compact = new Compact(this);
        compact.setIndex(getIndex());
        compact.setParent(tryItem);
        return compact;
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public void onRemove() {
        super.onRemove();
        this.typeId.setItem((IdItem) null);
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public void setKey(TypeKey typeKey) {
        getTypeUle128().setKey(typeKey);
    }

    @Override // com.reandroid.dex.ins.ExceptionHandler
    public boolean traps(TypeKey typeKey) {
        return typeKey != null && typeKey.equals(getKey());
    }

    public CatchTypedHandler(Ule128IdItemReference<TypeId> ule128IdItemReference) {
        this.typeId = ule128IdItemReference;
    }
}
