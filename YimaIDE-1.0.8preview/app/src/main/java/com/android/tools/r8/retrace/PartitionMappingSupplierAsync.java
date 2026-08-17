package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.CW;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import defpackage.f63;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PartitionMappingSupplierAsync extends CW<PartitionMappingSupplierAsync> implements MappingSupplierAsync<PartitionMappingSupplierAsync> {
    public static Builder builder() {
        return new Builder();
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierAsync
    public Retracer createRetracer(DiagnosticsHandler diagnosticsHandler, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
        return createRetracerFromPartitionSupplier(diagnosticsHandler, mappingPartitionFromKeySupplier);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierAsync, com.android.tools.r8.retrace.MappingSupplierBase
    /* JADX INFO: renamed from: registerClassUse */
    public PartitionMappingSupplierAsync mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference) {
        return (PartitionMappingSupplierAsync) super.mo19registerClassUse(diagnosticsHandler, classReference);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierAsync, com.android.tools.r8.retrace.MappingSupplierBase
    public PartitionMappingSupplierAsync registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference) {
        return (PartitionMappingSupplierAsync) mo19registerClassUse(diagnosticsHandler, fieldReference.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.MappingSupplierAsync, com.android.tools.r8.retrace.MappingSupplierBase
    public PartitionMappingSupplierAsync registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference) {
        return (PartitionMappingSupplierAsync) mo19registerClassUse(diagnosticsHandler, methodReference.getHolderClass());
    }

    public static class Builder extends PartitionMappingSupplierBuilderBase<Builder> {
        private byte[] a;

        private Builder() {
            super(MapVersion.MAP_VERSION_NONE);
        }

        public PartitionMappingSupplierAsync build() {
            byte[] bArr = this.a;
            if (bArr != null) {
                return new PartitionMappingSupplierAsync(this.registerCallback, this.prepareCallback, this.finishedCallback, this.allowExperimental, bArr, this.fallbackMapVersion);
            }
            f63.a("Cannot build without providing metadata.");
            return null;
        }

        public Builder setMetadata(byte[] bArr) {
            this.a = bArr;
            return self();
        }

        @Override // com.android.tools.r8.retrace.PartitionMappingSupplierBuilderBase
        public Builder self() {
            return this;
        }
    }

    private PartitionMappingSupplierAsync(RegisterMappingPartitionCallback registerMappingPartitionCallback, PrepareMappingPartitionsCallback prepareMappingPartitionsCallback, FinishedPartitionMappingCallback finishedPartitionMappingCallback, boolean z, byte[] bArr, MapVersion mapVersion) {
        super(registerMappingPartitionCallback, prepareMappingPartitionsCallback, finishedPartitionMappingCallback, z, bArr, mapVersion);
    }

    @Override // com.android.tools.r8.internal.CW
    public PartitionMappingSupplierAsync self() {
        return this;
    }
}
