package org.bouncycastle.math.field;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ExtensionField extends FiniteField {
    int getDegree();

    FiniteField getSubfield();
}
