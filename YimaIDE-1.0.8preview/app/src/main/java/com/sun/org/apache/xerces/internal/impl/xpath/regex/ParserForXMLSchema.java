package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import com.sun.jna.platform.win32.LMErr;
import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class ParserForXMLSchema extends RegexParser {
    private static final String LETTERS = "AZazÀÖØöøıĴľŁňŊžƀǰǴǵǺȗɐʨʻˁʰˑΆΆΈΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆאתװײءغفيٱڷںھۀێېۓەەۥۦअहऽऽक़ॡঅঌএঐওনপরললশহড়ঢ়য়ৡৰৱਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹਖ਼ੜਫ਼ਫ਼ੲੴઅઋઍઍએઑઓનપરલળવહઽઽૠૠଅଌଏଐଓନପରଲଳଶହଽଽଡ଼ଢ଼ୟୡஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹఅఌఎఐఒనపళవహౠౡಅಌಎಐಒನಪಳವಹೞೞೠೡഅഌഎഐഒനപഹൠൡกฮะะาำเๅກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະະາຳຽຽເໄཀཇཉཀྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼΩΩKÅ℮℮ↀↂ〇〇〡〩ぁゔァヺㄅㄬ一龥가힣ｦﾟ";
    private static final String NAMECHARS = "-.0:AZ__az··ÀÖØöøıĴľŁňŊžƀǃǍǰǴǵǺȗɐʨʻˁːˑ̀͠͡ͅΆΊΌΌΎΡΣώϐϖϚϚϜϜϞϞϠϠϢϳЁЌЎяёќўҁ҃҆ҐӄӇӈӋӌӐӫӮӵӸӹԱՖՙՙաֆֹֻֽֿֿׁׂ֑֣֡ׄׄאתװײءغـْ٠٩ٰڷںھۀێېۓە۪ۭۨ۰۹ँःअह़्॑॔क़ॣ०९ঁঃঅঌএঐওনপরললশহ়়াৄেৈো্ৗৗড়ঢ়য়ৣ০ৱਂਂਅਊਏਐਓਨਪਰਲਲ਼ਵਸ਼ਸਹ਼਼ਾੂੇੈੋ੍ਖ਼ੜਫ਼ਫ਼੦ੴઁઃઅઋઍઍએઑઓનપરલળવહ઼ૅેૉો્ૠૠ૦૯ଁଃଅଌଏଐଓନପରଲଳଶହ଼ୃେୈୋ୍ୖୗଡ଼ଢ଼ୟୡ୦୯ஂஃஅஊஎஐஒகஙசஜஜஞடணதநபமவஷஹாூெைொ்ௗௗ௧௯ఁఃఅఌఎఐఒనపళవహాౄెైొ్ౕౖౠౡ౦౯ಂಃಅಌಎಐಒನಪಳವಹಾೄೆೈೊ್ೕೖೞೞೠೡ೦೯ംഃഅഌഎഐഒനപഹാൃെൈൊ്ൗൗൠൡ൦൯กฮะฺเ๎๐๙ກຂຄຄງຈຊຊຍຍດທນຟມຣລລວວສຫອຮະູົຽເໄໆໆ່ໍ໐໙༘༙༠༩༹༹༵༵༷༷༾ཇཉཀྵ྄ཱ྆ྋྐྕྗྗྙྭྱྷྐྵྐྵႠჅაჶᄀᄀᄂᄃᄅᄇᄉᄉᄋᄌᄎᄒᄼᄼᄾᄾᅀᅀᅌᅌᅎᅎᅐᅐᅔᅕᅙᅙᅟᅡᅣᅣᅥᅥᅧᅧᅩᅩᅭᅮᅲᅳᅵᅵᆞᆞᆨᆨᆫᆫᆮᆯᆷᆸᆺᆺᆼᇂᇫᇫᇰᇰᇹᇹḀẛẠỹἀἕἘἝἠὅὈὍὐὗὙὙὛὛὝὝὟώᾀᾴᾶᾼιιῂῄῆῌῐΐῖΊῠῬῲῴῶῼ⃐⃜⃡⃡ΩΩKÅ℮℮ↀↂ々々〇〇〡〯〱〵ぁゔ゙゚ゝゞァヺーヾㄅㄬ一龥가힣";
    private static final String SPACES = "\t\n\r\r  ";
    private static Map<String, Token> ranges;
    private static Map<String, Token> ranges2;
    private static final int[] LETTERS_INT = {120720, 120744, 120746, 120777, 195099, 195101};
    private static final int[] DIGITS_INTS = {48, 57, WinError.ERROR_INSTALL_TEMP_UNWRITABLE, WinError.ERROR_SUCCESS_REBOOT_INITIATED, 1776, WinError.ERROR_UNRECOGNIZED_MEDIA, 2406, 2415, 2534, 2543, LMErr.NERR_DfsNoSuchVolume, LMErr.NERR_DfsBadRenamePath, 2790, 2799, 2918, 2927, 3047, 3055, 3174, 3183, 3302, 3311, 3430, 3439, 3664, 3673, 3792, 3801, 3872, 3881, 4160, 4169, 4969, 4977, 6112, 6121, 6160, 6169, 65296, 65305, 120782, 120831};

    public ParserForXMLSchema() {
    }

    public static synchronized RangeToken getRange(String str, boolean z) {
        try {
            if (ranges == null) {
                ranges = new HashMap();
                ranges2 = new HashMap();
                RangeToken rangeTokenCreateRange = Token.createRange();
                setupRange(rangeTokenCreateRange, SPACES);
                ranges.put("xml:isSpace", rangeTokenCreateRange);
                ranges2.put("xml:isSpace", Token.complementRanges(rangeTokenCreateRange));
                RangeToken rangeTokenCreateRange2 = Token.createRange();
                setupRange(rangeTokenCreateRange2, DIGITS_INTS);
                ranges.put("xml:isDigit", rangeTokenCreateRange2);
                ranges2.put("xml:isDigit", Token.complementRanges(rangeTokenCreateRange2));
                RangeToken rangeTokenCreateRange3 = Token.createRange();
                rangeTokenCreateRange3.mergeRanges(Token.getRange("P", true));
                rangeTokenCreateRange3.mergeRanges(Token.getRange(Constants.HASIDCALL_INDEX_SIG, true));
                rangeTokenCreateRange3.mergeRanges(Token.getRange("C", true));
                ranges2.put("xml:isWord", rangeTokenCreateRange3);
                ranges.put("xml:isWord", Token.complementRanges(rangeTokenCreateRange3));
                RangeToken rangeTokenCreateRange4 = Token.createRange();
                setupRange(rangeTokenCreateRange4, NAMECHARS);
                ranges.put("xml:isNameChar", rangeTokenCreateRange4);
                ranges2.put("xml:isNameChar", Token.complementRanges(rangeTokenCreateRange4));
                RangeToken rangeTokenCreateRange5 = Token.createRange();
                setupRange(rangeTokenCreateRange5, LETTERS);
                setupRange(rangeTokenCreateRange5, LETTERS_INT);
                rangeTokenCreateRange5.addRange(95, 95);
                rangeTokenCreateRange5.addRange(58, 58);
                ranges.put("xml:isInitialNameChar", rangeTokenCreateRange5);
                ranges2.put("xml:isInitialNameChar", Token.complementRanges(rangeTokenCreateRange5));
            }
        } catch (Throwable th) {
            throw th;
        }
        return z ? (RangeToken) ranges.get(str) : (RangeToken) ranges2.get(str);
    }

    public static void setupRange(Token token, String str) {
        int length = str.length();
        for (int i = 0; i < length; i += 2) {
            token.addRange(str.charAt(i), str.charAt(i + 1));
        }
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public boolean checkQuestion(int i) {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x003d A[FALL_THROUGH, RETURN] */
    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public int decodeEscaped() throws ParseException {
        if (read() != 10) {
            throw ex("parser.next.1", this.offset - 1);
        }
        int i = this.chardata;
        if (i != 45 && i != 46 && i != 63) {
            if (i == 110) {
                return 10;
            }
            if (i == 114) {
                return 13;
            }
            if (i == 116) {
                return 9;
            }
            switch (i) {
                default:
                    switch (i) {
                        default:
                            switch (i) {
                                case 123:
                                case 124:
                                case 125:
                                    break;
                                default:
                                    throw ex("parser.process.1", this.offset - 2);
                            }
                        case 91:
                        case 92:
                        case 93:
                        case 94:
                            return i;
                    }
                case 40:
                case 41:
                case 42:
                case 43:
                    return i;
            }
        }
        return i;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token getTokenForShorthand(int i) {
        if (i == 67) {
            return getRange("xml:isNameChar", false);
        }
        if (i == 68) {
            return getRange("xml:isDigit", false);
        }
        if (i == 73) {
            return getRange("xml:isInitialNameChar", false);
        }
        if (i == 83) {
            return getRange("xml:isSpace", false);
        }
        if (i == 87) {
            return getRange("xml:isWord", false);
        }
        if (i == 105) {
            return getRange("xml:isInitialNameChar", true);
        }
        if (i == 115) {
            return getRange("xml:isSpace", true);
        }
        if (i == 119) {
            return getRange("xml:isWord", true);
        }
        if (i == 99) {
            return getRange("xml:isNameChar", true);
        }
        if (i == 100) {
            return getRange("xml:isDigit", true);
        }
        ib0.a("Internal Error: shorthands: \\u", Integer.toString(i, 16));
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:140:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:142:0x01c1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:145:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:156:0x00a2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:158:0x010b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:168:0x01cb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:49:0x0096  */
    /* JADX WARN: Code duplicated, block: B:51:0x009e  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00ee A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:75:0x00f0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:76:0x00f2  */
    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public RangeToken parseCharacterClass(boolean z) throws ParseException {
        RangeToken rangeTokenCreateRange;
        RangeToken rangeTokenCreateRange2;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        RangeToken rangeTokenProcessBacksolidus_pP;
        int i2 = 1;
        setContext(1);
        next();
        boolean z5 = false;
        if (read() == 0 && this.chardata == 94) {
            next();
            rangeTokenCreateRange2 = Token.createRange();
            rangeTokenCreateRange2.addRange(0, 1114111);
            rangeTokenCreateRange = Token.createRange();
            z2 = true;
        } else {
            rangeTokenCreateRange = Token.createRange();
            rangeTokenCreateRange2 = null;
            z2 = false;
        }
        boolean z6 = true;
        while (true) {
            int i3 = read();
            if (i3 != i2) {
                if (i3 != 0 || this.chardata != 93 || z6) {
                    int iDecodeEscaped = this.chardata;
                    if (i3 != 10) {
                        if (i3 == 24 && !z6) {
                            if (z2) {
                                rangeTokenCreateRange2.subtractRanges(rangeTokenCreateRange);
                            } else {
                                rangeTokenCreateRange2 = rangeTokenCreateRange;
                            }
                            rangeTokenCreateRange2.subtractRanges(parseCharacterClass(z5));
                            if (read() == 0 && this.chardata == 93) {
                                break;
                            }
                            throw ex("parser.cc.5", this.offset);
                        }
                    } else {
                        if (iDecodeEscaped == 45) {
                            iDecodeEscaped = decodeEscaped();
                            z3 = i2;
                            z4 = z5;
                        } else if (iDecodeEscaped == 73) {
                            iDecodeEscaped = processCIinCharacterClass(rangeTokenCreateRange, iDecodeEscaped);
                            if (iDecodeEscaped < 0) {
                                z4 = i2;
                                z3 = z5;
                            }
                        } else {
                            if (iDecodeEscaped == 80) {
                                i = this.offset;
                                rangeTokenProcessBacksolidus_pP = processBacksolidus_pP(iDecodeEscaped);
                                if (rangeTokenProcessBacksolidus_pP != null) {
                                    throw ex("parser.atom.5", i);
                                }
                                rangeTokenCreateRange.mergeRanges(rangeTokenProcessBacksolidus_pP);
                            } else {
                                if (iDecodeEscaped != 83 && iDecodeEscaped != 87) {
                                    if (iDecodeEscaped == 105) {
                                        iDecodeEscaped = processCIinCharacterClass(rangeTokenCreateRange, iDecodeEscaped);
                                        if (iDecodeEscaped < 0) {
                                        }
                                    } else if (iDecodeEscaped == 112) {
                                        i = this.offset;
                                        rangeTokenProcessBacksolidus_pP = processBacksolidus_pP(iDecodeEscaped);
                                        if (rangeTokenProcessBacksolidus_pP != null) {
                                            throw ex("parser.atom.5", i);
                                        }
                                        rangeTokenCreateRange.mergeRanges(rangeTokenProcessBacksolidus_pP);
                                    } else if (iDecodeEscaped != 115 && iDecodeEscaped != 119) {
                                        if (iDecodeEscaped == 67) {
                                            iDecodeEscaped = processCIinCharacterClass(rangeTokenCreateRange, iDecodeEscaped);
                                            if (iDecodeEscaped < 0) {
                                            }
                                        } else if (iDecodeEscaped != 68) {
                                            if (iDecodeEscaped == 99) {
                                                iDecodeEscaped = processCIinCharacterClass(rangeTokenCreateRange, iDecodeEscaped);
                                                if (iDecodeEscaped < 0) {
                                                }
                                            } else if (iDecodeEscaped != 100) {
                                                iDecodeEscaped = decodeEscaped();
                                            }
                                        }
                                    }
                                }
                                rangeTokenCreateRange.mergeRanges(getTokenForShorthand(iDecodeEscaped));
                            }
                            z4 = i2;
                            z3 = z5;
                        }
                        next();
                        if (z4) {
                            if (i3 == 0) {
                                if (iDecodeEscaped != 91) {
                                    throw ex("parser.cc.6", this.offset - 2);
                                }
                                if (iDecodeEscaped != 93) {
                                    throw ex("parser.cc.7", this.offset - 2);
                                }
                                if (iDecodeEscaped == 45 && this.chardata != 93 && !z6) {
                                    throw ex("parser.cc.8", this.offset - 2);
                                }
                            }
                            if (read() != 0 && this.chardata == 45 && (iDecodeEscaped != 45 || z3 || !z6)) {
                                next();
                                int i4 = read();
                                if (i4 == 1) {
                                    throw ex("parser.cc.2", this.offset);
                                }
                                if (i4 == 0 && this.chardata == 93) {
                                    if (!isSet(2) || iDecodeEscaped > 65535) {
                                        rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                                    } else {
                                        RegexParser.addCaseInsensitiveChar(rangeTokenCreateRange, iDecodeEscaped);
                                    }
                                    rangeTokenCreateRange.addRange(45, 45);
                                } else {
                                    if (i4 == 24) {
                                        throw ex("parser.cc.8", this.offset - 1);
                                    }
                                    int iDecodeEscaped2 = this.chardata;
                                    if (i4 == 0) {
                                        if (iDecodeEscaped2 == 91) {
                                            throw ex("parser.cc.6", this.offset - 1);
                                        }
                                        if (iDecodeEscaped2 == 93) {
                                            throw ex("parser.cc.7", this.offset - 1);
                                        }
                                        if (iDecodeEscaped2 == 45) {
                                            throw ex("parser.cc.8", this.offset - 2);
                                        }
                                    } else if (i4 == 10) {
                                        iDecodeEscaped2 = decodeEscaped();
                                    }
                                    next();
                                    if (iDecodeEscaped > iDecodeEscaped2) {
                                        throw ex("parser.ope.3", this.offset - 1);
                                    }
                                    if (!isSet(2) || (iDecodeEscaped > 65535 && iDecodeEscaped2 > 65535)) {
                                        rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped2);
                                    } else {
                                        RegexParser.addCaseInsensitiveCharRange(rangeTokenCreateRange, iDecodeEscaped, iDecodeEscaped2);
                                    }
                                }
                            } else if (isSet(2) || iDecodeEscaped > 65535) {
                                rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                            } else {
                                RegexParser.addCaseInsensitiveChar(rangeTokenCreateRange, iDecodeEscaped);
                            }
                        }
                        i2 = 1;
                        z5 = false;
                        z6 = false;
                    }
                    z4 = z5;
                    z3 = z4;
                    next();
                    if (z4) {
                        if (i3 == 0) {
                            if (iDecodeEscaped != 91) {
                                throw ex("parser.cc.6", this.offset - 2);
                            }
                            if (iDecodeEscaped != 93) {
                                throw ex("parser.cc.7", this.offset - 2);
                            }
                            if (iDecodeEscaped == 45) {
                                throw ex("parser.cc.8", this.offset - 2);
                            }
                        }
                        if (read() != 0) {
                            if (isSet(2)) {
                                rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                            } else {
                                rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                            }
                        } else if (isSet(2)) {
                            rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                        } else {
                            rangeTokenCreateRange.addRange(iDecodeEscaped, iDecodeEscaped);
                        }
                    }
                    i2 = 1;
                    z5 = false;
                    z6 = false;
                } else if (z2) {
                    rangeTokenCreateRange2.subtractRanges(rangeTokenCreateRange);
                    break;
                }
            }
            rangeTokenCreateRange2 = rangeTokenCreateRange;
            break;
        }
        if (read() == 1) {
            throw ex("parser.cc.2", this.offset);
        }
        rangeTokenCreateRange2.sortRanges();
        rangeTokenCreateRange2.compactRanges();
        setContext(0);
        next();
        return rangeTokenCreateRange2;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public RangeToken parseSetOperations() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBackreference() throws ParseException {
        throw ex("parser.process.1", this.offset - 4);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_A() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_B() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_C() throws ParseException {
        next();
        return getTokenForShorthand(67);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_I() throws ParseException {
        next();
        return getTokenForShorthand(73);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_X() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_Z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_b() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_c() throws ParseException {
        next();
        return getTokenForShorthand(99);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_g() throws ParseException {
        throw ex("parser.process.1", this.offset - 2);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_gt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_i() throws ParseException {
        next();
        return getTokenForShorthand(105);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_lt() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processBacksolidus_z() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public int processCIinCharacterClass(RangeToken rangeToken, int i) {
        rangeToken.mergeRanges(getTokenForShorthand(i));
        return -1;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processCaret() throws ParseException {
        next();
        return Token.createChar(94);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processCondition() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processDollar() throws ParseException {
        next();
        return Token.createChar(36);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processIndependent() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processLookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processLookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processModifiers() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processNegativelookahead() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processNegativelookbehind() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processParen() throws ParseException {
        next();
        Token.ParenToken parenTokenCreateParen = Token.createParen(parseRegex(), 0);
        if (read() != 7) {
            throw ex("parser.factor.1", this.offset - 1);
        }
        next();
        return parenTokenCreateParen;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processParen2() throws ParseException {
        throw ex("parser.process.1", this.offset);
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processPlus(Token token) throws ParseException {
        next();
        return Token.createConcat(token, Token.createClosure(token));
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processQuestion(Token token) throws ParseException {
        next();
        Token.UnionToken unionTokenCreateUnion = Token.createUnion();
        unionTokenCreateUnion.addChild(token);
        unionTokenCreateUnion.addChild(Token.createEmpty());
        return unionTokenCreateUnion;
    }

    @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.RegexParser
    public Token processStar(Token token) throws ParseException {
        next();
        return Token.createClosure(token);
    }

    public ParserForXMLSchema(Locale locale) {
        super(locale);
    }

    public static void setupRange(Token token, int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length; i += 2) {
            token.addRange(iArr[i], iArr[i + 1]);
        }
    }
}
