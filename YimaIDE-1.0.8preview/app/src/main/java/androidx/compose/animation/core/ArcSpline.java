package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0001\u0015B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005R\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/animation/core/ArcSpline;", "", "arcModes", "", "timePoints", "", "y", "", "<init>", "([I[F[[F)V", "arcs", "Landroidx/compose/animation/core/ArcSpline$Arc;", "[[Landroidx/compose/animation/core/ArcSpline$Arc;", "isExtrapolate", "", "getPos", "", "time", "", "v", "getSlope", "Arc", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ArcSpline {
    public static final int $stable = 8;
    private final Arc[][] arcs;
    private final boolean isExtrapolate = true;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001BA\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0005J\u0011\u0010%\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005H\u0082\bJ\t\u0010&\u001a\u00020\u0005H\u0086\bJ\t\u0010'\u001a\u00020\u0005H\u0086\bJ\u0006\u0010(\u001a\u00020\u0005J\u0006\u0010)\u001a\u00020\u0005J\u000e\u0010*\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005J\u000e\u0010+\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0005J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u0005H\u0002J-\u0010.\u001a\u00020#2\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0002\b/R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u00020\u001b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u001e\u001a\u00020\u00058À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u000eR\u0015\u0010 \u001a\u00020\u00058À\u0002X\u0080\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u000e¨\u00060"}, d2 = {"Landroidx/compose/animation/core/ArcSpline$Arc;", "", "mode", "", "time1", "", "time2", "x1", "y1", "x2", "y2", "<init>", "(IFFFFFF)V", "getTime1", "()F", "getTime2", "arcDistance", "tmpSinAngle", "tmpCosAngle", "lut", "", "oneOverDeltaTime", "arcVelocity", "vertical", "ellipseA", "ellipseB", "isLinear", "", "ellipseCenterX", "ellipseCenterY", "linearDX", "getLinearDX$animation_core", "linearDY", "getLinearDY$animation_core", "setPoint", "", "time", "calcAngle", "calcX", "calcY", "calcDX", "calcDY", "getLinearX", "getLinearY", "lookup", "v", "buildTable", "buildTable$animation_core", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Arc {
        public static final int $stable = 8;
        private float arcDistance;
        private final float arcVelocity;
        public final float ellipseA;
        public final float ellipseB;
        public final float ellipseCenterX;
        public final float ellipseCenterY;
        public final boolean isLinear;
        private final float[] lut;
        private final float oneOverDeltaTime;
        private final float time1;
        private final float time2;
        private float tmpCosAngle;
        private float tmpSinAngle;
        private final float vertical;
        private final float x1;
        private final float x2;
        private final float y1;
        private final float y2;

        public Arc(int i, float f, float f2, float f3, float f4, float f5, float f6) {
            this.time1 = f;
            this.time2 = f2;
            this.x1 = f3;
            this.y1 = f4;
            this.x2 = f5;
            this.y2 = f6;
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            boolean z = true;
            boolean z2 = i == 1 || (i == 4 ? f8 > 0.0f : !(i != 5 || f8 >= 0.0f));
            float f9 = z2 ? -1.0f : 1.0f;
            this.vertical = f9;
            float f10 = 1.0f / (f2 - f);
            this.oneOverDeltaTime = f10;
            this.lut = new float[101];
            boolean z3 = i == 3;
            if (z3 || Math.abs(f7) < 0.001f || Math.abs(f8) < 0.001f) {
                float fHypot = (float) Math.hypot(f8, f7);
                this.arcDistance = fHypot;
                this.arcVelocity = fHypot * f10;
                this.ellipseCenterX = f7 * f10;
                this.ellipseCenterY = f8 * f10;
                this.ellipseA = Float.NaN;
                this.ellipseB = Float.NaN;
            } else {
                this.ellipseA = f7 * f9;
                this.ellipseB = f8 * (-f9);
                this.ellipseCenterX = z2 ? f5 : f3;
                this.ellipseCenterY = z2 ? f4 : f6;
                buildTable$animation_core(f3, f4, f5, f6);
                this.arcVelocity = this.arcDistance * f10;
                z = z3;
            }
            this.isLinear = z;
        }

        private final float calcAngle(float time) {
            return lookup((this.vertical == -1.0f ? this.time2 - time : time - this.time1) * this.oneOverDeltaTime) * 1.5707964f;
        }

        private final float lookup(float v) {
            if (v <= 0.0f) {
                return 0.0f;
            }
            if (v >= 1.0f) {
                return 1.0f;
            }
            float f = v * 100.0f;
            int i = (int) f;
            float f2 = f - i;
            float[] fArr = this.lut;
            float f3 = fArr[i];
            return f3 + (f2 * (fArr[i + 1] - f3));
        }

        public final void buildTable$animation_core(float x1, float y1, float x2, float y2) {
            float f;
            float f2;
            float fHypot;
            float f3 = x2 - x1;
            float f4 = y1 - y2;
            float[] fArr = ArcSplineKt.OurPercentCache;
            int length = fArr.length - 1;
            float f5 = length;
            float[] fArr2 = this.lut;
            if (1 <= length) {
                float f6 = f4;
                int i = 1;
                fHypot = 0.0f;
                float f7 = 0.0f;
                while (true) {
                    f2 = 0.0f;
                    double radians = (float) Math.toRadians((((double) i) * 90.0d) / ((double) length));
                    float fSin = ((float) Math.sin(radians)) * f3;
                    float fCos = ((float) Math.cos(radians)) * f4;
                    f = f5;
                    fHypot += (float) Math.hypot(fSin - f7, fCos - f6);
                    fArr[i] = fHypot;
                    if (i == length) {
                        break;
                    }
                    i++;
                    f6 = fCos;
                    f5 = f;
                    f7 = fSin;
                }
            } else {
                f = f5;
                f2 = 0.0f;
                fHypot = 0.0f;
            }
            this.arcDistance = fHypot;
            if (1 <= length) {
                int i2 = 1;
                while (true) {
                    fArr[i2] = fArr[i2] / fHypot;
                    if (i2 == length) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            int length2 = fArr2.length;
            for (int i3 = 0; i3 < length2; i3++) {
                float f8 = i3 / 100.0f;
                int iBinarySearch$default = ArraysKt.binarySearch$default(fArr, f8, 0, 0, 6, (Object) null);
                if (iBinarySearch$default >= 0) {
                    fArr2[i3] = iBinarySearch$default / f;
                } else {
                    if (iBinarySearch$default == -1) {
                        fArr2[i3] = f2;
                    } else {
                        int i4 = -iBinarySearch$default;
                        int i5 = i4 - 2;
                        float f9 = i5;
                        float f10 = fArr[i5];
                        fArr2[i3] = (f9 + ((f8 - f10) / (fArr[i4 - 1] - f10))) / f;
                    }
                }
            }
        }

        public final float calcDX() {
            float f = this.ellipseA * this.tmpCosAngle;
            return f * this.vertical * (this.arcVelocity / ((float) Math.hypot(f, (-this.ellipseB) * this.tmpSinAngle)));
        }

        public final float calcDY() {
            float f = this.ellipseA * this.tmpCosAngle;
            float f2 = (-this.ellipseB) * this.tmpSinAngle;
            return f2 * this.vertical * (this.arcVelocity / ((float) Math.hypot(f, f2)));
        }

        public final float calcX() {
            return this.ellipseCenterX + (this.ellipseA * this.tmpSinAngle);
        }

        public final float calcY() {
            return this.ellipseCenterY + (this.ellipseB * this.tmpCosAngle);
        }

        /* JADX INFO: renamed from: getLinearDX$animation_core, reason: from getter */
        public final float getEllipseCenterX() {
            return this.ellipseCenterX;
        }

        /* JADX INFO: renamed from: getLinearDY$animation_core, reason: from getter */
        public final float getEllipseCenterY() {
            return this.ellipseCenterY;
        }

        public final float getLinearX(float time) {
            float f = (time - this.time1) * this.oneOverDeltaTime;
            float f2 = this.x1;
            return f2 + (f * (this.x2 - f2));
        }

        public final float getLinearY(float time) {
            float f = (time - this.time1) * this.oneOverDeltaTime;
            float f2 = this.y1;
            return f2 + (f * (this.y2 - f2));
        }

        public final float getTime1() {
            return this.time1;
        }

        public final float getTime2() {
            return this.time2;
        }

        public final void setPoint(float time) {
            double dLookup = lookup((this.vertical == -1.0f ? this.time2 - time : time - this.time1) * this.oneOverDeltaTime) * 1.5707964f;
            this.tmpSinAngle = (float) Math.sin(dLookup);
            this.tmpCosAngle = (float) Math.cos(dLookup);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0028 A[PHI: r10
      0x0028: PHI (r10v1 int) = (r10v0 int), (r10v3 int), (r10v4 int) binds: [B:5:0x0018, B:10:0x0021, B:12:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:19:0x0031  */
    public ArcSpline(int[] iArr, float[] fArr, float[][] fArr2) {
        int i;
        int i2 = 1;
        int length = fArr.length - 1;
        Arc[][] arcArr = new Arc[length][];
        int i3 = 1;
        int i4 = 1;
        int i5 = 0;
        while (i5 < length) {
            int i6 = iArr[i5];
            int i7 = 3;
            if (i6 == 0) {
                i = i7;
            } else if (i6 == i2) {
                i3 = i2;
                i = i3;
            } else {
                if (i6 != 2) {
                    if (i6 != 3) {
                        i7 = 4;
                        if (i6 != 4) {
                            i7 = 5;
                            if (i6 != 5) {
                                i = i4;
                            } else {
                                i = i7;
                            }
                        } else {
                            i = i7;
                        }
                    } else {
                        if (i3 != i2) {
                            i3 = i2;
                        }
                        i = i3;
                    }
                }
                i3 = 2;
                i = i3;
            }
            float[] fArr3 = fArr2[i5];
            int i8 = i5 + 1;
            float[] fArr4 = fArr2[i8];
            float f = fArr[i5];
            float f2 = fArr[i8];
            int length2 = (fArr3.length % 2) + (fArr3.length / 2);
            Arc[] arcArr2 = new Arc[length2];
            int i9 = 0;
            while (i9 < length2) {
                int i10 = i9 * 2;
                int i11 = i9;
                int i12 = i10 + 1;
                arcArr2[i11] = new Arc(i, f, f2, fArr3[i10], fArr3[i12], fArr4[i10], fArr4[i12]);
                i9 = i11 + 1;
            }
            arcArr[i5] = arcArr2;
            i5 = i8;
            i4 = i;
            i2 = 1;
        }
        this.arcs = arcArr;
    }

    public final void getPos(float time, float[] v) {
        Arc[][] arcArr = this.arcs;
        int length = arcArr.length - 1;
        int i = 0;
        float time1 = arcArr[0][0].getTime1();
        float time2 = arcArr[length][0].getTime2();
        int length2 = v.length;
        if (!this.isExtrapolate) {
            time = Math.min(Math.max(time, time1), time2);
        } else if (time < time1 || time > time2) {
            if (time > time2) {
                time1 = time2;
            } else {
                length = 0;
            }
            float f = time - time1;
            int i2 = 0;
            while (i < length2 - 1) {
                Arc arc = arcArr[length][i2];
                if (arc.isLinear) {
                    v[i] = arc.getLinearX(time1) + (arc.ellipseCenterX * f);
                    v[i + 1] = arc.getLinearY(time1) + (arc.ellipseCenterY * f);
                } else {
                    arc.setPoint(time1);
                    v[i] = arc.ellipseCenterX + (arc.ellipseA * arc.tmpSinAngle) + (arc.calcDX() * f);
                    v[i + 1] = arc.ellipseCenterY + (arc.ellipseB * arc.tmpCosAngle) + (arc.calcDY() * f);
                }
                i += 2;
                i2++;
            }
            return;
        }
        boolean z = false;
        for (Arc[] arcArr2 : arcArr) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < length2 - 1) {
                Arc arc2 = arcArr2[i4];
                if (time <= arc2.getTime2()) {
                    if (arc2.isLinear) {
                        v[i3] = arc2.getLinearX(time);
                        v[i3 + 1] = arc2.getLinearY(time);
                    } else {
                        arc2.setPoint(time);
                        v[i3] = arc2.ellipseCenterX + (arc2.ellipseA * arc2.tmpSinAngle);
                        v[i3 + 1] = arc2.ellipseCenterY + (arc2.ellipseB * arc2.tmpCosAngle);
                    }
                    z = true;
                }
                i3 += 2;
                i4++;
            }
            if (z) {
                return;
            }
        }
    }

    public final void getSlope(float time, float[] v) {
        Arc[][] arcArr = this.arcs;
        float time1 = arcArr[0][0].getTime1();
        float time2 = arcArr[arcArr.length - 1][0].getTime2();
        if (time < time1) {
            time = time1;
        }
        if (time <= time2) {
            time2 = time;
        }
        int length = v.length;
        boolean z = false;
        for (Arc[] arcArr2 : arcArr) {
            int i = 0;
            int i2 = 0;
            while (i < length - 1) {
                Arc arc = arcArr2[i2];
                if (time2 <= arc.getTime2()) {
                    if (arc.isLinear) {
                        v[i] = arc.ellipseCenterX;
                        v[i + 1] = arc.ellipseCenterY;
                    } else {
                        arc.setPoint(time2);
                        v[i] = arc.calcDX();
                        v[i + 1] = arc.calcDY();
                    }
                    z = true;
                }
                i += 2;
                i2++;
            }
            if (z) {
                return;
            }
        }
    }
}
