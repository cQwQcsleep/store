package org.jetbrains.kotlin.descriptors;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/PackageFragmentProviderOptimized;", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "collectPackageFragments", Argument.Delimiters.none, "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "packageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "isEmpty", Argument.Delimiters.none, "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface PackageFragmentProviderOptimized extends PackageFragmentProvider {
    void collectPackageFragments(FqName fqName, Collection<PackageFragmentDescriptor> packageFragments);

    boolean isEmpty(FqName fqName);
}
