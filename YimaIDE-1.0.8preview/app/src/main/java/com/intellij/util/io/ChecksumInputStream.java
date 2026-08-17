package com.intellij.util.io;

import java.io.InputStream;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/intellij/util/io/ChecksumInputStream;", "Ljava/util/zip/CheckedInputStream;", "delegate", "Ljava/io/InputStream;", "checksumGen", "Lkotlin/Function0;", "Ljava/util/zip/Checksum;", "<init>", "(Ljava/io/InputStream;Lkotlin/jvm/functions/Function0;)V", "checksum", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ChecksumInputStream extends CheckedInputStream {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChecksumInputStream(InputStream inputStream, Function0<? extends Checksum> function0) {
        super(inputStream, (Checksum) function0.invoke());
        inputStream.getClass();
        function0.getClass();
    }

    public final long checksum() {
        return getChecksum().getValue();
    }
}
