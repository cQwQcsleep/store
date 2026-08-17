package androidx.compose.material3.internal;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÁ\u0002\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Landroidx/compose/material3/internal/Icons;", "", "<init>", "()V", "AutoMirrored", "Filled", "Outlined", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Icons {
    public static final int $stable = 0;
    public static final Icons INSTANCE = new Icons();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/compose/material3/internal/Icons$AutoMirrored;", "", "<init>", "()V", "Filled", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AutoMirrored {
        public static final int $stable = 0;
        public static final AutoMirrored INSTANCE = new AutoMirrored();

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/material3/internal/Icons$AutoMirrored$Filled;", "", "<init>", "()V", "KeyboardArrowLeft", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getKeyboardArrowLeft$material3", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "_keyboardArrowLeft", "KeyboardArrowRight", "getKeyboardArrowRight$material3", "_keyboardArrowRight", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Filled {
            private static ImageVector _keyboardArrowLeft;
            private static ImageVector _keyboardArrowRight;
            public static final Filled INSTANCE = new Filled();
            public static final int $stable = 8;

            private Filled() {
            }

            public final ImageVector getKeyboardArrowLeft$material3() {
                ImageVector imageVector = _keyboardArrowLeft;
                if (imageVector != null) {
                    imageVector.getClass();
                    return imageVector;
                }
                ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.KeyboardArrowLeft", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
                int defaultFillType = VectorKt.getDefaultFillType();
                SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
                int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
                int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(15.41f, 16.59f);
                pathBuilder.lineTo(10.83f, 12.0f);
                pathBuilder.lineToRelative(4.58f, -4.59f);
                pathBuilder.lineTo(14.0f, 6.0f);
                pathBuilder.lineToRelative(-6.0f, 6.0f);
                pathBuilder.lineToRelative(6.0f, 6.0f);
                pathBuilder.lineToRelative(1.41f, -1.41f);
                pathBuilder.close();
                ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
                _keyboardArrowLeft = imageVectorBuild;
                imageVectorBuild.getClass();
                return imageVectorBuild;
            }

            public final ImageVector getKeyboardArrowRight$material3() {
                ImageVector imageVector = _keyboardArrowRight;
                if (imageVector != null) {
                    imageVector.getClass();
                    return imageVector;
                }
                ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.KeyboardArrowRight", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
                int defaultFillType = VectorKt.getDefaultFillType();
                SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
                int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
                int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
                PathBuilder pathBuilder = new PathBuilder();
                pathBuilder.moveTo(8.59f, 16.59f);
                pathBuilder.lineTo(13.17f, 12.0f);
                pathBuilder.lineTo(8.59f, 7.41f);
                pathBuilder.lineTo(10.0f, 6.0f);
                pathBuilder.lineToRelative(6.0f, 6.0f);
                pathBuilder.lineToRelative(-6.0f, 6.0f);
                pathBuilder.lineToRelative(-1.41f, -1.41f);
                pathBuilder.close();
                ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
                _keyboardArrowRight = imageVectorBuild;
                imageVectorBuild.getClass();
                return imageVectorBuild;
            }
        }

        private AutoMirrored() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0007R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0007R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/compose/material3/internal/Icons$Filled;", "", "<init>", "()V", "Close", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getClose$material3", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "_close", "Check", "getCheck$material3", "_check", "Edit", "getEdit$material3", "_edit", "DateRange", "getDateRange$material3", "_dateRange", "ArrowDropDown", "getArrowDropDown$material3", "_arrowDropDown", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Filled {
        private static ImageVector _arrowDropDown;
        private static ImageVector _check;
        private static ImageVector _close;
        private static ImageVector _dateRange;
        private static ImageVector _edit;
        public static final Filled INSTANCE = new Filled();
        public static final int $stable = 8;

        private Filled() {
        }

        public final ImageVector getArrowDropDown$material3() {
            ImageVector imageVector = _arrowDropDown;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Filled.ArrowDropDown", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(7.0f, 10.0f);
            pathBuilder.lineToRelative(5.0f, 5.0f);
            pathBuilder.lineToRelative(5.0f, -5.0f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _arrowDropDown = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }

        public final ImageVector getCheck$material3() {
            ImageVector imageVector = _check;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Filled.Check", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(9.0f, 16.17f);
            pathBuilder.lineTo(4.83f, 12.0f);
            pathBuilder.lineToRelative(-1.42f, 1.41f);
            pathBuilder.lineTo(9.0f, 19.0f);
            pathBuilder.lineTo(21.0f, 7.0f);
            pathBuilder.lineToRelative(-1.41f, -1.41f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _check = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }

        public final ImageVector getClose$material3() {
            ImageVector imageVector = _close;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Filled.Close", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(19.0f, 6.41f);
            pathBuilder.lineTo(17.59f, 5.0f);
            pathBuilder.lineTo(12.0f, 10.59f);
            pathBuilder.lineTo(6.41f, 5.0f);
            pathBuilder.lineTo(5.0f, 6.41f);
            pathBuilder.lineTo(10.59f, 12.0f);
            pathBuilder.lineTo(5.0f, 17.59f);
            pathBuilder.lineTo(6.41f, 19.0f);
            pathBuilder.lineTo(12.0f, 13.41f);
            pathBuilder.lineTo(17.59f, 19.0f);
            pathBuilder.lineTo(19.0f, 17.59f);
            pathBuilder.lineTo(13.41f, 12.0f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _close = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }

        public final ImageVector getDateRange$material3() {
            ImageVector imageVector = _dateRange;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Filled.DateRange", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(9.0f, 11.0f);
            pathBuilder.lineTo(7.0f, 11.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(13.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(17.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(19.0f, 4.0f);
            pathBuilder.horizontalLineToRelative(-1.0f);
            pathBuilder.lineTo(18.0f, 2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(8.0f, 4.0f);
            pathBuilder.lineTo(8.0f, 2.0f);
            pathBuilder.lineTo(6.0f, 2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(5.0f, 4.0f);
            pathBuilder.curveToRelative(-1.11f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
            pathBuilder.lineTo(3.0f, 20.0f);
            pathBuilder.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
            pathBuilder.horizontalLineToRelative(14.0f);
            pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
            pathBuilder.lineTo(21.0f, 6.0f);
            pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(19.0f, 20.0f);
            pathBuilder.lineTo(5.0f, 20.0f);
            pathBuilder.lineTo(5.0f, 9.0f);
            pathBuilder.horizontalLineToRelative(14.0f);
            pathBuilder.verticalLineToRelative(11.0f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _dateRange = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }

        public final ImageVector getEdit$material3() {
            ImageVector imageVector = _edit;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Filled.Edit", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(3.0f, 17.25f);
            pathBuilder.verticalLineTo(21.0f);
            pathBuilder.horizontalLineToRelative(3.75f);
            pathBuilder.lineTo(17.81f, 9.94f);
            pathBuilder.lineToRelative(-3.75f, -3.75f);
            pathBuilder.lineTo(3.0f, 17.25f);
            pathBuilder.close();
            pathBuilder.moveTo(20.71f, 7.04f);
            pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
            pathBuilder.lineToRelative(-2.34f, -2.34f);
            pathBuilder.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
            pathBuilder.lineToRelative(-1.83f, 1.83f);
            pathBuilder.lineToRelative(3.75f, 3.75f);
            pathBuilder.lineToRelative(1.83f, -1.83f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _edit = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/compose/material3/internal/Icons$Outlined;", "", "<init>", "()V", "Schedule", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getSchedule", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "_schedule", "Keyboard", "getKeyboard", "_keyboard", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Outlined {
        private static ImageVector _keyboard;
        private static ImageVector _schedule;
        public static final Outlined INSTANCE = new Outlined();
        public static final int $stable = 8;

        private Outlined() {
        }

        public final ImageVector getKeyboard() {
            ImageVector imageVector = _keyboard;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Outlined.Keyboard", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(20.0f, 7.0f);
            pathBuilder.verticalLineToRelative(10.0f);
            pathBuilder.lineTo(4.0f, 17.0f);
            pathBuilder.lineTo(4.0f, 7.0f);
            pathBuilder.horizontalLineToRelative(16.0f);
            pathBuilder.moveToRelative(0.0f, -2.0f);
            pathBuilder.lineTo(4.0f, 5.0f);
            pathBuilder.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
            pathBuilder.lineTo(2.0f, 17.0f);
            pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
            pathBuilder.horizontalLineToRelative(16.0f);
            pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
            pathBuilder.lineTo(22.0f, 7.0f);
            pathBuilder.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(11.0f, 8.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(11.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(8.0f, 8.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(8.0f, 10.0f);
            pathBuilder.close();
            pathBuilder.moveTo(8.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(8.0f, 13.0f);
            pathBuilder.close();
            pathBuilder.moveTo(5.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(5.0f, 13.0f);
            pathBuilder.close();
            pathBuilder.moveTo(5.0f, 8.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(5.0f, 10.0f);
            pathBuilder.close();
            pathBuilder.moveTo(8.0f, 14.0f);
            pathBuilder.horizontalLineToRelative(8.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.lineTo(8.0f, 16.0f);
            pathBuilder.close();
            pathBuilder.moveTo(14.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(14.0f, 8.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(17.0f, 11.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(17.0f, 8.0f);
            pathBuilder.horizontalLineToRelative(2.0f);
            pathBuilder.verticalLineToRelative(2.0f);
            pathBuilder.horizontalLineToRelative(-2.0f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _keyboard = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }

        public final ImageVector getSchedule() {
            ImageVector imageVector = _schedule;
            if (imageVector != null) {
                imageVector.getClass();
                return imageVector;
            }
            ImageVector.Builder builder = new ImageVector.Builder("Outlined.Schedule", Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, BERTags.FLAGS, null);
            int defaultFillType = VectorKt.getDefaultFillType();
            SolidColor solidColor = new SolidColor(Color.INSTANCE.m3160getBlack0d7_KjU(), null);
            int iM3508getButtKaPHkGw = StrokeCap.INSTANCE.m3508getButtKaPHkGw();
            int iM3518getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3518getBevelLxFBmk8();
            PathBuilder pathBuilder = new PathBuilder();
            pathBuilder.moveTo(11.99f, 2.0f);
            pathBuilder.curveTo(6.47f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
            pathBuilder.reflectiveCurveToRelative(4.47f, 10.0f, 9.99f, 10.0f);
            pathBuilder.curveTo(17.52f, 22.0f, 22.0f, 17.52f, 22.0f, 12.0f);
            pathBuilder.reflectiveCurveTo(17.52f, 2.0f, 11.99f, 2.0f);
            pathBuilder.close();
            pathBuilder.moveTo(12.0f, 20.0f);
            pathBuilder.curveToRelative(-4.42f, 0.0f, -8.0f, -3.58f, -8.0f, -8.0f);
            pathBuilder.reflectiveCurveToRelative(3.58f, -8.0f, 8.0f, -8.0f);
            pathBuilder.reflectiveCurveToRelative(8.0f, 3.58f, 8.0f, 8.0f);
            pathBuilder.reflectiveCurveToRelative(-3.58f, 8.0f, -8.0f, 8.0f);
            pathBuilder.close();
            pathBuilder.moveTo(12.5f, 7.0f);
            pathBuilder.lineTo(11.0f, 7.0f);
            pathBuilder.verticalLineToRelative(6.0f);
            pathBuilder.lineToRelative(5.25f, 3.15f);
            pathBuilder.lineToRelative(0.75f, -1.23f);
            pathBuilder.lineToRelative(-4.5f, -2.67f);
            pathBuilder.close();
            ImageVector imageVectorBuild = ImageVector.Builder.m3865addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3508getButtKaPHkGw, iM3518getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
            _schedule = imageVectorBuild;
            imageVectorBuild.getClass();
            return imageVectorBuild;
        }
    }

    private Icons() {
    }
}
