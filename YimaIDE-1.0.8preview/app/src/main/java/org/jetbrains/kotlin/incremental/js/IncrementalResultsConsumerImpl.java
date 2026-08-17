package org.jetbrains.kotlin.incremental.js;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J(\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0005H\u0016Jd\u0010 \u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010\u00052\b\u0010*\u001a\u0004\u0018\u00010\u0005H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001f\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001f\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\n¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0019\u0010\u000eR\u001f\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0\n¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u001f\u0010\u000e¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/incremental/js/IncrementalResultsConsumerImpl;", "Lorg/jetbrains/kotlin/incremental/js/IncrementalResultsConsumer;", "<init>", "()V", "value", "", "headerMetadata", "getHeaderMetadata", "()[B", "packageParts", "", "Ljava/io/File;", "Lorg/jetbrains/kotlin/incremental/js/TranslationResultValue;", "getPackageParts", "()Ljava/util/Map;", "Ljava/util/HashMap;", "processHeader", "", "processPackagePart", "sourceFile", "packagePartMetadata", "binaryAst", "inlineData", "packageMetadata", "", "getPackageMetadata", "processPackageMetadata", "packageName", "metadata", "irFileData", "Lorg/jetbrains/kotlin/incremental/js/IrTranslationResultValue;", "getIrFileData", "processIrFile", "fileData", "types", "signatures", "strings", "declarations", "bodies", "fqn", "fileMetadata", "debugInfo", "fileEntries", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class IncrementalResultsConsumerImpl implements IncrementalResultsConsumer {
    private byte[] headerMetadata;
    private final HashMap<File, TranslationResultValue> packageParts = new HashMap<>();
    private final HashMap<String, byte[]> packageMetadata = new HashMap<>();
    private final HashMap<File, IrTranslationResultValue> irFileData = new HashMap<>();

    public final byte[] getHeaderMetadata() {
        byte[] bArr = this.headerMetadata;
        if (bArr != null) {
            return bArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("headerMetadata");
        return null;
    }

    public final Map<File, IrTranslationResultValue> getIrFileData() {
        return this.irFileData;
    }

    public final Map<String, byte[]> getPackageMetadata() {
        return this.packageMetadata;
    }

    public final Map<File, TranslationResultValue> getPackageParts() {
        return this.packageParts;
    }

    @Override // org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer
    public void processHeader(byte[] headerMetadata) {
        headerMetadata.getClass();
        this.headerMetadata = headerMetadata;
    }

    @Override // org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer
    public void processIrFile(File sourceFile, byte[] fileData, byte[] types, byte[] signatures, byte[] strings, byte[] declarations, byte[] bodies, byte[] fqn, byte[] fileMetadata, byte[] debugInfo, byte[] fileEntries) {
        sourceFile.getClass();
        fileData.getClass();
        types.getClass();
        signatures.getClass();
        strings.getClass();
        declarations.getClass();
        bodies.getClass();
        fqn.getClass();
        fileMetadata.getClass();
        this.irFileData.put(sourceFile, new IrTranslationResultValue(fileData, types, signatures, strings, declarations, bodies, fqn, fileMetadata, debugInfo, fileEntries));
    }

    @Override // org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer
    public void processPackageMetadata(String packageName, byte[] metadata) {
        packageName.getClass();
        metadata.getClass();
        this.packageMetadata.put(packageName, metadata);
    }

    @Override // org.jetbrains.kotlin.incremental.js.IncrementalResultsConsumer
    public void processPackagePart(File sourceFile, byte[] packagePartMetadata, byte[] binaryAst, byte[] inlineData) {
        sourceFile.getClass();
        packagePartMetadata.getClass();
        binaryAst.getClass();
        inlineData.getClass();
        this.packageParts.put(sourceFile, new TranslationResultValue(packagePartMetadata, binaryAst, inlineData));
    }
}
