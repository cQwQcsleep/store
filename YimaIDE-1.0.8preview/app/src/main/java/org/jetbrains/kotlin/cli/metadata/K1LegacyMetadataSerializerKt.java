package org.jetbrains.kotlin.cli.metadata;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.PackagePartClassUtils;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u0000¨\u0006\b"}, d2 = {"getClassFilePath", Argument.Delimiters.none, "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "getPackageFilePath", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "fileName", "org.jetbrains.kotlin:cli-metadata"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K1LegacyMetadataSerializerKt {
    public static final String getClassFilePath(ClassId classId) {
        classId.getClass();
        return StringsKt.replace$default(classId.asSingleFqName().asString(), '.', '/', false, 4, (Object) null) + ".kotlin_metadata";
    }

    public static final String getPackageFilePath(FqName fqName, String str) {
        fqName.getClass();
        str.getClass();
        return StringsKt.replace$default(fqName.asString(), '.', '/', false, 4, (Object) null) + '/' + PackagePartClassUtils.getFilePartShortName(str) + ".kotlin_metadata";
    }
}
