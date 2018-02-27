package com.lixm.animationdemo.activity

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lixm.animationdemo.R
import com.lixm.animationdemo.bean.PluginBean
import dalvik.system.PathClassLoader
import kotlinx.android.synthetic.main.activity_apk_theme.*
import org.xutils.common.util.LogUtil
import java.lang.reflect.Field

class ApkThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apk_theme)
        btn.setOnClickListener {
            val datas: MutableList<HashMap<String, String>> = mutableListOf()
            val plugins: List<PluginBean> = findAllPlugin()
            if (plugins?.size > 0) {
                for (plugin in plugins) {
                    val map: HashMap<String, String> = hashMapOf<String, String>()
                    map.put("label", plugin.label)
                    datas.add(map)
                    LogUtil.i("label=>" + plugin.label)
                }
                val id = dynamicLoadApk("com.lixm.apkthemeplugin", this)
                LogUtil.w("图片资源ID：" + id)
                if (id != 0)
                    img.setBackgroundResource(id)
            } else {
                Toast.makeText(this, "没有找到插件，请先下载" +
                        "", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun findAllPlugin(): List<PluginBean> {
        val plugins = ArrayList<PluginBean>()
        val pm: PackageManager = packageManager
        //通过包管理器查找所有已安装的apk文件
        val packageInfos: List<PackageInfo> = pm.getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES)
        for (item in packageInfos) {
            val pkgName = item.packageName
            val shareUserId = item.sharedUserId
            if (shareUserId != null && shareUserId.equals("com.lixm.apkthemeplugin") && !pkgName.equals(this.packageName)) {
                val label: String = pm.getApplicationLabel(item.applicationInfo).toString()
                val bean = PluginBean(label, pkgName)
                plugins.add(bean)
            }
        }
        return plugins
    }

    @Throws(Exception::class)
    private fun dynamicLoadApk(packageName: String, pluginContext: Context): Int {
        //第一个参数为包含dex的apk或者jar的路径，第二个参数为父加载器
        val pathClassLoader = PathClassLoader(pluginContext.packageResourcePath, ClassLoader.getSystemClassLoader())
        //参数：1、类的全名，2、是否初始化类，3、加载时使用的类加载器
        try {
//            val clazzs=pathClassLoader.loadClass("com.lixm.animationdemo" + ".R\$mipmap")
            val name="com.lixm.animationdemo.R&mipmap"
            val clazz = Class.forName(name, true, pathClassLoader)
            //使用上述两种方式都可以，这里我们得到R类中的内部类mipmap，通过它得到对应的图片id，进而给我们使用
            val field: Field = clazz.getDeclaredField("a")
            return field.getInt(R.mipmap::class.java)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return 0
        }
    }

}
