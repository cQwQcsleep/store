package com.intellij.util.lang;

import androidx.collection.ScatterMapKt;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class JarLoader implements Loader {
    private static final Map.Entry<Attribute, Attributes.Name>[] PACKAGE_FIELDS = {new AbstractMap.SimpleImmutableEntry(Attribute.SPEC_TITLE, Attributes.Name.SPECIFICATION_TITLE), new AbstractMap.SimpleImmutableEntry(Attribute.SPEC_VERSION, Attributes.Name.SPECIFICATION_VERSION), new AbstractMap.SimpleImmutableEntry(Attribute.SPEC_VENDOR, Attributes.Name.SPECIFICATION_VENDOR), new AbstractMap.SimpleImmutableEntry(Attribute.CLASS_PATH, Attributes.Name.CLASS_PATH), new AbstractMap.SimpleImmutableEntry(Attribute.IMPL_TITLE, Attributes.Name.IMPLEMENTATION_TITLE), new AbstractMap.SimpleImmutableEntry(Attribute.IMPL_VERSION, Attributes.Name.IMPLEMENTATION_VERSION), new AbstractMap.SimpleImmutableEntry(Attribute.IMPL_VENDOR, Attributes.Name.IMPLEMENTATION_VENDOR)};
    public final ClassPath configuration;
    private final Path path;
    public final URL url;
    public final ResourceFile zipFile;

    public enum Attribute {
        SPEC_TITLE,
        SPEC_VERSION,
        SPEC_VENDOR,
        CLASS_PATH,
        IMPL_TITLE,
        IMPL_VERSION,
        IMPL_VENDOR
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "configuration";
                break;
            case 2:
                objArr[0] = "zipFile";
                break;
            case 3:
                objArr[0] = "file";
                break;
            case 4:
                objArr[0] = "attributes";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "com/intellij/util/lang/JarLoader";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "name";
                break;
            case 8:
                objArr[0] = "dir";
                break;
            case 9:
                objArr[0] = "fileNameFilter";
                break;
            case 10:
                objArr[0] = "consumer";
                break;
            default:
                objArr[0] = "path";
                break;
        }
        if (i == 5 || i == 6) {
            objArr[1] = "getAttributes";
        } else {
            objArr[1] = "com/intellij/util/lang/JarLoader";
        }
        switch (i) {
            case 3:
                objArr[2] = "fileToUri";
                break;
            case 4:
                objArr[2] = "getAttributes";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "getResource";
                break;
            case 8:
            case 9:
            case 10:
                objArr[2] = "processResources";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public JarLoader(Path path, ClassPath classPath, ResourceFile resourceFile) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (classPath == null) {
            $$$reportNull$$$0(1);
        }
        if (resourceFile == null) {
            $$$reportNull$$$0(2);
        }
        this.path = path;
        this.configuration = classPath;
        this.zipFile = resourceFile;
        this.url = new URL("jar", "", -1, fileToUri(path) + "!/");
    }

    private static URI fileToUri(Path path) {
        if (path == null) {
            $$$reportNull$$$0(3);
        }
        String strReplace = path.toString().replace(File.separatorChar, '/');
        if (!strReplace.startsWith("/")) {
            strReplace = "/".concat(strReplace);
        } else if (strReplace.startsWith("//")) {
            strReplace = "//".concat(strReplace);
        }
        try {
            return new URI("file", null, strReplace, null);
        } catch (URISyntaxException e) {
            nrd.a(strReplace, e);
            return null;
        }
    }

    public static Map<Attribute, String> getAttributes(Attributes attributes) {
        if (attributes == null) {
            $$$reportNull$$$0(4);
        }
        if (attributes.isEmpty()) {
            Map<Attribute, String> map = Collections.EMPTY_MAP;
            if (map == null) {
                $$$reportNull$$$0(5);
            }
            return map;
        }
        Map<Attribute, String> enumMap = null;
        for (Map.Entry<Attribute, Attributes.Name> entry : PACKAGE_FIELDS) {
            String value = attributes.getValue(entry.getValue());
            if (value != null) {
                if (enumMap == null) {
                    enumMap = new EnumMap<>(Attribute.class);
                }
                enumMap.put(entry.getKey(), value);
            }
        }
        if (enumMap == null) {
            enumMap = Collections.EMPTY_MAP;
        }
        if (enumMap == null) {
            $$$reportNull$$$0(6);
        }
        return enumMap;
    }

    @Override // com.intellij.util.lang.Loader
    public Class<?> findClass(String str, String str2, ClassPath.ClassDataConsumer classDataConsumer) throws IOException {
        return this.zipFile.findClass(str, str2, this, classDataConsumer);
    }

    @Override // com.intellij.util.lang.Loader
    public Path getPath() {
        return this.path;
    }

    @Override // com.intellij.util.lang.Loader
    public Resource getResource(String str) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        try {
            return this.zipFile.getResource(str, this);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "JarLoader(path=" + this.path + ")";
    }
}
