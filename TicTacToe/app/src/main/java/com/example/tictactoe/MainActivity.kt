package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var TURN_COUNT=0
    private var comp_count=0
    private var user_count=0
    private var Player=true
    lateinit var board:Array<Array<Button>>
    private var  boardStatus= Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(btn,btn1,btn2),
            arrayOf(btn3,btn4,btn5),
            arrayOf(btn6,btn7,btn8)
        )

      board.forEach {
          it.forEach {iti:Button->
              iti.setOnClickListener(this)
          }
      }


        reset.setOnClickListener{
            Player=true
            txt.text="Player X win"
            txt1.text=""
            for (i in 0..2){
                for (j in 0..2){
                    boardStatus[i][j]=-1
                    board[i][j].isEnabled=true
                    board[i][j].text=""

                }
            }
            TURN_COUNT=0
            turn(Player)
        }

        for (i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
                board[i][j].isEnabled=true
            }
        }


    }


    override fun onClick(v: View?) {

        when(v?.id){
            R.id.btn->{
                updateBoard(0,0,Player)

            }
            R.id.btn1->{
                updateBoard(0,1,Player)

            }
            R.id.btn2->{
                updateBoard(0,2,Player)

            }
            R.id.btn3->{
                updateBoard(1,0,Player)
            }
            R.id.btn4->{
                updateBoard(1,1,Player)
            }
            R.id.btn5->{
                updateBoard(1,2,Player)

            }
            R.id.btn6->{
                updateBoard(2,0,Player)

            }
            R.id.btn7->{
                updateBoard(2,1,Player)

            }
            R.id.btn8->{
                updateBoard(2,2,Player)
            }
        }
    }
    private fun winGame(m:Int,n: Int,player: Boolean) {

        var flag = true
        var flag4 = true
        var flag6=true
        var flag5 = true
        var flag1 = true
        var flag2 = true
        var flag3=true
        for (i in 0..2) {
            if (boardStatus[m][i] == 1) {

            } else {
                flag = false
                break
            }
        }
        for (i in 0..2) {
            if (boardStatus[m][i] == 2) {

            } else {
                flag4 = false
                break
            }
        }
        for (i in 0..2) {
            if (boardStatus[i][n] == 1) {

            } else {
                flag1 = false
                break
            }
        }
            for (i in 0..2) {
                if (boardStatus[i][n] == 2) {

                } else {
                    flag5 = false
                    break
                }
            }
                for (i in 0..2) {
                    if (boardStatus[i][i] == 1) {

                    } else {
                        flag2 = false
                        break
                    }

          }
        var count =2
        for (i in 0..2){
            if(boardStatus[i][count--]==1){
            }
            else{
                flag3=false
                break
            }
        }
        count=2
        for (i in 0..2){
            if(boardStatus[i][count--]==2){
            }
            else{
                flag6=false
                break
            }
        }
            var win = boardStatus[m][n]
            if (boardStatus[m][n] == 1 && (flag || flag1 || flag2 || flag4 || flag5||flag3||flag6)&&this.TURN_COUNT>0) {
                txt.text="Player X Win"
                disabled()
                playAgain()
            }
            else if (boardStatus[m][n] == 2 && (flag || flag1 || flag2 || flag4 || flag5||flag3||flag6)&&this.TURN_COUNT>0) {
                txt.text="Player 0 Win"
                disabled()
                playAgain()
                }
            else if(!(flag || flag1 || flag2 || flag4 || flag5||flag3||flag6)&&this.TURN_COUNT==9){
                txt.text="Match Draw"
                playAgain()
            }

    }
    private fun playAgain(){
        start.apply {
        isVisible=true
        isEnabled=true
        setOnClickListener{
            turn(Player)
            for (i in 0..2){
                for (j in 0..2){
                    boardStatus[i][j]=-1
                    board[i][j].isEnabled=true
                    board[i][j].text=""
                }
            }
            it.isVisible=false
            it.isEnabled=false
            TURN_COUNT=0
        }
    }
    }

    private fun disabled() {
        for (i in 0..2){
            for (j in 0..2){
                board[i][j].isEnabled=false
            }
        }
    }
    private fun turn(Player: Boolean){
        if (Player){
            txt.text="Player X turn"
        }
        else{
            txt.text="Player O turn"
        }
    }

    private fun updateBoard(i:Int,j:Int,Player:Boolean) {
        board[i][j].isEnabled = false
        TURN_COUNT++
        if(Player){
           board[i][j].text="X"
            this.Player =false
            boardStatus[i][j]=1
        }
        else{
          board[i][j].text="O"
            this.Player=true
            boardStatus[i][j]=2
        }
        this.Player=!Player
        turn(!Player)
        winGame(i,j,Player)

    }
}