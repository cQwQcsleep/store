package com.sun.jna.platform.mac;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.platform.FileUtils;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.PointerByReference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MacFileUtils extends FileUtils {

    public interface FileManager extends Library {
        public static final FileManager INSTANCE = (FileManager) Native.load("CoreServices", FileManager.class);
        public static final int kFSFileOperationDefaultOptions = 0;
        public static final int kFSFileOperationsDoNotMoveAcrossVolumes = 4;
        public static final int kFSFileOperationsOverwrite = 1;
        public static final int kFSFileOperationsSkipPreflight = 8;
        public static final int kFSFileOperationsSkipSourcePermissionErrors = 2;
        public static final int kFSPathDefaultOptions = 0;
        public static final int kFSPathMakeRefDoNotFollowLeafSymlink = 1;

        @Structure.FieldOrder({"hidden"})
        public static class FSRef extends Structure {
            public byte[] hidden = new byte[80];
        }

        int FSMoveObjectToTrashSync(FSRef fSRef, FSRef fSRef2, int i);

        int FSPathMakeRef(String str, int i, ByteByReference byteByReference);

        int FSPathMakeRefWithOptions(String str, int i, FSRef fSRef, ByteByReference byteByReference);

        int FSPathMoveObjectToTrashSync(String str, PointerByReference pointerByReference, int i);

        int FSRefMakePath(FSRef fSRef, byte[] bArr, int i);
    }

    @Override // com.sun.jna.platform.FileUtils
    public boolean hasTrash() {
        return true;
    }

    @Override // com.sun.jna.platform.FileUtils
    public void moveToTrash(File... fileArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (File file : fileArr) {
            FileManager.FSRef fSRef = new FileManager.FSRef();
            FileManager fileManager = FileManager.INSTANCE;
            int iFSPathMakeRefWithOptions = fileManager.FSPathMakeRefWithOptions(file.getAbsolutePath(), 1, fSRef, null);
            if (iFSPathMakeRefWithOptions != 0) {
                arrayList.add(file + " (FSRef: " + iFSPathMakeRefWithOptions + ")");
            } else {
                int iFSMoveObjectToTrashSync = fileManager.FSMoveObjectToTrashSync(fSRef, null, 0);
                if (iFSMoveObjectToTrashSync != 0) {
                    arrayList.add(file + " (" + iFSMoveObjectToTrashSync + ")");
                }
            }
        }
        if (arrayList.size() <= 0) {
            return;
        }
        r8g.a("The following files could not be trashed: ", arrayList);
    }
}
