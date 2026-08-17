package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Diagnostic {
    String getDiagnosticMessage();

    Origin getOrigin();

    Position getPosition();
}
