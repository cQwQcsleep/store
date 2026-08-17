package io.github.rosemoe.sora.lang.analysis;

import io.github.rosemoe.sora.lang.brackets.BracketsProvider;
import io.github.rosemoe.sora.lang.diagnostic.DiagnosticsContainer;
import io.github.rosemoe.sora.lang.styling.Styles;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface StyleReceiver {
    void setDiagnostics(AnalyzeManager analyzeManager, DiagnosticsContainer diagnosticsContainer);

    void setStyles(AnalyzeManager analyzeManager, Styles styles);

    void setStyles(AnalyzeManager analyzeManager, Styles styles, Runnable runnable);

    void updateBracketProvider(AnalyzeManager analyzeManager, BracketsProvider bracketsProvider);

    default void updateStyles(AnalyzeManager analyzeManager, Styles styles, StyleUpdateRange styleUpdateRange) {
        setStyles(analyzeManager, styles);
    }
}
