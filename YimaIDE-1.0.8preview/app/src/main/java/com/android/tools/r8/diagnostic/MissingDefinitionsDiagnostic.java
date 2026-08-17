package com.android.tools.r8.diagnostic;

import com.android.tools.r8.Diagnostic;
import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface MissingDefinitionsDiagnostic extends Diagnostic {
    Collection<MissingDefinitionInfo> getMissingDefinitions();
}
