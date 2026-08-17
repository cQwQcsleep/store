package org.jetbrains.kotlin.backend.konan.serialization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.NativeRuntimeNames$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"ANNOTATIONS_TO_TREAT_AS_EXPORTED", "", "Lorg/jetbrains/kotlin/name/ClassId;", "getANNOTATIONS_TO_TREAT_AS_EXPORTED", "()Ljava/util/List;", "ANNOTATIONS_TO_TREAT_AS_EXPORTED_FQNS", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:ir.serialization.native"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KonanManglerKt {
    private static final List<ClassId> ANNOTATIONS_TO_TREAT_AS_EXPORTED;
    private static final List<FqName> ANNOTATIONS_TO_TREAT_AS_EXPORTED_FQNS;

    static {
        NativeRuntimeNames$Annotations nativeRuntimeNames$Annotations = NativeRuntimeNames$Annotations.INSTANCE;
        List<ClassId> listListOf = CollectionsKt.listOf(new ClassId[]{nativeRuntimeNames$Annotations.getSymbolNameClassId(), nativeRuntimeNames$Annotations.getGcUnsafeCallClassId(), nativeRuntimeNames$Annotations.getExportForCppRuntimeClassId(), nativeRuntimeNames$Annotations.getCNameClassId(), nativeRuntimeNames$Annotations.getExportForCompilerClassId()});
        ANNOTATIONS_TO_TREAT_AS_EXPORTED = listListOf;
        List<ClassId> list = listListOf;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((ClassId) it.next()).asSingleFqName());
        }
        ANNOTATIONS_TO_TREAT_AS_EXPORTED_FQNS = arrayList;
    }

    public static final List<ClassId> getANNOTATIONS_TO_TREAT_AS_EXPORTED() {
        return ANNOTATIONS_TO_TREAT_AS_EXPORTED;
    }
}
