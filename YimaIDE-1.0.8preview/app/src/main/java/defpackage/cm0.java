package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class cm0 {
    public static final cm0 a = new cm0();
    public static final Set b = SetsKt.setOf(new String[]{"build", ".gradle", ".idea", "node_modules", "target", ".cxx", "__pycache__", ".venv", "venv"});
    public static final int c = 8;

    public static final class b implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(Long.valueOf(((File) obj2).lastModified()), Long.valueOf(((File) obj).lastModified()));
        }
    }

    public static boolean a(File file) {
        return file.isFile();
    }

    public final a b(Context context, String str, String str2, String str3) {
        context.getClass();
        str.getClass();
        str2.getClass();
        str3.getClass();
        File fileQ0 = ufb.a.q0(context, str, str2);
        if (fileQ0 == null || !fileQ0.isDirectory()) {
            return new a(false, null, "文件夹不存在或路径越界：" + str2, 2, null);
        }
        File file = new File(context.getFilesDir(), "export");
        file.mkdirs();
        d(file, 3);
        String name = fileQ0.getName();
        String str4 = StringsKt.isBlank(name) ? "export" : name;
        str4.getClass();
        File file2 = new File(file, StringsKt.take(new Regex("[\\\\/:*?\"<>|\\s]+").replace(str4, "_"), 40) + "-" + System.currentTimeMillis() + ".zip");
        if (file2.exists()) {
            file2.delete();
        }
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2), 8192));
            try {
                for (File file3 : FilesKt.walkTopDown(fileQ0)) {
                    if (!Intrinsics.areEqual(file3, fileQ0)) {
                        String path = FilesKt.relativeTo(file3, fileQ0).getPath();
                        path.getClass();
                        String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
                        if (!StringsKt.isBlank(strReplace$default) && !a.c(strReplace$default) && !file3.isDirectory() && !StringsKt.startsWith$default(strReplace$default, PsuedoNames.PSEUDONAME_ROOT, false, 2, (Object) null) && !StringsKt.contains$default(strReplace$default, Constants.ATTRVAL_PARENT, false, 2, (Object) null)) {
                            zipOutputStream.putNextEntry(new ZipEntry(strReplace$default));
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file3), 8192);
                            try {
                                ByteStreamsKt.copyTo$default(bufferedInputStream, zipOutputStream, 0, 2, (Object) null);
                                CloseableKt.closeFinally(bufferedInputStream, (Throwable) null);
                                zipOutputStream.closeEntry();
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(bufferedInputStream, th);
                                    throw th2;
                                }
                            }
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(zipOutputStream, (Throwable) null);
                e(context, file2, str3);
                return new a(true, file2, null, 4, null);
            } catch (Throwable th3) {
                try {
                    throw th3;
                } catch (Throwable th4) {
                    CloseableKt.closeFinally(zipOutputStream, th3);
                    throw th4;
                }
            }
        } catch (Throwable th5) {
            Log.e("BackendExporter", "exportAndShare failed: " + str2, th5);
            if (!file2.exists()) {
                file2 = null;
            }
            if (file2 != null) {
                file2.delete();
            }
            String message = th5.getMessage();
            if (message == null) {
                message = th5.getClass().getSimpleName();
            }
            return new a(false, null, message, 2, null);
        }
    }

    public final boolean c(String str) {
        List listSplit$default = StringsKt.split$default(str, new char[]{'/'}, false, 0, 6, (Object) null);
        if ((listSplit$default instanceof Collection) && listSplit$default.isEmpty()) {
            return false;
        }
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            if (b.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public final void d(File file, int i) {
        Unit unit;
        List listSortedWith;
        List listDrop;
        try {
            Result.Companion companion = Result.Companion;
            File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: bm0
                @Override // java.io.FileFilter
                public final boolean accept(File file2) {
                    return cm0.a(file2);
                }
            });
            if (fileArrListFiles == null || (listSortedWith = ArraysKt.sortedWith(fileArrListFiles, new b())) == null || (listDrop = CollectionsKt.drop(listSortedWith, i)) == null) {
                unit = null;
            } else {
                Iterator it = listDrop.iterator();
                while (it.hasNext()) {
                    ((File) it.next()).delete();
                }
                unit = Unit.INSTANCE;
            }
            Result.constructor-impl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    public final void e(Context context, File file, String str) {
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        uriForFile.getClass();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("application/zip");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        intent.addFlags(1);
        context.startActivity(Intent.createChooser(intent, str).addFlags(268435456));
    }

    public static final class a {
        public final boolean a;
        public final File b;
        public final String c;

        public /* synthetic */ a(boolean z, File file, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, (i & 2) != 0 ? null : file, (i & 4) != 0 ? null : str);
        }

        public final String a() {
            return this.c;
        }

        public final boolean b() {
            return this.a;
        }

        public final File c() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.a) * 31;
            File file = this.b;
            int iHashCode2 = (iHashCode + (file == null ? 0 : file.hashCode())) * 31;
            String str = this.c;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Result(success=" + this.a + ", zipFile=" + this.b + ", error=" + this.c + ")";
        }

        public a(boolean z, File file, String str) {
            this.a = z;
            this.b = file;
            this.c = str;
        }
    }
}
