package defpackage;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileFilter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class tr1 {
    public static final tr1 a = new tr1();
    public static final SecureRandom b = new SecureRandom();
    public static final int c = 8;

    public static final class a implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(Long.valueOf(((File) obj).lastModified()), Long.valueOf(((File) obj2).lastModified()));
        }
    }

    public static boolean a(File file) {
        return file.isDirectory();
    }

    public final File b(Context context, String str) {
        return new File(context.getApplicationContext().getFilesDir(), "checkpoints/" + str);
    }

    public final void c(Context context, String str) {
        context.getClass();
        str.getClass();
        try {
            Result.Companion companion = Result.Companion;
            Result.constructor-impl(Boolean.valueOf(FilesKt.deleteRecursively(b(context, str))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        File fileE = e(context, str);
        try {
            Result.constructor-impl(Boolean.valueOf(FilesKt.deleteRecursively(new File(fileE.getParentFile(), fileE.getName() + ".restore-tmp"))));
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th2));
        }
        try {
            Result.constructor-impl(Boolean.valueOf(FilesKt.deleteRecursively(new File(fileE.getParentFile(), fileE.getName() + ".restore-bak"))));
        } catch (Throwable th3) {
            Result.Companion companion4 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th3));
        }
    }

    public final String d(Context context, String str) {
        context.getClass();
        str.getClass();
        File fileE = e(context, str);
        if (!fileE.isDirectory()) {
            return null;
        }
        String string = Long.toString(System.currentTimeMillis(), CharsKt.checkRadix(16));
        string.getClass();
        String str2 = string + "-" + Long.toHexString(b.nextLong() & ClassFileConstants.JDK_DEFERRED);
        File file = new File(b(context, str), str2);
        try {
            file.mkdirs();
            File[] fileArrListFiles = fileE.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    if (!Intrinsics.areEqual(file2.getName(), "build")) {
                        FilesKt.copyRecursively$default(file2, new File(file, file2.getName()), true, (Function2) null, 4, (Object) null);
                    }
                }
            }
            f(context, str);
            return str2;
        } catch (Throwable th) {
            Log.e("CheckpointStore", "创建检查点失败: projectId=" + str, th);
            FilesKt.deleteRecursively(file);
            return null;
        }
    }

    public final File e(Context context, String str) {
        return new File(context.getApplicationContext().getFilesDir(), "projects/" + str);
    }

    public final void f(Context context, String str) {
        List list;
        File[] fileArrListFiles = b(context, str).listFiles(new FileFilter() { // from class: sr1
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return tr1.a(file);
            }
        });
        if (fileArrListFiles == null || (list = ArraysKt.toList(fileArrListFiles)) == null || list.size() <= 30) {
            return;
        }
        for (File file : CollectionsKt.take(CollectionsKt.sortedWith(list, new a()), list.size() - 30)) {
            file.getClass();
            FilesKt.deleteRecursively(file);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3, types: [int] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v4 */
    public final boolean g(Context context, String str, String str2) {
        boolean z;
        Unit unit;
        context.getClass();
        str.getClass();
        str2.getClass();
        File file = new File(b(context, str), str2);
        boolean z2 = false;
        if (!file.isDirectory()) {
            return false;
        }
        File fileE = e(context, str);
        File file2 = new File(fileE.getParentFile(), fileE.getName() + ".restore-tmp");
        File file3 = new File(fileE.getParentFile(), fileE.getName() + ".restore-bak");
        try {
            FilesKt.deleteRecursively(file2);
            FilesKt.deleteRecursively(file3);
            file2.mkdirs();
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file4 : fileArrListFiles) {
                    file4.getClass();
                    FilesKt.copyRecursively$default(file4, new File(file2, file4.getName()), true, (Function2) null, 4, (Object) null);
                }
            }
            ArrayList arrayList = new ArrayList();
            try {
                fileE.mkdirs();
                file3.mkdirs();
                File[] fileArrListFiles2 = fileE.listFiles();
                if (fileArrListFiles2 != null) {
                    int length = fileArrListFiles2.length;
                    int i = 0;
                    while (i < length) {
                        File file5 = fileArrListFiles2[i];
                        if (Intrinsics.areEqual(file5.getName(), "build")) {
                            z = z2;
                        } else {
                            z = z2;
                            try {
                                File file6 = new File(file3, file5.getName());
                                if (!file5.renameTo(file6)) {
                                    FilesKt.copyRecursively$default(file5, file6, true, (Function2) null, 4, (Object) null);
                                    FilesKt.deleteRecursively(file5);
                                }
                                arrayList.add(TuplesKt.to(file5, file6));
                            } catch (Throwable th) {
                                th = th;
                                Log.e("CheckpointStore", "恢复检查点失败（移出现有内容），尝试回滚: projectId=" + str + ", checkpointId=" + str2, th);
                                h(arrayList, str);
                                FilesKt.deleteRecursively(file2);
                                FilesKt.deleteRecursively(file3);
                                return z;
                            }
                        }
                        i++;
                        z2 = z;
                    }
                }
                boolean z3 = z2;
                try {
                    File[] fileArrListFiles3 = file2.listFiles();
                    if (fileArrListFiles3 != null) {
                        int length2 = fileArrListFiles3.length;
                        for (?? r12 = z3; r12 < length2; r12++) {
                            File file7 = fileArrListFiles3[r12];
                            File file8 = new File(fileE, file7.getName());
                            if (!file7.renameTo(file8)) {
                                FilesKt.copyRecursively$default(file7, file8, true, (Function2) null, 4, (Object) null);
                                FilesKt.deleteRecursively(file7);
                            }
                        }
                    }
                    FilesKt.deleteRecursively(file2);
                    FilesKt.deleteRecursively(file3);
                    return true;
                } catch (Throwable th2) {
                    Log.e("CheckpointStore", "恢复检查点失败（移入快照内容），尝试回滚: projectId=" + str + ", checkpointId=" + str2, th2);
                    try {
                        Result.Companion companion = Result.Companion;
                        File[] fileArrListFiles4 = fileE.listFiles();
                        if (fileArrListFiles4 != null) {
                            int length3 = fileArrListFiles4.length;
                            for (?? r4 = z3; r4 < length3; r4++) {
                                File file9 = fileArrListFiles4[r4];
                                if (!Intrinsics.areEqual(file9.getName(), "build")) {
                                    FilesKt.deleteRecursively(file9);
                                }
                            }
                            unit = Unit.INSTANCE;
                        } else {
                            unit = null;
                        }
                        Result.constructor-impl(unit);
                    } catch (Throwable th3) {
                        Result.Companion companion2 = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(th3));
                    }
                    h(arrayList, str);
                    FilesKt.deleteRecursively(file2);
                    FilesKt.deleteRecursively(file3);
                    return z3;
                }
            } catch (Throwable th4) {
                th = th4;
                z = z2;
            }
        } catch (Throwable th5) {
            Log.e("CheckpointStore", "恢复检查点失败（拷贝快照到临时目录）: projectId=" + str + ", checkpointId=" + str2, th5);
            FilesKt.deleteRecursively(file2);
            return false;
        }
    }

    public final void h(List list, String str) {
        Object obj;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            File file = (File) pair.component1();
            File file2 = (File) pair.component2();
            try {
                Result.Companion companion = Result.Companion;
                if (file2.exists() && !file2.renameTo(file)) {
                    FilesKt.copyRecursively$default(file2, file, true, (Function2) null, 4, (Object) null);
                    FilesKt.deleteRecursively(file2);
                }
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                Log.w("CheckpointStore", "回滚失败: projectId=" + str + ", file=" + file.getName(), th2);
            }
        }
    }
}
