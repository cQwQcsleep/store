package com.shadow.kotlin.collections.builders;

import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import core.pro.android.notify.h;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes2.dex */
final class SerializedMap implements Externalizable {
    public static final Companion Companion = new Companion();
    private static final long serialVersionUID = 0;
    private Map<?, ?> map;

    public final class Companion {
    }

    public SerializedMap(Map<?, ?> map) {
        CloseableKt.checkNotNullParameter(map, "map");
        this.map = map;
    }

    private final Object readResolve() {
        return this.map;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) throws IOException {
        CloseableKt.checkNotNullParameter(objectInput, "input");
        byte b = objectInput.readByte();
        if (b != 0) {
            throw new InvalidObjectException(h.a(b, "Unsupported flags value: "));
        }
        int i = objectInput.readInt();
        if (i < 0) {
            throw new InvalidObjectException("Illegal size value: " + i + '.');
        }
        MapBuilder mapBuilder = new MapBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            mapBuilder.put(objectInput.readObject(), objectInput.readObject());
        }
        this.map = mapBuilder.build();
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        CloseableKt.checkNotNullParameter(objectOutput, "output");
        objectOutput.writeByte(0);
        objectOutput.writeInt(this.map.size());
        for (Map.Entry<?, ?> entry : this.map.entrySet()) {
            objectOutput.writeObject(entry.getKey());
            objectOutput.writeObject(entry.getValue());
        }
    }

    public SerializedMap() {
        this(MapsKt.a());
    }
}
