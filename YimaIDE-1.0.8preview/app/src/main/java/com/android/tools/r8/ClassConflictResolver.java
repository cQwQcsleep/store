package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ClassConflictResolver {
    Origin resolveDuplicateClass(ClassReference classReference, Collection<Origin> collection, DiagnosticsHandler diagnosticsHandler);
}
