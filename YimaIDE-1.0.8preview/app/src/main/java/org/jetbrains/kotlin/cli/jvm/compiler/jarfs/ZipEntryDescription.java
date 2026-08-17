package org.jetbrains.kotlin.cli.jvm.compiler.jarfs;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001bB7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u001a¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription;", Argument.Delimiters.none, "relativePath", Argument.Delimiters.none, "compressedSize", Argument.Delimiters.none, "uncompressedSize", "offsetInFile", "compressionKind", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription$CompressionKind;", "fileNameSize", Argument.Delimiters.none, "<init>", "(Ljava/lang/CharSequence;JJJLorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription$CompressionKind;I)V", "getRelativePath", "()Ljava/lang/CharSequence;", "getCompressedSize", "()J", "getUncompressedSize", "getOffsetInFile", "getCompressionKind", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription$CompressionKind;", "getFileNameSize", "()I", "isDirectory", Argument.Delimiters.none, "()Z", "CompressionKind", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ZipEntryDescription {
    private final long compressedSize;
    private final CompressionKind compressionKind;
    private final int fileNameSize;
    private final long offsetInFile;
    private final CharSequence relativePath;
    private final long uncompressedSize;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/ZipEntryDescription$CompressionKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "PLAIN", "DEFLATE", "kotlin-compiler"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum CompressionKind {
        PLAIN,
        DEFLATE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<CompressionKind> getEntries() {
            return $ENTRIES;
        }
    }

    public ZipEntryDescription(CharSequence charSequence, long j, long j2, long j3, CompressionKind compressionKind, int i) {
        charSequence.getClass();
        compressionKind.getClass();
        this.relativePath = charSequence;
        this.compressedSize = j;
        this.uncompressedSize = j2;
        this.offsetInFile = j3;
        this.compressionKind = compressionKind;
        this.fileNameSize = i;
    }

    public final long getCompressedSize() {
        return this.compressedSize;
    }

    public final CompressionKind getCompressionKind() {
        return this.compressionKind;
    }

    public final int getFileNameSize() {
        return this.fileNameSize;
    }

    public final long getOffsetInFile() {
        return this.offsetInFile;
    }

    public final CharSequence getRelativePath() {
        return this.relativePath;
    }

    public final long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public final boolean isDirectory() {
        return this.uncompressedSize == 0;
    }
}
