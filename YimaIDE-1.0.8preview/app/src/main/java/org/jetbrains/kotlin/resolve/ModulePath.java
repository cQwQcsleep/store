package org.jetbrains.kotlin.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u001d\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0007\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/ModulePath;", "", "nodes", "", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "<init>", "(Ljava/util/List;)V", "", "([Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)V", "getNodes", "()Ljava/util/List;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class ModulePath {
    private final List<ModuleDescriptor> nodes;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ModulePath(ModuleDescriptor... moduleDescriptorArr) {
        this((List<? extends ModuleDescriptor>) ArraysKt.toList(moduleDescriptorArr));
        moduleDescriptorArr.getClass();
    }

    public final List<ModuleDescriptor> getNodes() {
        return this.nodes;
    }

    public ModulePath(List<? extends ModuleDescriptor> list) {
        list.getClass();
        this.nodes = list;
    }
}
