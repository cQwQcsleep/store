package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004*\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"computeDirection", "Landroidx/compose/ui/graphics/Path$Direction;", "Landroidx/compose/ui/graphics/Path;", "divide", "", "contours", "reverse", "destination", "floatCountForType", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/graphics/PathSegment$Type;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PathGeometryKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PathSegment.Type.values().length];
            try {
                iArr[PathSegment.Type.Move.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PathSegment.Type.Line.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PathSegment.Type.Quadratic.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PathSegment.Type.Conic.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PathSegment.Type.Cubic.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PathSegment.Type.Close.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PathSegment.Type.Done.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code duplicated, block: B:24:0x00da  */
    /* JADX WARN: Code duplicated, block: B:26:0x00dd  */
    public static final Path.Direction computeDirection(Path path) {
        PathIterator it = path.iterator();
        float[] fArr = new float[8];
        PathSegment.Type typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
        boolean z = true;
        float fCubicArea = 0.0f;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (typeNext$default != PathSegment.Type.Done) {
            switch (WhenMappings.$EnumSwitchMapping$0[typeNext$default.ordinal()]) {
                case 1:
                    if (!z) {
                        return fCubicArea >= 0.0f ? Path.Direction.Clockwise : Path.Direction.CounterClockwise;
                    }
                    f3 = fArr[0];
                    f4 = fArr[1];
                    z = false;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                    break;
                case 2:
                    float f5 = fArr[0];
                    float f6 = fArr[1];
                    float f7 = fArr[2];
                    float f8 = fArr[3];
                    fCubicArea += BezierKt.cubicArea(f5, f6, f5, f6, f7, f8, f7, f8);
                    f = f7;
                    f2 = f8;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 3:
                    float f9 = fArr[0];
                    float f10 = fArr[1];
                    float f11 = fArr[2];
                    float f12 = fArr[3];
                    float f13 = fArr[4];
                    float f14 = fArr[5];
                    fCubicArea += BezierKt.cubicArea(f9, f10, f9 + ((f11 - f9) * 0.6666667f), f10 + ((f12 - f10) * 0.6666667f), f13 + ((f11 - f13) * 0.6666667f), f14 + ((f12 - f14) * 0.6666667f), f13, f14);
                    f = f13;
                    f2 = f14;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 4:
                    break;
                case 5:
                    fCubicArea += BezierKt.cubicArea(fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7]);
                    float f15 = fArr[6];
                    f2 = fArr[7];
                    f = f15;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 6:
                    if (Math.abs(f - f3) >= 1.05E-6f || Math.abs(f2 - f4) >= 1.05E-6f) {
                        fCubicArea += BezierKt.cubicArea(f, f2, f, f2, f3, f4, f3, f4);
                        f = f3;
                        f2 = f4;
                    }
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 7:
                    if (fCubicArea >= 0.0f) {
                    }
                default:
                    bu8.a();
                    return null;
            }
        }
        if (fCubicArea >= 0.0f) {
        }
    }

    public static final List<Path> divide(Path path, List<Path> list) {
        Path Path = AndroidPath_androidKt.Path();
        PathIterator it = path.iterator();
        float[] fArr = new float[8];
        PathSegment.Type typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
        Path Path2 = Path;
        boolean z = true;
        boolean z2 = true;
        while (typeNext$default != PathSegment.Type.Done) {
            switch (WhenMappings.$EnumSwitchMapping$0[typeNext$default.ordinal()]) {
                case 1:
                    if (!z && !z2) {
                        list.add(Path2);
                        Path2 = AndroidPath_androidKt.Path();
                    }
                    Path2.moveTo(fArr[0], fArr[1]);
                    z = false;
                    z2 = true;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 2:
                    Path2.lineTo(fArr[2], fArr[3]);
                    z2 = false;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 3:
                    Path2.quadraticTo(fArr[2], fArr[3], fArr[4], fArr[5]);
                    z2 = false;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 4:
                case 7:
                    break;
                case 5:
                    Path2.cubicTo(fArr[2], fArr[3], fArr[4], fArr[5], fArr[6], fArr[7]);
                    z2 = false;
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                case 6:
                    Path2.close();
                    typeNext$default = PathIterator.next$default(it, fArr, 0, 2, null);
                    break;
                default:
                    bu8.a();
                    return null;
            }
        }
        if (!z && !z2) {
            list.add(Path2);
        }
        return list;
    }

    public static /* synthetic */ List divide$default(Path path, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return divide(path, list);
    }

    private static final int floatCountForType(PathSegment.Type type) {
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
            case 4:
            case 5:
                return 8;
            case 6:
            case 7:
                return 0;
            default:
                bu8.a();
                return 0;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static final Path reverse(Path path, Path path2) {
        boolean z;
        float[] fArr;
        PathIterator it = path.iterator();
        int iCalculateSize = it.calculateSize(false);
        ArrayList arrayList = new ArrayList(iCalculateSize);
        ArrayList arrayList2 = new ArrayList(iCalculateSize);
        float[] fArr2 = new float[8];
        for (PathSegment.Type typeNext$default = PathIterator.next$default(it, fArr2, 0, 2, null); typeNext$default != PathSegment.Type.Done; typeNext$default = PathIterator.next$default(it, fArr2, 0, 2, null)) {
            arrayList.add(typeNext$default);
            if (typeNext$default != PathSegment.Type.Close) {
                arrayList2.add(Arrays.copyOf(fArr2, floatCountForType(typeNext$default)));
            }
        }
        int size = arrayList2.size();
        boolean z2 = false;
        boolean z3 = true;
        for (int size2 = arrayList.size() - 1; -1 < size2; size2--) {
            if (z3) {
                size--;
                fArr = (float[]) arrayList2.get(size);
                int lastIndex = ArraysKt.getLastIndex(fArr);
                path2.moveTo(fArr[lastIndex - 1], fArr[lastIndex]);
                z = false;
            } else {
                z = z3;
                fArr = (float[]) arrayList2.get(size);
            }
            int i = size;
            switch (WhenMappings.$EnumSwitchMapping$0[((PathSegment.Type) arrayList.get(size2)).ordinal()]) {
                case 1:
                    if (z2) {
                        path2.close();
                        z2 = false;
                    }
                    z3 = true;
                    size = i;
                    break;
                case 2:
                    float[] fArr3 = fArr;
                    path2.lineTo(fArr3[0], fArr3[1]);
                    i--;
                    size = i;
                    z3 = z;
                    break;
                case 3:
                    float[] fArr4 = fArr;
                    path2.quadraticTo(fArr4[2], fArr4[3], fArr4[0], fArr4[1]);
                    i--;
                    size = i;
                    z3 = z;
                    break;
                case 4:
                case 7:
                    size = i;
                    z3 = z;
                    break;
                case 5:
                    float[] fArr5 = fArr;
                    path2.cubicTo(fArr[4], fArr[5], fArr[2], fArr5[3], fArr5[0], fArr5[1]);
                    i--;
                    size = i;
                    z3 = z;
                    break;
                case 6:
                    z2 = true;
                    size = i;
                    z3 = z;
                    break;
                default:
                    bu8.a();
                    return null;
            }
        }
        if (z2) {
            path2.close();
        }
        return path2;
    }

    public static /* synthetic */ Path reverse$default(Path path, Path path2, int i, Object obj) {
        if ((i & 1) != 0) {
            path2 = AndroidPath_androidKt.Path();
        }
        return reverse(path, path2);
    }
}
