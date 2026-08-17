package org.jetbrains.kotlin.ir.backend.js.ic;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.backend.common.serialization.FingerprintHash;
import org.jetbrains.kotlin.backend.common.serialization.Hash128Bits;
import org.jetbrains.kotlin.backend.common.serialization.IdSignatureDeserializer;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinLibraryHeader;", "", "libraryFile", "Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinLibraryFile;", "getLibraryFile-dAnkW_k", "()Ljava/lang/String;", "libraryFingerprint", "Lorg/jetbrains/kotlin/backend/common/serialization/FingerprintHash;", "getLibraryFingerprint-jverJr4", "()Lorg/jetbrains/kotlin/backend/common/serialization/Hash128Bits;", "sourceFileDeserializers", "", "Lorg/jetbrains/kotlin/ir/backend/js/ic/KotlinSourceFile;", "Lorg/jetbrains/kotlin/backend/common/serialization/IdSignatureDeserializer;", "getSourceFileDeserializers", "()Ljava/util/Map;", "sourceFileFingerprints", "getSourceFileFingerprints", KlibKt.KLIB_PROPERTY_JS_OUTPUT_NAME, "", "getJsOutputName", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinLibraryHeader {
    String getJsOutputName();

    /* JADX INFO: renamed from: getLibraryFile-dAnkW_k, reason: not valid java name */
    String mo206getLibraryFiledAnkW_k();

    /* JADX INFO: renamed from: getLibraryFingerprint-jverJr4, reason: not valid java name */
    Hash128Bits mo207getLibraryFingerprintjverJr4();

    Map<KotlinSourceFile, IdSignatureDeserializer> getSourceFileDeserializers();

    Map<KotlinSourceFile, FingerprintHash> getSourceFileFingerprints();
}
