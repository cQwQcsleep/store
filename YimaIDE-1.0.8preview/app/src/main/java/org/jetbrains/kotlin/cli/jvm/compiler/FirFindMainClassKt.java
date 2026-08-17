package org.jetbrains.kotlin.cli.jvm.compiler;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.load.kotlin.PackagePartClassUtils;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"findMainClass", "Lorg/jetbrains/kotlin/name/FqName;", "fir", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFindMainClassKt {
    public static final FqName findMainClass(List<? extends FirFile> list) {
        list.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        FirMainClassFinder firMainClassFinder = new FirMainClassFinder(linkedHashMap);
        for (FirFile firFile : list) {
            firFile.accept(firMainClassFinder, TuplesKt.to(firFile, null));
        }
        Map.Entry entry = (Map.Entry) CollectionsKt.singleOrNull(linkedHashMap.entrySet());
        if (entry == null) {
            return null;
        }
        FirDeclaration firDeclaration = (FirDeclaration) entry.getKey();
        if (!(firDeclaration instanceof FirFile)) {
            if (!(firDeclaration instanceof FirRegularClass) || ((List) entry.getValue()).size() > 1) {
                return null;
            }
            return FirDeclarationUtilKt.getClassId((FirClassLikeDeclaration) firDeclaration).asSingleFqName();
        }
        if (((List) entry.getValue()).size() > 1) {
            Iterable<FirNamedFunction> iterable = (Iterable) entry.getValue();
            int i = 0;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (FirNamedFunction firNamedFunction : iterable) {
                    if (!firNamedFunction.getValueParameters().isEmpty() || firNamedFunction.getReceiverParameter() != null) {
                        i++;
                        if (i < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
            }
            if (i > 1) {
                return null;
            }
        }
        FirFile firFile2 = (FirFile) firDeclaration;
        return PackagePartClassUtils.getPackagePartFqName(UtilsKt.getPackageFqName(firFile2), firFile2.getName());
    }
}
