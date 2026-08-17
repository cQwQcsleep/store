package org.eclipse.tm4e.core.internal.rule;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class RuleId {
    public final int id;
    public static final RuleId NO_RULE = new RuleId(0);
    public static final RuleId END_RULE = new RuleId(-1);
    public static final RuleId WHILE_RULE = new RuleId(-2);

    private RuleId(int i) {
        this.id = i;
    }

    public static RuleId of(int i) {
        if (i >= 0) {
            return new RuleId(i);
        }
        w01.a("[id] must be > 0");
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RuleId) && this.id == ((RuleId) obj).id;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean notEquals(RuleId ruleId) {
        return this.id != ruleId.id;
    }

    public String toString() {
        return Integer.toString(this.id);
    }

    public boolean equals(RuleId ruleId) {
        return this.id == ruleId.id;
    }
}
