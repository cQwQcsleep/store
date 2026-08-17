package com.reandroid.dex.ins;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.smali.SmaliFormat;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PayloadEntry extends IntegerReference, SmaliFormat {
    int getIndex();

    PayloadData<?> getPayload();
}
