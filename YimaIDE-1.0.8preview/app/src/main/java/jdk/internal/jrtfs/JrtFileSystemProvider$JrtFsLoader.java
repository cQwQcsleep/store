package jdk.internal.jrtfs;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.net.URL;
import java.net.URLClassLoader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JrtFileSystemProvider$JrtFsLoader extends URLClassLoader {
    public JrtFileSystemProvider$JrtFsLoader(URL[] urlArr) {
        super(urlArr);
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> clsFindLoadedClass = findLoadedClass(str);
        if (clsFindLoadedClass == null) {
            if (findResource(str.replace('.', '/') + JavaClass.EXTENSION) == null) {
                return super.loadClass(str, z);
            }
            clsFindLoadedClass = findClass(str);
        }
        if (z) {
            resolveClass(clsFindLoadedClass);
        }
        return clsFindLoadedClass;
    }
}
