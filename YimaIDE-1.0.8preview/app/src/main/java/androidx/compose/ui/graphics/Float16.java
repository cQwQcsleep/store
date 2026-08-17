package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.internal.view.SupportMenu;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Regex;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0081@\u0018\u0000 O2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001OB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nJ\r\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0005J\r\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\t¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0014¢\u0006\u0004\b\"\u0010\u0016J\r\u0010#\u001a\u00020\u0014¢\u0006\u0004\b$\u0010\u0016J\u000f\u0010%\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(J\u0018\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b+\u0010,J\u0015\u0010/\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0000¢\u0006\u0004\b0\u00101J\r\u00102\u001a\u00020\u0000¢\u0006\u0004\b3\u0010\u0005J\r\u00104\u001a\u00020\u0000¢\u0006\u0004\b5\u0010\u0005J\r\u00106\u001a\u00020\u0000¢\u0006\u0004\b7\u0010\u0005J\r\u00108\u001a\u00020\u0000¢\u0006\u0004\b9\u0010\u0005J\r\u0010:\u001a\u00020\u0000¢\u0006\u0004\b;\u0010\u0005J\r\u0010@\u001a\u00020A¢\u0006\u0004\bB\u0010CJ\r\u0010D\u001a\u00020A¢\u0006\u0004\bE\u0010CJ\r\u0010F\u001a\u00020A¢\u0006\u0004\bG\u0010CJ\r\u0010H\u001a\u00020A¢\u0006\u0004\bI\u0010CJ\r\u0010J\u001a\u00020&¢\u0006\u0004\bK\u0010(J\u0013\u0010L\u001a\u00020A2\b\u0010*\u001a\u0004\u0018\u00010MHÖ\u0003J\t\u0010N\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010-\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b.\u0010\u0005R\u0011\u0010<\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b=\u0010\u0016R\u0011\u0010>\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b?\u0010\u0016\u0088\u0001\u0002¨\u0006P"}, d2 = {"Landroidx/compose/ui/graphics/Float16;", "", "halfValue", "", "constructor-impl", "(S)S", "value", "", "(F)S", "", "(D)S", "getHalfValue", "()S", "toByte", "", "toByte-impl", "(S)B", "toShort", "toShort-impl", "toInt", "", "toInt-impl", "(S)I", "toLong", "", "toLong-impl", "(S)J", "toFloat", "toFloat-impl", "(S)F", "toDouble", "toDouble-impl", "(S)D", "toBits", "toBits-impl", "toRawBits", "toRawBits-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "compareTo", "other", "compareTo-41bOqos", "(SS)I", "sign", "getSign-slo4al4", "withSign", "withSign-qCeQghg", "(SS)S", "absoluteValue", "absoluteValue-slo4al4", "round", "round-slo4al4", "ceil", "ceil-slo4al4", "floor", "floor-slo4al4", "trunc", "trunc-slo4al4", "exponent", "getExponent-impl", "significand", "getSignificand-impl", "isNaN", "", "isNaN-impl", "(S)Z", "isInfinite", "isInfinite-impl", "isFinite", "isFinite-impl", "isNormalized", "isNormalized-impl", "toHexString", "toHexString-impl", "equals", "", "hashCode", "Companion", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class Float16 implements Comparable<Float16> {
    public static final int MaxExponent = 15;
    public static final int MinExponent = -14;
    public static final int Size = 16;
    private final short halfValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final short Epsilon = m3243constructorimpl((short) 5120);
    private static final short LowestValue = m3243constructorimpl((short) -1025);
    private static final short MaxValue = m3243constructorimpl((short) 31743);
    private static final short MinNormal = m3243constructorimpl((short) 1024);
    private static final short MinValue = m3243constructorimpl((short) 1);
    private static final short NaN = m3243constructorimpl((short) 32256);
    private static final short NegativeInfinity = m3243constructorimpl((short) -1024);
    private static final short NegativeZero = m3243constructorimpl(Short.MIN_VALUE);
    private static final short PositiveInfinity = m3243constructorimpl((short) 31744);
    private static final short PositiveZero = m3243constructorimpl((short) 0);

    private /* synthetic */ Float16(short s) {
        this.halfValue = s;
    }

    /* JADX INFO: renamed from: absoluteValue-slo4al4, reason: not valid java name */
    public static final short m3237absoluteValueslo4al4(short s) {
        return m3243constructorimpl((short) (s & Short.MAX_VALUE));
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Float16 m3238boximpl(short s) {
        return new Float16(s);
    }

    /* JADX INFO: renamed from: ceil-slo4al4, reason: not valid java name */
    public static final short m3239ceilslo4al4(short s) {
        int i = 65535 & s;
        int i2 = s & Short.MAX_VALUE;
        if (i2 < 15360) {
            i = ((-((~(i >> 15)) & (i2 == 0 ? 0 : 1))) & 15360) | (s & Short.MIN_VALUE);
        } else if (i2 < 25600) {
            int i3 = (1 << (25 - (i2 >> 10))) - 1;
            i = (i + (((i >> 15) - 1) & i3)) & (~i3);
        }
        return m3243constructorimpl((short) i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: compareTo-41bOqos, reason: not valid java name */
    public static int m3240compareTo41bOqos(short s, short s2) {
        if (m3253isNaNimpl(s)) {
            return !m3253isNaNimpl(s2) ? 1 : 0;
        }
        if (m3253isNaNimpl(s2)) {
            return -1;
        }
        return Intrinsics.compare((s & Short.MIN_VALUE) != 0 ? 32768 - (s & 65535) : s & 65535, (s2 & Short.MIN_VALUE) != 0 ? 32768 - (s2 & 65535) : s2 & 65535);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m3242constructorimpl(float f) {
        int i;
        int iFloatToRawIntBits = Float.floatToRawIntBits(f);
        int i2 = iFloatToRawIntBits >>> 31;
        int i3 = (iFloatToRawIntBits >>> 23) & 255;
        int i4 = 8388607 & iFloatToRawIntBits;
        int i5 = 31;
        int i6 = 0;
        if (i3 != 255) {
            int i7 = i3 - 112;
            if (i7 >= 31) {
                i5 = 49;
            } else {
                if (i7 > 0) {
                    i6 = i4 >> 13;
                    if ((iFloatToRawIntBits & 4096) != 0) {
                        i = (((i7 << 10) | i6) + 1) | (i2 << 15);
                    } else {
                        i5 = i7;
                    }
                    return m3243constructorimpl((short) i);
                }
                if (i7 >= -10) {
                    int i8 = (8388608 | i4) >> (1 - i7);
                    if ((i8 & 4096) != 0) {
                        i8 += 8192;
                    }
                    i5 = 0;
                    i6 = i8 >> 13;
                } else {
                    i5 = 0;
                }
            }
        } else if (i4 != 0) {
            i6 = 512;
        }
        i = (i2 << 15) | (i5 << 10) | i6;
        return m3243constructorimpl((short) i);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3244equalsimpl(short s, Object obj) {
        return (obj instanceof Float16) && s == ((Float16) obj).m3269unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3245equalsimpl0(short s, short s2) {
        return s == s2;
    }

    /* JADX INFO: renamed from: floor-slo4al4, reason: not valid java name */
    public static final short m3246floorslo4al4(short s) {
        int i = SupportMenu.USER_MASK;
        int i2 = s & 65535;
        int i3 = s & Short.MAX_VALUE;
        if (i3 < 15360) {
            int i4 = s & Short.MIN_VALUE;
            if (i2 <= 32768) {
                i = 0;
            }
            i2 = i4 | (i & 15360);
        } else if (i3 < 25600) {
            int i5 = (1 << (25 - (i3 >> 10))) - 1;
            i2 = (i2 + ((-(i2 >> 15)) & i5)) & (~i5);
        }
        return m3243constructorimpl((short) i2);
    }

    /* JADX INFO: renamed from: getExponent-impl, reason: not valid java name */
    public static final int m3247getExponentimpl(short s) {
        return ((s >>> 10) & 31) - 15;
    }

    /* JADX INFO: renamed from: getSign-slo4al4, reason: not valid java name */
    public static final short m3248getSignslo4al4(short s) {
        int i = s & Short.MAX_VALUE;
        if (!((i > 31744) | (i == 0))) {
            i = (s & Short.MIN_VALUE) | 15360;
        }
        return m3243constructorimpl((short) i);
    }

    /* JADX INFO: renamed from: getSignificand-impl, reason: not valid java name */
    public static final int m3249getSignificandimpl(short s) {
        return s & 1023;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3250hashCodeimpl(short s) {
        return Short.hashCode(s);
    }

    /* JADX INFO: renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m3251isFiniteimpl(short s) {
        return (s & Short.MAX_VALUE) != 31744;
    }

    /* JADX INFO: renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m3252isInfiniteimpl(short s) {
        return (s & Short.MAX_VALUE) == 31744;
    }

    /* JADX INFO: renamed from: isNaN-impl, reason: not valid java name */
    public static final boolean m3253isNaNimpl(short s) {
        return (s & Short.MAX_VALUE) > 31744;
    }

    /* JADX INFO: renamed from: isNormalized-impl, reason: not valid java name */
    public static final boolean m3254isNormalizedimpl(short s) {
        int i = s & 31744;
        return (i != 0) & (i != 31744);
    }

    /* JADX INFO: renamed from: round-slo4al4, reason: not valid java name */
    public static final short m3255roundslo4al4(short s) {
        int i = SupportMenu.USER_MASK;
        int i2 = s & 65535;
        int i3 = s & Short.MAX_VALUE;
        if (i3 < 15360) {
            int i4 = s & Short.MIN_VALUE;
            if (i3 < 14336) {
                i = 0;
            }
            i2 = i4 | (i & 15360);
        } else if (i3 < 25600) {
            int i5 = i3 >> 10;
            i2 = (i2 + (1 << (24 - i5))) & (~((1 << (25 - i5)) - 1));
        }
        return m3243constructorimpl((short) i2);
    }

    /* JADX INFO: renamed from: toBits-impl, reason: not valid java name */
    public static final int m3256toBitsimpl(short s) {
        if (m3253isNaNimpl(s)) {
            return 32256;
        }
        return s & 65535;
    }

    /* JADX INFO: renamed from: toByte-impl, reason: not valid java name */
    public static final byte m3257toByteimpl(short s) {
        return (byte) m3259toFloatimpl(s);
    }

    /* JADX INFO: renamed from: toDouble-impl, reason: not valid java name */
    public static final double m3258toDoubleimpl(short s) {
        return m3259toFloatimpl(s);
    }

    /* JADX INFO: renamed from: toFloat-impl, reason: not valid java name */
    public static final float m3259toFloatimpl(short s) {
        int i;
        int i2;
        int i3;
        int i4 = Short.MIN_VALUE & s;
        int i5 = ((65535 & s) >>> 10) & 31;
        int i6 = s & 1023;
        if (i5 != 0) {
            int i7 = i6 << 13;
            if (i5 == 31) {
                i = 255;
                if (i7 != 0) {
                    i7 |= 4194304;
                }
            } else {
                i = i5 + 112;
            }
            int i8 = i;
            i2 = i7;
            i3 = i8;
        } else {
            if (i6 != 0) {
                float fIntBitsToFloat = Float.intBitsToFloat(i6 + 1056964608) - Float16Kt.Fp32DenormalFloat;
                return i4 == 0 ? fIntBitsToFloat : -fIntBitsToFloat;
            }
            i3 = 0;
            i2 = 0;
        }
        return Float.intBitsToFloat((i3 << 23) | (i4 << 16) | i2);
    }

    /* JADX INFO: renamed from: toHexString-impl, reason: not valid java name */
    public static final String m3260toHexStringimpl(short s) {
        StringBuilder sb = new StringBuilder();
        int i = 65535 & s;
        int i2 = i >>> 15;
        int i3 = (i >>> 10) & 31;
        int i4 = s & 1023;
        if (i3 != 31) {
            if (i2 == 1) {
                sb.append('-');
            }
            if (i3 != 0) {
                sb.append("0x1.");
                String string = Integer.toString(i4, CharsKt.checkRadix(16));
                string.getClass();
                sb.append(new Regex("0{2,}$").replaceFirst(string, ""));
                sb.append('p');
                sb.append(String.valueOf(i3 - 15));
            } else if (i4 == 0) {
                sb.append("0x0.0p0");
            } else {
                sb.append("0x0.");
                String string2 = Integer.toString(i4, CharsKt.checkRadix(16));
                string2.getClass();
                sb.append(new Regex("0{2,}$").replaceFirst(string2, ""));
                sb.append("p-14");
            }
        } else if (i4 == 0) {
            if (i2 != 0) {
                sb.append('-');
            }
            sb.append("Infinity");
        } else {
            sb.append("NaN");
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: toInt-impl, reason: not valid java name */
    public static final int m3261toIntimpl(short s) {
        return (int) m3259toFloatimpl(s);
    }

    /* JADX INFO: renamed from: toLong-impl, reason: not valid java name */
    public static final long m3262toLongimpl(short s) {
        return (long) m3259toFloatimpl(s);
    }

    /* JADX INFO: renamed from: toRawBits-impl, reason: not valid java name */
    public static final int m3263toRawBitsimpl(short s) {
        return s & 65535;
    }

    /* JADX INFO: renamed from: toShort-impl, reason: not valid java name */
    public static final short m3264toShortimpl(short s) {
        return (short) m3259toFloatimpl(s);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3265toStringimpl(short s) {
        return String.valueOf(m3259toFloatimpl(s));
    }

    /* JADX INFO: renamed from: trunc-slo4al4, reason: not valid java name */
    public static final short m3266truncslo4al4(short s) {
        int i = 65535 & s;
        int i2 = s & Short.MAX_VALUE;
        if (i2 < 15360) {
            i = Short.MIN_VALUE & s;
        } else if (i2 < 25600) {
            i &= ~((1 << (25 - (i2 >> 10))) - 1);
        }
        return m3243constructorimpl((short) i);
    }

    /* JADX INFO: renamed from: withSign-qCeQghg, reason: not valid java name */
    public static final short m3267withSignqCeQghg(short s, short s2) {
        return m3243constructorimpl((short) ((s & Short.MAX_VALUE) | (s2 & Short.MIN_VALUE)));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Float16 float16) {
        return m3268compareTo41bOqos(float16.m3269unboximpl());
    }

    public boolean equals(Object other) {
        return m3244equalsimpl(this.halfValue, other);
    }

    public final short getHalfValue() {
        return this.halfValue;
    }

    public int hashCode() {
        return m3250hashCodeimpl(this.halfValue);
    }

    public String toString() {
        return m3265toStringimpl(this.halfValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short m3269unboximpl() {
        return this.halfValue;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u000e\u0010\tR\u0013\u0010\u000f\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0010\u0010\tR\u0013\u0010\u0011\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0012\u0010\tR\u0013\u0010\u0013\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0014\u0010\tR\u0013\u0010\u0015\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0016\u0010\tR\u0013\u0010\u0017\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u0018\u0010\tR\u0013\u0010\u0019\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001a\u0010\tR\u0013\u0010\u001b\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001c\u0010\tR\u0013\u0010\u001d\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\u001e\u0010\t¨\u0006\u001f"}, d2 = {"Landroidx/compose/ui/graphics/Float16$Companion;", "", "<init>", "()V", "Size", "", "Epsilon", "Landroidx/compose/ui/graphics/Float16;", "getEpsilon-slo4al4", "()S", "S", "MaxExponent", "MinExponent", "LowestValue", "getLowestValue-slo4al4", "MaxValue", "getMaxValue-slo4al4", "MinNormal", "getMinNormal-slo4al4", "MinValue", "getMinValue-slo4al4", "NaN", "getNaN-slo4al4", "NegativeInfinity", "getNegativeInfinity-slo4al4", "NegativeZero", "getNegativeZero-slo4al4", "PositiveInfinity", "getPositiveInfinity-slo4al4", "PositiveZero", "getPositiveZero-slo4al4", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getEpsilon-slo4al4, reason: not valid java name */
        public final short m3270getEpsilonslo4al4() {
            return Float16.Epsilon;
        }

        /* JADX INFO: renamed from: getLowestValue-slo4al4, reason: not valid java name */
        public final short m3271getLowestValueslo4al4() {
            return Float16.LowestValue;
        }

        /* JADX INFO: renamed from: getMaxValue-slo4al4, reason: not valid java name */
        public final short m3272getMaxValueslo4al4() {
            return Float16.MaxValue;
        }

        /* JADX INFO: renamed from: getMinNormal-slo4al4, reason: not valid java name */
        public final short m3273getMinNormalslo4al4() {
            return Float16.MinNormal;
        }

        /* JADX INFO: renamed from: getMinValue-slo4al4, reason: not valid java name */
        public final short m3274getMinValueslo4al4() {
            return Float16.MinValue;
        }

        /* JADX INFO: renamed from: getNaN-slo4al4, reason: not valid java name */
        public final short m3275getNaNslo4al4() {
            return Float16.NaN;
        }

        /* JADX INFO: renamed from: getNegativeInfinity-slo4al4, reason: not valid java name */
        public final short m3276getNegativeInfinityslo4al4() {
            return Float16.NegativeInfinity;
        }

        /* JADX INFO: renamed from: getNegativeZero-slo4al4, reason: not valid java name */
        public final short m3277getNegativeZeroslo4al4() {
            return Float16.NegativeZero;
        }

        /* JADX INFO: renamed from: getPositiveInfinity-slo4al4, reason: not valid java name */
        public final short m3278getPositiveInfinityslo4al4() {
            return Float16.PositiveInfinity;
        }

        /* JADX INFO: renamed from: getPositiveZero-slo4al4, reason: not valid java name */
        public final short m3279getPositiveZeroslo4al4() {
            return Float16.PositiveZero;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: compareTo-41bOqos, reason: not valid java name */
    public int m3268compareTo41bOqos(short s) {
        return m3240compareTo41bOqos(this.halfValue, s);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m3241constructorimpl(double d) {
        return m3242constructorimpl((float) d);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static short m3243constructorimpl(short s) {
        return s;
    }
}
