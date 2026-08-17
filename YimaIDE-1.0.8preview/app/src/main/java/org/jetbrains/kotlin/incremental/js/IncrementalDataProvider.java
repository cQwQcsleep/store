package org.jetbrains.kotlin.incremental.js;

import java.io.File;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001e\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000bR\u001e\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00140\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0016À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "", "headerMetadata", "", "getHeaderMetadata", "()[B", "compiledPackageParts", "", "Ljava/io/File;", "Lorg/jetbrains/kotlin/incremental/js/TranslationResultValue;", "getCompiledPackageParts", "()Ljava/util/Map;", "metadataVersion", "", "getMetadataVersion", "()[I", "packageMetadata", "", "getPackageMetadata", "serializedIrFiles", "Lorg/jetbrains/kotlin/incremental/js/IrTranslationResultValue;", "getSerializedIrFiles", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface IncrementalDataProvider {
    Map<File, TranslationResultValue> getCompiledPackageParts();

    byte[] getHeaderMetadata();

    int[] getMetadataVersion();

    Map<String, byte[]> getPackageMetadata();

    Map<File, IrTranslationResultValue> getSerializedIrFiles();
}
