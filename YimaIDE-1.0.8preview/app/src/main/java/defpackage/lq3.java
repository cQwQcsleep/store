package defpackage;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.D8;
import com.android.tools.r8.D8Command;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.OutputMode;
import com.android.tools.r8.references.ClassReference;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class lq3 {
    public static final lq3 a = new lq3();
    public static final Regex b = new Regex("classes(\\d*)\\.dex");
    public static final int c = 8;

    public static final class a implements GlobalSyntheticsConsumer {
        public final File a;
        public final String b;
        public final AtomicInteger c;

        public a(File file, String str) {
            file.getClass();
            str.getClass();
            this.a = file;
            this.b = str;
            this.c = new AtomicInteger();
        }

        public void accept(ByteDataView byteDataView, ClassReference classReference, DiagnosticsHandler diagnosticsHandler) {
            byteDataView.getClass();
            diagnosticsHandler.getClass();
            this.a.mkdirs();
            File file = new File(this.a, this.b + "-" + this.c.getAndIncrement() + ".globals");
            byte[] bArrCopyByteData = byteDataView.copyByteData();
            bArrCopyByteData.getClass();
            FilesKt.writeBytes(file, bArrCopyByteData);
        }
    }

    public static final class b implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(((File) obj).getName(), ((File) obj2).getName());
        }
    }

    public static final class c implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(((File) obj).getName(), ((File) obj2).getName());
        }
    }

    public static boolean a(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static boolean b(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "dex");
    }

    public static boolean c(File file) {
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "globals");
    }

    public final boolean d(List list, File file, int i, DiagnosticsHandler diagnosticsHandler) {
        list.getClass();
        file.getClass();
        diagnosticsHandler.getClass();
        if (list.isEmpty()) {
            return true;
        }
        File file2 = new File(file.getParentFile(), file.getName() + "-globals");
        FilesKt.deleteRecursively(file2);
        file2.mkdirs();
        D8Command.Builder disableDesugaring = D8Command.builder(diagnosticsHandler).setOutput(file2.toPath(), OutputMode.DexIndexed).setMinApiLevel(i).setIntermediate(false).setDisableDesugaring(true);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            disableDesugaring.addGlobalSyntheticsFiles(new Path[]{((File) it.next()).toPath()});
        }
        D8.run(disableDesugaring.build());
        File[] fileArrListFiles = file2.listFiles(new FileFilter() { // from class: iq3
            @Override // java.io.FileFilter
            public final boolean accept(File file3) {
                return lq3.a(file3);
            }
        });
        List listSortedWith = fileArrListFiles != null ? ArraysKt.sortedWith(fileArrListFiles, new b()) : null;
        if (listSortedWith == null) {
            listSortedWith = CollectionsKt.emptyList();
        }
        if (listSortedWith.isEmpty()) {
            return false;
        }
        Iterator it2 = listSortedWith.iterator();
        while (it2.hasNext()) {
            if (!((File) it2.next()).renameTo(new File(file, f(file)))) {
                return false;
            }
        }
        FilesKt.deleteRecursively(file2);
        return true;
    }

    public final List e(File file) {
        file.getClass();
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: jq3
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return lq3.c(file2);
            }
        });
        List listSortedWith = fileArrListFiles != null ? ArraysKt.sortedWith(fileArrListFiles, new c()) : null;
        return listSortedWith == null ? CollectionsKt.emptyList() : listSortedWith;
    }

    public final String f(File file) {
        List groupValues;
        String str;
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: kq3
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return lq3.b(file2);
            }
        });
        int iIntValue = 0;
        if (fileArrListFiles != null) {
            ArrayList arrayList = new ArrayList();
            for (File file2 : fileArrListFiles) {
                Regex regex = b;
                String name = file2.getName();
                name.getClass();
                MatchResult matchResultMatchEntire = regex.matchEntire(name);
                Integer intOrNull = (matchResultMatchEntire == null || (groupValues = matchResultMatchEntire.getGroupValues()) == null || (str = (String) groupValues.get(1)) == null) ? null : str.length() == 0 ? 1 : StringsKt.toIntOrNull(str);
                if (intOrNull != null) {
                    arrayList.add(intOrNull);
                }
            }
            Integer num = (Integer) CollectionsKt.maxOrNull(arrayList);
            if (num != null) {
                iIntValue = num.intValue();
            }
        }
        if (iIntValue == 0) {
            return "classes.dex";
        }
        return "classes" + (iIntValue + 1) + ".dex";
    }
}
