package org.jetbrains.kotlin.incremental.snapshots;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"md5", "", "Ljava/io/File;", "getMd5", "(Ljava/io/File;)[B", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class HashUtilKt {
    public static final byte[] getMd5(File file) throws NoSuchAlgorithmException {
        file.getClass();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bArr = new byte[4048];
        FileInputStream fileInputStream = new FileInputStream(file);
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                if (i < 0) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, (Throwable) null);
                    byte[] bArrDigest = messageDigest.digest();
                    bArrDigest.getClass();
                    return bArrDigest;
                }
                messageDigest.update(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(fileInputStream, th);
                    throw th2;
                }
            }
        }
    }
}
