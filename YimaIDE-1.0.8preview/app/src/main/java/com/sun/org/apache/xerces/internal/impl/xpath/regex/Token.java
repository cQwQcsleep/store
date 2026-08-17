package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import com.sun.jna.platform.win32.WinNT;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.jvm.ClassReader;
import defpackage.uv;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class Token implements Serializable {
    static final int ANCHOR = 8;
    static final int BACKREFERENCE = 12;
    static final int CHAR = 0;
    static final int CHAR_FINAL_QUOTE = 30;
    static final int CHAR_INIT_QUOTE = 29;
    static final int CHAR_LETTER = 31;
    static final int CHAR_MARK = 32;
    static final int CHAR_NUMBER = 33;
    static final int CHAR_OTHER = 35;
    static final int CHAR_PUNCTUATION = 36;
    static final int CHAR_SEPARATOR = 34;
    static final int CHAR_SYMBOL = 37;
    static final int CLOSURE = 3;
    static final int CONCAT = 1;
    static final int CONDITION = 26;
    static final boolean COUNTTOKENS = true;
    static final int DOT = 11;
    static final int EMPTY = 7;
    static final int FC_ANY = 2;
    static final int FC_CONTINUE = 0;
    static final int FC_TERMINAL = 1;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIERGROUP = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    private static final int NONBMP_BLOCK_START = 84;
    static final int NONGREEDYCLOSURE = 9;
    static final int NRANGE = 5;
    static final int PAREN = 6;
    static final int RANGE = 4;
    static final int STRING = 10;
    static final int UNION = 2;
    static final int UTF16_MAX = 1114111;
    private static final String[] blockNames;
    static final String blockRanges = "\u0000\u007f\u0080ÿĀſƀɏɐʯʰ˿̀ͯͰϿЀӿ\u0530֏\u0590\u05ff\u0600ۿ܀ݏހ\u07bfऀॿঀ\u09ff\u0a00\u0a7f\u0a80૿\u0b00\u0b7f\u0b80\u0bffఀ౿ಀ\u0cffഀൿ\u0d80\u0dff\u0e00\u0e7f\u0e80\u0effༀ\u0fffက႟Ⴀჿᄀᇿሀ\u137fᎠ\u13ff᐀ᙿ\u1680\u169fᚠ\u16ffក\u17ff᠀\u18afḀỿἀ\u1fff\u2000\u206f⁰\u209f₠\u20cf⃐\u20ff℀⅏⅐\u218f←⇿∀⋿⌀⏿␀\u243f⑀\u245f①⓿─╿▀▟■◿☀⛿✀➿⠀⣿⺀\u2eff⼀\u2fdf⿰⿿\u3000〿\u3040ゟ゠ヿ\u3100ㄯ\u3130\u318f㆐㆟ㆠㆿ㈀㋿㌀㏿㐀䶵一鿿ꀀ\ua48f꒐\ua4cf가힣\ue000\uf8ff豈\ufaffﬀﭏﭐ﷿︠︯︰﹏﹐\ufe6fﹰ\ufefe\ufeff\ufeff\uff00\uffef";
    private static volatile Map<String, Token> categories = null;
    private static volatile Map<String, Token> categories2 = null;
    private static final String[] categoryNames;
    private static final Object lock;
    static final int[] nonBMPBlockRanges;
    static final Set<String> nonxs;
    private static final long serialVersionUID = 8484976002585487481L;
    static Token token_0to9 = null;
    private static Token token_ccs = null;
    private static Token token_grapheme = null;
    static Token token_not_0to9 = null;
    static Token token_not_spaces = null;
    static Token token_not_wordchars = null;
    static Token token_spaces = null;
    static Token token_wordchars = null;
    static int tokens = 0;
    static final String viramaString = "्্੍્୍்్್്ฺ྄";
    final int type;
    static Token token_empty = new Token(7);
    static Token token_linebeginning = createAnchor(94);
    static Token token_linebeginning2 = createAnchor(64);
    static Token token_lineend = createAnchor(36);
    static Token token_stringbeginning = createAnchor(65);
    static Token token_stringend = createAnchor(122);
    static Token token_stringend2 = createAnchor(90);
    static Token token_wordedge = createAnchor(98);
    static Token token_not_wordedge = createAnchor(66);
    static Token token_wordbeginning = createAnchor(60);
    static Token token_wordend = createAnchor(62);
    static Token token_dot = new Token(11);

    public static class CharToken extends Token implements Serializable {
        private static final long serialVersionUID = -4394272816279496989L;
        final int chardata;

        public CharToken(int i, int i2) {
            super(i);
            this.chardata = i2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int getChar() {
            return this.chardata;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public boolean match(int i) {
            if (this.type == 0) {
                return i == this.chardata;
            }
            uv.a("NFAArrow#match(): Internal error: ", this.type);
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            int i2 = this.type;
            if (i2 != 0) {
                if (i2 != 8) {
                    return null;
                }
                if (this == Token.token_linebeginning || this == Token.token_lineend) {
                    return "" + ((char) this.chardata);
                }
                return "\\" + ((char) this.chardata);
            }
            int i3 = this.chardata;
            if (i3 == 9) {
                return "\\t";
            }
            if (i3 == 10) {
                return "\\n";
            }
            if (i3 == 12) {
                return "\\f";
            }
            if (i3 == 13) {
                return "\\r";
            }
            if (i3 == 27) {
                return "\\e";
            }
            if (i3 != 46 && i3 != 63 && i3 != 91 && i3 != 92 && i3 != 123 && i3 != 124) {
                switch (i3) {
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                        break;
                    default:
                        int i4 = this.chardata;
                        if (i3 < 65536) {
                            return "" + ((char) i4);
                        }
                        String str = "0" + Integer.toHexString(i4);
                        return "\\v".concat(str.substring(str.length() - 6, str.length()));
                }
            }
            return "\\" + ((char) this.chardata);
        }
    }

    public static class ClosureToken extends Token implements Serializable {
        private static final long serialVersionUID = 1308971930673997452L;
        final Token child;
        int max;
        int min;

        public ClosureToken(int i, Token token) {
            super(i);
            this.child = token;
            setMin(-1);
            setMax(-1);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            return this.child;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public final int getMax() {
            return this.max;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public final int getMin() {
            return this.min;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public final void setMax(int i) {
            this.max = i;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public final void setMin(int i) {
            this.min = i;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            return 1;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            if (this.type == 3) {
                if (getMin() < 0 && getMax() < 0) {
                    return this.child.toString(i) + "*";
                }
                if (getMin() == getMax()) {
                    return this.child.toString(i) + "{" + getMin() + "}";
                }
                if (getMin() >= 0 && getMax() >= 0) {
                    return this.child.toString(i) + "{" + getMin() + "," + getMax() + "}";
                }
                if (getMin() < 0 || getMax() >= 0) {
                    bf9.a("Token#toString(): CLOSURE ", getMin(), ", ", getMax());
                    return null;
                }
                return this.child.toString(i) + "{" + getMin() + ",}";
            }
            if (getMin() < 0 && getMax() < 0) {
                return this.child.toString(i) + "*?";
            }
            if (getMin() == getMax()) {
                return this.child.toString(i) + "{" + getMin() + "}?";
            }
            if (getMin() >= 0 && getMax() >= 0) {
                return this.child.toString(i) + "{" + getMin() + "," + getMax() + "}?";
            }
            if (getMin() < 0 || getMax() >= 0) {
                bf9.a("Token#toString(): NONGREEDYCLOSURE ", getMin(), ", ", getMax());
                return null;
            }
            return this.child.toString(i) + "{" + getMin() + ",}?";
        }
    }

    public static class ConcatToken extends Token implements Serializable {
        private static final long serialVersionUID = 8717321425541346381L;
        final Token child;
        final Token child2;

        public ConcatToken(Token token, Token token2) {
            super(1);
            this.child = token;
            this.child2 = token2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            return i == 0 ? this.child : this.child2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            return 2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            Token token = this.child2;
            if (token.type == 3 && token.getChild(0) == this.child) {
                return this.child.toString(i) + "+";
            }
            Token token2 = this.child2;
            if (token2.type == 9 && token2.getChild(0) == this.child) {
                return this.child.toString(i) + "+?";
            }
            return this.child.toString(i) + this.child2.toString(i);
        }
    }

    public static class ConditionToken extends Token implements Serializable {
        private static final long serialVersionUID = 4353765277910594411L;
        final Token condition;
        final Token no;
        final int refNumber;
        final Token yes;

        public ConditionToken(int i, Token token, Token token2, Token token3) {
            super(26);
            this.refNumber = i;
            this.condition = token;
            this.yes = token2;
            this.no = token3;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            if (i == 0) {
                return this.yes;
            }
            if (i == 1) {
                return this.no;
            }
            gke.a("Internal Error: ", i);
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            return this.no == null ? 1 : 2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            String str;
            if (this.refNumber > 0) {
                str = "(?(" + this.refNumber + ")";
            } else {
                int i2 = this.condition.type;
                Token token = this.condition;
                if (i2 == 8) {
                    str = "(?(" + token + ")";
                } else {
                    str = "(?" + token;
                }
            }
            if (this.no == null) {
                return str + this.yes + ")";
            }
            return str + this.yes + "|" + this.no + ")";
        }
    }

    public static class FixedStringContainer {
        Token token = null;
        int options = 0;
    }

    public static class ModifierToken extends Token implements Serializable {
        private static final long serialVersionUID = -9114536559696480356L;
        final int add;
        final Token child;
        final int mask;

        public ModifierToken(Token token, int i, int i2) {
            super(25);
            this.child = token;
            this.add = i;
            this.mask = i2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            return this.child;
        }

        public int getOptions() {
            return this.add;
        }

        public int getOptionsMask() {
            return this.mask;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            return 1;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            StringBuilder sb = new StringBuilder("(?");
            int i2 = this.add;
            sb.append(i2 == 0 ? "" : REUtil.createOptionString(i2));
            int i3 = this.mask;
            sb.append(i3 != 0 ? REUtil.createOptionString(i3) : "");
            sb.append(":");
            sb.append(this.child.toString(i));
            sb.append(")");
            return sb.toString();
        }
    }

    public static class ParenToken extends Token implements Serializable {
        private static final long serialVersionUID = -5938014719827987704L;
        final Token child;
        final int parennumber;

        public ParenToken(int i, Token token, int i2) {
            super(i);
            this.child = token;
            this.parennumber = i2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            return this.child;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int getParenNumber() {
            return this.parennumber;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            return 1;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            int i2 = this.type;
            if (i2 == 6) {
                int i3 = this.parennumber;
                Token token = this.child;
                if (i3 == 0) {
                    return "(?:" + token.toString(i) + ")";
                }
                return "(" + token.toString(i) + ")";
            }
            switch (i2) {
                case 20:
                    return "(?=" + this.child.toString(i) + ")";
                case 21:
                    return "(?!" + this.child.toString(i) + ")";
                case 22:
                    return "(?<=" + this.child.toString(i) + ")";
                case 23:
                    return "(?<!" + this.child.toString(i) + ")";
                case 24:
                    return "(?>" + this.child.toString(i) + ")";
                default:
                    return null;
            }
        }
    }

    public static class StringToken extends Token implements Serializable {
        private static final long serialVersionUID = -4614366944218504172L;
        final int refNumber;
        String string;

        public StringToken(int i, String str, int i2) {
            super(i);
            this.string = str;
            this.refNumber = i2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int getReferenceNumber() {
            return this.refNumber;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String getString() {
            return this.string;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(int i) {
            if (this.type != 12) {
                return REUtil.quoteMeta(this.string);
            }
            return "\\" + this.refNumber;
        }
    }

    public static class UnionToken extends Token implements Serializable {
        private static final ObjectStreamField[] serialPersistentFields = {new ObjectStreamField(Constants.ELEMNAME_CHILDREN_STRING, Vector.class)};
        private static final long serialVersionUID = -2568843945989489861L;
        List<Token> children;

        public UnionToken(int i) {
            super(i);
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            Vector vector = (Vector) objectInputStream.readFields().get(Constants.ELEMNAME_CHILDREN_STRING, (Object) null);
            if (vector != null) {
                this.children = new ArrayList(vector);
            }
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.putFields().put(Constants.ELEMNAME_CHILDREN_STRING, this.children == null ? null : new Vector(this.children));
            objectOutputStream.writeFields();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public void addChild(Token token) {
            int i;
            StringBuilder sb;
            if (token == null) {
                return;
            }
            if (this.children == null) {
                this.children = new ArrayList();
            }
            if (this.type == 2) {
                this.children.add(token);
                return;
            }
            if (token.type == 1) {
                for (int i2 = 0; i2 < token.size(); i2++) {
                    addChild(token.getChild(i2));
                }
                return;
            }
            int size = this.children.size();
            List<Token> list = this.children;
            if (size == 0) {
                list.add(token);
                return;
            }
            int i3 = size - 1;
            Token tokenCreateString = list.get(i3);
            int i4 = tokenCreateString.type;
            if ((i4 != 0 && i4 != 10) || ((i = token.type) != 0 && i != 10)) {
                this.children.add(token);
                return;
            }
            int length = i == 0 ? 2 : token.getString().length();
            if (tokenCreateString.type == 0) {
                sb = new StringBuilder(length + 2);
                int i5 = tokenCreateString.getChar();
                if (i5 >= 65536) {
                    sb.append(REUtil.decomposeToSurrogates(i5));
                } else {
                    sb.append((char) i5);
                }
                tokenCreateString = Token.createString(null);
                this.children.set(i3, tokenCreateString);
            } else {
                sb = new StringBuilder(tokenCreateString.getString().length() + length);
                sb.append(tokenCreateString.getString());
            }
            if (token.type == 0) {
                int i6 = token.getChar();
                if (i6 >= 65536) {
                    sb.append(REUtil.decomposeToSurrogates(i6));
                } else {
                    sb.append((char) i6);
                }
            } else {
                sb.append(token.getString());
            }
            ((StringToken) tokenCreateString).string = new String(sb);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public Token getChild(int i) {
            return this.children.get(i);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public int size() {
            List<Token> list = this.children;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Token
        public String toString(final int i) {
            int i2 = this.type;
            List<Token> list = this.children;
            if (i2 != 1) {
                if (list.size() == 2 && getChild(1).type == 7) {
                    return getChild(0).toString(i) + "?";
                }
                if (this.children.size() == 2 && getChild(0).type == 7) {
                    return getChild(1).toString(i) + "??";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(this.children.get(0).toString(i));
                for (int i3 = 1; i3 < this.children.size(); i3++) {
                    sb.append('|');
                    sb.append(this.children.get(i3).toString(i));
                }
                return sb.toString();
            }
            if (list.size() != 2) {
                final StringBuilder sb2 = new StringBuilder();
                this.children.stream().forEach(new Consumer() { // from class: com.sun.org.apache.xerces.internal.impl.xpath.regex.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        sb2.append(((Token) obj).toString(i));
                    }
                });
                return sb2.toString();
            }
            Token child = getChild(0);
            Token child2 = getChild(1);
            if (child2.type == 3 && child2.getChild(0) == child) {
                return child.toString(i) + "+";
            }
            if (child2.type == 9 && child2.getChild(0) == child) {
                return child.toString(i) + "+?";
            }
            return child.toString(i) + child2.toString(i);
        }
    }

    static {
        RangeToken rangeTokenCreateRange = createRange();
        token_0to9 = rangeTokenCreateRange;
        rangeTokenCreateRange.addRange(48, 57);
        RangeToken rangeTokenCreateRange2 = createRange();
        token_wordchars = rangeTokenCreateRange2;
        rangeTokenCreateRange2.addRange(48, 57);
        token_wordchars.addRange(65, 90);
        token_wordchars.addRange(95, 95);
        token_wordchars.addRange(97, 122);
        RangeToken rangeTokenCreateRange3 = createRange();
        token_spaces = rangeTokenCreateRange3;
        rangeTokenCreateRange3.addRange(9, 9);
        token_spaces.addRange(10, 10);
        token_spaces.addRange(12, 12);
        token_spaces.addRange(13, 13);
        token_spaces.addRange(32, 32);
        token_not_0to9 = complementRanges(token_0to9);
        token_not_wordchars = complementRanges(token_wordchars);
        token_not_spaces = complementRanges(token_spaces);
        categories = null;
        categories2 = null;
        lock = new Object();
        categoryNames = new String[]{"Cn", "Lu", "Ll", "Lt", "Lm", "Lo", "Mn", "Me", "Mc", "Nd", "Nl", "No", "Zs", "Zl", "Zp", "Cc", "Cf", null, "Co", "Cs", "Pd", "Ps", "Pe", "Pc", "Po", "Sm", "Sc", "Sk", "So", "Pi", "Pf", "L", "M", "N", com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.HASIDCALL_INDEX_SIG, "C", "P", "S"};
        blockNames = new String[]{"Basic Latin", "Latin-1 Supplement", "Latin Extended-A", "Latin Extended-B", "IPA Extensions", "Spacing Modifier Letters", "Combining Diacritical Marks", "Greek", "Cyrillic", "Armenian", "Hebrew", "Arabic", "Syriac", "Thaana", "Devanagari", "Bengali", "Gurmukhi", "Gujarati", "Oriya", "Tamil", "Telugu", "Kannada", "Malayalam", "Sinhala", "Thai", "Lao", "Tibetan", "Myanmar", "Georgian", "Hangul Jamo", "Ethiopic", "Cherokee", "Unified Canadian Aboriginal Syllabics", "Ogham", "Runic", "Khmer", "Mongolian", "Latin Extended Additional", "Greek Extended", "General Punctuation", "Superscripts and Subscripts", "Currency Symbols", "Combining Marks for Symbols", "Letterlike Symbols", "Number Forms", "Arrows", "Mathematical Operators", "Miscellaneous Technical", "Control Pictures", "Optical Character Recognition", "Enclosed Alphanumerics", "Box Drawing", "Block Elements", "Geometric Shapes", "Miscellaneous Symbols", "Dingbats", "Braille Patterns", "CJK Radicals Supplement", "Kangxi Radicals", "Ideographic Description Characters", "CJK Symbols and Punctuation", "Hiragana", "Katakana", "Bopomofo", "Hangul Compatibility Jamo", "Kanbun", "Bopomofo Extended", "Enclosed CJK Letters and Months", "CJK Compatibility", "CJK Unified Ideographs Extension A", "CJK Unified Ideographs", "Yi Syllables", "Yi Radicals", "Hangul Syllables", "Private Use", "CJK Compatibility Ideographs", "Alphabetic Presentation Forms", "Arabic Presentation Forms-A", "Combining Half Marks", "CJK Compatibility Forms", "Small Form Variants", "Arabic Presentation Forms-B", "Specials", "Halfwidth and Fullwidth Forms", "Old Italic", "Gothic", "Deseret", "Byzantine Musical Symbols", "Musical Symbols", "Mathematical Alphanumeric Symbols", "CJK Unified Ideographs Extension B", "CJK Compatibility Ideographs Supplement", "Tags"};
        nonBMPBlockRanges = new int[]{66304, 66351, 66352, 66383, 66560, 66639, 118784, 119039, 119040, 119295, 119808, 120831, 131072, 173782, 194560, 195103, 917504, 917631};
        nonxs = Collections.synchronizedSet(new HashSet());
        token_grapheme = null;
        token_ccs = null;
    }

    public Token(int i) {
        this.type = i;
    }

    public static Token complementRanges(Token token) {
        return RangeToken.complementRanges(token);
    }

    private static CharToken createAnchor(int i) {
        tokens++;
        return new CharToken(8, i);
    }

    public static StringToken createBackReference(int i) {
        tokens++;
        return new StringToken(12, null, i);
    }

    public static CharToken createChar(int i) {
        tokens++;
        return new CharToken(0, i);
    }

    public static ClosureToken createClosure(Token token) {
        tokens++;
        return new ClosureToken(3, token);
    }

    public static ConcatToken createConcat(Token token, Token token2) {
        tokens++;
        return new ConcatToken(token, token2);
    }

    public static ConditionToken createCondition(int i, Token token, Token token2, Token token3) {
        tokens++;
        return new ConditionToken(i, token, token2, token3);
    }

    public static Token createEmpty() {
        return token_empty;
    }

    public static ParenToken createLook(int i, Token token) {
        tokens++;
        return new ParenToken(i, token, 0);
    }

    public static ModifierToken createModifierGroup(Token token, int i, int i2) {
        tokens++;
        return new ModifierToken(token, i, i2);
    }

    public static ClosureToken createNGClosure(Token token) {
        tokens++;
        return new ClosureToken(9, token);
    }

    public static RangeToken createNRange() {
        tokens++;
        return new RangeToken(5);
    }

    public static ParenToken createParen(Token token, int i) {
        tokens++;
        return new ParenToken(6, token, i);
    }

    public static RangeToken createRange() {
        tokens++;
        return new RangeToken(4);
    }

    public static StringToken createString(String str) {
        tokens++;
        return new StringToken(10, str, 0);
    }

    public static UnionToken createUnion() {
        tokens++;
        return new UnionToken(2);
    }

    public static synchronized Token getCombiningCharacterSequence() {
        Token token = token_ccs;
        if (token != null) {
            return token;
        }
        ConcatToken concatTokenCreateConcat = createConcat(getRange("M", false), createClosure(getRange("M", true)));
        token_ccs = concatTokenCreateConcat;
        return concatTokenCreateConcat;
    }

    public static synchronized Token getGraphemePattern() {
        try {
            Token token = token_grapheme;
            if (token != null) {
                return token;
            }
            RangeToken rangeTokenCreateRange = createRange();
            rangeTokenCreateRange.mergeRanges(getRange("ASSIGNED", true));
            rangeTokenCreateRange.subtractRanges(getRange("M", true));
            rangeTokenCreateRange.subtractRanges(getRange("C", true));
            RangeToken rangeTokenCreateRange2 = createRange();
            for (int i = 0; i < 11; i++) {
                rangeTokenCreateRange2.addRange(i, i);
            }
            RangeToken rangeTokenCreateRange3 = createRange();
            rangeTokenCreateRange3.mergeRanges(getRange("M", true));
            rangeTokenCreateRange3.addRange(4448, 4607);
            rangeTokenCreateRange3.addRange(65438, 65439);
            UnionToken unionTokenCreateUnion = createUnion();
            unionTokenCreateUnion.addChild(rangeTokenCreateRange);
            unionTokenCreateUnion.addChild(token_empty);
            UnionToken unionTokenCreateUnion2 = createUnion();
            unionTokenCreateUnion2.addChild(createConcat(rangeTokenCreateRange2, getRange("L", true)));
            unionTokenCreateUnion2.addChild(rangeTokenCreateRange3);
            ConcatToken concatTokenCreateConcat = createConcat(unionTokenCreateUnion, createClosure(unionTokenCreateUnion2));
            token_grapheme = concatTokenCreateConcat;
            return concatTokenCreateConcat;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static RangeToken getRange(String str, boolean z) {
        char c;
        Map<String, Token> mapUnmodifiableMap = categories;
        if (mapUnmodifiableMap == null) {
            synchronized (lock) {
                try {
                    mapUnmodifiableMap = categories;
                    if (mapUnmodifiableMap == null) {
                        HashMap map = new HashMap();
                        HashMap map2 = new HashMap();
                        int length = categoryNames.length;
                        Token[] tokenArr = new Token[length];
                        for (int i = 0; i < length; i++) {
                            tokenArr[i] = createRange();
                        }
                        for (int i2 = 0; i2 < 65536; i2++) {
                            int type = Character.getType((char) i2);
                            if (type == 21 || type == 22) {
                                if (i2 == 171 || i2 == 8216 || i2 == 8219 || i2 == 8220 || i2 == 8223 || i2 == 8249) {
                                    type = 29;
                                }
                                if (i2 == 187 || i2 == 8217 || i2 == 8221 || i2 == 8250) {
                                    type = 30;
                                }
                            }
                            tokenArr[type].addRange(i2, i2);
                            switch (type) {
                                case 0:
                                case 15:
                                case 16:
                                case 18:
                                case 19:
                                    c = '#';
                                    break;
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                    c = 31;
                                    break;
                                case 6:
                                case 7:
                                case 8:
                                    c = ' ';
                                    break;
                                case 9:
                                case 10:
                                case 11:
                                    c = '!';
                                    break;
                                case 12:
                                case 13:
                                case 14:
                                    c = '\"';
                                    break;
                                case 17:
                                default:
                                    throw new RuntimeException("org.apache.xerces.utils.regex.Token#getRange(): Unknown Unicode category: " + type);
                                case 20:
                                case 21:
                                case 22:
                                case 23:
                                case 24:
                                case 29:
                                case 30:
                                    c = '$';
                                    break;
                                case 25:
                                case 26:
                                case 27:
                                case 28:
                                    c = '%';
                                    break;
                            }
                            tokenArr[c].addRange(i2, i2);
                        }
                        tokenArr[0].addRange(65536, UTF16_MAX);
                        for (int i3 = 0; i3 < length; i3++) {
                            String[] strArr = categoryNames;
                            if (strArr[i3] != null) {
                                if (i3 == 0) {
                                    tokenArr[i3].addRange(65536, UTF16_MAX);
                                }
                                map.put(strArr[i3], tokenArr[i3]);
                                map2.put(strArr[i3], complementRanges(tokenArr[i3]));
                            }
                        }
                        StringBuilder sb = new StringBuilder(50);
                        int i4 = 0;
                        while (true) {
                            String[] strArr2 = blockNames;
                            if (i4 < strArr2.length) {
                                RangeToken rangeTokenCreateRange = createRange();
                                if (i4 < 84) {
                                    int i5 = i4 * 2;
                                    rangeTokenCreateRange.addRange(blockRanges.charAt(i5), blockRanges.charAt(i5 + 1));
                                } else {
                                    int i6 = (i4 - 84) * 2;
                                    int[] iArr = nonBMPBlockRanges;
                                    rangeTokenCreateRange.addRange(iArr[i6], iArr[i6 + 1]);
                                }
                                String str2 = strArr2[i4];
                                if (str2.equals("Specials")) {
                                    rangeTokenCreateRange.addRange(ClassReader.INITIAL_BUFFER_SIZE, 65533);
                                }
                                if (str2.equals("Private Use")) {
                                    rangeTokenCreateRange.addRange(WinNT.STANDARD_RIGHTS_REQUIRED, 1048573);
                                    rangeTokenCreateRange.addRange(1048576, 1114109);
                                }
                                map.put(str2, rangeTokenCreateRange);
                                map2.put(str2, complementRanges(rangeTokenCreateRange));
                                sb.setLength(0);
                                sb.append("Is");
                                if (str2.indexOf(32) >= 0) {
                                    for (int i7 = 0; i7 < str2.length(); i7++) {
                                        if (str2.charAt(i7) != ' ') {
                                            sb.append(str2.charAt(i7));
                                        }
                                    }
                                } else {
                                    sb.append(str2);
                                }
                                setAlias(map, map2, sb.toString(), str2, true);
                                i4++;
                            } else {
                                setAlias(map, map2, "ASSIGNED", "Cn", false);
                                setAlias(map, map2, "UNASSIGNED", "Cn", true);
                                RangeToken rangeTokenCreateRange2 = createRange();
                                rangeTokenCreateRange2.addRange(0, UTF16_MAX);
                                map.put("ALL", rangeTokenCreateRange2);
                                map2.put("ALL", complementRanges(rangeTokenCreateRange2));
                                registerNonXS("ASSIGNED");
                                registerNonXS("UNASSIGNED");
                                registerNonXS("ALL");
                                RangeToken rangeTokenCreateRange3 = createRange();
                                rangeTokenCreateRange3.mergeRanges(tokenArr[1]);
                                rangeTokenCreateRange3.mergeRanges(tokenArr[2]);
                                rangeTokenCreateRange3.mergeRanges(tokenArr[5]);
                                map.put("IsAlpha", rangeTokenCreateRange3);
                                map2.put("IsAlpha", complementRanges(rangeTokenCreateRange3));
                                registerNonXS("IsAlpha");
                                RangeToken rangeTokenCreateRange4 = createRange();
                                rangeTokenCreateRange4.mergeRanges(rangeTokenCreateRange3);
                                rangeTokenCreateRange4.mergeRanges(tokenArr[9]);
                                map.put("IsAlnum", rangeTokenCreateRange4);
                                map2.put("IsAlnum", complementRanges(rangeTokenCreateRange4));
                                registerNonXS("IsAlnum");
                                RangeToken rangeTokenCreateRange5 = createRange();
                                rangeTokenCreateRange5.mergeRanges(token_spaces);
                                rangeTokenCreateRange5.mergeRanges(tokenArr[34]);
                                map.put("IsSpace", rangeTokenCreateRange5);
                                map2.put("IsSpace", complementRanges(rangeTokenCreateRange5));
                                registerNonXS("IsSpace");
                                RangeToken rangeTokenCreateRange6 = createRange();
                                rangeTokenCreateRange6.mergeRanges(rangeTokenCreateRange4);
                                rangeTokenCreateRange6.addRange(95, 95);
                                map.put("IsWord", rangeTokenCreateRange6);
                                map2.put("IsWord", complementRanges(rangeTokenCreateRange6));
                                registerNonXS("IsWord");
                                RangeToken rangeTokenCreateRange7 = createRange();
                                rangeTokenCreateRange7.addRange(0, 127);
                                map.put("IsASCII", rangeTokenCreateRange7);
                                map2.put("IsASCII", complementRanges(rangeTokenCreateRange7));
                                registerNonXS("IsASCII");
                                RangeToken rangeTokenCreateRange8 = createRange();
                                rangeTokenCreateRange8.mergeRanges(tokenArr[35]);
                                rangeTokenCreateRange8.addRange(32, 32);
                                map.put("IsGraph", complementRanges(rangeTokenCreateRange8));
                                map2.put("IsGraph", rangeTokenCreateRange8);
                                registerNonXS("IsGraph");
                                RangeToken rangeTokenCreateRange9 = createRange();
                                rangeTokenCreateRange9.addRange(48, 57);
                                rangeTokenCreateRange9.addRange(65, 70);
                                rangeTokenCreateRange9.addRange(97, 102);
                                map.put("IsXDigit", complementRanges(rangeTokenCreateRange9));
                                map2.put("IsXDigit", rangeTokenCreateRange9);
                                registerNonXS("IsXDigit");
                                setAlias(map, map2, "IsDigit", "Nd", true);
                                setAlias(map, map2, "IsUpper", "Lu", true);
                                setAlias(map, map2, "IsLower", "Ll", true);
                                setAlias(map, map2, "IsCntrl", "C", true);
                                setAlias(map, map2, "IsPrint", "C", false);
                                setAlias(map, map2, "IsPunct", "P", true);
                                registerNonXS("IsDigit");
                                registerNonXS("IsUpper");
                                registerNonXS("IsLower");
                                registerNonXS("IsCntrl");
                                registerNonXS("IsPrint");
                                registerNonXS("IsPunct");
                                setAlias(map, map2, "alpha", "IsAlpha", true);
                                setAlias(map, map2, "alnum", "IsAlnum", true);
                                setAlias(map, map2, "ascii", "IsASCII", true);
                                setAlias(map, map2, "cntrl", "IsCntrl", true);
                                setAlias(map, map2, Constants.ATTRNAME_DIGIT, "IsDigit", true);
                                setAlias(map, map2, "graph", "IsGraph", true);
                                setAlias(map, map2, "lower", "IsLower", true);
                                setAlias(map, map2, "print", "IsPrint", true);
                                setAlias(map, map2, "punct", "IsPunct", true);
                                setAlias(map, map2, "space", "IsSpace", true);
                                setAlias(map, map2, "upper", "IsUpper", true);
                                setAlias(map, map2, "word", "IsWord", true);
                                setAlias(map, map2, "xdigit", "IsXDigit", true);
                                registerNonXS("alpha");
                                registerNonXS("alnum");
                                registerNonXS("ascii");
                                registerNonXS("cntrl");
                                registerNonXS(Constants.ATTRNAME_DIGIT);
                                registerNonXS("graph");
                                registerNonXS("lower");
                                registerNonXS("print");
                                registerNonXS("punct");
                                registerNonXS("space");
                                registerNonXS("upper");
                                registerNonXS("word");
                                registerNonXS("xdigit");
                                mapUnmodifiableMap = Collections.unmodifiableMap(map);
                                categories = mapUnmodifiableMap;
                                categories2 = Collections.unmodifiableMap(map2);
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return z ? (RangeToken) mapUnmodifiableMap.get(str) : (RangeToken) categories2.get(str);
    }

    public static boolean isRegisterNonXS(String str) {
        return nonxs.contains(str);
    }

    private static final boolean isSet(int i, int i2) {
        return (i & i2) == i2;
    }

    private final boolean isShorterThan(Token token) {
        if (token == null) {
            return false;
        }
        if (this.type != 10) {
            uv.a("Internal Error: Illegal type: ", this.type);
            return false;
        }
        int length = getString().length();
        if (token.type == 10) {
            return length < token.getString().length();
        }
        uv.a("Internal Error: Illegal type: ", token.type);
        return false;
    }

    public static void registerNonXS(String str) {
        nonxs.add(str);
    }

    private static void setAlias(Map<String, Token> map, Map<String, Token> map2, String str, String str2, boolean z) {
        Token token = map.get(str2);
        Token token2 = map2.get(str2);
        if (z) {
            map.put(str, token);
            map2.put(str, token2);
        } else {
            map2.put(str, token);
            map.put(str, token2);
        }
    }

    public void addChild(Token token) {
        throw new RuntimeException("Not supported.");
    }

    public void addRange(int i, int i2) {
        throw new RuntimeException("Not supported.");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:39:0x009f  */
    public final int analyzeFirstCharacter(RangeToken rangeToken, int i) {
        int i2 = this.type;
        switch (i2) {
            case 0:
                int i3 = getChar();
                rangeToken.addRange(i3, i3);
                if (i3 < 65536 && isSet(i, 2)) {
                    char upperCase = Character.toUpperCase((char) i3);
                    rangeToken.addRange(upperCase, upperCase);
                    char lowerCase = Character.toLowerCase(upperCase);
                    rangeToken.addRange(lowerCase, lowerCase);
                }
                return 1;
            case 1:
                int iAnalyzeFirstCharacter = 0;
                for (int i4 = 0; i4 < size(); i4++) {
                    iAnalyzeFirstCharacter = getChild(i4).analyzeFirstCharacter(rangeToken, i);
                    if (iAnalyzeFirstCharacter != 0) {
                        return iAnalyzeFirstCharacter;
                    }
                }
                return iAnalyzeFirstCharacter;
            case 2:
                if (size() == 0) {
                    return 0;
                }
                int iAnalyzeFirstCharacter2 = 0;
                boolean z = false;
                for (int i5 = 0; i5 < size() && (iAnalyzeFirstCharacter2 = getChild(i5).analyzeFirstCharacter(rangeToken, i)) != 2; i5++) {
                    if (iAnalyzeFirstCharacter2 == 0) {
                        z = true;
                    }
                }
                if (z) {
                    return 0;
                }
                return iAnalyzeFirstCharacter2;
            case 3:
            case 9:
                getChild(0).analyzeFirstCharacter(rangeToken, i);
                return 0;
            case 4:
                rangeToken.mergeRanges(this);
                return 1;
            case 5:
                rangeToken.mergeRanges(complementRanges(this));
                return 1;
            case 6:
                return getChild(0).analyzeFirstCharacter(rangeToken, i);
            case 7:
            case 8:
                return 0;
            case 10:
                int iCharAt = getString().charAt(0);
                if (REUtil.isHighSurrogate(iCharAt) && getString().length() >= 2) {
                    char cCharAt = getString().charAt(1);
                    if (REUtil.isLowSurrogate(cCharAt)) {
                        iCharAt = REUtil.composeFromSurrogates(iCharAt, cCharAt);
                    }
                }
                rangeToken.addRange(iCharAt, iCharAt);
                if (iCharAt < 65536 && isSet(i, 2)) {
                    char upperCase2 = Character.toUpperCase((char) iCharAt);
                    rangeToken.addRange(upperCase2, upperCase2);
                    char lowerCase2 = Character.toLowerCase(upperCase2);
                    rangeToken.addRange(lowerCase2, lowerCase2);
                }
                return 1;
            case 11:
                return 2;
            case 12:
                rangeToken.addRange(0, UTF16_MAX);
                return 2;
            default:
                switch (i2) {
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        return 0;
                    case 24:
                        return getChild(0).analyzeFirstCharacter(rangeToken, i);
                    case 25:
                        ModifierToken modifierToken = (ModifierToken) this;
                        return getChild(0).analyzeFirstCharacter(rangeToken, (i | modifierToken.getOptions()) & (~modifierToken.getOptionsMask()));
                    case 26:
                        int iAnalyzeFirstCharacter3 = getChild(0).analyzeFirstCharacter(rangeToken, i);
                        if (size() == 1) {
                            return 0;
                        }
                        if (iAnalyzeFirstCharacter3 == 2) {
                            return iAnalyzeFirstCharacter3;
                        }
                        int iAnalyzeFirstCharacter4 = getChild(1).analyzeFirstCharacter(rangeToken, i);
                        if (iAnalyzeFirstCharacter4 == 2) {
                            return iAnalyzeFirstCharacter4;
                        }
                        return (iAnalyzeFirstCharacter3 == 0 || iAnalyzeFirstCharacter4 == 0) ? 0 : 1;
                    default:
                        uv.a("Token#analyzeHeadCharacter(): Invalid Type: ", this.type);
                        return 0;
                }
        }
    }

    public void compactRanges() {
        throw new RuntimeException("Not supported.");
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002d  */
    /* JADX WARN: Code duplicated, block: B:13:0x0035  */
    public final void findFixedString(FixedStringContainer fixedStringContainer, int i) {
        int i2 = this.type;
        Token token = null;
        switch (i2) {
            case 0:
                fixedStringContainer.token = null;
                break;
            case 1:
                int i3 = 0;
                for (int i4 = 0; i4 < size(); i4++) {
                    getChild(i4).findFixedString(fixedStringContainer, i);
                    if (token == null || token.isShorterThan(fixedStringContainer.token)) {
                        token = fixedStringContainer.token;
                        i3 = fixedStringContainer.options;
                    }
                }
                fixedStringContainer.token = token;
                fixedStringContainer.options = i3;
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
                fixedStringContainer.token = null;
                break;
            case 6:
                getChild(0).findFixedString(fixedStringContainer, i);
                break;
            case 10:
                fixedStringContainer.token = this;
                fixedStringContainer.options = i;
                break;
            default:
                switch (i2) {
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 26:
                        fixedStringContainer.token = null;
                        break;
                    case 24:
                        getChild(0).findFixedString(fixedStringContainer, i);
                        break;
                    case 25:
                        ModifierToken modifierToken = (ModifierToken) this;
                        getChild(0).findFixedString(fixedStringContainer, (i | modifierToken.getOptions()) & (~modifierToken.getOptionsMask()));
                        break;
                    default:
                        uv.a("Token#findFixedString(): Invalid Type: ", this.type);
                        break;
                }
                break;
        }
    }

    public int getChar() {
        return -1;
    }

    public Token getChild(int i) {
        return null;
    }

    public int getMax() {
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0020  */
    /* JADX WARN: Code duplicated, block: B:21:0x0040  */
    /* JADX WARN: Code duplicated, block: B:23:0x0046 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:24:0x0047  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:25:0x004f
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:272)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:237)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:80)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:117)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:109)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:127)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:75)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public final int getMaxLength() {
        /*
            r4 = this;
            int r0 = r4.type
            r1 = 1
            r2 = -1
            r3 = 0
            switch(r0) {
                case 0: goto L80;
                case 1: goto L69;
                case 2: goto L40;
                case 3: goto L2b;
                case 4: goto L29;
                case 5: goto L29;
                case 6: goto L20;
                case 7: goto L1f;
                case 8: goto L1f;
                case 9: goto L2b;
                case 10: goto L16;
                case 11: goto L29;
                case 12: goto L15;
                default: goto L8;
            }
        L8:
            switch(r0) {
                case 20: goto L14;
                case 21: goto L14;
                case 22: goto L14;
                case 23: goto L14;
                case 24: goto L20;
                case 25: goto L20;
                case 26: goto L40;
                default: goto Lb;
            }
        Lb:
            java.lang.String r0 = "Token#getMaxLength(): Invalid Type: "
            int r4 = r4.type
            defpackage.uv.a(r0, r4)
            r4 = 0
            return r4
        L14:
            return r3
        L15:
            return r2
        L16:
            java.lang.String r4 = r4.getString()
            int r4 = r4.length()
            return r4
        L1f:
            return r3
        L20:
            com.sun.org.apache.xerces.internal.impl.xpath.regex.Token r4 = r4.getChild(r3)
            int r4 = r4.getMaxLength()
            return r4
        L29:
            r4 = 2
            return r4
        L2b:
            int r0 = r4.getMax()
            if (r0 < 0) goto L3f
            int r0 = r4.getMax()
            com.sun.org.apache.xerces.internal.impl.xpath.regex.Token r4 = r4.getChild(r3)
            int r4 = r4.getMaxLength()
            int r0 = r0 * r4
            return r0
        L3f:
            return r2
        L40:
            int r0 = r4.size()
            if (r0 != 0) goto L47
            return r3
        L47:
            com.sun.org.apache.xerces.internal.impl.xpath.regex.Token r0 = r4.getChild(r3)
            int r0 = r0.getMaxLength()
        L4f:
            if (r0 < 0) goto L68
            int r3 = r4.size()
            if (r1 >= r3) goto L68
            com.sun.org.apache.xerces.internal.impl.xpath.regex.Token r3 = r4.getChild(r1)
            int r3 = r3.getMaxLength()
            if (r3 >= 0) goto L62
            return r2
        L62:
            if (r3 <= r0) goto L65
            r0 = r3
        L65:
            int r1 = r1 + 1
            goto L4f
        L68:
            return r0
        L69:
            r0 = r3
        L6a:
            int r1 = r4.size()
            if (r3 >= r1) goto L7f
            com.sun.org.apache.xerces.internal.impl.xpath.regex.Token r1 = r4.getChild(r3)
            int r1 = r1.getMaxLength()
            if (r1 >= 0) goto L7b
            return r2
        L7b:
            int r0 = r0 + r1
            int r3 = r3 + 1
            goto L6a
        L7f:
            return r0
        L80:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.xpath.regex.Token.getMaxLength():int");
    }

    public int getMin() {
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001e  */
    /* JADX WARN: Code duplicated, block: B:18:0x003c  */
    /* JADX WARN: Code duplicated, block: B:20:0x0042 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:21:0x0043  */
    /* JADX WARN: Code duplicated, block: B:24:0x0051  */
    /* JADX WARN: Code duplicated, block: B:26:0x005b  */
    /* JADX WARN: Code duplicated, block: B:37:0x005c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013 A[RETURN] */
    public final int getMinLength() {
        int minLength;
        int minLength2;
        int i = this.type;
        switch (i) {
            case 0:
            case 4:
            case 5:
            case 11:
                return 1;
            case 1:
                int minLength3 = 0;
                for (int i2 = 0; i2 < size(); i2++) {
                    minLength3 += getChild(i2).getMinLength();
                }
                return minLength3;
            case 2:
                if (size() == 0) {
                    return 0;
                }
                minLength = getChild(0).getMinLength();
                for (int i3 = 1; i3 < size(); i3++) {
                    minLength2 = getChild(i3).getMinLength();
                    if (minLength2 < minLength) {
                        minLength = minLength2;
                    }
                }
                return minLength;
            case 3:
            case 9:
                if (getMin() >= 0) {
                    return getMin() * getChild(0).getMinLength();
                }
                return 0;
            case 6:
                return getChild(0).getMinLength();
            case 7:
            case 8:
                return 0;
            case 10:
                return getString().length();
            case 12:
                return 0;
            default:
                switch (i) {
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        return 0;
                    case 24:
                    case 25:
                        return getChild(0).getMinLength();
                    case 26:
                        if (size() == 0) {
                            return 0;
                        }
                        minLength = getChild(0).getMinLength();
                        while (i3 < size()) {
                            minLength2 = getChild(i3).getMinLength();
                            if (minLength2 < minLength) {
                                minLength = minLength2;
                            }
                        }
                        return minLength;
                    default:
                        uv.a("Token#getMinLength(): Invalid Type: ", this.type);
                        return 0;
                }
        }
    }

    public int getParenNumber() {
        return 0;
    }

    public int getReferenceNumber() {
        return 0;
    }

    public String getString() {
        return null;
    }

    public void intersectRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    public boolean match(int i) {
        throw new RuntimeException("NFAArrow#match(): Internal error: " + this.type);
    }

    public void mergeRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    public void setMax(int i) {
    }

    public void setMin(int i) {
    }

    public int size() {
        return 0;
    }

    public void sortRanges() {
        throw new RuntimeException("Not supported.");
    }

    public void subtractRanges(Token token) {
        throw new RuntimeException("Not supported.");
    }

    public String toString(int i) {
        return this.type == 11 ? Constants.ATTRVAL_THIS : "";
    }

    public static UnionToken createConcat() {
        tokens++;
        return new UnionToken(1);
    }

    public String toString() {
        return toString(0);
    }

    public static RangeToken getRange(String str, boolean z, boolean z2) {
        RangeToken range = getRange(str, z);
        if (z2 && range != null && isRegisterNonXS(str)) {
            return null;
        }
        return range;
    }
}
