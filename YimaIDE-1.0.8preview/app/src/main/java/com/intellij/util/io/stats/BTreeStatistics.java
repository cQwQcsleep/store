package com.intellij.util.io.stats;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\rHÆ\u0003Jm\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\t\u0010+\u001a\u00020,HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006-"}, d2 = {"Lcom/intellij/util/io/stats/BTreeStatistics;", "", "pages", "", "elements", "height", "moves", "leafPages", "maxSearchStepsInRequest", "searchRequests", "searchSteps", "pageCapacity", "sizeInBytes", "", "<init>", "(IIIIIIIIIJ)V", "getPages", "()I", "getElements", "getHeight", "getMoves", "getLeafPages", "getMaxSearchStepsInRequest", "getSearchRequests", "getSearchSteps", "getPageCapacity", "getSizeInBytes", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class BTreeStatistics {
    private final int elements;
    private final int height;
    private final int leafPages;
    private final int maxSearchStepsInRequest;
    private final int moves;
    private final int pageCapacity;
    private final int pages;
    private final int searchRequests;
    private final int searchSteps;
    private final long sizeInBytes;

    public BTreeStatistics(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j) {
        this.pages = i;
        this.elements = i2;
        this.height = i3;
        this.moves = i4;
        this.leafPages = i5;
        this.maxSearchStepsInRequest = i6;
        this.searchRequests = i7;
        this.searchSteps = i8;
        this.pageCapacity = i9;
        this.sizeInBytes = j;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BTreeStatistics)) {
            return false;
        }
        BTreeStatistics bTreeStatistics = (BTreeStatistics) other;
        return this.pages == bTreeStatistics.pages && this.elements == bTreeStatistics.elements && this.height == bTreeStatistics.height && this.moves == bTreeStatistics.moves && this.leafPages == bTreeStatistics.leafPages && this.maxSearchStepsInRequest == bTreeStatistics.maxSearchStepsInRequest && this.searchRequests == bTreeStatistics.searchRequests && this.searchSteps == bTreeStatistics.searchSteps && this.pageCapacity == bTreeStatistics.pageCapacity && this.sizeInBytes == bTreeStatistics.sizeInBytes;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.pages) * 31) + Integer.hashCode(this.elements)) * 31) + Integer.hashCode(this.height)) * 31) + Integer.hashCode(this.moves)) * 31) + Integer.hashCode(this.leafPages)) * 31) + Integer.hashCode(this.maxSearchStepsInRequest)) * 31) + Integer.hashCode(this.searchRequests)) * 31) + Integer.hashCode(this.searchSteps)) * 31) + Integer.hashCode(this.pageCapacity)) * 31) + Long.hashCode(this.sizeInBytes);
    }

    public String toString() {
        return "BTreeStatistics(pages=" + this.pages + ", elements=" + this.elements + ", height=" + this.height + ", moves=" + this.moves + ", leafPages=" + this.leafPages + ", maxSearchStepsInRequest=" + this.maxSearchStepsInRequest + ", searchRequests=" + this.searchRequests + ", searchSteps=" + this.searchSteps + ", pageCapacity=" + this.pageCapacity + ", sizeInBytes=" + this.sizeInBytes + ')';
    }
}
