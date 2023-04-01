package com.mytask.utils

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.arthenica.mobileffmpeg.FFmpeg
import com.mytask.utils.PlayableAudios.fullAudioPath
import com.mytask.utils.PlayableAudios.stackAudioList

class AudioConcatUtils {
    var isFileGenerated = MutableLiveData<Boolean>()

    fun mergeAudioFiles(outputPath: String, pathList: List<String>, isFinalOutput: Boolean, stackNo: Int){             // isFinalOutput parameter will check is this the build of final output file
        val cmd: Array<String> = when (pathList.size) {
            2 -> {
                twoFileCommand(outputPath, pathList)
            }
            3 -> {
                threeFileCommand(outputPath, pathList)
            }
            4 -> {
                fourFileCommand(outputPath, pathList)
            }
            else -> {
                arrayOf()
            }
        }

        merge(outputPath, cmd, isFinalOutput, stackNo)
    }

    private fun twoFileCommand(outputPath: String,pathList: List<String>): Array<String>{
        return arrayOf(
            "-y",
            "-i",
            pathList[0],
            "-i",
            pathList[1],
            "-filter_complex",
            "concat=n=2:v=0:a=1[out]",
            "-map",
            "[out]",
            "-acodec",
            "mp3",
            outputPath,"-f", "lavfi", "-t", "0.1", "-i", "anullsrc"
        )
    }

    private fun threeFileCommand(outputPath: String,pathList: List<String>): Array<String>{
        return arrayOf(
            "-y",
            "-i",
            pathList[0],
            "-i",
            pathList[1],
            "-i",
            pathList[2],
            "-filter_complex",
            "concat=n=3:v=0:a=1[out]",
            "-map",
            "[out]",
            "-acodec",
            "mp3",
            outputPath
        )
    }

    private fun fourFileCommand(outputPath: String,pathList: List<String>): Array<String>{
        return arrayOf(
            "-y",
            "-i",
            pathList[0],
            "-i",
            pathList[1],
            "-i",
            pathList[2],
            "-i",
            pathList[3],
            "-filter_complex",
            "concat=n=4:v=0:a=1[out]",
            "-map",
            "[out]",
            "-acodec",
            "mp3",
            outputPath,"-f", "lavfi", "-t", "0.1", "-i", "anullsrc"
        )
    }

    fun mergeFinalAudioFiles(outputPath: String, pathList: List<String>, fileUtils: FileUtils){
        val cmd = twoFileCommand(outputPath, pathList)
        mergeFinal(outputPath, cmd, pathList, fileUtils)
    }

    private fun mergeFinal(outputPath: String, cmd: Array<String>, pathList: List<String>, fileUtils: FileUtils){
        FFmpeg.executeAsync(cmd) { executionId, returnCode ->
            if (pathList.size == 2){
                fullAudioPath = outputPath
                isFileGenerated.postValue(true)
            }else{
                val oldList = mutableListOf<String>()
                for (i in 2 until pathList.size){
                    oldList.add(pathList[i])
                }
                val list = listOf(outputPath) + oldList
                val time = Calendar.getInstance().time.time
                val outPath = fileUtils.getConcatenatedAudioFilePath(time)

                mergeFinalAudioFiles(outPath, list, fileUtils)
            }
        }
    }

    private fun merge(outputPath: String, cmd: Array<String>, isFinalOutput: Boolean, stackNo: Int){
        FFmpeg.executeAsync(cmd) { executionId, returnCode ->
            Log.i("SetViews", "merge: executionId $executionId")
            Log.i("SetViews", "merge: return code $returnCode")

            if (!isFinalOutput){
                stackAudioList[stackNo] = outputPath
            }
//            else{
//                isFileGenerated.postValue(true)
//                fullAudioPath = outputPath
//            }
        }
    }
}