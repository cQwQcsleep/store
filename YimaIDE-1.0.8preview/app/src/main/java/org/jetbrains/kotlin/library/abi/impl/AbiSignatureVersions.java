package org.jetbrains.kotlin.library.abi.impl;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.library.abi.AbiSignatureVersion;
import org.jetbrains.kotlin.library.abi.ExperimentalLibraryAbiReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0002\b\tB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007Ê\u0001\u0002\b\u000b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/AbiSignatureVersions;", "", "<init>", "()V", "resolveByVersionNumber", "Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion;", "versionNumber", "", "Supported", "Unsupported", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public final class AbiSignatureVersions {
    public static final AbiSignatureVersions INSTANCE = new AbiSignatureVersions();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011j\u0002\b\rj\u0002\b\u000e¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/AbiSignatureVersions$Supported;", "Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion;", "", "versionNumber", "", "description", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getVersionNumber", "()I", "getDescription", "()Ljava/lang/String;", "V1", "V2", "isSupportedByAbiReader", "", "()Z", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum Supported implements AbiSignatureVersion {
        V1(1, "The signatures with hashes"),
        V2(2, "The self-descriptive signatures (with mangled names)");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String description;
        private final int versionNumber;

        Supported(int i, String str) {
            this.versionNumber = i;
            this.description = str;
        }

        public static EnumEntries<Supported> getEntries() {
            return $ENTRIES;
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public String getDescription() {
            return this.description;
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public int getVersionNumber() {
            return this.versionNumber;
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public boolean isSupportedByAbiReader() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\fHÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\nR\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/AbiSignatureVersions$Unsupported;", "Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion;", "versionNumber", "", "<init>", "(I)V", "getVersionNumber", "()I", "isSupportedByAbiReader", "", "()Z", "description", "", "getDescription", "()Ljava/lang/String;", "component1", "copy", "equals", "other", "", "hashCode", "toString", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final /* data */ class Unsupported implements AbiSignatureVersion {
        private final int versionNumber;

        public Unsupported(int i) {
            this.versionNumber = i;
        }

        public static /* synthetic */ Unsupported copy$default(Unsupported unsupported, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = unsupported.versionNumber;
            }
            return unsupported.copy(i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getVersionNumber() {
            return this.versionNumber;
        }

        public final Unsupported copy(int versionNumber) {
            return new Unsupported(versionNumber);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Unsupported) && this.versionNumber == ((Unsupported) other).versionNumber;
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public String getDescription() {
            return null;
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public int getVersionNumber() {
            return this.versionNumber;
        }

        public int hashCode() {
            return Integer.hashCode(this.versionNumber);
        }

        @Override // org.jetbrains.kotlin.library.abi.AbiSignatureVersion
        public boolean isSupportedByAbiReader() {
            return false;
        }

        public String toString() {
            return "Unsupported(versionNumber=" + this.versionNumber + ')';
        }
    }

    private AbiSignatureVersions() {
    }

    public final AbiSignatureVersion resolveByVersionNumber(int versionNumber) {
        Object next;
        Iterator it = Supported.getEntries().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((Supported) next).getVersionNumber() != versionNumber);
        Supported supported = (Supported) next;
        return supported != null ? supported : new Unsupported(versionNumber);
    }
}
