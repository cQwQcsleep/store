package io.github.rosemoe.sora.text.bidi;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IDirections {
    int getRunCount();

    int getRunEnd(int i);

    int getRunLevel(int i);

    int getRunStart(int i);

    boolean isRunRtl(int i);
}
