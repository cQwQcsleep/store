package androidx.compose.foundation.text;

import androidx.compose.foundation.text.modifiers.TextAutoSizeLayoutScope;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\t\u001a\u00020\u0003*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0002J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0013H\u0002J\f\u0010\u0015\u001a\u00020\u0012*\u00020\u0013H\u0002J\u0013\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/text/AutoSizeStepBased;", "Landroidx/compose/foundation/text/TextAutoSize;", "minFontSize", "Landroidx/compose/ui/unit/TextUnit;", "maxFontSize", "stepSize", "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "getFontSize", "Landroidx/compose/foundation/text/modifiers/TextAutoSizeLayoutScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "getFontSize-Ci0_558", "(Landroidx/compose/foundation/text/modifiers/TextAutoSizeLayoutScope;JLandroidx/compose/ui/text/AnnotatedString;)J", "didOverflow", "", "Landroidx/compose/ui/text/TextLayoutResult;", "didOverflowBounds", "didOverflowByEllipsize", "equals", "other", "", "hashCode", "", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class AutoSizeStepBased implements TextAutoSize {
    private final long maxFontSize;
    private long minFontSize;
    private final long stepSize;

    private AutoSizeStepBased(long j, long j2, long j3) {
        this.minFontSize = j;
        this.maxFontSize = j2;
        this.stepSize = j3;
        TextUnit.Companion companion = TextUnit.Companion;
        if (TextUnit.equals-impl0(j, companion.getUnspecified-XSAIIZE())) {
            w01.a("AutoSize.StepBased: TextUnit.Unspecified is not a valid value for minFontSize. Try using other values e.g. 10.sp");
            throw null;
        }
        if (TextUnit.equals-impl0(j2, companion.getUnspecified-XSAIIZE())) {
            w01.a("AutoSize.StepBased: TextUnit.Unspecified is not a valid value for maxFontSize. Try using other values e.g. 100.sp");
            throw null;
        }
        if (TextUnit.equals-impl0(j3, companion.getUnspecified-XSAIIZE())) {
            w01.a("AutoSize.StepBased: TextUnit.Unspecified is not a valid value for stepSize. Try using other values e.g. 0.25.sp");
            throw null;
        }
        if (TextUnitType.equals-impl0(TextUnit.getType-UIouoOA(this.minFontSize), TextUnit.getType-UIouoOA(j2))) {
            long j4 = this.minFontSize;
            TextUnitKt.checkArithmetic-NB67dxo(j4, j2);
            if (Float.compare(TextUnit.getValue-impl(j4), TextUnit.getValue-impl(j2)) > 0) {
                this.minFontSize = j2;
            }
        }
        if (TextUnitType.equals-impl0(TextUnit.getType-UIouoOA(j3), TextUnitType.Companion.getSp-UIouoOA())) {
            long sp = TextUnitKt.getSp(1.0E-4f);
            TextUnitKt.checkArithmetic-NB67dxo(j3, sp);
            if (Float.compare(TextUnit.getValue-impl(j3), TextUnit.getValue-impl(sp)) < 0) {
                w01.a("AutoSize.StepBased: stepSize must be greater than or equal to 0.0001f.sp");
                throw null;
            }
        }
        if (TextUnit.getValue-impl(this.minFontSize) < 0.0f) {
            w01.a("AutoSize.StepBased: minFontSize must not be negative");
            throw null;
        }
        if (TextUnit.getValue-impl(j2) >= 0.0f) {
            return;
        }
        w01.a("AutoSize.StepBased: maxFontSize must not be negative");
        throw null;
    }

    private final boolean didOverflow(TextLayoutResult textLayoutResult) {
        int i = textLayoutResult.getLayoutInput().getOverflow-gIe3tQ8();
        TextOverflow.Companion companion = TextOverflow.Companion;
        if (TextOverflow.equals-impl0(i, companion.getClip-gIe3tQ8()) || TextOverflow.equals-impl0(i, companion.getVisible-gIe3tQ8())) {
            return didOverflowBounds(textLayoutResult);
        }
        if (TextOverflow.equals-impl0(i, companion.getStartEllipsis-gIe3tQ8()) || TextOverflow.equals-impl0(i, companion.getMiddleEllipsis-gIe3tQ8()) || TextOverflow.equals-impl0(i, companion.getEllipsis-gIe3tQ8())) {
            return didOverflowByEllipsize(textLayoutResult);
        }
        yba.a("TextOverflow type ", TextOverflow.toString-impl(textLayoutResult.getLayoutInput().getOverflow-gIe3tQ8()), " is not supported.");
        return false;
    }

    private final boolean didOverflowBounds(TextLayoutResult textLayoutResult) {
        return textLayoutResult.getDidOverflowWidth() || textLayoutResult.getDidOverflowHeight();
    }

    private final boolean didOverflowByEllipsize(TextLayoutResult textLayoutResult) {
        int lineCount = textLayoutResult.getLineCount();
        if (lineCount == 0) {
            return false;
        }
        if (lineCount == 1) {
            return textLayoutResult.isLineEllipsized(0);
        }
        int i = textLayoutResult.getLayoutInput().getOverflow-gIe3tQ8();
        TextOverflow.Companion companion = TextOverflow.Companion;
        if (TextOverflow.equals-impl0(i, companion.getStartEllipsis-gIe3tQ8()) || TextOverflow.equals-impl0(i, companion.getMiddleEllipsis-gIe3tQ8())) {
            return didOverflowBounds(textLayoutResult);
        }
        if (TextOverflow.equals-impl0(i, companion.getEllipsis-gIe3tQ8())) {
            return textLayoutResult.isLineEllipsized(textLayoutResult.getLineCount() - 1);
        }
        return false;
    }

    @Override // androidx.compose.foundation.text.TextAutoSize
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || !(other instanceof AutoSizeStepBased)) {
            return false;
        }
        AutoSizeStepBased autoSizeStepBased = (AutoSizeStepBased) other;
        return TextUnit.equals-impl0(autoSizeStepBased.minFontSize, this.minFontSize) && TextUnit.equals-impl0(autoSizeStepBased.maxFontSize, this.maxFontSize) && TextUnit.equals-impl0(autoSizeStepBased.stepSize, this.stepSize);
    }

    @Override // androidx.compose.foundation.text.TextAutoSize
    /* JADX INFO: renamed from: getFontSize-Ci0_558, reason: not valid java name */
    public long mo1294getFontSizeCi0_558(TextAutoSizeLayoutScope textAutoSizeLayoutScope, long j, AnnotatedString annotatedString) {
        float f = textAutoSizeLayoutScope.toPx--R2X_6o(this.stepSize);
        float f2 = textAutoSizeLayoutScope.toPx--R2X_6o(this.minFontSize);
        float f3 = textAutoSizeLayoutScope.toPx--R2X_6o(this.maxFontSize);
        float f4 = (f2 + f3) / 2.0f;
        float f5 = f2;
        float f6 = f3;
        while (f6 - f5 >= f) {
            if (didOverflow(textAutoSizeLayoutScope.mo1714performLayout5ZSfY2I(j, annotatedString, textAutoSizeLayoutScope.toSp-kPz2Gy4(f4)))) {
                f6 = f4;
            } else {
                f5 = f4;
            }
            f4 = (f5 + f6) / 2.0f;
        }
        float fFloor = (((float) Math.floor((f5 - f2) / f)) * f) + f2;
        float f7 = f + fFloor;
        if (f7 <= f3 && !didOverflow(textAutoSizeLayoutScope.mo1714performLayout5ZSfY2I(j, annotatedString, textAutoSizeLayoutScope.toSp-kPz2Gy4(f7)))) {
            fFloor = f7;
        }
        return textAutoSizeLayoutScope.toSp-kPz2Gy4(fFloor);
    }

    @Override // androidx.compose.foundation.text.TextAutoSize
    public int hashCode() {
        return (((TextUnit.hashCode-impl(this.minFontSize) * 31) + TextUnit.hashCode-impl(this.maxFontSize)) * 31) + TextUnit.hashCode-impl(this.stepSize);
    }

    public /* synthetic */ AutoSizeStepBased(long j, long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3);
    }
}
