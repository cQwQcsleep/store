package org.snakeyaml.engine.v2.emitter;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class ScalarAnalysis {
    private final boolean allowBlock;
    private final boolean allowBlockPlain;
    private final boolean allowFlowPlain;
    private final boolean allowSingleQuoted;
    private final boolean empty;
    private final boolean multiline;
    private final String scalar;

    public ScalarAnalysis(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.scalar = str;
        this.empty = z;
        this.multiline = z2;
        this.allowFlowPlain = z3;
        this.allowBlockPlain = z4;
        this.allowSingleQuoted = z5;
        this.allowBlock = z6;
    }

    public String getScalar() {
        return this.scalar;
    }

    public boolean isAllowBlock() {
        return this.allowBlock;
    }

    public boolean isAllowBlockPlain() {
        return this.allowBlockPlain;
    }

    public boolean isAllowFlowPlain() {
        return this.allowFlowPlain;
    }

    public boolean isAllowSingleQuoted() {
        return this.allowSingleQuoted;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isMultiline() {
        return this.multiline;
    }
}
