package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.internal.CW;
import com.android.tools.r8.naming.MapVersion;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import defpackage.f63;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PartitionMappingSupplier extends CW<PartitionMappingSupplier> implements MappingSupplier<PartitionMappingSupplier> {
    private final MappingPartitionFromKeySupplier l;

    private PartitionMappingSupplier(RegisterMappingPartitionCallback registerMappingPartitionCallback, PrepareMappingPartitionsCallback prepareMappingPartitionsCallback, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier, FinishedPartitionMappingCallback finishedPartitionMappingCallback, boolean z, byte[] bArr, MapVersion mapVersion) {
        super(registerMappingPartitionCallback, prepareMappingPartitionsCallback, finishedPartitionMappingCallback, z, bArr, mapVersion);
        this.l = mappingPartitionFromKeySupplier;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static NoMetadataBuilder noMetadataBuilder(MapVersion mapVersion) {
        return new NoMetadataBuilder(mapVersion);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier
    public Retracer createRetracer(DiagnosticsHandler diagnosticsHandler) {
        return createRetracerFromPartitionSupplier(diagnosticsHandler, this.l);
    }

    public MappingPartitionFromKeySupplier getMappingPartitionFromKeySupplier() {
        return this.l;
    }

    @Override // com.android.tools.r8.internal.CW
    public PartitionMappingSupplier getPartitionMappingSupplier() {
        return this;
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    /* JADX INFO: renamed from: registerClassUse, reason: merged with bridge method [inline-methods] */
    public PartitionMappingSupplier mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference) {
        return (PartitionMappingSupplier) super.mo19registerClassUse(diagnosticsHandler, classReference);
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    public PartitionMappingSupplier registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference) {
        return (PartitionMappingSupplier) mo19registerClassUse(diagnosticsHandler, fieldReference.getHolderClass());
    }

    @Override // com.android.tools.r8.retrace.MappingSupplier, com.android.tools.r8.retrace.MappingSupplierBase
    public PartitionMappingSupplier registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference) {
        return (PartitionMappingSupplier) mo19registerClassUse(diagnosticsHandler, methodReference.getHolderClass());
    }

    public static abstract class NoMetadataBuilderBase<B extends NoMetadataBuilderBase<B>> extends PartitionMappingSupplierBuilderBase<B> {
        protected MappingPartitionFromKeySupplier partitionSupplier;

        public B setMappingPartitionFromKeySupplier(MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier) {
            this.partitionSupplier = mappingPartitionFromKeySupplier;
            return self();
        }

        private NoMetadataBuilderBase(MapVersion mapVersion) {
            super(mapVersion);
        }
    }

    public static class Builder extends NoMetadataBuilderBase<Builder> {
        private byte[] a;

        private Builder() {
            super(MapVersion.MAP_VERSION_NONE);
        }

        public PartitionMappingSupplier build() {
            MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier = this.partitionSupplier;
            if (mappingPartitionFromKeySupplier == null) {
                f63.a("Cannot build without providing a partition supplier");
                return null;
            }
            byte[] bArr = this.a;
            if (bArr != null) {
                return new PartitionMappingSupplier(this.registerCallback, this.prepareCallback, mappingPartitionFromKeySupplier, this.finishedCallback, this.allowExperimental, bArr, this.fallbackMapVersion);
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

    public static class NoMetadataBuilder extends NoMetadataBuilderBase<NoMetadataBuilder> {
        private NoMetadataBuilder(MapVersion mapVersion) {
            super(mapVersion);
        }

        public PartitionMappingSupplier build() {
            MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier = this.partitionSupplier;
            if (mappingPartitionFromKeySupplier != null) {
                return new PartitionMappingSupplier(this.registerCallback, this.prepareCallback, mappingPartitionFromKeySupplier, this.finishedCallback, this.allowExperimental, null, this.fallbackMapVersion);
            }
            f63.a("Cannot build without providing a partition supplier.");
            return null;
        }

        @Override // com.android.tools.r8.retrace.PartitionMappingSupplierBuilderBase
        public NoMetadataBuilder self() {
            return this;
        }
    }

    @Override // com.android.tools.r8.internal.CW
    public PartitionMappingSupplier self() {
        return this;
    }
}
