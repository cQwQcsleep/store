package com.reandroid.dex.model;

import com.reandroid.dex.ins.CatchAllHandler;
import com.reandroid.dex.ins.ExceptionHandler;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexCatch extends DexCode {
    private final DexTry dexTry;
    private final ExceptionHandler exceptionHandler;

    public DexCatch(DexTry dexTry, ExceptionHandler exceptionHandler) {
        this.dexTry = dexTry;
        this.exceptionHandler = exceptionHandler;
    }

    @Override // com.reandroid.dex.model.Dex, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getExceptionHandler().getHandlerLabel().appendExtra(smaliWriter);
    }

    public int getCatchAddress() {
        return getExceptionHandler().getCatchAddress();
    }

    public DexInstruction getCaughtInstruction() {
        return getDexMethod().getInstructionAt(getCatchAddress());
    }

    @Override // com.reandroid.dex.model.DexCode
    public DexMethod getDexMethod() {
        return getDexTry().getDexMethod();
    }

    public DexTry getDexTry() {
        return this.dexTry;
    }

    public ExceptionHandler getExceptionHandler() {
        return this.exceptionHandler;
    }

    public TypeKey getKey() {
        return getExceptionHandler().getKey();
    }

    public boolean isCatchAll() {
        return getExceptionHandler() instanceof CatchAllHandler;
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean isRemoved() {
        return getDexMethod().isRemoved() || getExceptionHandler().isRemoved();
    }

    @Override // com.reandroid.dex.model.Dex
    public void removeSelf() {
        getExceptionHandler().removeSelf();
    }

    public void setCatchAddress(int i) {
        getExceptionHandler().setCatchAddress(i);
    }

    public void setKey(TypeKey typeKey) {
        getExceptionHandler().setKey(typeKey);
    }

    public boolean traps(TypeKey typeKey) {
        return getExceptionHandler().traps(typeKey);
    }

    @Override // com.reandroid.dex.model.Dex
    public boolean uses(Key key) {
        TypeKey key2 = getKey();
        if (key2 != null) {
            return key2.uses(key);
        }
        return false;
    }
}
