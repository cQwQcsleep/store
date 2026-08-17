package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.ShortNavigationBarKt;
import androidx.compose.material3.tokens.NavigationBarHorizontalItemTokens;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.material3.tokens.NavigationBarVerticalItemTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u001aT\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0081\u0001\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\f2\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020%H\u0002\"\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\"\u0016\u0010(\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+\"\u0016\u0010-\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b.\u0010+\"\u0016\u0010/\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b0\u0010+\"\u0016\u00101\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b2\u0010+\"\u0016\u00103\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b4\u0010+\"\u0016\u00105\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b6\u0010+\"\u0016\u00107\u001a\u00020)X\u0080\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b8\u0010+¨\u00069"}, d2 = {"ShortNavigationBar", "", "modifier", "Landroidx/compose/ui/Modifier;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/material3/ShortNavigationBarArrangement;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "ShortNavigationBar-kQ6Tpik", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/foundation/layout/WindowInsets;ILkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ShortNavigationBarItem", "selected", "", "onClick", "icon", "label", "enabled", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "colors", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "ShortNavigationBarItem-6ZDA4I0", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "LocalShortNavigationBarOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/ShortNavigationBarOverride;", "getLocalShortNavigationBarOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "calculateCenteredContentHorizontalPadding", "", "itemsCount", "barWidth", "TopIconItemVerticalPadding", "Landroidx/compose/ui/unit/Dp;", "getTopIconItemVerticalPadding", "()F", "F", "TopIconIndicatorVerticalPadding", "getTopIconIndicatorVerticalPadding", "TopIconIndicatorHorizontalPadding", "getTopIconIndicatorHorizontalPadding", "StartIconIndicatorVerticalPadding", "getStartIconIndicatorVerticalPadding", "TopIconIndicatorToLabelPadding", "getTopIconIndicatorToLabelPadding", "StartIconIndicatorHorizontalPadding", "getStartIconIndicatorHorizontalPadding", "StartIconToLabelPadding", "getStartIconToLabelPadding", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShortNavigationBarKt {
    private static final ProvidableCompositionLocal<ShortNavigationBarOverride> LocalShortNavigationBarOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: lad
        public final Object invoke() {
            return ShortNavigationBarKt.a();
        }
    }, 1, null);
    private static final float StartIconIndicatorHorizontalPadding;
    private static final float StartIconIndicatorVerticalPadding;
    private static final float StartIconToLabelPadding;
    private static final float TopIconIndicatorHorizontalPadding;
    private static final float TopIconIndicatorToLabelPadding;
    private static final float TopIconIndicatorVerticalPadding;
    private static final float TopIconItemVerticalPadding;

    static {
        NavigationBarVerticalItemTokens navigationBarVerticalItemTokens = NavigationBarVerticalItemTokens.INSTANCE;
        TopIconItemVerticalPadding = navigationBarVerticalItemTokens.m1927getContainerBetweenSpaceD9Ej5fM();
        TopIconIndicatorVerticalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationBarVerticalItemTokens.m1925getActiveIndicatorHeightD9Ej5fM() - navigationBarVerticalItemTokens.m1928getIconSizeD9Ej5fM()) / 2.0f);
        TopIconIndicatorHorizontalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationBarVerticalItemTokens.m1926getActiveIndicatorWidthD9Ej5fM() - navigationBarVerticalItemTokens.m1928getIconSizeD9Ej5fM()) / 2.0f);
        NavigationBarHorizontalItemTokens navigationBarHorizontalItemTokens = NavigationBarHorizontalItemTokens.INSTANCE;
        StartIconIndicatorVerticalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationBarHorizontalItemTokens.m1916getActiveIndicatorHeightD9Ej5fM() - navigationBarHorizontalItemTokens.m1919getIconSizeD9Ej5fM()) / 2.0f);
        TopIconIndicatorToLabelPadding = Dp.m6022constructorimpl(4.0f);
        StartIconIndicatorHorizontalPadding = navigationBarHorizontalItemTokens.m1917getActiveIndicatorLeadingSpaceD9Ej5fM();
        StartIconToLabelPadding = NavigationBarTokens.INSTANCE.m1922getItemActiveIndicatorIconLabelSpaceD9Ej5fM();
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0112  */
    /* JADX WARN: Code duplicated, block: B:105:0x011e  */
    /* JADX WARN: Code duplicated, block: B:108:0x012b  */
    /* JADX WARN: Code duplicated, block: B:109:0x013a  */
    /* JADX WARN: Code duplicated, block: B:112:0x0145  */
    /* JADX WARN: Code duplicated, block: B:115:0x0165  */
    /* JADX WARN: Code duplicated, block: B:117:0x0170  */
    /* JADX WARN: Code duplicated, block: B:120:0x017e  */
    /* JADX WARN: Code duplicated, block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:70:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:94:0x00fb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:96:0x0100  */
    /* JADX WARN: Code duplicated, block: B:99:0x0106  */
    /* JADX INFO: renamed from: ShortNavigationBar-kQ6Tpik, reason: not valid java name */
    public static final void m875ShortNavigationBarkQ6Tpik(Modifier modifier, long j, long j2, WindowInsets windowInsets, int i, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        long containerColor;
        long contentColor;
        WindowInsets windowInsets2;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function3;
        boolean z;
        Modifier modifier3;
        final long j3;
        final long j4;
        final WindowInsets windowInsets3;
        final int i6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        int iM873getArrangementLnnQw40;
        long j5;
        WindowInsets windowInsets4;
        Composer composerStartRestartGroup = composer.startRestartGroup(552087412);
        int i7 = i3 & 1;
        if (i7 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            if ((i3 & 2) == 0) {
                containerColor = j;
                int i8 = composerStartRestartGroup.changed(containerColor) ? 32 : 16;
                i4 |= i8;
            } else {
                containerColor = j;
            }
            i4 |= i8;
        } else {
            containerColor = j;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                contentColor = j2;
                int i9 = composerStartRestartGroup.changed(contentColor) ? 256 : 128;
                i4 |= i9;
            } else {
                contentColor = j2;
            }
            i4 |= i9;
        } else {
            contentColor = j2;
        }
        if ((i2 & 3072) == 0) {
            if ((i3 & 8) == 0) {
                windowInsets2 = windowInsets;
                int i10 = composerStartRestartGroup.changed(windowInsets2) ? 2048 : 1024;
                i4 |= i10;
            } else {
                windowInsets2 = windowInsets;
            }
            i4 |= i10;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i2 & 24576) == 0) {
            if ((i3 & 16) == 0) {
                i5 = i;
                int i11 = composerStartRestartGroup.changed(i5) ? 16384 : 8192;
                i4 |= i11;
            } else {
                i5 = i;
            }
            i4 |= i11;
        } else {
            i5 = i;
        }
        if ((i3 & 32) == 0) {
            if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                function3 = function2;
                i4 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
            }
            if ((74899 & i4) != 74898) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i7 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        containerColor = ShortNavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        contentColor = ShortNavigationBarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                        windowInsets2 = ShortNavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                        modifier3 = modifier4;
                        iM873getArrangementLnnQw40 = ShortNavigationBarDefaults.INSTANCE.m873getArrangementLnnQw40();
                        j5 = contentColor;
                        windowInsets4 = windowInsets2;
                    } else {
                        modifier3 = modifier4;
                    }
                    long j6 = containerColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(552087412, i4, -1, "androidx.compose.material3.ShortNavigationBar (ShortNavigationBar.kt:101)");
                    }
                    ((ShortNavigationBarOverride) composerStartRestartGroup.consume(LocalShortNavigationBarOverride)).ShortNavigationBar(new ShortNavigationBarOverrideScope(modifier3, j6, j5, windowInsets4, iM873getArrangementLnnQw40, function3, null), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j6;
                    j4 = j5;
                    windowInsets3 = windowInsets4;
                    i6 = iM873getArrangementLnnQw40;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 2) != 0) {
                        i4 &= -113;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                    }
                    modifier3 = modifier2;
                }
                j5 = contentColor;
                windowInsets4 = windowInsets2;
                iM873getArrangementLnnQw40 = i5;
                long j7 = containerColor;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(552087412, i4, -1, "androidx.compose.material3.ShortNavigationBar (ShortNavigationBar.kt:101)");
                }
                ((ShortNavigationBarOverride) composerStartRestartGroup.consume(LocalShortNavigationBarOverride)).ShortNavigationBar(new ShortNavigationBarOverrideScope(modifier3, j7, j5, windowInsets4, iM873getArrangementLnnQw40, function3, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j7;
                j4 = j5;
                windowInsets3 = windowInsets4;
                i6 = iM873getArrangementLnnQw40;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j3 = containerColor;
                j4 = contentColor;
                windowInsets3 = windowInsets2;
                i6 = i5;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier3;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jad
                    public final Object invoke(Object obj, Object obj2) {
                        return ShortNavigationBarKt.c(modifier5, j3, j4, windowInsets3, i6, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function3 = function2;
        if ((74899 & i4) != 74898) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    containerColor = ShortNavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    contentColor = ShortNavigationBarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    windowInsets2 = ShortNavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    modifier3 = modifier4;
                    iM873getArrangementLnnQw40 = ShortNavigationBarDefaults.INSTANCE.m873getArrangementLnnQw40();
                    j5 = contentColor;
                    windowInsets4 = windowInsets2;
                } else {
                    modifier3 = modifier4;
                    j5 = contentColor;
                    windowInsets4 = windowInsets2;
                    iM873getArrangementLnnQw40 = i5;
                }
            } else {
                if (i7 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    containerColor = ShortNavigationBarDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -113;
                }
                if ((i3 & 4) != 0) {
                    contentColor = ShortNavigationBarDefaults.INSTANCE.getContentColor(composerStartRestartGroup, 6);
                    i4 &= -897;
                }
                if ((i3 & 8) != 0) {
                    i4 &= -7169;
                    windowInsets2 = ShortNavigationBarDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                }
                if ((i3 & 16) != 0) {
                    i4 &= -57345;
                    modifier3 = modifier4;
                    iM873getArrangementLnnQw40 = ShortNavigationBarDefaults.INSTANCE.m873getArrangementLnnQw40();
                    j5 = contentColor;
                    windowInsets4 = windowInsets2;
                } else {
                    modifier3 = modifier4;
                    j5 = contentColor;
                    windowInsets4 = windowInsets2;
                    iM873getArrangementLnnQw40 = i5;
                }
            }
            long j8 = containerColor;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(552087412, i4, -1, "androidx.compose.material3.ShortNavigationBar (ShortNavigationBar.kt:101)");
            }
            ((ShortNavigationBarOverride) composerStartRestartGroup.consume(LocalShortNavigationBarOverride)).ShortNavigationBar(new ShortNavigationBarOverrideScope(modifier3, j8, j5, windowInsets4, iM873getArrangementLnnQw40, function3, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j8;
            j4 = j5;
            windowInsets3 = windowInsets4;
            i6 = iM873getArrangementLnnQw40;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j3 = containerColor;
            j4 = contentColor;
            windowInsets3 = windowInsets2;
            i6 = i5;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier6 = modifier3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jad
                public final Object invoke(Object obj, Object obj2) {
                    return ShortNavigationBarKt.c(modifier6, j3, j4, windowInsets3, i6, function2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0117  */
    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:104:0x0123  */
    /* JADX WARN: Code duplicated, block: B:106:0x012e  */
    /* JADX WARN: Code duplicated, block: B:114:0x0153 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:115:0x0155  */
    /* JADX WARN: Code duplicated, block: B:117:0x015a  */
    /* JADX WARN: Code duplicated, block: B:119:0x015e  */
    /* JADX WARN: Code duplicated, block: B:122:0x0169  */
    /* JADX WARN: Code duplicated, block: B:123:0x0173  */
    /* JADX WARN: Code duplicated, block: B:125:0x0177  */
    /* JADX WARN: Code duplicated, block: B:126:0x0179  */
    /* JADX WARN: Code duplicated, block: B:129:0x0185  */
    /* JADX WARN: Code duplicated, block: B:131:0x0190  */
    /* JADX WARN: Code duplicated, block: B:133:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:135:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:138:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:140:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:142:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:144:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:147:0x022e  */
    /* JADX WARN: Code duplicated, block: B:149:0x023b  */
    /* JADX WARN: Code duplicated, block: B:152:0x024b  */
    /* JADX WARN: Code duplicated, block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x009a  */
    /* JADX WARN: Code duplicated, block: B:58:0x009e  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:78:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:82:0x00db  */
    /* JADX WARN: Code duplicated, block: B:83:0x00de  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:93:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:95:0x0102  */
    /* JADX WARN: Code duplicated, block: B:96:0x0105  */
    /* JADX INFO: renamed from: ShortNavigationBarItem-6ZDA4I0, reason: not valid java name */
    public static final void m876ShortNavigationBarItem6ZDA4I0(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, boolean z2, int i, NavigationItemColors navigationItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i2, final int i3) {
        boolean z3;
        int i4;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        final Modifier modifier2;
        int i5;
        boolean z4;
        int i6;
        int i7;
        int iM690getTopxw1Ddg;
        int i8;
        NavigationItemColors navigationItemColorsColors;
        int i9;
        int i10;
        int i11;
        boolean z5;
        Composer composer2;
        final boolean z6;
        final int i12;
        final NavigationItemColors navigationItemColors2;
        final MutableInteractionSource mutableInteractionSource2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i13;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        boolean z7;
        NavigationItemColors navigationItemColors3;
        MutableInteractionSource mutableInteractionSource4;
        boolean zM685equalsimpl0;
        float f;
        float f2;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1164996656);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            z3 = z;
        } else {
            z3 = z;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 48) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        if ((i3 & 4) != 0) {
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function4 = function2;
        } else {
            function4 = function2;
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
            }
        }
        if ((i3 & 8) != 0) {
            i4 |= 3072;
            function5 = function3;
        } else {
            function5 = function3;
            if ((i2 & 3072) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function5) ? 2048 : 1024;
            }
        }
        int i14 = i3 & 16;
        if (i14 == 0) {
            if ((i2 & 24576) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 16384 : 8192;
            }
            i5 = i3 & 32;
            if (i5 != 0) {
                if ((196608 & i2) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i6 = 131072;
                    } else {
                        i6 = 65536;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 64;
                if (i7 != 0) {
                    if ((1572864 & i2) == 0) {
                        iM690getTopxw1Ddg = i;
                        if (composerStartRestartGroup.changed(iM690getTopxw1Ddg)) {
                            i8 = 1048576;
                        } else {
                            i8 = 524288;
                        }
                        i4 |= i8;
                    }
                    if ((12582912 & i2) == 0) {
                        if ((i3 & 128) == 0) {
                            navigationItemColorsColors = navigationItemColors;
                            int i15 = composerStartRestartGroup.changed(navigationItemColorsColors) ? 8388608 : 4194304;
                            i4 |= i15;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i4 |= i15;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i9 = i3 & 256;
                    if (i9 != 0) {
                        if ((i2 & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i10 = 33554432;
                            }
                            i4 |= i10;
                        }
                        i11 = i4;
                        if ((i4 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i14 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i5 != 0) {
                                    z4 = true;
                                }
                                if (i7 != 0) {
                                    iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                                }
                                if ((i3 & 128) != 0) {
                                    i13 = i11 & (-29360129);
                                    navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                } else {
                                    i13 = i11;
                                }
                                if (i9 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i3 & 128) != 0) {
                                    i13 = i11 & (-29360129);
                                    modifier3 = modifier2;
                                    z7 = z4;
                                    navigationItemColors3 = navigationItemColorsColors;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                    i13 = i11;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                                }
                                if (mutableInteractionSource3 == null) {
                                    composerStartRestartGroup.startReplaceGroup(1215858123);
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(1424693900);
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                }
                                zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                                if (zM685equalsimpl0) {
                                    f = TopIconIndicatorHorizontalPadding;
                                } else {
                                    f = StartIconIndicatorHorizontalPadding;
                                }
                                float f3 = f;
                                if (zM685equalsimpl0) {
                                    f2 = TopIconIndicatorVerticalPadding;
                                } else {
                                    f2 = StartIconIndicatorVerticalPadding;
                                }
                                float f4 = f2;
                                NavigationBarTokens navigationBarTokens = NavigationBarTokens.INSTANCE;
                                int i16 = i13 >> 6;
                                composer2 = composerStartRestartGroup;
                                int i17 = iM690getTopxw1Ddg;
                                NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f3, f4, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i17, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i16 & 896) | (i16 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                mutableInteractionSource2 = mutableInteractionSource3;
                                navigationItemColors2 = navigationItemColors3;
                                modifier2 = modifier3;
                                z6 = z7;
                                i12 = i17;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(1215858123);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1424693900);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                            if (zM685equalsimpl0) {
                                f = TopIconIndicatorHorizontalPadding;
                            } else {
                                f = StartIconIndicatorHorizontalPadding;
                            }
                            float f5 = f;
                            if (zM685equalsimpl0) {
                                f2 = TopIconIndicatorVerticalPadding;
                            } else {
                                f2 = StartIconIndicatorVerticalPadding;
                            }
                            float f6 = f2;
                            NavigationBarTokens navigationBarTokens2 = NavigationBarTokens.INSTANCE;
                            int i18 = i13 >> 6;
                            composer2 = composerStartRestartGroup;
                            int i19 = iM690getTopxw1Ddg;
                            NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens2.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens2.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f5, f6, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i19, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i18 & 896) | (i18 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors3;
                            modifier2 = modifier3;
                            z6 = z7;
                            i12 = i19;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z4;
                            i12 = iM690getTopxw1Ddg;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                                public final Object invoke(Object obj, Object obj2) {
                                    return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 100663296;
                    i11 = i4;
                    if ((i4 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        } else {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(1215858123);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1424693900);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                        if (zM685equalsimpl0) {
                            f = TopIconIndicatorHorizontalPadding;
                        } else {
                            f = StartIconIndicatorHorizontalPadding;
                        }
                        float f7 = f;
                        if (zM685equalsimpl0) {
                            f2 = TopIconIndicatorVerticalPadding;
                        } else {
                            f2 = StartIconIndicatorVerticalPadding;
                        }
                        float f8 = f2;
                        NavigationBarTokens navigationBarTokens3 = NavigationBarTokens.INSTANCE;
                        int i110 = i13 >> 6;
                        composer2 = composerStartRestartGroup;
                        int i111 = iM690getTopxw1Ddg;
                        NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens3.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens3.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f7, f8, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i111, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i110 & 896) | (i110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors3;
                        modifier2 = modifier3;
                        z6 = z7;
                        i12 = i111;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z4;
                        i12 = iM690getTopxw1Ddg;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                            public final Object invoke(Object obj, Object obj2) {
                                return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                iM690getTopxw1Ddg = i;
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i15;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i9 = i3 & 256;
                if (i9 != 0) {
                    if ((i2 & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i10 = 33554432;
                        }
                        i4 |= i10;
                    }
                    i11 = i4;
                    if ((i4 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        } else {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(1215858123);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1424693900);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                        if (zM685equalsimpl0) {
                            f = TopIconIndicatorHorizontalPadding;
                        } else {
                            f = StartIconIndicatorHorizontalPadding;
                        }
                        float f9 = f;
                        if (zM685equalsimpl0) {
                            f2 = TopIconIndicatorVerticalPadding;
                        } else {
                            f2 = StartIconIndicatorVerticalPadding;
                        }
                        float f10 = f2;
                        NavigationBarTokens navigationBarTokens4 = NavigationBarTokens.INSTANCE;
                        int i112 = i13 >> 6;
                        composer2 = composerStartRestartGroup;
                        int i113 = iM690getTopxw1Ddg;
                        NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens4.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens4.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f9, f10, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i113, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i112 & 896) | (i112 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors3;
                        modifier2 = modifier3;
                        z6 = z7;
                        i12 = i113;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z4;
                        i12 = iM690getTopxw1Ddg;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                            public final Object invoke(Object obj, Object obj2) {
                                return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f11 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f12 = f2;
                    NavigationBarTokens navigationBarTokens5 = NavigationBarTokens.INSTANCE;
                    int i114 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i115 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens5.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens5.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f11, f12, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i115, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i114 & 896) | (i114 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i115;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z4 = z2;
            i7 = i3 & 64;
            if (i7 != 0) {
                if ((1572864 & i2) == 0) {
                    iM690getTopxw1Ddg = i;
                    if (composerStartRestartGroup.changed(iM690getTopxw1Ddg)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i4 |= i8;
                }
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i15;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i9 = i3 & 256;
                if (i9 != 0) {
                    if ((i2 & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i10 = 33554432;
                        }
                        i4 |= i10;
                    }
                    i11 = i4;
                    if ((i4 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        } else {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(1215858123);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1424693900);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                        if (zM685equalsimpl0) {
                            f = TopIconIndicatorHorizontalPadding;
                        } else {
                            f = StartIconIndicatorHorizontalPadding;
                        }
                        float f13 = f;
                        if (zM685equalsimpl0) {
                            f2 = TopIconIndicatorVerticalPadding;
                        } else {
                            f2 = StartIconIndicatorVerticalPadding;
                        }
                        float f14 = f2;
                        NavigationBarTokens navigationBarTokens6 = NavigationBarTokens.INSTANCE;
                        int i116 = i13 >> 6;
                        composer2 = composerStartRestartGroup;
                        int i117 = iM690getTopxw1Ddg;
                        NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens6.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens6.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f13, f14, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i117, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i116 & 896) | (i116 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors3;
                        modifier2 = modifier3;
                        z6 = z7;
                        i12 = i117;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z4;
                        i12 = iM690getTopxw1Ddg;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                            public final Object invoke(Object obj, Object obj2) {
                                return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f15 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f16 = f2;
                    NavigationBarTokens navigationBarTokens7 = NavigationBarTokens.INSTANCE;
                    int i118 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i119 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens7.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens7.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f15, f16, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i119, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i118 & 896) | (i118 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i119;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            iM690getTopxw1Ddg = i;
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i15;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i9 = i3 & 256;
            if (i9 != 0) {
                if ((i2 & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i10 = 33554432;
                    }
                    i4 |= i10;
                }
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f17 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f18 = f2;
                    NavigationBarTokens navigationBarTokens8 = NavigationBarTokens.INSTANCE;
                    int i1110 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i1111 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens8.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens8.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f17, f18, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i1111, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i1110 & 896) | (i1110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i1111;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i11 = i4;
            if ((i4 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(1215858123);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1424693900);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                if (zM685equalsimpl0) {
                    f = TopIconIndicatorHorizontalPadding;
                } else {
                    f = StartIconIndicatorHorizontalPadding;
                }
                float f19 = f;
                if (zM685equalsimpl0) {
                    f2 = TopIconIndicatorVerticalPadding;
                } else {
                    f2 = StartIconIndicatorVerticalPadding;
                }
                float f110 = f2;
                NavigationBarTokens navigationBarTokens9 = NavigationBarTokens.INSTANCE;
                int i1112 = i13 >> 6;
                composer2 = composerStartRestartGroup;
                int i1113 = iM690getTopxw1Ddg;
                NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens9.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens9.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f19, f110, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i1113, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i1112 & 896) | (i1112 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors3;
                modifier2 = modifier3;
                z6 = z7;
                i12 = i1113;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z4;
                i12 = iM690getTopxw1Ddg;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                    public final Object invoke(Object obj, Object obj2) {
                        return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        modifier2 = modifier;
        i5 = i3 & 32;
        if (i5 != 0) {
            if ((196608 & i2) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i6 = 131072;
                } else {
                    i6 = 65536;
                }
                i4 |= i6;
            }
            i7 = i3 & 64;
            if (i7 != 0) {
                if ((1572864 & i2) == 0) {
                    iM690getTopxw1Ddg = i;
                    if (composerStartRestartGroup.changed(iM690getTopxw1Ddg)) {
                        i8 = 1048576;
                    } else {
                        i8 = 524288;
                    }
                    i4 |= i8;
                }
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i15;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i9 = i3 & 256;
                if (i9 != 0) {
                    if ((i2 & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i10 = 33554432;
                        }
                        i4 |= i10;
                    }
                    i11 = i4;
                    if ((i4 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        } else {
                            if (i14 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i5 != 0) {
                                z4 = true;
                            }
                            if (i7 != 0) {
                                iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                            }
                            if ((i3 & 128) != 0) {
                                i13 = i11 & (-29360129);
                                navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            } else {
                                i13 = i11;
                            }
                            if (i9 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                            modifier3 = modifier2;
                            z7 = z4;
                            navigationItemColors3 = navigationItemColorsColors;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(1215858123);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1424693900);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                        if (zM685equalsimpl0) {
                            f = TopIconIndicatorHorizontalPadding;
                        } else {
                            f = StartIconIndicatorHorizontalPadding;
                        }
                        float f111 = f;
                        if (zM685equalsimpl0) {
                            f2 = TopIconIndicatorVerticalPadding;
                        } else {
                            f2 = StartIconIndicatorVerticalPadding;
                        }
                        float f112 = f2;
                        NavigationBarTokens navigationBarTokens10 = NavigationBarTokens.INSTANCE;
                        int i1114 = i13 >> 6;
                        composer2 = composerStartRestartGroup;
                        int i1115 = iM690getTopxw1Ddg;
                        NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens10.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens10.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f111, f112, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i1115, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i1114 & 896) | (i1114 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors3;
                        modifier2 = modifier3;
                        z6 = z7;
                        i12 = i1115;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z4;
                        i12 = iM690getTopxw1Ddg;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                            public final Object invoke(Object obj, Object obj2) {
                                return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f113 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f114 = f2;
                    NavigationBarTokens navigationBarTokens11 = NavigationBarTokens.INSTANCE;
                    int i1116 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i1117 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens11.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens11.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f113, f114, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i1117, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i1116 & 896) | (i1116 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i1117;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            iM690getTopxw1Ddg = i;
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i15;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i9 = i3 & 256;
            if (i9 != 0) {
                if ((i2 & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i10 = 33554432;
                    }
                    i4 |= i10;
                }
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f115 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f116 = f2;
                    NavigationBarTokens navigationBarTokens12 = NavigationBarTokens.INSTANCE;
                    int i1118 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i1119 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens12.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens12.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f115, f116, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i1119, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i1118 & 896) | (i1118 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i1119;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i11 = i4;
            if ((i4 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(1215858123);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1424693900);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                if (zM685equalsimpl0) {
                    f = TopIconIndicatorHorizontalPadding;
                } else {
                    f = StartIconIndicatorHorizontalPadding;
                }
                float f117 = f;
                if (zM685equalsimpl0) {
                    f2 = TopIconIndicatorVerticalPadding;
                } else {
                    f2 = StartIconIndicatorVerticalPadding;
                }
                float f118 = f2;
                NavigationBarTokens navigationBarTokens13 = NavigationBarTokens.INSTANCE;
                int i11110 = i13 >> 6;
                composer2 = composerStartRestartGroup;
                int i11111 = iM690getTopxw1Ddg;
                NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens13.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens13.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f117, f118, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i11111, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i11110 & 896) | (i11110 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors3;
                modifier2 = modifier3;
                z6 = z7;
                i12 = i11111;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z4;
                i12 = iM690getTopxw1Ddg;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                    public final Object invoke(Object obj, Object obj2) {
                        return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        z4 = z2;
        i7 = i3 & 64;
        if (i7 != 0) {
            if ((1572864 & i2) == 0) {
                iM690getTopxw1Ddg = i;
                if (composerStartRestartGroup.changed(iM690getTopxw1Ddg)) {
                    i8 = 1048576;
                } else {
                    i8 = 524288;
                }
                i4 |= i8;
            }
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i15;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i15;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i9 = i3 & 256;
            if (i9 != 0) {
                if ((i2 & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i10 = 33554432;
                    }
                    i4 |= i10;
                }
                i11 = i4;
                if ((i4 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    } else {
                        if (i14 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i5 != 0) {
                            z4 = true;
                        }
                        if (i7 != 0) {
                            iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                        }
                        if ((i3 & 128) != 0) {
                            i13 = i11 & (-29360129);
                            navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        } else {
                            i13 = i11;
                        }
                        if (i9 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                        modifier3 = modifier2;
                        z7 = z4;
                        navigationItemColors3 = navigationItemColorsColors;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(1215858123);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1424693900);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                    if (zM685equalsimpl0) {
                        f = TopIconIndicatorHorizontalPadding;
                    } else {
                        f = StartIconIndicatorHorizontalPadding;
                    }
                    float f119 = f;
                    if (zM685equalsimpl0) {
                        f2 = TopIconIndicatorVerticalPadding;
                    } else {
                        f2 = StartIconIndicatorVerticalPadding;
                    }
                    float f1110 = f2;
                    NavigationBarTokens navigationBarTokens14 = NavigationBarTokens.INSTANCE;
                    int i11112 = i13 >> 6;
                    composer2 = composerStartRestartGroup;
                    int i11113 = iM690getTopxw1Ddg;
                    NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens14.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens14.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f119, f1110, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i11113, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i11112 & 896) | (i11112 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors3;
                    modifier2 = modifier3;
                    z6 = z7;
                    i12 = i11113;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z4;
                    i12 = iM690getTopxw1Ddg;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                        public final Object invoke(Object obj, Object obj2) {
                            return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i11 = i4;
            if ((i4 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(1215858123);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1424693900);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                if (zM685equalsimpl0) {
                    f = TopIconIndicatorHorizontalPadding;
                } else {
                    f = StartIconIndicatorHorizontalPadding;
                }
                float f1111 = f;
                if (zM685equalsimpl0) {
                    f2 = TopIconIndicatorVerticalPadding;
                } else {
                    f2 = StartIconIndicatorVerticalPadding;
                }
                float f1112 = f2;
                NavigationBarTokens navigationBarTokens15 = NavigationBarTokens.INSTANCE;
                int i11114 = i13 >> 6;
                composer2 = composerStartRestartGroup;
                int i11115 = iM690getTopxw1Ddg;
                NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens15.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens15.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f1111, f1112, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i11115, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i11114 & 896) | (i11114 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors3;
                modifier2 = modifier3;
                z6 = z7;
                i12 = i11115;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z4;
                i12 = iM690getTopxw1Ddg;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                    public final Object invoke(Object obj, Object obj2) {
                        return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 1572864;
        iM690getTopxw1Ddg = i;
        if ((12582912 & i2) == 0) {
            if ((i3 & 128) == 0) {
                navigationItemColorsColors = navigationItemColors;
                if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                }
                i4 |= i15;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i4 |= i15;
        } else {
            navigationItemColorsColors = navigationItemColors;
        }
        i9 = i3 & 256;
        if (i9 != 0) {
            if ((i2 & 100663296) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i10 = 33554432;
                }
                i4 |= i10;
            }
            i11 = i4;
            if ((i4 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                } else {
                    if (i14 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i5 != 0) {
                        z4 = true;
                    }
                    if (i7 != 0) {
                        iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                    }
                    if ((i3 & 128) != 0) {
                        i13 = i11 & (-29360129);
                        navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    } else {
                        i13 = i11;
                    }
                    if (i9 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                    modifier3 = modifier2;
                    z7 = z4;
                    navigationItemColors3 = navigationItemColorsColors;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(1215858123);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(1424693900);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
                if (zM685equalsimpl0) {
                    f = TopIconIndicatorHorizontalPadding;
                } else {
                    f = StartIconIndicatorHorizontalPadding;
                }
                float f1113 = f;
                if (zM685equalsimpl0) {
                    f2 = TopIconIndicatorVerticalPadding;
                } else {
                    f2 = StartIconIndicatorVerticalPadding;
                }
                float f1114 = f2;
                NavigationBarTokens navigationBarTokens16 = NavigationBarTokens.INSTANCE;
                int i11116 = i13 >> 6;
                composer2 = composerStartRestartGroup;
                int i11117 = iM690getTopxw1Ddg;
                NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens16.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens16.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f1113, f1114, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i11117, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i11116 & 896) | (i11116 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors3;
                modifier2 = modifier3;
                z6 = z7;
                i12 = i11117;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z4;
                i12 = iM690getTopxw1Ddg;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                    public final Object invoke(Object obj, Object obj2) {
                        return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 100663296;
        i11 = i4;
        if ((i4 & 38347923) != 38347922) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    z4 = true;
                }
                if (i7 != 0) {
                    iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                }
                if ((i3 & 128) != 0) {
                    i13 = i11 & (-29360129);
                    navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    i13 = i11;
                }
                if (i9 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
                z7 = z4;
                navigationItemColors3 = navigationItemColorsColors;
            } else {
                if (i14 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    z4 = true;
                }
                if (i7 != 0) {
                    iM690getTopxw1Ddg = NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg();
                }
                if ((i3 & 128) != 0) {
                    i13 = i11 & (-29360129);
                    navigationItemColorsColors = ShortNavigationBarItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                } else {
                    i13 = i11;
                }
                if (i9 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
                modifier3 = modifier2;
                z7 = z4;
                navigationItemColors3 = navigationItemColorsColors;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1164996656, i13, -1, "androidx.compose.material3.ShortNavigationBarItem (ShortNavigationBar.kt:219)");
            }
            if (mutableInteractionSource3 == null) {
                composerStartRestartGroup.startReplaceGroup(1215858123);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                composerStartRestartGroup.startReplaceGroup(1424693900);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            zM685equalsimpl0 = NavigationItemIconPosition.m685equalsimpl0(iM690getTopxw1Ddg, NavigationItemIconPosition.INSTANCE.m690getTopxw1Ddg());
            if (zM685equalsimpl0) {
                f = TopIconIndicatorHorizontalPadding;
            } else {
                f = StartIconIndicatorHorizontalPadding;
            }
            float f1115 = f;
            if (zM685equalsimpl0) {
                f2 = TopIconIndicatorVerticalPadding;
            } else {
                f2 = StartIconIndicatorVerticalPadding;
            }
            float f1116 = f2;
            NavigationBarTokens navigationBarTokens17 = NavigationBarTokens.INSTANCE;
            int i11118 = i13 >> 6;
            composer2 = composerStartRestartGroup;
            int i11119 = iM690getTopxw1Ddg;
            NavigationItemKt.m694NavigationItem8Df7sds(z3, function1, function4, TypographyKt.getValue(navigationBarTokens17.getLabelTextFont(), composerStartRestartGroup, 6), ShapesKt.getValue(navigationBarTokens17.getItemActiveIndicatorShape(), composerStartRestartGroup, 6), NavigationBarVerticalItemTokens.INSTANCE.m1926getActiveIndicatorWidthD9Ej5fM(), f1115, f1116, TopIconIndicatorToLabelPadding, StartIconToLabelPadding, TopIconItemVerticalPadding, navigationItemColors3, modifier3, z7, function5, i11119, mutableInteractionSource4, composer2, (i13 & 14) | 906166272 | (i13 & 112) | (i13 & 896), 6 | ((i13 >> 18) & 112) | (i11118 & 896) | (i11118 & V4Signature.MAX_SIGNING_INFOS_SIZE) | ((i13 << 3) & 57344) | ((i13 >> 3) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource2 = mutableInteractionSource3;
            navigationItemColors2 = navigationItemColors3;
            modifier2 = modifier3;
            z6 = z7;
            i12 = i11119;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            z6 = z4;
            i12 = iM690getTopxw1Ddg;
            navigationItemColors2 = navigationItemColorsColors;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kad
                public final Object invoke(Object obj, Object obj2) {
                    return ShortNavigationBarKt.b(z, function0, function2, function3, modifier2, z6, i12, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static ShortNavigationBarOverride a() {
        return DefaultShortNavigationBarOverride.INSTANCE;
    }

    public static Unit b(boolean z, Function0 function0, Function2 function2, Function2 function3, Modifier modifier, boolean z2, int i, NavigationItemColors navigationItemColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m876ShortNavigationBarItem6ZDA4I0(z, function0, function2, function3, modifier, z2, i, navigationItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, long j, long j2, WindowInsets windowInsets, int i, Function2 function2, int i2, int i3, Composer composer, int i4) {
        m875ShortNavigationBarkQ6Tpik(modifier, j, j2, windowInsets, i, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int calculateCenteredContentHorizontalPadding(int i, int i2) {
        if (i > 6) {
            return 0;
        }
        return MathKt.roundToInt((((100 - ((i + 3) * 10)) / 2.0f) / 100.0f) * i2);
    }

    public static final ProvidableCompositionLocal<ShortNavigationBarOverride> getLocalShortNavigationBarOverride() {
        return LocalShortNavigationBarOverride;
    }

    public static final float getStartIconIndicatorHorizontalPadding() {
        return StartIconIndicatorHorizontalPadding;
    }

    public static final float getStartIconIndicatorVerticalPadding() {
        return StartIconIndicatorVerticalPadding;
    }

    public static final float getStartIconToLabelPadding() {
        return StartIconToLabelPadding;
    }

    public static final float getTopIconIndicatorHorizontalPadding() {
        return TopIconIndicatorHorizontalPadding;
    }

    public static final float getTopIconIndicatorToLabelPadding() {
        return TopIconIndicatorToLabelPadding;
    }

    public static final float getTopIconIndicatorVerticalPadding() {
        return TopIconIndicatorVerticalPadding;
    }

    public static final float getTopIconItemVerticalPadding() {
        return TopIconItemVerticalPadding;
    }
}
