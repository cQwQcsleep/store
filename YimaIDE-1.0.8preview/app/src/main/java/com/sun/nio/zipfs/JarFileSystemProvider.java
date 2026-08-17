package com.sun.nio.zipfs;

import defpackage.yba;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JarFileSystemProvider extends ZipFileSystemProvider {
    @Override // com.sun.nio.zipfs.ZipFileSystemProvider, java.nio.file.spi.FileSystemProvider
    public Path getPath(URI uri) {
        String string;
        int iIndexOf;
        FileSystem fileSystem = getFileSystem(uri);
        String fragment = uri.getFragment();
        if (fragment == null && (iIndexOf = (string = uri.toString()).indexOf("!/")) != -1) {
            fragment = string.substring(iIndexOf + 2);
        }
        if (fragment != null) {
            return fileSystem.getPath(fragment, new String[0]);
        }
        kg9.a("URI: ", uri, " does not contain path fragment ex. jar:///c:/foo.zip!/BAR");
        return null;
    }

    @Override // com.sun.nio.zipfs.ZipFileSystemProvider, java.nio.file.spi.FileSystemProvider
    public String getScheme() {
        return "jar";
    }

    @Override // com.sun.nio.zipfs.ZipFileSystemProvider
    public Path uriToPath(URI uri) {
        String scheme = uri.getScheme();
        if (scheme == null || !scheme.equalsIgnoreCase(getScheme())) {
            yba.a("URI scheme is not '", getScheme(), "'");
            return null;
        }
        try {
            String string = uri.toString();
            int iIndexOf = string.indexOf("!/");
            if (iIndexOf == -1) {
                iIndexOf = string.length();
            }
            URI uri2 = new URI(string.substring(4, iIndexOf));
            return Paths.get(new URI("file", uri2.getHost(), uri2.getPath(), null)).toAbsolutePath();
        } catch (URISyntaxException e) {
            x01.a(e);
            return null;
        }
    }
}
