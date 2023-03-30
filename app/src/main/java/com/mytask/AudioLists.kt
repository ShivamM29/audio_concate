package com.mytask

class AudioLists {
    fun itemLists(): List<List<Items>>{
        return listOf(rowList1(), rowList1(), rowList1(), rowList1(), rowList2(), rowList2(), rowList2(), rowList2(), rowList3(), rowList3(), rowList3(), rowList3(), rowList4(), rowList4(), rowList4(), rowList4())
    }

    private fun rowList1(): List<Items>{
        val item1 = Items(null, R.drawable.row_first_color)
        val item2 = Items(null, R.drawable.blue_color_round)
        val item3 = Items(null, R.drawable.apricot_color_round)
        val item4 = Items(null, R.drawable.brown_color_round)
        val item5 = Items(null, R.drawable.pink_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList2(): List<Items>{
        val item1 = Items(null, R.drawable.row_first_color)
        val item2 = Items(null, R.drawable.maroon_color_round)
        val item3 = Items(null, R.drawable.lavender_color_round)
        val item4 = Items(null, R.drawable.red_color_round)
        val item5 = Items(null, R.drawable.grey_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList3(): List<Items>{
        val item1 = Items(null, R.drawable.row_first_color)
        val item2 = Items(null, R.drawable.yellow_color_round)
        val item3 = Items(null, R.drawable.magenta_color_round)
        val item4 = Items(null, R.drawable.olive_color_round)
        val item5 = Items(null, R.drawable.cyan_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }

    private fun rowList4(): List<Items>{
        val item1 = Items(null, R.drawable.row_first_color)
        val item2 = Items(null, R.drawable.orange_color_round)
        val item3 = Items(null, R.drawable.navy_color_round)
        val item4 = Items(null, R.drawable.green_color_round)
        val item5 = Items(null, R.drawable.teal_color_round)

        return listOf(item1, item2, item3, item4, item5)
    }
}