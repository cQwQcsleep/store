package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.SourceInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nJ\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0007H\u0002J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;", Argument.Delimiters.none, "sourceInfo", "Lorg/jetbrains/kotlin/codegen/SourceInfo;", "<init>", "(Lorg/jetbrains/kotlin/codegen/SourceInfo;)V", ModuleXmlParser.NAME, Argument.Delimiters.none, "original", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "(Ljava/lang/String;Lorg/jetbrains/kotlin/codegen/inline/SMAP;)V", "getSourceInfo", "()Lorg/jetbrains/kotlin/codegen/SourceInfo;", "maxUsedValue", Argument.Delimiters.none, "fileMappings", "Ljava/util/LinkedHashMap;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", "resultMappings", Argument.Delimiters.none, "getResultMappings", "()Ljava/util/List;", "isTrivial", Argument.Delimiters.none, "()Z", "getOrRegisterNewSource", ModuleXmlParser.PATH, "mapLineNumber", "inlineSource", "Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "inlineCallSite", "mapSyntheticLineNumber", "id", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceMapper {
    public static final String FAKE_FILE_NAME = "fake.kt";
    public static final String FAKE_PATH = "kotlin/jvm/internal/FakeKt";
    public static final int LOCAL_VARIABLE_INLINE_ARGUMENT_SYNTHETIC_LINE_NUMBER = 1;
    private LinkedHashMap<Pair<String, String>, FileMapping> fileMappings;
    private int maxUsedValue;
    private final SourceInfo sourceInfo;

    public SourceMapper(SourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
        this.maxUsedValue = sourceInfo != null ? sourceInfo.getLinesInFile() : 0;
        this.fileMappings = new LinkedHashMap<>();
        if (sourceInfo != null) {
            String sourceFileName = sourceInfo.getSourceFileName();
            FileMapping.mapNewInterval$default(getOrRegisterNewSource(sourceFileName == null ? FAKE_FILE_NAME : sourceFileName, sourceInfo.getPathOrCleanFQN()), 1, 1, sourceInfo.getLinesInFile(), null, 8, null);
        }
    }

    private final FileMapping getOrRegisterNewSource(String name, String path) {
        LinkedHashMap<Pair<String, String>, FileMapping> linkedHashMap = this.fileMappings;
        Pair<String, String> pair = TuplesKt.to(name, path);
        FileMapping fileMapping = linkedHashMap.get(pair);
        if (fileMapping == null) {
            fileMapping = new FileMapping(name, path);
            linkedHashMap.put(pair, fileMapping);
        }
        return fileMapping;
    }

    public final List<FileMapping> getResultMappings() {
        Collection<FileMapping> collectionValues = this.fileMappings.values();
        collectionValues.getClass();
        return CollectionsKt.toList(collectionValues);
    }

    public final SourceInfo getSourceInfo() {
        return this.sourceInfo;
    }

    public final boolean isTrivial() {
        int i = this.maxUsedValue;
        if (i == 0) {
            return true;
        }
        SourceInfo sourceInfo = this.sourceInfo;
        return sourceInfo != null && i == sourceInfo.getLinesInFile();
    }

    public final int mapLineNumber(SourcePosition inlineSource, SourcePosition inlineCallSite) {
        inlineSource.getClass();
        int iMapNewLineNumber = getOrRegisterNewSource(inlineSource.getFile(), inlineSource.getPath()).mapNewLineNumber(inlineSource.getLine(), this.maxUsedValue, inlineCallSite);
        this.maxUsedValue = Math.max(this.maxUsedValue, iMapNewLineNumber);
        return iMapNewLineNumber;
    }

    public final int mapSyntheticLineNumber(int id) {
        return mapLineNumber(new SourcePosition(id, FAKE_FILE_NAME, FAKE_PATH), null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SourceMapper(String str, SMAP smap) {
        Object next;
        smap.getClass();
        Iterator<T> it = smap.getFileMappings().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((FileMapping) next).getName(), str));
        FileMapping fileMapping = (FileMapping) next;
        this(fileMapping != null ? fileMapping.toSourceInfo() : null);
    }
}
