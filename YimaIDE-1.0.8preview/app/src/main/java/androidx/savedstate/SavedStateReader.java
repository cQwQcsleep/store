package androidx.savedstate;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.os.BundleCompat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0016\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0019\n\u0002\b\n\n\u0002\u0010\u0013\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010$\n\u0002\b\n\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b\u0001\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\"\u0010 J\u0015\u0010#\u001a\u00020$2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u0004\u0018\u00010$2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b(\u0010)J\u0015\u0010*\u001a\u00020+2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u0004\u0018\u00010+2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b/\u00100J\u0015\u00101\u001a\u0002022\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b3\u00104J\u0017\u00105\u001a\u0004\u0018\u0001022\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b6\u00107J\u0015\u00108\u001a\u0002092\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b:\u0010;J\u0017\u0010<\u001a\u0004\u0018\u0001092\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b=\u0010>J-\u0010?\u001a\u0002H@\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\bD\u0010EJ$\u0010?\u001a\u0002H@\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\bD\u0010FJ/\u0010G\u001a\u0004\u0018\u0001H@\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\bH\u0010EJ&\u0010G\u001a\u0004\u0018\u0001H@\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\bH\u0010FJ-\u0010I\u001a\u0002H@\"\b\b\u0000\u0010@*\u00020J2\u0006\u0010\t\u001a\u00020\n2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\bL\u0010MJ$\u0010I\u001a\u0002H@\"\n\b\u0000\u0010@\u0018\u0001*\u00020J2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\bL\u0010NJ/\u0010O\u001a\u0004\u0018\u0001H@\"\b\b\u0000\u0010@*\u00020J2\u0006\u0010\t\u001a\u00020\n2\f\u0010K\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\bP\u0010MJ&\u0010O\u001a\u0004\u0018\u0001H@\"\n\b\u0000\u0010@\u0018\u0001*\u00020J2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\bP\u0010NJ\u0015\u0010Q\u001a\u00020R2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bS\u0010TJ\u0017\u0010U\u001a\u0004\u0018\u00010R2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bV\u0010TJ\u0015\u0010W\u001a\u00020X2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bY\u0010ZJ\u0017\u0010[\u001a\u0004\u0018\u00010X2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\\\u0010ZJ\u001f\u0010]\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b_\u0010`J!\u0010a\u001a\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bb\u0010`J\u0015\u0010c\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bd\u0010eJ\u0017\u0010f\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bg\u0010eJ\u001b\u0010h\u001a\b\u0012\u0004\u0012\u0002020i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bj\u0010kJ\u001d\u0010l\u001a\n\u0012\u0004\u0012\u000202\u0018\u00010i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bm\u0010kJ\u001b\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001e0i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bo\u0010kJ\u001d\u0010p\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bq\u0010kJ\u001f\u0010r\u001a\f\u0012\b\u0012\u00060\u0003j\u0002`\u00040i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bs\u0010kJ!\u0010t\u001a\u000e\u0012\b\u0012\u00060\u0003j\u0002`\u0004\u0018\u00010i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bu\u0010kJ\u001b\u0010v\u001a\b\u0012\u0004\u0012\u00020\n0i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\bw\u0010kJ\u001d\u0010x\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010i2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\by\u0010kJ3\u0010z\u001a\b\u0012\u0004\u0012\u0002H@0i\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\b{\u0010|J*\u0010z\u001a\b\u0012\u0004\u0012\u0002H@0i\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\b{\u0010kJ5\u0010}\u001a\n\u0012\u0004\u0012\u0002H@\u0018\u00010i\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0004\b~\u0010|J,\u0010}\u001a\n\u0012\u0004\u0012\u0002H@\u0018\u00010i\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0004\b~\u0010kJ\u0018\u0010\u007f\u001a\u00030\u0080\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0080\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0084\u0001\u0010\u0082\u0001J\u0019\u0010\u0085\u0001\u001a\u00030\u0086\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\u001b\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u0086\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u008a\u0001\u0010\u0088\u0001J\u001e\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J \u0010\u008e\u0001\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u008f\u0001\u0010\u008d\u0001J\u0019\u0010\u0090\u0001\u001a\u00030\u0091\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u001b\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0091\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0095\u0001\u0010\u0093\u0001J\u0019\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001b\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0097\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u009b\u0001\u0010\u0099\u0001J\u0019\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J\u001b\u0010 \u0001\u001a\u0005\u0018\u00010\u009d\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b¡\u0001\u0010\u009f\u0001J\u0019\u0010¢\u0001\u001a\u00030£\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b¤\u0001\u0010¥\u0001J\u001b\u0010¦\u0001\u001a\u0005\u0018\u00010£\u00012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b§\u0001\u0010¥\u0001J\u001e\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020\n0^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b©\u0001\u0010ª\u0001J \u0010«\u0001\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010^2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b¬\u0001\u0010ª\u0001J6\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u0002H@0^\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0006\b®\u0001\u0010¯\u0001J-\u0010\u00ad\u0001\u001a\b\u0012\u0004\u0012\u0002H@0^\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0006\b®\u0001\u0010°\u0001J8\u0010±\u0001\u001a\n\u0012\u0004\u0012\u0002H@\u0018\u00010^\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0006\b²\u0001\u0010¯\u0001J/\u0010±\u0001\u001a\n\u0012\u0004\u0012\u0002H@\u0018\u00010^\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0006\b²\u0001\u0010°\u0001J7\u0010³\u0001\u001a\t\u0012\u0004\u0012\u0002H@0´\u0001\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0006\bµ\u0001\u0010¶\u0001J.\u0010³\u0001\u001a\t\u0012\u0004\u0012\u0002H@0´\u0001\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0006\bµ\u0001\u0010·\u0001J9\u0010¸\u0001\u001a\u000b\u0012\u0004\u0012\u0002H@\u0018\u00010´\u0001\"\b\b\u0000\u0010@*\u00020A2\u0006\u0010\t\u001a\u00020\n2\f\u0010B\u001a\b\u0012\u0004\u0012\u0002H@0C¢\u0006\u0006\b¹\u0001\u0010¶\u0001J0\u0010¸\u0001\u001a\u000b\u0012\u0004\u0012\u0002H@\u0018\u00010´\u0001\"\n\b\u0000\u0010@\u0018\u0001*\u00020A2\u0006\u0010\t\u001a\u00020\nH\u0086\b¢\u0006\u0006\b¹\u0001\u0010·\u0001J\u001c\u0010º\u0001\u001a\u00060\u0003j\u0002`\u00042\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b»\u0001\u0010¼\u0001J \u0010½\u0001\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00042\u0006\u0010\t\u001a\u00020\n¢\u0006\u0006\b¾\u0001\u0010¼\u0001J\u0010\u0010¿\u0001\u001a\u000202¢\u0006\u0006\bÀ\u0001\u0010Á\u0001J\u0010\u0010Â\u0001\u001a\u00020\u0010¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001J\u0017\u0010Å\u0001\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n¢\u0006\u0005\bÆ\u0001\u0010\u0012J\u001a\u0010Ç\u0001\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0086\u0002¢\u0006\u0005\bÈ\u0001\u0010\u0012J\u001d\u0010É\u0001\u001a\u00020\u00102\u000b\u0010Ê\u0001\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0006\bË\u0001\u0010Ì\u0001J\u0010\u0010Í\u0001\u001a\u000202¢\u0006\u0006\bÎ\u0001\u0010Á\u0001J\u0010\u0010Ï\u0001\u001a\u00020\n¢\u0006\u0006\bÐ\u0001\u0010Ñ\u0001J\u001f\u0010Ò\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010Ó\u0001¢\u0006\u0006\bÔ\u0001\u0010Õ\u0001J\u001e\u0010Ö\u0001\u001a\u00020\u00102\t\u0010Ê\u0001\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0006\b×\u0001\u0010Ø\u0001J\u0013\u0010Ù\u0001\u001a\u000202HÖ\u0001¢\u0006\u0006\bÚ\u0001\u0010Á\u0001J\u0013\u0010Û\u0001\u001a\u00020\nHÖ\u0001¢\u0006\u0006\bÜ\u0001\u0010Ñ\u0001R\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004¨\u0006Ý\u0001"}, d2 = {"Landroidx/savedstate/SavedStateReader;", "", "source", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "constructor-impl", "(Landroid/os/Bundle;)Landroid/os/Bundle;", "getBinder", "Landroid/os/IBinder;", "key", "", "getBinder-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/IBinder;", "getBinderOrNull", "getBinderOrNull-impl", "getBoolean", "", "getBoolean-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Z", "getBooleanOrNull", "getBooleanOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Boolean;", "getChar", "", "getChar-impl", "(Landroid/os/Bundle;Ljava/lang/String;)C", "getCharOrNull", "getCharOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Character;", "getCharSequence", "", "getCharSequence-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/CharSequence;", "getCharSequenceOrNull", "getCharSequenceOrNull-impl", "getDouble", "", "getDouble-impl", "(Landroid/os/Bundle;Ljava/lang/String;)D", "getDoubleOrNull", "getDoubleOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Double;", "getFloat", "", "getFloat-impl", "(Landroid/os/Bundle;Ljava/lang/String;)F", "getFloatOrNull", "getFloatOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Float;", "getInt", "", "getInt-impl", "(Landroid/os/Bundle;Ljava/lang/String;)I", "getIntOrNull", "getIntOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Integer;", "getLong", "", "getLong-impl", "(Landroid/os/Bundle;Ljava/lang/String;)J", "getLongOrNull", "getLongOrNull-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Long;", "getParcelable", "T", "Landroid/os/Parcelable;", "parcelableClass", "Lkotlin/reflect/KClass;", "getParcelable-impl", "(Landroid/os/Bundle;Ljava/lang/String;Lkotlin/reflect/KClass;)Landroid/os/Parcelable;", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/Parcelable;", "getParcelableOrNull", "getParcelableOrNull-impl", "getJavaSerializable", "Ljava/io/Serializable;", "serializableClass", "getJavaSerializable-impl", "(Landroid/os/Bundle;Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/io/Serializable;", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/io/Serializable;", "getJavaSerializableOrNull", "getJavaSerializableOrNull-impl", "getSize", "Landroid/util/Size;", "getSize-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/util/Size;", "getSizeOrNull", "getSizeOrNull-impl", "getSizeF", "Landroid/util/SizeF;", "getSizeF-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/util/SizeF;", "getSizeFOrNull", "getSizeFOrNull-impl", "getSavedStateArray", "", "getSavedStateArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[Landroid/os/Bundle;", "getSavedStateArrayOrNull", "getSavedStateArrayOrNull-impl", "getString", "getString-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/String;", "getStringOrNull", "getStringOrNull-impl", "getIntList", "", "getIntList-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/util/List;", "getIntListOrNull", "getIntListOrNull-impl", "getCharSequenceList", "getCharSequenceList-impl", "getCharSequenceListOrNull", "getCharSequenceListOrNull-impl", "getSavedStateList", "getSavedStateList-impl", "getSavedStateListOrNull", "getSavedStateListOrNull-impl", "getStringList", "getStringList-impl", "getStringListOrNull", "getStringListOrNull-impl", "getParcelableList", "getParcelableList-impl", "(Landroid/os/Bundle;Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/util/List;", "getParcelableListOrNull", "getParcelableListOrNull-impl", "getBooleanArray", "", "getBooleanArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[Z", "getBooleanArrayOrNull", "getBooleanArrayOrNull-impl", "getCharArray", "", "getCharArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[C", "getCharArrayOrNull", "getCharArrayOrNull-impl", "getCharSequenceArray", "getCharSequenceArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[Ljava/lang/CharSequence;", "getCharSequenceArrayOrNull", "getCharSequenceArrayOrNull-impl", "getDoubleArray", "", "getDoubleArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[D", "getDoubleArrayOrNull", "getDoubleArrayOrNull-impl", "getFloatArray", "", "getFloatArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[F", "getFloatArrayOrNull", "getFloatArrayOrNull-impl", "getIntArray", "", "getIntArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[I", "getIntArrayOrNull", "getIntArrayOrNull-impl", "getLongArray", "", "getLongArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[J", "getLongArrayOrNull", "getLongArrayOrNull-impl", "getStringArray", "getStringArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;)[Ljava/lang/String;", "getStringArrayOrNull", "getStringArrayOrNull-impl", "getParcelableArray", "getParcelableArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;Lkotlin/reflect/KClass;)[Landroid/os/Parcelable;", "(Landroid/os/Bundle;Ljava/lang/String;)[Landroid/os/Parcelable;", "getParcelableArrayOrNull", "getParcelableArrayOrNull-impl", "getSparseParcelableArray", "Landroid/util/SparseArray;", "getSparseParcelableArray-impl", "(Landroid/os/Bundle;Ljava/lang/String;Lkotlin/reflect/KClass;)Landroid/util/SparseArray;", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/util/SparseArray;", "getSparseParcelableArrayOrNull", "getSparseParcelableArrayOrNull-impl", "getSavedState", "getSavedState-impl", "(Landroid/os/Bundle;Ljava/lang/String;)Landroid/os/Bundle;", "getSavedStateOrNull", "getSavedStateOrNull-impl", "size", "size-impl", "(Landroid/os/Bundle;)I", "isEmpty", "isEmpty-impl", "(Landroid/os/Bundle;)Z", "isNull", "isNull-impl", "contains", "contains-impl", "contentDeepEquals", "other", "contentDeepEquals-impl", "(Landroid/os/Bundle;Landroid/os/Bundle;)Z", "contentDeepHashCode", "contentDeepHashCode-impl", "contentDeepToString", "contentDeepToString-impl", "(Landroid/os/Bundle;)Ljava/lang/String;", "toMap", "", "toMap-impl", "(Landroid/os/Bundle;)Ljava/util/Map;", "equals", "equals-impl", "(Landroid/os/Bundle;Ljava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "toString-impl", "savedstate_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class SavedStateReader {
    private final Bundle source;

    private /* synthetic */ SavedStateReader(Bundle bundle) {
        this.source = bundle;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ SavedStateReader m6309boximpl(Bundle bundle) {
        return new SavedStateReader(bundle);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static Bundle m6310constructorimpl(Bundle bundle) {
        bundle.getClass();
        return bundle;
    }

    /* JADX INFO: renamed from: contains-impl, reason: not valid java name */
    public static final boolean m6311containsimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.containsKey(str);
    }

    /* JADX INFO: renamed from: contentDeepEquals-impl, reason: not valid java name */
    public static final boolean m6312contentDeepEqualsimpl(Bundle bundle, Bundle bundle2) {
        bundle2.getClass();
        return SavedStateReaderKt__SavedStateReader_androidKt.access$contentDeepEquals(bundle, bundle2);
    }

    /* JADX INFO: renamed from: contentDeepHashCode-impl, reason: not valid java name */
    public static final int m6313contentDeepHashCodeimpl(Bundle bundle) {
        return SavedStateReaderKt__SavedStateReader_androidKt.access$contentDeepHashCode(bundle);
    }

    /* JADX INFO: renamed from: contentDeepToString-impl, reason: not valid java name */
    public static final String m6314contentDeepToStringimpl(Bundle bundle) {
        StringBuilder sb = new StringBuilder((RangesKt.coerceAtMost(bundle.size(), 429496729) * 5) + 2);
        SavedStateReaderKt__SavedStateReader_androidKt.access$contentDeepToString(bundle, sb, new ArrayList());
        return sb.toString();
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6315equalsimpl(Bundle bundle, Object obj) {
        return (obj instanceof SavedStateReader) && Intrinsics.areEqual(bundle, ((SavedStateReader) obj).getSource());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6316equalsimpl0(Bundle bundle, Bundle bundle2) {
        return Intrinsics.areEqual(bundle, bundle2);
    }

    /* JADX INFO: renamed from: getBinder-impl, reason: not valid java name */
    public static final IBinder m6317getBinderimpl(Bundle bundle, String str) {
        str.getClass();
        IBinder binder = bundle.getBinder(str);
        if (binder != null) {
            return binder;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getBinderOrNull-impl, reason: not valid java name */
    public static final IBinder m6318getBinderOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getBinder(str);
    }

    /* JADX INFO: renamed from: getBoolean-impl, reason: not valid java name */
    public static final boolean m6319getBooleanimpl(Bundle bundle, String str) {
        str.getClass();
        boolean z = bundle.getBoolean(str, false);
        if (z || !bundle.getBoolean(str, true)) {
            return z;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return false;
    }

    /* JADX INFO: renamed from: getBooleanArray-impl, reason: not valid java name */
    public static final boolean[] m6320getBooleanArrayimpl(Bundle bundle, String str) {
        str.getClass();
        boolean[] booleanArray = bundle.getBooleanArray(str);
        if (booleanArray != null) {
            return booleanArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getBooleanArrayOrNull-impl, reason: not valid java name */
    public static final boolean[] m6321getBooleanArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getBooleanArray(str);
    }

    /* JADX INFO: renamed from: getBooleanOrNull-impl, reason: not valid java name */
    public static final Boolean m6322getBooleanOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        boolean z = bundle.getBoolean(str, false);
        if (z || !bundle.getBoolean(str, true)) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    /* JADX INFO: renamed from: getChar-impl, reason: not valid java name */
    public static final char m6323getCharimpl(Bundle bundle, String str) {
        str.getClass();
        char c = bundle.getChar(str, (char) 0);
        if (c != 0 || bundle.getChar(str, (char) 65535) != 65535) {
            return c;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return (char) 0;
    }

    /* JADX INFO: renamed from: getCharArray-impl, reason: not valid java name */
    public static final char[] m6324getCharArrayimpl(Bundle bundle, String str) {
        str.getClass();
        char[] charArray = bundle.getCharArray(str);
        if (charArray != null) {
            return charArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getCharArrayOrNull-impl, reason: not valid java name */
    public static final char[] m6325getCharArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getCharArray(str);
    }

    /* JADX INFO: renamed from: getCharOrNull-impl, reason: not valid java name */
    public static final Character m6326getCharOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        char c = bundle.getChar(str, (char) 0);
        if (c == 0 && bundle.getChar(str, (char) 65535) == 65535) {
            return null;
        }
        return Character.valueOf(c);
    }

    /* JADX INFO: renamed from: getCharSequence-impl, reason: not valid java name */
    public static final CharSequence m6327getCharSequenceimpl(Bundle bundle, String str) {
        str.getClass();
        CharSequence charSequence = bundle.getCharSequence(str);
        if (charSequence != null) {
            return charSequence;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getCharSequenceArray-impl, reason: not valid java name */
    public static final CharSequence[] m6328getCharSequenceArrayimpl(Bundle bundle, String str) {
        str.getClass();
        CharSequence[] charSequenceArray = bundle.getCharSequenceArray(str);
        if (charSequenceArray != null) {
            return charSequenceArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getCharSequenceArrayOrNull-impl, reason: not valid java name */
    public static final CharSequence[] m6329getCharSequenceArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getCharSequenceArray(str);
    }

    /* JADX INFO: renamed from: getCharSequenceList-impl, reason: not valid java name */
    public static final List<CharSequence> m6330getCharSequenceListimpl(Bundle bundle, String str) {
        str.getClass();
        ArrayList<CharSequence> charSequenceArrayList = bundle.getCharSequenceArrayList(str);
        if (charSequenceArrayList != null) {
            return charSequenceArrayList;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getCharSequenceListOrNull-impl, reason: not valid java name */
    public static final List<CharSequence> m6331getCharSequenceListOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getCharSequenceArrayList(str);
    }

    /* JADX INFO: renamed from: getCharSequenceOrNull-impl, reason: not valid java name */
    public static final CharSequence m6332getCharSequenceOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getCharSequence(str);
    }

    /* JADX INFO: renamed from: getDouble-impl, reason: not valid java name */
    public static final double m6333getDoubleimpl(Bundle bundle, String str) {
        str.getClass();
        double d = bundle.getDouble(str, Double.MIN_VALUE);
        if (d != Double.MIN_VALUE || bundle.getDouble(str, Double.MAX_VALUE) != Double.MAX_VALUE) {
            return d;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return 0.0d;
    }

    /* JADX INFO: renamed from: getDoubleArray-impl, reason: not valid java name */
    public static final double[] m6334getDoubleArrayimpl(Bundle bundle, String str) {
        str.getClass();
        double[] doubleArray = bundle.getDoubleArray(str);
        if (doubleArray != null) {
            return doubleArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getDoubleArrayOrNull-impl, reason: not valid java name */
    public static final double[] m6335getDoubleArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getDoubleArray(str);
    }

    /* JADX INFO: renamed from: getDoubleOrNull-impl, reason: not valid java name */
    public static final Double m6336getDoubleOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        double d = bundle.getDouble(str, Double.MIN_VALUE);
        if (d == Double.MIN_VALUE && bundle.getDouble(str, Double.MAX_VALUE) == Double.MAX_VALUE) {
            return null;
        }
        return Double.valueOf(d);
    }

    /* JADX INFO: renamed from: getFloat-impl, reason: not valid java name */
    public static final float m6337getFloatimpl(Bundle bundle, String str) {
        str.getClass();
        float f = bundle.getFloat(str, Float.MIN_VALUE);
        if (f != Float.MIN_VALUE || bundle.getFloat(str, Float.MAX_VALUE) != Float.MAX_VALUE) {
            return f;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return 0.0f;
    }

    /* JADX INFO: renamed from: getFloatArray-impl, reason: not valid java name */
    public static final float[] m6338getFloatArrayimpl(Bundle bundle, String str) {
        str.getClass();
        float[] floatArray = bundle.getFloatArray(str);
        if (floatArray != null) {
            return floatArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getFloatArrayOrNull-impl, reason: not valid java name */
    public static final float[] m6339getFloatArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getFloatArray(str);
    }

    /* JADX INFO: renamed from: getFloatOrNull-impl, reason: not valid java name */
    public static final Float m6340getFloatOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        float f = bundle.getFloat(str, Float.MIN_VALUE);
        if (f == Float.MIN_VALUE && bundle.getFloat(str, Float.MAX_VALUE) == Float.MAX_VALUE) {
            return null;
        }
        return Float.valueOf(f);
    }

    /* JADX INFO: renamed from: getInt-impl, reason: not valid java name */
    public static final int m6341getIntimpl(Bundle bundle, String str) {
        str.getClass();
        int i = bundle.getInt(str, Integer.MIN_VALUE);
        if (i != Integer.MIN_VALUE || bundle.getInt(str, Integer.MAX_VALUE) != Integer.MAX_VALUE) {
            return i;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return 0;
    }

    /* JADX INFO: renamed from: getIntArray-impl, reason: not valid java name */
    public static final int[] m6342getIntArrayimpl(Bundle bundle, String str) {
        str.getClass();
        int[] intArray = bundle.getIntArray(str);
        if (intArray != null) {
            return intArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getIntArrayOrNull-impl, reason: not valid java name */
    public static final int[] m6343getIntArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getIntArray(str);
    }

    /* JADX INFO: renamed from: getIntList-impl, reason: not valid java name */
    public static final List<Integer> m6344getIntListimpl(Bundle bundle, String str) {
        str.getClass();
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getIntListOrNull-impl, reason: not valid java name */
    public static final List<Integer> m6345getIntListOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getIntegerArrayList(str);
    }

    /* JADX INFO: renamed from: getIntOrNull-impl, reason: not valid java name */
    public static final Integer m6346getIntOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        int i = bundle.getInt(str, Integer.MIN_VALUE);
        if (i == Integer.MIN_VALUE && bundle.getInt(str, Integer.MAX_VALUE) == Integer.MAX_VALUE) {
            return null;
        }
        return Integer.valueOf(i);
    }

    /* JADX INFO: renamed from: getJavaSerializable-impl, reason: not valid java name */
    public static final <T extends Serializable> T m6348getJavaSerializableimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        T t = (T) BundleCompat.getSerializable(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
        if (t != null) {
            return t;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getJavaSerializableOrNull-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Serializable> T m6349getJavaSerializableOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) m6350getJavaSerializableOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Serializable.class));
    }

    /* JADX INFO: renamed from: getLong-impl, reason: not valid java name */
    public static final long m6351getLongimpl(Bundle bundle, String str) {
        str.getClass();
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j != Long.MIN_VALUE || bundle.getLong(str, Long.MAX_VALUE) != Long.MAX_VALUE) {
            return j;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return 0L;
    }

    /* JADX INFO: renamed from: getLongArray-impl, reason: not valid java name */
    public static final long[] m6352getLongArrayimpl(Bundle bundle, String str) {
        str.getClass();
        long[] longArray = bundle.getLongArray(str);
        if (longArray != null) {
            return longArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getLongArrayOrNull-impl, reason: not valid java name */
    public static final long[] m6353getLongArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getLongArray(str);
    }

    /* JADX INFO: renamed from: getLongOrNull-impl, reason: not valid java name */
    public static final Long m6354getLongOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        long j = bundle.getLong(str, Long.MIN_VALUE);
        if (j == Long.MIN_VALUE && bundle.getLong(str, Long.MAX_VALUE) == Long.MAX_VALUE) {
            return null;
        }
        return Long.valueOf(j);
    }

    /* JADX INFO: renamed from: getParcelable-impl, reason: not valid java name */
    public static final <T extends Parcelable> T m6356getParcelableimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        T t = (T) BundleCompat.getParcelable(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
        if (t != null) {
            return t;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getParcelableArray-impl, reason: not valid java name */
    public static final <T extends Parcelable> T[] m6358getParcelableArrayimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        T[] tArr = (T[]) m6360getParcelableArrayOrNullimpl(bundle, str, kClass);
        if (tArr != null) {
            return tArr;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getParcelableArrayOrNull-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> T[] m6359getParcelableArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T[]) m6360getParcelableArrayOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getParcelableList-impl, reason: not valid java name */
    public static final <T extends Parcelable> List<T> m6362getParcelableListimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        ArrayList parcelableArrayList = BundleCompat.getParcelableArrayList(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
        if (parcelableArrayList != null) {
            return parcelableArrayList;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getParcelableListOrNull-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> List<T> m6363getParcelableListOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return m6364getParcelableListOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getParcelableOrNull-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> T m6365getParcelableOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) m6366getParcelableOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getSavedState-impl, reason: not valid java name */
    public static final Bundle m6367getSavedStateimpl(Bundle bundle, String str) {
        str.getClass();
        Bundle bundle2 = bundle.getBundle(str);
        if (bundle2 != null) {
            return bundle2;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getSavedStateArray-impl, reason: not valid java name */
    public static final Bundle[] m6368getSavedStateArrayimpl(Bundle bundle, String str) {
        str.getClass();
        return (Bundle[]) m6358getParcelableArrayimpl(bundle, str, Reflection.getOrCreateKotlinClass(Bundle.class));
    }

    /* JADX INFO: renamed from: getSavedStateArrayOrNull-impl, reason: not valid java name */
    public static final Bundle[] m6369getSavedStateArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return (Bundle[]) m6360getParcelableArrayOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Bundle.class));
    }

    /* JADX INFO: renamed from: getSavedStateList-impl, reason: not valid java name */
    public static final List<Bundle> m6370getSavedStateListimpl(Bundle bundle, String str) {
        str.getClass();
        return m6362getParcelableListimpl(bundle, str, Reflection.getOrCreateKotlinClass(Bundle.class));
    }

    /* JADX INFO: renamed from: getSavedStateListOrNull-impl, reason: not valid java name */
    public static final List<Bundle> m6371getSavedStateListOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return m6364getParcelableListOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Bundle.class));
    }

    /* JADX INFO: renamed from: getSavedStateOrNull-impl, reason: not valid java name */
    public static final Bundle m6372getSavedStateOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getBundle(str);
    }

    /* JADX INFO: renamed from: getSize-impl, reason: not valid java name */
    public static final Size m6373getSizeimpl(Bundle bundle, String str) {
        str.getClass();
        Size size = bundle.getSize(str);
        if (size != null) {
            return size;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getSizeF-impl, reason: not valid java name */
    public static final SizeF m6374getSizeFimpl(Bundle bundle, String str) {
        str.getClass();
        SizeF sizeF = bundle.getSizeF(str);
        if (sizeF != null) {
            return sizeF;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getSizeFOrNull-impl, reason: not valid java name */
    public static final SizeF m6375getSizeFOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getSizeF(str);
    }

    /* JADX INFO: renamed from: getSizeOrNull-impl, reason: not valid java name */
    public static final Size m6376getSizeOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getSize(str);
    }

    /* JADX INFO: renamed from: getSparseParcelableArray-impl, reason: not valid java name */
    public static final <T extends Parcelable> SparseArray<T> m6378getSparseParcelableArrayimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        SparseArray<T> sparseArrayM6380getSparseParcelableArrayOrNullimpl = m6380getSparseParcelableArrayOrNullimpl(bundle, str, kClass);
        if (sparseArrayM6380getSparseParcelableArrayOrNullimpl != null) {
            return sparseArrayM6380getSparseParcelableArrayOrNullimpl;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getSparseParcelableArrayOrNull-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> SparseArray<T> m6379getSparseParcelableArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return m6380getSparseParcelableArrayOrNullimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getString-impl, reason: not valid java name */
    public static final String m6381getStringimpl(Bundle bundle, String str) {
        str.getClass();
        String string = bundle.getString(str);
        if (string != null) {
            return string;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getStringArray-impl, reason: not valid java name */
    public static final String[] m6382getStringArrayimpl(Bundle bundle, String str) {
        str.getClass();
        String[] stringArray = bundle.getStringArray(str);
        if (stringArray != null) {
            return stringArray;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getStringArrayOrNull-impl, reason: not valid java name */
    public static final String[] m6383getStringArrayOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getStringArray(str);
    }

    /* JADX INFO: renamed from: getStringList-impl, reason: not valid java name */
    public static final List<String> m6384getStringListimpl(Bundle bundle, String str) {
        str.getClass();
        ArrayList<String> stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList != null) {
            return stringArrayList;
        }
        SavedStateReaderKt.keyOrValueNotFoundError(str);
        wq6.a();
        return null;
    }

    /* JADX INFO: renamed from: getStringListOrNull-impl, reason: not valid java name */
    public static final List<String> m6385getStringListOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getStringArrayList(str);
    }

    /* JADX INFO: renamed from: getStringOrNull-impl, reason: not valid java name */
    public static final String m6386getStringOrNullimpl(Bundle bundle, String str) {
        str.getClass();
        return bundle.getString(str);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6387hashCodeimpl(Bundle bundle) {
        return bundle.hashCode();
    }

    /* JADX INFO: renamed from: isEmpty-impl, reason: not valid java name */
    public static final boolean m6388isEmptyimpl(Bundle bundle) {
        return bundle.isEmpty();
    }

    /* JADX INFO: renamed from: isNull-impl, reason: not valid java name */
    public static final boolean m6389isNullimpl(Bundle bundle, String str) {
        str.getClass();
        return m6311containsimpl(bundle, str) && bundle.get(str) == null;
    }

    /* JADX INFO: renamed from: size-impl, reason: not valid java name */
    public static final int m6390sizeimpl(Bundle bundle) {
        return bundle.size();
    }

    /* JADX INFO: renamed from: toMap-impl, reason: not valid java name */
    public static final Map<String, Object> m6391toMapimpl(Bundle bundle) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder(bundle.size());
        for (String str : bundle.keySet()) {
            str.getClass();
            mapCreateMapBuilder.put(str, bundle.get(str));
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6392toStringimpl(Bundle bundle) {
        return "SavedStateReader(source=" + bundle + ')';
    }

    public boolean equals(Object obj) {
        return m6315equalsimpl(this.source, obj);
    }

    public int hashCode() {
        return m6387hashCodeimpl(this.source);
    }

    public String toString() {
        return m6392toStringimpl(this.source);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Bundle getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: getJavaSerializableOrNull-impl, reason: not valid java name */
    public static final <T extends Serializable> T m6350getJavaSerializableOrNullimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        return (T) BundleCompat.getSerializable(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
    }

    /* JADX INFO: renamed from: getParcelableArrayOrNull-impl, reason: not valid java name */
    public static final <T extends Parcelable> T[] m6360getParcelableArrayOrNullimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        T[] tArr = (T[]) BundleCompat.getParcelableArray(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
        if (tArr != null) {
            return tArr;
        }
        return null;
    }

    /* JADX INFO: renamed from: getParcelableListOrNull-impl, reason: not valid java name */
    public static final <T extends Parcelable> List<T> m6364getParcelableListOrNullimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        return BundleCompat.getParcelableArrayList(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
    }

    /* JADX INFO: renamed from: getParcelableOrNull-impl, reason: not valid java name */
    public static final <T extends Parcelable> T m6366getParcelableOrNullimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        return (T) BundleCompat.getParcelable(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
    }

    /* JADX INFO: renamed from: getSparseParcelableArrayOrNull-impl, reason: not valid java name */
    public static final <T extends Parcelable> SparseArray<T> m6380getSparseParcelableArrayOrNullimpl(Bundle bundle, String str, KClass<T> kClass) {
        str.getClass();
        kClass.getClass();
        return BundleCompat.getSparseParcelableArray(bundle, str, JvmClassMappingKt.getJavaClass(kClass));
    }

    /* JADX INFO: renamed from: getParcelableArray-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> T[] m6357getParcelableArrayimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T[]) m6358getParcelableArrayimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getSparseParcelableArray-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> SparseArray<T> m6377getSparseParcelableArrayimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return m6378getSparseParcelableArrayimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getJavaSerializable-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Serializable> T m6347getJavaSerializableimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) m6348getJavaSerializableimpl(bundle, str, Reflection.getOrCreateKotlinClass(Serializable.class));
    }

    /* JADX INFO: renamed from: getParcelableList-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> List<T> m6361getParcelableListimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return m6362getParcelableListimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }

    /* JADX INFO: renamed from: getParcelable-impl, reason: not valid java name */
    public static final /* synthetic */ <T extends Parcelable> T m6355getParcelableimpl(Bundle bundle, String str) {
        str.getClass();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) m6356getParcelableimpl(bundle, str, Reflection.getOrCreateKotlinClass(Parcelable.class));
    }
}
