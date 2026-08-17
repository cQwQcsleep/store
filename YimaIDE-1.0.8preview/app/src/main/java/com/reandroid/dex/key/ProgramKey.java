package com.reandroid.dex.key;

import java.lang.annotation.ElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ProgramKey extends Key {
    @Override // com.reandroid.dex.key.Key
    TypeKey getDeclaring();

    ElementType getElementType();
}
