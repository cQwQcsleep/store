package org.jetbrains.kotlin.kdoc.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.ILazyParseableElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.kotlin.KotlinElementTypeProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface KDocTokens {
    public static final KDocToken CODE_BLOCK_TEXT;
    public static final int CODE_BLOCK_TEXT_Id = 4;
    public static final KDocToken CODE_SPAN_TEXT;
    public static final int CODE_SPAN_TEXT_Id = 10;
    public static final TokenSet CONTENT_TOKENS;
    public static final KDocToken END;
    public static final int END_Id = 1;
    public static final ILazyParseableElementType KDOC = KotlinElementTypeProvider.getInstance().getKdocType();
    public static final TokenSet KDOC_HIGHLIGHT_TOKENS;
    public static final KDocToken KDOC_LPAR;
    public static final int KDOC_LPAR_Id = 8;
    public static final KDocToken KDOC_RPAR;
    public static final int KDOC_RPAR_Id = 9;
    public static final KDocToken LEADING_ASTERISK;
    public static final int LEADING_ASTERISK_Id = 2;
    public static final KDocToken MARKDOWN_ESCAPED_CHAR;
    public static final int MARKDOWN_ESCAPED_CHAR_Id = 6;

    @Deprecated
    public static final KDocToken MARKDOWN_INLINE_LINK;

    @Deprecated
    public static final int MARKDOWN_INLINE_LINK_Id = 7;
    public static final ILazyParseableElementType MARKDOWN_LINK;
    public static final KDocToken START;
    public static final int START_Id = 0;
    public static final KDocToken TAG_NAME;
    public static final int TAG_NAME_Id = 5;
    public static final KDocToken TEXT;
    public static final int TEXT_Id = 3;

    static {
        KDocToken kDocToken = new KDocToken("KDOC_START", 0);
        START = kDocToken;
        KDocToken kDocToken2 = new KDocToken("KDOC_END", 1);
        END = kDocToken2;
        KDocToken kDocToken3 = new KDocToken("KDOC_LEADING_ASTERISK", 2);
        LEADING_ASTERISK = kDocToken3;
        KDocToken kDocToken4 = new KDocToken("KDOC_TEXT", 3);
        TEXT = kDocToken4;
        KDocToken kDocToken5 = new KDocToken("KDOC_CODE_BLOCK_TEXT", 4);
        CODE_BLOCK_TEXT = kDocToken5;
        KDocToken kDocToken6 = new KDocToken("KDOC_CODE_SPAN_TEXT", 10);
        CODE_SPAN_TEXT = kDocToken6;
        KDocToken kDocToken7 = new KDocToken("KDOC_TAG_NAME", 5);
        TAG_NAME = kDocToken7;
        IElementType kdocMarkdownLinkType = KotlinElementTypeProvider.getInstance().getKdocMarkdownLinkType();
        MARKDOWN_LINK = kdocMarkdownLinkType;
        KDocToken kDocToken8 = new KDocToken("KDOC_LPAR", 8);
        KDOC_LPAR = kDocToken8;
        KDocToken kDocToken9 = new KDocToken("KDOC_RPAR", 9);
        KDOC_RPAR = kDocToken9;
        KDocToken kDocToken10 = new KDocToken("KDOC_MARKDOWN_ESCAPED_CHAR", 6);
        MARKDOWN_ESCAPED_CHAR = kDocToken10;
        KDocToken kDocToken11 = new KDocToken("KDOC_MARKDOWN_INLINE_LINK", 7);
        MARKDOWN_INLINE_LINK = kDocToken11;
        KDOC_HIGHLIGHT_TOKENS = TokenSet.create(new IElementType[]{kDocToken, kDocToken2, kDocToken3, kDocToken4, kDocToken5, kDocToken6, kdocMarkdownLinkType, kDocToken10, kDocToken11, kDocToken8, kDocToken9});
        CONTENT_TOKENS = TokenSet.create(new IElementType[]{kDocToken4, kDocToken5, kDocToken6, kDocToken7, kdocMarkdownLinkType, kDocToken10, kDocToken11, kDocToken8, kDocToken9});
    }
}
