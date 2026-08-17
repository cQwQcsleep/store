package org.jetbrains.kotlin.codegen.coroutines;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.time.Duration;
import kotlin.time.TimeSource;
import kotlin.time.TimedValue;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\b\u0003\b\"\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00030\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00160\u0015H&¢\u0006\u0002\u0010\u0017J\u0017\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00160\u0015¢\u0006\u0002\u0010\u0017R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysis;", Argument.Delimiters.none, "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "containingClassName", Argument.Delimiters.none, "variablesToSpillBySuspensionPointIndex", "Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", "<init>", "(Ljava/util/List;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/lang/String;Ljava/util/List;)V", "getSuspensionPoints", "()Ljava/util/List;", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getContainingClassName", "()Ljava/lang/String;", "getVariablesToSpillBySuspensionPointIndex", "calculate", Argument.Delimiters.none, Argument.Delimiters.none, "()[Ljava/util/List;", "calculateAndPrintPerformanceStats", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
abstract class ReinitializationAnalysis {
    private final String containingClassName;
    private final MethodNode methodNode;
    private final List<SuspensionPoint> suspensionPoints;
    private final List<List<SpillableVariable>> variablesToSpillBySuspensionPointIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public ReinitializationAnalysis(List<SuspensionPoint> list, MethodNode methodNode, String str, List<? extends List<SpillableVariable>> list2) {
        list.getClass();
        methodNode.getClass();
        str.getClass();
        list2.getClass();
        this.suspensionPoints = list;
        this.methodNode = methodNode;
        this.containingClassName = str;
        this.variablesToSpillBySuspensionPointIndex = list2;
    }

    public abstract List<SpillableVariable>[] calculate();

    public final List<SpillableVariable>[] calculateAndPrintPerformanceStats() {
        TimedValue timedValue = new TimedValue(calculate(), TimeSource.Monotonic.ValueTimeMark.elapsedNow-UwyO8pc(TimeSource.Monotonic.INSTANCE.markNow-z9LOYto()), (DefaultConstructorMarker) null);
        List<SpillableVariable>[] listArr = (List[]) timedValue.component1();
        System.out.println((Object) (Reflection.getOrCreateKotlinClass(getClass()).getSimpleName() + ".calculate() for " + this.containingClassName + "::" + this.methodNode.name + " took " + Duration.getInWholeMilliseconds-impl(timedValue.component2-UwyO8pc()) + " ms; nSP=" + this.suspensionPoints.size() + ", nLocals=" + this.methodNode.maxLocals + ", nInstructions=" + this.methodNode.instructions.size()));
        return listArr;
    }

    public final String getContainingClassName() {
        return this.containingClassName;
    }

    public final MethodNode getMethodNode() {
        return this.methodNode;
    }

    public final List<SuspensionPoint> getSuspensionPoints() {
        return this.suspensionPoints;
    }

    public final List<List<SpillableVariable>> getVariablesToSpillBySuspensionPointIndex() {
        return this.variablesToSpillBySuspensionPointIndex;
    }
}
