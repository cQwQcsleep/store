package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.MappingSupplier;
import com.android.tools.r8.retrace.MappingSupplierBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class MappingSupplierBuilder<P extends MappingSupplier<P>, T extends MappingSupplierBuilder<P, T>> {
    public abstract P build();

    public abstract T self();

    public abstract T setAllowExperimental(boolean z);
}
