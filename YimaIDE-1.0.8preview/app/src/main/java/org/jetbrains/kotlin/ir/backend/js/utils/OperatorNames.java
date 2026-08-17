package org.jetbrains.kotlin.ir.backend.js.utils;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\"\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050)¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00050)¢\u0006\b\n\u0000\u001a\u0004\b-\u0010+R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00050)¢\u0006\b\n\u0000\u001a\u0004\b/\u0010+¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/utils/OperatorNames;", "", "<init>", "()V", "UNARY_PLUS", "Lorg/jetbrains/kotlin/name/Name;", "getUNARY_PLUS", "()Lorg/jetbrains/kotlin/name/Name;", "UNARY_MINUS", "getUNARY_MINUS", "ADD", "getADD", "SUB", "getSUB", "MUL", "getMUL", "DIV", "getDIV", "REM", "getREM", "AND", "getAND", "OR", "getOR", "XOR", "getXOR", "INV", "getINV", "SHL", "getSHL", "SHR", "getSHR", "SHRU", "getSHRU", "NOT", "getNOT", "INC", "getINC", "DEC", "getDEC", "BINARY", "", "getBINARY", "()Ljava/util/Set;", "UNARY", "getUNARY", "ALL", "getALL", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class OperatorNames {
    private static final Name ADD;
    private static final Set<Name> ALL;
    private static final Name AND;
    private static final Set<Name> BINARY;
    private static final Name DEC;
    private static final Name DIV;
    private static final Name INC;
    public static final OperatorNames INSTANCE = new OperatorNames();
    private static final Name INV;
    private static final Name MUL;
    private static final Name NOT;
    private static final Name OR;
    private static final Name REM;
    private static final Name SHL;
    private static final Name SHR;
    private static final Name SHRU;
    private static final Name SUB;
    private static final Set<Name> UNARY;
    private static final Name UNARY_MINUS;
    private static final Name UNARY_PLUS;
    private static final Name XOR;

    static {
        Name name = OperatorNameConventions.UNARY_PLUS;
        UNARY_PLUS = name;
        Name name2 = OperatorNameConventions.UNARY_MINUS;
        UNARY_MINUS = name2;
        Name name3 = OperatorNameConventions.PLUS;
        ADD = name3;
        Name name4 = OperatorNameConventions.MINUS;
        SUB = name4;
        Name name5 = OperatorNameConventions.TIMES;
        MUL = name5;
        Name name6 = OperatorNameConventions.DIV;
        DIV = name6;
        Name name7 = OperatorNameConventions.REM;
        REM = name7;
        Name name8 = OperatorNameConventions.AND;
        AND = name8;
        Name name9 = OperatorNameConventions.OR;
        OR = name9;
        Name name10 = OperatorNameConventions.XOR;
        XOR = name10;
        Name name11 = OperatorNameConventions.INV;
        INV = name11;
        Name name12 = OperatorNameConventions.SHL;
        SHL = name12;
        Name name13 = OperatorNameConventions.SHR;
        SHR = name13;
        Name name14 = OperatorNameConventions.USHR;
        SHRU = name14;
        Name name15 = OperatorNameConventions.NOT;
        NOT = name15;
        Name name16 = OperatorNameConventions.INC;
        INC = name16;
        Name name17 = OperatorNameConventions.DEC;
        DEC = name17;
        Set<Name> of = SetsKt.setOf(new Name[]{name3, name4, name5, name6, name7, name8, name9, name10, name12, name13, name14});
        BINARY = of;
        Set<Name> of2 = SetsKt.setOf(new Name[]{name, name2, name11, name15, name16, name17});
        UNARY = of2;
        ALL = SetsKt.plus(of, of2);
    }

    private OperatorNames() {
    }

    public final Name getADD() {
        return ADD;
    }

    public final Set<Name> getALL() {
        return ALL;
    }

    public final Name getAND() {
        return AND;
    }

    public final Set<Name> getBINARY() {
        return BINARY;
    }

    public final Name getDEC() {
        return DEC;
    }

    public final Name getDIV() {
        return DIV;
    }

    public final Name getINC() {
        return INC;
    }

    public final Name getINV() {
        return INV;
    }

    public final Name getMUL() {
        return MUL;
    }

    public final Name getNOT() {
        return NOT;
    }

    public final Name getOR() {
        return OR;
    }

    public final Name getREM() {
        return REM;
    }

    public final Name getSHL() {
        return SHL;
    }

    public final Name getSHR() {
        return SHR;
    }

    public final Name getSHRU() {
        return SHRU;
    }

    public final Name getSUB() {
        return SUB;
    }

    public final Set<Name> getUNARY() {
        return UNARY;
    }

    public final Name getUNARY_MINUS() {
        return UNARY_MINUS;
    }

    public final Name getUNARY_PLUS() {
        return UNARY_PLUS;
    }

    public final Name getXOR() {
        return XOR;
    }
}
