package androidx.savedstate;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b\u0001\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020 ¢\u0006\u0004\b!\u0010\"J\u001d\u0010#\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020$¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020(¢\u0006\u0004\b)\u0010*J\u0015\u0010+\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b,\u0010-J'\u0010.\u001a\u00020\b\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H/¢\u0006\u0004\b1\u00102J'\u00103\u001a\u00020\b\"\b\b\u0000\u0010/*\u0002042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H/¢\u0006\u0004\b5\u00106J\u001d\u00107\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u000208¢\u0006\u0004\b9\u0010:J\u001d\u0010;\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020<¢\u0006\u0004\b=\u0010>J\u001d\u0010?\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b@\u0010AJ#\u0010B\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020$0C¢\u0006\u0004\bD\u0010EJ#\u0010F\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00180C¢\u0006\u0004\bG\u0010EJ'\u0010H\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040C¢\u0006\u0004\bI\u0010EJ#\u0010J\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0C¢\u0006\u0004\bK\u0010EJ-\u0010L\u001a\u00020\b\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H/0C¢\u0006\u0004\bM\u0010EJ\u001d\u0010N\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020O¢\u0006\u0004\bP\u0010QJ\u001d\u0010R\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020S¢\u0006\u0004\bT\u0010UJ#\u0010V\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00180W¢\u0006\u0004\bX\u0010YJ\u001d\u0010Z\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020[¢\u0006\u0004\b\\\u0010]J\u001d\u0010^\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020_¢\u0006\u0004\b`\u0010aJ\u001d\u0010b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020c¢\u0006\u0004\bd\u0010eJ\u001d\u0010f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020g¢\u0006\u0004\bh\u0010iJ'\u0010j\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040W¢\u0006\u0004\bk\u0010lJ#\u0010m\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0W¢\u0006\u0004\bn\u0010oJ-\u0010p\u001a\u00020\b\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H/0W¢\u0006\u0004\bq\u0010rJ-\u0010s\u001a\u00020\b\"\b\b\u0000\u0010/*\u0002002\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H/0t¢\u0006\u0004\bu\u0010vJ!\u0010w\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\bx\u0010yJ\u0019\u0010z\u001a\u00020\b2\n\u0010{\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b|\u0010}J\u0015\u0010~\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u007f\u0010-J\u0010\u0010\u0080\u0001\u001a\u00020\b¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001e\u0010\u0083\u0001\u001a\u00020\u00102\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u0013\u0010\u0087\u0001\u001a\u00020$HÖ\u0001¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J\u0013\u0010\u008a\u0001\u001a\u00020\nHÖ\u0001¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004¨\u0006\u008d\u0001"}, d2 = {"Landroidx/savedstate/SavedStateWriter;", "", "source", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "constructor-impl", "(Landroid/os/Bundle;)Landroid/os/Bundle;", "putBinder", "", "key", "", "value", "Landroid/os/IBinder;", "putBinder-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/os/IBinder;)V", "putBoolean", "", "putBoolean-impl", "(Landroid/os/Bundle;Ljava/lang/String;Z)V", "putChar", "", "putChar-impl", "(Landroid/os/Bundle;Ljava/lang/String;C)V", "putCharSequence", "", "putCharSequence-impl", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/CharSequence;)V", "putDouble", "", "putDouble-impl", "(Landroid/os/Bundle;Ljava/lang/String;D)V", "putFloat", "", "putFloat-impl", "(Landroid/os/Bundle;Ljava/lang/String;F)V", "putInt", "", "putInt-impl", "(Landroid/os/Bundle;Ljava/lang/String;I)V", "putLong", "", "putLong-impl", "(Landroid/os/Bundle;Ljava/lang/String;J)V", "putNull", "putNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)V", "putParcelable", "T", "Landroid/os/Parcelable;", "putParcelable-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/os/Parcelable;)V", "putJavaSerializable", "Ljava/io/Serializable;", "putJavaSerializable-impl", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/io/Serializable;)V", "putSize", "Landroid/util/Size;", "putSize-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/util/Size;)V", "putSizeF", "Landroid/util/SizeF;", "putSizeF-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/util/SizeF;)V", "putString", "putString-impl", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)V", "putIntList", "", "putIntList-impl", "(Landroid/os/Bundle;Ljava/lang/String;Ljava/util/List;)V", "putCharSequenceList", "putCharSequenceList-impl", "putSavedStateList", "putSavedStateList-impl", "putStringList", "putStringList-impl", "putParcelableList", "putParcelableList-impl", "putBooleanArray", "", "putBooleanArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[Z)V", "putCharArray", "", "putCharArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[C)V", "putCharSequenceArray", "", "putCharSequenceArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[Ljava/lang/CharSequence;)V", "putDoubleArray", "", "putDoubleArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[D)V", "putFloatArray", "", "putFloatArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[F)V", "putIntArray", "", "putIntArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[I)V", "putLongArray", "", "putLongArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[J)V", "putSavedStateArray", "putSavedStateArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[Landroid/os/Bundle;)V", "putStringArray", "putStringArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[Ljava/lang/String;)V", "putParcelableArray", "putParcelableArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;[Landroid/os/Parcelable;)V", "putSparseParcelableArray", "Landroid/util/SparseArray;", "putSparseParcelableArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/util/SparseArray;)V", "putSavedState", "putSavedState-impl", "(Landroid/os/Bundle;Ljava/lang/String;Landroid/os/Bundle;)V", "putAll", "from", "putAll-impl", "(Landroid/os/Bundle;Landroid/os/Bundle;)V", "remove", "remove-impl", "clear", "clear-impl", "(Landroid/os/Bundle;)V", "equals", "other", "equals-impl", "(Landroid/os/Bundle;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "(Landroid/os/Bundle;)I", "toString", "toString-impl", "(Landroid/os/Bundle;)Ljava/lang/String;", "savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class SavedStateWriter {
    private final Bundle source;

    private /* synthetic */ SavedStateWriter(Bundle bundle) {
        this.source = bundle;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SavedStateWriter m6394boximpl(Bundle bundle) {
        return new SavedStateWriter(bundle);
    }

    /* JADX INFO: renamed from: clear-impl, reason: not valid java name */
    public static final void m6395clearimpl(Bundle bundle) {
        bundle.clear();
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static Bundle m6396constructorimpl(Bundle bundle) {
        bundle.getClass();
        return bundle;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6397equalsimpl(Bundle bundle, Object obj) {
        return (obj instanceof SavedStateWriter) && Intrinsics.areEqual(bundle, ((SavedStateWriter) obj).getSource());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6398equalsimpl0(Bundle bundle, Bundle bundle2) {
        return Intrinsics.areEqual(bundle, bundle2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6399hashCodeimpl(Bundle bundle) {
        return bundle.hashCode();
    }

    /* JADX INFO: renamed from: putAll-impl, reason: not valid java name */
    public static final void m6400putAllimpl(Bundle bundle, Bundle bundle2) {
        bundle2.getClass();
        bundle.putAll(bundle2);
    }

    /* JADX INFO: renamed from: putBinder-impl, reason: not valid java name */
    public static final void m6401putBinderimpl(Bundle bundle, String str, IBinder iBinder) {
        str.getClass();
        iBinder.getClass();
        bundle.putBinder(str, iBinder);
    }

    /* JADX INFO: renamed from: putBoolean-impl, reason: not valid java name */
    public static final void m6402putBooleanimpl(Bundle bundle, String str, boolean z) {
        str.getClass();
        bundle.putBoolean(str, z);
    }

    /* JADX INFO: renamed from: putBooleanArray-impl, reason: not valid java name */
    public static final void m6403putBooleanArrayimpl(Bundle bundle, String str, boolean[] zArr) {
        str.getClass();
        zArr.getClass();
        bundle.putBooleanArray(str, zArr);
    }

    /* JADX INFO: renamed from: putChar-impl, reason: not valid java name */
    public static final void m6404putCharimpl(Bundle bundle, String str, char c) {
        str.getClass();
        bundle.putChar(str, c);
    }

    /* JADX INFO: renamed from: putCharArray-impl, reason: not valid java name */
    public static final void m6405putCharArrayimpl(Bundle bundle, String str, char[] cArr) {
        str.getClass();
        cArr.getClass();
        bundle.putCharArray(str, cArr);
    }

    /* JADX INFO: renamed from: putCharSequence-impl, reason: not valid java name */
    public static final void m6406putCharSequenceimpl(Bundle bundle, String str, CharSequence charSequence) {
        str.getClass();
        charSequence.getClass();
        bundle.putCharSequence(str, charSequence);
    }

    /* JADX INFO: renamed from: putCharSequenceArray-impl, reason: not valid java name */
    public static final void m6407putCharSequenceArrayimpl(Bundle bundle, String str, CharSequence[] charSequenceArr) {
        str.getClass();
        charSequenceArr.getClass();
        bundle.putCharSequenceArray(str, charSequenceArr);
    }

    /* JADX INFO: renamed from: putCharSequenceList-impl, reason: not valid java name */
    public static final void m6408putCharSequenceListimpl(Bundle bundle, String str, List<? extends CharSequence> list) {
        str.getClass();
        list.getClass();
        bundle.putCharSequenceArrayList(str, SavedStateWriterKt.toArrayListUnsafe(list));
    }

    /* JADX INFO: renamed from: putDouble-impl, reason: not valid java name */
    public static final void m6409putDoubleimpl(Bundle bundle, String str, double d) {
        str.getClass();
        bundle.putDouble(str, d);
    }

    /* JADX INFO: renamed from: putDoubleArray-impl, reason: not valid java name */
    public static final void m6410putDoubleArrayimpl(Bundle bundle, String str, double[] dArr) {
        str.getClass();
        dArr.getClass();
        bundle.putDoubleArray(str, dArr);
    }

    /* JADX INFO: renamed from: putFloat-impl, reason: not valid java name */
    public static final void m6411putFloatimpl(Bundle bundle, String str, float f) {
        str.getClass();
        bundle.putFloat(str, f);
    }

    /* JADX INFO: renamed from: putFloatArray-impl, reason: not valid java name */
    public static final void m6412putFloatArrayimpl(Bundle bundle, String str, float[] fArr) {
        str.getClass();
        fArr.getClass();
        bundle.putFloatArray(str, fArr);
    }

    /* JADX INFO: renamed from: putInt-impl, reason: not valid java name */
    public static final void m6413putIntimpl(Bundle bundle, String str, int i) {
        str.getClass();
        bundle.putInt(str, i);
    }

    /* JADX INFO: renamed from: putIntArray-impl, reason: not valid java name */
    public static final void m6414putIntArrayimpl(Bundle bundle, String str, int[] iArr) {
        str.getClass();
        iArr.getClass();
        bundle.putIntArray(str, iArr);
    }

    /* JADX INFO: renamed from: putIntList-impl, reason: not valid java name */
    public static final void m6415putIntListimpl(Bundle bundle, String str, List<Integer> list) {
        str.getClass();
        list.getClass();
        bundle.putIntegerArrayList(str, SavedStateWriterKt.toArrayListUnsafe(list));
    }

    /* JADX INFO: renamed from: putJavaSerializable-impl, reason: not valid java name */
    public static final <T extends Serializable> void m6416putJavaSerializableimpl(Bundle bundle, String str, T t) {
        str.getClass();
        t.getClass();
        bundle.putSerializable(str, t);
    }

    /* JADX INFO: renamed from: putLong-impl, reason: not valid java name */
    public static final void m6417putLongimpl(Bundle bundle, String str, long j) {
        str.getClass();
        bundle.putLong(str, j);
    }

    /* JADX INFO: renamed from: putLongArray-impl, reason: not valid java name */
    public static final void m6418putLongArrayimpl(Bundle bundle, String str, long[] jArr) {
        str.getClass();
        jArr.getClass();
        bundle.putLongArray(str, jArr);
    }

    /* JADX INFO: renamed from: putNull-impl, reason: not valid java name */
    public static final void m6419putNullimpl(Bundle bundle, String str) {
        str.getClass();
        bundle.putString(str, null);
    }

    /* JADX INFO: renamed from: putParcelable-impl, reason: not valid java name */
    public static final <T extends Parcelable> void m6420putParcelableimpl(Bundle bundle, String str, T t) {
        str.getClass();
        t.getClass();
        bundle.putParcelable(str, t);
    }

    /* JADX INFO: renamed from: putParcelableArray-impl, reason: not valid java name */
    public static final <T extends Parcelable> void m6421putParcelableArrayimpl(Bundle bundle, String str, T[] tArr) {
        str.getClass();
        tArr.getClass();
        bundle.putParcelableArray(str, tArr);
    }

    /* JADX INFO: renamed from: putParcelableList-impl, reason: not valid java name */
    public static final <T extends Parcelable> void m6422putParcelableListimpl(Bundle bundle, String str, List<? extends T> list) {
        str.getClass();
        list.getClass();
        bundle.putParcelableArrayList(str, SavedStateWriterKt.toArrayListUnsafe(list));
    }

    /* JADX INFO: renamed from: putSavedState-impl, reason: not valid java name */
    public static final void m6423putSavedStateimpl(Bundle bundle, String str, Bundle bundle2) {
        str.getClass();
        bundle2.getClass();
        bundle.putBundle(str, bundle2);
    }

    /* JADX INFO: renamed from: putSavedStateArray-impl, reason: not valid java name */
    public static final void m6424putSavedStateArrayimpl(Bundle bundle, String str, Bundle[] bundleArr) {
        str.getClass();
        bundleArr.getClass();
        m6421putParcelableArrayimpl(bundle, str, bundleArr);
    }

    /* JADX INFO: renamed from: putSavedStateList-impl, reason: not valid java name */
    public static final void m6425putSavedStateListimpl(Bundle bundle, String str, List<Bundle> list) {
        str.getClass();
        list.getClass();
        m6422putParcelableListimpl(bundle, str, list);
    }

    /* JADX INFO: renamed from: putSize-impl, reason: not valid java name */
    public static final void m6426putSizeimpl(Bundle bundle, String str, Size size) {
        str.getClass();
        size.getClass();
        bundle.putSize(str, size);
    }

    /* JADX INFO: renamed from: putSizeF-impl, reason: not valid java name */
    public static final void m6427putSizeFimpl(Bundle bundle, String str, SizeF sizeF) {
        str.getClass();
        sizeF.getClass();
        bundle.putSizeF(str, sizeF);
    }

    /* JADX INFO: renamed from: putSparseParcelableArray-impl, reason: not valid java name */
    public static final <T extends Parcelable> void m6428putSparseParcelableArrayimpl(Bundle bundle, String str, SparseArray<T> sparseArray) {
        str.getClass();
        sparseArray.getClass();
        bundle.putSparseParcelableArray(str, sparseArray);
    }

    /* JADX INFO: renamed from: putString-impl, reason: not valid java name */
    public static final void m6429putStringimpl(Bundle bundle, String str, String str2) {
        str.getClass();
        str2.getClass();
        bundle.putString(str, str2);
    }

    /* JADX INFO: renamed from: putStringArray-impl, reason: not valid java name */
    public static final void m6430putStringArrayimpl(Bundle bundle, String str, String[] strArr) {
        str.getClass();
        strArr.getClass();
        bundle.putStringArray(str, strArr);
    }

    /* JADX INFO: renamed from: putStringList-impl, reason: not valid java name */
    public static final void m6431putStringListimpl(Bundle bundle, String str, List<String> list) {
        str.getClass();
        list.getClass();
        bundle.putStringArrayList(str, SavedStateWriterKt.toArrayListUnsafe(list));
    }

    /* JADX INFO: renamed from: remove-impl, reason: not valid java name */
    public static final void m6432removeimpl(Bundle bundle, String str) {
        str.getClass();
        bundle.remove(str);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6433toStringimpl(Bundle bundle) {
        return "SavedStateWriter(source=" + bundle + ')';
    }

    public boolean equals(Object obj) {
        return m6397equalsimpl(this.source, obj);
    }

    public int hashCode() {
        return m6399hashCodeimpl(this.source);
    }

    public String toString() {
        return m6433toStringimpl(this.source);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Bundle getSource() {
        return this.source;
    }
}
