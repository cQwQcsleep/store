package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.ShadowKt;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.FontWeightKt;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.BaselineShiftKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDrawStyleKt;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextGeometricTransformKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a'\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\u0006\u0010\n\u001a\u0002H\u00112\u0006\u0010\u000b\u001a\u0002H\u00112\u0006\u0010\u0012\u001a\u00020\rH\u0000¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\r\u001a&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00192\b\u0010\u0017\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0012\u001a\u00020\rH\u0002\u001a\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0015H\u0000\u001a½\u0001\u0010\u001c\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00052\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u00012\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020\u00012\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u00052\b\u00104\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u00010\u00192\b\u00109\u001a\u0004\u0018\u00010:H\u0000¢\u0006\u0004\b;\u0010<\u001a\u0018\u0010=\u001a\u0004\u0018\u00010\u0019*\u00020\u00152\b\u0010>\u001a\u0004\u0018\u00010\u0019H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"DefaultFontSize", "Landroidx/compose/ui/unit/TextUnit;", "J", "DefaultLetterSpacing", "DefaultBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "DefaultColor", "DefaultColorForegroundStyle", "Landroidx/compose/ui/text/style/TextForegroundStyle;", "lerpTextUnitInheritable", "a", "b", "t", "", "lerpTextUnitInheritable-C3pnCVY", "(JJF)J", "lerpDiscrete", "T", "fraction", "(Ljava/lang/Object;Ljava/lang/Object;F)Ljava/lang/Object;", "lerp", "Landroidx/compose/ui/text/SpanStyle;", "start", "stop", "lerpPlatformStyle", "Landroidx/compose/ui/text/PlatformSpanStyle;", "resolveSpanStyleDefaults", "style", "fastMerge", "color", "brush", "Landroidx/compose/ui/graphics/Brush;", "alpha", "fontSize", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "fontFamily", "Landroidx/compose/ui/text/font/FontFamily;", "fontFeatureSettings", "", "letterSpacing", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "textGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "localeList", "Landroidx/compose/ui/text/intl/LocaleList;", "background", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "platformStyle", "drawStyle", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "fastMerge-dSHsh3o", "(Landroidx/compose/ui/text/SpanStyle;JLandroidx/compose/ui/graphics/Brush;FJLandroidx/compose/ui/text/font/FontWeight;Landroidx/compose/ui/text/font/FontStyle;Landroidx/compose/ui/text/font/FontSynthesis;Landroidx/compose/ui/text/font/FontFamily;Ljava/lang/String;JLandroidx/compose/ui/text/style/BaselineShift;Landroidx/compose/ui/text/style/TextGeometricTransform;Landroidx/compose/ui/text/intl/LocaleList;JLandroidx/compose/ui/text/style/TextDecoration;Landroidx/compose/ui/graphics/Shadow;Landroidx/compose/ui/text/PlatformSpanStyle;Landroidx/compose/ui/graphics/drawscope/DrawStyle;)Landroidx/compose/ui/text/SpanStyle;", "mergePlatformStyle", "other", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SpanStyleKt {
    private static final long DefaultBackgroundColor;
    private static final long DefaultColor;
    private static final TextForegroundStyle DefaultColorForegroundStyle;
    private static final long DefaultFontSize = TextUnitKt.getSp(14);
    private static final long DefaultLetterSpacing = TextUnitKt.getSp(0);

    static {
        Color.Companion companion = Color.INSTANCE;
        DefaultBackgroundColor = companion.m3169getTransparent0d7_KjU();
        long jM3160getBlack0d7_KjU = companion.m3160getBlack0d7_KjU();
        DefaultColor = jM3160getBlack0d7_KjU;
        DefaultColorForegroundStyle = TextForegroundStyle.INSTANCE.m5927from8_81llA(jM3160getBlack0d7_KjU);
    }

    public static TextForegroundStyle a() {
        return DefaultColorForegroundStyle;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0183  */
    /* JADX WARN: Code duplicated, block: B:102:0x0187  */
    /* JADX WARN: Code duplicated, block: B:105:0x0193  */
    /* JADX WARN: Code duplicated, block: B:106:0x0198  */
    /* JADX WARN: Code duplicated, block: B:108:0x019c  */
    /* JADX WARN: Code duplicated, block: B:110:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:112:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:113:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:117:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:119:0x01ba  */
    /* JADX WARN: Code duplicated, block: B:11:0x003a A[PHI: r11
      0x003a: PHI (r11v7 long) = 
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v1 long)
      (r11v8 long)
     binds: [B:41:0x00ab, B:53:0x00dd, B:50:0x00d1, B:47:0x00c5, B:44:0x00b9, B:39:0x009d, B:34:0x008e, B:28:0x0076, B:25:0x006e, B:22:0x0062, B:19:0x0056, B:9:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:121:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:124:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:125:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:83:0x013e  */
    /* JADX WARN: Code duplicated, block: B:84:0x0147  */
    /* JADX WARN: Code duplicated, block: B:87:0x0157  */
    /* JADX WARN: Code duplicated, block: B:88:0x015c  */
    /* JADX WARN: Code duplicated, block: B:91:0x0166  */
    /* JADX WARN: Code duplicated, block: B:93:0x016c  */
    /* JADX WARN: Code duplicated, block: B:94:0x0171  */
    /* JADX WARN: Code duplicated, block: B:96:0x0175  */
    /* JADX WARN: Code duplicated, block: B:97:0x017a  */
    /* JADX WARN: Code duplicated, block: B:99:0x017e  */
    /* JADX INFO: renamed from: fastMerge-dSHsh3o, reason: not valid java name */
    public static final SpanStyle m5425fastMergedSHsh3o(SpanStyle spanStyle, long j, Brush brush, float f, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, PlatformSpanStyle platformSpanStyle, DrawStyle drawStyle) {
        long fontSize;
        TextGeometricTransform textGeometricTransform2;
        long background;
        DrawStyle drawStyle2;
        TextForegroundStyle textForegroundStyleM5927from8_81llA;
        FontFamily fontFamily2;
        FontWeight fontWeight2;
        FontStyle fontStyle2;
        FontSynthesis fontSynthesis2;
        long letterSpacing;
        LocaleList localeList2;
        DrawStyle drawStyle3;
        String fontFeatureSettings = str;
        BaselineShift baselineShift2 = baselineShift;
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow2 = shadow;
        if (!(TextUnit.m6214getRawTypeimpl(j2) == 0)) {
            fontSize = j2;
            if (!TextUnit.m6213equalsimpl0(fontSize, spanStyle.getFontSize())) {
                textGeometricTransform2 = textGeometricTransform;
                background = j4;
                drawStyle2 = drawStyle;
            }
            if (brush != null) {
                textForegroundStyleM5927from8_81llA = TextForegroundStyle.INSTANCE.from(brush, f);
            } else {
                textForegroundStyleM5927from8_81llA = TextForegroundStyle.INSTANCE.m5927from8_81llA(j);
            }
            TextForegroundStyle textForegroundStyleMerge = spanStyle.getTextForegroundStyle().merge(textForegroundStyleM5927from8_81llA);
            if (fontFamily == null) {
                fontFamily2 = spanStyle.getFontFamily();
            } else {
                fontFamily2 = fontFamily;
            }
            if (TextUnit.m6214getRawTypeimpl(fontSize) == 0) {
                fontSize = spanStyle.getFontSize();
            }
            if (fontWeight == null) {
                fontWeight2 = spanStyle.getFontWeight();
            } else {
                fontWeight2 = fontWeight;
            }
            if (fontStyle == null) {
                fontStyle2 = spanStyle.getFontStyle();
            } else {
                fontStyle2 = fontStyle;
            }
            if (fontSynthesis == null) {
                fontSynthesis2 = spanStyle.getFontSynthesis();
            } else {
                fontSynthesis2 = fontSynthesis;
            }
            if (fontFeatureSettings == null) {
                fontFeatureSettings = spanStyle.getFontFeatureSettings();
            }
            if (TextUnit.m6214getRawTypeimpl(j3) == 0) {
                letterSpacing = spanStyle.getLetterSpacing();
            } else {
                letterSpacing = j3;
            }
            if (baselineShift2 == null) {
                baselineShift2 = spanStyle.getBaselineShift();
            }
            if (textGeometricTransform2 == null) {
                textGeometricTransform2 = spanStyle.getTextGeometricTransform();
            }
            if (localeList == null) {
                localeList2 = spanStyle.getLocaleList();
            } else {
                localeList2 = localeList;
            }
            if (background == 16) {
                background = spanStyle.getBackground();
            }
            if (textDecoration2 == null) {
                textDecoration2 = spanStyle.getTextDecoration();
            }
            if (shadow2 == null) {
                shadow2 = spanStyle.getShadow();
            }
            Shadow shadow3 = shadow2;
            PlatformSpanStyle platformSpanStyleMergePlatformStyle = mergePlatformStyle(spanStyle, platformSpanStyle);
            if (drawStyle2 == null) {
                drawStyle3 = spanStyle.getDrawStyle();
            } else {
                drawStyle3 = drawStyle2;
            }
            return new SpanStyle(textForegroundStyleMerge, fontSize, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, fontFeatureSettings, letterSpacing, baselineShift2, textGeometricTransform2, localeList2, background, textDecoration2, shadow3, platformSpanStyleMergePlatformStyle, drawStyle3, (DefaultConstructorMarker) null);
        }
        fontSize = j2;
        if ((brush != null || j == 16 || Color.m3135equalsimpl0(j, spanStyle.getTextForegroundStyle().mo5780getColor0d7_KjU())) && ((fontStyle == null || Intrinsics.areEqual(fontStyle, spanStyle.getFontStyle())) && ((fontWeight == null || Intrinsics.areEqual(fontWeight, spanStyle.getFontWeight())) && (fontFamily == null || fontFamily == spanStyle.getFontFamily())))) {
            if ((TextUnit.m6214getRawTypeimpl(j3) == 0) || TextUnit.m6213equalsimpl0(j3, spanStyle.getLetterSpacing())) {
                if ((textDecoration2 == null || Intrinsics.areEqual(textDecoration2, spanStyle.getTextDecoration())) && Intrinsics.areEqual(brush, spanStyle.getTextForegroundStyle().getBrush()) && ((brush == null || f == spanStyle.getTextForegroundStyle().getAlpha()) && ((fontSynthesis == null || Intrinsics.areEqual(fontSynthesis, spanStyle.getFontSynthesis())) && ((fontFeatureSettings == null || Intrinsics.areEqual(fontFeatureSettings, spanStyle.getFontFeatureSettings())) && (baselineShift2 == null || Intrinsics.areEqual(baselineShift2, spanStyle.getBaselineShift())))))) {
                    if (textGeometricTransform != null) {
                        textGeometricTransform2 = textGeometricTransform;
                        if (Intrinsics.areEqual(textGeometricTransform2, spanStyle.getTextGeometricTransform())) {
                        }
                    } else {
                        textGeometricTransform2 = textGeometricTransform;
                    }
                    if (localeList == null || Intrinsics.areEqual(localeList, spanStyle.getLocaleList())) {
                        if (j4 != 16) {
                            background = j4;
                            if (Color.m3135equalsimpl0(background, spanStyle.getBackground())) {
                            }
                        } else {
                            background = j4;
                        }
                        if ((shadow2 == null || Intrinsics.areEqual(shadow2, spanStyle.getShadow())) && (platformSpanStyle == null || Intrinsics.areEqual(platformSpanStyle, spanStyle.getPlatformStyle()))) {
                            drawStyle2 = drawStyle;
                            if (drawStyle2 == null || Intrinsics.areEqual(drawStyle2, spanStyle.getDrawStyle())) {
                                return spanStyle;
                            }
                        }
                    }
                    drawStyle2 = drawStyle;
                } else {
                    textGeometricTransform2 = textGeometricTransform;
                }
                background = j4;
                drawStyle2 = drawStyle;
            } else {
                textGeometricTransform2 = textGeometricTransform;
                background = j4;
                drawStyle2 = drawStyle;
            }
        } else {
            textGeometricTransform2 = textGeometricTransform;
            background = j4;
            drawStyle2 = drawStyle;
        }
        if (brush != null) {
            textForegroundStyleM5927from8_81llA = TextForegroundStyle.INSTANCE.from(brush, f);
        } else {
            textForegroundStyleM5927from8_81llA = TextForegroundStyle.INSTANCE.m5927from8_81llA(j);
        }
        TextForegroundStyle textForegroundStyleMerge2 = spanStyle.getTextForegroundStyle().merge(textForegroundStyleM5927from8_81llA);
        if (fontFamily == null) {
            fontFamily2 = spanStyle.getFontFamily();
        } else {
            fontFamily2 = fontFamily;
        }
        if (TextUnit.m6214getRawTypeimpl(fontSize) == 0) {
            fontSize = spanStyle.getFontSize();
        }
        if (fontWeight == null) {
            fontWeight2 = spanStyle.getFontWeight();
        } else {
            fontWeight2 = fontWeight;
        }
        if (fontStyle == null) {
            fontStyle2 = spanStyle.getFontStyle();
        } else {
            fontStyle2 = fontStyle;
        }
        if (fontSynthesis == null) {
            fontSynthesis2 = spanStyle.getFontSynthesis();
        } else {
            fontSynthesis2 = fontSynthesis;
        }
        if (fontFeatureSettings == null) {
            fontFeatureSettings = spanStyle.getFontFeatureSettings();
        }
        if (TextUnit.m6214getRawTypeimpl(j3) == 0) {
            letterSpacing = spanStyle.getLetterSpacing();
        } else {
            letterSpacing = j3;
        }
        if (baselineShift2 == null) {
            baselineShift2 = spanStyle.getBaselineShift();
        }
        if (textGeometricTransform2 == null) {
            textGeometricTransform2 = spanStyle.getTextGeometricTransform();
        }
        if (localeList == null) {
            localeList2 = spanStyle.getLocaleList();
        } else {
            localeList2 = localeList;
        }
        if (background == 16) {
            background = spanStyle.getBackground();
        }
        if (textDecoration2 == null) {
            textDecoration2 = spanStyle.getTextDecoration();
        }
        if (shadow2 == null) {
            shadow2 = spanStyle.getShadow();
        }
        Shadow shadow4 = shadow2;
        PlatformSpanStyle platformSpanStyleMergePlatformStyle2 = mergePlatformStyle(spanStyle, platformSpanStyle);
        if (drawStyle2 == null) {
            drawStyle3 = spanStyle.getDrawStyle();
        } else {
            drawStyle3 = drawStyle2;
        }
        return new SpanStyle(textForegroundStyleMerge2, fontSize, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, fontFeatureSettings, letterSpacing, baselineShift2, textGeometricTransform2, localeList2, background, textDecoration2, shadow4, platformSpanStyleMergePlatformStyle2, drawStyle3, (DefaultConstructorMarker) null);
    }

    public static final SpanStyle lerp(SpanStyle spanStyle, SpanStyle spanStyle2, float f) {
        TextForegroundStyle textForegroundStyleLerp = TextDrawStyleKt.lerp(spanStyle.getTextForegroundStyle(), spanStyle2.getTextForegroundStyle(), f);
        FontFamily fontFamily = (FontFamily) lerpDiscrete(spanStyle.getFontFamily(), spanStyle2.getFontFamily(), f);
        long jM5426lerpTextUnitInheritableC3pnCVY = m5426lerpTextUnitInheritableC3pnCVY(spanStyle.getFontSize(), spanStyle2.getFontSize(), f);
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeight2 = spanStyle2.getFontWeight();
        if (fontWeight2 == null) {
            fontWeight2 = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeightLerp = FontWeightKt.lerp(fontWeight, fontWeight2, f);
        FontStyle fontStyle = (FontStyle) lerpDiscrete(spanStyle.getFontStyle(), spanStyle2.getFontStyle(), f);
        FontSynthesis fontSynthesis = (FontSynthesis) lerpDiscrete(spanStyle.getFontSynthesis(), spanStyle2.getFontSynthesis(), f);
        String str = (String) lerpDiscrete(spanStyle.getFontFeatureSettings(), spanStyle2.getFontFeatureSettings(), f);
        long jM5426lerpTextUnitInheritableC3pnCVY2 = m5426lerpTextUnitInheritableC3pnCVY(spanStyle.getLetterSpacing(), spanStyle2.getLetterSpacing(), f);
        BaselineShift baselineShift = spanStyle.getBaselineShift();
        float fM5768unboximpl = baselineShift != null ? baselineShift.m5768unboximpl() : BaselineShift.m5763constructorimpl(0.0f);
        BaselineShift baselineShift2 = spanStyle2.getBaselineShift();
        float fM5778lerpjWV1Mfo = BaselineShiftKt.m5778lerpjWV1Mfo(fM5768unboximpl, baselineShift2 != null ? baselineShift2.m5768unboximpl() : BaselineShift.m5763constructorimpl(0.0f), f);
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = spanStyle2.getTextGeometricTransform();
        if (textGeometricTransform2 == null) {
            textGeometricTransform2 = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransformLerp = TextGeometricTransformKt.lerp(textGeometricTransform, textGeometricTransform2, f);
        LocaleList localeList = (LocaleList) lerpDiscrete(spanStyle.getLocaleList(), spanStyle2.getLocaleList(), f);
        long jM3185lerpjxsXWHM = ColorKt.m3185lerpjxsXWHM(spanStyle.getBackground(), spanStyle2.getBackground(), f);
        TextDecoration textDecoration = (TextDecoration) lerpDiscrete(spanStyle.getTextDecoration(), spanStyle2.getTextDecoration(), f);
        Shadow shadow = spanStyle.getShadow();
        if (shadow == null) {
            shadow = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        Shadow shadow2 = spanStyle2.getShadow();
        if (shadow2 == null) {
            shadow2 = new Shadow(0L, 0L, 0.0f, 7, null);
        }
        return new SpanStyle(textForegroundStyleLerp, jM5426lerpTextUnitInheritableC3pnCVY, fontWeightLerp, fontStyle, fontSynthesis, fontFamily, str, jM5426lerpTextUnitInheritableC3pnCVY2, BaselineShift.m5762boximpl(fM5778lerpjWV1Mfo), textGeometricTransformLerp, localeList, jM3185lerpjxsXWHM, textDecoration, ShadowKt.lerp(shadow, shadow2, f), lerpPlatformStyle(spanStyle.getPlatformStyle(), spanStyle2.getPlatformStyle(), f), (DrawStyle) lerpDiscrete(spanStyle.getDrawStyle(), spanStyle2.getDrawStyle(), f), (DefaultConstructorMarker) null);
    }

    public static final <T> T lerpDiscrete(T t, T t2, float f) {
        return ((double) f) < 0.5d ? t : t2;
    }

    private static final PlatformSpanStyle lerpPlatformStyle(PlatformSpanStyle platformSpanStyle, PlatformSpanStyle platformSpanStyle2, float f) {
        if (platformSpanStyle == null && platformSpanStyle2 == null) {
            return null;
        }
        if (platformSpanStyle == null) {
            platformSpanStyle = PlatformSpanStyle.INSTANCE.getDefault();
        }
        if (platformSpanStyle2 == null) {
            platformSpanStyle2 = PlatformSpanStyle.INSTANCE.getDefault();
        }
        return AndroidTextStyle_androidKt.lerp(platformSpanStyle, platformSpanStyle2, f);
    }

    /* JADX INFO: renamed from: lerpTextUnitInheritable-C3pnCVY, reason: not valid java name */
    public static final long m5426lerpTextUnitInheritableC3pnCVY(long j, long j2, float f) {
        return (TextUnit.m6214getRawTypeimpl(j) == 0 || TextUnit.m6214getRawTypeimpl(j2) == 0) ? ((TextUnit) lerpDiscrete(TextUnit.m6206boximpl(j), TextUnit.m6206boximpl(j2), f)).getPackedValue() : TextUnitKt.m6236lerpC3pnCVY(j, j2, f);
    }

    private static final PlatformSpanStyle mergePlatformStyle(SpanStyle spanStyle, PlatformSpanStyle platformSpanStyle) {
        if (spanStyle.getPlatformStyle() == null) {
            return platformSpanStyle;
        }
        return platformSpanStyle == null ? spanStyle.getPlatformStyle() : spanStyle.getPlatformStyle().merge(platformSpanStyle);
    }

    public static final SpanStyle resolveSpanStyleDefaults(SpanStyle spanStyle) {
        TextForegroundStyle textForegroundStyleTakeOrElse = spanStyle.getTextForegroundStyle().takeOrElse(new Function0() { // from class: yjd
            public final Object invoke() {
                return SpanStyleKt.a();
            }
        });
        long fontSize = TextUnit.m6214getRawTypeimpl(spanStyle.getFontSize()) == 0 ? DefaultFontSize : spanStyle.getFontSize();
        FontWeight fontWeight = spanStyle.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        FontWeight fontWeight2 = fontWeight;
        FontStyle fontStyle = spanStyle.getFontStyle();
        FontStyle fontStyleM5588boximpl = FontStyle.m5588boximpl(fontStyle != null ? fontStyle.m5594unboximpl() : FontStyle.INSTANCE.m5598getNormal_LCdwA());
        FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
        FontSynthesis fontSynthesisM5599boximpl = FontSynthesis.m5599boximpl(fontSynthesis != null ? fontSynthesis.m5607unboximpl() : FontSynthesis.INSTANCE.m5608getAllGVVA2EU());
        FontFamily fontFamily = spanStyle.getFontFamily();
        if (fontFamily == null) {
            fontFamily = FontFamily.INSTANCE.getDefault();
        }
        FontFamily fontFamily2 = fontFamily;
        String fontFeatureSettings = spanStyle.getFontFeatureSettings();
        if (fontFeatureSettings == null) {
            fontFeatureSettings = "";
        }
        String str = fontFeatureSettings;
        long letterSpacing = TextUnit.m6214getRawTypeimpl(spanStyle.getLetterSpacing()) == 0 ? DefaultLetterSpacing : spanStyle.getLetterSpacing();
        BaselineShift baselineShift = spanStyle.getBaselineShift();
        float fM5768unboximpl = baselineShift != null ? baselineShift.m5768unboximpl() : BaselineShift.INSTANCE.m5773getNoney9eOQZs();
        if (Float.isNaN(fM5768unboximpl)) {
            fM5768unboximpl = BaselineShift.INSTANCE.m5773getNoney9eOQZs();
        }
        BaselineShift baselineShiftM5762boximpl = BaselineShift.m5762boximpl(fM5768unboximpl);
        TextGeometricTransform textGeometricTransform = spanStyle.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.INSTANCE.getNone$ui_text();
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = spanStyle.getLocaleList();
        if (localeList == null) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        LocaleList localeList2 = localeList;
        long background = spanStyle.getBackground();
        if (background == 16) {
            background = DefaultBackgroundColor;
        }
        long j = background;
        TextDecoration textDecoration = spanStyle.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.INSTANCE.getNone();
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = spanStyle.getShadow();
        if (shadow == null) {
            shadow = Shadow.INSTANCE.getNone();
        }
        Shadow shadow2 = shadow;
        PlatformSpanStyle platformStyle = spanStyle.getPlatformStyle();
        DrawStyle drawStyle = spanStyle.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        return new SpanStyle(textForegroundStyleTakeOrElse, fontSize, fontWeight2, fontStyleM5588boximpl, fontSynthesisM5599boximpl, fontFamily2, str, letterSpacing, baselineShiftM5762boximpl, textGeometricTransform2, localeList2, j, textDecoration2, shadow2, platformStyle, drawStyle, (DefaultConstructorMarker) null);
    }
}
