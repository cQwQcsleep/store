package org.jetbrains.kotlin.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface SemanticWhitespaceAwarePsiBuilder extends PsiBuilder {
    void disableJoiningComplexTokens();

    void disableNewlines();

    void enableJoiningComplexTokens();

    void enableNewlines();

    boolean isWhitespaceOrComment(IElementType iElementType);

    boolean newlineBeforeCurrentToken();

    void restoreJoiningComplexTokensState();

    void restoreNewlinesState();
}
