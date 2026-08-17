package javax.tools;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum StandardLocation implements JavaFileManager.Location {
    CLASS_OUTPUT,
    SOURCE_OUTPUT,
    CLASS_PATH,
    SOURCE_PATH,
    ANNOTATION_PROCESSOR_PATH,
    ANNOTATION_PROCESSOR_MODULE_PATH,
    PLATFORM_CLASS_PATH,
    NATIVE_HEADER_OUTPUT,
    MODULE_SOURCE_PATH,
    UPGRADE_MODULE_PATH,
    SYSTEM_MODULES,
    MODULE_PATH,
    PATCH_MODULE_PATH;

    private static final ConcurrentHashMap<String, JavaFileManager.Location> LOCATIONS = new ConcurrentHashMap<>();

    public static class LazyPatternHolder {
        static final Pattern MODULE_WORD_PATTERN = Pattern.compile("\\bMODULE\\b");

        private LazyPatternHolder() {
        }
    }

    public static final boolean computeIsModuleOrientedLocation(String str) {
        return LazyPatternHolder.MODULE_WORD_PATTERN.matcher(str).matches();
    }

    public static JavaFileManager.Location locationFor(final String str) {
        JavaFileManager.Location location;
        Objects.requireNonNull(str, "name");
        JavaFileManager.Location location2 = LOCATIONS.get(str);
        if (location2 != null) {
            return location2;
        }
        JavaFileManager.Location[] locationArrValues = values();
        int length = locationArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                location = null;
                break;
            }
            location = locationArrValues[i];
            if (location.getName().equals(str)) {
                break;
            }
            i++;
        }
        if (location == null) {
            final boolean zEndsWith = str.endsWith("_OUTPUT");
            final boolean zComputeIsModuleOrientedLocation = computeIsModuleOrientedLocation(str);
            location = new JavaFileManager.Location() { // from class: javax.tools.StandardLocation.1
                @Override // javax.tools.JavaFileManager.Location
                public String getName() {
                    return str;
                }

                @Override // javax.tools.JavaFileManager.Location
                public boolean isModuleOrientedLocation() {
                    return zComputeIsModuleOrientedLocation;
                }

                @Override // javax.tools.JavaFileManager.Location
                public boolean isOutputLocation() {
                    return zEndsWith;
                }
            };
        }
        JavaFileManager.Location locationPutIfAbsent = LOCATIONS.putIfAbsent(str, location);
        return locationPutIfAbsent != null ? locationPutIfAbsent : location;
    }

    @Override // javax.tools.JavaFileManager.Location
    public String getName() {
        return name();
    }

    @Override // javax.tools.JavaFileManager.Location
    public boolean isModuleOrientedLocation() {
        int iOrdinal = ordinal();
        if (iOrdinal == 5) {
            return true;
        }
        switch (iOrdinal) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return true;
            default:
                return false;
        }
    }

    @Override // javax.tools.JavaFileManager.Location
    public boolean isOutputLocation() {
        int iOrdinal = ordinal();
        return iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 7;
    }
}
