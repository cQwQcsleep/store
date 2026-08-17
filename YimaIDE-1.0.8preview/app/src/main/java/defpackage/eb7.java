package defpackage;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.collections.ArraysKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileReader;
import org.eclipse.jdt.internal.compiler.env.AccessRestriction;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.env.NameEnvironmentAnswer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class eb7 implements INameEnvironment {
    public static final a e = new a(null);
    public final ZipFile b;
    public final HashSet c;
    public final HashMap d;

    public eb7(File file) {
        file.getClass();
        ZipFile zipFile = new ZipFile(file);
        this.b = zipFile;
        this.c = new HashSet();
        this.d = new HashMap();
        Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            String name = enumerationEntries.nextElement().getName();
            name.getClass();
            if (StringsKt.endsWith$default(name, JavaClass.EXTENSION, false, 2, (Object) null)) {
                for (int iLastIndexOf$default = StringsKt.lastIndexOf$default(name, '/', 0, false, 6, (Object) null); iLastIndexOf$default > 0; iLastIndexOf$default = StringsKt.lastIndexOf$default(name, '/', iLastIndexOf$default - 1, false, 4, (Object) null)) {
                    if (!this.c.add(name.substring(0, iLastIndexOf$default))) {
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

    public static CharSequence b(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    public static CharSequence c(char[] cArr) {
        cArr.getClass();
        return new String(cArr);
    }

    private final NameEnvironmentAnswer d(String str) {
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
            ZipEntry entry = this.b.getEntry(str);
            if (entry != null) {
                InputStream inputStream = this.b.getInputStream(entry);
                try {
                    inputStream.getClass();
                    byte[] bytes = ByteStreamsKt.readBytes(inputStream);
                    CloseableKt.closeFinally(inputStream, (Throwable) null);
                    char[] charArray = str.toCharArray();
                    charArray.getClass();
                    nameEnvironmentAnswer = new NameEnvironmentAnswer(new ClassFileReader(bytes, charArray), (AccessRestriction) null);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStream, th);
                        throw th2;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        this.d.put(str, nameEnvironmentAnswer);
        return nameEnvironmentAnswer;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public void cleanup() {
        this.d.clear();
        try {
            this.b.close();
        } catch (Throwable unused) {
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[] cArr, char[][] cArr2) {
        String str;
        cArr.getClass();
        cArr2.getClass();
        String strJoinToString$default = ArraysKt.joinToString$default(cArr2, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: cb7
            public final Object invoke(Object obj) {
                return eb7.c((char[]) obj);
            }
        }, 30, (Object) null);
        if (strJoinToString$default.length() == 0) {
            str = "";
        } else {
            str = strJoinToString$default + PsuedoNames.PSEUDONAME_ROOT;
        }
        return d(str + new String(cArr) + JavaClass.EXTENSION);
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
                str = ArraysKt.joinToString$default(cArr, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: db7
                    public final Object invoke(Object obj) {
                        return eb7.b((char[]) obj);
                    }
                }, 30, (Object) null) + PsuedoNames.PSEUDONAME_ROOT + str;
            }
        }
        return this.c.contains(str);
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public a() {
        }
    }

    @Override // org.eclipse.jdt.internal.compiler.env.INameEnvironment
    public NameEnvironmentAnswer findType(char[][] cArr) {
        cArr.getClass();
        return d(ArraysKt.joinToString$default(cArr, PsuedoNames.PSEUDONAME_ROOT, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: bb7
            public final Object invoke(Object obj) {
                return eb7.a((char[]) obj);
            }
        }, 30, (Object) null) + JavaClass.EXTENSION);
    }
}
