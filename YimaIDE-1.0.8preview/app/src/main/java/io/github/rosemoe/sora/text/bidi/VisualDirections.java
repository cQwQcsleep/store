package io.github.rosemoe.sora.text.bidi;

import io.github.rosemoe.sora.util.IntPair;
import java.text.Bidi;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class VisualDirections implements IDirections {
    private final RunInfo[] runs;

    public static class RunInfo {
        int level;
        long range;

        public RunInfo(long j, int i) {
            this.range = j;
            this.level = i;
        }
    }

    public VisualDirections(Directions directions) {
        int runCount = directions.getRunCount();
        this.runs = new RunInfo[runCount];
        byte[] bArr = new byte[runCount];
        for (int i = 0; i < runCount; i++) {
            bArr[i] = (byte) directions.getRunLevel(i);
            this.runs[i] = new RunInfo(IntPair.pack(directions.getRunStart(i), directions.getRunEnd(i)), directions.getRunLevel(i));
        }
        Bidi.reorderVisually(bArr, 0, this.runs, 0, runCount);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunCount() {
        return this.runs.length;
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunEnd(int i) {
        return IntPair.getSecond(this.runs[i].range);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunLevel(int i) {
        return this.runs[i].level;
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public int getRunStart(int i) {
        return IntPair.getFirst(this.runs[i].range);
    }

    @Override // io.github.rosemoe.sora.text.bidi.IDirections
    public boolean isRunRtl(int i) {
        return (getRunLevel(i) & 1) != 0;
    }
}
