package io.github.rosemoe.sora.lang.smartEnter;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NewlineHandleResult {
    public final int shiftLeft;
    public final CharSequence text;

    public NewlineHandleResult(CharSequence charSequence, int i) {
        this.text = charSequence;
        this.shiftLeft = i;
        if (i < 0 || i > charSequence.length()) {
            w01.a("invalid shiftLeft");
            throw null;
        }
    }
}
