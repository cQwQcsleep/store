package com.android.tools.r8.profile.art;

import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.TextOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ArtProfileConsumer {
    void finished(DiagnosticsHandler diagnosticsHandler);

    default TextOutputStream getHumanReadableArtProfileConsumer() {
        return null;
    }

    default ArtProfileRuleConsumer getRuleConsumer() {
        return null;
    }
}
