package com.intellij.util.io;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class BooleanDataDescriptor extends InlineKeyDescriptor<Boolean> {
    public static final BooleanDataDescriptor INSTANCE = new BooleanDataDescriptor();

    private BooleanDataDescriptor() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.intellij.util.io.InlineKeyDescriptor
    public Boolean fromInt(int i) {
        return Boolean.valueOf(i != 0);
    }

    @Override // com.intellij.util.io.InlineKeyDescriptor
    public boolean isCompactFormat() {
        return true;
    }

    @Override // com.intellij.util.io.InlineKeyDescriptor
    public int toInt(Boolean bool) {
        return bool == Boolean.TRUE ? 1 : 0;
    }
}
