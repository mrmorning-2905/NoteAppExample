package com.example.noteapp

data class Notes(
    val title: String,
    val description: String,
    val lastModify: Long = System.currentTimeMillis()
)

val NOTE_LIST = listOf(
    Notes("Youtube script ideas", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Datastore Blog", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasasdf", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube scripdfat ideas", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasdsdf", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube scriptasdf ideas", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideassdfa", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasasdf", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasfghdgfh", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasghd", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasxcvb", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasfgh", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasbnc", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasbmvb", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasdfgh", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasdfglhjk", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideaskgjh", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasjkghj", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform"),
    Notes("Youtube script ideasghjkg", "There are many apps in Android that can run or emulate other operating systems, via utilizing hardware support for platform")
)
