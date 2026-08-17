package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.Z;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.MappingSupplier;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MappingSupplier<T extends MappingSupplier<T>> extends MappingSupplierBase<T>, Z {
    Retracer createRetracer(DiagnosticsHandler diagnosticsHandler);

    /* synthetic */ Set getMapVersions(DiagnosticsHandler diagnosticsHandler);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* JADX INFO: renamed from: registerClassUse */
    /* synthetic */ MappingSupplierBase mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* synthetic */ MappingSupplierBase registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference);

    @Override // com.android.tools.r8.retrace.MappingSupplierBase
    /* synthetic */ MappingSupplierBase registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference);

    /* synthetic */ void verifyMappingFileHash(DiagnosticsHandler diagnosticsHandler);
}
