package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.WindowInsetsHolder;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0001\u001a>\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u00122\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u000f¢\u0006\u0002\b\u0012H\u0003¨\u0006\u0016"}, d2 = {"safeDrawingPadding", "Landroidx/compose/ui/Modifier;", "safeGesturesPadding", "safeContentPadding", "systemBarsPadding", "displayCutoutPadding", "statusBarsPadding", "imePadding", "navigationBarsPadding", "captionBarPadding", "waterfallPadding", "systemGesturesPadding", "mandatorySystemGesturesPadding", "windowInsetsPadding", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "insetsCalculation", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "Landroidx/compose/foundation/layout/WindowInsets;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WindowInsetsPadding_androidKt {
    public static WindowInsets a(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getStatusBars();
    }

    public static WindowInsets b(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getSafeContent();
    }

    public static WindowInsets c(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getDisplayCutout();
    }

    public static final Modifier captionBarPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$captionBarPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("captionBarPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: eof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.f((WindowInsetsHolder) obj);
            }
        });
    }

    public static WindowInsets d(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getNavigationBars();
    }

    public static final Modifier displayCutoutPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$displayCutoutPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("displayCutoutPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: xnf
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.c((WindowInsetsHolder) obj);
            }
        });
    }

    public static WindowInsets e(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getMandatorySystemGestures();
    }

    public static WindowInsets f(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getCaptionBar();
    }

    public static WindowInsets g(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getSystemGestures();
    }

    public static WindowInsets h(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getWaterfall();
    }

    public static WindowInsets i(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getSystemBars();
    }

    public static final Modifier imePadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$imePadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("imePadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: aof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.k((WindowInsetsHolder) obj);
            }
        });
    }

    public static WindowInsets j(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getSafeGestures();
    }

    public static WindowInsets k(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getIme();
    }

    public static WindowInsets l(WindowInsetsHolder windowInsetsHolder) {
        return windowInsetsHolder.getSafeDrawing();
    }

    public static final Modifier mandatorySystemGesturesPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$mandatorySystemGesturesPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("mandatorySystemGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: znf
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.e((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier navigationBarsPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$navigationBarsPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("navigationBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: cof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.d((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier safeContentPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeContentPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeContentPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: ynf
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.b((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier safeDrawingPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeDrawingPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeDrawingPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: dof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.l((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier safeGesturesPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$safeGesturesPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("safeGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: vnf
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.j((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier statusBarsPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$statusBarsPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("statusBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: gof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.a((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier systemBarsPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$systemBarsPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("systemBarsPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: bof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.i((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier systemGesturesPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$systemGesturesPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("systemGesturesPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: wnf
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.g((WindowInsetsHolder) obj);
            }
        });
    }

    public static final Modifier waterfallPadding(Modifier modifier) {
        return windowInsetsPadding(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPadding_androidKt$waterfallPadding$$inlined$debugInspectorInfo$1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((InspectorInfo) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("waterfallPadding");
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function1() { // from class: fof
            public final Object invoke(Object obj) {
                return WindowInsetsPadding_androidKt.h((WindowInsetsHolder) obj);
            }
        });
    }

    private static final Modifier windowInsetsPadding(Modifier modifier, Function1<? super InspectorInfo, Unit> function1, Function1<? super WindowInsetsHolder, ? extends WindowInsets> function2) {
        return modifier.then(new SystemInsetsPaddingModifierElement(function1, function2));
    }
}
