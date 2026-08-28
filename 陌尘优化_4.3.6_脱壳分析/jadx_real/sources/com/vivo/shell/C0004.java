package com.vivo.shell;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.vivo.shell.拷贝, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0004 {
    /* renamed from: 复制, reason: contains not printable characters */
    public static void m268(Context context, String str, String str2, String str3) throws IOException {
        File externalFilesDir = context.getExternalFilesDir(null);
        File file = new File(externalFilesDir, str);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            InputStream inputStreamOpen = context.getAssets().open(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(externalFilesDir, str3));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i == -1) {
                    inputStreamOpen.close();
                    fileOutputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (Exception unused) {
        }
    }
}
