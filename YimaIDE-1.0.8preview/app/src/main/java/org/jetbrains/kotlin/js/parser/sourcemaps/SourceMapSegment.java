package org.jetbrains.kotlin.js.parser.sourcemaps;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003JI\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/js/parser/sourcemaps/SourceMapSegment;", "", "generatedColumnNumber", "", "sourceFileName", "", "sourceLineNumber", "sourceColumnNumber", "name", "isIgnored", "", "<init>", "(ILjava/lang/String;IILjava/lang/String;Z)V", "getGeneratedColumnNumber", "()I", "getSourceFileName", "()Ljava/lang/String;", "getSourceLineNumber", "getSourceColumnNumber", "getName", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SourceMapSegment {
    private final int generatedColumnNumber;
    private final boolean isIgnored;
    private final String name;
    private final int sourceColumnNumber;
    private final String sourceFileName;
    private final int sourceLineNumber;

    public SourceMapSegment(int i, String str, int i2, int i3, String str2, boolean z) {
        this.generatedColumnNumber = i;
        this.sourceFileName = str;
        this.sourceLineNumber = i2;
        this.sourceColumnNumber = i3;
        this.name = str2;
        this.isIgnored = z;
    }

    public static /* synthetic */ SourceMapSegment copy$default(SourceMapSegment sourceMapSegment, int i, String str, int i2, int i3, String str2, boolean z, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = sourceMapSegment.generatedColumnNumber;
        }
        if ((i4 & 2) != 0) {
            str = sourceMapSegment.sourceFileName;
        }
        if ((i4 & 4) != 0) {
            i2 = sourceMapSegment.sourceLineNumber;
        }
        if ((i4 & 8) != 0) {
            i3 = sourceMapSegment.sourceColumnNumber;
        }
        if ((i4 & 16) != 0) {
            str2 = sourceMapSegment.name;
        }
        if ((i4 & 32) != 0) {
            z = sourceMapSegment.isIgnored;
        }
        String str3 = str2;
        boolean z2 = z;
        return sourceMapSegment.copy(i, str, i2, i3, str3, z2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getGeneratedColumnNumber() {
        return this.generatedColumnNumber;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSourceFileName() {
        return this.sourceFileName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getSourceLineNumber() {
        return this.sourceLineNumber;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getSourceColumnNumber() {
        return this.sourceColumnNumber;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsIgnored() {
        return this.isIgnored;
    }

    public final SourceMapSegment copy(int generatedColumnNumber, String sourceFileName, int sourceLineNumber, int sourceColumnNumber, String name, boolean isIgnored) {
        return new SourceMapSegment(generatedColumnNumber, sourceFileName, sourceLineNumber, sourceColumnNumber, name, isIgnored);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SourceMapSegment)) {
            return false;
        }
        SourceMapSegment sourceMapSegment = (SourceMapSegment) other;
        return this.generatedColumnNumber == sourceMapSegment.generatedColumnNumber && Intrinsics.areEqual(this.sourceFileName, sourceMapSegment.sourceFileName) && this.sourceLineNumber == sourceMapSegment.sourceLineNumber && this.sourceColumnNumber == sourceMapSegment.sourceColumnNumber && Intrinsics.areEqual(this.name, sourceMapSegment.name) && this.isIgnored == sourceMapSegment.isIgnored;
    }

    public final int getGeneratedColumnNumber() {
        return this.generatedColumnNumber;
    }

    public final String getName() {
        return this.name;
    }

    public final int getSourceColumnNumber() {
        return this.sourceColumnNumber;
    }

    public final String getSourceFileName() {
        return this.sourceFileName;
    }

    public final int getSourceLineNumber() {
        return this.sourceLineNumber;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.generatedColumnNumber) * 31;
        String str = this.sourceFileName;
        int iHashCode2 = (((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.sourceLineNumber)) * 31) + Integer.hashCode(this.sourceColumnNumber)) * 31;
        String str2 = this.name;
        return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Boolean.hashCode(this.isIgnored);
    }

    public final boolean isIgnored() {
        return this.isIgnored;
    }

    public String toString() {
        return "SourceMapSegment(generatedColumnNumber=" + this.generatedColumnNumber + ", sourceFileName=" + this.sourceFileName + ", sourceLineNumber=" + this.sourceLineNumber + ", sourceColumnNumber=" + this.sourceColumnNumber + ", name=" + this.name + ", isIgnored=" + this.isIgnored + ')';
    }
}
