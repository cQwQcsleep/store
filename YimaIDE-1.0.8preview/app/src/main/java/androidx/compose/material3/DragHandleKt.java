package androidx.compose.material3;

import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.material3.DragHandleKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001a0\u0010\r\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0002¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0013X\u008a\u008e\u0002"}, d2 = {"VerticalDragHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "sizes", "Landroidx/compose/material3/DragHandleSizes;", "colors", "Landroidx/compose/material3/DragHandleColors;", "shapes", "Landroidx/compose/material3/DragHandleShapes;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DragHandleSizes;Landroidx/compose/material3/DragHandleColors;Landroidx/compose/material3/DragHandleShapes;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "pressable", "onPressed", "Lkotlin/Function0;", "onReleasedOrCancelled", "material3", "isDragged", "", "isPressed"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DragHandleKt {
    /* JADX WARN: Code duplicated, block: B:101:0x0134  */
    /* JADX WARN: Code duplicated, block: B:104:0x014e  */
    /* JADX WARN: Code duplicated, block: B:107:0x016b  */
    /* JADX WARN: Code duplicated, block: B:110:0x017f  */
    /* JADX WARN: Code duplicated, block: B:123:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:136:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:149:0x021e  */
    /* JADX WARN: Code duplicated, block: B:152:0x024e  */
    /* JADX WARN: Code duplicated, block: B:154:0x0254  */
    /* JADX WARN: Code duplicated, block: B:157:0x0260  */
    /* JADX WARN: Code duplicated, block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x009f  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:83:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:87:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:92:0x0103  */
    /* JADX WARN: Code duplicated, block: B:95:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0115  */
    /* JADX WARN: Code duplicated, block: B:99:0x0127  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final void VerticalDragHandle(Modifier modifier, DragHandleSizes dragHandleSizes, DragHandleColors dragHandleColors, DragHandleShapes dragHandleShapes, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        DragHandleSizes dragHandleSizes2;
        final DragHandleColors dragHandleColorsColors;
        final DragHandleShapes dragHandleShapesShapes;
        MutableInteractionSource mutableInteractionSource2;
        boolean z;
        boolean z2;
        Modifier modifier3;
        final DragHandleSizes dragHandleSizesSizes;
        final DragHandleColors dragHandleColors2;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        MutableInteractionSource mutableInteractionSource4;
        final State stateCollectIsDraggedAsState;
        Object objRememberedValue;
        Composer.Companion companion;
        final MutableState mutableState;
        Object objRememberedValue2;
        Object objRememberedValue3;
        boolean zChanged;
        Object objRememberedValue4;
        boolean zChanged2;
        Object objRememberedValue5;
        boolean z3;
        Object objRememberedValue6;
        Object objRememberedValue7;
        Composer composerStartRestartGroup = composer.startRestartGroup(1693656835);
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
        if ((i & 48) == 0) {
            if ((i2 & 2) == 0) {
                dragHandleSizes2 = dragHandleSizes;
                int i5 = composerStartRestartGroup.changed(dragHandleSizes2) ? 32 : 16;
                i3 |= i5;
            } else {
                dragHandleSizes2 = dragHandleSizes;
            }
            i3 |= i5;
        } else {
            dragHandleSizes2 = dragHandleSizes;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                dragHandleColorsColors = dragHandleColors;
                int i6 = composerStartRestartGroup.changed(dragHandleColorsColors) ? 256 : 128;
                i3 |= i6;
            } else {
                dragHandleColorsColors = dragHandleColors;
            }
            i3 |= i6;
        } else {
            dragHandleColorsColors = dragHandleColors;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                dragHandleShapesShapes = dragHandleShapes;
                int i7 = composerStartRestartGroup.changed(dragHandleShapesShapes) ? 2048 : 1024;
                i3 |= i7;
            } else {
                dragHandleShapesShapes = dragHandleShapes;
            }
            i3 |= i7;
        } else {
            dragHandleShapesShapes = dragHandleShapes;
        }
        int i8 = i2 & 16;
        if (i8 == 0) {
            if ((i & 24576) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                i3 |= composerStartRestartGroup.changed(mutableInteractionSource2) ? 16384 : 8192;
            }
            z = true;
            if ((i3 & 9363) != 9362) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i4 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        dragHandleSizesSizes = VerticalDragHandleDefaults.INSTANCE.sizes();
                        i3 &= -113;
                    } else {
                        dragHandleSizesSizes = dragHandleSizes2;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        dragHandleColorsColors = VerticalDragHandleDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        dragHandleShapesShapes = VerticalDragHandleDefaults.INSTANCE.shapes(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource2 = null;
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 2) != 0) {
                        i3 &= -113;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                    }
                    modifier3 = modifier2;
                    dragHandleSizesSizes = dragHandleSizes2;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1693656835, i3, -1, "androidx.compose.material3.VerticalDragHandle (DragHandle.kt:78)");
                }
                if (mutableInteractionSource2 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1544610024);
                    objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue7 = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                    }
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue7;
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-188374113);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                stateCollectIsDraggedAsState = DragInteractionKt.collectIsDraggedAsState(mutableInteractionSource4, composerStartRestartGroup, 0);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                Modifier modifierHoverable$default = HoverableKt.hoverable$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier3), mutableInteractionSource4, false, 2, (Object) null);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: wx3
                        public final Object invoke() {
                            return DragHandleKt.e(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function0 function0 = (Function0) objRememberedValue2;
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: xx3
                        public final Object invoke() {
                            return DragHandleKt.d(mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifierPressable = pressable(modifierHoverable$default, mutableInteractionSource4, function0, (Function0) objRememberedValue3);
                zChanged = composerStartRestartGroup.changed(stateCollectIsDraggedAsState) | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(dragHandleShapesShapes)) || (i3 & 3072) == 2048);
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: yx3
                        public final Object invoke(Object obj) {
                            return DragHandleKt.b(dragHandleShapesShapes, stateCollectIsDraggedAsState, mutableState, (GraphicsLayerScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierPressable, (Function1) objRememberedValue4);
                zChanged2 = composerStartRestartGroup.changed(stateCollectIsDraggedAsState) | ((((i3 & 112) ^ 48) <= 32 && composerStartRestartGroup.changed(dragHandleSizesSizes)) || (i3 & 48) == 32);
                objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = new Function3() { // from class: zx3
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return DragHandleKt.f(dragHandleSizesSizes, stateCollectIsDraggedAsState, mutableState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                Modifier modifierLayout = LayoutModifierKt.layout(modifierGraphicsLayer, (Function3) objRememberedValue5);
                boolean zChanged3 = composerStartRestartGroup.changed(stateCollectIsDraggedAsState);
                if ((((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 || !composerStartRestartGroup.changed(dragHandleColorsColors)) && (i3 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 256) {
                }
                z3 = zChanged3 | z;
                objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue6 == companion.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: ay3
                        public final Object invoke(Object obj) {
                            return DragHandleKt.a(dragHandleColorsColors, stateCollectIsDraggedAsState, mutableState, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                BoxKt.Box(IndicationKt.indication(DrawModifierKt.drawBehind(modifierLayout, (Function1) objRememberedValue6), mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                dragHandleSizesSizes = dragHandleSizes2;
            }
            dragHandleColors2 = dragHandleColorsColors;
            mutableInteractionSource3 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier4 = modifier3;
                final DragHandleSizes dragHandleSizes3 = dragHandleSizesSizes;
                final DragHandleShapes dragHandleShapes2 = dragHandleShapesShapes;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: by3
                    public final Object invoke(Object obj, Object obj2) {
                        return DragHandleKt.g(modifier4, dragHandleSizes3, dragHandleColors2, dragHandleShapes2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        mutableInteractionSource2 = mutableInteractionSource;
        z = true;
        if ((i3 & 9363) != 9362) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    dragHandleSizesSizes = VerticalDragHandleDefaults.INSTANCE.sizes();
                    i3 &= -113;
                } else {
                    dragHandleSizesSizes = dragHandleSizes2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    dragHandleColorsColors = VerticalDragHandleDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    dragHandleShapesShapes = VerticalDragHandleDefaults.INSTANCE.shapes(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource2 = null;
                }
            } else {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    dragHandleSizesSizes = VerticalDragHandleDefaults.INSTANCE.sizes();
                    i3 &= -113;
                } else {
                    dragHandleSizesSizes = dragHandleSizes2;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    dragHandleColorsColors = VerticalDragHandleDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    dragHandleShapesShapes = VerticalDragHandleDefaults.INSTANCE.shapes(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource2 = null;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1693656835, i3, -1, "androidx.compose.material3.VerticalDragHandle (DragHandle.kt:78)");
            }
            if (mutableInteractionSource2 == null) {
                composerStartRestartGroup.startReplaceGroup(-1544610024);
                objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue7;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-188374113);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = mutableInteractionSource2;
            }
            stateCollectIsDraggedAsState = DragInteractionKt.collectIsDraggedAsState(mutableInteractionSource4, composerStartRestartGroup, 0);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            Modifier modifierHoverable$default2 = HoverableKt.hoverable$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier3), mutableInteractionSource4, false, 2, (Object) null);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: wx3
                    public final Object invoke() {
                        return DragHandleKt.e(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function0 function1 = (Function0) objRememberedValue2;
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: xx3
                    public final Object invoke() {
                        return DragHandleKt.d(mutableState);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierPressable2 = pressable(modifierHoverable$default2, mutableInteractionSource4, function1, (Function0) objRememberedValue3);
            zChanged = composerStartRestartGroup.changed(stateCollectIsDraggedAsState) | ((((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) ^ 3072) <= 2048 && composerStartRestartGroup.changed(dragHandleShapesShapes)) || (i3 & 3072) == 2048);
            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue4 = new Function1() { // from class: yx3
                    public final Object invoke(Object obj) {
                        return DragHandleKt.b(dragHandleShapesShapes, stateCollectIsDraggedAsState, mutableState, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            } else {
                objRememberedValue4 = new Function1() { // from class: yx3
                    public final Object invoke(Object obj) {
                        return DragHandleKt.b(dragHandleShapesShapes, stateCollectIsDraggedAsState, mutableState, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierGraphicsLayer2 = GraphicsLayerModifierKt.graphicsLayer(modifierPressable2, (Function1) objRememberedValue4);
            zChanged2 = composerStartRestartGroup.changed(stateCollectIsDraggedAsState) | ((((i3 & 112) ^ 48) <= 32 && composerStartRestartGroup.changed(dragHandleSizesSizes)) || (i3 & 48) == 32);
            objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged2) {
                objRememberedValue5 = new Function3() { // from class: zx3
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return DragHandleKt.f(dragHandleSizesSizes, stateCollectIsDraggedAsState, mutableState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            } else {
                objRememberedValue5 = new Function3() { // from class: zx3
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return DragHandleKt.f(dragHandleSizesSizes, stateCollectIsDraggedAsState, mutableState, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            Modifier modifierLayout2 = LayoutModifierKt.layout(modifierGraphicsLayer2, (Function3) objRememberedValue5);
            boolean zChanged4 = composerStartRestartGroup.changed(stateCollectIsDraggedAsState);
            z = ((i3 & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) > 256 ? false : false;
            z3 = zChanged4 | z;
            objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                objRememberedValue6 = new Function1() { // from class: ay3
                    public final Object invoke(Object obj) {
                        return DragHandleKt.a(dragHandleColorsColors, stateCollectIsDraggedAsState, mutableState, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            } else {
                objRememberedValue6 = new Function1() { // from class: ay3
                    public final Object invoke(Object obj) {
                        return DragHandleKt.a(dragHandleColorsColors, stateCollectIsDraggedAsState, mutableState, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            BoxKt.Box(IndicationKt.indication(DrawModifierKt.drawBehind(modifierLayout2, (Function1) objRememberedValue6), mutableInteractionSource4, RippleKt.m782rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            dragHandleSizesSizes = dragHandleSizes2;
        }
        dragHandleColors2 = dragHandleColorsColors;
        mutableInteractionSource3 = mutableInteractionSource2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier3;
            final DragHandleSizes dragHandleSizes4 = dragHandleSizesSizes;
            final DragHandleShapes dragHandleShapes3 = dragHandleShapesShapes;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: by3
                public final Object invoke(Object obj, Object obj2) {
                    return DragHandleKt.g(modifier5, dragHandleSizes4, dragHandleColors2, dragHandleShapes3, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean VerticalDragHandle$lambda$1(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean VerticalDragHandle$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void VerticalDragHandle$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static Unit a(DragHandleColors dragHandleColors, State state, MutableState mutableState, DrawScope drawScope) {
        long pressedColor;
        if (VerticalDragHandle$lambda$1(state)) {
            pressedColor = dragHandleColors.getDraggedColor();
        } else {
            pressedColor = VerticalDragHandle$lambda$3(mutableState) ? dragHandleColors.getPressedColor() : dragHandleColors.getColor();
        }
        DrawScope.m3702drawRectnJ9OG0$default(drawScope, pressedColor, 0L, 0L, 0.0f, null, null, 0, 126, null);
        return Unit.INSTANCE;
    }

    public static Unit b(DragHandleShapes dragHandleShapes, State state, MutableState mutableState, GraphicsLayerScope graphicsLayerScope) {
        Shape pressedShape;
        if (VerticalDragHandle$lambda$1(state)) {
            pressedShape = dragHandleShapes.getDraggedShape();
        } else {
            pressedShape = VerticalDragHandle$lambda$3(mutableState) ? dragHandleShapes.getPressedShape() : dragHandleShapes.getShape();
        }
        graphicsLayerScope.setShape(pressedShape);
        graphicsLayerScope.setClip(true);
        return Unit.INSTANCE;
    }

    public static Unit c(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit d(MutableState mutableState) {
        VerticalDragHandle$lambda$4(mutableState, false);
        return Unit.INSTANCE;
    }

    public static Unit e(MutableState mutableState) {
        VerticalDragHandle$lambda$4(mutableState, true);
        return Unit.INSTANCE;
    }

    public static MeasureResult f(DragHandleSizes dragHandleSizes, State state, MutableState mutableState, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        long pressedSize;
        if (VerticalDragHandle$lambda$1(state)) {
            pressedSize = dragHandleSizes.getDraggedSize();
        } else {
            pressedSize = VerticalDragHandle$lambda$3(mutableState) ? dragHandleSizes.getPressedSize() : dragHandleSizes.getSize();
        }
        long jMo4558toSizeXkaWNTQ = measureScope.mo4558toSizeXkaWNTQ(pressedSize);
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.INSTANCE.m5985fixedJhjzzOo(Math.round(Float.intBitsToFloat((int) (jMo4558toSizeXkaWNTQ >> 32))), Math.round(Float.intBitsToFloat((int) (jMo4558toSizeXkaWNTQ & 4294967295L)))));
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: vx3
            public final Object invoke(Object obj) {
                return DragHandleKt.c(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static Unit g(Modifier modifier, DragHandleSizes dragHandleSizes, DragHandleColors dragHandleColors, DragHandleShapes dragHandleShapes, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        VerticalDragHandle(modifier, dragHandleSizes, dragHandleColors, dragHandleShapes, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final Modifier pressable(Modifier modifier, MutableInteractionSource mutableInteractionSource, final Function0<Unit> function0, final Function0<Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, mutableInteractionSource, new PointerInputEventHandler() { // from class: androidx.compose.material3.DragHandleKt.pressable.1

            /* JADX INFO: renamed from: androidx.compose.material3.DragHandleKt$pressable$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
            @DebugMetadata(c = "androidx.compose.material3.DragHandleKt$pressable$1$1", f = "DragHandle.kt", i = {0}, l = {341, 343}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
            public static final class C00031 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function0<Unit> $onPressed;
                final /* synthetic */ Function0<Unit> $onReleasedOrCancelled;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00031(Function0<Unit> function0, Function0<Unit> function1, Continuation<? super C00031> continuation) {
                    super(2, continuation);
                    this.$onPressed = function0;
                    this.$onReleasedOrCancelled = function1;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00031 c00031 = new C00031(this.$onPressed, this.$onReleasedOrCancelled, continuation);
                    c00031.L$0 = obj;
                    return c00031;
                }

                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:15:0x004d, code lost:
                
                    if (androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(r1, r10, r7) == r0) goto L16;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    C00031 c00031;
                    AwaitPointerEventScope awaitPointerEventScope;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        PointerEventPass pointerEventPass = PointerEventPass.Initial;
                        this.L$0 = awaitPointerEventScope2;
                        this.label = 1;
                        c00031 = this;
                        if (TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, pointerEventPass, c00031, 1, (Object) null) != coroutine_suspended) {
                            awaitPointerEventScope = awaitPointerEventScope2;
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        c00031 = this;
                    } else {
                        if (i != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                        c00031 = this;
                    }
                    c00031.$onReleasedOrCancelled.invoke();
                    return Unit.INSTANCE;
                    c00031.$onPressed.invoke();
                    PointerEventPass pointerEventPass2 = PointerEventPass.Initial;
                    c00031.L$0 = null;
                    c00031.label = 2;
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new C00031(function0, function1, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
    }
}
