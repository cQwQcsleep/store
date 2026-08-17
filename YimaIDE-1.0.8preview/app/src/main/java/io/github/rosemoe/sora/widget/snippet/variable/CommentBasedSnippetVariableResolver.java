package io.github.rosemoe.sora.widget.snippet.variable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CommentBasedSnippetVariableResolver implements ISnippetVariableResolver {
    private String[] commentTokens;

    public CommentBasedSnippetVariableResolver(String[] strArr) {
        setCommentTokens(strArr);
    }

    public String[] getCommentTokens() {
        return this.commentTokens;
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String[] getResolvableNames() {
        return new String[]{"LINE_COMMENT", "BLOCK_COMMENT_START", "BLOCK_COMMENT_END"};
    }

    @Override // io.github.rosemoe.sora.widget.snippet.variable.ISnippetVariableResolver
    public String resolve(String str) {
        String[] strArr = this.commentTokens;
        if (strArr == null || strArr.length != 3) {
            k2d.a("language comment style is not configured properly");
            return null;
        }
        str.getClass();
        switch (str) {
            case "LINE_COMMENT":
                return this.commentTokens[0];
            case "BLOCK_COMMENT_START":
                return this.commentTokens[1];
            case "BLOCK_COMMENT_END":
                return this.commentTokens[2];
            default:
                w01.a("Unsupported variable name:".concat(str));
                return null;
        }
    }

    public void setCommentTokens(String[] strArr) {
        this.commentTokens = strArr;
    }

    public CommentBasedSnippetVariableResolver() {
        this(null);
    }
}
