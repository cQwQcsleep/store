package org.jetbrains.kotlin.backend.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.library.components.KlibNativeIncludedBinariesConstants;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/LoadedNativeKlibs;", "", "all", "", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "friends", KlibNativeIncludedBinariesConstants.KLIB_NATIVE_INCLUDED_BINARIES_FOLDER_NAME, "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getAll", "()Ljava/util/List;", "getFriends", "getIncluded", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadedNativeKlibs {
    private final List<KotlinLibrary> all;
    private final List<KotlinLibrary> friends;
    private final List<KotlinLibrary> included;

    public /* synthetic */ LoadedNativeKlibs(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3);
    }

    public final List<KotlinLibrary> getAll() {
        return this.all;
    }

    public final List<KotlinLibrary> getFriends() {
        return this.friends;
    }

    public final List<KotlinLibrary> getIncluded() {
        return this.included;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LoadedNativeKlibs(List<? extends KotlinLibrary> list, List<? extends KotlinLibrary> list2, List<? extends KotlinLibrary> list3) {
        list.getClass();
        list2.getClass();
        list3.getClass();
        this.all = list;
        this.friends = list2;
        this.included = list3;
    }
}
