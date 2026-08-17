package com.android.tools.r8.internal;

import com.android.tools.r8.retrace.MappingSupplier;
import com.android.tools.r8.retrace.MappingSupplierBuilder;
import com.android.tools.r8.retrace.ProguardMapProducer;
import com.android.tools.r8.retrace.ProguardMappingSupplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eZ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1408eZ extends ProguardMappingSupplier.Builder {
    public ProguardMapProducer a;
    public boolean b = false;
    public boolean c = true;

    @Override // com.android.tools.r8.retrace.MappingSupplierBuilder
    public final MappingSupplier build() {
        return new C1494fZ(this.a, this.b, this.c);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierBuilder
    public final MappingSupplierBuilder self() {
        return this;
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierBuilder
    public final MappingSupplierBuilder setAllowExperimental(boolean z) {
        this.b = z;
        return this;
    }

    @Override // com.android.tools.r8.retrace.ProguardMappingSupplier.Builder
    public final ProguardMappingSupplier.Builder setLoadAllDefinitions(boolean z) {
        this.c = z;
        return this;
    }

    @Override // com.android.tools.r8.retrace.ProguardMappingSupplier.Builder
    public final ProguardMappingSupplier.Builder setProguardMapProducer(ProguardMapProducer proguardMapProducer) {
        this.a = proguardMapProducer;
        return this;
    }
}
