package org.jetbrains.kotlin.library.loader;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.Klib;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/loader/KlibLibraryProvider;", "", "getLibraryPaths", "", "", "postProcessLoadedLibrary", "", "klib", "Lorg/jetbrains/kotlin/library/Klib;", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface KlibLibraryProvider {
    List<String> getLibraryPaths();

    void postProcessLoadedLibrary(Klib klib);
}
