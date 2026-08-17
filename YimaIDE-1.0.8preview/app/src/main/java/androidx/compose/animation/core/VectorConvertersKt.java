package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001aJ\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u0006\u001a!\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\tH\u0080\b\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0014\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0001*\u00020\u00198F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u001a\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e0\u0001*\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u001d\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020 8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010!\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020#8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010$\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020&8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010'\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020)8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010*\"!\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001f0\u0001*\u00020,8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010-\"\u001a\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00100\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00103\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"TwoWayConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "convertToVector", "Lkotlin/Function1;", "convertFromVector", "lerp", "", "start", "stop", "fraction", "VectorConverter", "Landroidx/compose/animation/core/AnimationVector1D;", "Lkotlin/Float$Companion;", "getVectorConverter", "(Lkotlin/jvm/internal/FloatCompanionObject;)Landroidx/compose/animation/core/TwoWayConverter;", "", "Lkotlin/Int$Companion;", "(Lkotlin/jvm/internal/IntCompanionObject;)Landroidx/compose/animation/core/TwoWayConverter;", "FloatToVector", "IntToVector", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/animation/core/AnimationVector4D;", "Landroidx/compose/ui/geometry/Rect$Companion;", "(Landroidx/compose/ui/geometry/Rect$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/Dp;", "Landroidx/compose/ui/unit/Dp$Companion;", "(Landroidx/compose/ui/unit/Dp$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/ui/unit/DpOffset$Companion;", "(Landroidx/compose/ui/unit/DpOffset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Size;", "Landroidx/compose/ui/geometry/Size$Companion;", "(Landroidx/compose/ui/geometry/Size$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/ui/geometry/Offset$Companion;", "(Landroidx/compose/ui/geometry/Offset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/ui/unit/IntOffset$Companion;", "(Landroidx/compose/ui/unit/IntOffset$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/IntSize$Companion;", "(Landroidx/compose/ui/unit/IntSize$Companion;)Landroidx/compose/animation/core/TwoWayConverter;", "DpToVector", "DpOffsetToVector", "SizeToVector", "OffsetToVector", "IntOffsetToVector", "IntSizeToVector", "RectToVector", "animation-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class VectorConvertersKt {
    private static final TwoWayConverter<Float, AnimationVector1D> FloatToVector = TwoWayConverter(new Function1() { // from class: saf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.e(((Float) obj).floatValue());
        }
    }, new Function1() { // from class: jbf
        public final Object invoke(Object obj) {
            return Float.valueOf(VectorConvertersKt.j((AnimationVector1D) obj));
        }
    });
    private static final TwoWayConverter<Integer, AnimationVector1D> IntToVector = TwoWayConverter(new Function1() { // from class: taf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.l(((Integer) obj).intValue());
        }
    }, new Function1() { // from class: uaf
        public final Object invoke(Object obj) {
            return Integer.valueOf(VectorConvertersKt.f((AnimationVector1D) obj));
        }
    });
    private static final TwoWayConverter<Dp, AnimationVector1D> DpToVector = TwoWayConverter(new Function1() { // from class: vaf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.b((Dp) obj);
        }
    }, new Function1() { // from class: waf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.d((AnimationVector1D) obj);
        }
    });
    private static final TwoWayConverter<DpOffset, AnimationVector2D> DpOffsetToVector = TwoWayConverter(new Function1() { // from class: xaf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.g((DpOffset) obj);
        }
    }, new Function1() { // from class: yaf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.i((AnimationVector2D) obj);
        }
    });
    private static final TwoWayConverter<Size, AnimationVector2D> SizeToVector = TwoWayConverter(new Function1() { // from class: zaf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.c((Size) obj);
        }
    }, new Function1() { // from class: abf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.o((AnimationVector2D) obj);
        }
    });
    private static final TwoWayConverter<Offset, AnimationVector2D> OffsetToVector = TwoWayConverter(new Function1() { // from class: bbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.p((Offset) obj);
        }
    }, new Function1() { // from class: cbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.n((AnimationVector2D) obj);
        }
    });
    private static final TwoWayConverter<IntOffset, AnimationVector2D> IntOffsetToVector = TwoWayConverter(new Function1() { // from class: dbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.k((IntOffset) obj);
        }
    }, new Function1() { // from class: ebf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.m((AnimationVector2D) obj);
        }
    });
    private static final TwoWayConverter<IntSize, AnimationVector2D> IntSizeToVector = TwoWayConverter(new Function1() { // from class: fbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.a((IntSize) obj);
        }
    }, new Function1() { // from class: gbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.h((AnimationVector2D) obj);
        }
    });
    private static final TwoWayConverter<Rect, AnimationVector4D> RectToVector = TwoWayConverter(new Function1() { // from class: hbf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.r((Rect) obj);
        }
    }, new Function1() { // from class: ibf
        public final Object invoke(Object obj) {
            return VectorConvertersKt.q((AnimationVector4D) obj);
        }
    });

    public static final <T, V extends AnimationVector> TwoWayConverter<T, V> TwoWayConverter(Function1<? super T, ? extends V> function1, Function1<? super V, ? extends T> function2) {
        return new TwoWayConverterImpl(function1, function2);
    }

    public static AnimationVector2D a(IntSize intSize) {
        return new AnimationVector2D((int) (intSize.unbox-impl() >> 32), (int) (intSize.unbox-impl() & 4294967295L));
    }

    public static AnimationVector1D b(Dp dp) {
        return new AnimationVector1D(dp.unbox-impl());
    }

    public static AnimationVector2D c(Size size) {
        return new AnimationVector2D(Float.intBitsToFloat((int) (size.unbox-impl() >> 32)), Float.intBitsToFloat((int) (size.unbox-impl() & 4294967295L)));
    }

    public static Dp d(AnimationVector1D animationVector1D) {
        return Dp.box-impl(Dp.constructor-impl(animationVector1D.getValue()));
    }

    public static AnimationVector1D e(float f) {
        return new AnimationVector1D(f);
    }

    public static int f(AnimationVector1D animationVector1D) {
        return (int) animationVector1D.getValue();
    }

    public static AnimationVector2D g(DpOffset dpOffset) {
        return new AnimationVector2D(DpOffset.getX-D9Ej5fM(dpOffset.unbox-impl()), DpOffset.getY-D9Ej5fM(dpOffset.unbox-impl()));
    }

    public static final TwoWayConverter<Float, AnimationVector1D> getVectorConverter(FloatCompanionObject floatCompanionObject) {
        return FloatToVector;
    }

    public static IntSize h(AnimationVector2D animationVector2D) {
        int iRound = Math.round(animationVector2D.getV1());
        if (iRound < 0) {
            iRound = 0;
        }
        int iRound2 = Math.round(animationVector2D.getV2());
        return IntSize.box-impl(IntSize.constructor-impl((((long) (iRound2 >= 0 ? iRound2 : 0)) & 4294967295L) | (((long) iRound) << 32)));
    }

    public static DpOffset i(AnimationVector2D animationVector2D) {
        float f = Dp.constructor-impl(animationVector2D.getV1());
        float f2 = Dp.constructor-impl(animationVector2D.getV2());
        return DpOffset.box-impl(DpOffset.constructor-impl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)));
    }

    public static float j(AnimationVector1D animationVector1D) {
        return animationVector1D.getValue();
    }

    public static AnimationVector2D k(IntOffset intOffset) {
        return new AnimationVector2D(IntOffset.getX-impl(intOffset.unbox-impl()), IntOffset.getY-impl(intOffset.unbox-impl()));
    }

    public static AnimationVector1D l(int i) {
        return new AnimationVector1D(i);
    }

    public static final float lerp(float f, float f2, float f3) {
        return (f * (1.0f - f3)) + (f2 * f3);
    }

    public static IntOffset m(AnimationVector2D animationVector2D) {
        return IntOffset.box-impl(IntOffset.constructor-impl((((long) Math.round(animationVector2D.getV1())) << 32) | (((long) Math.round(animationVector2D.getV2())) & 4294967295L)));
    }

    public static Offset n(AnimationVector2D animationVector2D) {
        float v1 = animationVector2D.getV1();
        float v2 = animationVector2D.getV2();
        return Offset.box-impl(Offset.constructor-impl((((long) Float.floatToRawIntBits(v1)) << 32) | (((long) Float.floatToRawIntBits(v2)) & 4294967295L)));
    }

    public static Size o(AnimationVector2D animationVector2D) {
        float v1 = animationVector2D.getV1();
        float v2 = animationVector2D.getV2();
        return Size.box-impl(Size.constructor-impl((((long) Float.floatToRawIntBits(v1)) << 32) | (((long) Float.floatToRawIntBits(v2)) & 4294967295L)));
    }

    public static AnimationVector2D p(Offset offset) {
        return new AnimationVector2D(Float.intBitsToFloat((int) (offset.unbox-impl() >> 32)), Float.intBitsToFloat((int) (offset.unbox-impl() & 4294967295L)));
    }

    public static Rect q(AnimationVector4D animationVector4D) {
        return new Rect(animationVector4D.getV1(), animationVector4D.getV2(), animationVector4D.getV3(), animationVector4D.getV4());
    }

    public static AnimationVector4D r(Rect rect) {
        return new AnimationVector4D(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom());
    }

    public static final TwoWayConverter<Integer, AnimationVector1D> getVectorConverter(IntCompanionObject intCompanionObject) {
        return IntToVector;
    }

    public static final TwoWayConverter<Rect, AnimationVector4D> getVectorConverter(Rect.Companion companion) {
        return RectToVector;
    }

    public static final TwoWayConverter<Dp, AnimationVector1D> getVectorConverter(Dp.Companion companion) {
        return DpToVector;
    }

    public static final TwoWayConverter<DpOffset, AnimationVector2D> getVectorConverter(DpOffset.Companion companion) {
        return DpOffsetToVector;
    }

    public static final TwoWayConverter<Size, AnimationVector2D> getVectorConverter(Size.Companion companion) {
        return SizeToVector;
    }

    public static final TwoWayConverter<Offset, AnimationVector2D> getVectorConverter(Offset.Companion companion) {
        return OffsetToVector;
    }

    public static final TwoWayConverter<IntOffset, AnimationVector2D> getVectorConverter(IntOffset.Companion companion) {
        return IntOffsetToVector;
    }

    public static final TwoWayConverter<IntSize, AnimationVector2D> getVectorConverter(IntSize.Companion companion) {
        return IntSizeToVector;
    }
}
