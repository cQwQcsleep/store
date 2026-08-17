package com.sun.jna.platform.linux;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.PointerType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Udev extends Library {
    public static final Udev INSTANCE = (Udev) Native.load("udev", Udev.class);

    public static class UdevContext extends PointerType {
        public UdevDevice deviceNewFromSyspath(String str) {
            return Udev.INSTANCE.udev_device_new_from_syspath(this, str);
        }

        public UdevEnumerate enumerateNew() {
            return Udev.INSTANCE.udev_enumerate_new(this);
        }

        public UdevContext ref() {
            return Udev.INSTANCE.udev_ref(this);
        }

        public void unref() {
            Udev.INSTANCE.udev_unref(this);
        }
    }

    public static class UdevDevice extends PointerType {
        public String getDevnode() {
            return Udev.INSTANCE.udev_device_get_devnode(this);
        }

        public String getDevtype() {
            return Udev.INSTANCE.udev_device_get_devtype(this);
        }

        public UdevDevice getParent() {
            return Udev.INSTANCE.udev_device_get_parent(this);
        }

        public UdevDevice getParentWithSubsystemDevtype(String str, String str2) {
            return Udev.INSTANCE.udev_device_get_parent_with_subsystem_devtype(this, str, str2);
        }

        public String getPropertyValue(String str) {
            return Udev.INSTANCE.udev_device_get_property_value(this, str);
        }

        public String getSubsystem() {
            return Udev.INSTANCE.udev_device_get_subsystem(this);
        }

        public String getSysattrValue(String str) {
            return Udev.INSTANCE.udev_device_get_sysattr_value(this, str);
        }

        public String getSysname() {
            return Udev.INSTANCE.udev_device_get_syspath(this);
        }

        public String getSyspath() {
            return Udev.INSTANCE.udev_device_get_syspath(this);
        }

        public UdevDevice ref() {
            return Udev.INSTANCE.udev_device_ref(this);
        }

        public void unref() {
            Udev.INSTANCE.udev_device_unref(this);
        }
    }

    public static class UdevEnumerate extends PointerType {
        public int addMatchSubsystem(String str) {
            return Udev.INSTANCE.udev_enumerate_add_match_subsystem(this, str);
        }

        public UdevListEntry getListEntry() {
            return Udev.INSTANCE.udev_enumerate_get_list_entry(this);
        }

        public UdevEnumerate ref() {
            return Udev.INSTANCE.udev_enumerate_ref(this);
        }

        public int scanDevices() {
            return Udev.INSTANCE.udev_enumerate_scan_devices(this);
        }

        public void unref() {
            Udev.INSTANCE.udev_enumerate_unref(this);
        }
    }

    public static class UdevListEntry extends PointerType {
        public String getName() {
            return Udev.INSTANCE.udev_list_entry_get_name(this);
        }

        public UdevListEntry getNext() {
            return Udev.INSTANCE.udev_list_entry_get_next(this);
        }
    }

    String udev_device_get_devnode(UdevDevice udevDevice);

    String udev_device_get_devtype(UdevDevice udevDevice);

    UdevDevice udev_device_get_parent(UdevDevice udevDevice);

    UdevDevice udev_device_get_parent_with_subsystem_devtype(UdevDevice udevDevice, String str, String str2);

    String udev_device_get_property_value(UdevDevice udevDevice, String str);

    String udev_device_get_subsystem(UdevDevice udevDevice);

    String udev_device_get_sysattr_value(UdevDevice udevDevice, String str);

    String udev_device_get_sysname(UdevDevice udevDevice);

    String udev_device_get_syspath(UdevDevice udevDevice);

    UdevDevice udev_device_new_from_syspath(UdevContext udevContext, String str);

    UdevDevice udev_device_ref(UdevDevice udevDevice);

    UdevDevice udev_device_unref(UdevDevice udevDevice);

    int udev_enumerate_add_match_subsystem(UdevEnumerate udevEnumerate, String str);

    UdevListEntry udev_enumerate_get_list_entry(UdevEnumerate udevEnumerate);

    UdevEnumerate udev_enumerate_new(UdevContext udevContext);

    UdevEnumerate udev_enumerate_ref(UdevEnumerate udevEnumerate);

    int udev_enumerate_scan_devices(UdevEnumerate udevEnumerate);

    UdevEnumerate udev_enumerate_unref(UdevEnumerate udevEnumerate);

    String udev_list_entry_get_name(UdevListEntry udevListEntry);

    UdevListEntry udev_list_entry_get_next(UdevListEntry udevListEntry);

    UdevContext udev_new();

    UdevContext udev_ref(UdevContext udevContext);

    UdevContext udev_unref(UdevContext udevContext);
}
