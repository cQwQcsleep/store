package com.reandroid.dex.base;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.common.ArraySupplier;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DexArraySupplier<T> extends ArraySupplier<T> {
    IntegerReference getOffsetReference();

    Iterator<T> iterator();
}
