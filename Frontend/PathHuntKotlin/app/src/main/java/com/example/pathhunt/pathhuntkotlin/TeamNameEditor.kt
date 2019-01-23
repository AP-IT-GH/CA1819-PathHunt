package com.example.pathhunt.pathhuntkotlin

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_name_editor.*

class TeamNameEditor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_name_editor)

        btnContinue.setOnClickListener {
            val team = Team(null, etxtTeamname.text.toString(), 0)
            prefs.teamScore=0
            prefs.nextStreet="Rinkvenstraat 2, 2910 Essen, Belgium"
            prefs.nextLocationId = 1
            prefs.nextLocation = "Van Oevelen"
            prefs.numberOfQuestions = 0
            prefs.currentQuestion = 0

            if(prefs.numberOfQuestions == 0){
                getLocNr()
            }

            CreateTeam(Api().urlTeams, team)
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun CreateTeam(URL: String ,team: Team) {
        URL.httpPost()
            .jsonBody(Gson().toJson(team))
            .response {_,_, result ->
                println("result: " + result.toString())
            }
    }

    fun getLocNr() {
        Api().urlLocations.httpGet().responseObject(Locatie.Deserializer()) { request, response, result ->
            val (locations, err) = result
            Log.d("LocNr", locations!!.size.toString())
            val locNr : Int = locations!!.size
            prefs.numberOfQuestions = locNr
            //Log.d("straatnaam",straatnaam)
        }
    }
}
