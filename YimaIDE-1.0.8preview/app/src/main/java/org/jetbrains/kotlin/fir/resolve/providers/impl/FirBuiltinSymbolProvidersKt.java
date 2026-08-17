package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.ProtoBasedClassDataFinder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u0005¨\u0006\b"}, d2 = {"getTopLevelClassifierNamesInPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "builtInsPackageFragments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/AbstractFirBuiltinSymbolProvider$BuiltInsPackageFragment;", "packageFqName", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBuiltinSymbolProvidersKt {
    public static final Set<Name> getTopLevelClassifierNamesInPackage(Map<FqName, AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment> map, FqName fqName) {
        LinkedHashSet linkedHashSet;
        ProtoBasedClassDataFinder classDataFinder;
        Collection allClassIds;
        map.getClass();
        fqName.getClass();
        AbstractFirBuiltinSymbolProvider.BuiltInsPackageFragment builtInsPackageFragment = map.get(fqName);
        if (builtInsPackageFragment == null || (classDataFinder = builtInsPackageFragment.getClassDataFinder()) == null || (allClassIds = classDataFinder.getAllClassIds()) == null) {
            linkedHashSet = null;
        } else {
            linkedHashSet = new LinkedHashSet();
            Iterator it = allClassIds.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((ClassId) it.next()).getShortClassName());
            }
        }
        return linkedHashSet == null ? SetsKt.emptySet() : linkedHashSet;
    }
}
