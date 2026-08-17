package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.ArrayList;
import java.util.BitSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000H\u0016J\b\u0010\u000f\u001a\u00020\rH\u0016J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0014\u001a\u00020\u000bJ\u0014\u0010\u0015\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0003H\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/VariableLivenessFrame;", "Lorg/jetbrains/kotlin/codegen/optimization/common/VarFrame;", "maxLocals", Argument.Delimiters.none, "<init>", "(I)V", "getMaxLocals", "()I", "bitSet", "Ljava/util/BitSet;", "controlFlowMerge", Argument.Delimiters.none, "mergeFrom", Argument.Delimiters.none, "other", "markControlFlowMerge", "markAlive", "varIndex", "markDead", "isAlive", "isControlFlowMerge", "equals", Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariableLivenessFrame implements VarFrame<VariableLivenessFrame> {
    private final BitSet bitSet;
    private boolean controlFlowMerge;
    private final int maxLocals;

    public VariableLivenessFrame(int i) {
        this.maxLocals = i;
        this.bitSet = new BitSet(i);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.VarFrame
    public boolean equals(Object other) {
        if (!(other instanceof VariableLivenessFrame)) {
            return false;
        }
        VariableLivenessFrame variableLivenessFrame = (VariableLivenessFrame) other;
        return Intrinsics.areEqual(this.bitSet, variableLivenessFrame.bitSet) && this.controlFlowMerge == variableLivenessFrame.controlFlowMerge;
    }

    public final int getMaxLocals() {
        return this.maxLocals;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.VarFrame
    public int hashCode() {
        return (this.bitSet.hashCode() * 31) + Boolean.hashCode(this.controlFlowMerge);
    }

    public final boolean isAlive(int varIndex) {
        return this.bitSet.get(varIndex);
    }

    /* JADX INFO: renamed from: isControlFlowMerge, reason: from getter */
    public final boolean getControlFlowMerge() {
        return this.controlFlowMerge;
    }

    public final void markAlive(int varIndex) {
        this.bitSet.set(varIndex, true);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.VarFrame
    public void markControlFlowMerge() {
        this.controlFlowMerge = true;
    }

    public final void markDead(int varIndex) {
        this.bitSet.set(varIndex, false);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.VarFrame
    public void mergeFrom(VariableLivenessFrame other) {
        other.getClass();
        this.bitSet.or(other.bitSet);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.controlFlowMerge ? "*" : Argument.Delimiters.space);
        IntRange intRangeUntil = RangesKt.until(0, this.maxLocals);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        IntIterator it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(Character.valueOf(this.bitSet.get(it.nextInt()) ? '@' : '_'));
        }
        sb.append(CollectionsKt.joinToString$default(arrayList, Argument.Delimiters.none, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        return sb.toString();
    }
}
