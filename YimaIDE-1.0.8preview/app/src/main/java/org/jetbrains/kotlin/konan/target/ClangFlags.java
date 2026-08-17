package org.jetbrains.kotlin.konan.target;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.konan.properties.TargetableExternalStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/konan/target/ClangFlags;", "Lorg/jetbrains/kotlin/konan/properties/TargetableExternalStorage;", "Lorg/jetbrains/kotlin/konan/target/RelocationModeFlags;", "clangDebugFlags", "", "", "getClangDebugFlags", "()Ljava/util/List;", "clangFlags", "getClangFlags", "clangNooptFlags", "getClangNooptFlags", "clangOptFlags", "getClangOptFlags", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ClangFlags extends TargetableExternalStorage, RelocationModeFlags {
    default List<String> getClangDebugFlags() {
        return targetList("clangDebugFlags");
    }

    default List<String> getClangFlags() {
        return targetList("clangFlags");
    }

    default List<String> getClangNooptFlags() {
        return targetList("clangNooptFlags");
    }

    default List<String> getClangOptFlags() {
        return targetList("clangOptFlags");
    }
}
