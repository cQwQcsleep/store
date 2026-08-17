package org.jetbrains.kotlin.cli.common;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001b\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007HÆ\u0003JE\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\bHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/GroupedKtSources;", Argument.Delimiters.none, "platformSources", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", ModuleXmlParser.COMMON_SOURCES, "sourcesByModuleName", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Map;)V", "getPlatformSources", "()Ljava/util/Collection;", "getCommonSources", "getSourcesByModuleName", "()Ljava/util/Map;", "isEmpty", Argument.Delimiters.none, "component1", "component2", "component3", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class GroupedKtSources {
    private final Collection<KtSourceFile> commonSources;
    private final Collection<KtSourceFile> platformSources;
    private final Map<String, Set<KtSourceFile>> sourcesByModuleName;

    /* JADX WARN: Multi-variable type inference failed */
    public GroupedKtSources(Collection<? extends KtSourceFile> collection, Collection<? extends KtSourceFile> collection2, Map<String, ? extends Set<? extends KtSourceFile>> map) {
        collection.getClass();
        collection2.getClass();
        map.getClass();
        this.platformSources = collection;
        this.commonSources = collection2;
        this.sourcesByModuleName = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GroupedKtSources copy$default(GroupedKtSources groupedKtSources, Collection collection, Collection collection2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = groupedKtSources.platformSources;
        }
        if ((i & 2) != 0) {
            collection2 = groupedKtSources.commonSources;
        }
        if ((i & 4) != 0) {
            map = groupedKtSources.sourcesByModuleName;
        }
        return groupedKtSources.copy(collection, collection2, map);
    }

    public final Collection<KtSourceFile> component1() {
        return this.platformSources;
    }

    public final Collection<KtSourceFile> component2() {
        return this.commonSources;
    }

    public final Map<String, Set<KtSourceFile>> component3() {
        return this.sourcesByModuleName;
    }

    public final GroupedKtSources copy(Collection<? extends KtSourceFile> platformSources, Collection<? extends KtSourceFile> commonSources, Map<String, ? extends Set<? extends KtSourceFile>> sourcesByModuleName) {
        platformSources.getClass();
        commonSources.getClass();
        sourcesByModuleName.getClass();
        return new GroupedKtSources(platformSources, commonSources, sourcesByModuleName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GroupedKtSources)) {
            return false;
        }
        GroupedKtSources groupedKtSources = (GroupedKtSources) other;
        return Intrinsics.areEqual(this.platformSources, groupedKtSources.platformSources) && Intrinsics.areEqual(this.commonSources, groupedKtSources.commonSources) && Intrinsics.areEqual(this.sourcesByModuleName, groupedKtSources.sourcesByModuleName);
    }

    public final Collection<KtSourceFile> getCommonSources() {
        return this.commonSources;
    }

    public final Collection<KtSourceFile> getPlatformSources() {
        return this.platformSources;
    }

    public final Map<String, Set<KtSourceFile>> getSourcesByModuleName() {
        return this.sourcesByModuleName;
    }

    public int hashCode() {
        return (((this.platformSources.hashCode() * 31) + this.commonSources.hashCode()) * 31) + this.sourcesByModuleName.hashCode();
    }

    public final boolean isEmpty() {
        return this.platformSources.isEmpty() && this.commonSources.isEmpty();
    }

    public String toString() {
        return "GroupedKtSources(platformSources=" + this.platformSources + ", commonSources=" + this.commonSources + ", sourcesByModuleName=" + this.sourcesByModuleName + ')';
    }
}
