package org.jetbrains.kotlin.serialization.deserialization;

import java.io.InputStream;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/KotlinMetadataFinder;", "", "findMetadata", "Ljava/io/InputStream;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "findMetadataTopLevelClassesInPackage", "", "", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasMetadataPackage", "", "fqName", "findBuiltInsData", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public interface KotlinMetadataFinder {
    InputStream findBuiltInsData(FqName packageFqName);

    InputStream findMetadata(ClassId classId);

    Set<String> findMetadataTopLevelClassesInPackage(FqName packageFqName);

    boolean hasMetadataPackage(FqName fqName);
}
