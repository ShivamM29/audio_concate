package com.mytask.utils

import android.content.Context
import com.mytask.Items
import com.mytask.R

class AudioLists(private val context: Context) {
    private var fileUtils = FileUtils(context)

    fun itemLists(): List<List<Items>>{
        return listOf(rowList1(),rowList2(),rowList3(),rowList4())
    }

    private fun rowList1(): List<Items>{
        val audioPath1 = fileUtils.copyRawFileToInternalStorage(R.raw.a_snare_clap_only_one)
        val audioPath2 = fileUtils.copyRawFileToInternalStorage(R.raw.a_laid_back_kick_two)
        val audioPath3 = fileUtils.copyRawFileToInternalStorage(R.raw.a_break_ya_neck_kick_three)
        val audioPath4 = fileUtils.copyRawFileToInternalStorage(R.raw.a_party_kick_four)

        val item1 = Items("", R.drawable.row_first_color)
        val item2 = Items(audioPath1, R.drawable.blue_color_round)
        val item3 = Items(audioPath2, R.drawable.apricot_color_round)
        val item4 = Items(audioPath3, R.drawable.brown_color_round)
        val item5 = Items(audioPath4, R.drawable.pink_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList2(): List<Items>{
        val audioPath1 = fileUtils.copyRawFileToInternalStorage(R.raw.b_simple_hat_one)
        val audioPath2 = fileUtils.copyRawFileToInternalStorage(R.raw.b_eighths_hat_two)
        val audioPath3 = fileUtils.copyRawFileToInternalStorage(R.raw.b_busy_vibe_hat_three)
        val audioPath4 = fileUtils.copyRawFileToInternalStorage(R.raw.b_crazy_hat_four)

        val item1 = Items("", R.drawable.row_first_color)
        val item2 = Items(audioPath1, R.drawable.maroon_color_round)
        val item3 = Items(audioPath2, R.drawable.lavender_color_round)
        val item4 = Items(audioPath3, R.drawable.red_color_round)
        val item5 = Items(audioPath4, R.drawable.grey_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList3(): List<Items>{
        val audioPath1 = fileUtils.copyRawFileToInternalStorage(R.raw.c_welcome_to_the_matrix_lead_one)
        val audioPath2 = fileUtils.copyRawFileToInternalStorage(R.raw.c_simple_lead_two)
        val audioPath3 = fileUtils.copyRawFileToInternalStorage(R.raw.c_going_up_arp_lead_three)
        val audioPath4 = fileUtils.copyRawFileToInternalStorage(R.raw.c_telephone_lead_four)

        val item1 = Items("", R.drawable.row_first_color)
        val item2 = Items(audioPath1, R.drawable.yellow_color_round)
        val item3 = Items(audioPath2, R.drawable.magenta_color_round)
        val item4 = Items(audioPath3, R.drawable.olive_color_round)
        val item5 = Items(audioPath4, R.drawable.cyan_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList4(): List<Items>{
        val audioPath1 = fileUtils.copyRawFileToInternalStorage(R.raw.d_string_hit_fx_one)
        val audioPath2 = fileUtils.copyRawFileToInternalStorage(R.raw.d_siren_fx_two)
        val audioPath3 = fileUtils.copyRawFileToInternalStorage(R.raw.d_snare_fx_three)
        val audioPath4 = fileUtils.copyRawFileToInternalStorage(R.raw.d_hype_synth_fx_four)

        val item1 = Items("", R.drawable.row_first_color)
        val item2 = Items(audioPath1, R.drawable.orange_color_round)
        val item3 = Items(audioPath2, R.drawable.navy_color_round)
        val item4 = Items(audioPath3, R.drawable.green_color_round)
        val item5 = Items(audioPath4, R.drawable.teal_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }
}