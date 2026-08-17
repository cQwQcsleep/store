package androidx.compose.material3;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.TabRowDefaults;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.PrimaryNavigationTabTokens;
import androidx.compose.material3.tokens.SecondaryNavigationTabTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\fH\u0007¢\u0006\u0004\b!\u0010\"JA\u0010#\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\f2\b\b\u0002\u0010%\u001a\u00020&H\u0007¢\u0006\u0004\b'\u0010(J-\u0010)\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\fH\u0007¢\u0006\u0004\b*\u0010\"J\u0014\u0010+\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010,\u001a\u00020-H\u0007R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\f8GX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0003\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0014\u001a\u00020\f8GX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0003\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0017\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000fR\u0011\u0010\u0019\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u000f¨\u0006.²\u0006\n\u0010/\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u00020\u0005X\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/TabRowDefaults;", "", "<init>", "()V", "ScrollableTabRowMinTabWidth", "Landroidx/compose/ui/unit/Dp;", "getScrollableTabRowMinTabWidth-D9Ej5fM", "()F", "F", "ScrollableTabRowEdgeStartPadding", "getScrollableTabRowEdgeStartPadding-D9Ej5fM", "containerColor", "Landroidx/compose/ui/graphics/Color;", "getContainerColor$annotations", "getContainerColor", "(Landroidx/compose/runtime/Composer;I)J", "primaryContainerColor", "getPrimaryContainerColor", "secondaryContainerColor", "getSecondaryContainerColor", "contentColor", "getContentColor$annotations", "getContentColor", "primaryContentColor", "getPrimaryContentColor", "secondaryContentColor", "getSecondaryContentColor", "Indicator", "", "modifier", "Landroidx/compose/ui/Modifier;", "height", "color", "Indicator-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "PrimaryIndicator", "width", "shape", "Landroidx/compose/ui/graphics/Shape;", "PrimaryIndicator-10LGxhE", "(Landroidx/compose/ui/Modifier;FFJLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;II)V", "SecondaryIndicator", "SecondaryIndicator-9IZ8Weo", "tabIndicatorOffset", "currentTabPosition", "Landroidx/compose/material3/TabPosition;", "material3", "currentTabWidth", "indicatorOffset"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabRowDefaults {
    public static final int $stable = 0;
    public static final TabRowDefaults INSTANCE = new TabRowDefaults();
    private static final float ScrollableTabRowMinTabWidth = Dp.m6022constructorimpl(90.0f);
    private static final float ScrollableTabRowEdgeStartPadding = Dp.m6022constructorimpl(52.0f);

    private TabRowDefaults() {
    }

    public static Unit a(TabRowDefaults tabRowDefaults, Modifier modifier, float f, float f2, long j, Shape shape, int i, int i2, Composer composer, int i3) {
        tabRowDefaults.m995PrimaryIndicator10LGxhE(modifier, f, f2, j, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(TabRowDefaults tabRowDefaults, Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        tabRowDefaults.m996SecondaryIndicator9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(TabRowDefaults tabRowDefaults, Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        tabRowDefaults.m994Indicator9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(message = "Use TabRowDefaults.primaryContainerColor instead", replaceWith = @ReplaceWith(expression = "primaryContainerColor", imports = {}))
    public static /* synthetic */ void getContainerColor$annotations() {
    }

    @Deprecated(message = "Use TabRowDefaults.primaryContentColor instead", replaceWith = @ReplaceWith(expression = "primaryContentColor", imports = {}))
    public static /* synthetic */ void getContentColor$annotations() {
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004c  */
    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0057  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x0072  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b A[PHI: r2 r3 r4
      0x008b: PHI (r2v7 androidx.compose.ui.Modifier) = (r2v4 androidx.compose.ui.Modifier), (r2v9 androidx.compose.ui.Modifier) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x008b: PHI (r3v11 float) = (r3v7 float), (r3v12 float) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x008b: PHI (r4v16 int) = (r4v9 int), (r4v17 int) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:51:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0092  */
    /* JADX WARN: Code duplicated, block: B:55:0x0095  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00be  */
    /* JADX WARN: Code duplicated, block: B:65:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:72:? A[RETURN, SYNTHETIC] */
    @Deprecated(message = "Use SecondaryIndicator instead.", replaceWith = @ReplaceWith(expression = "SecondaryIndicator(modifier, height, color)", imports = {}))
    /* JADX INFO: renamed from: Indicator-9IZ8Weo, reason: not valid java name */
    public final void m994Indicator9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float f2;
        long j2;
        boolean z;
        final Modifier modifier3;
        final float fM2078getActiveIndicatorHeightD9Ej5fM;
        final long j3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long jFromToken;
        Composer composerStartRestartGroup = composer.startRestartGroup(1454716052);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 == 0) {
            if ((i & 48) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    j2 = j;
                    int i6 = composerStartRestartGroup.changed(j2) ? 256 : 128;
                    i3 |= i6;
                } else {
                    j2 = j;
                }
                i3 |= i6;
            } else {
                j2 = j;
            }
            if ((i3 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i5 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    } else {
                        fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        jFromToken = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor());
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1454716052, i3, -1, "androidx.compose.material3.TabRowDefaults.Indicator (TabRow.kt:1038)");
                    }
                    BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), jFromToken, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = jFromToken;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    modifier3 = modifier2;
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                jFromToken = j2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1454716052, i3, -1, "androidx.compose.material3.TabRowDefaults.Indicator (TabRow.kt:1038)");
                }
                BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), jFromToken, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = jFromToken;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: q1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowDefaults.c(this.b, modifier3, fM2078getActiveIndicatorHeightD9Ej5fM, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        f2 = f;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                j2 = j;
                if (composerStartRestartGroup.changed(j2)) {
                }
                i3 |= i6;
            } else {
                j2 = j;
            }
            i3 |= i6;
        } else {
            j2 = j;
        }
        if ((i3 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                } else {
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jFromToken = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor());
                } else {
                    jFromToken = j2;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                } else {
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    jFromToken = ColorSchemeKt.fromToken(MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6), PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor());
                } else {
                    jFromToken = j2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1454716052, i3, -1, "androidx.compose.material3.TabRowDefaults.Indicator (TabRow.kt:1038)");
            }
            BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), jFromToken, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jFromToken;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            fM2078getActiveIndicatorHeightD9Ej5fM = f2;
            j3 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: q1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowDefaults.c(this.b, modifier3, fM2078getActiveIndicatorHeightD9Ej5fM, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:32:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0063  */
    /* JADX WARN: Code duplicated, block: B:39:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x006f  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:48:0x007e  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:55:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c0 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:83:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:89:0x0119  */
    /* JADX WARN: Code duplicated, block: B:91:0x0121  */
    /* JADX WARN: Code duplicated, block: B:94:0x012c  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: PrimaryIndicator-10LGxhE, reason: not valid java name */
    public final void m995PrimaryIndicator10LGxhE(Modifier modifier, float f, float f2, long j, Shape shape, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float fM6022constructorimpl;
        int i4;
        float fM2078getActiveIndicatorHeightD9Ej5fM;
        int i5;
        long value;
        int i6;
        Shape activeIndicatorShape;
        int i7;
        boolean z;
        final Modifier modifier3;
        final float f3;
        final float f4;
        final long j2;
        final Shape shape2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1895596205);
        int i8 = i2 & 1;
        if (i8 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i9 = i2 & 2;
        if (i9 == 0) {
            if ((i & 48) == 0) {
                fM6022constructorimpl = f;
                i3 |= composerStartRestartGroup.changed(fM6022constructorimpl) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                    if (composerStartRestartGroup.changed(fM2078getActiveIndicatorHeightD9Ej5fM)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                if ((i & 3072) == 0) {
                    if ((i2 & 8) == 0) {
                        value = j;
                        int i10 = composerStartRestartGroup.changed(value) ? 2048 : 1024;
                        i3 |= i10;
                    } else {
                        value = j;
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        activeIndicatorShape = shape;
                        if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i8 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier2;
                            }
                            if (i9 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                            }
                            if (i4 != 0) {
                                fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                            }
                            if ((i2 & 8) != 0) {
                                value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                                i3 &= -7169;
                            }
                            if (i6 != 0) {
                                activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                            }
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 8) != 0) {
                                i3 &= -7169;
                            }
                            modifier3 = modifier2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                        }
                        SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                    }
                    f3 = fM6022constructorimpl;
                    f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
                    j2 = value;
                    shape2 = activeIndicatorShape;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                activeIndicatorShape = shape;
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                f3 = fM6022constructorimpl;
                f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            fM2078getActiveIndicatorHeightD9Ej5fM = f2;
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    activeIndicatorShape = shape;
                    if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                f3 = fM6022constructorimpl;
                f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            activeIndicatorShape = shape;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            f3 = fM6022constructorimpl;
            f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        fM6022constructorimpl = f;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                if (composerStartRestartGroup.changed(fM2078getActiveIndicatorHeightD9Ej5fM)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 8) == 0) {
                    value = j;
                    if (composerStartRestartGroup.changed(value)) {
                    }
                    i3 |= i10;
                } else {
                    value = j;
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    activeIndicatorShape = shape;
                    if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    } else {
                        if (i8 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier2;
                        }
                        if (i9 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                        }
                        if (i4 != 0) {
                            fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                        }
                        if ((i2 & 8) != 0) {
                            value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                            i3 &= -7169;
                        }
                        if (i6 != 0) {
                            activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                    }
                    SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                }
                f3 = fM6022constructorimpl;
                f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
                j2 = value;
                shape2 = activeIndicatorShape;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            activeIndicatorShape = shape;
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            f3 = fM6022constructorimpl;
            f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        fM2078getActiveIndicatorHeightD9Ej5fM = f2;
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                value = j;
                if (composerStartRestartGroup.changed(value)) {
                }
                i3 |= i10;
            } else {
                value = j;
            }
            i3 |= i10;
        } else {
            value = j;
        }
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                activeIndicatorShape = shape;
                if (composerStartRestartGroup.changed(activeIndicatorShape)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                } else {
                    if (i8 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i9 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                    }
                    if (i4 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    }
                    if ((i2 & 8) != 0) {
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                        i3 &= -7169;
                    }
                    if (i6 != 0) {
                        activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
                }
                SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            f3 = fM6022constructorimpl;
            f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
            j2 = value;
            shape2 = activeIndicatorShape;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        activeIndicatorShape = shape;
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i9 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            } else {
                if (i8 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i9 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(24.0f);
                }
                if (i4 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                }
                if ((i2 & 8) != 0) {
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    i3 &= -7169;
                }
                if (i6 != 0) {
                    activeIndicatorShape = PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorShape();
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895596205, i3, -1, "androidx.compose.material3.TabRowDefaults.PrimaryIndicator (TabRow.kt:1059)");
            }
            SpacerKt.Spacer(BackgroundKt.background-bw27NRU(SizeKt.requiredWidth-3ABfNKs(SizeKt.requiredHeight-3ABfNKs(modifier3, fM2078getActiveIndicatorHeightD9Ej5fM), fM6022constructorimpl), value, activeIndicatorShape), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        f3 = fM6022constructorimpl;
        f4 = fM2078getActiveIndicatorHeightD9Ej5fM;
        j2 = value;
        shape2 = activeIndicatorShape;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowDefaults.a(this.b, modifier3, f3, f4, j2, shape2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0048  */
    /* JADX WARN: Code duplicated, block: B:28:0x004c  */
    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0057  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x0072  */
    /* JADX WARN: Code duplicated, block: B:50:0x008b A[PHI: r2 r3 r4
      0x008b: PHI (r2v7 androidx.compose.ui.Modifier) = (r2v4 androidx.compose.ui.Modifier), (r2v9 androidx.compose.ui.Modifier) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x008b: PHI (r3v11 float) = (r3v7 float), (r3v12 float) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]
      0x008b: PHI (r4v16 int) = (r4v9 int), (r4v17 int) binds: [B:58:0x009f, B:49:0x0089] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:51:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0092  */
    /* JADX WARN: Code duplicated, block: B:55:0x0095  */
    /* JADX WARN: Code duplicated, block: B:56:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00db  */
    /* JADX WARN: Code duplicated, block: B:67:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:72:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: SecondaryIndicator-9IZ8Weo, reason: not valid java name */
    public final void m996SecondaryIndicator9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float f2;
        long j2;
        boolean z;
        final Modifier modifier3;
        final float fM2078getActiveIndicatorHeightD9Ej5fM;
        final long j3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long value;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1498258020);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 == 0) {
            if ((i & 48) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 4) == 0) {
                    j2 = j;
                    int i6 = composerStartRestartGroup.changed(j2) ? 256 : 128;
                    i3 |= i6;
                } else {
                    j2 = j;
                }
                i3 |= i6;
            } else {
                j2 = j;
            }
            if ((i3 & 147) != 146) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i5 != 0) {
                        fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                    } else {
                        fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1498258020, i3, -1, "androidx.compose.material3.TabRowDefaults.SecondaryIndicator (TabRow.kt:1081)");
                    }
                    BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), value, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = value;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    modifier3 = modifier2;
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                value = j2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1498258020, i3, -1, "androidx.compose.material3.TabRowDefaults.SecondaryIndicator (TabRow.kt:1081)");
                }
                BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), value, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = value;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabRowDefaults.b(this.b, modifier3, fM2078getActiveIndicatorHeightD9Ej5fM, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        f2 = f;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                j2 = j;
                if (composerStartRestartGroup.changed(j2)) {
                }
                i3 |= i6;
            } else {
                j2 = j;
            }
            i3 |= i6;
        } else {
            j2 = j;
        }
        if ((i3 & 147) != 146) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                } else {
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                } else {
                    value = j2;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i5 != 0) {
                    fM2078getActiveIndicatorHeightD9Ej5fM = PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM();
                } else {
                    fM2078getActiveIndicatorHeightD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveIndicatorColor(), composerStartRestartGroup, 6);
                } else {
                    value = j2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1498258020, i3, -1, "androidx.compose.material3.TabRowDefaults.SecondaryIndicator (TabRow.kt:1081)");
            }
            BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM2078getActiveIndicatorHeightD9Ej5fM), value, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = value;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            fM2078getActiveIndicatorHeightD9Ej5fM = f2;
            j3 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabRowDefaults.b(this.b, modifier3, fM2078getActiveIndicatorHeightD9Ej5fM, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final long getContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2026555673, i, -1, "androidx.compose.material3.TabRowDefaults.<get-containerColor> (TabRow.kt:994)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getContentColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1163072359, i, -1, "androidx.compose.material3.TabRowDefaults.<get-contentColor> (TabRow.kt:1010)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getPrimaryContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2069154037, i, -1, "androidx.compose.material3.TabRowDefaults.<get-primaryContainerColor> (TabRow.kt:998)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getPrimaryContentColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1410362619, i, -1, "androidx.compose.material3.TabRowDefaults.<get-primaryContentColor> (TabRow.kt:1014)");
        }
        long value = ColorSchemeKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    /* JADX INFO: renamed from: getScrollableTabRowEdgeStartPadding-D9Ej5fM, reason: not valid java name */
    public final float m997getScrollableTabRowEdgeStartPaddingD9Ej5fM() {
        return ScrollableTabRowEdgeStartPadding;
    }

    /* JADX INFO: renamed from: getScrollableTabRowMinTabWidth-D9Ej5fM, reason: not valid java name */
    public final float m998getScrollableTabRowMinTabWidthD9Ej5fM() {
        return ScrollableTabRowMinTabWidth;
    }

    public final long getSecondaryContainerColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1938007129, i, -1, "androidx.compose.material3.TabRowDefaults.<get-secondaryContainerColor> (TabRow.kt:1002)");
        }
        long value = ColorSchemeKt.getValue(SecondaryNavigationTabTokens.INSTANCE.getContainerColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    public final long getSecondaryContentColor(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1166419479, i, -1, "androidx.compose.material3.TabRowDefaults.<get-secondaryContentColor> (TabRow.kt:1018)");
        }
        long value = ColorSchemeKt.getValue(SecondaryNavigationTabTokens.INSTANCE.getActiveLabelTextColor(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return value;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Solely for use alongside deprecated TabRowDefaults.Indicator method. For recommended PrimaryIndicator and SecondaryIndicator methods, please use TabIndicatorScope.tabIndicatorOffset method.")
    public final Modifier tabIndicatorOffset(Modifier modifier, final TabPosition tabPosition) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$$inlined$debugInspectorInfo$1
            {
                super(1);
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("tabIndicatorOffset");
                inspectorInfo.setValue(tabPosition);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(tabPosition));
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TabRowDefaults$tabIndicatorOffset$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass2 implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ TabPosition $currentTabPosition;

        public AnonymousClass2(TabPosition tabPosition) {
            this.$currentTabPosition = tabPosition;
        }

        public static IntOffset a(State state, Density density) {
            return IntOffset.m6141boximpl(IntOffset.m6144constructorimpl(((long) density.mo4551roundToPx0680j_4(invoke$lambda$1(state))) << 32));
        }

        private static final float invoke$lambda$0(State<Dp> state) {
            return state.getValue().m6036unboximpl();
        }

        private static final float invoke$lambda$1(State<Dp> state) {
            return state.getValue().m6036unboximpl();
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i) {
            composer.startReplaceGroup(-1541271084);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1541271084, i, -1, "androidx.compose.material3.TabRowDefaults.tabIndicatorOffset.<anonymous> (TabRow.kt:1108)");
            }
            float width = this.$currentTabPosition.getWidth();
            MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.DefaultSpatial;
            State state = AnimateAsStateKt.animateDpAsState-AjpBEmI(width, MotionSchemeKt.value(motionSchemeKeyTokens, composer, 6), (String) null, (Function1) null, composer, 0, 12);
            final State state2 = AnimateAsStateKt.animateDpAsState-AjpBEmI(this.$currentTabPosition.getLeft(), MotionSchemeKt.value(motionSchemeKeyTokens, composer, 6), (String) null, (Function1) null, composer, 0, 12);
            Modifier modifierWrapContentSize$default = SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, (Object) null), Alignment.INSTANCE.getBottomStart(), false, 2, (Object) null);
            boolean zChanged = composer.changed(state2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.j4
                    public final Object invoke(Object obj) {
                        return TabRowDefaults.AnonymousClass2.a(state2, (Density) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifier2 = SizeKt.width-3ABfNKs(OffsetKt.offset(modifierWrapContentSize$default, (Function1) objRememberedValue), invoke$lambda$0(state));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return modifier2;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }
    }
}
