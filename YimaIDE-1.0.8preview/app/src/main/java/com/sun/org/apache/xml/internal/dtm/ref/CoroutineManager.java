package com.sun.org.apache.xml.internal.dtm.ref;

import com.sun.org.apache.xml.internal.res.XMLMessages;
import java.util.BitSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CoroutineManager {
    static final int ANYBODY = -1;
    static final int NOBODY = -1;
    static final int m_unreasonableId = 1024;
    BitSet m_activeIDs = new BitSet();
    Object m_yield = null;
    int m_nextCoroutine = -1;

    public synchronized Object co_entry_pause(int i) throws NoSuchMethodException {
        if (!this.m_activeIDs.get(i)) {
            throw new NoSuchMethodException();
        }
        while (this.m_nextCoroutine != i) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
        return this.m_yield;
    }

    public synchronized void co_exit(int i) {
        this.m_activeIDs.clear(i);
        this.m_nextCoroutine = -1;
        notify();
    }

    public synchronized void co_exit_to(Object obj, int i, int i2) throws NoSuchMethodException {
        if (!this.m_activeIDs.get(i2)) {
            throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_NOT_AVAIL", new Object[]{Integer.toString(i2)}));
        }
        this.m_yield = obj;
        this.m_nextCoroutine = i2;
        this.m_activeIDs.clear(i);
        notify();
    }

    public synchronized int co_joinCoroutineSet(int i) {
        try {
            if (i < 0) {
                i = 0;
                while (i < 1024 && this.m_activeIDs.get(i)) {
                    i++;
                }
                if (i >= 1024) {
                    return -1;
                }
            } else if (i >= 1024 || this.m_activeIDs.get(i)) {
                return -1;
            }
            this.m_activeIDs.set(i);
            return i;
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized Object co_resume(Object obj, int i, int i2) throws NoSuchMethodException {
        int i3;
        if (!this.m_activeIDs.get(i2)) {
            throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_NOT_AVAIL", new Object[]{Integer.toString(i2)}));
        }
        this.m_yield = obj;
        this.m_nextCoroutine = i2;
        notify();
        while (true) {
            i3 = this.m_nextCoroutine;
            if (i3 == i && i3 != -1 && i3 != -1) {
                break;
            }
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
        if (i3 == -1) {
            co_exit(i);
            throw new NoSuchMethodException(XMLMessages.createXMLMessage("ER_COROUTINE_CO_EXIT", null));
        }
        return this.m_yield;
    }
}
