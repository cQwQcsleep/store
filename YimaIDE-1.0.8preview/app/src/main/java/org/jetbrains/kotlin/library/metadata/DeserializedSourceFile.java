package org.jetbrains.kotlin.library.metadata;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0082\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/DeserializedSourceFile;", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "name_", "", "library", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/library/KotlinLibrary;)V", "getName_", "()Ljava/lang/String;", "getLibrary", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "getName", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DeserializedSourceFile implements SourceFile {
    private final KotlinLibrary library;
    private final String name_;

    public DeserializedSourceFile(String str, KotlinLibrary kotlinLibrary) {
        str.getClass();
        kotlinLibrary.getClass();
        this.name_ = str;
        this.library = kotlinLibrary;
    }

    public boolean equals(Object other) {
        if (!(other instanceof DeserializedSourceFile)) {
            return false;
        }
        DeserializedSourceFile deserializedSourceFile = (DeserializedSourceFile) other;
        return Intrinsics.areEqual(this.library, deserializedSourceFile.library) && Intrinsics.areEqual(getName_(), deserializedSourceFile.getName_());
    }

    public final KotlinLibrary getLibrary() {
        return this.library;
    }

    /* JADX INFO: renamed from: getName, reason: from getter */
    public String getName_() {
        return this.name_;
    }

    public final String getName_() {
        return this.name_;
    }

    public int hashCode() {
        int iHashCode = this.library.hashCode();
        String name_ = getName_();
        return (name_ != null ? name_.hashCode() : 0) ^ iHashCode;
    }

    public String toString() {
        return "DeserializedSourceFile(\"" + getName_() + "\" from \"" + this.library.getLibraryFile() + "\")";
    }
}
