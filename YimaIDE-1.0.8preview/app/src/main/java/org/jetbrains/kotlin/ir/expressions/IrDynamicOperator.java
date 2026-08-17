package org.jetbrains.kotlin.ir.expressions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b$\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "", "image", "", "isAssignmentOperator", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;Z)V", "getImage", "()Ljava/lang/String;", "()Z", "UNARY_PLUS", "UNARY_MINUS", "EXCL", "PREFIX_INCREMENT", "POSTFIX_INCREMENT", "PREFIX_DECREMENT", "POSTFIX_DECREMENT", "BINARY_PLUS", "BINARY_MINUS", "MUL", "DIV", "MOD", "GT", "LT", "GE", "LE", BuiltInOperatorNames.EQEQ, "EXCLEQ", BuiltInOperatorNames.EQEQEQ, "EXCLEQEQ", BuiltInOperatorNames.ANDAND, BuiltInOperatorNames.OROR, "EQ", "PLUSEQ", "MINUSEQ", "MULEQ", "DIVEQ", "MODEQ", "ARRAY_ACCESS", "INVOKE", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum IrDynamicOperator {
    UNARY_PLUS("+", false, 2, null),
    UNARY_MINUS("-", false, 2, null),
    EXCL("!", false, 2, null),
    PREFIX_INCREMENT("++", true),
    POSTFIX_INCREMENT("++", true),
    PREFIX_DECREMENT("--", true),
    POSTFIX_DECREMENT("--", true),
    BINARY_PLUS("+", false, 2, null),
    BINARY_MINUS("-", false, 2, null),
    MUL("*", false, 2, null),
    DIV("/", false, 2, null),
    MOD("%", false, 2, null),
    GT(">", false, 2, null),
    LT("<", false, 2, null),
    GE(">=", false, 2, null),
    LE("<=", false, 2, null),
    EQEQ("==", false, 2, null),
    EXCLEQ("!=", false, 2, null),
    EQEQEQ("===", false, 2, null),
    EXCLEQEQ("!==", false, 2, null),
    ANDAND("&&", false, 2, null),
    OROR("||", false, 2, null),
    EQ("=", true),
    PLUSEQ("+=", true),
    MINUSEQ("-=", true),
    MULEQ("*=", true),
    DIVEQ("/=", true),
    MODEQ("%=", true),
    ARRAY_ACCESS("[]", false, 2, null),
    INVOKE("()", false, 2, null);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String image;
    private final boolean isAssignmentOperator;

    /* synthetic */ IrDynamicOperator(String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z);
    }

    public static EnumEntries<IrDynamicOperator> getEntries() {
        return $ENTRIES;
    }

    public final String getImage() {
        return this.image;
    }

    /* JADX INFO: renamed from: isAssignmentOperator, reason: from getter */
    public final boolean getIsAssignmentOperator() {
        return this.isAssignmentOperator;
    }

    IrDynamicOperator(String str, boolean z) {
        this.image = str;
        this.isAssignmentOperator = z;
    }
}
