package com.example.artspace.data

import com.example.artspace.R
import com.example.artspace.model.Photo

class DataSource {
    fun loadGallery(): List<Photo> {
        return listOf<Photo>(
            Photo(title = "Testing Title 1", author = "Author 1", year = 2001, imageResourceId = R.drawable.acnh1),
            Photo(title = "Testing Title 2", author = "Author 2", year = 2002, imageResourceId = R.drawable.acnh1_1),
            Photo(title = "Testing Title 3", author = "Author 3", year = 2003, imageResourceId = R.drawable.acnh2),
            Photo(title = "Testing Title 4", author = "Author 4", year = 2004, imageResourceId = R.drawable.acnh2_2),
            Photo(title = "Testing Title 5", author = "Author 5", year = 2005, imageResourceId = R.drawable.acnh3),
            Photo(title = "Testing Title 6", author = "Author 6", year = 2006, imageResourceId = R.drawable.acnh3_3),
        )
    }
}