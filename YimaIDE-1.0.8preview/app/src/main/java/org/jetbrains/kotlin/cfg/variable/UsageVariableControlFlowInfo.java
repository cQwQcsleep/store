package org.jetbrains.kotlin.cfg.variable;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cfg.ControlFlowInfo;
import org.jetbrains.kotlin.cfg.ReadOnlyControlFlowInfo;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002$\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0003`\u00042\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0006B-\u0012$\b\u0002\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\bj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\t¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\f\u001a\u00020\u00002\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\bj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\tH\u0014¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cfg/variable/UsageVariableControlFlowInfo;", "Lorg/jetbrains/kotlin/cfg/ControlFlowInfo;", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "Lorg/jetbrains/kotlin/cfg/variable/VariableUseState;", "Lorg/jetbrains/kotlin/cfg/variable/VariableUsageControlFlowInfo;", "Lorg/jetbrains/kotlin/cfg/ReadOnlyControlFlowInfo;", "Lorg/jetbrains/kotlin/cfg/variable/VariableUsageReadOnlyControlInfo;", "map", "Lio/vavr/collection/Map;", "Lorg/jetbrains/kotlin/util/vavr/ImmutableMap;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lio/vavr/collection/Map;)V", "copy", "newMap", "org.jetbrains.kotlin:cfg"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class UsageVariableControlFlowInfo extends ControlFlowInfo<UsageVariableControlFlowInfo, VariableDescriptor, VariableUseState> implements ReadOnlyControlFlowInfo<VariableDescriptor, VariableUseState> {
    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ UsageVariableControlFlowInfo(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            map = HashMap.empty();
            map.getClass();
        }
        this(map);
    }

    /* JADX INFO: renamed from: copy, reason: collision with other method in class */
    public UsageVariableControlFlowInfo m2168copy(Map<VariableDescriptor, VariableUseState> newMap) {
        newMap.getClass();
        return new UsageVariableControlFlowInfo(newMap);
    }

    public /* bridge */ /* synthetic */ ControlFlowInfo copy(Map map) {
        return m2168copy((Map<VariableDescriptor, VariableUseState>) map);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UsageVariableControlFlowInfo(Map<VariableDescriptor, VariableUseState> map) {
        super(map);
        map.getClass();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public UsageVariableControlFlowInfo() {
        Map map = null;
        this(map, 1, map);
    }
}
