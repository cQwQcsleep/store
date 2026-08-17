package com.android.tools.r8.retrace;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.I;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.retrace.MappingSupplierBase;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MappingSupplierBase<T extends MappingSupplierBase<T>> extends I {
    /* JADX INFO: renamed from: registerClassUse */
    T mo19registerClassUse(DiagnosticsHandler diagnosticsHandler, ClassReference classReference);

    T registerFieldUse(DiagnosticsHandler diagnosticsHandler, FieldReference fieldReference);

    T registerMethodUse(DiagnosticsHandler diagnosticsHandler, MethodReference methodReference);
}
