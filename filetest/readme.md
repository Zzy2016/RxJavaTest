所有 Android 设备都有两个文件存储区域：内部存储空间和外部存储空间。这些名称是在 Android 早期确定的，
那时候大部分设备都提供内置的非易失性内存（内部存储空间）以及可移动存储媒介（如 Micro SD 卡，提供外部存储空间）。
现在，很多设备将永久性存储空间划分为单独的“内部”和“外部”分区。因此，即使没有可移动存储媒介，这两种存储空间也始终存在，
并且无论外部存储空间是否可移动，这两种存储空间的 API 行为都是相同的。


内部存储空间：
    始终提供。
    只有您的应用可以访问保存到此处的文件。
    如果用户卸载您的应用，系统会从内部存储空间中移除您应用的所有文件。
如果您希望确保用户和其他应用都无法访问您的文件，最好使用内部存储空间。



外部存储空间：
    并非始终提供，因为用户可以将外部存储空间装载为 USB 存储设备，也可以在某些情况下将其从设备中移除。
    全局可读，因此保存到此处的文件可能在您的控制范围之外被读取。
    如果用户卸载您的应用，系统会从此处移除您应用的文件（仅当您通过 getExternalFilesDir() 将这些文件保存到目录中）。
对于不需要访问限制的文件以及您希望与其他应用共享或允许用户通过计算机访问的文件，外部存储空间是最佳位置。
E/文件目录: /data/user/0/com.example.filetest/files/file   /data/user/0/com.example.filetest/files
E/文件: /storage/emulated/0/Android/data/com.example.filetest/files

大部分应用是直接在SDCard的根目录下创建一个文件夹，然后把数据保存在该文件夹中。
这样当该应用被卸载后，这些数据还保留在SDCard中，留下了垃圾数据。

通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

MODE_PRIVATE：说明该文件只能被当前的应用程序所读写
MODE_APPEND：以追加方式打开该文件，应用程序可以向该文件中追加内容。
MODE_WORLD_READABLE：该文件的内容可以被其他的应用程序所读取
MODE_WORLD_WRITEABLE：该文件的内容可以被其他的应用程序所读、写



安装apk 
配置权限
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission
        android:name="android.permission.MOUNT_FORMAT_FILESYSTEMS"
        tools:ignore="ProtectedPermissions" />

<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>


Intent intent = new Intent(Intent.ACTION_VIEW);
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    uri=FileProvider.getUriForFile(MainActivity.this,getPackageName()+".my_provider",apkFile);
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    intent.setDataAndType(uri, "application/vnd.android.package-archive");
} else {
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.setDataAndType(uri, "application/vnd.android.package-archive");
}
startActivity(intent);

FileProvider
清单声明
<provider
    android:name="androidx.core.content.FileProvider"  //固定写法此为AndroidX版本
    android:authorities="${applicationId}.my_provider" //唯一标识 可以结合包名
    android:exported="false"                           //必须为false 否则运行时会报错 java.lang.SecurityException: Provider must not be exported
    android:grantUriPermissions="true">                //用来控制是否允许临时授予文件的访问权限，必须设置成 true
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"    //android:name 固定写法 
        android:resource="@xml/file_paths" />               //指定共享文件的路径，此文件放在res/xml/下
</provider>


xml文件配置
<paths>
    <files-path
        name="files"
        path="/"/>
    <cache-path
        name="cache"
        path="/"/>
    <external-path
        name="external"
        path="/"/>
    <external-files-path
        name="external_file_path"
        path="/"/>
    <external-cache-path
        name="external_cache_path"
        path="/"/>
    <!--<external-media-path
        name="external-media-path"
        path=""/>-->
</paths>

可配置的元素：
    files-path 对应内部存储目录 Context.getFilesDir()
    cache-path 对应内部存储目录 Context.getCacheDir()
    external-path 对应 Environment.getExternalStorageDirectory()
    external-files-path 对应 Context.getExternalFilesDir()
    external-cache-path 对应 Context.getExternalCacheDir()
    external-media-path 对应 Context.getExternalMediaDirs()
    
    
使用 Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
