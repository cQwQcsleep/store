package org.apache.log4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import net.schmizz.sshj.sftp.SFTPEngine;
import okhttp3.HttpUrl;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Deprecated
public class Level extends Priority implements Serializable {
    static final long serialVersionUID = 3491141966387921974L;
    public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF", 0);
    public static final Level FATAL = new Level(50000, "FATAL", 0);
    public static final Level ERROR = new Level(40000, "ERROR", 3);
    public static final Level WARN = new Level(SFTPEngine.DEFAULT_TIMEOUT_MS, "WARN", 4);
    public static final Level INFO = new Level(20000, "INFO", 6);
    public static final Level DEBUG = new Level(10000, "DEBUG", 7);
    public static final Level TRACE = new Level(5000, "TRACE", 7);
    public static final Level ALL = new Level(PKIFailureInfo.systemUnavail, "ALL", 7);

    public Level(int i, String str, int i2) {
        super(i, str, i2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.level = objectInputStream.readInt();
        this.syslogEquivalent = objectInputStream.readInt();
        String utf = objectInputStream.readUTF();
        this.levelStr = utf;
        if (utf == null) {
            this.levelStr = HttpUrl.FRAGMENT_ENCODE_SET;
        }
    }

    private Object readResolve() throws ObjectStreamException {
        return getClass() == Level.class ? toLevel(this.level) : this;
    }

    public static Level toLevel(int i, Level level) {
        if (i == Integer.MIN_VALUE) {
            return ALL;
        }
        if (i == 5000) {
            return TRACE;
        }
        if (i == 10000) {
            return DEBUG;
        }
        if (i == 20000) {
            return INFO;
        }
        if (i == 30000) {
            return WARN;
        }
        if (i == 40000) {
            return ERROR;
        }
        if (i != 50000) {
            return i != Integer.MAX_VALUE ? level : OFF;
        }
        return FATAL;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.level);
        objectOutputStream.writeInt(this.syslogEquivalent);
        objectOutputStream.writeUTF(this.levelStr);
    }

    public static Level toLevel(int i) {
        return toLevel(i, DEBUG);
    }
}
