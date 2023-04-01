package com.mytask.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.icu.util.Calendar
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageButton
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager2.widget.ViewPager2
import com.mytask.Items
import com.mytask.R
import com.mytask.ViewPagerAdapter
import com.mytask.databinding.ActivityMainBinding
import com.mytask.utils.PlayableAudios.fullAudioPath
import com.mytask.utils.PlayableAudios.stackAudioList


class SetViews(private val context: Context, val binding: ActivityMainBinding, private var mediaPlayer: MediaPlayer?): OnCompletionListener, ViewPagerAdapter.OnClickEvent {
    private val selectedAudioList = List(4) {List(5) {""}.toMutableList() }.toMutableList()
    private val concatUtils = AudioConcatUtils()
    private val fileUtils = FileUtils(context)
    private var currentPlayingViews = listOf<View>()
    private var isFinalAudioPlaying = false
    private var stackPlaying = ""
    private var seekButton: ImageButton? = null

    init {
        val audioList = AudioLists(context).itemLists()

        setAdapter(binding.vp11.viewPager, audioList[0], binding.vp11.upView)
        setAdapter(binding.vp12.viewPager, audioList[0], binding.vp12.upView)
        setAdapter(binding.vp13.viewPager, audioList[0], binding.vp13.upView)
        setAdapter(binding.vp14.viewPager, audioList[0], binding.vp14.upView)
        setAdapter(binding.vp21.viewPager, audioList[1], binding.vp21.upView)
        setAdapter(binding.vp22.viewPager, audioList[1], binding.vp22.upView)
        setAdapter(binding.vp23.viewPager, audioList[1], binding.vp23.upView)
        setAdapter(binding.vp24.viewPager, audioList[1], binding.vp24.upView)
        setAdapter(binding.vp31.viewPager, audioList[2], binding.vp31.upView)
        setAdapter(binding.vp32.viewPager, audioList[2], binding.vp32.upView)
        setAdapter(binding.vp33.viewPager, audioList[2], binding.vp33.upView)
        setAdapter(binding.vp34.viewPager, audioList[2], binding.vp34.upView)
        setAdapter(binding.vp41.viewPager, audioList[3], binding.vp41.upView)
        setAdapter(binding.vp42.viewPager, audioList[3], binding.vp42.upView)
        setAdapter(binding.vp43.viewPager, audioList[3], binding.vp43.upView)
        setAdapter(binding.vp44.viewPager, audioList[3], binding.vp44.upView)

        binding.fabPlay.setOnClickListener {
            mediaPlayer?.let {mp ->
                if (mp.isPlaying && isFinalAudioPlaying){
                    isFinalAudioPlaying = false
                    mp.stop()
                    setViewVisibility(false)
                    return@setOnClickListener
                }
            }

            buildFinalAudio()

            mediaPlayer?.release()
            setViewVisibility(false)
            currentPlayingViews = firstStackView() + secondStackView() + thirdStackView() + fourthStackView()
            setViewVisibility(true)
            concatUtils.isFileGenerated.observe(context as LifecycleOwner){
                if (it){
                    try {
                        if (fullAudioPath.isNotEmpty()) {
                            mediaPlayer = MediaPlayer.create(context, Uri.parse(fullAudioPath))
                            mediaPlayer?.start()
                            mediaPlayer?.setOnCompletionListener(this)
                            isFinalAudioPlaying = true
                            binding.fabPlay.setImageResource(R.drawable.ic_round_pause_24)
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                    concatUtils.isFileGenerated.removeObservers(context as LifecycleOwner)
                    concatUtils.isFileGenerated.postValue(false)
                }
            }
        }

        binding.seek1.ibPlayPause.setOnClickListener {
            mediaPlayer?.let {mp ->
                if (mp.isPlaying && stackPlaying == "One"){
                    stackPlaying = ""
                    mp.stop()
                    setViewVisibility(false)
                    return@setOnClickListener
                }
            }

            try {
                if (stackAudioList[0].isNotEmpty()) {
                    mediaPlayer?.release()
                    setViewVisibility(false)
                    currentPlayingViews = firstStackView()
                    setViewVisibility(true)
                    mediaPlayer = MediaPlayer.create(context, Uri.parse(stackAudioList[0]))
                    mediaPlayer?.start()
                    mediaPlayer?.setOnCompletionListener(this)
                    stackPlaying = "One"
                    isFinalAudioPlaying = false
                    seekButton = binding.seek1.ibPlayPause
                    seekButton?.setImageResource(R.drawable.ic_round_pause_24)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.seek2.ibPlayPause.setOnClickListener {
            mediaPlayer?.let {mp ->
                if (mp.isPlaying && stackPlaying == "Two"){
                    stackPlaying = ""
                    mp.stop()
                    setViewVisibility(false)
                    return@setOnClickListener
                }
            }

            try {
                if (stackAudioList[1].isNotEmpty()) {
                    mediaPlayer?.release()
                    setViewVisibility(false)
                    currentPlayingViews = secondStackView()
                    setViewVisibility(true)
                    mediaPlayer = MediaPlayer.create(context, Uri.parse(stackAudioList[1]))
                    mediaPlayer?.start()
                    mediaPlayer?.setOnCompletionListener(this)
                    stackPlaying = "Two"
                    isFinalAudioPlaying = false
                    seekButton = binding.seek2.ibPlayPause
                    seekButton?.setImageResource(R.drawable.ic_round_pause_24)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.seek3.ibPlayPause.setOnClickListener {
            mediaPlayer?.let {mp ->
                if (mp.isPlaying && stackPlaying == "Three"){
                    stackPlaying = ""
                    mp.stop()
                    setViewVisibility(false)
                    return@setOnClickListener
                }
            }

            try {
                if (stackAudioList[2].isNotEmpty()) {
                    mediaPlayer?.release()
                    setViewVisibility(false)
                    currentPlayingViews = thirdStackView()
                    setViewVisibility(true)
                    mediaPlayer = MediaPlayer.create(context, Uri.parse(stackAudioList[2]))
                    mediaPlayer?.start()
                    mediaPlayer?.setOnCompletionListener(this)
                    stackPlaying = "Three"
                    isFinalAudioPlaying = false
                    seekButton = binding.seek3.ibPlayPause
                    seekButton?.setImageResource(R.drawable.ic_round_pause_24)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        binding.seek4.ibPlayPause.setOnClickListener {
            mediaPlayer?.let {mp ->
                if (mp.isPlaying && stackPlaying == "Four"){
                    stackPlaying = ""
                    mp.stop()
                    setViewVisibility(false)
                    return@setOnClickListener
                }
            }

            try {
                if (stackAudioList[3].isNotEmpty()) {
                    mediaPlayer?.release()
                    setViewVisibility(false)
                    currentPlayingViews = fourthStackView()
                    setViewVisibility(true)
                    mediaPlayer = MediaPlayer.create(context, Uri.parse(stackAudioList[3]))
                    mediaPlayer?.start()
                    mediaPlayer?.setOnCompletionListener(this)
                    stackPlaying = "Four"
                    isFinalAudioPlaying = false
                    seekButton = binding.seek4.ibPlayPause
                    seekButton?.setImageResource(R.drawable.ic_round_pause_24)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun setAdapter(viewPager: ViewPager2, itemsList: List<Items>, viewPagerUpperView: View){
        val adapter = ViewPagerAdapter(context, itemsList, viewPagerUpperView, this)
        viewPager.adapter = adapter

        viewPager.currentItem = 1

        onPageChangeCallback(viewPager, itemsList, itemsList.size + 2)
    }

    private fun onPageChangeCallback(viewPager: ViewPager2,list: List<Items>, listSize: Int){
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when(viewPager.currentItem){
                    listSize - 1 -> viewPager.setCurrentItem(1, false)
                    0 -> viewPager.setCurrentItem(listSize-2, false)
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position in 1..5){
                    try {
                        val index = position - 1
                        when(viewPager){
                            binding.vp11.viewPager -> {
                                selectedAudioList[0][0] = list[index].audio
                                setTheAudioPath(selectedAudioList[0], 0)
                            }
                            binding.vp12.viewPager -> {
                                selectedAudioList[1][0] = list[index].audio
                                setTheAudioPath(selectedAudioList[1], 1)
                            }
                            binding.vp13.viewPager -> {
                                selectedAudioList[2][0] = list[index].audio
                                setTheAudioPath(selectedAudioList[2], 2)
                            }
                            binding.vp14.viewPager -> {
                                selectedAudioList[3][0] = list[index].audio
                                setTheAudioPath(selectedAudioList[3], 3)
                            }
                            binding.vp21.viewPager -> {
                                selectedAudioList[0][1] = list[index].audio
                                setTheAudioPath(selectedAudioList[0], 0)
                            }
                            binding.vp22.viewPager -> {
                                selectedAudioList[1][1] = list[index].audio
                                setTheAudioPath(selectedAudioList[1], 1)
                            }
                            binding.vp23.viewPager -> {
                                selectedAudioList[2][1] = list[index].audio
                                setTheAudioPath(selectedAudioList[2], 2)
                            }
                            binding.vp24.viewPager -> {
                                selectedAudioList[3][1] = list[index].audio
                                setTheAudioPath(selectedAudioList[3], 3)
                            }
                            binding.vp31.viewPager -> {
                                selectedAudioList[0][2] = list[index].audio
                                setTheAudioPath(selectedAudioList[0], 0)
                            }
                            binding.vp32.viewPager -> {
                                selectedAudioList[1][2] = list[index].audio
                                setTheAudioPath(selectedAudioList[1], 1)
                            }
                            binding.vp33.viewPager -> {
                                selectedAudioList[2][2] = list[index].audio
                                setTheAudioPath(selectedAudioList[2], 2)
                            }
                            binding.vp34.viewPager -> {
                                selectedAudioList[3][2] = list[index].audio
                                setTheAudioPath(selectedAudioList[3], 3)
                            }
                            binding.vp41.viewPager -> {
                                selectedAudioList[0][3] = list[index].audio
                                setTheAudioPath(selectedAudioList[0], 0)
                            }
                            binding.vp42.viewPager -> {
                                selectedAudioList[1][3] = list[index].audio
                                setTheAudioPath(selectedAudioList[1], 1)
                            }
                            binding.vp43.viewPager -> {
                                selectedAudioList[2][3] = list[index].audio
                                setTheAudioPath(selectedAudioList[2], 2)
                            }
                            binding.vp44.viewPager -> {
                                selectedAudioList[3][3] = list[index].audio
                                setTheAudioPath(selectedAudioList[3], 3)
                            }
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }
        })
    }

    private fun setTheAudioPath(audioList: List<String>, stackNo: Int){
        val nonEmptyList = mutableListOf<String>()
        for (item in audioList){
            if (item.isNotEmpty()){
                nonEmptyList.add(item)
            }
        }

        if (nonEmptyList.size > 0) {
            buildAudio(nonEmptyList, stackNo)
        }else{
            stackAudioList[stackNo] = audioList[0]
        }
    }

    private fun buildAudio(audioList: List<String>, stackNo: Int){
        val outputPath = fileUtils.getConcatenatedStackAudioFilePath(stackNo)

        when(audioList.size){
            1 -> {
                // set the file path as it is to play( no need to concat as this list has only one item
                stackAudioList[stackNo] = audioList[0]
            }

            else -> {
                concatUtils.mergeAudioFiles(outputPath, audioList, false, stackNo)
            }
        }
    }

    private fun buildFinalAudio(){
        val time = Calendar.getInstance().time.time
        val outputPath = fileUtils.getConcatenatedAudioFilePath(time)

        val nonEmptyList = mutableListOf<String>()
        for (item in stackAudioList){
            if (item.isNotEmpty()){
                nonEmptyList.add(item)
            }
        }
        if (nonEmptyList.size > 1){
            concatUtils.mergeFinalAudioFiles(outputPath, nonEmptyList, fileUtils)

        }else if (nonEmptyList.size == 1) {
            fullAudioPath = nonEmptyList[0]
            concatUtils.isFileGenerated.postValue(true)
        }else {
            fullAudioPath = ""
        }
    }

    override fun onCompletion(p0: MediaPlayer?) {
        setViewVisibility(false)
        if (isFinalAudioPlaying){
            binding.fabPlay.setImageResource(R.drawable.ic_round_play_arrow_24)
        }else{
            seekButton?.setImageResource(R.drawable.ic_round_play_arrow_24)
        }

    }

    private fun setViewVisibility(visible: Boolean){
        binding.fabPlay.setImageResource(R.drawable.ic_round_play_arrow_24)
        binding.seek1.ibPlayPause.setImageResource(R.drawable.ic_round_play_arrow_24)
        binding.seek2.ibPlayPause.setImageResource(R.drawable.ic_round_play_arrow_24)
        binding.seek3.ibPlayPause.setImageResource(R.drawable.ic_round_play_arrow_24)
        binding.seek4.ibPlayPause.setImageResource(R.drawable.ic_round_play_arrow_24)

//        binding.seek1.seekProgress.visibility = View.GONE
//        binding.seek2.seekProgress.visibility = View.GONE
//        binding.seek3.seekProgress.visibility = View.GONE
//        binding.seek4.seekProgress.visibility = View.GONE

        for (view in currentPlayingViews){
            if (visible){
                view.visibility = View.VISIBLE
            }else{
                view.visibility = View.GONE
            }
        }
    }

    private fun setSeeksAnimation(seekView: View){
        val duration = mediaPlayer?.duration?.toLong()!!

        // animate the view to enlarge smoothly from the start
        val scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 0f, 1f)
        val scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1f)
        val animator: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(seekView, scaleXHolder, scaleYHolder)
        animator.duration = duration
        animator.interpolator = AccelerateInterpolator()
        animator.startDelay = 0

        // set the pivot point for the X axis to the start of the view
        seekView.pivotX = 0f
        seekView.pivotY = seekView.height/2f

        // delay the visibility of the view by 500ms
        Handler(Looper.myLooper()!!).postDelayed({
            seekView.visibility = View.VISIBLE
        }, duration/4)

        animator.start()
    }

    private fun setSeekAnimation(seekView: View){
        val duration = mediaPlayer?.duration?.toLong()!!
        seekView.pivotX = seekView.width / 2f
        seekView.pivotY = seekView.height / 2f

        val scaleXAnimator = ValueAnimator.ofFloat(0f, 1f)
        scaleXAnimator.duration = duration
        scaleXAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            seekView.scaleX = value
        }

        val scaleYAnimator = ValueAnimator.ofFloat(0f, 1f)
        scaleYAnimator.duration = duration/4
        scaleYAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            seekView.scaleY = value
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
        animatorSet.interpolator = AccelerateInterpolator()
        animatorSet.startDelay = 0

    // Start the animation and set the visibility of the view after a delay
//        Handler(Looper.myLooper()!!).postDelayed({
//            seekView.visibility = View.VISIBLE
//            animatorSet.start()
//        }, startDelay)


        seekView.visibility = View.VISIBLE
        animatorSet.start()
    }

    private fun firstStackView(): List<View>{
        return listOf(
            binding.vp11.upView,
            binding.vp21.upView,
            binding.vp31.upView,
            binding.vp41.upView
        )
    }

    private fun secondStackView(): List<View>{
        return listOf(
            binding.vp12.upView,
            binding.vp22.upView,
            binding.vp32.upView,
            binding.vp42.upView
        )
    }

    private fun thirdStackView(): List<View>{
        return listOf(
            binding.vp13.upView,
            binding.vp23.upView,
            binding.vp33.upView,
            binding.vp43.upView
        )
    }

    private fun fourthStackView(): List<View>{
        return listOf(
            binding.vp14.upView,
            binding.vp24.upView,
            binding.vp34.upView,
            binding.vp44.upView
        )
    }

    override fun onItemClicked(audio: String, view: View) {
        if (audio.isNotEmpty()) {

            setViewVisibility(false)
            currentPlayingViews = listOf(view)
            setViewVisibility(true)
            try {
                mediaPlayer?.release()

                mediaPlayer = MediaPlayer.create(context, Uri.parse(audio))
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener(this)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}