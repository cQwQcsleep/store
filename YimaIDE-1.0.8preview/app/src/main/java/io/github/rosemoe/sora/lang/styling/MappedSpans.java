package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MappedSpans implements Spans {
    private final List<List<Span>> spanMap;

    public class MappedSpansAccessor implements Spans.Reader, Spans.Modifier {
        private List<Span> span;

        private MappedSpansAccessor() {
        }

        private void checkLine() {
            if (this.span != null) {
                return;
            }
            k2d.a("line must be set first");
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Modifier
        public void addLineAt(int i, List<Span> list) {
            MappedSpans.this.spanMap.add(i, list);
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Modifier
        public void deleteLineAt(int i) {
            MappedSpans.this.spanMap.remove(i);
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
        public Span getSpanAt(int i) {
            checkLine();
            return this.span.get(i);
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
        public int getSpanCount() {
            checkLine();
            return this.span.size();
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
        public List<Span> getSpansOnLine(int i) {
            return Collections.unmodifiableList((List) MappedSpans.this.spanMap.get(i));
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Reader
        public void moveToLine(int i) {
            if (i == -1) {
                this.span = null;
            } else {
                this.span = (List) MappedSpans.this.spanMap.get(i);
            }
        }

        @Override // io.github.rosemoe.sora.lang.styling.Spans.Modifier
        public void setSpansOnLine(int i, List<Span> list) {
            List list2 = (List) MappedSpans.this.spanMap.get(MappedSpans.this.spanMap.size() - 1);
            Span span = (Span) list2.get(list2.size() - 1);
            while (MappedSpans.this.spanMap.size() <= i) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(MappedSpans.copyAndSetColumn(span, 0));
                MappedSpans.this.spanMap.add(arrayList);
            }
            MappedSpans.this.spanMap.set(i, list);
        }
    }

    private MappedSpans(List<List<Span>> list) {
        this.spanMap = list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Span copyAndSetColumn(Span span, int i) {
        Span spanCopy = span.copy();
        spanCopy.setColumn(i);
        return spanCopy;
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public void adjustOnDelete(CharPosition charPosition, CharPosition charPosition2) {
        int i = charPosition.line;
        int i2 = charPosition2.line;
        int i3 = charPosition.column;
        int i4 = charPosition2.column;
        List<List<Span>> list = this.spanMap;
        if (i == i2) {
            MappedSpanUpdater.shiftSpansOnSingleLineDelete(list, i, i3, i4);
        } else {
            MappedSpanUpdater.shiftSpansOnMultiLineDelete(list, i, i3, i2, i4);
        }
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public void adjustOnInsert(CharPosition charPosition, CharPosition charPosition2) {
        int i = charPosition.line;
        int i2 = charPosition2.line;
        int i3 = charPosition.column;
        int i4 = charPosition2.column;
        List<List<Span>> list = this.spanMap;
        if (i == i2) {
            MappedSpanUpdater.shiftSpansOnSingleLineInsert(list, i, i3, i4);
        } else {
            MappedSpanUpdater.shiftSpansOnMultiLineInsert(list, i, i3, i2, i4);
        }
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public int getLineCount() {
        return this.spanMap.size();
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public Spans.Modifier modify() {
        return new MappedSpansAccessor();
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public Spans.Reader read() {
        return new MappedSpansAccessor();
    }

    @Override // io.github.rosemoe.sora.lang.styling.Spans
    public boolean supportsModify() {
        return true;
    }

    public static class Builder {
        private Span last;
        private final List<List<Span>> spans;

        public Builder(int i) {
            this.spans = new ArrayList(i);
        }

        public void add(int i, Span span) {
            int size = this.spans.size() - 1;
            if (i == size) {
                this.spans.get(i).add(span);
            } else {
                if (i <= size) {
                    k2d.a("Invalid position");
                    return;
                }
                Span spanObtainNoExt = this.last;
                if (spanObtainNoExt == null) {
                    spanObtainNoExt = SpanFactory.obtainNoExt(0, 5L);
                }
                while (size < i) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(MappedSpans.copyAndSetColumn(spanObtainNoExt, 0));
                    this.spans.add(arrayList);
                    size++;
                }
                List<Span> list = this.spans.get(i);
                if (span.getColumn() == 0) {
                    list.clear();
                }
                list.add(span);
            }
            this.last = span;
        }

        public void addIfNeeded(int i, int i2, long j) {
            Span span = this.last;
            if (span == null || span.getStyle() != j) {
                add(i, SpanFactory.obtainNoExt(i2, j));
            }
        }

        public void addNormalIfNull() {
            if (this.spans.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(SpanFactory.obtainNoExt(0, 5L));
                this.spans.add(arrayList);
            }
        }

        public MappedSpans build() {
            return new MappedSpans(this.spans);
        }

        public void determine(int i) {
            Span spanObtainNoExt = this.last;
            if (spanObtainNoExt == null) {
                spanObtainNoExt = SpanFactory.obtainNoExt(0, 5L);
            }
            for (int size = this.spans.size() - 1; size < i; size++) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(MappedSpans.copyAndSetColumn(spanObtainNoExt, 0));
                this.spans.add(arrayList);
            }
        }

        public Builder() {
            this(CodeEditor.FLAG_DRAW_SOFT_WRAP);
        }
    }
}
