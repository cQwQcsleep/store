package org.jetbrains.kotlin.backend.common.serialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.backend.common.IrModuleDependencies;
import org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTrackerImpl;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.utils.DFS;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0007H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u0018\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTrackerImpl;", "Lorg/jetbrains/kotlin/backend/common/serialization/IrModuleDependencyTracker;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "trackedModules", "", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "", "getAllDependencies", "", "current", "result", "", "module", "addModuleForTracking", "trackDependency", "fromModule", "toModule", "reverseTopoOrder", "Lorg/jetbrains/kotlin/backend/common/IrModuleDependencies;", "moduleDependencies", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrModuleDependencyTrackerImpl implements IrModuleDependencyTracker {
    private final Map<IrModuleFragment, Set<IrModuleFragment>> trackedModules = new LinkedHashMap();

    public static Iterable a(IrModuleDependencyTrackerImpl irModuleDependencyTrackerImpl, IrModuleFragment irModuleFragment) {
        return (Iterable) MapsKt.getValue(irModuleDependencyTrackerImpl.trackedModules, irModuleFragment);
    }

    private final void getAllDependencies(IrModuleFragment current, Set<IrModuleFragment> result) {
        for (IrModuleFragment irModuleFragment : (Iterable) MapsKt.getValue(this.trackedModules, current)) {
            if (result.add(irModuleFragment)) {
                getAllDependencies(irModuleFragment, result);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence reverseTopoOrder$lambda$0$0(IrModuleFragment irModuleFragment) {
        irModuleFragment.getClass();
        String strAsString = irModuleFragment.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
    public void addModuleForTracking(IrModuleFragment module) {
        module.getClass();
        if (this.trackedModules.put(module, new LinkedHashSet()) == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("Module ");
        sb.append(module.getName());
        ode.a(sb, " is already present in ", Reflection.getOrCreateKotlinClass(IrModuleDependencyTrackerImpl.class));
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
    public IrModuleDependencies reverseTopoOrder(IrModuleDependencies moduleDependencies) {
        moduleDependencies.getClass();
        Set set = CollectionsKt.toSet(moduleDependencies.getAll());
        Set setMinus = SetsKt.minus(this.trackedModules.keySet(), set);
        if (!setMinus.isEmpty()) {
            StringBuilder sb = new StringBuilder("The following modules are not being tracked in ");
            sb.append(Reflection.getOrCreateKotlinClass(IrModuleDependencyTrackerImpl.class));
            ode.a(sb, ": ", CollectionsKt.joinToString$default(setMinus, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: p27
                public final Object invoke(Object obj) {
                    return IrModuleDependencyTrackerImpl.reverseTopoOrder$lambda$0$0((IrModuleFragment) obj);
                }
            }, 31, (Object) null));
            return null;
        }
        if (set.size() <= 1) {
            return moduleDependencies;
        }
        List list = DFS.topologicalOrder(set, new DFS.Neighbors() { // from class: q27
            public final Iterable getNeighbors(Object obj) {
                return IrModuleDependencyTrackerImpl.a(this.a, (IrModuleFragment) obj);
            }
        });
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (set.contains((IrModuleFragment) obj)) {
                arrayList.add(obj);
            }
        }
        List listReversed = CollectionsKt.reversed(arrayList);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list2 = listCreateListBuilder;
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list2, moduleDependencies.getStdlib());
        for (Object obj2 : listReversed) {
            IrModuleFragment irModuleFragment = (IrModuleFragment) obj2;
            if (!Intrinsics.areEqual(irModuleFragment, moduleDependencies.getStdlib()) && !Intrinsics.areEqual(irModuleFragment, moduleDependencies.getIncluded())) {
                list2.add(obj2);
            }
        }
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list2, moduleDependencies.getIncluded());
        return IrModuleDependencies.copy$default(moduleDependencies, CollectionsKt.build(listCreateListBuilder), (IrModuleFragment) null, (IrModuleFragment) null, (Map) null, 14, (Object) null);
    }

    @Override // org.jetbrains.kotlin.backend.common.serialization.IrModuleDependencyTracker
    public void trackDependency(IrModuleFragment fromModule, IrModuleFragment toModule) {
        fromModule.getClass();
        toModule.getClass();
        if (fromModule != toModule) {
            Set<IrModuleFragment> set = this.trackedModules.get(fromModule);
            if (set != null) {
                set.add(toModule);
                return;
            }
            StringBuilder sb = new StringBuilder("No module data for ");
            sb.append(fromModule.getName());
            ej7.a(sb, " in ", Reflection.getOrCreateKotlinClass(IrModuleDependencyTrackerImpl.class));
        }
    }

    public final Set<IrModuleFragment> getAllDependencies(IrModuleFragment module) {
        module.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        getAllDependencies(module, linkedHashSet);
        return linkedHashSet;
    }
}
