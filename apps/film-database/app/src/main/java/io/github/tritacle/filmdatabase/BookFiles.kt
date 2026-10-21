package io.github.tritacle.filmdatabase

import android.content.Context
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// All four go to disk, and move themselves to Dispatchers.IO. Nobody else knows
// they block, so the caller does not pick a thread.
class BookFiles(private val context: Context) {

    // Packed with the app. It can be read, and only read.
    suspend fun readPacked(resourceId: Int): List<String> =
        withContext(Dispatchers.IO) {
            context.resources.openRawResource(resourceId)
                .bufferedReader(Charsets.UTF_8)
                .use { reader -> reader.readLines() }
        }

    // The app's own storage. Here we can write.
    suspend fun writeLocal(name: String, lines: List<String>) {
        withContext(Dispatchers.IO) {
            File(context.filesDir, name)
                .writeText(lines.joinToString("\n"), Charsets.UTF_8)
        }
    }

    suspend fun readLocal(name: String): List<String> =
        withContext(Dispatchers.IO) {
            val file = File(context.filesDir, name)
            if (file.exists()) file.readLines(Charsets.UTF_8)
            else emptyList()
        }

    suspend fun localPath(name: String): String =
        withContext(Dispatchers.IO) {
            File(context.filesDir, name).path
        }
}
