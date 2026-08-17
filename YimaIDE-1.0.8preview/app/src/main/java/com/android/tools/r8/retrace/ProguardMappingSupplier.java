package com.android.tools.r8.retrace;

import com.android.tools.r8.internal.C1408eZ;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class ProguardMappingSupplier implements MappingSupplier<ProguardMappingSupplier> {

    public static abstract class Builder extends MappingSupplierBuilder<ProguardMappingSupplier, Builder> {
        public abstract Builder setLoadAllDefinitions(boolean z);

        public abstract Builder setProguardMapProducer(ProguardMapProducer proguardMapProducer);
    }

    public static Builder builder() {
        return new C1408eZ();
    }
}
