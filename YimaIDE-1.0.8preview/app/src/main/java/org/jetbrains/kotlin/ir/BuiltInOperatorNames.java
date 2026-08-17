package org.jetbrains.kotlin.ir;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/BuiltInOperatorNames;", "", "<init>", "()V", "LESS", "", "LESS_OR_EQUAL", "GREATER", "GREATER_OR_EQUAL", "COMPARE_TO", BuiltInOperatorNames.EQEQ, BuiltInOperatorNames.EQEQEQ, "IEEE754_EQUALS", BuiltInOperatorNames.THROW_CCE, BuiltInOperatorNames.THROW_ISE, "NO_WHEN_BRANCH_MATCHED_EXCEPTION", "ILLEGAL_ARGUMENT_EXCEPTION", BuiltInOperatorNames.ANDAND, BuiltInOperatorNames.OROR, BuiltInOperatorNames.CHECK_NOT_NULL, "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BuiltInOperatorNames {
    public static final String ANDAND = "ANDAND";
    public static final String CHECK_NOT_NULL = "CHECK_NOT_NULL";
    public static final String COMPARE_TO = "compareTo";
    public static final String EQEQ = "EQEQ";
    public static final String EQEQEQ = "EQEQEQ";
    public static final String GREATER = "greater";
    public static final String GREATER_OR_EQUAL = "greaterOrEqual";
    public static final String IEEE754_EQUALS = "ieee754equals";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION = "illegalArgumentException";
    public static final BuiltInOperatorNames INSTANCE = new BuiltInOperatorNames();
    public static final String LESS = "less";
    public static final String LESS_OR_EQUAL = "lessOrEqual";
    public static final String NO_WHEN_BRANCH_MATCHED_EXCEPTION = "noWhenBranchMatchedException";
    public static final String OROR = "OROR";
    public static final String THROW_CCE = "THROW_CCE";
    public static final String THROW_ISE = "THROW_ISE";

    private BuiltInOperatorNames() {
    }
}
