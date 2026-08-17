package org.jetbrains.kotlin.metadata.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/metadata/serialization/StringTable;", Argument.Delimiters.none, "getStringIndex", Argument.Delimiters.none, "string", Argument.Delimiters.none, "getQualifiedClassNameIndex", "className", "isLocal", Argument.Delimiters.none, "getPackageFqNameIndexByString", "fqName", "org.jetbrains.kotlin:metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface StringTable {
    int getPackageFqNameIndexByString(String fqName);

    int getQualifiedClassNameIndex(String className, boolean isLocal);

    int getStringIndex(String string);
}
