package org.jetbrains.kotlin.ir;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H&J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H&J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H&J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001dÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/IrFileEntry;", "", "name", "", "getName", "()Ljava/lang/String;", "maxOffset", "", "getMaxOffset", "()I", "lineStartOffsets", "", "getLineStartOffsets", "()[I", "firstRelevantLineIndex", "getFirstRelevantLineIndex", "supportsDebugInfo", "", "getSupportsDebugInfo", "()Z", "getSourceRangeInfo", "Lorg/jetbrains/kotlin/ir/SourceRangeInfo;", "beginOffset", "endOffset", "getLineNumber", "offset", "getColumnNumber", "getLineAndColumnNumbers", "Lorg/jetbrains/kotlin/ir/LineAndColumn;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrFileEntry {
    int getColumnNumber(int offset);

    int getFirstRelevantLineIndex();

    LineAndColumn getLineAndColumnNumbers(int offset);

    int getLineNumber(int offset);

    int[] getLineStartOffsets();

    int getMaxOffset();

    String getName();

    SourceRangeInfo getSourceRangeInfo(int beginOffset, int endOffset);

    default boolean getSupportsDebugInfo() {
        return true;
    }
}
