package com.sun.jna.platform.win32;

import com.sun.jna.platform.FileMonitor;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class W32FileMonitor extends FileMonitor {
    private static final int BUFFER_SIZE = 4096;
    private static final Logger LOG = Logger.getLogger(W32FileMonitor.class.getName());
    private static int watcherThreadID;
    private WinNT.HANDLE port;
    private Thread watcher;
    private final Map<File, FileInfo> fileMap = new HashMap();
    private final Map<WinNT.HANDLE, FileInfo> handleMap = new HashMap();
    private boolean disposing = false;

    public class FileInfo {
        public final File file;
        public final WinNT.HANDLE handle;
        public final int notifyMask;
        public final boolean recursive;
        public final WinNT.FILE_NOTIFY_INFORMATION info = new WinNT.FILE_NOTIFY_INFORMATION(4096);
        public final IntByReference infoLength = new IntByReference();
        public final WinBase.OVERLAPPED overlapped = new WinBase.OVERLAPPED();

        public FileInfo(File file, WinNT.HANDLE handle, int i, boolean z) {
            this.file = file;
            this.handle = handle;
            this.notifyMask = i;
            this.recursive = z;
        }
    }

    private int convertMask(int i) {
        int i2 = (i & 1) != 0 ? 64 : 0;
        if ((i & 2) != 0) {
            i2 |= 3;
        }
        if ((i & 4) != 0) {
            i2 |= 16;
        }
        if ((i & 48) != 0) {
            i2 |= 3;
        }
        if ((i & 64) != 0) {
            i2 |= 8;
        }
        if ((i & 8) != 0) {
            i2 |= 32;
        }
        if ((i & 128) != 0) {
            i2 |= 4;
        }
        return (i & 256) != 0 ? i2 | 256 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleChanges(FileInfo fileInfo) throws IOException {
        FileMonitor.FileEvent fileEvent;
        Kernel32 kernel32 = Kernel32.INSTANCE;
        WinNT.FILE_NOTIFY_INFORMATION next = fileInfo.info;
        next.read();
        do {
            File file = new File(fileInfo.file, next.getFilename());
            int i = next.Action;
            if (i == 0) {
                fileEvent = null;
            } else if (i == 1) {
                fileEvent = new FileMonitor.FileEvent(file, 1);
            } else if (i == 2) {
                fileEvent = new FileMonitor.FileEvent(file, 2);
            } else if (i == 3) {
                fileEvent = new FileMonitor.FileEvent(file, 4);
            } else if (i == 4) {
                fileEvent = new FileMonitor.FileEvent(file, 16);
            } else if (i != 5) {
                LOG.log(Level.WARNING, "Unrecognized file action ''{0}''", Integer.valueOf(i));
                fileEvent = null;
            } else {
                fileEvent = new FileMonitor.FileEvent(file, 32);
            }
            if (fileEvent != null) {
                notify(fileEvent);
            }
            next = next.next();
        } while (next != null);
        if (!fileInfo.file.exists()) {
            unwatch(fileInfo.file);
            return;
        }
        WinNT.HANDLE handle = fileInfo.handle;
        WinNT.FILE_NOTIFY_INFORMATION file_notify_information = fileInfo.info;
        if (kernel32.ReadDirectoryChangesW(handle, file_notify_information, file_notify_information.size(), fileInfo.recursive, fileInfo.notifyMask, fileInfo.infoLength, fileInfo.overlapped, null) || this.disposing) {
            return;
        }
        int iGetLastError = kernel32.GetLastError();
        StringBuilder sb = new StringBuilder("ReadDirectoryChangesW failed on ");
        sb.append(fileInfo.file);
        String messageFromLastErrorCode = Kernel32Util.formatMessageFromLastErrorCode(iGetLastError);
        sb.append(": '");
        sb.append(messageFromLastErrorCode);
        sb.append("' (");
        sb.append(iGetLastError);
        sb.append(")");
        throw new IOException(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FileInfo waitForChange() {
        FileInfo fileInfo;
        IntByReference intByReference = new IntByReference();
        BaseTSD.ULONG_PTRByReference uLONG_PTRByReference = new BaseTSD.ULONG_PTRByReference();
        if (!Kernel32.INSTANCE.GetQueuedCompletionStatus(this.port, intByReference, uLONG_PTRByReference, new PointerByReference(), -1)) {
            return null;
        }
        synchronized (this) {
            fileInfo = this.handleMap.get(new WinNT.HANDLE(uLONG_PTRByReference.getValue().toPointer()));
        }
        return fileInfo;
    }

    @Override // com.sun.jna.platform.FileMonitor
    public synchronized void dispose() {
        try {
            this.disposing = true;
            Object[] array = this.fileMap.keySet().toArray();
            int i = 0;
            while (!this.fileMap.isEmpty()) {
                unwatch((File) array[i]);
                i++;
            }
            Kernel32 kernel32 = Kernel32.INSTANCE;
            kernel32.PostQueuedCompletionStatus(this.port, 0, null, null);
            kernel32.CloseHandle(this.port);
            this.port = null;
            this.watcher = null;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.sun.jna.platform.FileMonitor
    public synchronized void unwatch(File file) {
        FileInfo fileInfoRemove = this.fileMap.remove(file);
        if (fileInfoRemove != null) {
            this.handleMap.remove(fileInfoRemove.handle);
            Kernel32.INSTANCE.CloseHandle(fileInfoRemove.handle);
        }
    }

    @Override // com.sun.jna.platform.FileMonitor
    public synchronized void watch(File file, int i, boolean z) throws Throwable {
        File parentFile;
        boolean z2;
        try {
            try {
                if (file.isDirectory()) {
                    parentFile = file;
                    z2 = z;
                } else {
                    parentFile = file.getParentFile();
                    z2 = false;
                }
                while (parentFile != null && !parentFile.exists()) {
                    parentFile = parentFile.getParentFile();
                    z2 = true;
                }
                if (parentFile == null) {
                    throw new FileNotFoundException("No ancestor found for " + file);
                }
                Kernel32 kernel32 = Kernel32.INSTANCE;
                WinNT.HANDLE handleCreateFile = kernel32.CreateFile(file.getAbsolutePath(), 1, 7, null, 3, 1107296256, null);
                WinNT.HANDLE handle = WinBase.INVALID_HANDLE_VALUE;
                if (handle.equals(handleCreateFile)) {
                    throw new IOException("Unable to open " + file + " (" + kernel32.GetLastError() + ")");
                }
                int iConvertMask = convertMask(i);
                FileInfo fileInfo = new FileInfo(file, handleCreateFile, iConvertMask, z2);
                this.fileMap.put(file, fileInfo);
                this.handleMap.put(handleCreateFile, fileInfo);
                WinNT.HANDLE handleCreateIoCompletionPort = kernel32.CreateIoCompletionPort(handleCreateFile, this.port, handleCreateFile.getPointer(), 0);
                this.port = handleCreateIoCompletionPort;
                if (handle.equals(handleCreateIoCompletionPort)) {
                    throw new IOException("Unable to create/use I/O Completion port for " + file + " (" + kernel32.GetLastError() + ")");
                }
                WinNT.FILE_NOTIFY_INFORMATION file_notify_information = fileInfo.info;
                if (kernel32.ReadDirectoryChangesW(handleCreateFile, file_notify_information, file_notify_information.size(), z2, iConvertMask, fileInfo.infoLength, fileInfo.overlapped, null)) {
                    if (this.watcher == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("W32 File Monitor-");
                        int i2 = watcherThreadID;
                        watcherThreadID = i2 + 1;
                        sb.append(i2);
                        Thread thread = new Thread(sb.toString()) { // from class: com.sun.jna.platform.win32.W32FileMonitor.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                while (true) {
                                    FileInfo fileInfoWaitForChange = W32FileMonitor.this.waitForChange();
                                    W32FileMonitor w32FileMonitor = W32FileMonitor.this;
                                    if (fileInfoWaitForChange == null) {
                                        synchronized (w32FileMonitor) {
                                            try {
                                                if (W32FileMonitor.this.fileMap.isEmpty()) {
                                                    W32FileMonitor.this.watcher = null;
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                throw th;
                                            }
                                        }
                                    } else {
                                        try {
                                            w32FileMonitor.handleChanges(fileInfoWaitForChange);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        };
                        this.watcher = thread;
                        thread.setDaemon(true);
                        this.watcher.start();
                    }
                    return;
                }
                int iGetLastError = kernel32.GetLastError();
                throw new IOException("ReadDirectoryChangesW failed on " + fileInfo.file + ", handle " + handleCreateFile + ": '" + Kernel32Util.formatMessageFromLastErrorCode(iGetLastError) + "' (" + iGetLastError + ")");
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
