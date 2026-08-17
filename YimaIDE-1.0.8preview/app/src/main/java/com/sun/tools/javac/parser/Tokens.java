package com.sun.tools.javac.parser;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.api.Messages;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.s22;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Tokens {
    private Map<String, TokenKind> keywords = new HashMap();
    private final Names names;
    public static final Context.Key<Tokens> tokensKey = new Context.Key<>();
    public static final Token DUMMY = new Token(TokenKind.ERROR, 0, 0, null);

    public interface Comment {

        public enum CommentStyle {
            LINE,
            BLOCK,
            JAVADOC_LINE,
            JAVADOC_BLOCK
        }

        JCDiagnostic.DiagnosticPosition getPos();

        int getSourcePos(int i);

        CommentStyle getStyle();

        String getText();

        boolean isDeprecated();

        Comment stripIndent();
    }

    public static final class NamedToken extends Token {
        public final Name name;

        public NamedToken(TokenKind tokenKind, int i, int i2, Name name, List<Comment> list) {
            super(tokenKind, i, i2, list);
            this.name = name;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public void checkKind() {
            Token.Tag tag = this.kind.tag;
            Token.Tag tag2 = Token.Tag.NAMED;
            if (tag == tag2) {
                return;
            }
            s22.a("Bad token kind - expected ", tag2);
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public Name name() {
            return this.name;
        }
    }

    public static final class NumericToken extends StringToken {
        public final int radix;

        public NumericToken(TokenKind tokenKind, int i, int i2, String str, int i3, List<Comment> list) {
            super(tokenKind, i, i2, str, list);
            this.radix = i3;
        }

        @Override // com.sun.tools.javac.parser.Tokens.StringToken, com.sun.tools.javac.parser.Tokens.Token
        public void checkKind() {
            Token.Tag tag = this.kind.tag;
            Token.Tag tag2 = Token.Tag.NUMERIC;
            if (tag == tag2) {
                return;
            }
            s22.a("Bad token kind - expected ", tag2);
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public int radix() {
            return this.radix;
        }
    }

    public static class StringToken extends Token {
        public final String stringVal;

        public StringToken(TokenKind tokenKind, int i, int i2, String str, List<Comment> list) {
            super(tokenKind, i, i2, list);
            this.stringVal = str;
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public void checkKind() {
            Token.Tag tag = this.kind.tag;
            Token.Tag tag2 = Token.Tag.STRING;
            if (tag == tag2) {
                return;
            }
            s22.a("Bad token kind - expected ", tag2);
        }

        @Override // com.sun.tools.javac.parser.Tokens.Token
        public String stringVal() {
            return this.stringVal;
        }
    }

    public static class Token {
        public final List<Comment> comments;
        public final int endPos;
        public final TokenKind kind;
        public final int pos;

        public enum Tag {
            DEFAULT,
            NAMED,
            STRING,
            NUMERIC
        }

        public Token(TokenKind tokenKind, int i, int i2, List<Comment> list) {
            this.kind = tokenKind;
            this.pos = i;
            this.endPos = i2;
            this.comments = list;
            checkKind();
        }

        private List<Comment> getDocComments() {
            if (this.comments == null) {
                return List.nil();
            }
            ListBuffer listBuffer = new ListBuffer();
            for (Comment comment : this.comments) {
                int iOrdinal = comment.getStyle().ordinal();
                if (iOrdinal == 2 || iOrdinal == 3) {
                    listBuffer.add(comment);
                }
            }
            return listBuffer.toList();
        }

        public void checkKind() {
            Tag tag = this.kind.tag;
            Tag tag2 = Tag.DEFAULT;
            if (tag == tag2) {
                return;
            }
            s22.a("Bad token kind - expected ", tag2);
        }

        public boolean deprecatedFlag() {
            Iterator<Comment> it = getDocComments().iterator();
            while (it.hasNext()) {
                if (it.next().isDeprecated()) {
                    return true;
                }
            }
            return false;
        }

        public Comment docComment() {
            List<Comment> docComments = getDocComments();
            if (docComments.isEmpty()) {
                return null;
            }
            return docComments.head;
        }

        public Name name() {
            throw new UnsupportedOperationException();
        }

        public int radix() {
            throw new UnsupportedOperationException();
        }

        public Token[] split(Tokens tokens) {
            if (this.kind.name.length() >= 2) {
                TokenKind tokenKind = this.kind;
                if (tokenKind.tag == Tag.DEFAULT) {
                    TokenKind tokenKindLookupKind = tokens.lookupKind(tokenKind.name.substring(0, 1));
                    TokenKind tokenKindLookupKind2 = tokens.lookupKind(this.kind.name.substring(1));
                    if (tokenKindLookupKind == null || tokenKindLookupKind2 == null) {
                        x01.a("Can't split - bad subtokens");
                        return null;
                    }
                    int i = this.pos;
                    return new Token[]{new Token(tokenKindLookupKind, i, tokenKindLookupKind.name.length() + i, this.comments), new Token(tokenKindLookupKind2, this.pos + tokenKindLookupKind.name.length(), this.endPos, null)};
                }
            }
            pe1.a("Can't split", this.kind);
            return null;
        }

        public String stringVal() {
            throw new UnsupportedOperationException();
        }
    }

    public Tokens(Context context) {
        context.put(tokensKey, this);
        this.names = Names.instance(context);
        for (TokenKind tokenKind : TokenKind.values()) {
            String str = tokenKind.name;
            if (str != null) {
                this.names.fromString(str);
                this.keywords.put(tokenKind.name, tokenKind);
            }
        }
    }

    public static Tokens instance(Context context) {
        Tokens tokens = (Tokens) context.get(tokensKey);
        return tokens == null ? new Tokens(context) : tokens;
    }

    public TokenKind lookupKind(Name name) {
        TokenKind tokenKind = this.keywords.get(name.toString());
        return tokenKind != null ? tokenKind : TokenKind.IDENTIFIER;
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'IDENTIFIER' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class TokenKind implements Formattable, Predicate<TokenKind> {
        private static final /* synthetic */ TokenKind[] $VALUES;
        public static final TokenKind ABSTRACT;
        public static final TokenKind AMP;
        public static final TokenKind AMPAMP;
        public static final TokenKind AMPEQ;
        public static final TokenKind ARROW;
        public static final TokenKind ASSERT;
        public static final TokenKind BANG;
        public static final TokenKind BANGEQ;
        public static final TokenKind BAR;
        public static final TokenKind BARBAR;
        public static final TokenKind BAREQ;
        public static final TokenKind BOOLEAN;
        public static final TokenKind BREAK;
        public static final TokenKind BYTE;
        public static final TokenKind CARET;
        public static final TokenKind CARETEQ;
        public static final TokenKind CASE;
        public static final TokenKind CATCH;
        public static final TokenKind CHAR;
        public static final TokenKind CHARLITERAL;
        public static final TokenKind CLASS;
        public static final TokenKind COLCOL;
        public static final TokenKind COLON;
        public static final TokenKind COMMA;
        public static final TokenKind CONST;
        public static final TokenKind CONTINUE;
        public static final TokenKind CUSTOM;
        public static final TokenKind DEFAULT;
        public static final TokenKind DO;
        public static final TokenKind DOT;
        public static final TokenKind DOUBLE;
        public static final TokenKind DOUBLELITERAL;
        public static final TokenKind ELLIPSIS;
        public static final TokenKind ELSE;
        public static final TokenKind ENUM;
        public static final TokenKind EQ;
        public static final TokenKind EQEQ;
        public static final TokenKind EXTENDS;
        public static final TokenKind FALSE;
        public static final TokenKind FINAL;
        public static final TokenKind FINALLY;
        public static final TokenKind FLOAT;
        public static final TokenKind FLOATLITERAL;
        public static final TokenKind FOR;
        public static final TokenKind GOTO;
        public static final TokenKind GT;
        public static final TokenKind GTEQ;
        public static final TokenKind GTGT;
        public static final TokenKind GTGTEQ;
        public static final TokenKind GTGTGT;
        public static final TokenKind GTGTGTEQ;
        public static final TokenKind IDENTIFIER;
        public static final TokenKind IF;
        public static final TokenKind IMPLEMENTS;
        public static final TokenKind IMPORT;
        public static final TokenKind INSTANCEOF;
        public static final TokenKind INT;
        public static final TokenKind INTERFACE;
        public static final TokenKind INTLITERAL;
        public static final TokenKind LBRACE;
        public static final TokenKind LBRACKET;
        public static final TokenKind LONG;
        public static final TokenKind LONGLITERAL;
        public static final TokenKind LPAREN;
        public static final TokenKind LT;
        public static final TokenKind LTEQ;
        public static final TokenKind LTLT;
        public static final TokenKind LTLTEQ;
        public static final TokenKind MONKEYS_AT;
        public static final TokenKind NATIVE;
        public static final TokenKind NEW;
        public static final TokenKind NULL;
        public static final TokenKind PACKAGE;
        public static final TokenKind PERCENT;
        public static final TokenKind PERCENTEQ;
        public static final TokenKind PLUS;
        public static final TokenKind PLUSEQ;
        public static final TokenKind PLUSPLUS;
        public static final TokenKind PRIVATE;
        public static final TokenKind PROTECTED;
        public static final TokenKind PUBLIC;
        public static final TokenKind QUES;
        public static final TokenKind RBRACE;
        public static final TokenKind RBRACKET;
        public static final TokenKind RETURN;
        public static final TokenKind RPAREN;
        public static final TokenKind SEMI;
        public static final TokenKind SHORT;
        public static final TokenKind SLASH;
        public static final TokenKind SLASHEQ;
        public static final TokenKind STAR;
        public static final TokenKind STAREQ;
        public static final TokenKind STATIC;
        public static final TokenKind STRICTFP;
        public static final TokenKind STRINGFRAGMENT;
        public static final TokenKind STRINGLITERAL;
        public static final TokenKind SUB;
        public static final TokenKind SUBEQ;
        public static final TokenKind SUBSUB;
        public static final TokenKind SUPER;
        public static final TokenKind SWITCH;
        public static final TokenKind SYNCHRONIZED;
        public static final TokenKind THIS;
        public static final TokenKind THROW;
        public static final TokenKind THROWS;
        public static final TokenKind TILDE;
        public static final TokenKind TRANSIENT;
        public static final TokenKind TRUE;
        public static final TokenKind TRY;
        public static final TokenKind UNDERSCORE;
        public static final TokenKind VOID;
        public static final TokenKind VOLATILE;
        public static final TokenKind WHILE;
        public final String name;
        public final Token.Tag tag;
        public static final TokenKind EOF = new TokenKind("EOF", 0);
        public static final TokenKind ERROR = new TokenKind("ERROR", 1);

        private static /* synthetic */ TokenKind[] $values() {
            return new TokenKind[]{EOF, ERROR, IDENTIFIER, ABSTRACT, ASSERT, BOOLEAN, BREAK, BYTE, CASE, CATCH, CHAR, CLASS, CONST, CONTINUE, DEFAULT, DO, DOUBLE, ELSE, ENUM, EXTENDS, FINAL, FINALLY, FLOAT, FOR, GOTO, IF, IMPLEMENTS, IMPORT, INSTANCEOF, INT, INTERFACE, LONG, NATIVE, NEW, PACKAGE, PRIVATE, PROTECTED, PUBLIC, RETURN, SHORT, STATIC, STRICTFP, SUPER, SWITCH, SYNCHRONIZED, THIS, THROW, THROWS, TRANSIENT, TRY, VOID, VOLATILE, WHILE, INTLITERAL, LONGLITERAL, FLOATLITERAL, DOUBLELITERAL, CHARLITERAL, STRINGLITERAL, STRINGFRAGMENT, TRUE, FALSE, NULL, UNDERSCORE, ARROW, COLCOL, LPAREN, RPAREN, LBRACE, RBRACE, LBRACKET, RBRACKET, SEMI, COMMA, DOT, ELLIPSIS, EQ, GT, LT, BANG, TILDE, QUES, COLON, EQEQ, LTEQ, GTEQ, BANGEQ, AMPAMP, BARBAR, PLUSPLUS, SUBSUB, PLUS, SUB, STAR, SLASH, AMP, BAR, CARET, PERCENT, LTLT, GTGT, GTGTGT, PLUSEQ, SUBEQ, STAREQ, SLASHEQ, AMPEQ, BAREQ, CARETEQ, PERCENTEQ, LTLTEQ, GTGTEQ, GTGTGTEQ, MONKEYS_AT, CUSTOM};
        }

        static {
            Token.Tag tag = Token.Tag.NAMED;
            IDENTIFIER = new TokenKind("IDENTIFIER", 2, tag);
            ABSTRACT = new TokenKind("ABSTRACT", 3, PsiKeyword.ABSTRACT);
            ASSERT = new TokenKind("ASSERT", 4, PsiKeyword.ASSERT, tag);
            BOOLEAN = new TokenKind("BOOLEAN", 5, "boolean", tag);
            BREAK = new TokenKind("BREAK", 6, PsiKeyword.BREAK);
            BYTE = new TokenKind("BYTE", 7, "byte", tag);
            CASE = new TokenKind("CASE", 8, PsiKeyword.CASE);
            CATCH = new TokenKind("CATCH", 9, PsiKeyword.CATCH);
            CHAR = new TokenKind("CHAR", 10, PsiKeyword.CHAR, tag);
            CLASS = new TokenKind("CLASS", 11, "class");
            CONST = new TokenKind("CONST", 12, PsiKeyword.CONST);
            CONTINUE = new TokenKind("CONTINUE", 13, PsiKeyword.CONTINUE);
            DEFAULT = new TokenKind("DEFAULT", 14, "default");
            DO = new TokenKind("DO", 15, PsiKeyword.DO);
            DOUBLE = new TokenKind("DOUBLE", 16, "double", tag);
            ELSE = new TokenKind("ELSE", 17, PsiKeyword.ELSE);
            ENUM = new TokenKind("ENUM", 18, PsiKeyword.ENUM, tag);
            EXTENDS = new TokenKind("EXTENDS", 19, PsiKeyword.EXTENDS);
            FINAL = new TokenKind("FINAL", 20, PsiKeyword.FINAL);
            FINALLY = new TokenKind("FINALLY", 21, PsiKeyword.FINALLY);
            FLOAT = new TokenKind("FLOAT", 22, "float", tag);
            FOR = new TokenKind("FOR", 23, PsiKeyword.FOR);
            GOTO = new TokenKind("GOTO", 24, PsiKeyword.GOTO);
            IF = new TokenKind("IF", 25, "if");
            IMPLEMENTS = new TokenKind("IMPLEMENTS", 26, PsiKeyword.IMPLEMENTS);
            IMPORT = new TokenKind("IMPORT", 27, "import");
            INSTANCEOF = new TokenKind("INSTANCEOF", 28, PsiKeyword.INSTANCEOF);
            INT = new TokenKind("INT", 29, "int", tag);
            INTERFACE = new TokenKind("INTERFACE", 30, PsiKeyword.INTERFACE);
            LONG = new TokenKind("LONG", 31, "long", tag);
            NATIVE = new TokenKind("NATIVE", 32, PsiKeyword.NATIVE);
            NEW = new TokenKind("NEW", 33, PsiKeyword.NEW);
            PACKAGE = new TokenKind("PACKAGE", 34, PsiKeyword.PACKAGE);
            PRIVATE = new TokenKind("PRIVATE", 35, PsiKeyword.PRIVATE);
            PROTECTED = new TokenKind("PROTECTED", 36, PsiKeyword.PROTECTED);
            PUBLIC = new TokenKind("PUBLIC", 37, PsiKeyword.PUBLIC);
            RETURN = new TokenKind("RETURN", 38, PsiKeyword.RETURN);
            SHORT = new TokenKind("SHORT", 39, "short", tag);
            STATIC = new TokenKind("STATIC", 40, PsiKeyword.STATIC);
            STRICTFP = new TokenKind("STRICTFP", 41, PsiKeyword.STRICTFP);
            SUPER = new TokenKind("SUPER", 42, PsiKeyword.SUPER, tag);
            SWITCH = new TokenKind("SWITCH", 43, PsiKeyword.SWITCH);
            SYNCHRONIZED = new TokenKind("SYNCHRONIZED", 44, PsiKeyword.SYNCHRONIZED);
            THIS = new TokenKind("THIS", 45, PsiKeyword.THIS, tag);
            THROW = new TokenKind("THROW", 46, PsiKeyword.THROW);
            THROWS = new TokenKind("THROWS", 47, PsiKeyword.THROWS);
            TRANSIENT = new TokenKind("TRANSIENT", 48, PsiKeyword.TRANSIENT);
            TRY = new TokenKind("TRY", 49, PsiKeyword.TRY);
            VOID = new TokenKind("VOID", 50, PsiKeyword.VOID, tag);
            VOLATILE = new TokenKind("VOLATILE", 51, PsiKeyword.VOLATILE);
            WHILE = new TokenKind("WHILE", 52, PsiKeyword.WHILE);
            Token.Tag tag2 = Token.Tag.NUMERIC;
            INTLITERAL = new TokenKind("INTLITERAL", 53, tag2);
            LONGLITERAL = new TokenKind("LONGLITERAL", 54, tag2);
            FLOATLITERAL = new TokenKind("FLOATLITERAL", 55, tag2);
            DOUBLELITERAL = new TokenKind("DOUBLELITERAL", 56, tag2);
            CHARLITERAL = new TokenKind("CHARLITERAL", 57, tag2);
            Token.Tag tag3 = Token.Tag.STRING;
            STRINGLITERAL = new TokenKind("STRINGLITERAL", 58, tag3);
            STRINGFRAGMENT = new TokenKind("STRINGFRAGMENT", 59, tag3);
            TRUE = new TokenKind("TRUE", 60, "true", tag);
            FALSE = new TokenKind("FALSE", 61, "false", tag);
            NULL = new TokenKind("NULL", 62, PsiKeyword.NULL, tag);
            UNDERSCORE = new TokenKind("UNDERSCORE", 63, "_", tag);
            ARROW = new TokenKind("ARROW", 64, "->");
            COLCOL = new TokenKind("COLCOL", 65, "::");
            LPAREN = new TokenKind("LPAREN", 66, "(");
            RPAREN = new TokenKind("RPAREN", 67, ")");
            LBRACE = new TokenKind("LBRACE", 68, "{");
            RBRACE = new TokenKind("RBRACE", 69, "}");
            LBRACKET = new TokenKind("LBRACKET", 70, "[");
            RBRACKET = new TokenKind("RBRACKET", 71, "]");
            SEMI = new TokenKind("SEMI", 72, ";");
            COMMA = new TokenKind("COMMA", 73, ",");
            DOT = new TokenKind("DOT", 74, Constants.ATTRVAL_THIS);
            ELLIPSIS = new TokenKind("ELLIPSIS", 75, "...");
            EQ = new TokenKind("EQ", 76, "=");
            GT = new TokenKind("GT", 77, ">");
            LT = new TokenKind("LT", 78, "<");
            BANG = new TokenKind("BANG", 79, "!");
            TILDE = new TokenKind("TILDE", 80, "~");
            QUES = new TokenKind("QUES", 81, "?");
            COLON = new TokenKind("COLON", 82, ":");
            EQEQ = new TokenKind("EQEQ", 83, "==");
            LTEQ = new TokenKind("LTEQ", 84, "<=");
            GTEQ = new TokenKind("GTEQ", 85, ">=");
            BANGEQ = new TokenKind("BANGEQ", 86, "!=");
            AMPAMP = new TokenKind("AMPAMP", 87, "&&");
            BARBAR = new TokenKind("BARBAR", 88, "||");
            PLUSPLUS = new TokenKind("PLUSPLUS", 89, "++");
            SUBSUB = new TokenKind("SUBSUB", 90, "--");
            PLUS = new TokenKind("PLUS", 91, "+");
            SUB = new TokenKind("SUB", 92, "-");
            STAR = new TokenKind("STAR", 93, "*");
            SLASH = new TokenKind("SLASH", 94, PsuedoNames.PSEUDONAME_ROOT);
            AMP = new TokenKind("AMP", 95, "&");
            BAR = new TokenKind("BAR", 96, "|");
            CARET = new TokenKind("CARET", 97, "^");
            PERCENT = new TokenKind("PERCENT", 98, "%");
            LTLT = new TokenKind("LTLT", 99, "<<");
            GTGT = new TokenKind("GTGT", 100, ">>");
            GTGTGT = new TokenKind("GTGTGT", 101, ">>>");
            PLUSEQ = new TokenKind("PLUSEQ", 102, "+=");
            SUBEQ = new TokenKind("SUBEQ", 103, "-=");
            STAREQ = new TokenKind("STAREQ", 104, "*=");
            SLASHEQ = new TokenKind("SLASHEQ", 105, "/=");
            AMPEQ = new TokenKind("AMPEQ", 106, "&=");
            BAREQ = new TokenKind("BAREQ", 107, "|=");
            CARETEQ = new TokenKind("CARETEQ", 108, "^=");
            PERCENTEQ = new TokenKind("PERCENTEQ", 109, "%=");
            LTLTEQ = new TokenKind("LTLTEQ", 110, "<<=");
            GTGTEQ = new TokenKind("GTGTEQ", 111, ">>=");
            GTGTGTEQ = new TokenKind("GTGTGTEQ", 112, ">>>=");
            MONKEYS_AT = new TokenKind("MONKEYS_AT", 113, "@");
            CUSTOM = new TokenKind("CUSTOM", 114);
            $VALUES = $values();
        }

        private TokenKind(String str, int i, String str2, Token.Tag tag) {
            super(str, i);
            this.name = str2;
            this.tag = tag;
        }

        public static TokenKind valueOf(String str) {
            return (TokenKind) Enum.valueOf(TokenKind.class, str);
        }

        public static TokenKind[] values() {
            return (TokenKind[]) $VALUES.clone();
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String getKind() {
            return "Token";
        }

        @Override // java.lang.Enum
        public String toString() {
            int iOrdinal = ordinal();
            if (iOrdinal == 0) {
                return "token.end-of-input";
            }
            if (iOrdinal == 1) {
                return "token.bad-symbol";
            }
            if (iOrdinal == 2) {
                return "token.identifier";
            }
            switch (iOrdinal) {
                case 53:
                    return "token.integer";
                case 54:
                    return "token.long-integer";
                case 55:
                    return "token.float";
                case 56:
                    return "token.double";
                case 57:
                    return "token.character";
                case 58:
                    return "token.string";
                default:
                    String str = this.name;
                    switch (iOrdinal) {
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                            return "'" + str + "'";
                        default:
                            return str;
                    }
            }
        }

        @Override // java.util.function.Predicate
        public boolean test(TokenKind tokenKind) {
            return this == tokenKind;
        }

        private TokenKind(String str, int i, String str2) {
            this(str, i, str2, Token.Tag.DEFAULT);
        }

        private TokenKind(String str, int i, Token.Tag tag) {
            this(str, i, null, tag);
        }

        private TokenKind(String str, int i) {
            this(str, i, null, Token.Tag.DEFAULT);
        }

        @Override // com.sun.tools.javac.api.Formattable
        public String toString(Locale locale, Messages messages) {
            if (this.name != null) {
                return toString();
            }
            return messages.getLocalizedString(locale, "compiler.misc." + toString(), new Object[0]);
        }
    }

    public TokenKind lookupKind(String str) {
        TokenKind tokenKind = this.keywords.get(str);
        return tokenKind != null ? tokenKind : TokenKind.IDENTIFIER;
    }
}
