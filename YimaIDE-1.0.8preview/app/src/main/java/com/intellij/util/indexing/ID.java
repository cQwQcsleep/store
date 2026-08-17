package com.intellij.util.indexing;

import androidx.collection.ScatterMapKt;
import com.intellij.ide.plugins.cl.PluginAwareClassLoader;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.util.Java11Shim;
import com.intellij.util.containers.UtilKt;
import com.intellij.util.indexing.ID;
import com.intellij.util.io.SimpleStringPersistentEnumerator;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ID<K, V> extends IndexId<K, V> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String INDICES_ENUM_FILE = "indices.enum";
    public static final int MAX_NUMBER_OF_INDICES = 32767;
    private static volatile Map<ID<?, ?>, PluginId> idToPluginId;
    private static volatile Map<ID<?, ?>, Throwable> idToRegistrationStackTrace;
    private volatile int uniqueId;
    private static final Logger LOG = Logger.getInstance(ID.class);
    private static final PluginId CORE_PLUGIN_ID = PluginId.getId("com.intellij");
    private static volatile SimpleStringPersistentEnumerator nameToIdRegistry = new SimpleStringPersistentEnumerator(getEnumFile());
    private static final Map<String, ID<?, ?>> idObjects = new ConcurrentHashMap();
    private static final Object lock = new Object();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8 || i == 11) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8 || i == 11) ? 3 : 2];
        if (i == 1) {
            objArr[0] = "enumFile";
        } else if (i == 2 || i == 3 || i == 4 || i == 6 || i == 7 || i == 8) {
            objArr[0] = "name";
        } else if (i != 11) {
            objArr[0] = "com/intellij/util/indexing/ID";
        } else {
            objArr[0] = "id";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 11:
                objArr[1] = "com/intellij/util/indexing/ID";
                break;
            case 5:
                objArr[1] = "create";
                break;
            case 9:
                objArr[1] = "getInvalidIdAccessMessage";
                break;
            case 10:
                objArr[1] = "getRegistrationTrace";
                break;
            default:
                objArr[1] = "getEnumFile";
                break;
        }
        if (i == 1) {
            objArr[2] = "reloadEnumFile";
        } else if (i == 2) {
            objArr[2] = "<init>";
        } else if (i == 3) {
            objArr[2] = "stringToId";
        } else if (i == 4) {
            objArr[2] = "create";
        } else if (i == 6 || i == 7) {
            objArr[2] = "findByName";
        } else if (i == 8) {
            objArr[2] = "getInvalidIdAccessMessage";
        } else if (i == 11) {
            objArr[2] = "unloadId";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 6 && i != 7 && i != 8 && i != 11) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    static {
        Java11Shim.Companion companion = Java11Shim.INSTANCE;
        idToPluginId = companion.getINSTANCE().mapOf();
        idToRegistrationStackTrace = companion.getINSTANCE().mapOf();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ID(String str, PluginId pluginId) {
        super(str);
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        this.uniqueId = stringToId(str);
        idObjects.put(str, this);
        synchronized (lock) {
            try {
                idToPluginId.get(this);
                idToPluginId = UtilKt.with(idToPluginId, this, pluginId == null ? CORE_PLUGIN_ID : pluginId);
                idToRegistrationStackTrace = UtilKt.with(idToRegistrationStackTrace, this, new Throwable());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ void a(Map map, SimpleStringPersistentEnumerator simpleStringPersistentEnumerator, String str, Integer num) {
        Integer num2 = (Integer) map.get(str);
        if (num2 != null) {
            if (num.intValue() != num2.intValue()) {
                reassign(str, num2.intValue());
            }
        } else {
            int iEnumerate = simpleStringPersistentEnumerator.enumerate(str);
            if (iEnumerate != num.intValue()) {
                reassign(str, iEnumerate);
            }
        }
    }

    public static <K, V> ID<K, V> create(String str) {
        ID<K, V> idFindByName;
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        PluginId callerPluginId = getCallerPluginId();
        synchronized (lock) {
            try {
                idFindByName = findByName(str, true, callerPluginId);
                if (idFindByName == null) {
                    idFindByName = new ID<>(str, callerPluginId);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return idFindByName;
    }

    public static ID<?, ?> findById(int i) {
        String strValueOf = nameToIdRegistry.valueOf(i);
        if (strValueOf == null) {
            return null;
        }
        return idObjects.get(strValueOf);
    }

    public static <K, V> ID<K, V> findByName(String str, boolean z, PluginId pluginId) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        ID<K, V> id = (ID<K, V>) findById(stringToId(str));
        if (z && id != null) {
            PluginId pluginId2 = idToPluginId.get(id);
            String idString = pluginId2 == null ? "" : pluginId2.getIdString();
            String idString2 = pluginId != null ? pluginId.getIdString() : "";
            if (!Objects.equals(idString, idString2)) {
                Throwable th = idToRegistrationStackTrace.get(id);
                String invalidIdAccessMessage = getInvalidIdAccessMessage(str, idString, idString2, th);
                if (th != null) {
                    throw new AssertionError(invalidIdAccessMessage, th);
                }
                x01.a(invalidIdAccessMessage);
                return null;
            }
        }
        return id;
    }

    public static PluginId getCallerPluginId() {
        Class<?> callerClass = Java11Shim.INSTANCE.getINSTANCE().getCallerClass(3);
        if (callerClass == null) {
            return null;
        }
        PluginAwareClassLoader classLoader = callerClass.getClassLoader();
        if (classLoader instanceof PluginAwareClassLoader) {
            return classLoader.getPluginId();
        }
        return null;
    }

    private static Path getEnumFile() {
        Path pathResolve = PathManager.getIndexRoot().resolve(INDICES_ENUM_FILE);
        if (pathResolve == null) {
            $$$reportNull$$$0(0);
        }
        return pathResolve;
    }

    private static String getInvalidIdAccessMessage(String str, String str2, String str3, Throwable th) {
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        StringBuilder sb = new StringBuilder("ID with name '");
        sb.append(str);
        sb.append("' requested for plugin ");
        sb.append(str3);
        sb.append(" but registered for ");
        sb.append(str2);
        sb.append(" plugin. Please use an instance field to access corresponding ID.");
        sb.append(th == null ? " Registration stack trace: " : "");
        return sb.toString();
    }

    public static Collection<ID<?, ?>> getRegisteredIds() {
        return idToPluginId.keySet();
    }

    private static void reassign(String str, int i) {
        ID<?, ?> id = idObjects.get(str);
        if (id != null) {
            ((ID) id).uniqueId = i;
        }
    }

    public static void reinitializeDiskStorage() {
        nameToIdRegistry.forceDiskSync();
    }

    private static void reloadEnumFile(Path path) {
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (Files.exists(path, new LinkOption[0]) && path.equals(nameToIdRegistry.getFile())) {
            return;
        }
        final SimpleStringPersistentEnumerator simpleStringPersistentEnumerator = new SimpleStringPersistentEnumerator(getEnumFile());
        final Map<String, Integer> invertedState = simpleStringPersistentEnumerator.getInvertedState();
        nameToIdRegistry.getInvertedState().forEach(new BiConsumer() { // from class: hf6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ID.a(invertedState, simpleStringPersistentEnumerator, (String) obj, (Integer) obj2);
            }
        });
        nameToIdRegistry = simpleStringPersistentEnumerator;
    }

    private static int stringToId(String str) {
        if (str == null) {
            $$$reportNull$$$0(3);
        }
        int iEnumerate = nameToIdRegistry.enumerate(str);
        if (iEnumerate == ((short) iEnumerate)) {
            return iEnumerate;
        }
        x01.a("Too many indexes registered");
        return 0;
    }

    public static void unloadId(ID<?, ?> id) {
        if (id == null) {
            $$$reportNull$$$0(11);
        }
        String name = id.getName();
        synchronized (lock) {
            ID<?, ?> idRemove = idObjects.remove(name);
            LOG.assertTrue(id.equals(idRemove), "Failed to unload: " + name);
            idToPluginId = UtilKt.without(idToPluginId, id);
            idToRegistrationStackTrace = UtilKt.without(idToRegistrationStackTrace, id);
        }
    }

    public PluginId getPluginId() {
        return idToPluginId.get(this);
    }

    public Throwable getRegistrationTrace() {
        Throwable th = idToRegistrationStackTrace.get(this);
        if (th == null) {
            $$$reportNull$$$0(10);
        }
        return th;
    }

    public int getUniqueId() {
        return this.uniqueId;
    }

    public static void reloadEnumFile() {
        reloadEnumFile(getEnumFile());
    }

    public static <K, V> ID<K, V> findByName(String str) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        return findByName(str, false, null);
    }
}
