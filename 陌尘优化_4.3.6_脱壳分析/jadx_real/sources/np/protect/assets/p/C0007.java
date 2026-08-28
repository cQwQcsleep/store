package np.protect.assets.p;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import obfuse.NPStringFog;

/* renamed from: np.protect.assets.p.۟, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0007 {
    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    private static void m339(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    /* renamed from: ۟, reason: not valid java name and contains not printable characters */
    public static byte[] m340(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                m341(byteArrayInputStream, byteArrayOutputStream);
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused2) {
                }
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(NPStringFog.decode("3B1E08191E040411170A50244E21410217000102"), e);
            }
        } finally {
        }
    }

    /* renamed from: ۟۟, reason: not valid java name and contains not printable characters */
    public static void m341(InputStream inputStream, OutputStream outputStream) throws IOException {
        m339(new C0008(inputStream), outputStream);
    }
}
