package defpackage;

import android.content.Context;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.DynamicTonalPaletteKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.internal.view.SupportMenu;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class kce {
    public static final ColorScheme a = ColorSchemeKt.m284darkColorScheme_VG5OTI$default(r62.d(), 0, 0, 0, 0, r62.f(), 0, 0, 0, r62.b(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -546, SupportMenu.USER_MASK, null);
    public static final ColorScheme b = ColorSchemeKt.m290lightColorScheme_VG5OTI$default(r62.c(), 0, 0, 0, 0, r62.e(), 0, 0, 0, r62.a(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -546, SupportMenu.USER_MASK, null);

    public static Unit a(boolean z, boolean z2, Function2 function2, int i, int i2, Composer composer, int i3) {
        b(z, z2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void b(boolean z, boolean z2, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        ColorScheme colorSchemeDynamicDarkColorScheme;
        function2.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(1914725072);
        if ((i & 6) == 0) {
            i3 = (((i2 & 1) == 0 && composerStartRestartGroup.changed(z)) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                if ((i2 & 1) != 0) {
                    z = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
                    i3 &= -15;
                }
                if (i4 != 0) {
                    z2 = true;
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1914725072, i3, -1, "com.yimaide.app.ui.theme.YimaIDETheme (Theme.kt:41)");
            }
            if (z2) {
                composerStartRestartGroup.startReplaceGroup(114268151);
                Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
                colorSchemeDynamicDarkColorScheme = z ? DynamicTonalPaletteKt.dynamicDarkColorScheme(context) : DynamicTonalPaletteKt.dynamicLightColorScheme(context);
                composerStartRestartGroup.endReplaceGroup();
            } else if (z) {
                composerStartRestartGroup.startReplaceGroup(1804806943);
                composerStartRestartGroup.endReplaceGroup();
                colorSchemeDynamicDarkColorScheme = a;
            } else {
                composerStartRestartGroup.startReplaceGroup(1804807968);
                composerStartRestartGroup.endReplaceGroup();
                colorSchemeDynamicDarkColorScheme = b;
            }
            MaterialThemeKt.MaterialTheme(colorSchemeDynamicDarkColorScheme, null, bve.a(), function2, composerStartRestartGroup, ((i3 << 3) & V4Signature.MAX_SIGNING_INFOS_SIZE) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final boolean z3 = z;
        final boolean z4 = z2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jce
                public final Object invoke(Object obj, Object obj2) {
                    return kce.a(z3, z4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
