package org.eclipse.tm4e.core.internal.grammar.dependencies;

import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleConstant;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class AbsoluteRuleReference {
    public final String scopeName;

    public static final class TopLevelRepositoryRuleReference extends AbsoluteRuleReference {
        final String ruleName;

        public TopLevelRepositoryRuleReference(String str, String str2) {
            super(str);
            this.ruleName = str2;
        }

        @Override // org.eclipse.tm4e.core.internal.grammar.dependencies.AbsoluteRuleReference
        public String toKey() {
            return this.scopeName + MangleConstant.FUNCTION_NAME_PREFIX + this.ruleName;
        }
    }

    public static final class TopLevelRuleReference extends AbsoluteRuleReference {
        public TopLevelRuleReference(String str) {
            super(str);
        }
    }

    private AbsoluteRuleReference(String str) {
        this.scopeName = str;
    }

    public String toKey() {
        return this.scopeName;
    }
}
