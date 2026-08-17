package org.jetbrains.kotlin.cli.common;

import com.intellij.openapi.Disposable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/CliModuleVisibilityManagerImpl;", "Lorg/jetbrains/kotlin/load/kotlin/ModuleVisibilityManager;", "Lcom/intellij/openapi/Disposable;", "enabled", Argument.Delimiters.none, "<init>", "(Z)V", "getEnabled", "()Z", "chunk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", "getChunk", "()Ljava/util/List;", "friendPaths", Argument.Delimiters.none, "getFriendPaths", "addModule", Argument.Delimiters.none, ModuleXmlParser.MODULE, "addFriendPath", ModuleXmlParser.PATH, "dispose", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliModuleVisibilityManagerImpl implements Disposable, ModuleVisibilityManager {
    private final boolean enabled;
    private final List<Module> chunk = new ArrayList();
    private final List<String> friendPaths = new ArrayList();

    public CliModuleVisibilityManagerImpl(boolean z) {
        this.enabled = z;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager
    public void addFriendPath(String path) {
        path.getClass();
        List<String> friendPaths = getFriendPaths();
        String absolutePath = new File(path).getAbsolutePath();
        absolutePath.getClass();
        friendPaths.add(absolutePath);
    }

    @Override // org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager
    public void addModule(Module module) {
        module.getClass();
        getChunk().add(module);
    }

    public void dispose() {
        getChunk().clear();
    }

    @Override // org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager
    public List<Module> getChunk() {
        return this.chunk;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.ModuleVisibilityManager
    public List<String> getFriendPaths() {
        return this.friendPaths;
    }
}
