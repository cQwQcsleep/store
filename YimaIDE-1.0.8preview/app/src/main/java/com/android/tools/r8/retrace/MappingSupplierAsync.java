package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.MappingSupplierAsync;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MappingSupplierAsync<T extends MappingSupplierAsync<T>> extends MappingSupplierBase<T> {
    Retracer createRetracer(DiagnosticsHandler diagnosticsHandler, MappingPartitionFromKeySupplier mappingPartitionFromKeySupplier);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* JADX INFO: renamed from: registerClassUse */
    /* synthetic */ MappingSupplierBase mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* synthetic */ MappingSupplierBase registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* synthetic */ MappingSupplierBase registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference);
}
