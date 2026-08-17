package org.jetbrains.kotlin.config;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001Bc\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR#\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", Argument.Delimiters.none, ModuleXmlParser.MODULES, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/HmppCliModule;", "sourceDependencies", Argument.Delimiters.none, "moduleDependencies", Argument.Delimiters.none, "friendDependencies", "<init>", "(Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getModules", "()Ljava/util/List;", "getSourceDependencies", "()Ljava/util/Map;", "getModuleDependencies", "getFriendDependencies", "org.jetbrains.kotlin:config"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class HmppCliModuleStructure {
    private final Map<HmppCliModule, List<String>> friendDependencies;
    private final Map<HmppCliModule, List<String>> moduleDependencies;
    private final List<HmppCliModule> modules;
    private final Map<HmppCliModule, List<HmppCliModule>> sourceDependencies;

    /* JADX WARN: Multi-variable type inference failed */
    public HmppCliModuleStructure(List<HmppCliModule> list, Map<HmppCliModule, ? extends List<HmppCliModule>> map, Map<HmppCliModule, ? extends List<String>> map2, Map<HmppCliModule, ? extends List<String>> map3) {
        list.getClass();
        map.getClass();
        map2.getClass();
        map3.getClass();
        this.modules = list;
        this.sourceDependencies = map;
        this.moduleDependencies = map2;
        this.friendDependencies = map3;
    }

    public final Map<HmppCliModule, List<String>> getFriendDependencies() {
        return this.friendDependencies;
    }

    public final Map<HmppCliModule, List<String>> getModuleDependencies() {
        return this.moduleDependencies;
    }

    public final List<HmppCliModule> getModules() {
        return this.modules;
    }

    public final Map<HmppCliModule, List<HmppCliModule>> getSourceDependencies() {
        return this.sourceDependencies;
    }
}
