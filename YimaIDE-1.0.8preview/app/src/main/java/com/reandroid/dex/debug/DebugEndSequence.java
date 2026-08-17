package com.reandroid.dex.debug;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugEndSequence extends DebugElement {
    public static final DebugEndSequence INSTANCE = new DebugEndSequence();

    private DebugEndSequence() {
        super(0, DebugElementType.END_SEQUENCE);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugEndSequence> getElementType() {
        return DebugElementType.END_SEQUENCE;
    }
}
