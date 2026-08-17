package androidx.compose.foundation;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.compose.foundation.AndroidExternalSurface_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidMatrixConversions_androidKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0002\u001aZ\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\b2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a\r\u0010\u0014\u001a\u00020\u0015H\u0003¢\u0006\u0002\u0010\u0016\u001aR\u0010\u0017\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\u000f¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"rememberAndroidExternalSurfaceState", "Landroidx/compose/foundation/AndroidExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidExternalSurfaceState;", "AndroidExternalSurface", "", "modifier", "Landroidx/compose/ui/Modifier;", "isOpaque", "", "surfaceSize", "Landroidx/compose/ui/unit/IntSize;", "zOrder", "Landroidx/compose/foundation/AndroidExternalSurfaceZOrder;", "isSecure", "onInit", "Lkotlin/Function1;", "Landroidx/compose/foundation/AndroidExternalSurfaceScope;", "Lkotlin/ExtensionFunctionType;", "AndroidExternalSurface-58FFMhA", "(Landroidx/compose/ui/Modifier;ZJIZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "rememberAndroidEmbeddedExternalSurfaceState", "Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/AndroidEmbeddedExternalSurfaceState;", "AndroidEmbeddedExternalSurface", "transform", "Landroidx/compose/ui/graphics/Matrix;", "AndroidEmbeddedExternalSurface-sv6N_fY", "(Landroidx/compose/ui/Modifier;ZJ[FLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AndroidExternalSurface_androidKt {
    /* JADX WARN: Code duplicated, block: B:101:0x0145  */
    /* JADX WARN: Code duplicated, block: B:102:0x0148  */
    /* JADX WARN: Code duplicated, block: B:105:0x0150  */
    /* JADX WARN: Code duplicated, block: B:108:0x0155  */
    /* JADX WARN: Code duplicated, block: B:109:0x015a  */
    /* JADX WARN: Code duplicated, block: B:112:0x0166  */
    /* JADX WARN: Code duplicated, block: B:116:0x0170  */
    /* JADX WARN: Code duplicated, block: B:119:0x0197  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:124:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:126:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x0069 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0070  */
    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    /* JADX WARN: Code duplicated, block: B:45:0x007a  */
    /* JADX WARN: Code duplicated, block: B:49:0x0081  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:57:0x009c  */
    /* JADX WARN: Code duplicated, block: B:58:0x009f  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:72:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:80:0x00de  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:87:0x0105  */
    /* JADX WARN: Code duplicated, block: B:90:0x011a  */
    /* JADX WARN: Code duplicated, block: B:93:0x012a  */
    /* JADX WARN: Code duplicated, block: B:95:0x0130  */
    /* JADX INFO: renamed from: AndroidEmbeddedExternalSurface-sv6N_fY, reason: not valid java name */
    public static final void m316AndroidEmbeddedExternalSurfacesv6N_fY(Modifier modifier, boolean z, long j, final float[] fArr, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        boolean z2;
        long j2;
        int i4;
        Matrix matrix;
        int i5;
        Function1<? super AndroidExternalSurfaceScope, Unit> function2;
        boolean z3;
        final float[] fArr2;
        final Modifier modifier3;
        final boolean z4;
        final long j3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        final boolean z5;
        final AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState;
        Object objRememberedValue;
        Composer.Companion companion;
        Object objRememberedValue2;
        boolean z6;
        Matrix matrix2;
        boolean zChangedInstance;
        Object objRememberedValue3;
        final long j4;
        int i6;
        int i7;
        Composer composerStartRestartGroup = composer.startRestartGroup(217541314);
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
                z2 = z;
                i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
            }
            if ((i & 384) == 0) {
                j2 = j;
                if ((i2 & 4) == 0 || !composerStartRestartGroup.changed(j2)) {
                    i7 = 128;
                } else {
                    i7 = 256;
                }
                i3 |= i7;
            } else {
                j2 = j;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                i3 |= 3072;
            } else if ((i & 3072) == 0) {
                if (fArr != null) {
                    matrix = Matrix.box-impl(fArr);
                } else {
                    matrix = null;
                }
                if (composerStartRestartGroup.changedInstance(matrix)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                function2 = function1;
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            } else {
                function2 = function1;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i8 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i9 != 0) {
                        z2 = true;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i4 != 0) {
                        z5 = z2;
                        fArr = null;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:432)");
                    }
                    androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: u50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$0$0((Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function3 = (Function1) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: v50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$1$0((TextureView) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function1 function4 = (Function1) objRememberedValue2;
                    boolean zChangedInstance2 = ((((i3 & 896) ^ 384) <= 256 && composerStartRestartGroup.changed(j2)) || (i3 & 384) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                    if ((57344 & i3) == 16384) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    boolean z7 = zChangedInstance2 | z6 | ((i3 & 112) == 32);
                    if (fArr != null) {
                        matrix2 = Matrix.box-impl(fArr);
                    } else {
                        matrix2 = null;
                    }
                    zChangedInstance = z7 | composerStartRestartGroup.changedInstance(matrix2);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (!zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
                        j4 = j2;
                        final Function1<? super AndroidExternalSurfaceScope, Unit> function5 = function2;
                        objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.b
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(j4, androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState, function5, z5, fArr, (TextureView) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        j4 = j2;
                    }
                    Modifier modifier5 = modifier4;
                    AndroidView_androidKt.AndroidView(function3, modifier5, function4, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier5;
                    j3 = j4;
                    z4 = z5;
                    fArr2 = fArr;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                    }
                    modifier4 = modifier2;
                }
                z5 = z2;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:432)");
                }
                androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: u50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$0$0((Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function6 = (Function1) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: v50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$1$0((TextureView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function1 function7 = (Function1) objRememberedValue2;
                boolean zChangedInstance3 = ((((i3 & 896) ^ 384) <= 256 && composerStartRestartGroup.changed(j2)) || (i3 & 384) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
                if ((57344 & i3) == 16384) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                boolean z8 = zChangedInstance3 | z6 | ((i3 & 112) == 32);
                if (fArr != null) {
                    matrix2 = Matrix.box-impl(fArr);
                } else {
                    matrix2 = null;
                }
                zChangedInstance = z8 | composerStartRestartGroup.changedInstance(matrix2);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    j4 = j2;
                    final Function1 function8 = function2;
                    objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.b
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(j4, androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState, function8, z5, fArr, (TextureView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    j4 = j2;
                    final Function1 function9 = function2;
                    objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.b
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(j4, androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState, function9, z5, fArr, (TextureView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                Modifier modifier6 = modifier4;
                AndroidView_androidKt.AndroidView(function6, modifier6, function7, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
                j3 = j4;
                z4 = z5;
                fArr2 = fArr;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                fArr2 = fArr;
                modifier3 = modifier2;
                z4 = z2;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: w50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidExternalSurface_androidKt.e(modifier3, z4, j3, fArr2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        z2 = z;
        if ((i & 384) == 0) {
            j2 = j;
            if ((i2 & 4) == 0) {
                i7 = 128;
            } else {
                i7 = 128;
            }
            i3 |= i7;
        } else {
            j2 = j;
        }
        i4 = i2 & 8;
        if (i4 != 0) {
            i3 |= 3072;
        } else if ((i & 3072) == 0) {
            if (fArr != null) {
                matrix = Matrix.box-impl(fArr);
            } else {
                matrix = null;
            }
            if (composerStartRestartGroup.changedInstance(matrix)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i3 |= i5;
        }
        if ((i & 24576) == 0) {
            function2 = function1;
            if (composerStartRestartGroup.changedInstance(function2)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i3 |= i6;
        } else {
            function2 = function1;
        }
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i8 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    j2 = IntSize.Companion.getZero-YbymL2g();
                }
                if (i4 != 0) {
                    z5 = z2;
                    fArr = null;
                } else {
                    z5 = z2;
                }
            } else {
                if (i8 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i9 != 0) {
                    z2 = true;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    j2 = IntSize.Companion.getZero-YbymL2g();
                }
                if (i4 != 0) {
                    z5 = z2;
                    fArr = null;
                } else {
                    z5 = z2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(217541314, i3, -1, "androidx.compose.foundation.AndroidEmbeddedExternalSurface (AndroidExternalSurface.android.kt:432)");
            }
            androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState = rememberAndroidEmbeddedExternalSurfaceState(composerStartRestartGroup, 0);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: u50
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$0$0((Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function10 = (Function1) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: v50
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$1$0((TextureView) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function1 function11 = (Function1) objRememberedValue2;
            boolean zChangedInstance4 = ((((i3 & 896) ^ 384) <= 256 && composerStartRestartGroup.changed(j2)) || (i3 & 384) == 256) | composerStartRestartGroup.changedInstance(androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState);
            if ((57344 & i3) == 16384) {
                z6 = true;
            } else {
                z6 = false;
            }
            boolean z9 = zChangedInstance4 | z6 | ((i3 & 112) == 32);
            if (fArr != null) {
                matrix2 = Matrix.box-impl(fArr);
            } else {
                matrix2 = null;
            }
            zChangedInstance = z9 | composerStartRestartGroup.changedInstance(matrix2);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                j4 = j2;
                final Function1 function12 = function2;
                objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.b
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(j4, androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState, function12, z5, fArr, (TextureView) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                j4 = j2;
                final Function1 function13 = function2;
                objRememberedValue3 = new Function1() { // from class: androidx.compose.foundation.b
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(j4, androidEmbeddedExternalSurfaceStateRememberAndroidEmbeddedExternalSurfaceState, function13, z5, fArr, (TextureView) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifier7 = modifier4;
            AndroidView_androidKt.AndroidView(function10, modifier7, function11, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i3 << 3) & 112) | 390, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier7;
            j3 = j4;
            z4 = z5;
            fArr2 = fArr;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            fArr2 = fArr;
            modifier3 = modifier2;
            z4 = z2;
            j3 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: w50
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidExternalSurface_androidKt.e(modifier3, z4, j3, fArr2, function1, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextureView AndroidEmbeddedExternalSurface_sv6N_fY$lambda$0$0(Context context) {
        return new TextureView(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AndroidEmbeddedExternalSurface_sv6N_fY$lambda$1$0(TextureView textureView) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AndroidEmbeddedExternalSurface_sv6N_fY$lambda$2$0(long j, AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState, Function1 function1, boolean z, float[] fArr, TextureView textureView) {
        android.graphics.Matrix matrix;
        SurfaceTexture surfaceTexture;
        if (!IntSize.equals-impl0(j, IntSize.Companion.getZero-YbymL2g()) && (surfaceTexture = textureView.getSurfaceTexture()) != null) {
            surfaceTexture.setDefaultBufferSize((int) (j >> 32), (int) (4294967295L & j));
        }
        androidEmbeddedExternalSurfaceState.m305setSurfaceSizeozmzZPI(j);
        if (textureView.getSurfaceTextureListener() != androidEmbeddedExternalSurfaceState) {
            function1.invoke(androidEmbeddedExternalSurfaceState);
            textureView.setSurfaceTextureListener(androidEmbeddedExternalSurfaceState);
        }
        textureView.setOpaque(z);
        if (fArr != null) {
            matrix = androidEmbeddedExternalSurfaceState.getMatrix();
            AndroidMatrixConversions_androidKt.setFrom-EL8BTi8(matrix, fArr);
        } else {
            matrix = null;
        }
        textureView.setTransform(matrix);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x012c  */
    /* JADX WARN: Code duplicated, block: B:102:0x0134  */
    /* JADX WARN: Code duplicated, block: B:105:0x014b  */
    /* JADX WARN: Code duplicated, block: B:108:0x015d  */
    /* JADX WARN: Code duplicated, block: B:110:0x0163  */
    /* JADX WARN: Code duplicated, block: B:116:0x0171  */
    /* JADX WARN: Code duplicated, block: B:117:0x0174  */
    /* JADX WARN: Code duplicated, block: B:120:0x017c  */
    /* JADX WARN: Code duplicated, block: B:121:0x017f  */
    /* JADX WARN: Code duplicated, block: B:125:0x018a  */
    /* JADX WARN: Code duplicated, block: B:128:0x0194  */
    /* JADX WARN: Code duplicated, block: B:132:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:135:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:137:0x01dd  */
    /* JADX WARN: Code duplicated, block: B:140:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:31:0x0059  */
    /* JADX WARN: Code duplicated, block: B:33:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:38:0x0068  */
    /* JADX WARN: Code duplicated, block: B:40:0x006c  */
    /* JADX WARN: Code duplicated, block: B:42:0x0074  */
    /* JADX WARN: Code duplicated, block: B:43:0x0077  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:49:0x0084  */
    /* JADX WARN: Code duplicated, block: B:51:0x0088  */
    /* JADX WARN: Code duplicated, block: B:53:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x0093  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:78:0x00dc A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x00de  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:93:0x0109  */
    /* JADX WARN: Code duplicated, block: B:96:0x011d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0120  */
    /* JADX INFO: renamed from: AndroidExternalSurface-58FFMhA, reason: not valid java name */
    public static final void m317AndroidExternalSurface58FFMhA(Modifier modifier, boolean z, long j, int i, boolean z2, final Function1<? super AndroidExternalSurfaceScope, Unit> function1, Composer composer, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        boolean z3;
        long j2;
        int i5;
        int iM313getBehindB_4ceCc;
        int i6;
        int i7;
        boolean z4;
        int i8;
        boolean z5;
        final Modifier modifier3;
        final boolean z6;
        final long j3;
        final boolean z7;
        final int i9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Modifier modifier5;
        final AndroidExternalSurfaceState androidExternalSurfaceStateRememberAndroidExternalSurfaceState;
        boolean z8;
        boolean zChangedInstance;
        Object objRememberedValue;
        Object objRememberedValue2;
        Composer.Companion companion;
        boolean z9;
        boolean z10;
        boolean z11;
        Object objRememberedValue3;
        long j4;
        boolean z12;
        int i10;
        boolean z13;
        int i11;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(640888974);
        int i13 = i3 & 1;
        if (i13 != 0) {
            i4 = i2 | 6;
            modifier2 = modifier;
        } else if ((i2 & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i2;
        } else {
            modifier2 = modifier;
            i4 = i2;
        }
        int i14 = i3 & 2;
        if (i14 == 0) {
            if ((i2 & 48) == 0) {
                z3 = z;
                i4 |= composerStartRestartGroup.changed(z3) ? 32 : 16;
            }
            if ((i2 & 384) == 0) {
                j2 = j;
                if ((i3 & 4) == 0 || !composerStartRestartGroup.changed(j2)) {
                    i12 = 128;
                } else {
                    i12 = 256;
                }
                i4 |= i12;
            } else {
                j2 = j;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i2 & 3072) == 0) {
                    iM313getBehindB_4ceCc = i;
                    if (composerStartRestartGroup.changed(iM313getBehindB_4ceCc)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 16;
                if (i7 != 0) {
                    if ((i2 & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 16384;
                        } else {
                            i8 = 8192;
                        }
                        i4 |= i8;
                    }
                    if ((i2 & 196608) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i11 = 131072;
                        } else {
                            i11 = 65536;
                        }
                        i4 |= i11;
                    }
                    if ((i4 & 74899) != 74898) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i13 != 0) {
                                modifier4 = Modifier.Companion;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i14 != 0) {
                                z3 = true;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                j2 = IntSize.Companion.getZero-YbymL2g();
                            }
                            if (i5 != 0) {
                                iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                            }
                            if (i7 != 0) {
                                z4 = false;
                            }
                            modifier5 = modifier4;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                            }
                            modifier5 = modifier2;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                        }
                        androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                        if ((458752 & i4) == 131072) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                                public final Object invoke(Object obj) {
                                    return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Function1 function2 = (Function1) objRememberedValue;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: r50
                                public final Object invoke(Object obj) {
                                    return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Function1 function3 = (Function1) objRememberedValue2;
                        boolean z14 = (((i4 & 896) ^ 384) <= 256 && composerStartRestartGroup.changed(j2)) || (i4 & 384) == 256;
                        if ((i4 & 112) == 32) {
                            z9 = true;
                        } else {
                            z9 = false;
                        }
                        boolean z15 = z9 | z14;
                        if ((i4 & 7168) == 2048) {
                            z10 = true;
                        } else {
                            z10 = false;
                        }
                        z11 = z15 | z10 | ((57344 & i4) == 16384);
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (!z11 || objRememberedValue3 == companion.getEmpty()) {
                            final boolean z16 = z3;
                            final long j5 = j2;
                            final boolean z17 = z4;
                            final int i15 = iM313getBehindB_4ceCc;
                            objRememberedValue3 = new Function1() { // from class: s50
                                public final Object invoke(Object obj) {
                                    return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j5, z16, i15, z17, (SurfaceView) obj);
                                }
                            };
                            j4 = j5;
                            z12 = z16;
                            i10 = i15;
                            z13 = z17;
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            z12 = z3;
                            j4 = j2;
                            z13 = z4;
                            i10 = iM313getBehindB_4ceCc;
                        }
                        AndroidView_androidKt.AndroidView(function2, modifier5, function3, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j3 = j4;
                        modifier3 = modifier5;
                        z7 = z13;
                        i9 = i10;
                        z6 = z12;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z6 = z3;
                        j3 = j2;
                        z7 = z4;
                        i9 = iM313getBehindB_4ceCc;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                z4 = z2;
                if ((i2 & 196608) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
                if ((i4 & 74899) != 74898) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    } else {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    if ((458752 & i4) == 131072) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function4 = (Function1) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: r50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function1 function5 = (Function1) objRememberedValue2;
                    if (((i4 & 896) ^ 384) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z18 = z9 | z14;
                    if ((i4 & 7168) == 2048) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    z11 = z18 | z10 | ((57344 & i4) == 16384);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z11) {
                        final boolean z19 = z3;
                        final long j6 = j2;
                        final boolean z110 = z4;
                        final int i16 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j6, z19, i16, z110, (SurfaceView) obj);
                            }
                        };
                        j4 = j6;
                        z12 = z19;
                        i10 = i16;
                        z13 = z110;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        final boolean z111 = z3;
                        final long j7 = j2;
                        final boolean z112 = z4;
                        final int i17 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j7, z111, i17, z112, (SurfaceView) obj);
                            }
                        };
                        j4 = j7;
                        z12 = z111;
                        i10 = i17;
                        z13 = z112;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    AndroidView_androidKt.AndroidView(function4, modifier5, function5, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                    modifier3 = modifier5;
                    z7 = z13;
                    i9 = i10;
                    z6 = z12;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z3;
                    j3 = j2;
                    z7 = z4;
                    i9 = iM313getBehindB_4ceCc;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            iM313getBehindB_4ceCc = i;
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i2 & 196608) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
                if ((i4 & 74899) != 74898) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    } else {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    if ((458752 & i4) == 131072) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function6 = (Function1) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: r50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function1 function7 = (Function1) objRememberedValue2;
                    if (((i4 & 896) ^ 384) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z113 = z9 | z14;
                    if ((i4 & 7168) == 2048) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    z11 = z113 | z10 | ((57344 & i4) == 16384);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z11) {
                        final boolean z114 = z3;
                        final long j8 = j2;
                        final boolean z115 = z4;
                        final int i18 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j8, z114, i18, z115, (SurfaceView) obj);
                            }
                        };
                        j4 = j8;
                        z12 = z114;
                        i10 = i18;
                        z13 = z115;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        final boolean z116 = z3;
                        final long j9 = j2;
                        final boolean z117 = z4;
                        final int i19 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j9, z116, i19, z117, (SurfaceView) obj);
                            }
                        };
                        j4 = j9;
                        z12 = z116;
                        i10 = i19;
                        z13 = z117;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    AndroidView_androidKt.AndroidView(function6, modifier5, function7, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                    modifier3 = modifier5;
                    z7 = z13;
                    i9 = i10;
                    z6 = z12;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z3;
                    j3 = j2;
                    z7 = z4;
                    i9 = iM313getBehindB_4ceCc;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i2 & 196608) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i4 |= i11;
            }
            if ((i4 & 74899) != 74898) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                } else {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                if ((458752 & i4) == 131072) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function8 = (Function1) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: r50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function1 function9 = (Function1) objRememberedValue2;
                if (((i4 & 896) ^ 384) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z118 = z9 | z14;
                if ((i4 & 7168) == 2048) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                z11 = z118 | z10 | ((57344 & i4) == 16384);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z11) {
                    final boolean z119 = z3;
                    final long j10 = j2;
                    final boolean z1110 = z4;
                    final int i110 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j10, z119, i110, z1110, (SurfaceView) obj);
                        }
                    };
                    j4 = j10;
                    z12 = z119;
                    i10 = i110;
                    z13 = z1110;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final boolean z1111 = z3;
                    final long j11 = j2;
                    final boolean z1112 = z4;
                    final int i111 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j11, z1111, i111, z1112, (SurfaceView) obj);
                        }
                    };
                    j4 = j11;
                    z12 = z1111;
                    i10 = i111;
                    z13 = z1112;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                AndroidView_androidKt.AndroidView(function8, modifier5, function9, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
                modifier3 = modifier5;
                z7 = z13;
                i9 = i10;
                z6 = z12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z3;
                j3 = j2;
                z7 = z4;
                i9 = iM313getBehindB_4ceCc;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        z3 = z;
        if ((i2 & 384) == 0) {
            j2 = j;
            if ((i3 & 4) == 0) {
                i12 = 128;
            } else {
                i12 = 128;
            }
            i4 |= i12;
        } else {
            j2 = j;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i2 & 3072) == 0) {
                iM313getBehindB_4ceCc = i;
                if (composerStartRestartGroup.changed(iM313getBehindB_4ceCc)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            i7 = i3 & 16;
            if (i7 != 0) {
                if ((i2 & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 16384;
                    } else {
                        i8 = 8192;
                    }
                    i4 |= i8;
                }
                if ((i2 & 196608) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i11 = 131072;
                    } else {
                        i11 = 65536;
                    }
                    i4 |= i11;
                }
                if ((i4 & 74899) != 74898) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    } else {
                        if (i13 != 0) {
                            modifier4 = Modifier.Companion;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i14 != 0) {
                            z3 = true;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            j2 = IntSize.Companion.getZero-YbymL2g();
                        }
                        if (i5 != 0) {
                            iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                        }
                        if (i7 != 0) {
                            z4 = false;
                        }
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                    }
                    androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                    if ((458752 & i4) == 131072) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Function1 function10 = (Function1) objRememberedValue;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: r50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Function1 function11 = (Function1) objRememberedValue2;
                    if (((i4 & 896) ^ 384) <= 256) {
                    }
                    if ((i4 & 112) == 32) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                    boolean z1113 = z9 | z14;
                    if ((i4 & 7168) == 2048) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    z11 = z1113 | z10 | ((57344 & i4) == 16384);
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z11) {
                        final boolean z1114 = z3;
                        final long j12 = j2;
                        final boolean z1115 = z4;
                        final int i112 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j12, z1114, i112, z1115, (SurfaceView) obj);
                            }
                        };
                        j4 = j12;
                        z12 = z1114;
                        i10 = i112;
                        z13 = z1115;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        final boolean z1116 = z3;
                        final long j13 = j2;
                        final boolean z1117 = z4;
                        final int i113 = iM313getBehindB_4ceCc;
                        objRememberedValue3 = new Function1() { // from class: s50
                            public final Object invoke(Object obj) {
                                return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j13, z1116, i113, z1117, (SurfaceView) obj);
                            }
                        };
                        j4 = j13;
                        z12 = z1116;
                        i10 = i113;
                        z13 = z1117;
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    AndroidView_androidKt.AndroidView(function10, modifier5, function11, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j3 = j4;
                    modifier3 = modifier5;
                    z7 = z13;
                    i9 = i10;
                    z6 = z12;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z3;
                    j3 = j2;
                    z7 = z4;
                    i9 = iM313getBehindB_4ceCc;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            z4 = z2;
            if ((i2 & 196608) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i4 |= i11;
            }
            if ((i4 & 74899) != 74898) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                } else {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                if ((458752 & i4) == 131072) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function12 = (Function1) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: r50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function1 function13 = (Function1) objRememberedValue2;
                if (((i4 & 896) ^ 384) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z1118 = z9 | z14;
                if ((i4 & 7168) == 2048) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                z11 = z1118 | z10 | ((57344 & i4) == 16384);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z11) {
                    final boolean z1119 = z3;
                    final long j14 = j2;
                    final boolean z11110 = z4;
                    final int i114 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j14, z1119, i114, z11110, (SurfaceView) obj);
                        }
                    };
                    j4 = j14;
                    z12 = z1119;
                    i10 = i114;
                    z13 = z11110;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final boolean z11111 = z3;
                    final long j15 = j2;
                    final boolean z11112 = z4;
                    final int i115 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j15, z11111, i115, z11112, (SurfaceView) obj);
                        }
                    };
                    j4 = j15;
                    z12 = z11111;
                    i10 = i115;
                    z13 = z11112;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                AndroidView_androidKt.AndroidView(function12, modifier5, function13, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
                modifier3 = modifier5;
                z7 = z13;
                i9 = i10;
                z6 = z12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z3;
                j3 = j2;
                z7 = z4;
                i9 = iM313getBehindB_4ceCc;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        iM313getBehindB_4ceCc = i;
        i7 = i3 & 16;
        if (i7 != 0) {
            if ((i2 & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i8 = 16384;
                } else {
                    i8 = 8192;
                }
                i4 |= i8;
            }
            if ((i2 & 196608) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i11 = 131072;
                } else {
                    i11 = 65536;
                }
                i4 |= i11;
            }
            if ((i4 & 74899) != 74898) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                } else {
                    if (i13 != 0) {
                        modifier4 = Modifier.Companion;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i14 != 0) {
                        z3 = true;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        j2 = IntSize.Companion.getZero-YbymL2g();
                    }
                    if (i5 != 0) {
                        iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                    }
                    if (i7 != 0) {
                        z4 = false;
                    }
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
                }
                androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
                if ((458752 & i4) == 131072) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Function1 function14 = (Function1) objRememberedValue;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: r50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Function1 function15 = (Function1) objRememberedValue2;
                if (((i4 & 896) ^ 384) <= 256) {
                }
                if ((i4 & 112) == 32) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                boolean z11113 = z9 | z14;
                if ((i4 & 7168) == 2048) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                z11 = z11113 | z10 | ((57344 & i4) == 16384);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z11) {
                    final boolean z11114 = z3;
                    final long j16 = j2;
                    final boolean z11115 = z4;
                    final int i116 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j16, z11114, i116, z11115, (SurfaceView) obj);
                        }
                    };
                    j4 = j16;
                    z12 = z11114;
                    i10 = i116;
                    z13 = z11115;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    final boolean z11116 = z3;
                    final long j17 = j2;
                    final boolean z11117 = z4;
                    final int i117 = iM313getBehindB_4ceCc;
                    objRememberedValue3 = new Function1() { // from class: s50
                        public final Object invoke(Object obj) {
                            return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j17, z11116, i117, z11117, (SurfaceView) obj);
                        }
                    };
                    j4 = j17;
                    z12 = z11116;
                    i10 = i117;
                    z13 = z11117;
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                AndroidView_androidKt.AndroidView(function14, modifier5, function15, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j3 = j4;
                modifier3 = modifier5;
                z7 = z13;
                i9 = i10;
                z6 = z12;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z3;
                j3 = j2;
                z7 = z4;
                i9 = iM313getBehindB_4ceCc;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        z4 = z2;
        if ((i2 & 196608) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i11 = 131072;
            } else {
                i11 = 65536;
            }
            i4 |= i11;
        }
        if ((i4 & 74899) != 74898) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i13 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    j2 = IntSize.Companion.getZero-YbymL2g();
                }
                if (i5 != 0) {
                    iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
                modifier5 = modifier4;
            } else {
                if (i13 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (i14 != 0) {
                    z3 = true;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    j2 = IntSize.Companion.getZero-YbymL2g();
                }
                if (i5 != 0) {
                    iM313getBehindB_4ceCc = AndroidExternalSurfaceZOrder.INSTANCE.m313getBehindB_4ceCc();
                }
                if (i7 != 0) {
                    z4 = false;
                }
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(640888974, i4, -1, "androidx.compose.foundation.AndroidExternalSurface (AndroidExternalSurface.android.kt:274)");
            }
            androidExternalSurfaceStateRememberAndroidExternalSurfaceState = rememberAndroidExternalSurfaceState(composerStartRestartGroup, 0);
            if ((458752 & i4) == 131072) {
                z8 = true;
            } else {
                z8 = false;
            }
            zChangedInstance = z8 | composerStartRestartGroup.changedInstance(androidExternalSurfaceStateRememberAndroidExternalSurfaceState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function1() { // from class: androidx.compose.foundation.a
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$0$0(function1, androidExternalSurfaceStateRememberAndroidExternalSurfaceState, (Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function16 = (Function1) objRememberedValue;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: r50
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$1$0((SurfaceView) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function1 function17 = (Function1) objRememberedValue2;
            if (((i4 & 896) ^ 384) <= 256) {
            }
            if ((i4 & 112) == 32) {
                z9 = true;
            } else {
                z9 = false;
            }
            boolean z11118 = z9 | z14;
            if ((i4 & 7168) == 2048) {
                z10 = true;
            } else {
                z10 = false;
            }
            z11 = z11118 | z10 | ((57344 & i4) == 16384);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z11) {
                final boolean z11119 = z3;
                final long j18 = j2;
                final boolean z111110 = z4;
                final int i118 = iM313getBehindB_4ceCc;
                objRememberedValue3 = new Function1() { // from class: s50
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j18, z11119, i118, z111110, (SurfaceView) obj);
                    }
                };
                j4 = j18;
                z12 = z11119;
                i10 = i118;
                z13 = z111110;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                final boolean z111111 = z3;
                final long j19 = j2;
                final boolean z111112 = z4;
                final int i119 = iM313getBehindB_4ceCc;
                objRememberedValue3 = new Function1() { // from class: s50
                    public final Object invoke(Object obj) {
                        return AndroidExternalSurface_androidKt.AndroidExternalSurface_58FFMhA$lambda$2$0(j19, z111111, i119, z111112, (SurfaceView) obj);
                    }
                };
                j4 = j19;
                z12 = z111111;
                i10 = i119;
                z13 = z111112;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            AndroidView_androidKt.AndroidView(function16, modifier5, function17, (Function1) null, (Function1) objRememberedValue3, composerStartRestartGroup, ((i4 << 3) & 112) | 384, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = j4;
            modifier3 = modifier5;
            z7 = z13;
            i9 = i10;
            z6 = z12;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z6 = z3;
            j3 = j2;
            z7 = z4;
            i9 = iM313getBehindB_4ceCc;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: t50
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidExternalSurface_androidKt.d(modifier3, z6, j3, i9, z7, function1, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SurfaceView AndroidExternalSurface_58FFMhA$lambda$0$0(Function1 function1, AndroidExternalSurfaceState androidExternalSurfaceState, Context context) {
        SurfaceView surfaceView = new SurfaceView(context);
        function1.invoke(androidExternalSurfaceState);
        surfaceView.getHolder().addCallback(androidExternalSurfaceState);
        return surfaceView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AndroidExternalSurface_58FFMhA$lambda$1$0(SurfaceView surfaceView) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AndroidExternalSurface_58FFMhA$lambda$2$0(long j, boolean z, int i, boolean z2, SurfaceView surfaceView) {
        if (IntSize.equals-impl0(j, IntSize.Companion.getZero-YbymL2g())) {
            surfaceView.getHolder().setSizeFromLayout();
        } else {
            surfaceView.getHolder().setFixedSize((int) (j >> 32), (int) (j & 4294967295L));
        }
        surfaceView.getHolder().setFormat(z ? -1 : -3);
        AndroidExternalSurfaceZOrder.Companion companion = AndroidExternalSurfaceZOrder.INSTANCE;
        if (AndroidExternalSurfaceZOrder.m309equalsimpl0(i, companion.m313getBehindB_4ceCc())) {
            surfaceView.setZOrderOnTop(false);
        } else if (AndroidExternalSurfaceZOrder.m309equalsimpl0(i, companion.m314getMediaOverlayB_4ceCc())) {
            surfaceView.setZOrderMediaOverlay(true);
        } else if (AndroidExternalSurfaceZOrder.m309equalsimpl0(i, companion.m315getOnTopB_4ceCc())) {
            surfaceView.setZOrderOnTop(true);
        }
        surfaceView.setSecure(z2);
        return Unit.INSTANCE;
    }

    public static Unit d(Modifier modifier, boolean z, long j, int i, boolean z2, Function1 function1, int i2, int i3, Composer composer, int i4) {
        m317AndroidExternalSurface58FFMhA(modifier, z, j, i, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit e(Modifier modifier, boolean z, long j, float[] fArr, Function1 function1, int i, int i2, Composer composer, int i3) {
        m316AndroidEmbeddedExternalSurfacesv6N_fY(modifier, z, j, fArr, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final AndroidEmbeddedExternalSurfaceState rememberAndroidEmbeddedExternalSurfaceState(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1057437053, i, -1, "androidx.compose.foundation.rememberAndroidEmbeddedExternalSurfaceState (AndroidExternalSurface.android.kt:370)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer);
            composer.updateRememberedValue(objRememberedValue);
        }
        CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new AndroidEmbeddedExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidEmbeddedExternalSurfaceState androidEmbeddedExternalSurfaceState = (AndroidEmbeddedExternalSurfaceState) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return androidEmbeddedExternalSurfaceState;
    }

    private static final AndroidExternalSurfaceState rememberAndroidExternalSurfaceState(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-873615933, i, -1, "androidx.compose.foundation.rememberAndroidExternalSurfaceState (AndroidExternalSurface.android.kt:187)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer);
            composer.updateRememberedValue(objRememberedValue);
        }
        CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new AndroidExternalSurfaceState(coroutineScope);
            composer.updateRememberedValue(objRememberedValue2);
        }
        AndroidExternalSurfaceState androidExternalSurfaceState = (AndroidExternalSurfaceState) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return androidExternalSurfaceState;
    }
}
