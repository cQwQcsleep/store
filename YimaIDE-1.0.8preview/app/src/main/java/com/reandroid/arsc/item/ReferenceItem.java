package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ReferenceItem extends IntegerReference {
    <T1 extends Block> T1 getReferredParent(Class<T1> cls);
}
