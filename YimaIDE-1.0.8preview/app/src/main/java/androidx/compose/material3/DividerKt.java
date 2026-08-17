package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a-\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\b\u0010\t\u001a-\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u000b\u0010\t\u001a-\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\r\u0010\t¨\u0006\u000e"}, d2 = {"HorizontalDivider", "", "modifier", "Landroidx/compose/ui/Modifier;", "thickness", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "HorizontalDivider-9IZ8Weo", "(Landroidx/compose/ui/Modifier;FJLandroidx/compose/runtime/Composer;II)V", "VerticalDivider", "VerticalDivider-9IZ8Weo", "Divider", "Divider-9IZ8Weo", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DividerKt {
    /* JADX WARN: Code duplicated, block: B:61:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:65:0x00db  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ff  */
    @Deprecated(message = "Renamed to HorizontalDivider", replaceWith = @ReplaceWith(expression = "HorizontalDivider(modifier, thickness, color)", imports = {}))
    /* JADX INFO: renamed from: Divider-9IZ8Weo, reason: not valid java name */
    public static final void m414Divider9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        Modifier modifier2;
        float fM413getThicknessD9Ej5fM;
        long color;
        float fM6022constructorimpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(1562471785);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
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
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                modifier2 = i4 != 0 ? Modifier.INSTANCE : modifier;
                fM413getThicknessD9Ej5fM = i5 != 0 ? DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM() : f;
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1562471785, i3, -1, "androidx.compose.material3.Divider (Divider.kt:99)");
                }
                if (Dp.m6027equalsimpl0(fM413getThicknessD9Ej5fM, Dp.INSTANCE.m6040getHairlineD9Ej5fM())) {
                    composerStartRestartGroup.startReplaceGroup(-1258250053);
                    fM6022constructorimpl = Dp.m6022constructorimpl(1.0f / ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).getDensity());
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1258183496);
                    composerStartRestartGroup.endReplaceGroup();
                    fM6022constructorimpl = fM413getThicknessD9Ej5fM;
                }
                BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier2, 0.0f, 1, (Object) null), fM6022constructorimpl), color, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
                modifier2 = modifier;
                fM413getThicknessD9Ej5fM = f;
            }
            color = j2;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1562471785, i3, -1, "androidx.compose.material3.Divider (Divider.kt:99)");
            }
            if (Dp.m6027equalsimpl0(fM413getThicknessD9Ej5fM, Dp.INSTANCE.m6040getHairlineD9Ej5fM())) {
                composerStartRestartGroup.startReplaceGroup(-1258250053);
                fM6022constructorimpl = Dp.m6022constructorimpl(1.0f / ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).getDensity());
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1258183496);
                composerStartRestartGroup.endReplaceGroup();
                fM6022constructorimpl = fM413getThicknessD9Ej5fM;
            }
            BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier2, 0.0f, 1, (Object) null), fM6022constructorimpl), color, (Shape) null, 2, (Object) null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            fM413getThicknessD9Ej5fM = f;
            color = j2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier3 = modifier2;
            final float f2 = fM413getThicknessD9Ej5fM;
            final long j3 = color;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vu3
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.e(modifier3, f2, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x0069  */
    /* JADX WARN: Code duplicated, block: B:40:0x0072  */
    /* JADX WARN: Code duplicated, block: B:49:0x008c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x008e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0091  */
    /* JADX WARN: Code duplicated, block: B:53:0x0094  */
    /* JADX WARN: Code duplicated, block: B:54:0x009b  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:80:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:81:0x0103  */
    /* JADX WARN: Code duplicated, block: B:84:0x010e  */
    /* JADX WARN: Code duplicated, block: B:86:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: HorizontalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m415HorizontalDivider9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float f2;
        final long color;
        boolean z;
        boolean z2;
        Modifier modifier3;
        final float fM413getThicknessD9Ej5fM;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z3;
        boolean z4;
        Object objRememberedValue;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(75144485);
        int i5 = i2 & 1;
        if (i5 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i6 = i2 & 2;
        if (i6 == 0) {
            if ((i & 48) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                color = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(color)) {
                    i4 = 128;
                } else {
                    i4 = 256;
                }
                i3 |= i4;
            } else {
                color = j;
            }
            z = true;
            if ((i3 & 147) != 146) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                    } else {
                        fM413getThicknessD9Ej5fM = f2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    modifier3 = modifier2;
                    fM413getThicknessD9Ej5fM = f2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(75144485, i3, -1, "androidx.compose.material3.HorizontalDivider (Divider.kt:53)");
                }
                Modifier modifier4 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM413getThicknessD9Ej5fM);
                if ((i3 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 || !composerStartRestartGroup.changed(color)) && (i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 256) {
                }
                z4 = z3 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: yu3
                        public final Object invoke(Object obj) {
                            return DividerKt.d(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier4, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                fM413getThicknessD9Ej5fM = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier3;
                final float f3 = fM413getThicknessD9Ej5fM;
                final long j2 = color;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zu3
                    public final Object invoke(Object obj, Object obj2) {
                        return DividerKt.b(modifier5, f3, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        f2 = f;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            color = j;
            if ((i2 & 4) == 0) {
                i4 = 128;
            } else {
                i4 = 128;
            }
            i3 |= i4;
        } else {
            color = j;
        }
        z = true;
        if ((i3 & 147) != 146) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                } else {
                    fM413getThicknessD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                }
            } else {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                } else {
                    fM413getThicknessD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(75144485, i3, -1, "androidx.compose.material3.HorizontalDivider (Divider.kt:53)");
            }
            Modifier modifier6 = SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), fM413getThicknessD9Ej5fM);
            if ((i3 & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            z = ((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 ? false : false;
            z4 = z3 | z;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z4) {
                objRememberedValue = new Function1() { // from class: yu3
                    public final Object invoke(Object obj) {
                        return DividerKt.d(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: yu3
                    public final Object invoke(Object obj) {
                        return DividerKt.d(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier6, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            fM413getThicknessD9Ej5fM = f2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier7 = modifier3;
            final float f4 = fM413getThicknessD9Ej5fM;
            final long j3 = color;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zu3
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.b(modifier7, f4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x004b  */
    /* JADX WARN: Code duplicated, block: B:31:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0067  */
    /* JADX WARN: Code duplicated, block: B:37:0x0069  */
    /* JADX WARN: Code duplicated, block: B:40:0x0072  */
    /* JADX WARN: Code duplicated, block: B:49:0x008c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x008e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0091  */
    /* JADX WARN: Code duplicated, block: B:53:0x0094  */
    /* JADX WARN: Code duplicated, block: B:54:0x009b  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:60:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:63:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:80:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:81:0x0103  */
    /* JADX WARN: Code duplicated, block: B:84:0x010e  */
    /* JADX WARN: Code duplicated, block: B:86:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: VerticalDivider-9IZ8Weo, reason: not valid java name */
    public static final void m416VerticalDivider9IZ8Weo(Modifier modifier, float f, long j, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        float f2;
        final long color;
        boolean z;
        boolean z2;
        Modifier modifier3;
        final float fM413getThicknessD9Ej5fM;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z3;
        boolean z4;
        Object objRememberedValue;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1534852205);
        int i5 = i2 & 1;
        if (i5 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        int i6 = i2 & 2;
        if (i6 == 0) {
            if ((i & 48) == 0) {
                f2 = f;
                i3 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                color = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(color)) {
                    i4 = 128;
                } else {
                    i4 = 256;
                }
                i3 |= i4;
            } else {
                color = j;
            }
            z = true;
            if ((i3 & 147) != 146) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i5 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i6 != 0) {
                        fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                    } else {
                        fM413getThicknessD9Ej5fM = f2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    modifier3 = modifier2;
                    fM413getThicknessD9Ej5fM = f2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1534852205, i3, -1, "androidx.compose.material3.VerticalDivider (Divider.kt:81)");
                }
                Modifier modifier4 = SizeKt.width-3ABfNKs(SizeKt.fillMaxHeight$default(modifier3, 0.0f, 1, (Object) null), fM413getThicknessD9Ej5fM);
                if ((i3 & 112) == 32) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if ((((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 || !composerStartRestartGroup.changed(color)) && (i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 256) {
                }
                z4 = z3 | z;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z4 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: wu3
                        public final Object invoke(Object obj) {
                            return DividerKt.c(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifier4, (Function1) objRememberedValue, composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                fM413getThicknessD9Ej5fM = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier5 = modifier3;
                final float f3 = fM413getThicknessD9Ej5fM;
                final long j2 = color;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu3
                    public final Object invoke(Object obj, Object obj2) {
                        return DividerKt.a(modifier5, f3, j2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        f2 = f;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            color = j;
            if ((i2 & 4) == 0) {
                i4 = 128;
            } else {
                i4 = 128;
            }
            i3 |= i4;
        } else {
            color = j;
        }
        z = true;
        if ((i3 & 147) != 146) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                } else {
                    fM413getThicknessD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                }
            } else {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i6 != 0) {
                    fM413getThicknessD9Ej5fM = DividerDefaults.INSTANCE.m413getThicknessD9Ej5fM();
                } else {
                    fM413getThicknessD9Ej5fM = f2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    color = DividerDefaults.INSTANCE.getColor(composerStartRestartGroup, 6);
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1534852205, i3, -1, "androidx.compose.material3.VerticalDivider (Divider.kt:81)");
            }
            Modifier modifier6 = SizeKt.width-3ABfNKs(SizeKt.fillMaxHeight$default(modifier3, 0.0f, 1, (Object) null), fM413getThicknessD9Ej5fM);
            if ((i3 & 112) == 32) {
                z3 = true;
            } else {
                z3 = false;
            }
            z = ((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 ? false : false;
            z4 = z3 | z;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z4) {
                objRememberedValue = new Function1() { // from class: wu3
                    public final Object invoke(Object obj) {
                        return DividerKt.c(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: wu3
                    public final Object invoke(Object obj) {
                        return DividerKt.c(fM413getThicknessD9Ej5fM, color, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier6, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            fM413getThicknessD9Ej5fM = f2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier7 = modifier3;
            final float f4 = fM413getThicknessD9Ej5fM;
            final long j3 = color;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xu3
                public final Object invoke(Object obj, Object obj2) {
                    return DividerKt.a(modifier7, f4, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m416VerticalDivider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m415HorizontalDivider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(float f, long j, DrawScope drawScope) {
        float fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(f);
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(drawScope.mo4557toPx0680j_4(f) / 2.0f)) << 32) | (((long) Float.floatToRawIntBits(0.0f)) & 4294967295L));
        float fMo4557toPx0680j_5 = drawScope.mo4557toPx0680j_4(f) / 2.0f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() & 4294967295L));
        DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j, jM2881constructorimpl, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fMo4557toPx0680j_5)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) & 4294967295L)), fMo4557toPx0680j_4, 0, null, 0.0f, null, 0, 496, null);
        return Unit.INSTANCE;
    }

    public static Unit d(float f, long j, DrawScope drawScope) {
        float fMo4557toPx0680j_4 = drawScope.mo4557toPx0680j_4(f);
        float fMo4557toPx0680j_5 = drawScope.mo4557toPx0680j_4(f) / 2.0f;
        long jM2881constructorimpl = Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(0.0f)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_5)) & 4294967295L));
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.mo3708getSizeNHjbRc() >> 32));
        float fMo4557toPx0680j_6 = drawScope.mo4557toPx0680j_4(f) / 2.0f;
        DrawScope.m3694drawLineNGM6Ib0$default(drawScope, j, jM2881constructorimpl, Offset.m2881constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fMo4557toPx0680j_6)) & 4294967295L)), fMo4557toPx0680j_4, 0, null, 0.0f, null, 0, 496, null);
        return Unit.INSTANCE;
    }

    public static Unit e(Modifier modifier, float f, long j, int i, int i2, Composer composer, int i3) {
        m414Divider9IZ8Weo(modifier, f, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }
}
