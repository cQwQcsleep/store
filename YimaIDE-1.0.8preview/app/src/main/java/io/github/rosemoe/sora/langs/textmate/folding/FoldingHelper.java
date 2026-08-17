package io.github.rosemoe.sora.langs.textmate.folding;

import org.eclipse.tm4e.core.internal.oniguruma.OnigResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface FoldingHelper {
    int getIndentFor(int i);

    OnigResult getResultFor(int i);
}
