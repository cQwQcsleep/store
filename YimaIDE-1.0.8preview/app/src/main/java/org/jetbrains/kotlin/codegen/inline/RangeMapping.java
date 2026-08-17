package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0086\u0002J\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J=\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\r\"\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/RangeMapping;", Argument.Delimiters.none, "source", Argument.Delimiters.none, "dest", "range", "callSite", "Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "parent", "Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", "<init>", "(IIILorg/jetbrains/kotlin/codegen/inline/SourcePosition;Lorg/jetbrains/kotlin/codegen/inline/FileMapping;)V", "getSource", "()I", "getDest", "getRange", "setRange", "(I)V", "getCallSite", "()Lorg/jetbrains/kotlin/codegen/inline/SourcePosition;", "getParent", "()Lorg/jetbrains/kotlin/codegen/inline/FileMapping;", "contains", Argument.Delimiters.none, "destLine", "hasMappingForSource", "sourceLine", "mapDestToSource", "mapSourceToDest", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class RangeMapping {
    private final SourcePosition callSite;
    private final int dest;
    private final FileMapping parent;
    private int range;
    private final int source;

    public RangeMapping(int i, int i2, int i3, SourcePosition sourcePosition, FileMapping fileMapping) {
        fileMapping.getClass();
        this.source = i;
        this.dest = i2;
        this.range = i3;
        this.callSite = sourcePosition;
        this.parent = fileMapping;
    }

    public static /* synthetic */ RangeMapping copy$default(RangeMapping rangeMapping, int i, int i2, int i3, SourcePosition sourcePosition, FileMapping fileMapping, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = rangeMapping.source;
        }
        if ((i4 & 2) != 0) {
            i2 = rangeMapping.dest;
        }
        if ((i4 & 4) != 0) {
            i3 = rangeMapping.range;
        }
        if ((i4 & 8) != 0) {
            sourcePosition = rangeMapping.callSite;
        }
        if ((i4 & 16) != 0) {
            fileMapping = rangeMapping.parent;
        }
        FileMapping fileMapping2 = fileMapping;
        int i5 = i3;
        return rangeMapping.copy(i, i2, i5, sourcePosition, fileMapping2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getDest() {
        return this.dest;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getRange() {
        return this.range;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SourcePosition getCallSite() {
        return this.callSite;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final FileMapping getParent() {
        return this.parent;
    }

    public final boolean contains(int destLine) {
        int i = this.dest;
        return i <= destLine && destLine < i + this.range;
    }

    public final RangeMapping copy(int source, int dest, int range, SourcePosition callSite, FileMapping parent) {
        parent.getClass();
        return new RangeMapping(source, dest, range, callSite, parent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RangeMapping)) {
            return false;
        }
        RangeMapping rangeMapping = (RangeMapping) other;
        return this.source == rangeMapping.source && this.dest == rangeMapping.dest && this.range == rangeMapping.range && Intrinsics.areEqual(this.callSite, rangeMapping.callSite) && Intrinsics.areEqual(this.parent, rangeMapping.parent);
    }

    public final SourcePosition getCallSite() {
        return this.callSite;
    }

    public final int getDest() {
        return this.dest;
    }

    public final FileMapping getParent() {
        return this.parent;
    }

    public final int getRange() {
        return this.range;
    }

    public final int getSource() {
        return this.source;
    }

    public final boolean hasMappingForSource(int sourceLine) {
        int i = this.source;
        return i <= sourceLine && sourceLine < i + this.range;
    }

    public int hashCode() {
        int iHashCode = ((((Integer.hashCode(this.source) * 31) + Integer.hashCode(this.dest)) * 31) + Integer.hashCode(this.range)) * 31;
        SourcePosition sourcePosition = this.callSite;
        return ((iHashCode + (sourcePosition == null ? 0 : sourcePosition.hashCode())) * 31) + this.parent.hashCode();
    }

    public final SourcePosition mapDestToSource(int destLine) {
        return new SourcePosition(this.source + (destLine - this.dest), this.parent.getName(), this.parent.getPath());
    }

    public final int mapSourceToDest(int sourceLine) {
        return this.dest + (sourceLine - this.source);
    }

    public final void setRange(int i) {
        this.range = i;
    }

    public String toString() {
        return "RangeMapping(source=" + this.source + ", dest=" + this.dest + ", range=" + this.range + ", callSite=" + this.callSite + ", parent=" + this.parent + ')';
    }
}
