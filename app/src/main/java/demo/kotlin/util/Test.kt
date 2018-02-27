package demo.kotlin.util

import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.PluginBean
import dalvik.system.PathClassLoader


/**
 * @author Lixm
 * @date 2018/2/27
 * @detail
 */
class Test : AppCompatActivity() {
//var clazz = Class.forName(packageName + ".R\$mipmap", true, pathClassLoader)
    /**
     * 加载已安装的apk
     * @param packageName 应用的包名
     * @param pluginContext 插件app的上下文
     * @return 对应资源的id
     */
    @Throws(Exception::class)
    private fun dynamicLoadApk(packageName: String, pluginContext: Context): Int {
        //第一个参数为包含dex的apk或者jar的路径，第二个参数为父加载器
        val pathClassLoader = PathClassLoader(pluginContext.getPackageResourcePath(), ClassLoader.getSystemClassLoader())
        //        Class<?> clazz = pathClassLoader.loadClass(packageName + ".R$mipmap");//通过使用自身的加载器反射出mipmap类进而使用该类的功能
        //参数：1、类的全名，2、是否初始化类，3、加载时使用的类加载器
        val clazz = Class.forName("$packageName.R\$mipmap", true, pathClassLoader)
        //使用上述两种方式都可以，这里我们得到R类中的内部类mipmap，通过它得到对应的图片id，进而给我们使用
        val field = clazz.getDeclaredField("one")
        return field.getInt(R.mipmap::class.java)
    }

    /**
     * 查找手机内所有的插件
     * @return 返回一个插件List
     */
    private fun findAllPlugin(): List<PluginBean> {
        val plugins = ArrayList<PluginBean>()
        val pm = packageManager
        //通过包管理器查找所有已安装的apk文件
        val packageInfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES)
        for (info in packageInfos) {
            //得到当前apk的包名
            val pkgName = info.packageName
            //得到当前apk的sharedUserId
            val shareUesrId = info.sharedUserId
            //判断这个apk是否是我们应用程序的插件
            if (shareUesrId != null && shareUesrId == "com.sunzxyong.myapp" && pkgName != this.getPackageName()) {
                val label = pm.getApplicationLabel(info.applicationInfo).toString()//得到插件apk的名称
                val bean = PluginBean(label, pkgName)
                plugins.add(bean)
            }
        }
        return plugins
    }
}