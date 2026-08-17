package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle;
import io.github.rosemoe.sora.lang.styling.line.LineStyles;
import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.util.MutableInt;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Styles {
    public List<CodeBlock> blocks;
    public List<CodeBlock> blocksByStart;
    public boolean indentCountMode;
    public List<LineStyles> lineStyles;
    public Spans spans;
    public Map<Class<?>, MutableInt> styleTypeCount;
    public int suppressSwitch;

    public Styles(Spans spans, boolean z) {
        this.suppressSwitch = Integer.MAX_VALUE;
        this.indentCountMode = false;
        this.spans = spans;
        if (z) {
            this.blocks = new ArrayList(CodeEditor.FLAG_DRAW_SOFT_WRAP);
        }
    }

    private void styleCountUpdate(Class<?> cls, int i) {
        MutableInt mutableInt = this.styleTypeCount.get(cls);
        if (mutableInt == null) {
            mutableInt = new MutableInt(0);
            this.styleTypeCount.put(cls, mutableInt);
        }
        mutableInt.value += i;
    }

    public void addCodeBlock(CodeBlock codeBlock) {
        Objects.requireNonNull(codeBlock, "CodeBlock must not be null");
        this.blocks.add(codeBlock);
    }

    public void addLineStyle(LineAnchorStyle lineAnchorStyle) {
        if (this.lineStyles == null) {
            this.lineStyles = new ArrayList();
            this.styleTypeCount = new ConcurrentHashMap();
        }
        Class<?> cls = lineAnchorStyle.getClass();
        for (LineStyles lineStyles : this.lineStyles) {
            if (lineStyles.getLine() == lineAnchorStyle.getLine()) {
                styleCountUpdate(cls, lineStyles.addStyle(lineAnchorStyle));
                return;
            }
        }
        LineStyles lineStyles2 = new LineStyles(lineAnchorStyle.getLine());
        this.lineStyles.add(lineStyles2);
        styleCountUpdate(cls, lineStyles2.addStyle(lineAnchorStyle));
    }

    public void adjustOnDelete(CharPosition charPosition, CharPosition charPosition2) {
        this.spans.adjustOnDelete(charPosition, charPosition2);
        int i = charPosition.line;
        int i2 = i - charPosition2.line;
        if (i2 == 0) {
            return;
        }
        List<CodeBlock> list = this.blocks;
        if (list != null) {
            BlocksUpdater.update(list, i, i2);
        }
        List<LineStyles> list2 = this.lineStyles;
        if (list2 != null) {
            Iterator<LineStyles> it2 = list2.iterator();
            while (it2.hasNext()) {
                LineStyles next = it2.next();
                int line = next.getLine();
                if (line > charPosition2.line) {
                    next.setLine(line + i2);
                    next.updateElements();
                } else if (line > charPosition.line) {
                    it2.remove();
                }
            }
        }
    }

    public void adjustOnInsert(CharPosition charPosition, CharPosition charPosition2) {
        this.spans.adjustOnInsert(charPosition, charPosition2);
        int i = charPosition2.line;
        int i2 = charPosition.line;
        int i3 = i - i2;
        if (i3 == 0) {
            return;
        }
        List<CodeBlock> list = this.blocks;
        if (list != null) {
            BlocksUpdater.update(list, i2, i3);
        }
        List<LineStyles> list2 = this.lineStyles;
        if (list2 != null) {
            for (LineStyles lineStyles : list2) {
                if (lineStyles.getLine() > charPosition.line) {
                    lineStyles.setLine(lineStyles.getLine() + i3);
                    lineStyles.updateElements();
                }
            }
        }
    }

    public void eraseAllLineStyles() {
        List<LineStyles> list = this.lineStyles;
        if (list == null) {
            return;
        }
        list.clear();
        this.styleTypeCount.clear();
    }

    public void eraseLineStyle(int i, Class<? extends LineAnchorStyle> cls) {
        List<LineStyles> list = this.lineStyles;
        if (list == null) {
            return;
        }
        for (LineStyles lineStyles : list) {
            if (lineStyles.getLine() == i) {
                styleCountUpdate(cls, -lineStyles.eraseStyle(cls));
                return;
            }
        }
    }

    public void finishBuilding() {
        if (this.blocks != null) {
            int i = -1;
            int i2 = 0;
            while (i2 < this.blocks.size() - 1) {
                i2++;
                int i3 = this.blocks.get(i2).endLine;
                if (i > i3) {
                    Collections.sort(this.blocks, CodeBlock.COMPARATOR_END);
                    break;
                }
                i = i3;
            }
            ArrayList arrayList = new ArrayList(this.blocks);
            this.blocksByStart = arrayList;
            Collections.sort(arrayList, CodeBlock.COMPARATOR_START);
        } else {
            this.blocksByStart = null;
        }
        List<LineStyles> list = this.lineStyles;
        if (list != null) {
            Collections.sort(list);
        }
    }

    public Spans getSpans() {
        return this.spans;
    }

    public int getSuppressSwitch() {
        return this.suppressSwitch;
    }

    public boolean isIndentCountMode() {
        return this.indentCountMode;
    }

    public void setIndentCountMode(boolean z) {
        this.indentCountMode = z;
    }

    public void setSuppressSwitch(int i) {
        this.suppressSwitch = i;
    }

    public Styles(Spans spans) {
        this(spans, true);
    }

    public Styles() {
        this(null);
    }
}
