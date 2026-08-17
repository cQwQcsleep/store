package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.MathUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.system.CpuArch;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PageCacheUtils {
    static final OpenChannelsCache CHANNELS_CACHE;
    private static final int CHANNELS_CACHE_CAPACITY;
    public static final int DEFAULT_PAGE_SIZE;
    public static final long FILE_PAGE_CACHES_TOTAL_CAPACITY_BYTES;
    public static final long FILE_PAGE_CACHE_NEW_CAPACITY_BYTES;
    public static final long FILE_PAGE_CACHE_OLD_CAPACITY_BYTES;
    static final float HEAP_CAPACITY_FRACTION;
    public static final boolean LOCK_FREE_PAGE_CACHE_ENABLED;
    private static final Logger LOG;
    static final int MAX_DIRECT_BUFFERS_POOL_BYTES;
    public static final long MAX_DIRECT_MEMORY_TO_USE_BYTES;
    public static final double NEW_PAGE_CACHE_MEMORY_FRACTION;

    static {
        String str;
        Logger logger = Logger.getInstance(PageCacheUtils.class);
        LOG = logger;
        int iMax = Math.max(1, SystemProperties.getIntProperty("idea.paged.storage.page.size", 10)) * IOUtil.MiB;
        DEFAULT_PAGE_SIZE = iMax;
        long jMaxDirectMemory = maxDirectMemory() - (((long) iMax) * 2);
        MAX_DIRECT_MEMORY_TO_USE_BYTES = jMaxDirectMemory;
        long jClamp = MathUtil.clamp(SystemProperties.getLongProperty("file-page-cache.cache-capacity-mb", CpuArch.is32Bit() ? 200L : 600L) * 1048576, Math.min(104857600L, jMaxDirectMemory), jMaxDirectMemory);
        FILE_PAGE_CACHES_TOTAL_CAPACITY_BYTES = jClamp;
        boolean booleanProperty = SystemProperties.getBooleanProperty("vfs.lock-free-impl.enable", false);
        LOCK_FREE_PAGE_CACHE_ENABLED = booleanProperty;
        double floatProperty = SystemProperties.getFloatProperty("vfs.lock-free-impl.fraction-direct-memory-to-utilize", 0.2f);
        NEW_PAGE_CACHE_MEMORY_FRACTION = floatProperty;
        long jRound = booleanProperty ? Math.round(jClamp * floatProperty) : 0L;
        FILE_PAGE_CACHE_NEW_CAPACITY_BYTES = jRound;
        long j = jClamp - jRound;
        FILE_PAGE_CACHE_OLD_CAPACITY_BYTES = j;
        int iMin = (int) Math.min(104857600L, Math.max(0L, (jMaxDirectMemory - jClamp) - 314572800));
        MAX_DIRECT_BUFFERS_POOL_BYTES = iMin;
        float floatProperty2 = SystemProperties.getFloatProperty("vfs.lock-free-impl.heap-capacity-ratio", 0.1f);
        HEAP_CAPACITY_FRACTION = floatProperty2;
        int intProperty = SystemProperties.getIntProperty("paged.file.storage.open.channel.cache.capacity", 400);
        CHANNELS_CACHE_CAPACITY = intProperty;
        CHANNELS_CACHE = new OpenChannelsCache(intProperty);
        StringBuilder sb = new StringBuilder("File page caching params:\n\tDEFAULT_PAGE_SIZE: ");
        sb.append(iMax);
        sb.append("\n\tDirect memory to use, max: ");
        sb.append(jMaxDirectMemory);
        sb.append("\n");
        if (booleanProperty) {
            str = "\tFilePageCache: regular + lock-free (LOCK_FREE_PAGE_CACHE_ENABLED:true)\n\tNEW_PAGE_CACHE_MEMORY_FRACTION: " + floatProperty + "\n\tRegular FilePageCache: " + j + " bytes\n\tNew     FilePageCache: " + jRound + " bytes (+ up to " + (floatProperty2 * 100.0f) + "% overflow)\n";
        } else {
            str = "\tFilePageCache: regular\n\tRegular FilePageCache: " + j + " bytes\n";
        }
        sb.append(str);
        sb.append("\tDirectByteBuffers pool: ");
        sb.append(iMin);
        sb.append(" bytes");
        logger.info(sb.toString());
    }

    private PageCacheUtils() {
        throw new AssertionError("Not for instantiation");
    }

    private static long maxDirectMemory() {
        try {
            try {
                return ((Long) Class.forName("jdk.internal.misc.VM").getMethod("maxDirectMemory", null).invoke(null, null)).longValue();
            } catch (Throwable unused) {
                try {
                    try {
                        Field declaredField = Class.forName("java.nio.Bits").getDeclaredField("maxMemory");
                        declaredField.setAccessible(true);
                        return ((Long) declaredField.get(null)).longValue();
                    } catch (Throwable unused2) {
                        return Runtime.getRuntime().maxMemory();
                    }
                } catch (Throwable unused3) {
                    Field declaredField2 = Class.forName("java.nio.Bits").getDeclaredField("MAX_MEMORY");
                    declaredField2.setAccessible(true);
                    return ((Long) declaredField2.get(null)).longValue();
                }
            }
        } catch (Throwable unused4) {
            return ((Long) Class.forName("sun.misc.VM").getMethod("maxDirectMemory", null).invoke(null, null)).longValue();
        }
    }
}
