package org.jetbrains.kotlin.codegen.inline;

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/SourceMapCopier;", Argument.Delimiters.none, "parent", "Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;", "smap", "Lorg/jetbrains/kotlin/codegen/inline/SMAP;", "callSite", "Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;Lorg/jetbrains/kotlin/codegen/inline/SMAP;Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;)V", "getParent", "()Lorg/jetbrains/kotlin/codegen/inline/SourceMapper;", "getCallSite", "()Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "visitedLines", "Lit/unimi/dsi/fastutil/ints/Int2IntOpenHashMap;", "lastVisitedRange", "Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", "mapLineNumber", Argument.Delimiters.none, "lineNumber", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceMapCopier {
    private final SourcePosition callSite;
    private RangeMapping lastVisitedRange;
    private final SourceMapper parent;
    private final SMAP smap;
    private final Int2IntOpenHashMap visitedLines;

    public SourceMapCopier(SourceMapper sourceMapper, SMAP smap, SourcePosition sourcePosition) {
        sourceMapper.getClass();
        smap.getClass();
        this.parent = sourceMapper;
        this.smap = smap;
        this.callSite = sourcePosition;
        this.visitedLines = new Int2IntOpenHashMap();
    }

    public final SourcePosition getCallSite() {
        return this.callSite;
    }

    public final SourceMapper getParent() {
        return this.parent;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0017  */
    /* JADX WARN: Code duplicated, block: B:14:0x001f A[RETURN] */
    public final int mapLineNumber(int lineNumber) {
        int i = this.visitedLines.get(lineNumber);
        if (i > 0) {
            return i;
        }
        RangeMapping rangeMappingFindRange = this.lastVisitedRange;
        if (rangeMappingFindRange == null) {
            rangeMappingFindRange = this.smap.findRange(lineNumber);
            if (rangeMappingFindRange == null) {
                return -1;
            }
        } else {
            if (!rangeMappingFindRange.contains(lineNumber)) {
                rangeMappingFindRange = null;
            }
            if (rangeMappingFindRange == null) {
                rangeMappingFindRange = this.smap.findRange(lineNumber);
                if (rangeMappingFindRange == null) {
                    return -1;
                }
            }
        }
        this.lastVisitedRange = rangeMappingFindRange;
        SourceMapper sourceMapper = this.parent;
        SourcePosition sourcePositionMapDestToSource = rangeMappingFindRange.mapDestToSource(lineNumber);
        SourcePosition callSite = this.callSite;
        if (callSite == null) {
            callSite = rangeMappingFindRange.getCallSite();
        }
        int iMapLineNumber = sourceMapper.mapLineNumber(sourcePositionMapDestToSource, callSite);
        this.visitedLines.put(lineNumber, iMapLineNumber);
        return iMapLineNumber;
    }

    public /* synthetic */ SourceMapCopier(SourceMapper sourceMapper, SMAP smap, SourcePosition sourcePosition, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sourceMapper, smap, (i & 4) != 0 ? null : sourcePosition);
    }
}
