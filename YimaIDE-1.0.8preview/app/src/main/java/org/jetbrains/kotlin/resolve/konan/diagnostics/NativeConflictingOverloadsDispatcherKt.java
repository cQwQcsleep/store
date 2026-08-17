package org.jetbrains.kotlin.resolve.konan.diagnostics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¨\u0006\u0004"}, d2 = {"hasDifferentParameterNames", "", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "other", "org.jetbrains.kotlin:frontend.native"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class NativeConflictingOverloadsDispatcherKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasDifferentParameterNames(FunctionDescriptor functionDescriptor, FunctionDescriptor functionDescriptor2) {
        List valueParameters = functionDescriptor.getValueParameters();
        valueParameters.getClass();
        List listDrop = CollectionsKt.drop(valueParameters, 1);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
        Iterator it = listDrop.iterator();
        while (it.hasNext()) {
            arrayList.add(((ValueParameterDescriptor) it.next()).getName());
        }
        List valueParameters2 = functionDescriptor2.getValueParameters();
        valueParameters2.getClass();
        List listDrop2 = CollectionsKt.drop(valueParameters2, 1);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop2, 10));
        Iterator it2 = listDrop2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((ValueParameterDescriptor) it2.next()).getName());
        }
        return !Intrinsics.areEqual(arrayList, arrayList2);
    }
}
