package com.example.noteapp

data class Notes(
    val id: Int,
    val title: String,
    val description: String,
    val lastModify: Long = System.currentTimeMillis()
)

val NOTE_LIST = listOf(
    Notes(1,"Lifecycle of composables", "As mentioned in the Managing state documentation, a Composition describes the UI of your app and is produced by running composables. A Composition is a tree-structure of the composables that describe your UI.\n" +
            "\n" +
            "When Jetpack Compose runs your composables for the first time, during initial composition, it will keep track of the composables that you call to describe your UI in a Composition. Then, when the state of your app changes, Jetpack Compose schedules a recomposition. Recomposition is when Jetpack Compose re-executes the composables that may have changed in response to state changes, and then updates the Composition to reflect any changes.\n" +
            "\n" +
            "A Composition can only be produced by an initial composition and updated by recomposition. The only way to modify a Composition is through recomposition."),
    Notes(2,"Architecting your Compose UI", "In Compose the UI is immutable—there's no way to update it after it's been drawn. What you can control is the state of your UI. Every time the state of the UI changes, Compose recreates the parts of the UI tree that have changed. Composables can accept state and expose events—for example, a TextField accepts a value and exposes a callback onValueChange that requests the callback handler to change the value."),
    Notes(3,"Categories of storage locations", "Android provides two types of physical storage locations: internal storage and external storage. On most devices, internal storage is smaller than external storage. However, internal storage is always available on all devices, making it a more reliable place to put data on which your app depends.\n" +
            "\n" +
            "Removable volumes, such as an SD card, appear in the file system as part of external storage. Android represents these devices using a path, such as /sdcard."),
    Notes(4,"Access app-specific files", "When the user uninstalls your app, the files saved in app-specific storage are removed. Because of this behavior, you shouldn't use this storage to save anything that the user expects to persist independently of your app. For example, if your app allows users to capture photos, the user would expect that they can access those photos even after they uninstall your app. So you should instead use shared storage to save those types of files to the appropriate media collection."),
    Notes(5,"Permissions and access to external storage", "Android defines the following storage-related permissions: READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, and MANAGE_EXTERNAL_STORAGE.\n" +
            "\n" +
            "On earlier versions of Android, apps needed to declare the READ_EXTERNAL_STORAGE permission to access any file outside the app-specific directories on external storage. Also, apps needed to declare the WRITE_EXTERNAL_STORAGE permission to write to any file outside the app-specific directory.\n" +
            "\n" +
            "More recent versions of Android rely more on a file's purpose than its location for determining an app's ability to access, and write to, a given file. In particular, if your app targets Android 11 (API level 30) or higher, the WRITE_EXTERNAL_STORAGE permission doesn't have any effect on your app's access to storage. This purpose-based storage model improves user privacy because apps are given access only to the areas of the device's file system that they actually use."),
    Notes(6,"Access from internal storage", "For each app, the system provides directories within internal storage where an app can organize its files. One directory is designed for your app's persistent files, and another contains your app's cached files. Your app doesn't require any system permissions to read and write to files in these directories.\n" +
            "\n" +
            "Other apps cannot access files stored within internal storage. This makes internal storage a good place for app data that other apps shouldn't access."),
    Notes(7,"Access from external storage", "If internal storage doesn't provide enough space to store app-specific files, consider using external storage instead. The system provides directories within external storage where an app can organize files that provide value to the user only within your app. One directory is designed for your app's persistent files, and another contains your app's cached files.\n" +
            "\n" +
            "On Android 4.4 (API level 19) or higher, your app doesn't need to request any storage-related permissions to access app-specific directories within external storage. The files stored in these directories are removed when your app is uninstalled."),
    Notes(8,"Verify that storage is available", "The most straightforward and common use of the Android Sharesheet is to send text content from one activity to another. For example, most browsers can share the URL of the currently displayed page as text with another app. This is useful for sharing an article or website with friends through email or social networking. Here's an example of how to do this:"),
    Notes(9,"Configure the base module", "Because external storage resides on a physical volume that the user might be able to remove, verify that the volume is accessible before trying to read app-specific data from, or write app-specific data to, external storage.\n" +
            "\n" +
            "You can query the volume's state by calling Environment.getExternalStorageState(). If the returned state is MEDIA_MOUNTED, then you can read and write app-specific files within external storage. If it's MEDIA_MOUNTED_READ_ONLY, you can only read these files."),
    Notes(10,"Select a physical storage location", "The most straightforward and common use of the Android Sharesheet is to send text content from one activity to another. For example, most browsers can share the URL of the currently displayed page as text with another app. This is useful for sharing an article or website with friends through email or social networking. Here's an example of how to do this:"),
    Notes(11,"About Android App Bundles", "Sometimes, a device that allocates a partition of its internal memory as external storage also provides an SD card slot. This means that the device has multiple physical volumes that could contain external storage, so you need to select which one to use for your app-specific storage.\n" +
            "\n" +
            "To access the different locations, call ContextCompat.getExternalFilesDirs(). As shown in the code snippet, the first element in the returned array is considered the primary external storage volume. Use this volume unless it's full or unavailable."),
    Notes(12,"Query free space", "Many users don't have much storage space available on their devices, so your app should consume space thoughtfully.\n" +
            "\n" +
            "If you know ahead of time how much data you're storing, you can find out how much space the device can provide your app by calling getAllocatableBytes(). The return value of getAllocatableBytes() might be larger than the current amount of free space on the device. This is because the system has identified files that it can remove from other apps' cache directories."),
    Notes(13,"Create a storage management activity", "Your app can declare and create a custom activity that, when launched, allows the user to manage the data that your app has stored on the user's device. You declare this custom \"manage space\" activity using the android:manageSpaceActivity attribute in the manifest file. File manager apps can invoke this activity even when your app doesn't export the activity; that is, when your activity sets android:exported to false."),
    Notes(14,"Manage all files on a storage device", "The majority of apps that require shared storage access can follow the best practices for sharing media files and sharing non-media files. However, some apps have a core use case that requires broad access to files on a device, but can't access them efficiently using the privacy-friendly storage best practices. Android provides a special app access called all-files access for these situations.\n" +
            "\n" +
            "For example, an anti-virus app's primary use case might require regular scanning of many files across different directories. If this scanning requires repeated user interactions to select directories using the system file picker, it provides a poor user experience. Other use cases—such as file manager apps, backup and restore apps, and document management apps—require similar considerations."),
    Notes(15,"Invoke another app's storage management activity", "On Android 12 (API level 31) and higher, apps that have both the MANAGE_EXTERNAL_STORAGE permission and the QUERY_ALL_PACKAGES permission—such as file management apps—can use the getManageSpaceActivityIntent() to send users to another app's custom space management activity.\n" +
            "\n" +
            "The getManageSpaceActivityIntent() method takes in a package name and a request code, and it returns one of the following:\n" +
            "\n" +
            "A PendingIntent, if the app with the specified package name has defined a custom \"manage space\" activity. The file management app that called the getManageSpaceActivityIntent() method can then invoke the returned intent to send users to the custom activity.\n" +
            "null, if the app with the specified package name doesn't define a \"manage space\" activity."),
    Notes(16,"Google Play notice", "This section provides a notice for developers who publish apps on Google Play.\n" +
            "\n" +
            "To limit broad access to shared storage, the Google Play store has updated its policy to evaluate apps that target Android 11 (API level 30) or higher and request all-files access through the MANAGE_EXTERNAL_STORAGE permission. This policy is in effect as of May 2021.\n" +
            "\n" +
            "When your app targets Android 11 or higher and it declares the MANAGE_EXTERNAL_STORAGE permission, Android Studio shows the lint warning that appears in figure 1. This warning reminds you that the Google Play store has a policy that limits usage of the permission."),
    Notes(17,"Sharing simple data", "One of the great things about Android apps is their ability to communicate and integrate with each other. Why reinvent functionality that isn't core to your application when it already exists in another application?\n" +
            "\n" +
            "This section covers some common ways you can send and receive simple data (like text, images and files) between applications using the Android Sharesheet and Intent Resolver with Intent objects."),
    Notes(18,"Send simple data to other apps", "Android uses intents and their associated extras to let users share information quickly and easily using their favorite apps.\n" +
            "\n" +
            "Android provides two ways for users to share data between apps:\n" +
            "\n" +
            "The Android Sharesheet is primarily designed for sending content outside your app and/or directly to another user. For example, sharing a URL with a friend.\n" +
            "The Android intent resolver is best suited for passing data to the next stage of a well-defined task. For example, opening a PDF from your app and letting users pick their preferred viewer."),
    Notes(19,"Why to use the Android Sharesheet", "We strongly recommend using the Android Sharesheet to create consistency for your users across apps. Don't display your app's own list of share targets or create your own Sharesheet variations.\n" +
            "\n" +
            "The Android Sharesheet lets users share information with the right person, with relevant app suggestions, all with a single tap. The Sharesheet can suggest targets unavailable to custom solutions and uses a consistent ranking. This is because the Sharesheet can take into account information about app and user activity that is only available to the system.")
)
