package org.jetbrains.kotlin.library.abi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.konan.library.NativeLibraryConstantsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003JI\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010'\u001a\u00020(HÖ\u0081\u0004J\n\u0010)\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rRL\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058FX\u0087\u0004r*\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u001c\b\u001a\u0012\u0018\b\u000bB\u0014\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u0004\u0012\u0006\b\u001d\u0012\u0002\b\f¢\u0006\f\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u000fÊ\u0001\u0002\b+¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/LibraryManifest;", "", NativeLibraryConstantsKt.KONAN_DISTRIBUTION_PLATFORM_LIBS_DIR, "", "platformTargets", "", "Lorg/jetbrains/kotlin/library/abi/LibraryTarget;", "compilerVersion", "abiVersion", "irProviderName", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPlatform", "()Ljava/lang/String;", "getPlatformTargets", "()Ljava/util/List;", "getCompilerVersion", "getAbiVersion", "getIrProviderName", "nativeTargets", "getNativeTargets$annotations", "()V", "getNativeTargets", "Lkotlin/Deprecated;", "message", "Use platformTargets instead", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "imports", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public final /* data */ class LibraryManifest {
    private final String abiVersion;
    private final String compilerVersion;
    private final String irProviderName;
    private final String platform;
    private final List<LibraryTarget> platformTargets;

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryManifest(String str, List<? extends LibraryTarget> list, String str2, String str3, String str4) {
        list.getClass();
        this.platform = str;
        this.platformTargets = list;
        this.compilerVersion = str2;
        this.abiVersion = str3;
        this.irProviderName = str4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LibraryManifest copy$default(LibraryManifest libraryManifest, String str, List list, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = libraryManifest.platform;
        }
        if ((i & 2) != 0) {
            list = libraryManifest.platformTargets;
        }
        if ((i & 4) != 0) {
            str2 = libraryManifest.compilerVersion;
        }
        if ((i & 8) != 0) {
            str3 = libraryManifest.abiVersion;
        }
        if ((i & 16) != 0) {
            str4 = libraryManifest.irProviderName;
        }
        String str5 = str4;
        String str6 = str2;
        return libraryManifest.copy(str, list, str6, str3, str5);
    }

    @Deprecated(message = "Use platformTargets instead", replaceWith = @ReplaceWith(expression = "platformTargets", imports = {}))
    public static /* synthetic */ void getNativeTargets$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPlatform() {
        return this.platform;
    }

    public final List<LibraryTarget> component2() {
        return this.platformTargets;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCompilerVersion() {
        return this.compilerVersion;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAbiVersion() {
        return this.abiVersion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIrProviderName() {
        return this.irProviderName;
    }

    public final LibraryManifest copy(String platform, List<? extends LibraryTarget> platformTargets, String compilerVersion, String abiVersion, String irProviderName) {
        platformTargets.getClass();
        return new LibraryManifest(platform, platformTargets, compilerVersion, abiVersion, irProviderName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryManifest)) {
            return false;
        }
        LibraryManifest libraryManifest = (LibraryManifest) other;
        return Intrinsics.areEqual(this.platform, libraryManifest.platform) && Intrinsics.areEqual(this.platformTargets, libraryManifest.platformTargets) && Intrinsics.areEqual(this.compilerVersion, libraryManifest.compilerVersion) && Intrinsics.areEqual(this.abiVersion, libraryManifest.abiVersion) && Intrinsics.areEqual(this.irProviderName, libraryManifest.irProviderName);
    }

    public final String getAbiVersion() {
        return this.abiVersion;
    }

    public final String getCompilerVersion() {
        return this.compilerVersion;
    }

    public final String getIrProviderName() {
        return this.irProviderName;
    }

    public final List<String> getNativeTargets() {
        List<LibraryTarget> list = this.platformTargets;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof LibraryTarget.Native) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((LibraryTarget.Native) it.next()).getName());
        }
        return arrayList2;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final List<LibraryTarget> getPlatformTargets() {
        return this.platformTargets;
    }

    public int hashCode() {
        String str = this.platform;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.platformTargets.hashCode()) * 31;
        String str2 = this.compilerVersion;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.abiVersion;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.irProviderName;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "LibraryManifest(platform=" + this.platform + ", platformTargets=" + this.platformTargets + ", compilerVersion=" + this.compilerVersion + ", abiVersion=" + this.abiVersion + ", irProviderName=" + this.irProviderName + ')';
    }
}
