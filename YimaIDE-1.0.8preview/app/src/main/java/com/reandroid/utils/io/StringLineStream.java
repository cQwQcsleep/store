package com.reandroid.utils.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringLineStream implements Iterator<String>, Closeable {
    private final InputStream inputStream;
    private String mCurrent;
    private IOException mError;
    private boolean mFinished;
    private int mLineNumber;
    private BufferedReader mReader;

    public StringLineStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private String getCurrent() {
        String str = this.mCurrent;
        if (str != null) {
            return str;
        }
        String next = readNext();
        this.mCurrent = next;
        return next;
    }

    private BufferedReader getReader() {
        if (this.mReader == null) {
            this.mReader = new BufferedReader(new InputStreamReader(this.inputStream));
        }
        return this.mReader;
    }

    private String readNext() {
        if (this.mFinished) {
            return null;
        }
        try {
            String line = getReader().readLine();
            if (line != null) {
                this.mLineNumber++;
                return line;
            }
            this.mFinished = true;
            close();
            return line;
        } catch (IOException e) {
            this.mError = e;
            this.mFinished = true;
            try {
                close();
            } catch (Exception unused) {
            }
            return null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.mFinished = true;
        this.inputStream.close();
        BufferedReader bufferedReader = this.mReader;
        if (bufferedReader == null) {
            return;
        }
        try {
            bufferedReader.close();
        } catch (Exception unused) {
        }
    }

    public IOException getError() {
        return this.mError;
    }

    public int getLineNumber() {
        return this.mLineNumber;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return getCurrent() != null;
    }

    @Override // java.util.Iterator
    public String next() {
        String current = getCurrent();
        this.mCurrent = null;
        return current;
    }

    public String toString() {
        String str = this.mCurrent;
        StringBuilder sb = new StringBuilder("line=");
        sb.append(this.mLineNumber);
        if (this.mFinished) {
            sb.append(", FINISHED");
        }
        if (str != null) {
            sb.append(", ");
            sb.append(str);
        }
        return sb.toString();
    }
}
