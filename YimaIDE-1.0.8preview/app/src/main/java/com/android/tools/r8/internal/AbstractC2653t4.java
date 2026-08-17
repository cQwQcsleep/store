package com.android.tools.r8.internal;

import com.android.tools.r8.profile.art.ArtProfileProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2653t4 {
    public static String a(ArtProfileProvider artProfileProvider) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8);
        try {
            artProfileProvider.getArtProfile(new C2568s4(outputStreamWriter));
            outputStreamWriter.close();
            return byteArrayOutputStream.toString();
        } catch (Throwable th) {
            try {
                outputStreamWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static C2312p4 a(Path path) {
        return new C2312p4(path);
    }

    public static void a(OutputStreamWriter outputStreamWriter, String str) {
        try {
            outputStreamWriter.write(str);
            outputStreamWriter.write(10);
        } catch (IOException e) {
            u8i.a(e);
        }
    }
}
