package com.reandroid.dex.model;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DexCode extends Dex {
    @Override // com.reandroid.dex.model.Dex
    public DexClassRepository getClassRepository() {
        return getDexMethod().getClassRepository();
    }

    public DexClass getDexClass() {
        return getDexMethod().getDexClass();
    }

    public abstract DexMethod getDexMethod();
}
