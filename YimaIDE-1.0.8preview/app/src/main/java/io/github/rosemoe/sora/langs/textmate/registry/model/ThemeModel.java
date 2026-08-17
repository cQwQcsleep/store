package io.github.rosemoe.sora.langs.textmate.registry.model;

import io.github.rosemoe.sora.langs.textmate.utils.StringUtils;
import java.util.List;
import org.eclipse.tm4e.core.internal.theme.Theme;
import org.eclipse.tm4e.core.internal.theme.raw.IRawTheme;
import org.eclipse.tm4e.core.internal.theme.raw.RawThemeReader;
import org.eclipse.tm4e.core.registry.IThemeSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ThemeModel {
    public static final ThemeModel EMPTY = new ThemeModel("EMPTY");
    private boolean isDark;
    private String name;
    private IRawTheme rawTheme;
    private Theme theme;
    private IThemeSource themeSource;

    private ThemeModel(String str) {
        this.themeSource = null;
        this.rawTheme = null;
        this.name = str;
        this.theme = Theme.createFromRawTheme((IRawTheme) null, (List) null);
    }

    public String getName() {
        return this.name;
    }

    public IRawTheme getRawTheme() {
        return this.rawTheme;
    }

    public Theme getTheme() {
        return this.theme;
    }

    public IThemeSource getThemeSource() {
        return this.themeSource;
    }

    public boolean isDark() {
        return this.isDark;
    }

    public boolean isLoaded() {
        return this.theme != null;
    }

    public void load(List<String> list) throws Exception {
        IRawTheme theme = RawThemeReader.readTheme(this.themeSource);
        this.rawTheme = theme;
        this.theme = Theme.createFromRawTheme(theme, list);
    }

    public void setDark(boolean z) {
        this.isDark = z;
    }

    public void load() throws Exception {
        load(null);
    }

    public ThemeModel(IThemeSource iThemeSource, String str) {
        this.themeSource = iThemeSource;
        this.name = str;
    }

    public ThemeModel(IThemeSource iThemeSource) {
        this.themeSource = iThemeSource;
        this.name = StringUtils.getFileNameWithoutExtension(iThemeSource.getFilePath());
    }
}
