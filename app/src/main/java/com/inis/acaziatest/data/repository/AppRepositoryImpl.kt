package com.inis.acaziatest.data.repository

import com.inis.acaziatest.data.remote.AppApi
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(val appApi: AppApi) : AppRepository {
}