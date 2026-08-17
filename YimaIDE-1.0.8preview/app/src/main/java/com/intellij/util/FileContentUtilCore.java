package com.intellij.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFilePropertyChangeEvent;
import com.intellij.util.FileContentUtilCore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FileContentUtilCore {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "files", "com/intellij/util/FileContentUtilCore", "reparseFiles"));
    }

    public static /* synthetic */ void a(Collection collection) {
        HashSet hashSet = new HashSet();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            VirtualFile virtualFile = (VirtualFile) it.next();
            if (virtualFile != null && !virtualFile.isDirectory() && virtualFile.isValid()) {
                hashSet.add(new VFilePropertyChangeEvent("FileContentUtilCore.saveOrReload", virtualFile, "name", virtualFile.getName(), virtualFile.getName()));
            }
        }
        BulkFileListener bulkFileListener = (BulkFileListener) ApplicationManager.getApplication().getMessageBus().syncPublisher(VirtualFileManager.VFS_CHANGES);
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(hashSet));
        bulkFileListener.before(listUnmodifiableList);
        bulkFileListener.after(listUnmodifiableList);
        ForcefulReparseModificationTracker.increment();
    }

    public static void reparseFiles(final Collection<? extends VirtualFile> collection) {
        if (collection == null) {
            $$$reportNull$$$0(1);
        }
        ApplicationManager.getApplication().runWriteAction(new Runnable() { // from class: cp4
            @Override // java.lang.Runnable
            public final void run() {
                FileContentUtilCore.a(collection);
            }
        });
    }

    public static void reparseFiles(VirtualFile... virtualFileArr) {
        if (virtualFileArr == null) {
            $$$reportNull$$$0(0);
        }
        reparseFiles(Arrays.asList(virtualFileArr));
    }
}
