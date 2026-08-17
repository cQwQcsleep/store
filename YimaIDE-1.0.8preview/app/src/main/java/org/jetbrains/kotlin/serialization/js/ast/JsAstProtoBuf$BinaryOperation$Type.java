package org.jetbrains.kotlin.serialization.js.ast;

import org.jetbrains.kotlin.protobuf.Internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public enum JsAstProtoBuf$BinaryOperation$Type implements Internal.EnumLite {
    MUL(0, 1),
    DIV(1, 2),
    MOD(2, 3),
    ADD(3, 4),
    SUB(4, 5),
    SHL(5, 6),
    SHR(6, 7),
    SHRU(7, 8),
    LT(8, 9),
    LTE(9, 10),
    GT(10, 11),
    GTE(11, 12),
    INSTANCEOF(12, 13),
    IN(13, 14),
    EQ(14, 15),
    NEQ(15, 16),
    REF_EQ(16, 17),
    REF_NEQ(17, 18),
    BIT_AND(18, 19),
    BIT_XOR(19, 20),
    BIT_OR(20, 21),
    AND(21, 22),
    OR(22, 23),
    ASG(23, 24),
    ASG_ADD(24, 25),
    ASG_SUB(25, 26),
    ASG_MUL(26, 27),
    ASG_DIV(27, 28),
    ASG_MOD(28, 29),
    ASG_SHL(29, 30),
    ASG_SHR(30, 31),
    ASG_SHRU(31, 32),
    ASG_BIT_AND(32, 33),
    ASG_BIT_OR(33, 34),
    ASG_BIT_XOR(34, 35),
    COMMA(35, 36);

    public static final int ADD_VALUE = 4;
    public static final int AND_VALUE = 22;
    public static final int ASG_ADD_VALUE = 25;
    public static final int ASG_BIT_AND_VALUE = 33;
    public static final int ASG_BIT_OR_VALUE = 34;
    public static final int ASG_BIT_XOR_VALUE = 35;
    public static final int ASG_DIV_VALUE = 28;
    public static final int ASG_MOD_VALUE = 29;
    public static final int ASG_MUL_VALUE = 27;
    public static final int ASG_SHL_VALUE = 30;
    public static final int ASG_SHRU_VALUE = 32;
    public static final int ASG_SHR_VALUE = 31;
    public static final int ASG_SUB_VALUE = 26;
    public static final int ASG_VALUE = 24;
    public static final int BIT_AND_VALUE = 19;
    public static final int BIT_OR_VALUE = 21;
    public static final int BIT_XOR_VALUE = 20;
    public static final int COMMA_VALUE = 36;
    public static final int DIV_VALUE = 2;
    public static final int EQ_VALUE = 15;
    public static final int GTE_VALUE = 12;
    public static final int GT_VALUE = 11;
    public static final int INSTANCEOF_VALUE = 13;
    public static final int IN_VALUE = 14;
    public static final int LTE_VALUE = 10;
    public static final int LT_VALUE = 9;
    public static final int MOD_VALUE = 3;
    public static final int MUL_VALUE = 1;
    public static final int NEQ_VALUE = 16;
    public static final int OR_VALUE = 23;
    public static final int REF_EQ_VALUE = 17;
    public static final int REF_NEQ_VALUE = 18;
    public static final int SHL_VALUE = 6;
    public static final int SHRU_VALUE = 8;
    public static final int SHR_VALUE = 7;
    public static final int SUB_VALUE = 5;
    private static Internal.EnumLiteMap<JsAstProtoBuf$BinaryOperation$Type> internalValueMap = new Internal.EnumLiteMap<JsAstProtoBuf$BinaryOperation$Type>() { // from class: org.jetbrains.kotlin.serialization.js.ast.JsAstProtoBuf$BinaryOperation$Type.1
        @Override // org.jetbrains.kotlin.protobuf.Internal.EnumLiteMap
        public JsAstProtoBuf$BinaryOperation$Type findValueByNumber(int i) {
            return JsAstProtoBuf$BinaryOperation$Type.valueOf(i);
        }
    };
    private final int value;

    JsAstProtoBuf$BinaryOperation$Type(int i, int i2) {
        this.value = i2;
    }

    public static Internal.EnumLiteMap<JsAstProtoBuf$BinaryOperation$Type> internalGetValueMap() {
        return internalValueMap;
    }

    public static JsAstProtoBuf$BinaryOperation$Type valueOf(int i) {
        switch (i) {
            case 1:
                return MUL;
            case 2:
                return DIV;
            case 3:
                return MOD;
            case 4:
                return ADD;
            case 5:
                return SUB;
            case 6:
                return SHL;
            case 7:
                return SHR;
            case 8:
                return SHRU;
            case 9:
                return LT;
            case 10:
                return LTE;
            case 11:
                return GT;
            case 12:
                return GTE;
            case 13:
                return INSTANCEOF;
            case 14:
                return IN;
            case 15:
                return EQ;
            case 16:
                return NEQ;
            case 17:
                return REF_EQ;
            case 18:
                return REF_NEQ;
            case 19:
                return BIT_AND;
            case 20:
                return BIT_XOR;
            case 21:
                return BIT_OR;
            case 22:
                return AND;
            case 23:
                return OR;
            case 24:
                return ASG;
            case 25:
                return ASG_ADD;
            case 26:
                return ASG_SUB;
            case 27:
                return ASG_MUL;
            case 28:
                return ASG_DIV;
            case 29:
                return ASG_MOD;
            case 30:
                return ASG_SHL;
            case 31:
                return ASG_SHR;
            case 32:
                return ASG_SHRU;
            case 33:
                return ASG_BIT_AND;
            case 34:
                return ASG_BIT_OR;
            case 35:
                return ASG_BIT_XOR;
            case 36:
                return COMMA;
            default:
                return null;
        }
    }

    @Override // org.jetbrains.kotlin.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }
}
