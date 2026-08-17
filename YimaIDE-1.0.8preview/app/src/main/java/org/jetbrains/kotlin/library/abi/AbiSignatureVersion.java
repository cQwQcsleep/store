package org.jetbrains.kotlin.library.abi;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.library.abi.impl.AbiSignatureVersions;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\bg\u0018\u0000 \r2\u00020\u0001:\u0001\rR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fÊ\u0001\u0002\b\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion;", "", "versionNumber", "", "getVersionNumber", "()I", "isSupportedByAbiReader", "", "()Z", "description", "", "getDescription", "()Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public interface AbiSignatureVersion {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion$Companion;", "", "<init>", "()V", "allSupportedByAbiReader", "", "Lorg/jetbrains/kotlin/library/abi/AbiSignatureVersion;", "getAllSupportedByAbiReader", "()Ljava/util/List;", "resolveByVersionNumber", "versionNumber", "", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final List<AbiSignatureVersion> getAllSupportedByAbiReader() {
            return AbiSignatureVersions.Supported.getEntries();
        }

        public final AbiSignatureVersion resolveByVersionNumber(int versionNumber) {
            return AbiSignatureVersions.INSTANCE.resolveByVersionNumber(versionNumber);
        }
    }

    String getDescription();

    int getVersionNumber();

    boolean isSupportedByAbiReader();
}
