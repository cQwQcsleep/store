package org.eclipse.jdt.internal.compiler.env;

import org.eclipse.jdt.core.compiler.CharOperation;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AccessRule {
    public static final int IgnoreIfBetter = 33554432;
    public final char[] pattern;
    public final int problemId;

    public AccessRule(char[] cArr, int i, boolean z) {
        this.pattern = cArr;
        this.problemId = z ? i | 33554432 : i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessRule)) {
            return false;
        }
        AccessRule accessRule = (AccessRule) obj;
        if (this.problemId != accessRule.problemId) {
            return false;
        }
        return CharOperation.equals(this.pattern, accessRule.pattern);
    }

    public int getProblemId() {
        return this.problemId & (-33554433);
    }

    public int hashCode() {
        return (this.problemId * 17) + CharOperation.hashCode(this.pattern);
    }

    public boolean ignoreIfBetter() {
        return (this.problemId & 33554432) != 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("pattern=");
        sb.append(this.pattern);
        int problemId = getProblemId();
        if (problemId == 16777496) {
            sb.append(" (DISCOURAGED");
        } else if (problemId != 16777523) {
            sb.append(" (ACCESSIBLE");
        } else {
            sb.append(" (NON ACCESSIBLE");
        }
        if (ignoreIfBetter()) {
            sb.append(" | IGNORE IF BETTER");
        }
        sb.append(Util.C_PARAM_END);
        return sb.toString();
    }

    public AccessRule(char[] cArr, int i) {
        this(cArr, i, false);
    }
}
