package defpackage;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.env.AccessRestriction;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ay1 implements INameEnvironment {
    public final File b;
    public final HashSet c;
    public final HashMap d;

    public ay1(File file) {
        file.getClass();
        this.b = file;
        this.c = new HashSet();
        this.d = new HashMap();
        if (file.isDirectory()) {
            Iterator it = SequencesKt.filter(FilesKt.walkTopDown(file), new Function1() { // from class: xx1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ay1.b((File) obj));
                }
            }).iterator();
            while (it.hasNext()) {
                String path = FilesKt.relativeTo((File) it.next(), this.b).getPath();
                path.getClass();
                String strReplace$default = StringsKt.replace$default(path, '\\', '/', false, 4, (Object) null);
                for (int iLastIndexOf$default = StringsKt.lastIndexOf$default(strReplace$default, '/', 0, false, 6, (Object) null); iLastIndexOf$default > 0; iLastIndexOf$default = StringsKt.lastIndexOf$default(strReplace$default, '/', iLastIndexOf$default - 1, false, 4, (Object) null)) {
                    if (!this.c.add(strReplace$default.substring(0, iLastIndexOf$default))) {
                        break;
                    }
                }
            }
        }
    }

    public static CharSequence a(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static boolean b(File file) {
        file.getClass();
        return file.isFile() && Intrinsics.areEqual(FilesKt.getExtension(file), "class");
    }

    public static CharSequence c(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static CharSequence d(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public void cleanup() {
        this.d.clear();
    }

    public final NameEnvironmentAnswer e(String str) {
        boolean zContainsKey = this.d.containsKey(str);
        HashMap map = this.d;
        if (zContainsKey) {
            return (NameEnvironmentAnswer) map.get(str);
        }
        if (map.size() >= 4096) {
            this.d.clear();
        }
        NameEnvironmentAnswer nameEnvironmentAnswer = null;
        try {
            File file = new File(this.b, str);
            if (file.isFile()) {
                byte[] bytes = FilesKt.readBytes(file);
                char[] charArray = str.toCharArray();
                charArray.getClass();
                nameEnvironmentAnswer = new NameEnvironmentAnswer(new ClassFileReader(bytes, charArray), (AccessRestriction) null);
            }
        } catch (Throwable unused) {
        }
        this.d.put(str, nameEnvironmentAnswer);
        return nameEnvironmentAnswer;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[] cArr, char[][] cArr2) {
        String str;
        cArr.getClass();
        cArr2.getClass();
        String strJoinToString$default = ArraysKt.joinToString$default(cArr2, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: yx1
            public final Object invoke(Object obj) {
                return ay1.a((char[]) obj);
            }
        }, 30, (Object) null);
        if (strJoinToString$default.length() == 0) {
            str = "";
        } else {
            str = strJoinToString$default + PsuedoNames.PSEUDONAME_ROOT;
        }
        return e(str + new String(cArr) + JavaClass.EXTENSION);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public boolean isPackage(char[][] cArr, char[] cArr2) {
        if (cArr2 == null) {
            return false;
        }
        String str = new String(cArr2);
        if (str.length() == 0) {
            return false;
        }
        if (cArr != null) {
            if (!(cArr.length == 0)) {
                str = ArraysKt.joinToString$default(cArr, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: zx1
                    public final Object invoke(Object obj) {
                        return ay1.d((char[]) obj);
                    }
                }, 30, (Object) null) + PsuedoNames.PSEUDONAME_ROOT + str;
            }
        }
        return this.c.contains(str);
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[][] cArr) {
        cArr.getClass();
        return e(ArraysKt.joinToString$default(cArr, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: wx1
            public final Object invoke(Object obj) {
                return ay1.c((char[]) obj);
            }
        }, 30, (Object) null) + JavaClass.EXTENSION);
    }
}
