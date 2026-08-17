package com.sun.jna.platform.win32.COM.tlb;

import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbCmdlineArgs;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbCoClass;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbDispInterface;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbEnum;
import com.sun.jna.platform.win32.COM.tlb.imp.TlbInterface;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbImp implements TlbConst {
    private TlbCmdlineArgs cmdlineArgs;
    private File comRootDir;
    private File outputDir;
    private TypeLibUtil typeLibUtil;

    public TlbImp(String[] strArr) {
        TlbCmdlineArgs tlbCmdlineArgs = new TlbCmdlineArgs(strArr);
        this.cmdlineArgs = tlbCmdlineArgs;
        boolean zIsTlbId = tlbCmdlineArgs.isTlbId();
        TlbCmdlineArgs tlbCmdlineArgs2 = this.cmdlineArgs;
        if (zIsTlbId) {
            this.typeLibUtil = new TypeLibUtil(tlbCmdlineArgs2.getRequiredParam(TlbConst.CMD_ARG_TYPELIB_ID), this.cmdlineArgs.getIntParam(TlbConst.CMD_ARG_TYPELIB_MAJOR_VERSION), this.cmdlineArgs.getIntParam(TlbConst.CMD_ARG_TYPELIB_MINOR_VERSION));
            startCOM2Java();
            return;
        }
        boolean zIsTlbFile = tlbCmdlineArgs2.isTlbFile();
        TlbCmdlineArgs tlbCmdlineArgs3 = this.cmdlineArgs;
        if (!zIsTlbFile) {
            tlbCmdlineArgs3.showCmdHelp();
        } else {
            this.typeLibUtil = new TypeLibUtil(tlbCmdlineArgs3.getRequiredParam(TlbConst.CMD_ARG_TYPELIB_FILE));
            startCOM2Java();
        }
    }

    private void createCOMCoClass(int i, String str, TypeLibUtil typeLibUtil, String str2) throws IOException {
        writeTlbClass(new TlbCoClass(i, getPackageName(), typeLibUtil, str2));
    }

    private void createCOMDispInterface(int i, String str, TypeLibUtil typeLibUtil) throws IOException {
        writeTlbClass(new TlbDispInterface(i, str, typeLibUtil));
    }

    private void createCOMEnum(int i, String str, TypeLibUtil typeLibUtil) throws IOException {
        writeTlbClass(new TlbEnum(i, str, typeLibUtil));
    }

    private void createCOMInterface(int i, String str, TypeLibUtil typeLibUtil) throws IOException {
        writeTlbClass(new TlbInterface(i, str, typeLibUtil));
    }

    private void createDir() throws FileNotFoundException {
        String param = this.cmdlineArgs.getParam(TlbConst.CMD_ARG_OUTPUT_DIR);
        String str = "_jnaCOM_" + System.currentTimeMillis() + "\\myPackage\\" + this.typeLibUtil.getName().toLowerCase() + "\\";
        if (param != null) {
            this.comRootDir = new File(param + "\\" + str);
        } else {
            this.comRootDir = new File(System.getProperty("java.io.tmpdir") + "\\" + str);
        }
        if (this.comRootDir.exists()) {
            this.comRootDir.delete();
        }
        if (this.comRootDir.mkdirs()) {
            logInfo("Output directory sucessfully created.");
            return;
        }
        throw new FileNotFoundException("Output directory NOT sucessfully created to: " + this.comRootDir.toString());
    }

    private String getPackageName() {
        return "myPackage." + this.typeLibUtil.getName().toLowerCase();
    }

    public static void logInfo(String str) {
        System.out.println(str);
    }

    public static void main(String[] strArr) {
        new TlbImp(strArr);
    }

    private void writeTextFile(String str, String str2) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.comRootDir + File.separator + str));
        bufferedOutputStream.write(str2.getBytes());
        bufferedOutputStream.close();
    }

    private void writeTlbClass(TlbBase tlbBase) throws IOException {
        writeTextFile(tlbBase.getFilename(), tlbBase.getClassBuffer().toString());
    }

    public void startCOM2Java() {
        try {
            createDir();
            String bindingMode = this.cmdlineArgs.getBindingMode();
            int typeInfoCount = this.typeLibUtil.getTypeInfoCount();
            for (int i = 0; i < typeInfoCount; i++) {
                int i2 = this.typeLibUtil.getTypeInfoType(i).value;
                if (i2 == 0) {
                    createCOMEnum(i, getPackageName(), this.typeLibUtil);
                } else if (i2 == 1) {
                    logInfo("'TKIND_RECORD' objects are currently not supported!");
                } else if (i2 == 2) {
                    logInfo("'TKIND_MODULE' objects are currently not supported!");
                } else if (i2 == 3) {
                    createCOMInterface(i, getPackageName(), this.typeLibUtil);
                } else if (i2 == 4) {
                    createCOMDispInterface(i, getPackageName(), this.typeLibUtil);
                } else if (i2 == 5) {
                    createCOMCoClass(i, getPackageName(), this.typeLibUtil, bindingMode);
                } else if (i2 == 6) {
                    logInfo("'TKIND_ALIAS' objects are currently not supported!");
                } else if (i2 == 7) {
                    logInfo("'TKIND_UNION' objects are currently not supported!");
                }
            }
            logInfo(typeInfoCount + " files sucessfully written to: " + this.comRootDir.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
