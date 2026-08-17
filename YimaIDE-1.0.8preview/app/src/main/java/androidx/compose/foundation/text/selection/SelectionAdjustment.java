package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.StringHelpersKt;
import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.foundation.text.selection.SelectionAdjustment;
import androidx.compose.foundation.text.selection.SelectionLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bà\u0080\u0001\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "", "adjust", "Landroidx/compose/foundation/text/selection/Selection;", "layout", "Landroidx/compose/foundation/text/selection/SelectionLayout;", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SelectionAdjustment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionAdjustment$Companion;", "", "<init>", "()V", "None", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "getNone", "()Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "Character", "getCharacter", "Word", "getWord", "Paragraph", "getParagraph", "CharacterWithWordAccelerate", "getCharacterWithWordAccelerate", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SelectionAdjustment None = new SelectionAdjustment() { // from class: d3d
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            public final Selection adjust(SelectionLayout selectionLayout) {
                return SelectionAdjustment.Companion.d(selectionLayout);
            }
        };
        private static final SelectionAdjustment Character = new SelectionAdjustment() { // from class: e3d
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            public final Selection adjust(SelectionLayout selectionLayout) {
                return SelectionAdjustment.Companion.a(selectionLayout);
            }
        };
        private static final SelectionAdjustment Word = new SelectionAdjustment() { // from class: f3d
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            public final Selection adjust(SelectionLayout selectionLayout) {
                return SelectionAdjustment.Companion.e(selectionLayout);
            }
        };
        private static final SelectionAdjustment Paragraph = new SelectionAdjustment() { // from class: g3d
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            public final Selection adjust(SelectionLayout selectionLayout) {
                return SelectionAdjustment.Companion.b(selectionLayout);
            }
        };
        private static final SelectionAdjustment CharacterWithWordAccelerate = new SelectionAdjustment() { // from class: h3d
            @Override // androidx.compose.foundation.text.selection.SelectionAdjustment
            public final Selection adjust(SelectionLayout selectionLayout) {
                return SelectionAdjustment.Companion.c(selectionLayout);
            }
        };

        private Companion() {
        }

        public static Selection a(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.ensureAtLeastOneChar(None.adjust(selectionLayout), selectionLayout);
        }

        public static Selection b(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.adjustToBoundaries(selectionLayout, new BoundaryFunction() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Paragraph$1$1
                @Override // androidx.compose.foundation.text.selection.BoundaryFunction
                /* JADX INFO: renamed from: getBoundary-fzxv0v0 */
                public final long mo1739getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i) {
                    return StringHelpersKt.getParagraphBoundary(selectableInfo.getInputText(), i);
                }
            });
        }

        public static Selection c(SelectionLayout selectionLayout) {
            Selection.AnchorInfo end;
            Selection.AnchorInfo anchorInfoUpdateSelectionBoundary;
            Selection.AnchorInfo start;
            Selection.AnchorInfo end2;
            Selection previousSelection = selectionLayout.getPreviousSelection();
            if (previousSelection == null) {
                return Word.adjust(selectionLayout);
            }
            if (selectionLayout.getIsStartHandle()) {
                end = previousSelection.getStart();
                anchorInfoUpdateSelectionBoundary = SelectionAdjustmentKt.updateSelectionBoundary(selectionLayout, selectionLayout.getStartInfo(), end);
                end2 = previousSelection.getEnd();
                start = anchorInfoUpdateSelectionBoundary;
            } else {
                end = previousSelection.getEnd();
                anchorInfoUpdateSelectionBoundary = SelectionAdjustmentKt.updateSelectionBoundary(selectionLayout, selectionLayout.getEndInfo(), end);
                start = previousSelection.getStart();
                end2 = anchorInfoUpdateSelectionBoundary;
            }
            if (Intrinsics.areEqual(anchorInfoUpdateSelectionBoundary, end)) {
                return previousSelection;
            }
            return SelectionAdjustmentKt.ensureAtLeastOneChar(new Selection(start, end2, selectionLayout.getCrossStatus() == CrossStatus.CROSSED || (selectionLayout.getCrossStatus() == CrossStatus.COLLAPSED && start.getOffset() > end2.getOffset())), selectionLayout);
        }

        public static Selection d(SelectionLayout selectionLayout) {
            return new Selection(selectionLayout.getStartInfo().anchorForOffset(selectionLayout.getStartInfo().getRawStartHandleOffset()), selectionLayout.getEndInfo().anchorForOffset(selectionLayout.getEndInfo().getRawEndHandleOffset()), selectionLayout.getCrossStatus() == CrossStatus.CROSSED);
        }

        public static Selection e(SelectionLayout selectionLayout) {
            return SelectionAdjustmentKt.adjustToBoundaries(selectionLayout, new BoundaryFunction() { // from class: androidx.compose.foundation.text.selection.SelectionAdjustment$Companion$Word$1$1
                @Override // androidx.compose.foundation.text.selection.BoundaryFunction
                /* JADX INFO: renamed from: getBoundary-fzxv0v0 */
                public final long mo1739getBoundaryfzxv0v0(SelectableInfo selectableInfo, int i) {
                    return selectableInfo.getTextLayoutResult().getWordBoundary--jx7JFs(i);
                }
            });
        }

        public final SelectionAdjustment getCharacter() {
            return Character;
        }

        public final SelectionAdjustment getCharacterWithWordAccelerate() {
            return CharacterWithWordAccelerate;
        }

        public final SelectionAdjustment getNone() {
            return None;
        }

        public final SelectionAdjustment getParagraph() {
            return Paragraph;
        }

        public final SelectionAdjustment getWord() {
            return Word;
        }
    }

    Selection adjust(SelectionLayout layout);
}
