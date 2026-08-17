package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u00070\u0006\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u0006\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"bodyInstuctions", "Lorg/jetbrains/kotlin/codegen/InsnSequence;", "Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;", "getBodyInstuctions", "(Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;)Lorg/jetbrains/kotlin/codegen/InsnSequence;", "doClustering", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/TryBlockCluster;", "T", "Lorg/jetbrains/kotlin/codegen/inline/IntervalWithHandler;", "blocks", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TryBlockClusteringKt {

    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\"\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"org/jetbrains/kotlin/codegen/inline/TryBlockClusteringKt$doClustering$TryBlockInterval", Argument.Delimiters.none, "startLabel", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "endLabel", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;)V", "getStartLabel", "()Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getEndLabel", "component1", "component2", "copy", "(Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;)Lorg/jetbrains/kotlin/codegen/inline/TryBlockClusteringKt$doClustering$TryBlockInterval;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class TryBlockInterval {
        private final LabelNode endLabel;
        private final LabelNode startLabel;

        public TryBlockInterval(LabelNode labelNode, LabelNode labelNode2) {
            labelNode.getClass();
            labelNode2.getClass();
            this.startLabel = labelNode;
            this.endLabel = labelNode2;
        }

        public static /* synthetic */ TryBlockInterval copy$default(TryBlockInterval tryBlockInterval, LabelNode labelNode, LabelNode labelNode2, int i, Object obj) {
            if ((i & 1) != 0) {
                labelNode = tryBlockInterval.startLabel;
            }
            if ((i & 2) != 0) {
                labelNode2 = tryBlockInterval.endLabel;
            }
            return tryBlockInterval.copy(labelNode, labelNode2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final LabelNode getStartLabel() {
            return this.startLabel;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final LabelNode getEndLabel() {
            return this.endLabel;
        }

        public final TryBlockInterval copy(LabelNode startLabel, LabelNode endLabel) {
            startLabel.getClass();
            endLabel.getClass();
            return new TryBlockInterval(startLabel, endLabel);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TryBlockInterval)) {
                return false;
            }
            TryBlockInterval tryBlockInterval = (TryBlockInterval) other;
            return Intrinsics.areEqual(this.startLabel, tryBlockInterval.startLabel) && Intrinsics.areEqual(this.endLabel, tryBlockInterval.endLabel);
        }

        public final LabelNode getEndLabel() {
            return this.endLabel;
        }

        public final LabelNode getStartLabel() {
            return this.startLabel;
        }

        public int hashCode() {
            return (this.startLabel.hashCode() * 31) + this.endLabel.hashCode();
        }

        public String toString() {
            return "TryBlockInterval(startLabel=" + this.startLabel + ", endLabel=" + this.endLabel + ')';
        }
    }

    public static final <T extends IntervalWithHandler> List<TryBlockCluster<T>> doClustering(List<? extends T> list) {
        list.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            IntervalWithHandler intervalWithHandler = (IntervalWithHandler) it.next();
            TryBlockInterval tryBlockInterval = new TryBlockInterval(InlineCodegenUtilsKt.firstLabelInChain(intervalWithHandler.getStartLabel()), InlineCodegenUtilsKt.firstLabelInChain(intervalWithHandler.getEndLabel()));
            Object tryBlockCluster = linkedHashMap.get(tryBlockInterval);
            if (tryBlockCluster == null) {
                tryBlockCluster = new TryBlockCluster(new ArrayList());
                linkedHashMap.put(tryBlockInterval, tryBlockCluster);
            }
            ((TryBlockCluster) tryBlockCluster).getBlocks().add(intervalWithHandler);
        }
        Collection collectionValues = linkedHashMap.values();
        collectionValues.getClass();
        return CollectionsKt.toList(collectionValues);
    }

    public static final InsnSequence getBodyInstuctions(TryCatchBlockNodeInfo tryCatchBlockNodeInfo) {
        tryCatchBlockNodeInfo.getClass();
        return new InsnSequence(tryCatchBlockNodeInfo.getStartLabel(), tryCatchBlockNodeInfo.getEndLabel());
    }
}
