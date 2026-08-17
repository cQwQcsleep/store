package io.github.rosemoe.sora.widget;

import io.github.rosemoe.sora.text.CharPosition;
import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.ContentLine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SymbolPairMatch {
    private final Map<Character, List<SymbolPair>> multipleCharByEndPairMaps;
    private SymbolPairMatch parent;
    private final Map<Character, SymbolPair> singleCharPairMaps;

    public static final class DefaultSymbolPairs extends SymbolPairMatch {
        public DefaultSymbolPairs() {
            super.putPair('{', new SymbolPair("{", "}"));
            super.putPair('(', new SymbolPair("(", ")"));
            super.putPair('[', new SymbolPair("[", "]"));
            super.putPair(Typography.quote, new SymbolPair("\"", "\"", new SymbolPair.SymbolPairEx() { // from class: io.github.rosemoe.sora.widget.SymbolPairMatch.DefaultSymbolPairs.1
                @Override // io.github.rosemoe.sora.widget.SymbolPairMatch.SymbolPair.SymbolPairEx
                public boolean shouldDoAutoSurround(Content content) {
                    return content.getCursor().isSelected();
                }
            }));
            super.putPair('\'', new SymbolPair("'", "'", new SymbolPair.SymbolPairEx() { // from class: io.github.rosemoe.sora.widget.SymbolPairMatch.DefaultSymbolPairs.2
                @Override // io.github.rosemoe.sora.widget.SymbolPairMatch.SymbolPair.SymbolPairEx
                public boolean shouldDoAutoSurround(Content content) {
                    return content.getCursor().isSelected();
                }
            }));
        }
    }

    public SymbolPairMatch(SymbolPairMatch symbolPairMatch) {
        this.singleCharPairMaps = new HashMap();
        this.multipleCharByEndPairMaps = new HashMap();
        setParent(symbolPairMatch);
    }

    public final SymbolPair matchBestPair(CodeEditor codeEditor, CharPosition charPosition, char[] cArr, char c) {
        boolean z;
        Content text = codeEditor.getText();
        SymbolPair symbolPairMatchBestPairBySingleChar = cArr == null ? matchBestPairBySingleChar(c) : null;
        if (symbolPairMatchBestPairBySingleChar != null) {
            symbolPairMatchBestPairBySingleChar.measureCursorPosition(charPosition.index);
            return symbolPairMatchBestPairBySingleChar;
        }
        for (SymbolPair symbolPair : matchBestPairList(c)) {
            if (symbolPair.shouldReplace(codeEditor)) {
                char[] charArray = symbolPair.open.toCharArray();
                int i = charPosition.index;
                if (cArr == null) {
                    z = true;
                    for (int length = charArray.length - 2; length >= 0; length--) {
                        if (i > 0) {
                            i--;
                        }
                        z &= text.charAt(i) == charArray[length];
                    }
                } else if (cArr.length > charArray.length) {
                    continue;
                } else {
                    int length2 = charArray.length - 1;
                    int length3 = cArr.length - 1;
                    boolean z2 = true;
                    while (length3 > 0) {
                        z2 &= cArr[length3] == charArray[length2];
                        length3--;
                        length2--;
                    }
                    if (!z2 || length2 <= 0) {
                        z = z2;
                    } else {
                        i--;
                        z = z2;
                        while (length2 >= 0) {
                            z &= text.charAt(i) == charArray[length2];
                            i--;
                            length2--;
                        }
                    }
                }
                if (z) {
                    symbolPair.measureCursorPosition(i);
                    return symbolPair;
                }
            }
        }
        return null;
    }

    public final SymbolPair matchBestPairBySingleChar(char c) {
        SymbolPairMatch symbolPairMatch;
        SymbolPair symbolPair = this.singleCharPairMaps.get(Character.valueOf(c));
        return (symbolPair != null || (symbolPairMatch = this.parent) == null) ? symbolPair : symbolPairMatch.matchBestPairBySingleChar(c);
    }

    public final List<SymbolPair> matchBestPairList(char c) {
        SymbolPairMatch symbolPairMatch;
        List<SymbolPair> arrayList = this.multipleCharByEndPairMaps.get(Character.valueOf(c));
        if (arrayList == null && (symbolPairMatch = this.parent) != null) {
            arrayList = new ArrayList<>(symbolPairMatch.matchBestPairList(c));
        }
        return arrayList == null ? Collections.EMPTY_LIST : arrayList;
    }

    public void putPair(char[] cArr, SymbolPair symbolPair) {
        char c = cArr[cArr.length - 1];
        List<SymbolPair> arrayList = this.multipleCharByEndPairMaps.get(Character.valueOf(c));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        arrayList.add(symbolPair);
        this.multipleCharByEndPairMaps.put(Character.valueOf(c), arrayList);
    }

    public void removeAllPairs() {
        this.singleCharPairMaps.clear();
        this.multipleCharByEndPairMaps.clear();
    }

    public void setParent(SymbolPairMatch symbolPairMatch) {
        this.parent = symbolPairMatch;
    }

    public static class SymbolPair {
        public static final SymbolPair EMPTY_SYMBOL_PAIR = new SymbolPair("", "");
        public final String close;
        private int cursorOffset;
        private int insertOffset;
        public final String open;
        private SymbolPairEx symbolPairEx;

        public interface SymbolPairEx {
            default boolean shouldDoAutoSurround(Content content) {
                return false;
            }

            default boolean shouldReplace(CodeEditor codeEditor, ContentLine contentLine, int i) {
                return true;
            }
        }

        public SymbolPair(String str, String str2) {
            this.open = str;
            this.close = str2;
        }

        public int getCursorOffset() {
            return this.cursorOffset;
        }

        public int getInsertOffset() {
            return this.insertOffset;
        }

        public void measureCursorPosition(int i) {
            this.cursorOffset = this.open.length() + i;
            this.insertOffset = i;
        }

        public boolean shouldDoAutoSurround(Content content) {
            SymbolPairEx symbolPairEx = this.symbolPairEx;
            if (symbolPairEx == null) {
                return false;
            }
            return symbolPairEx.shouldDoAutoSurround(content);
        }

        public boolean shouldReplace(CodeEditor codeEditor) {
            if (this.symbolPairEx == null) {
                return false;
            }
            Content text = codeEditor.getText();
            return this.symbolPairEx.shouldReplace(codeEditor, text.getLine(text.getCursor().getLeftLine()), text.getCursor().getLeftColumn());
        }

        public SymbolPair(String str, String str2, SymbolPairEx symbolPairEx) {
            this(str, str2);
            this.symbolPairEx = symbolPairEx;
        }
    }

    public SymbolPairMatch() {
        this(null);
    }

    public void putPair(char c, SymbolPair symbolPair) {
        this.singleCharPairMaps.put(Character.valueOf(c), symbolPair);
    }

    public void putPair(String str, SymbolPair symbolPair) {
        putPair(str.toCharArray(), symbolPair);
    }
}
