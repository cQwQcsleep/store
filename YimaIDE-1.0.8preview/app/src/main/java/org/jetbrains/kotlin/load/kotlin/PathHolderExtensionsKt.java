package org.jetbrains.kotlin.load.kotlin;

import java.nio.file.Path;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"asNioPath", "Ljava/nio/file/Path;", "Lorg/jetbrains/kotlin/load/kotlin/PathHolder;", "org.jetbrains.kotlin:frontend.common.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PathHolderExtensionsKt {
    public static final Path asNioPath(PathHolder pathHolder) {
        pathHolder.getClass();
        Object path = pathHolder.getPath();
        if (path instanceof Path) {
            return (Path) path;
        }
        return null;
    }
}
