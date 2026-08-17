package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ0\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineScopeInfo;", Argument.Delimiters.none, "scopeNumber", Argument.Delimiters.none, "callSiteLineNumber", "surroundingScopeNumber", "<init>", "(ILjava/lang/Integer;Ljava/lang/Integer;)V", "getScopeNumber", "()I", "getCallSiteLineNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSurroundingScopeNumber", "component1", "component2", "component3", "copy", "(ILjava/lang/Integer;Ljava/lang/Integer;)Lorg/jetbrains/kotlin/codegen/inline/InlineScopeInfo;", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class InlineScopeInfo {
    private final Integer callSiteLineNumber;
    private final int scopeNumber;
    private final Integer surroundingScopeNumber;

    public InlineScopeInfo(int i, Integer num, Integer num2) {
        this.scopeNumber = i;
        this.callSiteLineNumber = num;
        this.surroundingScopeNumber = num2;
    }

    public static /* synthetic */ InlineScopeInfo copy$default(InlineScopeInfo inlineScopeInfo, int i, Integer num, Integer num2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = inlineScopeInfo.scopeNumber;
        }
        if ((i2 & 2) != 0) {
            num = inlineScopeInfo.callSiteLineNumber;
        }
        if ((i2 & 4) != 0) {
            num2 = inlineScopeInfo.surroundingScopeNumber;
        }
        return inlineScopeInfo.copy(i, num, num2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getScopeNumber() {
        return this.scopeNumber;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getCallSiteLineNumber() {
        return this.callSiteLineNumber;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getSurroundingScopeNumber() {
        return this.surroundingScopeNumber;
    }

    public final InlineScopeInfo copy(int scopeNumber, Integer callSiteLineNumber, Integer surroundingScopeNumber) {
        return new InlineScopeInfo(scopeNumber, callSiteLineNumber, surroundingScopeNumber);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InlineScopeInfo)) {
            return false;
        }
        InlineScopeInfo inlineScopeInfo = (InlineScopeInfo) other;
        return this.scopeNumber == inlineScopeInfo.scopeNumber && Intrinsics.areEqual(this.callSiteLineNumber, inlineScopeInfo.callSiteLineNumber) && Intrinsics.areEqual(this.surroundingScopeNumber, inlineScopeInfo.surroundingScopeNumber);
    }

    public final Integer getCallSiteLineNumber() {
        return this.callSiteLineNumber;
    }

    public final int getScopeNumber() {
        return this.scopeNumber;
    }

    public final Integer getSurroundingScopeNumber() {
        return this.surroundingScopeNumber;
    }

    public int hashCode() {
        int iHashCode = Integer.hashCode(this.scopeNumber) * 31;
        Integer num = this.callSiteLineNumber;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.surroundingScopeNumber;
        return iHashCode2 + (num2 != null ? num2.hashCode() : 0);
    }

    public String toString() {
        return "InlineScopeInfo(scopeNumber=" + this.scopeNumber + ", callSiteLineNumber=" + this.callSiteLineNumber + ", surroundingScopeNumber=" + this.surroundingScopeNumber + ')';
    }
}
