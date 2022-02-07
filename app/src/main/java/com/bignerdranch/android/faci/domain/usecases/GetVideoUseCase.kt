package com.bignerdranch.android.faci.domain.usecases

import com.bignerdranch.android.faci.datalayer.remote.dto.video.VideoResponse
import com.bignerdranch.android.faci.domain.MovieRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(private val repository: MovieRepository) {

    fun getVideo(id: Int): Single<VideoResponse> {
        return repository.getVideo(id)
    }

}