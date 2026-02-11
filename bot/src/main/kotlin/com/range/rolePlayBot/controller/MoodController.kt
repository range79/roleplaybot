//package com.range.pierrotdiscorcdbot.controller
//
//import com.range.pierrotdiscorcdbot.enums.Mood
//import com.range.pierrotdiscorcdbot.service.MoodService
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class MoodController (
//    private val moodService: MoodService
//){
//    @GetMapping("/mood")
//    fun getMood(): Mood{
//        return moodService.mood()
//    }
//    @GetMapping("/suggest-how-make-happy")
//    fun suggestHowMakeHappy(): String{
//        return moodService.makeHappy()
//    }
//
// todo maybe one day i will learn about how to make frontend and implement this shi