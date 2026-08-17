package org.eclipse.tm4e.languageconfiguration.internal.supports;

import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Indexer;
import org.eclipse.tm4e.languageconfiguration.internal.model.CharacterPair;
import org.eclipse.tm4e.languageconfiguration.internal.model.CommentRule;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CommentSupport {
    private final CommentRule comments;

    public CommentSupport(CommentRule commentRule) {
        this.comments = commentRule;
    }

    private boolean isInBlockComment(String str) {
        CharacterPair characterPair;
        CommentRule commentRule = this.comments;
        if (commentRule == null || (characterPair = commentRule.blockComment) == null) {
            return false;
        }
        String str2 = characterPair.open;
        String str3 = characterPair.close;
        int iIndexOf = str.indexOf(str2);
        while (iIndexOf != -1 && iIndexOf < str.length()) {
            int iIndexOf2 = str.indexOf(str3, iIndexOf + str2.length());
            if (iIndexOf2 == -1) {
                return true;
            }
            iIndexOf = str.indexOf(str2, iIndexOf2 + str3.length());
        }
        return false;
    }

    private boolean isInComment(Content content, int i) {
        try {
            if (isInBlockComment(content.subSequence(0, i).toString())) {
                return true;
            }
            Indexer indexer = content.getIndexer();
            int charIndex = indexer.getCharIndex(indexer.getCharLine(i), 0);
            return isInLineComment(content.subSequence(charIndex, i - charIndex).toString());
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isInLineComment(String str) {
        CommentRule commentRule = this.comments;
        return (commentRule == null || str.indexOf(commentRule.lineComment) == -1) ? false : true;
    }

    public CharacterPair getBlockComment() {
        CommentRule commentRule = this.comments;
        if (commentRule == null) {
            return null;
        }
        return commentRule.blockComment;
    }

    public String getLineComment() {
        CommentRule commentRule = this.comments;
        if (commentRule == null) {
            return null;
        }
        return commentRule.lineComment;
    }
}
