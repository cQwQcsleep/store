package androidx.compose.compiler.plugins.kotlin;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.LanguageVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bJ\r\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004¢\u0006\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0088\u0001\u0002Ê\u0001\u0002\b\u001c¨\u0006\u001b"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/ComposeMetadata;", "", "data", "", "constructor-impl", "([B)[B", "version", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "(Lorg/jetbrains/kotlin/config/LanguageVersion;)[B", "getData", "()[B", "supportsOpenFunctionsWithDefaultParams", "", "supportsOpenFunctionsWithDefaultParams-impl", "([B)Z", "equals", "other", "equals-impl", "([BLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "([B)I", "toString", "", "toString-impl", "([B)Ljava/lang/String;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class ComposeMetadata {
    private final byte[] data;

    private /* synthetic */ ComposeMetadata(byte[] bArr) {
        this.data = bArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ComposeMetadata m266boximpl(byte[] bArr) {
        return new ComposeMetadata(bArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m267constructorimpl(LanguageVersion languageVersion) {
        languageVersion.getClass();
        return m268constructorimpl(new byte[]{(byte) languageVersion.getMajor(), (byte) languageVersion.getMinor()});
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m269equalsimpl(byte[] bArr, Object obj) {
        return (obj instanceof ComposeMetadata) && Intrinsics.areEqual(bArr, ((ComposeMetadata) obj).m274unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m270equalsimpl0(byte[] bArr, byte[] bArr2) {
        return Intrinsics.areEqual(bArr, bArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m271hashCodeimpl(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    /* JADX INFO: renamed from: supportsOpenFunctionsWithDefaultParams-impl, reason: not valid java name */
    public static final boolean m272supportsOpenFunctionsWithDefaultParamsimpl(byte[] bArr) {
        return bArr[0] >= 2 && bArr[1] >= 1;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m273toStringimpl(byte[] bArr) {
        return "ComposeMetadata(data=" + Arrays.toString(bArr) + ')';
    }

    public boolean equals(Object obj) {
        return m269equalsimpl(this.data, obj);
    }

    public final byte[] getData() {
        return this.data;
    }

    public int hashCode() {
        return m271hashCodeimpl(this.data);
    }

    public String toString() {
        return m273toStringimpl(this.data);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte[] m274unboximpl() {
        return this.data;
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m268constructorimpl(byte[] bArr) {
        bArr.getClass();
        return bArr;
    }
}
