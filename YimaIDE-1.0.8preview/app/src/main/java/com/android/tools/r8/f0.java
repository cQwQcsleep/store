package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class f0 extends d0 {
    public f0(AndroidResourceInput androidResourceInput, C2742u50 c2742u50) {
        super(androidResourceInput, c2742u50);
    }

    @Override // com.android.tools.r8.AndroidResourceOutput
    public final ByteDataView getByteDataView() {
        try {
            return ByteDataView.of(K7.a(this.a.getByteStream()));
        } catch (ResourceException | IOException e) {
            this.b.error(new ExceptionDiagnostic(e, this.a.getOrigin()));
            return null;
        }
    }
}
