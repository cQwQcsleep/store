package io.github.rosemoe.sora.langs.textmate.registry.provider;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AssetsFileResolver implements FileResolver {
    private AssetManager assetManager;

    public AssetsFileResolver(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void dispose() {
        this.assetManager = null;
    }

    public InputStream resolveStreamByPath(String str) {
        try {
            return this.assetManager.open(str);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
