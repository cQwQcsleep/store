package org.jetbrains.kotlin.codegen.signature;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum BothSignatureWriter$Mode {
    METHOD(1),
    CLASS(0),
    TYPE(2),
    SKIP_CHECKS(null);

    private final Integer asmType;

    BothSignatureWriter$Mode(Integer num) {
        this.asmType = num;
    }
}
