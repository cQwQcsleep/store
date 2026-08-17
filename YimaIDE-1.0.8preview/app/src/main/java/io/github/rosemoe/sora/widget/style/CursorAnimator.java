package io.github.rosemoe.sora.widget.style;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface CursorAnimator {
    float animatedLineBottom();

    float animatedLineHeight();

    float animatedX();

    float animatedY();

    void cancel();

    boolean isRunning();

    void markEndPos();

    void markStartPos();

    void start();
}
