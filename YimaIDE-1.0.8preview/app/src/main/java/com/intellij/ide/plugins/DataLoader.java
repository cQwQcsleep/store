package com.intellij.ide.plugins;

import com.intellij.psi.impl.source.tree.ChildRole;
import java.io.InputStream;
import java.nio.file.Path;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\fH&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/intellij/ide/plugins/DataLoader;", "", "emptyDescriptorIfCannotResolve", "", "getEmptyDescriptorIfCannotResolve", "()Z", "isExcludedFromSubSearch", "jarFile", "Ljava/nio/file/Path;", "load", "Ljava/io/InputStream;", "path", "", "pluginDescriptorSourceOnly", "toString", "intellij.platform.core.impl"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public interface DataLoader {
    default boolean isExcludedFromSubSearch(Path jarFile) {
        jarFile.getClass();
        return false;
    }

    InputStream load(String path, boolean pluginDescriptorSourceOnly);

    String toString();
}
