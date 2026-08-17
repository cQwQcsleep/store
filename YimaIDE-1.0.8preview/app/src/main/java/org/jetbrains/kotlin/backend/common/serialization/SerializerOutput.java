package org.jetbrains.kotlin.backend.common.serialization;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.SerializedIrModule;
import org.jetbrains.kotlin.library.SerializedMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/SerializerOutput;", "", "serializedMetadata", "Lorg/jetbrains/kotlin/library/SerializedMetadata;", "serializedIr", "Lorg/jetbrains/kotlin/library/SerializedIrModule;", "neededLibraries", "", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "<init>", "(Lorg/jetbrains/kotlin/library/SerializedMetadata;Lorg/jetbrains/kotlin/library/SerializedIrModule;Ljava/util/Collection;)V", "getSerializedMetadata", "()Lorg/jetbrains/kotlin/library/SerializedMetadata;", "getSerializedIr", "()Lorg/jetbrains/kotlin/library/SerializedIrModule;", "getNeededLibraries", "()Ljava/util/Collection;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SerializerOutput {
    private final Collection<KotlinLibrary> neededLibraries;
    private final SerializedIrModule serializedIr;
    private final SerializedMetadata serializedMetadata;

    /* JADX WARN: Multi-variable type inference failed */
    public SerializerOutput(SerializedMetadata serializedMetadata, SerializedIrModule serializedIrModule, Collection<? extends KotlinLibrary> collection) {
        collection.getClass();
        this.serializedMetadata = serializedMetadata;
        this.serializedIr = serializedIrModule;
        this.neededLibraries = collection;
    }

    public final Collection<KotlinLibrary> getNeededLibraries() {
        return this.neededLibraries;
    }

    public final SerializedIrModule getSerializedIr() {
        return this.serializedIr;
    }

    public final SerializedMetadata getSerializedMetadata() {
        return this.serializedMetadata;
    }
}
