package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SimpleInterval;", "Lorg/jetbrains/kotlin/codegen/inline/Interval;", "startLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "endLabel", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;)V", "getStartLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getEndLabel", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SimpleInterval implements Interval {
    private final LabelNode endLabel;
    private final LabelNode startLabel;

    public SimpleInterval(LabelNode labelNode, LabelNode labelNode2) {
        labelNode.getClass();
        labelNode2.getClass();
        this.startLabel = labelNode;
        this.endLabel = labelNode2;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getEndLabel() {
        return this.endLabel;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.Interval
    public LabelNode getStartLabel() {
        return this.startLabel;
    }
}
