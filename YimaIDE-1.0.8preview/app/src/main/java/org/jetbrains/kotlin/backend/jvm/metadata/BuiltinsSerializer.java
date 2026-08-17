package org.jetbrains.kotlin.backend.jvm.metadata;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/metadata/BuiltinsSerializer;", Argument.Delimiters.none, "serialize", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "filesMetadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "serializeEmptyPackage", "fqName", "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface BuiltinsSerializer {
    List<Pair<FqName, byte[]>> serialize(List<? extends MetadataSource.File> filesMetadata);

    byte[] serializeEmptyPackage(FqName fqName);
}
