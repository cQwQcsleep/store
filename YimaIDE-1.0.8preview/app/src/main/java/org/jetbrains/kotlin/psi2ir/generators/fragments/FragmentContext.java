package org.jetbrains.kotlin.psi2ir.generators.fragments;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/psi2ir/generators/fragments/FragmentContext;", "", "capturedDescriptorToFragmentParameterMap", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "Lorg/jetbrains/kotlin/ir/symbols/IrValueParameterSymbol;", "<init>", "(Ljava/util/Map;)V", "getCapturedDescriptorToFragmentParameterMap", "()Ljava/util/Map;", "org.jetbrains.kotlin:ir.psi2ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class FragmentContext {
    private final Map<DeclarationDescriptor, IrValueParameterSymbol> capturedDescriptorToFragmentParameterMap;

    public /* synthetic */ FragmentContext(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map);
    }

    public final Map<DeclarationDescriptor, IrValueParameterSymbol> getCapturedDescriptorToFragmentParameterMap() {
        return this.capturedDescriptorToFragmentParameterMap;
    }

    public FragmentContext(Map<DeclarationDescriptor, IrValueParameterSymbol> map) {
        map.getClass();
        this.capturedDescriptorToFragmentParameterMap = map;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FragmentContext() {
        Map map = null;
        this(map, 1, map);
    }
}
