package org.jetbrains.kotlin;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u001c\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0012\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/KtSourceFileLinesMappingFromLineStartOffsets;", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "lineStartOffsets", "", "lastOffset", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "([II)V", "getLineStartOffsets", "()[I", "getLastOffset", "()I", "getLineStartOffset", "line", "getLineAndColumnByOffset", "Lkotlin/Pair;", "offset", "getLineByOffset", "linesCount", "getLinesCount", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public class KtSourceFileLinesMappingFromLineStartOffsets implements KtSourceFileLinesMapping {
    private final int lastOffset;
    private final int[] lineStartOffsets;

    public KtSourceFileLinesMappingFromLineStartOffsets(int[] iArr, int i) {
        iArr.getClass();
        this.lineStartOffsets = iArr;
        this.lastOffset = i;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLastOffset() {
        return this.lastOffset;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public Pair<Integer, Integer> getLineAndColumnByOffset(int offset) {
        int lineByOffset = getLineByOffset(offset);
        if (lineByOffset < 0) {
            return TuplesKt.to(-1, -1);
        }
        return TuplesKt.to(Integer.valueOf(lineByOffset), Integer.valueOf(offset - this.lineStartOffsets[lineByOffset]));
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLineByOffset(int offset) {
        int iBinarySearch$default = ArraysKt.binarySearch$default(this.lineStartOffsets, offset, 0, 0, 6, (Object) null);
        return iBinarySearch$default >= 0 ? iBinarySearch$default : (-iBinarySearch$default) - 2;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLineStartOffset(int line) {
        return this.lineStartOffsets[line];
    }

    public final int[] getLineStartOffsets() {
        return this.lineStartOffsets;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLinesCount() {
        return this.lineStartOffsets.length;
    }
}
