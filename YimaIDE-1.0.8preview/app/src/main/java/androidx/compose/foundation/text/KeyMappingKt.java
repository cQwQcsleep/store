package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference1Impl;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000\"\u0014\u0010\u0006\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"commonKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "shortcutModifier", "Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "defaultKeyMapping", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    static {
        final KeyMapping keyMappingCommonKeyMapping = commonKeyMapping(new PropertyReference1Impl() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.isCtrlPressed-ZmokQxo(((KeyEvent) obj).unbox-impl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1354mapZmokQxo(android.view.KeyEvent event) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.isShiftPressed-ZmokQxo(event) && KeyEvent_androidKt.isCtrlPressed-ZmokQxo(event)) {
                    long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion = Key.Companion;
                    if (Key.equals-impl0(j, companion.getDirectionLeft-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.equals-impl0(j, companion.getDirectionRight-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.equals-impl0(j, companion.getDirectionUp-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.equals-impl0(j, companion.getDirectionDown-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.isCtrlPressed-ZmokQxo(event)) {
                    long j2 = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion2 = Key.Companion;
                    if (Key.equals-impl0(j2, companion2.getDirectionLeft-EK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.equals-impl0(j2, companion2.getDirectionRight-EK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.equals-impl0(j2, companion2.getDirectionUp-EK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.equals-impl0(j2, companion2.getDirectionDown-EK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.equals-impl0(j2, companion2.getH-EK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.equals-impl0(j2, companion2.getDelete-EK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.equals-impl0(j2, companion2.getBackspace-EK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.equals-impl0(j2, companion2.getBackslash-EK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.isShiftPressed-ZmokQxo(event)) {
                    long j3 = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion3 = Key.Companion;
                    if (Key.equals-impl0(j3, companion3.getMoveHome-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_START;
                    } else if (Key.equals-impl0(j3, companion3.getMoveEnd-EK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_END;
                    }
                } else if (KeyEvent_androidKt.isAltPressed-ZmokQxo(event)) {
                    long j4 = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion4 = Key.Companion;
                    if (Key.equals-impl0(j4, companion4.getBackspace-EK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.equals-impl0(j4, companion4.getDelete-EK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? keyMappingCommonKeyMapping.mo1354mapZmokQxo(event) : keyCommand;
            }
        };
    }

    public static final KeyMapping commonKeyMapping(final Function1<? super KeyEvent, Boolean> function1) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo1354mapZmokQxo(android.view.KeyEvent event) {
                if (((Boolean) function1.invoke(KeyEvent.box-impl(event))).booleanValue() && KeyEvent_androidKt.isShiftPressed-ZmokQxo(event)) {
                    if (Key.equals-impl0(KeyEvent_androidKt.getKey-ZmokQxo(event), Key.Companion.getZ-EK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (((Boolean) function1.invoke(KeyEvent.box-impl(event))).booleanValue()) {
                    long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion = Key.Companion;
                    if (Key.equals-impl0(j, companion.getC-EK5gGoQ()) || Key.equals-impl0(j, companion.getInsert-EK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.equals-impl0(j, companion.getV-EK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.equals-impl0(j, companion.getX-EK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.equals-impl0(j, companion.getA-EK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.equals-impl0(j, companion.getY-EK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.equals-impl0(j, companion.getZ-EK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.isCtrlPressed-ZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.isShiftPressed-ZmokQxo(event)) {
                    long j2 = KeyEvent_androidKt.getKey-ZmokQxo(event);
                    Key.Companion companion2 = Key.Companion;
                    if (Key.equals-impl0(j2, companion2.getDirectionLeft-EK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.equals-impl0(j2, companion2.getDirectionRight-EK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.equals-impl0(j2, companion2.getDirectionUp-EK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.equals-impl0(j2, companion2.getDirectionDown-EK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.equals-impl0(j2, companion2.getPageUp-EK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.equals-impl0(j2, companion2.getPageDown-EK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.equals-impl0(j2, companion2.getMoveHome-EK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.equals-impl0(j2, companion2.getMoveEnd-EK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.equals-impl0(j2, companion2.getInsert-EK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long j3 = KeyEvent_androidKt.getKey-ZmokQxo(event);
                Key.Companion companion3 = Key.Companion;
                if (Key.equals-impl0(j3, companion3.getDirectionLeft-EK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.equals-impl0(j3, companion3.getDirectionRight-EK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.equals-impl0(j3, companion3.getDirectionUp-EK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.equals-impl0(j3, companion3.getDirectionDown-EK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.equals-impl0(j3, companion3.getDirectionCenter-EK5gGoQ())) {
                    return KeyCommand.CENTER;
                }
                if (Key.equals-impl0(j3, companion3.getPageUp-EK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.equals-impl0(j3, companion3.getPageDown-EK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.equals-impl0(j3, companion3.getMoveHome-EK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.equals-impl0(j3, companion3.getMoveEnd-EK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.equals-impl0(j3, companion3.getEnter-EK5gGoQ()) || Key.equals-impl0(j3, companion3.getNumPadEnter-EK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.equals-impl0(j3, companion3.getBackspace-EK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.equals-impl0(j3, companion3.getDelete-EK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.equals-impl0(j3, companion3.getPaste-EK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.equals-impl0(j3, companion3.getCut-EK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.equals-impl0(j3, companion3.getCopy-EK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.equals-impl0(j3, companion3.getTab-EK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }
}
