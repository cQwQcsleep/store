package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0001H\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/LocalVarNodeWrapper;", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;)V", "getNode", "()Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "startLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getStartLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "endLabel", "getEndLabel", "split", "Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "splitBy", "keepStart", Argument.Delimiters.none, "copyWithNewBounds", "newBounds", "remapLabel", Argument.Delimiters.none, "oldLabel", "newLabel", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalVarNodeWrapper implements Interval, SplittableInterval<LocalVarNodeWrapper> {
    private final LocalVariableNode node;

    public LocalVarNodeWrapper(LocalVariableNode localVariableNode) {
        localVariableNode.getClass();
        this.node = localVariableNode;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.SplittableInterval
    public LocalVarNodeWrapper copyWithNewBounds(Interval newBounds) {
        newBounds.getClass();
        LocalVariableNode localVariableNode = this.node;
        return new LocalVarNodeWrapper(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, newBounds.getStartLabel(), newBounds.getEndLabel(), this.node.index));
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getEndLabel() {
        LabelNode labelNode = this.node.end;
        labelNode.getClass();
        return labelNode;
    }

    public final LocalVariableNode getNode() {
        return this.node;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getStartLabel() {
        LabelNode labelNode = this.node.start;
        labelNode.getClass();
        return labelNode;
    }

    public final void remapLabel(LabelNode oldLabel, LabelNode newLabel) {
        oldLabel.getClass();
        newLabel.getClass();
        if (Intrinsics.areEqual(this.node.start, oldLabel)) {
            this.node.start = newLabel;
        }
        if (Intrinsics.areEqual(this.node.end, oldLabel)) {
            this.node.end = newLabel;
        }
    }

    @Override // org.jetbrains.kotlin.codegen.inline.SplittableInterval
    public SplitPair<LocalVarNodeWrapper> split(Interval splitBy, boolean keepStart) {
        SimpleInterval simpleInterval;
        splitBy.getClass();
        if (keepStart) {
            LabelNode endLabel = getEndLabel();
            this.node.end = splitBy.getStartLabel();
            simpleInterval = new SimpleInterval(splitBy.getEndLabel(), endLabel);
        } else {
            LabelNode startLabel = getStartLabel();
            this.node.start = splitBy.getEndLabel();
            simpleInterval = new SimpleInterval(startLabel, splitBy.getStartLabel());
        }
        return new SplitPair<>(this, copyWithNewBounds((Interval) simpleInterval));
    }
}
