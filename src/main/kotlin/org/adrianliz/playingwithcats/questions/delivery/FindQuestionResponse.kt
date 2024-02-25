package org.adrianliz.playingwithcats.questions.delivery

import com.fasterxml.jackson.annotation.JsonInclude

data class FindQuestionResponse(
    val id: String,
    @JsonInclude(value = JsonInclude.Include.NON_NULL) val expectedBreed: FindQuestionExpectedBreedResponse?,
    val status: String,
)
