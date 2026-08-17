package org.jetbrains.kotlin.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.util.Processor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.library.KlibConstants;
import org.jetbrains.kotlin.library.components.KlibIrConstants;
import org.jetbrains.kotlin.utils.JsLibrary;
import org.jetbrains.kotlin.utils.JsLibraryUtils;
import org.jetbrains.kotlin.utils.fileUtils.FileUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J(\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\nH\u0007J$\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0007J\u0010\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J(\u0010\u001a\u001a\u00020\u000e*\u00020\f2\u0006\u0010\u001b\u001a\u00020\b2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002J \u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nH\u0002J\u000e\u0010\u001e\u001a\u0004\u0018\u00010\b*\u00020\fH\u0002J\f\u0010\u001f\u001a\u00020\f*\u00020\fH\u0002J$\u0010 \u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002J$\u0010!\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002J \u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\nH\u0002J \u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\nH\u0002J$\u0010'\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\f2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016H\u0002J\u0012\u0010(\u001a\u0004\u0018\u00010\b2\u0006\u0010)\u001a\u00020\bH\u0002R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/utils/JsLibraryUtils;", "", "<init>", "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "META_INF_RESOURCES", "", "isKotlinJavascriptIrLibrary", "", "candidate", "Ljava/io/File;", "copyJsFilesFromLibraries", "", "libraries", "", "outputLibraryJsPath", "copySourceMap", "traverseJsLibrary", "lib", "action", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/utils/JsLibrary;", "isZippedKlibInZip", "isZippedKlib", "runIfFileExists", "relativePath", "copyJsFilesFromDirectory", "dir", "contentIfExists", "correspondingSourceMapFile", "processDirectory", "traverseDirectory", "copyJsFilesFromZip", "file", "copyLibrary", "outputPath", "library", "traverseArchive", "getSuggestedPath", "path", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsLibraryUtils {
    public static final JsLibraryUtils INSTANCE = new JsLibraryUtils();
    private static final Logger LOG;
    private static final String META_INF_RESOURCES;

    static {
        Logger logger = Logger.getInstance(JsLibraryUtils.class);
        logger.getClass();
        LOG = logger;
        META_INF_RESOURCES = LibraryUtils.INSTANCE.getMETA_INF() + "resources/";
    }

    private JsLibraryUtils() {
    }

    public static Unit a(String str, boolean z, JsLibrary jsLibrary) {
        jsLibrary.getClass();
        INSTANCE.copyLibrary(str, jsLibrary, z);
        return Unit.INSTANCE;
    }

    public static Unit b(String str, boolean z, JsLibrary jsLibrary) {
        jsLibrary.getClass();
        INSTANCE.copyLibrary(str, jsLibrary, z);
        return Unit.INSTANCE;
    }

    public static boolean c(File file, Function1 function1, File file2) {
        JsLibraryUtils jsLibraryUtils;
        String suggestedPath;
        String relativePath = FileUtil.getRelativePath(file, file2);
        if (relativePath != null) {
            if (StringsKt.endsWith$default(relativePath, ".js", false, 2, (Object) null) && (suggestedPath = (jsLibraryUtils = INSTANCE).getSuggestedPath(relativePath)) != null) {
                file2.getClass();
                jsLibraryUtils.runIfFileExists(file2, suggestedPath, function1);
            }
            return true;
        }
        throw new IllegalArgumentException("relativePath should not be null " + file + ' ' + file2);
    }

    private final String contentIfExists(File file) {
        if (file.exists()) {
            return FilesKt.readText$default(file, (Charset) null, 1, (Object) null);
        }
        return null;
    }

    private final void copyJsFilesFromDirectory(File dir, final String outputLibraryJsPath, final boolean copySourceMap) {
        traverseDirectory(dir, new Function1() { // from class: it7
            public final Object invoke(Object obj) {
                return JsLibraryUtils.b(outputLibraryJsPath, copySourceMap, (JsLibrary) obj);
            }
        });
    }

    @JvmStatic
    public static final void copyJsFilesFromLibraries(List<String> libraries, String outputLibraryJsPath, boolean copySourceMap) throws IOException {
        libraries.getClass();
        outputLibraryJsPath.getClass();
        Iterator<String> it = libraries.iterator();
        while (it.hasNext()) {
            File file = new File(it.next());
            file.exists();
            if (file.isDirectory()) {
                INSTANCE.copyJsFilesFromDirectory(file, outputLibraryJsPath, copySourceMap);
            } else {
                INSTANCE.copyJsFilesFromZip(file, outputLibraryJsPath, copySourceMap);
            }
        }
    }

    public static /* synthetic */ void copyJsFilesFromLibraries$default(List list, String str, boolean z, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            z = false;
        }
        copyJsFilesFromLibraries(list, str, z);
    }

    private final void copyJsFilesFromZip(File file, final String outputLibraryJsPath, final boolean copySourceMap) throws IOException {
        traverseArchive(file, new Function1() { // from class: jt7
            public final Object invoke(Object obj) {
                return JsLibraryUtils.a(outputLibraryJsPath, copySourceMap, (JsLibrary) obj);
            }
        });
    }

    private final void copyLibrary(String outputPath, JsLibrary library, boolean copySourceMap) {
        String sourceMapContent;
        File file = new File(outputPath, library.getPath());
        file.getParentFile().mkdirs();
        FilesKt.writeText$default(file, library.getContent(), (Charset) null, 2, (Object) null);
        if (!copySourceMap || (sourceMapContent = library.getSourceMapContent()) == null) {
            return;
        }
        FilesKt.writeText$default(new File(file.getParent(), file.getName() + ".map"), sourceMapContent, (Charset) null, 2, (Object) null);
    }

    private final File correspondingSourceMapFile(File file) {
        return new File(file.getParentFile(), file.getName() + ".map");
    }

    private final String getSuggestedPath(String path) {
        String systemIndependentName = FileUtil.toSystemIndependentName(path);
        systemIndependentName.getClass();
        if (!StringsKt.startsWith$default(systemIndependentName, LibraryUtils.INSTANCE.getMETA_INF(), false, 2, (Object) null)) {
            return path;
        }
        String str = META_INF_RESOURCES;
        if (StringsKt.startsWith$default(systemIndependentName, str, false, 2, (Object) null)) {
            return path.substring(str.length());
        }
        return null;
    }

    @JvmStatic
    public static final boolean isKotlinJavascriptIrLibrary(File candidate) {
        candidate.getClass();
        JsLibraryUtils jsLibraryUtils = INSTANCE;
        if (jsLibraryUtils.isZippedKlib(candidate)) {
            return true;
        }
        if (FileUtil.isJarOrZip(candidate)) {
            return jsLibraryUtils.isZippedKlibInZip(candidate);
        }
        return FilesKt.resolve(candidate, KlibConstants.KLIB_DEFAULT_COMPONENT_NAME).isDirectory() && FilesKt.resolve(FilesKt.resolve(candidate, KlibConstants.KLIB_DEFAULT_COMPONENT_NAME), KlibConstants.KLIB_MANIFEST_FILE_NAME).isFile() && FilesKt.resolve(FilesKt.resolve(candidate, KlibConstants.KLIB_DEFAULT_COMPONENT_NAME), KlibIrConstants.KLIB_IR_FOLDER_NAME).isDirectory();
    }

    private final boolean isZippedKlib(File candidate) {
        return Intrinsics.areEqual(FilesKt.getExtension(candidate), "klib");
    }

    private final boolean isZippedKlibInZip(File candidate) {
        ZipFile zipFile = new ZipFile(candidate);
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            enumerationEntries.getClass();
            Iterator it = CollectionsKt.iterator(enumerationEntries);
            boolean z = false;
            boolean z2 = false;
            while (it.hasNext()) {
                ZipEntry zipEntry = (ZipEntry) it.next();
                if (Intrinsics.areEqual(zipEntry.getName(), "default/manifest")) {
                    z = true;
                }
                if (Intrinsics.areEqual(zipEntry.getName(), "default/ir/")) {
                    z2 = true;
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipFile, (Throwable) null);
            return z && z2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipFile, th);
                throw th2;
            }
        }
    }

    private final void processDirectory(final File dir, final Function1<? super JsLibrary, Unit> action) {
        FileUtil.processFilesRecursively(dir, new Processor() { // from class: kt7
            public final boolean process(Object obj) {
                return JsLibraryUtils.c(dir, action, (File) obj);
            }
        });
    }

    private final void runIfFileExists(File file, String str, Function1<? super JsLibrary, Unit> function1) {
        if (file.isFile()) {
            function1.invoke(new JsLibrary(FilesKt.readText$default(file, (Charset) null, 1, (Object) null), str, contentIfExists(correspondingSourceMapFile(file)), file));
        }
    }

    private final void traverseArchive(File file, Function1<? super JsLibrary, Unit> action) throws IOException {
        try {
            ZipFile zipFile = new ZipFile(file.getPath());
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                ArrayList<JsLibrary> arrayList = new ArrayList();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (!zipEntryNextElement.isDirectory()) {
                        name.getClass();
                        if (StringsKt.endsWith$default(name, ".js", false, 2, (Object) null)) {
                            String suggestedPath = getSuggestedPath(name);
                            if (suggestedPath != null) {
                                InputStream inputStream = zipFile.getInputStream(zipEntryNextElement);
                                inputStream.getClass();
                                arrayList.add(new JsLibrary(TextStreamsKt.readText(new InputStreamReader(inputStream, Charsets.UTF_8)), suggestedPath, null, null));
                            }
                        } else if (StringsKt.endsWith$default(name, ".js.map", false, 2, (Object) null)) {
                            linkedHashMap.put(StringsKt.removeSuffix(name, ".js.map") + ".js", zipEntryNextElement);
                        }
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                for (JsLibrary jsLibraryCopy$default : arrayList) {
                    ZipEntry zipEntry = (ZipEntry) linkedHashMap.get(jsLibraryCopy$default.getPath());
                    if (zipEntry != null) {
                        InputStream inputStream2 = zipFile.getInputStream(zipEntry);
                        inputStream2.getClass();
                        jsLibraryCopy$default = JsLibrary.copy$default(jsLibraryCopy$default, null, null, TextStreamsKt.readText(new InputStreamReader(inputStream2, Charsets.UTF_8)), null, 11, null);
                    }
                    arrayList2.add(jsLibraryCopy$default);
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    action.invoke(it.next());
                }
            } catch (IOException e) {
                LOG.error("Could not extract files from archive " + file.getName() + ": " + e.getMessage());
            } finally {
                zipFile.close();
            }
        } catch (ZipException e2) {
            cia.a("Failed to open zip file: ", file, e2);
        }
    }

    private final void traverseDirectory(File dir, Function1<? super JsLibrary, Unit> action) {
        try {
            processDirectory(dir, action);
        } catch (IOException e) {
            LOG.error("Could not read files from directory " + dir.getName() + ": " + e.getMessage());
        }
    }

    @JvmStatic
    public static final void traverseJsLibrary(File lib, Function1<? super JsLibrary, Unit> action) throws IOException {
        lib.getClass();
        action.getClass();
        if (lib.isDirectory()) {
            INSTANCE.traverseDirectory(lib, action);
            return;
        }
        if (FileUtil.isJarOrZip(lib)) {
            INSTANCE.traverseArchive(lib, action);
            return;
        }
        String name = lib.getName();
        name.getClass();
        if (StringsKt.endsWith$default(name, ".js", false, 2, (Object) null)) {
            JsLibraryUtils jsLibraryUtils = INSTANCE;
            String path = lib.getPath();
            path.getClass();
            jsLibraryUtils.runIfFileExists(lib, path, action);
            File fileWithReplacedExtensionOrNull = FileUtilsKt.withReplacedExtensionOrNull(lib, ".meta.js", ".js");
            if (fileWithReplacedExtensionOrNull != null) {
                String path2 = fileWithReplacedExtensionOrNull.getPath();
                path2.getClass();
                jsLibraryUtils.runIfFileExists(fileWithReplacedExtensionOrNull, path2, action);
            }
        }
    }
}
