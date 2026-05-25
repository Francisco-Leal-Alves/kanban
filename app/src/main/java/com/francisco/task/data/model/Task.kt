package com.francisco.task.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task (
    var id: String,
    var description: String,
    val status: Status = Status.TODO

): Parcelable


