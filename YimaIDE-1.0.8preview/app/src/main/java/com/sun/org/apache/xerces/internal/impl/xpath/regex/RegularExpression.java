package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import defpackage.uv;
import java.io.Serializable;
import java.text.CharacterIterator;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegularExpression implements Serializable {
    static final int CARRIAGE_RETURN = 13;
    static final boolean DEBUG = false;
    static final int EXTENDED_COMMENT = 16;
    static final int IGNORE_CASE = 2;
    static final int LINE_FEED = 10;
    static final int LINE_SEPARATOR = 8232;
    static final int MULTIPLE_LINES = 8;
    static final int PARAGRAPH_SEPARATOR = 8233;
    static final int PROHIBIT_FIXED_STRING_OPTIMIZATION = 256;
    static final int PROHIBIT_HEAD_CHARACTER_OPTIMIZATION = 128;
    static final int SINGLE_LINE = 4;
    static final int SPECIAL_COMMA = 1024;
    static final int UNICODE_WORD_BOUNDARY = 64;
    static final int USE_UNICODE_CATEGORY = 32;
    private static final int WT_IGNORE = 0;
    private static final int WT_LETTER = 1;
    private static final int WT_OTHER = 2;
    static final int XMLSCHEMA_MODE = 512;
    private static final long serialVersionUID = 6242499334195006401L;
    transient Context context;
    transient RangeToken firstChar;
    transient String fixedString;
    transient boolean fixedStringOnly;
    transient int fixedStringOptions;
    transient BMPattern fixedStringTable;
    boolean hasBackReferences;
    transient int minlength;
    int nofparen;
    transient int numberOfClosures;
    transient Op operations;
    int options;
    String regex;
    Token tokentree;

    public static final class ClosureContext {
        int[] offsets = new int[4];
        int currentIndex = 0;

        private int[] expandOffsets() {
            int[] iArr = this.offsets;
            int[] iArr2 = new int[iArr.length << 1];
            System.arraycopy(iArr, 0, iArr2, 0, this.currentIndex);
            return iArr2;
        }

        public void addOffset(int i) {
            if (this.currentIndex == this.offsets.length) {
                this.offsets = expandOffsets();
            }
            int[] iArr = this.offsets;
            int i2 = this.currentIndex;
            this.currentIndex = i2 + 1;
            iArr[i2] = i;
        }

        public boolean contains(int i) {
            for (int i2 = 0; i2 < this.currentIndex; i2++) {
                if (this.offsets[i2] == i) {
                    return true;
                }
            }
            return false;
        }

        public void reset() {
            this.currentIndex = 0;
        }
    }

    public static abstract class ExpressionTarget {
        public abstract char charAt(int i);

        public abstract boolean regionMatches(boolean z, int i, int i2, int i3, int i4);

        public abstract boolean regionMatches(boolean z, int i, int i2, String str, int i3);
    }

    public RegularExpression(String str, Token token, int i, boolean z, int i2) {
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        this.regex = str;
        this.tokentree = token;
        this.nofparen = i;
        this.options = i2;
        this.hasBackReferences = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.Op, com.sun.org.apache.xerces.internal.impl.xpath.regex.Op$ChildOp] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.Op] */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.Op] */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.Op] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.Op] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression] */
    private Op compile(Token token, Op op, boolean z) {
        Op.ChildOp childOpCreateClosure;
        ?? Compile;
        int i = token.type;
        int i2 = 0;
        switch (i) {
            case 0:
                Op.CharOp charOpCreateChar = Op.createChar(token.getChar());
                charOpCreateChar.next = op;
                return charOpCreateChar;
            case 1:
                if (z) {
                    while (i2 < token.size()) {
                        op = compile(token.getChild(i2), op, true);
                        i2++;
                    }
                    return op;
                }
                for (int size = token.size() - 1; size >= 0; size--) {
                    op = compile(token.getChild(size), op, false);
                }
                return op;
            case 2:
                Op.UnionOp unionOpCreateUnion = Op.createUnion(token.size());
                while (i2 < token.size()) {
                    unionOpCreateUnion.addElement(compile(token.getChild(i2), op, z));
                    i2++;
                }
                return unionOpCreateUnion;
            case 3:
            case 9:
                Token child = token.getChild(0);
                int min = token.getMin();
                int max = token.getMax();
                if (min >= 0 && min == max) {
                    while (i2 < min) {
                        op = compile(child, op, z);
                        i2++;
                    }
                    return op;
                }
                if (min > 0 && max > 0) {
                    max -= min;
                }
                if (max > 0) {
                    Compile = op;
                    int i3 = 0;
                    while (i3 < max) {
                        Op.ChildOp childOpCreateQuestion = Op.createQuestion(token.type == 9);
                        childOpCreateQuestion.next = op;
                        childOpCreateQuestion.setChild(compile(child, Compile, z));
                        i3++;
                        Compile = childOpCreateQuestion;
                    }
                } else {
                    if (token.type == 9) {
                        childOpCreateClosure = Op.createNonGreedyClosure();
                    } else {
                        int i4 = this.numberOfClosures;
                        this.numberOfClosures = i4 + 1;
                        childOpCreateClosure = Op.createClosure(i4);
                    }
                    Compile = childOpCreateClosure;
                    Compile.next = op;
                    Compile.setChild(compile(child, Compile, z));
                }
                if (min > 0) {
                    while (i2 < min) {
                        Compile = compile(child, Compile, z);
                        i2++;
                    }
                }
                return Compile;
            case 4:
            case 5:
                Op.RangeOp rangeOpCreateRange = Op.createRange(token);
                rangeOpCreateRange.next = op;
                return rangeOpCreateRange;
            case 6:
                if (token.getParenNumber() == 0) {
                    return compile(token.getChild(0), op, z);
                }
                if (z) {
                    return Op.createCapture(-token.getParenNumber(), compile(token.getChild(0), Op.createCapture(token.getParenNumber(), op), z));
                }
                return Op.createCapture(token.getParenNumber(), compile(token.getChild(0), Op.createCapture(-token.getParenNumber(), op), z));
            case 7:
                return op;
            case 8:
                Op.CharOp charOpCreateAnchor = Op.createAnchor(token.getChar());
                charOpCreateAnchor.next = op;
                return charOpCreateAnchor;
            case 10:
                Op.StringOp stringOpCreateString = Op.createString(token.getString());
                stringOpCreateString.next = op;
                return stringOpCreateString;
            case 11:
                Op opCreateDot = Op.createDot();
                opCreateDot.next = op;
                return opCreateDot;
            case 12:
                Op.CharOp charOpCreateBackReference = Op.createBackReference(token.getReferenceNumber());
                charOpCreateBackReference.next = op;
                return charOpCreateBackReference;
            default:
                switch (i) {
                    case 20:
                        return Op.createLook(20, op, compile(token.getChild(0), null, false));
                    case 21:
                        return Op.createLook(21, op, compile(token.getChild(0), null, false));
                    case 22:
                        return Op.createLook(22, op, compile(token.getChild(0), null, true));
                    case 23:
                        return Op.createLook(23, op, compile(token.getChild(0), null, true));
                    case 24:
                        return Op.createIndependent(op, compile(token.getChild(0), null, z));
                    case 25:
                        Op opCompile = compile(token.getChild(0), null, z);
                        Token.ModifierToken modifierToken = (Token.ModifierToken) token;
                        return Op.createModifier(op, opCompile, modifierToken.getOptions(), modifierToken.getOptionsMask());
                    case 26:
                        Token.ConditionToken conditionToken = (Token.ConditionToken) token;
                        int i5 = conditionToken.refNumber;
                        Token token2 = conditionToken.condition;
                        Op opCompile2 = token2 == null ? null : compile(token2, null, z);
                        Op opCompile3 = compile(conditionToken.yes, op, z);
                        Token token3 = conditionToken.no;
                        return Op.createCondition(op, i5, opCompile2, opCompile3, token3 != null ? compile(token3, op, z) : null);
                    default:
                        uv.a("Unknown token type: ", token.type);
                        return null;
                }
        }
    }

    private static final int getPreviousWordType(ExpressionTarget expressionTarget, int i, int i2, int i3, int i4) {
        int i5 = i3 - 1;
        int wordType = getWordType(expressionTarget, i, i2, i5, i4);
        while (wordType == 0) {
            i5--;
            wordType = getWordType(expressionTarget, i, i2, i5, i4);
        }
        return wordType;
    }

    private static final int getWordType(ExpressionTarget expressionTarget, int i, int i2, int i3, int i4) {
        if (i3 < i || i3 >= i2) {
            return 2;
        }
        return getWordType0(expressionTarget.charAt(i3), i4);
    }

    private static final int getWordType0(char c, int i) {
        if (!isSet(i, 64)) {
            if (isSet(i, 32)) {
                return Token.getRange("IsWord", true).match(c) ? 1 : 2;
            }
            return isWordChar(c) ? 1 : 2;
        }
        int type = Character.getType(c);
        if (type == 15) {
            switch (c) {
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                    return 2;
                default:
                    return 0;
            }
        }
        if (type != 16) {
            switch (type) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 8:
                case 9:
                case 10:
                case 11:
                    return 1;
                case 6:
                case 7:
                    break;
                default:
                    return 2;
            }
        }
        return 0;
    }

    private static final boolean isEOLChar(int i) {
        return i == 10 || i == 13 || i == 8232 || i == 8233;
    }

    private static final boolean isSet(int i, int i2) {
        return (i & i2) == i2;
    }

    private static final boolean isWordChar(int i) {
        if (i == 95) {
            return true;
        }
        if (i < 48 || i > 122) {
            return false;
        }
        if (i <= 57) {
            return true;
        }
        if (i < 65) {
            return false;
        }
        return i <= 90 || i >= 97;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:179:0x035f  */
    /* JADX WARN: Code duplicated, block: B:182:0x0366  */
    /* JADX WARN: Code duplicated, block: B:189:0x0380  */
    /* JADX WARN: Code duplicated, block: B:190:0x0383  */
    /* JADX WARN: Code duplicated, block: B:193:0x0388  */
    /* JADX WARN: Code duplicated, block: B:200:0x039e  */
    /* JADX WARN: Code duplicated, block: B:204:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:209:0x03bc  */
    /* JADX WARN: Code duplicated, block: B:226:0x0365 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:227:0x03f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:229:0x03ae A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:230:0x03cb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:231:0x03d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:232:0x037c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:233:0x038e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:234:0x0392 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:235:0x0398 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:236:0x03a5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:237:0x0099 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:238:0x0394 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:239:0x0378 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:240:0x03b4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:241:0x03cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:242:0x03d4 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:243:0x0375 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:251:0x035d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:253:0x035d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:254:0x035d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:0x035d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:256:0x035d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:52:0x00fe  */
    /* JADX WARN: Failed to find 'out' block for switch in B:184:0x0375. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:185:0x0378. Please report as an issue. */
    /* JADX WARN: Path cross not found for [B:62:0x014f, B:67:0x016b], limit reached: 248 */
    /* JADX WARN: Switch 'out' block B:178:0x035d for B:184:0x0375 already processed. Defaulting to fallback option. */
    /* JADX WARN: Switch 'out' block B:178:0x035d for B:185:0x0378 already processed. Defaulting to fallback option. */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0099 -> B:178:0x035d). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    private int match(com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.Context r21, com.sun.org.apache.xerces.internal.impl.xpath.regex.Op r22, int r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 1090
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.match(com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression$Context, com.sun.org.apache.xerces.internal.impl.xpath.regex.Op, int, int, int):int");
    }

    private boolean matchChar(int i, int i2, boolean z) {
        if (z) {
            return matchIgnoreCase(i, i2);
        }
        return i == i2;
    }

    private static final boolean matchIgnoreCase(int i, int i2) {
        char upperCase;
        char upperCase2;
        if (i == i2) {
            return true;
        }
        return i <= 65535 && i2 <= 65535 && ((upperCase = Character.toUpperCase((char) i)) == (upperCase2 = Character.toUpperCase((char) i2)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2));
    }

    private void setPattern(String str, int i, Locale locale) throws ParseException {
        this.regex = str;
        this.options = i;
        RegexParser parserForXMLSchema = isSet(i, 512) ? new ParserForXMLSchema(locale) : new RegexParser(locale);
        this.tokentree = parserForXMLSchema.parse(this.regex, this.options);
        this.nofparen = parserForXMLSchema.parennumber;
        this.hasBackReferences = parserForXMLSchema.hasBackReferences;
        this.operations = null;
        this.context = null;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof RegularExpression)) {
            return false;
        }
        RegularExpression regularExpression = (RegularExpression) obj;
        return this.regex.equals(regularExpression.regex) && this.options == regularExpression.options;
    }

    public int getNumberOfGroups() {
        return this.nofparen;
    }

    public String getOptions() {
        return REUtil.createOptionString(this.options);
    }

    public String getPattern() {
        return this.regex;
    }

    public int hashCode() {
        return (this.regex + PsuedoNames.PSEUDONAME_ROOT + getOptions()).hashCode();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean matchAnchor(ExpressionTarget expressionTarget, Op op, Context context, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int wordType;
        int wordType2;
        int data = op.getData();
        if (data != 36) {
            if (data != 60) {
                if (data != 62) {
                    if (data == 90) {
                        int i7 = context.limit;
                        if (i != i7 && (((i6 = i + 1) != i7 || !isEOLChar(expressionTarget.charAt(i))) && (i + 2 != context.limit || expressionTarget.charAt(i) != '\r' || expressionTarget.charAt(i6) != '\n'))) {
                            return false;
                        }
                    } else if (data != 94) {
                        if (data != 98) {
                            if (data != 122) {
                                switch (data) {
                                    case 64:
                                        int i8 = context.start;
                                        if (i != i8 && (i <= i8 || !isEOLChar(expressionTarget.charAt(i - 1)))) {
                                            return false;
                                        }
                                        break;
                                    case 65:
                                        if (i != context.start) {
                                            return false;
                                        }
                                        break;
                                    case 66:
                                        if (context.length != 0 && (wordType2 = getWordType(expressionTarget, context.start, context.limit, i, i2)) != 0 && wordType2 != getPreviousWordType(expressionTarget, context.start, context.limit, i, i2)) {
                                            return false;
                                        }
                                        break;
                                }
                            } else if (i != context.limit) {
                                return false;
                            }
                        } else if (context.length == 0 || (wordType = getWordType(expressionTarget, context.start, context.limit, i, i2)) == 0 || wordType == getPreviousWordType(expressionTarget, context.start, context.limit, i, i2)) {
                            return false;
                        }
                    } else if (isSet(i2, 8)) {
                        int i9 = context.start;
                        if (i != i9 && (i <= i9 || i >= context.limit || !isEOLChar(expressionTarget.charAt(i - 1)))) {
                            return false;
                        }
                    } else if (i != context.start) {
                        return false;
                    }
                } else if (context.length == 0 || i == (i5 = context.start) || getWordType(expressionTarget, i5, context.limit, i, i2) != 2 || getPreviousWordType(expressionTarget, context.start, context.limit, i, i2) != 1) {
                    return false;
                }
            } else if (context.length == 0 || i == (i4 = context.limit) || getWordType(expressionTarget, context.start, i4, i, i2) != 1 || getPreviousWordType(expressionTarget, context.start, context.limit, i, i2) != 2) {
                return false;
            }
        } else if (isSet(i2, 8)) {
            int i10 = context.limit;
            if (i != i10 && (i >= i10 || !isEOLChar(expressionTarget.charAt(i)))) {
                return false;
            }
        } else {
            int i11 = context.limit;
            if (i != i11 && (((i3 = i + 1) != i11 || !isEOLChar(expressionTarget.charAt(i))) && (i + 2 != context.limit || expressionTarget.charAt(i) != '\r' || expressionTarget.charAt(i3) != '\n'))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0164  */
    /* JADX WARN: Code duplicated, block: B:104:0x0170  */
    /* JADX WARN: Code duplicated, block: B:99:0x0160  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:113:0x017b
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public boolean matches(java.text.CharacterIterator r10, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match r11) {
        /*
            Method dump skipped, instruction units count: 381
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.matches(java.text.CharacterIterator, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match):boolean");
    }

    public void prepare() {
        int i;
        compile(this.tokentree);
        this.minlength = this.tokentree.getMinLength();
        this.firstChar = null;
        if (!isSet(this.options, 128) && !isSet(this.options, 512)) {
            RangeToken rangeTokenCreateRange = Token.createRange();
            if (this.tokentree.analyzeFirstCharacter(rangeTokenCreateRange, this.options) == 1) {
                rangeTokenCreateRange.compactRanges();
                this.firstChar = rangeTokenCreateRange;
            }
        }
        Op op = this.operations;
        if (op != null && (((i = op.type) == 6 || i == 1) && op.next == null)) {
            this.fixedStringOnly = true;
            if (i == 6) {
                this.fixedString = op.getString();
            } else {
                int data = op.getData();
                Op op2 = this.operations;
                if (data >= 65536) {
                    this.fixedString = REUtil.decomposeToSurrogates(op2.getData());
                } else {
                    this.fixedString = new String(new char[]{(char) op2.getData()});
                }
            }
            int i2 = this.options;
            this.fixedStringOptions = i2;
            this.fixedStringTable = new BMPattern(this.fixedString, 256, isSet(i2, 2));
            return;
        }
        if (isSet(this.options, 256) || isSet(this.options, 512)) {
            return;
        }
        Token.FixedStringContainer fixedStringContainer = new Token.FixedStringContainer();
        this.tokentree.findFixedString(fixedStringContainer, this.options);
        Token token = fixedStringContainer.token;
        String string = token == null ? null : token.getString();
        this.fixedString = string;
        this.fixedStringOptions = fixedStringContainer.options;
        if (string != null && string.length() < 2) {
            this.fixedString = null;
        }
        String str = this.fixedString;
        if (str != null) {
            this.fixedStringTable = new BMPattern(str, 256, isSet(this.fixedStringOptions, 2));
        }
    }

    public String toString() {
        return this.tokentree.toString(this.options);
    }

    public static final class CharArrayTarget extends ExpressionTarget {
        char[] target;

        public CharArrayTarget(char[] cArr) {
            this.target = cArr;
        }

        private final boolean regionMatches(int i, int i2, String str, int i3) {
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i4 + 1;
                if (this.target[i] != str.charAt(i4)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, String str, int i3) {
            char upperCase;
            char upperCase2;
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char c = this.target[i];
                int i7 = i4 + 1;
                char cCharAt = str.charAt(i4);
                if (c != cCharAt && (upperCase = Character.toUpperCase(c)) != (upperCase2 = Character.toUpperCase(cCharAt)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public char charAt(int i) {
            return this.target[i];
        }

        public final void resetTarget(char[] cArr) {
            this.target = cArr;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i < 0 || i2 - i < i3) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, str, i3);
            }
            return regionMatches(i, i2, str, i3);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i < 0 || i2 - i < i4) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, i3, i4);
            }
            return regionMatches(i, i2, i3, i4);
        }

        private final boolean regionMatches(int i, int i2, int i3, int i4) {
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                char[] cArr = this.target;
                int i6 = i + 1;
                int i7 = i3 + 1;
                if (cArr[i] != cArr[i3]) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, int i3, int i4) {
            char upperCase;
            char upperCase2;
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                char[] cArr = this.target;
                int i6 = i + 1;
                char c = cArr[i];
                int i7 = i3 + 1;
                char c2 = cArr[i3];
                if (c != c2 && (upperCase = Character.toUpperCase(c)) != (upperCase2 = Character.toUpperCase(c2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }
    }

    public static final class Context {
        private CharArrayTarget charArrayTarget;
        private CharacterIteratorTarget characterIteratorTarget;
        ClosureContext[] closureContexts;
        boolean inuse = false;
        int length;
        int limit;
        Match match;
        int start;
        private StringTarget stringTarget;
        ExpressionTarget target;

        private void resetCommon(int i) {
            this.length = this.limit - this.start;
            setInUse(true);
            this.match = null;
            ClosureContext[] closureContextArr = this.closureContexts;
            if (closureContextArr == null || closureContextArr.length != i) {
                this.closureContexts = new ClosureContext[i];
            }
            for (int i2 = 0; i2 < i; i2++) {
                ClosureContext[] closureContextArr2 = this.closureContexts;
                ClosureContext closureContext = closureContextArr2[i2];
                if (closureContext == null) {
                    closureContextArr2[i2] = new ClosureContext();
                } else {
                    closureContext.reset();
                }
            }
        }

        public void reset(CharacterIterator characterIterator, int i, int i2, int i3) {
            CharacterIteratorTarget characterIteratorTarget = this.characterIteratorTarget;
            if (characterIteratorTarget == null) {
                this.characterIteratorTarget = new CharacterIteratorTarget(characterIterator);
            } else {
                characterIteratorTarget.resetTarget(characterIterator);
            }
            this.target = this.characterIteratorTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }

        public synchronized void setInUse(boolean z) {
            this.inuse = z;
        }

        public void reset(String str, int i, int i2, int i3) {
            StringTarget stringTarget = this.stringTarget;
            if (stringTarget == null) {
                this.stringTarget = new StringTarget(str);
            } else {
                stringTarget.resetTarget(str);
            }
            this.target = this.stringTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }

        public void reset(char[] cArr, int i, int i2, int i3) {
            CharArrayTarget charArrayTarget = this.charArrayTarget;
            if (charArrayTarget == null) {
                this.charArrayTarget = new CharArrayTarget(cArr);
            } else {
                charArrayTarget.resetTarget(cArr);
            }
            this.target = this.charArrayTarget;
            this.start = i;
            this.limit = i2;
            resetCommon(i3);
        }
    }

    public static final class StringTarget extends ExpressionTarget {
        private String target;

        public StringTarget(String str) {
            this.target = str;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final char charAt(int i) {
            return this.target.charAt(i);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i2 - i < i3) {
                return false;
            }
            String str2 = this.target;
            return z ? str2.regionMatches(true, i, str, 0, i3) : str2.regionMatches(i, str, 0, i3);
        }

        public final void resetTarget(String str) {
            this.target = str;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i2 - i < i4) {
                return false;
            }
            String str = this.target;
            if (z) {
                return str.regionMatches(true, i, str, i3, i4);
            }
            return str.regionMatches(i, str, i3, i4);
        }
    }

    public RegularExpression(String str, String str2) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(str, str2);
    }

    public static final class CharacterIteratorTarget extends ExpressionTarget {
        CharacterIterator target;

        public CharacterIteratorTarget(CharacterIterator characterIterator) {
            this.target = characterIterator;
        }

        private final boolean regionMatches(int i, int i2, int i3, int i4) {
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i3 + 1;
                if (this.target.setIndex(i) != this.target.setIndex(i3)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, int i3, int i4) {
            char upperCase;
            char upperCase2;
            while (true) {
                int i5 = i4 - 1;
                if (i4 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char index = this.target.setIndex(i);
                int i7 = i3 + 1;
                char index2 = this.target.setIndex(i3);
                if (index != index2 && (upperCase = Character.toUpperCase(index)) != (upperCase2 = Character.toUpperCase(index2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i5;
                i = i6;
                i3 = i7;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final char charAt(int i) {
            return this.target.setIndex(i);
        }

        public final void resetTarget(CharacterIterator characterIterator) {
            this.target = characterIterator;
        }

        private final boolean regionMatches(int i, int i2, String str, int i3) {
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                int i7 = i4 + 1;
                if (this.target.setIndex(i) != str.charAt(i4)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, int i3, int i4) {
            if (i < 0 || i2 - i < i4) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, i3, i4);
            }
            return regionMatches(i, i2, i3, i4);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.ExpressionTarget
        public final boolean regionMatches(boolean z, int i, int i2, String str, int i3) {
            if (i < 0 || i2 - i < i3) {
                return false;
            }
            if (z) {
                return regionMatchesIgnoreCase(i, i2, str, i3);
            }
            return regionMatches(i, i2, str, i3);
        }

        private final boolean regionMatchesIgnoreCase(int i, int i2, String str, int i3) {
            char upperCase;
            char upperCase2;
            int i4 = 0;
            while (true) {
                int i5 = i3 - 1;
                if (i3 <= 0) {
                    return true;
                }
                int i6 = i + 1;
                char index = this.target.setIndex(i);
                int i7 = i4 + 1;
                char cCharAt = str.charAt(i4);
                if (index != cCharAt && (upperCase = Character.toUpperCase(index)) != (upperCase2 = Character.toUpperCase(cCharAt)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)) {
                    return false;
                }
                i4 = i7;
                i3 = i5;
                i = i6;
            }
        }
    }

    public boolean equals(String str, int i) {
        return this.regex.equals(str) && this.options == i;
    }

    public RegularExpression(String str, String str2, Locale locale) throws ParseException {
        this.hasBackReferences = false;
        this.operations = null;
        this.context = null;
        this.firstChar = null;
        this.fixedString = null;
        this.fixedStringTable = null;
        this.fixedStringOnly = false;
        setPattern(str, str2, locale);
    }

    public RegularExpression(String str) throws ParseException {
        this(str, null);
    }

    public void setPattern(String str, Locale locale) throws ParseException {
        setPattern(str, this.options, locale);
    }

    public void setPattern(String str) throws ParseException {
        setPattern(str, Locale.getDefault());
    }

    public void setPattern(String str, String str2) throws ParseException {
        setPattern(str, str2, Locale.getDefault());
    }

    public void setPattern(String str, String str2, Locale locale) throws ParseException {
        setPattern(str, REUtil.parseOptions(str2), locale);
    }

    public boolean matches(char[] cArr, int i, int i2) {
        return matches(cArr, i, i2, (Match) null);
    }

    public boolean matches(char[] cArr, Match match) {
        return matches(cArr, 0, cArr.length, match);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0156  */
    /* JADX WARN: Code duplicated, block: B:103:0x0162  */
    /* JADX WARN: Code duplicated, block: B:98:0x0152  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:112:0x016d
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public boolean matches(char[] r9, int r10, int r11, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match r12) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.matches(char[], int, int, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match):boolean");
    }

    public boolean matches(String str) {
        return matches(str, 0, str.length(), (Match) null);
    }

    public boolean matches(String str, int i, int i2) {
        return matches(str, i, i2, (Match) null);
    }

    public boolean matches(String str, Match match) {
        return matches(str, 0, str.length(), match);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x015c  */
    /* JADX WARN: Code duplicated, block: B:103:0x0168  */
    /* JADX WARN: Code duplicated, block: B:98:0x0158  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:112:0x0173
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public boolean matches(java.lang.String r9, int r10, int r11, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match r12) {
        /*
            Method dump skipped, instruction units count: 373
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression.matches(java.lang.String, int, int, com.sun.org.apache.xerces.internal.impl.xpath.regex.Match):boolean");
    }

    private synchronized void compile(Token token) {
        if (this.operations != null) {
            return;
        }
        this.numberOfClosures = 0;
        this.operations = compile(token, null, false);
    }

    public boolean matches(CharacterIterator characterIterator) {
        return matches(characterIterator, (Match) null);
    }

    public boolean matches(char[] cArr) {
        return matches(cArr, 0, cArr.length, (Match) null);
    }
}
