package com.intellij.util.codeInsight;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class CommentUtilCore {
    public static boolean isComment(ASTNode aSTNode) {
        if (aSTNode == null) {
            return false;
        }
        IElementType elementType = aSTNode.getElementType();
        ParserDefinition parserDefinition = (ParserDefinition) LanguageParserDefinitions.INSTANCE.forLanguage(elementType.getLanguage());
        return parserDefinition != null && parserDefinition.getCommentTokens().contains(elementType);
    }

    public static boolean isComment(PsiElement psiElement) {
        return psiElement != null && isComment(psiElement.getNode());
    }
}
