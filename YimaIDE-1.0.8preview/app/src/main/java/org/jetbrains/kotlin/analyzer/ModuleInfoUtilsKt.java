package org.jetbrains.kotlin.analyzer;

import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\b"}, d2 = {"moduleInfo", "Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getModuleInfo", "(Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;)Lorg/jetbrains/kotlin/analyzer/ModuleInfo;", "collectAllExpectedByModules", "", "entryModule", "org.jetbrains.kotlin:frontend"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ModuleInfoUtilsKt {
    public static final Set<ModuleInfo> collectAllExpectedByModules(ModuleInfo moduleInfo) {
        moduleInfo.getClass();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.addAll(moduleInfo.getExpectedBy());
        HashSet hashSet = new HashSet();
        while (!arrayDeque.isEmpty()) {
            ModuleInfo moduleInfo2 = (ModuleInfo) arrayDeque.removeFirst();
            if (hashSet.add(moduleInfo2)) {
                arrayDeque.addAll(moduleInfo2.getExpectedBy());
            }
        }
        return hashSet;
    }

    public static final ModuleInfo getModuleInfo(ModuleDescriptor moduleDescriptor) {
        moduleDescriptor.getClass();
        return (ModuleInfo) moduleDescriptor.getCapability(ModuleInfo.Companion.getCapability());
    }
}
