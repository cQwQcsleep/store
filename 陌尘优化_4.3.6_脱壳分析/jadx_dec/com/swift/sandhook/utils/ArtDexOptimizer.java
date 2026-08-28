package com.swift.sandhook.utils;

import android.os.Build;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;
import com.swift.sandhook.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class ArtDexOptimizer {

    static class StreamConsumer {
        static final Executor STREAM_CONSUMER = Executors.newSingleThreadExecutor();

        private StreamConsumer() {
        }

        static void consumeInputStream(InputStream inputStream) {
            STREAM_CONSUMER.execute(new Runnable(inputStream) { // from class: com.swift.sandhook.utils.ArtDexOptimizer.StreamConsumer.1
                final InputStream val$is;

                {
                    this.val$is = inputStream;
                }

                @Override // java.lang.Runnable
                public void run() throws IOException {
                    if (this.val$is == null) {
                        return;
                    }
                    do {
                        try {
                        } catch (IOException unused) {
                        } catch (Throwable th) {
                            try {
                                this.val$is.close();
                            } catch (Exception unused2) {
                            }
                            throw th;
                        }
                    } while (this.val$is.read(new byte[FileUtils.FileMode.MODE_IRUSR]) > 0);
                    try {
                        this.val$is.close();
                    } catch (Exception unused3) {
                    }
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ee A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ef A[Catch: InterruptedException -> 0x0102, TryCatch #0 {InterruptedException -> 0x0102, blocks: (B:25:0x00e8, B:28:0x00ef, B:29:0x0101), top: B:34:0x00e8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void dexoatAndDisableInline(String str, String str2) throws InterruptedException, IOException {
        String strDecode;
        int iWaitFor;
        String strDecode2 = "dex2oat works unsuccessfully, exit code: ";
        File file = new File(str2);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        if (SandHookConfig.SDK_INT >= 24) {
            String strDecode3 = "--runtime-arg";
            arrayList.add(strDecode3);
            arrayList.add("-classpath");
            arrayList.add(strDecode3);
            arrayList.add("&");
        }
        arrayList.add("--dex-file=" + str);
        arrayList.add("--oat-file=" + str2);
        arrayList.add("--instruction-set=".concat(SandHook.is64Bit() ? "arm64" : "arm"));
        arrayList.add("--compiler-filter=everything");
        if (SandHookConfig.SDK_INT >= 22 && SandHookConfig.SDK_INT < 29) {
            arrayList.add("--compile-pic");
        }
        try {
            if (SandHookConfig.SDK_INT <= 25) {
                if (Build.VERSION.SDK_INT >= 23) {
                    strDecode = "--inline-depth-limit=0";
                }
                ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
                processBuilder.redirectErrorStream(true);
                Process processStart = processBuilder.start();
                StreamConsumer.consumeInputStream(processStart.getInputStream());
                StreamConsumer.consumeInputStream(processStart.getErrorStream());
                iWaitFor = processStart.waitFor();
                if (iWaitFor != 0) {
                    return;
                } else {
                    throw new IOException(strDecode2 + iWaitFor);
                }
            }
            strDecode = "--inline-max-code-units=0";
            iWaitFor = processStart.waitFor();
            if (iWaitFor != 0) {
            }
        } catch (InterruptedException e) {
            throw new IOException("dex2oat is interrupted, msg: " + e.getMessage(), e);
        }
        arrayList.add(strDecode);
        ProcessBuilder processBuilder2 = new ProcessBuilder(arrayList);
        processBuilder2.redirectErrorStream(true);
        Process processStart2 = processBuilder2.start();
        StreamConsumer.consumeInputStream(processStart2.getInputStream());
        StreamConsumer.consumeInputStream(processStart2.getErrorStream());
    }
}
