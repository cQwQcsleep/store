package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\u0010\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u001cH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;", "Lorg/jetbrains/kotlin/codegen/inline/IntervalWithHandler;", "Lorg/jetbrains/kotlin/codegen/inline/SplittableInterval;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "onlyCopyNotProcess", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;Z)V", "getNode", "()Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "getOnlyCopyNotProcess", "()Z", "startLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getStartLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "endLabel", "getEndLabel", "handler", "getHandler", ModuleXmlParser.TYPE, Argument.Delimiters.none, "getType", "()Ljava/lang/String;", "split", "Lorg/jetbrains/kotlin/codegen/inline/SplitPair;", "splitBy", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "keepStart", "copyWithNewBounds", "newBounds", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TryCatchBlockNodeInfo implements IntervalWithHandler, SplittableInterval<TryCatchBlockNodeInfo> {
    private final TryCatchBlockNode node;
    private final boolean onlyCopyNotProcess;

    public TryCatchBlockNodeInfo(TryCatchBlockNode tryCatchBlockNode, boolean z) {
        tryCatchBlockNode.getClass();
        this.node = tryCatchBlockNode;
        this.onlyCopyNotProcess = z;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.SplittableInterval
    public TryCatchBlockNodeInfo copyWithNewBounds(Interval newBounds) {
        newBounds.getClass();
        return new TryCatchBlockNodeInfo(new TryCatchBlockNode(newBounds.getStartLabel(), newBounds.getEndLabel(), getHandler(), getType()), this.onlyCopyNotProcess);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getEndLabel() {
        LabelNode labelNode = this.node.end;
        labelNode.getClass();
        return labelNode;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.IntervalWithHandler
    public LabelNode getHandler() {
        LabelNode labelNode = this.node.handler;
        labelNode.getClass();
        return labelNode;
    }

    public final TryCatchBlockNode getNode() {
        return this.node;
    }

    public final boolean getOnlyCopyNotProcess() {
        return this.onlyCopyNotProcess;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getStartLabel() {
        LabelNode labelNode = this.node.start;
        labelNode.getClass();
        return labelNode;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.IntervalWithHandler
    public String getType() {
        return this.node.type;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.SplittableInterval
    public SplitPair<TryCatchBlockNodeInfo> split(Interval splitBy, boolean keepStart) {
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
