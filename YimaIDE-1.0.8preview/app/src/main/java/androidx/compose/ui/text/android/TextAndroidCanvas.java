package androidx.compose.ui.text.android;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.graphics.fonts.Font;
import android.graphics.text.MeasuredText;
import androidx.compose.ui.text.internal.InlineClassHelperKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.reandroid.archive.PathTree;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0017\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u0010H\u0086\bJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0017J\b\u0010\u0019\u001a\u00020\rH\u0017J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\u001cH\u0016J\b\u0010\"\u001a\u00020\u001cH\u0016J\b\u0010#\u001a\u00020\u001cH\u0016J$\u0010$\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u001cH\u0017J\u001c\u0010$\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J:\u0010$\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u001cH\u0017J2\u0010$\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\"\u0010.\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010%2\u0006\u0010/\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\u001cH\u0017J\u001a\u0010.\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010%2\u0006\u0010/\u001a\u00020\u001cH\u0016J8\u0010.\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010/\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\u001cH\u0017J0\u0010.\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010/\u001a\u00020\u001cH\u0016J\b\u00100\u001a\u00020\rH\u0016J\b\u00101\u001a\u00020\u001cH\u0016J\u0010\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\u001cH\u0016J\u0018\u00104\u001a\u00020\r2\u0006\u00105\u001a\u00020*2\u0006\u00106\u001a\u00020*H\u0016J\u0018\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020*H\u0016J\u0010\u0010:\u001a\u00020\r2\u0006\u0010;\u001a\u00020*H\u0016J\u0018\u0010<\u001a\u00020\r2\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020*H\u0016J\u0012\u0010=\u001a\u00020\r2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u0012\u0010@\u001a\u00020\r2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u0010\u0010A\u001a\u00020\r2\u0006\u0010B\u001a\u00020?H\u0017J\u0018\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020FH\u0017J\u0018\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020\u00142\u0006\u0010E\u001a\u00020FH\u0017J\u0010\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020%H\u0016J\u0010\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020\u0014H\u0016J0\u0010C\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010E\u001a\u00020FH\u0017J(\u0010C\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0016J(\u0010C\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0016J\u0010\u0010G\u001a\u00020\u00122\u0006\u0010D\u001a\u00020%H\u0017J\u0010\u0010G\u001a\u00020\u00122\u0006\u0010D\u001a\u00020\u0014H\u0017J(\u0010G\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0017J(\u0010G\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001c2\u0006\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\u001cH\u0017J\u0018\u0010H\u001a\u00020\u00122\u0006\u0010I\u001a\u00020J2\u0006\u0010E\u001a\u00020FH\u0017J\u0010\u0010H\u001a\u00020\u00122\u0006\u0010I\u001a\u00020JH\u0016J\u0010\u0010K\u001a\u00020\u00122\u0006\u0010I\u001a\u00020JH\u0017J\n\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0012\u0010N\u001a\u00020\r2\b\u0010O\u001a\u0004\u0018\u00010MH\u0016J\u0018\u0010P\u001a\u00020\u00122\u0006\u0010D\u001a\u00020%2\u0006\u0010Q\u001a\u00020RH\u0017J\u0010\u0010P\u001a\u00020\u00122\u0006\u0010D\u001a\u00020%H\u0017J\u0018\u0010P\u001a\u00020\u00122\u0006\u0010I\u001a\u00020J2\u0006\u0010Q\u001a\u00020RH\u0017J\u0010\u0010P\u001a\u00020\u00122\u0006\u0010I\u001a\u00020JH\u0017J0\u0010P\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010Q\u001a\u00020RH\u0017J(\u0010P\u001a\u00020\u00122\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0017J\u0010\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020UH\u0016J\u0018\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020%H\u0016J\u0018\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u0014H\u0016J0\u0010W\u001a\u00020\r2\u0006\u0010X\u001a\u00020%2\u0006\u0010Y\u001a\u00020*2\u0006\u0010Z\u001a\u00020*2\u0006\u0010[\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0016JH\u0010W\u001a\u00020\r2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010Y\u001a\u00020*2\u0006\u0010Z\u001a\u00020*2\u0006\u0010[\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0016J(\u0010\\\u001a\u00020\r2\u0006\u0010]\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020\u001c2\u0006\u0010`\u001a\u00020\u001cH\u0016J*\u0010a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J,\u0010a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010b\u001a\u0004\u0018\u00010\u00142\u0006\u0010V\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J,\u0010a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010b\u001a\u0004\u0018\u00010\u00142\u0006\u0010V\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010'H\u0016JR\u0010a\u001a\u00020\r2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u001c2\u0006\u0010f\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010i\u001a\u00020\u001c2\u0006\u0010j\u001a\u00020\u001c2\u0006\u0010k\u001a\u00020\u00122\b\u0010&\u001a\u0004\u0018\u00010'H\u0017JR\u0010a\u001a\u00020\r2\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020\u001c2\u0006\u0010f\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020\u001c2\u0006\u0010h\u001a\u00020\u001c2\u0006\u0010i\u001a\u00020\u001c2\u0006\u0010j\u001a\u00020\u001c2\u0006\u0010k\u001a\u00020\u00122\b\u0010&\u001a\u0004\u0018\u00010'H\u0017J\"\u0010a\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010>\u001a\u00020?2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016JL\u0010l\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010m\u001a\u00020\u001c2\u0006\u0010n\u001a\u00020\u001c2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020\u001c2\b\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010r\u001a\u00020\u001c2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J(\u0010s\u001a\u00020\r2\u0006\u0010t\u001a\u00020*2\u0006\u0010u\u001a\u00020*2\u0006\u0010v\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010w\u001a\u00020\r2\u0006\u0010x\u001a\u00020\u001cH\u0016J\u0010\u0010w\u001a\u00020\r2\u0006\u0010x\u001a\u00020yH\u0017J\u0018\u0010w\u001a\u00020\r2\u0006\u0010x\u001a\u00020\u001c2\u0006\u0010z\u001a\u00020{H\u0016J\u0018\u0010w\u001a\u00020\r2\u0006\u0010x\u001a\u00020\u001c2\u0006\u0010z\u001a\u00020|H\u0017J\u0018\u0010w\u001a\u00020\r2\u0006\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020|H\u0017J2\u0010}\u001a\u00020\r2\u0006\u0010~\u001a\u00020*2\u0006\u0010\u007f\u001a\u00020*2\u0007\u0010\u0080\u0001\u001a\u00020*2\u0007\u0010\u0081\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J+\u0010\u0082\u0001\u001a\u00020\r2\u0007\u0010\u0083\u0001\u001a\u00020p2\u0006\u0010e\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'H\u0016J\u001a\u0010\u0082\u0001\u001a\u00020\r2\u0007\u0010\u0083\u0001\u001a\u00020p2\u0006\u0010&\u001a\u00020'H\u0016J\u0019\u0010\u0085\u0001\u001a\u00020\r2\u0006\u0010X\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J1\u0010\u0085\u0001\u001a\u00020\r2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J\u0011\u0010\u0086\u0001\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0016J%\u0010\u0087\u0001\u001a\u00020\r2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0006\u0010V\u001a\u00020\u00142\b\u0010&\u001a\u0004\u0018\u00010'H\u0017J%\u0010\u0087\u0001\u001a\u00020\r2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0006\u0010V\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0017J\u0019\u0010\u008a\u0001\u001a\u00020\r2\u0006\u0010I\u001a\u00020J2\u0006\u0010&\u001a\u00020'H\u0016J!\u0010\u008b\u0001\u001a\u00020\r2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J-\u0010\u008c\u0001\u001a\u00020\r2\t\u0010\u0083\u0001\u001a\u0004\u0018\u00010p2\u0006\u0010e\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'H\u0016J\u001a\u0010\u008c\u0001\u001a\u00020\r2\u0007\u0010\u0083\u0001\u001a\u00020p2\u0006\u0010&\u001a\u00020'H\u0016J6\u0010\u008d\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0007\u0010\u0091\u0001\u001a\u00020p2\u0006\u0010&\u001a\u00020'H\u0017J$\u0010\u008d\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u0092\u00012\u0007\u0010\u0091\u0001\u001a\u00020p2\u0006\u0010&\u001a\u00020'H\u0017J\u0019\u0010\u0093\u0001\u001a\u00020\r2\u0006\u0010D\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u0019\u0010\u0093\u0001\u001a\u00020\r2\u0006\u0010^\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'H\u0016J1\u0010\u0093\u0001\u001a\u00020\r2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J!\u0010\u0094\u0001\u001a\u00020\r2\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010_\u001a\u00020\u001c2\u0006\u0010`\u001a\u00020\u001cH\u0016J+\u0010\u0095\u0001\u001a\u00020\r2\u0006\u0010D\u001a\u00020%2\u0007\u0010\u0096\u0001\u001a\u00020*2\u0007\u0010\u0097\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016JC\u0010\u0095\u0001\u001a\u00020\r2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0007\u0010\u0096\u0001\u001a\u00020*2\u0007\u0010\u0097\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016JG\u0010\u0098\u0001\u001a\u00020\r2\u0007\u0010\u0099\u0001\u001a\u00020%2\u0007\u0010\u009a\u0001\u001a\u00020*2\u0007\u0010\u009b\u0001\u001a\u00020*2\u0007\u0010\u009c\u0001\u001a\u00020%2\u0007\u0010\u009d\u0001\u001a\u00020*2\u0007\u0010\u009e\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0017J5\u0010\u0098\u0001\u001a\u00020\r2\u0007\u0010\u0099\u0001\u001a\u00020%2\u0007\u0010\u009f\u0001\u001a\u00020p2\u0007\u0010\u009c\u0001\u001a\u00020%2\u0007\u0010 \u0001\u001a\u00020p2\u0006\u0010&\u001a\u00020'H\u0017JH\u0010¡\u0001\u001a\u00020\r2\u0007\u0010¢\u0001\u001a\u00020d2\u0007\u0010£\u0001\u001a\u00020\u001c2\u0007\u0010¤\u0001\u001a\u00020p2\u0007\u0010¥\u0001\u001a\u00020\u001c2\u0007\u0010¦\u0001\u001a\u00020\u001c2\b\u0010§\u0001\u001a\u00030¨\u00012\u0006\u0010&\u001a\u00020'H\u0017J=\u0010©\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J+\u0010©\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u0092\u00012\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J=\u0010©\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u0092\u00012\u0007\u0010ª\u0001\u001a\u00020\u001c2\u0007\u0010«\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J=\u0010©\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030¬\u00012\u0007\u0010ª\u0001\u001a\u00020\u001c2\u0007\u0010«\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016JG\u0010\u00ad\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020J2\u0007\u0010®\u0001\u001a\u00020*2\u0007\u0010¯\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016J5\u0010\u00ad\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u0092\u00012\u0006\u0010I\u001a\u00020J2\u0007\u0010®\u0001\u001a\u00020*2\u0007\u0010¯\u0001\u001a\u00020*2\u0006\u0010&\u001a\u00020'H\u0016JX\u0010°\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020\u001c2\u0007\u0010\u0084\u0001\u001a\u00020\u001c2\u0007\u0010±\u0001\u001a\u00020\u001c2\u0007\u0010²\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0007\u0010³\u0001\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0017JX\u0010°\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030¬\u00012\u0007\u0010ª\u0001\u001a\u00020\u001c2\u0007\u0010«\u0001\u001a\u00020\u001c2\u0007\u0010´\u0001\u001a\u00020\u001c2\u0007\u0010µ\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0007\u0010³\u0001\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0017JX\u0010°\u0001\u001a\u00020\r2\b\u0010\u008e\u0001\u001a\u00030¶\u00012\u0007\u0010ª\u0001\u001a\u00020\u001c2\u0007\u0010«\u0001\u001a\u00020\u001c2\u0007\u0010´\u0001\u001a\u00020\u001c2\u0007\u0010µ\u0001\u001a\u00020\u001c2\u0006\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020*2\u0007\u0010³\u0001\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0017Jw\u0010·\u0001\u001a\u00020\r2\u0007\u0010z\u001a\u00030¸\u00012\u0007\u0010¹\u0001\u001a\u00020\u001c2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020\u001c2\t\u0010º\u0001\u001a\u0004\u0018\u00010p2\u0007\u0010»\u0001\u001a\u00020\u001c2\b\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010r\u001a\u00020\u001c2\n\u0010¼\u0001\u001a\u0005\u0018\u00010½\u00012\u0007\u0010¾\u0001\u001a\u00020\u001c2\u0007\u0010¿\u0001\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020'H\u0016J\u0013\u0010À\u0001\u001a\u00020\r2\b\u0010Á\u0001\u001a\u00030Â\u0001H\u0017R&\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0003\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006Ã\u0001"}, d2 = {"Landroidx/compose/ui/text/android/TextAndroidCanvas;", "Landroid/graphics/Canvas;", "<init>", "()V", "_nativeCanvas", "get_nativeCanvas$ui_text$annotations", "get_nativeCanvas$ui_text", "()Landroid/graphics/Canvas;", "set_nativeCanvas$ui_text", "(Landroid/graphics/Canvas;)V", "nativeCanvas", "getNativeCanvas", "withCanvas", "", "canvas", "block", "Lkotlin/Function1;", "getClipBounds", "", "bounds", "Landroid/graphics/Rect;", "setBitmap", "bitmap", "Landroid/graphics/Bitmap;", "enableZ", "disableZ", "isOpaque", "getWidth", "", "getHeight", "getDensity", "setDensity", "density", "getMaximumBitmapWidth", "getMaximumBitmapHeight", "save", "saveLayer", "Landroid/graphics/RectF;", "paint", "Landroid/graphics/Paint;", "saveFlags", "left", "", "top", "right", "bottom", "saveLayerAlpha", "alpha", "restore", "getSaveCount", "restoreToCount", "saveCount", "translate", "dx", "dy", "scale", "sx", "sy", "rotate", "degrees", "skew", "concat", "matrix", "Landroid/graphics/Matrix;", "setMatrix", "getMatrix", "ctm", "clipRect", "rect", "op", "Landroid/graphics/Region$Op;", "clipOutRect", "clipPath", PathTree.NAME_path, "Landroid/graphics/Path;", "clipOutPath", "getDrawFilter", "Landroid/graphics/DrawFilter;", "setDrawFilter", "filter", "quickReject", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroid/graphics/Canvas$EdgeType;", "drawPicture", "picture", "Landroid/graphics/Picture;", "dst", "drawArc", "oval", "startAngle", "sweepAngle", "useCenter", "drawARGB", "a", "r", "g", "b", "drawBitmap", "src", "colors", "", "offset", "stride", "x", "y", "width", "height", "hasAlpha", "drawBitmapMesh", "meshWidth", "meshHeight", "verts", "", "vertOffset", "colorOffset", "drawCircle", "cx", "cy", "radius", "drawColor", "color", "", "mode", "Landroid/graphics/PorterDuff$Mode;", "Landroid/graphics/BlendMode;", "drawLine", "startX", "startY", "stopX", "stopY", "drawLines", "pts", "count", "drawOval", "drawPaint", "drawPatch", "patch", "Landroid/graphics/NinePatch;", "drawPath", "drawPoint", "drawPoints", "drawPosText", "text", "", "index", "pos", "", "drawRect", "drawRGB", "drawRoundRect", "rx", "ry", "drawDoubleRoundRect", "outer", "outerRx", "outerRy", "inner", "innerRx", "innerRy", "outerRadii", "innerRadii", "drawGlyphs", "glyphIds", "glyphIdOffset", "positions", "positionOffset", "glyphCount", "font", "Landroid/graphics/fonts/Font;", "drawText", "start", "end", "", "drawTextOnPath", "hOffset", "vOffset", "drawTextRun", "contextIndex", "contextCount", "isRtl", "contextStart", "contextEnd", "Landroid/graphics/text/MeasuredText;", "drawVertices", "Landroid/graphics/Canvas$VertexMode;", "vertexCount", "texs", "texOffset", "indices", "", "indexOffset", "indexCount", "drawRenderNode", "renderNode", "Landroid/graphics/RenderNode;", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextAndroidCanvas extends Canvas {
    public static final int $stable = 8;
    private Canvas _nativeCanvas;

    private final Canvas getNativeCanvas() {
        Canvas canvas = this._nativeCanvas;
        if (canvas != null) {
            return canvas;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Text drawing wrapper is missing a Canvas!");
        wq6.a();
        return null;
    }

    public static /* synthetic */ void get_nativeCanvas$ui_text$annotations() {
    }

    @Override // android.graphics.Canvas
    public boolean clipOutPath(Path path) {
        return CanvasCompatO.INSTANCE.clipOutPath(getNativeCanvas(), path);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(float left, float top, float right, float bottom) {
        return CanvasCompatO.INSTANCE.clipOutRect(getNativeCanvas(), left, top, right, bottom);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean clipPath(Path path, Region.Op op) {
        return getNativeCanvas().clipPath(path, op);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean clipRect(RectF rect, Region.Op op) {
        return getNativeCanvas().clipRect(rect, op);
    }

    @Override // android.graphics.Canvas
    public void concat(Matrix matrix) {
        getNativeCanvas().concat(matrix);
    }

    @Override // android.graphics.Canvas
    public void disableZ() {
        CanvasCompatQ.INSTANCE.disableZ(getNativeCanvas());
    }

    @Override // android.graphics.Canvas
    public void drawARGB(int a, int r, int g, int b) {
        getNativeCanvas().drawARGB(a, r, g, b);
    }

    @Override // android.graphics.Canvas
    public void drawArc(RectF oval, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        getNativeCanvas().drawArc(oval, startAngle, sweepAngle, useCenter, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) {
        getNativeCanvas().drawBitmap(bitmap, left, top, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmapMesh(Bitmap bitmap, int meshWidth, int meshHeight, float[] verts, int vertOffset, int[] colors, int colorOffset, Paint paint) {
        getNativeCanvas().drawBitmapMesh(bitmap, meshWidth, meshHeight, verts, vertOffset, colors, colorOffset, paint);
    }

    @Override // android.graphics.Canvas
    public void drawCircle(float cx, float cy, float radius, Paint paint) {
        getNativeCanvas().drawCircle(cx, cy, radius, paint);
    }

    @Override // android.graphics.Canvas
    public void drawColor(long color) {
        CanvasCompatQ.INSTANCE.drawColor(getNativeCanvas(), color);
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF outer, float outerRx, float outerRy, RectF inner, float innerRx, float innerRy, Paint paint) {
        CanvasCompatQ.INSTANCE.drawDoubleRoundRect(getNativeCanvas(), outer, outerRx, outerRy, inner, innerRx, innerRy, paint);
    }

    @Override // android.graphics.Canvas
    public void drawGlyphs(int[] glyphIds, int glyphIdOffset, float[] positions, int positionOffset, int glyphCount, Font font, Paint paint) {
        CanvasCompatS.INSTANCE.drawGlyphs(getNativeCanvas(), glyphIds, glyphIdOffset, positions, positionOffset, glyphCount, font, paint);
    }

    @Override // android.graphics.Canvas
    public void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) {
        getNativeCanvas().drawLine(startX, startY, stopX, stopY, paint);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] pts, int offset, int count, Paint paint) {
        getNativeCanvas().drawLines(pts, offset, count, paint);
    }

    @Override // android.graphics.Canvas
    public void drawOval(RectF oval, Paint paint) {
        getNativeCanvas().drawOval(oval, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPaint(Paint paint) {
        getNativeCanvas().drawPaint(paint);
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch patch, Rect dst, Paint paint) {
        CanvasCompatS.INSTANCE.drawPatch(getNativeCanvas(), patch, dst, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPath(Path path, Paint paint) {
        getNativeCanvas().drawPath(path, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture) {
        getNativeCanvas().drawPicture(picture);
    }

    @Override // android.graphics.Canvas
    public void drawPoint(float x, float y, Paint paint) {
        getNativeCanvas().drawPoint(x, y, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] pts, int offset, int count, Paint paint) {
        getNativeCanvas().drawPoints(pts, offset, count, paint);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public void drawPosText(char[] text, int index, int count, float[] pos, Paint paint) {
        getNativeCanvas().drawPosText(text, index, count, pos, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRGB(int r, int g, int b) {
        getNativeCanvas().drawRGB(r, g, b);
    }

    @Override // android.graphics.Canvas
    public void drawRect(RectF rect, Paint paint) {
        getNativeCanvas().drawRect(rect, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRenderNode(RenderNode renderNode) {
        CanvasCompatQ.INSTANCE.drawRenderNode(getNativeCanvas(), renderNode);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(RectF rect, float rx, float ry, Paint paint) {
        getNativeCanvas().drawRoundRect(rect, rx, ry, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(char[] text, int index, int count, float x, float y, Paint paint) {
        getNativeCanvas().drawText(text, index, count, x, y, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint) {
        getNativeCanvas().drawTextOnPath(text, index, count, path, hOffset, vOffset, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(char[] text, int index, int count, int contextIndex, int contextCount, float x, float y, boolean isRtl, Paint paint) {
        CanvasCompatM.INSTANCE.drawTextRun(getNativeCanvas(), text, index, count, contextIndex, contextCount, x, y, isRtl, paint);
    }

    @Override // android.graphics.Canvas
    public void drawVertices(Canvas.VertexMode mode, int vertexCount, float[] verts, int vertOffset, float[] texs, int texOffset, int[] colors, int colorOffset, short[] indices, int indexOffset, int indexCount, Paint paint) {
        getNativeCanvas().drawVertices(mode, vertexCount, verts, vertOffset, texs, texOffset, colors, colorOffset, indices, indexOffset, indexCount, paint);
    }

    @Override // android.graphics.Canvas
    public void enableZ() {
        CanvasCompatQ.INSTANCE.enableZ(getNativeCanvas());
    }

    @Override // android.graphics.Canvas
    public boolean getClipBounds(Rect bounds) {
        boolean clipBounds = getNativeCanvas().getClipBounds(bounds);
        if (clipBounds) {
            bounds.set(0, 0, bounds.width(), Integer.MAX_VALUE);
        }
        return clipBounds;
    }

    @Override // android.graphics.Canvas
    public int getDensity() {
        return getNativeCanvas().getDensity();
    }

    @Override // android.graphics.Canvas
    public DrawFilter getDrawFilter() {
        return getNativeCanvas().getDrawFilter();
    }

    @Override // android.graphics.Canvas
    public int getHeight() {
        return getNativeCanvas().getHeight();
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public void getMatrix(Matrix ctm) {
        getNativeCanvas().getMatrix(ctm);
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapHeight() {
        return getNativeCanvas().getMaximumBitmapHeight();
    }

    @Override // android.graphics.Canvas
    public int getMaximumBitmapWidth() {
        return getNativeCanvas().getMaximumBitmapWidth();
    }

    @Override // android.graphics.Canvas
    public int getSaveCount() {
        return getNativeCanvas().getSaveCount();
    }

    @Override // android.graphics.Canvas
    public int getWidth() {
        return getNativeCanvas().getWidth();
    }

    /* JADX INFO: renamed from: get_nativeCanvas$ui_text, reason: from getter */
    public final Canvas get_nativeCanvas() {
        return this._nativeCanvas;
    }

    @Override // android.graphics.Canvas
    public boolean isOpaque() {
        return getNativeCanvas().isOpaque();
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(float left, float top, float right, float bottom) {
        return CanvasCompatR.INSTANCE.quickReject(getNativeCanvas(), left, top, right, bottom);
    }

    @Override // android.graphics.Canvas
    public void restore() {
        getNativeCanvas().restore();
    }

    @Override // android.graphics.Canvas
    public void restoreToCount(int saveCount) {
        getNativeCanvas().restoreToCount(saveCount);
    }

    @Override // android.graphics.Canvas
    public void rotate(float degrees) {
        getNativeCanvas().rotate(degrees);
    }

    @Override // android.graphics.Canvas
    public int save() {
        return getNativeCanvas().save();
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public int saveLayer(RectF bounds, Paint paint, int saveFlags) {
        return getNativeCanvas().saveLayer(bounds, paint, saveFlags);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public int saveLayerAlpha(RectF bounds, int alpha, int saveFlags) {
        return getNativeCanvas().saveLayerAlpha(bounds, alpha, saveFlags);
    }

    @Override // android.graphics.Canvas
    public void scale(float sx, float sy) {
        getNativeCanvas().scale(sx, sy);
    }

    @Override // android.graphics.Canvas
    public void setBitmap(Bitmap bitmap) {
        getNativeCanvas().setBitmap(bitmap);
    }

    @Override // android.graphics.Canvas
    public void setDensity(int density) {
        getNativeCanvas().setDensity(density);
    }

    @Override // android.graphics.Canvas
    public void setDrawFilter(DrawFilter filter) {
        getNativeCanvas().setDrawFilter(filter);
    }

    @Override // android.graphics.Canvas
    public void setMatrix(Matrix matrix) {
        getNativeCanvas().setMatrix(matrix);
    }

    public final void set_nativeCanvas$ui_text(Canvas canvas) {
        this._nativeCanvas = canvas;
    }

    @Override // android.graphics.Canvas
    public void skew(float sx, float sy) {
        getNativeCanvas().skew(sx, sy);
    }

    @Override // android.graphics.Canvas
    public void translate(float dx, float dy) {
        getNativeCanvas().translate(dx, dy);
    }

    public final void withCanvas(Canvas canvas, Function1<? super Canvas, Unit> block) {
        set_nativeCanvas$ui_text(canvas);
        try {
            block.invoke(this);
        } finally {
            InlineMarker.finallyStart(1);
            set_nativeCanvas$ui_text(null);
            InlineMarker.finallyEnd(1);
        }
    }

    @Override // android.graphics.Canvas
    public void drawArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        getNativeCanvas().drawArc(left, top, right, bottom, startAngle, sweepAngle, useCenter, paint);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect src, RectF dst, Paint paint) {
        getNativeCanvas().drawBitmap(bitmap, src, dst, paint);
    }

    @Override // android.graphics.Canvas
    public void drawLines(float[] pts, Paint paint) {
        getNativeCanvas().drawLines(pts, paint);
    }

    @Override // android.graphics.Canvas
    public void drawOval(float left, float top, float right, float bottom, Paint paint) {
        getNativeCanvas().drawOval(left, top, right, bottom, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, RectF dst) {
        getNativeCanvas().drawPicture(picture, dst);
    }

    @Override // android.graphics.Canvas
    public void drawPoints(float[] pts, Paint paint) {
        getNativeCanvas().drawPoints(pts, paint);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public void drawPosText(String text, float[] pos, Paint paint) {
        getNativeCanvas().drawPosText(text, pos, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRect(Rect r, Paint paint) {
        getNativeCanvas().drawRect(r, paint);
    }

    @Override // android.graphics.Canvas
    public void drawRoundRect(float left, float top, float right, float bottom, float rx, float ry, Paint paint) {
        getNativeCanvas().drawRoundRect(left, top, right, bottom, rx, ry, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(String text, float x, float y, Paint paint) {
        getNativeCanvas().drawText(text, x, y, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint) {
        getNativeCanvas().drawTextOnPath(text, path, hOffset, vOffset, paint);
    }

    @Override // android.graphics.Canvas
    public boolean clipPath(Path path) {
        return getNativeCanvas().clipPath(path);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean clipRect(Rect rect, Region.Op op) {
        return getNativeCanvas().clipRect(rect, op);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst, Paint paint) {
        getNativeCanvas().drawBitmap(bitmap, src, dst, paint);
    }

    @Override // android.graphics.Canvas
    public void drawPicture(Picture picture, Rect dst) {
        getNativeCanvas().drawPicture(picture, dst);
    }

    @Override // android.graphics.Canvas
    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        getNativeCanvas().drawRect(left, top, right, bottom, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(String text, int start, int end, float x, float y, Paint paint) {
        getNativeCanvas().drawText(text, start, end, x, y, paint);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(RectF bounds, Paint paint) {
        return getNativeCanvas().saveLayer(bounds, paint);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(RectF bounds, int alpha) {
        return getNativeCanvas().saveLayerAlpha(bounds, alpha);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(RectF rect) {
        return getNativeCanvas().clipRect(rect);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public void drawBitmap(int[] colors, int offset, int stride, float x, float y, int width, int height, boolean hasAlpha, Paint paint) {
        getNativeCanvas().drawBitmap(colors, offset, stride, x, y, width, height, hasAlpha, paint);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color) {
        getNativeCanvas().drawColor(color);
    }

    @Override // android.graphics.Canvas
    public void drawPatch(NinePatch patch, RectF dst, Paint paint) {
        CanvasCompatS.INSTANCE.drawPatch(getNativeCanvas(), patch, dst, paint);
    }

    @Override // android.graphics.Canvas
    public void drawText(CharSequence text, int start, int end, float x, float y, Paint paint) {
        getNativeCanvas().drawText(text, start, end, x, y, paint);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public int saveLayer(float left, float top, float right, float bottom, Paint paint, int saveFlags) {
        return getNativeCanvas().saveLayer(left, top, right, bottom, paint, saveFlags);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha, int saveFlags) {
        return getNativeCanvas().saveLayerAlpha(left, top, right, bottom, alpha, saveFlags);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(Rect rect) {
        return getNativeCanvas().clipRect(rect);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public void drawBitmap(int[] colors, int offset, int stride, int x, int y, int width, int height, boolean hasAlpha, Paint paint) {
        getNativeCanvas().drawBitmap(colors, offset, stride, x, y, width, height, hasAlpha, paint);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color, PorterDuff.Mode mode) {
        getNativeCanvas().drawColor(color, mode);
    }

    @Override // android.graphics.Canvas
    public int saveLayer(float left, float top, float right, float bottom, Paint paint) {
        return getNativeCanvas().saveLayer(left, top, right, bottom, paint);
    }

    @Override // android.graphics.Canvas
    public int saveLayerAlpha(float left, float top, float right, float bottom, int alpha) {
        return getNativeCanvas().saveLayerAlpha(left, top, right, bottom, alpha);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean clipRect(float left, float top, float right, float bottom, Region.Op op) {
        return getNativeCanvas().clipRect(left, top, right, bottom, op);
    }

    @Override // android.graphics.Canvas
    public void drawBitmap(Bitmap bitmap, Matrix matrix, Paint paint) {
        getNativeCanvas().drawBitmap(bitmap, matrix, paint);
    }

    @Override // android.graphics.Canvas
    public void drawColor(int color, BlendMode mode) {
        CanvasCompatQ.INSTANCE.drawColor(getNativeCanvas(), color, mode);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(float left, float top, float right, float bottom) {
        return getNativeCanvas().clipRect(left, top, right, bottom);
    }

    @Override // android.graphics.Canvas
    public void drawColor(long color, BlendMode mode) {
        CanvasCompatQ.INSTANCE.drawColor(getNativeCanvas(), color, mode);
    }

    @Override // android.graphics.Canvas
    public boolean clipRect(int left, int top, int right, int bottom) {
        return getNativeCanvas().clipRect(left, top, right, bottom);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(Rect rect) {
        return CanvasCompatO.INSTANCE.clipOutRect(getNativeCanvas(), rect);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(RectF rect) {
        return CanvasCompatR.INSTANCE.quickReject(getNativeCanvas(), rect);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(RectF rect) {
        return CanvasCompatO.INSTANCE.clipOutRect(getNativeCanvas(), rect);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean quickReject(Path path, Canvas.EdgeType type) {
        return getNativeCanvas().quickReject(path, type);
    }

    @Override // android.graphics.Canvas
    public boolean clipOutRect(int left, int top, int right, int bottom) {
        return CanvasCompatO.INSTANCE.clipOutRect(getNativeCanvas(), left, top, right, bottom);
    }

    @Override // android.graphics.Canvas
    public boolean quickReject(Path path) {
        return CanvasCompatR.INSTANCE.quickReject(getNativeCanvas(), path);
    }

    @Override // android.graphics.Canvas
    public void drawDoubleRoundRect(RectF outer, float[] outerRadii, RectF inner, float[] innerRadii, Paint paint) {
        CanvasCompatQ.INSTANCE.drawDoubleRoundRect(getNativeCanvas(), outer, outerRadii, inner, innerRadii, paint);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean quickReject(float left, float top, float right, float bottom, Canvas.EdgeType type) {
        return getNativeCanvas().quickReject(left, top, right, bottom, type);
    }

    @Override // android.graphics.Canvas
    @Deprecated(message = "Deprecated in Java")
    public boolean quickReject(RectF rect, Canvas.EdgeType type) {
        return getNativeCanvas().quickReject(rect, type);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(CharSequence text, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) {
        CanvasCompatM.INSTANCE.drawTextRun(getNativeCanvas(), text, start, end, contextStart, contextEnd, x, y, isRtl, paint);
    }

    @Override // android.graphics.Canvas
    public void drawTextRun(MeasuredText text, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint paint) {
        CanvasCompatQ.INSTANCE.drawTextRun(getNativeCanvas(), text, start, end, contextStart, contextEnd, x, y, isRtl, paint);
    }
}
