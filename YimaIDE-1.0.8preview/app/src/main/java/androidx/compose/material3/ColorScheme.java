package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\bm\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0087\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003\u0012\u0006\u0010'\u001a\u00020\u0003\u0012\u0006\u0010(\u001a\u00020\u0003\u0012\u0006\u0010)\u001a\u00020\u0003\u0012\u0006\u0010*\u001a\u00020\u0003\u0012\u0006\u0010+\u001a\u00020\u0003\u0012\u0006\u0010,\u001a\u00020\u0003\u0012\u0006\u0010-\u001a\u00020\u0003\u0012\u0006\u0010.\u001a\u00020\u0003\u0012\u0006\u0010/\u001a\u00020\u0003\u0012\u0006\u00100\u001a\u00020\u0003\u0012\u0006\u00101\u001a\u00020\u0003\u0012\u0006\u00102\u001a\u00020\u0003¢\u0006\u0004\b3\u00104B©\u0002\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0006\u0010 \u001a\u00020\u0003\u0012\u0006\u0010!\u001a\u00020\u0003\u0012\u0006\u0010\"\u001a\u00020\u0003\u0012\u0006\u0010#\u001a\u00020\u0003\u0012\u0006\u0010$\u001a\u00020\u0003\u0012\u0006\u0010%\u001a\u00020\u0003\u0012\u0006\u0010&\u001a\u00020\u0003¢\u0006\u0004\b3\u00105Bñ\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\u0006\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0003\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001c\u001a\u00020\u0003\u0012\u0006\u0010\u001d\u001a\u00020\u0003\u0012\u0006\u0010\u001e\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003¢\u0006\u0004\b3\u00106Jí\u0003\u0010i\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\b\b\u0002\u0010'\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00032\b\b\u0002\u0010)\u001a\u00020\u00032\b\b\u0002\u0010*\u001a\u00020\u00032\b\b\u0002\u0010+\u001a\u00020\u00032\b\b\u0002\u0010,\u001a\u00020\u00032\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00032\b\b\u0002\u0010/\u001a\u00020\u00032\b\b\u0002\u00100\u001a\u00020\u00032\b\b\u0002\u00101\u001a\u00020\u00032\b\b\u0002\u00102\u001a\u00020\u0003¢\u0006\u0004\bj\u0010kJ±\u0002\u0010i\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u0003H\u0007¢\u0006\u0004\bl\u0010mJ÷\u0002\u0010i\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00032\b\b\u0002\u0010!\u001a\u00020\u00032\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00032\b\b\u0002\u0010$\u001a\u00020\u00032\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u0003H\u0007¢\u0006\u0004\bn\u0010oJ\b\u0010p\u001a\u00020qH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b7\u00108R\u0013\u0010\u0004\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b:\u00108R\u0013\u0010\u0005\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b;\u00108R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b<\u00108R\u0013\u0010\u0007\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b=\u00108R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b>\u00108R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b?\u00108R\u0013\u0010\n\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b@\u00108R\u0013\u0010\u000b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bA\u00108R\u0013\u0010\f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bB\u00108R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bC\u00108R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bD\u00108R\u0013\u0010\u000f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bE\u00108R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bF\u00108R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bG\u00108R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bH\u00108R\u0013\u0010\u0013\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bI\u00108R\u0013\u0010\u0014\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bJ\u00108R\u0013\u0010\u0015\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bK\u00108R\u0013\u0010\u0016\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bL\u00108R\u0013\u0010\u0017\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bM\u00108R\u0013\u0010\u0018\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bN\u00108R\u0013\u0010\u0019\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bO\u00108R\u0013\u0010\u001a\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bP\u00108R\u0013\u0010\u001b\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bQ\u00108R\u0013\u0010\u001c\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bR\u00108R\u0013\u0010\u001d\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bS\u00108R\u0013\u0010\u001e\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bT\u00108R\u0013\u0010\u001f\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bU\u00108R\u0013\u0010 \u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bV\u00108R\u0013\u0010!\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bW\u00108R\u0013\u0010\"\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bX\u00108R\u0013\u0010#\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bY\u00108R\u0013\u0010$\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bZ\u00108R\u0013\u0010%\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b[\u00108R\u0013\u0010&\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b\\\u00108R\u0013\u0010'\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b]\u00108R\u0013\u0010(\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b^\u00108R\u0013\u0010)\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b_\u00108R\u0013\u0010*\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\b`\u00108R\u0013\u0010+\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\ba\u00108R\u0013\u0010,\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bb\u00108R\u0013\u0010-\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bc\u00108R\u0013\u0010.\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bd\u00108R\u0013\u0010/\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\be\u00108R\u0013\u00100\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bf\u00108R\u0013\u00101\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bg\u00108R\u0013\u00102\u001a\u00020\u0003¢\u0006\n\n\u0002\u00109\u001a\u0004\bh\u00108R\u001c\u0010r\u001a\u0004\u0018\u00010sX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u001c\u0010x\u001a\u0004\u0018\u00010sX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010u\"\u0004\bz\u0010wR\u001c\u0010{\u001a\u0004\u0018\u00010sX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010u\"\u0004\b}\u0010wR\u001d\u0010~\u001a\u0004\u0018\u00010sX\u0080\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010u\"\u0005\b\u0080\u0001\u0010wR\u001f\u0010\u0081\u0001\u001a\u0004\u0018\u00010sX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010u\"\u0005\b\u0083\u0001\u0010wR\"\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u0006\b\u0088\u0001\u0010\u0089\u0001R\"\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008b\u0001\u0010\u0087\u0001\"\u0006\b\u008c\u0001\u0010\u0089\u0001R\"\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u0085\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008e\u0001\u0010\u0087\u0001\"\u0006\b\u008f\u0001\u0010\u0089\u0001R\"\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001\"\u0006\b\u0094\u0001\u0010\u0095\u0001R\"\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0091\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0097\u0001\u0010\u0093\u0001\"\u0006\b\u0098\u0001\u0010\u0095\u0001R\"\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0091\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u0093\u0001\"\u0006\b\u009b\u0001\u0010\u0095\u0001R\"\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u0091\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u0093\u0001\"\u0006\b\u009e\u0001\u0010\u0095\u0001R\"\u0010\u009f\u0001\u001a\u0005\u0018\u00010 \u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¡\u0001\u0010¢\u0001\"\u0006\b£\u0001\u0010¤\u0001R\"\u0010¥\u0001\u001a\u0005\u0018\u00010 \u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¦\u0001\u0010¢\u0001\"\u0006\b§\u0001\u0010¤\u0001R\"\u0010¨\u0001\u001a\u0005\u0018\u00010 \u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b©\u0001\u0010¢\u0001\"\u0006\bª\u0001\u0010¤\u0001R\"\u0010«\u0001\u001a\u0005\u0018\u00010¬\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u00ad\u0001\u0010®\u0001\"\u0006\b¯\u0001\u0010°\u0001R*\u0010±\u0001\u001a\u0005\u0018\u00010²\u0001X\u0080\u000e¢\u0006\u001a\n\u0000\u0012\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0006\b·\u0001\u0010¸\u0001R\"\u0010¹\u0001\u001a\u0005\u0018\u00010º\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b»\u0001\u0010¼\u0001\"\u0006\b½\u0001\u0010¾\u0001R*\u0010¿\u0001\u001a\u0005\u0018\u00010À\u0001X\u0080\u000e¢\u0006\u001a\n\u0000\u0012\u0006\bÁ\u0001\u0010´\u0001\u001a\u0006\bÂ\u0001\u0010Ã\u0001\"\u0006\bÄ\u0001\u0010Å\u0001R\"\u0010Æ\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001R\"\u0010Ì\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÍ\u0001\u0010É\u0001\"\u0006\bÎ\u0001\u0010Ë\u0001R\"\u0010Ï\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÑ\u0001\u0010Ò\u0001\"\u0006\bÓ\u0001\u0010Ô\u0001R\"\u0010Õ\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÖ\u0001\u0010Ò\u0001\"\u0006\b×\u0001\u0010Ô\u0001R\"\u0010Ø\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÙ\u0001\u0010É\u0001\"\u0006\bÚ\u0001\u0010Ë\u0001R\"\u0010Û\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bÜ\u0001\u0010Ò\u0001\"\u0006\bÝ\u0001\u0010Ô\u0001R\"\u0010Þ\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bß\u0001\u0010É\u0001\"\u0006\bà\u0001\u0010Ë\u0001R\"\u0010á\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bâ\u0001\u0010Ò\u0001\"\u0006\bã\u0001\u0010Ô\u0001R\"\u0010ä\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bå\u0001\u0010É\u0001\"\u0006\bæ\u0001\u0010Ë\u0001R\"\u0010ç\u0001\u001a\u0005\u0018\u00010Ç\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bè\u0001\u0010É\u0001\"\u0006\bé\u0001\u0010Ë\u0001R\"\u0010ê\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bë\u0001\u0010Ò\u0001\"\u0006\bì\u0001\u0010Ô\u0001R\"\u0010í\u0001\u001a\u0005\u0018\u00010Ð\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bî\u0001\u0010Ò\u0001\"\u0006\bï\u0001\u0010Ô\u0001R\"\u0010ð\u0001\u001a\u0005\u0018\u00010ñ\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bò\u0001\u0010ó\u0001\"\u0006\bô\u0001\u0010õ\u0001R\"\u0010ö\u0001\u001a\u0005\u0018\u00010÷\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bø\u0001\u0010ù\u0001\"\u0006\bú\u0001\u0010û\u0001R\"\u0010ü\u0001\u001a\u0005\u0018\u00010ý\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\bþ\u0001\u0010ÿ\u0001\"\u0006\b\u0080\u0002\u0010\u0081\u0002R\"\u0010\u0082\u0002\u001a\u0005\u0018\u00010\u0083\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002\"\u0006\b\u0086\u0002\u0010\u0087\u0002R\"\u0010\u0088\u0002\u001a\u0005\u0018\u00010\u0089\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u008a\u0002\u0010\u008b\u0002\"\u0006\b\u008c\u0002\u0010\u008d\u0002R\"\u0010\u008e\u0002\u001a\u0005\u0018\u00010\u008f\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0090\u0002\u0010\u0091\u0002\"\u0006\b\u0092\u0002\u0010\u0093\u0002R\"\u0010\u0094\u0002\u001a\u0005\u0018\u00010\u0083\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0095\u0002\u0010\u0085\u0002\"\u0006\b\u0096\u0002\u0010\u0087\u0002R\"\u0010\u0097\u0002\u001a\u0005\u0018\u00010\u0098\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0099\u0002\u0010\u009a\u0002\"\u0006\b\u009b\u0002\u0010\u009c\u0002R\"\u0010\u009d\u0002\u001a\u0005\u0018\u00010\u009e\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009f\u0002\u0010 \u0002\"\u0006\b¡\u0002\u0010¢\u0002R\"\u0010£\u0002\u001a\u0005\u0018\u00010¤\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¥\u0002\u0010¦\u0002\"\u0006\b§\u0002\u0010¨\u0002R\"\u0010©\u0002\u001a\u0005\u0018\u00010ª\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b«\u0002\u0010¬\u0002\"\u0006\b\u00ad\u0002\u0010®\u0002R\"\u0010¯\u0002\u001a\u0005\u0018\u00010°\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b±\u0002\u0010²\u0002\"\u0006\b³\u0002\u0010´\u0002R\"\u0010µ\u0002\u001a\u0005\u0018\u00010°\u0002X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¶\u0002\u0010²\u0002\"\u0006\b·\u0002\u0010´\u0002R*\u0010¸\u0002\u001a\u0005\u0018\u00010¹\u0002X\u0080\u000e¢\u0006\u001a\n\u0000\u0012\u0006\bº\u0002\u0010´\u0001\u001a\u0006\b»\u0002\u0010¼\u0002\"\u0006\b½\u0002\u0010¾\u0002R*\u0010¿\u0002\u001a\u0005\u0018\u00010À\u0002X\u0080\u000e¢\u0006\u001a\n\u0000\u0012\u0006\bÁ\u0002\u0010´\u0001\u001a\u0006\bÂ\u0002\u0010Ã\u0002\"\u0006\bÄ\u0002\u0010Å\u0002¨\u0006Æ\u0002"}, d2 = {"Landroidx/compose/material3/ColorScheme;", "", "primary", "Landroidx/compose/ui/graphics/Color;", "onPrimary", "primaryContainer", "onPrimaryContainer", "inversePrimary", "secondary", "onSecondary", "secondaryContainer", "onSecondaryContainer", "tertiary", "onTertiary", "tertiaryContainer", "onTertiaryContainer", "background", "onBackground", "surface", "onSurface", "surfaceVariant", "onSurfaceVariant", "surfaceTint", "inverseSurface", "inverseOnSurface", "error", "onError", "errorContainer", "onErrorContainer", "outline", "outlineVariant", "scrim", "surfaceBright", "surfaceDim", "surfaceContainer", "surfaceContainerHigh", "surfaceContainerHighest", "surfaceContainerLow", "surfaceContainerLowest", "primaryFixed", "primaryFixedDim", "onPrimaryFixed", "onPrimaryFixedVariant", "secondaryFixed", "secondaryFixedDim", "onSecondaryFixed", "onSecondaryFixedVariant", "tertiaryFixed", "tertiaryFixedDim", "onTertiaryFixed", "onTertiaryFixedVariant", "<init>", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getPrimary-0d7_KjU", "()J", "J", "getOnPrimary-0d7_KjU", "getPrimaryContainer-0d7_KjU", "getOnPrimaryContainer-0d7_KjU", "getInversePrimary-0d7_KjU", "getSecondary-0d7_KjU", "getOnSecondary-0d7_KjU", "getSecondaryContainer-0d7_KjU", "getOnSecondaryContainer-0d7_KjU", "getTertiary-0d7_KjU", "getOnTertiary-0d7_KjU", "getTertiaryContainer-0d7_KjU", "getOnTertiaryContainer-0d7_KjU", "getBackground-0d7_KjU", "getOnBackground-0d7_KjU", "getSurface-0d7_KjU", "getOnSurface-0d7_KjU", "getSurfaceVariant-0d7_KjU", "getOnSurfaceVariant-0d7_KjU", "getSurfaceTint-0d7_KjU", "getInverseSurface-0d7_KjU", "getInverseOnSurface-0d7_KjU", "getError-0d7_KjU", "getOnError-0d7_KjU", "getErrorContainer-0d7_KjU", "getOnErrorContainer-0d7_KjU", "getOutline-0d7_KjU", "getOutlineVariant-0d7_KjU", "getScrim-0d7_KjU", "getSurfaceBright-0d7_KjU", "getSurfaceDim-0d7_KjU", "getSurfaceContainer-0d7_KjU", "getSurfaceContainerHigh-0d7_KjU", "getSurfaceContainerHighest-0d7_KjU", "getSurfaceContainerLow-0d7_KjU", "getSurfaceContainerLowest-0d7_KjU", "getPrimaryFixed-0d7_KjU", "getPrimaryFixedDim-0d7_KjU", "getOnPrimaryFixed-0d7_KjU", "getOnPrimaryFixedVariant-0d7_KjU", "getSecondaryFixed-0d7_KjU", "getSecondaryFixedDim-0d7_KjU", "getOnSecondaryFixed-0d7_KjU", "getOnSecondaryFixedVariant-0d7_KjU", "getTertiaryFixed-0d7_KjU", "getTertiaryFixedDim-0d7_KjU", "getOnTertiaryFixed-0d7_KjU", "getOnTertiaryFixedVariant-0d7_KjU", "copy", "copy-_VG5OTI", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "copy-G1PFc-w", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "copy-C-Xl9yA", "(JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ)Landroidx/compose/material3/ColorScheme;", "toString", "", "defaultButtonColorsCached", "Landroidx/compose/material3/ButtonColors;", "getDefaultButtonColorsCached$material3", "()Landroidx/compose/material3/ButtonColors;", "setDefaultButtonColorsCached$material3", "(Landroidx/compose/material3/ButtonColors;)V", "defaultElevatedButtonColorsCached", "getDefaultElevatedButtonColorsCached$material3", "setDefaultElevatedButtonColorsCached$material3", "defaultFilledTonalButtonColorsCached", "getDefaultFilledTonalButtonColorsCached$material3", "setDefaultFilledTonalButtonColorsCached$material3", "defaultOutlinedButtonColorsCached", "getDefaultOutlinedButtonColorsCached$material3", "setDefaultOutlinedButtonColorsCached$material3", "defaultTextButtonColorsCached", "getDefaultTextButtonColorsCached$material3", "setDefaultTextButtonColorsCached$material3", "defaultCardColorsCached", "Landroidx/compose/material3/CardColors;", "getDefaultCardColorsCached$material3", "()Landroidx/compose/material3/CardColors;", "setDefaultCardColorsCached$material3", "(Landroidx/compose/material3/CardColors;)V", "defaultElevatedCardColorsCached", "getDefaultElevatedCardColorsCached$material3", "setDefaultElevatedCardColorsCached$material3", "defaultOutlinedCardColorsCached", "getDefaultOutlinedCardColorsCached$material3", "setDefaultOutlinedCardColorsCached$material3", "defaultAssistChipColorsCached", "Landroidx/compose/material3/ChipColors;", "getDefaultAssistChipColorsCached$material3", "()Landroidx/compose/material3/ChipColors;", "setDefaultAssistChipColorsCached$material3", "(Landroidx/compose/material3/ChipColors;)V", "defaultElevatedAssistChipColorsCached", "getDefaultElevatedAssistChipColorsCached$material3", "setDefaultElevatedAssistChipColorsCached$material3", "defaultSuggestionChipColorsCached", "getDefaultSuggestionChipColorsCached$material3", "setDefaultSuggestionChipColorsCached$material3", "defaultElevatedSuggestionChipColorsCached", "getDefaultElevatedSuggestionChipColorsCached$material3", "setDefaultElevatedSuggestionChipColorsCached$material3", "defaultFilterChipColorsCached", "Landroidx/compose/material3/SelectableChipColors;", "getDefaultFilterChipColorsCached$material3", "()Landroidx/compose/material3/SelectableChipColors;", "setDefaultFilterChipColorsCached$material3", "(Landroidx/compose/material3/SelectableChipColors;)V", "defaultElevatedFilterChipColorsCached", "getDefaultElevatedFilterChipColorsCached$material3", "setDefaultElevatedFilterChipColorsCached$material3", "defaultInputChipColorsCached", "getDefaultInputChipColorsCached$material3", "setDefaultInputChipColorsCached$material3", "defaultVerticalDragHandleColorsCached", "Landroidx/compose/material3/DragHandleColors;", "getDefaultVerticalDragHandleColorsCached$material3", "()Landroidx/compose/material3/DragHandleColors;", "setDefaultVerticalDragHandleColorsCached$material3", "(Landroidx/compose/material3/DragHandleColors;)V", "defaultTopAppBarColorsCached", "Landroidx/compose/material3/TopAppBarColors;", "getDefaultTopAppBarColorsCached$material3$annotations", "()V", "getDefaultTopAppBarColorsCached$material3", "()Landroidx/compose/material3/TopAppBarColors;", "setDefaultTopAppBarColorsCached$material3", "(Landroidx/compose/material3/TopAppBarColors;)V", "defaultCheckboxColorsCached", "Landroidx/compose/material3/CheckboxColors;", "getDefaultCheckboxColorsCached$material3", "()Landroidx/compose/material3/CheckboxColors;", "setDefaultCheckboxColorsCached$material3", "(Landroidx/compose/material3/CheckboxColors;)V", "defaultDatePickerColorsCached", "Landroidx/compose/material3/DatePickerColors;", "getDefaultDatePickerColorsCached$material3$annotations", "getDefaultDatePickerColorsCached$material3", "()Landroidx/compose/material3/DatePickerColors;", "setDefaultDatePickerColorsCached$material3", "(Landroidx/compose/material3/DatePickerColors;)V", "defaultIconButtonColorsCached", "Landroidx/compose/material3/IconButtonColors;", "getDefaultIconButtonColorsCached$material3", "()Landroidx/compose/material3/IconButtonColors;", "setDefaultIconButtonColorsCached$material3", "(Landroidx/compose/material3/IconButtonColors;)V", "defaultIconButtonVibrantColorsCached", "getDefaultIconButtonVibrantColorsCached$material3", "setDefaultIconButtonVibrantColorsCached$material3", "defaultIconToggleButtonColorsCached", "Landroidx/compose/material3/IconToggleButtonColors;", "getDefaultIconToggleButtonColorsCached$material3", "()Landroidx/compose/material3/IconToggleButtonColors;", "setDefaultIconToggleButtonColorsCached$material3", "(Landroidx/compose/material3/IconToggleButtonColors;)V", "defaultIconToggleButtonVibrantColorsCached", "getDefaultIconToggleButtonVibrantColorsCached$material3", "setDefaultIconToggleButtonVibrantColorsCached$material3", "defaultFilledIconButtonColorsCached", "getDefaultFilledIconButtonColorsCached$material3", "setDefaultFilledIconButtonColorsCached$material3", "defaultFilledIconToggleButtonColorsCached", "getDefaultFilledIconToggleButtonColorsCached$material3", "setDefaultFilledIconToggleButtonColorsCached$material3", "defaultFilledTonalIconButtonColorsCached", "getDefaultFilledTonalIconButtonColorsCached$material3", "setDefaultFilledTonalIconButtonColorsCached$material3", "defaultFilledTonalIconToggleButtonColorsCached", "getDefaultFilledTonalIconToggleButtonColorsCached$material3", "setDefaultFilledTonalIconToggleButtonColorsCached$material3", "defaultOutlinedIconButtonColorsCached", "getDefaultOutlinedIconButtonColorsCached$material3", "setDefaultOutlinedIconButtonColorsCached$material3", "defaultOutlinedIconButtonVibrantColorsCached", "getDefaultOutlinedIconButtonVibrantColorsCached$material3", "setDefaultOutlinedIconButtonVibrantColorsCached$material3", "defaultOutlinedIconToggleButtonColorsCached", "getDefaultOutlinedIconToggleButtonColorsCached$material3", "setDefaultOutlinedIconToggleButtonColorsCached$material3", "defaultOutlinedIconToggleButtonVibrantColorsCached", "getDefaultOutlinedIconToggleButtonVibrantColorsCached$material3", "setDefaultOutlinedIconToggleButtonVibrantColorsCached$material3", "defaultListItemColorsCached", "Landroidx/compose/material3/ListItemColors;", "getDefaultListItemColorsCached$material3", "()Landroidx/compose/material3/ListItemColors;", "setDefaultListItemColorsCached$material3", "(Landroidx/compose/material3/ListItemColors;)V", "defaultMenuItemColorsCached", "Landroidx/compose/material3/MenuItemColors;", "getDefaultMenuItemColorsCached$material3", "()Landroidx/compose/material3/MenuItemColors;", "setDefaultMenuItemColorsCached$material3", "(Landroidx/compose/material3/MenuItemColors;)V", "defaultNavigationBarItemColorsCached", "Landroidx/compose/material3/NavigationBarItemColors;", "getDefaultNavigationBarItemColorsCached$material3", "()Landroidx/compose/material3/NavigationBarItemColors;", "setDefaultNavigationBarItemColorsCached$material3", "(Landroidx/compose/material3/NavigationBarItemColors;)V", "defaultShortNavigationBarItemColorsCached", "Landroidx/compose/material3/NavigationItemColors;", "getDefaultShortNavigationBarItemColorsCached$material3", "()Landroidx/compose/material3/NavigationItemColors;", "setDefaultShortNavigationBarItemColorsCached$material3", "(Landroidx/compose/material3/NavigationItemColors;)V", "defaultNavigationRailItemColorsCached", "Landroidx/compose/material3/NavigationRailItemColors;", "getDefaultNavigationRailItemColorsCached$material3", "()Landroidx/compose/material3/NavigationRailItemColors;", "setDefaultNavigationRailItemColorsCached$material3", "(Landroidx/compose/material3/NavigationRailItemColors;)V", "defaultWideWideNavigationRailColorsCached", "Landroidx/compose/material3/WideNavigationRailColors;", "getDefaultWideWideNavigationRailColorsCached$material3", "()Landroidx/compose/material3/WideNavigationRailColors;", "setDefaultWideWideNavigationRailColorsCached$material3", "(Landroidx/compose/material3/WideNavigationRailColors;)V", "defaultWideNavigationRailItemColorsCached", "getDefaultWideNavigationRailItemColorsCached$material3", "setDefaultWideNavigationRailItemColorsCached$material3", "defaultRadioButtonColorsCached", "Landroidx/compose/material3/RadioButtonColors;", "getDefaultRadioButtonColorsCached$material3", "()Landroidx/compose/material3/RadioButtonColors;", "setDefaultRadioButtonColorsCached$material3", "(Landroidx/compose/material3/RadioButtonColors;)V", "defaultSegmentedButtonColorsCached", "Landroidx/compose/material3/SegmentedButtonColors;", "getDefaultSegmentedButtonColorsCached$material3", "()Landroidx/compose/material3/SegmentedButtonColors;", "setDefaultSegmentedButtonColorsCached$material3", "(Landroidx/compose/material3/SegmentedButtonColors;)V", "defaultSliderColorsCached", "Landroidx/compose/material3/SliderColors;", "getDefaultSliderColorsCached$material3", "()Landroidx/compose/material3/SliderColors;", "setDefaultSliderColorsCached$material3", "(Landroidx/compose/material3/SliderColors;)V", "defaultSwitchColorsCached", "Landroidx/compose/material3/SwitchColors;", "getDefaultSwitchColorsCached$material3", "()Landroidx/compose/material3/SwitchColors;", "setDefaultSwitchColorsCached$material3", "(Landroidx/compose/material3/SwitchColors;)V", "defaultOutlinedTextFieldColorsCached", "Landroidx/compose/material3/TextFieldColors;", "getDefaultOutlinedTextFieldColorsCached$material3", "()Landroidx/compose/material3/TextFieldColors;", "setDefaultOutlinedTextFieldColorsCached$material3", "(Landroidx/compose/material3/TextFieldColors;)V", "defaultTextFieldColorsCached", "getDefaultTextFieldColorsCached$material3", "setDefaultTextFieldColorsCached$material3", "defaultTimePickerColorsCached", "Landroidx/compose/material3/TimePickerColors;", "getDefaultTimePickerColorsCached$material3$annotations", "getDefaultTimePickerColorsCached$material3", "()Landroidx/compose/material3/TimePickerColors;", "setDefaultTimePickerColorsCached$material3", "(Landroidx/compose/material3/TimePickerColors;)V", "defaultRichTooltipColorsCached", "Landroidx/compose/material3/RichTooltipColors;", "getDefaultRichTooltipColorsCached$material3$annotations", "getDefaultRichTooltipColorsCached$material3", "()Landroidx/compose/material3/RichTooltipColors;", "setDefaultRichTooltipColorsCached$material3", "(Landroidx/compose/material3/RichTooltipColors;)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ColorScheme {
    public static final int $stable = 0;
    private final long background;
    private ChipColors defaultAssistChipColorsCached;
    private ButtonColors defaultButtonColorsCached;
    private CardColors defaultCardColorsCached;
    private CheckboxColors defaultCheckboxColorsCached;
    private DatePickerColors defaultDatePickerColorsCached;
    private ChipColors defaultElevatedAssistChipColorsCached;
    private ButtonColors defaultElevatedButtonColorsCached;
    private CardColors defaultElevatedCardColorsCached;
    private SelectableChipColors defaultElevatedFilterChipColorsCached;
    private ChipColors defaultElevatedSuggestionChipColorsCached;
    private IconButtonColors defaultFilledIconButtonColorsCached;
    private IconToggleButtonColors defaultFilledIconToggleButtonColorsCached;
    private ButtonColors defaultFilledTonalButtonColorsCached;
    private IconButtonColors defaultFilledTonalIconButtonColorsCached;
    private IconToggleButtonColors defaultFilledTonalIconToggleButtonColorsCached;
    private SelectableChipColors defaultFilterChipColorsCached;
    private IconButtonColors defaultIconButtonColorsCached;
    private IconButtonColors defaultIconButtonVibrantColorsCached;
    private IconToggleButtonColors defaultIconToggleButtonColorsCached;
    private IconToggleButtonColors defaultIconToggleButtonVibrantColorsCached;
    private SelectableChipColors defaultInputChipColorsCached;
    private ListItemColors defaultListItemColorsCached;
    private MenuItemColors defaultMenuItemColorsCached;
    private NavigationBarItemColors defaultNavigationBarItemColorsCached;
    private NavigationRailItemColors defaultNavigationRailItemColorsCached;
    private ButtonColors defaultOutlinedButtonColorsCached;
    private CardColors defaultOutlinedCardColorsCached;
    private IconButtonColors defaultOutlinedIconButtonColorsCached;
    private IconButtonColors defaultOutlinedIconButtonVibrantColorsCached;
    private IconToggleButtonColors defaultOutlinedIconToggleButtonColorsCached;
    private IconToggleButtonColors defaultOutlinedIconToggleButtonVibrantColorsCached;
    private TextFieldColors defaultOutlinedTextFieldColorsCached;
    private RadioButtonColors defaultRadioButtonColorsCached;
    private RichTooltipColors defaultRichTooltipColorsCached;
    private SegmentedButtonColors defaultSegmentedButtonColorsCached;
    private NavigationItemColors defaultShortNavigationBarItemColorsCached;
    private SliderColors defaultSliderColorsCached;
    private ChipColors defaultSuggestionChipColorsCached;
    private SwitchColors defaultSwitchColorsCached;
    private ButtonColors defaultTextButtonColorsCached;
    private TextFieldColors defaultTextFieldColorsCached;
    private TimePickerColors defaultTimePickerColorsCached;
    private TopAppBarColors defaultTopAppBarColorsCached;
    private DragHandleColors defaultVerticalDragHandleColorsCached;
    private NavigationItemColors defaultWideNavigationRailItemColorsCached;
    private WideNavigationRailColors defaultWideWideNavigationRailColorsCached;
    private final long error;
    private final long errorContainer;
    private final long inverseOnSurface;
    private final long inversePrimary;
    private final long inverseSurface;
    private final long onBackground;
    private final long onError;
    private final long onErrorContainer;
    private final long onPrimary;
    private final long onPrimaryContainer;
    private final long onPrimaryFixed;
    private final long onPrimaryFixedVariant;
    private final long onSecondary;
    private final long onSecondaryContainer;
    private final long onSecondaryFixed;
    private final long onSecondaryFixedVariant;
    private final long onSurface;
    private final long onSurfaceVariant;
    private final long onTertiary;
    private final long onTertiaryContainer;
    private final long onTertiaryFixed;
    private final long onTertiaryFixedVariant;
    private final long outline;
    private final long outlineVariant;
    private final long primary;
    private final long primaryContainer;
    private final long primaryFixed;
    private final long primaryFixedDim;
    private final long scrim;
    private final long secondary;
    private final long secondaryContainer;
    private final long secondaryFixed;
    private final long secondaryFixedDim;
    private final long surface;
    private final long surfaceBright;
    private final long surfaceContainer;
    private final long surfaceContainerHigh;
    private final long surfaceContainerHighest;
    private final long surfaceContainerLow;
    private final long surfaceContainerLowest;
    private final long surfaceDim;
    private final long surfaceTint;
    private final long surfaceVariant;
    private final long tertiary;
    private final long tertiaryContainer;
    private final long tertiaryFixed;
    private final long tertiaryFixedDim;

    private ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48) {
        this.primary = j;
        this.onPrimary = j2;
        this.primaryContainer = j3;
        this.onPrimaryContainer = j4;
        this.inversePrimary = j5;
        this.secondary = j6;
        this.onSecondary = j7;
        this.secondaryContainer = j8;
        this.onSecondaryContainer = j9;
        this.tertiary = j10;
        this.onTertiary = j11;
        this.tertiaryContainer = j12;
        this.onTertiaryContainer = j13;
        this.background = j14;
        this.onBackground = j15;
        this.surface = j16;
        this.onSurface = j17;
        this.surfaceVariant = j18;
        this.onSurfaceVariant = j19;
        this.surfaceTint = j20;
        this.inverseSurface = j21;
        this.inverseOnSurface = j22;
        this.error = j23;
        this.onError = j24;
        this.errorContainer = j25;
        this.onErrorContainer = j26;
        this.outline = j27;
        this.outlineVariant = j28;
        this.scrim = j29;
        this.surfaceBright = j30;
        this.surfaceDim = j31;
        this.surfaceContainer = j32;
        this.surfaceContainerHigh = j33;
        this.surfaceContainerHighest = j34;
        this.surfaceContainerLow = j35;
        this.surfaceContainerLowest = j36;
        this.primaryFixed = j37;
        this.primaryFixedDim = j38;
        this.onPrimaryFixed = j39;
        this.onPrimaryFixedVariant = j40;
        this.secondaryFixed = j41;
        this.secondaryFixedDim = j42;
        this.onSecondaryFixed = j43;
        this.onSecondaryFixedVariant = j44;
        this.tertiaryFixed = j45;
        this.tertiaryFixedDim = j46;
        this.onTertiaryFixed = j47;
        this.onTertiaryFixedVariant = j48;
    }

    /* JADX INFO: renamed from: copy-C-Xl9yA$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m222copyCXl9yA$default(ColorScheme colorScheme, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, int i, int i2, Object obj) {
        long j37;
        long j38;
        long j39 = (i & 1) != 0 ? colorScheme.primary : j;
        long j40 = (i & 2) != 0 ? colorScheme.onPrimary : j2;
        long j41 = (i & 4) != 0 ? colorScheme.primaryContainer : j3;
        long j42 = (i & 8) != 0 ? colorScheme.onPrimaryContainer : j4;
        long j43 = (i & 16) != 0 ? colorScheme.inversePrimary : j5;
        long j44 = (i & 32) != 0 ? colorScheme.secondary : j6;
        long j45 = (i & 64) != 0 ? colorScheme.onSecondary : j7;
        long j46 = j39;
        long j47 = (i & 128) != 0 ? colorScheme.secondaryContainer : j8;
        long j48 = (i & 256) != 0 ? colorScheme.onSecondaryContainer : j9;
        long j49 = (i & 512) != 0 ? colorScheme.tertiary : j10;
        long j50 = (i & 1024) != 0 ? colorScheme.onTertiary : j11;
        long j51 = (i & 2048) != 0 ? colorScheme.tertiaryContainer : j12;
        long j52 = (i & 4096) != 0 ? colorScheme.onTertiaryContainer : j13;
        long j53 = (i & 8192) != 0 ? colorScheme.background : j14;
        long j54 = (i & 16384) != 0 ? colorScheme.onBackground : j15;
        long j55 = (i & 32768) != 0 ? colorScheme.surface : j16;
        long j56 = (i & 65536) != 0 ? colorScheme.onSurface : j17;
        long j57 = (i & 131072) != 0 ? colorScheme.surfaceVariant : j18;
        long j58 = (i & 262144) != 0 ? colorScheme.onSurfaceVariant : j19;
        long j59 = (i & 524288) != 0 ? colorScheme.surfaceTint : j20;
        long j60 = (i & 1048576) != 0 ? colorScheme.inverseSurface : j21;
        long j61 = (i & 2097152) != 0 ? colorScheme.inverseOnSurface : j22;
        long j62 = (i & 4194304) != 0 ? colorScheme.error : j23;
        long j63 = (i & 8388608) != 0 ? colorScheme.onError : j24;
        long j64 = (i & 16777216) != 0 ? colorScheme.errorContainer : j25;
        long j65 = (i & 33554432) != 0 ? colorScheme.onErrorContainer : j26;
        long j66 = (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? colorScheme.outline : j27;
        long j67 = (i & 134217728) != 0 ? colorScheme.outlineVariant : j28;
        long j68 = (i & 268435456) != 0 ? colorScheme.scrim : j29;
        long j69 = (i & 536870912) != 0 ? colorScheme.surfaceBright : j30;
        long j70 = (i & 1073741824) != 0 ? colorScheme.surfaceDim : j31;
        long j71 = (i & Integer.MIN_VALUE) != 0 ? colorScheme.surfaceContainer : j32;
        long j72 = (i2 & 1) != 0 ? colorScheme.surfaceContainerHigh : j33;
        long j73 = (i2 & 2) != 0 ? colorScheme.surfaceContainerHighest : j34;
        long j74 = (i2 & 4) != 0 ? colorScheme.surfaceContainerLow : j35;
        if ((i2 & 8) != 0) {
            j38 = j74;
            j37 = colorScheme.surfaceContainerLowest;
        } else {
            j37 = j36;
            j38 = j74;
        }
        return colorScheme.m225copyCXl9yA(j46, j40, j41, j42, j43, j44, j45, j47, j48, j49, j50, j51, j52, j53, j54, j55, j56, j57, j58, j59, j60, j61, j62, j63, j64, j65, j66, j67, j68, j69, j70, j71, j72, j73, j38, j37);
    }

    /* JADX INFO: renamed from: copy-G1PFc-w$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m223copyG1PFcw$default(ColorScheme colorScheme, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, int i, Object obj) {
        long j30;
        long j31;
        long j32 = (i & 1) != 0 ? colorScheme.primary : j;
        long j33 = (i & 2) != 0 ? colorScheme.onPrimary : j2;
        long j34 = (i & 4) != 0 ? colorScheme.primaryContainer : j3;
        long j35 = (i & 8) != 0 ? colorScheme.onPrimaryContainer : j4;
        long j36 = (i & 16) != 0 ? colorScheme.inversePrimary : j5;
        long j37 = (i & 32) != 0 ? colorScheme.secondary : j6;
        long j38 = (i & 64) != 0 ? colorScheme.onSecondary : j7;
        long j39 = j32;
        long j40 = (i & 128) != 0 ? colorScheme.secondaryContainer : j8;
        long j41 = (i & 256) != 0 ? colorScheme.onSecondaryContainer : j9;
        long j42 = (i & 512) != 0 ? colorScheme.tertiary : j10;
        long j43 = (i & 1024) != 0 ? colorScheme.onTertiary : j11;
        long j44 = (i & 2048) != 0 ? colorScheme.tertiaryContainer : j12;
        long j45 = (i & 4096) != 0 ? colorScheme.onTertiaryContainer : j13;
        long j46 = (i & 8192) != 0 ? colorScheme.background : j14;
        long j47 = (i & 16384) != 0 ? colorScheme.onBackground : j15;
        long j48 = (i & 32768) != 0 ? colorScheme.surface : j16;
        long j49 = (i & 65536) != 0 ? colorScheme.onSurface : j17;
        long j50 = (i & 131072) != 0 ? colorScheme.surfaceVariant : j18;
        long j51 = (i & 262144) != 0 ? colorScheme.onSurfaceVariant : j19;
        long j52 = (i & 524288) != 0 ? colorScheme.surfaceTint : j20;
        long j53 = (i & 1048576) != 0 ? colorScheme.inverseSurface : j21;
        long j54 = (i & 2097152) != 0 ? colorScheme.inverseOnSurface : j22;
        long j55 = (i & 4194304) != 0 ? colorScheme.error : j23;
        long j56 = (i & 8388608) != 0 ? colorScheme.onError : j24;
        long j57 = (i & 16777216) != 0 ? colorScheme.errorContainer : j25;
        long j58 = (i & 33554432) != 0 ? colorScheme.onErrorContainer : j26;
        long j59 = (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? colorScheme.outline : j27;
        long j60 = (i & 134217728) != 0 ? colorScheme.outlineVariant : j28;
        if ((i & 268435456) != 0) {
            j31 = j60;
            j30 = colorScheme.scrim;
        } else {
            j30 = j29;
            j31 = j60;
        }
        return colorScheme.m226copyG1PFcw(j39, j33, j34, j35, j36, j37, j38, j40, j41, j42, j43, j44, j45, j46, j47, j48, j49, j50, j51, j52, j53, j54, j55, j56, j57, j58, j59, j31, j30);
    }

    /* JADX INFO: renamed from: copy-_VG5OTI$default, reason: not valid java name */
    public static /* synthetic */ ColorScheme m224copy_VG5OTI$default(ColorScheme colorScheme, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, int i, int i2, Object obj) {
        long j49;
        long j50;
        long j51 = (i & 1) != 0 ? colorScheme.primary : j;
        long j52 = (i & 2) != 0 ? colorScheme.onPrimary : j2;
        long j53 = (i & 4) != 0 ? colorScheme.primaryContainer : j3;
        long j54 = (i & 8) != 0 ? colorScheme.onPrimaryContainer : j4;
        long j55 = (i & 16) != 0 ? colorScheme.inversePrimary : j5;
        long j56 = (i & 32) != 0 ? colorScheme.secondary : j6;
        long j57 = (i & 64) != 0 ? colorScheme.onSecondary : j7;
        long j58 = (i & 128) != 0 ? colorScheme.secondaryContainer : j8;
        long j59 = (i & 256) != 0 ? colorScheme.onSecondaryContainer : j9;
        long j60 = (i & 512) != 0 ? colorScheme.tertiary : j10;
        long j61 = (i & 1024) != 0 ? colorScheme.onTertiary : j11;
        long j62 = (i & 2048) != 0 ? colorScheme.tertiaryContainer : j12;
        long j63 = (i & 4096) != 0 ? colorScheme.onTertiaryContainer : j13;
        long j64 = (i & 8192) != 0 ? colorScheme.background : j14;
        long j65 = (i & 16384) != 0 ? colorScheme.onBackground : j15;
        long j66 = (i & 32768) != 0 ? colorScheme.surface : j16;
        long j67 = (i & 65536) != 0 ? colorScheme.onSurface : j17;
        long j68 = (i & 131072) != 0 ? colorScheme.surfaceVariant : j18;
        long j69 = (i & 262144) != 0 ? colorScheme.onSurfaceVariant : j19;
        long j70 = (i & 524288) != 0 ? colorScheme.surfaceTint : j20;
        long j71 = (i & 1048576) != 0 ? colorScheme.inverseSurface : j21;
        long j72 = (i & 2097152) != 0 ? colorScheme.inverseOnSurface : j22;
        long j73 = (i & 4194304) != 0 ? colorScheme.error : j23;
        long j74 = (i & 8388608) != 0 ? colorScheme.onError : j24;
        long j75 = (i & 16777216) != 0 ? colorScheme.errorContainer : j25;
        long j76 = (i & 33554432) != 0 ? colorScheme.onErrorContainer : j26;
        long j77 = (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? colorScheme.outline : j27;
        long j78 = (i & 134217728) != 0 ? colorScheme.outlineVariant : j28;
        long j79 = (i & 268435456) != 0 ? colorScheme.scrim : j29;
        long j80 = (i & 536870912) != 0 ? colorScheme.surfaceBright : j30;
        long j81 = (i & 1073741824) != 0 ? colorScheme.surfaceDim : j31;
        long j82 = (i & Integer.MIN_VALUE) != 0 ? colorScheme.surfaceContainer : j32;
        long j83 = (i2 & 1) != 0 ? colorScheme.surfaceContainerHigh : j33;
        long j84 = (i2 & 2) != 0 ? colorScheme.surfaceContainerHighest : j34;
        long j85 = (i2 & 4) != 0 ? colorScheme.surfaceContainerLow : j35;
        long j86 = (i2 & 8) != 0 ? colorScheme.surfaceContainerLowest : j36;
        long j87 = (i2 & 16) != 0 ? colorScheme.primaryFixed : j37;
        long j88 = (i2 & 32) != 0 ? colorScheme.primaryFixedDim : j38;
        long j89 = (i2 & 64) != 0 ? colorScheme.onPrimaryFixed : j39;
        long j90 = (i2 & 128) != 0 ? colorScheme.onPrimaryFixedVariant : j40;
        long j91 = (i2 & 256) != 0 ? colorScheme.secondaryFixed : j41;
        long j92 = (i2 & 512) != 0 ? colorScheme.secondaryFixedDim : j42;
        long j93 = (i2 & 1024) != 0 ? colorScheme.onSecondaryFixed : j43;
        long j94 = (i2 & 2048) != 0 ? colorScheme.onSecondaryFixedVariant : j44;
        long j95 = (i2 & 4096) != 0 ? colorScheme.tertiaryFixed : j45;
        long j96 = (i2 & 8192) != 0 ? colorScheme.tertiaryFixedDim : j46;
        long j97 = (i2 & 16384) != 0 ? colorScheme.onTertiaryFixed : j47;
        if ((i2 & 32768) != 0) {
            j50 = j97;
            j49 = colorScheme.onTertiaryFixedVariant;
        } else {
            j49 = j48;
            j50 = j97;
        }
        return colorScheme.m227copy_VG5OTI(j51, j52, j53, j54, j55, j56, j57, j58, j59, j60, j61, j62, j63, j64, j65, j66, j67, j68, j69, j70, j71, j72, j73, j74, j75, j76, j77, j78, j79, j80, j81, j82, j83, j84, j85, j86, j87, j88, j89, j90, j91, j92, j93, j94, j95, j96, j50, j49);
    }

    public static /* synthetic */ void getDefaultDatePickerColorsCached$material3$annotations() {
    }

    public static /* synthetic */ void getDefaultRichTooltipColorsCached$material3$annotations() {
    }

    public static /* synthetic */ void getDefaultTimePickerColorsCached$material3$annotations() {
    }

    public static /* synthetic */ void getDefaultTopAppBarColorsCached$material3$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional fixed roles instead")
    /* JADX INFO: renamed from: copy-C-Xl9yA, reason: not valid java name */
    public final /* synthetic */ ColorScheme m225copyCXl9yA(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim, long surfaceBright, long surfaceDim, long surfaceContainer, long surfaceContainerHigh, long surfaceContainerHighest, long surfaceContainerLow, long surfaceContainerLowest) {
        return m224copy_VG5OTI$default(this, primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, surfaceBright, surfaceDim, surfaceContainer, surfaceContainerHigh, surfaceContainerHighest, surfaceContainerLow, surfaceContainerLowest, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0, 65520, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with additional fixed roles instead")
    /* JADX INFO: renamed from: copy-G1PFc-w, reason: not valid java name */
    public final /* synthetic */ ColorScheme m226copyG1PFcw(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim) {
        return m224copy_VG5OTI$default(this, primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -536870912, SupportMenu.USER_MASK, null);
    }

    /* JADX INFO: renamed from: copy-_VG5OTI, reason: not valid java name */
    public final ColorScheme m227copy_VG5OTI(long primary, long onPrimary, long primaryContainer, long onPrimaryContainer, long inversePrimary, long secondary, long onSecondary, long secondaryContainer, long onSecondaryContainer, long tertiary, long onTertiary, long tertiaryContainer, long onTertiaryContainer, long background, long onBackground, long surface, long onSurface, long surfaceVariant, long onSurfaceVariant, long surfaceTint, long inverseSurface, long inverseOnSurface, long error, long onError, long errorContainer, long onErrorContainer, long outline, long outlineVariant, long scrim, long surfaceBright, long surfaceDim, long surfaceContainer, long surfaceContainerHigh, long surfaceContainerHighest, long surfaceContainerLow, long surfaceContainerLowest, long primaryFixed, long primaryFixedDim, long onPrimaryFixed, long onPrimaryFixedVariant, long secondaryFixed, long secondaryFixedDim, long onSecondaryFixed, long onSecondaryFixedVariant, long tertiaryFixed, long tertiaryFixedDim, long onTertiaryFixed, long onTertiaryFixedVariant) {
        return new ColorScheme(primary, onPrimary, primaryContainer, onPrimaryContainer, inversePrimary, secondary, onSecondary, secondaryContainer, onSecondaryContainer, tertiary, onTertiary, tertiaryContainer, onTertiaryContainer, background, onBackground, surface, onSurface, surfaceVariant, onSurfaceVariant, surfaceTint, inverseSurface, inverseOnSurface, error, onError, errorContainer, onErrorContainer, outline, outlineVariant, scrim, surfaceBright, surfaceDim, surfaceContainer, surfaceContainerHigh, surfaceContainerHighest, surfaceContainerLow, surfaceContainerLowest, primaryFixed, primaryFixedDim, onPrimaryFixed, onPrimaryFixedVariant, secondaryFixed, secondaryFixedDim, onSecondaryFixed, onSecondaryFixedVariant, tertiaryFixed, tertiaryFixedDim, onTertiaryFixed, onTertiaryFixedVariant, null);
    }

    /* JADX INFO: renamed from: getBackground-0d7_KjU, reason: not valid java name and from getter */
    public final long getBackground() {
        return this.background;
    }

    /* JADX INFO: renamed from: getDefaultAssistChipColorsCached$material3, reason: from getter */
    public final ChipColors getDefaultAssistChipColorsCached() {
        return this.defaultAssistChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultButtonColorsCached$material3, reason: from getter */
    public final ButtonColors getDefaultButtonColorsCached() {
        return this.defaultButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultCardColorsCached$material3, reason: from getter */
    public final CardColors getDefaultCardColorsCached() {
        return this.defaultCardColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultCheckboxColorsCached$material3, reason: from getter */
    public final CheckboxColors getDefaultCheckboxColorsCached() {
        return this.defaultCheckboxColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultDatePickerColorsCached$material3, reason: from getter */
    public final DatePickerColors getDefaultDatePickerColorsCached() {
        return this.defaultDatePickerColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultElevatedAssistChipColorsCached$material3, reason: from getter */
    public final ChipColors getDefaultElevatedAssistChipColorsCached() {
        return this.defaultElevatedAssistChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultElevatedButtonColorsCached$material3, reason: from getter */
    public final ButtonColors getDefaultElevatedButtonColorsCached() {
        return this.defaultElevatedButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultElevatedCardColorsCached$material3, reason: from getter */
    public final CardColors getDefaultElevatedCardColorsCached() {
        return this.defaultElevatedCardColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultElevatedFilterChipColorsCached$material3, reason: from getter */
    public final SelectableChipColors getDefaultElevatedFilterChipColorsCached() {
        return this.defaultElevatedFilterChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultElevatedSuggestionChipColorsCached$material3, reason: from getter */
    public final ChipColors getDefaultElevatedSuggestionChipColorsCached() {
        return this.defaultElevatedSuggestionChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilledIconButtonColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultFilledIconButtonColorsCached() {
        return this.defaultFilledIconButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilledIconToggleButtonColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultFilledIconToggleButtonColorsCached() {
        return this.defaultFilledIconToggleButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilledTonalButtonColorsCached$material3, reason: from getter */
    public final ButtonColors getDefaultFilledTonalButtonColorsCached() {
        return this.defaultFilledTonalButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilledTonalIconButtonColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultFilledTonalIconButtonColorsCached() {
        return this.defaultFilledTonalIconButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilledTonalIconToggleButtonColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultFilledTonalIconToggleButtonColorsCached() {
        return this.defaultFilledTonalIconToggleButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultFilterChipColorsCached$material3, reason: from getter */
    public final SelectableChipColors getDefaultFilterChipColorsCached() {
        return this.defaultFilterChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultIconButtonColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultIconButtonColorsCached() {
        return this.defaultIconButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultIconButtonVibrantColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultIconButtonVibrantColorsCached() {
        return this.defaultIconButtonVibrantColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultIconToggleButtonColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultIconToggleButtonColorsCached() {
        return this.defaultIconToggleButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultIconToggleButtonVibrantColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultIconToggleButtonVibrantColorsCached() {
        return this.defaultIconToggleButtonVibrantColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultInputChipColorsCached$material3, reason: from getter */
    public final SelectableChipColors getDefaultInputChipColorsCached() {
        return this.defaultInputChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultListItemColorsCached$material3, reason: from getter */
    public final ListItemColors getDefaultListItemColorsCached() {
        return this.defaultListItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultMenuItemColorsCached$material3, reason: from getter */
    public final MenuItemColors getDefaultMenuItemColorsCached() {
        return this.defaultMenuItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultNavigationBarItemColorsCached$material3, reason: from getter */
    public final NavigationBarItemColors getDefaultNavigationBarItemColorsCached() {
        return this.defaultNavigationBarItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultNavigationRailItemColorsCached$material3, reason: from getter */
    public final NavigationRailItemColors getDefaultNavigationRailItemColorsCached() {
        return this.defaultNavigationRailItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedButtonColorsCached$material3, reason: from getter */
    public final ButtonColors getDefaultOutlinedButtonColorsCached() {
        return this.defaultOutlinedButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedCardColorsCached$material3, reason: from getter */
    public final CardColors getDefaultOutlinedCardColorsCached() {
        return this.defaultOutlinedCardColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedIconButtonColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultOutlinedIconButtonColorsCached() {
        return this.defaultOutlinedIconButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedIconButtonVibrantColorsCached$material3, reason: from getter */
    public final IconButtonColors getDefaultOutlinedIconButtonVibrantColorsCached() {
        return this.defaultOutlinedIconButtonVibrantColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedIconToggleButtonColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultOutlinedIconToggleButtonColorsCached() {
        return this.defaultOutlinedIconToggleButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedIconToggleButtonVibrantColorsCached$material3, reason: from getter */
    public final IconToggleButtonColors getDefaultOutlinedIconToggleButtonVibrantColorsCached() {
        return this.defaultOutlinedIconToggleButtonVibrantColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultOutlinedTextFieldColorsCached$material3, reason: from getter */
    public final TextFieldColors getDefaultOutlinedTextFieldColorsCached() {
        return this.defaultOutlinedTextFieldColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultRadioButtonColorsCached$material3, reason: from getter */
    public final RadioButtonColors getDefaultRadioButtonColorsCached() {
        return this.defaultRadioButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultRichTooltipColorsCached$material3, reason: from getter */
    public final RichTooltipColors getDefaultRichTooltipColorsCached() {
        return this.defaultRichTooltipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultSegmentedButtonColorsCached$material3, reason: from getter */
    public final SegmentedButtonColors getDefaultSegmentedButtonColorsCached() {
        return this.defaultSegmentedButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultShortNavigationBarItemColorsCached$material3, reason: from getter */
    public final NavigationItemColors getDefaultShortNavigationBarItemColorsCached() {
        return this.defaultShortNavigationBarItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultSliderColorsCached$material3, reason: from getter */
    public final SliderColors getDefaultSliderColorsCached() {
        return this.defaultSliderColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultSuggestionChipColorsCached$material3, reason: from getter */
    public final ChipColors getDefaultSuggestionChipColorsCached() {
        return this.defaultSuggestionChipColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultSwitchColorsCached$material3, reason: from getter */
    public final SwitchColors getDefaultSwitchColorsCached() {
        return this.defaultSwitchColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultTextButtonColorsCached$material3, reason: from getter */
    public final ButtonColors getDefaultTextButtonColorsCached() {
        return this.defaultTextButtonColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultTextFieldColorsCached$material3, reason: from getter */
    public final TextFieldColors getDefaultTextFieldColorsCached() {
        return this.defaultTextFieldColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultTimePickerColorsCached$material3, reason: from getter */
    public final TimePickerColors getDefaultTimePickerColorsCached() {
        return this.defaultTimePickerColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultTopAppBarColorsCached$material3, reason: from getter */
    public final TopAppBarColors getDefaultTopAppBarColorsCached() {
        return this.defaultTopAppBarColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultVerticalDragHandleColorsCached$material3, reason: from getter */
    public final DragHandleColors getDefaultVerticalDragHandleColorsCached() {
        return this.defaultVerticalDragHandleColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultWideNavigationRailItemColorsCached$material3, reason: from getter */
    public final NavigationItemColors getDefaultWideNavigationRailItemColorsCached() {
        return this.defaultWideNavigationRailItemColorsCached;
    }

    /* JADX INFO: renamed from: getDefaultWideWideNavigationRailColorsCached$material3, reason: from getter */
    public final WideNavigationRailColors getDefaultWideWideNavigationRailColorsCached() {
        return this.defaultWideWideNavigationRailColorsCached;
    }

    /* JADX INFO: renamed from: getError-0d7_KjU, reason: not valid java name and from getter */
    public final long getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: getErrorContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getErrorContainer() {
        return this.errorContainer;
    }

    /* JADX INFO: renamed from: getInverseOnSurface-0d7_KjU, reason: not valid java name and from getter */
    public final long getInverseOnSurface() {
        return this.inverseOnSurface;
    }

    /* JADX INFO: renamed from: getInversePrimary-0d7_KjU, reason: not valid java name and from getter */
    public final long getInversePrimary() {
        return this.inversePrimary;
    }

    /* JADX INFO: renamed from: getInverseSurface-0d7_KjU, reason: not valid java name and from getter */
    public final long getInverseSurface() {
        return this.inverseSurface;
    }

    /* JADX INFO: renamed from: getOnBackground-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnBackground() {
        return this.onBackground;
    }

    /* JADX INFO: renamed from: getOnError-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnError() {
        return this.onError;
    }

    /* JADX INFO: renamed from: getOnErrorContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnErrorContainer() {
        return this.onErrorContainer;
    }

    /* JADX INFO: renamed from: getOnPrimary-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnPrimary() {
        return this.onPrimary;
    }

    /* JADX INFO: renamed from: getOnPrimaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnPrimaryContainer() {
        return this.onPrimaryContainer;
    }

    /* JADX INFO: renamed from: getOnPrimaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnPrimaryFixed() {
        return this.onPrimaryFixed;
    }

    /* JADX INFO: renamed from: getOnPrimaryFixedVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnPrimaryFixedVariant() {
        return this.onPrimaryFixedVariant;
    }

    /* JADX INFO: renamed from: getOnSecondary-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSecondary() {
        return this.onSecondary;
    }

    /* JADX INFO: renamed from: getOnSecondaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSecondaryContainer() {
        return this.onSecondaryContainer;
    }

    /* JADX INFO: renamed from: getOnSecondaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSecondaryFixed() {
        return this.onSecondaryFixed;
    }

    /* JADX INFO: renamed from: getOnSecondaryFixedVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSecondaryFixedVariant() {
        return this.onSecondaryFixedVariant;
    }

    /* JADX INFO: renamed from: getOnSurface-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSurface() {
        return this.onSurface;
    }

    /* JADX INFO: renamed from: getOnSurfaceVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnSurfaceVariant() {
        return this.onSurfaceVariant;
    }

    /* JADX INFO: renamed from: getOnTertiary-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnTertiary() {
        return this.onTertiary;
    }

    /* JADX INFO: renamed from: getOnTertiaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnTertiaryContainer() {
        return this.onTertiaryContainer;
    }

    /* JADX INFO: renamed from: getOnTertiaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnTertiaryFixed() {
        return this.onTertiaryFixed;
    }

    /* JADX INFO: renamed from: getOnTertiaryFixedVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getOnTertiaryFixedVariant() {
        return this.onTertiaryFixedVariant;
    }

    /* JADX INFO: renamed from: getOutline-0d7_KjU, reason: not valid java name and from getter */
    public final long getOutline() {
        return this.outline;
    }

    /* JADX INFO: renamed from: getOutlineVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getOutlineVariant() {
        return this.outlineVariant;
    }

    /* JADX INFO: renamed from: getPrimary-0d7_KjU, reason: not valid java name and from getter */
    public final long getPrimary() {
        return this.primary;
    }

    /* JADX INFO: renamed from: getPrimaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getPrimaryContainer() {
        return this.primaryContainer;
    }

    /* JADX INFO: renamed from: getPrimaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getPrimaryFixed() {
        return this.primaryFixed;
    }

    /* JADX INFO: renamed from: getPrimaryFixedDim-0d7_KjU, reason: not valid java name and from getter */
    public final long getPrimaryFixedDim() {
        return this.primaryFixedDim;
    }

    /* JADX INFO: renamed from: getScrim-0d7_KjU, reason: not valid java name and from getter */
    public final long getScrim() {
        return this.scrim;
    }

    /* JADX INFO: renamed from: getSecondary-0d7_KjU, reason: not valid java name and from getter */
    public final long getSecondary() {
        return this.secondary;
    }

    /* JADX INFO: renamed from: getSecondaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getSecondaryContainer() {
        return this.secondaryContainer;
    }

    /* JADX INFO: renamed from: getSecondaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getSecondaryFixed() {
        return this.secondaryFixed;
    }

    /* JADX INFO: renamed from: getSecondaryFixedDim-0d7_KjU, reason: not valid java name and from getter */
    public final long getSecondaryFixedDim() {
        return this.secondaryFixedDim;
    }

    /* JADX INFO: renamed from: getSurface-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurface() {
        return this.surface;
    }

    /* JADX INFO: renamed from: getSurfaceBright-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceBright() {
        return this.surfaceBright;
    }

    /* JADX INFO: renamed from: getSurfaceContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceContainer() {
        return this.surfaceContainer;
    }

    /* JADX INFO: renamed from: getSurfaceContainerHigh-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceContainerHigh() {
        return this.surfaceContainerHigh;
    }

    /* JADX INFO: renamed from: getSurfaceContainerHighest-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceContainerHighest() {
        return this.surfaceContainerHighest;
    }

    /* JADX INFO: renamed from: getSurfaceContainerLow-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceContainerLow() {
        return this.surfaceContainerLow;
    }

    /* JADX INFO: renamed from: getSurfaceContainerLowest-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceContainerLowest() {
        return this.surfaceContainerLowest;
    }

    /* JADX INFO: renamed from: getSurfaceDim-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceDim() {
        return this.surfaceDim;
    }

    /* JADX INFO: renamed from: getSurfaceTint-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceTint() {
        return this.surfaceTint;
    }

    /* JADX INFO: renamed from: getSurfaceVariant-0d7_KjU, reason: not valid java name and from getter */
    public final long getSurfaceVariant() {
        return this.surfaceVariant;
    }

    /* JADX INFO: renamed from: getTertiary-0d7_KjU, reason: not valid java name and from getter */
    public final long getTertiary() {
        return this.tertiary;
    }

    /* JADX INFO: renamed from: getTertiaryContainer-0d7_KjU, reason: not valid java name and from getter */
    public final long getTertiaryContainer() {
        return this.tertiaryContainer;
    }

    /* JADX INFO: renamed from: getTertiaryFixed-0d7_KjU, reason: not valid java name and from getter */
    public final long getTertiaryFixed() {
        return this.tertiaryFixed;
    }

    /* JADX INFO: renamed from: getTertiaryFixedDim-0d7_KjU, reason: not valid java name and from getter */
    public final long getTertiaryFixedDim() {
        return this.tertiaryFixedDim;
    }

    public final void setDefaultAssistChipColorsCached$material3(ChipColors chipColors) {
        this.defaultAssistChipColorsCached = chipColors;
    }

    public final void setDefaultButtonColorsCached$material3(ButtonColors buttonColors) {
        this.defaultButtonColorsCached = buttonColors;
    }

    public final void setDefaultCardColorsCached$material3(CardColors cardColors) {
        this.defaultCardColorsCached = cardColors;
    }

    public final void setDefaultCheckboxColorsCached$material3(CheckboxColors checkboxColors) {
        this.defaultCheckboxColorsCached = checkboxColors;
    }

    public final void setDefaultDatePickerColorsCached$material3(DatePickerColors datePickerColors) {
        this.defaultDatePickerColorsCached = datePickerColors;
    }

    public final void setDefaultElevatedAssistChipColorsCached$material3(ChipColors chipColors) {
        this.defaultElevatedAssistChipColorsCached = chipColors;
    }

    public final void setDefaultElevatedButtonColorsCached$material3(ButtonColors buttonColors) {
        this.defaultElevatedButtonColorsCached = buttonColors;
    }

    public final void setDefaultElevatedCardColorsCached$material3(CardColors cardColors) {
        this.defaultElevatedCardColorsCached = cardColors;
    }

    public final void setDefaultElevatedFilterChipColorsCached$material3(SelectableChipColors selectableChipColors) {
        this.defaultElevatedFilterChipColorsCached = selectableChipColors;
    }

    public final void setDefaultElevatedSuggestionChipColorsCached$material3(ChipColors chipColors) {
        this.defaultElevatedSuggestionChipColorsCached = chipColors;
    }

    public final void setDefaultFilledIconButtonColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultFilledIconButtonColorsCached = iconButtonColors;
    }

    public final void setDefaultFilledIconToggleButtonColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultFilledIconToggleButtonColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultFilledTonalButtonColorsCached$material3(ButtonColors buttonColors) {
        this.defaultFilledTonalButtonColorsCached = buttonColors;
    }

    public final void setDefaultFilledTonalIconButtonColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultFilledTonalIconButtonColorsCached = iconButtonColors;
    }

    public final void setDefaultFilledTonalIconToggleButtonColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultFilledTonalIconToggleButtonColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultFilterChipColorsCached$material3(SelectableChipColors selectableChipColors) {
        this.defaultFilterChipColorsCached = selectableChipColors;
    }

    public final void setDefaultIconButtonColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultIconButtonColorsCached = iconButtonColors;
    }

    public final void setDefaultIconButtonVibrantColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultIconButtonVibrantColorsCached = iconButtonColors;
    }

    public final void setDefaultIconToggleButtonColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultIconToggleButtonColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultIconToggleButtonVibrantColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultIconToggleButtonVibrantColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultInputChipColorsCached$material3(SelectableChipColors selectableChipColors) {
        this.defaultInputChipColorsCached = selectableChipColors;
    }

    public final void setDefaultListItemColorsCached$material3(ListItemColors listItemColors) {
        this.defaultListItemColorsCached = listItemColors;
    }

    public final void setDefaultMenuItemColorsCached$material3(MenuItemColors menuItemColors) {
        this.defaultMenuItemColorsCached = menuItemColors;
    }

    public final void setDefaultNavigationBarItemColorsCached$material3(NavigationBarItemColors navigationBarItemColors) {
        this.defaultNavigationBarItemColorsCached = navigationBarItemColors;
    }

    public final void setDefaultNavigationRailItemColorsCached$material3(NavigationRailItemColors navigationRailItemColors) {
        this.defaultNavigationRailItemColorsCached = navigationRailItemColors;
    }

    public final void setDefaultOutlinedButtonColorsCached$material3(ButtonColors buttonColors) {
        this.defaultOutlinedButtonColorsCached = buttonColors;
    }

    public final void setDefaultOutlinedCardColorsCached$material3(CardColors cardColors) {
        this.defaultOutlinedCardColorsCached = cardColors;
    }

    public final void setDefaultOutlinedIconButtonColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultOutlinedIconButtonColorsCached = iconButtonColors;
    }

    public final void setDefaultOutlinedIconButtonVibrantColorsCached$material3(IconButtonColors iconButtonColors) {
        this.defaultOutlinedIconButtonVibrantColorsCached = iconButtonColors;
    }

    public final void setDefaultOutlinedIconToggleButtonColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultOutlinedIconToggleButtonColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultOutlinedIconToggleButtonVibrantColorsCached$material3(IconToggleButtonColors iconToggleButtonColors) {
        this.defaultOutlinedIconToggleButtonVibrantColorsCached = iconToggleButtonColors;
    }

    public final void setDefaultOutlinedTextFieldColorsCached$material3(TextFieldColors textFieldColors) {
        this.defaultOutlinedTextFieldColorsCached = textFieldColors;
    }

    public final void setDefaultRadioButtonColorsCached$material3(RadioButtonColors radioButtonColors) {
        this.defaultRadioButtonColorsCached = radioButtonColors;
    }

    public final void setDefaultRichTooltipColorsCached$material3(RichTooltipColors richTooltipColors) {
        this.defaultRichTooltipColorsCached = richTooltipColors;
    }

    public final void setDefaultSegmentedButtonColorsCached$material3(SegmentedButtonColors segmentedButtonColors) {
        this.defaultSegmentedButtonColorsCached = segmentedButtonColors;
    }

    public final void setDefaultShortNavigationBarItemColorsCached$material3(NavigationItemColors navigationItemColors) {
        this.defaultShortNavigationBarItemColorsCached = navigationItemColors;
    }

    public final void setDefaultSliderColorsCached$material3(SliderColors sliderColors) {
        this.defaultSliderColorsCached = sliderColors;
    }

    public final void setDefaultSuggestionChipColorsCached$material3(ChipColors chipColors) {
        this.defaultSuggestionChipColorsCached = chipColors;
    }

    public final void setDefaultSwitchColorsCached$material3(SwitchColors switchColors) {
        this.defaultSwitchColorsCached = switchColors;
    }

    public final void setDefaultTextButtonColorsCached$material3(ButtonColors buttonColors) {
        this.defaultTextButtonColorsCached = buttonColors;
    }

    public final void setDefaultTextFieldColorsCached$material3(TextFieldColors textFieldColors) {
        this.defaultTextFieldColorsCached = textFieldColors;
    }

    public final void setDefaultTimePickerColorsCached$material3(TimePickerColors timePickerColors) {
        this.defaultTimePickerColorsCached = timePickerColors;
    }

    public final void setDefaultTopAppBarColorsCached$material3(TopAppBarColors topAppBarColors) {
        this.defaultTopAppBarColorsCached = topAppBarColors;
    }

    public final void setDefaultVerticalDragHandleColorsCached$material3(DragHandleColors dragHandleColors) {
        this.defaultVerticalDragHandleColorsCached = dragHandleColors;
    }

    public final void setDefaultWideNavigationRailItemColorsCached$material3(NavigationItemColors navigationItemColors) {
        this.defaultWideNavigationRailItemColorsCached = navigationItemColors;
    }

    public final void setDefaultWideWideNavigationRailColorsCached$material3(WideNavigationRailColors wideNavigationRailColors) {
        this.defaultWideWideNavigationRailColorsCached = wideNavigationRailColors;
    }

    public String toString() {
        return "ColorScheme(primary=" + ((Object) Color.m3142toStringimpl(this.primary)) + "onPrimary=" + ((Object) Color.m3142toStringimpl(this.onPrimary)) + "primaryContainer=" + ((Object) Color.m3142toStringimpl(this.primaryContainer)) + "onPrimaryContainer=" + ((Object) Color.m3142toStringimpl(this.onPrimaryContainer)) + "inversePrimary=" + ((Object) Color.m3142toStringimpl(this.inversePrimary)) + "secondary=" + ((Object) Color.m3142toStringimpl(this.secondary)) + "onSecondary=" + ((Object) Color.m3142toStringimpl(this.onSecondary)) + "secondaryContainer=" + ((Object) Color.m3142toStringimpl(this.secondaryContainer)) + "onSecondaryContainer=" + ((Object) Color.m3142toStringimpl(this.onSecondaryContainer)) + "tertiary=" + ((Object) Color.m3142toStringimpl(this.tertiary)) + "onTertiary=" + ((Object) Color.m3142toStringimpl(this.onTertiary)) + "tertiaryContainer=" + ((Object) Color.m3142toStringimpl(this.tertiaryContainer)) + "onTertiaryContainer=" + ((Object) Color.m3142toStringimpl(this.onTertiaryContainer)) + "background=" + ((Object) Color.m3142toStringimpl(this.background)) + "onBackground=" + ((Object) Color.m3142toStringimpl(this.onBackground)) + "surface=" + ((Object) Color.m3142toStringimpl(this.surface)) + "onSurface=" + ((Object) Color.m3142toStringimpl(this.onSurface)) + "surfaceVariant=" + ((Object) Color.m3142toStringimpl(this.surfaceVariant)) + "onSurfaceVariant=" + ((Object) Color.m3142toStringimpl(this.onSurfaceVariant)) + "surfaceTint=" + ((Object) Color.m3142toStringimpl(this.surfaceTint)) + "inverseSurface=" + ((Object) Color.m3142toStringimpl(this.inverseSurface)) + "inverseOnSurface=" + ((Object) Color.m3142toStringimpl(this.inverseOnSurface)) + "error=" + ((Object) Color.m3142toStringimpl(this.error)) + "onError=" + ((Object) Color.m3142toStringimpl(this.onError)) + "errorContainer=" + ((Object) Color.m3142toStringimpl(this.errorContainer)) + "onErrorContainer=" + ((Object) Color.m3142toStringimpl(this.onErrorContainer)) + "outline=" + ((Object) Color.m3142toStringimpl(this.outline)) + "outlineVariant=" + ((Object) Color.m3142toStringimpl(this.outlineVariant)) + "scrim=" + ((Object) Color.m3142toStringimpl(this.scrim)) + "surfaceBright=" + ((Object) Color.m3142toStringimpl(this.surfaceBright)) + "surfaceDim=" + ((Object) Color.m3142toStringimpl(this.surfaceDim)) + "surfaceContainer=" + ((Object) Color.m3142toStringimpl(this.surfaceContainer)) + "surfaceContainerHigh=" + ((Object) Color.m3142toStringimpl(this.surfaceContainerHigh)) + "surfaceContainerHighest=" + ((Object) Color.m3142toStringimpl(this.surfaceContainerHighest)) + "surfaceContainerLow=" + ((Object) Color.m3142toStringimpl(this.surfaceContainerLow)) + "surfaceContainerLowest=" + ((Object) Color.m3142toStringimpl(this.surfaceContainerLowest)) + "primaryFixed=" + ((Object) Color.m3142toStringimpl(this.primaryFixed)) + "primaryFixedDim=" + ((Object) Color.m3142toStringimpl(this.primaryFixedDim)) + "onPrimaryFixed=" + ((Object) Color.m3142toStringimpl(this.onPrimaryContainer)) + "onPrimaryFixedVariant=" + ((Object) Color.m3142toStringimpl(this.onPrimaryFixedVariant)) + "secondaryFixed=" + ((Object) Color.m3142toStringimpl(this.secondaryFixed)) + "secondaryFixedDim=" + ((Object) Color.m3142toStringimpl(this.secondaryFixedDim)) + "onSecondaryFixed=" + ((Object) Color.m3142toStringimpl(this.onSecondaryFixed)) + "onSecondaryFixedVariant=" + ((Object) Color.m3142toStringimpl(this.onSecondaryFixedVariant)) + "tertiaryFixed=" + ((Object) Color.m3142toStringimpl(this.tertiaryFixed)) + "tertiaryFixedDim=" + ((Object) Color.m3142toStringimpl(this.tertiaryFixedDim)) + "onTertiaryFixed=" + ((Object) Color.m3142toStringimpl(this.onTertiaryFixed)) + "onTertiaryFixedVariant=" + ((Object) Color.m3142toStringimpl(this.onTertiaryFixedVariant)) + ')';
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use constructor with additional 'fixed' container roles.", replaceWith = @ReplaceWith(expression = "ColorScheme(primary,\nonPrimary,\nprimaryContainer,\nonPrimaryContainer,\ninversePrimary,\nsecondary,\nonSecondary,\nsecondaryContainer,\nonSecondaryContainer,\ntertiary,\nonTertiary,\ntertiaryContainer,\nonTertiaryContainer,\nbackground,\nonBackground,\nsurface,\nonSurface,\nsurfaceVariant,\nonSurfaceVariant,\nsurfaceTint,\ninverseSurface,\ninverseOnSurface,\nerror,\nonError,\nerrorContainer,\nonErrorContainer,\noutline,\noutlineVariant,\nscrim,\nsurfaceBright,\nsurfaceDim,\nsurfaceContainer,\nsurfaceContainerHigh,\nsurfaceContainerHighest,\nsurfaceContainerLow,\nsurfaceContainerLowest,)", imports = {}))
    public /* synthetic */ ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use constructor with additional 'surfaceContainer' roles.", replaceWith = @ReplaceWith(expression = "ColorScheme(primary,\nonPrimary,\nprimaryContainer,\nonPrimaryContainer,\ninversePrimary,\nsecondary,\nonSecondary,\nsecondaryContainer,\nonSecondaryContainer,\ntertiary,\nonTertiary,\ntertiaryContainer,\nonTertiaryContainer,\nbackground,\nonBackground,\nsurface,\nonSurface,\nsurfaceVariant,\nonSurfaceVariant,\nsurfaceTint,\ninverseSurface,\ninverseOnSurface,\nerror,\nonError,\nerrorContainer,\nonErrorContainer,\noutline,\noutlineVariant,\nscrim,\nsurfaceBright,\nsurfaceDim,\nsurfaceContainer,\nsurfaceContainerHigh,\nsurfaceContainerHighest,\nsurfaceContainerLow,\nsurfaceContainerLowest,\nprimaryFixed,\nprimaryFixedDim,\nonPrimaryFixed,\nonPrimaryFixedVariant,\nsecondaryFixed,\nsecondaryFixedDim,\nonSecondaryFixed,\nonSecondaryFixedVariant,\ntertiaryFixed,\ntertiaryFixedDim,\nonTertiaryFixed,\nonTertiaryFixedVariant)", imports = {}))
    public /* synthetic */ ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29);
    }

    public /* synthetic */ ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, long j43, long j44, long j45, long j46, long j47, long j48, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42, j43, j44, j45, j46, j47, j48);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36) {
        Color.Companion companion = Color.INSTANCE;
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    private ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29) {
        Color.Companion companion = Color.INSTANCE;
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), companion.m3170getUnspecified0d7_KjU(), null);
    }
}
