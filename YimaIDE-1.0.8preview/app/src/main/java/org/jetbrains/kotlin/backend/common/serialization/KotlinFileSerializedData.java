package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.library.SerializedIrFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u00002\u00020\u0001B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\u000bB#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/KotlinFileSerializedData;", "", "metadata", "", "irData", "Lorg/jetbrains/kotlin/library/SerializedIrFile;", "path", "", "fqName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "([BLorg/jetbrains/kotlin/library/SerializedIrFile;Ljava/lang/String;Ljava/lang/String;)V", "([BLorg/jetbrains/kotlin/library/SerializedIrFile;)V", "([BLjava/lang/String;Ljava/lang/String;)V", "getMetadata", "()[B", "getIrData", "()Lorg/jetbrains/kotlin/library/SerializedIrFile;", "getPath", "()Ljava/lang/String;", "getFqName", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class KotlinFileSerializedData {
    private final String fqName;
    private final SerializedIrFile irData;
    private final byte[] metadata;
    private final String path;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KotlinFileSerializedData(byte[] bArr, SerializedIrFile serializedIrFile) {
        this(bArr, serializedIrFile, serializedIrFile.getPath(), serializedIrFile.getFqName());
        bArr.getClass();
        serializedIrFile.getClass();
    }

    public final String getFqName() {
        return this.fqName;
    }

    public final SerializedIrFile getIrData() {
        return this.irData;
    }

    public final byte[] getMetadata() {
        return this.metadata;
    }

    public final String getPath() {
        return this.path;
    }

    private KotlinFileSerializedData(byte[] bArr, SerializedIrFile serializedIrFile, String str, String str2) {
        this.metadata = bArr;
        this.irData = serializedIrFile;
        this.path = str;
        this.fqName = str2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public KotlinFileSerializedData(byte[] bArr, String str, String str2) {
        this(bArr, null, str, str2);
        bArr.getClass();
        str2.getClass();
    }
}
