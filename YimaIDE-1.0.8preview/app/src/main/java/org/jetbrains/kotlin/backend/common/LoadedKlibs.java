package org.jetbrains.kotlin.backend.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.konan.library.components.KlibNativeIncludedBinariesConstants;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/LoadedKlibs;", "", "all", "", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "friends", KlibNativeIncludedBinariesConstants.KLIB_NATIVE_INCLUDED_BINARIES_FOLDER_NAME, "<init>", "(Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/library/KotlinLibrary;)V", "getAll", "()Ljava/util/List;", "getFriends", "getIncluded", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadedKlibs {
    private final List<KotlinLibrary> all;
    private final List<KotlinLibrary> friends;
    private final KotlinLibrary included;

    public /* synthetic */ LoadedKlibs(List list, List list2, KotlinLibrary kotlinLibrary, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? null : kotlinLibrary);
    }

    public final List<KotlinLibrary> getAll() {
        return this.all;
    }

    public final List<KotlinLibrary> getFriends() {
        return this.friends;
    }

    public final KotlinLibrary getIncluded() {
        return this.included;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LoadedKlibs(List<? extends KotlinLibrary> list, List<? extends KotlinLibrary> list2, KotlinLibrary kotlinLibrary) {
        list.getClass();
        list2.getClass();
        this.all = list;
        this.friends = list2;
        this.included = kotlinLibrary;
    }
}
