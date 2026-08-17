package androidx.compose.foundation.text.modifiers;

import android.os.Trace;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.text.modifiers.TextStringSimpleNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001PBS\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\f\u0010$\u001a\u00020 *\u00020%H\u0002J\r\u0010\"\u001a\u00020 H\u0003¢\u0006\u0002\b&J\u0018\u0010'\u001a\u00020\u000e2\b\u0010(\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010)\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006J=\u0010*\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b+\u0010,J\u001e\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\u000eJ\u0010\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020\u0006H\u0002J\b\u0010:\u001a\u00020.H\u0002J\f\u0010;\u001a\u00020.*\u00020<H\u0016J\b\u0010=\u001a\u00020.H\u0002J#\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0016¢\u0006\u0004\bE\u0010FJ\u001c\u0010G\u001a\u00020\u0010*\u00020%2\u0006\u0010A\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0010H\u0016J\u001c\u0010J\u001a\u00020\u0010*\u00020%2\u0006\u0010A\u001a\u00020H2\u0006\u0010K\u001a\u00020\u0010H\u0016J\u001c\u0010L\u001a\u00020\u0010*\u00020%2\u0006\u0010A\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0010H\u0016J\u001c\u0010M\u001a\u00020\u0010*\u00020%2\u0006\u0010A\u001a\u00020H2\u0006\u0010K\u001a\u00020\u0010H\u0016J\f\u0010N\u001a\u00020.*\u00020OH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001bX\u0082\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001d\u0010\u001eR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\"\u00102\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020504\u0012\u0004\u0012\u00020\u000e\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "softWrap", "", "maxLines", "", "minLines", "overrideColor", "Landroidx/compose/ui/graphics/ColorProducer;", "<init>", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;IZIILandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "I", "shouldAutoInvalidate", "getShouldAutoInvalidate", "()Z", "baselineCache", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getBaselineCache$annotations", "()V", "_layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "layoutCache", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "getLayoutCacheForMeasure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "getLayoutCacheOrSubstitute", "updateDraw", "color", "updateText", "updateLayoutRelatedArgs", "updateLayoutRelatedArgs-HuAbxIM", "(Landroidx/compose/ui/text/TextStyle;IIZLandroidx/compose/ui/text/font/FontFamily$Resolver;I)Z", "doInvalidations", "", "drawChanged", "textChanged", "layoutChanged", "semanticsTextLayoutResult", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/TextLayoutResult;", "textSubstitution", "Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "setSubstitution", "updatedText", "clearSubstitution", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "invalidateForTranslate", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "TextSubstitutionValue", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextStringSimpleNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode {
    public static final int $stable = 8;
    private ParagraphLayoutCache _layoutCache;
    private Map<AlignmentLine, Integer> baselineCache;
    private FontFamily.Resolver fontFamilyResolver;
    private int maxLines;
    private int minLines;
    private int overflow;
    private ColorProducer overrideColor;
    private Function1<? super List<TextLayoutResult>, Boolean> semanticsTextLayoutResult;
    private boolean softWrap;
    private TextStyle style;
    private String text;
    private TextSubstitutionValue textSubstitution;

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, (i4 & 8) != 0 ? TextOverflow.Companion.getClip-gIe3tQ8() : i, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? Integer.MAX_VALUE : i2, (i4 & 64) != 0 ? 1 : i3, (i4 & 128) != 0 ? null : colorProducer, null);
    }

    public static boolean a(TextStringSimpleNode textStringSimpleNode, boolean z) {
        TextSubstitutionValue textSubstitutionValue = textStringSimpleNode.textSubstitution;
        if (textSubstitutionValue == null) {
            return false;
        }
        textSubstitutionValue.setShowingSubstitution(z);
        textStringSimpleNode.invalidateForTranslate();
        return true;
    }

    public static boolean b(TextStringSimpleNode textStringSimpleNode) {
        textStringSimpleNode.clearSubstitution();
        textStringSimpleNode.invalidateForTranslate();
        return true;
    }

    private final void clearSubstitution() {
        this.textSubstitution = null;
    }

    public static boolean d(TextStringSimpleNode textStringSimpleNode, AnnotatedString annotatedString) {
        textStringSimpleNode.setSubstitution(annotatedString.getText());
        textStringSimpleNode.invalidateForTranslate();
        return true;
    }

    public static boolean e(TextStringSimpleNode textStringSimpleNode, List list) {
        ParagraphLayoutCache layoutCache = textStringSimpleNode.getLayoutCache();
        TextStyle textStyle = textStringSimpleNode.style;
        ColorProducer colorProducer = textStringSimpleNode.overrideColor;
        TextLayoutResult textLayoutResultSlowCreateTextLayoutResultOrNull = layoutCache.slowCreateTextLayoutResultOrNull(TextStyle.merge-dA7vx0o$default(textStyle, colorProducer != null ? colorProducer.invoke-0d7_KjU() : Color.Companion.getUnspecified-0d7_KjU(), 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (LineHeightStyle) null, 0, 0, (PlatformTextStyle) null, (TextMotion) null, 16777214, (Object) null));
        if (textLayoutResultSlowCreateTextLayoutResultOrNull != null) {
            list.add(textLayoutResultSlowCreateTextLayoutResultOrNull);
        } else {
            textLayoutResultSlowCreateTextLayoutResultOrNull = null;
        }
        return textLayoutResultSlowCreateTextLayoutResultOrNull != null;
    }

    private static /* synthetic */ void getBaselineCache$annotations() {
    }

    private final ParagraphLayoutCache getLayoutCache() {
        if (this._layoutCache == null) {
            this._layoutCache = new ParagraphLayoutCache(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        }
        ParagraphLayoutCache paragraphLayoutCache = this._layoutCache;
        paragraphLayoutCache.getClass();
        return paragraphLayoutCache;
    }

    private final ParagraphLayoutCache getLayoutCacheForMeasure(IntrinsicMeasureScope intrinsicMeasureScope) {
        ParagraphLayoutCache layoutCacheOrSubstitute = getLayoutCacheOrSubstitute();
        layoutCacheOrSubstitute.setDensity$foundation(intrinsicMeasureScope);
        return layoutCacheOrSubstitute;
    }

    private final ParagraphLayoutCache getLayoutCacheOrSubstitute() {
        ParagraphLayoutCache layoutCache;
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            if (!textSubstitutionValue.isShowingSubstitution()) {
                textSubstitutionValue = null;
            }
            if (textSubstitutionValue != null && (layoutCache = textSubstitutionValue.getLayoutCache()) != null) {
                return layoutCache;
            }
        }
        return getLayoutCache();
    }

    private final void invalidateForTranslate() {
        SemanticsModifierNodeKt.invalidateSemantics(this);
        LayoutModifierNodeKt.invalidateMeasurement(this);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure_3p2s80s$lambda$0$0(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    private final boolean setSubstitution(String updatedText) {
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            if (Intrinsics.areEqual(updatedText, textSubstitutionValue.getSubstitution())) {
                return false;
            }
            textSubstitutionValue.setSubstitution(updatedText);
            ParagraphLayoutCache layoutCache = textSubstitutionValue.getLayoutCache();
            if (layoutCache == null) {
                return false;
            }
            layoutCache.m1724updateL6sJoHM(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
            return true;
        }
        TextSubstitutionValue textSubstitutionValue2 = new TextSubstitutionValue(this.text, updatedText, false, null, 12, null);
        ParagraphLayoutCache paragraphLayoutCache = new ParagraphLayoutCache(updatedText, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines, null);
        paragraphLayoutCache.setDensity$foundation(getLayoutCache().getDensity());
        textSubstitutionValue2.setLayoutCache(paragraphLayoutCache);
        this.textSubstitution = textSubstitutionValue2;
        return true;
    }

    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        Function1<? super List<TextLayoutResult>, Boolean> function1 = this.semanticsTextLayoutResult;
        if (function1 == null) {
            function1 = new Function1() { // from class: ace
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(TextStringSimpleNode.e(this.b, (List) obj));
                }
            };
            this.semanticsTextLayoutResult = function1;
        }
        SemanticsPropertiesKt.setText(semanticsPropertyReceiver, new AnnotatedString(this.text, (List) null, 2, (DefaultConstructorMarker) null));
        TextSubstitutionValue textSubstitutionValue = this.textSubstitution;
        if (textSubstitutionValue != null) {
            SemanticsPropertiesKt.setShowingTextSubstitution(semanticsPropertyReceiver, textSubstitutionValue.isShowingSubstitution());
            SemanticsPropertiesKt.setTextSubstitution(semanticsPropertyReceiver, new AnnotatedString(textSubstitutionValue.getSubstitution(), (List) null, 2, (DefaultConstructorMarker) null));
        }
        SemanticsPropertiesKt.setTextSubstitution$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: bce
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextStringSimpleNode.d(this.b, (AnnotatedString) obj));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.showTextSubstitution$default(semanticsPropertyReceiver, (String) null, new Function1() { // from class: cce
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextStringSimpleNode.a(this.b, ((Boolean) obj).booleanValue()));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.clearTextSubstitution$default(semanticsPropertyReceiver, (String) null, new Function0() { // from class: dce
            public final Object invoke() {
                return Boolean.valueOf(TextStringSimpleNode.b(this.b));
            }
        }, 1, (Object) null);
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, (String) null, function1, 1, (Object) null);
    }

    public final void doInvalidations(boolean drawChanged, boolean textChanged, boolean layoutChanged) {
        if (textChanged || layoutChanged) {
            getLayoutCache().m1724updateL6sJoHM(this.text, this.style, this.fontFamilyResolver, this.overflow, this.softWrap, this.maxLines, this.minLines);
        }
        if (isAttached()) {
            if (textChanged || (drawChanged && this.semanticsTextLayoutResult != null)) {
                SemanticsModifierNodeKt.invalidateSemantics(this);
            }
            if (textChanged || layoutChanged) {
                LayoutModifierNodeKt.invalidateMeasurement(this);
                DrawModifierNodeKt.invalidateDraw(this);
            }
            if (drawChanged) {
                DrawModifierNodeKt.invalidateDraw(this);
            }
        }
    }

    public void draw(ContentDrawScope contentDrawScope) {
        if (isAttached()) {
            ParagraphLayoutCache layoutCacheOrSubstitute = getLayoutCacheOrSubstitute();
            Paragraph paragraph = layoutCacheOrSubstitute.getParagraph();
            if (paragraph == null) {
                InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("Internal Error: ParagraphLayoutCache could not provide a Paragraph during the draw phase. Please report this bug on the official Issue Tracker with the following diagnostic information: (layoutCache=" + this._layoutCache + ", textSubstitution=" + this.textSubstitution + ')');
                wq6.a();
                return;
            }
            Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
            boolean didOverflow = layoutCacheOrSubstitute.getDidOverflow();
            if (didOverflow) {
                float layoutSize = (int) (layoutCacheOrSubstitute.getLayoutSize() >> 32);
                float layoutSize2 = (int) (layoutCacheOrSubstitute.getLayoutSize() & 4294967295L);
                canvas.save();
                Canvas.clipRect-N_I0leg$default(canvas, 0.0f, 0.0f, layoutSize, layoutSize2, 0, 16, (Object) null);
            }
            try {
                TextDecoration textDecoration = this.style.getTextDecoration();
                if (textDecoration == null) {
                    textDecoration = TextDecoration.Companion.getNone();
                }
                TextDecoration textDecoration2 = textDecoration;
                Shadow shadow = this.style.getShadow();
                if (shadow == null) {
                    shadow = Shadow.Companion.getNone();
                }
                Shadow shadow2 = shadow;
                Fill drawStyle = this.style.getDrawStyle();
                if (drawStyle == null) {
                    drawStyle = Fill.INSTANCE;
                }
                Fill fill = drawStyle;
                Brush brush = this.style.getBrush();
                if (brush != null) {
                    Paragraph.paint-hn5TExg$default(paragraph, canvas, brush, this.style.getAlpha(), shadow2, textDecoration2, fill, 0, 64, (Object) null);
                } else {
                    ColorProducer colorProducer = this.overrideColor;
                    long j = colorProducer != null ? colorProducer.invoke-0d7_KjU() : Color.Companion.getUnspecified-0d7_KjU();
                    if (j == 16) {
                        j = this.style.getColor-0d7_KjU() != 16 ? this.style.getColor-0d7_KjU() : Color.Companion.getBlack-0d7_KjU();
                    }
                    Paragraph.paint-LG529CI$default(paragraph, canvas, j, shadow2, textDecoration2, fill, 0, 32, (Object) null);
                }
            } finally {
                if (didOverflow) {
                    canvas.restore();
                }
            }
        }
    }

    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCacheForMeasure(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCacheForMeasure(intrinsicMeasureScope).maxIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m1733measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        Trace.beginSection("TextStringSimpleNode::measure");
        try {
            ParagraphLayoutCache layoutCacheForMeasure = getLayoutCacheForMeasure(measureScope);
            boolean zM1722layoutWithConstraintsK40F9xA = layoutCacheForMeasure.m1722layoutWithConstraintsK40F9xA(j, measureScope.getLayoutDirection());
            layoutCacheForMeasure.getObserveFontChanges$foundation();
            Paragraph paragraph = layoutCacheForMeasure.getParagraph();
            paragraph.getClass();
            long layoutSize = layoutCacheForMeasure.getLayoutSize();
            if (zM1722layoutWithConstraintsK40F9xA) {
                LayoutModifierNodeKt.invalidateLayer(this);
                Map map = this.baselineCache;
                if (map == null) {
                    map = new HashMap(2);
                    this.baselineCache = map;
                }
                map.put(AlignmentLineKt.getFirstBaseline(), Integer.valueOf(Math.round(paragraph.getFirstBaseline())));
                map.put(AlignmentLineKt.getLastBaseline(), Integer.valueOf(Math.round(paragraph.getLastBaseline())));
            }
            int i = (int) (layoutSize >> 32);
            int i2 = (int) (layoutSize & 4294967295L);
            final Placeable placeable = measurable.measure-BRTryo0(Constraints.Companion.fitPrioritizingWidth-Zbe2FdA(i, i, i2, i2));
            Map<AlignmentLine, Integer> map2 = this.baselineCache;
            map2.getClass();
            return measureScope.layout(i, i2, map2, new Function1() { // from class: ece
                public final Object invoke(Object obj) {
                    return TextStringSimpleNode.measure_3p2s80s$lambda$0$0(placeable, (Placeable.PlacementScope) obj);
                }
            });
        } finally {
            Trace.endSection();
        }
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCacheForMeasure(intrinsicMeasureScope).intrinsicHeight(i, intrinsicMeasureScope.getLayoutDirection());
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return getLayoutCacheForMeasure(intrinsicMeasureScope).minIntrinsicWidth(intrinsicMeasureScope.getLayoutDirection());
    }

    public final boolean updateDraw(ColorProducer color, TextStyle style) {
        boolean zAreEqual = Intrinsics.areEqual(color, this.overrideColor);
        this.overrideColor = color;
        return (zAreEqual && style.hasSameDrawAffectingAttributes(this.style)) ? false : true;
    }

    /* JADX INFO: renamed from: updateLayoutRelatedArgs-HuAbxIM, reason: not valid java name */
    public final boolean m1734updateLayoutRelatedArgsHuAbxIM(TextStyle style, int minLines, int maxLines, boolean softWrap, FontFamily.Resolver fontFamilyResolver, int overflow) {
        boolean z = !this.style.hasSameLayoutAffectingAttributes(style);
        this.style = style;
        if (this.minLines != minLines) {
            this.minLines = minLines;
            z = true;
        }
        if (this.maxLines != maxLines) {
            this.maxLines = maxLines;
            z = true;
        }
        if (this.softWrap != softWrap) {
            this.softWrap = softWrap;
            z = true;
        }
        if (!Intrinsics.areEqual(this.fontFamilyResolver, fontFamilyResolver)) {
            this.fontFamilyResolver = fontFamilyResolver;
            z = true;
        }
        if (TextOverflow.equals-impl0(this.overflow, overflow)) {
            return z;
        }
        this.overflow = overflow;
        return true;
    }

    public final boolean updateText(String text) {
        if (Intrinsics.areEqual(this.text, text)) {
            return false;
        }
        this.text = text;
        clearSubstitution();
        return true;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0017\u001a\u00020\u0003H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\f\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006!"}, d2 = {"Landroidx/compose/foundation/text/modifiers/TextStringSimpleNode$TextSubstitutionValue;", "", "original", "", "substitution", "isShowingSubstitution", "", "layoutCache", "Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "getOriginal", "()Ljava/lang/String;", "getSubstitution", "setSubstitution", "(Ljava/lang/String;)V", "()Z", "setShowingSubstitution", "(Z)V", "getLayoutCache", "()Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;", "setLayoutCache", "(Landroidx/compose/foundation/text/modifiers/ParagraphLayoutCache;)V", "toString", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class TextSubstitutionValue {
        public static final int $stable = 8;
        private boolean isShowingSubstitution;
        private ParagraphLayoutCache layoutCache;
        private final String original;
        private String substitution;

        public /* synthetic */ TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : paragraphLayoutCache);
        }

        public static /* synthetic */ TextSubstitutionValue copy$default(TextSubstitutionValue textSubstitutionValue, String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache, int i, Object obj) {
            if ((i & 1) != 0) {
                str = textSubstitutionValue.original;
            }
            if ((i & 2) != 0) {
                str2 = textSubstitutionValue.substitution;
            }
            if ((i & 4) != 0) {
                z = textSubstitutionValue.isShowingSubstitution;
            }
            if ((i & 8) != 0) {
                paragraphLayoutCache = textSubstitutionValue.layoutCache;
            }
            return textSubstitutionValue.copy(str, str2, z, paragraphLayoutCache);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getOriginal() {
            return this.original;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSubstitution() {
            return this.substitution;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final TextSubstitutionValue copy(String original, String substitution, boolean isShowingSubstitution, ParagraphLayoutCache layoutCache) {
            return new TextSubstitutionValue(original, substitution, isShowingSubstitution, layoutCache);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TextSubstitutionValue)) {
                return false;
            }
            TextSubstitutionValue textSubstitutionValue = (TextSubstitutionValue) other;
            return Intrinsics.areEqual(this.original, textSubstitutionValue.original) && Intrinsics.areEqual(this.substitution, textSubstitutionValue.substitution) && this.isShowingSubstitution == textSubstitutionValue.isShowingSubstitution && Intrinsics.areEqual(this.layoutCache, textSubstitutionValue.layoutCache);
        }

        public final ParagraphLayoutCache getLayoutCache() {
            return this.layoutCache;
        }

        public final String getOriginal() {
            return this.original;
        }

        public final String getSubstitution() {
            return this.substitution;
        }

        public int hashCode() {
            int iHashCode = ((((this.original.hashCode() * 31) + this.substitution.hashCode()) * 31) + Boolean.hashCode(this.isShowingSubstitution)) * 31;
            ParagraphLayoutCache paragraphLayoutCache = this.layoutCache;
            return iHashCode + (paragraphLayoutCache == null ? 0 : paragraphLayoutCache.hashCode());
        }

        public final boolean isShowingSubstitution() {
            return this.isShowingSubstitution;
        }

        public final void setLayoutCache(ParagraphLayoutCache paragraphLayoutCache) {
            this.layoutCache = paragraphLayoutCache;
        }

        public final void setShowingSubstitution(boolean z) {
            this.isShowingSubstitution = z;
        }

        public final void setSubstitution(String str) {
            this.substitution = str;
        }

        public String toString() {
            return "TextSubstitution(layoutCache=" + this.layoutCache + ", isShowingSubstitution=" + this.isShowingSubstitution + ')';
        }

        public TextSubstitutionValue(String str, String str2, boolean z, ParagraphLayoutCache paragraphLayoutCache) {
            this.original = str;
            this.substitution = str2;
            this.isShowingSubstitution = z;
            this.layoutCache = paragraphLayoutCache;
        }
    }

    private TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer) {
        this.text = str;
        this.style = textStyle;
        this.fontFamilyResolver = resolver;
        this.overflow = i;
        this.softWrap = z;
        this.maxLines = i2;
        this.minLines = i3;
        this.overrideColor = colorProducer;
    }

    public /* synthetic */ TextStringSimpleNode(String str, TextStyle textStyle, FontFamily.Resolver resolver, int i, boolean z, int i2, int i3, ColorProducer colorProducer, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, textStyle, resolver, i, z, i2, i3, colorProducer);
    }
}
