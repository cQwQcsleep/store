package org.jetbrains.kotlin.cli.common.repl;

import com.google.common.collect.Maps;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.util.TraceClassVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class ReplClassLoader extends ClassLoader {
    private final Map<JvmClassName, byte[]> classes;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[0] = "org/jetbrains/kotlin/cli/common/repl/ReplClassLoader";
                break;
            case 4:
                objArr[0] = "className";
                break;
            case 5:
                objArr[0] = "bytes";
                break;
            case 6:
                objArr[0] = "writer";
                break;
            default:
                objArr[0] = "parent";
                break;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "findClass";
        } else {
            objArr[1] = "org/jetbrains/kotlin/cli/common/repl/ReplClassLoader";
        }
        switch (i) {
            case 1:
                objArr[2] = "findClass";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                break;
            case 4:
            case 5:
                objArr[2] = "addClass";
                break;
            case 6:
                objArr[2] = "dumpClasses";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReplClassLoader(ClassLoader classLoader) {
        super(classLoader);
        if (classLoader == null) {
            $$$reportNull$$$0(0);
        }
        this.classes = Maps.newLinkedHashMap();
    }

    public void addClass(JvmClassName jvmClassName, byte[] bArr) {
        if (jvmClassName == null) {
            $$$reportNull$$$0(4);
        }
        if (bArr == null) {
            $$$reportNull$$$0(5);
        }
        if (this.classes.put(jvmClassName, bArr) == null) {
            return;
        }
        qu7.a("Rewrite at key ", jvmClassName);
    }

    public void dumpClasses(PrintWriter printWriter) {
        if (printWriter == null) {
            $$$reportNull$$$0(6);
        }
        Iterator<byte[]> it = this.classes.values().iterator();
        while (it.hasNext()) {
            new ClassReader(it.next()).accept(new TraceClassVisitor(printWriter), 0);
        }
    }

    @Override // java.lang.ClassLoader
    public Class<?> findClass(String str) throws ClassNotFoundException {
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        byte[] bArr = this.classes.get(JvmClassName.byFqNameWithoutInnerClasses(str));
        if (bArr != null) {
            Class<?> clsDefineClass = defineClass(str, bArr, 0, bArr.length);
            if (clsDefineClass == null) {
                $$$reportNull$$$0(2);
            }
            return clsDefineClass;
        }
        Class<?> clsFindClass = super.findClass(str);
        if (clsFindClass == null) {
            $$$reportNull$$$0(3);
        }
        return clsFindClass;
    }
}
