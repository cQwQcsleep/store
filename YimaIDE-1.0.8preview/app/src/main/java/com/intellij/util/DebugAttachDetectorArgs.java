package com.intellij.util;

import com.intellij.openapi.diagnostic.Logger;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class DebugAttachDetectorArgs {
    private static final Logger LOG = Logger.getInstance(DebugAttachDetectorArgs.class);
    private static final String DEBUG_ARGS = findDebugArgs();
    private static final Properties AGENT_PROPERTIES = findAgentProperties();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "properties", "com/intellij/util/DebugAttachDetectorArgs", "isAttached"));
    }

    private static Properties findAgentProperties() {
        Class<?> cls;
        try {
            try {
                cls = Class.forName("jdk.internal.vm.VMSupport");
            } catch (Exception unused) {
                cls = Class.forName("sun.misc.VMSupport");
            }
            try {
                return (Properties) cls.getMethod("getAgentProperties", null).invoke(null, null);
            } catch (IllegalAccessException unused2) {
                return null;
            } catch (NoSuchMethodException | InvocationTargetException e) {
                LOG.error(e);
                return null;
            }
        } catch (Exception unused3) {
            LOG.warn("Unable to init DebugAttachDetector, VMSupport class not found");
            return null;
        }
    }

    private static String findDebugArgs() {
        try {
            for (String str : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
                if (str.contains("-agentlib:jdwp")) {
                    return str;
                }
            }
            return null;
        } catch (Exception e) {
            LOG.error(e);
            return null;
        }
    }

    public static boolean isAttached() {
        Properties properties;
        if (!isDebugEnabled()) {
            return false;
        }
        if (isDebugServer() && (properties = AGENT_PROPERTIES) != null) {
            return isAttached(properties);
        }
        return true;
    }

    public static boolean isDebugEnabled() {
        return DEBUG_ARGS != null;
    }

    private static boolean isDebugServer() {
        String str = DEBUG_ARGS;
        return str != null && str.contains("server=y");
    }

    private static boolean isAttached(Properties properties) {
        if (properties == null) {
            $$$reportNull$$$0(0);
        }
        String property = properties.getProperty("sun.jdwp.listenerAddress");
        return property != null && property.isEmpty();
    }
}
