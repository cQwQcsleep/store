package org.jetbrains.kotlin.backend.konan.serialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/SerializedFileReference;", "", "fqName", "", "path", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;)V", "getFqName", "()Ljava/lang/String;", "getPath", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SerializedFileReference {
    private final String fqName;
    private final String path;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SerializedFileReference(IrFile irFile) {
        this(irFile.getPackageFqName().asString(), IrDeclarationsKt.getPath(irFile));
        irFile.getClass();
    }

    public static /* synthetic */ SerializedFileReference copy$default(SerializedFileReference serializedFileReference, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializedFileReference.fqName;
        }
        if ((i & 2) != 0) {
            str2 = serializedFileReference.path;
        }
        return serializedFileReference.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFqName() {
        return this.fqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final SerializedFileReference copy(String fqName, String path) {
        fqName.getClass();
        path.getClass();
        return new SerializedFileReference(fqName, path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SerializedFileReference)) {
            return false;
        }
        SerializedFileReference serializedFileReference = (SerializedFileReference) other;
        return Intrinsics.areEqual(this.fqName, serializedFileReference.fqName) && Intrinsics.areEqual(this.path, serializedFileReference.path);
    }

    public final String getFqName() {
        return this.fqName;
    }

    public final String getPath() {
        return this.path;
    }

    public int hashCode() {
        return (this.fqName.hashCode() * 31) + this.path.hashCode();
    }

    public String toString() {
        return "SerializedFileReference(fqName=" + this.fqName + ", path=" + this.path + Util.C_PARAM_END;
    }

    public SerializedFileReference(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.fqName = str;
        this.path = str2;
    }
}
