package com.sun.tools.javac.platform;

import com.sun.source.util.Plugin;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Processor;
import javax.tools.JavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PlatformDescription extends Closeable {

    public interface PluginInfo<T> {
        String getName();

        Map<String, String> getOptions();

        T getPlugin();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    List<String> getAdditionalOptions();

    List<PluginInfo<Processor>> getAnnotationProcessors();

    JavaFileManager getFileManager();

    List<PluginInfo<Plugin>> getPlugins();

    String getSourceVersion();

    String getTargetVersion();
}
