package com.notupdate.mihoyobbs;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookMain implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final LoadPackageParam lpp) {
        if (!lpp.packageName.equals("com.mihoyo.hyperion"))
            return;
        XposedHelpers.findAndHookMethod(
                "com.mihoyo.sora.upgrade.entities.LatestReleaseBean",
                lpp.classLoader,
                "getHasUpdate",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        param.setResult(false);
                        XposedBridge.log("拦截成功");
                    }
                }
        );
    }
}
