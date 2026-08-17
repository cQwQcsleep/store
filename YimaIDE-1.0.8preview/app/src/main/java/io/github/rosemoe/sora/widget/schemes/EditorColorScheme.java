package io.github.rosemoe.sora.widget.schemes;

import android.util.SparseIntArray;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class EditorColorScheme {
    public static final int ANNOTATION = 28;
    public static final int ATTRIBUTE_NAME = 33;
    public static final int ATTRIBUTE_VALUE = 34;
    private static final int BACKGROUND_COLOR_DARK = -14606047;
    private static final int BACKGROUND_COLOR_LIGHT = -65794;
    public static final int BLOCK_LINE = 14;
    public static final int BLOCK_LINE_CURRENT = 15;
    public static final int COMMENT = 22;
    public static final int COMPLETION_WND_BACKGROUND = 19;
    public static final int COMPLETION_WND_CORNER = 20;
    public static final int COMPLETION_WND_ITEM_CURRENT = 44;
    public static final int COMPLETION_WND_TEXT_MATCHED = 67;
    public static final int COMPLETION_WND_TEXT_PRIMARY = 42;
    public static final int COMPLETION_WND_TEXT_SECONDARY = 43;
    public static final int CURRENT_LINE = 9;
    public static final int CURRENT_ROW_BORDER = 80;
    public static final int DIAGNOSTIC_TOOLTIP_ACTION = 56;
    public static final int DIAGNOSTIC_TOOLTIP_BACKGROUND = 53;
    public static final int DIAGNOSTIC_TOOLTIP_BRIEF_MSG = 54;
    public static final int DIAGNOSTIC_TOOLTIP_DETAILED_MSG = 55;
    protected static final int END_COLOR_ID = 80;
    public static final int FUNCTION_CHAR_BACKGROUND_STROKE = 52;
    public static final int FUNCTION_NAME = 27;
    public static final int HARD_WRAP_MARKER = 51;
    public static final int HIGHLIGHTED_DELIMITERS_BACKGROUND = 41;
    public static final int HIGHLIGHTED_DELIMITERS_BORDER = 75;
    public static final int HIGHLIGHTED_DELIMITERS_FOREGROUND = 39;
    public static final int HIGHLIGHTED_DELIMITERS_UNDERLINE = 40;
    public static final int HOVER_BACKGROUND = 69;
    public static final int HOVER_BORDER = 70;
    public static final int HOVER_TEXT_HIGHLIGHTED = 72;
    public static final int HOVER_TEXT_NORMAL = 68;
    public static final int HTML_TAG = 32;
    public static final int IDENTIFIER_NAME = 26;
    public static final int IDENTIFIER_VAR = 25;
    public static final int KEYWORD = 21;
    public static final int LINE_BLOCK_LABEL = 18;
    public static final int LINE_DIVIDER = 1;
    public static final int LINE_NUMBER = 2;
    public static final int LINE_NUMBER_BACKGROUND = 3;
    public static final int LINE_NUMBER_CURRENT = 45;
    public static final int LINE_NUMBER_PANEL = 16;
    public static final int LINE_NUMBER_PANEL_TEXT = 17;
    public static final int LITERAL = 24;
    public static final int MATCHED_TEXT_BACKGROUND = 29;
    public static final int MATCHED_TEXT_BORDER = 78;
    public static final int NON_PRINTABLE_CHAR = 31;
    public static final int OPERATOR = 23;
    private static final int PRIMARY_TEXT_COLOR_DEFAULT_DARK = -657931;
    private static final int PRIMARY_TEXT_COLOR_DEFAULT_LIGHT = -12434878;
    public static final int PROBLEM_ERROR = 35;
    public static final int PROBLEM_TYPO = 37;
    public static final int PROBLEM_WARNING = 36;
    public static final int SCROLL_BAR_THUMB = 11;
    public static final int SCROLL_BAR_THUMB_PRESSED = 12;
    public static final int SCROLL_BAR_TRACK = 13;
    private static final int SECONDARY_TEXT_COLOR_DARK = -1118482;
    private static final int SECONDARY_TEXT_COLOR_LIGHT = -10395295;
    public static final int SELECTED_TEXT_BACKGROUND = 6;
    public static final int SELECTED_TEXT_BORDER = 79;
    public static final int SELECTION_HANDLE = 8;
    public static final int SELECTION_INSERT = 7;
    public static final int SIDE_BLOCK_LINE = 38;
    public static final int SIGNATURE_BACKGROUND = 60;
    public static final int SIGNATURE_BORDER = 71;
    public static final int SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER = 59;
    public static final int SIGNATURE_TEXT_NORMAL = 58;
    public static final int SNIPPET_BACKGROUND_EDITING = 48;
    public static final int SNIPPET_BACKGROUND_INACTIVE = 46;
    public static final int SNIPPET_BACKGROUND_RELATED = 47;
    protected static final int START_COLOR_ID = 1;
    public static final int STATIC_SPAN_BACKGROUND = 63;
    public static final int STATIC_SPAN_FOREGROUND = 64;
    public static final int STICKY_SCROLL_DIVIDER = 62;
    public static final int STRIKETHROUGH = 57;
    public static final int STRIKE_THROUGH = 57;
    public static final int TEXT_ACTION_WINDOW_BACKGROUND = 65;
    public static final int TEXT_ACTION_WINDOW_ICON_COLOR = 66;
    public static final int TEXT_HIGHLIGHT_BACKGROUND = 74;
    public static final int TEXT_HIGHLIGHT_BORDER = 77;
    public static final int TEXT_HIGHLIGHT_STRONG_BACKGROUND = 73;
    public static final int TEXT_HIGHLIGHT_STRONG_BORDER = 76;
    public static final int TEXT_INLAY_HINT_BACKGROUND = 49;
    public static final int TEXT_INLAY_HINT_FOREGROUND = 50;
    public static final int TEXT_NORMAL = 5;
    public static final int TEXT_SELECTED = 30;
    public static final int UNDERLINE = 10;
    public static final int WHOLE_BACKGROUND = 4;
    private static EditorColorScheme globalDefault = new EditorColorScheme();
    protected final SparseIntArray colors;
    private final boolean dark;
    private final List<WeakReference<CodeEditor>> editors;

    public EditorColorScheme(boolean z) {
        this.colors = new SparseIntArray();
        this.editors = new ArrayList();
        this.dark = z;
        applyDefault();
    }

    /* JADX WARN: Code duplicated, block: B:35:0x0086  */
    private void applyDefault(int i) {
        int i2 = this.colors.get(i);
        switch (i) {
            case 1:
            case 3:
                i2 = -286331154;
                break;
            case 2:
            case LINE_NUMBER_CURRENT /* 45 */:
                i2 = -11513776;
                break;
            case 4:
            case 19:
            case 20:
            case STATIC_SPAN_BACKGROUND /* 63 */:
                if (!isDark()) {
                    i2 = -1;
                } else {
                    i2 = BACKGROUND_COLOR_DARK;
                }
                break;
            case 5:
            case 64:
                i2 = -13421773;
                break;
            case 6:
            case FUNCTION_CHAR_BACKGROUND_STROKE /* 52 */:
                i2 = 759124405;
                break;
            case 7:
                i2 = -581734914;
                break;
            case 8:
                i2 = -11309570;
                break;
            case 9:
                i2 = 268435456;
                break;
            case 10:
                i2 = -16777216;
                break;
            case 11:
                i2 = -2565928;
                break;
            case SCROLL_BAR_THUMB_PRESSED /* 12 */:
                i2 = -14210774;
                break;
            case 13:
            case 18:
            case TEXT_SELECTED /* 30 */:
            case HIGHLIGHTED_DELIMITERS_FOREGROUND /* 39 */:
            case HIGHLIGHTED_DELIMITERS_UNDERLINE /* 40 */:
            case 57:
                i2 = 0;
                break;
            case BLOCK_LINE /* 14 */:
                i2 = -2236963;
                break;
            case BLOCK_LINE_CURRENT /* 15 */:
            case SIDE_BLOCK_LINE /* 38 */:
            case HOVER_BORDER /* 70 */:
            case SIGNATURE_BORDER /* 71 */:
                i2 = -6710887;
                break;
            case 16:
                i2 = -587202560;
                break;
            case LINE_NUMBER_PANEL_TEXT /* 17 */:
                i2 = -1;
                break;
            case 21:
                i2 = -14575885;
                break;
            case COMMENT /* 22 */:
                i2 = -5723992;
                break;
            case 23:
                i2 = -16750890;
                break;
            case LITERAL /* 24 */:
                i2 = -16744320;
                break;
            case 25:
                i2 = -11243910;
                break;
            case IDENTIFIER_NAME /* 26 */:
            case ANNOTATION /* 28 */:
            case SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER /* 59 */:
            case HOVER_TEXT_HIGHLIGHTED /* 72 */:
                i2 = -16537100;
                break;
            case FUNCTION_NAME /* 27 */:
                i2 = -2080517;
                break;
            case MATCHED_TEXT_BACKGROUND /* 29 */:
                i2 = -256;
                break;
            case NON_PRINTABLE_CHAR /* 31 */:
                i2 = -288568116;
                break;
            case PROBLEM_ERROR /* 35 */:
                i2 = -1426128896;
                break;
            case PROBLEM_WARNING /* 36 */:
                i2 = -1426067200;
                break;
            case PROBLEM_TYPO /* 37 */:
                i2 = 1711341329;
                break;
            case HIGHLIGHTED_DELIMITERS_BACKGROUND /* 41 */:
                i2 = 486539264;
                break;
            case COMPLETION_WND_TEXT_PRIMARY /* 42 */:
            case COMPLETION_WND_TEXT_SECONDARY /* 43 */:
            case 50:
                i2 = !isDark() ? -16777216 : -1;
                break;
            case COMPLETION_WND_ITEM_CURRENT /* 44 */:
                i2 = SECONDARY_TEXT_COLOR_DARK;
                break;
            case SNIPPET_BACKGROUND_INACTIVE /* 46 */:
                i2 = 1725816285;
                break;
            case SNIPPET_BACKGROUND_RELATED /* 47 */:
                i2 = -1428300323;
                break;
            case SNIPPET_BACKGROUND_EDITING /* 48 */:
                i2 = -3355444;
                break;
            case TEXT_INLAY_HINT_BACKGROUND /* 49 */:
                i2 = !isDark() ? 486539264 : 502197998;
                break;
            case HARD_WRAP_MARKER /* 51 */:
                i2 = !isDark() ? SECONDARY_TEXT_COLOR_DARK : 486539264;
                break;
            case DIAGNOSTIC_TOOLTIP_BACKGROUND /* 53 */:
            case SIGNATURE_BACKGROUND /* 60 */:
            case TEXT_ACTION_WINDOW_BACKGROUND /* 65 */:
            case HOVER_BACKGROUND /* 69 */:
                if (!isDark()) {
                    i2 = BACKGROUND_COLOR_LIGHT;
                } else {
                    i2 = BACKGROUND_COLOR_DARK;
                }
                break;
            case DIAGNOSTIC_TOOLTIP_BRIEF_MSG /* 54 */:
                i2 = !isDark() ? PRIMARY_TEXT_COLOR_DEFAULT_LIGHT : PRIMARY_TEXT_COLOR_DEFAULT_DARK;
                break;
            case DIAGNOSTIC_TOOLTIP_DETAILED_MSG /* 55 */:
                i2 = !isDark() ? SECONDARY_TEXT_COLOR_LIGHT : SECONDARY_TEXT_COLOR_DARK;
                break;
            case DIAGNOSTIC_TOOLTIP_ACTION /* 56 */:
                i2 = -12409355;
                break;
            case SIGNATURE_TEXT_NORMAL /* 58 */:
            case HOVER_TEXT_NORMAL /* 68 */:
                i2 = !isDark() ? -16777216 : SECONDARY_TEXT_COLOR_DARK;
                break;
            case STICKY_SCROLL_DIVIDER /* 62 */:
                i2 = -1712394514;
                break;
            case TEXT_ACTION_WINDOW_ICON_COLOR /* 66 */:
                i2 = !isDark() ? -7829368 : SECONDARY_TEXT_COLOR_DARK;
                break;
            case COMPLETION_WND_TEXT_MATCHED /* 67 */:
                i2 = -11687172;
                break;
            case TEXT_HIGHLIGHT_STRONG_BACKGROUND /* 73 */:
                i2 = !isDark() ? 1074684828 : -1207940750;
                break;
            case TEXT_HIGHLIGHT_BACKGROUND /* 74 */:
                i2 = !isDark() ? 1079465815 : -1202235561;
                break;
            case HIGHLIGHTED_DELIMITERS_BORDER /* 75 */:
                i2 = -12627531;
                break;
        }
        setColor(i, i2);
    }

    public static EditorColorScheme getDefault() {
        return globalDefault;
    }

    public static void setDefault(EditorColorScheme editorColorScheme, boolean z) {
        if (editorColorScheme == null) {
            editorColorScheme = new EditorColorScheme();
        }
        if (z) {
            for (WeakReference weakReference : (WeakReference[]) globalDefault.editors.toArray(new WeakReference[0])) {
                CodeEditor codeEditor = (CodeEditor) weakReference.get();
                if (codeEditor != null) {
                    codeEditor.setColorScheme(editorColorScheme);
                }
            }
        }
        globalDefault = editorColorScheme;
    }

    public void attachEditor(CodeEditor codeEditor) {
        Objects.requireNonNull(codeEditor);
        Iterator<WeakReference<CodeEditor>> it2 = this.editors.iterator();
        while (it2.hasNext()) {
            if (it2.next().get() == codeEditor) {
                return;
            }
        }
        this.editors.add(new WeakReference<>(codeEditor));
        codeEditor.onColorFullUpdate();
    }

    public void detachEditor(CodeEditor codeEditor) {
        Iterator<WeakReference<CodeEditor>> it2 = this.editors.iterator();
        while (it2.hasNext()) {
            if (it2.next().get() == codeEditor) {
                it2.remove();
                return;
            }
        }
    }

    public int getColor(int i) {
        return this.colors.get(i);
    }

    public boolean isDark() {
        return this.dark;
    }

    public void setColor(int i, int i2) {
        if (getColor(i) == i2) {
            return;
        }
        this.colors.put(i, i2);
        Iterator<WeakReference<CodeEditor>> it2 = this.editors.iterator();
        while (it2.hasNext()) {
            CodeEditor codeEditor = it2.next().get();
            if (codeEditor == null) {
                it2.remove();
            } else {
                codeEditor.onColorUpdated(i);
            }
        }
    }

    public EditorColorScheme() {
        this(false);
    }

    public EditorColorScheme(CodeEditor codeEditor) {
        this();
        attachEditor(codeEditor);
    }

    public static void setDefault(EditorColorScheme editorColorScheme) {
        setDefault(editorColorScheme, false);
    }

    public void applyDefault() {
        for (int i = 1; i <= 80; i++) {
            applyDefault(i);
        }
    }
}
