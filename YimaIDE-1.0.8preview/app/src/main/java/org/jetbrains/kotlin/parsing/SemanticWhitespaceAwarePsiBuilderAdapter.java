package org.jetbrains.kotlin.parsing;

import com.intellij.lang.impl.PsiBuilderAdapter;
import com.intellij.psi.tree.IElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class SemanticWhitespaceAwarePsiBuilderAdapter extends PsiBuilderAdapter implements SemanticWhitespaceAwarePsiBuilder {
    private final SemanticWhitespaceAwarePsiBuilder myBuilder;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementType", "org/jetbrains/kotlin/parsing/SemanticWhitespaceAwarePsiBuilderAdapter", "isWhitespaceOrComment"));
    }

    public SemanticWhitespaceAwarePsiBuilderAdapter(SemanticWhitespaceAwarePsiBuilder semanticWhitespaceAwarePsiBuilder) {
        super(semanticWhitespaceAwarePsiBuilder);
        this.myBuilder = semanticWhitespaceAwarePsiBuilder;
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void disableJoiningComplexTokens() {
        this.myBuilder.disableJoiningComplexTokens();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void disableNewlines() {
        this.myBuilder.disableNewlines();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void enableJoiningComplexTokens() {
        this.myBuilder.enableJoiningComplexTokens();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void enableNewlines() {
        this.myBuilder.enableNewlines();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public boolean isWhitespaceOrComment(IElementType iElementType) {
        if (iElementType == null) {
            $$$reportNull$$$0(0);
        }
        return this.myBuilder.isWhitespaceOrComment(iElementType);
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public boolean newlineBeforeCurrentToken() {
        return this.myBuilder.newlineBeforeCurrentToken();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void restoreJoiningComplexTokensState() {
        this.myBuilder.restoreJoiningComplexTokensState();
    }

    @Override // org.jetbrains.kotlin.parsing.SemanticWhitespaceAwarePsiBuilder
    public void restoreNewlinesState() {
        this.myBuilder.restoreNewlinesState();
    }
}
