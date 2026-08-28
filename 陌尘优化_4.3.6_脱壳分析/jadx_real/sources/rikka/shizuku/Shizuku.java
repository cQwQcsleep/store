package rikka.shizuku;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import moe.shizuku.server.IShizukuApplication;
import moe.shizuku.server.IShizukuService;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class Shizuku {
    private static IBinder binder;
    private static IShizukuService service;
    private static int serverUid = -1;
    private static int serverApiVersion = -1;
    private static int serverPatchVersion = -1;
    private static String serverContext = null;
    private static boolean permissionGranted = false;
    private static boolean shouldShowRequestPermissionRationale = false;
    private static boolean preV11 = false;
    private static boolean binderReady = false;
    private static final IShizukuApplication SHIZUKU_APPLICATION = new IShizukuApplication.Stub() { // from class: rikka.shizuku.Shizuku.1
        @Override // moe.shizuku.server.IShizukuApplication
        public void bindApplication(Bundle bundle) {
            int unused = Shizuku.serverUid = bundle.getInt(NPStringFog.decode("1D18041B1B0A125F131A040C02064C150002020940140705"), -1);
            int unused2 = Shizuku.serverApiVersion = bundle.getInt(NPStringFog.decode("1D18041B1B0A125F131A040C02064C150002020940170B13140C1D00"), -1);
            int unused3 = Shizuku.serverPatchVersion = bundle.getInt(NPStringFog.decode("1D18041B1B0A125F131A040C02064C150002020940110F15040D5F18151F12070E09"), -1);
            String unused4 = Shizuku.serverContext = bundle.getString(NPStringFog.decode("1D18041B1B0A125F131A040C02064C150002020940120B02080B060B0819"));
            boolean unused5 = Shizuku.permissionGranted = bundle.getBoolean(NPStringFog.decode("1D18041B1B0A125F131A040C02064C150002020940110B130A0C011D19020F430615041C1A1509"), false);
            boolean unused6 = Shizuku.shouldShowRequestPermissionRationale = bundle.getBoolean(NPStringFog.decode("1D18041B1B0A125F131A040C02064C15000202094012060E1209164303050E194C1500031B151E15431102171F07031E08010F4A17131A19020F0F0D02"), false);
            Shizuku.scheduleBinderReceivedListeners();
        }

        @Override // moe.shizuku.server.IShizukuApplication
        public void dispatchRequestPermissionResult(int i, Bundle bundle) {
            Shizuku.scheduleRequestPermissionResultListener(i, bundle.getBoolean(NPStringFog.decode("1D18041B1B0A125F000B0118041D154A15171C1D04121D08080B5F1C151D0D174C06091E01070805"), false) ? 0 : -1);
        }

        @Override // moe.shizuku.server.IShizukuApplication
        public void showPermissionConfirmation(int i, int i2, String str, int i3) {
        }
    };
    private static final IBinder.DeathRecipient DEATH_RECIPIENT = new IBinder.DeathRecipient() { // from class: rikka.shizuku.Shizuku$$ExternalSyntheticLambda2
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            Shizuku.lambda$static$0();
        }
    };
    private static final List<OnBinderReceivedListener> RECEIVED_LISTENERS = new CopyOnWriteArrayList();
    private static final List<OnBinderDeadListener> DEAD_LISTENERS = new CopyOnWriteArrayList();
    private static final List<OnRequestPermissionResultListener> PERMISSION_LISTENERS = new CopyOnWriteArrayList();
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public interface OnBinderDeadListener {
        void onBinderDead();
    }

    public interface OnBinderReceivedListener {
        void onBinderReceived();
    }

    public interface OnRequestPermissionResultListener {
        void onRequestPermissionResult(int i, int i2);
    }

    public static class UserServiceArgs {
        final ComponentName componentName;
        String processName;
        String tag;
        int versionCode = 1;
        boolean debuggable = false;
        boolean daemon = true;
        boolean use32BitAppProcess = false;

        public UserServiceArgs(ComponentName componentName) {
            this.componentName = componentName;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle forAdd() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A061D0300020F0B0F13"), this.componentName);
            bundle.putBoolean(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A01170C050A060F030B00"), this.debuggable);
            bundle.putInt(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A13171C03040E004C040A160B"), this.versionCode);
            bundle.putBoolean(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A01130B1D020F"), this.daemon);
            bundle.putBoolean(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A10010B5D5E5343030E115F0F001D4C1E130806171D03"), this.use32BitAppProcess);
            bundle.putString(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A1500011308121D4C09041F0B"), (String) Objects.requireNonNull(this.processName, NPStringFog.decode("1E0202020B1214451C0F1D08411D1401031B165000141D15470B1D1A500F044E0F12091E")));
            if (this.tag != null) {
                bundle.putString(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A111309"), this.tag);
            }
            return bundle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle forRemove() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A061D0300020F0B0F13"), this.componentName);
            if (this.tag != null) {
                bundle.putString(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A111309"), this.tag);
            }
            return bundle;
        }

        private UserServiceArgs use32BitAppProcess(boolean z) {
            this.use32BitAppProcess = z;
            return this;
        }

        public UserServiceArgs daemon(boolean z) {
            this.daemon = z;
            return this;
        }

        public UserServiceArgs debuggable(boolean z) {
            this.debuggable = z;
            return this;
        }

        public UserServiceArgs processNameSuffix(String str) {
            this.processName = str;
            return this;
        }

        public UserServiceArgs tag(String str) {
            this.tag = str;
            return this;
        }

        public UserServiceArgs version(int i) {
            this.versionCode = i;
            return this;
        }
    }

    public static void addBinderDeadListener(OnBinderDeadListener onBinderDeadListener) {
        DEAD_LISTENERS.add(onBinderDeadListener);
    }

    public static void addBinderReceivedListener(OnBinderReceivedListener onBinderReceivedListener) {
        addBinderReceivedListener((OnBinderReceivedListener) Objects.requireNonNull(onBinderReceivedListener), false);
    }

    private static void addBinderReceivedListener(final OnBinderReceivedListener onBinderReceivedListener, boolean z) {
        if (z && binderReady) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onBinderReceivedListener.onBinderReceived();
            } else {
                Handler handler = MAIN_HANDLER;
                Objects.requireNonNull(onBinderReceivedListener);
                handler.post(new Runnable() { // from class: rikka.shizuku.Shizuku$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        onBinderReceivedListener.onBinderReceived();
                    }
                });
            }
        }
        RECEIVED_LISTENERS.add(onBinderReceivedListener);
    }

    public static void addBinderReceivedListenerSticky(OnBinderReceivedListener onBinderReceivedListener) {
        addBinderReceivedListener((OnBinderReceivedListener) Objects.requireNonNull(onBinderReceivedListener), true);
    }

    public static void addRequestPermissionResultListener(OnRequestPermissionResultListener onRequestPermissionResultListener) {
        PERMISSION_LISTENERS.add(onRequestPermissionResultListener);
    }

    public static void attachUserService(IBinder iBinder, Bundle bundle) {
        try {
            requireService().attachUserService(iBinder, bundle);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static void bindUserService(UserServiceArgs userServiceArgs, ServiceConnection serviceConnection) {
        ShizukuServiceConnection shizukuServiceConnection = ShizukuServiceConnections.get(userServiceArgs);
        shizukuServiceConnection.addConnection(serviceConnection);
        try {
            requireService().addUserService(shizukuServiceConnection, userServiceArgs.forAdd());
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static int checkRemotePermission(String str) {
        if (serverUid == 0) {
            return 0;
        }
        try {
            return requireService().checkPermission(str);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static int checkSelfPermission() {
        if (permissionGranted) {
            return 0;
        }
        try {
            permissionGranted = requireService().checkSelfPermission();
            return permissionGranted ? 0 : -1;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchBinderDeadListeners() {
        Iterator<OnBinderDeadListener> it = DEAD_LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().onBinderDead();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dispatchBinderReceivedListeners() {
        Iterator<OnBinderReceivedListener> it = RECEIVED_LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().onBinderReceived();
        }
        binderReady = true;
    }

    public static void dispatchPermissionConfirmationResult(int i, int i2, int i3, Bundle bundle) {
        try {
            requireService().dispatchPermissionConfirmationResult(i, i2, i3, bundle);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dispatchRequestPermissionResultListener(int i, int i2) {
        Iterator<OnRequestPermissionResultListener> it = PERMISSION_LISTENERS.iterator();
        while (it.hasNext()) {
            it.next().onRequestPermissionResult(i, i2);
        }
    }

    public static void exit() {
        try {
            requireService().exit();
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static IBinder getBinder() {
        return binder;
    }

    public static int getFlagsForUid(int i, int i2) {
        try {
            return requireService().getFlagsForUid(i, i2);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static int getLatestServiceVersion() {
        return 12;
    }

    public static String getSELinuxContext() {
        if (serverContext != null) {
            return serverContext;
        }
        try {
            serverContext = requireService().getSELinuxContext();
            return serverContext;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        } catch (SecurityException e2) {
            return null;
        }
    }

    public static int getServerPatchVersion() {
        return serverPatchVersion;
    }

    public static int getUid() {
        if (serverUid != -1) {
            return serverUid;
        }
        try {
            serverUid = requireService().getUid();
            return serverUid;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        } catch (SecurityException e2) {
            return -1;
        }
    }

    public static int getVersion() {
        if (serverApiVersion != -1) {
            return serverApiVersion;
        }
        try {
            serverApiVersion = requireService().getVersion();
            return serverApiVersion;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        } catch (SecurityException e2) {
            return -1;
        }
    }

    public static boolean isPreV11() {
        return preV11;
    }

    static /* synthetic */ void lambda$static$0() {
        binderReady = false;
        onBinderReceived(null, null);
    }

    public static ShizukuRemoteProcess newProcess(String[] strArr, String[] strArr2, String str) {
        try {
            return new ShizukuRemoteProcess(requireService().newProcess(strArr, strArr2, str));
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static void onBinderReceived(IBinder iBinder, String str) {
        String strDecode = NPStringFog.decode("0F0419000D0926150202190E001A08080B");
        if (binder == iBinder) {
            return;
        }
        if (iBinder == null) {
            binder = null;
            service = null;
            serverUid = -1;
            serverApiVersion = -1;
            serverContext = null;
            scheduleBinderDeadListeners();
            return;
        }
        if (binder != null) {
            binder.unlinkToDeath(DEATH_RECIPIENT, 0);
        }
        binder = iBinder;
        service = IShizukuService.Stub.asInterface(iBinder);
        try {
            binder.linkToDeath(DEATH_RECIPIENT, 0);
        } catch (Throwable th) {
            Log.i("ShizukuApplication", strDecode);
        }
        try {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(NPStringFog.decode("031F084F1D090E1F07050543120B1311000040393E09071B120E073D151F17070202"));
                parcelObtain.writeStrongBinder(SHIZUKU_APPLICATION.asBinder());
                parcelObtain.writeString(str);
                preV11 = binder.transact(14, parcelObtain, parcelObtain2, 0) ? false : true;
                parcelObtain2.readException();
                parcelObtain2.recycle();
                parcelObtain.recycle();
                Log.i("ShizukuApplication", strDecode);
            } catch (Throwable th2) {
                parcelObtain2.recycle();
                parcelObtain.recycle();
                throw th2;
            }
        } catch (Throwable th3) {
            Log.w("ShizukuApplication", Log.getStackTraceString(th3));
        }
        if (preV11) {
            binderReady = true;
            scheduleBinderReceivedListeners();
        }
    }

    public static boolean peekUserService(UserServiceArgs userServiceArgs, ServiceConnection serviceConnection) {
        ShizukuServiceConnection shizukuServiceConnection = ShizukuServiceConnections.get(userServiceArgs);
        shizukuServiceConnection.addConnection(serviceConnection);
        try {
            Bundle bundleForAdd = userServiceArgs.forAdd();
            bundleForAdd.putBoolean(NPStringFog.decode("1D18041B1B0A125F071D151F4C1D0415131B0D1540001C064A0B1D43131F040F1502"), true);
            return requireService().addUserService(shizukuServiceConnection, bundleForAdd) == 0;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static boolean pingBinder() {
        return binder != null && binder.pingBinder();
    }

    public static boolean removeBinderDeadListener(OnBinderDeadListener onBinderDeadListener) {
        return DEAD_LISTENERS.remove(onBinderDeadListener);
    }

    public static boolean removeBinderReceivedListener(OnBinderReceivedListener onBinderReceivedListener) {
        return RECEIVED_LISTENERS.remove(onBinderReceivedListener);
    }

    public static boolean removeRequestPermissionResultListener(OnRequestPermissionResultListener onRequestPermissionResultListener) {
        return PERMISSION_LISTENERS.remove(onRequestPermissionResultListener);
    }

    public static void requestPermission(int i) {
        try {
            requireService().requestPermission(i);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    protected static IShizukuService requireService() {
        if (service != null) {
            return service;
        }
        throw new IllegalStateException(NPStringFog.decode("0C1903050B13470D13181503461A4105001700501F040D040E13170A"));
    }

    private static RuntimeException rethrowAsRuntimeException(RemoteException remoteException) {
        return new RuntimeException(remoteException);
    }

    private static void scheduleBinderDeadListeners() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            dispatchBinderDeadListeners();
        } else {
            MAIN_HANDLER.post(new Runnable() { // from class: rikka.shizuku.Shizuku$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    Shizuku.dispatchBinderDeadListeners();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void scheduleBinderReceivedListeners() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            dispatchBinderReceivedListeners();
        } else {
            MAIN_HANDLER.post(new Runnable() { // from class: rikka.shizuku.Shizuku$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Shizuku.dispatchBinderReceivedListeners();
                }
            });
        }
    }

    static void scheduleRequestPermissionResultListener(final int i, final int i2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            dispatchRequestPermissionResultListener(i, i2);
        } else {
            MAIN_HANDLER.post(new Runnable() { // from class: rikka.shizuku.Shizuku$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    Shizuku.dispatchRequestPermissionResultListener(i, i2);
                }
            });
        }
    }

    public static boolean shouldShowRequestPermissionRationale() {
        if (permissionGranted) {
            return false;
        }
        if (shouldShowRequestPermissionRationale) {
            return true;
        }
        try {
            shouldShowRequestPermissionRationale = requireService().shouldShowRequestPermissionRationale();
            return shouldShowRequestPermissionRationale;
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static void transactRemote(Parcel parcel, Parcel parcel2, int i) throws RemoteException {
        try {
            requireService().asBinder().transact(1, parcel, parcel2, i);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }

    public static void unbindUserService(UserServiceArgs userServiceArgs, ServiceConnection serviceConnection, boolean z) {
        if (z) {
            try {
                requireService().removeUserService(null, userServiceArgs.forRemove());
            } catch (RemoteException e) {
                throw rethrowAsRuntimeException(e);
            }
        }
    }

    public static void updateFlagsForUid(int i, int i2, int i3) {
        try {
            requireService().updateFlagsForUid(i, i2, i3);
        } catch (RemoteException e) {
            throw rethrowAsRuntimeException(e);
        }
    }
}
