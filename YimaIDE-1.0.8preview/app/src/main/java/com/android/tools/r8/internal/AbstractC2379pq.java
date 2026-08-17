package com.android.tools.r8.internal;

import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2379pq {
    public static String[] a(String[] strArr, Consumer consumer) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            if (str.startsWith("@")) {
                Path path = Paths.get(str.substring(1), new String[0]);
                try {
                    arrayList.addAll(Files.readAllLines(path));
                } catch (IOException e) {
                    consumer.accept(new ExceptionDiagnostic(e, new C2293oq(path)));
                }
            } else {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(Wf0.b);
    }
}
