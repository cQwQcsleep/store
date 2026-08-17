package com.sun.jna.platform.mac;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.PointerByReference;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Carbon extends Library {
    public static final Carbon INSTANCE = (Carbon) Native.load("Carbon", Carbon.class);
    public static final int cmdKey = 256;
    public static final int controlKey = 4096;
    public static final int optionKey = 2048;
    public static final int shiftKey = 512;

    public interface EventHandlerProcPtr extends Callback {
        int callback(Pointer pointer, Pointer pointer2, Pointer pointer3);
    }

    @Structure.FieldOrder({"signature", "id"})
    public static class EventHotKeyID extends Structure {
        public int id;
        public int signature;

        public static class ByValue extends EventHotKeyID implements Structure.ByValue {
        }
    }

    @Structure.FieldOrder({"eventClass", "eventKind"})
    public static class EventTypeSpec extends Structure {
        public int eventClass;
        public int eventKind;
    }

    Pointer GetEventDispatcherTarget();

    int GetEventParameter(Pointer pointer, int i, int i2, Pointer pointer2, int i3, IntBuffer intBuffer, EventHotKeyID eventHotKeyID);

    int InstallEventHandler(Pointer pointer, EventHandlerProcPtr eventHandlerProcPtr, int i, EventTypeSpec[] eventTypeSpecArr, Pointer pointer2, PointerByReference pointerByReference);

    int RegisterEventHotKey(int i, int i2, EventHotKeyID.ByValue byValue, Pointer pointer, int i3, PointerByReference pointerByReference);

    int RemoveEventHandler(Pointer pointer);

    int UnregisterEventHotKey(Pointer pointer);
}
