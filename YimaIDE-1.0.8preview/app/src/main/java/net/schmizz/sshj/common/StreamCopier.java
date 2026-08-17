package net.schmizz.sshj.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.schmizz.concurrent.Event;
import net.schmizz.concurrent.ExceptionChainer;
import org.slf4j.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class StreamCopier {
    private static final Listener NULL_LISTENER = new Listener() { // from class: net.schmizz.sshj.common.StreamCopier.1
        @Override // net.schmizz.sshj.common.StreamCopier.Listener
        public void reportProgress(long j) {
        }
    };
    private final InputStream in;
    private final Logger log;
    private final LoggerFactory loggerFactory;
    private final OutputStream out;
    private Listener listener = NULL_LISTENER;
    private int bufSize = 1;
    private boolean keepFlushing = true;
    private long length = -1;

    public interface Listener {
        void reportProgress(long j) throws IOException;
    }

    public StreamCopier(InputStream inputStream, OutputStream outputStream, LoggerFactory loggerFactory) {
        this.in = inputStream;
        this.out = outputStream;
        this.loggerFactory = loggerFactory;
        this.log = loggerFactory.getLogger(getClass());
    }

    private Event<IOException> spawn(String str, boolean z) {
        Event<IOException> event = new Event<>("copyDone", new ExceptionChainer<IOException>() { // from class: net.schmizz.sshj.common.StreamCopier.2
            @Override // net.schmizz.concurrent.ExceptionChainer
            public IOException chain(Throwable th) {
                return th instanceof IOException ? (IOException) th : new IOException(th);
            }
        }, this.loggerFactory);
        new Thread(str, z, event) { // from class: net.schmizz.sshj.common.StreamCopier.3
            final /* synthetic */ boolean val$daemon;
            final /* synthetic */ Event val$doneEvent;
            final /* synthetic */ String val$name;

            {
                this.val$name = str;
                this.val$daemon = z;
                this.val$doneEvent = event;
                setName(str);
                setDaemon(z);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    StreamCopier.this.log.debug("Will copy from {} to {}", StreamCopier.this.in, StreamCopier.this.out);
                    StreamCopier.this.copy();
                    StreamCopier.this.log.debug("Done copying from {}", StreamCopier.this.in);
                    this.val$doneEvent.set();
                } catch (IOException e) {
                    StreamCopier.this.log.error(String.format("In pipe from %1$s to %2$s", StreamCopier.this.in.toString(), StreamCopier.this.out.toString()), e);
                    this.val$doneEvent.deliverError(e);
                }
            }
        }.start();
        return event;
    }

    private long write(byte[] bArr, long j, int i) throws IOException {
        this.out.write(bArr, 0, i);
        if (this.keepFlushing) {
            this.out.flush();
        }
        long j2 = i;
        this.listener.reportProgress(j + j2);
        return j2;
    }

    public StreamCopier bufSize(int i) {
        this.bufSize = i;
        return this;
    }

    public long copy() throws IOException {
        int i;
        byte[] bArr = new byte[this.bufSize];
        long jNanoTime = System.nanoTime();
        long jWrite = 0;
        if (this.length == -1) {
            while (true) {
                i = this.in.read(bArr);
                if (i == -1) {
                    break;
                }
                jWrite += write(bArr, jWrite, i);
            }
        } else {
            long jWrite2 = 0;
            int i2 = 0;
            while (true) {
                long j = this.length;
                if (jWrite2 >= j || (i2 = this.in.read(bArr, 0, (int) Math.min(this.bufSize, j - jWrite2))) == -1) {
                    break;
                }
                jWrite2 += write(bArr, jWrite2, i2);
            }
            i = i2;
            jWrite = jWrite2;
        }
        if (!this.keepFlushing) {
            this.out.flush();
        }
        double dNanoTime = ((System.nanoTime() - jNanoTime) / 1000000) / 1000.0d;
        double d = jWrite / 1024.0d;
        this.log.debug(String.format("%1$,.1f KiB transferred in %2$,.1f seconds (%3$,.2f KiB/s)", Double.valueOf(d), Double.valueOf(dNanoTime), Double.valueOf(d / dNanoTime)));
        if (i == -1) {
            this.out.close();
            if (this.length != -1) {
                throw new IOException("Encountered EOF, could not transfer " + this.length + " bytes");
            }
        }
        return jWrite;
    }

    public StreamCopier keepFlushing(boolean z) {
        this.keepFlushing = z;
        return this;
    }

    public StreamCopier length(long j) {
        this.length = j;
        return this;
    }

    public StreamCopier listener(Listener listener) {
        if (listener == null) {
            this.listener = NULL_LISTENER;
            return this;
        }
        this.listener = listener;
        return this;
    }

    public Event<IOException> spawnDaemon(String str) {
        return spawn(str, true);
    }

    public Event<IOException> spawn(String str) {
        return spawn(str, false);
    }
}
