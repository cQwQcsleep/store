package org.jetbrains.kotlin.parsing;

import com.intellij.psi.tree.IElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class TruncatedSemanticWhitespaceAwarePsiBuilder extends SemanticWhitespaceAwarePsiBuilderAdapter {
    private final int myEOFPosition;

    public TruncatedSemanticWhitespaceAwarePsiBuilder(SemanticWhitespaceAwarePsiBuilder semanticWhitespaceAwarePsiBuilder, int i) {
        super(semanticWhitespaceAwarePsiBuilder);
        this.myEOFPosition = i;
    }

    private boolean isOffsetBeyondEof(int i) {
        int i2 = this.myEOFPosition;
        return i2 >= 0 && i >= i2;
    }

    private int rawLookAhead(int i) {
        int i2 = 0;
        while (i > 0) {
            i2++;
            IElementType iElementTypeRawLookup = rawLookup(i2);
            while (iElementTypeRawLookup != null && isWhitespaceOrComment(iElementTypeRawLookup)) {
                i2++;
                iElementTypeRawLookup = rawLookup(i2);
            }
            i--;
        }
        return i2;
    }

    public boolean eof() {
        return super.eof() || isOffsetBeyondEof(getCurrentOffset());
    }

    public String getTokenText() {
        if (eof()) {
            return null;
        }
        return super.getTokenText();
    }

    public IElementType getTokenType() {
        if (eof()) {
            return null;
        }
        return super.getTokenType();
    }

    public IElementType lookAhead(int i) {
        if (eof()) {
            return null;
        }
        int iRawLookAhead = rawLookAhead(i);
        if (isOffsetBeyondEof(rawTokenTypeStart(iRawLookAhead))) {
            return null;
        }
        return super.rawLookup(iRawLookAhead);
    }
}
