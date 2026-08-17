package org.jetbrains.kotlin.codegen.coroutines;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aK\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\tH\u0000¢\u0006\u0002\u0010\u0010\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\u0011"}, d2 = {"COMPARE_ALGORITHMS_PERFORMANCE", Argument.Delimiters.none, "getCOMPARE_ALGORITHMS_PERFORMANCE$annotations", "()V", "calculateVariablesToReinitializeBySuspensionPoint", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "containingClassName", Argument.Delimiters.none, "variablesToSpillBySuspensionPointIndex", "(Ljava/util/List;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/lang/String;Ljava/util/List;)[Ljava/util/List;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResumePointDependentAnalysisKt {
    public static final List<SpillableVariable>[] calculateVariablesToReinitializeBySuspensionPoint(List<SuspensionPoint> list, MethodNode methodNode, String str, List<? extends List<SpillableVariable>> list2) {
        list.getClass();
        methodNode.getClass();
        str.getClass();
        list2.getClass();
        return calculateVariablesToReinitializeBySuspensionPoint$createAlgorithm(list, methodNode, str, list2, false).calculate();
    }

    private static final ReinitializationAnalysis calculateVariablesToReinitializeBySuspensionPoint$createAlgorithm(List<SuspensionPoint> list, MethodNode methodNode, String str, List<? extends List<SpillableVariable>> list2, boolean z) {
        return z ? new ReinitializationAnalysisUsingDFA(list, methodNode, str, list2) : new ReinitializationAnalysisUsingDFS(list, methodNode, str, list2);
    }
}
