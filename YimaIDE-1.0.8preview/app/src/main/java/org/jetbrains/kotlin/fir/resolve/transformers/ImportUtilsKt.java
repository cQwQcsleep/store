package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedParentInImport;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"findLongestExistingPackage", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageAndClass;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "resolveToPackageOrClass", "Lorg/jetbrains/kotlin/fir/resolve/transformers/PackageResolutionResult;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ImportUtilsKt {
    public static final PackageAndClass findLongestExistingPackage(FirSymbolProvider firSymbolProvider, FqName fqName) {
        firSymbolProvider.getClass();
        fqName.getClass();
        List listPathSegments = fqName.pathSegments();
        int size = listPathSegments.size();
        FqName fqNameParent = fqName;
        while (!fqNameParent.isRoot() && size > 0 && !firSymbolProvider.hasPackage(fqNameParent)) {
            fqNameParent = fqNameParent.parent();
            size--;
        }
        if (Intrinsics.areEqual(fqNameParent, fqName)) {
            return new PackageAndClass(fqNameParent, null);
        }
        FqName.Companion companion = FqName.Companion;
        IntRange intRangeUntil = RangesKt.until(size, listPathSegments.size());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        IntIterator it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(((Name) listPathSegments.get(it.nextInt())).asString());
        }
        return new PackageAndClass(fqNameParent, companion.fromSegments(arrayList));
    }

    public static final PackageResolutionResult resolveToPackageOrClass(FirSymbolProvider firSymbolProvider, FqName fqName) {
        firSymbolProvider.getClass();
        fqName.getClass();
        PackageAndClass packageAndClassFindLongestExistingPackage = findLongestExistingPackage(firSymbolProvider, fqName);
        FqName packageFqName = packageAndClassFindLongestExistingPackage.getPackageFqName();
        FqName relativeClassFqName = packageAndClassFindLongestExistingPackage.getRelativeClassFqName();
        return relativeClassFqName == null ? new PackageResolutionResult.PackageOrClass(packageFqName, null, null) : resolveToPackageOrClass(firSymbolProvider, new ClassId(packageFqName, relativeClassFqName, false));
    }

    public static final PackageResolutionResult resolveToPackageOrClass(FirSymbolProvider firSymbolProvider, ClassId classId) {
        firSymbolProvider.getClass();
        classId.getClass();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = firSymbolProvider.getClassLikeSymbolByClassId(classId);
        if (classLikeSymbolByClassId == null) {
            return new PackageResolutionResult.Error(new ConeUnresolvedParentInImport(classId));
        }
        return new PackageResolutionResult.PackageOrClass(classId.getPackageFqName(), classId.getRelativeClassName(), classLikeSymbolByClassId);
    }
}
