package org.eclipse.jdt.internal.compiler.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class FileFinder {
    public static String[] find(File file, String str) {
        ArrayList arrayList = new ArrayList();
        find0(file, str, arrayList);
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    private static void find0(File file, String str, List<String> list) {
        String[] list2;
        if (!file.isDirectory() || (list2 = file.list()) == null) {
            return;
        }
        for (String str2 : list2) {
            File file2 = new File(file, str2);
            if (file2.isDirectory()) {
                find0(file2, str, list);
            } else {
                String lowerCase = file2.getName().toLowerCase();
                if (lowerCase.endsWith(str)) {
                    if (lowerCase.endsWith("module-info.java")) {
                        list.add(0, file2.getAbsolutePath());
                    } else {
                        list.add(file2.getAbsolutePath());
                    }
                }
            }
        }
    }
}
