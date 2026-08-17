package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "classSMAP", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/kotlin/codegen/inline/SMAP;)V", "getNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getClassSMAP", "()Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SMAPAndMethodNode {
    private final SMAP classSMAP;
    private final MethodNode node;

    public SMAPAndMethodNode(MethodNode methodNode, SMAP smap) {
        methodNode.getClass();
        smap.getClass();
        this.node = methodNode;
        this.classSMAP = smap;
    }

    public static /* synthetic */ SMAPAndMethodNode copy$default(SMAPAndMethodNode sMAPAndMethodNode, MethodNode methodNode, SMAP smap, int i, Object obj) {
        if ((i & 1) != 0) {
            methodNode = sMAPAndMethodNode.node;
        }
        if ((i & 2) != 0) {
            smap = sMAPAndMethodNode.classSMAP;
        }
        return sMAPAndMethodNode.copy(methodNode, smap);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final MethodNode getNode() {
        return this.node;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SMAP getClassSMAP() {
        return this.classSMAP;
    }

    public final SMAPAndMethodNode copy(MethodNode node, SMAP classSMAP) {
        node.getClass();
        classSMAP.getClass();
        return new SMAPAndMethodNode(node, classSMAP);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SMAPAndMethodNode)) {
            return false;
        }
        SMAPAndMethodNode sMAPAndMethodNode = (SMAPAndMethodNode) other;
        return Intrinsics.areEqual(this.node, sMAPAndMethodNode.node) && Intrinsics.areEqual(this.classSMAP, sMAPAndMethodNode.classSMAP);
    }

    public final SMAP getClassSMAP() {
        return this.classSMAP;
    }

    public final MethodNode getNode() {
        return this.node;
    }

    public int hashCode() {
        return (this.node.hashCode() * 31) + this.classSMAP.hashCode();
    }

    public String toString() {
        return "SMAPAndMethodNode(node=" + this.node + ", classSMAP=" + this.classSMAP + ')';
    }
}
