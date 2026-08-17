package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.text.CharPosition;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Spans {

    public interface Modifier {
        void addLineAt(int i, List<Span> list);

        void deleteLineAt(int i);

        void setSpansOnLine(int i, List<Span> list);
    }

    public interface Reader {
        Span getSpanAt(int i);

        int getSpanCount();

        List<Span> getSpansOnLine(int i);

        void moveToLine(int i);
    }

    void adjustOnDelete(CharPosition charPosition, CharPosition charPosition2);

    void adjustOnInsert(CharPosition charPosition, CharPosition charPosition2);

    int getLineCount();

    Modifier modify();

    Reader read();

    boolean supportsModify();
}
