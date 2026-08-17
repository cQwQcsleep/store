package androidx.compose.foundation.text;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.text.TextLinkScope;
import androidx.compose.foundation.text.TextRangeLayoutMeasureResult;
import androidx.compose.foundation.text.TextRangeLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.UriHandler;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.LinkAnnotation;
import androidx.compose.ui.text.LinkInteractionListener;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextLinkStyles;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u001f\u001a\u00020 *\u00020 2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001e\u0010%\u001a\u00020 *\u00020 2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001c\u0010&\u001a\u0004\u0018\u00010'2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J\u001c\u0010(\u001a\u0004\u0018\u00010)2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$H\u0002J0\u0010*\u001a\u0010\u0012\u0004\u0012\u00020#\u0018\u00010\"j\u0004\u0018\u0001`$2\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"j\u0002`$2\u0006\u0010\n\u001a\u00020\tH\u0002J\r\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010,J\u001a\u0010-\u001a\u0004\u0018\u00010.*\u0004\u0018\u00010.2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002J\u0018\u00100\u001a\u00020\u00182\u0006\u0010!\u001a\u00020#2\u0006\u00101\u001a\u000202H\u0002J\r\u00103\u001a\u00020\u0003H\u0000¢\u0006\u0002\b4J>\u00105\u001a\u00020\u00182\u0016\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u000107\"\u0004\u0018\u00010\u00012\u0017\u00108\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0002\u00109R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R/\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0011\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0007\"\u0004\b\u0013\u0010\u0005R%\u0010\u0014\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u00190\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006:"}, d2 = {"Landroidx/compose/foundation/text/TextLinkScope;", "", "initialText", "Landroidx/compose/ui/text/AnnotatedString;", "<init>", "(Landroidx/compose/ui/text/AnnotatedString;)V", "getInitialText$foundation", "()Landroidx/compose/ui/text/AnnotatedString;", "<set-?>", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "getTextLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "setTextLayoutResult", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "textLayoutResult$delegate", "Landroidx/compose/runtime/MutableState;", "text", "getText$foundation", "setText$foundation", "annotators", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Lkotlin/Function1;", "Landroidx/compose/foundation/text/TextAnnotatorScope;", "", "Lkotlin/ExtensionFunctionType;", "shouldMeasureLinks", "Lkotlin/Function0;", "", "getShouldMeasureLinks", "()Lkotlin/jvm/functions/Function0;", "textRange", "Landroidx/compose/ui/Modifier;", "link", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/LinkAnnotation;", "Landroidx/compose/foundation/text/LinkRange;", "clipLink", "shapeForRange", "Landroidx/compose/ui/graphics/Shape;", "pathForRangeInRangeCoordinates", "Landroidx/compose/ui/graphics/Path;", "calculateVisibleLinkRange", "LinksComposables", "(Landroidx/compose/runtime/Composer;I)V", "mergeOrUse", "Landroidx/compose/ui/text/SpanStyle;", "other", "handleLink", "uriHandler", "Landroidx/compose/ui/platform/UriHandler;", "applyAnnotators", "applyAnnotators$foundation", "StyleAnnotation", "keys", "", "block", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextLinkScope {
    public static final int $stable = 8;
    private final AnnotatedString initialText;
    private AnnotatedString text;

    /* JADX INFO: renamed from: textLayoutResult$delegate, reason: from kotlin metadata */
    private final MutableState textLayoutResult = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
    private final SnapshotStateList<Function1<TextAnnotatorScope, Unit>> annotators = SnapshotStateKt.mutableStateListOf();

    public TextLinkScope(AnnotatedString annotatedString) {
        this.initialText = annotatedString;
        this.text = annotatedString.flatMapAnnotations(new Function1() { // from class: nbe
            public final Object invoke(Object obj) {
                return TextLinkScope.i((AnnotatedString.Range) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$1$0(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertyKey linkTestMarker = SemanticsProperties.INSTANCE.getLinkTestMarker();
        Unit unit = Unit.INSTANCE;
        semanticsPropertyReceiver.set(linkTestMarker, unit);
        return unit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$2$0(TextLinkScope textLinkScope, AnnotatedString.Range range, UriHandler uriHandler) {
        textLinkScope.handleLink((LinkAnnotation) range.getItem(), uriHandler);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LinksComposables$lambda$0$5$0(TextLinkScope textLinkScope, AnnotatedString.Range range, LinkStateInteractionSourceObserver linkStateInteractionSourceObserver, TextAnnotatorScope textAnnotatorScope) {
        TextLinkStyles styles;
        TextLinkStyles styles2;
        TextLinkStyles styles3;
        TextLinkStyles styles4 = ((LinkAnnotation) range.getItem()).getStyles();
        SpanStyle pressedStyle = null;
        SpanStyle spanStyleMergeOrUse = textLinkScope.mergeOrUse(textLinkScope.mergeOrUse(styles4 != null ? styles4.getStyle() : null, (!linkStateInteractionSourceObserver.isFocused() || (styles3 = ((LinkAnnotation) range.getItem()).getStyles()) == null) ? null : styles3.getFocusedStyle()), (!linkStateInteractionSourceObserver.isHovered() || (styles2 = ((LinkAnnotation) range.getItem()).getStyles()) == null) ? null : styles2.getHoveredStyle());
        if (linkStateInteractionSourceObserver.isPressed() && (styles = ((LinkAnnotation) range.getItem()).getStyles()) != null) {
            pressedStyle = styles.getPressedStyle();
        }
        textAnnotatorScope.replaceStyle(range, textLinkScope.mergeOrUse(spanStyleMergeOrUse, pressedStyle));
        return Unit.INSTANCE;
    }

    private final void StyleAnnotation(final Object[] objArr, final Function1<? super TextAnnotatorScope, Unit> function1, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-2083052099);
        int i2 = (i & 48) == 0 ? (composerStartRestartGroup.changedInstance(function1) ? 32 : 16) | i : i;
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(this) ? 256 : 128;
        }
        composerStartRestartGroup.startMovableGroup(-358306546, Integer.valueOf(objArr.length));
        int i3 = i2 | (composerStartRestartGroup.changed(objArr.length) ? 4 : 0);
        for (Object obj : objArr) {
            i3 |= composerStartRestartGroup.changedInstance(obj) ? 4 : 0;
        }
        composerStartRestartGroup.endMovableGroup();
        if ((i3 & 14) == 0) {
            i3 |= 2;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2083052099, i3, -1, "androidx.compose.foundation.text.TextLinkScope.StyleAnnotation (TextLinkScope.kt:315)");
            }
            SpreadBuilder spreadBuilder = new SpreadBuilder(2);
            spreadBuilder.add(function1);
            spreadBuilder.addSpread(objArr);
            Object[] array = spreadBuilder.toArray(new Object[spreadBuilder.size()]);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(this) | ((i3 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: lbe
                    public final Object invoke(Object obj2) {
                        return TextLinkScope.StyleAnnotation$lambda$0$0(this.b, function1, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.DisposableEffect(array, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: mbe
                public final Object invoke(Object obj2, Object obj3) {
                    return TextLinkScope.h(this.b, objArr, function1, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult StyleAnnotation$lambda$0$0(final TextLinkScope textLinkScope, final Function1 function1, DisposableEffectScope disposableEffectScope) {
        textLinkScope.annotators.add(function1);
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.text.TextLinkScope$StyleAnnotation$lambda$0$0$$inlined$onDispose$1
            public void dispose() {
                this.this$0.annotators.remove(function1);
            }
        };
    }

    private final AnnotatedString.Range<LinkAnnotation> calculateVisibleLinkRange(AnnotatedString.Range<LinkAnnotation> link, TextLayoutResult textLayoutResult) {
        int lineEnd$default = TextLayoutResult.getLineEnd$default(textLayoutResult, textLayoutResult.getLineCount() - 1, false, 2, (Object) null);
        if (link.getStart() < lineEnd$default) {
            return AnnotatedString.Range.copy$default(link, (Object) null, 0, Math.min(link.getEnd(), lineEnd$default), (String) null, 11, (Object) null);
        }
        return null;
    }

    private final Modifier clipLink(Modifier modifier, final AnnotatedString.Range<LinkAnnotation> range) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new Function1() { // from class: ebe
            public final Object invoke(Object obj) {
                return TextLinkScope.d(this.b, range, (GraphicsLayerScope) obj);
            }
        });
    }

    public static Unit d(TextLinkScope textLinkScope, AnnotatedString.Range range, GraphicsLayerScope graphicsLayerScope) {
        Shape shapeShapeForRange = textLinkScope.shapeForRange(range);
        if (shapeShapeForRange != null) {
            graphicsLayerScope.setShape(shapeShapeForRange);
            graphicsLayerScope.setClip(true);
        }
        return Unit.INSTANCE;
    }

    public static Unit h(TextLinkScope textLinkScope, Object[] objArr, Function1 function1, int i, Composer composer, int i2) {
        textLinkScope.StyleAnnotation(objArr, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private final void handleLink(LinkAnnotation link, UriHandler uriHandler) {
        LinkInteractionListener linkInteractionListener;
        if (!(link instanceof LinkAnnotation.Url)) {
            if (!(link instanceof LinkAnnotation.Clickable) || (linkInteractionListener = ((LinkAnnotation.Clickable) link).getLinkInteractionListener()) == null) {
                return;
            }
            linkInteractionListener.onClick(link);
            return;
        }
        LinkInteractionListener linkInteractionListener2 = ((LinkAnnotation.Url) link).getLinkInteractionListener();
        if (linkInteractionListener2 != null) {
            linkInteractionListener2.onClick(link);
        } else {
            try {
                uriHandler.openUri(((LinkAnnotation.Url) link).getUrl());
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    public static List i(AnnotatedString.Range range) {
        SpanStyle spanStyle;
        if (range.getItem() instanceof LinkAnnotation) {
            Object item = range.getItem();
            item.getClass();
            if (!TextLinkScopeKt.isNullOrEmpty(((LinkAnnotation) item).getStyles())) {
                Object item2 = range.getItem();
                item2.getClass();
                TextLinkStyles styles = ((LinkAnnotation) item2).getStyles();
                if (styles == null || (spanStyle = styles.getStyle()) == null) {
                    spanStyle = new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 65535, (DefaultConstructorMarker) null);
                }
                return CollectionsKt.arrayListOf(new AnnotatedString.Range[]{range, new AnnotatedString.Range(spanStyle, range.getStart(), range.getEnd())});
            }
        }
        return CollectionsKt.arrayListOf(new AnnotatedString.Range[]{range});
    }

    public static TextRangeLayoutMeasureResult k(TextLinkScope textLinkScope, AnnotatedString.Range range, TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
        TextLayoutResult textLayoutResult = textLinkScope.getTextLayoutResult();
        if (textLayoutResult == null) {
            return textRangeLayoutMeasureScope.layout(0, 0, new Function0() { // from class: ibe
                public final Object invoke() {
                    return TextLinkScope.textRange$lambda$0$0();
                }
            });
        }
        AnnotatedString.Range<LinkAnnotation> rangeCalculateVisibleLinkRange = textLinkScope.calculateVisibleLinkRange(range, textLayoutResult);
        if (rangeCalculateVisibleLinkRange == null) {
            return textRangeLayoutMeasureScope.layout(0, 0, new Function0() { // from class: jbe
                public final Object invoke() {
                    return TextLinkScope.textRange$lambda$0$1();
                }
            });
        }
        final IntRect intRectRoundToIntRect = IntRectKt.roundToIntRect(textLayoutResult.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd()).getBounds());
        return textRangeLayoutMeasureScope.layout(intRectRoundToIntRect.getWidth(), intRectRoundToIntRect.getHeight(), new Function0() { // from class: kbe
            public final Object invoke() {
                return TextLinkScope.textRange$lambda$0$2(intRectRoundToIntRect);
            }
        });
    }

    public static boolean l(TextLinkScope textLinkScope) {
        TextLayoutInput layoutInput;
        AnnotatedString annotatedString = textLinkScope.text;
        TextLayoutResult textLayoutResult = textLinkScope.getTextLayoutResult();
        return Intrinsics.areEqual(annotatedString, (textLayoutResult == null || (layoutInput = textLayoutResult.getLayoutInput()) == null) ? null : layoutInput.getText());
    }

    public static Unit m(TextLinkScope textLinkScope, int i, Composer composer, int i2) {
        textLinkScope.LinksComposables(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private final SpanStyle mergeOrUse(SpanStyle spanStyle, SpanStyle spanStyle2) {
        SpanStyle spanStyleMerge;
        return (spanStyle == null || (spanStyleMerge = spanStyle.merge(spanStyle2)) == null) ? spanStyle2 : spanStyleMerge;
    }

    private final Path pathForRangeInRangeCoordinates(AnnotatedString.Range<LinkAnnotation> link) {
        TextLayoutResult textLayoutResult;
        AnnotatedString.Range<LinkAnnotation> rangeCalculateVisibleLinkRange;
        if (!((Boolean) getShouldMeasureLinks().invoke()).booleanValue() || (textLayoutResult = getTextLayoutResult()) == null || (rangeCalculateVisibleLinkRange = calculateVisibleLinkRange(link, textLayoutResult)) == null) {
            return null;
        }
        Path pathForRange = textLayoutResult.getPathForRange(rangeCalculateVisibleLinkRange.getStart(), rangeCalculateVisibleLinkRange.getEnd());
        Rect boundingBox = textLayoutResult.getBoundingBox(rangeCalculateVisibleLinkRange.getStart());
        pathForRange.translate-k-4lQ0M(Offset.constructor-impl(Offset.constructor-impl((((long) Float.floatToRawIntBits(textLayoutResult.getLineForOffset(rangeCalculateVisibleLinkRange.getStart()) == textLayoutResult.getLineForOffset(rangeCalculateVisibleLinkRange.getEnd() + (-1)) ? Math.min(textLayoutResult.getBoundingBox(rangeCalculateVisibleLinkRange.getEnd() - 1).getLeft(), boundingBox.getLeft()) : 0.0f)) << 32) | (((long) Float.floatToRawIntBits(boundingBox.getTop())) & 4294967295L)) ^ (-9223372034707292160L)));
        return pathForRange;
    }

    private final Shape shapeForRange(AnnotatedString.Range<LinkAnnotation> link) {
        final Path pathPathForRangeInRangeCoordinates = pathForRangeInRangeCoordinates(link);
        if (pathPathForRangeInRangeCoordinates != null) {
            return new Shape() { // from class: androidx.compose.foundation.text.TextLinkScope$shapeForRange$1$1
                /* JADX INFO: renamed from: createOutline-Pq9zytI, reason: not valid java name */
                public Outline m1447createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
                    return new Outline.Generic(pathPathForRangeInRangeCoordinates);
                }
            };
        }
        return null;
    }

    private final Modifier textRange(Modifier modifier, final AnnotatedString.Range<LinkAnnotation> range) {
        return modifier.then(new TextRangeLayoutModifier(new TextRangeScopeMeasurePolicy() { // from class: hbe
            @Override // androidx.compose.foundation.text.TextRangeScopeMeasurePolicy
            public final TextRangeLayoutMeasureResult measure(TextRangeLayoutMeasureScope textRangeLayoutMeasureScope) {
                return TextLinkScope.k(this.a, range, textRangeLayoutMeasureScope);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$0() {
        return IntOffset.box-impl(IntOffset.Companion.getZero-nOcc-ac());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$1() {
        return IntOffset.box-impl(IntOffset.Companion.getZero-nOcc-ac());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntOffset textRange$lambda$0$2(IntRect intRect) {
        return IntOffset.box-impl(intRect.getTopLeft-nOcc-ac());
    }

    public final void LinksComposables(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1154651354);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        int i3 = 1;
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1154651354, i2, -1, "androidx.compose.foundation.text.TextLinkScope.LinksComposables (TextLinkScope.kt:214)");
            }
            final UriHandler uriHandler = (UriHandler) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalUriHandler());
            AnnotatedString annotatedString = this.text;
            List linkAnnotations = annotatedString.getLinkAnnotations(0, annotatedString.length());
            int size = linkAnnotations.size();
            int i4 = 0;
            while (i4 < size) {
                final AnnotatedString.Range<LinkAnnotation> range = (AnnotatedString.Range) linkAnnotations.get(i4);
                if (range.getStart() != range.getEnd()) {
                    composerStartRestartGroup.startReplaceGroup(725478935);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    Composer.Companion companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                    Modifier modifierClipLink = clipLink(Modifier.Companion, range);
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: obe
                            public final Object invoke(Object obj) {
                                return TextLinkScope.LinksComposables$lambda$0$1$0((SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierPointerHoverIcon$default = PointerIconKt.pointerHoverIcon$default(HoverableKt.hoverable$default(textRange(SemanticsModifierKt.semantics$default(modifierClipLink, false, (Function1) objRememberedValue2, i3, (Object) null), range), mutableInteractionSource, false, 2, null), PointerIcon.Companion.getHand(), false, 2, (Object) null);
                    boolean zChangedInstance = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changed(range) | composerStartRestartGroup.changedInstance(uriHandler);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: pbe
                            public final Object invoke() {
                                return TextLinkScope.LinksComposables$lambda$0$2$0(this.b, range, uriHandler);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    BoxKt.Box(ClickableKt.m363combinedClickableauXiCPI$default(modifierPointerHoverIcon$default, mutableInteractionSource, null, false, null, null, null, null, null, false, (Function0) objRememberedValue3, 508, null), composerStartRestartGroup, 0);
                    if (TextLinkScopeKt.isNullOrEmpty(((LinkAnnotation) range.getItem()).getStyles())) {
                        composerStartRestartGroup.startReplaceGroup(716130110);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(726303039);
                        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == companion.getEmpty()) {
                            objRememberedValue4 = new LinkStateInteractionSourceObserver(mutableInteractionSource);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        final LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = (LinkStateInteractionSourceObserver) objRememberedValue4;
                        Unit unit = Unit.INSTANCE;
                        Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue5 == companion.getEmpty()) {
                            objRememberedValue5 = new TextLinkScope$LinksComposables$1$3$1(linkStateInteractionSourceObserver, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                        }
                        EffectsKt.LaunchedEffect(unit, (Function2) objRememberedValue5, composerStartRestartGroup, 6);
                        Boolean boolValueOf = Boolean.valueOf(linkStateInteractionSourceObserver.isHovered());
                        Boolean boolValueOf2 = Boolean.valueOf(linkStateInteractionSourceObserver.isFocused());
                        Boolean boolValueOf3 = Boolean.valueOf(linkStateInteractionSourceObserver.isPressed());
                        TextLinkStyles styles = ((LinkAnnotation) range.getItem()).getStyles();
                        SpanStyle style = styles != null ? styles.getStyle() : null;
                        TextLinkStyles styles2 = ((LinkAnnotation) range.getItem()).getStyles();
                        SpanStyle focusedStyle = styles2 != null ? styles2.getFocusedStyle() : null;
                        TextLinkStyles styles3 = ((LinkAnnotation) range.getItem()).getStyles();
                        SpanStyle hoveredStyle = styles3 != null ? styles3.getHoveredStyle() : null;
                        TextLinkStyles styles4 = ((LinkAnnotation) range.getItem()).getStyles();
                        Object[] objArr = {boolValueOf, boolValueOf2, boolValueOf3, style, focusedStyle, hoveredStyle, styles4 != null ? styles4.getPressedStyle() : null};
                        boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(this) | composerStartRestartGroup.changed(range);
                        Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance2 || objRememberedValue6 == companion.getEmpty()) {
                            objRememberedValue6 = new Function1() { // from class: androidx.compose.foundation.text.l
                                public final Object invoke(Object obj) {
                                    return TextLinkScope.LinksComposables$lambda$0$5$0(this.b, range, linkStateInteractionSourceObserver, (TextAnnotatorScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                        }
                        StyleAnnotation(objArr, (Function1) objRememberedValue6, composerStartRestartGroup, (i2 << 6) & 896);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(716130110);
                }
                composerStartRestartGroup.endReplaceGroup();
                i4++;
                i3 = 1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fbe
                public final Object invoke(Object obj, Object obj2) {
                    return TextLinkScope.m(this.b, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final AnnotatedString applyAnnotators$foundation() {
        boolean zIsEmpty = this.annotators.isEmpty();
        AnnotatedString styledText = this.text;
        if (!zIsEmpty) {
            TextAnnotatorScope textAnnotatorScope = new TextAnnotatorScope(styledText);
            SnapshotStateList<Function1<TextAnnotatorScope, Unit>> snapshotStateList = this.annotators;
            int size = snapshotStateList.size();
            for (int i = 0; i < size; i++) {
                ((Function1) snapshotStateList.get(i)).invoke(textAnnotatorScope);
            }
            styledText = textAnnotatorScope.getStyledText();
        }
        this.text = styledText;
        return styledText;
    }

    /* JADX INFO: renamed from: getInitialText$foundation, reason: from getter */
    public final AnnotatedString getInitialText() {
        return this.initialText;
    }

    public final Function0<Boolean> getShouldMeasureLinks() {
        return new Function0() { // from class: gbe
            public final Object invoke() {
                return Boolean.valueOf(TextLinkScope.l(this.b));
            }
        };
    }

    /* JADX INFO: renamed from: getText$foundation, reason: from getter */
    public final AnnotatedString getText() {
        return this.text;
    }

    public final TextLayoutResult getTextLayoutResult() {
        return (TextLayoutResult) this.textLayoutResult.getValue();
    }

    public final void setText$foundation(AnnotatedString annotatedString) {
        this.text = annotatedString;
    }

    public final void setTextLayoutResult(TextLayoutResult textLayoutResult) {
        this.textLayoutResult.setValue(textLayoutResult);
    }
}
