package org.eclipse.tm4e.core.internal.rule;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class CompilePatternsResult {
    final boolean hasMissingPatterns;
    final RuleId[] patterns;

    public CompilePatternsResult(RuleId[] ruleIdArr, boolean z) {
        this.hasMissingPatterns = z;
        this.patterns = ruleIdArr;
    }
}
