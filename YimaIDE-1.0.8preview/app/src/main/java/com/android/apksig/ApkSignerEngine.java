package com.android.apksig;

import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.RunnablesExecutor;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface ApkSignerEngine extends Closeable {

    public interface InspectJarEntryRequest {
        void done();

        DataSink getDataSink();

        String getEntryName();
    }

    @Deprecated
    public interface OutputApkSigningBlockRequest {
        void done();

        byte[] getApkSigningBlock();
    }

    public interface OutputApkSigningBlockRequest2 {
        void done();

        byte[] getApkSigningBlock();

        int getPaddingSizeBeforeApkSigningBlock();
    }

    public interface OutputJarSignatureRequest {

        public static class JarEntry {
            private final byte[] mData;
            private final String mName;

            public JarEntry(String str, byte[] bArr) {
                this.mName = str;
                this.mData = (byte[]) bArr.clone();
            }

            public byte[] getData() {
                return (byte[]) this.mData.clone();
            }

            public String getName() {
                return this.mName;
            }
        }

        void done();

        List<JarEntry> getAdditionalJarEntries();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    default byte[] generateSourceStampCertificateDigest() throws SignatureException {
        return new byte[0];
    }

    default Set<String> initWith(byte[] bArr, Set<String> set) {
        throw new UnsupportedOperationException("initWith method is not implemented");
    }

    void inputApkSigningBlock(DataSource dataSource) throws IllegalStateException, IOException, ApkFormatException;

    InputJarEntryInstructions inputJarEntry(String str) throws IllegalStateException;

    InputJarEntryInstructions.OutputPolicy inputJarEntryRemoved(String str) throws IllegalStateException;

    default boolean isEligibleForSourceStamp() {
        return false;
    }

    void outputDone() throws IllegalStateException;

    OutputJarSignatureRequest outputJarEntries() throws IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, ApkFormatException;

    InspectJarEntryRequest outputJarEntry(String str) throws IllegalStateException;

    void outputJarEntryRemoved(String str) throws IllegalStateException;

    @Deprecated
    OutputApkSigningBlockRequest outputZipSections(DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws IllegalStateException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, ApkFormatException;

    OutputApkSigningBlockRequest2 outputZipSections2(DataSource dataSource, DataSource dataSource2, DataSource dataSource3) throws IllegalStateException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, ApkFormatException;

    default void setExecutor(RunnablesExecutor runnablesExecutor) {
        throw new UnsupportedOperationException("setExecutor method is not implemented");
    }

    void signV4(DataSource dataSource, File file, boolean z) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException;

    public static class InputJarEntryInstructions {
        private final InspectJarEntryRequest mInspectJarEntryRequest;
        private final OutputPolicy mOutputPolicy;

        public enum OutputPolicy {
            SKIP,
            OUTPUT,
            OUTPUT_BY_ENGINE
        }

        public InputJarEntryInstructions(OutputPolicy outputPolicy, InspectJarEntryRequest inspectJarEntryRequest) {
            this.mOutputPolicy = outputPolicy;
            this.mInspectJarEntryRequest = inspectJarEntryRequest;
        }

        public InspectJarEntryRequest getInspectJarEntryRequest() {
            return this.mInspectJarEntryRequest;
        }

        public OutputPolicy getOutputPolicy() {
            return this.mOutputPolicy;
        }

        public InputJarEntryInstructions(OutputPolicy outputPolicy) {
            this(outputPolicy, null);
        }
    }
}
