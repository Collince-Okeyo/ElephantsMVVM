package com.ramgdeveloper.elephantsmvvm.model

class Elephant : ArrayList<Elephant.ElephantItem>(){
     class ElephantItem(
        val __v: Int,
        val _id: String,
        val affiliation: String,
        val dob: String,
        val dod: String,
        val fictional: String,
        val image: String,
        val index: Int,
        val name: String,
        val note: String,
        val sex: String,
        val species: String,
        val wikilink: String
    )
}