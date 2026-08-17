package androidx.compose.foundation;

import androidx.compose.foundation.ImageKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.PainterModifierKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.BitmapPainterKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.graphics.vector.VectorPainterKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001a_\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010\u0017\u001aS\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"Image", "", "bitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;II)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "Image-5h-nEew", "(Landroidx/compose/ui/graphics/ImageBitmap;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;II)V", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;II)V", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "(Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ImageKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0114  */
    /* JADX WARN: Code duplicated, block: B:102:0x0122  */
    /* JADX WARN: Code duplicated, block: B:103:0x0124  */
    /* JADX WARN: Code duplicated, block: B:106:0x012b  */
    /* JADX WARN: Code duplicated, block: B:108:0x0133  */
    /* JADX WARN: Code duplicated, block: B:110:0x0146  */
    /* JADX WARN: Code duplicated, block: B:113:0x0175  */
    /* JADX WARN: Code duplicated, block: B:116:0x0198  */
    /* JADX WARN: Code duplicated, block: B:119:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:120:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:123:0x01df  */
    /* JADX WARN: Code duplicated, block: B:125:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:128:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0057  */
    /* JADX WARN: Code duplicated, block: B:35:0x005c  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0068  */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:44:0x0072  */
    /* JADX WARN: Code duplicated, block: B:46:0x0077  */
    /* JADX WARN: Code duplicated, block: B:48:0x007b  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:55:0x008f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0093  */
    /* JADX WARN: Code duplicated, block: B:59:0x0096  */
    /* JADX WARN: Code duplicated, block: B:61:0x009e  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:66:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:67:0x00af  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00be  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:81:0x00da  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:94:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:95:0x0101  */
    /* JADX WARN: Code duplicated, block: B:98:0x0109  */
    public static final void Image(final Painter painter, final String str, Modifier modifier, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        int i5;
        int i6;
        ContentScale contentScale2;
        int i7;
        int i8;
        float f2;
        int i9;
        int i10;
        int i11;
        boolean z;
        final Alignment alignment2;
        final ColorFilter colorFilter2;
        final Modifier modifier3;
        final ContentScale contentScale3;
        final float f3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Alignment center;
        ContentScale fit;
        float f4;
        ColorFilter colorFilter3;
        int i12;
        Modifier modifierSemantics$default;
        Object objRememberedValue;
        Function0<ComposeUiNode> constructor;
        boolean z2;
        Object objRememberedValue2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1142754848);
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(painter) : composerStartRestartGroup.changedInstance(painter) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        int i13 = i2 & 4;
        if (i13 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(alignment)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        contentScale2 = contentScale;
                        if (composerStartRestartGroup.changed(contentScale2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        if ((196608 & i) == 0) {
                            f2 = f;
                            if (composerStartRestartGroup.changed(f2)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                        i10 = i2 & 64;
                        if (i10 != 0) {
                            i3 |= 1572864;
                        } else if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(colorFilter)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                        if ((i3 & 599187) != 599186) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            if (i13 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                center = Alignment.INSTANCE.getCenter();
                            } else {
                                center = alignment;
                            }
                            if (i6 != 0) {
                                fit = ContentScale.INSTANCE.getFit();
                            } else {
                                fit = contentScale2;
                            }
                            if (i8 != 0) {
                                f4 = 1.0f;
                            } else {
                                f4 = f2;
                            }
                            if (i10 != 0) {
                                colorFilter3 = null;
                            } else {
                                colorFilter3 = colorFilter;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                            }
                            if (str != null) {
                                composerStartRestartGroup.startReplaceGroup(1899222916);
                                Modifier.Companion companion = Modifier.INSTANCE;
                                if ((i3 & 112) == 32) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new Function1() { // from class: ak6
                                        public final Object invoke(Object obj) {
                                            return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                i12 = 0;
                                modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue2, 1, null);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                i12 = 0;
                                composerStartRestartGroup.startReplaceGroup(1899381698);
                                composerStartRestartGroup.endReplaceGroup();
                                modifierSemantics$default = Modifier.INSTANCE;
                            }
                            int i14 = i12;
                            Modifier modifier5 = modifier4;
                            Modifier modifierPaint$default = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
                            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i14));
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default);
                            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                            constructor = companion2.getConstructor();
                            if (composerStartRestartGroup.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composerStartRestartGroup.startReusableNode();
                            if (composerStartRestartGroup.getInserting()) {
                                composerStartRestartGroup.createNode(constructor);
                            } else {
                                composerStartRestartGroup.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                            Updater.m2394reconcileimpl(composerM2388constructorimpl, companion2.getApplyOnDeactivatedNodeAssertion());
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                            Updater.m2392initimpl(composerM2388constructorimpl, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
                            composerStartRestartGroup.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            colorFilter2 = colorFilter3;
                            f3 = f4;
                            contentScale3 = fit;
                            alignment2 = center;
                            modifier3 = modifier5;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            alignment2 = alignment;
                            colorFilter2 = colorFilter;
                            modifier3 = modifier2;
                            contentScale3 = contentScale2;
                            f3 = f2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                                public final Object invoke(Object obj, Object obj2) {
                                    return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    f2 = f;
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(colorFilter)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i13 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            center = Alignment.INSTANCE.getCenter();
                        } else {
                            center = alignment;
                        }
                        if (i6 != 0) {
                            fit = ContentScale.INSTANCE.getFit();
                        } else {
                            fit = contentScale2;
                        }
                        if (i8 != 0) {
                            f4 = 1.0f;
                        } else {
                            f4 = f2;
                        }
                        if (i10 != 0) {
                            colorFilter3 = null;
                        } else {
                            colorFilter3 = colorFilter;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                        }
                        if (str != null) {
                            composerStartRestartGroup.startReplaceGroup(1899222916);
                            Modifier.Companion companion3 = Modifier.INSTANCE;
                            if ((i3 & 112) == 32) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i12 = 0;
                            modifierSemantics$default = SemanticsModifierKt.semantics$default(companion3, false, (Function1) objRememberedValue2, 1, null);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            i12 = 0;
                            composerStartRestartGroup.startReplaceGroup(1899381698);
                            composerStartRestartGroup.endReplaceGroup();
                            modifierSemantics$default = Modifier.INSTANCE;
                        }
                        int i15 = i12;
                        Modifier modifier6 = modifier4;
                        Modifier modifierPaint$default2 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        MeasurePolicy measurePolicy2 = (MeasurePolicy) objRememberedValue;
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i15));
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default2);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                        constructor = companion4.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicy2, companion4.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
                        Updater.m2394reconcileimpl(composerM2388constructorimpl2, companion4.getApplyOnDeactivatedNodeAssertion());
                        Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
                        Updater.m2392initimpl(composerM2388constructorimpl2, Integer.valueOf(iHashCode2), companion4.getSetCompositeKeyHash());
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorFilter2 = colorFilter3;
                        f3 = f4;
                        contentScale3 = fit;
                        alignment2 = center;
                        modifier3 = modifier6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        alignment2 = alignment;
                        colorFilter2 = colorFilter;
                        modifier3 = modifier2;
                        contentScale3 = contentScale2;
                        f3 = f2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                            public final Object invoke(Object obj, Object obj2) {
                                return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                contentScale2 = contentScale;
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        f2 = f;
                        if (composerStartRestartGroup.changed(f2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(colorFilter)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i13 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            center = Alignment.INSTANCE.getCenter();
                        } else {
                            center = alignment;
                        }
                        if (i6 != 0) {
                            fit = ContentScale.INSTANCE.getFit();
                        } else {
                            fit = contentScale2;
                        }
                        if (i8 != 0) {
                            f4 = 1.0f;
                        } else {
                            f4 = f2;
                        }
                        if (i10 != 0) {
                            colorFilter3 = null;
                        } else {
                            colorFilter3 = colorFilter;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                        }
                        if (str != null) {
                            composerStartRestartGroup.startReplaceGroup(1899222916);
                            Modifier.Companion companion5 = Modifier.INSTANCE;
                            if ((i3 & 112) == 32) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i12 = 0;
                            modifierSemantics$default = SemanticsModifierKt.semantics$default(companion5, false, (Function1) objRememberedValue2, 1, null);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            i12 = 0;
                            composerStartRestartGroup.startReplaceGroup(1899381698);
                            composerStartRestartGroup.endReplaceGroup();
                            modifierSemantics$default = Modifier.INSTANCE;
                        }
                        int i16 = i12;
                        Modifier modifier7 = modifier4;
                        Modifier modifierPaint$default3 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        MeasurePolicy measurePolicy3 = (MeasurePolicy) objRememberedValue;
                        int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i16));
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default3);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        ComposeUiNode.Companion companion6 = ComposeUiNode.INSTANCE;
                        constructor = companion6.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicy3, companion6.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion6.getSetResolvedCompositionLocals());
                        Updater.m2394reconcileimpl(composerM2388constructorimpl3, companion6.getApplyOnDeactivatedNodeAssertion());
                        Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion6.getSetModifier());
                        Updater.m2392initimpl(composerM2388constructorimpl3, Integer.valueOf(iHashCode3), companion6.getSetCompositeKeyHash());
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorFilter2 = colorFilter3;
                        f3 = f4;
                        contentScale3 = fit;
                        alignment2 = center;
                        modifier3 = modifier7;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        alignment2 = alignment;
                        colorFilter2 = colorFilter;
                        modifier3 = modifier2;
                        contentScale3 = contentScale2;
                        f3 = f2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                            public final Object invoke(Object obj, Object obj2) {
                                return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                f2 = f;
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion7 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion7, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i17 = i12;
                    Modifier modifier8 = modifier4;
                    Modifier modifierPaint$default4 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy4 = (MeasurePolicy) objRememberedValue;
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i17));
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default4);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                    constructor = companion8.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl4 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl4, measurePolicy4, companion8.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl4, currentCompositionLocalMap4, companion8.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl4, companion8.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl4, modifierMaterializeModifier4, companion8.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl4, Integer.valueOf(iHashCode4), companion8.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier8;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    contentScale2 = contentScale;
                    if (composerStartRestartGroup.changed(contentScale2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        f2 = f;
                        if (composerStartRestartGroup.changed(f2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(colorFilter)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i13 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            center = Alignment.INSTANCE.getCenter();
                        } else {
                            center = alignment;
                        }
                        if (i6 != 0) {
                            fit = ContentScale.INSTANCE.getFit();
                        } else {
                            fit = contentScale2;
                        }
                        if (i8 != 0) {
                            f4 = 1.0f;
                        } else {
                            f4 = f2;
                        }
                        if (i10 != 0) {
                            colorFilter3 = null;
                        } else {
                            colorFilter3 = colorFilter;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                        }
                        if (str != null) {
                            composerStartRestartGroup.startReplaceGroup(1899222916);
                            Modifier.Companion companion9 = Modifier.INSTANCE;
                            if ((i3 & 112) == 32) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i12 = 0;
                            modifierSemantics$default = SemanticsModifierKt.semantics$default(companion9, false, (Function1) objRememberedValue2, 1, null);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            i12 = 0;
                            composerStartRestartGroup.startReplaceGroup(1899381698);
                            composerStartRestartGroup.endReplaceGroup();
                            modifierSemantics$default = Modifier.INSTANCE;
                        }
                        int i18 = i12;
                        Modifier modifier9 = modifier4;
                        Modifier modifierPaint$default5 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        MeasurePolicy measurePolicy5 = (MeasurePolicy) objRememberedValue;
                        int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i18));
                        Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default5);
                        CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        ComposeUiNode.Companion companion10 = ComposeUiNode.INSTANCE;
                        constructor = companion10.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl5 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl5, measurePolicy5, companion10.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl5, currentCompositionLocalMap5, companion10.getSetResolvedCompositionLocals());
                        Updater.m2394reconcileimpl(composerM2388constructorimpl5, companion10.getApplyOnDeactivatedNodeAssertion());
                        Updater.m2396setimpl(composerM2388constructorimpl5, modifierMaterializeModifier5, companion10.getSetModifier());
                        Updater.m2392initimpl(composerM2388constructorimpl5, Integer.valueOf(iHashCode5), companion10.getSetCompositeKeyHash());
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorFilter2 = colorFilter3;
                        f3 = f4;
                        contentScale3 = fit;
                        alignment2 = center;
                        modifier3 = modifier9;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        alignment2 = alignment;
                        colorFilter2 = colorFilter;
                        modifier3 = modifier2;
                        contentScale3 = contentScale2;
                        f3 = f2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                            public final Object invoke(Object obj, Object obj2) {
                                return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                f2 = f;
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion11 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion11, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i19 = i12;
                    Modifier modifier10 = modifier4;
                    Modifier modifierPaint$default6 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy6 = (MeasurePolicy) objRememberedValue;
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i19));
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default6);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion12 = ComposeUiNode.INSTANCE;
                    constructor = companion12.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl6 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl6, measurePolicy6, companion12.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl6, currentCompositionLocalMap6, companion12.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl6, companion12.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl6, modifierMaterializeModifier6, companion12.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl6, Integer.valueOf(iHashCode6), companion12.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            contentScale2 = contentScale;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion13 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion13, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i110 = i12;
                    Modifier modifier11 = modifier4;
                    Modifier modifierPaint$default7 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy7 = (MeasurePolicy) objRememberedValue;
                    int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i110));
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default7);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion14 = ComposeUiNode.INSTANCE;
                    constructor = companion14.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl7 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl7, measurePolicy7, companion14.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl7, currentCompositionLocalMap7, companion14.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl7, companion14.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl7, modifierMaterializeModifier7, companion14.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl7, Integer.valueOf(iHashCode7), companion14.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier11;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = f;
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(colorFilter)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i3 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    center = Alignment.INSTANCE.getCenter();
                } else {
                    center = alignment;
                }
                if (i6 != 0) {
                    fit = ContentScale.INSTANCE.getFit();
                } else {
                    fit = contentScale2;
                }
                if (i8 != 0) {
                    f4 = 1.0f;
                } else {
                    f4 = f2;
                }
                if (i10 != 0) {
                    colorFilter3 = null;
                } else {
                    colorFilter3 = colorFilter;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                }
                if (str != null) {
                    composerStartRestartGroup.startReplaceGroup(1899222916);
                    Modifier.Companion companion15 = Modifier.INSTANCE;
                    if ((i3 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i12 = 0;
                    modifierSemantics$default = SemanticsModifierKt.semantics$default(companion15, false, (Function1) objRememberedValue2, 1, null);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    i12 = 0;
                    composerStartRestartGroup.startReplaceGroup(1899381698);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierSemantics$default = Modifier.INSTANCE;
                }
                int i111 = i12;
                Modifier modifier12 = modifier4;
                Modifier modifierPaint$default8 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MeasurePolicy measurePolicy8 = (MeasurePolicy) objRememberedValue;
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i111));
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default8);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion16 = ComposeUiNode.INSTANCE;
                constructor = companion16.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl8 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl8, measurePolicy8, companion16.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl8, currentCompositionLocalMap8, companion16.getSetResolvedCompositionLocals());
                Updater.m2394reconcileimpl(composerM2388constructorimpl8, companion16.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl8, modifierMaterializeModifier8, companion16.getSetModifier());
                Updater.m2392initimpl(composerM2388constructorimpl8, Integer.valueOf(iHashCode8), companion16.getSetCompositeKeyHash());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorFilter2 = colorFilter3;
                f3 = f4;
                contentScale3 = fit;
                alignment2 = center;
                modifier3 = modifier12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                alignment2 = alignment;
                colorFilter2 = colorFilter;
                modifier3 = modifier2;
                contentScale3 = contentScale2;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                    public final Object invoke(Object obj, Object obj2) {
                        return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(alignment)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    contentScale2 = contentScale;
                    if (composerStartRestartGroup.changed(contentScale2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        f2 = f;
                        if (composerStartRestartGroup.changed(f2)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(colorFilter)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                    if ((i3 & 599187) != 599186) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i13 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            center = Alignment.INSTANCE.getCenter();
                        } else {
                            center = alignment;
                        }
                        if (i6 != 0) {
                            fit = ContentScale.INSTANCE.getFit();
                        } else {
                            fit = contentScale2;
                        }
                        if (i8 != 0) {
                            f4 = 1.0f;
                        } else {
                            f4 = f2;
                        }
                        if (i10 != 0) {
                            colorFilter3 = null;
                        } else {
                            colorFilter3 = colorFilter;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                        }
                        if (str != null) {
                            composerStartRestartGroup.startReplaceGroup(1899222916);
                            Modifier.Companion companion17 = Modifier.INSTANCE;
                            if ((i3 & 112) == 32) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: ak6
                                    public final Object invoke(Object obj) {
                                        return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            i12 = 0;
                            modifierSemantics$default = SemanticsModifierKt.semantics$default(companion17, false, (Function1) objRememberedValue2, 1, null);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            i12 = 0;
                            composerStartRestartGroup.startReplaceGroup(1899381698);
                            composerStartRestartGroup.endReplaceGroup();
                            modifierSemantics$default = Modifier.INSTANCE;
                        }
                        int i112 = i12;
                        Modifier modifier13 = modifier4;
                        Modifier modifierPaint$default9 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        MeasurePolicy measurePolicy9 = (MeasurePolicy) objRememberedValue;
                        int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i112));
                        Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default9);
                        CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        ComposeUiNode.Companion companion18 = ComposeUiNode.INSTANCE;
                        constructor = companion18.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composerM2388constructorimpl9 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl9, measurePolicy9, companion18.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl9, currentCompositionLocalMap9, companion18.getSetResolvedCompositionLocals());
                        Updater.m2394reconcileimpl(composerM2388constructorimpl9, companion18.getApplyOnDeactivatedNodeAssertion());
                        Updater.m2396setimpl(composerM2388constructorimpl9, modifierMaterializeModifier9, companion18.getSetModifier());
                        Updater.m2392initimpl(composerM2388constructorimpl9, Integer.valueOf(iHashCode9), companion18.getSetCompositeKeyHash());
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        colorFilter2 = colorFilter3;
                        f3 = f4;
                        contentScale3 = fit;
                        alignment2 = center;
                        modifier3 = modifier13;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        alignment2 = alignment;
                        colorFilter2 = colorFilter;
                        modifier3 = modifier2;
                        contentScale3 = contentScale2;
                        f3 = f2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                            public final Object invoke(Object obj, Object obj2) {
                                return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                f2 = f;
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion19 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion19, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i113 = i12;
                    Modifier modifier14 = modifier4;
                    Modifier modifierPaint$default10 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy10 = (MeasurePolicy) objRememberedValue;
                    int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i113));
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default10);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion110 = ComposeUiNode.INSTANCE;
                    constructor = companion110.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl10 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl10, measurePolicy10, companion110.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl10, currentCompositionLocalMap10, companion110.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl10, companion110.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl10, modifierMaterializeModifier10, companion110.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl10, Integer.valueOf(iHashCode10), companion110.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier14;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            contentScale2 = contentScale;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion111 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion111, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i114 = i12;
                    Modifier modifier15 = modifier4;
                    Modifier modifierPaint$default11 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy11 = (MeasurePolicy) objRememberedValue;
                    int iHashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i114));
                    Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default11);
                    CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion112 = ComposeUiNode.INSTANCE;
                    constructor = companion112.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl11 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl11, measurePolicy11, companion112.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl11, currentCompositionLocalMap11, companion112.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl11, companion112.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl11, modifierMaterializeModifier11, companion112.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl11, Integer.valueOf(iHashCode11), companion112.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier15;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = f;
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(colorFilter)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i3 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    center = Alignment.INSTANCE.getCenter();
                } else {
                    center = alignment;
                }
                if (i6 != 0) {
                    fit = ContentScale.INSTANCE.getFit();
                } else {
                    fit = contentScale2;
                }
                if (i8 != 0) {
                    f4 = 1.0f;
                } else {
                    f4 = f2;
                }
                if (i10 != 0) {
                    colorFilter3 = null;
                } else {
                    colorFilter3 = colorFilter;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                }
                if (str != null) {
                    composerStartRestartGroup.startReplaceGroup(1899222916);
                    Modifier.Companion companion113 = Modifier.INSTANCE;
                    if ((i3 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i12 = 0;
                    modifierSemantics$default = SemanticsModifierKt.semantics$default(companion113, false, (Function1) objRememberedValue2, 1, null);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    i12 = 0;
                    composerStartRestartGroup.startReplaceGroup(1899381698);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierSemantics$default = Modifier.INSTANCE;
                }
                int i115 = i12;
                Modifier modifier16 = modifier4;
                Modifier modifierPaint$default12 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MeasurePolicy measurePolicy12 = (MeasurePolicy) objRememberedValue;
                int iHashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i115));
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default12);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion114 = ComposeUiNode.INSTANCE;
                constructor = companion114.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl12 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl12, measurePolicy12, companion114.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl12, currentCompositionLocalMap12, companion114.getSetResolvedCompositionLocals());
                Updater.m2394reconcileimpl(composerM2388constructorimpl12, companion114.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl12, modifierMaterializeModifier12, companion114.getSetModifier());
                Updater.m2392initimpl(composerM2388constructorimpl12, Integer.valueOf(iHashCode12), companion114.getSetCompositeKeyHash());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorFilter2 = colorFilter3;
                f3 = f4;
                contentScale3 = fit;
                alignment2 = center;
                modifier3 = modifier16;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                alignment2 = alignment;
                colorFilter2 = colorFilter;
                modifier3 = modifier2;
                contentScale3 = contentScale2;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                    public final Object invoke(Object obj, Object obj2) {
                        return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                contentScale2 = contentScale;
                if (composerStartRestartGroup.changed(contentScale2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    f2 = f;
                    if (composerStartRestartGroup.changed(f2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(colorFilter)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
                if ((i3 & 599187) != 599186) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        center = Alignment.INSTANCE.getCenter();
                    } else {
                        center = alignment;
                    }
                    if (i6 != 0) {
                        fit = ContentScale.INSTANCE.getFit();
                    } else {
                        fit = contentScale2;
                    }
                    if (i8 != 0) {
                        f4 = 1.0f;
                    } else {
                        f4 = f2;
                    }
                    if (i10 != 0) {
                        colorFilter3 = null;
                    } else {
                        colorFilter3 = colorFilter;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                    }
                    if (str != null) {
                        composerStartRestartGroup.startReplaceGroup(1899222916);
                        Modifier.Companion companion115 = Modifier.INSTANCE;
                        if ((i3 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: ak6
                                public final Object invoke(Object obj) {
                                    return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        i12 = 0;
                        modifierSemantics$default = SemanticsModifierKt.semantics$default(companion115, false, (Function1) objRememberedValue2, 1, null);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        i12 = 0;
                        composerStartRestartGroup.startReplaceGroup(1899381698);
                        composerStartRestartGroup.endReplaceGroup();
                        modifierSemantics$default = Modifier.INSTANCE;
                    }
                    int i116 = i12;
                    Modifier modifier17 = modifier4;
                    Modifier modifierPaint$default13 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MeasurePolicy measurePolicy13 = (MeasurePolicy) objRememberedValue;
                    int iHashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i116));
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default13);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion116 = ComposeUiNode.INSTANCE;
                    constructor = companion116.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composerM2388constructorimpl13 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl13, measurePolicy13, companion116.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl13, currentCompositionLocalMap13, companion116.getSetResolvedCompositionLocals());
                    Updater.m2394reconcileimpl(composerM2388constructorimpl13, companion116.getApplyOnDeactivatedNodeAssertion());
                    Updater.m2396setimpl(composerM2388constructorimpl13, modifierMaterializeModifier13, companion116.getSetModifier());
                    Updater.m2392initimpl(composerM2388constructorimpl13, Integer.valueOf(iHashCode13), companion116.getSetCompositeKeyHash());
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    colorFilter2 = colorFilter3;
                    f3 = f4;
                    contentScale3 = fit;
                    alignment2 = center;
                    modifier3 = modifier17;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    alignment2 = alignment;
                    colorFilter2 = colorFilter;
                    modifier3 = modifier2;
                    contentScale3 = contentScale2;
                    f3 = f2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                        public final Object invoke(Object obj, Object obj2) {
                            return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            f2 = f;
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(colorFilter)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i3 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    center = Alignment.INSTANCE.getCenter();
                } else {
                    center = alignment;
                }
                if (i6 != 0) {
                    fit = ContentScale.INSTANCE.getFit();
                } else {
                    fit = contentScale2;
                }
                if (i8 != 0) {
                    f4 = 1.0f;
                } else {
                    f4 = f2;
                }
                if (i10 != 0) {
                    colorFilter3 = null;
                } else {
                    colorFilter3 = colorFilter;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                }
                if (str != null) {
                    composerStartRestartGroup.startReplaceGroup(1899222916);
                    Modifier.Companion companion117 = Modifier.INSTANCE;
                    if ((i3 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i12 = 0;
                    modifierSemantics$default = SemanticsModifierKt.semantics$default(companion117, false, (Function1) objRememberedValue2, 1, null);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    i12 = 0;
                    composerStartRestartGroup.startReplaceGroup(1899381698);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierSemantics$default = Modifier.INSTANCE;
                }
                int i117 = i12;
                Modifier modifier18 = modifier4;
                Modifier modifierPaint$default14 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MeasurePolicy measurePolicy14 = (MeasurePolicy) objRememberedValue;
                int iHashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i117));
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default14);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion118 = ComposeUiNode.INSTANCE;
                constructor = companion118.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl14 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl14, measurePolicy14, companion118.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl14, currentCompositionLocalMap14, companion118.getSetResolvedCompositionLocals());
                Updater.m2394reconcileimpl(composerM2388constructorimpl14, companion118.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl14, modifierMaterializeModifier14, companion118.getSetModifier());
                Updater.m2392initimpl(composerM2388constructorimpl14, Integer.valueOf(iHashCode14), companion118.getSetCompositeKeyHash());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorFilter2 = colorFilter3;
                f3 = f4;
                contentScale3 = fit;
                alignment2 = center;
                modifier3 = modifier18;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                alignment2 = alignment;
                colorFilter2 = colorFilter;
                modifier3 = modifier2;
                contentScale3 = contentScale2;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                    public final Object invoke(Object obj, Object obj2) {
                        return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        contentScale2 = contentScale;
        i8 = i2 & 32;
        if (i8 != 0) {
            if ((196608 & i) == 0) {
                f2 = f;
                if (composerStartRestartGroup.changed(f2)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(colorFilter)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
            if ((i3 & 599187) != 599186) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    center = Alignment.INSTANCE.getCenter();
                } else {
                    center = alignment;
                }
                if (i6 != 0) {
                    fit = ContentScale.INSTANCE.getFit();
                } else {
                    fit = contentScale2;
                }
                if (i8 != 0) {
                    f4 = 1.0f;
                } else {
                    f4 = f2;
                }
                if (i10 != 0) {
                    colorFilter3 = null;
                } else {
                    colorFilter3 = colorFilter;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
                }
                if (str != null) {
                    composerStartRestartGroup.startReplaceGroup(1899222916);
                    Modifier.Companion companion119 = Modifier.INSTANCE;
                    if ((i3 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ak6
                            public final Object invoke(Object obj) {
                                return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    i12 = 0;
                    modifierSemantics$default = SemanticsModifierKt.semantics$default(companion119, false, (Function1) objRememberedValue2, 1, null);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    i12 = 0;
                    composerStartRestartGroup.startReplaceGroup(1899381698);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierSemantics$default = Modifier.INSTANCE;
                }
                int i118 = i12;
                Modifier modifier19 = modifier4;
                Modifier modifierPaint$default15 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MeasurePolicy measurePolicy15 = (MeasurePolicy) objRememberedValue;
                int iHashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i118));
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default15);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion1110 = ComposeUiNode.INSTANCE;
                constructor = companion1110.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl15 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl15, measurePolicy15, companion1110.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl15, currentCompositionLocalMap15, companion1110.getSetResolvedCompositionLocals());
                Updater.m2394reconcileimpl(composerM2388constructorimpl15, companion1110.getApplyOnDeactivatedNodeAssertion());
                Updater.m2396setimpl(composerM2388constructorimpl15, modifierMaterializeModifier15, companion1110.getSetModifier());
                Updater.m2392initimpl(composerM2388constructorimpl15, Integer.valueOf(iHashCode15), companion1110.getSetCompositeKeyHash());
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                colorFilter2 = colorFilter3;
                f3 = f4;
                contentScale3 = fit;
                alignment2 = center;
                modifier3 = modifier19;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                alignment2 = alignment;
                colorFilter2 = colorFilter;
                modifier3 = modifier2;
                contentScale3 = contentScale2;
                f3 = f2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                    public final Object invoke(Object obj, Object obj2) {
                        return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        f2 = f;
        i10 = i2 & 64;
        if (i10 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(colorFilter)) {
                i11 = 1048576;
            } else {
                i11 = 524288;
            }
            i3 |= i11;
        }
        if ((i3 & 599187) != 599186) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i13 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                center = Alignment.INSTANCE.getCenter();
            } else {
                center = alignment;
            }
            if (i6 != 0) {
                fit = ContentScale.INSTANCE.getFit();
            } else {
                fit = contentScale2;
            }
            if (i8 != 0) {
                f4 = 1.0f;
            } else {
                f4 = f2;
            }
            if (i10 != 0) {
                colorFilter3 = null;
            } else {
                colorFilter3 = colorFilter;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1142754848, i3, -1, "androidx.compose.foundation.Image (Image.kt:247)");
            }
            if (str != null) {
                composerStartRestartGroup.startReplaceGroup(1899222916);
                Modifier.Companion companion1111 = Modifier.INSTANCE;
                if ((i3 & 112) == 32) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue2 = new Function1() { // from class: ak6
                        public final Object invoke(Object obj) {
                            return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: ak6
                        public final Object invoke(Object obj) {
                            return ImageKt.Image$lambda$0$0(str, (SemanticsPropertyReceiver) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                i12 = 0;
                modifierSemantics$default = SemanticsModifierKt.semantics$default(companion1111, false, (Function1) objRememberedValue2, 1, null);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                i12 = 0;
                composerStartRestartGroup.startReplaceGroup(1899381698);
                composerStartRestartGroup.endReplaceGroup();
                modifierSemantics$default = Modifier.INSTANCE;
            }
            int i119 = i12;
            Modifier modifier110 = modifier4;
            Modifier modifierPaint$default16 = PainterModifierKt.paint$default(ClipKt.clipToBounds(modifier4.then(modifierSemantics$default)), painter, false, center, fit, f4, colorFilter3, 2, null);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = ImageKt$Image$1$1.INSTANCE;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy16 = (MeasurePolicy) objRememberedValue;
            int iHashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, i119));
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPaint$default16);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion1112 = ComposeUiNode.INSTANCE;
            constructor = companion1112.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl16 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl16, measurePolicy16, companion1112.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl16, currentCompositionLocalMap16, companion1112.getSetResolvedCompositionLocals());
            Updater.m2394reconcileimpl(composerM2388constructorimpl16, companion1112.getApplyOnDeactivatedNodeAssertion());
            Updater.m2396setimpl(composerM2388constructorimpl16, modifierMaterializeModifier16, companion1112.getSetModifier());
            Updater.m2392initimpl(composerM2388constructorimpl16, Integer.valueOf(iHashCode16), companion1112.getSetCompositeKeyHash());
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colorFilter2 = colorFilter3;
            f3 = f4;
            contentScale3 = fit;
            alignment2 = center;
            modifier3 = modifier110;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            alignment2 = alignment;
            colorFilter2 = colorFilter;
            modifier3 = modifier2;
            contentScale3 = contentScale2;
            f3 = f2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bk6
                public final Object invoke(Object obj, Object obj2) {
                    return ImageKt.a(painter, str, modifier3, alignment2, contentScale3, f3, colorFilter2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit Image$lambda$0$0(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        SemanticsPropertiesKt.m5264setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5249getImageo7Vup1c());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: Image-5h-nEew, reason: not valid java name */
    public static final void m13Image5hnEew(ImageBitmap imageBitmap, String str, Modifier modifier, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, Composer composer, int i2, int i3) {
        Modifier modifier2 = (i3 & 4) != 0 ? Modifier.INSTANCE : modifier;
        Alignment center = (i3 & 8) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i3 & 16) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i3 & 32) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i3 & 64) != 0 ? null : colorFilter;
        int iM3711getDefaultFilterQualityfv9h1I = (i3 & 128) != 0 ? DrawScope.INSTANCE.m3711getDefaultFilterQualityfv9h1I() : i;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1396260732, i2, -1, "androidx.compose.foundation.Image (Image.kt:156)");
        }
        boolean zChanged = composer.changed(imageBitmap);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = BitmapPainterKt.m3831BitmapPainterQZhYCtY$default(imageBitmap, 0L, 0L, iM3711getDefaultFilterQualityfv9h1I, 6, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        Image((BitmapPainter) objRememberedValue, str, modifier2, center, fit, f2, colorFilter2, composer, BitmapPainter.$stable | (i2 & 112) | (i2 & 896) | (i2 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i2) | (458752 & i2) | (i2 & 3670016), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public static Unit a(Painter painter, String str, Modifier modifier, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, int i2, Composer composer, int i3) {
        Image(painter, str, modifier, alignment, contentScale, f, colorFilter, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void Image(ImageVector imageVector, String str, Modifier modifier, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, Composer composer, int i, int i2) {
        if ((i2 & 4) != 0) {
            modifier = Modifier.INSTANCE;
        }
        Modifier modifier2 = modifier;
        if ((i2 & 8) != 0) {
            alignment = Alignment.INSTANCE.getCenter();
        }
        Alignment alignment2 = alignment;
        ContentScale fit = (i2 & 16) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i2 & 32) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i2 & 64) != 0 ? null : colorFilter;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1595907091, i, -1, "androidx.compose.foundation.Image (Image.kt:202)");
        }
        Image(VectorPainterKt.rememberVectorPainter(imageVector, composer, i & 14), str, modifier2, alignment2, fit, f2, colorFilter2, composer, VectorPainter.$stable | (i & 112) | (i & 896) | (i & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i) | (458752 & i) | (3670016 & i), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Consider usage of the Image composable that consumes an optional FilterQuality parameter", replaceWith = @ReplaceWith(expression = "Image(bitmap, contentDescription, modifier, alignment, contentScale, alpha, colorFilter, DefaultFilterQuality)", imports = {"androidx.compose.foundation", "androidx.compose.ui.graphics.DefaultAlpha", "androidx.compose.ui.Alignment", "androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultFilterQuality", "androidx.compose.ui.layout.ContentScale.Fit"}))
    public static final /* synthetic */ void Image(ImageBitmap imageBitmap, String str, Modifier modifier, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, Composer composer, int i, int i2) {
        if ((i2 & 4) != 0) {
            modifier = Modifier.INSTANCE;
        }
        Modifier modifier2 = modifier;
        Alignment center = (i2 & 8) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i2 & 16) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i2 & 32) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i2 & 64) != 0 ? null : colorFilter;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2123228673, i, -1, "androidx.compose.foundation.Image (Image.kt:98)");
        }
        m13Image5hnEew(imageBitmap, str, modifier2, center, fit, f2, colorFilter2, FilterQuality.INSTANCE.m3234getLowfv9h1I(), composer, i & 4194302, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
