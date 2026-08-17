package org.jetbrains.kotlin.fir.resolve;

import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.FirJvmDefaultImportsProvider;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirBuiltinSymbolProvidersKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirFallbackBuiltinSymbolProvider;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;
import org.jetbrains.kotlin.resolve.ImportPath;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirJvmDefaultImportsProvider;", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "<init>", "()V", "platformSpecificDefaultImports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/ImportPath;", "getPlatformSpecificDefaultImports", "()Ljava/util/List;", "platformSpecificDefaultImports$delegate", "Lkotlin/Lazy;", "defaultLowPriorityImports", "getDefaultLowPriorityImports", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmDefaultImportsProvider extends DefaultImportsProvider {
    public static final FirJvmDefaultImportsProvider INSTANCE = new FirJvmDefaultImportsProvider();

    /* JADX INFO: renamed from: platformSpecificDefaultImports$delegate, reason: from kotlin metadata */
    private static final Lazy platformSpecificDefaultImports = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: t95
        public final Object invoke() {
            return FirJvmDefaultImportsProvider.a();
        }
    });
    private static final List<ImportPath> defaultLowPriorityImports = CollectionsKt.listOf(ImportPath.Companion.fromString("java.lang.*"));

    private FirJvmDefaultImportsProvider() {
    }

    public static List a() {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(ImportPath.Companion.fromString("kotlin.jvm.*"));
        for (FqName fqName : StandardClassIds.INSTANCE.getBuiltInsPackagesWithDefaultNamedImport()) {
            Iterator<T> it = FirBuiltinSymbolProvidersKt.getTopLevelClassifierNamesInPackage(FirFallbackBuiltinSymbolProvider.INSTANCE.getBuiltInsPackageFragments(), fqName).iterator();
            while (it.hasNext()) {
                listCreateListBuilder.add(new ImportPath(fqName.child((Name) it.next()), false, null, 4, null));
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public List<ImportPath> getDefaultLowPriorityImports() {
        return defaultLowPriorityImports;
    }

    public List<ImportPath> getPlatformSpecificDefaultImports() {
        return (List) platformSpecificDefaultImports.getValue();
    }
}
