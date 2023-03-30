package com.mytask

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.arthenica.mobileffmpeg.FFmpeg
import com.arthenica.mobileffmpeg.FFmpegExecution
import com.mytask.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.*


class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private var binding: ActivityMainBinding? = null
    private lateinit var gestureDetector: GestureDetector
    private lateinit var adapter: ViewPagerAdapter
    private var outputPath = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val audioLists = AudioLists()
//        val itemList = listOf(images, images, images, images, images, images, images, images, images, images, images, images, images, images, images, images)
        val adapter = MainRecyclerAdapter(this, audioLists.itemLists())
        binding?.rvItems?.adapter = adapter
        val itemDecoration = CenterGridItemDecoration(this, 4, 30, true)
        binding?.rvItems?.addItemDecoration(itemDecoration)



        binding?.fabPlay?.setOnClickListener {
            val audioList = listOf(R.raw.a_break_ya_neck_kick_three, R.raw.a_laid_back_kick_two)
            merge()
        }
    }

    private fun merge(){
        val outputPath = getConcatenatedAudioFilePath()
        val path1 = copyRawFileToInternalStorage(R.raw.a_break_ya_neck_kick_three)
        val path2 = copyRawFileToInternalStorage(R.raw.c_simple_lead_two)
        val path3 = copyRawFileToInternalStorage(R.raw.d_hype_synth_fx_four)
        val path4 = copyRawFileToInternalStorage(R.raw.b_busy_vibe_hat_three)

        val cmd = arrayOf(
            "-y",
            "-i",
            path1,
            "-i",
            path2,
            "-i",
            path3,
            "-i",
            path4,
            "-filter_complex",
            "concat=n=4:v=0:a=1[out]",
            "-map",
            "[out]",
            "-acodec",
            "mp3",
            outputPath,"-f", "lavfi", "-t", "0.1", "-i", "anullsrc"
        )


        FFmpeg.executeAsync(cmd) { executionId, returnCode ->
            Log.i(TAG, "return  $returnCode")
            Log.i(TAG, "executionID  $executionId")
            Log.i(TAG, "FFMPEG  " + FFmpegExecution(executionId, cmd))

            Log.i(TAG, "merge: output Path $outputPath")

            val mediaPlayer = MediaPlayer.create(this, Uri.parse(outputPath))
            mediaPlayer.start()
        }
    }

    private fun copyRawFileToInternalStorage(resourceId: Int): String? {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        val filePath = filesDir.absolutePath + "/" + resourceId
        val outputStream: FileOutputStream
        try {
            outputStream = FileOutputStream(filePath)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return filePath
    }

    private fun getConcatenatedAudioFilePath(): String? {
        return filesDir.absolutePath + "/concatenated_audio.mp3"
    }















//    private fun onPageChangeCallback(listSize: Int){
//        binding?.viewPager?.viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//                    when(binding?.viewPager?.viewPager?.currentItem){
//                        listSize - 1 -> binding?.viewPager?.viewPager?.setCurrentItem(1, false)
//                        0 -> binding?.viewPager?.viewPager?.setCurrentItem(listSize-2, false)
//                    }
//            }
//        })
//
//    }
}