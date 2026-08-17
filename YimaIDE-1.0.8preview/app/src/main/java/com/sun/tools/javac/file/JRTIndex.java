package com.sun.tools.javac.file;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.ConfigProvider;
import com.sun.tools.javac.util.Context;
import defpackage.u8i;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.ProviderNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.tools.FileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JRTIndex {
    private static JRTIndex sharedInstance;
    private ResourceBundle ctBundle;
    private final FileSystem jrtfs = ConfigProvider.jrtFsProvider.getFileSystem(URI.create("jrt:/"));
    private final Map<RelativePath.RelativeDirectory, SoftReference<Entry>> entries = new HashMap();

    public static class CtSym {
        static final CtSym EMPTY = new CtSym(false, false, null);
        public final boolean hidden;
        public final String minProfile;
        public final boolean proprietary;

        public CtSym(boolean z, boolean z2, String str) {
            this.hidden = z;
            this.proprietary = z2;
            this.minProfile = str;
        }

        public String toString() {
            boolean z;
            StringBuilder sb = new StringBuilder("CtSym[");
            boolean z2 = true;
            if (this.hidden) {
                sb.append("hidden");
                z = true;
            } else {
                z = false;
            }
            if (this.proprietary) {
                if (z) {
                    sb.append(",");
                }
                sb.append("proprietary");
            } else {
                z2 = z;
            }
            if (this.minProfile != null) {
                if (z2) {
                    sb.append(",");
                }
                sb.append(this.minProfile);
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public class Entry {
        final CtSym ctSym;
        final Map<String, Path> files;
        final Set<RelativePath.RelativeDirectory> subdirs;

        private Entry(Map<String, Path> map, Set<RelativePath.RelativeDirectory> set, CtSym ctSym) {
            this.files = map;
            this.subdirs = set;
            this.ctSym = ctSym;
        }
    }

    private JRTIndex() throws IOException {
    }

    /* JADX WARN: Code duplicated, block: B:22:0x006c  */
    private CtSym getCtInfo(RelativePath.RelativeDirectory relativeDirectory) {
        if (relativeDirectory.path.length() == 0) {
            return CtSym.EMPTY;
        }
        if (this.ctBundle == null) {
            this.ctBundle = ResourceBundle.getBundle("com.sun.tools.javac.resources.ct");
        }
        try {
            boolean z = false;
            String str = null;
            boolean z2 = false;
            for (String str2 : this.ctBundle.getString(relativeDirectory.path.replace('/', '.') + '*').split(" +", 0)) {
                int iHashCode = str2.hashCode();
                if (iHashCode != -1217487446) {
                    if (iHashCode == -261419329 && str2.equals("proprietary")) {
                        z = true;
                    } else {
                        str = str2;
                    }
                } else if (str2.equals("hidden")) {
                    z2 = true;
                } else {
                    str = str2;
                }
            }
            return new CtSym(z2, z, str);
        } catch (MissingResourceException unused) {
            return CtSym.EMPTY;
        }
    }

    public static synchronized JRTIndex getSharedInstance() {
        if (sharedInstance == null) {
            try {
                sharedInstance = new JRTIndex();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return sharedInstance;
    }

    public static JRTIndex instance(Context context) {
        try {
            JRTIndex jRTIndex = (JRTIndex) context.get(JRTIndex.class);
            if (jRTIndex != null) {
                return jRTIndex;
            }
            JRTIndex jRTIndex2 = new JRTIndex();
            context.put((Class<JRTIndex>) JRTIndex.class, jRTIndex2);
            return jRTIndex2;
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    public static boolean isAvailable() {
        try {
            ConfigProvider.jrtFsProvider.getFileSystem(URI.create("jrt:/"));
            return true;
        } catch (FileSystemNotFoundException | ProviderNotFoundException unused) {
            return false;
        }
    }

    public CtSym getCtSym(CharSequence charSequence) throws IOException {
        return getEntry(RelativePath.RelativeDirectory.forPackage(charSequence)).ctSym;
    }

    public synchronized Entry getEntry(RelativePath.RelativeDirectory relativeDirectory) throws Throwable {
        Throwable th;
        JRTIndex jRTIndex;
        Path path;
        try {
            try {
                SoftReference<Entry> softReference = this.entries.get(relativeDirectory);
                Entry entry = softReference == null ? null : softReference.get();
                if (entry == null) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    int length = relativeDirectory.path.length();
                    FileSystem fileSystem = this.jrtfs;
                    if (length == 0) {
                        try {
                            path = fileSystem.getPath("/modules", new String[0]);
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    } else {
                        path = fileSystem.getPath("/packages", new String[0]).resolve(relativeDirectory.getPath().replaceAll("/$", "").replace(PsuedoNames.PSEUDONAME_ROOT, Constants.ATTRVAL_THIS));
                    }
                    if (Files.exists(path, new LinkOption[0])) {
                        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
                        try {
                            for (Path symbolicLink : directoryStreamNewDirectoryStream) {
                                if (Files.isSymbolicLink(symbolicLink)) {
                                    symbolicLink = Files.readSymbolicLink(symbolicLink);
                                }
                                Path pathResolveAgainst = relativeDirectory.resolveAgainst(symbolicLink);
                                if (Files.exists(pathResolveAgainst, new LinkOption[0])) {
                                    DirectoryStream<Path> directoryStreamNewDirectoryStream2 = Files.newDirectoryStream(pathResolveAgainst);
                                    try {
                                        for (Path path2 : directoryStreamNewDirectoryStream2) {
                                            String string = path2.getFileName().toString();
                                            if (Files.isRegularFile(path2, new LinkOption[0])) {
                                                linkedHashMap.put(string, path2);
                                            } else if (Files.isDirectory(path2, new LinkOption[0])) {
                                                linkedHashSet.add(new RelativePath.RelativeDirectory(relativeDirectory, string));
                                            }
                                        }
                                        directoryStreamNewDirectoryStream2.close();
                                    } catch (Throwable th3) {
                                        if (directoryStreamNewDirectoryStream2 == null) {
                                            throw th3;
                                        }
                                        try {
                                            directoryStreamNewDirectoryStream2.close();
                                            throw th3;
                                        } catch (Throwable th4) {
                                            th3.addSuppressed(th4);
                                            throw th3;
                                        }
                                        th = th2;
                                        throw th;
                                    }
                                }
                            }
                            directoryStreamNewDirectoryStream.close();
                        } catch (Throwable th5) {
                            if (directoryStreamNewDirectoryStream == null) {
                                throw th5;
                            }
                            try {
                                directoryStreamNewDirectoryStream.close();
                                throw th5;
                            } catch (Throwable th6) {
                                th5.addSuppressed(th6);
                                throw th5;
                            }
                        }
                    }
                    Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
                    Set setUnmodifiableSet = Collections.unmodifiableSet(linkedHashSet);
                    jRTIndex = this;
                    entry = new Entry(mapUnmodifiableMap, setUnmodifiableSet, getCtInfo(relativeDirectory));
                    jRTIndex.entries.put(relativeDirectory, new SoftReference<>(entry));
                } else {
                    jRTIndex = this;
                }
                return entry;
            } catch (Throwable th7) {
                th = th7;
                th = th;
                throw th;
            }
        } catch (Throwable th8) {
            th = th8;
            th = th;
            throw th;
        }
        throw th;
    }

    public boolean isInJRT(FileObject fileObject) {
        return (fileObject instanceof PathFileObject) && ((PathFileObject) fileObject).getPath().getFileSystem() == this.jrtfs;
    }
}
