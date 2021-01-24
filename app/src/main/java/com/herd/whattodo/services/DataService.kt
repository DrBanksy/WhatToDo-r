package com.herd.whattodo.services

import com.herd.whattodo.model.Category

object DataService {

    val topics = listOf<Category>(
        Category("Digital Activities","entertainment"),
        Category("Outdoor Activities", "outdoor"),
        Category("Activities for kids", "kid"),
        Category("Indoor Games", "gameideas")


    )


}