package org.jetbrains.kotlin.codegen;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Manifest;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.ExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class GeneratedClassLoader extends URLClassLoader {
    private ClassFileFactory factory;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 4 || i == 5 || i == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 4 || i == 5 || i == 6) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
            case 5:
            case 6:
                objArr[0] = "org/jetbrains/kotlin/codegen/GeneratedClassLoader";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            default:
                objArr[0] = "factory";
                break;
        }
        if (i == 1) {
            objArr[1] = "getClassFileFactory";
        } else if (i == 4 || i == 5) {
            objArr[1] = "findClass";
        } else if (i != 6) {
            objArr[1] = "org/jetbrains/kotlin/codegen/GeneratedClassLoader";
        } else {
            objArr[1] = "getAllGeneratedFiles";
        }
        switch (i) {
            case 1:
            case 4:
            case 5:
            case 6:
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[2] = "createFakeURLForResource";
                break;
            case 3:
                objArr[2] = "findClass";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 4 && i != 5 && i != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GeneratedClassLoader(ClassFileFactory classFileFactory, ClassLoader classLoader, URL... urlArr) {
        super(urlArr, classLoader);
        if (classFileFactory == null) {
            $$$reportNull$$$0(0);
        }
        this.factory = classFileFactory;
    }

    private URL createFakeURLForResource(String str) {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        try {
            OutputFile outputFile = this.factory.get(str);
            if (outputFile == null) {
                return null;
            }
            return BytesUrlUtils.createBytesUrl(outputFile.asByteArray());
        } catch (IOException e) {
            throw ExceptionUtilsKt.rethrow(e);
        }
    }

    public void dispose() {
        this.factory = null;
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public Class<?> findClass(String str) throws ClassNotFoundException {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        OutputFile outputFile = this.factory.get(str.replace('.', '/') + ".class");
        if (outputFile == null) {
            Class<?> clsFindClass = super.findClass(str);
            if (clsFindClass == null) {
                $$$reportNull$$$0(5);
            }
            return clsFindClass;
        }
        byte[] bArrAsByteArray = outputFile.asByteArray();
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            String strSubstring = str.substring(0, iLastIndexOf);
            if (getPackage(strSubstring) == null) {
                definePackage(strSubstring, new Manifest(), null);
            }
        }
        Class<?> clsDefineClass = defineClass(str, bArrAsByteArray, 0, bArrAsByteArray.length);
        if (clsDefineClass == null) {
            $$$reportNull$$$0(4);
        }
        return clsDefineClass;
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public URL findResource(String str) {
        URL urlCreateFakeURLForResource = createFakeURLForResource(str);
        return urlCreateFakeURLForResource != null ? urlCreateFakeURLForResource : super.findResource(str);
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public Enumeration<URL> findResources(String str) throws IOException {
        Enumeration<URL> enumerationFindResources = super.findResources(str);
        URL urlCreateFakeURLForResource = createFakeURLForResource(str);
        if (urlCreateFakeURLForResource == null) {
            return enumerationFindResources;
        }
        List listSingletonList = Collections.singletonList(urlCreateFakeURLForResource);
        if (enumerationFindResources.hasMoreElements()) {
            listSingletonList = CollectionsKt.plus(listSingletonList, Collections.list(enumerationFindResources));
        }
        return Collections.enumeration(listSingletonList);
    }

    public List<OutputFile> getAllGeneratedFiles() {
        List<OutputFile> listAsList = this.factory.asList();
        if (listAsList == null) {
            $$$reportNull$$$0(6);
        }
        return listAsList;
    }

    public ClassFileFactory getClassFileFactory() {
        ClassFileFactory classFileFactory = this.factory;
        if (classFileFactory == null) {
            $$$reportNull$$$0(1);
        }
        return classFileFactory;
    }

    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public InputStream getResourceAsStream(String str) {
        OutputFile outputFile = this.factory.get(str);
        return outputFile != null ? new ByteArrayInputStream(outputFile.asByteArray()) : super.getResourceAsStream(str);
    }
}
