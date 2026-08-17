package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.SourceInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017J&\u0010\u0018\u001a\u00020\u0019*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u0017H\u0002J*\u0010\u001d\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", Argument.Delimiters.none, ModuleXmlParser.NAME, Argument.Delimiters.none, ModuleXmlParser.PATH, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getPath", "lineMappings", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", "Lkotlin/collections/ArrayList;", "getLineMappings", "()Ljava/util/ArrayList;", "toSourceInfo", "Lorg/jetbrains/kotlin/codegen/SourceInfo;", "mapNewLineNumber", Argument.Delimiters.none, "source", "currentIndex", "callSite", "Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "canReuseFor", Argument.Delimiters.none, "newSource", "globalMaxDest", "newCallSite", "mapNewInterval", "dest", "range", "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FileMapping {
    private final ArrayList<RangeMapping> lineMappings;
    private final String name;
    private final String path;

    public FileMapping(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.name = str;
        this.path = str2;
        this.lineMappings = new ArrayList<>();
    }

    private final boolean canReuseFor(RangeMapping rangeMapping, int i, int i2, SourcePosition sourcePosition) {
        if (Intrinsics.areEqual(rangeMapping.getCallSite(), sourcePosition)) {
            int range = rangeMapping.getRange() + (rangeMapping.contains(i2) ? 10 : 0);
            int source = i - rangeMapping.getSource();
            if (source >= 0 && source < range) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ RangeMapping mapNewInterval$default(FileMapping fileMapping, int i, int i2, int i3, SourcePosition sourcePosition, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            sourcePosition = null;
        }
        return fileMapping.mapNewInterval(i, i2, i3, sourcePosition);
    }

    public final ArrayList<RangeMapping> getLineMappings() {
        return this.lineMappings;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public final RangeMapping mapNewInterval(int source, int dest, int range, SourcePosition callSite) {
        RangeMapping rangeMapping = new RangeMapping(source, dest, range, callSite, this);
        this.lineMappings.add(rangeMapping);
        return rangeMapping;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0029  */
    /* JADX WARN: Code duplicated, block: B:16:0x002f  */
    /* JADX WARN: Code duplicated, block: B:9:0x0016  */
    public final int mapNewLineNumber(int source, int currentIndex, SourcePosition callSite) {
        RangeMapping rangeMapping;
        RangeMapping rangeMappingMapNewInterval = (RangeMapping) CollectionsKt.lastOrNull(this.lineMappings);
        RangeMapping rangeMapping2 = null;
        if (rangeMappingMapNewInterval == null) {
            rangeMapping = (RangeMapping) CollectionsKt.firstOrNull(this.lineMappings);
            if (rangeMapping != null && canReuseFor(rangeMapping, source, currentIndex, callSite)) {
                rangeMapping2 = rangeMapping;
            }
            if (rangeMapping2 == null) {
                rangeMappingMapNewInterval = mapNewInterval(source, currentIndex + 1, 1, callSite);
            } else {
                rangeMappingMapNewInterval = rangeMapping2;
            }
        } else {
            if (!canReuseFor(rangeMappingMapNewInterval, source, currentIndex, callSite)) {
                rangeMappingMapNewInterval = null;
            }
            if (rangeMappingMapNewInterval == null) {
                rangeMapping = (RangeMapping) CollectionsKt.firstOrNull(this.lineMappings);
                if (rangeMapping != null) {
                    rangeMapping2 = rangeMapping;
                }
                if (rangeMapping2 == null) {
                    rangeMappingMapNewInterval = mapNewInterval(source, currentIndex + 1, 1, callSite);
                } else {
                    rangeMappingMapNewInterval = rangeMapping2;
                }
            }
        }
        rangeMappingMapNewInterval.setRange(Math.max(rangeMappingMapNewInterval.getRange(), (source - rangeMappingMapNewInterval.getSource()) + 1));
        return rangeMappingMapNewInterval.mapSourceToDest(source);
    }

    public final SourceInfo toSourceInfo() {
        String str = this.name;
        String str2 = this.path;
        int iMax = 0;
        for (RangeMapping rangeMapping : this.lineMappings) {
            iMax = Math.max(iMax, (rangeMapping.getSource() + rangeMapping.getRange()) - 1);
        }
        return new SourceInfo(str, str2, iMax);
    }
}
