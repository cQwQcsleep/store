package org.jetbrains.kotlin.backend.common.serialization.metadata;

import java.io.File;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J7\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH&¢\u0006\u0002\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/metadata/FileVisitor;", "SourceFile", "", "visit", "", "index", "", "ioFile", "Ljava/io/File;", "sourceFile", "ktSourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "packageName", "Lorg/jetbrains/kotlin/name/FqName;", "(ILjava/io/File;Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceFile;Lorg/jetbrains/kotlin/name/FqName;)V", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FileVisitor<SourceFile> {
    void visit(int index, File ioFile, SourceFile sourceFile, KtSourceFile ktSourceFile, FqName packageName);
}
