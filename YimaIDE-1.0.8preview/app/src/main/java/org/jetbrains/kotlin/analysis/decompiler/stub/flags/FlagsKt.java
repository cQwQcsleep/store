package org.jetbrains.kotlin.analysis.decompiler.stub.flags;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0002\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003\"\u0011\u0010\f\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0003\"\u0011\u0010\u000e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0003\"\u0011\u0010\u0010\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0003\"\u0011\u0010\u0012\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0003\"\u0011\u0010\u0014\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0003\"\u0011\u0010\u0016\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0003\"\u0011\u0010\u0018\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0003\"\u0011\u0010\u001a\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0003\"\u0011\u0010\u001c\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0003\"\u0011\u0010\u001e\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0003\"\u0011\u0010 \u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0003\"\u0011\u0010\"\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0003\"\u0011\u0010$\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0003\"\u0011\u0010&\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0003\"\u0011\u0010(\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0003\"\u0011\u0010*\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0003\"\u0011\u0010,\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0003\"\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00010/X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101¨\u00067"}, d2 = {"MODALITY", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/flags/FlagsToModifiers;", "getMODALITY", "()Lorg/jetbrains/kotlin/analysis/decompiler/stub/flags/FlagsToModifiers;", "INTERFACE_MODALITY", "getINTERFACE_MODALITY", "VISIBILITY", "getVISIBILITY", "INNER", "getINNER", "CONST", "getCONST", "LATEINIT", "getLATEINIT", "OPERATOR", "getOPERATOR", "INFIX", "getINFIX", "DATA", "getDATA", "EXTERNAL_FUN", "getEXTERNAL_FUN", "EXTERNAL_PROPERTY", "getEXTERNAL_PROPERTY", "EXTERNAL_ACCESSOR", "getEXTERNAL_ACCESSOR", "EXTERNAL_CLASS", "getEXTERNAL_CLASS", "INLINE", "getINLINE", "INLINE_ACCESSOR", "getINLINE_ACCESSOR", "VALUE_CLASS", "getVALUE_CLASS", "FUN_INTERFACE", "getFUN_INTERFACE", "TAILREC", "getTAILREC", "SUSPEND", "getSUSPEND", "EXPECT_CLASS", "getEXPECT_CLASS", "EXPECT_FUNCTION", "getEXPECT_FUNCTION", "EXPECT_PROPERTY", "getEXPECT_PROPERTY", "ACCESSOR_FLAGS", "", "getACCESSOR_FLAGS", "()Ljava/util/List;", "createBooleanFlagToModifier", "flagField", "Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;", "ktModifierKeywordToken", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class FlagsKt {
    private static final List<FlagsToModifiers> ACCESSOR_FLAGS;
    private static final FlagsToModifiers CONST;
    private static final FlagsToModifiers DATA;
    private static final FlagsToModifiers EXPECT_CLASS;
    private static final FlagsToModifiers EXPECT_FUNCTION;
    private static final FlagsToModifiers EXPECT_PROPERTY;
    private static final FlagsToModifiers EXTERNAL_ACCESSOR;
    private static final FlagsToModifiers EXTERNAL_CLASS;
    private static final FlagsToModifiers EXTERNAL_FUN;
    private static final FlagsToModifiers EXTERNAL_PROPERTY;
    private static final FlagsToModifiers FUN_INTERFACE;
    private static final FlagsToModifiers INFIX;
    private static final FlagsToModifiers INLINE;
    private static final FlagsToModifiers INLINE_ACCESSOR;
    private static final FlagsToModifiers INNER;
    private static final FlagsToModifiers INTERFACE_MODALITY;
    private static final FlagsToModifiers LATEINIT;
    private static final FlagsToModifiers MODALITY;
    private static final FlagsToModifiers OPERATOR;
    private static final FlagsToModifiers SUSPEND;
    private static final FlagsToModifiers TAILREC;
    private static final FlagsToModifiers VALUE_CLASS;
    private static final FlagsToModifiers VISIBILITY;

    static {
        FlagsToModifiers flagsToModifiers = new FlagsToModifiers() { // from class: org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsKt$MODALITY$1
            @Override // org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsToModifiers
            public KtModifierKeywordToken getModifiers(int flags) {
                ProtoBuf.Modality modality = (ProtoBuf.Modality) Flags.MODALITY.get(flags);
                int i = modality == null ? -1 : WhenMappings.$EnumSwitchMapping$0[modality.ordinal()];
                if (i == -1) {
                    k2d.a("Unexpected modality: null");
                    return null;
                }
                if (i == 1) {
                    KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ABSTRACT_KEYWORD;
                    ktModifierKeywordToken.getClass();
                    return ktModifierKeywordToken;
                }
                if (i == 2) {
                    KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.FINAL_KEYWORD;
                    ktModifierKeywordToken2.getClass();
                    return ktModifierKeywordToken2;
                }
                if (i == 3) {
                    KtModifierKeywordToken ktModifierKeywordToken3 = KtTokens.OPEN_KEYWORD;
                    ktModifierKeywordToken3.getClass();
                    return ktModifierKeywordToken3;
                }
                if (i != 4) {
                    bu8.a();
                    return null;
                }
                KtModifierKeywordToken ktModifierKeywordToken4 = KtTokens.SEALED_KEYWORD;
                ktModifierKeywordToken4.getClass();
                return ktModifierKeywordToken4;
            }
        };
        MODALITY = flagsToModifiers;
        INTERFACE_MODALITY = new FlagsToModifiers() { // from class: org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsKt$INTERFACE_MODALITY$1
            @Override // org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsToModifiers
            public KtModifierKeywordToken getModifiers(int flags) {
                ProtoBuf.Modality modality = (ProtoBuf.Modality) Flags.MODALITY.get(flags);
                KtModifierKeywordToken ktModifierKeywordToken = KtTokens.SEALED_KEYWORD;
                if (modality == ProtoBuf.Modality.SEALED) {
                    return ktModifierKeywordToken;
                }
                return null;
            }
        };
        FlagsToModifiers flagsToModifiers2 = new FlagsToModifiers() { // from class: org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsKt$VISIBILITY$1
            @Override // org.jetbrains.kotlin.analysis.decompiler.stub.flags.FlagsToModifiers
            public KtModifierKeywordToken getModifiers(int flags) {
                ProtoBuf.Visibility visibility = (ProtoBuf.Visibility) Flags.VISIBILITY.get(flags);
                int i = visibility == null ? -1 : WhenMappings.$EnumSwitchMapping$0[visibility.ordinal()];
                if (i == 1 || i == 2) {
                    return KtTokens.PRIVATE_KEYWORD;
                }
                if (i == 3) {
                    return KtTokens.INTERNAL_KEYWORD;
                }
                if (i == 4) {
                    return KtTokens.PROTECTED_KEYWORD;
                }
                if (i == 5) {
                    return KtTokens.PUBLIC_KEYWORD;
                }
                qu7.a("Unexpected visibility: ", visibility);
                return null;
            }
        };
        VISIBILITY = flagsToModifiers2;
        Flags.BooleanFlagField booleanFlagField = Flags.IS_INNER;
        booleanFlagField.getClass();
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.INNER_KEYWORD;
        ktModifierKeywordToken.getClass();
        INNER = createBooleanFlagToModifier(booleanFlagField, ktModifierKeywordToken);
        Flags.BooleanFlagField booleanFlagField2 = Flags.IS_CONST;
        booleanFlagField2.getClass();
        KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.CONST_KEYWORD;
        ktModifierKeywordToken2.getClass();
        CONST = createBooleanFlagToModifier(booleanFlagField2, ktModifierKeywordToken2);
        Flags.BooleanFlagField booleanFlagField3 = Flags.IS_LATEINIT;
        booleanFlagField3.getClass();
        KtModifierKeywordToken ktModifierKeywordToken3 = KtTokens.LATEINIT_KEYWORD;
        ktModifierKeywordToken3.getClass();
        LATEINIT = createBooleanFlagToModifier(booleanFlagField3, ktModifierKeywordToken3);
        Flags.BooleanFlagField booleanFlagField4 = Flags.IS_OPERATOR;
        booleanFlagField4.getClass();
        KtModifierKeywordToken ktModifierKeywordToken4 = KtTokens.OPERATOR_KEYWORD;
        ktModifierKeywordToken4.getClass();
        OPERATOR = createBooleanFlagToModifier(booleanFlagField4, ktModifierKeywordToken4);
        Flags.BooleanFlagField booleanFlagField5 = Flags.IS_INFIX;
        booleanFlagField5.getClass();
        KtModifierKeywordToken ktModifierKeywordToken5 = KtTokens.INFIX_KEYWORD;
        ktModifierKeywordToken5.getClass();
        INFIX = createBooleanFlagToModifier(booleanFlagField5, ktModifierKeywordToken5);
        Flags.BooleanFlagField booleanFlagField6 = Flags.IS_DATA;
        booleanFlagField6.getClass();
        KtModifierKeywordToken ktModifierKeywordToken6 = KtTokens.DATA_KEYWORD;
        ktModifierKeywordToken6.getClass();
        DATA = createBooleanFlagToModifier(booleanFlagField6, ktModifierKeywordToken6);
        Flags.BooleanFlagField booleanFlagField7 = Flags.IS_EXTERNAL_FUNCTION;
        booleanFlagField7.getClass();
        KtModifierKeywordToken ktModifierKeywordToken7 = KtTokens.EXTERNAL_KEYWORD;
        ktModifierKeywordToken7.getClass();
        EXTERNAL_FUN = createBooleanFlagToModifier(booleanFlagField7, ktModifierKeywordToken7);
        Flags.BooleanFlagField booleanFlagField8 = Flags.IS_EXTERNAL_PROPERTY;
        booleanFlagField8.getClass();
        ktModifierKeywordToken7.getClass();
        EXTERNAL_PROPERTY = createBooleanFlagToModifier(booleanFlagField8, ktModifierKeywordToken7);
        Flags.BooleanFlagField booleanFlagField9 = Flags.IS_EXTERNAL_ACCESSOR;
        booleanFlagField9.getClass();
        ktModifierKeywordToken7.getClass();
        FlagsToModifiers flagsToModifiersCreateBooleanFlagToModifier = createBooleanFlagToModifier(booleanFlagField9, ktModifierKeywordToken7);
        EXTERNAL_ACCESSOR = flagsToModifiersCreateBooleanFlagToModifier;
        Flags.BooleanFlagField booleanFlagField10 = Flags.IS_EXTERNAL_CLASS;
        booleanFlagField10.getClass();
        ktModifierKeywordToken7.getClass();
        EXTERNAL_CLASS = createBooleanFlagToModifier(booleanFlagField10, ktModifierKeywordToken7);
        Flags.BooleanFlagField booleanFlagField11 = Flags.IS_INLINE;
        booleanFlagField11.getClass();
        KtModifierKeywordToken ktModifierKeywordToken8 = KtTokens.INLINE_KEYWORD;
        ktModifierKeywordToken8.getClass();
        INLINE = createBooleanFlagToModifier(booleanFlagField11, ktModifierKeywordToken8);
        Flags.BooleanFlagField booleanFlagField12 = Flags.IS_INLINE_ACCESSOR;
        booleanFlagField12.getClass();
        ktModifierKeywordToken8.getClass();
        FlagsToModifiers flagsToModifiersCreateBooleanFlagToModifier2 = createBooleanFlagToModifier(booleanFlagField12, ktModifierKeywordToken8);
        INLINE_ACCESSOR = flagsToModifiersCreateBooleanFlagToModifier2;
        Flags.BooleanFlagField booleanFlagField13 = Flags.IS_VALUE_CLASS;
        booleanFlagField13.getClass();
        KtModifierKeywordToken ktModifierKeywordToken9 = KtTokens.VALUE_KEYWORD;
        ktModifierKeywordToken9.getClass();
        VALUE_CLASS = createBooleanFlagToModifier(booleanFlagField13, ktModifierKeywordToken9);
        Flags.BooleanFlagField booleanFlagField14 = Flags.IS_FUN_INTERFACE;
        booleanFlagField14.getClass();
        KtModifierKeywordToken ktModifierKeywordToken10 = KtTokens.FUN_KEYWORD;
        ktModifierKeywordToken10.getClass();
        FUN_INTERFACE = createBooleanFlagToModifier(booleanFlagField14, ktModifierKeywordToken10);
        Flags.BooleanFlagField booleanFlagField15 = Flags.IS_TAILREC;
        booleanFlagField15.getClass();
        KtModifierKeywordToken ktModifierKeywordToken11 = KtTokens.TAILREC_KEYWORD;
        ktModifierKeywordToken11.getClass();
        TAILREC = createBooleanFlagToModifier(booleanFlagField15, ktModifierKeywordToken11);
        Flags.BooleanFlagField booleanFlagField16 = Flags.IS_SUSPEND;
        booleanFlagField16.getClass();
        KtModifierKeywordToken ktModifierKeywordToken12 = KtTokens.SUSPEND_KEYWORD;
        ktModifierKeywordToken12.getClass();
        SUSPEND = createBooleanFlagToModifier(booleanFlagField16, ktModifierKeywordToken12);
        Flags.BooleanFlagField booleanFlagField17 = Flags.IS_EXPECT_CLASS;
        booleanFlagField17.getClass();
        KtModifierKeywordToken ktModifierKeywordToken13 = KtTokens.EXPECT_KEYWORD;
        ktModifierKeywordToken13.getClass();
        EXPECT_CLASS = createBooleanFlagToModifier(booleanFlagField17, ktModifierKeywordToken13);
        Flags.BooleanFlagField booleanFlagField18 = Flags.IS_EXPECT_FUNCTION;
        booleanFlagField18.getClass();
        ktModifierKeywordToken13.getClass();
        EXPECT_FUNCTION = createBooleanFlagToModifier(booleanFlagField18, ktModifierKeywordToken13);
        Flags.BooleanFlagField booleanFlagField19 = Flags.IS_EXPECT_PROPERTY;
        booleanFlagField19.getClass();
        ktModifierKeywordToken13.getClass();
        EXPECT_PROPERTY = createBooleanFlagToModifier(booleanFlagField19, ktModifierKeywordToken13);
        ACCESSOR_FLAGS = CollectionsKt.listOf(new FlagsToModifiers[]{flagsToModifiers2, flagsToModifiers, flagsToModifiersCreateBooleanFlagToModifier2, flagsToModifiersCreateBooleanFlagToModifier});
    }

    private static final FlagsToModifiers createBooleanFlagToModifier(Flags.BooleanFlagField booleanFlagField, KtModifierKeywordToken ktModifierKeywordToken) {
        return new BooleanFlagToModifier(booleanFlagField, ktModifierKeywordToken);
    }

    public static final List<FlagsToModifiers> getACCESSOR_FLAGS() {
        return ACCESSOR_FLAGS;
    }

    public static final FlagsToModifiers getCONST() {
        return CONST;
    }

    public static final FlagsToModifiers getDATA() {
        return DATA;
    }

    public static final FlagsToModifiers getEXPECT_CLASS() {
        return EXPECT_CLASS;
    }

    public static final FlagsToModifiers getEXPECT_FUNCTION() {
        return EXPECT_FUNCTION;
    }

    public static final FlagsToModifiers getEXPECT_PROPERTY() {
        return EXPECT_PROPERTY;
    }

    public static final FlagsToModifiers getEXTERNAL_ACCESSOR() {
        return EXTERNAL_ACCESSOR;
    }

    public static final FlagsToModifiers getEXTERNAL_CLASS() {
        return EXTERNAL_CLASS;
    }

    public static final FlagsToModifiers getEXTERNAL_FUN() {
        return EXTERNAL_FUN;
    }

    public static final FlagsToModifiers getEXTERNAL_PROPERTY() {
        return EXTERNAL_PROPERTY;
    }

    public static final FlagsToModifiers getFUN_INTERFACE() {
        return FUN_INTERFACE;
    }

    public static final FlagsToModifiers getINFIX() {
        return INFIX;
    }

    public static final FlagsToModifiers getINLINE() {
        return INLINE;
    }

    public static final FlagsToModifiers getINLINE_ACCESSOR() {
        return INLINE_ACCESSOR;
    }

    public static final FlagsToModifiers getINNER() {
        return INNER;
    }

    public static final FlagsToModifiers getINTERFACE_MODALITY() {
        return INTERFACE_MODALITY;
    }

    public static final FlagsToModifiers getLATEINIT() {
        return LATEINIT;
    }

    public static final FlagsToModifiers getMODALITY() {
        return MODALITY;
    }

    public static final FlagsToModifiers getOPERATOR() {
        return OPERATOR;
    }

    public static final FlagsToModifiers getSUSPEND() {
        return SUSPEND;
    }

    public static final FlagsToModifiers getTAILREC() {
        return TAILREC;
    }

    public static final FlagsToModifiers getVALUE_CLASS() {
        return VALUE_CLASS;
    }

    public static final FlagsToModifiers getVISIBILITY() {
        return VISIBILITY;
    }
}
