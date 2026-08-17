package io.github.rosemoe.sora.langs.textmate.registry;

import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.tm4e.core.internal.theme.raw.IRawTheme;
import org.eclipse.tm4e.core.registry.IThemeSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ThemeRegistry {
    private static ThemeRegistry instance;
    private final List<ThemeChangeListener> allListener = new ArrayList();
    private final List<ThemeModel> allThemeModel = new ArrayList();
    private ThemeModel currentThemeModel = ThemeModel.EMPTY;

    @FunctionalInterface
    public interface ThemeChangeListener {
        void onChangeTheme(ThemeModel themeModel);
    }

    private void dispatchThemeChange(ThemeModel themeModel) {
        Iterator<ThemeChangeListener> it2 = this.allListener.iterator();
        while (it2.hasNext()) {
            it2.next().onChangeTheme(themeModel);
        }
    }

    public static synchronized ThemeRegistry getInstance() {
        try {
            if (instance == null) {
                instance = new ThemeRegistry();
            }
        } catch (Throwable th) {
            throw th;
        }
        return instance;
    }

    public synchronized void addListener(ThemeChangeListener themeChangeListener) {
        this.allListener.add(themeChangeListener);
    }

    public void dispose() {
        this.allListener.clear();
    }

    public ThemeModel findThemeByFileName(String str) {
        for (ThemeModel themeModel : this.allThemeModel) {
            if (themeModel.getName().equals(str)) {
                return themeModel;
            }
        }
        return null;
    }

    public ThemeModel findThemeByThemeName(String str) {
        for (ThemeModel themeModel : this.allThemeModel) {
            IRawTheme rawTheme = themeModel.getRawTheme();
            if (rawTheme != null && str.equals(rawTheme.getName())) {
                return themeModel;
            }
        }
        return null;
    }

    public ThemeModel getCurrentThemeModel() {
        return this.currentThemeModel;
    }

    public boolean hasListener(ThemeChangeListener themeChangeListener) {
        return this.allListener.contains(themeChangeListener);
    }

    public synchronized void loadTheme(ThemeModel themeModel, boolean z) throws Exception {
        try {
            if (!themeModel.isLoaded()) {
                themeModel.load();
            }
            ThemeModel themeModelFindThemeByThemeName = findThemeByThemeName(themeModel.getName());
            if (themeModelFindThemeByThemeName != null) {
                setTheme(themeModelFindThemeByThemeName);
                return;
            }
            this.allThemeModel.add(themeModel);
            if (z) {
                setTheme(themeModel);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void removeListener(ThemeChangeListener themeChangeListener) {
        this.allListener.remove(themeChangeListener);
    }

    public void setTheme(ThemeModel themeModel) {
        this.currentThemeModel = themeModel;
        if (!this.allThemeModel.contains(themeModel)) {
            this.allThemeModel.add(themeModel);
        }
        if (!themeModel.isLoaded()) {
            try {
                themeModel.load();
            } catch (Exception e) {
                rc6.a(e);
                return;
            }
        }
        dispatchThemeChange(this.currentThemeModel);
    }

    public synchronized boolean setTheme(String str) {
        ThemeModel themeModelFindThemeByFileName = findThemeByFileName(str);
        if (themeModelFindThemeByFileName != null) {
            setTheme(themeModelFindThemeByFileName);
            return true;
        }
        ThemeModel themeModelFindThemeByThemeName = findThemeByThemeName(str);
        if (themeModelFindThemeByThemeName == null) {
            return false;
        }
        setTheme(themeModelFindThemeByThemeName);
        return true;
    }

    public void loadTheme(IThemeSource iThemeSource, boolean z) throws Exception {
        loadTheme(new ThemeModel(iThemeSource), z);
    }

    public void loadTheme(ThemeModel themeModel) throws Exception {
        loadTheme(themeModel, true);
    }

    public void loadTheme(IThemeSource iThemeSource) throws Exception {
        loadTheme(iThemeSource, true);
    }
}
