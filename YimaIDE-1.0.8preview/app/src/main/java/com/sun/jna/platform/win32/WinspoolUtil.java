package com.sun.jna.platform.win32;

import com.sun.jna.ptr.IntByReference;
import defpackage.kka;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class WinspoolUtil {
    public static Winspool.PRINTER_INFO_2[] getAllPrinterInfo2() {
        return getPrinterInfo2(6);
    }

    public static Winspool.JOB_INFO_1[] getJobInfo1(WinNT.HANDLEByReference hANDLEByReference) {
        Winspool.JOB_INFO_1 job_info_1;
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        Winspool.INSTANCE.EnumJobs(hANDLEByReference.getValue(), 0, 255, 1, null, 0, intByReference, intByReference2);
        if (intByReference.getValue() <= 0) {
            return new Winspool.JOB_INFO_1[0];
        }
        int iGetLastError = 0;
        do {
            job_info_1 = new Winspool.JOB_INFO_1(intByReference.getValue());
            if (!Winspool.INSTANCE.EnumJobs(hANDLEByReference.getValue(), 0, 255, 1, job_info_1.getPointer(), intByReference.getValue(), intByReference, intByReference2)) {
                iGetLastError = Kernel32.INSTANCE.GetLastError();
            }
        } while (iGetLastError == 122);
        if (iGetLastError != 0) {
            kka.a(iGetLastError);
            return null;
        }
        if (intByReference2.getValue() <= 0) {
            return new Winspool.JOB_INFO_1[0];
        }
        job_info_1.read();
        return (Winspool.JOB_INFO_1[]) job_info_1.toArray(intByReference2.getValue());
    }

    public static Winspool.PRINTER_INFO_1[] getPrinterInfo1() {
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        Winspool winspool = Winspool.INSTANCE;
        winspool.EnumPrinters(2, null, 1, null, 0, intByReference, intByReference2);
        if (intByReference.getValue() <= 0) {
            return new Winspool.PRINTER_INFO_1[0];
        }
        Winspool.PRINTER_INFO_1 printer_info_1 = new Winspool.PRINTER_INFO_1(intByReference.getValue());
        if (!winspool.EnumPrinters(2, null, 1, printer_info_1.getPointer(), intByReference.getValue(), intByReference, intByReference2)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        printer_info_1.read();
        return (Winspool.PRINTER_INFO_1[]) printer_info_1.toArray(intByReference2.getValue());
    }

    public static Winspool.PRINTER_INFO_2 getPrinterInfo2(String str) {
        Winspool.PRINTER_INFO_2 printer_info_2;
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        WinNT.HANDLEByReference hANDLEByReference = new WinNT.HANDLEByReference();
        Winspool winspool = Winspool.INSTANCE;
        Win32Exception win32Exception = null;
        if (!winspool.OpenPrinter(str, hANDLEByReference, null)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        try {
            try {
                winspool.GetPrinter(hANDLEByReference.getValue(), 2, null, 0, intByReference);
                if (intByReference.getValue() <= 0) {
                    Winspool.PRINTER_INFO_2 printer_info_3 = new Winspool.PRINTER_INFO_2();
                    if (!winspool.ClosePrinter(hANDLEByReference.getValue())) {
                        new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    return printer_info_3;
                }
                printer_info_2 = new Winspool.PRINTER_INFO_2(intByReference.getValue());
                try {
                    if (!winspool.GetPrinter(hANDLEByReference.getValue(), 2, printer_info_2.getPointer(), intByReference.getValue(), intByReference2)) {
                        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    printer_info_2.read();
                    if (!winspool.ClosePrinter(hANDLEByReference.getValue())) {
                        new Win32Exception(Kernel32.INSTANCE.GetLastError());
                    }
                    if (win32Exception == null) {
                        return printer_info_2;
                    }
                    throw win32Exception;
                } catch (Win32Exception e) {
                    win32Exception = e;
                    if (!Winspool.INSTANCE.ClosePrinter(hANDLEByReference.getValue())) {
                        new Win32Exception(Kernel32.INSTANCE.GetLastError()).addSuppressedReflected(win32Exception);
                    }
                }
            } catch (Throwable th) {
                if (Winspool.INSTANCE.ClosePrinter(hANDLEByReference.getValue())) {
                    throw th;
                }
                new Win32Exception(Kernel32.INSTANCE.GetLastError());
                throw th;
            }
        } catch (Win32Exception e2) {
            win32Exception = e2;
            printer_info_2 = null;
        }
    }

    public static Winspool.PRINTER_INFO_4[] getPrinterInfo4() {
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        Winspool winspool = Winspool.INSTANCE;
        winspool.EnumPrinters(2, null, 4, null, 0, intByReference, intByReference2);
        if (intByReference.getValue() <= 0) {
            return new Winspool.PRINTER_INFO_4[0];
        }
        Winspool.PRINTER_INFO_4 printer_info_4 = new Winspool.PRINTER_INFO_4(intByReference.getValue());
        if (!winspool.EnumPrinters(2, null, 4, printer_info_4.getPointer(), intByReference.getValue(), intByReference, intByReference2)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        printer_info_4.read();
        return (Winspool.PRINTER_INFO_4[]) printer_info_4.toArray(intByReference2.getValue());
    }

    private static Winspool.PRINTER_INFO_2[] getPrinterInfo2(int i) {
        IntByReference intByReference = new IntByReference();
        IntByReference intByReference2 = new IntByReference();
        Winspool winspool = Winspool.INSTANCE;
        winspool.EnumPrinters(i, null, 2, null, 0, intByReference, intByReference2);
        if (intByReference.getValue() <= 0) {
            return new Winspool.PRINTER_INFO_2[0];
        }
        Winspool.PRINTER_INFO_2 printer_info_2 = new Winspool.PRINTER_INFO_2(intByReference.getValue());
        if (winspool.EnumPrinters(i, null, 2, printer_info_2.getPointer(), intByReference.getValue(), intByReference, intByReference2)) {
            printer_info_2.read();
            return (Winspool.PRINTER_INFO_2[]) printer_info_2.toArray(intByReference2.getValue());
        }
        throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
    }

    public static Winspool.PRINTER_INFO_2[] getPrinterInfo2() {
        return getPrinterInfo2(2);
    }
}
