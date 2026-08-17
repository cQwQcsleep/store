package org.jetbrains.kotlin.js.sourceMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0004J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0004H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0004H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0011j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/js/sourceMap/SourceFilePathResolver;", "", "sourceRoots", "", "Ljava/io/File;", "outputDir", "includeUnavailableSourcesIntoSourceMap", "", "<init>", "(Ljava/util/List;Ljava/io/File;Z)V", "", "outputDirPathResolver", "Lorg/jetbrains/kotlin/js/sourceMap/RelativePathCalculator;", "cache", "", "", "filesInDirExistCache", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getPathRelativeToSourceRoots", "file", "getPathRelativeToSourceRootsIfExists", "calculatePathRelativeToSourceRoots", "calculatePathRelativeToOutput", "Companion", "org.jetbrains.kotlin:js.sourcemap"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SourceFilePathResolver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<File, String> cache;
    private final HashMap<File, Boolean> filesInDirExistCache;
    private final boolean includeUnavailableSourcesIntoSourceMap;
    private final RelativePathCalculator outputDirPathResolver;
    private final Set<File> sourceRoots;

    public SourceFilePathResolver(List<? extends File> list, File file, boolean z) {
        list.getClass();
        this.includeUnavailableSourcesIntoSourceMap = z;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((File) it.next()).getAbsoluteFile());
        }
        this.sourceRoots = linkedHashSet;
        this.outputDirPathResolver = file != null ? new RelativePathCalculator(file) : null;
        this.cache = new LinkedHashMap();
        this.filesInDirExistCache = new HashMap<>();
    }

    private final String calculatePathRelativeToOutput(File file) {
        RelativePathCalculator relativePathCalculator = this.outputDirPathResolver;
        if (relativePathCalculator != null) {
            return relativePathCalculator.calculateRelativePathTo(file);
        }
        return null;
    }

    private final String calculatePathRelativeToSourceRoots(File file) throws IOException {
        String strCalculatePathRelativeToOutput = calculatePathRelativeToOutput(file);
        if (strCalculatePathRelativeToOutput != null) {
            return strCalculatePathRelativeToOutput;
        }
        ArrayList arrayList = new ArrayList();
        File absoluteFile = file.getAbsoluteFile();
        absoluteFile.getClass();
        for (File fileNormalize = FilesKt.normalize(absoluteFile); fileNormalize != null; fileNormalize = fileNormalize.getParentFile()) {
            if (this.sourceRoots.contains(fileNormalize)) {
                if (arrayList.isEmpty()) {
                    break;
                }
                CollectionsKt.reverse(arrayList);
                String str = File.separator;
                str.getClass();
                return CollectionsKt.joinToString$default(arrayList, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            }
            String name = fileNormalize.getName();
            name.getClass();
            arrayList.add(name);
        }
        String name2 = file.getName();
        name2.getClass();
        return name2;
    }

    @JvmStatic
    public static final SourceFilePathResolver create(List<String> list, String str, File file, boolean z) {
        return INSTANCE.create(list, str, file, z);
    }

    public final String getPathRelativeToSourceRoots(File file) throws IOException {
        file.getClass();
        String str = this.cache.get(file);
        if (str != null) {
            return str;
        }
        String strCalculatePathRelativeToSourceRoots = calculatePathRelativeToSourceRoots(file);
        this.cache.put(file, strCalculatePathRelativeToSourceRoots);
        return strCalculatePathRelativeToSourceRoots;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002c  */
    public final String getPathRelativeToSourceRootsIfExists(File file) throws IOException {
        boolean z;
        file.getClass();
        if (this.includeUnavailableSourcesIntoSourceMap) {
            z = true;
        } else {
            HashMap<File, Boolean> map = this.filesInDirExistCache;
            File parentFile = file.getParentFile();
            if (parentFile == null) {
                parentFile = file;
            }
            Boolean boolValueOf = map.get(parentFile);
            if (boolValueOf == null) {
                boolValueOf = Boolean.valueOf(file.exists());
                map.put(parentFile, boolValueOf);
            }
            if (boolValueOf.booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            return getPathRelativeToSourceRoots(file);
        }
        return null;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007b\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/js/sourceMap/SourceFilePathResolver$Companion;", "", "<init>", "()V", "create", "Lorg/jetbrains/kotlin/js/sourceMap/SourceFilePathResolver;", "sourceRoots", "", "", "sourceMapPrefix", "outputDir", "Ljava/io/File;", "includeUnavailableSourcesIntoSourceMap", "", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:js.sourcemap"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ SourceFilePathResolver create$default(Companion companion, List list, String str, File file, boolean z, int i, Object obj) {
            if ((i & 8) != 0) {
                z = false;
            }
            return companion.create(list, str, file, z);
        }

        @JvmStatic
        public final SourceFilePathResolver create(List<String> sourceRoots, String sourceMapPrefix, File outputDir, boolean includeUnavailableSourcesIntoSourceMap) {
            sourceRoots.getClass();
            sourceMapPrefix.getClass();
            boolean z = sourceMapPrefix.length() == 0 && sourceRoots.isEmpty();
            List<String> list = sourceRoots;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new File((String) it.next()));
            }
            if (!z) {
                outputDir = null;
            }
            return new SourceFilePathResolver(arrayList, outputDir, includeUnavailableSourcesIntoSourceMap);
        }

        private Companion() {
        }
    }

    public /* synthetic */ SourceFilePathResolver(List list, File file, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : file, (i & 4) != 0 ? false : z);
    }
}
