package io.github.rosemoe.sora.lang.styling.line;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0001J\u001e\u0010\r\u001a\u00020\u0003\"\b\b\u0000\u0010\u000e*\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0003J\u000e\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0003J%\u0010\u0016\u001a\u0004\u0018\u0001H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0010¢\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u0019\"\b\b\u0000\u0010\u000e*\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0010J\u0014\u0010\u001a\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lio/github/rosemoe/sora/lang/styling/line/LineStyles;", "Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "line", "", "<init>", "(I)V", "getLine", "()I", "setLine", "styles", "", "addStyle", "style", "eraseStyle", "T", "type", "Ljava/lang/Class;", "updateElements", "", "getElementCount", "getElementAt", "index", "findOne", "(Ljava/lang/Class;)Lio/github/rosemoe/sora/lang/styling/line/LineAnchorStyle;", "findAll", "", "typedElementCount", "", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class LineStyles extends LineAnchorStyle {
    private int line;
    private final List<LineAnchorStyle> styles;

    public LineStyles(int i) {
        super(i);
        this.line = i;
        this.styles = new ArrayList();
    }

    public final int addStyle(LineAnchorStyle style) {
        style.getClass();
        int i = 0;
        if (style instanceof LineStyles) {
            w01.a("Can not add LineStyles object");
            return 0;
        }
        if (style.getLine() != getLine()) {
            w01.a("target line differs from this object");
            return 0;
        }
        if (findOne(style.getClass()) != null) {
            eraseStyle(style.getClass());
        } else {
            i = 1;
        }
        this.styles.add(style);
        return i;
    }

    public final <T extends LineAnchorStyle> int eraseStyle(Class<T> type) {
        type.getClass();
        List<LineAnchorStyle> listFindAll = findAll(type);
        this.styles.removeAll(listFindAll);
        return listFindAll.size();
    }

    public final <T extends LineAnchorStyle> List<LineAnchorStyle> findAll(Class<T> type) {
        type.getClass();
        List<LineAnchorStyle> list = this.styles;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (type.isInstance((LineAnchorStyle) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final <T extends LineAnchorStyle> T findOne(Class<T> type) {
        Object next;
        type.getClass();
        Iterator<T> it2 = this.styles.iterator();
        while (it2.hasNext()) {
            next = it2.next();
            if (type.isInstance((LineAnchorStyle) next)) {
                return (T) next;
            }
        }
        next = null;
        return (T) next;
    }

    public final LineAnchorStyle getElementAt(int index) {
        return this.styles.get(index);
    }

    public final int getElementCount() {
        return this.styles.size();
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public int getLine() {
        return this.line;
    }

    @Override // io.github.rosemoe.sora.lang.styling.line.LineAnchorStyle
    public void setLine(int i) {
        this.line = i;
    }

    public final int typedElementCount(Class<Object> type) {
        type.getClass();
        List<LineAnchorStyle> list = this.styles;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (type.isInstance((LineAnchorStyle) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList.size();
    }

    public final void updateElements() {
        Iterator<T> it2 = this.styles.iterator();
        while (it2.hasNext()) {
            ((LineAnchorStyle) it2.next()).setLine(getLine());
        }
    }
}
