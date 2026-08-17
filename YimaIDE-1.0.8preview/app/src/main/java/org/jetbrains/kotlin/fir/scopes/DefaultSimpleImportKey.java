package org.jetbrains.kotlin.fir.scopes;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.impl.DefaultImportPriority;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/DefaultSimpleImportKey;", Argument.Delimiters.none, "priority", "Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "excludedImportNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;Ljava/util/Set;)V", "getPriority", "()Lorg/jetbrains/kotlin/fir/scopes/impl/DefaultImportPriority;", "getExcludedImportNames", "()Ljava/util/Set;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class DefaultSimpleImportKey {
    private final Set<FqName> excludedImportNames;
    private final DefaultImportPriority priority;

    public DefaultSimpleImportKey(DefaultImportPriority defaultImportPriority, Set<FqName> set) {
        defaultImportPriority.getClass();
        set.getClass();
        this.priority = defaultImportPriority;
        this.excludedImportNames = set;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DefaultSimpleImportKey copy$default(DefaultSimpleImportKey defaultSimpleImportKey, DefaultImportPriority defaultImportPriority, Set set, int i, Object obj) {
        if ((i & 1) != 0) {
            defaultImportPriority = defaultSimpleImportKey.priority;
        }
        if ((i & 2) != 0) {
            set = defaultSimpleImportKey.excludedImportNames;
        }
        return defaultSimpleImportKey.copy(defaultImportPriority, set);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DefaultImportPriority getPriority() {
        return this.priority;
    }

    public final Set<FqName> component2() {
        return this.excludedImportNames;
    }

    public final DefaultSimpleImportKey copy(DefaultImportPriority priority, Set<FqName> excludedImportNames) {
        priority.getClass();
        excludedImportNames.getClass();
        return new DefaultSimpleImportKey(priority, excludedImportNames);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DefaultSimpleImportKey)) {
            return false;
        }
        DefaultSimpleImportKey defaultSimpleImportKey = (DefaultSimpleImportKey) other;
        return this.priority == defaultSimpleImportKey.priority && Intrinsics.areEqual(this.excludedImportNames, defaultSimpleImportKey.excludedImportNames);
    }

    public final Set<FqName> getExcludedImportNames() {
        return this.excludedImportNames;
    }

    public final DefaultImportPriority getPriority() {
        return this.priority;
    }

    public int hashCode() {
        return (this.priority.hashCode() * 31) + this.excludedImportNames.hashCode();
    }

    public String toString() {
        return "DefaultSimpleImportKey(priority=" + this.priority + ", excludedImportNames=" + this.excludedImportNames + ')';
    }
}
