package com.android.tools.r8.retrace;

import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.retrace.PartitionMappingSupplierBuilderBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class PartitionMappingSupplierBuilderBase<T extends PartitionMappingSupplierBuilderBase<T>> {
    protected final MapVersion fallbackMapVersion;
    protected RegisterMappingPartitionCallback registerCallback = RegisterMappingPartitionCallback.empty();
    protected PrepareMappingPartitionsCallback prepareCallback = PrepareMappingPartitionsCallback.empty();
    protected FinishedPartitionMappingCallback finishedCallback = FinishedPartitionMappingCallback.empty();
    protected boolean allowExperimental = false;

    public PartitionMappingSupplierBuilderBase(MapVersion mapVersion) {
        this.fallbackMapVersion = mapVersion;
    }

    public abstract T self();

    public T setAllowExperimental(boolean z) {
        this.allowExperimental = z;
        return (T) self();
    }

    public T setFinishedPartitionMappingCallback(FinishedPartitionMappingCallback finishedPartitionMappingCallback) {
        this.finishedCallback = finishedPartitionMappingCallback;
        return (T) self();
    }

    public T setPrepareMappingPartitionsCallback(PrepareMappingPartitionsCallback prepareMappingPartitionsCallback) {
        this.prepareCallback = prepareMappingPartitionsCallback;
        return (T) self();
    }

    public T setRegisterMappingPartitionCallback(RegisterMappingPartitionCallback registerMappingPartitionCallback) {
        this.registerCallback = registerMappingPartitionCallback;
        return (T) self();
    }
}
