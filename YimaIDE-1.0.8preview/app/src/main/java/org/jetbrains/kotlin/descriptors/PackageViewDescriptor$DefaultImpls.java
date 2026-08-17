package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PackageViewDescriptor$DefaultImpls {
    public static boolean isEmpty(PackageViewDescriptor packageViewDescriptor) {
        return packageViewDescriptor.getFragments().isEmpty();
    }
}
