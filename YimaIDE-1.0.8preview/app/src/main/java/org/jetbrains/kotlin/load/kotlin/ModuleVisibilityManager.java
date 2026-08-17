package org.jetbrains.kotlin.load.kotlin;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0013J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H&J\u0010\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\bH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/ModuleVisibilityManager;", Argument.Delimiters.none, "chunk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", "getChunk", "()Ljava/util/Collection;", "friendPaths", Argument.Delimiters.none, "getFriendPaths", "addModule", Argument.Delimiters.none, ModuleXmlParser.MODULE, "addFriendPath", ModuleXmlParser.PATH, "enabled", Argument.Delimiters.none, "getEnabled", "()Z", "SERVICE", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface ModuleVisibilityManager {
    void addFriendPath(String path);

    void addModule(Module module);

    Collection<Module> getChunk();

    default boolean getEnabled() {
        return true;
    }

    Collection<String> getFriendPaths();
}
